package com.example.finalproject.services;

import com.example.finalproject.constants.Constants;
import com.example.finalproject.models.Credit;
import com.example.finalproject.models.CreditScore;
import com.example.finalproject.models.User;
import com.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UserService implements IUserService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private CreditService creditService;
    private CreditScoreService creditScoreService;

    @Autowired
    public UserService(UserRepository userRepository,
                       CreditService creditService,
                       CreditScoreService creditScoreService) {
        this.userRepository = userRepository;
        this.creditService = creditService;
        this.creditScoreService = creditScoreService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            logger.error("ERROR in findById - An error occurred - user does not exist!");
            throw new IllegalArgumentException("An error occurred - user does not exist!");
        }
    }


    @Override
    public void saveToDatabase(User user) {

        validateIdentityNumber(user.getIdentityNumber());

        CreditScore creditScore = new CreditScore(getCreditScore(user));
        creditScore.setUser(user);
        user.setCreditScore(creditScore);

        creditScoreService.saveToDatabase(creditScore);
        userRepository.save(user);

        logger.info("INFO in saveToDatabase - user with user id: {} and credit score: {} saved", user.getId() , creditScore.getCreditScore());

    }

    @Override
    public void deleteFromDatabase(User user) {
        deleteFromDatabase(user.getId()); // Delete from database by id method is called here in order to return the 500 response which includes systems message
    }

    @Override
    public void deleteFromDatabase(int id) {
        try {
            userRepository.deleteById(id);
            logger.info("INFO in deleteFromDatabase - user with id: {} is deleted.", id);
        } catch (Exception e) {
            logger.error("ERROR in deleteFromDatabase - An error occurred - user does not exist!");
            throw new IllegalArgumentException("An error occurred - user does not exist!");
        }
    }

    @Override
    public void updateOnDatabase(User user, int id) {

        validateIdentityNumber(user.getIdentityNumber());

        User existingUser = userRepository.findById(id).get();

        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setIdentityNumber(user.getIdentityNumber());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setMonthlyIncome(user.getMonthlyIncome());

            //user.getCreditScore().setCreditScore(getCreditScore(user));
            //user.getCreditScore().setUser(existingUser);
            //existingUser.setCreditScore(user.getCreditScore());
            existingUser.getCreditScore().setCreditScore(getCreditScore(user));
            userRepository.save(existingUser);

            logger.info("INFO in updateOnDatabase - user with id: {} is updated.", existingUser.getId());
        }
    }

    @Override
    public String applyCredit(int id){

        Credit credit = null;
        String notificationMessage;
        boolean isCreditAlreadyExist = false;

        try {
            credit = calculateCreditLimit(id);

            if (credit == null){
                notificationMessage = "Insufficient credit score";
            } else if(findById(id).getCredit() == null){
                creditService.saveToDatabase(credit);
                findById(id).setCredit(credit);

                notificationMessage = "Credit score is sufficient and calculated credit limit as: {}" + credit.getCreditLimit();
            } else {
                notificationMessage = "Credit already exists.";
                isCreditAlreadyExist = true;
            }

            logger.info("INFO in applyCredit - result: {}", notificationMessage);

        } catch (NullPointerException | NoSuchElementException e) {
            logger.error("ERROR in applyCredit - An error occurred - user and/or credit record not exist!");
            throw new IllegalArgumentException("An error occurred - user and/or credit record not exist!");
        }

        sendSMSNotification(id, notificationMessage);//sends sms notification to user with the specified notification message.

        return  credit == null ? "Rejected. " + notificationMessage : (isCreditAlreadyExist ? notificationMessage : "Approved. " + "Your credit limit: " + credit.getCreditLimit() );
    }

    @Override
    public String checkCreditStatus(String identityNumber){ // This method is for an endpoint.

        validateIdentityNumber(identityNumber);

        try {
            User user = userRepository.findByIdentityNumber(identityNumber);
            logger.info("INFO in checkCreditStatus - successful");
            return "Credit amount: " + String.valueOf(user.getCredit().getCreditLimit()); // toString method didnt used here in order not to return database id to the user.
        }
        catch (NullPointerException | NoSuchElementException e) {
            logger.error("ERROR in checkCreditStatus - An error occurred - user and/or credit record not exist!");
            throw new IllegalArgumentException("An error occurred - user and/or credit record not exist!");
        }

    }

    private void validateIdentityNumber(String identityNumber) {    // This private method checks if the entered identity number is valid or not
        char lastCharacterOfIdentity = identityNumber.charAt(identityNumber.length() - 1);
        if(lastCharacterOfIdentity % 2 != 0){
            logger.error("ERROR in validateIdentityNumber - An error occurred - identity number must end with even digit.");
            throw new IllegalArgumentException("An error occurred - identity number must end with even digit.");
        }
}


    private Credit calculateCreditLimit(int id) {   // This private method is used to calculate credit limit with the specified criteria for a user.

        Credit userCredit = new Credit();

        userCredit.setUser(findById(id));

        double monthlyIncome = findById(id).getMonthlyIncome();

        double creditScoreOfUser = findById(id).getCreditScore().getCreditScore();

        if (Constants.LOWER_CREDIT_LIMIT > creditScoreOfUser){
            return null;
        }
        else if(Constants.UPPER_CREDIT_LIMIT> creditScoreOfUser){

            if (monthlyIncome < Constants.LOWER_INCOME_BOUND){
                userCredit.setCreditLimit(Constants.LOW_CREDIT_LIMIT);
            }
            else{
                userCredit.setCreditLimit(Constants.HIGH_CREDIT_LIMIT);
            }
            System.out.println("Credit limit is {} " + userCredit.getCreditLimit()); // LOGGER
        }
        else {
            userCredit.setCreditLimit(monthlyIncome * Constants.CREDIT_LIMIT_FACTOR);
            System.out.println("Credit limit is {} " + userCredit.getCreditLimit()); // LOGGER
        }

        return userCredit;
    }

    private void sendSMSNotification(int id, String message) {  // pretender method
        System.out.println(message);
    }

    private double getCreditScore(User user){   // This private method is used to calculate credit score of user.
        char lastCharacterOfIdentity = user.getIdentityNumber().charAt(user.getIdentityNumber().length() - 1);
        return Constants.getScore(lastCharacterOfIdentity);
    }

}

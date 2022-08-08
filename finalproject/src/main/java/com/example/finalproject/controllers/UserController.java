package com.example.finalproject.controllers;

import com.example.finalproject.models.User;
import com.example.finalproject.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final IUserService userService;



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        User user;
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            userService.saveToDatabase(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            userService.updateOnDatabase(user, id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>("Data updated on Database", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        try {
            userService.deleteFromDatabase(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody User user) {
        try {
            userService.deleteFromDatabase(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @GetMapping("/applyCredit/{id}")                //me
    public ResponseEntity<String> applyCredit(@PathVariable int id){

        String result = null;

        try {
            result = userService.applyCredit(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/checkCreditStatus/{identityNumber}")
    public ResponseEntity<String> checkCreditStatus(@PathVariable String identityNumber){
        String result = null;
        try {
            result = userService.checkCreditStatus(identityNumber);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);   // Not found error is being sent to the user by backend itself to avoid exposing sensitive information
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

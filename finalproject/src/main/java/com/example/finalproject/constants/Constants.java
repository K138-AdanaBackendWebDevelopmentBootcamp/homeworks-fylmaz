package com.example.finalproject.constants;


public class Constants {    //This class is used to store constants that are used in the application.
    public static final double LOWER_CREDIT_LIMIT = 500;
    public static final double UPPER_CREDIT_LIMIT = 1000;

    public static final double LOWER_INCOME_BOUND = 5000;

    public static final double LOW_CREDIT_LIMIT = 10000;
    public static final double HIGH_CREDIT_LIMIT = 20000;

 //  @Transient
    public static final double CREDIT_LIMIT_FACTOR = 4;

    private static final double SCORE_TWO = 550;
    private static final double SCORE_FOUR = 1000;
    private static final double SCORE_SIX = 400;
    private static final double SCORE_EIGHT = 900;
    private static final double SCORE_ZERO = 2000;

    public static final double getScore(char score) {
        switch (score) {
            case '2':
                return SCORE_TWO;
            case '4':
                return SCORE_FOUR;
            case '6':
                return SCORE_SIX;
            case '8':
                return SCORE_EIGHT;
            case '0':
                return SCORE_ZERO;
            default:
                return 0;
        }
    }
}

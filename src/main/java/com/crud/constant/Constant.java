package com.crud.constant;

public interface Constant {

    class Response {
        public static final int SUCCESS_CODE = 200;
        public static final String SUCCESS_MESSAGE = "Success";
    }

    class Message {
        public static final String EXIST_DATA_MESSAGE = "data already exist";
        public static final String NOT_FOUND_DATA_MESSAGE = "data not found";
        public static final String FORBIDDEN_REQUEST_MESSAGE = "Different {value} with exist data is forbidden";
    }

    class Regex {
        public static final String NUMERIC = "\\d+";
        public static final String ALPHANUMERIC = "^[a-zA-Z0-9]+$";
        public static final String ALPHABET = "^[a-zA-Z]+$";
        public static final String ALPHANUMERIC_WITH_DOT_AND_SPACE = "^[a-zA-Z0-9.' ]+$";
    }

}
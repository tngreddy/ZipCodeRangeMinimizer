package com.wsi.zipcodes.exception;
/********************************************************************************************
 * Name			: InvalidInputException.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A custom Exception which is primarily thrown when the Input request is Invalid
 *******************************************************************************************/
public class InvalidInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidInputException(String message) {
        super(message);
    }
}

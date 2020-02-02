package com.wsi.zipcodes.bean;
/********************************************************************************************
 * Name			: Error.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A bean class for holding Errors
 *******************************************************************************************/
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    private String errorMessage;
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.wsi.zipcodes.validator;

/********************************************************************************************
 * Name			: ZipCodeRangeValidator.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A validator class which provide methods to validate the input request.
 *******************************************************************************************/
import com.wsi.zipcodes.bean.ZipCodeRanges;
import com.wsi.zipcodes.exception.InvalidInputException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.Objects;
import static com.wsi.zipcodes.bean.CommonConstants.*;

@Component
public class ZipCodeRangeValidator {

    /**
     * Method to validate the Input list of zipcode ranges
     * @param zipCodeRanges
     * Here are the conditions validated
     * - Null/empty list
     * - Null range array
     * - Range Array length should be 2
     * - Range should not contain null values
     * - Range values should be of 5 digits
     * - LowerBound should be greater than the UpperBound in the range
     */
    public void validateInput(ZipCodeRanges zipCodeRanges) {
        if (Objects.isNull(zipCodeRanges)
                || CollectionUtils.isEmpty(zipCodeRanges.getZipCodeRangeList())
                || zipCodeRanges.getZipCodeRangeList()
                .stream()
                .anyMatch(range -> Objects.isNull(range) ||
                        range.length != ARRAY_LENGTH ||
                        Objects.isNull(range[LOWER_BOUND]) ||
                        Objects.isNull(range[UPPER_BOUND]) ||
                        String.valueOf(range[LOWER_BOUND]).length() != NUMBER_LENGTH ||
                        String.valueOf(range[UPPER_BOUND]).length() != NUMBER_LENGTH ||
                        range[LOWER_BOUND] > range[UPPER_BOUND])) {
            throw new InvalidInputException(ERROR_MESSAGE);
        }
    }
}

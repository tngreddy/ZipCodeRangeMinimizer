package com.wsi.zipcodes.service;
/********************************************************************************************
 * Name			: ZipCodeService.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A service interface which provides methods for processing the list of zipcode ranges
 *******************************************************************************************/
import java.util.List;

public interface ZipCodeService {

    /**
     * Method to process the provided zipcode ranges list and return the list of minimized zipcode ranges.
     * @param zipcodeRanges - list of zipCode ranges(range - array of two zipcodes)
     * @return list of minimized zipcode ranges.
     *
     * Example:
     * Sample Input = [94133,94133] [94200,94299] [94600,94699]
     *
     * Sample Output = [94133,94133] [94200,94299] [94600,94699]
     *
     *
     * Sample Input = [94133,94133] [94200,94299] [94226,94399]
     *
     * Sample Output = [94133,94133] [94200,94399]
     */

    List<Integer[]> processZipCodeRanges(List<Integer[]> zipcodeRanges);
}

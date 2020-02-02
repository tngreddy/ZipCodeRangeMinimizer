package com.wsi.zipcodes.service.impl;
/********************************************************************************************
 * Name			: ZipCodeServiceImpl.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: An Implementation class for the ZipCodeService interface which provides the
                  implementaion processing the list of zipcode ranges
 *******************************************************************************************/
import com.wsi.zipcodes.service.ZipCodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.wsi.zipcodes.bean.CommonConstants.*;
import static java.lang.Math.max;

import java.util.*;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {

    /**
     * Method to process the provided zipcode ranges list and return the list of minimized zipcode ranges.
     *
     * @param zipcodeRanges - list of zipCode ranges(range - array of two zipcodes)
     * @return list of minimized zipcode ranges.
     * <p>
     * Example:
     * Sample Input = [94133,94133] [94200,94299] [94600,94699]
     * <p>
     * Sample Output = [94133,94133] [94200,94299] [94600,94699]
     * <p>
     * <p>
     * Sample Input = [94133,94133] [94200,94299] [94226,94399]
     * <p>
     * Sample Output = [94133,94133] [94200,94399]
     */

    @Override
    public List<Integer[]> processZipCodeRanges(List<Integer[]> zipcodeRanges) {

        List<Integer[]> minimizedZipCodeRanges = new ArrayList<>();

        if (!CollectionUtils.isEmpty(zipcodeRanges)) {

            //sorting the list based on the lower bound of each zipcode range array
            zipcodeRanges.sort(Comparator.comparingInt(range -> range[LOWER_BOUND]));

            Integer[] currentZipCodeRange = zipcodeRanges.get(0);

            // Iterating and merging the two consecutive zipcode ranges if applicable
            for (int i = 0; i < zipcodeRanges.size() - 1; i++) {
                Integer[] nextZipCodeRange = zipcodeRanges.get(i + 1);
                if (currentZipCodeRange[UPPER_BOUND] >= nextZipCodeRange[LOWER_BOUND]
                        || currentZipCodeRange[UPPER_BOUND] + 1 == nextZipCodeRange[LOWER_BOUND]) {
                    currentZipCodeRange[UPPER_BOUND] = max(currentZipCodeRange[UPPER_BOUND], nextZipCodeRange[UPPER_BOUND]);
                } else {
                    minimizedZipCodeRanges.add(currentZipCodeRange);
                    currentZipCodeRange = nextZipCodeRange;
                }
            }
            minimizedZipCodeRanges.add(currentZipCodeRange);
        }
        return minimizedZipCodeRanges;

    }
}

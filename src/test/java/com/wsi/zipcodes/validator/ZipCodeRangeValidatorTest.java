package com.wsi.zipcodes.validator;

import com.wsi.zipcodes.bean.CommonConstants;
import com.wsi.zipcodes.bean.ZipCodeRanges;
import com.wsi.zipcodes.exception.InvalidInputException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZipCodeRangeValidatorTest {

    private ZipCodeRangeValidator zipCodeRangeValidator;

    @BeforeAll
    public void setUp() {
        zipCodeRangeValidator = new ZipCodeRangeValidator();
    }

    @Test
    public void testValidateInput() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94134});
        zipCodeRangeList.add(new Integer[]{94122, 94133});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        zipCodeRangeValidator.validateInput(new ZipCodeRanges(zipCodeRangeList));

    }

    @Test
    public void testValidateInputInvalidArrayLength() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);
    }

    @Test
    public void testValidateInputNullLowerBound() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{null, 94133});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);

    }

    @Test
    public void testValidateInputNullUpperBound() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, null});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);
    }

    @Test
    public void testValidateInputInvalidLowerBound() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{133, 94133});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);
    }

    @Test
    public void testValidateInputInvalidUpperBound() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 133});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);
    }

    @Test
    public void testValidateInputInvalidRange() {
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94133});
        zipCodeRangeList.add(new Integer[]{94300, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        validateRequest(zipCodeRangeList);
    }

    private void validateRequest(List<Integer[]> zipCodeRangeList) {
        Exception exception = assertThrows(InvalidInputException.class, () -> zipCodeRangeValidator.validateInput(new ZipCodeRanges(zipCodeRangeList)));
        assertNotNull(exception);
        assertEquals(CommonConstants.ERROR_MESSAGE, exception.getMessage());
    }
}

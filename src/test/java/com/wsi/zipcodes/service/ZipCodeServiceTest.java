package com.wsi.zipcodes.service;
/********************************************************************************************
 * Name			: ZipCodeServiceTest.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A test class for testing the implemented methods of ZipCodeService interface
 *******************************************************************************************/
import com.wsi.zipcodes.service.impl.ZipCodeServiceImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZipCodeServiceTest {

    private ZipCodeService zipCodeService;

    @BeforeAll
    public void beforeAll() {
       zipCodeService = new ZipCodeServiceImpl();
    }

    @Test
    public void testProcessZipCodeRangesInput1(){
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94133});
        zipCodeRangeList.add(new Integer[]{94122, 94134});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRangeList);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(2, minimizedZipCodeRanges.size());
        assertArrayEquals(new Integer[]{94122,94134},minimizedZipCodeRanges.get(0));
        assertArrayEquals(new Integer[]{94226,94399},minimizedZipCodeRanges.get(1));
    }

    @Test
    public void testProcessZipCodeRangesInput2(){
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94133});
        zipCodeRangeList.add(new Integer[]{94200, 94299});
        zipCodeRangeList.add(new Integer[]{94600, 94699});
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRangeList);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(3, minimizedZipCodeRanges.size());
        assertArrayEquals(new Integer[]{94133, 94133},minimizedZipCodeRanges.get(0));
        assertArrayEquals(new Integer[]{94200, 94299},minimizedZipCodeRanges.get(1));
        assertArrayEquals(new Integer[]{94600, 94699},minimizedZipCodeRanges.get(2));
    }

    @Test
    public void testProcessZipCodeRangesInput3(){
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94133});
        zipCodeRangeList.add(new Integer[]{94200, 94299});
        zipCodeRangeList.add(new Integer[]{94226,94399});
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRangeList);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(2, minimizedZipCodeRanges.size());
        assertArrayEquals(new Integer[]{94133, 94133},minimizedZipCodeRanges.get(0));
        assertArrayEquals(new Integer[]{94200, 94399},minimizedZipCodeRanges.get(1));
    }

    @Test
    public void testProcessZipCodeRangesInput4(){
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94133});
        zipCodeRangeList.add(new Integer[]{94226, 94399});
        zipCodeRangeList.add(new Integer[]{94200, 94299});
        zipCodeRangeList.add(new Integer[]{94420, 94500});
        zipCodeRangeList.add(new Integer[]{94110, 94300});
        zipCodeRangeList.add(new Integer[]{94200, 94600});
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRangeList);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(1, minimizedZipCodeRanges.size());
        assertArrayEquals(new Integer[]{94110, 94600},minimizedZipCodeRanges.get(0));
    }

    @Test
    public void testProcessZipCodeRangesInput5(){
        List<Integer[]> zipCodeRangeList = new ArrayList<>();
        zipCodeRangeList.add(new Integer[]{94133, 94179});
        zipCodeRangeList.add(new Integer[]{94200, 94299});
        zipCodeRangeList.add(new Integer[]{94180, 94399});
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRangeList);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(1, minimizedZipCodeRanges.size());
        assertArrayEquals(new Integer[]{94133,94399},minimizedZipCodeRanges.get(0));
    }

    @Test
    public void testProcessZipCodeRangesNullInput(){
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(null);
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(0, minimizedZipCodeRanges.size());
    }

    @Test
    public void testProcessZipCodeRangesEmptyInput(){
        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(new ArrayList<>());
        assertNotNull(minimizedZipCodeRanges);
        assertEquals(0, minimizedZipCodeRanges.size());
    }
}

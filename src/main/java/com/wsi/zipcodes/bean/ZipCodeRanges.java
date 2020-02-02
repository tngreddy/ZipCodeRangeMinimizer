package com.wsi.zipcodes.bean;
/********************************************************************************************
 * Name			: ZipCodeRanges.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A bean class which holds the list of ZipCode Ranges
 *******************************************************************************************/
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ZipCodeRanges {
    private List<Integer[]> zipCodeRangeList;
    public ZipCodeRanges(List<Integer[]> zipCodeRangeList) {
        this.zipCodeRangeList = zipCodeRangeList;
    }
}

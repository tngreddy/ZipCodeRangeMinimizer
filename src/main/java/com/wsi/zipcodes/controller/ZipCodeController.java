package com.wsi.zipcodes.controller;
/********************************************************************************************
 * Name			: ZipCodeController.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: Controller class to handle the request to minimize the list of ZipCode Ranges provided
 *******************************************************************************************/
import com.wsi.zipcodes.bean.ZipCodeRanges;
import com.wsi.zipcodes.service.ZipCodeService;
import com.wsi.zipcodes.validator.ZipCodeRangeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.wsi.zipcodes.bean.CommonConstants.*;

@RestController
@RequestMapping(ZIPCODE)
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @Autowired
    private ZipCodeRangeValidator zipCodeRangeValidator;

    /**
     * A Controller method which handles the POST request with a payload of ZipCode Ranges list.
     * @param zipCodeRanges
     * @return List of minimized zipcode Ranges list (Produces in default JSON format)
     */
    @PostMapping
    ZipCodeRanges processZipCodeRanges(@RequestBody ZipCodeRanges zipCodeRanges) {

        zipCodeRangeValidator.validateInput(zipCodeRanges);

        List<Integer[]> minimizedZipCodeRanges = zipCodeService.processZipCodeRanges(zipCodeRanges.getZipCodeRangeList());
        zipCodeRanges.setZipCodeRangeList(minimizedZipCodeRanges);

        return zipCodeRanges;
    }
}

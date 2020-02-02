package com.wsi.zipcodes.controller;
/********************************************************************************************
 * Name			: ZipCodeControllerTest.java
 * Created By	: Gowtham Tiyyagura
 * Project Name	: ZipCodeRangeMinimizer
 * Description	: A test class for testing the methods in ZipCodeController
 *******************************************************************************************/
import com.wsi.zipcodes.bean.CommonConstants;
import com.wsi.zipcodes.exception.GlobalExceptionHandler;
import com.wsi.zipcodes.exception.InvalidInputException;
import com.wsi.zipcodes.service.ZipCodeService;
import com.wsi.zipcodes.validator.ZipCodeRangeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZipCodeControllerTest {

    private MockMvc mockMvc;

    @Mock
    ZipCodeService zipCodeService;

    @Mock
    ZipCodeRangeValidator zipCodeRangeValidator;

    @InjectMocks
    ZipCodeController zipCodeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc =  MockMvcBuilders.standaloneSetup(zipCodeController).setControllerAdvice(GlobalExceptionHandler.class).build();

    }

    @Test
    public void testProcessZipCodeRanges() throws Exception {

        String payload = "{\n" +
                "        \"zipCodeRangeList\" :[\n" +
                "\t\t[94133, 94133],\n" +
                "\t\t[94226, 94399],\n" +
                "\t\t[94200, 94299],\n" +
                "\t\t[94420, 94500],\n" +
                "\t\t[94110, 94300],\n" +
                "\t\t[94200, 94600]\n" +
                "\t\t]\n" +
                "    }";

        List<Integer[]> minimizedZipCodeRangeList = new ArrayList<>();
        minimizedZipCodeRangeList.add(new Integer[]{94110, 94600});

        when(zipCodeService.processZipCodeRanges(anyList())).thenReturn(minimizedZipCodeRangeList);

        mockMvc.perform(post("/zipcode")
                .content(payload)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.zipCodeRangeList", is(notNullValue())))
                .andExpect(jsonPath("$.zipCodeRangeList").isArray())
                .andExpect(jsonPath("$.zipCodeRangeList", hasSize(1)))
                .andExpect(jsonPath("$.zipCodeRangeList[0]").isArray())
                .andExpect(jsonPath("$.zipCodeRangeList[0][0]").value(94110))
                .andExpect(jsonPath("$.zipCodeRangeList[0][1]").value(94600));
    }

    @Test
    public void testProcessZipCodeRangesInvalidRequest() throws Exception {

        String payload = "{\n" +
                "        \"zipCodeRangeList\" :[\n" +
                "\t\t[933, 94133],\n" +
                "\t\t[94226, 94399],\n" +
                "\t\t[94200, 94600]\n" +
                "\t\t]\n" +
                "    }";

        List<Integer[]> minimizedZipCodeRangeList = new ArrayList<>();
        minimizedZipCodeRangeList.add(new Integer[]{94110, 94600});

        doThrow(new InvalidInputException(CommonConstants.ERROR_MESSAGE)).when(zipCodeRangeValidator).validateInput(any());
        when(zipCodeService.processZipCodeRanges(anyList())).thenReturn(minimizedZipCodeRangeList);

        mockMvc.perform(post("/zipcode")
                .content(payload)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorMessage").value(CommonConstants.ERROR_MESSAGE));
    }
}

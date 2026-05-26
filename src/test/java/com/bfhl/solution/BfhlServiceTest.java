package com.bfhl.solution;

import com.bfhl.solution.dto.BfhlRequest;
import com.bfhl.solution.dto.BfhlResponse;
import com.bfhl.solution.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl bfhlServiceInstance;

    @BeforeEach
    void initSetup() {
        bfhlServiceInstance = new BfhlServiceImpl();
        ReflectionTestUtils.setField(bfhlServiceInstance, "candidateFullName",   "john doe");
        ReflectionTestUtils.setField(bfhlServiceInstance, "candidateDob",        "17091999");
        ReflectionTestUtils.setField(bfhlServiceInstance, "candidateEmail",      "john@xyz.com");
        ReflectionTestUtils.setField(bfhlServiceInstance, "candidateRollNum", "0827CS231276");
    }

    @Test
    void executeTestCaseA() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);

        assertTrue(testRes.isSuccess());
        assertEquals("john_doe_17091999", testRes.getUserId());
        assertEquals(List.of("1"),        testRes.getOddNumbers());
        assertEquals(List.of("334","4"),  testRes.getEvenNumbers());
        assertEquals(List.of("A","R"),    testRes.getAlphabets());
        assertEquals(List.of("$"),        testRes.getSpecialCharacters());
        assertEquals("339",              testRes.getSum());
        assertEquals("Ra",               testRes.getConcatString());
    }

    @Test
    void executeTestCaseB() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(Arrays.asList("2","a","y","4","&","-","*","5","92","b"));

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);

        assertTrue(testRes.isSuccess());
        assertEquals(List.of("5"),           testRes.getOddNumbers());
        assertEquals(List.of("2","4","92"),  testRes.getEvenNumbers());
        assertEquals(List.of("A","Y","B"),   testRes.getAlphabets());
        assertEquals(List.of("&","-","*"),   testRes.getSpecialCharacters());
        assertEquals("103",                  testRes.getSum());
        assertEquals("ByA",                  testRes.getConcatString());
    }

    @Test
    void executeTestCaseC() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(Arrays.asList("A","ABCD","DOE"));

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);

        assertTrue(testRes.isSuccess());
        assertEquals(List.of(),              testRes.getOddNumbers());
        assertEquals(List.of(),              testRes.getEvenNumbers());
        assertEquals(List.of("A","ABCD","DOE"), testRes.getAlphabets());
        assertEquals(List.of(),              testRes.getSpecialCharacters());
        assertEquals("0",                    testRes.getSum());
        assertEquals("EoDdCbAa",             testRes.getConcatString());
    }

    @Test
    void checkEmptyDataScenario() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(List.of());

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);
        assertEquals("0", testRes.getSum());
        assertEquals("",  testRes.getConcatString());
    }

    @Test
    void checkSpecialCharScenario() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(Arrays.asList("@","#","!"));

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);

        assertEquals(List.of(),              testRes.getAlphabets());
        assertEquals(List.of(),              testRes.getOddNumbers());
        assertEquals(List.of(),              testRes.getEvenNumbers());
        assertEquals(List.of("@","#","!"),   testRes.getSpecialCharacters());
        assertEquals("0",                    testRes.getSum());
        assertEquals("",                     testRes.getConcatString());
    }

    @Test
    void checkUserIdFormatScenario() {
        BfhlRequest testReq = new BfhlRequest();
        testReq.setData(List.of("1"));

        BfhlResponse testRes = bfhlServiceInstance.processData(testReq);
        assertEquals("john_doe_17091999", testRes.getUserId());
    }
}

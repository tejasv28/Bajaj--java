package com.bfhl.solution.service.impl;

import com.bfhl.solution.dto.BfhlRequest;
import com.bfhl.solution.dto.BfhlResponse;
import com.bfhl.solution.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${candidate.full_name}")
    private String candidateFullName;

    @Value("${candidate.dob}")
    private String candidateDob;

    @Value("${candidate.email}")
    private String candidateEmail;

    @Value("${candidate.roll_number}")
    private String candidateRollNum;

    @Override
    public BfhlResponse processData(BfhlRequest req) {
        List<String> inputData = req.getData();

        List<String> odds = new ArrayList<>();
        List<String> evens = new ArrayList<>();
        List<String> alphaList = new ArrayList<>();
        List<String> specialsList = new ArrayList<>();
        long calculatedSum = 0;

        for (String element : inputData) {
            if (checkIfNumeric(element)) {
                long numericValue = Long.parseLong(element);
                calculatedSum += numericValue;
                if (numericValue % 2 == 0) {
                    evens.add(element);
                } else {
                    odds.add(element);
                }
            } else if (checkIfAlphabetic(element)) {
                alphaList.add(element.toUpperCase());
            } else {
                specialsList.add(element);
            }
        }

        BfhlResponse apiResponse = new BfhlResponse();
        apiResponse.setSuccess(true);
        apiResponse.setUserId(generateUserId());
        apiResponse.setEmail(candidateEmail);
        apiResponse.setRollNumber(candidateRollNum);
        apiResponse.setOddNumbers(odds);
        apiResponse.setEvenNumbers(evens);
        apiResponse.setAlphabets(alphaList);
        apiResponse.setSpecialCharacters(specialsList);
        apiResponse.setSum(String.valueOf(calculatedSum));
        apiResponse.setConcatString(createAlternateConcat(alphaList));

        return apiResponse;
    }

    private String generateUserId() {
        String baseName = candidateFullName.trim().toLowerCase().replace(" ", "_");
        return baseName + "_" + candidateDob;
    }

    private boolean checkIfNumeric(String val) {
        if (val == null || val.isEmpty()) return false;
        try {
            Long.parseLong(val);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean checkIfAlphabetic(String val) {
        if (val == null || val.isEmpty()) return false;
        for (char ch : val.toCharArray()) {
            if (!Character.isLetter(ch)) return false;
        }
        return true;
    }

    private String createAlternateConcat(List<String> alphaList) {
        if (alphaList.isEmpty()) return "";

        StringBuilder combinedString = new StringBuilder();
        for (String alpha : alphaList) {
            combinedString.append(alpha.toUpperCase());
        }

        String flippedString = combinedString.reverse().toString();

        StringBuilder finalResult = new StringBuilder();
        for (int idx = 0; idx < flippedString.length(); idx++) {
            char currChar = flippedString.charAt(idx);
            if (idx % 2 == 0) {
                finalResult.append(Character.toUpperCase(currChar));
            } else {
                finalResult.append(Character.toLowerCase(currChar));
            }
        }

        return finalResult.toString();
    }
}

package com.bfhl.solution.service;

import com.bfhl.solution.dto.BfhlRequest;
import com.bfhl.solution.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse processData(BfhlRequest request);
}

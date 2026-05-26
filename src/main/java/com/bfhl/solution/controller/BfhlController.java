package com.bfhl.solution.controller;

import com.bfhl.solution.dto.BfhlRequest;
import com.bfhl.solution.dto.BfhlResponse;
import com.bfhl.solution.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(@RequestBody BfhlRequest request) {
        if (request.getData() == null || request.getData().isEmpty()) {
            BfhlResponse err = new BfhlResponse();
            err.setSuccess(false);
            return ResponseEntity.badRequest().body(err);
        }
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}

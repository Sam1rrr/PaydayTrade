package com.gradiant.paydaytrade.common;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Builder
public class ApiResponse  {
    private final boolean success;
    private final String msg;

    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}

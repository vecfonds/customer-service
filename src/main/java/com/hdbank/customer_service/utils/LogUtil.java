package com.hdbank.customer_service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogUtil {
    private final ObjectMapper objectMapper;

    public void logHelper(HttpServletRequest http, Object request, Object response) {
        try {
            String method = http.getMethod();
            String requestJson = objectMapper.writeValueAsString(request);
            String responseJson = objectMapper.writeValueAsString(response);

            log.info("LogHelper: method={}, request={}, response={}", method, requestJson, responseJson);
        } catch (Exception e) {
            log.error("Error logging HTTP request and response", e);
        }
    }

    /**
     * Log fetching
     */
    public static void logFetching(Logger log, String type, String details) {
        log.debug("Fetching {} for {}", type, details);
    }

    /**
     * Log result
     */
    public static void logResult(Logger log, String type, String result, String details) {
        if (StringUtils.isEmpty(result)) {
            log.info("Request to {} (No data found) for {}", type, details);
        } else {
            log.info("Request to {} completed. Details: {}, Result: {}", type, details, result);
        }
    }

    /**
     * Log error
     */
    public static void logError(Logger log, String type, String details, Throwable e) {
        log.error("Request to {} failed for {}", type, details, e);
    }
}

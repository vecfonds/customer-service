package com.hdbank.customer_service.shared.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SharedConstant {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ApiRequestContextKey {

        public static final String TRACE_ID = "X-B3-TraceId";
        public static final String REQUEST_ID = "request_context_id";
        public static final String REQUEST_URL = "request_url";
        public static final String REQUEST_METHOD = "request_method";
        public static final String IS_MULTIPART_REQUEST = "is_multipart-request";

    }
}

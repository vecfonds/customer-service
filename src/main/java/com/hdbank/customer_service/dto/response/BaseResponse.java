package com.hdbank.customer_service.dto.response;

import com.hdbank.customer_service.shared.enumeration.ResponseEnum;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String responseCode = "0000";
    private String message = "Success";
    private String timestamp = Instant.now().toString();
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public static <O> BaseResponse<O> of(ResponseEnum responseEnum) {
        BaseResponse<O> res = new BaseResponse<>();
        res.setMessage(responseEnum.getMessage());
        res.setResponseCode(responseEnum.getResponseCode());
        return res;
    }

    public static BaseResponse<String> of() {
        return new BaseResponse<>();
    }
}

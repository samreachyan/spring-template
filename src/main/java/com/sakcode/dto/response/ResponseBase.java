package com.sakcode.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBase<T> {
    private Integer status;
    private String message;
    private T data;
}

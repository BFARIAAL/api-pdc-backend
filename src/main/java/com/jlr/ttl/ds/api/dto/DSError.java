package com.jlr.ttl.ds.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DSError {
    private String field;
    private String errorMessage;
}

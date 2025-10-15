package com.guihang.medicalbackend.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
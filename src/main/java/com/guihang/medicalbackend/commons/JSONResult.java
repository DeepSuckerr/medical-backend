package com.guihang.medicalbackend.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JSONResult implements Serializable {

    private Integer code;
    private String message;
    private Object data;


    public JSONResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JSONResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

}

package com.gasen.rpccore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GASEN
 * @date 2024/3/29 10:28
 * @classType description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class rpcResponse implements Serializable {
    private Object data;
    private Class<?> dataType;
    private String message;
    private Exception exception;
}

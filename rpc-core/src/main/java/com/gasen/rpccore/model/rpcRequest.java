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
public class rpcRequest implements Serializable {
    private String serviceName;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}

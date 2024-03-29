package com.gasen.rpccore.serializer;

import java.io.IOError;
import java.io.IOException;

/**
 * @author GASEN
 * @date 2024/3/29 10:15
 * @classType description
 */
public interface Serializer {

    <T> byte[] serialize(T obj) throws IOException;

    <T> T deserialize(byte[] data, Class<T> clazz) throws IOException, ClassNotFoundException;

}

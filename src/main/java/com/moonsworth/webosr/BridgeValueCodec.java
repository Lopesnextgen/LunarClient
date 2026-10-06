package com.moonsworth.webosr;

@FunctionalInterface
public interface BridgeValueCodec {
   String objectToString(Object obj1, Class<?> clazz2);
}

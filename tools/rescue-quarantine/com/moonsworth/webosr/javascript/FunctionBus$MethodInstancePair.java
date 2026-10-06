package com.moonsworth.webosr.javascript;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Parameter;

public class FunctionBus$MethodInstancePair {
   private final Object instance;
   private final MethodHandle method;
   private final boolean async;
   private final boolean passInstance;
   private final Parameter[] params;
   private final Class<?> returnType;
   private final boolean hasBrowser;
   private final boolean hasPromiseJS;
   private final Class<?> promiseValueType;
   private final boolean hasStringArray;
   private final int requiredJsArgCount;

   protected FunctionBus$MethodInstancePair(
      Object obj1,
      MethodHandle methodhandle2,
      boolean flag3,
      boolean flag4,
      Parameter[] items5,
      Class<?> clazz6,
      boolean flag7,
      boolean flag8,
      Class<?> clazz9,
      boolean flag10,
      int number11
   ) {
      this.instance = obj1;
      this.method = methodhandle2;
      this.async = flag3;
      this.passInstance = flag4;
      this.params = items5;
      this.returnType = clazz6;
      this.hasBrowser = flag7;
      this.hasPromiseJS = flag8;
      this.promiseValueType = clazz9;
      this.hasStringArray = flag10;
      this.requiredJsArgCount = number11;
   }
}

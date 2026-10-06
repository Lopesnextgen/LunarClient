package com.moonsworth.lunar.bridge;

import java.util.Collection;
import java.util.Map;

public interface ShaderDefinesBuilderBridge {
   Map<String, String> bridge$values();

   Collection<String> bridge$flags();

   void bridge$copyFrom(ShaderDefinesBridge bridge2_21);

   void bridge$copyFrom(ShaderDefinesBuilderBridge bridge2$extension21);

   void bridge$define(String text1);

   void bridge$define(String text1, String text2);

   ShaderDefinesBridge bridge$build();
}

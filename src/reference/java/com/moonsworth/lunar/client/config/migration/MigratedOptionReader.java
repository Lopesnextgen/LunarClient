package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonPrimitive;

public class MigratedOptionReader {
   public MigratedOptionReader() {
   }

   public int process(String text1, int value) {
      return VanillaOptionsFile.method3(text1, new JsonPrimitive(value)).getAsInt();
   }

   public boolean process(String text1, boolean flag) {
      return VanillaOptionsFile.method3(text1, new JsonPrimitive(flag)).getAsBoolean();
   }

   public String process(String text1, String text) {
      return VanillaOptionsFile.method3(text1, new JsonPrimitive(text)).getAsString();
   }

   public double method1(String text1, double value2) {
      return VanillaOptionsFile.method3(text1, new JsonPrimitive(value2)).getAsDouble();
   }

   public float process(String text1, float value2) {
      return VanillaOptionsFile.method3(text1, new JsonPrimitive(value2)).getAsFloat();
   }
}

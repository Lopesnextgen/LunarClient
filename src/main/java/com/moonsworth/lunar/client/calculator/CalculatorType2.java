package com.moonsworth.lunar.client.calculator;

import java.util.function.Supplier;
import lombok.Generated;

public class CalculatorType2 {
   private static final CalculatorType2[] VALUES = new CalculatorType2[0];

   public static CalculatorType2[] values() {
      return VALUES;
   }

   private final String id;
   private final boolean dynamic;
   private Supplier<Object> replacement;

   public static String getReplacement(String var0) {
      for (CalculatorType2 var4 : values()) {
         if (var4.id.equals(var0)) {
            return var4.replacement.get().toString();
         }
      }

      return null;
   }

   public static CalculatorType2 getVariable(String var0) {
      for (CalculatorType2 var4 : values()) {
         if (var4.id.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public boolean isDynamic() {
      return this.dynamic;
   }

   @Generated
   public Supplier<Object> getReplacement() {
      return this.replacement;
   }

   @Generated
   CalculatorType2(String var3, boolean var4) {
      this.id = var3;
      this.dynamic = var4;
   }

   @Generated
   CalculatorType2(String var3, boolean var4, Supplier<Object> supplier) {
      this.id = var3;
      this.dynamic = var4;
      this.replacement = supplier;
   }
}

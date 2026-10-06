package com.moonsworth.lunar.client.chat.translation;

import java.util.function.Supplier;
import lombok.Generated;

public enum TranslationVariable {
   ;
   private final String id;
   private final boolean dynamic;
   private Supplier<Object> replacement;

   public static String getReplacement(String text0) {
      for (TranslationVariable calculatortype24 : values()) {
         if (calculatortype24.id.equals(text0)) {
            return calculatortype24.replacement.get().toString();
         }
      }

      return null;
   }

   public static TranslationVariable getVariable(String text0) {
      for (TranslationVariable calculatortype24 : values()) {
         if (calculatortype24.id.equals(text0)) {
            return calculatortype24;
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
   TranslationVariable(String text3, boolean flag4) {
      this.id = text3;
      this.dynamic = flag4;
   }

   @Generated
   TranslationVariable(String text3, boolean flag4, Supplier<Object> supplier5) {
      this.id = text3;
      this.dynamic = flag4;
      this.replacement = supplier5;
   }
}

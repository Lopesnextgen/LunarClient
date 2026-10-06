package com.moonsworth.lunar.client.calculator;

import lombok.Generated;

public enum CalculatorType {
   DUTCH("nl_NL", "nl"),
   FRENCH("fr_FR", "fr"),
   GERMAN("de_DE", "de"),
   ENGLISH("en_US", "en"),
   ITALIAN("it_IT", "it"),
   POLISH("pl_PL", "pl"),
   SPANISH("es_ES", "es"),
   SWEDISH("sv_SE", "sv"),
   LITHUANIAN("lt_LT", "lt"),
   PORTUGUESE("pt_PT", "pt"),
   PORTUGUESE_BR("pt_BR", "pt"),
   TURKISH("tr_TR", "tr");

   final String fileName;
   final String base;

   public static boolean isSupported(String text) {
      for (CalculatorType var4 : values()) {
         if (var4.fileName.equals(text)) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public String getFileName() {
      return this.fileName;
   }

   @Generated
   public String getBase() {
      return this.base;
   }

   @Generated
   CalculatorType(String text, String var4) {
      this.fileName = text;
      this.base = var4;
   }
}

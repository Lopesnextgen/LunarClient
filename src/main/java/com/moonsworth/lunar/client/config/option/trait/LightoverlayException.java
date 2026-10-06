package com.moonsworth.lunar.client.config.option.trait;

public abstract class LightoverlayException extends Exception {
   public LightoverlayException(String text1) {
      super(text1);
   }

   public static class Data extends LightoverlayException {
      public Data(String text1, String text2) {
         super("Trait Type '" + text1 + "' requires trait type '" + text2 + "' to also be set.");
      }
   }

   public static class TraitConflictException extends LightoverlayException {
      public TraitConflictException(String text1, String text2) {
         super("Conflicting Trait Types found: " + text1 + " and " + text2);
      }
   }
}

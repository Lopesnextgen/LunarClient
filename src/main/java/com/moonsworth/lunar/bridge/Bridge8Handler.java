package com.moonsworth.lunar.bridge;

public class Bridge8Handler implements Bridge8_7 {
   private final Bridge8Handler.Type field1;

   public Bridge8Handler(Bridge8Handler.Type type) {
      this.field1 = type;
   }

   public Bridge8Handler.Type method1() {
      return this.field1;
   }

   public enum Type {
      COLOR,
      DEPTH;
   }
}

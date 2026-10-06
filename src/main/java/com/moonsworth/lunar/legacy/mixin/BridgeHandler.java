package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge_7;

public class BridgeHandler implements Bridge_7 {
   private final char field1;
   private final int field2;
   private final boolean field3;

   public BridgeHandler(char character, int value, boolean flag) {
      this.field1 = character;
      this.field2 = value;
      this.field3 = flag;
   }

   @Override
   public boolean method1() {
      return Character.isAlphabetic(this.field1);
   }

   @Override
   public boolean method2() {
      return this.field3;
   }

   @Override
   public boolean method3() {
      return this.field2 == 1;
   }

   public char method4() {
      return this.field1;
   }

   public int code() {
      return this.field2;
   }
}

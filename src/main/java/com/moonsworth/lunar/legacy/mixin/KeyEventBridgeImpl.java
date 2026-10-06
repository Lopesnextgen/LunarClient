package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.KeyEventBridge;

public class KeyEventBridgeImpl implements KeyEventBridge {
   private final char field1;
   private final int field2;
   private final boolean field3;

   public KeyEventBridgeImpl(char character1, int value, boolean flag) {
      this.field1 = character1;
      this.field2 = value;
      this.field3 = flag;
   }

   public boolean method1() {
      return Character.isAlphabetic(this.field1);
   }

   public boolean method2() {
      return this.field3;
   }

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

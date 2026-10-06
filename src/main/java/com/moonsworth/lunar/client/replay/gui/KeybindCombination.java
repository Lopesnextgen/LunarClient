package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.KeyCode;

public class KeybindCombination {
   private final KeyCode field1;
   private final boolean field2;
   private final boolean field3;
   private final boolean field4;

   public KeybindCombination(KeyCode bridgetype_81, boolean flag, boolean flag2, boolean flag3) {
      this.field1 = bridgetype_81;
      this.field2 = flag;
      this.field3 = flag2;
      this.field4 = flag3;
   }

   public KeyCode method1() {
      return this.field1;
   }

   public boolean method2() {
      return this.field2;
   }

   public boolean method3() {
      return this.field3;
   }

   public boolean method4() {
      return this.field4;
   }
}

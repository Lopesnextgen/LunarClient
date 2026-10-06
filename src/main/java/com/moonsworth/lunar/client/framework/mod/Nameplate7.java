package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework6;

public class Nameplate7 implements Framework6 {
   private boolean field1 = true;

   @Override
   public boolean method1() {
      return this.field1;
   }

   @Override
   public Framework6 method2(boolean flag) {
      this.field1 = flag;
      return this;
   }
}

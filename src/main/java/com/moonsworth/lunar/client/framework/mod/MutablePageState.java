package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.PageState;

public class MutablePageState implements PageState {
   private boolean field1 = true;

   public MutablePageState() {
   }

   @Override
   public boolean method1() {
      return this.field1;
   }

   @Override
   public PageState method2(boolean flag) {
      this.field1 = flag;
      return this;
   }
}

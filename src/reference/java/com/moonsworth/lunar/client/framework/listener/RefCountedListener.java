package com.moonsworth.lunar.client.framework.listener;

public abstract class RefCountedListener implements AutoReconnectListener {
   private int field1 = 0;

   public RefCountedListener() {
   }

   protected abstract void onEnable();

   protected abstract void onDisable();

   @Override
   public void method1() {
      this.field1++;
      if (this.field1 == 1) {
         this.onEnable();
      }
   }

   @Override
   public void method3() {
      this.field1--;
      if (this.field1 == 0) {
         this.onDisable();
      }
   }
}

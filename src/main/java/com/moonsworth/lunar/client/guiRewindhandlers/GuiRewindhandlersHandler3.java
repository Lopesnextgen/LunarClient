package com.moonsworth.lunar.client.guiRewindhandlers;

public abstract class GuiRewindhandlersHandler3 implements GuiRewindhandlers {
   private int field1 = 0;

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

package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.framework.listener.Nameplate;

public class GuiRewindhandlersHandler implements AutoReconnectListener {
   private final DependencyTracker field1 = DependencyTracker.method2(this::method4);
   private final AutoReconnectListener field2;
   private final Nameplate field3;
   private boolean isEnabled;
   private int field4;

   public GuiRewindhandlersHandler(AutoReconnectListener guirewindhandlers1, Nameplate nameplate2) {
      this.field2 = guirewindhandlers1;
      this.field3 = nameplate2;
   }

   @Override
   public void method1() {
      this.field4++;
      if (this.field4 == 1) {
         this.method4();
      }
   }

   @Override
   public void method3() {
      this.field4--;
      if (this.field4 == 0) {
         this.method4();
      }
   }

   private void method4() {
      boolean flag1 = this.field1.method3(() -> this.field4 > 0 && this.field3.isEnabled());
      if (flag1 != this.isEnabled) {
         this.isEnabled = flag1;
         if (flag1) {
            this.field2.method1();
         } else {
            this.field2.method3();
         }
      }
   }
}

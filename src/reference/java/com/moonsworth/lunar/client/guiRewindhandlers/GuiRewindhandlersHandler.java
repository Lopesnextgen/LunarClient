package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.client.framework.listener.Nameplate;

public class GuiRewindhandlersHandler implements GuiRewindhandlers {
   private final GuiRewindhandlers3 field1 = GuiRewindhandlers3.method2(this::method4);
   private final GuiRewindhandlers field2;
   private final Nameplate field3;
   private boolean isEnabled;
   private int field4;

   public GuiRewindhandlersHandler(GuiRewindhandlers var1, Nameplate nameplate) {
      this.field2 = var1;
      this.field3 = nameplate;
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
      boolean var1 = this.field1.method3(() -> this.field4 > 0 && this.field3.isEnabled());
      if (var1 != this.isEnabled) {
         this.isEnabled = var1;
         if (var1) {
            this.field2.method1();
         } else {
            this.field2.method3();
         }
      }
   }
}

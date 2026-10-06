package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.FrameworkType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3;

public class Nameplate8 implements Framework7 {
   private final GuiRewindhandlers3 field1 = GuiRewindhandlers3.method2(this::method5);
   private final com.moonsworth.lunar.client.framework.listener.Nameplate field2;
   private final Framework7Extension field3;
   private boolean field4 = true;

   public Nameplate8(Framework7Extension var1, com.moonsworth.lunar.client.framework.listener.Nameplate var2) {
      this.field3 = var1;
      this.field2 = var2;
      this.method4();
   }

   @Override
   public boolean method3() {
      return this.field4;
   }

   @Override
   public void method4() {
      this.field4 = (Boolean)this.field1.method3(this.field2::isEnabled);
   }

   private void method5() {
      boolean var1 = this.field4;
      this.method4();
      if (var1 != this.field4) {
         if (this.field3.method9(Framework.field8, FrameworkType.COMPLETE) == FrameworkType.COMPLETE) {
            ModEnabledState var2 = (ModEnabledState)this.field3.method1(Framework.field6);
            if (var2 == null) {
               this.field3.updateEnabled();
            } else {
               var2.method5(this.field3, var2.isEnabled() && this.field4);
            }
         }
      }
   }
}

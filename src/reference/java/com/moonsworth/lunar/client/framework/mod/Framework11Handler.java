package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.listener.Nameplate;

public class Framework11Handler implements Framework11 {
   private final GuiRewindhandlers3 field1 = GuiRewindhandlers3.method2(() -> this.method3(true));
   private final Nameplate field2;
   private final Framework7Extension field3;
   private boolean field4 = false;

   public Framework11Handler(Nameplate var1, Framework7Extension var2) {
      this.field2 = var1;
      this.field3 = var2;
      this.method3(true);
   }

   @Override
   public void method3(boolean var1) {
      Framework10Extension var2 = (Framework10Extension)this.field3.method1(Framework.field12);
      if (var2 == null) {
         this.method3();
      } else {
         boolean var3 = (Boolean)this.field1.method3(() -> var2.method6() && this.field2.isEnabled());
         if (var3 != this.field4) {
            this.field4 = var3;
            if (var1) {
               var2.method7(
                  this,
                  (AlertExtension)this.field3.method1(Framework.field5),
                  (Framework4)this.field3.method1(Framework.field16)
               );
            }
         }
      }
   }

   private void method3() {
      this.field4 = (Boolean)this.field1.method3(() -> this.field3.isEnabled() && this.field2.isEnabled());
   }

   @Generated
   @Override
   public boolean method2() {
      return this.field4;
   }
}

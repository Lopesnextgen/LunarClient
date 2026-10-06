package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class F3display$Data4 implements F3display_3 {
   private float width;
   private float height;
   private final boolean field1;

   public F3display$Data4(boolean var1) {
      this.field1 = var1;
   }

   @Override
   public F3display_3 method3(boolean var1, String... var2) {
      for (String var6 : var2) {
         if (var6 == null) {
            return this;
         }
      }

      String var7 = String.join("", var2);
      Bridge10_2 var8 = ThreadModuleDump63.method10();
      this.method6(var8.bridge$getStringWidth(var7), var8.method19());
      return this;
   }

   @Override
   public F3display_3 method4(boolean var1, List<ProfilerResultBridge> var2) {
      this.method6(150.0F, 150.0F);
      return this;
   }

   @Override
   public F3display_3 method5(com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display var1) {
      this.method6(242.0F, 52.0F);
      return this;
   }

   @Override
   public F3display_3 method6(String var1, String var2) {
      return this;
   }

   @Override
   public boolean method7() {
      return this.field1;
   }

   private void method6(float var1, float var2) {
      this.width = Math.max(this.width, var1);
      this.height += var2;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }
}

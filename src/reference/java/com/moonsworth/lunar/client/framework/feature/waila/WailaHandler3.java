package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class WailaHandler3 implements Waila {
   private static final int field1 = 5;
   private final String field2;
   private boolean field3 = true;
   private ColorOption field4 = null;

   public WailaHandler3(String var1) {
      this.field2 = var1;
   }

   @Override
   public int getWidth() {
      return (int)ThreadModuleDump63.method10().bridge$getStringWidth(this.field2);
   }

   @Override
   public int getHeight() {
      return ThreadModuleDump63.method10().method19();
   }

   public WailaHandler3 method1() {
      this.field3 = false;
      return this;
   }

   public WailaHandler3 method2(ColorOption var1) {
      this.field4 = var1;
      return this;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      (this.field4 == null ? var2.method16() : this.field4)
         .HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, this.field2, var3 + 1, (float)var4 + (this.field3 ? 5 : 1), (Boolean)var2.method17().get());
   }
}

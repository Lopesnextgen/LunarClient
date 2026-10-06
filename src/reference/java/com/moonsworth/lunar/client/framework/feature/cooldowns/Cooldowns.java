package com.moonsworth.lunar.client.framework.feature.cooldowns;

import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump11.Type;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import lombok.Generated;

public abstract class Cooldowns {
   protected static final int field1 = 20;
   private static final int field2 = 1;
   private static final int field3 = 42;
   private static final int field4 = 4;
   private static final String field5 = "23h59m";
   private final String field6;
   private final long length;
   private final long field7;
   private final CooldownStyle field8;

   protected Cooldowns(String var1, long var2, CooldownStyle var4) {
      this.field6 = var1;
      this.length = var2;
      this.field8 = var4;
      this.field7 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   public static FloatFloatPair method1(Gui2Extension gui2) {
      Bridge10_2 var1 = ThreadModuleDump63.method10();
      float var2 = var1.method19();
      float var3 = var1.bridge$getStringWidth("23h59m");

      return switch (gui2) {
         case LEFT, RIGHT -> FloatFloatPair.of(46.0F + var3, 42.0F);
         case ABOVE, BELOW -> FloatFloatPair.of(Math.max(42.0F, var3), 46.0F + var2);
      };
   }

   public void method2(MixinHelper_4 var1, float var2, float var3, int var4, int var5, int var6, int var7, Gui2Extension var8) {
      Bridge10_2 var9 = ThreadModuleDump63.method10();
      int var10 = var9.method19();
      float var11 = var2 + 1.0F;
      float var12 = var3 + 1.0F;
      switch (var8) {
         case LEFT:
            float var13 = var9.bridge$getStringWidth("23h59m");
            var11 = var2 + var13 + 4.0F + 1.0F;
            break;
         case ABOVE:
            var12 = var3 + var10 + 4.0F + 1.0F;
      }

      this.method3(var1, var11, var12);
      double var15 = this.length - (ThreadModuleDump63.method3().bridge$getSystemTime() - this.field7);
      if (!(var15 <= 0.0)) {
         this.method4(var1, var11, var12, var15, var4, var5, var6);
         this.method5(var1, var9, var2, var3, var11, var12, var15, var10, var7, var8);
      }
   }

   public abstract void method3(MixinHelper_4 var1, float var2, float var3);

   private void method4(MixinHelper_4 var1, float var2, float var3, double var4, int var6, int var7, int var8) {
      double var9 = var2 + 20.0F;
      double var11 = var3 + 20.0F;
      double var13 = 20.0;
      double var15 = 17.0;
      byte var17 = 32;
      LcuiScreen.method63(var1, var9, var11, 20.0, 0.0, (float)this.length / 3.95F, (int)this.length, var4, 872415231);
      LcuiScreen.method63(var1, var9, var11, 20.0, 0.0, (float)this.length / 3.95F, (int)this.length, var4, 855638016);
      LcuiScreen.method61(var1, var9, var11, var13 + 0.3, var15 - 0.3, 0.0, 1.0, 0.0, var7, var17);
      LcuiScreen.method63(var1, var9, var11, var13 + 0.3, var15 - 0.3, (float)this.length / 3.95F, (int)this.length, var4, var6);
      LcuiScreen.method61(var1, var9, var11, var13 + 1.0, var13, 0.0, 1.0, 0.0, var8, var17);
      LcuiScreen.method61(var1, var9, var11, var15, var15 - 1.0, 0.0, 1.0, 0.0, var8, var17);
   }

   private void method5(
      MixinHelper_4 var1, Bridge10_2 var2, float var3, float var4, float var5, float var6, double var7, int var9, int var10, Gui2Extension var11
   ) {
      String var12 = Type.COMPACT_DYNAMIC.format((long)var7);
      float var13 = var2.bridge$getStringWidth(var12);
      float var14 = var5 + 20.0F;
      float var15 = var6 + 20.0F;
      float var16 = var9 / 2.0F;
      float var17;
      float var18;
      switch (var11) {
         case LEFT:
            var17 = var3;
            var18 = var15 - var16;
            break;
         case RIGHT:
            var17 = var3 + 42.0F + 4.0F;
            var18 = var15 - var16;
            break;
         case ABOVE:
            var17 = var14 - var13 / 2.0F;
            var18 = var4;
            break;
         case BELOW:
            var17 = var14 - var13 / 2.0F;
            var18 = var4 + 42.0F + 4.0F;
            break;
         default:
            var17 = var3 + 42.0F + 4.0F;
            var18 = var15 - var16;
      }

      var1.method19(var2, var12, var17, var18, var10, true);
   }

   public boolean method6() {
      return this.field7 < ThreadModuleDump63.method3().bridge$getSystemTime() - this.length;
   }

   @Generated
   public String getName() {
      return this.field6;
   }

   @Generated
   public CooldownStyle method7() {
      return this.field8;
   }
}

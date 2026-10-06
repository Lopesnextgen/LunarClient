package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class F3display2 {
   public static final float field1 = 150.0F;
   public static final List<ProfilerResultBridge> field2 = List.of(
      new F3display3(100.0, 100.0, "root"),
      new F3display3(91.7, 91.7, "render"),
      new F3display3(7.19, 7.19, "tick"),
      new F3display3(0.36, 0.36, "scheduledPacketProcessing"),
      new F3display3(0.26, 0.26, "scheduledExecutables"),
      new F3display3(0.2, 0.2, "unspecified"),
      new F3display3(0.12, 0.12, "fpsUpdate"),
      new F3display3(0.05, 0.05, "mouse"),
      new F3display3(0.05, 0.05, "sound"),
      new F3display3(0.03, 0.03, "yield"),
      new F3display3(0.02, 0.02, "wait_for_gpu"),
      new F3display3(0.01, 0.01, "toasts")
   );
   private static final int[] field3 = new int[]{7698431, 9961333, 16751733, 16545279, 7733230, 16765060, 9078015, 16759382};
   protected final boolean field4;
   private final MixinHelper_4 field5;
   private final F3DisplayModule field6;
   private final float field7;
   private final float field8;
   private float y;
   private float field9 = 0.0F;
   private float field10 = 0.0F;

   public F3display2(MixinHelper_4 var1, F3DisplayModule var2, float var3, float var4, boolean var5) {
      this.field6 = var2;
      this.field7 = var3;
      this.y = var4;
      this.field8 = var4;
      this.field4 = var5;
      this.field5 = var1;
   }

   public void method1(String... var1) {
      Bridge10_2 var2 = ThreadModuleDump63.method10();
      float var3;
      if (var1.length == 1) {
         var3 = var2.bridge$getStringWidth(var1[0]);
      } else {
         String var4 = String.join("", var1);
         var3 = var2.bridge$getStringWidth(var4);
      }

      Data var10 = this.field6.getElement();
      float var5 = this.field7;
      switch (var10.method23()) {
         case RIGHT:
            var5 += var10.getWidth() - var3;
            break;
         case CENTER:
            var5 += (var10.getWidth() - var3) / 2.0F;
      }

      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data var6 = this.field6.getDisplayOptions();
      float var7 = var2.method19();
      int var8 = (int)(var5 - 1.0F);
      int var9 = (int)(this.y - 1.0F);
      this.field5.method1(var8, var9, (int)(var8 + var3 + 2.0F), (int)(var9 + var7), var6.field3.method14(0.0F));
      this.y += var7;
   }

   public void method2(boolean var1, String... var2) {
      Bridge10_2 var3 = ThreadModuleDump63.method10();
      float var4;
      if (var2.length == 1) {
         var4 = var3.bridge$getStringWidth(var2[0]);
      } else {
         String var5 = String.join("", var2);
         var4 = var3.bridge$getStringWidth(var5);
      }

      Data var17 = this.field6.getElement();
      float var6 = this.field7;
      switch (var17.method23()) {
         case RIGHT:
            var6 += var17.getWidth() - var4;
            break;
         case CENTER:
            var6 += (var17.getWidth() - var4) / 2.0F;
      }

      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data var7 = this.field6.getDisplayOptions();
      float var8 = var3.method19();
      boolean var9 = (Boolean)var7.field1.get();
      float var10 = 0.0F;
      boolean var11 = var1;

      for (String var15 : var2) {
         int var16 = var11 ? var7.field4.method14(0.0F) : var7.field5.method14(0.0F);
         this.field5.method22(var3, var15, (int)(var6 + var10), (int)this.y, var16, var9);
         var10 += var3.bridge$getStringWidth(var15);
         var11 = !var11;
      }

      this.method8(var10, var8);
   }

   public void method3(List<ProfilerResultBridge> var1) {
      float var2 = switch (this.field6.getElement().method23()) {
         case RIGHT -> this.field7 + this.field6.getElement().getWidth() - 75.0F;
         case CENTER -> this.field7 + this.field6.getElement().getWidth() / 2.0F;
         case LEFT -> this.field7 + 75.0F;
         default -> throw new IncompatibleClassChangeError();
      };
      Bridge10_2 var3 = ThreadModuleDump63.method10();
      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data var4 = this.field6.getDisplayOptions();
      float var5 = this.y + 150.0F + 2.0F;
      int var6 = 0;

      for (ProfilerResultBridge var8 : var1) {
         String var9 = this.method5(var8, var6);
         float var10 = var3.bridge$getStringWidth(var9);
         int var11 = var6 == 0 ? 0 : 5;

         float var12 = switch (this.field6.getElement().method23()) {
            case RIGHT -> this.field7 + this.field6.getElement().getWidth() - var10 - var11;
            case CENTER -> var2 - var10 / 2.0F;
            case LEFT -> this.field7 + var11;
            default -> throw new IncompatibleClassChangeError();
         };
         int var13 = (int)(var12 - 1.0F);
         int var14 = (int)(var5 - 1.0F);
         this.field5.method1(var13, var14, (int)(var13 + var10 + 2.0F), var14 + var3.method19(), var4.field3.method14(0.0F));
         var5 += var3.method19();
         if (var6 == 0) {
            var5 += var3.method19();
         }

         var6++;
      }

      this.y += 150.0F;
   }

   public void method4(boolean var1, List<ProfilerResultBridge> var2) {
      if (var2 != null && !var2.isEmpty()) {
         float var3 = this.y + 75.0F;

         float var4 = switch (this.field6.getElement().method23()) {
            case RIGHT -> this.field7 + this.field6.getElement().getWidth() - 75.0F;
            case CENTER -> this.field7 + this.field6.getElement().getWidth() / 2.0F;
            case LEFT -> this.field7 + 75.0F;
            default -> throw new IncompatibleClassChangeError();
         };
         float var5 = 75.0F;
         double[] var6;
         if (var2.size() == 1) {
            var6 = new double[]{((ProfilerResultBridge)var2.get(0)).method1()};
         } else {
            var6 = new double[var2.size() - 1];

            for (int var7 = 1; var7 < var2.size(); var7++) {
               var6[var7 - 1] = ((ProfilerResultBridge)var2.get(var7)).method1();
            }
         }

         int[] var25 = field3;
         com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data var8 = this.field6.getDisplayOptions();
         boolean var9 = (Boolean)var8.field1.get();
         if (var1) {
            LcuiScreen.method65(this.field5, var4, var3, var5, var6, var25, var8.field4.method14(0.0F));
         } else {
            LcuiScreen.method64(this.field5, var4, var3, var5, var6, var25);
         }

         float var10 = this.y + 150.0F + 2.0F;
         Bridge10_2 var11 = ThreadModuleDump63.method10();
         float var12 = 150.0F;
         int var13 = 0;

         for (ProfilerResultBridge var15 : var2) {
            boolean var16 = this.field6.getElement().method23() == com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.RIGHT;
            String var17 = this.method5(var15, var13);
            float var18 = var11.bridge$getStringWidth(var17);
            int var19 = var13 == 0 ? 0 : 5;

            float var20 = switch (this.field6.getElement().method23()) {
               case RIGHT -> this.field7 + this.field6.getElement().getWidth() - var18 - var19;
               case CENTER -> var4 - var18 / 2.0F;
               case LEFT -> this.field7 + var19;
               default -> throw new IncompatibleClassChangeError();
            };
            byte var21 = -1;
            if (var2.size() == 1) {
               var21 = (byte)(var1 ? var8.field4.method14(0.0F) : var25[0]);
            } else if (var13 != 0) {
               var21 = (byte)(var1 && var13 == 1 ? var8.field4.method14(0.0F) : var25[(var13 - 1) % var25.length]);
            }

            this.field5.method22(var11, var17, (int)var20, (int)var10, var21, var9);
            if ((Boolean)this.field6.getShowGlobalPercent().get()) {
               String var22 = "(" + F3display.method33(var15.method2(), 2) + "%)";
               int var23 = ThreadModuleDump23.method22(var21, (int)(ThreadModuleDump23.method4(var21) * 0.8));
               if (var16) {
                  float var24 = var11.bridge$getStringWidth(var22);
                  this.field5.method22(var11, var22, (int)(var20 - var24 - 5.0F), (int)var10, var23, false);
                  var18 += var24;
               } else {
                  float var26 = var11.bridge$getStringWidth(var17);
                  this.field5.method22(var11, var22, (int)(var20 + var18 + 5.0F), (int)var10, var23, false);
                  var18 += var26 + var11.bridge$getStringWidth(var22);
               }
            }

            var10 += var11.method19();
            if (var13 == 0) {
               var10 += var11.method19();
            }

            if (var18 > var12) {
               var12 = var18;
            }

            var13++;
         }

         this.method8(150.0F, 150.0F);
      } else {
         this.method8(150.0F, 150.0F);
      }
   }

   private String method5(ProfilerResultBridge var1, int var2) {
      String var3 = var1.bridge$getName();
      if (var2 == 0) {
         var3 = var3.replace('\u001e', '/');
      }

      boolean var4 = this.field6.getElement().method23() == com.moonsworth.lunar.client.ui.hud.row.Gui2Extension.RIGHT;
      if (var2 == 0) {
         return var4 ? var3 + " [0]" : "[0] " + var3;
      } else {
         return var4
            ? F3display.method33(var1.method1(), 2) + "% " + var3 + " [" + var2 + "]"
            : "[" + var2 + "] " + var3 + " " + F3display.method33(var1.method1(), 2) + "%";
      }
   }

   public void method6(com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display var1) {
      if (!var1.method10() || this.field4) {
         com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data var2 = this.field6.getDisplayOptions();
         int var3 = var2.field3.method14(0.0F);
         byte var4 = 50;
         short var5 = 240;
         float var6 = this.field7 + 1.0F;
         float var7 = this.y + 1.0F;
         this.field5.method1((int)var6, (int)var7, Math.round(var6 + var5), Math.round(var7 + var4), var3);
         this.y += 52.0F;
      }
   }

   public void method7(com.moonsworth.lunar.client.framework.feature.f3display.mixin.F3display var1) {
      Data var2 = this.field6.getElement();
      float var3 = this.field7;
      switch (var2.method23()) {
         case RIGHT:
            var3 += var2.getWidth() - 242.0F;
            break;
         case CENTER:
            var3 += (var2.getWidth() - 242.0F) / 2.0F;
      }

      boolean var4 = var1.method13(this, this.field6, var3, this.y, this.field4);
      if (var4) {
         this.method8(242.0F, 52.0F);
      }
   }

   private void method8(float var1, float var2) {
      this.y += var2;
      this.field10 += var2;
      this.field9 = Math.max(var1, this.field9);
   }

   public float method9() {
      return this.field10 == 0.0F ? ThreadModuleDump63.method10().method19() : this.field10;
   }

   public float method10() {
      return Math.max(this.field9, 40.0F);
   }

   public void method11() {
      this.y = this.field8;
   }

   public boolean method12() {
      return (Boolean)this.field6.getDisplayOptions().field2.get();
   }

   @Generated
   public MixinHelper_4 method13() {
      return this.field5;
   }
}

package com.moonsworth.lunar.client.ui.hud.row;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump60;
import java.util.List;

public class Hitbox {
   public static final int field1 = 3;

   public static void method1(MixinHelper_4 var0, float var1, float var2, float var3, Gui2Extension var4, List<Hitbox2> var5) {
      for (Hitbox2 var7 : var5) {
         switch (var4) {
            case LEFT:
               float var10 = var1 + (var7 instanceof Hitbox2Handler3 ? ((Hitbox2Handler3)var7).padding : 0);
               var7.method1(var0, var10, var2);
               break;
            case CENTER:
               float var9 = var1 + var3 / 2.0F - var7.method2() / 2.0F + (var7 instanceof Hitbox2Handler3 ? ((Hitbox2Handler3)var7).padding / 2 : 0);
               var7.method1(var0, var9, var2);
               break;
            case RIGHT:
               float var8 = var1 + var3 - var7.method2();
               var7.method1(var0, var8, var2);
         }

         var2 += var7.method3();
      }
   }

   public static ThreadModuleDump60 method2(
      MixinHelper_4 var0,
      MixinCore9Extension var1,
      float var2,
      float var3,
      Gui2Extension var4,
      boolean var5,
      ColorOption var6,
      boolean var7,
      float var8,
      ColorOption var9,
      List<Hitbox2> var10
   ) {
      float var11 = 6.0F;
      int var12 = 6;

      for (Hitbox2 var14 : var10) {
         var12 = (int)(var12 + var14.method3());
         float var15 = 6.0F + var14.method2();
         if (var15 > var11) {
            var11 = var15;
         }
      }

      if (var5) {
         var6.method11(var0, var2, var3, var11, var12);
         if (var7) {
            var9.method11(var0, var1, var2, var3, var11, var12, var8);
         }
      }

      method1(var0, var2 + 3.0F, var3 + 3.0F, var11 - 6.0F, var4, var10);
      ThreadModuleDump60 var16 = new ThreadModuleDump60();
      var16.x = var11;
      var16.y = var12;
      return var16;
   }

   public static Gui2Extension method3(boolean var0, HudAnchor var1, Gui2Extension var2) {
      if (!var0) {
         return var2;
      }

      return switch (var1.getHorizontal()) {
         case LEFT -> Gui2Extension.LEFT;
         case RIGHT -> Gui2Extension.RIGHT;
         default -> Gui2Extension.CENTER;
      };
   }

   public static Hitbox2Handler3 method4(int var0, Hitbox2... var1) {
      return new Hitbox2Handler3(var1, var0);
   }

   public static Hitbox2Handler method5(String var0, ColorOption var1, boolean var2) {
      return new Hitbox2Handler(var0, var1, var2);
   }

   public static Hitbox2Handler4 method6(ItemStackBridge var0) {
      return new Hitbox2Handler4(var0);
   }

   public static Hitbox2Handler2 method7(int var0) {
      return new Hitbox2Handler2(var0);
   }
}

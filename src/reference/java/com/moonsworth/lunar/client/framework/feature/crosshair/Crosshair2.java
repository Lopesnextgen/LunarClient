package com.moonsworth.lunar.client.framework.feature.crosshair;

import com.moonsworth.lunar.client.framework.feature.crosshair.mixin.Gui2Extension2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.jetbrains.annotations.NotNull;

public class Crosshair2 {
   private final Gui2Extension2 field1;
   private final boolean[] field2;

   public Crosshair2(Gui2Extension2 var1) {
      this(var1, new boolean[var1.size() * var1.size()]);
   }

   public Crosshair2(boolean[] var1) {
      this(Gui2Extension2.fromGridLength((int)Math.floor(Math.sqrt(var1.length))), var1);
   }

   public Crosshair2(Gui2Extension2 var1, boolean[] var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static Crosshair2 method1() {
      return new Crosshair2(Gui2Extension2.MEDIUM);
   }

   public Crosshair2 method2() {
      boolean[] var1 = new boolean[this.field2.length];
      System.arraycopy(this.field2, 0, var1, 0, var1.length);
      return new Crosshair2(this.field1, var1);
   }

   public Crosshair2 method3(Gui2Extension2 var1, boolean var2) {
      int var3 = this.method12().size();
      int var4 = var1.size();
      if (var3 == var4) {
         return this.method2();
      }

      BufferedImage var5 = new BufferedImage(var3, var3, 2);

      for (int var6 = 0; var6 < var3; var6++) {
         for (int var7 = 0; var7 < var3; var7++) {
            var5.setRGB(var6, var7, this.field2[var6 + var7 * var3] ? -1 : 0);
         }
      }

      BufferedImage var11 = new BufferedImage(var4, var4, 2);
      Graphics2D var12 = var11.createGraphics();
      if (var2) {
         var12.drawImage(var5, 0, 0, var4, var4, null);
      } else {
         int var8 = var4 / 2 - var3 / 2;
         var12.drawImage(var5, var8, var8, var3, var3, null);
      }

      var12.dispose();
      boolean[] var13 = new boolean[var4 * var4];

      for (int var9 = 0; var9 < var4; var9++) {
         for (int var10 = 0; var10 < var4; var10++) {
            var13[var9 + var10 * var4] = var11.getRGB(var9, var10) != 0;
         }
      }

      return new Crosshair2(var13);
   }

   public boolean isEmpty() {
      for (boolean var4 : this.field2) {
         if (var4) {
            return false;
         }
      }

      return true;
   }

   @NotNull
   public String method4() {
      return new Crosshair("LCCH", this.method10(), this.method7()).toString();
   }

   @NotNull
   public static Crosshair2 method5(String var0) {
      Crosshair var1 = Crosshair.method1(var0);
      return var1 != null && var1.type().equals("LCCH") ? method6(var1.method2(), var1.method3()) : method1();
   }

   public static Crosshair2 method6(int var0, byte[] var1) {
      Crosshair2 var2 = new Crosshair2(Gui2Extension2.fromGridLength(var0));
      int var3 = var2.field1.size();
      int var4 = var2.method9(var0);

      for (int var5 = 0; var5 < var0; var5++) {
         for (int var6 = 0; var6 < var0; var6++) {
            int var7 = var5 + var4 + (var6 + var4) * var3;
            if (var7 >= 0 && var7 < var3 * var3) {
               int var8 = (var5 + var6 * var0) / 8;
               int var9 = 1 << (var5 + var6 * var0) % 8;
               var2.field2[var7] = (var1[var8] & var9) != 0;
            }
         }
      }

      return var2;
   }

   public byte[] method7() {
      int var1 = this.field1.size();
      int var2 = this.method10();
      int var3 = this.method9(var2);
      byte[] var4 = new byte[this.method8(var2)];

      for (int var5 = 0; var5 < var2; var5++) {
         for (int var6 = 0; var6 < var2; var6++) {
            int var7 = var6 + var3 + (var5 + var3) * var1;
            if (var7 >= 0 && var7 < var1 * var1 && this.field2[var7]) {
               int var8 = (var6 + var5 * var2) / 8;
               int var9 = 1 << (var6 + var5 * var2) % 8;
               var4[var8] |= (byte)var9;
            }
         }
      }

      return var4;
   }

   public int method8(int var1) {
      return (int)Math.ceil(var1 * var1 / 8.0F);
   }

   public int method9(int var1) {
      return (int)Math.floor((this.field1.size() - var1) / 2.0F);
   }

   public int method10() {
      int var1 = this.field1.size();
      int var2 = 1;

      for (int var3 = 0; var3 < this.field2.length; var3++) {
         if (this.field2[var3]) {
            int var4 = var3 % var1;
            int var5 = var3 / var1;
            var2 = Math.max(var2, this.method11(var4, var1));
            var2 = Math.max(var2, this.method11(var5, var1));
         }
      }

      return var2;
   }

   private int method11(int var1, int var2) {
      int var3 = var1 - var2 / 2;
      return var3 <= 0 ? -var3 * 2 + 1 : var3 * 2;
   }

   public Gui2Extension2 method12() {
      return this.field1;
   }

   public boolean[] method13() {
      return this.field2;
   }
}

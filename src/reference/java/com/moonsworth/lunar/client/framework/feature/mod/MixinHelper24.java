package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper24 extends MixinHelper2_3 {
   public MixinHelper24(MixinHelper var1) {
      super(var1);
   }

   public int method1(String var1, int var2, int var3, int var4, int var5) {
      boolean var6 = this.field1.method42(var2 - 2, var3 - 2, var4 + 4, var5 + 4);
      byte var7 = 0;
      if (this.method12().method17() && var6) {
         if (this.method1().field7) {
            this.field1.method43(var2 - 2, var3 - 2, var4 + 4, var5 + 4, this.getTheme().panelBorderOut);
            this.field1.method43(var2 + var4, var3 - 1, 1, var5 + 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var2 - 1, var3 + var5, var4 + 2, 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var2 - 1, var3 - 1, var4 + 2, 1, this.getTheme().panelBorderRight);
            this.field1.method43(var2 - 1, var3 - 1, 1, var5 + 2, this.getTheme().panelBorderRight);
            this.field1.method43(var2, var3, var4, var5, this.getTheme().insetBackground);
         }

         var7 = 1;
      } else {
         this.field1.method37(var2, var3, var4, var5);
      }

      if (var1 != null) {
         float var8 = this.field1.method51(var1, var4, var5);
         this.field1.method23(var1, var2 + var4 / 2 + var7 - 1, var3 + var5 / 2 + var7 - 1, false, var8);
      }

      return this.field1.method41(var2 - 2, var3 - 2, var4 + 4, var5 + 4);
   }

   public int drawPanelLeftAligned(String var1, int var2, int var3, int var4, int var5, int var6) {
      boolean var7 = this.field1.method42(var2 - 2, var3 - 2, var4 + 4, var5 + 4);
      byte var8 = 0;
      if (this.method12().method17() && var7) {
         if (this.method1().field7) {
            this.field1.method43(var2 - 2, var3 - 2, var4 + 4, var5 + 4, this.getTheme().panelBorderOut);
            this.field1.method43(var2 + var4, var3 - 1, 1, var5 + 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var2 - 1, var3 + var5, var4 + 2, 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var2 - 1, var3 - 1, var4 + 2, 1, this.getTheme().panelBorderRight);
            this.field1.method43(var2 - 1, var3 - 1, 1, var5 + 2, this.getTheme().panelBorderRight);
            this.field1.method43(var2, var3, var4, var5, this.getTheme().insetBackground);
         }

         var8 = 1;
      } else {
         this.field1.method37(var2, var3, var4, var5);
      }

      if (var1 != null) {
         float var9 = this.field1.method51(var1, var4 - 2, var5);
         if (var6 < 2) {
            var6 = 2;
         }

         this.field1.method25(var1, var2 + var6 + var8 - 1, (int)(var3 + var5 / 2 + var8 - 2 - 2.0F * var9), false, var9);
      }

      return this.field1.method41(var2 - 2, var3 - 2, var4 + 4, var5 + 4);
   }
}

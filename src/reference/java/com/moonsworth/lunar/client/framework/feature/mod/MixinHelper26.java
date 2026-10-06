package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.ThreadModuleDump62;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper26 extends MixinHelper2_3 {
   public MixinHelper26(MixinHelper var1) {
      super(var1);
   }

   public void method1(int var1, int var2, int var3, int var4) {
      if (var3 >= -4 && var4 >= -4) {
         if (this.method1().field7) {
            ThreadModuleDump62.field2 = ThreadModuleDump70.of(var1 - 2, var2 - 2, var3 + 4, var4 + 4)
               .method6(this.method1().field9, this.method1().field10);
            this.field1.method43(var1 - 2, var2 - 2, var3 + 4, var4 + 4, this.getTheme().panelBorderOut);
            ThreadModuleDump62.field1 = false;
            ThreadModuleDump62.field3 = true;
            this.field1.method43(var1 - 1, var2 - 1, var3 + 1, 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var1 - 1, var2 - 1, 1, var4 + 1, this.getTheme().panelBorderLeft);
            this.field1.method43(var1 + var3, var2 - 1, 1, var4 + 1, this.getTheme().panelBorderRight);
            this.field1.method43(var1 - 1, var2 + var4, var3 + 2, 1, this.getTheme().panelBorderRight);
            this.field1.method43(var1, var2, var3, var4, this.getTheme().panelBackground);
            ThreadModuleDump62.reset();
         }
      }
   }

   public void method2(int var1, int var2, int var3, int var4) {
      if (var3 >= -4 && var4 >= -4) {
         if (this.method1().field7) {
            ThreadModuleDump62.field2 = ThreadModuleDump70.of(var1 - 1, var2 - 1, var3 + 2, var4 + 2)
               .method6(this.method1().field9, this.method1().field10);
            this.field1.method43(var1, var2, var3, var4, this.getTheme().insetBackground);
            ThreadModuleDump62.field1 = false;
            ThreadModuleDump62.field3 = true;
            this.field1.method43(var1 - 1, var2 - 1, var3 + 2, 1, this.getTheme().insetBorderLeft);
            this.field1.method43(var1 - 1, var2, 1, var4 + 1, this.getTheme().insetBorderLeft);
            this.field1.method43(var1 + var3, var2 - 1, 1, var4 + 2, this.getTheme().insetBorderRight);
            this.field1.method43(var1 - 1, var2 + var4, var3 + 1, 1, this.getTheme().insetBorderRight);
            ThreadModuleDump62.reset();
         }
      }
   }

   public void method3(int var1, int var2, int var3, int var4) {
      if (var3 >= -4 && var4 >= -4) {
         if (this.method1().field7) {
            this.field1.method43(var1, var2, var3, var4, this.getTheme().insetBackground);
            this.field1.method43(var1 - 1, var2 - 1, var3 + 2, 1, this.getTheme().insetBorderLeft);
            this.field1.method43(var1 - 1, var2, 1, var4 + 1, this.getTheme().insetBorderLeft);
            this.field1.method43(var1 + var3, var2 - 1, 1, var4 + 2, this.getTheme().insetBorderRight);
            this.field1.method43(var1 - 1, var2 + var4, var3 + 1, 1, this.getTheme().insetBorderRight);
         }
      }
   }

   public void method4(int var1, int var2, int var3) {
      if (this.method1().field7) {
         this.field1.method43(var1, var2, var3, 1, this.getTheme().insetBorderLeft);
      }
   }
}

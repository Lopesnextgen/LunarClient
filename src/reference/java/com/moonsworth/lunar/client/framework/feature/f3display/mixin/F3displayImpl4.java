package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

public class F3displayImpl4 extends F3display {
   public long field8 = 0L;

   public F3displayImpl4() {
      super(240, "RAM");
      this.method4(false);
   }

   @Override
   protected int[] method8() {
      int var1 = this.method9();
      int var2 = var1 / 1024;
      if (var2 == 0) {
         if (var1 >= 800) {
            return new int[]{512};
         }

         if (var1 >= 512) {
            return new int[]{256};
         }
      }

      int var3 = 0;
      if (var2 >= 16) {
         var3 = var2 / 2;
      } else if (var2 >= 12) {
         var3 = 8;
      } else if (var2 >= 8) {
         var3 = 6;
      } else if (var2 >= 6) {
         var3 = 4;
      } else if (var2 >= 4) {
         var3 = 2;
      } else if (var2 >= 2) {
         var3 = 1;
      }

      return var3 == 0 ? null : new int[]{var3 * 1024};
   }

   @Override
   public String method11(int var1) {
      if (var1 < 1024) {
         return var1 + " MB";
      }

      double var2 = var1 / 1024.0;
      double var4 = (int)(var2 * 10.0) / 10.0;
      return var4 + " GB";
   }

   public void method3() {
      long var1 = System.currentTimeMillis();
      if (var1 - this.field8 >= 100L) {
         this.field8 = var1;
         Runtime var3 = Runtime.getRuntime();
         long var4 = var3.totalMemory() - var3.freeMemory();
         int var6 = Math.toIntExact(var4 / 1048576L);
         this.method2(var6);
      }
   }

   public void method4(boolean var1) {
      if (var1) {
         this.field3 = Math.toIntExact(Runtime.getRuntime().maxMemory() / 1048576L);
      } else {
         this.field3 = 256;
      }
   }
}

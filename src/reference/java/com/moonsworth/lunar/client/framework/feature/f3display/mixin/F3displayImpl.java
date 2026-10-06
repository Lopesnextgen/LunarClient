package com.moonsworth.lunar.client.framework.feature.f3display.mixin;

public class F3displayImpl extends F3display {
   private static final long field8 = 50L;
   public long field9 = 0L;
   private long field10 = 0L;

   public F3displayImpl() {
      super(240, "");
      this.field3 = 2000;
   }

   @Override
   public String method11(int var1) {
      switch (var1) {
         case 600:
            return "64 B/s";
         case 1000:
            return "1 KiB/s";
         case 1400:
            return "16 KiB/s";
         case 2000:
            return "1 MiB/s";
         default:
            int var2 = (int)Math.pow(2.0, var1 / 100.0);
            if (var2 < 1024) {
               return var2 + " B/s";
            } else if (var2 >> 10 < 1024) {
               double var8 = var2 / 1024.0;
               double var10 = (int)(var8 * 10.0) / 10.0;
               return var10 + " KiB/s";
            } else if (var2 >> 20 < 1024) {
               double var7 = var2 / 1048576.0;
               double var9 = (int)(var7 * 10.0) / 10.0;
               return var9 + " MiB/s";
            } else {
               double var3 = var2 / 1.0737418E9F;
               double var5 = (int)(var3 * 10.0) / 10.0;
               return var5 + " GiB/s";
            }
      }
   }

   @Override
   protected int[] method8() {
      int var1 = this.method9();
      return var1 >= 2300 ? new int[]{2000} : new int[]{600, 1000, 1400};
   }

   public void method3() {
      long var1 = System.currentTimeMillis();
      if (this.field9 == 0L) {
         this.field9 = var1;
      } else {
         long var3 = var1 - this.field9;
         if (var3 > 50L) {
            double var5 = 1000.0 / var3;
            double var7 = this.field10 * var5;
            this.field10 = 0L;
            this.field9 = var1;
            double var9 = Math.log(var7) * 1.44269504089;
            this.method5((int)(var9 * 100.0));
         }
      }
   }

   public void method5(int var1) {
      this.field10 += var1;
   }

   public int method6(int var1) {
      for (int var2 = 1; var2 < 5; var2++) {
         if ((var1 & -1 << var2 * 7) == 0) {
            return var2;
         }
      }

      return 5;
   }
}

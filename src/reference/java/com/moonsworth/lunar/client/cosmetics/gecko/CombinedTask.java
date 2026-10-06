package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.NotNull;

public class CombinedTask implements InactiveTask {
   @NotNull
   private final InactiveTask[] field1;
   private final boolean @NotNull [] field2;
   private int field3 = 0;

   public CombinedTask(@NotNull InactiveTask[] var1) {
      if (var1.length <= 1) {
         throw new IllegalStateException("Combined tasks must have at least two tasks!");
      }

      this.field1 = var1;
      this.field2 = new boolean[var1.length];
   }

   @Override
   public boolean method1(EmoteDefinition var1, PathFilter var2) {
      return !this.method9();
   }

   @Override
   public boolean method2(EmoteDefinition var1, PathFilter var2) {
      boolean var3 = false;

      for (int var4 = 0; var4 < this.field1.length; var4++) {
         if (this.field1[var4].method2(var1, var2)) {
            this.field2[var4] = true;
            var3 = true;
         } else {
            this.field2[var4] = false;
         }
      }

      this.field3 = 0;
      return var3;
   }

   @Override
   public void method3(EmoteDefinition var1, PathFilter var2) {
      for (int var3 = 0; var3 < this.field1.length; var3++) {
         if (this.field2[var3]) {
            this.method5(var3, var1, var2);
         }
      }
   }

   @Override
   public void method6(EmoteDefinition var1, PathFilter var2) {
      if (this.field3 > 0) {
         this.field3--;
      }

      int var3 = 0;
      int var4 = 0;

      for (int var5 = 0; var5 < this.field1.length; var5++) {
         if (this.field2[var5]) {
            boolean var6 = true;
            InactiveTask var7 = this.field1[var5];
            int var8 = var7.method4();
            if (var8 == 0 || ThreadModuleDump63.method8() != null && ThreadModuleDump63.method8().bridge$getGameTime() % var8 == 0L) {
               var7.method6(var1, var2);
               if (var7.method1(var1, var2)) {
                  this.method5(var5, var1, var2);
                  var6 = false;
               } else {
                  var4++;
               }
            }

            if (var6 && var7.isCancellable()) {
               var3++;
            }
         }
      }

      if (var3 == var4) {
         for (int var9 = 0; var9 < this.field1.length; var9++) {
            if (this.field2[var9]) {
               this.method5(var9, var1, var2);
            }
         }
      }
   }

   private void method5(int var1, EmoteDefinition var2, PathFilter var3) {
      InactiveTask var4 = this.field1[var1];
      var4.method3(var2, var3);
      this.method8(var4);
      this.field2[var1] = false;
   }

   @Override
   public int method4() {
      return 0;
   }

   @Override
   public int getDuration() {
      return this.field3;
   }

   private void method8(InactiveTask var1) {
      int var2 = var1.getDuration();
      if (var2 > this.field3) {
         this.field3 = var2;
      }
   }

   private boolean method9() {
      for (boolean var4 : this.field2) {
         if (var4) {
            return true;
         }
      }

      return false;
   }
}

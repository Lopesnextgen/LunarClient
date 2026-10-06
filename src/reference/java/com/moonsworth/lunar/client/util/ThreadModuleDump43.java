package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.Contract;

public interface ThreadModuleDump43<Builder> {
   @Contract("_ -> this")
   default Builder method1(Config var1) {
      return this.method4(var1, Config.field41, false);
   }

   @Contract("_ -> this")
   default Builder method2(Config var1) {
      return this.method4(Config.field1, var1, false);
   }

   @Contract("_,_ -> this")
   default Builder method3(Config var1, Config var2) {
      return this.method4(var1, var2, false);
   }

   @Contract("_,_,_ -> this")
   default Builder method4(Config var1, Config var2, boolean var3) {
      return this.method5(Config.method8(var1, var2, var3).toArray(new Config[0]));
   }

   @Contract("_ -> this")
   default Builder method5(Config... var1) {
      int[] var2 = new int[var1.length];

      for (int var3 = 0; var3 < var1.length; var3++) {
         var2[var3] = var1[var3].getOrdinal();
      }

      return this.method11(var2);
   }

   @Contract("_ -> this")
   default Builder method6(Config... var1) {
      int[] var2 = new int[var1.length];

      for (int var3 = 0; var3 < var1.length; var3++) {
         var2[var3] = var1[var3].getOrdinal();
      }

      return this.method12(var2);
   }

   @Contract("_ -> this")
   default Builder method7(int var1) {
      return this.method10(var1, Config.field41.getOrdinal(), false);
   }

   @Contract("_ -> this")
   default Builder method8(int var1) {
      return this.method10(0, var1, false);
   }

   @Contract("_,_ -> this")
   default Builder method9(int var1, int var2) {
      return this.method10(var1, var2, false);
   }

   @Contract("_,_,_ -> this")
   default Builder method10(int var1, int var2, boolean var3) {
      int[] var4 = new int[var2 - var1 + 1];
      int var5 = var1;

      while (var5 <= var2) {
         var4[var5 - var1] = var5++;
      }

      return var3 ? this.method12(var4) : this.method11(var4);
   }

   @Contract("_ -> this")
   Builder method11(int... var1);

   @Contract("_ -> this")
   Builder method12(int... var1);

   @Contract("-> this")
   default Builder method13() {
      return this.method7(6);
   }

   @Contract("-> this")
   default Builder method14() {
      return this.method8(5);
   }

   @Contract("-> this")
   default Builder method15() {
      return this.method7(9);
   }

   @Contract("-> this")
   default Builder method16() {
      IntArrayList var1 = new IntArrayList();
      var1.add(Config.field2.getOrdinal());

      for (int var2 = Config.field27.getOrdinal(); var2 <= Config.field41.getOrdinal(); var2++) {
         var1.add(var2);
      }

      return this.method11(Arrays.copyOf(var1.elements(), var1.size()));
   }

   @Contract("_ -> this")
   Builder method17(String... var1);

   @Contract("_ -> this")
   Builder method18(String... var1);
}

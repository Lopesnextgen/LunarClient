package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.config.Config;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.Contract;

public interface ConfigRangeBuilder<Builder> {
   @Contract("_ -> this")
   default Builder method1(Config config1) {
      return this.method4(config1, Config.field41, false);
   }

   @Contract("_ -> this")
   default Builder method2(Config config1) {
      return this.method4(Config.field1, config1, false);
   }

   @Contract("_,_ -> this")
   default Builder method3(Config config1, Config config2) {
      return this.method4(config1, config2, false);
   }

   @Contract("_,_,_ -> this")
   default Builder method4(Config config1, Config config2, boolean flag3) {
      return this.method5(Config.method8(config1, config2, flag3).toArray(new Config[0]));
   }

   @Contract("_ -> this")
   default Builder method5(Config... items1) {
      int[] items2 = new int[items1.length];

      for (int index3 = 0; index3 < items1.length; index3++) {
         items2[index3] = items1[index3].getOrdinal();
      }

      return this.method11(items2);
   }

   @Contract("_ -> this")
   default Builder method6(Config... items1) {
      int[] items2 = new int[items1.length];

      for (int index3 = 0; index3 < items1.length; index3++) {
         items2[index3] = items1[index3].getOrdinal();
      }

      return this.method12(items2);
   }

   @Contract("_ -> this")
   default Builder method7(int number1) {
      return this.method10(number1, Config.field41.getOrdinal(), false);
   }

   @Contract("_ -> this")
   default Builder method8(int number1) {
      return this.method10(0, number1, false);
   }

   @Contract("_,_ -> this")
   default Builder method9(int number1, int value) {
      return this.method10(number1, value, false);
   }

   @Contract("_,_,_ -> this")
   default Builder method10(int index1, int index2, boolean flag3) {
      int[] items4 = new int[index2 - index1 + 1];
      int index5 = index1;

      while (index5 <= index2) {
         items4[index5 - index1] = index5++;
      }

      return flag3 ? this.method12(items4) : this.method11(items4);
   }

   @Contract("_ -> this")
   Builder method11(int... items1);

   @Contract("_ -> this")
   Builder method12(int... items1);

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
      IntArrayList intarraylist1 = new IntArrayList();
      intarraylist1.add(Config.field2.getOrdinal());

      for (int index2 = Config.field27.getOrdinal(); index2 <= Config.field41.getOrdinal(); index2++) {
         intarraylist1.add(index2);
      }

      return this.method11(Arrays.copyOf(intarraylist1.elements(), intarraylist1.size()));
   }

   @Contract("_ -> this")
   Builder method17(String... items1);

   @Contract("_ -> this")
   Builder method18(String... items1);
}

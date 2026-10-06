package com.moonsworth.lunar.client.cosmetics.molang;

import com.moonsworth.lunar.client.cosmetics.molang.MolangStmtCompiler;

import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.List;
import lombok.Generated;

public class MolangCustomFunction implements MolangBuiltin {
   private final int field1;
   private final List<MolangVariable> field2;
   private MolangStmtCompiler field3;

   public MolangCustomFunction(int number1, List<MolangVariable> list, MolangStmtCompiler fps63) {
      this.field1 = number1;
      this.field2 = list;
      this.field3 = fps63;
   }

   @Override
   public boolean method1(int number1) {
      return number1 == this.field1;
   }

   @Override
   public boolean method2(int number1) {
      return this.field1 <= 4;
   }

   @KeepName
   public double call() {
      return this.field3.run();
   }

   @KeepName
   public double call(double value1) {
      this.field2.get(0).value = value1;
      return this.field3.run();
   }

   @KeepName
   public double call(double value1, double value3) {
      this.field2.get(0).value = value1;
      this.field2.get(1).value = value3;
      return this.field3.run();
   }

   @KeepName
   public double call(double value1, double value3, double value5) {
      this.field2.get(0).value = value1;
      this.field2.get(1).value = value3;
      this.field2.get(2).value = value5;
      return this.field3.run();
   }

   @KeepName
   public double call(double value1, double value3, double value5, double value2) {
      this.field2.get(0).value = value1;
      this.field2.get(1).value = value3;
      this.field2.get(2).value = value5;
      this.field2.get(3).value = value2;
      return this.field3.run();
   }

   @Override
   public double call(double... items1) {
      for (int index2 = 0; index2 < this.field1; index2++) {
         this.field2.get(index2).value = items1[index2];
      }

      return this.field3.run();
   }

   @Generated
   public List<MolangVariable> method3() {
      return this.field2;
   }

   @Generated
   public void method4(MolangStmtCompiler fps61) {
      this.field3 = fps61;
   }

   @Generated
   public MolangStmtCompiler method5() {
      return this.field3;
   }
}

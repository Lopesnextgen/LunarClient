package com.moonsworth.lunar.client.cosmetics.molang;

import java.util.HashMap;
import java.util.Map;

public class MolangScope {
   final Map<String, MolangSymbol> field1 = new HashMap<>();

   public MolangScope() {
   }

   public void method1() {
      MolangBuiltinFunctions.method1(this);
   }

   public void method2(String text1, MolangSymbol fps72) {
      this.field1.put(text1, fps72);
   }

   public MolangSymbol method3(String text1) {
      return this.field1.get(text1);
   }

   public MolangBuiltin method4(String text1) {
      return this.method3(text1) instanceof MolangBuiltin fps7extension2 ? fps7extension2 : null;
   }

   public MolangVariable method5(String text1) {
      return this.method3(text1) instanceof MolangVariable fps7handler2 ? fps7handler2 : null;
   }

   public MolangScope method6() {
      return new MolangScope.ChildScope(this);
   }

   public MolangVariable method7(String text1) {
      MolangVariable fps7handler2 = (MolangVariable)this.method3(text1);
      if (fps7handler2 != null) {
         return fps7handler2;
      }

      MolangVariable fps7handler3 = new MolangVariable();
      this.method2(text1, fps7handler3);
      return fps7handler3;
   }

   private static class ChildScope extends MolangScope {
      private final MolangScope field2;

      public ChildScope(MolangScope fps111) {
         this.field2 = fps111;
      }

      @Override
      public MolangSymbol method3(String text1) {
         MolangSymbol fps72 = super.method3(text1);
         return fps72 != null ? fps72 : this.field2.method3(text1);
      }
   }
}

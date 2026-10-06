package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.ast.Expr;
import com.eliotlash.molang.functions.Function;
import com.eliotlash.molang.variables.ExecutionContext;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationStateConfig;
import com.moonsworth.lunar.ichor.util.KeepName;
import org.jetbrains.annotations.NotNull;

public class MolangAnimationState extends Function implements MolangBuiltin {
   private final boolean field1;
   @NotNull
   private final String field2;
   private final EmoteDefinition field3;

   public MolangAnimationState(String text1, boolean flag2, @NotNull String text3, EmoteDefinition inactive34) {
      super(text1);
      this.field1 = flag2;
      this.field2 = text3;
      this.field3 = inactive34;
   }

   public boolean method1(int number1) {
      return number1 == 0;
   }

   public boolean method2(int number1) {
      return true;
   }

   @KeepName
   public double call() {
      AnimationStateConfig gui31 = this.field1 ? this.field3.method47() : this.field3.method45();
      if (gui31 == null) {
         return 0.0;
      } else {
         return this.field2.equalsIgnoreCase(gui31.getId()) ? 1.0 : 0.0;
      }
   }

   public double _evaluate(Expr[] items1, ExecutionContext executioncontext2) {
      return this.call();
   }

   public double evaluate(Expr[] items1, ExecutionContext executioncontext2) {
      return this.call();
   }
}

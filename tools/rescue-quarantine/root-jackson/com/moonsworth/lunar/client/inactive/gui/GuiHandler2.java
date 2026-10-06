package com.moonsworth.lunar.client.inactive.gui;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.fov.Fov10;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.easing.EasingType;

public class GuiHandler2 implements Gui {
   private GuiHandler2.Data2[] field1;

   @Override
   public @Nullable GuiHandler method1(Fov10 var1) {
      Evaluator var2 = var1.getEvaluator();

      for (GuiHandler2.Data2 var6 : this.field1) {
         var6.field5.evaluate(var2);
         if (var6.field5.evaluate(var2) == 1.0) {
            return var6;
         }
      }

      return null;
   }

   @Generated
   public GuiHandler2(GuiHandler2.Data2[] var1) {
      this.field1 = var1;
   }

   public static class Data2 extends GuiHandler {
      @Annotation27(value = "condition", required = true)
      private Evaluatable field5;

      public Data2(AnimationBuilder var1, @Nullable Evaluatable var2, Double @Nullable [] var3, EasingType var4, Evaluatable var5) {
         super(var1, var2, var3, var4);
         this.field5 = var5;
      }

      @Generated
      public Data2() {
      }
   }
}

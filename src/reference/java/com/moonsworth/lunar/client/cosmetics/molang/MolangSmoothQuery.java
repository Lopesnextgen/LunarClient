package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.client.cosmetics.molang.MolangScope;
import com.moonsworth.lunar.client.cosmetics.molang.MolangBuiltin;
import com.moonsworth.lunar.client.cosmetics.molang.MolangVariable;
import com.moonsworth.lunar.client.cosmetics.molang.QueryTransition;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.List;
import software.bernie.geckolib3.core.easing.EasingType;

public class MolangSmoothQuery implements MolangBuiltin {
   private final String field1;
   private final EasingType field2;
   private final MolangScope field3;
   private QueryTransition field4;
   private MolangVariable field5;

   public MolangSmoothQuery(String text, EasingType easingType, MolangScope fps113) {
      this.field1 = text;
      this.field2 = easingType;
      this.field3 = fps113;
   }

   public boolean method1(int number1) {
      return number1 == 1 || number1 == 2;
   }

   public boolean method2(int number1) {
      return true;
   }

   @KeepName
   public double call(double value1) {
      if (this.field5 == null) {
         this.field5 = this.field3.method7(this.field1);
      }

      if (this.field4 == null) {
         this.field4 = new QueryTransition(this.field2, this.field1, value1, MolangUtils.doubleToBoolean(this.field5.value));
      }

      return this.field4.method1(MolangUtils.doubleToBoolean(this.field5.value), value1, List.of());
   }

   @KeepName
   public double call(double value1, double value2) {
      if (this.field5 == null) {
         this.field5 = this.field3.method5(this.field1);
      }

      if (this.field4 == null) {
         this.field4 = new QueryTransition(this.field2, this.field1, value1, MolangUtils.doubleToBoolean(this.field5.value));
      }

      return this.field4.method1(MolangUtils.doubleToBoolean(this.field5.value), value1, List.of(value2));
   }
}

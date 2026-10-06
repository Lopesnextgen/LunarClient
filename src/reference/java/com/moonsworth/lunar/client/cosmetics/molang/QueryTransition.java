package com.moonsworth.lunar.client.cosmetics.molang;

import com.eliotlash.molang.utils.Interpolations;
import com.eliotlash.molang.utils.MolangUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;
import software.bernie.geckolib3.core.easing.EasingManager;
import software.bernie.geckolib3.core.easing.EasingType;

public class QueryTransition {
   private final EasingType easingType;
   private final String field1;
   private double length;
   private boolean field2;
   private long startTime = Ref.method14();
   private boolean field3 = false;

   public QueryTransition(EasingType easingType2, String text, double value, boolean flag) {
      this.easingType = easingType2;
      this.field1 = text;
      this.length = value;
      this.field2 = flag;
   }

   public double method1(boolean flag, double value, List<Double> list) {
      if (flag == this.field2) {
         if (!this.field3) {
            return MolangUtils.booleanToFloat(this.field2);
         }

         this.startTime = Ref.method14();
         this.field2 = !this.field2;
      }

      if (!this.field3) {
         this.startTime = Ref.method14();
      }

      this.field3 = true;
      double value5 = this.startTime + value * 1000.0;
      double value7 = Ref.method14() - this.startTime;
      double value9 = value7 / (value5 - this.startTime);
      if (Ref.method14() - this.startTime >= value * 1000.0) {
         this.field3 = false;
         this.field2 = flag;
         return MolangUtils.booleanToFloat(this.field2);
      } else {
         value9 = EasingManager.ease(value9, this.easingType, list);
         return Interpolations.lerp(MolangUtils.booleanToFloat(this.field2), MolangUtils.booleanToFloat(flag), value9);
      }
   }

   @Generated
   public String getQuery() {
      return this.field1;
   }

   @Generated
   public double getLength() {
      return this.length;
   }
}

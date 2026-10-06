package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class FloatNumberRange extends AbstractNumberRule<Float> {
   private final float field5;
   private final float field6;
   private final int field7;

   public FloatNumberRange(float value1, float value2, boolean flag3, boolean flag4, int number5) {
      super(flag3, flag4);
      this.field5 = value1;
      this.field6 = value2;
      this.field7 = number5;
   }

   public Float method5() {
      return this.field5;
   }

   public Float method7() {
      return this.field6;
   }

   @Override
   public boolean method6() {
      return this.field7 == 1;
   }

   @Override
   public String method4() {
      return "Float";
   }

   public Float method6(double value1) {
      return (float)value1;
   }

   public static FloatNumberRange method6(float value0, float value1, boolean flag2, boolean flag3, int number4) {
      int number5 = 4;
      number5 = 31 * number5 + Float.hashCode(value0);
      number5 = 31 * number5 + Float.hashCode(value1);
      number5 = 31 * number5 + Boolean.hashCode(flag2);
      number5 = 31 * number5 + Boolean.hashCode(flag3);
      number5 = 31 * number5 + Integer.hashCode(number4);
      return (FloatNumberRange)method6(number5, arg5x -> new FloatNumberRange(value0, value1, flag2, flag3, number4));
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof FloatNumberRange nameplateimpl22)) {
         return false;
      } else if (!nameplateimpl22.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Float value3 = this.method5();
         Float value4 = nameplateimpl22.method5();
         if (value3 == null ? value4 == null : value3.equals(value4)) {
            Float value5 = this.method7();
            Float value6 = nameplateimpl22.method7();
            return (value5 == null ? value6 == null : value5.equals(value6)) ? this.method3() == nameplateimpl22.method3() : false;
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof FloatNumberRange;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Float value3 = this.method5();
      number2 = number2 * 59 + (value3 == null ? 43 : value3.hashCode());
      Float value4 = this.method7();
      number2 = number2 * 59 + (value4 == null ? 43 : value4.hashCode());
      return number2 * 59 + this.method3();
   }

   @Generated
   @Override
   public int method3() {
      return this.field7;
   }
}

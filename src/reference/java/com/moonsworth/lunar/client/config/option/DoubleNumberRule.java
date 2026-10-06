package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class DoubleNumberRule extends AbstractNumberRule<Double> {
   private final double field5;
   private final double field6;
   private final int field7;

   public DoubleNumberRule(double value1, double value3, boolean flag5, boolean flag6, int number7) {
      super(flag5, flag6);
      this.field5 = value1;
      this.field6 = value3;
      this.field7 = number7;
   }

   public Double method5() {
      return this.field5;
   }

   public Double method7() {
      return this.field6;
   }

   @Override
   public boolean method6() {
      return this.field7 == 1;
   }

   @Override
   public String method4() {
      return "Double";
   }

   public Double method6(double value1) {
      return value1;
   }

   public static DoubleNumberRule method6(double value0, double value2, boolean flag4, boolean flag5, int number6) {
      int number7 = 5;
      number7 = 31 * number7 + Double.hashCode(value0);
      number7 = 31 * number7 + Double.hashCode(value2);
      number7 = 31 * number7 + Boolean.hashCode(flag4);
      number7 = 31 * number7 + Boolean.hashCode(flag5);
      number7 = 31 * number7 + Integer.hashCode(number6);
      return (DoubleNumberRule)method6(number7, arg7x -> new DoubleNumberRule(value0, value2, flag4, flag5, number6));
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof DoubleNumberRule nameplateimpl2)) {
         return false;
      } else if (!nameplateimpl2.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Double value3 = this.method5();
         Double value4 = nameplateimpl2.method5();
         if (value3 == null ? value4 == null : value3.equals(value4)) {
            Double value5 = this.method7();
            Double value6 = nameplateimpl2.method7();
            return (value5 == null ? value6 == null : value5.equals(value6)) ? this.method3() == nameplateimpl2.method3() : false;
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof DoubleNumberRule;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Double value3 = this.method5();
      number2 = number2 * 59 + (value3 == null ? 43 : value3.hashCode());
      Double value4 = this.method7();
      number2 = number2 * 59 + (value4 == null ? 43 : value4.hashCode());
      return number2 * 59 + this.method3();
   }

   @Generated
   @Override
   public int method3() {
      return this.field7;
   }
}

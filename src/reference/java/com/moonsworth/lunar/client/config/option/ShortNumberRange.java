package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class ShortNumberRange extends AbstractNumberRule<Short> {
   private final short field5;
   private final short field6;

   public ShortNumberRange(short number1, short number2, boolean flag3, boolean flag4) {
      super(flag3, flag4);
      this.field5 = number1;
      this.field6 = number2;
   }

   public Short method5() {
      return this.field5;
   }

   public Short method7() {
      return this.field6;
   }

   @Override
   public String method4() {
      return "Short";
   }

   public Short method4(double value1) {
      return (short)Math.round(value1);
   }

   public static ShortNumberRange method5(short number0, short number1, boolean flag2, boolean flag3) {
      int number4 = 1;
      number4 = 31 * number4 + Short.hashCode(number0);
      number4 = 31 * number4 + Short.hashCode(number1);
      number4 = 31 * number4 + Boolean.hashCode(flag2);
      number4 = 31 * number4 + Boolean.hashCode(flag3);
      return (ShortNumberRange)method5(number4, arg4x -> new ShortNumberRange(number0, number1, flag2, flag3));
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ShortNumberRange nameplateimpl42)) {
         return false;
      } else if (!nameplateimpl42.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Short number3 = this.method5();
         Short number4 = nameplateimpl42.method5();
         if (number3 == null ? number4 == null : number3.equals(number4)) {
            Short number5 = this.method7();
            Short number6 = nameplateimpl42.method7();
            return number5 == null ? number6 == null : number5.equals(number6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ShortNumberRange;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Short number3 = this.method5();
      number2 = number2 * 59 + (number3 == null ? 43 : number3.hashCode());
      Short number4 = this.method7();
      return number2 * 59 + (number4 == null ? 43 : number4.hashCode());
   }
}

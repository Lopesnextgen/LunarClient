package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class LongNumberRange extends AbstractNumberRule<Long> {
   private final long field5;
   private final long field6;

   public LongNumberRange(long number1, long number3, boolean flag5, boolean flag6) {
      super(flag5, flag6);
      this.field5 = number1;
      this.field6 = number3;
   }

   public Long method5() {
      return this.field5;
   }

   public Long method7() {
      return this.field6;
   }

   @Override
   public String method4() {
      return "Long";
   }

   public Long method4(double value1) {
      return Math.round(value1);
   }

   public static LongNumberRange method5(long number0, long number2, boolean flag4, boolean flag5) {
      int number6 = 3;
      number6 = 31 * number6 + Long.hashCode(number0);
      number6 = 31 * number6 + Long.hashCode(number2);
      number6 = 31 * number6 + Boolean.hashCode(flag4);
      number6 = 31 * number6 + Boolean.hashCode(flag5);
      return (LongNumberRange)method5(number6, arg6x -> new LongNumberRange(number0, number2, flag4, flag5));
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof LongNumberRange nameplateimpl32)) {
         return false;
      } else if (!nameplateimpl32.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Long number3 = this.method5();
         Long number4 = nameplateimpl32.method5();
         if (number3 == null ? number4 == null : number3.equals(number4)) {
            Long number5 = this.method7();
            Long number6 = nameplateimpl32.method7();
            return number5 == null ? number6 == null : number5.equals(number6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof LongNumberRange;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Long number3 = this.method5();
      number2 = number2 * 59 + (number3 == null ? 43 : number3.hashCode());
      Long number4 = this.method7();
      return number2 * 59 + (number4 == null ? 43 : number4.hashCode());
   }
}

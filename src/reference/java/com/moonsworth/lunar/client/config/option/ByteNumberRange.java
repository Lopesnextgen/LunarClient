package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public class ByteNumberRange extends AbstractNumberRule<Byte> {
   private final byte field5;
   private final byte field6;

   public ByteNumberRange(byte number1, byte number2, boolean flag3, boolean flag4) {
      super(flag3, flag4);
      this.field5 = number1;
      this.field6 = number2;
   }

   public static ByteNumberRange method1(byte number0, byte number1, boolean flag2, boolean flag3) {
      int number4 = 0;
      number4 = 31 * number4 + Byte.hashCode(number0);
      number4 = 31 * number4 + Byte.hashCode(number1);
      number4 = 31 * number4 + Boolean.hashCode(flag2);
      number4 = 31 * number4 + Boolean.hashCode(flag3);
      return (ByteNumberRange)method1(number4, arg4x -> new ByteNumberRange(number0, number1, flag2, flag3));
   }

   public Byte method5() {
      return this.field5;
   }

   public Byte method7() {
      return this.field6;
   }

   @Override
   public String method4() {
      return "Byte";
   }

   public Byte method6(double value1) {
      return (byte)Math.round(value1);
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ByteNumberRange nameplateimpl62)) {
         return false;
      } else if (!nameplateimpl62.canEqual(this)) {
         return false;
      } else if (!super.equals(obj1)) {
         return false;
      } else {
         Byte number3 = this.method5();
         Byte number4 = nameplateimpl62.method5();
         if (number3 == null ? number4 == null : number3.equals(number4)) {
            Byte number5 = this.method7();
            Byte number6 = nameplateimpl62.method7();
            return number5 == null ? number6 == null : number5.equals(number6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ByteNumberRange;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = super.hashCode();
      Byte number3 = this.method5();
      number2 = number2 * 59 + (number3 == null ? 43 : number3.hashCode());
      Byte number4 = this.method7();
      return number2 * 59 + (number4 == null ? 43 : number4.hashCode());
   }
}

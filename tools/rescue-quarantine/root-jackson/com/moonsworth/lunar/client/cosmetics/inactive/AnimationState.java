package com.moonsworth.lunar.client.cosmetics.inactive;

import com.moonsworth.lunar.Annotation27;
import lombok.Generated;

public class AnimationState {
   private String field1;
   private Boolean field2;
   private Long field3;

   @Generated
   public AnimationState() {
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof AnimationState inactive22)) {
         return false;
      } else {
         if (!inactive22.canEqual(this)) {
            return false;
         }

         Boolean flag3 = this.method3();
         Boolean flag4 = inactive22.method3();
         if (flag3 == null ? flag4 == null : flag3.equals(flag4)) {
            Long number5 = this.method5();
            Long number6 = inactive22.method5();
            if (number5 == null ? number6 == null : number5.equals(number6)) {
               String text7 = this.method1();
               String text8 = inactive22.method1();
               return text7 == null ? text8 == null : text7.equals(text8);
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof AnimationState;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      Boolean flag3 = this.method3();
      number2 = number2 * 59 + (flag3 == null ? 43 : flag3.hashCode());
      Long number4 = this.method5();
      number2 = number2 * 59 + (number4 == null ? 43 : number4.hashCode());
      String text5 = this.method1();
      return number2 * 59 + (text5 == null ? 43 : text5.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "AnimationState(animation=" + this.method1() + ", isDefault=" + this.method3() + ", timer=" + this.method5() + ")";
   }

   @Annotation27("animation")
   @Generated
   public String method1() {
      return this.field1;
   }

   @Annotation27("animation")
   @Generated
   public void method2(String text1) {
      this.field1 = text1;
   }

   @Annotation27("is_default")
   @Generated
   public Boolean method3() {
      return this.field2;
   }

   @Annotation27("is_default")
   @Generated
   public void method4(Boolean flag1) {
      this.field2 = flag1;
   }

   @Annotation27("timer")
   @Generated
   public Long method5() {
      return this.field3;
   }

   @Annotation27("timer")
   @Generated
   public void method6(Long number1) {
      this.field3 = number1;
   }
}

package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.bridge.KeyCode;
import lombok.Generated;

public class KeyVersionPair {
   private String field1;
   private KeyCode field2;
   private int field3;

   @Generated
   public KeyVersionPair(String text1, KeyCode bridgetype_82, int value) {
      this.field1 = text1;
      this.field2 = bridgetype_82;
      this.field3 = value;
   }

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public KeyCode method2() {
      return this.field2;
   }

   @Generated
   public int method3() {
      return this.field3;
   }

   @Generated
   public void method4(String text1) {
      this.field1 = text1;
   }

   @Generated
   public void method5(KeyCode bridgetype_81) {
      this.field2 = bridgetype_81;
   }

   @Generated
   public void method6(int number1) {
      this.field3 = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof KeyVersionPair threadmoduledump302)) {
         return false;
      } else if (!threadmoduledump302.canEqual(this)) {
         return false;
      } else if (this.method3() != threadmoduledump302.method3()) {
         return false;
      } else {
         String text3 = this.method1();
         String text4 = threadmoduledump302.method1();
         if (text3 == null ? text4 == null : text3.equals(text4)) {
            KeyCode bridgetype_85 = this.method2();
            KeyCode bridgetype_86 = threadmoduledump302.method2();
            return bridgetype_85 == null ? bridgetype_86 == null : bridgetype_85.equals(bridgetype_86);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof KeyVersionPair;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.method3();
      String text3 = this.method1();
      number2 = number2 * 59 + (text3 == null ? 43 : text3.hashCode());
      KeyCode bridgetype_84 = this.method2();
      return number2 * 59 + (bridgetype_84 == null ? 43 : bridgetype_84.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "KeyVersionPair(keyCode=" + this.method1() + ", key=" + this.method2() + ", legacyKeyCode=" + this.method3() + ")";
   }
}

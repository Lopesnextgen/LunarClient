package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 1)
public class WidgetState {
   private final WidgetStateCache field1;
   public String field2;
   public int field3;
   public int field4;
   private double field5 = 0.0;
   private int field6 = 0;
   private int field7 = 0;
   public boolean isDirty = true;

   public WidgetState(String text, int number2, int number3, WidgetStateCache mixinhelper24) {
      this.field2 = text;
      this.field3 = number2;
      this.field4 = number3;
      this.field1 = mixinhelper24;
   }

   public void method1(int value, int number2, int number3, int value2) {
      this.field6 = value;
      this.field7 = number2;
      if (this.field3 != number3 || this.field4 != value2 || this.method5() != this.field5) {
         this.field5 = this.method5();
         this.isDirty = true;
      }

      this.field3 = number3;
      this.field4 = value2;
   }

   public void method2() {
   }

   public void method3() {
   }

   public void method4() {
   }

   public void delete() {
   }

   private double method5() {
      return Ref.method3().bridge$getGuiScale() * this.field1.method17().field9;
   }

   private double method6() {
      return Ref.method3().bridge$getGuiScale() * this.field1.method17().field10;
   }
}

package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.KeyCode;
import lombok.Generated;
import com.moonsworth.lunar.client.config.option.KeyVersionPair;

public class ThreadModuleDump30 {
   private String field1;
   private KeyCode field2;
   private int field3;

   @Generated
   public ThreadModuleDump30(String var1, KeyCode var2, int var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
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
   public void method4(String var1) {
      this.field1 = var1;
   }

   @Generated
   public void method5(KeyCode var1) {
      this.field2 = var1;
   }

   @Generated
   public void method6(int var1) {
      this.field3 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ThreadModuleDump30 var2)) {
         return false;
      } else if (!var2.canEqual(this)) {
         return false;
      } else if (this.method3() != var2.method3()) {
         return false;
      } else {
         String var3 = this.method1();
         String var4 = var2.method1();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            KeyCode var5 = this.method2();
            KeyCode var6 = var2.method2();
            return var5 == null ? var6 == null : var5.equals(var6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof ThreadModuleDump30;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method3();
      String var3 = this.method1();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      KeyCode var4 = this.method2();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "KeyVersionPair(keyCode=" + this.method1() + ", key=" + this.method2() + ", legacyKeyCode=" + this.method3() + ")";
   }
}

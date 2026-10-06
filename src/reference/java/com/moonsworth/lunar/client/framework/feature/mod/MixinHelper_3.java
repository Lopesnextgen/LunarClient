package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 1)
public class MixinHelper_3 {
   private final MixinHelper2 field1;
   public String field2;
   public int field3;
   public int field4;
   private double field5 = 0.0;
   private int field6 = 0;
   private int field7 = 0;
   public boolean isDirty = true;

   public MixinHelper_3(String var1, int var2, int var3, MixinHelper2 var4) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field1 = var4;
   }

   public void method1(int var1, int var2, int var3, int var4) {
      this.field6 = var1;
      this.field7 = var2;
      if (this.field3 != var3 || this.field4 != var4 || this.method5() != this.field5) {
         this.field5 = this.method5();
         this.isDirty = true;
      }

      this.field3 = var3;
      this.field4 = var4;
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
      return ThreadModuleDump63.method3().bridge$getGuiScale() * this.field1.getRenderState().field9;
   }

   private double method6() {
      return ThreadModuleDump63.method3().bridge$getGuiScale() * this.field1.getRenderState().field10;
   }
}

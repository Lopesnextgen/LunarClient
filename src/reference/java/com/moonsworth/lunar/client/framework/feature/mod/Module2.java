package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Module2 {
   private final GuiRewindhandlersHandler2 field1;
   private final List<Runnable> field2 = new ArrayList<>();

   public boolean method1() {
      return this.field1.isScreenActive() ? this.field1.method9().method4() : false;
   }

   public boolean method2() {
      return this.field1.isScreenActive() ? this.field1.method9().method5() : false;
   }

   public void method3(Bridge5Extension6 var1, MixinHelper_4 var2, int var3, int var4, float var5) {
      if (this.field1.isScreenActive()) {
         this.field1.method9().method5(var1, var2, var3, var4, var5);
         this.field2.forEach(Runnable::run);
         this.field2.clear();
      } else {
         for (ModuleBase2 var7 : this.field1.getActiveOverlays()) {
            var7.method5(var1, var2, var3, var4, var5);
         }

         this.field2.forEach(Runnable::run);
         this.field2.clear();
      }
   }

   public boolean method4(Bridge5Extension6 var1, double var2) {
      if (this.field1.isScreenActive()) {
         this.field1.method9().method5(var1, var2);
         return true;
      }

      for (ModuleBase2 var5 : this.field1.getActiveOverlays()) {
         if (var5.method5(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean method5(Bridge5Extension6 var1, int var2, int var3, int var4) {
      if (this.field1.isScreenActive()) {
         this.field1.method9().method3(var1, var2, var3, var4);
         return true;
      }

      for (ModuleBase2 var6 : this.field1.getActiveOverlays()) {
         if (var6.method3(var1, var2, var3, var4)) {
            return true;
         }
      }

      return false;
   }

   public boolean method6(Bridge5Extension6 var1, int var2, int var3, int var4) {
      if (this.field1.isScreenActive()) {
         this.field1.method9().method4(var1, var2, var3, var4);
         return true;
      }

      for (ModuleBase2 var6 : this.field1.getActiveOverlays()) {
         if (var6.method4(var1, var2, var3, var4)) {
            return true;
         }
      }

      return false;
   }

   public boolean method7(Bridge5Extension6 var1, int var2, int var3, int var4, long var5) {
      if (this.field1.isScreenActive()) {
         this.field1.method9().method6(var1, var2, var3, var4, var5);
         return true;
      }

      for (ModuleBase2 var8 : this.field1.getActiveOverlays()) {
         if (var8.method6(var1, var2, var3, var4, var5)) {
            return true;
         }
      }

      return false;
   }

   public boolean method8(Bridge5Extension6 var1, Bridge_7 var2) {
      if (this.field1.isScreenActive()) {
         return this.field1.method9().method10(var1, var2);
      }

      for (ModuleBase2 var4 : this.field1.getActiveOverlays()) {
         if (var4.method10(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public void method9(Runnable var1) {
      if (this.method10()) {
         this.field2.add(var1);
      } else {
         var1.run();
      }
   }

   public boolean method10() {
      return this.field1.isScreenActive() || !this.field1.getActiveOverlays().isEmpty();
   }

   @Generated
   public Module2(GuiRewindhandlersHandler2 var1) {
      this.field1 = var1;
   }
}

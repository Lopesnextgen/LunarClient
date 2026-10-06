package com.moonsworth.lunar.client.framework.feature.waila.mixin;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.OptionalInt;
import lombok.Generated;

public class Waila2 {
   private final List<Waila> field1;
   private int width = -1;
   private int height = -1;

   public Waila2(Waila... var1) {
      this.field1 = List.of(var1);
   }

   public Waila2(List<Waila> var1) {
      this.field1 = var1;
   }

   public int getWidth() {
      if (this.width == -1) {
         OptionalInt var1 = this.field1.stream().mapToInt(Waila::getWidth).max();
         this.width = var1.isPresent() ? var1.getAsInt() : -1;
      }

      return this.width;
   }

   public int getHeight() {
      if (this.height == -1) {
         this.height = this.field1.stream().mapToInt(Waila::getHeight).sum() + ThreadModuleDump63.method4().method40().method71().method15();
      }

      return this.height;
   }

   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      int var5 = var2.method15() / 2;

      for (Waila var7 : this.field1) {
         var7.method1(var1, var2, var3, var4 + var5);
         var5 += var7.getHeight();
      }
   }

   public List<Waila> method2() {
      return this.field1;
   }

   @Generated
   public void setWidth(int var1) {
      this.width = var1;
   }

   @Generated
   public void setHeight(int var1) {
      this.height = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Waila2 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.getWidth() != var2.getWidth()) {
            return false;
         }

         if (this.getHeight() != var2.getHeight()) {
            return false;
         }

         List var3 = this.method2();
         List var4 = var2.method2();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Waila2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.getWidth();
      var2 = var2 * 59 + this.getHeight();
      List var3 = this.method2();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "WAILALayout(rows=" + this.method2() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
   }
}

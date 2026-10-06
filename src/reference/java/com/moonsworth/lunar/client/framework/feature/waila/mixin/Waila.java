package com.moonsworth.lunar.client.framework.feature.waila.mixin;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.List;
import java.util.OptionalInt;
import lombok.Generated;

public class Waila {
   private final List<com.moonsworth.lunar.client.framework.feature.waila.Waila> field1;
   private final float field2;

   public Waila(com.moonsworth.lunar.client.framework.feature.waila.Waila... var1) {
      this(1.0F, var1);
   }

   public Waila(float var1, com.moonsworth.lunar.client.framework.feature.waila.Waila... var2) {
      this.field1 = List.of(var2);
      this.field2 = var1;
   }

   public Waila(List<com.moonsworth.lunar.client.framework.feature.waila.Waila> var1) {
      this(1.0F, var1);
   }

   public Waila(float var1, List<com.moonsworth.lunar.client.framework.feature.waila.Waila> var2) {
      this.field1 = var2;
      this.field2 = var1;
   }

   public int getWidth() {
      return (int)(this.field1.stream().mapToInt(com.moonsworth.lunar.client.framework.feature.waila.Waila::getWidth).sum() * this.field2);
   }

   public int getHeight() {
      OptionalInt var1 = this.field1.stream().mapToInt(com.moonsworth.lunar.client.framework.feature.waila.Waila::getHeight).max();
      return var1.isPresent() ? (int)(var1.getAsInt() * this.field2) : 0;
   }

   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      int var5 = 0;
      var1.push();
      var1.scale(this.field2, this.field2, this.field2);

      for (com.moonsworth.lunar.client.framework.feature.waila.Waila var7 : this.field1) {
         var7.method1(var1, var2, (int)(var3 / this.field2) + var5, (int)(var4 / this.field2));
         var5 = (int)(var5 + var7.getWidth() * this.field2);
      }

      var1.pop();
   }

   @Generated
   public List<com.moonsworth.lunar.client.framework.feature.waila.Waila> getElements() {
      return this.field1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Waila var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (Float.compare(this.getScale(), var2.getScale()) != 0) {
            return false;
         }

         List var3 = this.getElements();
         List var4 = var2.getElements();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Waila;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + Float.floatToIntBits(this.getScale());
      List var3 = this.getElements();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "WAILARow(elements=" + this.getElements() + ", scale=" + this.getScale() + ")";
   }

   @Generated
   public float getScale() {
      return this.field2;
   }
}

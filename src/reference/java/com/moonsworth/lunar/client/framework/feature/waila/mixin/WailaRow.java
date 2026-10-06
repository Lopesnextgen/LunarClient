package com.moonsworth.lunar.client.framework.feature.waila.mixin;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import java.util.List;
import java.util.OptionalInt;
import lombok.Generated;

public class WailaRow {
   private final List<com.moonsworth.lunar.client.framework.feature.waila.WailaComponent> field1;
   private final float field2;

   public WailaRow(com.moonsworth.lunar.client.framework.feature.waila.WailaComponent... items1) {
      this(1.0F, items1);
   }

   public WailaRow(float value1, com.moonsworth.lunar.client.framework.feature.waila.WailaComponent... items2) {
      this.field1 = List.of(items2);
      this.field2 = value1;
   }

   public WailaRow(List<com.moonsworth.lunar.client.framework.feature.waila.WailaComponent> list) {
      this(1.0F, list);
   }

   public WailaRow(float value1, List<com.moonsworth.lunar.client.framework.feature.waila.WailaComponent> list) {
      this.field1 = list;
      this.field2 = value1;
   }

   public int getWidth() {
      return (int)(this.field1.stream().mapToInt(com.moonsworth.lunar.client.framework.feature.waila.WailaComponent::getWidth).sum() * this.field2);
   }

   public int getHeight() {
      OptionalInt optionalint1 = this.field1.stream().mapToInt(com.moonsworth.lunar.client.framework.feature.waila.WailaComponent::getHeight).max();
      return optionalint1.isPresent() ? (int)(optionalint1.getAsInt() * this.field2) : 0;
   }

   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int value2) {
      int number5 = 0;
      mixinhelper_41.push();
      mixinhelper_41.scale(this.field2, this.field2, this.field2);

      for (com.moonsworth.lunar.client.framework.feature.waila.WailaComponent waila7 : this.field1) {
         waila7.method1(mixinhelper_41, waila2, (int)(value / this.field2) + number5, (int)(value2 / this.field2));
         number5 = (int)(number5 + waila7.getWidth() * this.field2);
      }

      mixinhelper_41.pop();
   }

   @Generated
   public List<com.moonsworth.lunar.client.framework.feature.waila.WailaComponent> getElements() {
      return this.field1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof WailaRow waila2)) {
         return false;
      } else {
         if (!waila2.canEqual(this)) {
            return false;
         }

         if (Float.compare(this.getScale(), waila2.getScale()) != 0) {
            return false;
         }

         List list3 = this.getElements();
         List list4 = waila2.getElements();
         return list3 == null ? list4 == null : list3.equals(list4);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof WailaRow;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + Float.floatToIntBits(this.getScale());
      List list3 = this.getElements();
      return number2 * 59 + (list3 == null ? 43 : list3.hashCode());
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

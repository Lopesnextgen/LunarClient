package com.moonsworth.lunar.client.framework.feature.waila.mixin;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.OptionalInt;
import lombok.Generated;

public class WailaLayout {
   private final List<WailaRow> field1;
   private int width = -1;
   private int height = -1;

   public WailaLayout(WailaRow... items1) {
      this.field1 = List.of(items1);
   }

   public WailaLayout(List<WailaRow> list) {
      this.field1 = list;
   }

   public int getWidth() {
      if (this.width == -1) {
         OptionalInt optionalint1 = this.field1.stream().mapToInt(WailaRow::getWidth).max();
         this.width = optionalint1.isPresent() ? optionalint1.getAsInt() : -1;
      }

      return this.width;
   }

   public int getHeight() {
      if (this.height == -1) {
         this.height = this.field1.stream().mapToInt(WailaRow::getHeight).sum() + Ref.method4().method40().method71().method15();
      }

      return this.height;
   }

   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int value, int value2) {
      int number5 = waila2.method15() / 2;

      for (WailaRow waila7 : this.field1) {
         waila7.method1(mixinhelper_41, waila2, value, value2 + number5);
         number5 += waila7.getHeight();
      }
   }

   public List<WailaRow> method2() {
      return this.field1;
   }

   @Generated
   public void setWidth(int number1) {
      this.width = number1;
   }

   @Generated
   public void setHeight(int number1) {
      this.height = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof WailaLayout waila22)) {
         return false;
      } else {
         if (!waila22.canEqual(this)) {
            return false;
         }

         if (this.getWidth() != waila22.getWidth()) {
            return false;
         }

         if (this.getHeight() != waila22.getHeight()) {
            return false;
         }

         List list3 = this.method2();
         List list4 = waila22.method2();
         return list3 == null ? list4 == null : list3.equals(list4);
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof WailaLayout;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.getWidth();
      number2 = number2 * 59 + this.getHeight();
      List list3 = this.method2();
      return number2 * 59 + (list3 == null ? 43 : list3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "WAILALayout(rows=" + this.method2() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
   }
}

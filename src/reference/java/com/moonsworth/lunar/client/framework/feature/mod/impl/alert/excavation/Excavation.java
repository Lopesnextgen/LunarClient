package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation;

import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.ExcavationType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.FossilPattern;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.FossilCellState;
import org.joml.Vector2i;

public class Excavation {
   private final FossilPattern field1;
   private final int field2;
   private final int field3;
   private final ExcavationType field4;

   public Excavation(FossilPattern fossilPattern, int number2, int value, ExcavationType excavationType) {
      this.field1 = fossilPattern;
      this.field2 = number2;
      this.field3 = value;
      this.field4 = excavationType;
   }

   public int getWidth() {
      return this.field4.isSwapXY() ? this.field1.getHeight() : this.field1.getWidth();
   }

   public int getHeight() {
      return this.field4.isSwapXY() ? this.field1.getWidth() : this.field1.getHeight();
   }

   public Vector2i method1(int number1, int number2) {
      return this.field4.transform(number1 - this.field2, number2 - this.field3, this.field1.getWidth(), this.field1.getHeight());
   }

   public boolean matches(int number1, int number2) {
      Vector2i vector2i3 = this.method1(number1, number2);
      return vector2i3.x >= 0 && vector2i3.x < this.field1.getWidth() && vector2i3.y >= 0 && vector2i3.y < this.field1.getHeight() ? this.field1.matches(vector2i3.x, vector2i3.y) : false;
   }

   public boolean method2(ExcavationGrid excavation21) {
      for (int index2 = 0; index2 < 9; index2++) {
         for (int index3 = 0; index3 < 6; index3++) {
            FossilCellState excavationtype34 = excavation21.method3(index2, index3);
            if (excavationtype34 != FossilCellState.UNKNOWN) {
               boolean flag5 = this.matches(index2, index3);
               if (flag5 && excavationtype34 == FossilCellState.EMPTY || !flag5 && excavationtype34 == FossilCellState.FOSSIL) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }

      if (object != null && this.getClass() == object.getClass()) {
         Excavation excavation2 = (Excavation)object;
         if (this.field2 == excavation2.field2 && this.field3 == excavation2.field3 && this.field1 == excavation2.field1) {
            if (this.field4 == excavation2.field4) {
               return true;
            }

            for (int index3 = 0; index3 < this.getWidth(); index3++) {
               for (int index4 = 0; index4 < this.getHeight(); index4++) {
                  if (this.matches(index3, index4) != excavation2.matches(index3, index4)) {
                     return false;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}

package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelRenderConfig;
import org.joml.Vector3f;

public class ModelQuad {
   public static final int[] field1 = new int[]{0, 1, 2, 3};
   public static final int[] field2 = new int[]{1, 0, 3, 2};
   public ModelVertex[] field3;
   public final Vector3f field4;
   public HorsestatsType_2 field5;

   public ModelQuad(ModelVertex[] items1, float value, float value2, float value4, float value5, float value3, float value6, Boolean booleanValue, HorsestatsType_2 horsestatstype_29) {
      this.field5 = horsestatstype_29;
      this.field3 = items1;
      float value10 = value + value4;
      float value11 = value2 + value5;
      value /= value3;
      value10 /= value3;
      value2 /= value6;
      value11 /= value6;
      if (booleanValue != null && booleanValue) {
         this.field3[0] = items1[0].method1(value, value2);
         this.field3[1] = items1[1].method1(value10, value2);
         this.field3[2] = items1[2].method1(value10, value11);
         this.field3[3] = items1[3].method1(value, value11);
      } else {
         this.field3[0] = items1[0].method1(value10, value2);
         this.field3[1] = items1[1].method1(value, value2);
         this.field3[2] = items1[2].method1(value, value11);
         this.field3[3] = items1[3].method1(value10, value11);
      }

      this.field4 = horsestatstype_29.getUnitVector();
      if (booleanValue != null && booleanValue) {
         this.field4.mul(-1.0F, 1.0F, 1.0F);
      }
   }

   public ModelQuad(ModelVertex[] items1, double[] items2, double[] items3, float value4, float value5, Boolean booleanValue, HorsestatsType_2 horsestatstype_27) {
      this(items1, (float)items2[0], (float)items2[1], (float)items3[0], (float)items3[1], value4, value5, booleanValue, horsestatstype_27);
   }

   public int[] method1(ModelRenderConfig fov81) {
      return this.method2(fov81.method12());
   }

   public int[] method2(boolean flag) {
      return flag ? field2 : field1;
   }
}

package com.moonsworth.lunar.icon;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Icon {
   private static final float[] field1 = new float[4];
   private static final float[] field2 = new float[4];

   public Icon() {
   }

   private static void method1(FloatBuffer floatbuffer0, float[] items1, float[] items2) {
      for (int index3 = 0; index3 < 4; index3++) {
         items2[index3] = items1[0] * floatbuffer0.get(floatbuffer0.position() + 0 + index3)
            + items1[1] * floatbuffer0.get(floatbuffer0.position() + 4 + index3)
            + items1[2] * floatbuffer0.get(floatbuffer0.position() + 8 + index3)
            + items1[3] * floatbuffer0.get(floatbuffer0.position() + 12 + index3);
      }
   }

   public static boolean method2(float value, float value2, float value3, FloatBuffer floatbuffer3, FloatBuffer floatbuffer4, IntBuffer intbuffer5, FloatBuffer floatbuffer6) {
      float[] items7 = field1;
      float[] items8 = field2;
      items7[0] = value;
      items7[1] = value2;
      items7[2] = value3;
      items7[3] = 1.0F;
      method1(floatbuffer3, items7, items8);
      method1(floatbuffer4, items8, items7);
      if (items7[3] == 0.0) {
         return false;
      }

      items7[3] = 1.0F / items7[3] * 0.5F;
      items7[0] = items7[0] * items7[3] + 0.5F;
      items7[1] = items7[1] * items7[3] + 0.5F;
      items7[2] = items7[2] * items7[3] + 0.5F;
      floatbuffer6.put(0, items7[0] * intbuffer5.get(intbuffer5.position() + 2) + intbuffer5.get(intbuffer5.position() + 0));
      floatbuffer6.put(1, items7[1] * intbuffer5.get(intbuffer5.position() + 3) + intbuffer5.get(intbuffer5.position() + 1));
      floatbuffer6.put(2, items7[2]);
      return true;
   }
}

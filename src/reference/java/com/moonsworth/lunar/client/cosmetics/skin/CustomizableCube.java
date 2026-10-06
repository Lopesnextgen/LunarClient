package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.Bridge3_10;
import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import java.util.HashMap;
import org.joml.Vector3f;

public class CustomizableCube {
   private final CubeDirection[] field1;
   private final CustomizableCube.Polygon[] field2;

   public CustomizableCube(
      int value,
      int value2,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      float value10,
      float value11,
      boolean flag,
      float value12,
      float value13,
      CubeDirection[] items15,
      CubeDirection[][] items16
   ) {
      this.field1 = items15;
      float value17 = value3 + value6;
      float value18 = value4 + value7;
      float value19 = value5 + value8;
      value3 -= value9;
      value4 -= value10;
      value5 -= value11;
      value17 += value9;
      value18 += value10;
      value19 += value11;
      if (flag) {
         float value20 = value17;
         value17 = value3;
         value3 = value20;
      }

      CustomizableCube.Face data49 = new CustomizableCube.Face(value3, value4, value5, 0.0F, 0.0F);
      CustomizableCube.Face data21 = new CustomizableCube.Face(value17, value4, value5, 0.0F, 8.0F);
      CustomizableCube.Face data22 = new CustomizableCube.Face(value17, value18, value5, 8.0F, 8.0F);
      CustomizableCube.Face data23 = new CustomizableCube.Face(value3, value18, value5, 8.0F, 0.0F);
      CustomizableCube.Face data24 = new CustomizableCube.Face(value3, value4, value19, 0.0F, 0.0F);
      CustomizableCube.Face data25 = new CustomizableCube.Face(value17, value4, value19, 0.0F, 8.0F);
      CustomizableCube.Face data26 = new CustomizableCube.Face(value17, value18, value19, 8.0F, 8.0F);
      CustomizableCube.Face data27 = new CustomizableCube.Face(value3, value18, value19, 8.0F, 0.0F);
      float value28 = value + 1.0F;
      float value29 = value2 + 1.0F;
      HashMap map30 = new HashMap();

      for (CubeDirection[] items34 : items16) {
         label58:
         for (CubeDirection.CubeAxis type38 : CubeDirection.CubeAxis.VALUES) {
            for (CubeDirection pkgtype42 : items34) {
               if (pkgtype42.getAxis() == type38) {
                  continue label58;
               }
            }

            map30.put(type38, items34);
            break;
         }
      }

      this.field2 = new CustomizableCube.Polygon[this.method1()];
      int index50 = 0;
      if (this.method2(CubeDirection.DOWN)) {
         this.field2[index50++] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data25, data24, data49, data21}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.Y)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.DOWN
         );
      }

      if (this.method2(CubeDirection.UP)) {
         this.field2[index50++] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data22, data23, data27, data26}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.Y)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.UP
         );
      }

      if (this.method2(CubeDirection.NORTH)) {
         this.field2[index50++] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data21, data49, data23, data22}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.Z)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.NORTH
         );
      }

      if (this.method2(CubeDirection.SOUTH)) {
         this.field2[index50++] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data24, data25, data26, data27}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.Z)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.SOUTH
         );
      }

      if (this.method2(CubeDirection.WEST)) {
         this.field2[index50++] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data49, data24, data27, data23}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.X)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.WEST
         );
      }

      if (this.method2(CubeDirection.EAST)) {
         this.field2[index50] = new CustomizableCube.Polygon(
            method3(new CustomizableCube.Face[]{data25, data21, data22, data26}, (CubeDirection[])map30.get(CubeDirection.CubeAxis.X)),
            value,
            value2,
            value28,
            value29,
            value12,
            value13,
            flag,
            CubeDirection.EAST
         );
      }
   }

   private int method1() {
      int index1 = 0;

      for (CubeDirection pkgtype5 : CubeDirection.VALUES) {
         if (this.method2(pkgtype5)) {
            index1++;
         }
      }

      return index1;
   }

   private boolean method2(CubeDirection cubeDirection) {
      for (CubeDirection pkgtype5 : this.field1) {
         if (pkgtype5 == cubeDirection) {
            return false;
         }
      }

      return true;
   }

   private static CustomizableCube.Face[] method3(CustomizableCube.Face[] items0, CubeDirection[] items1) {
      if (items1 == null) {
         return items0;
      }

      CustomizableCube.Face data2 = items0[0];

      for (int index3 = 1; index3 < 4; index3++) {
         data2 = method4(data2, items0[index3], items1);
      }

      int index5 = 0;

      for (int index4 = 0; index4 < 4; index4++) {
         if (items0[index4] != data2) {
            items0[index5++] = items0[index4];
         }
      }

      items0[3] = items0[2];
      return items0;
   }

   private static CustomizableCube.Face method4(CustomizableCube.Face face, CustomizableCube.Face face2, CubeDirection[] items2) {
      for (CubeDirection pkgtype6 : items2) {
         double value7 = pkgtype6.getAxis().choose(face.field1.x() - face2.field1.x(), face.field1.y() - face2.field1.y(), face.field1.z() - face2.field1.z())
            * pkgtype6.getAxisDirection();
         if (value7 > 0.0) {
            return face;
         }

         if (value7 < 0.0) {
            return face2;
         }
      }

      return face;
   }

   public void method5(DrawBufferBridge bridge2_321, float value2) {
      for (CustomizableCube.Polygon data28 : this.field2) {
         Vector3f vector3f3 = data28.field2;

         for (int index9 = 0; index9 < 4; index9++) {
            CustomizableCube.Face data4 = data28.field1[index9];
            float value10 = data4.field1.x() * value2;
            float value11 = data4.field1.y() * value2;
            float value12 = data4.field1.z() * value2;
            bridge2_321.method2(value10, value11, value12);
            bridge2_321.method10(data4.field2, data4.field3);
            bridge2_321.method14(vector3f3.x, vector3f3.y, vector3f3.z);
            bridge2_321.method16();
         }
      }
   }

   public void method6(Bridge3_10 bridge3_101, VertexConsumerBridge bridge4_62, int value, int value2, float value5, float value6, float value7, float value8, float value9) {
      MixinHelper_21 mixinhelper_2110 = bridge3_101.bridge$pose();
      Matrix3fBridge mixinhelper2_611 = bridge3_101.bridge$normal();

      for (CustomizableCube.Polygon data217 : this.field2) {
         Vector3f vector3f12 = data217.field2;
         float value18 = mixinhelper2_611.bridge$getTransformX(vector3f12.x, vector3f12.y, vector3f12.z);
         float value19 = mixinhelper2_611.bridge$getTransformY(vector3f12.x, vector3f12.y, vector3f12.z);
         float value20 = mixinhelper2_611.bridge$getTransformZ(vector3f12.x, vector3f12.y, vector3f12.z);

         for (int index21 = 0; index21 < 4; index21++) {
            CustomizableCube.Face data13 = data217.field1[index21];
            float value22 = data13.field1.x() * value9;
            float value23 = data13.field1.y() * value9;
            float value24 = data13.field1.z() * value9;
            float value25 = mixinhelper_2110.bridge$getTransformX(value22, value23, value24, 1.0F);
            float value26 = mixinhelper_2110.bridge$getTransformY(value22, value23, value24, 1.0F);
            float value27 = mixinhelper_2110.bridge$getTransformZ(value22, value23, value24, 1.0F);
            bridge4_62.bridge$vertex(value25, value26, value27, value5, value6, value7, value8, data13.field2, data13.field3, value2, value, value18, value19, value20);
         }
      }
   }

   private static class Face {
      public final Vector3f field1;
      public final float field2;
      public final float field3;

      public Face(float value1, float value2, float value3, float value4, float value5) {
         this(new Vector3f(value1, value2, value3), value4, value5);
      }

      public CustomizableCube.Face method1(float value1, float value2) {
         return new CustomizableCube.Face(this.field1, value1, value2);
      }

      public Face(Vector3f vector3f1, float value2, float value3) {
         this.field1 = vector3f1;
         this.field2 = value2;
         this.field3 = value3;
      }
   }

   private static class Polygon {
      public final CustomizableCube.Face[] field1;
      public final Vector3f field2;

      public Polygon(CustomizableCube.Face[] items1, float value2, float value3, float value4, float value5, float value6, float value7, boolean flag, CubeDirection cubeDirection) {
         this.field1 = items1;
         float value10 = 0.0F / value6;
         float value11 = 0.0F / value7;
         items1[0] = items1[0].method1(value4 / value6 - value10, value3 / value7 + value11);
         items1[1] = items1[1].method1(value2 / value6 + value10, value3 / value7 + value11);
         items1[2] = items1[2].method1(value2 / value6 + value10, value5 / value7 - value11);
         items1[3] = items1[3].method1(value4 / value6 - value10, value5 / value7 - value11);
         if (flag) {
            int index12 = items1.length;

            for (int index13 = 0; index13 < index12 / 2; index13++) {
               CustomizableCube.Face data14 = items1[index13];
               items1[index13] = items1[index12 - 1 - index13];
               items1[index12 - 1 - index13] = data14;
            }
         }

         this.field2 = new Vector3f(cubeDirection.getStepX(), cubeDirection.getStepY(), cubeDirection.getStepZ());
         if (flag) {
            this.field2.mul(-1.0F, 1.0F, 1.0F);
         }
      }
   }
}

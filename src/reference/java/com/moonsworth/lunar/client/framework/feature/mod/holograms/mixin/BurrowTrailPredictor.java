package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3dc;
import org.joml.Vector3i;

public class BurrowTrailPredictor {
   private final BurrowLocatingListener field1;
   private final BurrowGroundScanner field2;
   private final Vector3dc field3;
   private final List<Vector3dc> points = new ArrayList<>();
   private final long field4;
   public Vector3i field5;
   public long field6;
   private Vector3dc field7;

   public BurrowTrailPredictor(BurrowGroundScanner burrowGroundScanner, BurrowLocatingListener listener, Vector3dc vector3dc3, Vector3dc vector3dc4) {
      this.field2 = burrowGroundScanner;
      this.field1 = listener;
      this.field7 = vector3dc3;
      this.field3 = vector3dc4;
      this.field4 = Ref.method3().bridge$getSystemTime();
   }

   public boolean method1(Vector3dc vector3dc1) {
      return vector3dc1.distanceSquared(this.field7) < 9.0;
   }

   public void method2(Vector3dc vector3dc1) {
      if (this.points.size() <= 7) {
         if (this.points.isEmpty() || !vector3dc1.equals(this.points.get(this.points.size() - 1))) {
            if (this.method1(vector3dc1)) {
               this.points.add(vector3dc1);
               if (this.points.size() > 3) {
                  this.method4();
               }
            }
         }
      }
   }

   public void method3(Vector3dc vector3dc1) {
      this.field6 = Ref.method3().bridge$getSystemTime();
      this.field7 = vector3dc1;
   }

   private void method4() {
      this.field5 = null;
      Vector3i vector3i1 = this.method6();
      if (!this.field1.method9().contains(vector3i1) && this.field2.method5(vector3i1)) {
         this.field5 = vector3i1;
      }
   }

   private static double bezier(double value0, double value2, double value4, double value6, double value8) {
      double value10 = method5(value0, value2, value4, value6);
      double value12 = method5(value0, value4, value6, value8);
      return lerp(value0, value10, value12);
   }

   private static double method5(double value0, double value2, double value4, double value6) {
      return (value2 * (1.0 - value0) + value4 * value0) * (1.0 - value0) + (value4 * (1.0 - value0) + value6 * value0) * value0;
   }

   private static double lerp(double value0, double value2, double value4) {
      return value2 * (1.0 - value0) + value4 * value0;
   }

   private Vector3i method6() {
      double value1 = this.points.get(0).y();
      double value3 = value1 + this.field3.y() * 4.0 + 3.0;
      double value5 = Double.MAX_VALUE;
      int number7 = 80;
      double value8 = 0.0;

      for (int index10 = 70; index10 <= 100; index10++) {
         double value11 = index10 + 4;
         double value13 = index10 + 1;
         double value15 = 0.0;
         double value17 = 0.2;
         double value19 = Double.MAX_VALUE;
         double value21 = 0.0;

         label44:
         for (int index23 = 0; index23 < 10; index23++) {
            double value24 = (value17 - value15) / 1000.0;

            for (int index26 = 0; index26 <= 1000; index26++) {
               double value27 = value15 + value24 * index26;
               double value29 = 0.0;

               for (int index31 = 1; index31 < this.points.size(); index31++) {
                  double value32 = bezier(value27 * index31, value1, value3, value11, value13);
                  double value34 = Math.pow(this.points.get(index31).y() - value32, 2.0);
                  value29 += value34;
               }

               if (value29 < value19) {
                  value19 = value29;
                  value21 = value27;
                  if (value19 == 0.0) {
                     break label44;
                  }
               }
            }

            value15 = Math.max(0.0, value21 - value24);
            value17 = Math.min(0.2, value21 + value24);
         }

         if (value19 < value5) {
            value5 = value19;
            number7 = index10;
            value8 = value21;
         }
      }

      return new Vector3i(
         this.method7(
            value8, -283, 200, this.points.get(0).x(), this.points.get(0).x() + this.field3.x() * 4.0, this.points.stream().<Double>map(Vector3dc::x).toList()
         ),
         number7,
         this.method7(
            value8, -230, 200, this.points.get(0).z(), this.points.get(0).z() + this.field3.z() * 4.0, this.points.stream().<Double>map(Vector3dc::z).toList()
         )
      );
   }

   private int method7(double value1, int value, int value2, double value5, double value3, List<Double> list) {
      double value10 = Double.MAX_VALUE;
      int number12 = 0;

      for (int index13 = value; index13 < value2; index13++) {
         double value14 = index13 + 0.5;
         double value16 = 0.0;

         for (int index18 = 1; index18 < list.size(); index18++) {
            double value19 = bezier(value1 * index18, value5, value3, value14, value14);
            double value21 = Math.pow((Double)list.get(index18) - value19, 2.0);
            value16 += value21;
         }

         if (value16 < value10) {
            value10 = value16;
            number12 = index13;
         }
      }

      return number12;
   }

   public boolean method8() {
      return Ref.method3().bridge$getSystemTime() - this.field4 < 2000L;
   }

   public boolean method9() {
      return Ref.method3().bridge$getSystemTime() - this.field4 < 10L;
   }

   @Generated
   public void method10(Vector3i vector3i1) {
      this.field5 = vector3i1;
   }

   @Generated
   public void method11(long value) {
      this.field6 = value;
   }
}

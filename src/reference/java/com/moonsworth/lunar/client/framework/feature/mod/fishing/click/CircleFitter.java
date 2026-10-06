package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.util.collection.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import toxi.geom.Circle;

public class CircleFitter {
   private static final float field1 = 0.01F;

   public CircleFitter() {
   }

   public static List<CircleFitter.Data> findCircles(List<ParticleSample> list0, float value1, float value2, int number3) {
      return fitCircles(list0, 20, 50, value1, value2, 1.0, number3);
   }

   public static List<CircleFitter.Data> findCircles(List<ParticleSample> list0, float value1, float value2, double value3, int number5) {
      return fitCircles(list0, 20, 50, value1, value2, value3, number5);
   }

   public static List<CircleFitter.Data> fitCircles(List<ParticleSample> list0, int number1, int number2, float value3, float value4, double value5, int number7) {
      ArrayList list8 = new ArrayList(list0);
      ArrayList list9 = new ArrayList();
      int index10 = 0;

      while (index10 < number2 && list8.size() >= number7) {
         CircleFitter.CircleCandidate data211 = null;

         for (int index12 = 0; index12 < number1; index12++) {
            CircleFitter.CircleCandidate data213 = sampleCandidate(list8, value3, value4, value5, number7);
            if (data213 != null && (data211 == null || data211.getPosition() < data213.getPosition())) {
               data211 = data213;
            }
         }

         if (data211 == null) {
            index10++;
         } else {
            index10 = 0;
            double value16 = 0.0;

            for (int index15 : data211.method3()) {
               value16 += ((ParticleSample)list8.get(index15)).getY();
            }

            value16 /= data211.method3().size();

            for (int index20 : data211.method3()) {
               ListUtils.getPosition(list8, index20);
            }

            Circle circle19 = data211.getY();
            list9.add(new CircleFitter.Data(circle19.x, circle19.y, value16, circle19.getRadius(), data211.method3().size()));
         }
      }

      return list9;
   }

   @Nullable
   private static CircleFitter.CircleCandidate sampleCandidate(List<ParticleSample> list0, float value1, float value2, double value3, int number5) {
      ParticleSample click96 = (ParticleSample)list0.get((int)(list0.size() * Math.random()));
      ParticleSample click97 = (ParticleSample)list0.get((int)(list0.size() * Math.random()));
      ParticleSample click98 = (ParticleSample)list0.get((int)(list0.size() * Math.random()));
      if (click96 != click97 && click96 != click98 && click97 != click98) {
         Circle circle9 = Circle.from3Points(click96.getPosition(), click97.getPosition(), click98.getPosition());
         if (circle9 == null) {
            return null;
         }

         float value10 = circle9.getRadius();
         if (!Float.isNaN(value10) && !(value10 < value1 - 0.01F) && !(value10 > value2 + 0.01F)) {
            ArrayList list11 = new ArrayList();
            double value12 = 0.0;

            for (int index14 = 0; index14 < list0.size(); index14++) {
               double value15 = Math.abs(circle9.distanceTo(((ParticleSample)list0.get(index14)).getPosition()) - value10);
               if (value15 < value3) {
                  list11.add(index14);
                  value12 += value3 - value15;
               }
            }

            if (list11.size() >= number5) {
               Collections.reverse(list11);
               return new CircleFitter.CircleCandidate(value12, circle9, list11);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public class Data {
      private final double field1;
      private final double field2;
      private final double weight;
      private final double radius;
      private final int pointCount;

      public Data(double value1, double value3, double value5, double value7, int number9) {
         this.RADIUS_EPSILON = value1;
         this.field2 = value3;
         this.weight = value5;
         this.radius = value7;
         this.pointCount = number9;
      }

      public double findCircles() {
         return this.RADIUS_EPSILON;
      }

      public double findCircles() {
         return this.field2;
      }

      public double fitCircles() {
         return this.weight;
      }

      public double sampleCandidate() {
         return this.radius;
      }

      public int getPointCount() {
         return this.pointCount;
      }
   }

   private class CircleCandidate {
      private final double field1;
      private final Circle circle;
      private final ArrayList<Integer> field2;

      private CircleCandidate(double value1, Circle circle3, ArrayList<Integer> list4) {
         this.RADIUS_EPSILON = value1;
         this.circle = circle3;
         this.field2 = list4;
      }

      public double findCircles() {
         return this.RADIUS_EPSILON;
      }

      public Circle findCircles() {
         return this.circle;
      }

      public ArrayList<Integer> fitCircles() {
         return this.field2;
      }
   }
}

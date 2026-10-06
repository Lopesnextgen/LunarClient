package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.util.ThreadModuleDump53;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import toxi.geom.Circle;

public class Click5 {
   private static final float field1 = 0.01F;

   public static List<Click5.Data> getPosition(List<Click9> var0, float var1, float var2, int var3) {
      return fitCircles(var0, 20, 50, var1, var2, 1.0, var3);
   }

   public static List<Click5.Data> getWeight(List<Click9> var0, float var1, float var2, double var3, int var5) {
      return fitCircles(var0, 20, 50, var1, var2, var3, var5);
   }

   public static List<Click5.Data> fitCircles(List<Click9> var0, int var1, int var2, float var3, float var4, double var5, int var7) {
      ArrayList var8 = new ArrayList(var0);
      ArrayList var9 = new ArrayList();
      int var10 = 0;

      while (var10 < var2 && var8.size() >= var7) {
         Click5.Data2 var11 = null;

         for (int var12 = 0; var12 < var1; var12++) {
            Click5.Data2 var13 = sampleCandidate(var8, var3, var4, var5, var7);
            if (var13 != null && (var11 == null || var11.getPosition() < var13.getPosition())) {
               var11 = var13;
            }
         }

         if (var11 == null) {
            var10++;
         } else {
            var10 = 0;
            double var16 = 0.0;

            for (int var15 : var11.method3()) {
               var16 += ((Click9)var8.get(var15)).getWeight();
            }

            var16 /= var11.method3().size();

            for (int var20 : var11.method3()) {
               ThreadModuleDump53.getPosition(var8, var20);
            }

            Circle var19 = var11.getWeight();
            var9.add(new Click5.Data(var19.x, var19.y, var16, var19.getRadius(), var11.method3().size()));
         }
      }

      return var9;
   }

   @Nullable
   private static Click5.Data2 sampleCandidate(List<Click9> var0, float var1, float var2, double var3, int var5) {
      Click9 var6 = (Click9)var0.get((int)(var0.size() * Math.random()));
      Click9 var7 = (Click9)var0.get((int)(var0.size() * Math.random()));
      Click9 var8 = (Click9)var0.get((int)(var0.size() * Math.random()));
      if (var6 != var7 && var6 != var8 && var7 != var8) {
         Circle var9 = Circle.from3Points(var6.getPosition(), var7.getPosition(), var8.getPosition());
         if (var9 == null) {
            return null;
         }

         float var10 = var9.getRadius();
         if (!Float.isNaN(var10) && !(var10 < var1 - 0.01F) && !(var10 > var2 + 0.01F)) {
            ArrayList var11 = new ArrayList();
            double var12 = 0.0;

            for (int var14 = 0; var14 < var0.size(); var14++) {
               double var15 = Math.abs(var9.distanceTo(((Click9)var0.get(var14)).getPosition()) - var10);
               if (var15 < var3) {
                  var11.add(var14);
                  var12 += var3 - var15;
               }
            }

            if (var11.size() >= var5) {
               Collections.reverse(var11);
               return new Click5.Data2(var12, var9, var11);
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

      public Data(double var1, double var3, double var5, double var7, int var9) {
         this.RADIUS_EPSILON = var1;
         this.field2 = var3;
         this.weight = var5;
         this.radius = var7;
         this.pointCount = var9;
      }

      public double getPosition() {
         return this.RADIUS_EPSILON;
      }

      public double getWeight() {
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

   private class Data2 {
      private final double field1;
      private final Circle circle;
      private final ArrayList<Integer> field2;

      private Data2(double var1, Circle var3, ArrayList<Integer> var4) {
         this.RADIUS_EPSILON = var1;
         this.circle = var3;
         this.field2 = var4;
      }

      public double getPosition() {
         return this.RADIUS_EPSILON;
      }

      public Circle getWeight() {
         return this.circle;
      }

      public ArrayList<Integer> fitCircles() {
         return this.field2;
      }
   }
}

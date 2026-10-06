package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.util.math.LineSegment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class LineFitter {
   public LineFitter() {
   }

   @Nullable
   public static LineSegment fitLine(Set<Vector3d> set0, int number1) {
      return fitLine(set0, 100, 0.1, number1);
   }

   @Nullable
   public static LineSegment fitLine(Set<Vector3d> set0, int number1, double value2, int number4) {
      if (set0.size() < number4) {
         return null;
      }

      ArrayList list5 = new ArrayList(set0);
      Random random6 = new Random();
      List list7 = Collections.emptyList();

      for (int index8 = 0; index8 < number1; index8++) {
         Vector3d vector3d9 = (Vector3d)list5.get(random6.nextInt(list5.size()));
         Vector3d vector3d10 = (Vector3d)list5.get(random6.nextInt(list5.size()));
         if (!vector3d9.equals(vector3d10)) {
            Vector3d vector3d11 = new Vector3d(vector3d10).sub(vector3d9).normalize();
            if (vector3d11.lengthSquared() != 0.0) {
               ArrayList list12 = new ArrayList();

               for (Vector3d vector3d14 : list5) {
                  double value15 = distanceToLine(vector3d14, vector3d9, vector3d11);
                  if (value15 < value2) {
                     list12.add(vector3d14);
                  }
               }

               if (list12.size() >= number4 && list12.size() > list7.size()) {
                  list7 = list12;
                  if (list7.size() > 0.9 * list5.size()) {
                     break;
                  }
               }
            }
         }
      }

      return list7.size() < number4 ? null : createLineSegment(list7);
   }

   private static double distanceToLine(Vector3d vector3d0, Vector3d vector3d1, Vector3d vector3d2) {
      Vector3d vector3d3 = new Vector3d(vector3d0).sub(vector3d1);
      Vector3d vector3d4 = vector3d3.cross(vector3d2, new Vector3d());
      return vector3d4.length();
   }

   private static LineSegment createLineSegment(List<Vector3d> list0) {
      Vector3d vector3d1 = new Vector3d();

      for (Vector3d vector3d3 : list0) {
         vector3d1.add(vector3d3);
      }

      vector3d1.div(list0.size());
      Vector3d vector3d11 = new Vector3d((Vector3dc)list0.get(0)).sub(vector3d1).normalize();
      double value12 = Double.POSITIVE_INFINITY;
      double value5 = Double.NEGATIVE_INFINITY;

      for (Vector3d vector3d8 : list0) {
         double value9 = new Vector3d(vector3d8).sub(vector3d1).dot(vector3d11);
         value12 = Math.min(value12, value9);
         value5 = Math.max(value5, value9);
      }

      Vector3d vector3d13 = new Vector3d(vector3d11).mul(value12).add(vector3d1);
      Vector3d vector3d14 = new Vector3d(vector3d11).mul(value5).add(vector3d1);
      return new LineSegment(vector3d13, vector3d14);
   }
}

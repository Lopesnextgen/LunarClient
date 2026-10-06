package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.util.math.Fishing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class Click7 {
   @Nullable
   public static Fishing fitLine(Set<Vector3d> var0, int var1) {
      return fitLine(var0, 100, 0.1, var1);
   }

   @Nullable
   public static Fishing fitLine(Set<Vector3d> var0, int var1, double var2, int var4) {
      if (var0.size() < var4) {
         return null;
      }

      ArrayList var5 = new ArrayList(var0);
      Random var6 = new Random();
      List var7 = Collections.emptyList();

      for (int var8 = 0; var8 < var1; var8++) {
         Vector3d var9 = (Vector3d)var5.get(var6.nextInt(var5.size()));
         Vector3d var10 = (Vector3d)var5.get(var6.nextInt(var5.size()));
         if (!var9.equals(var10)) {
            Vector3d var11 = new Vector3d(var10).sub(var9).normalize();
            if (var11.lengthSquared() != 0.0) {
               ArrayList var12 = new ArrayList();

               for (Vector3d var14 : var5) {
                  double var15 = distanceToLine(var14, var9, var11);
                  if (var15 < var2) {
                     var12.add(var14);
                  }
               }

               if (var12.size() >= var4 && var12.size() > var7.size()) {
                  var7 = var12;
                  if (var7.size() > 0.9 * var5.size()) {
                     break;
                  }
               }
            }
         }
      }

      return var7.size() < var4 ? null : createSegment(var7);
   }

   private static double distanceToLine(Vector3d var0, Vector3d var1, Vector3d var2) {
      Vector3d var3 = new Vector3d(var0).sub(var1);
      Vector3d var4 = var3.cross(var2, new Vector3d());
      return var4.length();
   }

   private static Fishing createSegment(List<Vector3d> var0) {
      Vector3d var1 = new Vector3d();

      for (Vector3d var3 : var0) {
         var1.add(var3);
      }

      var1.div(var0.size());
      Vector3d var11 = new Vector3d((Vector3dc)var0.get(0)).sub(var1).normalize();
      double var12 = Double.POSITIVE_INFINITY;
      double var5 = Double.NEGATIVE_INFINITY;

      for (Vector3d var8 : var0) {
         double var9 = new Vector3d(var8).sub(var1).dot(var11);
         var12 = Math.min(var12, var9);
         var5 = Math.max(var5, var9);
      }

      Vector3d var13 = new Vector3d(var11).mul(var12).add(var1);
      Vector3d var14 = new Vector3d(var11).mul(var5).add(var1);
      return new Fishing(var13, var14);
   }
}

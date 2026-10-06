package com.moonsworth.lunar.client.util;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Optional;
import lombok.Generated;

public final class ThreadModuleDump15 {
   public static double getSlope(Line2D var0) {
      return (var0.getY2() - var0.getY1()) / (var0.getX2() - var0.getX1());
   }

   public static double getYIntercept(Line2D var0) {
      return var0.getY1() - getSlope(var0) * var0.getX1();
   }

   public static Optional<Point2D> getIntersection(Line2D var0, Line2D line2D) {
      if (!var0.intersectsLine(line2D)) {
         return Optional.empty();
      }

      double var2 = getSlope(var0);
      double var4 = getSlope(line2D);
      double var6 = getYIntercept(var0);
      double var8 = getYIntercept(line2D);
      double var10 = (var8 - var6) / (var2 - var4);
      double var12 = var2 * var10 + var6;
      return Optional.of(new Double(var10, var12));
   }

   @Generated
   private ThreadModuleDump15() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

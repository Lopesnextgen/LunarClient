package com.moonsworth.lunar.client.util.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Optional;
import lombok.Generated;

public final class LineMath {
   public static double method1(Line2D line2d0) {
      return (line2d0.getY2() - line2d0.getY1()) / (line2d0.getX2() - line2d0.getX1());
   }

   public static double method2(Line2D line2d0) {
      return line2d0.getY1() - method1(line2d0) * line2d0.getX1();
   }

   public static Optional<Point2D> method3(Line2D line2d0, Line2D line2d1) {
      if (!line2d0.intersectsLine(line2d1)) {
         return Optional.empty();
      }

      double value2 = method1(line2d0);
      double value4 = method1(line2d1);
      double value6 = method2(line2d0);
      double value8 = method2(line2d1);
      double value10 = (value8 - value6) / (value2 - value4);
      double value12 = value2 * value10 + value6;
      return Optional.of(new Double(value10, value12));
   }

   @Generated
   private LineMath() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

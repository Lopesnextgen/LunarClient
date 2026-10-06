package com.moonsworth.lunar.client.util.math;

import org.joml.Vector3dc;

public class LineSegment {
   private final Vector3dc field1;
   private final Vector3dc field2;

   public LineSegment(Vector3dc vector3dc1, Vector3dc vector3dc2) {
      this.field1 = vector3dc1;
      this.field2 = vector3dc2;
   }

   public Vector3dc method1() {
      return this.field1;
   }

   public Vector3dc method2() {
      return this.field2;
   }
}

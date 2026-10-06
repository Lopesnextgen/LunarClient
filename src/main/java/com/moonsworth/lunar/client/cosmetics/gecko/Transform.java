package com.moonsworth.lunar.client.cosmetics.gecko;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
   public final Vector3f field1 = new Vector3f(1.0F);
   public final Quaternionf field2 = new Quaternionf();
   public final Vector3f field3 = new Vector3f();
   private final Vector3f field4 = new Vector3f();

   public Transform() {
   }

   public void method1(Transform fov3$data31) {
      this.field1.set(fov3$data31.field1);
      this.field2.set(fov3$data31.field2);
      this.field3.set(fov3$data31.field3);
   }

   public void method2(float value, float value2, float value3) {
      this.field4.set(value, value2, value3);
      this.field4.mul(this.field1);
      this.field4.rotate(this.field2);
      this.field3.add(this.field4);
   }
}

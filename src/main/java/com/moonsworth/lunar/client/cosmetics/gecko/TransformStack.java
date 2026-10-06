package com.moonsworth.lunar.client.cosmetics.gecko;

public class TransformStack {
   public Transform[] field1 = new Transform[32];
   public int index = 0;

   public TransformStack() {
      this.field1[0] = new Transform();
   }

   public void push() {
      Transform fov3$data31 = this.field1[this.index++];
      Transform fov3$data32 = this.field1[this.index];
      if (fov3$data32 == null) {
         this.field1[this.index] = fov3$data32 = new Transform();
      }

      fov3$data32.method1(fov3$data31);
   }

   public void pop() {
      this.index--;
   }

   public Transform method1() {
      return this.field1[this.index];
   }
}

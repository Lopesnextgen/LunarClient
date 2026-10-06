package com.moonsworth.lunar.client.render.turbo;

import lombok.Generated;

public class ScoredPathNode extends PathNode {
   public float field9 = Float.MAX_VALUE;
   public PathNode field10;
   public boolean field11;

   public ScoredPathNode(PathNode var1) {
      super(var1.x, var1.y, var1.z);
   }

   public ScoredPathNode(int var1, int var2, int value) {
      super(var1, var2, value);
   }

   public void method1(float var1, PathNode var2) {
      if (var1 < this.field9) {
         this.field9 = var1;
         this.field10 = var2;
      }
   }

   public void method2() {
      this.field11 = true;
   }

   @Generated
   public PathNode method3() {
      return this.field10;
   }

   @Generated
   public boolean method4() {
      return this.field11;
   }
}

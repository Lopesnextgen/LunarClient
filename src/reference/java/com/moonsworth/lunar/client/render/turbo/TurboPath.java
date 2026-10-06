package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import java.util.List;
import javax.annotation.Nullable;
import lombok.Generated;

public class TurboPath {
   private final List<com.moonsworth.lunar.client.render.turbo.PathNode> field1;
   private final Vector3iBridge field2;
   private final float field3;
   private final boolean field4;
   private int field5;

   public TurboPath(List<com.moonsworth.lunar.client.render.turbo.PathNode> var1, Vector3iBridge var2, boolean var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var1.isEmpty() ? Float.MAX_VALUE : this.field1.get(this.field1.size() - 1).method7(this.field2);
      this.field4 = var3;
   }

   public boolean isDone() {
      return this.field5 >= this.field1.size();
   }

   public boolean method1(@Nullable TurboPath var1) {
      if (var1 == null) {
         return false;
      }

      if (var1.field1.size() != this.field1.size()) {
         return false;
      }

      for (int var2 = 0; var2 < this.field1.size(); var2++) {
         com.moonsworth.lunar.client.render.turbo.PathNode var3 = this.field1.get(var2);
         com.moonsworth.lunar.client.render.turbo.PathNode var4 = var1.field1.get(var2);
         if (var3.x != var4.x || var3.y != var4.y || var3.z != var4.z) {
            return false;
         }
      }

      return true;
   }

   public boolean method2() {
      return this.field4;
   }

   public void advance() {
      this.field5++;
   }

   public boolean method3() {
      return this.field5 <= 0;
   }

   @Nullable
   public com.moonsworth.lunar.client.render.turbo.PathNode method4() {
      return this.field1.isEmpty() ? null : this.field1.get(this.field1.size() - 1);
   }

   public com.moonsworth.lunar.client.render.turbo.PathNode method5(int var1) {
      return this.field1.get(var1);
   }

   public void method6(int var1) {
      if (this.field1.size() > var1) {
         this.field1.subList(var1, this.field1.size()).clear();
      }
   }

   public void method7(int var1, com.moonsworth.lunar.client.render.turbo.PathNode var2) {
      this.field1.set(var1, var2);
   }

   public int method8() {
      return this.field1.size();
   }

   public Vec3Bridge method9(com.moonsworth.lunar.client.render.turbo.PathEntity var1, int var2) {
      AxisAlignedBBBridge var3 = var1.method7();
      com.moonsworth.lunar.client.render.turbo.PathNode var4 = this.field1.get(var2);
      double var5 = var4.x + (int)(var3.bridge$getMaxX() - var3.bridge$getMinX() + 1.0) * 0.5;
      double var7 = var4.z + (int)(var3.bridge$getMaxZ() - var3.bridge$getMinZ() + 1.0) * 0.5;
      return Vec3Bridge.method2(var5, var4.y, var7);
   }

   public Horsestats20Extension2 method10(int var1) {
      return this.field1.get(var1).method12();
   }

   public Vec3Bridge method11(com.moonsworth.lunar.client.render.turbo.PathEntity var1) {
      return this.method9(var1, this.field5);
   }

   public Horsestats20Extension2 method12() {
      return this.field1.get(this.field5).method12();
   }

   public com.moonsworth.lunar.client.render.turbo.PathNode method13() {
      return this.field1.get(this.field5);
   }

   @Nullable
   public com.moonsworth.lunar.client.render.turbo.PathNode method14() {
      return this.field5 > 0 ? this.field1.get(this.field5 - 1) : null;
   }

   public TurboPath method15() {
      TurboPath var1 = new TurboPath(this.field1, this.field2, this.field4);
      var1.field5 = this.field5;
      return var1;
   }

   @Override
   public String toString() {
      return "Path(length=" + this.field1.size() + ")";
   }

   @Generated
   public Vector3iBridge method16() {
      return this.field2;
   }

   @Generated
   public float method17() {
      return this.field3;
   }

   @Generated
   public void method18(int var1) {
      this.field5 = var1;
   }

   @Generated
   public int method19() {
      return this.field5;
   }
}

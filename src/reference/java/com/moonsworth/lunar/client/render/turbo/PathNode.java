package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2_3;

public class PathNode {
   public int x;
   public int y;
   public int z;
   public int hash;
   public int field1 = -1;
   public float field2;
   public float field3;
   public float field4;
   public PathNode field5;
   public boolean closed;
   public float field6;
   public float field7;
   public ItemcounterType2_3 field8 = ItemcounterType2_3.BLOCKED;

   public PathNode(int var1, int var2, int var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.hash = method1(var1, var2, var3);
   }

   public static int method1(int value, int var1, int var2) {
      return var1 & 0xFF | (value & 32767) << 8 | (var2 & 32767) << 24 | (value < 0 ? Integer.MIN_VALUE : 0) | (var2 < 0 ? 32768 : 0);
   }

   public float method2(PathNode var1) {
      float var2 = var1.x - this.x;
      float var3 = var1.y - this.y;
      float var4 = var1.z - this.z;
      return (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public float method3(PathNode var1) {
      float var2 = var1.x - this.x;
      float var3 = var1.y - this.y;
      float var4 = var1.z - this.z;
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public float method4(PathNode var1) {
      float var2 = Math.abs(var1.x - this.x);
      float var3 = Math.abs(var1.y - this.y);
      float var4 = Math.abs(var1.z - this.z);
      return var2 + var3 + var4;
   }

   public float method5(Vector3iBridge var1) {
      float var2 = Math.abs(var1.bridge$getX() - this.x);
      float var3 = Math.abs(var1.bridge$getY() - this.y);
      float var4 = Math.abs(var1.bridge$getZ() - this.z);
      return (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public float method6(Vector3iBridge var1) {
      float var2 = Math.abs(var1.bridge$getX() - this.x);
      float var3 = Math.abs(var1.bridge$getY() - this.y);
      float var4 = Math.abs(var1.bridge$getZ() - this.z);
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public float method7(Vector3iBridge var1) {
      float var2 = Math.abs(var1.bridge$getX() - this.x);
      float var3 = Math.abs(var1.bridge$getY() - this.y);
      float var4 = Math.abs(var1.bridge$getZ() - this.z);
      return var2 + var3 + var4;
   }

   public float method8(PathNode var1) {
      float var2 = var1.x - this.x;
      float var3 = var1.z - this.z;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   public float method9(PathNode var1) {
      float var2 = var1.x - this.x;
      float var3 = var1.z - this.z;
      return var2 * var2 + var3 * var3;
   }

   public float method10(Vector3iBridge var1) {
      float var2 = var1.bridge$getX() - this.x;
      float var3 = var1.bridge$getZ() - this.z;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   public float method11(Vector3iBridge var1) {
      float var2 = var1.bridge$getX() - this.x;
      float var3 = var1.bridge$getZ() - this.z;
      return var2 * var2 + var3 * var3;
   }

   public Horsestats20Extension2 method12() {
      return Bridge.method8().method4(this.x, this.y, this.z);
   }

   public Vec3Bridge method13() {
      return Vec3Bridge.method2(this.x, this.y, this.z);
   }

   public boolean method14() {
      return this.field1 >= 0;
   }

   public PathNode method15(int var1, int var2, int var3) {
      PathNode var4 = new PathNode(var1, var2, var3);
      var4.field1 = this.field1;
      var4.field2 = this.field2;
      var4.field3 = this.field3;
      var4.field4 = this.field4;
      var4.field5 = this.field5;
      var4.closed = this.closed;
      var4.field6 = this.field6;
      var4.field7 = this.field7;
      var4.field8 = this.field8;
      return var4;
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof PathNode var2) ? false : this.hash == var2.hash && this.x == var2.x && this.y == var2.y && this.z == var2.z;
   }

   @Override
   public String toString() {
      return "Node{x=" + this.x + ", y=" + this.y + ", z=" + this.z + "}";
   }

   @Override
   public int hashCode() {
      return this.hash;
   }
}

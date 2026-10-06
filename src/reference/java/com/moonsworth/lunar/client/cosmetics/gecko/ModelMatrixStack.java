package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import java.util.Stack;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.ui.IllegalStateException;

public class ModelMatrixStack {
   private final Stack<Matrix4f> field1 = new Stack<>();
   private final Stack<Matrix3f> field2 = new Stack<>();

   public ModelMatrixStack() {
      Matrix4f matrix4f1 = new Matrix4f();
      Matrix3f matrix3f2 = new Matrix3f();
      matrix4f1.identity();
      matrix3f2.identity();
      this.field1.add(matrix4f1);
      this.field2.add(matrix3f2);
   }

   public Matrix4f method1() {
      return this.field1.peek();
   }

   public Matrix3f method2() {
      return this.field2.peek();
   }

   public void push() {
      this.field1.add(new Matrix4f((Matrix4fc)this.field1.peek()));
      this.field2.add(new Matrix3f((Matrix3fc)this.field2.peek()));
   }

   public void pop() {
      if (this.field1.size() == 1) {
         throw new IllegalStateException("A one level stack can't be popped!");
      }

      this.field1.pop();
      this.field2.pop();
   }

   public void method3(float value1, float value2, float value3) {
      this.method4(new Vector3f(value1, value2, value3));
   }

   public void method4(Vector3f vector3f1) {
      this.field1.peek().translate(vector3f1);
   }

   public void method5(CubeMesh rewindhandlers_21) {
      Vector3f vector3f2 = rewindhandlers_21.field2;
      this.method3(vector3f2.x() / 16.0F, vector3f2.y() / 16.0F, vector3f2.z() / 16.0F);
   }

   public void method6(CubeMesh rewindhandlers_21) {
      Vector3f vector3f2 = rewindhandlers_21.field2;
      this.method3(-vector3f2.x() / 16.0F, -vector3f2.y() / 16.0F, -vector3f2.z() / 16.0F);
   }

   public void method7(IBoneSerializer iboneserializer1) {
      this.method3(iboneserializer1.getPivotX() / 16.0F, iboneserializer1.getPivotY() / 16.0F, iboneserializer1.getPivotZ() / 16.0F);
   }

   public void method8(IBoneSerializer iboneserializer1) {
      this.method3(-iboneserializer1.getPivotX() / 16.0F, -iboneserializer1.getPivotY() / 16.0F, -iboneserializer1.getPivotZ() / 16.0F);
   }

   public void method9(IBoneSerializer iboneserializer1) {
      this.method3(-iboneserializer1.getPositionX() / 16.0F, iboneserializer1.getPositionY() / 16.0F, iboneserializer1.getPositionZ() / 16.0F);
   }

   public void scale(float value1, float value2, float value3) {
      this.field1.peek().scale(value1, value2, value3);
      if (value1 < 0.0F || value2 < 0.0F || value3 < 0.0F) {
         this.field2.peek().scale(value1 < 0.0F ? -1.0F : 1.0F, value2 < 0.0F ? -1.0F : 1.0F, value3 < 0.0F ? -1.0F : 1.0F);
      }
   }

   public void method10(IBoneSerializer iboneserializer1) {
      this.scale(iboneserializer1.getScaleX(), iboneserializer1.getScaleY(), iboneserializer1.getScaleZ());
   }

   public void method11(IBoneSerializer iboneserializer1) {
      this.field1.peek().rotateZYX(iboneserializer1.getRotationZ(), iboneserializer1.getRotationY(), iboneserializer1.getRotationX());
      this.field2.peek().rotateZYX(iboneserializer1.getRotationZ(), iboneserializer1.getRotationY(), iboneserializer1.getRotationX());
   }

   public void method12(CubeMesh rewindhandlers_21) {
      this.field1.peek().rotateZYX(rewindhandlers_21.field3);
      this.field2.peek().rotateZYX(rewindhandlers_21.field3);
   }
}

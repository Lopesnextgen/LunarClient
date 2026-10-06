package com.moonsworth.lunar.client.cosmetics.gecko;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3f;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.snapshot.BoneSnapshot;

public class IBoneSerializer implements IBone {
   public List<IBoneSerializer> field1 = new ArrayList<>();
   public List<CubeMesh> field2 = new ArrayList<>();
   public String name;
   private BoneSnapshot field3;
   public Double field4;
   public boolean isHidden;
   private final Vector3f field5 = new Vector3f(1.0F, 1.0F, 1.0F);
   private final Vector3f field6 = new Vector3f();
   private final Vector3f field7 = new Vector3f();
   private final Vector3f field8 = new Vector3f();
   public String field9;
   public String field10;
   public String field11;
   public String field12;
   public String field13;
   public String field14;
   public String field15;
   public String field16;
   public String field17;

   public IBoneSerializer() {
   }

   public void setModelRendererName(String text) {
      this.name = text;
      this.field9 = "bone_" + this.name + "_pos_x";
      this.field10 = "bone_" + this.name + "_pos_y";
      this.field11 = "bone_" + this.name + "_pos_z";
      this.field12 = "bone_" + this.name + "_rot_x";
      this.field13 = "bone_" + this.name + "_rot_y";
      this.field14 = "bone_" + this.name + "_rot_z";
      this.field15 = "bone_" + this.name + "_scale_x";
      this.field16 = "bone_" + this.name + "_scale_y";
      this.field17 = "bone_" + this.name + "_scale_z";
   }

   public void saveInitialSnapshot() {
      if (this.field3 == null) {
         this.field3 = new BoneSnapshot(this, true);
      }
   }

   public BoneSnapshot getInitialSnapshot() {
      return this.field3;
   }

   public String getName() {
      return this.name;
   }

   public float getRotationX() {
      return this.field8.x();
   }

   public float getRotationY() {
      return this.field8.y();
   }

   public float getRotationZ() {
      return this.field8.z();
   }

   public float getPositionX() {
      return this.field6.x();
   }

   public float getPositionY() {
      return this.field6.y();
   }

   public float getPositionZ() {
      return this.field6.z();
   }

   public float getScaleX() {
      return this.field5.x();
   }

   public float getScaleY() {
      return this.field5.y();
   }

   public float getScaleZ() {
      return this.field5.z();
   }

   public void setRotationX(float value1) {
      this.field8.x = value1;
   }

   public void setRotationY(float value1) {
      this.field8.y = value1;
   }

   public void setRotationZ(float value1) {
      this.field8.z = value1;
   }

   public void setPositionX(float value1) {
      this.field6.x = value1;
   }

   public void setPositionY(float value1) {
      this.field6.y = value1;
   }

   public void setPositionZ(float value1) {
      this.field6.z = value1;
   }

   public void setScaleX(float value1) {
      this.field5.x = value1;
   }

   public void setScaleY(float value1) {
      this.field5.y = value1;
   }

   public void setScaleZ(float value1) {
      this.field5.z = value1;
   }

   public boolean isHidden() {
      return this.isHidden;
   }

   public void setHidden(boolean flag) {
      this.isHidden = flag;
   }

   public void setPivotX(float value1) {
      this.field7.x = value1;
   }

   public void setPivotY(float value1) {
      this.field7.y = value1;
   }

   public void setPivotZ(float value1) {
      this.field7.z = value1;
   }

   public float getPivotX() {
      return this.field7.x;
   }

   public float getPivotY() {
      return this.field7.y;
   }

   public float getPivotZ() {
      return this.field7.z;
   }
}

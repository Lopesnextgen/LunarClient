package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class HologramBounds {
   @SerializedName("x")
   private float x;
   @SerializedName("y")
   private float y;
   @SerializedName("width")
   private float width;
   @SerializedName("height")
   private float height;
   private transient float field1;

   public boolean method1(MarkerModel.Data5 data51) {
      return data51.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x
         && data51.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + this.width
         && data51.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y
         && data51.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= this.y + this.height;
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public float method2() {
      return this.field1;
   }

   @Generated
   public void setX(float value1) {
      this.x = value1;
   }

   @Generated
   public void setY(float value1) {
      this.y = value1;
   }

   @Generated
   public void setWidth(float value1) {
      this.width = value1;
   }

   @Generated
   public void setHeight(float value1) {
      this.height = value1;
   }

   @Generated
   public void method5(float value1) {
      this.field1 = value1;
   }

   @Generated
   public HologramBounds(float value1, float value2, float value3, float value4, float value5) {
      this.x = value1;
      this.y = value2;
      this.width = value3;
      this.height = value4;
      this.field1 = value5;
   }
}

package com.moonsworth.lunar.client.driver.core.holograms;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class HologramBoundsLegacy {
   @SerializedName("x")
   private float x;
   @SerializedName("y")
   private float y;
   @SerializedName("width")
   private float width;
   @SerializedName("height")
   private float height;
   private transient float field1;

   public boolean method1(MarkerModel.Data5 var1) {
      return var1.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x
         && var1.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + this.width
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() >= this.y
         && var1.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() <= this.y + this.height;
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
   public void setX(float var1) {
      this.x = var1;
   }

   @Generated
   public void setY(float var1) {
      this.y = var1;
   }

   @Generated
   public void setWidth(float var1) {
      this.width = var1;
   }

   @Generated
   public void setHeight(float var1) {
      this.height = var1;
   }

   @Generated
   public void method5(float var1) {
      this.field1 = var1;
   }

   @Generated
   public HologramBoundsLegacy(float var1, float var2, float var3, float var4, float var5) {
      this.x = var1;
      this.y = var2;
      this.width = var3;
      this.height = var4;
      this.field1 = var5;
   }
}

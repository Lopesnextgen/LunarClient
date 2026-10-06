package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.particle.ParticleType;
import lombok.Generated;
import org.joml.Vector3d;

public class EventSpawnParticle extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final ParticleType field1;
   private final double posX;
   private final double posY;
   private final double posZ;
   private final float field2;
   private final float field3;
   private final float field4;
   private final float colorR;
   private final float colorG;
   private final float colorB;
   private final float field5;

   public Vector3d method1() {
      return new Vector3d(this.posX, this.posY, this.posZ);
   }

   @Generated
   public ParticleType method2() {
      return this.field1;
   }

   @Generated
   public double getPosX() {
      return this.posX;
   }

   @Generated
   public double getPosY() {
      return this.posY;
   }

   @Generated
   public double getPosZ() {
      return this.posZ;
   }

   @Generated
   public float method6() {
      return this.field2;
   }

   @Generated
   public float method7() {
      return this.field3;
   }

   @Generated
   public float method8() {
      return this.field4;
   }

   @Generated
   public float getColorR() {
      return this.colorR;
   }

   @Generated
   public float getColorG() {
      return this.colorG;
   }

   @Generated
   public float getColorB() {
      return this.colorB;
   }

   @Generated
   public float method12() {
      return this.field5;
   }

   @Generated
   public EventSpawnParticle(
      ParticleType particleType, double value, double value2, double value3, float value4, float value5, float value6, float value7, float value8, float value9, float value10
   ) {
      this.field1 = particleType;
      this.posX = value;
      this.posY = value2;
      this.posZ = value3;
      this.field2 = value4;
      this.field3 = value5;
      this.field4 = value6;
      this.colorR = value7;
      this.colorG = value8;
      this.colorB = value9;
      this.field5 = value10;
   }
}

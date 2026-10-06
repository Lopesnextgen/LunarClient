package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

@Annotation2(max = 0)
public class RenderViewEntityProxy extends EntityLivingBase {
   private final Entity field1;

   public RenderViewEntityProxy(Entity var1) {
      super(var1.worldObj);
      this.field1 = var1;
   }

   private void method1() {
      this.lastTickPosX = this.field1.lastTickPosX;
      this.lastTickPosY = this.field1.lastTickPosY;
      this.lastTickPosZ = this.field1.lastTickPosZ;
      this.posX = this.field1.posX;
      this.posY = this.field1.posY;
      this.posZ = this.field1.posZ;
      this.prevPosX = this.field1.prevPosX;
      this.prevPosY = this.field1.prevPosY;
      this.prevPosZ = this.field1.prevPosZ;
      this.rotationYaw = this.field1.rotationYaw;
      this.rotationPitch = this.field1.rotationPitch;
      this.prevRotationYaw = this.field1.prevRotationYaw;
      this.prevRotationPitch = this.field1.prevRotationPitch;
      this.ridingEntity = this.field1.ridingEntity;
      this.height = this.field1.height;
      this.width = this.field1.width;
      this.world = this.field1.worldObj;
      this.onGround = this.field1.onGround;
      this.chunkCoordX = this.field1.chunkCoordX;
      this.chunkCoordY = this.field1.chunkCoordY;
      this.chunkCoordZ = this.field1.chunkCoordZ;
      this.addedToChunk = this.field1.addedToChunk;
      this.collided = this.field1.isCollided;
      this.collidedHorizontally = this.field1.isCollidedHorizontally;
      this.collidedVertically = this.field1.isCollidedVertically;
      this.dimension = this.field1.dimension;
      this.fire = this.field1.fire;
      this.firstUpdate = this.field1.firstUpdate;
      this.isInWeb = this.field1.isInWeb;
      this.motionX = this.field1.field_149152_f;
      this.motionY = this.field1.field_149153_g;
      this.motionZ = this.field1.field_149159_h;
      this.noClip = this.field1.noClip;
      this.inPortal = this.field1.inPortal;
      this.timeUntilPortal = this.field1.timeUntilPortal;
      this.portalCounter = this.field1.portalCounter;
      this.teleportDirection$v1_7 = this.field1.teleportDirection$v1_7;
      this.boundingBox = this.field1.boundingBox;
      this.rotationYawHead = this.field1.rotationYaw;
      this.prevRotationYawHead = this.field1.prevRotationYaw;
   }

   public boolean isPlayerSleeping() {
      this.method1();
      return super.isPlayerSleeping();
   }

   public MovingObjectPosition rayTrace(double var1, float var3) {
      this.method1();
      return super.rayTrace(var1, var3);
   }

   public Vec3 getPosition(float var1) {
      this.method1();
      return super.getPosition(var1);
   }

   public Vec3 getLookVec() {
      this.method1();
      return super.getLookVec();
   }

   public double getDistanceSq(double var1, double var3, double var5) {
      this.method1();
      return super.getDistanceSq(var1, var3, var5);
   }

   public double getDistance(double var1, double var3, double var5) {
      this.method1();
      return super.getDistance(var1, var3, var5);
   }

   public float getDistanceToEntity(Entity var1) {
      this.method1();
      return super.getDistanceToEntity(var1);
   }
}

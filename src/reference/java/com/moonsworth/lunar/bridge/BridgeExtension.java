package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import net.kyori.adventure.text.Component;
import org.joml.Math;
import org.joml.Vector3d;
import org.joml.Vector3ic;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/entity/Entity")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/world/entity/Entity"))
   }
)
public interface BridgeExtension extends Bridge_61, Bridge_38 {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isSneaking()Z")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isShiftKeyDown()Z"))
      }
   )
   boolean bridge$isSneaking();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isRiding()Z")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isPassenger()Z"))
      }
   )
   boolean bridge$isRiding();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("ticksExisted")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("tickCount"))
      }
   )
   int method2();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("ticksExisted")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("tickCount"))
      }
   )
   void method2(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("worldObj")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("world")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("level"))
      }
   )
   Itemcounter6 bridge$getWorld();

   void bridge$lerpTo(double var1, double var3, double var5, float var7, float var8);

   void bridge$setOnGround(boolean var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posX")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "x"}))
      }
   )
   @Override
   double bridge$getPosX();

   void bridge$setPosX(double var1);

   void bridge$setChunkX(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posY")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "y"}))
      }
   )
   @Override
   double bridge$getPosY();

   void bridge$setPosY(double var1);

   void bridge$setChunkY(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posZ")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "z"}))
      }
   )
   @Override
   double bridge$getPosZ();

   void bridge$setPosZ(double var1);

   void bridge$setChunkZ(int var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("prevPosX")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("xo"))
      }
   )
   double method3();

   void bridge$setPreviousPosX(double var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("prevPosY")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("yo"))
      }
   )
   double method4();

   void bridge$setPreviousPosY(double var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("prevPosZ")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("zo"))
      }
   )
   double method5();

   void bridge$setPreviousPosZ(double var1);

   default void bridge$setOldPos(double var1, double var3, double var5) {
   }

   Horsestats20Extension2 bridge$getBlockPos();

   @Override
   double bridge$getMotionX();

   void method6(double var1);

   @Override
   double bridge$getMotionY();

   void bridge$setMotionY(double var1);

   @Override
   double bridge$getMotionZ();

   void method7(double var1);

   double bridge$getRotationYaw();

   double bridge$getRotationPitch();

   void bridge$setRotationYaw(double var1);

   void bridge$setRotationPitch(double var1);

   double bridge$getPreviousRotationYaw();

   void bridge$setPreviousRotationYaw(double var1);

   double bridge$getPreviousRotationPitch();

   void bridge$setPreviousRotationPitch(double var1);

   UUID bridge$getUniqueID();

   void bridge$setUniqueID(UUID var1);

   @Override
   AxisAlignedBBBridge bridge$getBoundingBox();

   AxisAlignedBBBridge bridge$getBoundingBox(float var1);

   AxisAlignedBBBridge bridge$getBoundingBoxForCulling();

   default double method8(float var1) {
      return this.method3() + (this.bridge$getPosX() - this.method3()) * var1;
   }

   default double method9(float var1) {
      return this.method4() + (this.bridge$getPosY() - this.method4()) * var1;
   }

   default double method10(float var1) {
      return this.method5() + (this.bridge$getPosZ() - this.method5()) * var1;
   }

   default AxisAlignedBBBridge method11(float var1) {
      return this.bridge$getBoundingBox()
         .bridge$offset(this.method8(var1) - this.bridge$getPosX(), this.method9(var1) - this.bridge$getPosY(), this.method10(var1) - this.bridge$getPosZ());
   }

   AxisAlignedBBBridge method12();

   boolean bridge$isOnGround();

   boolean bridge$isInvisibleTo(Bridge6_10 var1);

   default double method13(BridgeExtension var1) {
      double var2 = this.bridge$getPosX() - var1.bridge$getPosX();
      double var4 = this.bridge$getPosY() - var1.bridge$getPosY();
      double var6 = this.bridge$getPosZ() - var1.bridge$getPosZ();
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   default double method14(BridgeExtension var1) {
      return Math.sqrt(this.method13(var1));
   }

   default double method15(double var1, double var3, double var5) {
      double var7 = this.bridge$getPosX() - var1;
      double var9 = this.bridge$getPosY() - var3;
      double var11 = this.bridge$getPosZ() - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   default double method16(Vector3iBridge var1) {
      return this.method15(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   default double method17(Vector3ic var1) {
      return this.method15(var1.x(), var1.y(), var1.z());
   }

   default double method18(double var1, double var3, double var5) {
      return Math.sqrt(this.method15(var1, var3, var5));
   }

   default Vec3Bridge method19() {
      return this.bridge$getViewVector(1.0F);
   }

   Vec3Bridge bridge$getViewVector(float var1);

   int bridge$getDimension();

   void bridge$setDimension(int var1);

   default String bridge$getDimensionName() {
      return "";
   }

   default Vec3Bridge bridge$getEyePosition() {
      return this.bridge$getEyePosition(0.0F);
   }

   Vec3Bridge bridge$getEyePosition(float var1);

   BridgeExtension bridge$getRidingEntity();

   double bridge$getFallDistance();

   void bridge$resetFallDistance();

   boolean bridge$hitByEntity(BridgeExtension var1);

   double bridge$lastTickX();

   void bridge$setLastTickX(double var1);

   double bridge$lastTickY();

   void bridge$setLastTickY(double var1);

   double bridge$lastTickZ();

   void bridge$setLastTickZ(double var1);

   Bridge2_7 bridge$getDataWatcher();

   boolean bridge$isRidingBoat();

   boolean bridge$isCollidedHorizontally();

   boolean bridge$isRemoved();

   default double method20() {
      double var1 = this.bridge$getPosX() - this.method3();
      double var3 = this.bridge$getPosY() - this.method4();
      double var5 = this.bridge$getPosZ() - this.method5();
      return var1 * var1 + var3 * var3 + var5 * var5;
   }

   @Override
   Component bridge$getTypeName();

   String bridge$getEntityString();

   ItemStackBridge bridge$getPickResult();

   void bridge$setCustomName(Component var1);

   void bridge$setCustomNameVisible(boolean var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$shouldShowName();

   int bridge$getSpawnEggColor(int var1);

   String bridge$getType();

   boolean bridge$isGlowing();

   void bridge$setGlowing(boolean var1);

   void bridge$turn(float var1, float var2);

   void bridge$setGlowingColor(int var1);

   int bridge$getGlowingColor();

   default void bridge$decreaseEntityIdCounter() {
   }

   void bridge$setId(int var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void bridge$setNoPhysics(boolean var1) {
   }

   boolean bridge$shouldRenderAtSqrDistance(double var1);

   void bridge$sendPairingData(Consumer<Bridge3_21> var1);

   boolean bridge$isInWater();

   float bridge$maxUpStep();

   int bridge$getMaxFallDistance();

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   @OverridingMethodsMustInvokeSuper
   default int bridge$canTurbo() {
      if (this.bridge$isGlowing()) {
         return 1180;
      } else {
         return this.bridge$isOnFire() ? 100 : -1;
      }
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default void bridge$stopTurbo(@Nullable Vec3Bridge var1) {
      throw new AbstractMethodErrorImpl();
   }

   boolean bridge$isAlive();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Bridge2_42 bridge$getNbtAsComponent() {
      throw new AbstractMethodErrorImpl();
   }

   boolean bridge$getNoCulling();

   boolean bridge$isInWaterOrBubble();

   boolean bridge$canBeCollidedWith();

   @Override
   default float bridge$ageInTicks() {
      return this.method2();
   }

   boolean bridge$isItemFrame();

   boolean bridge$isFirework();

   boolean bridge$isWitherSkull();

   boolean bridge$isSnowball();

   boolean bridge$isFireball();

   boolean bridge$isMonster();

   boolean bridge$isMob();

   boolean bridge$isLeashed();

   @Nullable
   BridgeExtension bridge$getLeashedToEntity();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   BridgeExtension bridge$getVehicle();

   Optional<String> bridge$getTeamName();

   Optional<String> bridge$getBoatType();

   boolean bridge$isBoat();

   boolean bridge$isMinecart();

   default boolean bridge$isTextDisplay() {
      return false;
   }

   default Vector3d bridge$getPosition() {
      return new Vector3d(this.bridge$getPosX(), this.bridge$getPosY(), this.bridge$getPosZ());
   }

   default Vector3iBridge bridge$pick$v1_16(float var1, float var2, boolean var3) {
      return null;
   }

   Vector3d bridge$getLookAngle();

   boolean bridge$shouldRender(double var1, double var3, double var5);

   List<BridgeExtension> bridge$getPassengers();
}

package com.moonsworth.lunar.bridge.itemcounter;

import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_59;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3ic;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/world/World")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/world/level/Level"))
   }
)
public interface Itemcounter6 {
   Optional<Bridge6_10> bridge$getPlayerByUniqueId(UUID var1);

   Optional<Bridge6_10> bridge$getPlayerByName(String var1);

   Optional<BridgeExtension> bridge$getEntityById(int var1);

   void bridge$playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10);

   List<Bridge6_10> bridge$getPlayerEntities();

   List<BridgeExtension> bridge$getEntities();

   List<BridgeExtension> bridge$getEntities(AxisAlignedBBBridge var1, Predicate<? super BridgeExtension> var2);

   default BridgeExtension bridge$getFirstEntity(AxisAlignedBBBridge var1, Predicate<? super BridgeExtension> var2) {
      List var3 = this.bridge$getEntities(var1, var2);
      return var3.isEmpty() ? null : (BridgeExtension)var3.get(0);
   }

   List<HitcolorExtension> bridge$getBlockEntities();

   Collection<HitcolorExtension> bridge$getBlockEntities(int var1, int var2);

   boolean bridge$isRemote();

   Itemcounter5_2 bridge$getWorldInfo();

   boolean bridge$isChunkLoaded(int var1, int var2);

   com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2 bridge$getChunk(int var1, int var2);

   com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2 bridge$getChunkFromBlockCoords(Vector3ic var1);

   Itemcounter3_3 bridge$getWorldChunkManager();

   void bridge$setWorldTime(long var1);

   default void bridge$setWorldTime(long var1, boolean var3) {
      this.bridge$setWorldTime(var1);
   }

   default void bridge$resetWorldTime() {
   }

   long bridge$getWorldTime();

   int bridge$getDimensionId();

   String bridge$getDimensionKey();

   void bridge$spawnParticle(HorsestatsType2 var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15);

   default Itemcounter_3 method1(Vector3iBridge var1) {
      return this.bridge$getBiome(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   Itemcounter_3 bridge$getBiome(int var1, int var2, int var3);

   Bridge2_17 bridge$getBlockState(int var1, int var2, int var3);

   Bridge2_17 bridge$getBlockState(double var1, double var3, double var5);

   default Bridge2_17 method2(Vector3iBridge var1) {
      return this.bridge$getBlockState(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   default Bridge_59 bridge$getFluidState$v1_16(Vector3iBridge var1) {
      return null;
   }

   default Bridge2_17 method3(Vector3ic var1) {
      return this.bridge$getBlockState(var1.x(), var1.y(), var1.z());
   }

   Bridge3_23 bridge$getBlockAt(int var1, int var2, int var3);

   Bridge3_23 bridge$getBlockAt(double var1, double var3, double var5);

   default Bridge3_23 method4(Vector3iBridge var1) {
      return this.bridge$getBlockAt(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   default Bridge3_23 method5(Vector3ic var1) {
      return this.bridge$getBlockAt(var1.x(), var1.y(), var1.z());
   }

   List<AxisAlignedBBBridge> bridge$getBlockCollisionBoxes(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2);

   List<AxisAlignedBBBridge> bridge$getCollisionBoxes(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2);

   boolean bridge$isBlockLoaded(Vector3iBridge var1);

   int bridge$getPackedLight(Vector3iBridge var1);

   boolean bridge$isInWater(double var1, double var3, double var5);

   default int bridge$getMinBuildHeight() {
      return 0;
   }

   default int bridge$getMaxBuildHeight() {
      return 256;
   }

   File bridge$getWorldDirectory();

   UUID bridge$getWorldId();

   List<Bridge3_21> bridge$getScoreboardPackets();

   boolean bridge$noCollision(@Nullable BridgeExtension var1, AxisAlignedBBBridge var2);

   ItemcounterType2_3 bridge$getPathTypeFromState(Vector3iBridge var1);

   @Nullable
   HitcolorExtension bridge$getBlockEntity(Vector3iBridge var1);

   Lighting4 bridge$getScoreBoard();

   boolean bridge$isRaining();

   boolean bridge$isThundering();

   default int bridge$getFoliageColor(Horsestats20Extension2 var1) {
      return 0;
   }
}

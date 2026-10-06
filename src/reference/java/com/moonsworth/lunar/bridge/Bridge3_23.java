package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.List;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/block/Block")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("net/minecraft/world/level/block/Block"))
   }
)
public interface Bridge3_23 {
   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   List<Bridge2_17> bridge$getValidStates();

   boolean bridge$isSkull();

   boolean bridge$isFoliage();

   boolean bridge$isFlower();

   ItemStackBridge bridge$getStack(Vector3iBridge var1);

   boolean bridge$isWater();

   default boolean bridge$isBubbleColumn() {
      return false;
   }

   boolean bridge$isAir();

   boolean bridge$isFire();

   boolean bridge$isPressurePlate();

   boolean bridge$isCarpet();

   boolean bridge$isSign();

   boolean bridge$isSmallPot();

   boolean bridge$isBanner();

   boolean bridge$isHandOpenableTrapDoor();

   boolean bridge$isFenceGate();

   boolean bridge$isFlippedLever(int var1, int var2, int var3);

   boolean bridge$isDepressedPlate(int var1, int var2, int var3);

   boolean bridge$isCauldron();

   boolean bridge$isCake();

   boolean bridge$isAnyChest();

   boolean bridge$isMushroom();

   boolean bridge$isFlowerPot();

   boolean bridge$isExcludedFromMinimap();

   Component bridge$getName();

   MissResult bridge$clip(Itemcounter6 var1, Vector3iBridge var2, Vec3Bridge var3, Vec3Bridge var4);

   @Nullable
   default HorsestatsType_2 bridge$isMultiBlock(Itemcounter6 var1, Vector3iBridge var2) {
      return null;
   }

   @Nullable
   AxisAlignedBBBridge bridge$getAABB(Itemcounter6 var1, Vector3iBridge var2);

   boolean bridge$hasCollision(Itemcounter6 var1, Vector3iBridge var2);

   default int bridge$getDamageValue(Itemcounter6 var1, Vector3iBridge var2) {
      throw new AbstractMethodErrorImpl();
   }

   boolean bridge$entityCanStandOn(Itemcounter6 var1, Vector3iBridge var2, BridgeExtension var3);

   int bridge$getPreFlatteningID();

   String bridge$getRegistryName();

   float bridge$getDestroySpeed();

   boolean bridge$breaksByPickaxe();

   boolean bridge$breaksByAxe();

   boolean bridge$breaksByShovel();

   boolean bridge$breaksByHoe();

   boolean bridge$breaksByShears();

   boolean bridge$isCubeBlock();

   boolean bridge$isSpawner();

   boolean bridge$isGlass();

   default void bridge$onStateChange(Vector3iBridge var1, Bridge2_17 var2, Bridge2_17 var3) {
   }
}

package com.moonsworth.lunar.bridge;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.EnumFacingBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.PathTypeBridge;
import com.moonsworth.lunar.bridge.world.BlockRenderTypeBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import javax.annotation.Nullable;

public interface BlockStateBridge {
   Bridge3_23 bridge$getBlock();

   MissResult bridge$clip(Itemcounter6 itemcounter61, Vec3iBridge horsestats202, Vec3Bridge horsestats153, Vec3Bridge horsestats154);

   @Nullable
   default Itemcounter_4 bridge$getBlockShape(Itemcounter6 itemcounter61, Vec3iBridge horsestats202) {
      throw new AbstractMethodErrorImpl();
   }

   @Nullable
   default Itemcounter_4 bridge$getInteractionShape(Itemcounter6 itemcounter61, Vec3iBridge horsestats202) {
      throw new AbstractMethodErrorImpl();
   }

   double bridge$getCollisionHeight(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22);

   double bridge$getCollisionShapeMaxY(Itemcounter6 itemcounter61, Vec3iBridge horsestats202);

   @VersionGate(min = 1)
   EnumFacingBridge bridge$getFacingValue();

   @VersionGate(min = 1)
   BedPartTypeBridge bridge$getBedPartValue();

   boolean bridge$isFluid();

   boolean bridge$isSolid();

   boolean bridge$isPathfindable(Itemcounter6 itemcounter61, Vec3iBridge horsestats202, PathTypeBridge itemcountertype_33);

   BlockRenderTypeBridge bridge$getRenderShape();

   @VersionGate(min = 1)
   int bridge$getBites();

   int bridge$getLayersValue();

   boolean bridge$getOpenValue();

   int bridge$getMapColor(ChunkBridge itemcounter21, int number2, int number3, int number4);

   boolean bridge$hasBlockEntity();

   @VersionGate(min = 1)
   float bridge$getHardness(WorldBridgeExtension itemcounter6extension1, Vec3iBridge horsestats202);

   @VersionGate(min = 1)
   boolean bridge$breaksWithPickaxe();

   @VersionGate(min = 1)
   boolean bridge$breaksWithAxe();

   @VersionGate(min = 1)
   boolean bridge$breaksWithShovel();

   boolean bridge$isCollisionShapeFullBlock(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22);

   boolean bridge$isCollisionFaceFull(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22, HorsestatsType_2 horsestatstype_23);

   boolean bridge$isViewBlocking(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22);

   boolean bridge$isOcclusionFaceFull(Itemcounter6 itemcounter61, Horsestats20Extension2 horsestats20extension22, HorsestatsType_2 horsestatstype_23);

   boolean bridge$isCubeBlock();

   @VersionGate(min = 1)
   default ImmutableMap<String, Comparable<?>> bridge$getStringProperties() {
      return ImmutableMap.of();
   }

   @VersionGate(min = 6)
   default List<ResourceLocationBridge> bridge$getTags() {
      return List.of();
   }

   boolean bridge$isSmoothDiorite();

   default boolean bridge$isCandle() {
      return false;
   }

   boolean bridge$isFlowerPot();

   boolean bridge$isSign();
}

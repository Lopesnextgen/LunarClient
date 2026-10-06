package com.moonsworth.lunar.bridge;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.FacingIndexBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_3;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType_4;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import java.util.List;
import javax.annotation.Nullable;

public interface Bridge2_17 {
   Bridge3_23 bridge$getBlock();

   MissResult bridge$clip(Itemcounter6 var1, Vector3iBridge var2, Vec3Bridge var3, Vec3Bridge var4);

   @Nullable
   default Itemcounter_4 bridge$getBlockShape(Itemcounter6 var1, Vector3iBridge var2) {
      throw new AbstractMethodErrorImpl();
   }

   @Nullable
   default Itemcounter_4 bridge$getInteractionShape(Itemcounter6 var1, Vector3iBridge var2) {
      throw new AbstractMethodErrorImpl();
   }

   double bridge$getCollisionHeight(Itemcounter6 var1, Horsestats20Extension2 var2);

   double bridge$getCollisionShapeMaxY(Itemcounter6 var1, Vector3iBridge var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   FacingIndexBridge bridge$getFacingValue();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   MixinHelper2_10 bridge$getBedPartValue();

   boolean bridge$isFluid();

   boolean bridge$isSolid();

   boolean bridge$isPathfindable(Itemcounter6 var1, Vector3iBridge var2, ItemcounterType_3 var3);

   ItemcounterType_4 bridge$getRenderShape();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   int bridge$getBites();

   int bridge$getLayersValue();

   boolean bridge$getOpenValue();

   int bridge$getMapColor(Itemcounter2 var1, int var2, int var3, int var4);

   boolean bridge$hasBlockEntity();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   float bridge$getHardness(Itemcounter6Extension var1, Vector3iBridge var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$breaksWithPickaxe();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$breaksWithAxe();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   boolean bridge$breaksWithShovel();

   boolean bridge$isCollisionShapeFullBlock(Itemcounter6 var1, Horsestats20Extension2 var2);

   boolean bridge$isCollisionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3);

   boolean bridge$isViewBlocking(Itemcounter6 var1, Horsestats20Extension2 var2);

   boolean bridge$isOcclusionFaceFull(Itemcounter6 var1, Horsestats20Extension2 var2, HorsestatsType_2 var3);

   boolean bridge$isCubeBlock();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   default ImmutableMap<String, Comparable<?>> bridge$getStringProperties() {
      return ImmutableMap.of();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
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

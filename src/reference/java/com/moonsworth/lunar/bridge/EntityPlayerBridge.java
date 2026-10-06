package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface EntityPlayerBridge extends BridgeExtension22_3 {
   default String bridge$getSkinType() {
      return "default";
   }

   boolean bridge$isSpectator();

   @Override
   int bridge$getEntityId();

   String bridge$getName();

   boolean bridge$isUsingItem();

   boolean bridge$isRiding();

   boolean bridge$showHat();

   boolean bridge$showJacket();

   boolean bridge$showLeftPants();

   boolean bridge$showRightPants();

   boolean bridge$showLeftSleeve();

   boolean bridge$showRightSleeve();

   boolean bridge$showCape();

   EntityPlayerBridge.Type bridge$getPlayerType();

   UUID bridge$getUniqueID();

   AxisAlignedBBBridge bridge$getBoundingBox();

   boolean bridge$isSkinTextureUploaded();

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   default void bridge$setSkinTextureUploaded(boolean var1) {
   }

   default ResourceLocationBridge bridge$getLocationSkin() {
      return null;
   }

   default ResourceLocationBridge bridge$getLocationSkinNoOverride() {
      return null;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   default void bridge$setLocationSkin(ResourceLocationBridge var1) {
   }

   boolean bridge$isEmoting();

   @Nullable
   <C> C bridge$getEmoteController();

   @Nullable
   <C> List<C> bridge$getWornCosmetics();

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   @Nullable
   MixinHelper bridge$getClothCloakState();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   BakedModelExtension bridge$getBakedHelmetModel();

   default boolean bridge$isSelf() {
      return this.bridge$getPlayerType() == EntityPlayerBridge.Type.SELF;
   }

   default boolean method2() {
      return this.bridge$isDummySelf() || this.bridge$isDummyMannequin();
   }

   default boolean bridge$isDummySelf() {
      return this.bridge$getPlayerType() == EntityPlayerBridge.Type.DUMMY_SELF;
   }

   default boolean bridge$isDummyMannequin() {
      return this.bridge$getPlayerType() == EntityPlayerBridge.Type.DUMMY_MANNEQUIN;
   }

   default void bridge$setLunarLogoNameTagWorkaround(boolean var1) {
   }

   default void bridge$setLunarBadgeNameTagWorkaround(boolean var1) {
   }

   default boolean bridge$isMainHandSwapped() {
      return false;
   }

   enum Type {
      DUMMY_SELF,
      DUMMY_MANNEQUIN,
      MANNEQUIN,
      SELF,
      OTHER;
   }
}

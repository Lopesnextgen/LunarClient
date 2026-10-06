package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.ichor.VersionGate;
import org.jetbrains.annotations.Nullable;

public interface RenderItemBridge {
   @VersionGate(max = 5)
   default void bridge$renderModel(BakedModelBridge mixinhelper4_51, int value) {
      throw new AbstractMethodErrorImpl();
   }

   void bridge$setZLevel(float value1);

   float bridge$getZLevel();

   default boolean bridge$shouldRenderItemIn3D(ItemStackBridge bridgeextension_41) {
      return false;
   }

   void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext bridgeextension_91, ItemStackBridge bridgeextension_42, int number3, int number4);

   void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext bridgeextension_91, ItemStackBridge bridgeextension_42, int number3, int number4, boolean flag5);

   @VersionGate(min = 1, max = 1)
   void bridge$renderItemInWorld(AbstractRenderContext bridgeextension_91, ItemStackBridge bridgeextension_42);

   default void bridge$renderItemAndEffectIntoGUIImmediate(AbstractRenderContext bridgeextension_91, ItemStackBridge bridgeextension_42, int number3, int number4) {
      this.bridge$renderItemAndEffectIntoGUI(bridgeextension_91, bridgeextension_42, number3, number4);
   }

   default void bridge$renderDroppedItem(EntityItemBridge entity, Bridge4_8 bridge4_82, int number3, float value, float value2, float value3, float value4) {
   }

   void bridge$renderItem(ItemStackBridge bridgeextension_41, BakedModelBridge mixinhelper4_52);

   @Nullable
   ItemModelMesherBridge bridge$getItemModelShaper();

   @Nullable
   default ResourceLocationBridge bridge$getModelLocation() {
      return null;
   }

   @VersionGate(min = 1)
   @Nullable
   BakedModelBridge bridge$getModel(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62, Object obj3);
}

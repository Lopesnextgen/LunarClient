package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import org.jetbrains.annotations.Nullable;

public interface Bridge5_19 {
   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default void bridge$renderModel(BakedModelExtension var1, int var2) {
      throw new AbstractMethodErrorImpl();
   }

   void bridge$setZLevel(float var1);

   float bridge$getZLevel();

   default boolean bridge$shouldRenderItemIn3D(ItemStackBridge var1) {
      return false;
   }

   void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext var1, ItemStackBridge var2, int var3, int var4);

   void bridge$renderItemAndEffectIntoGUI(AbstractRenderContext var1, ItemStackBridge var2, int var3, int var4, boolean var5);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1, max = 1)
   void bridge$renderItemInWorld(AbstractRenderContext var1, ItemStackBridge var2);

   default void bridge$renderItemAndEffectIntoGUIImmediate(AbstractRenderContext var1, ItemStackBridge var2, int var3, int var4) {
      this.bridge$renderItemAndEffectIntoGUI(var1, var2, var3, var4);
   }

   default void bridge$renderDroppedItem(ItemEntityBridge var1, Bridge4_8 var2, int var3, float var4, float var5, float value, float value2) {
   }

   void bridge$renderItem(ItemStackBridge var1, BakedModelExtension var2);

   @Nullable
   Bridge2_29 bridge$getItemModelShaper();

   @Nullable
   default ResourceLocationBridge bridge$getModelLocation() {
      return null;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   @Nullable
   BakedModelExtension bridge$getModel(ItemStackBridge var1, Itemcounter6 var2, Object var3);
}

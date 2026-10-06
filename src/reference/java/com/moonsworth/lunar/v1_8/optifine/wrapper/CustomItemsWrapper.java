package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.optifine.CustomItemsBridge;
import java.util.Optional;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.optifine.CustomItems;

public class CustomItemsWrapper implements CustomItemsBridge {
   public CustomItemsWrapper() {
   }

   @Override
   public boolean useGlint() {
      return CustomItems.isUseGlint();
   }

   @Override
   public boolean renderCustomEffect(RenderItemBridge bridge5_191, ItemStackBridge bridgeextension_42, BakedModelBridge mixinhelper4_53) {
      return CustomItems.renderCustomEffect((RenderItem)bridge5_191, (ItemStack)bridgeextension_42, (IBakedModel)mixinhelper4_53);
   }

   @Override
   public Optional<BakedModelBridge> getCustomItemModel(ItemStackBridge bridgeextension_41, BakedModelBridge mixinhelper4_52, ResourceLocationBridge horsestats143, boolean flag4) {
      return Optional.of((BakedModelBridge)CustomItems.getCustomItemModel((ItemStack)bridgeextension_41, (IBakedModel)mixinhelper4_52, (ModelResourceLocation)horsestats143, flag4));
   }
}

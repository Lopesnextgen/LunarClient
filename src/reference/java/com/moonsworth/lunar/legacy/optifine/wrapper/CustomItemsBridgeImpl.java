package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.optifine.CustomItemsBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Optional;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;

@VersionGate(min = 5)
public class CustomItemsBridgeImpl implements CustomItemsBridge {
   public CustomItemsBridgeImpl() {
   }

   public boolean useGlint() {
      return CustomItems.isUseGlint();
   }

   public boolean renderCustomEffect(RenderItemBridge bridge5_191, ItemStackBridge bridgeextension_42, BakedModelBridge mixinhelper4_53) {
      return CustomItems.renderCustomEffect((RenderItem)bridge5_191, (ItemStack)bridgeextension_42, (IBakedModel)mixinhelper4_53);
   }

   public Optional<BakedModelBridge> getCustomItemModel(ItemStackBridge bridgeextension_41, BakedModelBridge mixinhelper4_52, ResourceLocationBridge horsestats143, boolean flag4) {
      return Optional.of((BakedModelBridge)CustomItems.getCustomItemModel((ItemStack)bridgeextension_41, (IBakedModel)mixinhelper4_52, (ResourceLocation)horsestats143, flag4));
   }
}

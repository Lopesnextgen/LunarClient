package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;

@Annotation2(min = 5)
public class Slayer3Renderer implements Slayer6 {
   public boolean useGlint() {
      return CustomItems.isUseGlint();
   }

   public boolean renderCustomEffect(Bridge5_19 var1, ItemStackBridge var2, BakedModelExtension var3) {
      return CustomItems.renderCustomEffect((RenderItem)var1, (ItemStack)var2, (IBakedModel)var3);
   }

   public Optional<BakedModelExtension> getCustomItemModel(ItemStackBridge var1, BakedModelExtension var2, ResourceLocationBridge var3, boolean var4) {
      return Optional.of((BakedModelExtension)CustomItems.getCustomItemModel((ItemStack)var1, (IBakedModel)var2, (ResourceLocation)var3, var4));
   }
}

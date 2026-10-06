package com.moonsworth.lunar.v1_8.optifine.wrapper;

import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer6;
import java.util.Optional;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.optifine.CustomItems;

public class Wrapper2 implements Slayer6 {
   @Override
   public boolean useGlint() {
      return CustomItems.isUseGlint();
   }

   @Override
   public boolean renderCustomEffect(Bridge5_19 var1, ItemStackBridge var2, BakedModelExtension var3) {
      return CustomItems.renderCustomEffect((RenderItem)var1, (ItemStack)var2, (IBakedModel)var3);
   }

   @Override
   public Optional<BakedModelExtension> getCustomItemModel(ItemStackBridge var1, BakedModelExtension var2, ResourceLocationBridge var3, boolean var4) {
      return Optional.of((BakedModelExtension)CustomItems.getCustomItemModel((ItemStack)var1, (IBakedModel)var2, (ModelResourceLocation)var3, var4));
   }
}

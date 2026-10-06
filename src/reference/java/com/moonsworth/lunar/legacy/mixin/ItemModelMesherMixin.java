package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemModelMesherBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(ItemModelMesher.class)
public abstract class ItemModelMesherMixin implements ItemModelMesherBridge {
   public ItemModelMesherMixin() {
   }

   @Shadow
   public abstract IBakedModel getItemModel(ItemStack stack1);

   public BakedModelBridge bridge$getItemModel(ItemStackBridge bridgeextension_41) {
      return (BakedModelBridge)this.getItemModel((ItemStack)bridgeextension_41);
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(RenderItem.class)
public abstract class RenderItemBridgeMixin implements RenderItemBridge {
   @Shadow
   @VersionGate(max = 1)
   public ModelResourceLocation modelLocation;
   @Shadow
   @VersionGate(min = 5)
   public ResourceLocation modelLocation$v1_12;
   @Final
   @Shadow
   public ItemModelMesher itemModelMesher;

   public RenderItemBridgeMixin() {
   }

   @Shadow
   public abstract IBakedModel getItemModelWithOverrides$v1_12(ItemStack stack1, World world2, EntityLivingBase entity3);

   public ResourceLocationBridge bridge$getModelLocation() {
      return Ref.MC_VERSION >= 5 ? (ResourceLocationBridge)this.modelLocation$v1_12 : (ResourceLocationBridge)this.modelLocation;
   }

   public BakedModelBridge bridge$getModel(ItemStackBridge bridgeextension_41, Itemcounter6 itemcounter62, Object obj3) {
      if (Ref.MC_VERSION >= 5) {
         return (BakedModelBridge)this.getItemModelWithOverrides$v1_12((ItemStack)bridgeextension_41, (World)itemcounter62, (EntityLivingBase)obj3);
      } else if (Ref.MC_VERSION == 1) {
         return (BakedModelBridge)this.itemModelMesher.getItemModel((ItemStack)bridgeextension_41);
      } else {
         throw new UnsupportedOperationException("unavailable function in 1.7.10");
      }
   }
}

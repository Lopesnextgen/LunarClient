package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BakedModelExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
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

@Annotation2(min = 1)
@Mixin(RenderItem.class)
public abstract class RenderItemMixin3 implements Bridge5_19 {
   @Shadow
   @Annotation2(max = 1)
   public ModelResourceLocation modelLocation;
   @Shadow
   @Annotation2(min = 5)
   public ResourceLocation modelLocation$v1_12;
   @Final
   @Shadow
   public ItemModelMesher itemModelMesher;

   @Shadow
   public abstract IBakedModel getItemModelWithOverrides$v1_12(ItemStack var1, World var2, EntityLivingBase var3);

   public ResourceLocationBridge bridge$getModelLocation() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? (ResourceLocationBridge)this.modelLocation$v1_12 : (ResourceLocationBridge)this.modelLocation;
   }

   public BakedModelExtension bridge$getModel(ItemStackBridge var1, Itemcounter6 var2, Object var3) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (BakedModelExtension)this.getItemModelWithOverrides$v1_12((ItemStack)var1, (World)var2, (EntityLivingBase)var3);
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         return (BakedModelExtension)this.itemModelMesher.getItemModel((ItemStack)var1);
      } else {
         throw new UnsupportedOperationException("unavailable function in 1.7.10");
      }
   }
}

package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin.BedwarsBedModelFactory;
import java.util.Set;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
   @Shadow
   public Set<ResourceLocation> textures;

   public ModelLoaderMixin() {
   }

   @Inject(method = "loadItems", at = @At("RETURN"))
   private void lunar$onLoadItems(CallbackInfo callback1) {
      for (String text3 : BedwarsBedModelFactory.method3()) {
         this.textures.add(new ResourceLocation(text3));
      }
   }
}

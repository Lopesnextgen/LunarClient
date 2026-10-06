package com.moonsworth.lunar.forge.mixin;

import java.util.Set;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.IRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin2 extends ModelBakery {
   public ModelLoaderMixin2(IResourceManager var1, TextureMap var2, BlockModelShapes var3) {
      super(var1, var2, var3);
   }

   @Inject(
      method = "setupModelRegistry",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureMap;loadSprites(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/renderer/texture/ITextureMapPopulator;)V"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void ichor$insertNewLambda(CallbackInfoReturnable<IRegistry<ModelResourceLocation, IBakedModel>> var1, Set<ResourceLocation> var2) {
      this.textureMap.loadSprites(this.resourceManager, var1x -> var2.forEach(var1x::registerSprite));
   }

   @Redirect(
      method = "setupModelRegistry",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureMap;loadSprites(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/renderer/texture/ITextureMapPopulator;)V"
      )
   )
   public void ichor$killOldLambda(TextureMap var1, IResourceManager var2, IIconCreator var3) {
   }
}

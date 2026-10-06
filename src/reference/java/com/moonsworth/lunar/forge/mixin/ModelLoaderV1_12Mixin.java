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
public abstract class ModelLoaderV1_12Mixin extends ModelBakery {
   public ModelLoaderV1_12Mixin(IResourceManager iresourcemanager1, TextureMap texturemap2, BlockModelShapes blockmodelshapes3) {
      super(iresourcemanager1, texturemap2, blockmodelshapes3);
   }

   @Inject(
      method = "setupModelRegistry",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureMap;loadSprites(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/renderer/texture/ITextureMapPopulator;)V"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void ichor$insertNewLambda(CallbackInfoReturnable<IRegistry<ModelResourceLocation, IBakedModel>> callbackinforeturnable1, Set<ResourceLocation> set2) {
      this.textureMap.loadSprites(this.resourceManager, arg1x -> set2.forEach(arg1x::registerSprite));
   }

   @Redirect(
      method = "setupModelRegistry",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/texture/TextureMap;loadSprites(Lnet/minecraft/client/resources/IResourceManager;Lnet/minecraft/client/renderer/texture/ITextureMapPopulator;)V"
      )
   )
   public void ichor$killOldLambda(TextureMap texturemap1, IResourceManager iresourcemanager2, IIconCreator iiconcreator3) {
   }
}

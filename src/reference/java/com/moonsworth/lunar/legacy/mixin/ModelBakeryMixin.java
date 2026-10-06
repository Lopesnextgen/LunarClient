package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin.BedwarsBedModelFactory;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.ItemModelBaker;
import com.moonsworth.lunar.legacy.wrapper.ModelBakeryHolder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(min = 1)
@Mixin(ModelBakery.class)
public class ModelBakeryMixin {
   @Final
   @Shadow
   public Map<String, ResourceLocation> itemLocations;
   @Final
   @Shadow
   public Map<ResourceLocation, TextureAtlasSprite> sprites;
   @Final
   @Shadow
   public Map<ResourceLocation, ModelBlock> models;
   @Final
   @Shadow
   public TextureMap textureMap;
   @Final
   @Shadow
   public RegistrySimple<ModelResourceLocation, IBakedModel> bakedRegistry;
   @Unique
   private final HashSet<String> prebakedItemModels = new HashSet<>();

   public ModelBakeryMixin() {
   }

   @Shadow
   public boolean hasItemModel(ModelBlock modelblock1) {
      throw new AssertionError();
   }

   @Shadow
   public boolean isCustomRenderer(ModelBlock modelblock1) {
      throw new AssertionError();
   }

   @Inject(method = "<init>", at = @At("RETURN"))
   private void onInit(IResourceManager iresourcemanager1, TextureMap texturemap2, BlockModelShapes blockmodelshapes3, CallbackInfo callback4) {
      ModelBakeryHolder.field1 = (ModelBakery)this;
   }

   @VersionGate(max = 1)
   @Redirect(
      method = "bakeBlockModels",
      at = @At(value = "FIELD", target = "net/minecraft/client/resources/model/ModelBakery.itemLocations : Ljava/util/Map;")
   )
   private Map<String, ResourceLocation> impl$dontBakePrebakedModels_v1_8(ModelBakery modelbakery1) {
      return this.impl$dontBakePrebakedModels();
   }

   @VersionGate(min = 5)
   @Redirect(
      method = "bakeItemModels",
      at = @At(value = "FIELD", target = "net/minecraft/client/renderer/block/model/ModelBakery.itemLocations : Ljava/util/Map;")
   )
   private Map<String, ResourceLocation> impl$dontBakePrebakedModels_v1_12(ModelBakery modelbakery1) {
      return this.impl$dontBakePrebakedModels();
   }

   private Map<String, ResourceLocation> impl$dontBakePrebakedModels() {
      HashMap map1 = new HashMap<>(this.itemLocations);

      for (String text3 : this.prebakedItemModels) {
         map1.remove(text3);
      }

      return map1;
   }

   @VersionGate(max = 1)
   @Overwrite
   public void bakeItemModels() {
      this.impl$makeAndBakeItemModels();
   }

   @VersionGate(min = 5)
   @Overwrite
   public void makeItemModels$v1_12() {
      this.impl$makeAndBakeItemModels();
   }

   @Unique
   public void impl$makeAndBakeItemModels() {
      this.itemLocations.forEach((arg1, arg2x) -> {
         ModelBlock modelblock3 = this.models.get(arg2x);
         if (this.hasItemModel(modelblock3)) {
            IBakedModel ibakedmodel4 = ItemModelBaker.method1((ModelBakery)this, this.textureMap, modelblock3);
            ModelResourceLocation modelresourcelocation5 = new ModelResourceLocation(arg1, "inventory");
            this.bakedRegistry.putObject(modelresourcelocation5, ibakedmodel4);
            this.prebakedItemModels.add(arg1);
         } else if (this.isCustomRenderer(modelblock3)) {
            this.models.put(arg2x, modelblock3);
         }
      });

      for (TextureAtlasSprite textureatlassprite2 : this.sprites.values()) {
         if (!textureatlassprite2.hasAnimationMetadata()) {
            textureatlassprite2.clearFramesTextureData();
         }
      }
   }

   @Inject(method = "getVariantsTextureLocations", at = @At("RETURN"), cancellable = true)
   public void lunar$onLoadItems(CallbackInfoReturnable<Set<ResourceLocation>> callbackinforeturnable1) {
      for (String text3 : BedwarsBedModelFactory.method3()) {
         ((Set)callbackinforeturnable1.getReturnValue()).add(new ResourceLocation(text3));
      }
   }
}

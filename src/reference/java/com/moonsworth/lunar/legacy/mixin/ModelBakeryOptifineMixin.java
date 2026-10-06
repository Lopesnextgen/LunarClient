package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.MixinCondition;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@MixinCondition(present = "forge")
@Mixin(ModelBakery.class)
public abstract class ModelBakeryOptifineMixin {
   public ModelBakeryOptifineMixin() {
   }

   @Shadow
   public abstract ResourceLocation getModelLocation(ResourceLocation location1);

   @ModifyVariable(
      method = "loadModel",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/resources/IResourceManager;getResource(Lnet/minecraft/util/ResourceLocation;)Lnet/minecraft/client/resources/IResource;"
      ),
      argsOnly = true
   )
   private ResourceLocation lunar$setResourceLocationAsOptiFineDoes(ResourceLocation location1) {
      return this.getModelLocation(location1);
   }

   @ModifyVariable(
      method = "loadModel",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/block/model/ModelBlock;name:Ljava/lang/String;", opcode = 181),
      ordinal = 0
   )
   private ModelBlock lunar$fixModelLocationsAsOptiFineDoes(ModelBlock modelblock1, ResourceLocation location2) {
      ResourceLocationBridge horsestats143 = (ResourceLocationBridge)location2;
      String text4 = TextureUtils.getBasePath(horsestats143.bridge$getPath());
      fixModelLocations(modelblock1, text4);
      return modelblock1;
   }

   @Inject(method = "registerVariantNames", at = @At("TAIL"))
   private void lunar$initializeOptiFineCITs(CallbackInfo callback1) {
      CustomItems.update();
      CustomItems.loadModels((ModelBakery)this);
   }

   @Unique
   private static void fixModelLocations(ModelBlock modelblock0, String text1) {
      ResourceLocation location2 = fixModelLocation(modelblock0.getParentLocation(), text1);
      if (location2 != modelblock0.getParentLocation()) {
         modelblock0.parentLocation = location2;
      }

      Map map3 = modelblock0.textures;
      if (map3 != null) {
         for (Entry entry5 : map3.entrySet()) {
            String text6 = (String)entry5.getValue();
            String text7 = fixResourcePath(text6, text1);
            if (!Objects.equals(text7, text6)) {
               entry5.setValue(text7);
            }
         }
      }
   }

   @Unique
   private static ResourceLocation fixModelLocation(ResourceLocation location0, String text1) {
      ResourceLocationBridge horsestats142 = (ResourceLocationBridge)location0;
      if (location0 != null && text1 != null) {
         if (!horsestats142.bridge$getDomain().equals("minecraft")) {
            return location0;
         }

         String text3 = horsestats142.bridge$getPath();
         String text4 = fixResourcePath(text3, text1);
         if (!Objects.equals(text4, text3)) {
            location0 = new ResourceLocation(horsestats142.bridge$getDomain(), text4);
         }

         return location0;
      } else {
         return location0;
      }
   }

   @Unique
   private static String fixResourcePath(String text0, String text1) {
      text0 = TextureUtils.fixResourcePath(text0, text1);
      text0 = StrUtils.removeSuffix(text0, ".json");
      return StrUtils.removeSuffix(text0, ".png");
   }
}

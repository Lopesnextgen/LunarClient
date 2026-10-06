package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ModelRendererBridge;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(max = 0)
@Mixin(ModelBiped.class)
public abstract class ModelBipedBridgeMixin implements ModelPlayerBridge {
   @Shadow
   public ModelRenderer bipedCloak$v1_7;
   @Unique
   private boolean lunar$isMainModel = false;

   public ModelBipedBridgeMixin() {
   }

   @Inject(method = "<init>(FFII)V", at = @At("RETURN"))
   private void lunar$setMainModel(float value, float value2, int value3, int value4, CallbackInfo callback5) {
      this.lunar$isMainModel = value == 0.0F;
   }

   public ModelRendererBridge bridge$cloak() {
      return (ModelRendererBridge)this.bipedCloak$v1_7;
   }

   public ModelRendererBridge bridge$leftPants() {
      return null;
   }

   public ModelRendererBridge bridge$rightPants() {
      return null;
   }

   public ModelRendererBridge bridge$leftSleeve() {
      return null;
   }

   public ModelRendererBridge bridge$rightSleeve() {
      return null;
   }

   public ModelRendererBridge bridge$jacket() {
      return null;
   }

   public boolean bridge$isSlim() {
      return false;
   }

   public boolean bridge$isMainModel() {
      return this.lunar$isMainModel;
   }
}

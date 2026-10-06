package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_46;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(ModelBiped.class)
public abstract class ModelBipedMixin2 implements BridgeExtension2_7 {
   @Shadow
   public ModelRenderer bipedCloak$v1_7;
   @Unique
   private boolean lunar$isMainModel = false;

   @Inject(method = "<init>(FFII)V", at = @At("RETURN"))
   private void lunar$setMainModel(float value, float value2, int value3, int value4, CallbackInfo callbackInfo) {
      this.lunar$isMainModel = value == 0.0F;
   }

   public Bridge2_46 bridge$cloak() {
      return (Bridge2_46)this.bipedCloak$v1_7;
   }

   public Bridge2_46 bridge$leftPants() {
      return null;
   }

   public Bridge2_46 bridge$rightPants() {
      return null;
   }

   public Bridge2_46 bridge$leftSleeve() {
      return null;
   }

   public Bridge2_46 bridge$rightSleeve() {
      return null;
   }

   public Bridge2_46 bridge$jacket() {
      return null;
   }

   public boolean bridge$isSlim() {
      return false;
   }

   public boolean bridge$isMainModel() {
      return this.lunar$isMainModel;
   }
}

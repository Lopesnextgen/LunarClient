package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.moonsworth.lunar.bridge.LayerCapeBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticModelRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.driver.DriverView;
import com.moonsworth.lunar.client.driver.hologram.HologramRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.Optional;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.skin_n_bones.api.animation.AnimationMesh;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJArmature;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJBone;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(LayerCape.class)
public abstract class LayerCapeMixin implements LayerCapeBridge {
   @Final
   @Shadow
   public RenderPlayer playerRenderer;

   public LayerCapeMixin() {
   }

   @Shadow
   public abstract void doRenderLayer(AbstractClientPlayer player1, float value2, float value3, float value4, float value5, float value6, float value7, float value8);

   @Inject(
      method = "doRenderLayer",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER),
      cancellable = true
   )
   public void lunar$doRenderLayer$pushMatrix(
      AbstractClientPlayer player1, float value2, float value3, float value4, float value5, float value6, float value7, float value8, CallbackInfo callback9
   ) {
      EmoteController emotecontroller10 = (EmoteController)EmoteController.get((BridgeExtension)player1);
      if (emotecontroller10 != null && emotecontroller10.isEmoting()) {
         if (!emotecontroller10.shouldRenderCape()) {
            callback9.cancel();
            GlStateManager.popMatrix();
         } else {
            BOBJArmature bobjarmature11 = ((AnimationMesh)emotecontroller10.animator.animation.meshes.get(0)).armature;
            emotecontroller10.animator.setupMatrix((BOBJBone)bobjarmature11.bones.get("low_body"));
            GlStateManager.translate(0.0, 0.375, 0.0);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }
   }

   @Redirect(
      method = "doRenderLayer",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderPlayer_v1_8;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   private void lunar$bindTexture(RenderPlayer renderplayer1, ResourceLocation location2) {
      if (location2 != null) {
         renderplayer1.bindTexture(location2);
      }
   }

   @WrapWithCondition(method = "doRenderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPlayer;renderCape(F)V"))
   private boolean lunar$doRenderLayer(ModelPlayer modelplayer1, float value2, AbstractClientPlayer player3) {
      if (Ref.method4().method41().method6().method6((EntityPlayerBridge)player3) && CosmeticCategoryType.CLOAK.canShowCosmetic()) {
         CosmeticManager holograms124 = Ref.method4().method53();
         CosmeticMetadata gui2handler35 = holograms124.getProvider(((Bridge5_11)player3).bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
         if (gui2handler35 != null && holograms124.method34(gui2handler35)) {
            return false;
         }

         if (((Bridge5_11)player3).bridge$isDummyMannequin() && gui2handler35 == null) {
            return false;
         }

         ResourceLocation location6 = this.lunar$getLocationCape(player3);
         Bridge8Extension3 bridge8extension37 = Ref.method3().bridge$getTextureManager().method1((ResourceLocationBridge)location6);
         List list8 = Client.method109().method53().method14(player3.getUniqueID(), CosmeticCategoryType.BACKPACK);
         if (!list8.isEmpty()) {
            return false;
         }

         this.lunar$doRenderCloak(player3, bridge8extension37, location6);
         if (gui2handler35 != null && gui2handler35.method4().method22()) {
            DriverView.method21().method14().method3(AbstractRenderContext.method32(), gui2handler35.method4(), () -> this.lunar$doRenderCloak(player3, bridge8extension37, location6));
         }

         return false;
      } else {
         return true;
      }
   }

   @Unique
   private void lunar$doRenderCloak(AbstractClientPlayer player1, Bridge8Extension3 bridge8Extension3, ResourceLocation location3) {
      if (player1 != null && ((Bridge5_11)player1).bridge$isDummySelf() && HologramRenderer.field2) {
         location3 = (ResourceLocation & ResourceLocationBridge)CosmeticManager.field2;
      }

      CosmeticModelRenderer.method5(
         AbstractRenderContext.method32(), null, (Bridge5_11)player1, bridge8Extension3, (ResourceLocationBridge)location3, () -> this.playerRenderer.getMainModel().renderCape(0.0625F)
      );
   }

   @Redirect(method = "doRenderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isSneaking()Z"))
   private boolean lunar$isSneaking(AbstractClientPlayer player1) {
      return player1.isSneaking() && !Ref.method4().method45().method9((Bridge6_10)player1);
   }

   @Redirect(
      method = "doRenderLayer",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;getLocationCape()Lnet/minecraft/util/ResourceLocation;")
   )
   @Nullable
   private ResourceLocation lunar$getLocationCape(AbstractClientPlayer player1) {
      Optional optional2 = Ref.method4().method53().method13(player1.getUniqueID()).map(CosmeticMetadata::method4);
      if (!Ref.method4().method41().method6().method6((EntityPlayerBridge)player1)) {
         return player1.getLocationCape();
      } else if (optional2.isPresent() && CosmeticCategoryType.CLOAK.canShowCosmetic()) {
         CosmeticManager holograms123 = Ref.method4().method53();
         return holograms123.method36((OwnedCosmetic)optional2.get(), player1.getUniqueID()).isEmpty()
            ? null
            : (ResourceLocation)((OwnedCosmetic)optional2.get()).method4((Bridge6_10)player1);
      } else {
         return player1.getLocationCape();
      }
   }

   @Redirect(
      method = "doRenderLayer",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isWearing$v1_8(Lnet/minecraft/entity/player/EnumPlayerModelParts;)Z"
      )
   )
   private boolean lunar$isWearing(AbstractClientPlayer player1, EnumPlayerModelParts enumplayermodelparts2) {
      if (enumplayermodelparts2 == EnumPlayerModelParts.CAPE) {
         Optional optional3 = Ref.method4().method53().method13(player1.getUniqueID()).map(CosmeticMetadata::method4);
         return optional3.isPresent() && CosmeticCategoryType.CLOAK.canShowCosmetic() ? true : player1.isWearing(enumplayermodelparts2);
      } else {
         return player1.isWearing(enumplayermodelparts2);
      }
   }

   public void bridge$render(EntityPlayerBridge entity, float value2, float value3, float value4, float value5, float value6, float value7, float value8) {
      this.doRenderLayer((AbstractClientPlayer)entity, value2, value3, value4, value5, value6, value7, value8);
   }
}

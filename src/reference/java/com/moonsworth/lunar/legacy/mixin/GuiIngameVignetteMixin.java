package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.vignette.Vignette;
import com.lunarclient.apollo.module.vignette.VignetteModule;
import com.moonsworth.lunar.client.network.apollo.VignetteApolloHandler;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class GuiIngameVignetteMixin {
   @Unique
   private ResourceLocation apollo$vignetteResource = null;
   @Unique
   private float apollo$vignetteOpacity = -1.0F;

   public GuiIngameVignetteMixin() {
   }

   @Inject(method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"}, at = @At("HEAD"))
   private void apollo$renderVignette(CallbackInfo callback1) {
      Optional optional2 = Ref.method4().method84().method3(VignetteModule.class);
      if (!optional2.isEmpty()) {
         VignetteApolloHandler highlight3iterator293 = (VignetteApolloHandler)optional2.get();
         Vignette vignette4 = highlight3iterator293.method4();
         ResourceLocation location5 = (ResourceLocation)highlight3iterator293.method6();
         if (vignette4 != null && location5 != null) {
            ITextureObject itextureobject6 = Minecraft.getMinecraft().getTextureManager().getTexture(location5);
            if (!highlight3iterator293.method8() && itextureobject6 == TextureUtil.missingTexture) {
               highlight3iterator293.method3();
               String text7 = Ref.MC_VERSION >= 5 ? location5.getNamespace$v1_12() : location5.getResourceDomain();
               String text8 = Ref.MC_VERSION >= 5 ? location5.getPath() : location5.getResourcePath();
               LunarLogger.method6("Apollo Vignette", "Resetting the Vignette because there is no texture provided at %s:%s.", new Object[]{text7, text8});
            } else {
               this.apollo$vignetteResource = location5;
               this.apollo$vignetteOpacity = vignette4.getOpacity();
            }
         }
      }
   }

   @WrapOperation(
      method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;armorItemInSlot(I)Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack apollo$showVignetteOverlay(InventoryPlayer inventoryplayer1, int number2, Operation<ItemStack> operation3) {
      return this.apollo$vignetteResource != null && this.apollo$vignetteOpacity != -1.0F
         ? new ItemStack(Item.getItemFromBlock(Blocks.pumpkin))
         : (ItemStack)operation3.call(new Object[]{inventoryplayer1, number2});
   }

   @Inject(method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"}, at = @At("TAIL"))
   private void apollo$cleanVignette(CallbackInfo callback1) {
      this.apollo$vignetteResource = null;
      this.apollo$vignetteOpacity = -1.0F;
   }

   @Redirect(
      method = {"renderPumpkinOverlay$v1_8", "renderPumpkinBlur$v1_7"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void apollo$renderPumpkinOverlay$texture(TextureManager texturemanager1, ResourceLocation location2) {
      texturemanager1.bindTexture(this.apollo$vignetteResource != null ? this.apollo$vignetteResource : location2);
   }

   @ModifyConstant(method = {"renderPumpkinOverlay$v1_8", "renderPumpkinBlur$v1_7"}, constant = @Constant(floatValue = 1.0F, ordinal = 3))
   public float apollo$renderPumpkin$opacity(float value1) {
      return this.apollo$vignetteOpacity != -1.0F ? this.apollo$vignetteOpacity : value1;
   }
}

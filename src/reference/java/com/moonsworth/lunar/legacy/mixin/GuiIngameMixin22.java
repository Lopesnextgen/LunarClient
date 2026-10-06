package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.vignette.Vignette;
import com.lunarclient.apollo.module.vignette.VignetteModule;
import com.moonsworth.lunar.client.Highlight3Iterator29;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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
public abstract class GuiIngameMixin22 {
   @Unique
   private ResourceLocation apollo$vignetteResource = null;
   @Unique
   private float apollo$vignetteOpacity = -1.0F;

   @Inject(method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"}, at = @At("HEAD"))
   private void apollo$renderVignette(CallbackInfo var1) {
      Optional var2 = ThreadModuleDump63.method4().method84().method3(VignetteModule.class);
      if (!var2.isEmpty()) {
         Highlight3Iterator29 var3 = (Highlight3Iterator29)var2.get();
         Vignette var4 = var3.method4();
         ResourceLocation var5 = (ResourceLocation)var3.method6();
         if (var4 != null && var5 != null) {
            ITextureObject var6 = Minecraft.getMinecraft().getTextureManager().getTexture(var5);
            if (!var3.method8() && var6 == TextureUtil.missingTexture) {
               var3.method3();
               String var7 = ThreadModuleDump63.MC_VERSION >= 5 ? var5.getNamespace$v1_12() : var5.getResourceDomain();
               String var8 = ThreadModuleDump63.MC_VERSION >= 5 ? var5.getPath() : var5.getResourcePath();
               Slayer.method6("Apollo Vignette", "Resetting the Vignette because there is no texture provided at %s:%s.", new Object[]{var7, var8});
            } else {
               this.apollo$vignetteResource = var5;
               this.apollo$vignetteOpacity = var4.getOpacity();
            }
         }
      }
   }

   @WrapOperation(
      method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/InventoryPlayer;armorItemInSlot(I)Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack apollo$showVignetteOverlay(InventoryPlayer var1, int var2, Operation<ItemStack> var3) {
      return this.apollo$vignetteResource != null && this.apollo$vignetteOpacity != -1.0F
         ? new ItemStack(Item.getItemFromBlock(Blocks.pumpkin))
         : (ItemStack)var3.call(new Object[]{var1, var2});
   }

   @Inject(method = {"renderGameOverlay$v1_8", "renderGameOverlay$v1_7"}, at = @At("TAIL"))
   private void apollo$cleanVignette(CallbackInfo var1) {
      this.apollo$vignetteResource = null;
      this.apollo$vignetteOpacity = -1.0F;
   }

   @Redirect(
      method = {"renderPumpkinOverlay$v1_8", "renderPumpkinBlur$v1_7"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void apollo$renderPumpkinOverlay$texture(TextureManager var1, ResourceLocation var2) {
      var1.bindTexture(this.apollo$vignetteResource != null ? this.apollo$vignetteResource : var2);
   }

   @ModifyConstant(method = {"renderPumpkinOverlay$v1_8", "renderPumpkinBlur$v1_7"}, constant = @Constant(floatValue = 1.0F, ordinal = 3))
   public float apollo$renderPumpkin$opacity(float var1) {
      return this.apollo$vignetteOpacity != -1.0F ? this.apollo$vignetteOpacity : var1;
   }
}

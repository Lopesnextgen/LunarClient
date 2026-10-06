package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.InventoryApolloHandler;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryEffectRenderer.class)
public abstract class InventoryEffectRendererMixin extends GuiContainer {
   @Shadow
   public boolean hasActivePotionEffects;
   @VersionGate(max = 0)
   @Unique
   private boolean lunar$adjustedSearchField;
   @Unique
   private int lunar$inventoryButtonsShift;

   protected InventoryEffectRendererMixin(Container container1) {
      super(container1);
   }

   @Inject(method = "drawScreen", at = @At("RETURN"))
   @VersionGate(max = 0)
   private void lunar$onDrawScreen$v1_7(CallbackInfo callback1) {
      if (this.lunar$adjustGuiLeft() && this instanceof GuiContainerCreative guicontainercreative2 && !this.lunar$adjustedSearchField) {
         guicontainercreative2.searchField.xPosition -= 60;
         this.lunar$adjustedSearchField = true;
      }
   }

   @Inject(method = "updateActivePotionEffects$v1_8", at = @At("RETURN"))
   @VersionGate(min = 1)
   private void lunar$updateActivePotionEffects(CallbackInfo callback1) {
      this.lunar$adjustGuiLeft();
   }

   @Unique
   private boolean lunar$adjustGuiLeft() {
      Collection list1 = Ref.MC_VERSION >= 1 ? this.mc.thePlayer.getActivePotionEffects() : this.mc.thePlayer$v1_7.getActivePotionEffects();
      if (!list1.isEmpty() && (Boolean)Client.method109().method41().method6().method23().get()) {
         this.guiLeft = (this.width - this.xSize) / 2;
         return true;
      } else {
         return false;
      }
   }

   @Inject(method = "drawActivePotionEffects", at = @At("HEAD"), cancellable = true)
   private void impl$drawActivePotionEffects(CallbackInfo callback1) {
      if (Ref.method4().method40().method23().isEnabled() && !(Boolean)Ref.method4().method40().method23().method19().get()) {
         callback1.cancel();
      } else {
         Ref.method4().method84().method3(InventoryModule.class).ifPresent(arg2 -> {
            int number3 = ((InventoryApolloHandler)arg2).method9((GuiContainerBridge)this);
            if (number3 > 0 && this.guiLeft - 124 - number3 < 0) {
               callback1.cancel();
            } else {
               this.lunar$inventoryButtonsShift = number3;
               this.guiLeft -= number3;
            }
         });
      }
   }

   @Inject(method = "drawActivePotionEffects", at = @At("RETURN"))
   private void lunar$restoreEffectsShift(CallbackInfo callback1) {
      this.guiLeft = this.guiLeft + this.lunar$inventoryButtonsShift;
      this.lunar$inventoryButtonsShift = 0;
   }

   @WrapOperation(
      method = "drawActivePotionEffects",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/InventoryEffectRenderer;drawTexturedModalRect(IIIIII)V", ordinal = 0)
   )
   private void lunar$effectDurationBarHook(
      InventoryEffectRenderer inventoryeffectrenderer1, int number2, int number3, int number4, int number5, int number6, int number7, Operation<Void> operation8, @Local PotionEffect effect9
   ) {
      operation8.call(new Object[]{inventoryeffectrenderer1, number2, number3, number4, number5, number6, number7});
      PotionEffects potioneffects10 = Ref.method4().method40().method23();
      if (potioneffects10.isEffectBarEnabledInInventory()) {
         potioneffects10.renderWithContext(AbstractRenderContext.method32(), (PotionEffectBridge)effect9, number2 + 3, number3 + 3, 114.0F, 26.0F);
      }
   }
}

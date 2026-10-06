package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.lunarclient.apollo.module.inventory.InventoryModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ContainerClickType;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.inventorymod.Inventorymod;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.HologramRenderEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent;
import com.moonsworth.lunar.client.event.render.ScreenItemRenderEvent;
import com.moonsworth.lunar.client.event.render.SlotRenderEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;
import com.moonsworth.lunar.client.mod.player.slotbinding.SlotBinding;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Optional;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiContainer.class)
public class GuiContainerSlotEventsMixin extends GuiScreen {
   @Annotation2(max = 1)
   @Shadow
   public Slot hoveredSlot;
   @Shadow
   public Container inventorySlots;
   @Shadow
   public boolean doubleClick;
   @Shadow
   public int guiLeft;
   @Shadow
   public int guiTop;

   @Annotation2(max = 1)
   @WrapMethod(method = "handleMouseClick$v1_7")
   private void lunar$handleMouseClick$v1_7(Slot var1, int var2, int var3, int var4, Operation<Void> var5) {
      SlotRenderEvent var6 = ClientEventBus.method29()
         .method12(SlotRenderEvent.class, () -> new SlotRenderEvent((Bridge5Extension_3)this, (Bridge3_18)var1, var2, var3, ContainerClickType.fromId(var4)));
      if (var6 == null) {
         var5.call(new Object[]{var1, var2, var3, var4});
      } else if (!var6.isCancelled()) {
         if (!var6.isModified()) {
            var5.call(new Object[]{var1, var2, var3, var4});
         } else {
            int var7 = var6.getSlotId();
            if (var7 >= 0) {
               Slot var8 = this.inventorySlots.getSlot(var7);
               var5.call(new Object[]{var8, var7, var6.method6(), var6.method7().toId()});
            }
         }
      }
   }

   @Annotation2(min = 5)
   @WrapMethod(method = "handleMouseClick$v1_12")
   private void lunar$handleMouseClick$v1_12(Slot var1, int var2, int var3, ClickType var4, Operation<Void> var5) {
      SlotRenderEvent var6 = ClientEventBus.method29()
         .method12(SlotRenderEvent.class, () -> new SlotRenderEvent((Bridge5Extension_3)this, (Bridge3_18)var1, var2, var3, ContainerClickType.fromVanilla(var4)));
      if (var6 == null) {
         var5.call(new Object[]{var1, var2, var3, var4});
      } else if (!var6.isCancelled()) {
         if (!var6.isModified()) {
            var5.call(new Object[]{var1, var2, var3, var4});
         } else {
            int var7 = var6.getSlotId();
            if (var7 >= 0) {
               ClickType var8 = var6.method7().toVanilla(ClickType.values());
               Slot var9 = this.inventorySlots.getSlot(var7);
               var5.call(new Object[]{var9, var7, var6.method6(), var8});
            }
         }
      }
   }

   @Inject(
      method = "mouseClicked",
      at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;doubleClick:Z", opcode = 181, shift = Shift.AFTER)
   )
   private void lunar$evalDoubleClick(int var1, int var2, int var3, CallbackInfo var4, @Local Slot var5) {
      if (var5 != null) {
         InventoryMods var6 = ThreadModuleDump63.method4().method40().method93();
         if (var6.isEnabled()) {
            int var7 = ThreadModuleDump63.MC_VERSION >= 1
               ? this.inventorySlots.inventorySlots.indexOf(var5)
               : this.inventorySlots.inventorySlots$v1_7.indexOf(var5);
            int var8 = Inventorymod.method4((Bridge5Extension_3)this, var7);
            if (var8 > 0) {
               SlotLocking var9 = var6.method14();
               SlotBinding var10 = var6.method15();
               if (var9.isEnabled() && var9.method11(var8)) {
                  this.doubleClick = false;
               }

               if (var10.isEnabled() && var10.method1(var8)) {
                  this.doubleClick = false;
               }
            }
         }
      }
   }

   @WrapOperation(method = {"mouseClicked", "mouseReleased"}, at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z"))
   private boolean lunar$isShiftKeyDown(int var1, Operation<Boolean> var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      if (!var3.method19()) {
         return (Boolean)var2.call(new Object[]{var1});
      }

      Nameplate2 var4 = var3.method35().method42().get().method8();
      return var4.method10();
   }

   @ModifyExpressionValue(method = "drawSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;"))
   private ItemStack lunar$fireEventRenderSlotPre(ItemStack var1, @Local(argsOnly = true) Slot var2) {
      BridgeExtension3_5 var3 = AbstractRenderContext.method32();
      var3.translate(-this.guiLeft, -this.guiTop, 0.0);
      HologramRenderEvent.HologramItemRenderEvent var4 = ClientEventBus.method29()
         .method12(HologramRenderEvent.HologramItemRenderEvent.class, () -> new HologramRenderEvent.HologramItemRenderEvent(new LegacyGuiGraphicsBridge(var3), (Bridge5Extension_3)this, (Bridge3_18)var2));
      var3.translate(this.guiLeft, this.guiTop, 0.0);
      return var4 != null && var4.method4() != null ? (ItemStack)var4.method4() : var1;
   }

   @Inject(method = "drawSlot", at = @At("RETURN"))
   private void lunar$fireEventRenderSlotPost(Slot var1, CallbackInfo var2) {
      BridgeExtension3_5 var3 = AbstractRenderContext.method32();
      var3.translate(-this.guiLeft, -this.guiTop, 0.0);
      ClientEventBus.method29()
         .method12(HologramRenderEvent.HologramTextRenderEvent.class, () -> new HologramRenderEvent.HologramTextRenderEvent(new LegacyGuiGraphicsBridge(var3), (Bridge5Extension_3)this, (Bridge3_18)var1));
      var3.translate(this.guiLeft, this.guiTop, 0.0);
   }

   @Annotation2(min = 5)
   @ModifyExpressionValue(
      method = "renderHoveredToolTip$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;")
   )
   private ItemStack lunar$fireEventContainerItemTooltip$v1_12(ItemStack var1) {
      return this.lunar$fireEventContainerItemTooltip(var1);
   }

   @Annotation2(max = 1)
   @ModifyExpressionValue(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;"))
   private ItemStack lunar$fireEventContainerItemTooltip$v1_7(ItemStack var1) {
      return this.lunar$fireEventContainerItemTooltip(var1);
   }

   @Unique
   private ItemStack lunar$fireEventContainerItemTooltip(ItemStack var1) {
      ScreenItemRenderEvent var2 = ClientEventBus.method29()
         .method12(ScreenItemRenderEvent.class, () -> new ScreenItemRenderEvent((Bridge5Extension_3)this, (Bridge3_18)this.hoveredSlot));
      return var2 != null && var2.method3() != null ? (ItemStack)var2.method3() : var1;
   }

   @Annotation2(max = 1)
   @Inject(method = "handleMouseClick$v1_7", at = @At("HEAD"), cancellable = true)
   private void apollo$inventoryModule$v1_7(Slot var1, int var2, int var3, int var4, CallbackInfo var5) {
      if (this.apollo$handleInventoryClick(var1)) {
         var5.cancel();
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "handleMouseClick$v1_12", at = @At("HEAD"), cancellable = true)
   private void apollo$inventoryModule$v1_12(Slot var1, int var2, int var3, ClickType var4, CallbackInfo var5) {
      if (this.apollo$handleInventoryClick(var1)) {
         var5.cancel();
      }
   }

   @Unique
   private boolean apollo$handleInventoryClick(Slot var1) {
      ApolloModuleManager var2 = ThreadModuleDump63.method4().method84();
      Optional var3 = var2.method3(InventoryModule.class);
      return var3.<Boolean>map(var1x -> var1x.method14((Bridge3_18)var1)).orElse(false);
   }

   @WrapWithCondition(
      method = "drawScreen(IIF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGradientRect(IIIIII)V")
   )
   private boolean lunar$onSlotHighlight(GuiContainer var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      return !this.apollo$handleSlotHightlight(this.hoveredSlot);
   }

   @Unique
   private boolean apollo$handleSlotHightlight(Slot var1) {
      ApolloModuleManager var2 = ThreadModuleDump63.method4().method84();
      Optional var3 = var2.method3(InventoryModule.class);
      return var3.<Boolean>map(var1x -> var1x.method12((Bridge3_18)var1)).orElse(false);
   }

   @Annotation2(max = 1)
   @WrapWithCondition(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;renderToolTip(Lnet/minecraft/item/ItemStack;II)V")
   )
   @Dynamic
   private boolean lunar$onHoveredTooltipRender$v1_7(GuiContainer var1, ItemStack var2, int var3, int var4) {
      return !this.apollo$handleTooltipRender(this.hoveredSlot);
   }

   @Annotation2(min = 5)
   @Inject(method = "renderHoveredToolTip$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$onHoveredTooltipRender$v1_12(int var1, int var2, CallbackInfo var3) {
      if (this.apollo$handleTooltipRender(this.hoveredSlot)) {
         var3.cancel();
      }
   }

   @Unique
   private boolean apollo$handleTooltipRender(Slot var1) {
      ApolloModuleManager var2 = ThreadModuleDump63.method4().method84();
      Optional var3 = var2.method3(InventoryModule.class);
      return var3.<Boolean>map(var1x -> var1x.method13((Bridge3_18)var1)).orElse(false);
   }

   @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V", shift = Shift.AFTER))
   private void lunar$renderGuiBeforeItems(int var1, int var2, float var3, CallbackInfo var4) {
      ClientEventBus.method29().method12(ContainerSlotRenderEvent.Data.class, () -> {
         BridgeExtension3_5 var4x = AbstractRenderContext.method32();
         LegacyGuiGraphicsBridge var5 = new LegacyGuiGraphicsBridge(var4x);
         return new ContainerSlotRenderEvent.Data(new MarkerModel.Data4(var1, var2), var3, (Bridge5Extension6)this, var4x, var5);
      });
   }

   @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerForegroundLayer(II)V"))
   private void lunar$renderGuiAfterItems(int var1, int var2, float var3, CallbackInfo var4) {
      BridgeExtension3_5 var5 = AbstractRenderContext.method32();
      var5.translate(-this.guiLeft, -this.guiTop, 0.0);
      ClientEventBus.method29().method12(ContainerSlotRenderEvent.ContainerSlotAfterItemsEvent.class, () -> {
         LegacyGuiGraphicsBridge var5x = new LegacyGuiGraphicsBridge(var5);
         return new ContainerSlotRenderEvent.ContainerSlotAfterItemsEvent(new MarkerModel.Data4(var1, var2), var3, (Bridge5Extension6)this, var5, var5x);
      });
      var5.translate(this.guiLeft, this.guiTop, 0.0);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         Bridge.method14().method3();
      } else {
         Bridge.method14().method2();
      }

      var5.method18();
      var5.method18(-1);
   }

   @Inject(method = "checkHotbarKeys", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelHotbarKeys(int var1, CallbackInfoReturnable<Boolean> var2) {
      if (!GuiRewindhandlersHandler2.field7.method8()) {
         var2.cancel();
      }
   }

   @WrapOperation(
      method = "drawScreen(IIF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerBackgroundLayer(FII)V")
   )
   private void lunar$tintContainerBg(GuiContainer var1, float var2, int var3, int var4, Operation<Void> var5) {
      OverlayMod var6 = ThreadModuleDump63.method4().method40().method84();
      if (!var6.isContainerTintEnabled()) {
         var5.call(new Object[]{var1, var2, var3, var4});
      } else {
         Gui2.method1(var6.getContainerTint());

         try {
            var5.call(new Object[]{var1, var2, var3, var4});
         } finally {
            Gui2.method2();
         }
      }
   }

   @WrapOperation(
      method = "drawScreen(IIF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerForegroundLayer(II)V")
   )
   private void lunar$applyContainerTextColor(GuiContainer var1, int var2, int var3, Operation<Void> var4) {
      OverlayMod var5 = ThreadModuleDump63.method4().method40().method84();
      if (!var5.isCustomGuiColorsEnabled()) {
         var4.call(new Object[]{var1, var2, var3});
      } else {
         Gui2.method9(var5.getGuiTextColor());

         try {
            var4.call(new Object[]{var1, var2, var3});
         } finally {
            Gui2.method10();
         }
      }
   }
}

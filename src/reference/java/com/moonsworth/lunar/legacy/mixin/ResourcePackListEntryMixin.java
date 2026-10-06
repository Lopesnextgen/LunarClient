package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.GuiScreenResourcePacksBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.ResourcePackListEntryImpl;
import com.moonsworth.lunar.legacy.wrapper.ResourcePackListEntryIterator;
import java.util.List;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ResourcePackListEntry.class)
public abstract class ResourcePackListEntryMixin {
   @Final
   @Shadow
   public GuiScreenResourcePacks resourcePacksGUI;

   public ResourcePackListEntryMixin() {
   }

   @Shadow
   public abstract int getResourcePackFormat$v1_12();

   @Shadow
   public abstract int func_183019_a();

   @Redirect(method = "mousePressed", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", ordinal = 1))
   private void impl$onAddAvailablePack(List<ResourcePackListEntry> list1, int number2, Object obj3) {
      int index4;
      for (index4 = 0; index4 < list1.size(); index4++) {
         ResourcePackListEntry resourcepacklistentry5 = (ResourcePackListEntry)list1.get(index4);
         if (!(resourcepacklistentry5 instanceof ResourcePackListEntryIterator) && !(resourcepacklistentry5 instanceof ResourcePackListEntryImpl)) {
            break;
         }
      }

      list1.add(index4, (ResourcePackListEntry)obj3);
   }

   @Inject(method = "mousePressed", at = @At("RETURN"))
   private void impl$onMousePressed(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      ((GuiScreenResourcePacksBridge)this.resourcePacksGUI).bridge$handlePackSwapList();
   }

   @VersionGate(0)
   @Inject(
      method = "drawEntry$v1_7",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 1, shift = Shift.AFTER),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 2, shift = Shift.AFTER)
      },
      cancellable = true
   )
   private void lunar$draw$v1_7(int number1, int number2, int number3, int number4, int number5, Tessellator tessellator6, int number7, int number8, boolean flag9, CallbackInfo callback10) {
      this.lunar$renderPreview(callback10, number2, number3, number4, number5);
   }

   @VersionGate(1)
   @Inject(
      method = "drawEntry$v1_8",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 1, shift = Shift.AFTER),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 2, shift = Shift.AFTER)
      },
      cancellable = true
   )
   private void lunar$draw$v1_8(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, CallbackInfo callback9) {
      if (this.func_183019_a() == 1) {
         this.lunar$renderPreview(callback9, number2, number3, number4, number5);
      }
   }

   @VersionGate(5)
   @Inject(
      method = "drawEntry$v1_12",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 1, shift = Shift.AFTER),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;drawModalRectWithCustomSizedTexture(IIFFIIFF)V", ordinal = 2, shift = Shift.AFTER)
      },
      cancellable = true
   )
   private void lunar$draw$v1_12(int number1, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8, float value9, CallbackInfo callback10) {
      if (this.getResourcePackFormat$v1_12() == 3) {
         this.lunar$renderPreview(callback10, number2, number3, number4, number5);
      }
   }

   @Unique
   private void lunar$renderPreview(CallbackInfo callback1, int number2, int number3, int number4, int number5) {
      if ((Boolean)Client.method109().method41().method6().method29().get()) {
         if (this instanceof ResourcePackListEntryFound resourcepacklistentryfound6) {
            ResourcePackBridge bridge148 = Ref.MC_VERSION == 5
               ? (ResourcePackBridge)resourcepacklistentryfound6.getResourcePackEntry$v1_12().getResourcePack()
               : (ResourcePackBridge)resourcepacklistentryfound6.func_148318_i().getResourcePack();
            Client.method109().method85().method1(bridge148).ifPresent(arg5x -> {
               callback1.cancel();
               Client.method109().method85().method2(AbstractRenderContext.method32().method42(), arg5x, number2, number3, number4, number5);
            });
         }
      }
   }
}

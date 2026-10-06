package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventRenderEntityItem;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderDroppedItem;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemRotation;
import com.moonsworth.lunar.client.event.mixin.highlight.EventGroundItemTransform;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemClump;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@MixinCondition(absent = "forge", available = @VersionGate(min = 5))
@Mixin(RenderEntityItem.class)
public abstract class RenderEntityItemMixin {
   @Unique
   private float lunar$clumpSpread = 0.15F;

   public RenderEntityItemMixin() {
   }

   @Redirect(
      method = {"func_177077_a$v1_8", "transformModelCount$v1_12"},
      slice = @Slice(to = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.rotate (FFFF)V")),
      at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.translate (FFF)V")
   )
   private void lunar$onInitialTranslate(
      float value1, float value2, float value3, EntityItem entityitem4, double value5, double value7, double value9, float value11, IBakedModel ibakedmodel12
   ) {
      float value13 = (float)(value2 - value7);
      EventRenderDroppedItem highlightimpl14 = (EventRenderDroppedItem)LunarEventBus.method29().method12(EventRenderDroppedItem.class, () -> new EventRenderDroppedItem((EntityItemBridge)entityitem4, value13));
      if (highlightimpl14 != null && highlightimpl14.method2() != value13) {
         GlStateManager.translate(value1, value7 + highlightimpl14.method2(), value3);
      } else {
         GlStateManager.translate(value1, value2, value3);
      }
   }

   @Redirect(
      method = {"func_177077_a$v1_8", "transformModelCount$v1_12"},
      at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/GlStateManager.rotate(FFFF)V")
   )
   private void lunar$onRotate(
      float value1, float value2, float value3, float value4, EntityItem entityitem5, double value6, double value8, double value10, float value12, IBakedModel ibakedmodel13
   ) {
      EventRenderItemRotation highlightimpl1614 = (EventRenderItemRotation)LunarEventBus.method29()
         .method12(EventRenderItemRotation.class, () -> new EventRenderItemRotation(AbstractRenderContext.method32(), (EntityItemBridge)entityitem5, value12));
      if (highlightimpl1614 == null || !highlightimpl1614.isCancelled()) {
         GlStateManager.rotate(value1, value2, value3, value4);
      }
   }

   @Redirect(method = "doRender*", at = @At(value = "INVOKE", target = "java/util/Random.setSeed(J)V", remap = false))
   private void lunar$onSetSeed(Random random1, long number2, EntityItem entityitem4, double value5, double value7, double value9, float value11, float value12) {
      EventRenderItemClump highlightimpl913 = (EventRenderItemClump)LunarEventBus.method29()
         .method12(EventRenderItemClump.class, () -> new EventRenderItemClump((EntityItemBridge)entityitem4, number2, 0.2F));
      if (highlightimpl913 == null) {
         random1.setSeed(number2);
         this.lunar$clumpSpread = 0.2F;
      } else {
         random1.setSeed(highlightimpl913.method2());
         this.lunar$clumpSpread = highlightimpl913.method3();
      }
   }

   @ModifyConstant(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", constant = @Constant(floatValue = 0.15F))
   private float lunar$tweakClumping(float value1) {
      return this.lunar$clumpSpread;
   }

   @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At("HEAD"), cancellable = true)
   private void lunar$onRender(EntityItem entityitem1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10) {
      EventRenderEntityItem highlightimpl411 = (EventRenderEntityItem)LunarEventBus.method29()
         .method12(EventRenderEntityItem.class, () -> new EventRenderEntityItem(AbstractRenderContext.method32(), null, (EntityItemBridge)entityitem1, value2, value4, value6, false, value9));
      if (highlightimpl411 != null && highlightimpl411.isCancelled()) {
         callback10.cancel();
      }
   }

   @VersionGate(min = 5)
   @ModifyVariable(
      method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"
      )
   )
   private IBakedModel lunar$onRenderScale(IBakedModel ibakedmodel1, EntityItem entityitem2, double value3, double value5, double value7, float value9, float value10) {
      ItemStack stack11 = entityitem2.getItem();
      LunarEventBus.method29().method12(EventGroundItemTransform.class, () -> new EventGroundItemTransform(AbstractRenderContext.method32(), (ItemStackBridge)stack11, false, false));
      if (Client.method109().method40().method30().isEnabled()) {
         GlStateManager.translate(0.0, -ibakedmodel1.getItemCameraTransforms().ground.enumString.y, 0.0);
      }

      return ibakedmodel1;
   }
}

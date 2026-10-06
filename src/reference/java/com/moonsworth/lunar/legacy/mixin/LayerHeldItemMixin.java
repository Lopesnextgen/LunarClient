package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.Bridge3_26;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.RenderEntityItemLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(LayerHeldItem.class)
public abstract class LayerHeldItemMixin implements Bridge3_26 {
   @Annotation2(max = 1)
   @Inject(
      method = "doRenderLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isSneaking()Z")
   )
   private void impl$onDoRenderLayer(
      EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, CallbackInfo var9
   ) {
      ItemStack var10 = var1.getHeldItem();
      if (var10 != null) {
         this.lunar$fireEvent(var1, var10);
      }
   }

   @Annotation2(min = 5)
   @Inject(method = "renderHeldItem$v1_12", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isSneaking()Z"))
   private void impl$onDoRenderLayer(EntityLivingBase var1, ItemStack var2, TransformType var3, EnumHandSide var4, CallbackInfo var5) {
      this.lunar$fireEvent(var1, var2);
   }

   @Unique
   private void lunar$fireEvent(EntityLivingBase var1, ItemStack var2) {
      ClientEventBus.method29()
         .method12(RenderEntityItemLegacyEvent.class, () -> new RenderEntityItemLegacyEvent((BridgeExtension2_5)var1, (ItemStackBridge)var2, AbstractRenderContext.method32()));
   }

   @Annotation2(1)
   @Inject(method = "doRenderLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER))
   private void lunar$doRenderLayerPre(CallbackInfo var1, @Local(argsOnly = true) EntityLivingBase var2) {
      if (var2 instanceof Bridge6_10 var3) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  var3,
                  GlintTransformEvent.Type.BEFORE_TRANSFORMS,
                  (ItemStackBridge)var2.getHeldItem(),
                  ItemTransformsBridge.Type.THIRD_PERSON_RIGHT_HAND,
                  AbstractRenderContext.method32()
               )
            );
      }
   }

   @Annotation2(1)
   @WrapOperation(
      method = "doRenderLayer",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"
      )
   )
   private void lunar$doRenderLayerPost(ItemRenderer var1, EntityLivingBase var2, ItemStack var3, TransformType var4, Operation<Void> var5) {
      if (var2 instanceof Bridge6_10 var6) {
         ClientEventBus.method29().method12(GlintTransformEvent.class, () -> {
            ItemTransformsBridge.Type var3x = switch (var4) {
               case NONE -> ItemTransformsBridge.Type.NONE;
               case THIRD_PERSON -> ItemTransformsBridge.Type.THIRD_PERSON_RIGHT_HAND;
               case FIRST_PERSON -> ItemTransformsBridge.Type.FIRST_PERSON_RIGHT_HAND;
               case HEAD -> ItemTransformsBridge.Type.HEAD;
               case GUI -> ItemTransformsBridge.Type.GUI;
               case ground -> ItemTransformsBridge.Type.GROUND;
               case FIXED -> ItemTransformsBridge.Type.FIXED;
               default -> throw new IllegalStateException("Unexpected value: " + var4);
            };
            return new GlintTransformEvent(var6, GlintTransformEvent.Type.AFTER_TRANSFORMS, (ItemStackBridge)var3, var3x, AbstractRenderContext.method32());
         });
      }

      var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Annotation2(5)
   @Inject(
      method = "renderHeldItem$v1_12",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = Shift.AFTER)
   )
   private void lunar$renderHeldItemPre(EntityLivingBase var1, ItemStack var2, TransformType var3, EnumHandSide var4, CallbackInfo var5) {
      if (var1 instanceof Bridge6_10 var6) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  var6, GlintTransformEvent.Type.BEFORE_TRANSFORMS, (ItemStackBridge)var2, ItemTransformsBridge.Type.VALUES[var3.ordinal()], AbstractRenderContext.method32()
               )
            );
      }
   }

   @Annotation2(5)
   @WrapOperation(
      method = "renderHeldItem$v1_12",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemSide$v1_12(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;Z)V"
      )
   )
   private void lunar$renderHeldItemPost(ItemRenderer var1, EntityLivingBase var2, ItemStack var3, TransformType var4, boolean var5, Operation<Void> var6) {
      if (var2 instanceof Bridge6_10 var7) {
         ClientEventBus.method29()
            .method12(
               GlintTransformEvent.class,
               () -> new GlintTransformEvent(
                  var7, GlintTransformEvent.Type.AFTER_TRANSFORMS, (ItemStackBridge)var3, ItemTransformsBridge.Type.VALUES[var4.ordinal()], AbstractRenderContext.method32()
               )
            );
      }

      var6.call(new Object[]{var1, var2, var3, var4, var5});
   }
}

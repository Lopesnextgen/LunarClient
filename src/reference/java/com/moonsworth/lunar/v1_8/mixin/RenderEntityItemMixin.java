package com.moonsworth.lunar.v1_8.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventGroundItemTransform;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RenderEntityItem.class)
public class RenderEntityItemMixin {
   public RenderEntityItemMixin() {
   }

   @Inject(
      method = "doRender",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/client/ForgeHooksClient;handleCameraTransforms(Lnet/minecraft/client/resources/model/IBakedModel;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)Lnet/minecraft/client/resources/model/IBakedModel;"
      ),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   @Group(name = "lunar.RenderEntityItem.doRender", max = 1)
   private void lunar$onRenderScale$forge(
      EntityItem entityitem1, double value2, double value4, double value6, float value8, float value9, CallbackInfo callback10, ItemStack stack11, boolean flag12, IBakedModel ibakedmodel13
   ) {
      LunarEventBus.method29()
         .method12(EventGroundItemTransform.class, () -> new EventGroundItemTransform(AbstractRenderContext.method32(), (ItemStackBridge)entityitem1.getEntityItem(), true, false));
      if (Client.method109().method40().method30().isEnabled()) {
         GlStateManager.translate(0.0, -ibakedmodel13.getItemCameraTransforms().ground.enumString.y, 0.0);
      }
   }
}

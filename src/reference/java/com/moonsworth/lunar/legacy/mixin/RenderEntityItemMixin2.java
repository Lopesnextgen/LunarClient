package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.GroundItemTransformEvent;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Annotation2(1)
@Annotation_2(absent = "forge")
@Mixin(RenderEntityItem.class)
public class RenderEntityItemMixin2 {
   @ModifyVariable(
      method = "doRender",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"
      )
   )
   private IBakedModel lunar$postEventGroundItemTransform(IBakedModel var1, EntityItem var2, double var3, double var5, double var7, float var9, float var10) {
      ItemStack var11 = var2.getEntityItem();
      ClientEventBus.method29().method12(GroundItemTransformEvent.class, () -> new GroundItemTransformEvent(AbstractRenderContext.method32(), (ItemStackBridge)var11, true, false));
      if (Client.method109().method40().method30().isEnabled()) {
         GlStateManager.translate(0.0, -var1.getItemCameraTransforms().ground.enumString.y, 0.0);
      }

      return var1;
   }
}

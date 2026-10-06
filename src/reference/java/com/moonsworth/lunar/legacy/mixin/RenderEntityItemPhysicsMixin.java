package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.EventGroundItemTransform;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@VersionGate(1)
@MixinCondition(absent = "forge")
@Mixin(RenderEntityItem.class)
public class RenderEntityItemPhysicsMixin {
   public RenderEntityItemPhysicsMixin() {
   }

   @ModifyVariable(
      method = "doRender",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms;applyTransform(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V"
      )
   )
   private IBakedModel lunar$postEventGroundItemTransform(IBakedModel ibakedmodel1, EntityItem entityitem2, double value3, double value5, double value7, float value9, float value10) {
      ItemStack stack11 = entityitem2.getEntityItem();
      LunarEventBus.method29().method12(EventGroundItemTransform.class, () -> new EventGroundItemTransform(AbstractRenderContext.method32(), (ItemStackBridge)stack11, true, false));
      if (Client.method109().method40().method30().isEnabled()) {
         GlStateManager.translate(0.0, -ibakedmodel1.getItemCameraTransforms().ground.enumString.y, 0.0);
      }

      return ibakedmodel1;
   }
}

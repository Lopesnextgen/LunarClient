package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.optifine.v1_7.ItemRendererOF;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRendererOF.class)
public abstract class ItemRendererOFMixin {
   @Redirect(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEffect()Z"))
   public boolean impl$renderItem$hasEffect(ItemStack var1) {
      if (!var1.hasEffect()) {
         return false;
      }

      ItemGlintRenderEvent.Type var2 = ItemGlintRenderEvent.Type.ITEM;
      ItemGlintRenderEvent var3 = ClientEventBus.method29().method12(ItemGlintRenderEvent.class, () -> new ItemGlintRenderEvent(var2, var0x -> {
         float var1xx = (var0x >> 24 & 0xFF) / 255.0F;
         float var2x = (var0x >> 16 & 0xFF) / 255.0F;
         float var3x = (var0x >> 8 & 0xFF) / 255.0F;
         float var4 = (var0x & 0xFF) / 255.0F;
         GL11.glColor4f(var2x, var3x, var4, var1xx);
         ItemRenderer.renderItemIn2D(Tessellator.theMinecraft, 0.0F, 0.0F, 1.0F, 1.0F, 16, 16, 0.0625F);
      }, null, null, (ItemStackBridge)var1, AbstractRenderContext.method32()));
      return var3 == null || !var3.isCancelled();
   }

   @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
   private void lunar$renderGeckolibItem$v1_7(EntityLivingBase var1, ItemStack var2, int var3, CallbackInfo var4) {
      if (var1 instanceof EntityPlayerBridge var5) {
         if (PlayerModelPartMap.method39(var5, (ItemStackRenderStateBridge)var2)) {
            var4.cancel();
         }
      }
   }
}

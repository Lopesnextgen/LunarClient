package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.MixinHelper_6;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer_v1_7;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(0)
@Mixin(RenderPlayer_v1_7.class)
public abstract class RenderPlayer_v1_7Mixin2 extends RendererLivingEntity implements MixinHelper_6 {
   @Shadow
   public ModelBiped modelBipedMain;

   @Shadow
   public abstract void renderEquippedItems(AbstractClientPlayer var1, float var2);

   @Override
   public BridgeExtension2_7 bridge$getMainModel() {
      return (BridgeExtension2_7)this.modelBipedMain;
   }

   @Override
   public void bridge$renderEquippedItems(Bridge5_11 var1, float var2) {
      this.renderEquippedItems((AbstractClientPlayer)var1, var2);
   }
}

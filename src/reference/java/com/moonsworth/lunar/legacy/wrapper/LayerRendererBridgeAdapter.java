package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.LegacyLayerRendererBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LayerRendererBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

@VersionGate(min = 1)
public class LayerRendererBridgeAdapter<E extends EntityLivingBridge, S extends EntityLivingStateBridge>
   implements LegacyLayerRendererBridge<EntityLivingBridge>,
   LayerRenderer<net.minecraft.entity.EntityLivingBase> {
   private final RenderPlayer field1;
   private final LayerRendererBridge<E, S> field2;

   public LayerRendererBridgeAdapter(LayerRendererBridge<E, S> mextension1, RenderPlayer renderplayer2) {
      this.field2 = mextension1;
      this.field1 = renderplayer2;
   }

   public void doRenderLayer(net.minecraft.entity.EntityLivingBase entity1, float value2, float value3, float value4, float value5, float value6, float value7, float value8) {
      this.method1(AbstractRenderContext.method32(), (EntityLivingBridge)entity1, value2, value3, value4, value5, value6, value7, value8);
   }

   public boolean shouldCombineTextures() {
      return this.field2.method7();
   }

   public void method1(BridgeExtension3_5 bridgeextension3_51, EntityLivingBridge bridgeextension2_52, float value3, float value4, float value5, float value6, float value7, float value8, float value9) {
      if (this.field2.method9((EntityPlayerBridge)bridgeextension2_52)) {
         this.field2.method1(bridgeextension3_51, bridgeextension2_52, (ModelPlayerBridge)this.field1.getMainModel(), value3, value4, value5, value6, value7, value8, value9);
      }
   }
}

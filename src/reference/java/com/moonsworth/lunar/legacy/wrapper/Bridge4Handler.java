package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge4_9;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_2;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

@Annotation2(min = 1)
public class Bridge4Handler<E extends BridgeExtension2_5, S extends BridgeExtension2_2>
   implements Bridge4_9<BridgeExtension2_5>,
   LayerRenderer<net.minecraft.entity.EntityLivingBase> {
   private final RenderPlayer field1;
   private final MExtension<E, S> field2;

   public Bridge4Handler(MExtension<E, S> var1, RenderPlayer var2) {
      this.field2 = var1;
      this.field1 = var2;
   }

   public void doRenderLayer(net.minecraft.entity.EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.method1(AbstractRenderContext.method32(), (BridgeExtension2_5)var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public boolean shouldCombineTextures() {
      return this.field2.method7();
   }

   public void method1(BridgeExtension3_5 var1, BridgeExtension2_5 var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      if (this.field2.method9((EntityPlayerBridge)var2)) {
         this.field2.method1(var1, var2, (BridgeExtension2_7)this.field1.getMainModel(), var3, var4, var5, var6, var7, var8, var9);
      }
   }
}

package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.LivingEntityRendererBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.util.ResourceLocation;

@Annotation2(min = 1)
public class RendererLivingEntityBrightnessAdapter extends RendererLivingEntity<net.minecraft.entity.EntityLivingBase> implements LivingEntityRendererBridge {
   @Annotation2(min = 1)
   public RendererLivingEntityBrightnessAdapter(RenderManager var1, net.minecraft.client.model.ModelBase var2, float var3) {
      super(var1, var2, var3);
   }

   public int getColorMultiplier(net.minecraft.entity.EntityLivingBase var1, float var2, float var3) {
      return 0;
   }

   public ResourceLocation method1(net.minecraft.entity.EntityLivingBase var1) {
      return null;
   }

   public boolean method1(BridgeExtension2_5 var1) {
      return this.canRenderName((net.minecraft.entity.EntityLivingBase)var1);
   }

   public boolean method2(BridgeExtension2_5 var1, float var2) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? super.setBrightness((net.minecraft.entity.EntityLivingBase)var1, var2, true) : false;
   }

   public void method3() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         super.unsetBrightness();
      }
   }

   public void method4() {
   }
}

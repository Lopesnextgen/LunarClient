package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RendererLivingEntityBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.util.ResourceLocation;

@VersionGate(min = 1)
public class RendererLivingEntityBrightnessImpl extends RendererLivingEntity<net.minecraft.entity.EntityLivingBase> implements RendererLivingEntityBridge {
   @VersionGate(min = 1)
   public RendererLivingEntityBrightnessImpl(RenderManager rendermanager1, net.minecraft.client.model.ModelBase modelbase2, float value3) {
      super(rendermanager1, modelbase2, value3);
   }

   public int getColorMultiplier(net.minecraft.entity.EntityLivingBase entity1, float value2, float value3) {
      return 0;
   }

   public ResourceLocation method1(net.minecraft.entity.EntityLivingBase entity1) {
      return null;
   }

   public boolean method1(EntityLivingBridge bridgeextension2_51) {
      return this.canRenderName((net.minecraft.entity.EntityLivingBase)bridgeextension2_51);
   }

   public boolean method2(EntityLivingBridge bridgeextension2_51, float value2) {
      return Ref.MC_VERSION >= 1 ? super.setBrightness((net.minecraft.entity.EntityLivingBase)bridgeextension2_51, value2, true) : false;
   }

   public void method3() {
      if (Ref.MC_VERSION >= 1) {
         super.unsetBrightness();
      }
   }

   public void method4() {
   }
}

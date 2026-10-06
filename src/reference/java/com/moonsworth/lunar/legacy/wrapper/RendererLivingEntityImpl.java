package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.RendererLivingEntityBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@VersionGate(0)
public class RendererLivingEntityImpl extends RendererLivingEntity implements RendererLivingEntityBridge {
   public RendererLivingEntityImpl(net.minecraft.client.model.ModelBase modelbase1, float value2) {
      super(modelbase1, value2);
   }

   public int getColorMultiplier(net.minecraft.entity.EntityLivingBase entity1, float value2, float value3) {
      return 0;
   }

   public boolean method1(EntityLivingBridge bridgeextension2_51) {
      return this.canRenderName((net.minecraft.entity.EntityLivingBase)bridgeextension2_51);
   }

   public boolean method2(EntityLivingBridge bridgeextension2_51, float value2) {
      return false;
   }

   public void method3() {
   }

   public void method4() {
   }

   public void doRender(Entity entity1, double value2, double value4, double value6, float value8, float value9) {
   }

   public ResourceLocation getEntityTexture(Entity entity1) {
      return null;
   }
}

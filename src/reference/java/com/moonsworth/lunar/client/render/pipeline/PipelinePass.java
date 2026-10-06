package com.moonsworth.lunar.client.render.pipeline;

import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeLookup;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.BiConsumer;
import lombok.Generated;

public class PipelinePass {
   private final ResourceLocationBridge texture;
   private final BiConsumer<Boolean, VertexConsumerBridge> drawCallback;
   private RenderTypeLookup renderType = LunarRenderTypes.field9;
   private boolean enabled = true;

   @Generated
   public PipelinePass(ResourceLocationBridge horsestats141, BiConsumer<Boolean, VertexConsumerBridge> biconsumer2, RenderTypeLookup mixinhelper6_33, boolean flag4) {
      this.texture = horsestats141;
      this.drawCallback = biconsumer2;
      this.renderType = mixinhelper6_33;
      this.enabled = flag4;
   }

   @Generated
   public ResourceLocationBridge getTexture() {
      return this.texture;
   }

   @Generated
   public BiConsumer<Boolean, VertexConsumerBridge> getDrawCallback() {
      return this.drawCallback;
   }

   @Generated
   public RenderTypeLookup getRenderType() {
      return this.renderType;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public void setRenderType(RenderTypeLookup mixinhelper6_31) {
      this.renderType = mixinhelper6_31;
   }

   @Generated
   public void setEnabled(boolean flag1) {
      this.enabled = flag1;
   }

   @Generated
   public PipelinePass(ResourceLocationBridge horsestats141, BiConsumer<Boolean, VertexConsumerBridge> biconsumer2) {
      this.texture = horsestats141;
      this.drawCallback = biconsumer2;
   }
}

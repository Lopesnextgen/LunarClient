package com.moonsworth.lunar.client.util.click;

import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.BiConsumer;
import lombok.Generated;

public class Click {
   private final ResourceLocationBridge texture;
   private final BiConsumer<Boolean, Bridge4_6> consumer;
   private RenderTypeResolver renderType = LunarRenderTypes.field9;
   private boolean enabled = true;

   @Generated
   public Click(ResourceLocationBridge var1, BiConsumer<Boolean, Bridge4_6> var2, RenderTypeResolver renderTypeResolver, boolean flag) {
      this.texture = var1;
      this.consumer = var2;
      this.renderType = renderTypeResolver;
      this.enabled = flag;
   }

   @Generated
   public ResourceLocationBridge getTexture() {
      return this.texture;
   }

   @Generated
   public BiConsumer<Boolean, Bridge4_6> getConsumer() {
      return this.consumer;
   }

   @Generated
   public RenderTypeResolver getRenderType() {
      return this.renderType;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public void setRenderType(RenderTypeResolver var1) {
      this.renderType = var1;
   }

   @Generated
   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   @Generated
   public Click(ResourceLocationBridge var1, BiConsumer<Boolean, Bridge4_6> var2) {
      this.texture = var1;
      this.consumer = var2;
   }
}

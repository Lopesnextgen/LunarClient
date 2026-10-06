package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.mixin.ClickEventBridge;
import net.minecraft.event.ClickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClickEvent.class)
public abstract class ClickEventMixin implements ClickEventBridge {
   public ClickEventMixin() {
   }

   @Shadow
   public abstract String getValue();

   public String bridge$getValue() {
      return this.getValue();
   }
}

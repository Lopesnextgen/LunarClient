package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.mixin.MixinHelper;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HoverEvent.class)
public abstract class HoverEventMixin implements MixinHelper {
   @Shadow
   public abstract IChatComponent getValue();

   @Shadow
   public abstract IChatComponent getValue();

   @Override
   public Bridge2_42 bridge$getValue() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge2_42)this.getValue() : (Bridge2_42)this.getValue();
   }
}

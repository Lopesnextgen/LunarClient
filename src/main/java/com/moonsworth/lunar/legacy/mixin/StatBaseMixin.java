package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.stats.StatBaseBridge;
import net.minecraft.stats.StatBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StatBase.class)
public class StatBaseMixin implements StatBaseBridge {
   public StatBaseMixin() {
   }
}

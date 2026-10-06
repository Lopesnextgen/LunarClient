package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.DamageSourceBridge;
import java.util.Objects;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin implements DamageSourceBridge {
   @Shadow
   public String damageType;

   public DamageSourceMixin() {
   }

   public boolean bridge$isGeneric() {
      return this == DamageSource.generic;
   }

   public boolean bridge$isPlayerAttack() {
      return Objects.equals(this.damageType, "player");
   }
}

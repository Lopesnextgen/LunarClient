package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Potion.class)
public abstract class PotionMixin implements PotionBridge {
   @Final
   @Shadow
   public boolean isBadEffect;
   @Final
   @Shadow
   public int id;

   public PotionMixin() {
   }

   @Shadow
   public abstract boolean hasStatusIcon();

   @Shadow
   public abstract int getStatusIconIndex();

   public boolean bridge$isBadEffect() {
      return this.isBadEffect;
   }

   public boolean bridge$hasStatusIcon() {
      return this.hasStatusIcon();
   }

   public int bridge$getStatusIconIndex() {
      return this.getStatusIconIndex();
   }

   public int bridge$getID() {
      return Ref.MC_VERSION == 5 ? Potion.getIdFromPotion$v1_12((Potion)this) : this.id;
   }
}

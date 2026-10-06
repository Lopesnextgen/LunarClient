package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@VersionGate(min = 1)
@Mixin(LayerArmorBase.class)
public abstract class LayerArmorBaseHitColorMixin {
   public LayerArmorBaseHitColorMixin() {
   }

   @Overwrite
   public boolean shouldCombineTextures() {
      HitColor hitcolor1 = Client.method109().method40().method14();
      return hitcolor1.isEnabled() && (Boolean)hitcolor1.method15().get();
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Annotation2(min = 1)
@Mixin(LayerArmorBase.class)
public abstract class LayerArmorBaseMixin2 {
   @Overwrite
   public boolean shouldCombineTextures() {
      HitColor var1 = Client.method109().method40().method14();
      return var1.isEnabled() && var1.method15().get();
   }
}

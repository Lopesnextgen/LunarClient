package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.player.PlayerItemsLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@VersionGate(min = 1)
@Mixin(PlayerItemsLayer.class)
public abstract class PlayerItemsLayerMixin {
   public PlayerItemsLayerMixin() {
   }

   @Overwrite
   public void renderEquippedItems(EntityLivingBase entity1, float value, float value2) {
   }

   @Overwrite
   public static void register(Map map) {
   }
}

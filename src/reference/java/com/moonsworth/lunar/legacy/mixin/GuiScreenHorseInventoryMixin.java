package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_2;
import com.moonsworth.lunar.bridge.Bridge4_23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiScreenHorseInventory.class)
public class GuiScreenHorseInventoryMixin implements Bridge3_2 {
   @Final
   @Shadow
   public AbstractHorse horseEntity$v1_12;
   @Shadow
   public EntityHorse horseEntity;
   @Shadow
   public EntityHorse field_147034_x$v1_7;

   @Override
   public Bridge4_23 bridge$getHorse() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return (Bridge4_23)this.horseEntity$v1_12;
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? (Bridge4_23)this.horseEntity : (Bridge4_23)this.field_147034_x$v1_7;
      }
   }
}

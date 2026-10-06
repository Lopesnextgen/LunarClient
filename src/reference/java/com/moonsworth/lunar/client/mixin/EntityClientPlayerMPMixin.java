package com.moonsworth.lunar.client.mixin;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin extends EntityPlayer {
   @Final
   @Shadow
   public StatFileWriter statWriter;

   @Overwrite
   public void addStat(StatBase var1, int var2) {
      if (var1 != null) {
         this.statWriter.increaseStat(this, var1, var2);
      }
   }
}

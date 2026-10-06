package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.stats.StatisticsFileBridge;
import com.moonsworth.lunar.bridge.stats.StatBaseBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.TupleIntJsonSerializable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StatisticsFile.class)
public abstract class StatisticsFileMixin extends net.minecraft.stats.StatFileWriter implements StatisticsFileBridge {
   @Final
   @Shadow
   public MinecraftServer server;

   public StatisticsFileMixin() {
   }

   @VersionGate(min = 1)
   @Redirect(
      method = "unlockAchievement$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/stats/StatisticsManager;unlockAchievement$v1_8(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/stats/StatBase;I)V"
      )
   )
   private void lunar$dontUseLocalManager$v1_8(net.minecraft.stats.StatFileWriter statfilewriter1, EntityPlayer player2, StatBase statbase3, int number4) {
      this.lunar$unlockAchievement(player2, statbase3, number4);
   }

   @Unique
   private void lunar$unlockAchievement(EntityPlayer player1, StatBase statbase2, int number3) {
      Map map4 = Ref.MC_VERSION >= 1 ? super.statsData : this.field_150875_a$v1_7;
      if (map4 != null) {
         TupleIntJsonSerializable tupleintjsonserializable5 = map4.computeIfAbsent(statbase2, arg0 -> new TupleIntJsonSerializable());
         if (this.server.isSinglePlayer() && player1.gameProfile.getName().equals(Ref.method3().bridge$getSession().bridge$getProfile().getName())) {
            int number6 = tupleintjsonserializable5.getIntegerValue();
            this.bridge$recordStat((StatBaseBridge)statbase2, number3 - number6);
         }

         tupleintjsonserializable5.setIntegerValue(number3);
      }
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@VersionGate(max = 1)
@Mixin(S0FPacketSpawnMob.class)
public class S0FPacketSpawnMobMixin {
   @Shadow
   public DataWatcher dataManager;

   public S0FPacketSpawnMobMixin() {
   }

   @WrapOperation(
      method = {"func_149027_c$v1_7", "func_149027_c$v1_8"},
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;getAllWatched$v1_7()Ljava/util/List;"),
            @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;getAllWatched$v1_8()Ljava/util/List;")
      }
   )
   private List lunar$fixNPEWhenSpawningMobs(DataWatcher datawatcher1, Operation<List> operation2) {
      return this.dataManager == null ? null : (List)operation2.call(new Object[]{datawatcher1});
   }
}

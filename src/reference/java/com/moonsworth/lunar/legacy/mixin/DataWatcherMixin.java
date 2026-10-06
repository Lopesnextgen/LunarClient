package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DataWatcher.class)
public abstract class DataWatcherMixin implements Bridge2_7 {
   @Shadow
   public abstract <T> void set(DataParameter<T> var1, Object var2);

   @Shadow
   public abstract void updateObject(int var1, Object var2);

   @Shadow
   public abstract void updateObject(int var1, Object var2);

   @Override
   public void bridge$updateObject(int var1, Object var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         if (var1 == 10) {
            this.set(EntityPlayer.PLAYER_MODEL_FLAG$v1_12, var2);
         }
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.updateObject(var1, var2);
      } else {
         this.updateObject(var1, var2);
      }
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.BlockPos;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(BlockPos.class)
public abstract class BlockPosMixin implements Horsestats20Extension2 {
   public BlockPosMixin() {
   }

   @Shadow
   public abstract long toLong();

   @Shadow
   public abstract BlockPos add(int index1, int number2, int number3);

   public long bridge$asLong() {
      return this.toLong();
   }

   public Horsestats20Extension2 bridge$add(Vector3ic vector3ic1) {
      return (Horsestats20Extension2)this.add(vector3ic1.x(), vector3ic1.y(), vector3ic1.z());
   }
}

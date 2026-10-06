package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.util.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(MutableBlockPos.class)
public abstract class MutableBlockPosMixin extends BlockPos implements Horsestats20Extension2, Vector3iBridge.Extension {
   @Shadow
   public int x;
   @Shadow
   public int y;
   @Shadow
   public int z;

   @Override
   public void bridge$setPos(int var1, int var2, int var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   @Override
   public int bridge$getX() {
      return this.x;
   }

   @Override
   public int bridge$getY() {
      return this.y;
   }

   @Override
   public int bridge$getZ() {
      return this.z;
   }

   @Override
   public Horsestats20Extension2 bridge$offset(int var1, int var2, int var3) {
      return Horsestats20Extension2.super.bridge$offset(var1, var2, var3);
   }

   @Override
   public Horsestats20Extension2 bridge$add(Vector3ic var1) {
      return Bridge.method8().method4(this.x + var1.x(), this.y + var1.y(), this.z + var1.z());
   }

   @Override
   public Horsestats20Extension2 bridge$above() {
      this.y++;
      return this;
   }

   @Override
   public Horsestats20Extension2 bridge$below() {
      this.y--;
      return this;
   }

   @Override
   public long bridge$asLong() {
      return this.toLong();
   }
}

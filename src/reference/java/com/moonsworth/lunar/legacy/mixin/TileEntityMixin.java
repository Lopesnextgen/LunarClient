package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.tileentity.BlockEntityBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntity.class)
public abstract class TileEntityMixin implements BlockEntityBridge {
   @Shadow
   public int xCoord$v1_7;
   @Shadow
   public int yCoord$v1_7;
   @Shadow
   public int zCoord$v1_7;
   @Shadow
   public BlockPos pos$v1_8;

   public TileEntityMixin() {
   }

   @Shadow
   public abstract Block getBlockType();

   public Vec3iBridge bridge$getBlockPos() {
      return Ref.MC_VERSION >= 1
         ? (Vec3iBridge)this.pos$v1_8
         : (Vec3iBridge)(new Vector3i(this.xCoord$v1_7, this.yCoord$v1_7, this.zCoord$v1_7));
   }

   public Bridge3_23 bridge$getBlockType() {
      return (Bridge3_23)this.getBlockType();
   }
}

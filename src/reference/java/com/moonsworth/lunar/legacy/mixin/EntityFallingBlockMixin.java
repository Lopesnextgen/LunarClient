package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.EntityFallingBlockBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityFallingBlock.class)
public abstract class EntityFallingBlockMixin implements EntityFallingBlockBridge {
   @Shadow
   @VersionGate(max = 0)
   public Block blockObj$v1_7;
   @Shadow
   @VersionGate(min = 1)
   public IBlockState fallTile;

   public EntityFallingBlockMixin() {
   }

   public Bridge3_23 bridge$getBlock() {
      return Ref.MC_VERSION <= 0 ? (Bridge3_23)this.blockObj$v1_7 : (Bridge3_23)this.fallTile.getBlock();
   }
}

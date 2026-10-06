package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge14_2;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneBrick.EnumType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStoneBrick.class)
public class BlockStoneBrickMixin implements Bridge14_2 {
   @Final
   @Shadow
   public static PropertyEnum<EnumType> VARIANT;

   public BlockStoneBrickMixin() {
   }

   public boolean bridge$isCracked(BlockStateBridge bridge2_171) {
      if (Ref.MC_VERSION >= 1) {
         try {
            return ((BlockStateBase)bridge2_171).getValue(VARIANT) == EnumType.CRACKED;
         } catch (IllegalArgumentException illegalargumentexception3) {
            return false;
         }
      } else {
         return false;
      }
   }
}

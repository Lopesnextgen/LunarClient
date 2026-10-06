package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge7_5;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStone.class)
public class BlockStoneMixin implements Bridge7_5 {
   @Final
   @Shadow
   public static PropertyEnum<EnumType> VARIANT;

   public BlockStoneMixin() {
   }

   public boolean bridge$isPolishedAndesite(BlockStateBridge bridge2_171) {
      return Ref.MC_VERSION >= 1 ? ((BlockStateBase)bridge2_171).getValue(VARIANT) == EnumType.ANDESITE_SMOOTH : false;
   }
}

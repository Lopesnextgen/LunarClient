package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.BlockDoublePlantBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDoublePlant.EnumPlantType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockDoublePlant.class)
public class BlockDoublePlantMixin implements BlockDoublePlantBridge {
   @Final
   @Shadow
   public static PropertyEnum<EnumPlantType> VARIANT;

   public BlockDoublePlantMixin() {
   }

   public boolean bridge$isSunflower(BlockStateBridge bridge2_171) {
      return Ref.MC_VERSION >= 1 ? ((BlockStateBase)bridge2_171).getValue(VARIANT) == EnumPlantType.SUNFLOWER : false;
   }

   public boolean bridge$isRoseBush(BlockStateBridge bridge2_171) {
      return Ref.MC_VERSION >= 1 ? ((BlockStateBase)bridge2_171).getValue(VARIANT) == EnumPlantType.ROSE : false;
   }
}

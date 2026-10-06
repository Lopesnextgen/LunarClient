package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumWorldBlockLayer;
import org.spongepowered.asm.mixin.Mixin;

@Annotation2(min = 1)
@Mixin(BlockBarrier.class)
public class BlockBarrierMixin extends Block {
   @Annotation2(1)
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Annotation2(max = 1)
   public int getRenderType() {
      return ThreadModuleDump63.method4().method40().method84().method48() ? 3 : -1;
   }

   @Annotation2(min = 5)
   public EnumWorldBlockLayer getRenderLayer$v1_12() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Annotation2(min = 5)
   public EnumBlockRenderType getRenderType(IBlockState var1) {
      return ThreadModuleDump63.method4().method40().method84().method48() ? EnumBlockRenderType.MODEL : EnumBlockRenderType.INVISIBLE;
   }
}

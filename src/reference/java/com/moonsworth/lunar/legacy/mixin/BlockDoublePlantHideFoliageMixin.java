package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({BlockDoublePlant.class, BlockTallGrass.class})
public abstract class BlockDoublePlantHideFoliageMixin extends Block {
   @Unique
   private static final int INVISIBLE = -1;

   @Annotation2(1)
   public int getRenderType() {
      return ThreadModuleDump63.method32() ? -1 : super.getRenderType();
   }

   @Annotation2(min = 5)
   public EnumBlockRenderType getRenderType(IBlockState var1) {
      return ThreadModuleDump63.method32() ? EnumBlockRenderType.INVISIBLE : super.getRenderType(var1);
   }
}

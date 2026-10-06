package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({BlockDoublePlant.class, BlockTallGrass.class})
public abstract class BlockDoublePlantMixin extends Block {
   private static final int INVISIBLE = -1;

   public BlockDoublePlantMixin() {
   }

   @VersionGate(max = 0)
   public int getRenderType() {
      if (Ref.method32()) {
         return -1;
      } else {
         return this instanceof BlockDoublePlant ? 40 : super.getRenderType();
      }
   }
}

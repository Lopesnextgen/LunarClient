package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.util.EnumWorldBlockLayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockPane.class)
public class BlockPaneMixin extends Block {
   public BlockPaneMixin() {
   }

   @VersionGate(0)
   public int getRenderBlockPass$v1_7() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? 1 : 0;
   }

   @VersionGate(1)
   public EnumWorldBlockLayer getBlockLayer() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @VersionGate(5)
   public EnumWorldBlockLayer getRenderLayer$v1_12() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.CUTOUT_MIPPED;
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockGlass;
import net.minecraft.util.EnumWorldBlockLayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockGlass.class)
public class BlockGlassMixin extends BlockBreakable {
   public BlockGlassMixin() {
   }

   @VersionGate(0)
   public int getRenderBlockPass$v1_7() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? 1 : 0;
   }

   @VersionGate(1)
   public EnumWorldBlockLayer getBlockLayer() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.CUTOUT;
   }

   @VersionGate(5)
   public EnumWorldBlockLayer getRenderLayer$v1_12() {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      return overlaymod1.isClearGlassEnabled() ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.CUTOUT;
   }
}

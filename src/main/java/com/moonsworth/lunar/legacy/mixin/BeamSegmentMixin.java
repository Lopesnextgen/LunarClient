package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.tileentity.BeamSegmentBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.tileentity.TileEntityBeacon.BeamSegment;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(BeamSegment.class)
public abstract class BeamSegmentMixin implements BeamSegmentBridge {
   public BeamSegmentMixin() {
   }
}

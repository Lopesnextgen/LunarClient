package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.TileEntityBeaconRendererBridge;
import com.moonsworth.lunar.bridge.tileentity.BeamSegmentBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBeacon.BeamSegment;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@VersionGate(min = 1)
@Mixin(TileEntityBeaconRenderer.class)
public abstract class TileEntityBeaconRendererMixin implements TileEntityBeaconRendererBridge {
   @Unique
   private World bridge$world = null;
   @Unique
   private List<BeamSegment> bridge$beamSegments = null;

   public TileEntityBeaconRendererMixin() {
   }

   @Shadow
   public abstract void renderBeacon$v1_12(double value1, double value3, double value5, double value7, double value9, List<BeamSegment> list11, double value12);

   @Shadow
   public abstract void renderTileEntityAt(TileEntity tileentity1, double value2, double value4, double value6, float value8, int number9);

   public void bridge$renderBeacon(Itemcounter6 itemcounter61, double value2, double value4, double value6, double value8, double value10, Color color12, double value13) {
      ArrayList list15 = new ArrayList();

      for (int index16 = (int)value4 + 1; index16 < 256; index16++) {
         list15.add((BeamSegmentBridge)(new BeamSegment(ColorUtils.method15(color12.getRGB()))));
      }

      this.bridge$renderBeacon(itemcounter61, value2, value4, value6, value8, value10, list15, value13);
   }

   public void bridge$renderBeacon(Itemcounter6 itemcounter61, double value2, double value4, double value6, double value8, double value10, List<BeamSegmentBridge> list12, double value13) {
      this.bridge$world = (World)itemcounter61;
      this.bridge$beamSegments = list12;
      if (Ref.MC_VERSION >= 5) {
         this.renderBeacon$v1_12(value2, value4, value6, value8, value10, list12, value13);
      } else if (Ref.MC_VERSION == 1) {
         this.renderTileEntityAt(null, value2, value4, value6, (float)value8, 0);
      }

      this.bridge$world = null;
      this.bridge$beamSegments = null;
   }

   @VersionGate(max = 1)
   @Redirect(
      method = "renderTileEntityAt$v1_8(Lnet/minecraft/tileentity/TileEntityBeacon;DDDFI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityBeacon;shouldBeamRender()F")
   )
   public float bridge$shouldBeamRender(TileEntityBeacon tileentitybeacon1) {
      return tileentitybeacon1 == null ? 1.0F : tileentitybeacon1.shouldBeamRender();
   }

   @VersionGate(max = 1)
   @Redirect(
      method = "renderTileEntityAt$v1_8(Lnet/minecraft/tileentity/TileEntityBeacon;DDDFI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityBeacon;getBeamSegments$v1_8()Ljava/util/List;")
   )
   public List<BeamSegment> bridge$getBeamSegments(TileEntityBeacon tileentitybeacon1) {
      return tileentitybeacon1 == null ? this.bridge$beamSegments : tileentitybeacon1.getBeamSegments();
   }

   @VersionGate(max = 1)
   @Redirect(
      method = "renderTileEntityAt$v1_8(Lnet/minecraft/tileentity/TileEntityBeacon;DDDFI)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityBeacon;getWorld()Lnet/minecraft/world/World;")
   )
   public World bridge$getWorld(TileEntityBeacon tileentitybeacon1) {
      return tileentitybeacon1 == null ? this.bridge$world : tileentitybeacon1.getWorld();
   }
}

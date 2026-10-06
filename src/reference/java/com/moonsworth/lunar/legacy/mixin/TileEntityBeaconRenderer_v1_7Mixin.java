package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_34;
import com.moonsworth.lunar.bridge.hitcolor.Hitcolor;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer_v1_7;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Annotation2(max = 0)
@Mixin(TileEntityBeaconRenderer_v1_7.class)
public abstract class TileEntityBeaconRenderer_v1_7Mixin implements Bridge3_34 {
   @Unique
   private World bridge$world = null;
   @Unique
   private Color bridge$color$1_7 = null;

   @Shadow
   public abstract void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8);

   @Override
   public void bridge$renderBeacon(Itemcounter6 var1, double var2, double var4, double var6, double var8, double var10, Color var12, double var13) {
      this.bridge$world = (World)var1;
      this.bridge$color$1_7 = var12;
      this.renderTileEntityAt(null, var2, var4, var6, (float)var8);
      this.bridge$world = null;
      this.bridge$color$1_7 = null;
   }

   @Override
   public void bridge$renderBeacon(Itemcounter6 var1, double var2, double var4, double var6, double var8, double var10, List<Hitcolor> var12, double var13) {
      throw new UnsupportedOperationException("Not Available on 1.7");
   }

   @Redirect(
      method = "renderTileEntityAt(Lnet/minecraft/tileentity/TileEntityBeacon;DDDF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityBeacon;shouldBeamRender()F")
   )
   public float bridge$shouldBeamRender(TileEntityBeacon var1) {
      return var1 == null ? 1.0F : var1.shouldBeamRender();
   }

   @Annotation2(max = 1)
   @Redirect(
      method = "renderTileEntityAt(Lnet/minecraft/tileentity/TileEntityBeacon;DDDF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityBeacon;getWorld()Lnet/minecraft/world/World;")
   )
   public World bridge$getWorld(TileEntityBeacon var1) {
      return var1 == null ? this.bridge$world : var1.getWorld();
   }

   @Annotation2(max = 0)
   @Redirect(
      method = "renderTileEntityAt(Lnet/minecraft/tileentity/TileEntityBeacon;DDDF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;setColorRGBA$v1_7(IIII)V", ordinal = 0)
   )
   public void bridge$setColorRGBA$1_7(Tessellator var1, int var2, int var3, int var4, int var5) {
      if (this.bridge$color$1_7 != null) {
         var2 = this.bridge$color$1_7.getRed();
         var3 = this.bridge$color$1_7.getGreen();
         var4 = this.bridge$color$1_7.getBlue();
         var5 = this.bridge$color$1_7.getAlpha();
      }

      Tessellator.theMinecraft.setColorRGBA$v1_7(var2, var3, var4, var5);
   }
}

package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5_5;
import com.moonsworth.lunar.bridge.Bridge9_7;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(RenderChunk.class)
public class RenderChunkMixin implements Bridge9_7 {
   @Shadow
   public BlockPos position;
   @Shadow
   public MutableBlockPos position$v1_12;
   @Annotation2(min = 5)
   @Unique
   private Horsestats20Extension2 bridge$originSnapshot;

   @Override
   public Horsestats20Extension2 bridge$origin() {
      if (ThreadModuleDump63.MC_VERSION < 5) {
         return (Horsestats20Extension2)this.position;
      }

      Horsestats20Extension2 var1 = (Horsestats20Extension2)this.position$v1_12;
      Horsestats20Extension2 var2 = this.bridge$originSnapshot;
      if (var2 == null || var2.bridge$getX() != var1.bridge$getX() || var2.bridge$getY() != var1.bridge$getY() || var2.bridge$getZ() != var1.bridge$getZ()) {
         var2 = (Horsestats20Extension2)(new BlockPos(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ()));
         this.bridge$originSnapshot = var2;
      }

      return var2;
   }

   @Nullable
   @Override
   public Bridge5_5 bridge$lightOverlayTracker() {
      return null;
   }

   @Inject(method = "rebuildChunk", at = @At("RETURN"))
   private void lunar$onRebuild(CallbackInfo var1) {
      ThreadModuleDump63.method4().method40().method92().setNeedsUpdate(true);
   }
}

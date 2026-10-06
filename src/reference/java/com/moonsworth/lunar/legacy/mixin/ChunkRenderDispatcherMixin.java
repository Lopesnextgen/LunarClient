package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@VersionGate(min = 1)
@Mixin(ChunkRenderDispatcher.class)
public abstract class ChunkRenderDispatcherMixin {
   public ChunkRenderDispatcherMixin() {
   }

   @ModifyVariable(method = "runChunkUploads", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private long lunar$rewindFlawlessFrames(long number1) {
      RewindMod rewind3 = Ref.method4().method40().method85();
      boolean flag4 = rewind3.method17(arg0 -> arg0.method57().method25());
      return flag4 ? Long.MAX_VALUE : number1;
   }
}

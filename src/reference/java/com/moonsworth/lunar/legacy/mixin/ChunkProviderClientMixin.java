package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkLoad;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkProviderClient.class)
public abstract class ChunkProviderClientMixin {
   public ChunkProviderClientMixin() {
   }

   @VersionGate(min = 1, max = 4)
   @Redirect(method = "loadChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;setChunkLoaded$v1_8(Z)V"))
   private void lunar$chunkLoadEvent$v1_8(Chunk chunk1, boolean flag2) {
      lunar$chunkLoadEvent(chunk1, flag2);
   }

   @VersionGate(min = 5)
   @Redirect(method = "loadChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;markLoaded$v1_12(Z)V"))
   private void lunar$chunkLoadEvent$v1_12(Chunk chunk1, boolean flag2) {
      lunar$chunkLoadEvent(chunk1, flag2);
   }

   @Unique
   private static void lunar$chunkLoadEvent(Chunk chunk0, boolean flag1) {
      LunarEventBus.method29().method12(EventChunkLoad.class, () -> new EventChunkLoad((ChunkBridge)chunk0));
      if (Ref.MC_VERSION >= 5) {
         chunk0.markLoaded$v1_12(flag1);
      } else if (Ref.MC_VERSION == 1) {
         chunk0.setChunkLoaded(flag1);
      } else {
         chunk0.isChunkLoaded = true;
      }
   }
}

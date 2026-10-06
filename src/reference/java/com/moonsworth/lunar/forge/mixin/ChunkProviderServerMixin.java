package com.moonsworth.lunar.forge.mixin;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.chunkio.ChunkIOExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkProviderServer.class)
public abstract class ChunkProviderServerMixin {
   public ChunkProviderServerMixin() {
   }

   @Shadow
   public abstract Chunk originalLoadChunk(int number1, int number2);

   @Redirect(
      method = "loadChunk(IILjava/lang/Runnable;)Lnet/minecraft/world/chunk/Chunk;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/common/chunkio/ChunkIOExecutor;syncChunkLoad(Lnet/minecraft/world/World;Lnet/minecraft/world/chunk/storage/AnvilChunkLoader;Lnet/minecraft/world/gen/ChunkProviderServer;II)Lnet/minecraft/world/chunk/Chunk;"
      )
   )
   private Chunk lunar$redirectAsyncLoadCall(World world1, AnvilChunkLoader anvilchunkloader2, ChunkProviderServer chunkproviderserver3, int number4, int number5) {
      try {
         return ChunkIOExecutor.syncChunkLoad(world1, anvilchunkloader2, chunkproviderserver3, number4, number5);
      } catch (Exception exception7) {
         return this.originalLoadChunk(number4, number5);
      }
   }
}

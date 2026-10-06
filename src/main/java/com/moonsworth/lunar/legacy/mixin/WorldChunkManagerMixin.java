package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.world.WorldChunkManagerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.world.biome.WorldChunkManager;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(max = 1)
@Mixin(WorldChunkManager.class)
public class WorldChunkManagerMixin implements WorldChunkManagerBridge {
   public WorldChunkManagerMixin() {
   }
}

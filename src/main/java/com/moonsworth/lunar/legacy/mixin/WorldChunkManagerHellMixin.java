package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.world.WorldChunkManagerBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.world.biome.WorldChunkManagerHell;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 5)
@Mixin(WorldChunkManagerHell.class)
public abstract class WorldChunkManagerHellMixin implements WorldChunkManagerBridge {
   public WorldChunkManagerHellMixin() {
   }
}

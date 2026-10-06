package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import net.minecraft.network.play.server.SPacketUnloadChunk;

public class ChunkUnloadPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper13 {
   public ChunkUnloadPacketFactory(List<MixinHelper_19> var1) {
      super(ThreadModuleDump63.MC_VERSION <= 1 ? null : SPacketUnloadChunk.class, var1);
   }
}

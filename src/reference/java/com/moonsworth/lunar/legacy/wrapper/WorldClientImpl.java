package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.mixin.WorldMixin4;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Blocks;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

public class WorldClientImpl extends WorldClient {
   public WorldClientImpl(NetHandlerPlayClient var1) {
      super(
         var1,
         ThreadModuleDump63.MC_VERSION >= 5
            ? new WorldSettings(0L, GameType.NOT_SET, true, false, WorldType.DEFAULT)
            : new WorldSettings(0L, net.minecraft.world.WorldSettings.GameType.NOT_SET, true, false, WorldType.DEFAULT),
         0,
         EnumDifficulty.NORMAL,
         new Profiler()
      );
      ((WorldMixin4)this).bridge$setIsRemote(true);
   }

   @Annotation2(max = 0)
   public Block getBlock(int var1, int var2, int var3) {
      return Blocks.air;
   }
}

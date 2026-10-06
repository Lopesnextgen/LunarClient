package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkDataPacketFactory extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper21 {
   public ChunkDataPacketFactory(List<MixinHelper_19> var1) {
      super(ThreadModuleDump63.MC_VERSION <= 1 ? S26PacketMapChunkBulk.class : S21PacketChunkData.class, var1);
   }

   public List<Bridge3_21> method1(Itemcounter6 var1) {
      World var2 = (World)var1;
      ArrayList var3 = new ArrayList();
      if (ThreadModuleDump63.MC_VERSION <= 1) {
         if (ThreadModuleDump63.MC_VERSION == 0) {
            var3.add((Bridge3_21)(new S26PacketMapChunkBulk(((ChunkProviderClient)var2.getChunkProvider()).chunkListing$v1_7)));
         } else {
            var3.add((Bridge3_21)(new S26PacketMapChunkBulk(((ChunkProviderClient)var2.getChunkProvider()).chunkListing)));
         }

         for (TileEntity var6 : ThreadModuleDump63.MC_VERSION == 0
            ? ((ChunkProviderClient)var2.getChunkProvider()).worldObj.loadedTileEntityList$v1_7
            : ((ChunkProviderClient)var2.getChunkProvider()).worldObj.loadedTileEntityList) {
            if (ThreadModuleDump63.MC_VERSION == 0) {
               var3.add((Bridge3_21)var6.getDescriptionPacket());
            } else {
               var3.add((Bridge3_21)var6.getDescriptionPacket());
            }
         }
      } else {
         ObjectIterator var7 = ((ChunkProviderClient)var2.getChunkProvider()).loadedChunks$v1_12.values().iterator();

         while (var7.hasNext()) {
            Chunk var8 = (Chunk)var7.next();
            if (var8 != null) {
               var3.add((Bridge3_21)(new S21PacketChunkData(var8, 65535)));
            }
         }
      }

      return var3;
   }
}

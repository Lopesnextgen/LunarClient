package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkDataPacketBuilder extends RewindPacketBuilder implements com.moonsworth.lunar.bridge.ChunkDataPacketBridge {
   public ChunkDataPacketBuilder(List<PacketBuilder> list1) {
      super(Ref.MC_VERSION <= 1 ? S26PacketMapChunkBulk.class : S21PacketChunkData.class, list1);
   }

   public List<PacketBridge> method1(Itemcounter6 itemcounter61) {
      World world2 = (World)itemcounter61;
      ArrayList list3 = new ArrayList();
      if (Ref.MC_VERSION <= 1) {
         if (Ref.MC_VERSION == 0) {
            list3.add((PacketBridge)(new S26PacketMapChunkBulk(((ChunkProviderClient)world2.getChunkProvider()).chunkListing$v1_7)));
         } else {
            list3.add((PacketBridge)(new S26PacketMapChunkBulk(((ChunkProviderClient)world2.getChunkProvider()).chunkListing)));
         }

         for (TileEntity tileentity6 : Ref.MC_VERSION == 0
            ? ((ChunkProviderClient)world2.getChunkProvider()).worldObj.loadedTileEntityList$v1_7
            : ((ChunkProviderClient)world2.getChunkProvider()).worldObj.loadedTileEntityList) {
            if (Ref.MC_VERSION == 0) {
               list3.add((PacketBridge)tileentity6.getDescriptionPacket());
            } else {
               list3.add((PacketBridge)tileentity6.getDescriptionPacket());
            }
         }
      } else {
         ObjectIterator objectiterator7 = ((ChunkProviderClient)world2.getChunkProvider()).loadedChunks$v1_12.values().iterator();

         while (objectiterator7.hasNext()) {
            Chunk chunk8 = (Chunk)objectiterator7.next();
            if (chunk8 != null) {
               list3.add((PacketBridge)(new S21PacketChunkData(chunk8, 65535)));
            }
         }
      }

      return list3;
   }
}

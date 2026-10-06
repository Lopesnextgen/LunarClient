package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class ServerPackPacket extends ReplayPacket {
   private List<Integer> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.method3();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method5(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      if (!nameplate41.method11().isEmpty() || !this.field1.isEmpty()) {
         ArrayList list2 = new ArrayList();

         for (int index4 : this.field1) {
            RewindFileReader rewind35 = nameplate41.method6().method40().method35();
            if (rewind35 != null) {
               File file6 = new File(RewindPaths.field6, rewind35.method13().getId().toString());
               File file7 = new File(file6, String.valueOf(index4));
               list2.add(Bridge.method8().method79(file7, true));
            }
         }

         for (ResourcePackBridge bridge149 : nameplate41.method11()) {
            bridge149.bridge$close();
         }

         nameplate41.method25(list2);
         nameplate41.method26(this.field1);
      }
   }

   @Generated
   public ServerPackPacket(List<Integer> list) {
      this.field1 = list;
   }

   @Generated
   public ServerPackPacket() {
   }
}

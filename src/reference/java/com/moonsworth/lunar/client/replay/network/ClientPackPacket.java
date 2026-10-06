package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class ClientPackPacket extends ReplayPacket {
   private List<String> packs;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      int number2 = bytebufloader1.readVarInt();
      this.packs = new ArrayList<>();

      for (int index3 = 0; index3 < number2; index3++) {
         this.packs.add(bytebufloader1.readString());
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.packs.size());

      for (String text3 : this.packs) {
         bytebufloader1.method1(text3);
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      if (!nameplate41.method13().isEmpty() || !this.packs.isEmpty()) {
         ArrayList list2 = new ArrayList<>(this.packs);
         File file3 = new File(Ref.method3().bridge$getMcDataDir(), "resourcepacks");
         ArrayList list4 = new ArrayList();

         for (String text6 : this.packs) {
            File file7 = file3.toPath().resolve(text6).toFile();

            try {
               list4.add(Bridge.method8().method79(file7, false));
            } catch (Exception exception9) {
               list2.remove(text6);
            }
         }

         for (ResourcePackBridge bridge1411 : nameplate41.method13()) {
            bridge1411.bridge$close();
         }

         nameplate41.method27(list4);
         nameplate41.method28(list2);
      }
   }

   @Generated
   public ClientPackPacket(List<String> list) {
      this.packs = list;
   }

   @Generated
   public ClientPackPacket() {
   }
}

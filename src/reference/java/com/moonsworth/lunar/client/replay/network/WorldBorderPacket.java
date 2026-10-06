package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.WorldBorderExtensionBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;

public class WorldBorderPacket extends ReplayPacket {
   private Map<String, WorldBorderExtensionBridge> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new HashMap<>();
      int number2 = bytebufloader1.readVarInt();

      for (int index3 = 0; index3 < number2; index3++) {
         String text4 = bytebufloader1.readString();
         double value5 = bytebufloader1.readDouble();
         double value7 = bytebufloader1.readDouble();
         double value9 = bytebufloader1.readDouble();
         double value11 = bytebufloader1.readDouble();
         double value13 = bytebufloader1.readDouble();
         double value15 = bytebufloader1.readDouble();
         int number17 = bytebufloader1.readVarInt();
         boolean flag18 = bytebufloader1.readBoolean();
         boolean flag19 = bytebufloader1.readBoolean();
         String text20 = bytebufloader1.readString();
         WorldBorderExtensionBridge itemcounter4extension21 = Bridge.method8().method46(text20, number17);
         itemcounter4extension21.method1(value5, value9, value7, value11, 0);
         itemcounter4extension21.method9(value13, value15);
         itemcounter4extension21.setCancelEntry(flag18);
         itemcounter4extension21.setCancelExit(flag19);
         this.field1.put(text4, itemcounter4extension21);
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.size());

      for (Entry entry3 : this.field1.entrySet()) {
         bytebufloader1.method1((String)entry3.getKey());
         WorldBorderExtensionBridge itemcounter4extension4 = (WorldBorderExtensionBridge)entry3.getValue();
         bytebufloader1.writeDouble(itemcounter4extension4.method3());
         bytebufloader1.writeDouble(itemcounter4extension4.method5());
         bytebufloader1.writeDouble(itemcounter4extension4.method25());
         bytebufloader1.writeDouble(itemcounter4extension4.method24());
         bytebufloader1.writeDouble(itemcounter4extension4.method19());
         bytebufloader1.writeDouble(itemcounter4extension4.method20());
         bytebufloader1.method11(itemcounter4extension4.getColor());
         bytebufloader1.writeBoolean(itemcounter4extension4.isCancelEntry());
         bytebufloader1.writeBoolean(itemcounter4extension4.isCancelExit());
         bytebufloader1.method1(itemcounter4extension4.getWorld());
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method4().method63().IORHHHROCRRHORHRCHCCHHIHICCRCO().clear();
      Ref.method4().method63().IORHHHROCRRHORHRCHCCHHIHICCRCO().putAll(this.field1);
   }

   @Generated
   public WorldBorderPacket(Map<String, WorldBorderExtensionBridge> map1) {
      this.field1 = map1;
   }

   @Generated
   public WorldBorderPacket() {
   }

   @Generated
   public Map<String, WorldBorderExtensionBridge> method4() {
      return this.field1;
   }
}

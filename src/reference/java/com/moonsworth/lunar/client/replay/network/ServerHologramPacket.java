package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class ServerHologramPacket extends ReplayPacket {
   private Map<String, Serverholograms> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new HashMap<>();
      int number2 = bytebufloader1.readVarInt();

      for (int index3 = 0; index3 < number2; index3++) {
         String text4 = bytebufloader1.readString();
         String text5 = bytebufloader1.readString();
         double value6 = bytebufloader1.readDouble();
         double value8 = bytebufloader1.readDouble();
         double value10 = bytebufloader1.readDouble();
         boolean flag12 = bytebufloader1.readBoolean();
         boolean flag13 = bytebufloader1.readBoolean();
         boolean flag14 = bytebufloader1.readBoolean();
         boolean flag15 = bytebufloader1.readBoolean();
         int index16 = bytebufloader1.readVarInt();
         Component[] items17 = new Component[index16];

         for (int index18 = 0; index18 < index16; index18++) {
            items17[index18] = PlainTextComponentSerializer.plainText().deserialize(bytebufloader1.readString());
         }

         this.field1.put(text4, new Serverholograms(text5, items17, value6, value8, value10, flag12, flag13, flag14, flag15));
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.size());

      for (Entry entry3 : this.field1.entrySet()) {
         bytebufloader1.method1((String)entry3.getKey());
         Serverholograms serverholograms4 = (Serverholograms)entry3.getValue();
         bytebufloader1.method1(serverholograms4.getId());
         bytebufloader1.writeDouble(serverholograms4.getX());
         bytebufloader1.writeDouble(serverholograms4.getY());
         bytebufloader1.writeDouble(serverholograms4.getZ());
         bytebufloader1.writeBoolean(serverholograms4.isShowThroughWalls());
         bytebufloader1.writeBoolean(serverholograms4.method2());
         bytebufloader1.writeBoolean(serverholograms4.isBackground());
         bytebufloader1.writeBoolean(serverholograms4.method3());
         bytebufloader1.method11(serverholograms4.method1().length);

         for (Component component8 : serverholograms4.method1()) {
            bytebufloader1.method1(PlainTextComponentSerializer.plainText().serialize(component8));
         }
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method4().method57().IORHHHROCRRHORHRCHCCHHIHICCRCO().clear();
      Ref.method4().method57().IORHHHROCRRHORHRCHCCHHIHICCRCO().putAll(this.field1);
   }

   @Generated
   public ServerHologramPacket(Map<String, Serverholograms> map1) {
      this.field1 = map1;
   }

   @Generated
   public ServerHologramPacket() {
   }

   @Generated
   public Map<String, Serverholograms> method4() {
      return this.field1;
   }
}

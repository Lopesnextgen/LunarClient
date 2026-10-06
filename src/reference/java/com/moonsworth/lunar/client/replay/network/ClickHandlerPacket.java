package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.SprayEntry;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector3f;

public class ClickHandlerPacket extends ReplayPacket {
   private Map<UUID, LinkedList<SprayPlacementTracker>> field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = new HashMap<>();
      int number2 = bytebufloader1.readVarInt();

      for (int index3 = 0; index3 < number2; index3++) {
         UUID uuid4 = UUID.fromString(bytebufloader1.readString());
         LinkedList list5 = new LinkedList();
         int number6 = bytebufloader1.readVarInt();

         for (int index7 = 0; index7 < number6; index7++) {
            HorsestatsType_2 horsestatstype_28 = bytebufloader1.method9(HorsestatsType_2.class);
            float value9 = bytebufloader1.readFloat();
            float value10 = bytebufloader1.readFloat();
            float value11 = bytebufloader1.readFloat();
            float value12 = bytebufloader1.readFloat();
            SprayEntry gui2handler13 = new SprayEntry(
               bytebufloader1.readVarInt(),
               bytebufloader1.readString(),
               ResourceLocationBridge.create(bytebufloader1.readString()),
               bytebufloader1.readInt(),
               bytebufloader1.readFloat(),
               bytebufloader1.readFloat(),
               bytebufloader1.readFloat(),
               bytebufloader1.readFloat(),
               bytebufloader1.readBoolean(),
               bytebufloader1.readBoolean(),
               bytebufloader1.readBoolean(),
               bytebufloader1.readInt()
            );
            SprayPlacementTracker clickhandler214 = Ref.method4().method46().method15(gui2handler13, new Vector3f(value10, value11, value12), horsestatstype_28, value9, false);
            list5.add(clickhandler214);
         }

         this.field1.put(uuid4, list5);
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.field1.size());

      for (Entry entry3 : this.field1.entrySet()) {
         bytebufloader1.method1(((UUID)entry3.getKey()).toString());
         List list4 = (List)entry3.getValue();
         bytebufloader1.method11(list4.size());

         for (SprayPlacementTracker clickhandler26 : list4) {
            bytebufloader1.method10(clickhandler26.method3());
            bytebufloader1.writeFloat(clickhandler26.getRotation());
            bytebufloader1.writeFloat(clickhandler26.method2().x);
            bytebufloader1.writeFloat(clickhandler26.method2().y);
            bytebufloader1.writeFloat(clickhandler26.method2().z);
            SprayEntry gui2handler7 = clickhandler26.method1();
            bytebufloader1.method11(gui2handler7.getId());
            bytebufloader1.method1(gui2handler7.getName());
            bytebufloader1.method1(gui2handler7.method4().toString());
            bytebufloader1.writeInt(gui2handler7.method5());
            bytebufloader1.writeFloat(gui2handler7.getWidth());
            bytebufloader1.writeFloat(gui2handler7.getHeight());
            bytebufloader1.writeFloat(gui2handler7.method6());
            bytebufloader1.writeFloat(gui2handler7.method7());
            bytebufloader1.writeBoolean(gui2handler7.method8());
            bytebufloader1.writeBoolean(gui2handler7.method9());
            bytebufloader1.writeBoolean(gui2handler7.method10());
            bytebufloader1.writeInt(gui2handler7.getDuration());
         }
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      Ref.method4().method46().method43().clear();

      for (Entry entry3 : this.field1.entrySet()) {
         Ref.method4().method46().method43().put((UUID)entry3.getKey(), (LinkedList)entry3.getValue());
      }
   }

   @Generated
   public ClickHandlerPacket(Map<UUID, LinkedList<SprayPlacementTracker>> map1) {
      this.field1 = map1;
   }

   @Generated
   public ClickHandlerPacket() {
   }

   @Generated
   public Map<UUID, LinkedList<SprayPlacementTracker>> method4() {
      return this.field1;
   }
}

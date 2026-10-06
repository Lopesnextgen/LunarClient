package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.network.TickMarkerPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class InputTimelinePanel extends com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider {
   private UUID field6 = null;
   private JsonArray field7 = new JsonArray();

   public InputTimelinePanel(List<com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider> list1) {
      super(list1);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      boolean flag2 = Ref.method4().method90().method41();
      this.method3("enabled", flag2);
      this.method3("tick", flag2 ? ((ReplayContext)rewindhandlers1.method42().get()).getTick() : 0);

      try {
         this.method2(rewindhandlers1, flag2);
      } catch (IOException exception4) {
         throw new RuntimeException(exception4);
      }

      this.method4(rewindhandlers1, flag2);
   }

   private void method2(RewindHandlers rewindhandlers1, boolean flag2) {
      if (!flag2) {
         this.method3("inputTicks", new JsonArray());
      } else {
         RewindFileReader rewind33 = rewindhandlers1.method40().method35();
         if (rewind33 == null) {
            this.method3("inputTicks", new JsonArray());
         } else if (this.field6 == rewind33.method13().getId()) {
            this.method3(rewindhandlers1);
         } else {
            this.field6 = rewind33.method13().getId();
            JsonArray array4 = new JsonArray();
            JsonObject json5 = new JsonObject();
            JsonArray array6 = new JsonArray();
            json5.addProperty("tick", 0);
            json5.add("packets", array6);

            RewindFileReader rewind37;
            ReplayPacket nameplate28;
            for (rewind37 = new RewindFileReader(rewind33.getFile()); (nameplate28 = rewind37.method2(false, true)) != null; rewind37.consume()) {
               if (nameplate28 instanceof TickMarkerPacket nameplate2impl9) {
                  if (json5.get("tick").getAsInt() == 0 && array6.isEmpty()) {
                     json5.addProperty("tick", nameplate2impl9.getTick());
                  } else {
                     array4.add(json5);
                     json5 = new JsonObject();
                     array6 = new JsonArray();
                     json5.addProperty("tick", nameplate2impl9.getTick());
                     json5.add("packets", array6);
                  }
               }

               JsonObject json12 = new JsonObject();

               try {
                  json12.addProperty("type", nameplate28.name());
                  json12.addProperty("data", nameplate28.data());
                  array6.add(json12);
               } catch (Exception exception11) {
               }
            }

            if (!array6.isEmpty()) {
               array4.add(json5);
            }

            rewind37.close();
            this.field7 = array4;
            this.method3(rewindhandlers1);
         }
      }
   }

   private void method3(RewindHandlers rewindhandlers1) {
      JsonArray array2 = new JsonArray();
      int number3 = ((ReplayContext)rewindhandlers1.method42().get()).getTick();
      int number4 = 0;

      for (int index5 = 0; index5 < this.field7.size(); index5++) {
         JsonObject json6 = this.field7.get(index5).getAsJsonObject();
         if (json6.get("tick").getAsInt() == number3) {
            number4 = index5;
            break;
         }
      }

      for (int index7 = Math.max(0, number4 - 20); index7 < Math.min(this.field7.size(), number4 + 20); index7++) {
         array2.add(this.field7.get(index7));
      }

      this.method3("inputTicks", array2);
   }

   private void method4(RewindHandlers rewindhandlers1, boolean flag2) {
      if (!flag2) {
         this.method3("inputMetadata", new JsonArray());
      } else {
         RewindFileReader rewind33 = rewindhandlers1.method40().method35();
         if (rewind33 == null) {
            this.method3("inputMetadata", new JsonArray());
         } else {
            this.method3("inputMetadata", rewindhandlers1.method40().method29().toJson(rewind33.method13()));
         }
      }
   }
}

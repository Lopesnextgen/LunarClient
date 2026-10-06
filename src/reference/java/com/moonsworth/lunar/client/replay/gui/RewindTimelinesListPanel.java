package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;

public class RewindTimelinesListPanel extends RewindPropertyProvider {
   public RewindTimelinesListPanel(List<RewindPropertyProvider> list) {
      super(list);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      JsonArray array2 = new JsonArray();

      for (ReplayTimeline highlight_34 : rewindhandlers1.method40().method36()) {
         JsonObject json5 = new JsonObject();
         json5.addProperty("id", highlight_34.getId().toString());
         json5.addProperty("name", highlight_34.getName());
         array2.add(json5);
      }

      this.method3("timelines", array2);
   }
}

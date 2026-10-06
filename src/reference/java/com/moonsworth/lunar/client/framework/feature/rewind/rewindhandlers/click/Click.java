package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.click;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;

public class Click extends GuiIterator {
   public Click(List<GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void refresh(RewindHandlers var1) {
      JsonArray var2 = new JsonArray();

      for (Highlight_3 var4 : var1.method40().method36()) {
         JsonObject var5 = new JsonObject();
         var5.addProperty("id", var4.getId().toString());
         var5.addProperty("name", var4.getName());
         var2.add(var5);
      }

      this.method3("timelines", var2);
   }
}

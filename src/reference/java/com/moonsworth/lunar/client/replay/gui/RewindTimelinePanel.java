package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.timeline.ModPropertyGroup;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.Range;

public class RewindTimelinePanel extends RewindPropertyProvider {
   public RewindTimelinePanel(List<RewindPropertyProvider> list1) {
      super(list1);
   }

   private JsonArray method1(Collection<UUID> list1) {
      JsonArray array2 = new JsonArray();

      for (UUID uuid4 : list1) {
         array2.add(uuid4.toString());
      }

      return array2;
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      ReplayTimeline highlight_32 = rewindhandlers1.method40().method37();
      this.method3("selectedLayer", RewindEditorContext.getSelectedLayer() == null ? "" : RewindEditorContext.getSelectedLayer().getId().toString());
      this.method3("additionalSelectedLayers", this.method1(RewindEditorContext.getAdditionalSelectedLayers().stream().map(RewindIterator::getId).toList()));
      if (highlight_32 != null) {
         HashSet set3 = new HashSet<>(RewindEditorContext.isLinkSelectionEnabled() ? highlight_32.method11().method6().method4(RewindEditorContext.getSelectedLayer()) : Collections.emptySet());

         for (RewindIterator rewinditerator5 : RewindEditorContext.getAdditionalSelectedLayers()) {
            set3.addAll(highlight_32.method11().method6().method4(rewinditerator5));
         }

         this.method3("links", this.method1(set3));
         JsonObject json20 = new JsonObject();
         JsonArray array21 = new JsonArray();

         for (Track gui_27 : highlight_32.method11()) {
            JsonObject json8 = new JsonObject();
            JsonArray array9 = new JsonArray();
            HashMap map10 = new HashMap();
            HashMap map11 = new HashMap();

            for (Range range13 : gui_27.method5().method11().keySet()) {
               RewindIterator rewinditerator14 = (RewindIterator)gui_27.method5().method11().get(range13);
               JsonObject json15 = (JsonObject)rewinditerator14.method16(highlight_32, range13, rewindhandlers1, () -> this.method1(rewindhandlers1));
               JsonArray array16 = new JsonArray();

               for (PropertyGroup fishing2iterator18 : rewinditerator14.method18().values()) {
                  method3(fishing2iterator18, highlight_32, map10, map11, array16, "");
               }

               json15.add("keyframes", array16);
               array9.add(json15);
            }

            JsonArray array22 = new JsonArray();

            for (JsonObject json24 : map10.values()) {
               Set set25 = (Set)map11.get(json24.get("id").getAsString());
               if (set25 != null) {
                  JsonArray array26 = new JsonArray();

                  for (String text28 : set25) {
                     JsonObject json19 = new JsonObject();
                     json19.addProperty("name", text28);
                     array26.add(json19);
                  }

                  json24.add("keyframes", array26);
               }

               array22.add(json24);
            }

            json8.addProperty("id", String.valueOf(gui_27.getId()));
            json8.addProperty("type", gui_27.getType());
            json8.add("layers", array9);
            json8.add("properties", array22);
            json8.addProperty("showKeyframes", gui_27.method6());
            json8.addProperty("enabled", gui_27.isEnabled());
            array21.add(json8);
         }

         json20.addProperty("id", highlight_32.getId().toString());
         json20.addProperty("name", highlight_32.getName());
         json20.add("tracks", array21);
         json20.addProperty("framerate", highlight_32.method13().getFps());
         json20.addProperty("frameTime", highlight_32.method9());
         json20.addProperty("viewportWidth", highlight_32.method13().getWidth());
         json20.addProperty("viewportHeight", highlight_32.method13().getHeight());
         json20.addProperty("durationFrame", highlight_32.method3() + 3600);
         json20.addProperty("actualDurationFrame", highlight_32.method3());
         this.method3("timeline", json20);
      } else {
         this.method3("timeline", null);
      }
   }

   private static void method3(
      PropertyGroup fishing2iterator0, ReplayTimeline highlight_31, Map<String, JsonObject> map2, Map<String, Set<String>> map3, JsonArray array4, String text5
   ) {
      String text6 = text5.isEmpty() ? fishing2iterator0.type() : text5 + "/" + fishing2iterator0.type();
      String text7 = fishing2iterator0.method9();
      if (fishing2iterator0 instanceof ModPropertyGroup fishing2iterator28) {
         ModDetails framework89 = (ModDetails)fishing2iterator28.getFeature().HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field13);
         if (framework89 != null) {
            text7 = framework89.getName();
         }
      }

      JsonArray array16 = new JsonArray();

      for (KeyframeProperty fishing2loader10 : fishing2iterator0.method12().values()) {
         OptionDataProvider guiextension11 = (OptionDataProvider)fishing2loader10.getOption().HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
         if (guiextension11 != null
            && fishing2loader10.method28()
            && !fishing2loader10.method27().isEmpty()
            && (fishing2loader10.method27().size() != 1 || !fishing2loader10.method27().containsKey(Integer.MIN_VALUE))) {
            for (Integer index13 : fishing2loader10.method27().keySet()) {
               if (guiextension11.provide() instanceof JsonObject json14) {
                  json14.addProperty("frame", index13);
                  json14.addProperty("milliseconds", index13.intValue() * highlight_31.method9());
                  json14.addProperty("selected", RewindEditorContext.isKeyframeSelected(fishing2loader10, index13));
                  array16.add(json14);
               }
            }

            JsonObject json21 = map2.getOrDefault(text6, new JsonObject());
            json21.addProperty("id", text6);
            json21.addProperty("name", text7);
            if (guiextension11.provide() instanceof JsonObject json23) {
               map3.computeIfAbsent(text6, arg0x -> new HashSet<>()).add(json23.get("name").getAsString());
            }

            map2.put(text6, json21);
         }
      }

      JsonObject json18 = new JsonObject();
      json18.addProperty("propertyId", text6);
      json18.addProperty("propertyName", text7);
      json18.add("keyframes", array16);
      array4.add(json18);
      String text19 = text5.isEmpty() ? fishing2iterator0.type() : text5 + "/" + fishing2iterator0.type();

      for (PropertyGroup fishing2iterator22 : fishing2iterator0.method11().values()) {
         method3(fishing2iterator22, highlight_31, map2, map3, array4, text19);
      }
   }
}

package com.moonsworth.lunar.client.replay.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.ImageSegment;
import com.moonsworth.lunar.client.replay.timeline.EffectSegment;
import com.moonsworth.lunar.client.replay.timeline.EffectTrack;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class EffectsPanel extends com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider {
   public EffectsPanel(List<com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider> list1) {
      super(list1);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      ReplayProjectManager rewind2_32 = rewindhandlers1.method40();
      ReplayTimeline highlight_33 = rewind2_32.method37();
      if (highlight_33 != null) {
         JsonArray array4 = new JsonArray();

         for (EffectTrack guiimpl6 : highlight_33.method11().method2()) {
            if (guiimpl6.isEnabled()) {
               Entry entry7 = guiimpl6.CCOIHCHRIHICROIOOCRRRHORHIRIOO(highlight_33.method15());
               if (entry7 != null) {
                  EffectSegment rewind_28 = (EffectSegment)entry7.getValue();
                  if (!rewind_28.type().equals("effect")) {
                     JsonObject json9 = new JsonObject();
                     json9.addProperty("type", rewind_28.type());
                     json9.addProperty("id", rewind_28.getId().toString());
                     if (rewind_28 instanceof ImageSegment rewindimpl10) {
                        MediaPool highlight_411 = rewind2_32.method43().method5();
                        File file12 = highlight_411.method6(rewindimpl10.method4());
                        if (file12 != null) {
                           json9.addProperty("source", MediaPool.method15(file12));
                           if (file12.getName().toLowerCase().endsWith(".gif")) {
                              int number13 = (Integer)((Range)entry7.getKey()).getMinimum();
                              int number14 = highlight_33.method15() - number13;
                              json9.addProperty("ms", (long)(number14 * highlight_33.method9()));
                           }
                        } else {
                           json9.addProperty("source", "");
                        }
                     }

                     for (Entry entry18 : rewind_28.method18().entrySet()) {
                        PropertyGroup fishing2iterator19 = (PropertyGroup)entry18.getValue();
                        JsonObject json20 = new JsonObject();

                        for (Entry entry15 : fishing2iterator19.method12().entrySet()) {
                           OptionDataProvider guiextension16 = (OptionDataProvider)((KeyframeProperty)entry15.getValue()).getOption().HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
                           if (guiextension16 != null) {
                              json20.add((String)entry15.getKey(), guiextension16.provide());
                           }
                        }

                        json9.add((String)entry18.getKey(), json20);
                     }

                     array4.add(json9);
                  }
               }
            }
         }

         this.method3("effects", array4);
      }
   }
}

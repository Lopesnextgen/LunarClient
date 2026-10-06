package com.moonsworth.lunar.client.replay.export;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.project.RewindFileCache;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.audio.MediaAudioStream;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.mutable.MutableObject;
import com.moonsworth.lunar.client.replay.audio.MusicTrackManager;
import com.moonsworth.lunar.client.replay.audio.MusicTrack;

public class MediaExporter extends RewindPropertyProvider {
   public MediaExporter(List<RewindPropertyProvider> list1) {
      super(list1);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      ReplayProjectManager rewind2_32 = rewindhandlers1.method40();
      MediaPool highlight_43 = rewind2_32.method43().method5();
      RewindFileCache rewind4 = rewind2_32.method43().method6();
      ReplayTimeline highlight_35 = rewind2_32.method37();
      JsonArray array6 = new JsonArray();
      MusicTrackManager highlight7 = rewind2_32.method44();
      highlight7.method1();

      for (Entry entry9 : highlight_43.method7()) {
         JsonObject json10 = new JsonObject();
         json10.addProperty("id", ((UUID)entry9.getKey()).toString());
         json10.addProperty("name", (String)entry9.getValue());
         json10.addProperty("type", "folder");
         UUID uuid11 = highlight_43.method9((UUID)entry9.getKey());
         if (uuid11 != null) {
            json10.addProperty("parent", uuid11.toString());
         }

         array6.add(json10);
      }

      for (Entry entry19 : highlight_43.entrySet()) {
         if (!highlight7.method5().contains(((File)entry19.getValue()).getName())) {
            JsonObject json21 = new JsonObject();
            json21.addProperty("id", ((UUID)entry19.getKey()).toString());
            File file23 = (File)entry19.getValue();
            json21.addProperty("name", highlight_43.method11((UUID)entry19.getKey(), file23));
            UUID uuid12 = highlight_43.method9((UUID)entry19.getKey());
            if (uuid12 != null) {
               json21.addProperty("parent", uuid12.toString());
            }

            MutableObject mutableobject13 = new MutableObject("media");
            if (file23.isDirectory()) {
               mutableobject13.setValue("folder");
            }

            try {
               if (rewind4.method5((UUID)entry19.getKey())) {
                  String text14 = rewind2_32.method41().method3((UUID)entry19.getKey(), 0);
                  if (text14 != null) {
                     json21.addProperty("thumbnail", text14);
                  }
               }
            } catch (IOException exception16) {
               throw new RuntimeException(exception16);
            }

            JsonObject json27 = new JsonObject();
            json27.addProperty("max", 10000);
            if (highlight_35 != null && file23.isFile()) {
               highlight_43.method17(file23, () -> {
                  try {
                     RewindFileReader rewind36x = rewind4.method4((UUID)entry19.getKey());
                     if (rewind36x != null) {
                        mutableobject13.setValue("gameplay");
                        json27.addProperty("max", rewind36x.method13().method1());
                        String text7x = rewind2_32.method41().method3((UUID)entry19.getKey(), 0);
                        if (text7x != null) {
                           json21.addProperty("thumbnail", text7x);
                           json27.addProperty("thumbnail", text7x);
                        }
                     }
                  } catch (Exception exception8) {
                  }
               }, () -> {
                  try {
                     MediaAudioStream rewindhandlers2impl5x = new MediaAudioStream(rewind2_32.method32(), "media://" + entry19.getKey(), rewind2_32.method42(), highlight_43, rewind2_32.method44());
                     if (rewindhandlers2impl5x.isValid()) {
                        json27.addProperty("max", rewindhandlers2impl5x.getDuration());
                     }

                     mutableobject13.setValue("audio");
                  } catch (Exception exception6x) {
                     exception6x.printStackTrace();
                  }
               }, () -> {
                  mutableobject13.setValue("image");
                  json21.addProperty("thumbnail", MediaPool.method15(file23));
                  json27.addProperty("thumbnail", MediaPool.method15(file23));
               });
               json27.addProperty("id", ((UUID)entry19.getKey()).toString());
               json27.addProperty("type", (String)mutableobject13.getValue());
               json27.addProperty("min", 0);
               json21.addProperty("type", ((String)mutableobject13.getValue()).equals("gameplay") ? "media" : (String)mutableobject13.getValue());
               json21.add("layer", json27);
            }

            array6.add(json21);
         }
      }

      this.method3("media", array6);
      JsonArray array18 = new JsonArray();
      if (highlight7.method2()) {
         for (MusicTrack highlight222 : highlight7.method4()) {
            UUID uuid24 = highlight_43.method2(new File(rewind2_32.method32(), highlight222.name()));
            JsonObject json25 = new JsonObject();
            json25.addProperty("id", uuid24.toString());
            json25.addProperty("name", highlight222.name());

            MediaAudioStream rewindhandlers2impl26;
            try {
               rewindhandlers2impl26 = new MediaAudioStream(rewind2_32.method32(), "media://" + uuid24, rewind2_32.method42(), highlight_43, rewind2_32.method44());
            } catch (IOException exception15) {
               throw new RuntimeException(exception15);
            }

            JsonObject json28 = new JsonObject();
            json28.addProperty("id", uuid24.toString());
            json28.addProperty("type", "audio");
            json28.addProperty("min", 0);
            json28.addProperty("max", rewindhandlers2impl26.isValid() ? rewindhandlers2impl26.getDuration() : 10000L);
            json25.addProperty("type", "audio");
            json25.addProperty("thumbnail", highlight222.method2());
            json25.add("layer", json28);
            array18.add(json25);
         }
      }

      this.method3("provided", array18);
   }
}

package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.TrackRegistry;
import java.lang.reflect.Type;
import lombok.Generated;

public class TrackCollectionDeserializer implements JsonDeserializer<TrackCollection> {
   private final ReplayProjectManager field1;

   public TrackCollection method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      TrackCollection gui2_24 = new TrackCollection(this.field1.method40());
      JsonObject json5 = element1.getAsJsonObject();
      if (json5.has("effects")) {
         for (JsonElement element8 : json5.getAsJsonArray("effects")) {
            String text9 = element8.getAsJsonObject().get("type").getAsString();
            EffectTrack guiimpl10 = (EffectTrack)jsondeserializationcontext3.deserialize(element8, TrackRegistry.getType(text9));
            gui2_24.method1(guiimpl10);
            gui2_24.field1.add(guiimpl10);
         }
      }

      if (json5.has("gameplay")) {
         JsonElement element11 = json5.get("gameplay");
         if (element11.isJsonObject()) {
            GameplayTrack guiimpl314 = (GameplayTrack)jsondeserializationcontext3.deserialize(element11, GameplayTrack.class);
            gui2_24.method1(guiimpl314);
            gui2_24.field2.add(guiimpl314);
         } else if (element11.isJsonArray()) {
            for (JsonElement element20 : element11.getAsJsonArray()) {
               GameplayTrack guiimpl322 = (GameplayTrack)jsondeserializationcontext3.deserialize(element20, GameplayTrack.class);
               gui2_24.method1(guiimpl322);
               gui2_24.field2.add(guiimpl322);
            }
         }
      }

      if (json5.has("audios")) {
         for (JsonElement element19 : json5.getAsJsonArray("audios")) {
            SoundTrack guiimpl221 = (SoundTrack)jsondeserializationcontext3.deserialize(element19, SoundTrack.class);
            gui2_24.method1(guiimpl221);
            gui2_24.field3.add(guiimpl221);
         }
      }

      for (Track gui_217 : gui2_24) {
         gui_217.method3(null);
      }

      return gui2_24;
   }

   @Generated
   public TrackCollectionDeserializer(ReplayProjectManager rewind2_31) {
      this.field1 = rewind2_31;
   }
}

package com.moonsworth.lunar.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.network.mixin.MixinHelper7;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class GameEventBatchPostRequest implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_EVENTS = "events";
   @SerializedName("events")
   private List<GameBatchEvent> events = new ArrayList<>();
   public static HashSet<String> field4 = new HashSet<>();
   public static HashSet<String> field5 = new HashSet<>();

   public GameEventBatchPostRequest() {
   }

   public GameEventBatchPostRequest events(List<GameBatchEvent> list1) {
      this.events = list1;
      return this;
   }

   public GameEventBatchPostRequest addEventsItem(GameBatchEvent mixinhelper621) {
      if (this.events == null) {
         this.events = new ArrayList<>();
      }

      this.events.add(mixinhelper621);
      return this;
   }

   @Nonnull
   public List<GameBatchEvent> getEvents() {
      return this.events;
   }

   public void setEvents(List<GameBatchEvent> list1) {
      this.events = list1;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         GameEventBatchPostRequest mixinhelper112 = (GameEventBatchPostRequest)obj1;
         return Objects.equals(this.events, mixinhelper112.events);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.events);
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class GameEventBatchPostRequest {\n");
      builder1.append("    events: ").append(this.toIndentedString(this.events)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field5.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameEventBatchPostRequest is not found in the empty JSON string", field5.toString())
         );
      }

      for (Entry entry3 : element0.getAsJsonObject().entrySet()) {
         if (!field4.contains(entry3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameEventBatchPostRequest` properties. JSON: %s", entry3.getKey(), element0.toString()
               )
            );
         }
      }

      for (String text7 : field5) {
         if (element0.getAsJsonObject().get(text7) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", text7, element0.toString()));
         }
      }

      JsonObject json6 = element0.getAsJsonObject();
      if (!json6.get("events").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `events` to be an array in the JSON string but got `%s`", json6.get("events").toString())
         );
      }

      JsonArray array8 = json6.getAsJsonArray("events");

      for (int index4 = 0; index4 < array8.size(); index4++) {
         GameBatchEvent.validateJsonElement(array8.get(index4));
      }
   }

   public static GameEventBatchPostRequest method5(String text) {
      return (GameEventBatchPostRequest)MixinHelper7.getGson().fromJson(text, GameEventBatchPostRequest.class);
   }

   public String toJson() {
      return MixinHelper7.getGson().toJson(this);
   }

   static {
      field4.add("events");
      field5.add("events");
   }
}

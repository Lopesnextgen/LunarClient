package com.moonsworth.lunar.client.cosmetics.emote;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.util.UUID;
import javax.annotation.Nullable;

public class EmoteGiftInfo implements JsonProvider {
   private final boolean field1;
   private final UUID field2;
   private final String field3;
   private final String field4;

   public EmoteGiftInfo(@Nullable UUID uuid1, String text, String text2, boolean flag) {
      this.field2 = uuid1;
      this.field3 = text;
      this.field4 = text2;
      this.field1 = flag;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      if (this.field1) {
         json1.addProperty("anonymous", true);
      } else if (this.field2 != null && this.field2.getLeastSignificantBits() != 0L && this.field2.getMostSignificantBits() != 0L) {
         json1.addProperty("uuid", this.field2.toString());
         json1.addProperty("username", this.field3);
      }

      json1.addProperty("message", this.field4);
      return json1;
   }
}

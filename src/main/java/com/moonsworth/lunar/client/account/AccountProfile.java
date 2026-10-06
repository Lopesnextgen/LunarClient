package com.moonsworth.lunar.client.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import lombok.Generated;

public class AccountProfile implements JsonConfigurable {
   private String field1 = null;
   private boolean field2 = false;
   private boolean field3 = false;
   private boolean field4 = false;
   private boolean persistent = true;
   private JsonArray field5 = new JsonArray();

   public void load(JsonObject json1) {
      if (json1.has("avatar") && !json1.get("avatar").isJsonNull()) {
         this.field1 = json1.get("avatar").getAsString();
      }

      this.field2 = json1.get("eligibleForMigration").getAsBoolean();
      this.field3 = json1.get("hasMultipleProfiles").getAsBoolean();
      this.field4 = json1.get("legacy").getAsBoolean();
      this.persistent = json1.get("persistent").getAsBoolean();
      this.field5 = json1.getAsJsonArray("userProperites");
   }

   public void method1(JsonObject json1) {
      if (this.field1 != null) {
         json1.addProperty("avatar", this.field1);
      }

      json1.addProperty("eligibleForMigration", this.field2);
      json1.addProperty("hasMultipleProfiles", this.field3);
      json1.addProperty("legacy", this.field4);
      json1.addProperty("persistent", this.persistent);
      json1.add("userProperites", this.field5);
   }

   @Generated
   public AccountProfile() {
   }
}

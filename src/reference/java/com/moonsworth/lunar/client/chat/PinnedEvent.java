package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public class PinnedEvent extends SystemEvent {
   @Nullable
   private final GuiProfile field1;
   private final UUID field2;

   public PinnedEvent(@Nullable GuiProfile fov_31, UUID uuid2) {
      this.field1 = fov_31;
      this.field2 = uuid2;
   }

   @Override
   public SystemEventType method2() {
      return SystemEventType.PINNED;
   }

   @Nullable
   @Override
   public GuiProfile method3() {
      return this.field1;
   }

   @Override
   public String method4() {
      return "pinned a message";
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("pinnedMessageId", this.field2.toString());
      return json1;
   }
}

package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public class NameChangeEvent extends SystemEvent {
   @Nullable
   private final GuiProfile field1;
   private final String field2;

   public NameChangeEvent(@Nullable GuiProfile fov_31, String text) {
      this.field1 = fov_31;
      this.field2 = text;
   }

   @Override
   public SystemEventType method2() {
      return SystemEventType.NAME_CHANGE;
   }

   @Nullable
   @Override
   public GuiProfile method3() {
      return this.field1;
   }

   @Override
   public String method4() {
      return "changed conversation name";
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("newName", this.field2);
      return json1;
   }
}

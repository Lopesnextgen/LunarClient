package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public class IconChangeEvent extends SystemEvent {
   @Nullable
   private final GuiProfile field1;
   private final String field2;

   public IconChangeEvent(@Nullable GuiProfile fov_31, String text) {
      this.field1 = fov_31;
      this.field2 = text;
   }

   @Override
   public SystemEventType method2() {
      return SystemEventType.ICON_CHANGE;
   }

   @Nullable
   @Override
   public GuiProfile method3() {
      return this.field1;
   }

   @Override
   public String method4() {
      return "changed conversation icon to";
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("newIconUrl", this.field2);
      return json1;
   }
}

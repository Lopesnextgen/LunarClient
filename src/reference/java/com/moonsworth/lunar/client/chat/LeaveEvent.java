package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public class LeaveEvent extends SystemEvent {
   @Nullable
   private final GuiProfile field1;
   private final GuiProfile field2;

   public LeaveEvent(@Nullable GuiProfile fov_31, GuiProfile fov_32) {
      this.field1 = fov_31;
      this.field2 = fov_32;
   }

   @Override
   public SystemEventType method2() {
      return SystemEventType.LEAVE;
   }

   @Nullable
   @Override
   public GuiProfile method3() {
      return this.field1;
   }

   @Override
   public String method4() {
      return "left the conversation";
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.add("leftMember", this.field2.provide());
      return json1;
   }
}

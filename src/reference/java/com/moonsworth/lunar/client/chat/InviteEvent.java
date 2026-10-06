package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public class InviteEvent extends SystemEvent {
   @Nullable
   private final GuiProfile field1;
   private final List<GuiProfile> field2;

   public InviteEvent(@Nullable GuiProfile fov_31, List<GuiProfile> list) {
      this.field1 = fov_31;
      this.field2 = List.copyOf(list);
   }

   @Override
   public SystemEventType method2() {
      return SystemEventType.INVITE;
   }

   @Nullable
   @Override
   public GuiProfile method3() {
      return this.field1;
   }

   @Override
   public String method4() {
      return "invited";
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      JsonArray array2 = new JsonArray();
      this.field2.forEach(arg1x -> array2.add(arg1x.provide()));
      json1.add("invitees", array2);
      return json1;
   }
}

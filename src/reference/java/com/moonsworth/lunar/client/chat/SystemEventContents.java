package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.chat.MessageContents;

public class SystemEventContents extends MessageContents {
   private final SystemEvent field3;

   public SystemEventContents(SystemEvent fov5_31, List<String> list2, @Nullable MessageEmbed fov4_23) {
      super(list2, fov4_23);
      this.field3 = fov5_31;
   }

   @Override
   public MessageContentType method2() {
      return MessageContentType.SYSTEM;
   }

   @Override
   public String method3() {
      return this.field3.method4();
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.add("systemEvent", this.field3.provide());
      return json1;
   }

   @Generated
   public SystemEvent method4() {
      return this.field3;
   }
}

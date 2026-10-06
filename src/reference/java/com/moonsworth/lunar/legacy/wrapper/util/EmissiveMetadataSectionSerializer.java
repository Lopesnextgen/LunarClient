package com.moonsworth.lunar.legacy.wrapper.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.VersionGate;
import java.lang.reflect.Type;
import lombok.Generated;
import net.minecraft.util.JsonUtils;

@VersionGate(min = 1, max = 5)
public class EmissiveMetadataSectionSerializer implements net.minecraft.client.resources.data.IMetadataSectionSerializer<EmissiveMetadataSection> {
   public static final EmissiveMetadataSectionSerializer field1 = new EmissiveMetadataSectionSerializer();

   public String getSectionName() {
      return "lunar";
   }

   public EmissiveMetadataSection method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = JsonUtils.getJsonObject(element1, "lunar");
      return new EmissiveMetadataSection(JsonUtils.getString(json4, "emissive", null), JsonUtils.getBoolean(json4, "emissiveAnimated", true));
   }

   @Generated
   private EmissiveMetadataSectionSerializer() {
   }
}

package com.moonsworth.lunar.legacy.wrapper.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.Annotation2;
import java.lang.reflect.Type;
import lombok.Generated;
import net.minecraft.util.JsonUtils;

@Annotation2(min = 1, max = 5)
public class IMetadataSectionSerializer implements net.minecraft.client.resources.data.IMetadataSectionSerializer<Bridge3Extension2> {
   public static final IMetadataSectionSerializer field1 = new IMetadataSectionSerializer();

   public String getSectionName() {
      return "lunar";
   }

   public Bridge3Extension2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getJsonObject(var1, "lunar");
      return new Bridge3Extension2(JsonUtils.getString(var4, "emissive", null), JsonUtils.getBoolean(var4, "emissiveAnimated", true));
   }

   @Generated
   private IMetadataSectionSerializer() {
   }
}

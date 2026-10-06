package com.moonsworth.lunar.legacy.wrapper.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.Annotation2;
import java.lang.reflect.Type;
import lombok.Generated;
import net.minecraft.client.resources.data.IMetadataSectionSerializer_v1_7;
import net.minecraft.util.JsonUtils;

@Annotation2(max = 0)
public class JsonDeserializerImpl implements JsonDeserializer<Bridge3Extension2>, IMetadataSectionSerializer_v1_7 {
   public static final JsonDeserializerImpl field1 = new JsonDeserializerImpl();

   public String getSectionName() {
      return "lunar";
   }

   public Bridge3Extension2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getElementAsJsonObject$v1_7(var1, "lunar");
      return new Bridge3Extension2(
         JsonUtils.getJsonObjectStringFieldValueOrDefault$v1_7(var4, "emissive", null),
         JsonUtils.getJsonObjectBooleanFieldValueOrDefault$v1_7(var4, "emissiveAnimated", true)
      );
   }

   @Generated
   private JsonDeserializerImpl() {
   }
}

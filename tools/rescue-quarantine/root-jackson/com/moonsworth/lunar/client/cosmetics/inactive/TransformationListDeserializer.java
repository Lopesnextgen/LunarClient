package com.moonsworth.lunar.client.cosmetics.inactive;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.client.fog.holograms.Holograms12;
import com.moonsworth.lunar.client.util.LunarConstants;
import com.moonsworth.lunar.client.util.ThreadModuleDump91;
import java.util.ArrayList;
import java.util.List;

public class TransformationListDeserializer extends MixinHelper102_4<List<ThreadModuleDump91>> {
   public TransformationListDeserializer() {
   }

   public List<ThreadModuleDump91> method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
      String text3 = mixinhelper531.method98().toString();
      ArrayList list4 = new ArrayList();
      JsonArray array5 = (JsonArray)LunarConstants.field22.fromJson(text3, JsonArray.class);
      if (array5 == null) {
         return new ArrayList<>();
      }

      for (JsonElement element7 : array5) {
         if (!element7.isJsonNull()) {
            list4.addAll(Holograms12.method32(element7.getAsJsonObject()));
         }
      }

      return list4;
   }
}

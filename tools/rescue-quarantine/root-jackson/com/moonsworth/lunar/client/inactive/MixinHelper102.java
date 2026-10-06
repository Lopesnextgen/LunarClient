package com.moonsworth.lunar.client.inactive;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.client.fog.holograms.Holograms12;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump91;
import java.util.ArrayList;
import java.util.List;

public class MixinHelper102 extends MixinHelper102_4<List<ThreadModuleDump91>> {
   public List<ThreadModuleDump91> method1(MixinHelper53 var1, MixinHelper73_3 var2) {
      String var3 = var1.method98().toString();
      ArrayList var4 = new ArrayList();
      JsonArray var5 = (JsonArray)ThreadModuleDump48.field22.fromJson(var3, JsonArray.class);
      if (var5 == null) {
         return new ArrayList<>();
      }

      for (JsonElement var7 : var5) {
         if (!var7.isJsonNull()) {
            var4.addAll(Holograms12.method32(var7.getAsJsonObject()));
         }
      }

      return var4;
   }
}

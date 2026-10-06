package com.moonsworth.lunar.client.feature;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;

public class ModuleType3_2 extends AbstractCosmetic {
   public ModuleType3_2(String var1, boolean flag, ThreadModuleDump91... items) {
      super(var1, ModuleType.BODY, ModuleType2.BODYWEAR, flag, items);
   }

   public ModuleType3_2(JsonObject var1) {
      super(var1);
   }
}

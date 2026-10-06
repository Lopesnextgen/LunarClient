package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import com.moonsworth.lunar.client.cosmetics.AbstractCosmetic;

public class BodywearCosmetic extends AbstractCosmetic {
   public BodywearCosmetic(String text1, boolean flag2, ThreadModuleDump91... items3) {
      super(text1, CosmeticSlot.BODY, CosmeticCategory.BODYWEAR, flag2, items3);
   }

   public BodywearCosmetic(JsonObject json1) {
      super(json1);
   }
}

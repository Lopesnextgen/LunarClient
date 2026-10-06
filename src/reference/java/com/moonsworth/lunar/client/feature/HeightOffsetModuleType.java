package com.moonsworth.lunar.client.feature;

import com.google.common.collect.Range;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.feature.ModuleType;
import com.moonsworth.lunar.client.feature.ModuleType2;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import java.util.Optional;
import lombok.Generated;

public class HeightOffsetModuleType extends com.moonsworth.lunar.client.cosmetics.AbstractCosmetic {
   protected boolean field11 = false;
   private Range<Float> field12;

   public HeightOffsetModuleType(JsonObject var1) {
      super(var1);
   }

   public HeightOffsetModuleType(String var1, boolean var2, ThreadModuleDump91... items) {
      super(var1, ModuleType.HEAD, ModuleType2.HATS, var2, items);
   }

   public ModuleType method5() {
      return ModuleType.HEAD;
   }

   public HeightOffsetModuleType method7() {
      this.field4 = true;
      return this;
   }

   public boolean method1() {
      return this.field3;
   }

   public Optional<Range<Float>> method8() {
      return Optional.ofNullable(this.field12);
   }

   public void load(JsonObject var1) {
      super.load(var1);
      if (var1.has("heightOffsetRange") && var1.get("heightOffsetRange").isJsonArray()) {
         JsonArray var2 = var1.get("heightOffsetRange").getAsJsonArray();
         if (var2.size() == 2) {
            this.field12 = Range.open(var2.get(0).getAsFloat(), var2.get(1).getAsFloat());
         }
      }
   }

   @Generated
   public boolean method9() {
      return this.field11;
   }
}

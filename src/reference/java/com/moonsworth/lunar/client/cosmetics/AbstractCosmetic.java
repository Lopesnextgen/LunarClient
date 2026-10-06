package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.framework.Tickable;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import com.moonsworth.lunar.client.feature.Module;
import com.moonsworth.lunar.client.feature.ModuleType2;
import com.moonsworth.lunar.client.feature.ModuleType;

public abstract class AbstractCosmetic implements Tickable, Module {
   private static final String field1 = "cosmetics/models/%s/%s/%s";
   protected String name;
   protected boolean field2;
   protected boolean field3;
   protected boolean field4;
   private final Map<String, Boolean> field5 = new HashMap<>();
   protected ResourceLocationBridge field6;
   protected ResourceLocationBridge field7;
   private ModuleType2 field8;
   private ModuleType field9 = ModuleType.HEAD;
   private final List<ThreadModuleDump91> field10 = new ArrayList<>();
   private float scale = 1.0F;

   protected AbstractCosmetic(String var1, ModuleType var2, ModuleType2 var3, boolean var4, ThreadModuleDump91... var5) {
      this.name = var1;
      this.field2 = var4;
      this.field9 = var2;
      this.field8 = var3;
      this.field6 = ResourceLocationBridge.create("lunar-jit", String.format("cosmetics/models/%s/%s/%s", var3.name().toLowerCase(Locale.ROOT), var1, var1 + ".webp"));
      this.field7 = ResourceLocationBridge.create("lunar-jit", String.format("cosmetics/models/%s/%s/%s", var3.name().toLowerCase(Locale.ROOT), var1, var1 + ".obj"));
      this.field10.addAll(Arrays.asList(var5));
   }

   protected AbstractCosmetic(JsonObject var1) {
      this.load(var1);
      this.field6 = ResourceLocationBridge.create(
         "lunar-jit", String.format("cosmetics/models/%s/%s/%s", this.field8.name().toLowerCase(Locale.ROOT), this.name, this.name + ".webp")
      );
      this.field7 = ResourceLocationBridge.create(
         "lunar-jit", String.format("cosmetics/models/%s/%s/%s", this.field8.name().toLowerCase(Locale.ROOT), this.name, this.name + ".obj")
      );
   }

   public <T extends AbstractCosmetic> T method1(String... var1) {
      for (String var5 : var1) {
         this.field5.put(var5, true);
      }

      return (T)this;
   }

   public <T extends AbstractCosmetic> T method2(String var1) {
      this.field5.put(var1, false);
      return (T)this;
   }

   public boolean method3(Module var1, OwnedCosmetic var2, OwnedCosmetic var3) {
      boolean var4 = var2.method10() == CosmeticCategoryType.HAT && var3.method10() == CosmeticCategoryType.HAT;
      boolean var5 = var4 || var2.method10() != var3.method10();
      if (var5) {
         return this.field5.containsKey(var1.getName()) ? this.field5.get(var1.getName()) : this.field5.containsKey("any");
      } else {
         return false;
      }
   }

   @Override
   public boolean method1() {
      return this.field3;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isDynamic() {
      return this.field4;
   }

   @Override
   public boolean method4() {
      return this.field2;
   }

   @Override
   public ModuleType method5() {
      return this.field9;
   }

   @Override
   public Collection<ThreadModuleDump91> method6() {
      return this.field10;
   }

   @Override
   public void tick() {
   }

   @Override
   public void load(JsonObject var1) {
      this.field2 = var1.get("animated").getAsBoolean();
      this.name = var1.get("name").getAsString();
      this.field9 = ModuleType.valueOf(var1.get("bodyPart").getAsString());
      this.field8 = ModuleType2.valueOf(var1.get("cosmeticType").getAsString());
      this.field4 = var1.has("dynamic") && var1.get("dynamic").getAsBoolean();
      this.field3 = var1.has("showWithArmor") && var1.get("showWithArmor").getAsBoolean();

      for (JsonElement var4 : var1.get("transformations").getAsJsonArray()) {
         if (!var4.isJsonNull()) {
            this.field10.addAll(CosmeticManager.method32(var4.getAsJsonObject()));
         }
      }

      if (var1.has("exclude")) {
         for (JsonElement var7 : var1.getAsJsonArray("exclude")) {
            if (var7.isJsonPrimitive()) {
               this.field5.put(var7.getAsString(), false);
            }
         }
      }

      if (var1.has("compatible")) {
         for (JsonElement var8 : var1.getAsJsonArray("compatible")) {
            if (var8.isJsonPrimitive()) {
               this.field5.put(var8.getAsString(), true);
            }
         }
      }
   }

   @Override
   public JsonObject save() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("animated", this.field2);
      var1.addProperty("dynamic", this.field4);
      var1.addProperty("name", this.name);
      var1.addProperty("bodyPart", this.field9.name());
      var1.addProperty("indexType", this.field8.name());
      var1.addProperty("showWithArmor", this.method1());
      JsonArray var2 = new JsonArray();

      for (ThreadModuleDump91 var4 : this.field10) {
         var2.add(var4.save());
      }

      var1.add("transformations", var2);
      JsonArray var7 = new JsonArray();
      JsonArray var8 = new JsonArray();

      for (Entry var6 : this.field5.entrySet()) {
         (var6.getValue() ? var7 : var8).add(new JsonPrimitive((String)var6.getKey()));
      }

      if (!var7.isEmpty()) {
         var1.add("compatible", var7);
      }

      if (!var8.isEmpty()) {
         var1.add("exclude", var8);
      }

      return var1;
   }

   @Generated
   @Override
   public ResourceLocationBridge method3() {
      return this.field6;
   }

   @Generated
   @Override
   public ResourceLocationBridge method2() {
      return this.field7;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public void setScale(float var1) {
      this.scale = var1;
   }
}

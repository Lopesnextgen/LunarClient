package com.moonsworth.lunar.client.network.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.client.framework.JsonFileConfig;
import com.moonsworth.lunar.client.keystrokes.Keystrokes2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump78;
import io.netty.util.internal.ConcurrentSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public final class PinnedServerManager extends com.moonsworth.lunar.client.framework.ItemMapHandler<String, Keystrokes2> implements JsonFileConfig {
   private final Set<String> field2 = new ConcurrentSet();
   private final Map<String, PinnedServerManager.Type> field3 = new ConcurrentHashMap<>();

   @Override
   public void init() {
      super.init();
      this.method4();
   }

   @Override
   protected Map<String, Keystrokes2> method3() {
      return new LinkedHashMap<>();
   }

   public void method2(Keystrokes2 var1) {
      if (!var1.method5() || !this.field2.contains(var1.method2())) {
         super.method2().put(var1.method2(), var1);
      }
   }

   public boolean method3(Bridge3_19 var1) {
      Keystrokes2 var2 = this.method2().get(var1.bridge$serverIP());
      return var2.method5();
   }

   private boolean method4(Keystrokes2 var1) {
      return var1.method5() && this.field2.contains(var1.method2()) ? false : var1.method4();
   }

   @Override
   public Map<String, Keystrokes2> method2() {
      LinkedHashMap var1 = new LinkedHashMap(super.method2());
      ThreadModuleDump78.retainIfValue(var1, this::method4);
      return var1;
   }

   public Map<String, Keystrokes2> method10() {
      LinkedHashMap var1 = new LinkedHashMap(super.method2());
      ThreadModuleDump78.retainIfValue(var1, this::method4);
      ThreadModuleDump78.retainIfValue(var1, Keystrokes2::method1);
      return var1;
   }

   @Override
   public String method5() {
      return "pinned_manager.json";
   }

   public void load(JsonObject var1) {
      for (JsonElement var3 : var1.getAsJsonArray("removed")) {
         this.field2.add(var3.getAsString());
      }

      JsonElement var7 = var1.get("packChoices");
      if (var7 != null && var7.isJsonObject()) {
         for (Entry var4 : var7.getAsJsonObject().entrySet()) {
            if (((JsonElement)var4.getValue()).isJsonPrimitive()) {
               try {
                  this.field3.put((String)var4.getKey(), PinnedServerManager.Type.valueOf(((JsonElement)var4.getValue()).getAsString()));
               } catch (IllegalArgumentException var6) {
               }
            }
         }
      }
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();

      for (String var4 : this.field2) {
         var2.add(new JsonPrimitive(var4));
      }

      var1.add("removed", var2);
      JsonObject var6 = new JsonObject();

      for (Entry var5 : this.field3.entrySet()) {
         var6.addProperty((String)var5.getKey(), ((PinnedServerManager.Type)var5.getValue()).name());
      }

      var1.add("packChoices", var6);
   }

   public void method9(Bridge3_19 var1) {
      this.field2.add(var1.bridge$serverIP());
      this.field3.remove(var1.bridge$serverIP());
      ThreadModuleDump63.method3().bridge$submit(this::method9);
   }

   public void method10(String var1, PinnedServerManager.Type var2) {
      if (this.field3.put(var1, var2) != var2) {
         ThreadModuleDump63.method3().bridge$submit(this::method9);
      }
   }

   public void method11(String var1) {
      if (this.field3.remove(var1) != null) {
         ThreadModuleDump63.method3().bridge$submit(this::method9);
      }
   }

   public void method12(Bridge3_19 var1) {
      PinnedServerManager.Type var2 = this.field3.get(var1.bridge$serverIP());
      if (var2 == PinnedServerManager.Type.ENABLED) {
         var1.bridge$enableResourcePack();
      } else if (var2 == PinnedServerManager.Type.DISABLED) {
         var1.bridge$disableResourcePack();
      }
   }

   public enum Type {
      ENABLED,
      DISABLED;
   }
}

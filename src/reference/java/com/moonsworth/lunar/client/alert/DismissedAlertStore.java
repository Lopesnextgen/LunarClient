package com.moonsworth.lunar.client.alert;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.config.override.AlertCard;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.util.internal.ConcurrentSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;

public class DismissedAlertStore extends ItemSetHandler<AlertCard> implements JsonFileConfig, Extension {
   private final GuiIterator field2 = new GuiIterator();
   private final Set<Integer> field3 = new ConcurrentSet();
   private Map<String, AnimatedValue> field4 = new HashMap<>();

   @Override
   public void init() {
      super.init();
      this.method4();
      this.method10();
   }

   @Override
   protected Set<AlertCard> method3() {
      return new LinkedHashSet<>();
   }

   public void method2(AlertCard var1) {
      super.method13().add(var1);
      this.method10();
   }

   public boolean method3(AlertCard var1) {
      return this.field3.contains(var1.getId());
   }

   @Override
   public String method5() {
      return "alert_manager.json";
   }

   public void load(JsonObject var1) {
      for (JsonElement var3 : var1.getAsJsonArray("dismissed")) {
         this.field3.add(var3.getAsInt());
      }
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();

      for (Integer var4 : this.field3) {
         var2.add(new JsonPrimitive(var4));
      }

      var1.add("dismissed", var2);
   }

   public void method6(Integer var1) {
      AlertCard var2 = this.method13().stream().filter(var1x -> var1x.getId() == var1).findFirst().orElse(null);
      if (var2 != null && var2.method4()) {
         this.field3.add(var2.getId());
         this.method10();
         ThreadModuleDump63.method3().bridge$submit(this::method9);
      }
   }

   public void method10() {
      JsonArray var1 = new JsonArray();

      for (AlertCard var3 : this.method13()) {
         var1.add(var3.provide());
      }

      this.field2.method3("alerts", var1);
      JsonArray var5 = new JsonArray();

      for (Integer var4 : this.field3) {
         var5.add(var4);
      }

      this.field2.method3("dismissed", var5);
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field2;
   }

   @Generated
   public Map<String, AnimatedValue> getColors() {
      return this.field4;
   }

   @Generated
   public void method9(Map<String, AnimatedValue> var1) {
      this.field4 = var1;
   }
}

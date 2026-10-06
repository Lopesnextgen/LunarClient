package com.moonsworth.lunar.client.network.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.framework.JsonFileConfig;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lombok.Generated;

public class PingEntryConfig implements LoadableHandler, JsonFileConfig {
   private Map<LocalDate, ServerAddressBookImpl> field1 = new ConcurrentHashMap<>();
   private ServerAddressBook field2;
   @Nullable
   private ServerAddressBook field3;
   @Nullable
   private ServerAddressBook field4;
   @Nullable
   private ServerAddressBook field5;
   @Nullable
   private ServerAddressBook field6;

   @Override
   public void close() {
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @Override
   public void init() {
      this.method4();
      this.method3();
      this.field2 = new ServerAddressBook();
      this.field3 = this.method4(LocalDate.now().minusMonths(1L), LocalDate.now().minusDays(1L));
      this.field4 = this.method4(LocalDate.now().minusWeeks(1L), LocalDate.now().minusDays(1L));
      this.field5 = this.method4(LocalDate.now().minusYears(1L), LocalDate.now().minusDays(1L));
      this.field6 = this.method4(null, LocalDate.now().minusDays(1L));
   }

   @Override
   public String method5() {
      return "statistics.json";
   }

   public void load(JsonObject var1) {
      if (var1.has("daily_stats") && var1.get("daily_stats").isJsonArray()) {
         for (JsonElement var4 : var1.get("daily_stats").getAsJsonArray()) {
            ServerAddressBookImpl var5 = (ServerAddressBookImpl)ThreadModuleDump48.field22.fromJson(var4, ServerAddressBookImpl.class);
            this.field1.put(var5.method1(), var5);
         }
      }
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();

      for (ServerAddressBookImpl var4 : this.field1.values()) {
         var2.add(ThreadModuleDump48.field22.toJsonTree(var4));
      }

      var1.add("daily_stats", var2);
   }

   public ServerAddressBookImpl method3() {
      return this.field1.computeIfAbsent(LocalDate.now(), var0 -> new ServerAddressBookImpl());
   }

   public ServerAddressBook method4(@Nullable LocalDate var1, @Nullable LocalDate var2) {
      Optional var3 = this.field1
         .values()
         .stream()
         .filter(var1x -> var1 == null || var1x.method1().isAfter(var1) || var1x.method1().equals(var1))
         .filter(var1x -> var2 == null || var1x.method1().isBefore(var2) || var1x.method1().equals(var2))
         .map(var0 -> (ServerAddressBook)var0)
         .reduce(ServerAddressBook::method1);
      return var3.orElse(new ServerAddressBook());
   }

   public void method5(Consumer<ServerAddressBook> var1) {
      var1.accept(this.field2);
      var1.accept(this.method3());
   }

   public ServerAddressBook method10() {
      return this.method3().method1(this.field3);
   }

   public ServerAddressBook method11() {
      return this.method3().method1(this.field5);
   }

   public ServerAddressBook method12() {
      return this.method3().method1(this.field4);
   }

   public ServerAddressBook method13() {
      return this.method3().method1(this.field6);
   }

   public void method14() {
      this.field2 = new ServerAddressBook();
   }

   @Generated
   public ServerAddressBook method15() {
      return this.field2;
   }
}

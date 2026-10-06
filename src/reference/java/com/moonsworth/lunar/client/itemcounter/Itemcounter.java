package com.moonsworth.lunar.client.itemcounter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.prompt.PromptAction;
import com.moonsworth.lunar.client.ui.prompt.BlockedActionPrompt;
import com.moonsworth.lunar.client.ui.prompt.MaliciousUrlPrompt;
import com.moonsworth.lunar.client.ui.prompt.MaliciousServerPrompt;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.network.transfer.TransferSrvResolver;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public class Itemcounter {
   private final AtomicBoolean field1 = new AtomicBoolean(false);
   private Set<Itemcounter.Data2> field2;
   private Set<Itemcounter.Data3> field3;

   public Itemcounter() {
      try {
         this.method1();
      } catch (Exception var2) {
         Slayer.error("Unexpected error while fetching malicious data!", var2);
      }
   }

   private void method1() {
      HttpRequest var1 = HttpRequest.newBuilder()
         .uri(URI.create(ServiceEndpoints.method7() + "/game/safety"))
         .header("X-Installation-Id", ThreadModuleDump80.installationId)
         .header("X-Overwolf-Muid", ThreadModuleDump80.overwolfMuid)
         .GET()
         .build();
      HttpClient.newHttpClient().sendAsync(var1, BodyHandlers.ofString()).thenAccept(var1x -> {
         JsonElement var2;
         try {
            var2 = JsonParser.parseString(var1x.body());
         } catch (Exception var6) {
            Slayer.error("Invalid JSON received for malicious data: " + var1x.body(), var6);
            return;
         }

         if (var2.isJsonObject()) {
            JsonObject var3 = var2.getAsJsonObject();
            HashSet var4 = new HashSet();
            HashSet var5 = new HashSet();
            this.method2(var3, "maliciousServers", (var1xx, var2x) -> var4.add(new Itemcounter.Data2(var1xx, var2x)));
            this.method2(var3, "maliciousUrls", (var1xx, var2x) -> {
               try {
                  var5.add(new Itemcounter.Data3(Pattern.compile(var1xx), var2x));
               } catch (Exception var4x) {
                  Slayer.error(String.format("Invalid regex '%s' in maliciousUrls list", var1xx), var4x);
               }
            });
            this.field2 = Set.copyOf(var4);
            this.field3 = Set.copyOf(var5);
            this.field1.set(true);
         }
      });
   }

   private void method2(JsonObject var1, String var2, BiConsumer<String, Itemcounter.Type4> var3) {
      if (var1.has(var2) && var1.get(var2).isJsonArray()) {
         for (JsonElement var5 : var1.getAsJsonArray(var2)) {
            if (var5.isJsonObject()) {
               JsonObject var6 = var5.getAsJsonObject();
               if (var6.has("pattern") && var6.get("pattern").isJsonPrimitive() && var6.has("behaviour") && var6.get("behaviour").isJsonArray()) {
                  String var7 = var6.get("pattern").getAsString();
                  String var8 = var6.getAsJsonArray("behaviour").get(0).getAsString();

                  Itemcounter.Type4 var9;
                  try {
                     var9 = Itemcounter.Type4.valueOf(var8);
                  } catch (IllegalArgumentException var11) {
                     Slayer.warn(String.format("Invalid behaviour '%s' for pattern '%s'", var8, var7), var11);
                     continue;
                  }

                  var3.accept(var7, var9);
               }
            }
         }
      }
   }

   public boolean method3(Bridge5Extension6 var1, URI var2) {
      Bridge5_12 var3 = ThreadModuleDump63.method3();
      String var4 = var2.toString();
      return this.method4(var2, () -> {
         if (var3.bridge$getGameSettings().bridge$isChatPromptLinks()) {
            var3.bridge$displayScreen(Bridge.method8().method33(var1, var4, var2, false));
         } else {
            ThreadModuleDump61.method7(var4, Initiator.INITIATOR_UNSPECIFIED);
            var3.bridge$displayScreen(var1);
         }
      }, () -> var3.bridge$displayScreen(var1));
   }

   public boolean method4(URI var1, Runnable var2, Runnable var3) {
      String var4 = var1.toString();
      if (!this.field1.get()) {
         return false;
      } else {
         Optional var5 = this.field3.stream().filter(var1x -> var1x.method1().matcher(var4).find()).findFirst();
         if (var5.isEmpty()) {
            return false;
         } else {
            Itemcounter.Data3 var6 = (Itemcounter.Data3)var5.get();
            Itemcounter.Type4 var7 = var6.method2();
            if (var7 == Itemcounter.Type4.PROMPT && !ThreadModuleDump63.method4().method41().method6().method86().contains(var4)) {
               this.method6(new MaliciousUrlPrompt(var4), "safety.maliciousUrl.prompt", var2, var3);
               return true;
            } else if (var7 == Itemcounter.Type4.BLOCK) {
               this.method7("safety.maliciousUrl.block", var3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public boolean method5(String var1, Runnable var2, Runnable var3) {
      if (!this.field1.get()) {
         return false;
      } else {
         Optional var4 = this.field2.stream().filter(var1x -> TransferSrvResolver.method1(var1x.method1(), var1)).findFirst();
         if (var4.isEmpty()) {
            return false;
         } else {
            Itemcounter.Data2 var5 = (Itemcounter.Data2)var4.get();
            Itemcounter.Type4 var6 = var5.method2();
            if (var6 == Itemcounter.Type4.PROMPT && !ThreadModuleDump63.method4().method41().method6().method85().contains(var1)) {
               this.method6(new MaliciousServerPrompt(var1), "safety.maliciousServer.prompt", var2, var3);
               return true;
            } else if (var6 == Itemcounter.Type4.BLOCK) {
               this.method7("safety.maliciousServer.block", var3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private void method6(PromptAction var1, String var2, Runnable var3, Runnable var4) {
      TranslationManager var5 = ThreadModuleDump63.method4().method67();
      com.moonsworth.lunar.client.ui.prompt.ActivePrompt var6 = ThreadModuleDump63.method4().method92();
      var6.method1(var1);
      ThreadModuleDump63.method3()
         .bridge$displayScreen(
            Bridge.method8()
               .method34(
                  var5.method2(var2, "header"), var5.method2(var2, "warning"), var5.method2(var2, "confirmButton"), var5.method2(var2, "denyButton"), () -> {
                     var6.method2();
                     var3.run();
                  }, () -> {
                     var6.method2();
                     var4.run();
                  }
               )
         );
   }

   private void method7(String var1, Runnable var2) {
      TranslationManager var3 = ThreadModuleDump63.method4().method67();
      com.moonsworth.lunar.client.ui.prompt.ActivePrompt var4 = ThreadModuleDump63.method4().method92();
      var4.method1(new BlockedActionPrompt());
      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method35(() -> {
         var4.method2();
         var2.run();
      }, var3.method2(var1, "header"), var3.method2(var1, "warning")));
   }

   protected class Data2 {
      private final String field1;
      private final Itemcounter.Type4 field2;

      protected Data2(String var1, Itemcounter.Type4 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String method1() {
         return this.field1;
      }

      public Itemcounter.Type4 method2() {
         return this.field2;
      }
   }

   protected class Data3 {
      private final Pattern field1;
      private final Itemcounter.Type4 field2;

      protected Data3(Pattern var1, Itemcounter.Type4 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Pattern method1() {
         return this.field1;
      }

      public Itemcounter.Type4 method2() {
         return this.field2;
      }
   }

   protected enum Type4 {
      PROMPT,
      BLOCK;
   }
}

package com.moonsworth.lunar.client.network.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.prompt.PromptAction;
import com.moonsworth.lunar.client.ui.prompt.BlockedActionPrompt;
import com.moonsworth.lunar.client.ui.prompt.MaliciousUrlPrompt;
import com.moonsworth.lunar.client.ui.prompt.MaliciousServerPrompt;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.network.transfer.TransferSrvResolver;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LaunchOptions;
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

public class MaliciousListFetcher {
   private final AtomicBoolean field1 = new AtomicBoolean(false);
   private Set<MaliciousListFetcher.MaliciousServer> field2;
   private Set<MaliciousListFetcher.MaliciousUrl> field3;

   public MaliciousListFetcher() {
      try {
         this.method1();
      } catch (Exception exception2) {
         LunarLogger.error("Unexpected error while fetching malicious data!", exception2);
      }
   }

   private void method1() {
      HttpRequest httprequest1 = HttpRequest.newBuilder()
         .uri(URI.create(ServiceEndpoints.method7() + "/game/safety"))
         .header("X-Installation-Id", LaunchOptions.field1)
         .header("X-Overwolf-Muid", LaunchOptions.field2)
         .GET()
         .build();
      HttpClient.newHttpClient().sendAsync(httprequest1, BodyHandlers.ofString()).thenAccept(arg1x -> {
         JsonElement element2;
         try {
            element2 = JsonParser.parseString(arg1x.body());
         } catch (Exception exception6) {
            LunarLogger.error("Invalid JSON received for malicious data: " + arg1x.body(), exception6);
            return;
         }

         if (element2.isJsonObject()) {
            JsonObject json3 = element2.getAsJsonObject();
            HashSet set4 = new HashSet();
            HashSet set5 = new HashSet();
            this.method2(json3, "maliciousServers", (arg1xx, arg2x) -> set4.add(new MaliciousListFetcher.MaliciousServer(arg1xx, arg2x)));
            this.method2(json3, "maliciousUrls", (arg1xx, arg2x) -> {
               try {
                  set5.add(new MaliciousListFetcher.MaliciousUrl(Pattern.compile(arg1xx), arg2x));
               } catch (Exception exception4x) {
                  LunarLogger.error(String.format("Invalid regex '%s' in maliciousUrls list", arg1xx), exception4x);
               }
            });
            this.field2 = Set.copyOf(set4);
            this.field3 = Set.copyOf(set5);
            this.field1.set(true);
         }
      });
   }

   private void method2(JsonObject json1, String text2, BiConsumer<String, MaliciousListFetcher.SafetyAction> biconsumer3) {
      if (json1.has(text2) && json1.get(text2).isJsonArray()) {
         for (JsonElement element5 : json1.getAsJsonArray(text2)) {
            if (element5.isJsonObject()) {
               JsonObject json6 = element5.getAsJsonObject();
               if (json6.has("pattern") && json6.get("pattern").isJsonPrimitive() && json6.has("behaviour") && json6.get("behaviour").isJsonArray()) {
                  String text7 = json6.get("pattern").getAsString();
                  String text8 = json6.getAsJsonArray("behaviour").get(0).getAsString();

                  MaliciousListFetcher.SafetyAction type49;
                  try {
                     type49 = MaliciousListFetcher.SafetyAction.valueOf(text8);
                  } catch (IllegalArgumentException illegalargumentexception11) {
                     LunarLogger.warn(String.format("Invalid behaviour '%s' for pattern '%s'", text8, text7), illegalargumentexception11);
                     continue;
                  }

                  biconsumer3.accept(text7, type49);
               }
            }
         }
      }
   }

   public boolean method3(GuiScreenBridge bridge5extension61, URI uri2) {
      MinecraftBridge bridge5_123 = Ref.method3();
      String text4 = uri2.toString();
      return this.method4(uri2, () -> {
         if (bridge5_123.bridge$getGameSettings().bridge$isChatPromptLinks()) {
            bridge5_123.bridge$displayScreen(Bridge.method8().method33(bridge5extension61, text4, uri2, false));
         } else {
            BrowserUtils.method7(text4, Initiator.INITIATOR_UNSPECIFIED);
            bridge5_123.bridge$displayScreen(bridge5extension61);
         }
      }, () -> bridge5_123.bridge$displayScreen(bridge5extension61));
   }

   public boolean method4(URI uri1, Runnable runnable2, Runnable runnable3) {
      String text4 = uri1.toString();
      if (!this.field1.get()) {
         return false;
      } else {
         Optional optional5 = this.field3.stream().filter(arg1x -> arg1x.method1().matcher(text4).find()).findFirst();
         if (optional5.isEmpty()) {
            return false;
         } else {
            MaliciousListFetcher.MaliciousUrl data36 = (MaliciousListFetcher.MaliciousUrl)optional5.get();
            MaliciousListFetcher.SafetyAction type47 = data36.method2();
            if (type47 == MaliciousListFetcher.SafetyAction.PROMPT && !Ref.method4().method41().method6().method86().contains(text4)) {
               this.method6(new MaliciousUrlPrompt(text4), "safety.maliciousUrl.prompt", runnable2, runnable3);
               return true;
            } else if (type47 == MaliciousListFetcher.SafetyAction.BLOCK) {
               this.method7("safety.maliciousUrl.block", runnable3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public boolean method5(String text1, Runnable runnable2, Runnable runnable3) {
      if (!this.field1.get()) {
         return false;
      } else {
         Optional optional4 = this.field2.stream().filter(arg1x -> TransferSrvResolver.method1(arg1x.method1(), text1)).findFirst();
         if (optional4.isEmpty()) {
            return false;
         } else {
            MaliciousListFetcher.MaliciousServer data25 = (MaliciousListFetcher.MaliciousServer)optional4.get();
            MaliciousListFetcher.SafetyAction type46 = data25.method2();
            if (type46 == MaliciousListFetcher.SafetyAction.PROMPT && !Ref.method4().method41().method6().method85().contains(text1)) {
               this.method6(new MaliciousServerPrompt(text1), "safety.maliciousServer.prompt", runnable2, runnable3);
               return true;
            } else if (type46 == MaliciousListFetcher.SafetyAction.BLOCK) {
               this.method7("safety.maliciousServer.block", runnable3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private void method6(PromptAction animations1, String text2, Runnable runnable3, Runnable runnable4) {
      TranslationManager foghandler285 = Ref.method4().method67();
      com.moonsworth.lunar.client.ui.prompt.ActivePrompt animations6 = Ref.method4().method92();
      animations6.method1(animations1);
      Ref.method3()
         .bridge$displayScreen(
            Bridge.method8()
               .method34(
                  foghandler285.method2(text2, "header", new Object[0]),
                  foghandler285.method2(text2, "warning", new Object[0]),
                  foghandler285.method2(text2, "confirmButton", new Object[0]),
                  foghandler285.method2(text2, "denyButton", new Object[0]),
                  () -> {
                     animations6.method2();
                     runnable3.run();
                  },
                  () -> {
                     animations6.method2();
                     runnable4.run();
                  }
               )
         );
   }

   private void method7(String text1, Runnable runnable2) {
      TranslationManager foghandler283 = Ref.method4().method67();
      com.moonsworth.lunar.client.ui.prompt.ActivePrompt animations4 = Ref.method4().method92();
      animations4.method1(new BlockedActionPrompt());
      Ref.method3().bridge$displayScreen(Bridge.method8().method35(() -> {
         animations4.method2();
         runnable2.run();
      }, foghandler283.method2(text1, "header", new Object[0]), foghandler283.method2(text1, "warning", new Object[0])));
   }

   protected class MaliciousServer {
      private final String field1;
      private final MaliciousListFetcher.SafetyAction field2;

      protected MaliciousServer(String text1, MaliciousListFetcher.SafetyAction type42) {
         this.field1 = text1;
         this.field2 = type42;
      }

      public String method1() {
         return this.field1;
      }

      public MaliciousListFetcher.SafetyAction method2() {
         return this.field2;
      }
   }

   protected class MaliciousUrl {
      private final Pattern field1;
      private final MaliciousListFetcher.SafetyAction field2;

      protected MaliciousUrl(Pattern pattern1, MaliciousListFetcher.SafetyAction type42) {
         this.field1 = pattern1;
         this.field2 = type42;
      }

      public Pattern method1() {
         return this.field1;
      }

      public MaliciousListFetcher.SafetyAction method2() {
         return this.field2;
      }
   }

   protected enum SafetyAction {
      PROMPT,
      BLOCK;

      SafetyAction() {
      }
   }
}

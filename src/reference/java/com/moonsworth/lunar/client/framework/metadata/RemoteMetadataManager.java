package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import com.moonsworth.lunar.client.config.JsonFileConfig;
import com.moonsworth.lunar.client.framework.metadata.SentryFilterConsumer;
import com.moonsworth.lunar.client.framework.metadata.ClientSettingsConsumer;
import com.moonsworth.lunar.client.framework.metadata.PinnedServerConsumer;
import com.moonsworth.lunar.client.framework.metadata.FeatureFlagConsumer;
import com.moonsworth.lunar.client.framework.metadata.StarServerConsumer;
import com.moonsworth.lunar.client.framework.metadata.LanguageOverrideConsumer;
import com.moonsworth.lunar.client.framework.metadata.StoreBadgeConsumer;
import com.moonsworth.lunar.client.framework.metadata.LinkOverrideConsumer;
import com.moonsworth.lunar.client.framework.metadata.ModSettingsConsumer;
import com.moonsworth.lunar.client.framework.metadata.ServerIntegrationConsumer;
import com.moonsworth.lunar.client.framework.metadata.BlogPostConsumer;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RemoteMetadataManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, MetadataConsumer> implements JsonFileConfig, LoadingStage {
   private JsonObject field2 = new JsonObject();

   @Override
   protected Map<String, MetadataConsumer> method3() {
      HashMap var1 = new HashMap();
      this.method6("blogPosts", new BlogPostConsumer(), var1);
      this.method6("alert", new com.moonsworth.lunar.client.framework.metadata.AlertConsumer(), var1);
      this.method6("modSettings", new ModSettingsConsumer(), var1);
      this.method6("clientSettings", new ClientSettingsConsumer(), var1);
      this.method6("pinnedServers", new PinnedServerConsumer(), var1);
      this.method6("serverIntegration", new ServerIntegrationConsumer(), var1);
      this.method6("starServers", new StarServerConsumer(), var1);
      this.method6("featureFlag", new FeatureFlagConsumer(), var1);
      this.method6("sentryFilteredExceptions", new SentryFilterConsumer(), var1);
      this.method6("links", new LinkOverrideConsumer(), var1);
      this.method6("langOverride", new LanguageOverrideConsumer(), var1);
      this.method6("storeBadge", new StoreBadgeConsumer(), var1);
      this.method7(var1);
      return var1;
   }

   @Override
   public String method5() {
      return "metadata_fallback.json";
   }

   @Override
   public void load(JsonObject var1) {
      var1 = var1.get("metaData").getAsJsonObject();
      boolean var2 = false;

      for (Entry var4 : this.method3().entrySet()) {
         String var5 = (String)var4.getKey();
         Consumer var6 = (Consumer)var4.getValue();
         if (var1.has(var5) && !this.field2.has(var5)) {
            try {
               JsonElement var7 = var1.get(var5);
               var6.accept(var7);
               this.field2.add(var5, var7);
            } catch (Exception var8) {
               var2 = true;
               Inventorymod2.method5(var8, "GameMeta Processor");
            }
         }
      }

      if (var2 && var1.has("webCopy")) {
         this.method4();
      } else {
         this.<init>();
      }
   }

   @Override
   public void method1(JsonObject var1) {
      var1.add("metaData", this.field2);
   }

   public MetadataConsumer method4(String var1) {
      return (MetadataConsumer)this.method3().get(var1);
   }

   @Nullable
   public String method10() {
      return this.method4("storeBadge") instanceof StoreBadgeConsumer var2 ? var2.method2() : null;
   }

   private MetadataConsumer method6(String var1, MetadataConsumer var2, HashMap<String, MetadataConsumer> var3) {
      var2.setName(var1);
      var3.put(var1, var2);
      return var2;
   }

   public void method7(Map<String, MetadataConsumer> var1) {
      if (var1 == null) {
         var1 = this.method3();
      }

      this.field2 = new JsonObject();

      try {
         String var2 = URLEncoder.encode(ThreadModuleDump48.field1, StandardCharsets.UTF_8);
         String var3 = URLEncoder.encode(ThreadModuleDump48.field3, StandardCharsets.UTF_8);
         String var4 = URLEncoder.encode(LunarBuildData.field3, StandardCharsets.UTF_8);
         String var5 = URLEncoder.encode(LunarBuildData.field1, StandardCharsets.UTF_8);
         String var6;
         if (Client.method109() != null && Client.method109().method67() != null) {
            var6 = Client.method109().method67().method21().getBase();
         } else {
            var6 = ThreadModuleDump80.language.split("_")[0];
         }

         String var7 = URLEncoder.encode(var6, StandardCharsets.UTF_8);
         HttpRequest var8 = HttpRequest.newBuilder()
            .uri(
               URI.create(
                  ServiceEndpoints.method7()
                     + "/game/metadata?os="
                     + var2
                     + "&version="
                     + Client.method19()
                     + "&arch="
                     + var3
                     + "&gitCommit="
                     + var4
                     + "&branch="
                     + var5
                     + "&language="
                     + var7
               )
            )
            .header("X-Installation-Id", ThreadModuleDump80.installationId)
            .header("X-Overwolf-Muid", ThreadModuleDump80.overwolfMuid)
            .header("sentry-trace", ThreadModuleDump80.sentryTraceId)
            .GET()
            .build();
         Map var9 = var1;
         HttpClient.newHttpClient().sendAsync(var8, BodyHandlers.ofString()).thenAccept(var2x -> {
            JsonElement var3x;
            try {
               var3x = new JsonParser().parse(var2x.body());
            } catch (Exception var8x) {
               var8x.printStackTrace();
               this.method4();
               return;
            }

            if (!var3x.isJsonObject()) {
               this.method4();
            } else {
               boolean var4x = false;

               for (String var6x : var9.keySet()) {
                  if (var3x.getAsJsonObject().has(var6x)) {
                     var4x = true;
                     break;
                  }
               }

               if (!var4x) {
                  this.method4();
               } else {
                  JsonObject var9x = new JsonObject();

                  try {
                     var9x.addProperty("webCopy", true);
                     var9x.add("metaData", var3x.getAsJsonObject());
                     this.load(var9x);
                  } catch (IOException var7x) {
                     Inventorymod2.method5(var7x, "GameMeta Processor - Download");
                     this.method4();
                  }
               }
            }
         });
      } catch (Exception var10) {
         Inventorymod2.method5(var10, "GameMeta Processor - Outbound Request");
         this.method4();
      }
   }

   public void method8(JsonObject var1, String var2, Object var3, @NotNull ClientOption var4, Alert2 var5, Predicate<@Nullable String> var6) {
      Optional var7 = var4.method14(var1.get(var2));
      if (!var7.isEmpty()) {
         if (var6 == null) {
            var5.method1(var3, AlertType.SERVER, var7.get());
         } else {
            var5.method6(var6, var7.get());
         }
      }
   }

   @Override
   public String getCategory() {
      return "Metadata";
   }

   @Override
   public Set<? extends LoadableResource> method1() {
      return new HashSet<>(this.method3().values());
   }
}

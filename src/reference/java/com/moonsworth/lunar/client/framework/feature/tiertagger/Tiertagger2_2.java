package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDumpThread;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Tiertagger2_2 {
   private ThreadModuleDumpThread<UUID, Tiertagger4> field1;

   @NotNull
   public abstract Tiertagger2.Tiertagger2$Data method1();

   @Nullable
   protected abstract Tiertagger4 method2(UUID var1);

   @NotNull
   public abstract List<Tiertagger_2> method3();

   @Nullable
   public Tiertagger4 method4(UUID var1) {
      if (this.field1 == null) {
         this.field1 = new ThreadModuleDumpThread<>(
               TierTagger.field8, 150L, 2, this::method2, Caffeine.newBuilder().expireAfterWrite(15L, TimeUnit.MINUTES).initialCapacity(150).maximumSize(650L)
            )
            .method1(7, TimeUnit.SECONDS);
         this.field1.setName(this.method8() + " Fetch Thread");
         this.field1.start();
      }

      return this.field1.get(var1);
   }

   @Nullable
   public Tiertagger4 method5(UUID var1) {
      return this.method2(var1);
   }

   public void clearCache() {
      if (this.field1 != null) {
         this.field1.invalidateAll();
      }
   }

   public void method6() {
      if (this.field1 != null) {
         this.field1.method5();
      }
   }

   public String method7() {
      return this.field1 == null ? "N/A" : this.field1.getInfo();
   }

   protected String userAgent() {
      return "LunarClient/" + LunarBuildData.field2;
   }

   protected String method8() {
      String var1 = URI.create(this.method1().method2()).getHost();
      return var1 == null ? "Tier" : var1;
   }

   public final Optional<Tiertagger_2> method9(String var1) {
      return this.method3().stream().filter(var1x -> var1x.apiName().equalsIgnoreCase(var1)).findFirst();
   }

   public final CompletableFuture<Optional<JsonElement>> method10(String var1, boolean var2) {
      if (var1.endsWith("/")) {
         var1 = var1.substring(0, var1.length() - 1);
      }

      String var3 = var1;
      Builder var4 = HttpRequest.newBuilder().uri(URI.create(var3)).timeout(Duration.ofSeconds(10L)).header("User-Agent", this.userAgent());
      Function var5 = var1x -> {
         if (var1x == null) {
            Slayer.method5("httpGet failed: %s (null response)", var3);
            return Optional.empty();
         }

         int var2x = var1x.statusCode();
         if (var2x != 200) {
            if (var2x != 404 && var2x != 400) {
               Slayer.method5("httpGet failed: %s (status %s)", var3, var2x);
            }

            return var2x != 404 && var2x != 422 && var2x != 400 ? Optional.empty() : Optional.of(new JsonObject());
         } else {
            try {
               return Optional.of(JsonParser.parseString((String)var1x.body()));
            } catch (Exception var4x) {
               Slayer.method9(var4x, "httpGet json parse failed %s: %s", var3, var1x.body());
               return Optional.of(new JsonObject());
            }
         }
      };
      if (var2) {
         return TierTagger.field9
            .sendAsync(var4.GET().build(), BodyHandlers.ofString())
            .<Optional<JsonElement>>thenApply(var5)
            .orTimeout(10L, TimeUnit.SECONDS)
            .exceptionally(var1x -> {
               Slayer.method9(var1x, "httpGet exception %s", var3);
               return Optional.empty();
            });
      }

      CompletableFuture var6 = new CompletableFuture();

      try {
         HttpResponse var7 = TierTagger.field9.send(var4.GET().build(), BodyHandlers.ofString());
         var6.complete((Optional)var5.apply(var7));
      } catch (Exception var8) {
         Slayer.method9(var8, "httpGet exception %s", var3);
         var6.complete(Optional.empty());
      }

      return var6;
   }
}

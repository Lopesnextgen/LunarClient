package com.moonsworth.lunar.client.render.texture;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge4_8;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2.Data6;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;

public abstract class ModelTextureUpdater {
   public boolean method1(Collection<String> var1) {
      boolean var2 = false;

      for (String var4 : var1) {
         try {
            var2 |= this.method5(var4);
         } catch (Throwable var6) {
            Slayer.method9(var6, "[TextureUpdater] Error while updating %s", new Object[]{var4});
         }
      }

      return var2;
   }

   protected Set<String> method2(Bridge4_8 var1, String var2) {
      HashSet var3 = new HashSet();
      var3.add(var2);
      ThreadModuleDump63.method4()
         .method40()
         .method84()
         .method71()
         .method11()
         .flatMap(var1x -> var1x.method1(var1))
         .<Collection<? extends E>>map(Data6::method4)
         .ifPresent(var3::addAll);
      if (ThreadModuleDump63.MC_VERSION < 1) {
         return var3;
      }

      if (ThreadModuleDump63.MC_VERSION >= 6 && var2.contains("ancient_debris_side")) {
         var2 = "ancient_debris";
      }

      HashSet var4 = new HashSet();

      try {
         this.method4("blockstates", var2).forEach(var2x -> this.method3(null, var2x, var4));
      } catch (IOException var9) {
         return var3;
      }

      for (String var6 : var4) {
         try {
            this.method4("models/block", var6).forEach(var1x -> {
               if (var1x.isJsonObject()) {
                  ThreadModuleDump9.findJsonObject(var1x.getAsJsonObject(), "textures").ifPresent(var1xx -> {
                     for (Entry var3x : var1xx.entrySet()) {
                        if (!"particle".equals(var3x.getKey())) {
                           String var4x;
                           if (((JsonElement)var3x.getValue()).isJsonObject()) {
                              var4x = ((JsonElement)var3x.getValue()).getAsJsonObject().get("sprite").getAsString();
                           } else {
                              var4x = ((JsonElement)var3x.getValue()).getAsString();
                           }

                           var3.add(TexturePathResolver.method12(var4x));
                        }
                     }
                  });
               }
            });
         } catch (IOException var8) {
         }
      }

      return var3;
   }

   private void method3(@Nullable String var1, JsonElement var2, Set<String> var3) {
      if (var2.isJsonObject()) {
         for (Entry var5 : var2.getAsJsonObject().entrySet()) {
            this.method3((String)var5.getKey(), (JsonElement)var5.getValue(), var3);
         }
      } else if (var2.isJsonArray()) {
         for (JsonElement var7 : var2.getAsJsonArray()) {
            this.method3(null, var7, var3);
         }
      } else if (var2.isJsonPrimitive() && "model".equals(var1)) {
         var3.add(var2.getAsString());
      }
   }

   private List<JsonElement> method4(String var1, String var2) {
      String var3 = "minecraft";
      if (var2.contains(":")) {
         String[] var4 = var2.split(":");
         var3 = var4[0];
         var2 = var4[1];
      }

      if (var2.contains("/")) {
         var2 = var2.split("/")[1];
      }

      if (!var1.endsWith("/")) {
         var1 = var1 + "/";
      }

      ResourceLocationBridge var5 = ResourceLocationBridge.create(var3, var1 + var2 + ".json");
      return ThreadModuleDump63.method3()
         .bridge$getResourceManager()
         .bridge$getAllResources(var5)
         .stream()
         .map(var0 -> JsonParser.parseReader(new InputStreamReader(var0.bridge$getInputStream())))
         .filter(Objects::nonNull)
         .toList();
   }

   protected abstract boolean method5(String var1);

   public void method6() {
   }

   public void method7() {
   }
}

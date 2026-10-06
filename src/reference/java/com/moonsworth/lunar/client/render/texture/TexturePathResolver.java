package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTickEnd;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.render.texture.TextureProcessor;

public class TexturePathResolver extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   @NotNull
   private static TextureProcessor field7 = new ClickImpl();
   private final Map<String, List<TextureProcessor>> field8 = new ConcurrentHashMap<>();
   private final com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2 field9 = ThreadModuleDump63.hasModule("optifine")
      ? (com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2)this.method3(
         com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2.class
      )
      : null;
   private volatile boolean field10 = false;

   private TexturePathResolver() {
      if (ThreadModuleDump63.hasModule("sodium")) {
         this.handle(EventTickEnd.class, var1 -> {
            if (this.field10) {
               this.field10 = false;
               if (ThreadModuleDump63.method3() != null) {
                  Bridge14_3 var2 = ThreadModuleDump63.method3().bridge$getLevelRenderer();
                  if (var2 != null) {
                     var2.bridge$reloadChunks();
                  }
               }
            }
         });
      }
   }

   public static void method1(com.moonsworth.lunar.client.render.texture.ModelTextureUpdater var0) {
      if (var0 != null) {
         ThreadModuleDump37.method11(() -> {
            com.moonsworth.lunar.client.render.texture.ModelTextureUpdater var1 = field7;
            field7 = var0;
            if (var1 instanceof ClickImpl var2) {
               var0.method1(var2.method2());
               var2.method2().clear();
            }
         });
      }
   }

   protected void onDisable() {
      if (!this.field8.isEmpty()) {
         HashSet var1 = new HashSet<>(this.field8.keySet());
         this.field8.clear();
         this.method4(var1);
      }
   }

   public void method2(Set<? extends TextureProcessor> var1) {
      HashSet var2 = new HashSet();

      for (String var4 : this.field8.keySet()) {
         for (TextureProcessor var7 : this.field8.get(var4)) {
            if (var1.contains(var7)) {
               var2.add(var4);
               break;
            }
         }
      }

      if (!var2.isEmpty()) {
         field7.method1(var2);
         this.field10 = true;
      }
   }

   public void method5() {
      if (!this.field8.isEmpty()) {
         field7.method1(this.field8.keySet());
         this.field10 = true;
      }
   }

   public void method4(Set<String> var1) {
      if (!var1.isEmpty()) {
         field7.method1(var1);
         this.field10 = true;
      }
   }

   public void method5(String var1, Supplier<TextureProcessor.Extension> var2) {
      List var3 = this.field8.get(var1);
      if (var3 != null && !var3.isEmpty()) {
         TextureProcessor.Extension var4 = (TextureProcessor.Extension)var2.get();

         for (int var5 = var3.size() - 1; var5 >= 0; var5--) {
            ((TextureProcessor)var3.get(var5)).process(var4);
         }
      }
   }

   public boolean method6(String var1) {
      List var2 = this.field8.get(var1);
      return var2 != null && !var2.isEmpty();
   }

   public void method7(String var1, TextureProcessor var2, boolean var3) {
      var1 = method12(var1);
      List var4 = this.field8.computeIfAbsent(var1, var0 -> new CopyOnWriteArrayList<>());
      if (!var4.contains(var2)) {
         var4.add(var2);
         if (var3) {
            this.method4(Set.of(var1));
         }
      }
   }

   public void method8(String var1, TextureProcessor var2, boolean var3) {
      var1 = method12(var1);
      List var4 = this.field8.get(var1);
      if (var4 != null) {
         boolean var5 = var4.remove(var2) && var3;
         if (var4.isEmpty()) {
            this.field8.remove(var1);
         }

         if (var5) {
            this.method4(Set.of(var1));
         }
      }
   }

   public void method9(TextureProcessor var1, boolean var2) {
      HashSet var3 = new HashSet();
      Iterator var4 = this.field8.entrySet().iterator();

      while (var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         String var6 = (String)var5.getKey();
         List var7 = (List)var5.getValue();
         if (var7 != null && var7.remove(var1)) {
            if (var2) {
               var3.add(var6);
            }

            if (var7.isEmpty()) {
               var4.remove();
            }
         }
      }

      if (!var3.isEmpty()) {
         this.method4(var3);
      }
   }

   public void method10(String var1, boolean var2) {
      var1 = method12(var1);
      List var3 = this.field8.remove(var1);
      if (var3 != null) {
         boolean var4 = var2 && !var3.isEmpty();
         var3.clear();
         if (var4) {
            this.method4(Set.of(var1));
         }
      }
   }

   public Optional<com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2> method11() {
      return this.field9 != null && this.field9.isEnabled() ? Optional.of(this.field9) : Optional.empty();
   }

   public static String method12(String var0) {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         int var1 = var0.indexOf(":");
         if (var1 != -1) {
            var0 = var0.substring(var1 + 1);
         }

         var0 = var0.replace("blocks/", "");
         var0 = var0.replace("block/", "");
         var0 = var0.replace("items/", "");
         return var0.replace("item/", "");
      } else {
         if (!var0.contains(":")) {
            var0 = "minecraft:" + var0;
         }

         if (ThreadModuleDump63.MC_VERSION > 5) {
            var0 = var0.replace("minecraft:blocks/", "minecraft:block/");
            var0 = var0.replace("minecraft:items/", "minecraft:item/");
         } else {
            var0 = var0.replace("minecraft:block/", "minecraft:blocks/");
            var0 = var0.replace("minecraft:item/", "minecraft:items/");
         }

         return var0;
      }
   }

   public void method13() {
      field7.method6();
   }

   public void method15() {
      field7.method7();
   }
}

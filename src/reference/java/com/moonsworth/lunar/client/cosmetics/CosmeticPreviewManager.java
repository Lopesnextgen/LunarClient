package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.gameipc.store.v1.StartStorePreviewSessionPush;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension611;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverScreenLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.driver.core.gui.mixin.AccountBridgeLegacy;
import com.moonsworth.lunar.client.driver.core.highlight.StorePriceEntryLegacy;
import com.moonsworth.lunar.client.driver.core.highlight.StorePriceLegacy;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class CosmeticPreviewManager extends ItemSetHandler<Void> implements Extension, EventRegistrar {
   private static final int field2 = 2;
   private static final int field3 = 4;
   private final GuiIterator field4 = new GuiIterator();
   private List<StorePriceEntryLegacy> field5 = null;
   private StorePriceEntryLegacy field6 = null;
   private boolean field7;
   private boolean field8;
   private int field9;

   @Override
   protected Set<Void> method3() {
      return new HashSet<>();
   }

   @Override
   public void init() {
      this.handle(ScreenChangeEvent.class, var1 -> {
         if (var1.method1() instanceof Bridge5Extension610) {
            if (this.field7 && ThreadModuleDump63.method8() == null && ThreadModuleDump63.method3().bridge$getIntegratedServer() == null) {
               this.method7();
            }
         } else {
            if (this.field7 && ThreadModuleDump63.method8() != null && var1.method1() instanceof Bridge5Extension611) {
               var1.cancel();
               if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method61() == DriverRouteRegistryLegacy.field23) {
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.NULL);
               } else {
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field23);
               }
            }
         }
      });
      this.handle(DisconnectEvent.class, var1 -> {
         if (this.field7) {
            this.method9();
         }
      });
      this.handle(
         EventEverySecond.class,
         var1 -> {
            if (this.field7 && ThreadModuleDump63.method8() != null && ThreadModuleDump63.method3().bridge$getIntegratedServer() == null) {
               Slayer.method8("Preview", "Preview session survived into another world, ending it", new Object[0]);
               this.method9();
            }

            if (this.field8 && this.field9 < 4) {
               if (ThreadModuleDump63.method8() == null && ThreadModuleDump63.method3().bridge$getIntegratedServer() == null) {
                  this.field9++;
                  this.field8 = !ThreadModuleDump63.method3().bridge$deleteWorld(method17());
                  if (this.field8 && this.field9 >= 4) {
                     ThreadModuleDump63.method4()
                        .method69()
                        .method2(
                           com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewTitle", new Object[0]),
                           com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewWorldNotRemoved", new Object[0])
                        );
                  }
               }
            }
         }
      );
      this.handle(
         KeybindEvent.class,
         var1 -> {
            if (var1.method10() == KeyCode.KEY_ESCAPE
               && var1.method11() == InputActionLegacy.DOWN
               && this.field7
               && com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method61() == DriverRouteRegistryLegacy.field23) {
               com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.NULL);
            }
         }
      );
   }

   public void method2(List<StorePriceEntryLegacy> var1) {
      if (this.field5 == null) {
         this.field5 = new ArrayList<>();
      }

      for (StorePriceEntryLegacy var3 : var1) {
         this.method3(var3);
      }

      if (this.field5.isEmpty()) {
         this.field5 = null;
         ThreadModuleDump63.method4()
            .method69()
            .method2(
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewTitle", new Object[0]),
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewEmpty", new Object[0])
            );
         Slayer.method4("Preview", "Store preview session had no cosmetics to show.", new Object[0]);
         this.method16();
      } else {
         this.method16();
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var4 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
         if (var4 != null) {
            var4.method19(DriverOverlayRegistryLegacy.field10);
         }
      }
   }

   private void method3(StorePriceEntryLegacy var1) {
      for (int var2 = 0; var2 < this.field5.size(); var2++) {
         StorePriceEntryLegacy var3 = this.field5.get(var2);
         if (var3.method2() == var1.method2()) {
            StorePriceEntryLegacy var4 = new StorePriceEntryLegacy(var3.id(), var1.method2(), var1.name(), var1.method3(), var1.method4(), var1.method5(), var1.method6());
            this.field5.set(var2, var4);
            if (this.field6 != null && this.field6.method2() == var1.method2()) {
               this.field6 = var4;
            }

            return;
         }
      }

      this.field5.add(var1);
   }

   public void method5() {
      if (this.field7 && this.field5 != null && !this.field5.isEmpty()) {
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var1 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
         if (var1 != null) {
            if (var1.method61() == DriverRouteRegistryLegacy.field23) {
               var1.method16(DriverRouteRegistryLegacy.field3);
               ThreadModuleDump63.method3().bridge$displayScreen(null);
            }

            var1.method19(DriverOverlayRegistryLegacy.field10);
         }
      }
   }

   public void method6() {
      if (!this.field7) {
         this.field5 = null;
         this.field6 = null;
         this.method16();
      }
   }

   public void method6(UUID var1) {
      if (!ThreadModuleDump63.method4().method43().method23()) {
         AccountBridgeLegacy.method2();
         ThreadModuleDump63.method4().method69().method3(com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewLogin", new Object[0]));
      } else if (this.field5 != null) {
         if (!this.field7 || ThreadModuleDump63.method8() != null) {
            this.field6 = this.field5.stream().filter(var1x -> var1x.id().equals(var1)).findFirst().orElse(null);
            if (this.field6 != null) {
               OutfitManager var2 = ThreadModuleDump63.method4().method55();
               Horsestats var3 = ThreadModuleDump63.method3().bridge$getSession();
               if (ThreadModuleDump63.method8() != null) {
                  if (this.field7) {
                     com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method21();
                     com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method56().method3(DriverScreenLegacy.NULL);
                     if (var3 != null) {
                        UUID var6 = var3.bridge$getProfile().getId();
                        List var5 = this.method10();
                        var2.method11(var6, var5);
                        if (ThreadModuleDump63.method7() != null) {
                           ThreadModuleDump63.method4().method88().method7(ThreadModuleDump63.method7());
                        }
                     }

                     this.method16();
                  }
               } else {
                  if (var3 != null) {
                     List var4 = this.method10();
                     var2.method11(var3.bridge$getProfile().getId(), var4);
                  }

                  this.field8 = !ThreadModuleDump63.method3().bridge$deleteWorld(method17());
                  this.field9 = 0;
                  this.field7 = true;
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method21();
                  com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field3);
                  ThreadModuleDump63.method3()
                     .bridge$joinWorld("Cosmetic Preview World", method17(), Bridge.method8().method82("Cosmetic Preview World"), this::method7);
                  this.method16();
               }
            }
         }
      }
   }

   private void method7() {
      if (this.field7) {
         Slayer.method8("Preview", "Could not open the preview world %s", new Object[]{method17()});
         this.method9();
         this.field8 = false;
         ThreadModuleDump63.method4().method24(true);
         ThreadModuleDump63.method4()
            .method69()
            .method2(
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewTitle", new Object[0]),
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewWorldFailed", new Object[0])
            );
      }
   }

   public void method8(UUID var1) {
      if (this.field5 != null) {
         StorePriceEntryLegacy var2 = this.field5.stream().filter(var1x -> var1x.id().equals(var1)).findFirst().orElse(null);
         if (var2 != null) {
            String var3 = "https://store.lunarclient.com/checkout/instant?package=" + var2.method2() + "&addedVia=game-package-preview";
            ThreadModuleDump61.method7(var3, Initiator.INITIATOR_STORE_PREVIEW);
         }
      }
   }

   public void method9() {
      if (this.field7) {
         Horsestats var1 = ThreadModuleDump63.method3().bridge$getSession();
         if (var1 != null) {
            OutfitManager var2 = ThreadModuleDump63.method4().method55();
            var2.method11(var1.bridge$getProfile().getId(), null);
            var2.method6();
         }

         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var3 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
         if (var3 != null) {
            var3.method21();
         }

         this.field7 = false;
         this.field6 = null;
         this.field5 = null;
         this.field8 = true;
         this.field9 = 0;
         this.method16();
      }
   }

   private List<CosmeticMetadata> method10() {
      ArrayList var1 = new ArrayList();
      CosmeticManager var2 = ThreadModuleDump63.method4().method53();
      if (this.field6 != null && this.field6.method3() != null) {
         Integer[] var3 = this.field6.method3();
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            int var6 = var3[var5];
            CosmeticMetadata var7 = var2.method48(var6, new JsonObject());
            if (var7 != null) {
               var1.add(var7);
            }
         }

         return var1;
      } else {
         return var1;
      }
   }

   public void method11(StartStorePreviewSessionPush var1) {
      if (this.method12()) {
         ThreadModuleDump63.method4()
            .method69()
            .method2(
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewTitle", new Object[0]),
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewLoading", new Object[0])
            );
         CompletableFuture var2;
         if (var1.getPackageId() != 0) {
            var2 = this.method14(var1.getPackageId(), var1.getBasketIdent(), var1.getCurrency()).thenApply(var0 -> {
               ArrayList var1x = new ArrayList();
               if (var0 != null) {
                  var1x.add(var0);
               }

               return var1x;
            });
         } else {
            var2 = this.method13(var1.getBasketIdent(), var1.getCurrency());
         }

         var2.thenAcceptAsync(var1x -> {
            if (this.method12()) {
               this.method2((List<StorePriceEntryLegacy>)var1x);
            }
         }, ThreadModuleDump37.method8()).exceptionally(var0 -> {
            Slayer.method8("Preview", "Failed to start preview session: " + var0.getMessage(), new Object[0]);
            return null;
         });
      }
   }

   private boolean method12() {
      if (ThreadModuleDump63.method8() != null && !this.field7) {
         ThreadModuleDump63.method4()
            .method69()
            .method2(
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewTitle", new Object[0]),
               com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("storePreviewInWorld", new Object[0])
            );
         Slayer.method4("Preview", "Cosmetics cannot be previewed while in a world.", new Object[0]);
         return false;
      } else {
         return true;
      }
   }

   private CompletableFuture<List<StorePriceEntryLegacy>> method13(String var1, String var2) {
      if (var1.isEmpty()) {
         return CompletableFuture.completedFuture(Collections.emptyList());
      }

      HttpRequest var3 = HttpRequest.newBuilder()
         .uri(URI.create(ServiceEndpoints.method7() + "/store/basket/info"))
         .header("X-Basket-Ident", var1)
         .GET()
         .build();
      return HttpClient.newHttpClient()
         .sendAsync(var3, BodyHandlers.ofString())
         .thenCompose(
            var3x -> {
               try {
                  JsonObject var4 = JsonParser.parseString(var3x.body()).getAsJsonObject();
                  JsonObject var5 = var4.getAsJsonObject("basket");
                  JsonArray var6 = var5.getAsJsonArray("items");
                  List var7 = var6.asList()
                     .stream()
                     .map(var0 -> var0.getAsJsonObject().get("id").getAsInt())
                     .map(var3xx -> this.method14(var3xx, var1, var2))
                     .toList();
                  return CompletableFuture.allOf(var7.toArray(CompletableFuture[]::new))
                     .thenApply(var1xx -> var7.stream().map(CompletableFuture::join).filter(Objects::nonNull).collect(Collectors.toList()));
               } catch (Exception var8) {
                  Slayer.method8("Preview", "Failed to parse basket response: " + var8.getMessage(), new Object[0]);
                  return CompletableFuture.completedFuture(Collections.emptyList());
               }
            }
         )
         .exceptionally(var0 -> {
            Slayer.method8("Preview", "Failed to load basket: " + var0.getMessage(), new Object[0]);
            return Collections.emptyList();
         });
   }

   private CompletableFuture<StorePriceEntryLegacy> method14(int var1, String var2, String var3) {
      if (var1 == 0) {
         return CompletableFuture.completedFuture(null);
      }

      Builder var4 = HttpRequest.newBuilder()
         .uri(URI.create(ServiceEndpoints.method7() + "/store/package/" + var1 + "/info"))
         .header("X-Basket-Ident", var2)
         .GET();
      if (!var3.isEmpty()) {
         var4.header("X-Currency-Code", var3);
      }

      HttpRequest var5 = var4.build();
      return HttpClient.newHttpClient()
         .sendAsync(var5, BodyHandlers.ofString())
         .thenApply(
            var1x -> {
               try {
                  JsonObject var2x = JsonParser.parseString(var1x.body()).getAsJsonObject();
                  JsonObject var3x = var2x.getAsJsonObject("package");
                  JsonObject var4x = var3x.getAsJsonObject("preview");
                  JsonArray var5x = var4x.getAsJsonArray("items");
                  Integer[] var6 = var5x.asList().stream().filter(var0x -> {
                     String var1xx = var0x.getAsJsonObject().get("type").getAsString();
                     return var1xx.equals("COSMETIC");
                  }).map(var0x -> {
                     JsonObject var1xx = var0x.getAsJsonObject();
                     JsonObject var2xx = var1xx.getAsJsonObject("data");
                     return var2xx.get("id").getAsInt();
                  }).toArray(Integer[]::new);
                  if (var6.length == 0) {
                     return null;
                  }

                  Currency var7 = Currency.getInstance(var3x.get("currency").getAsString());
                  String var8 = var7.getSymbol();
                  int var9 = method18(var7);
                  StorePriceLegacy var10 = new StorePriceLegacy(
                     method17(var3x), method19(var3x.get("finalPrice").getAsDouble(), var9), method19(var3x.get("originalPrice").getAsDouble(), var9)
                  );
                  return new StorePriceEntryLegacy(UUID.randomUUID(), var1, var3x.get("name").getAsString(), var6, var10, var8, var9);
               } catch (Exception var11) {
                  Slayer.method8("Preview", "Failed to parse package response for ID " + var1 + ": " + var11.getMessage(), new Object[0]);
                  return null;
               }
            }
         )
         .exceptionally(var1x -> {
            Slayer.method8("Preview", "Failed to load package " + var1 + ": " + var1x.getMessage(), new Object[0]);
            return null;
         });
   }

   private void method16() {
      this.field4.method3("isOverlayVisible", this.field7);
      if (this.field5 != null) {
         JsonArray var1 = new JsonArray();

         for (StorePriceEntryLegacy var3 : this.field5) {
            var1.add(var3.provide());
         }

         this.field4.method3("previewEntries", var1);
      } else {
         this.field4.method3("previewEntries", new JsonArray());
      }

      if (this.field6 != null) {
         this.field4.method3("previewEntry", this.field6.provide());
      } else {
         this.field4.method3("previewEntry", null);
      }

      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var4 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
      if (var4 != null) {
         var4.method4("cosmeticPreview");
      }
   }

   private static String method17() {
      return "lunar_cosmetic_preview_"
         + Config.method2(ThreadModuleDump63.MC_VERSION).<String>map(Config::getId).orElse(String.valueOf(ThreadModuleDump63.MC_VERSION));
   }

   private static @Nullable Integer method17(JsonObject var0) {
      JsonElement var1 = var0.get("coinsPrice");
      return var1 != null && !var1.isJsonNull() ? var1.getAsInt() : null;
   }

   private static int method18(Currency var0) {
      int var1 = var0.getDefaultFractionDigits();
      return var1 < 0 ? 2 : var1;
   }

   private static double method19(double var0, int var2) {
      return BigDecimal.valueOf(var0).setScale(var2, RoundingMode.HALF_UP).doubleValue();
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field4;
   }

   @Generated
   public List<StorePriceEntryLegacy> method21() {
      return this.field5;
   }

   @Generated
   public void method22(List<StorePriceEntryLegacy> var1) {
      this.field5 = var1;
   }

   @Generated
   public StorePriceEntryLegacy method23() {
      return this.field6;
   }

   @Generated
   public void method24(StorePriceEntryLegacy var1) {
      this.field6 = var1;
   }

   @Generated
   public boolean method25() {
      return this.field7;
   }
}

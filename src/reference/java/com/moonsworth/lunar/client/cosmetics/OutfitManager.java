package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.lunarclient.websocket.badge.v1.EquipBadgeRequest;
import com.lunarclient.websocket.cosmetic.v2.CreateOutfitRequest;
import com.lunarclient.websocket.cosmetic.v2.DeleteOutfitRequest;
import com.lunarclient.websocket.cosmetic.v2.Outfit;
import com.lunarclient.websocket.cosmetic.v2.OutfitTree;
import com.lunarclient.websocket.cosmetic.v2.PlayerCosmeticsPushV2;
import com.lunarclient.websocket.cosmetic.v2.SelectOutfitRequest;
import com.lunarclient.websocket.cosmetic.v2.CreateOutfitResponse.Status;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.account.skin.SkinUploadService;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.Outfit;
import com.moonsworth.lunar.client.cosmetics.emote.ConditionalOutfitTree;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.account.skin.SavedSkinManager;

public class OutfitManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<UUID, Outfit> {
   private final GuiIterator field2 = new GuiIterator();
   private ConditionalOutfitTree field3;
   private final List<Outfit> field4 = new ArrayList<>();
   private final Map<UUID, ConditionalOutfitTree> field5 = new HashMap<>();

   @Override
   protected Map<UUID, Outfit> method3() {
      return new HashMap<>();
   }

   public void method2(PlayerCosmeticsPushV2 var1) {
      CosmeticManager var2 = ThreadModuleDump63.method4().method53();
      UUID var3 = ThreadModuleDump66.method1(var1.getPlayerUuid());
      Outfit var4 = new Outfit(
         var3,
         var3.toString(),
         var1.getDefaultCosmeticsList()
            .stream()
            .map(var1x -> var2.method48(var1x.getCosmeticId(), ThreadModuleDump66.method22(var1x.getGeckolibMetadata())))
            .filter(Objects::nonNull)
            .toList(),
         false,
         Instant.now(),
         Instant.now(),
         null,
         var1.getBadgeId() != 0 ? (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var1.getBadgeId()) : null
      );
      ConditionalOutfitTree var5 = new ConditionalOutfitTree(List.of(), var4);
      this.field5.put(var3, var5);
   }

   public void method3(List<Outfit> var1, OutfitTree var2) {
      this.field4.clear();
      var1.stream().map(Outfit::method1).forEach(this.field4::add);
      this.field4.forEach(var1x -> this.method2().put(var1x.getId(), var1x));
      this.field3 = ConditionalOutfitTree.method3(var2);
      if (ThreadModuleDump63.method3().bridge$getSession() != null) {
         this.field5.put(ThreadModuleDump63.method3().bridge$getSession().bridge$getId(), this.field3);
      }

      this.method6();
   }

   public Outfit method4(UUID var1) {
      ConditionalOutfitTree var2 = this.field5.get(var1);
      return var2 != null ? var2.method5() : null;
   }

   public void method5() {
      this.field3 = null;
      this.field4.clear();
      this.field5.clear();
      this.method2().clear();
      this.method6();
   }

   public void method6() {
      JsonArray var1 = new JsonArray(this.field4.size());

      for (Outfit var3 : this.field4) {
         var1.add(var3.provide());
      }

      this.field2.method3("outfits", var1);
      this.field2.method3("defaultOutfitId", this.field3 != null && this.field3.method5() != null ? this.field3.method5().getId().toString() : null);
   }

   public void method7(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         ThreadModuleDump63.method4().method35().method87().createOutfit(null, CreateOutfitRequest.newBuilder().setName(var1).build(), var2 -> {
            if (var2.hasOutfit() && var2.getStatus() == Status.STATUS_OK) {
               Outfit var3 = Outfit.method1(var2.getOutfit());
               this.field4.add(var3);
               if (this.field3 == null) {
                  this.field3 = new ConditionalOutfitTree(new ArrayList<>(), var3);
               } else {
                  this.field3.method6(var3);
               }

               this.method2().put(var3.getId(), var3);
               ThreadModuleDump63.method4().method69().method8("Created Outfit", "Created Outfit: " + var1);
               this.method6();
               Horsestats var4 = ThreadModuleDump63.method3().bridge$getSession();
               if (var4 == null) {
                  return;
               }

               ThreadModuleDump63.method4().method88().method11(var4.bridge$getId());
            }
         });
      }
   }

   public void method8(UUID var1) {
      if (var1 != null) {
         if (this.field3 != null && this.field3.method5() != null && var1.equals(this.field3.method5().getId())) {
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Cannot delete the default outfit");
         } else {
            ThreadModuleDump63.method4()
               .method35()
               .method87()
               .deleteOutfit(null, DeleteOutfitRequest.newBuilder().setOutfitId(ThreadModuleDump66.method3(var1)).build(), var2 -> {
                  if (var2.getStatus() == com.lunarclient.websocket.cosmetic.v2.DeleteOutfitResponse.Status.STATUS_OK) {
                     this.field4.removeIf(var1xx -> var1xx.getId().equals(var1));
                     this.method2().remove(var1);
                     this.method6();
                  } else {
                     ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to delete outfit");
                  }
               });
         }
      }
   }

   public void method9(UUID var1) {
      Outfit var2 = (Outfit)this.method2().get(var1);
      if (this.field3 != null && var2 != null) {
         this.field3.method6((Outfit)this.method2().get(var1));
         ThreadModuleDump63.method4()
            .method35()
            .method87()
            .selectOutfit(
               null,
               SelectOutfitRequest.newBuilder().setOutfitTree(this.field3.method4()).build(),
               var1x -> {
                  if (var1x.getStatus() == com.lunarclient.websocket.cosmetic.v2.SelectOutfitResponse.Status.STATUS_OK) {
                     ThreadModuleDump63.method4().method69().method8("Updated outfit", "Applied " + var2.getName() + " outfit");
                     ThreadModuleDump63.method4()
                        .method35()
                        .method92()
                        .equipBadge(null, EquipBadgeRequest.newBuilder().setBadgeId(var2.method9() == null ? -1 : var2.method9().id()).build(), var0x -> {});
                     Horsestats var2x = ThreadModuleDump63.method3().bridge$getSession();
                     if (var2x == null) {
                        return;
                     }

                     UUID var3x = var2x.bridge$getProfile().getId();
                     ThreadModuleDump63.method4().method53().method63().computeIfPresent(var3x, (var1xx, var2xx) -> var2xx.method4(var2.method9()));
                     ThreadModuleDump63.method4().method88().method11(var3x);
                  } else {
                     ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Failed to apply outfit");
                  }
               }
            );
         SavedSkinManager var3 = ThreadModuleDump63.method4().method75();
         if (var2.method8() != null) {
            SavedSkin var4 = var2.method8();
            var3.method22(var4);
            var3.method21();
            if (var4 != var3.method26()) {
               SkinUploadService.method1(
                  var4,
                  false,
                  false,
                  () -> {
                     if (ThreadModuleDump63.method7() != null) {
                        ThreadModuleDump63.method7()
                           .bridge$setSkinLocation(
                              com.moonsworth.lunar.client.driver.holograms.HologramsIterator2.method18(var3.method26().getHash(), var3.method26().getUrl()),
                              var3.method26().method2().toString().equals("classic") ? "default" : var3.method26().method2().toString()
                           );
                     }
                  },
                  var0 -> ThreadModuleDump63.method4().method69().method10(new com.moonsworth.lunar.client.ui.notification.Notification(NotificationType.ERROR, var0))
               );
            }
         }

         this.method6();
      }
   }

   public void method10(UUID var1, List<CosmeticMetadata> var2) {
      ConditionalOutfitTree var3 = this.field5
         .computeIfAbsent(
            var1,
            var1x -> new ConditionalOutfitTree(
               new ArrayList<>(),
               new Outfit(
                  UUID.randomUUID(),
                  "Default Outfit",
                  new ArrayList<>(),
                  false,
                  Instant.now(),
                  Instant.now(),
                  null,
                  this.field3 != null ? this.field3.method5().method9() : null
               )
            )
         );
      var3.method5().method10(var2);
   }

   public void method11(UUID var1, @Nullable List<CosmeticMetadata> var2) {
      if (var2 != null) {
         Outfit var5 = this.method4(var1);
         Outfit var4 = new Outfit(
            UUID.randomUUID(),
            "Default Outfit",
            new ArrayList<>(var2),
            false,
            Instant.now(),
            Instant.now(),
            var5 != null ? var5.method8() : null,
            var5 != null ? var5.method9() : null
         );
         this.field5.put(var1, new ConditionalOutfitTree(new ArrayList<>(), var4));
      } else {
         Horsestats var3 = ThreadModuleDump63.method3().bridge$getSession();
         if (this.field3 != null && var3 != null && var1.equals(var3.bridge$getId())) {
            this.field5.put(var1, this.field3);
         } else {
            this.field5.remove(var1);
         }
      }
   }

   public void method12(UUID var1, String var2) {
      Outfit var3 = (Outfit)this.method2().get(var1);
      if (var3 != null && var2 != null && !var2.isEmpty()) {
         var3.setName(var2);
         var3.method3();
      }
   }

   public void removePlayer(UUID var1) {
      this.field5.remove(var1);
   }

   public void method13(UUID var1) {
      Outfit var2 = (Outfit)this.method2().get(var1);
      if (var2 != null) {
         var2.method11(!var2.method5());
         var2.method3();
      }
   }

   public boolean method14(CosmeticMetadata var1) {
      return this.field3 != null && this.field3.method5() != null ? this.field3.method5().method4().contains(var1) : false;
   }

   public void method15(UUID var1, UUID var2) {
      if (this.field5.containsKey(var1)) {
         this.field5.put(var2, this.field5.get(var1));
      } else {
         this.field5.remove(var2);
      }
   }

   @Generated
   public GuiIterator method16() {
      return this.field2;
   }

   @Generated
   public ConditionalOutfitTree method17() {
      return this.field3;
   }
}

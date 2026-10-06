package com.moonsworth.lunar.client.cosmetics.emote;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.protobuf.Any;
import com.lunarclient.websocket.emote.v1.EquippedEmote;
import com.lunarclient.websocket.emote.v1.StopEmotePush;
import com.lunarclient.websocket.emote.v1.StopEmoteRequest;
import com.lunarclient.websocket.emote.v1.UpdateEquippedEmotesRequest;
import com.lunarclient.websocket.emote.v1.UseEmotePush;
import com.lunarclient.websocket.emote.v1.UseEmoteRequest;
import com.lunarclient.websocket.emote.v1.UseEmoteResponse.Status;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_11;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.highlight.Fishing2Extension;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteImpl;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.player.PlayerJoinWorldEventLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGiftProvider;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import mchorse.emoticons.common.EmoteAPI;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.GeneralSettings;

public class EmoteManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<UUID, Emote> implements Extension, EventRegistrar {
   private final GuiIterator field2 = new GuiIterator();
   public static final BiMap<Integer, com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition> field3 = HashBiMap.create();
   private List<EmoteGift> field4 = new ArrayList<>();
   private Set<EmoteGiftProvider> field5 = new HashSet<>();
   private Integer field6 = null;
   private boolean field7 = false;
   private final Map<UUID, EmoteManager.Data> field8 = new ConcurrentHashMap<>();
   public boolean field9;
   public int field10;
   public boolean field11;

   public EmoteManager() {
      this.handle(EventClientTick.class, this::getProvider);
      this.handle(EventPlayerRemoval.class, var0 -> {
         if (ThreadModuleDump63.method8() != null) {
            ThreadModuleDump63.method4().method45().method10(var0.method1(), false);
         }
      });
      this.handle(PlayerJoinWorldEventLegacy.class, var0 -> {
         if (ThreadModuleDump63.method8() != null) {
            ThreadModuleDump63.method4().method45().method10(var0.method1(), false);
         }
      });
      this.handle(DisconnectEvent.class, var0 -> {
         if (ThreadModuleDump63.method8() != null) {
            ThreadModuleDump63.method4().method45().method10(ThreadModuleDump63.method7(), false);
         }
      });
   }

   public void method4() {
      if (ThreadModuleDump63.method7() != null) {
         this.method11(ThreadModuleDump63.method7(), false, false);
      }

      if (ThreadModuleDump63.method8() != null) {
         for (Bridge6_10 var2 : ThreadModuleDump63.method8().bridge$getPlayerEntities()) {
            ThreadModuleDump63.method4().method45().method11(var2, false, false);
         }
      }
   }

   public void method5() {
      List var1 = this.field5.stream().sorted(Comparator.comparingInt(EmoteGiftProvider::getSlotId)).toList();
      List var2 = var1.stream()
         .map(var0 -> EquippedEmote.newBuilder().setEmoteId(var0.getEmoteId()).setAttachedJamId(var0.getJamId()).setSlotNumber(var0.getSlotId()).build())
         .toList();
      ThreadModuleDump63.method5()
         .ifPresent(
            var1x -> var1x.method90().updateEquippedEmotes(null, UpdateEquippedEmotesRequest.newBuilder().addAllEquippedEmotes(var2).build(), var0x -> {})
         );
   }

   public void method3(int var1, int var2, int var3) {
      if (!ThreadModuleDump63.method4().method40().method64().method13()) {
         if (this.field4.contains(new EmoteGift(var1, -1L, null, new ArrayList<>(), null))) {
            if (this.method2().containsKey(ThreadModuleDump63.method7().bridge$getUniqueID())) {
               this.field9 = false;
               this.field11 = true;
            } else {
               this.field11 = false;
            }

            StyngrSong var4 = JamManager.method12().get(var3);
            UUID var5 = var4 != null ? var4.method1() : null;
            String var6 = var5 != null ? var5.toString() : "";
            if (!var6.isEmpty()) {
               ThreadModuleDump63.method4()
                  .method73()
                  .method3(
                     var6,
                     var5x -> {
                        GeneralSettings var6x = ThreadModuleDump63.method4().method41().method6();
                        if ((Boolean)var6x.method69().get()
                           && (Boolean)var6x.method70().get()
                           && !var4.method7()
                           && ThreadModuleDump63.method4().method34().method1()
                           && (Boolean)var6x.method72().get()) {
                           Notification var7 = new Notification(
                              NotificationType.WARNING.getIcon(), com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("disableStreamerMode", new Object[0])
                           );
                           var7.method10(6000L);
                           Client.method109().method69().method10(var7);
                        }

                        this.method4(var1, var2, var5x, var3);
                     }
                  );
            } else {
               this.method4(var1, var2, "", 0);
            }
         } else {
            Slayer.method7("Couldn't perform emote (%s) as you do not own it", new Object[]{var1});
         }
      }
   }

   private void method4(int var1, int var2, String var3, int var4) {
      ThreadModuleDump63.method5()
         .ifPresent(
            var5 -> var5.method90()
               .useEmote(
                  null,
                  UseEmoteRequest.newBuilder().setEmoteId(var1).setEmoteMetadata(var2).setEmoteJamId(var4).setEmoteSoundtrackUrl(var3).build(),
                  var5x -> {
                     if (var5x.getStatus() != Status.STATUS_OK) {
                        if (var5x.getStatus() != Status.STATUS_OK) {
                           String var8 = switch (var5x.getStatus()) {
                              case STATUS_EMOTE_NOT_OWNED -> "You do not own that emote.";
                              case STATUS_ILLEGAL_SOUNDTRACK -> "Invalid emote soundtrack.";
                              case STATUS_OK, STATUS_UNSPECIFIED, STATUS_OK, UNRECOGNIZED -> "Unknown error.";
                              default -> throw new IncompatibleClassChangeError();
                           };
                           ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, var8);
                        } else if (!(Boolean)ThreadModuleDump63.method4().method41().method6().method37().get()) {
                           String var7 = ThreadModuleDump63.method4().method67().method2("popups", "emotesDisabled");
                           ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, var7);
                        } else {
                           Fishing.method2(Fishing2Extension.class)
                              .ifPresent(
                                 var3xxx -> {
                                    if (Client.method109().method40().method64().isEnabled()) {
                                       var3xxx.method8(
                                          Any.pack(
                                             UseEmotePush.newBuilder()
                                                .setEmoteId(var1)
                                                .setEmoteMetadata(var2)
                                                .setEmoteSoundtrackUrl(var3)
                                                .setPlayerUuid(ThreadModuleDump66.method3(ThreadModuleDump63.method7().bridge$getUniqueID()))
                                                .build()
                                          )
                                       );
                                    }
                                 }
                              );
                           Rewind var6 = ThreadModuleDump63.method4().method40().method85();
                           if (var6.isRecording()) {
                              var6.method34()
                                 .method1(
                                    Any.pack(
                                       UseEmotePush.newBuilder()
                                          .setEmoteId(var1)
                                          .setEmoteMetadata(var2)
                                          .setEmoteSoundtrackUrl(var3)
                                          .setPlayerUuid(ThreadModuleDump66.method3(ThreadModuleDump63.method7().bridge$getUniqueID()))
                                          .build()
                                    )
                                 );
                           }

                           this.method7(ThreadModuleDump63.method3().bridge$getPlayer(), this.method13(var1), var2, var3, var4);
                        }
                     }
                  }
               )
         );
   }

   public void method5(int var1, int var2) {
      this.method3(var1, var2, 0);
   }

   public void method6(Emote var1) {
      this.method5(var1.getId(), var1.getMetadata());
   }

   public void method7(@Nullable Bridge6_10 var1, @Nullable Emote var2, int var3, String var4, int var5) {
      if (var1 != null) {
         this.method12(var1);
         if (var2 != null) {
            if (EmoteController.avoidEmote(var1)) {
               if (var1 == ThreadModuleDump63.method7()) {
                  ThreadModuleDump63.method4().method69().method9(com.moonsworth.lunar.client.gui.notification.NotificationManager.method15("emoteFailure", new Object[0]));
               }

               return;
            }

            if (var1 == ThreadModuleDump63.method7()
               && !ThreadModuleDump63.method4().method40().method64().method13()
               && !ThreadModuleDump63.method7().bridge$getMovementInput().bridge$isSneaking()
               && Bridge.method9().bridge$getGameSettings().bridge$getThirdPersonView() == 0) {
               this.field9 = true;
               Bridge.method9().bridge$getGameSettings().bridge$setThirdPersonView(1);
            }

            this.method2().putIfAbsent(var1.bridge$getUniqueID(), var2);
            if (var2 instanceof EmoteImpl) {
               ((EmoteImpl)var2).method2(var1, var3);
            }

            if (var4 != null && !var4.isEmpty()) {
               this.method8(var1.bridge$getUniqueID(), var4, var5);
            }
         }
      }
   }

   private void method8(UUID var1, String var2, int var3) {
      if (!ThreadModuleDump63.method4().method40().method85().method19()) {
         GeneralSettings var4 = ThreadModuleDump63.method4().method41().method6();
         if ((Boolean)var4.method69().get()) {
            StyngrSong var5 = var3 == 0 ? null : JamManager.method12().get(var3);
            if ((var5 == null || !var5.method7()) && ThreadModuleDump63.method4().method34().method1()) {
               return;
            }

            this.field8.remove(var1);
            ThreadModuleDump63.method3().bridge$submit(() -> {
               float var5x = (Float)var4.method73().get();
               Bridge5Extension_5 var6 = ThreadModuleDump63.method7();
               if (var6 != null && var6.bridge$getUniqueID() != var1) {
                  var5x = (Float)var4.method74().get();
               }

               Object var7 = ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$playMp3FromURL(var2, var5x, true, true);
               if (var7 != null) {
                  this.field8.put(var1, new EmoteManager.Data(System.currentTimeMillis(), var7, var2, var3));
               }
            });
         }
      }
   }

   public boolean method9(Bridge6_10 var1) {
      EmoteController var2 = (EmoteController)EmoteController.get(var1);
      return var2 != null && var2.isEmoting();
   }

   public void method10(Bridge6_10 var1, boolean var2) {
      this.method11(var1, var2, true);
   }

   public void method11(Bridge6_10 var1, boolean var2, boolean var3) {
      if (this.method2().containsKey(var1.bridge$getUniqueID())) {
         this.method12(var1);
         if (!(var1 instanceof ThreadModuleDump54)) {
            Fishing.method2(Fishing2Extension.class).ifPresent(var1x -> {
               if (Client.method109().method40().method64().isEnabled()) {
                  var1x.method8(Any.pack(StopEmotePush.newBuilder().setPlayerUuid(ThreadModuleDump66.method3(var1.bridge$getUniqueID())).build()));
               }
            });
            Rewind var4 = ThreadModuleDump63.method4().method40().method85();
            if (var4.isRecording()) {
               var4.method34().method1(Any.pack(StopEmotePush.newBuilder().setPlayerUuid(ThreadModuleDump66.method3(var1.bridge$getUniqueID())).build()));
            }
         }

         if (var1 == ThreadModuleDump63.method7()
            && ThreadModuleDump63.method5().isPresent()
            && ((EntityRenderer4)ThreadModuleDump63.method5().get()).isOpen()
            && var3
            && !var2) {
            ThreadModuleDump63.method5().ifPresent(var0 -> var0.method90().stopEmote(null, StopEmoteRequest.getDefaultInstance(), var0x -> {}));
         }
      }
   }

   private void method12(Bridge6_10 var1) {
      if (var1 != null && var1.bridge$getUniqueID() != null) {
         EmoteManager.Data var2 = this.field8.remove(var1.bridge$getUniqueID());
         if (var2 != null) {
            try {
               ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$destroySound(var2.source);
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

         Map var3 = this.method2();
         if (var3 != null && var3.containsKey(var1.bridge$getUniqueID())) {
            Emote var4 = (Emote)var3.get(var1.bridge$getUniqueID());
            if (var4 != null) {
               if (var4 instanceof EmoteImpl) {
                  EmoteAPI.setEmoteClient(null, var1, -1);
               }

               var4.method3(var1);
            }

            var3.remove(var1.bridge$getUniqueID());
         }
      }
   }

   public Emote method13(int var1) {
      return this.method14(var1, 0);
   }

   public Emote method14(int var1, int var2) {
      try {
         com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition var3 = (com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition)field3.get(var1);
         StyngrSong var4 = JamManager.method12().get(var2);
         return var3 == null ? null : new EmoteImpl(var1, var3.method4(), var3.getId(), var3.method3(), var4);
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   private void getProvider(EventClientTick var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (!this.method2().isEmpty()) {
         ArrayList var3 = new ArrayList();

         for (Entry var5 : this.method2().entrySet()) {
            UUID var6 = (UUID)var5.getKey();
            Emote var7 = (Emote)var5.getValue();
            if (var7.method3()) {
               if (var2 != null) {
                  var2.bridge$getPlayerByUniqueId(var6).ifPresent(var1x -> {
                     if (var7.method3()) {
                        var7.method3(var1x);
                     }
                  });
               }

               var3.add(var6);
            }
         }

         for (UUID var11 : var3) {
            this.method2().remove(var11);
         }
      }

      for (Entry var10 : this.field8.entrySet()) {
         if (var10.getValue() != null && var2 != null) {
            var2.bridge$getPlayerByUniqueId((UUID)var10.getKey()).ifPresent(var2x -> {
               Bridge3_11 var3x = ThreadModuleDump63.method3().bridge$getSoundHandler();
               EmoteManager.Data var4 = (EmoteManager.Data)var10.getValue();
               if (!var3x.bridge$isSoundPlaying(var4.source)) {
                  try {
                     var3x.bridge$destroySound(var4.source);
                  } catch (Exception var6x) {
                     var6x.printStackTrace();
                  }

                  if (var4.timestamp + 1000L > System.currentTimeMillis()) {
                     this.field8.remove(var10.getKey());
                     return;
                  }

                  this.method8((UUID)var10.getKey(), var4.field1, var4.field2);
               }

               var3x.bridge$setSoundLocation(var4.source, var2x.bridge$getPosX(), var2x.bridge$getPosY(), var2x.bridge$getPosZ());
            });
         }
      }
   }

   @Override
   protected Map<UUID, Emote> method3() {
      return new HashMap<>();
   }

   public void method17(List<EmoteGift> var1) {
      this.field4 = var1;
      this.method21();
   }

   public void method18(Set<EmoteGiftProvider> var1) {
      this.field5 = var1;
      this.method22();
   }

   public boolean method19(BridgeExtension2_5 var1) {
      Emote var2 = (Emote)this.method2().get(var1.bridge$getUniqueID());
      return var2 == null || var2.method4();
   }

   public Optional<EmoteGiftProvider> method20(int var1) {
      return this.field5.stream().filter(var1x -> var1x.getSlotId() == var1).findFirst();
   }

   public void method21() {
      List var1 = this.method24().stream().map(var0 -> Client.method109().method45().method13(var0.id())).toList();
      JsonArray var2 = new JsonArray(var1.size());

      for (Emote var4 : var1) {
         if (var4 != null) {
            JsonObject var5 = (JsonObject)var4.provide();
            var5.addProperty("active", this.method25().stream().anyMatch(var1x -> var1x.getEmoteId() == var4.getId()));
            var2.add(var5);
         }
      }

      this.field2.method3("ownedEmotes", var2);
      this.field2.method3("freeLunarPlusEmote", this.field6);
      this.field2.method3("ownsLunarPlusEmote", this.field7);
   }

   public void method22() {
      JsonArray var1 = new JsonArray(this.method25().size());

      for (EmoteGiftProvider var3 : this.method25()) {
         if (var3 != null) {
            var1.add(var3.provide());
         }
      }

      this.field2.method3("equippedEmotes", var1);
   }

   @Generated
   public GuiIterator getProvider() {
      return this.field2;
   }

   @Generated
   public List<EmoteGift> method24() {
      return this.field4;
   }

   @Generated
   public Set<EmoteGiftProvider> method25() {
      return this.field5;
   }

   @Generated
   public Integer method26() {
      return this.field6;
   }

   @Generated
   public void method27(Integer var1) {
      this.field6 = var1;
   }

   @Generated
   public void method28(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public Map<UUID, EmoteManager.Data> method29() {
      return this.field8;
   }

   public static final class Data {
      private long timestamp;
      private Object source;
      private final String field1;
      private final int field2;

      @Generated
      public long getTimestamp() {
         return this.timestamp;
      }

      @Generated
      public Object getSource() {
         return this.source;
      }

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public int getJamId() {
         return this.field2;
      }

      @Generated
      public void setTimestamp(long var1) {
         this.timestamp = var1;
      }

      @Generated
      public void method2(Object var1) {
         this.source = var1;
      }

      @Generated
      public Data(long var1, Object var3, String var4, int var5) {
         this.timestamp = var1;
         this.source = var3;
         this.field1 = var4;
         this.field2 = var5;
      }
   }
}

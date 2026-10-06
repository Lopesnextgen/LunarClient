package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.lunarclient.common.v1.LunarClientVersion;
import com.lunarclient.common.v1.MinecraftVersion;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.common.v1.Vector3f;
import com.lunarclient.websocket.screenshot.v1.ExtraScreenshotData;
import com.lunarclient.websocket.screenshot.v1.Perspective;
import com.lunarclient.websocket.screenshot.v1.PlayerState;
import com.lunarclient.websocket.screenshot.v1.ScreenshotMessage;
import com.lunarclient.websocket.screenshot.v1.ScreenshotPlayer;
import com.lunarclient.websocket.screenshot.v1.Weather;
import com.lunarclient.websocket.screenshot.v1.ScreenshotMessage.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.world.Biome;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteDefinition;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.network.websocket.AssetServerClient;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.Map.Entry;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;

public class Screenshot {
   private final UUID field1;
   private final ScreenshotMessage field2;
   private final ExtraScreenshotData field3;

   public Screenshot(UUID uuid1, ScreenshotMessage screenshotmessage2, ExtraScreenshotData extrascreenshotdata3) {
      this.field1 = uuid1;
      this.field2 = screenshotmessage2;
      this.field3 = extrascreenshotdata3;
   }

   public static Screenshot method1() {
      ExtraScreenshotData extrascreenshotdata0 = ExtraScreenshotData.newBuilder()
         .setMcVersion(MinecraftVersion.newBuilder().setEnum(Bridge.getMinecraftVersion().getId()))
         .setLunarVersion(LunarClientVersion.newBuilder().setGitBranch(LunarBuildData.field1).setGitCommit(LunarBuildData.field3).setSemver(Client.method19()))
         .setLocation(AssetServerClient.method82())
         .build();
      UUID uuid1 = UUID.randomUUID();
      Horsestats horsestats2 = Ref.method3().bridge$getSession();
      Memory memory3 = Client.method109().method31();
      Builder builder4 = ScreenshotMessage.newBuilder()
         .setAuthor(
            UuidAndUsername.newBuilder()
               .setUuid(ProtoConverter.method3(memory3 == null ? new UUID(0L, 0L) : memory3.method10()))
               .setUsername(horsestats2 == null ? "unknown" : horsestats2.bridge$getUsername())
         )
         .setPerspective(method2(Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView()))
         .setHasHud(!Ref.method3().bridge$getGameSettings().bridge$isHideGui());
      WorldBridgeExtension itemcounter6extension5 = Ref.method8();
      Bridge5Extension_5 bridge5extension_56 = Ref.method7();
      if (bridge5extension_56 != null && itemcounter6extension5 != null) {
         builder4.addAllStates(method4(bridge5extension_56))
            .setLocalId(ProtoConverter.method3(uuid1))
            .setYaw(bridge5extension_56.bridge$getRotationYawHead())
            .setPitch((float)bridge5extension_56.bridge$getRotationPitch())
            .setHealth(bridge5extension_56.bridge$getHealth())
            .setMaxHealth(bridge5extension_56.bridge$getMaxHealth())
            .setFoodLevel(bridge5extension_56.bridge$getFoodStats().bridge$getFoodLevel())
            .setSaturationLevel(bridge5extension_56.bridge$getFoodStats().bridge$getSaturationLevel());
         if ((Boolean)Ref.method4().method40().method32().method22().get()) {
            Biome itemcountertype_27 = Biome.fromBiomeBridgeOrNull(bridge5extension_56.method6());
            if (itemcountertype_27 != null) {
               builder4.setBiome("minecraft:" + itemcountertype_27.getResourceLocation());
            } else {
               builder4.setBiome(bridge5extension_56.method6().bridge$getBiomeName());
            }

            ArrayList list8 = new ArrayList();

            for (Bridge6_10 bridge6_1010 : itemcounter6extension5.bridge$getPlayerEntities()) {
               if (bridge5extension_56 == bridge6_1010) {
                  if (Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView() != 0) {
                     list8.add(method3(bridge6_1010));
                  }
               } else if (bridge5extension_56.method2(bridge6_1010) <= 100.0
                  && Ref.method3().bridge$getLevelRenderer().bridge$isVisible(bridge6_1010.bridge$getBoundingBoxForCulling())) {
                  list8.add(method3(bridge6_1010));
               }
            }

            builder4.addAllPlayers(list8)
               .setDimension(itemcounter6extension5.bridge$getDimensionKey())
               .setPos(Vector3f.newBuilder().setX((float)bridge5extension_56.bridge$getPosX()).setY((float)bridge5extension_56.bridge$getPosY()).setZ((float)bridge5extension_56.bridge$getPosZ()))
               .setDayTime(itemcounter6extension5.bridge$getDayTime())
               .setWeather(
                  itemcounter6extension5.bridge$isThundering()
                     ? Weather.WEATHER_THUNDER
                     : (
                        itemcounter6extension5.bridge$isRaining()
                           ? (itemcounter6extension5.bridge$isSnowing(bridge5extension_56.bridge$getBlockPos()) ? Weather.WEATHER_SNOW : Weather.WEATHER_RAIN)
                           : Weather.WEATHER_CLEAR
                     )
               );
         }
      } else {
         builder4.setBiome("unknown")
            .addAllPlayers(List.of())
            .setDimension("unknown")
            .setYaw(0.0F)
            .setPitch(0.0F)
            .setPos(Vector3f.newBuilder().build())
            .setDayTime(0L)
            .setWeather(Weather.WEATHER_UNSPECIFIED);
      }

      return new Screenshot(uuid1, builder4.build(), extrascreenshotdata0);
   }

   private static Perspective method2(int number0) {
      return switch (number0) {
         case 0 -> Perspective.PERSPECTIVE_FIRST;
         case 1 -> Perspective.PERSPECTIVE_BACK;
         case 2 -> Perspective.PERSPECTIVE_FRONT;
         default -> Perspective.PERSPECTIVE_UNSPECIFIED;
      };
   }

   private static ScreenshotPlayer method3(Bridge6_10 bridge6_100) {
      com.lunarclient.websocket.screenshot.v1.ScreenshotPlayer.Builder builder1 = ScreenshotPlayer.newBuilder()
         .setPlayer(UuidAndUsername.newBuilder().setUsername(bridge6_100.bridge$getName()).setUuid(ProtoConverter.method3(bridge6_100.bridge$getUniqueID())))
         .addAllStates(method4(bridge6_100))
         .addAllCosmetics(method5(bridge6_100));
      method6(bridge6_100).ifPresent(builder1::setEmote);
      return builder1.build();
   }

   private static List<PlayerState> method4(Bridge6_10 bridge6_100) {
      if (bridge6_100.bridge$isSleeping()) {
         return List.of(PlayerState.PLAYER_STATE_SLEEPING);
      }

      if (bridge6_100.bridge$isElytraFlying()) {
         return List.of(PlayerState.PLAYER_STATE_ELYTRA_FLYING);
      }

      ArrayList list1 = new ArrayList();
      if (bridge6_100.bridge$isFlying()) {
         list1.add(PlayerState.PLAYER_STATE_FLYING);
      } else if (bridge6_100.bridge$isSprinting()) {
         list1.add(PlayerState.PLAYER_STATE_SPRINTING);
      } else if (bridge6_100.bridge$isVisiblyCrouching()) {
         list1.add(PlayerState.PLAYER_STATE_SNEAKING);
      } else if (bridge6_100.bridge$isSwimming()) {
         list1.add(PlayerState.PLAYER_STATE_SWIMMING);
      }

      if (bridge6_100.bridge$isRiding()) {
         list1.add(PlayerState.PLAYER_STATE_RIDING);
      }

      if (bridge6_100.bridge$isJumping()) {
         list1.add(PlayerState.PLAYER_STATE_JUMPING);
      } else if (bridge6_100.bridge$getFallDistance() > 1.0) {
         list1.add(PlayerState.PLAYER_STATE_FALLING);
      }

      if (list1.isEmpty()) {
         if (bridge6_100.method20() > 0.25) {
            list1.add(PlayerState.PLAYER_STATE_WALKING);
         } else {
            list1.add(PlayerState.PLAYER_STATE_STANDING);
         }
      }

      return list1;
   }

   private static IntArrayList method5(Bridge6_10 bridge6_100) {
      List list1 = Ref.method4().method53().method19(bridge6_100.bridge$getUniqueID());
      if (list1 == null) {
         return new IntArrayList(0);
      }

      IntArrayList intarraylist2 = new IntArrayList(list1.size());

      for (CosmeticMetadata gui2handler34 : list1) {
         intarraylist2.add((int)gui2handler34.method4().method9());
      }

      return intarraylist2;
   }

   private static OptionalInt method6(Bridge6_10 bridge6_100) {
      EmoteController emotecontroller1 = (EmoteController)EmoteController.get(bridge6_100);
      if (emotecontroller1 != null && emotecontroller1.emote != null) {
         String text2 = emotecontroller1.emote.name;

         for (Entry entry4 : EmoteManager.field3.entrySet()) {
            if (((EmoteDefinition)entry4.getValue()).getId().equals(text2)) {
               return OptionalInt.of((Integer)entry4.getKey());
            }
         }
      }

      return OptionalInt.empty();
   }

   public UUID method7() {
      return this.field1;
   }

   public ScreenshotMessage method8() {
      return this.field2;
   }

   public ExtraScreenshotData method9() {
      return this.field3;
   }
}

package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class MarkerSource {
   private static final Map<KeystrokesType, MarkerDetectionFunction> field1 = new Builder()
      .put(KeystrokesType.HYPIXEL, (MarkerDetectionFunction)(arg0, arg1, arg2) -> Objects.equals(HypixelLocationListener.field7.method7().field1, arg2.method5()))
      .build();
   private final Set<MarkerTeam> field2 = new HashSet<>();

   public MarkerSource() {
   }

   public void method1(MarkerTeam gui2extension1, boolean flag2) {
      if (flag2) {
         this.field2.add(gui2extension1);
      } else {
         this.field2.remove(gui2extension1);
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         LunarLogger.method3("[MarkerModel] Setting team " + gui2extension1.id() + ": " + flag2, new Object[0]);
      }
   }

   @NotNull
   public List<UUID> method2() {
      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         LunarLogger.method3("[MarkerModel] Active teams: " + this.field2.stream().map(MarkerTeam::id).collect(Collectors.joining(",")), new Object[0]);
      }

      if (!this.field2.isEmpty() && Ref.method7() != null) {
         NetHandlerPlayClientBridge bridgeextension_71 = Ref.method3().bridge$getClientPacketListener();
         if (bridgeextension_71 == null) {
            return Collections.emptyList();
         }

         ArrayList list2 = new ArrayList();

         for (PlayerInfoBridge bridge2_334 : bridgeextension_71.bridge$getPlayerInfoMap()) {
            GameProfile gameprofile5 = bridge2_334.bridge$getGameProfile();
            if (gameprofile5 != null) {
               String text6 = gameprofile5.getName();
               UUID uuid7 = gameprofile5.getId();
               if (!uuid7.equals(Ref.method7().bridge$getUniqueID())) {
                  if (NpcUtils.method1(text6, uuid7, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))) {
                     if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                        LunarLogger.method3("[MarkersBroadcast] %s marked as NPC, skipping", new Object[]{uuid7});
                     }
                  } else if (Ref.method4().method91().method6(uuid7) == null) {
                     if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                        LunarLogger.method3("[MarkersBroadcast] %s not on lunar, skipping", new Object[]{uuid7});
                     }
                  } else {
                     for (MarkerTeam gui2extension9 : this.field2) {
                        if (gui2extension9.getDetectionFunction().check(bridge2_334, gameprofile5, null)) {
                           if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                              LunarLogger.method3("[MarkerModel] Receiver check success %s %s %s", new Object[]{gui2extension9, uuid7, text6});
                           }

                           list2.add(uuid7);
                           break;
                        }
                     }
                  }
               }
            } else if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
               LunarLogger.method3(
                  "[MarkerModel] Marker receivers collection skipped (game profile is null) %s", new Object[]{TextBridge.getTextContent(bridge2_334.bridge$getDisplayName())}
               );
            }
         }

         return list2;
      } else {
         return Collections.emptyList();
      }
   }

   public boolean method3(MarkerModel markers1, UUID uuid2) {
      if (!this.field2.isEmpty() && Ref.method7() != null) {
         if (markers1.dimension() != this.getDimension()) {
            if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
               LunarLogger.method3("[MarkerModel] Marker source dimension check for %s failed! %s, %s", new Object[]{uuid2, markers1.dimension(), this.getDimension()});
            }

            return false;
         } else {
            NetHandlerPlayClientBridge bridgeextension_73 = Ref.method3().bridge$getClientPacketListener();
            if (bridgeextension_73 == null) {
               return false;
            }

            PlayerInfoBridge bridge2_334 = bridgeextension_73.bridge$getPlayerInfo(uuid2);
            if (bridge2_334 == null) {
               if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                  LunarLogger.method3("[MarkerModel] Marker source check failed (player info) %s", new Object[]{uuid2});
               }

               return false;
            } else {
               GameProfile gameprofile5 = bridge2_334.bridge$getGameProfile();
               if (gameprofile5 == null) {
                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     LunarLogger.method3("[MarkerModel] Marker source check failed (game profile) %s", new Object[]{uuid2});
                  }

                  return false;
               } else {
                  Ref.method4().method33();
                  KeystrokesType keystrokestype6 = ServerBrandWatcher.method9();
                  if (keystrokestype6 != null) {
                     MarkerDetectionFunction markers37 = field1.get(keystrokestype6);
                     if (markers37 != null && !markers37.check(bridge2_334, gameprofile5, markers1)) {
                        if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                           LunarLogger.method3("[MarkerModel] Extra check for %s failed! %s, %s", new Object[]{keystrokestype6, uuid2, markers1.method1()});
                        }

                        return false;
                     }
                  }

                  for (MarkerTeam gui2extension8 : markers1.method3()) {
                     if (gui2extension8.getDetectionFunction().check(bridge2_334, gameprofile5, markers1)) {
                        if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                           LunarLogger.method3("[MarkerModel] Team check %s success %s, %s", new Object[]{gui2extension8.name(), uuid2, markers1.method1()});
                        }

                        return true;
                     }
                  }

                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     LunarLogger.method3("[MarkerModel] All %s team checks failed for %s, %s", new Object[]{markers1.method3().size(), uuid2, markers1.method1()});
                  }

                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public MarkerModel method4() {
      String text1 = null;
      if (ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
         text1 = HypixelLocationListener.field7.method7().field1;
      }

      return new MarkerModel(this.field2, Ref.MC_VERSION, this.getDimension(), text1);
   }

   private int getDimension() {
      return Ref.method7().bridge$getDimension();
   }
}

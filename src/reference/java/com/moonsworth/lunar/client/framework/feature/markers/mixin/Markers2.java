package com.moonsworth.lunar.client.framework.feature.markers.mixin;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class Markers2 {
   private static final Map<KeystrokesType, Markers3> field1 = new Builder()
      .put(KeystrokesType.HYPIXEL, (Markers3)(var0, var1, var2) -> Objects.equals(GuiRewindhandlersHandler23.field7.method7().field1, var2.method5()))
      .build();
   private final Set<Gui2Extension> field2 = new HashSet<>();

   public void method1(Gui2Extension var1, boolean var2) {
      if (var2) {
         this.field2.add(var1);
      } else {
         this.field2.remove(var1);
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         Slayer.method3("[Markers] Setting team " + var1.id() + ": " + var2, new Object[0]);
      }
   }

   @NotNull
   public List<UUID> method2() {
      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         Slayer.method3("[Markers] Active teams: " + this.field2.stream().map(Gui2Extension::id).collect(Collectors.joining(",")), new Object[0]);
      }

      if (!this.field2.isEmpty() && ThreadModuleDump63.method7() != null) {
         ClientPacketListenerBridge var1 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
         if (var1 == null) {
            return Collections.emptyList();
         }

         ArrayList var2 = new ArrayList();

         for (Bridge2_33 var4 : var1.bridge$getPlayerInfoMap()) {
            GameProfile var5 = var4.bridge$getGameProfile();
            if (var5 != null) {
               String var6 = var5.getName();
               UUID var7 = var5.getId();
               if (!var7.equals(ThreadModuleDump63.method7().bridge$getUniqueID())) {
                  if (ThreadModuleDump22.method1(var6, var7, Highlight3Iterator.method8(KeystrokesType.HYPIXEL))) {
                     if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                        Slayer.method3("[MarkersBroadcast] %s marked as NPC, skipping", new Object[]{var7});
                     }
                  } else if (ThreadModuleDump63.method4().method91().method6(var7) == null) {
                     if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                        Slayer.method3("[MarkersBroadcast] %s not on lunar, skipping", new Object[]{var7});
                     }
                  } else {
                     for (Gui2Extension var9 : this.field2) {
                        if (var9.getDetectionFunction().check(var4, var5, null)) {
                           if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                              Slayer.method3("[Markers] Receiver check success %s %s %s", new Object[]{var9, var7, var6});
                           }

                           var2.add(var7);
                           break;
                        }
                     }
                  }
               }
            } else if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
               Slayer.method3(
                  "[Markers] Marker receivers collection skipped (game profile is null) %s", new Object[]{AdventureTextBridge.getTextContent(var4.bridge$getDisplayName())}
               );
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public boolean method3(Markers var1, UUID var2) {
      if (!this.field2.isEmpty() && ThreadModuleDump63.method7() != null) {
         if (var1.dimension() != this.getDimension()) {
            if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
               Slayer.method3("[Markers] Marker source dimension check for %s failed! %s, %s", new Object[]{var2, var1.dimension(), this.getDimension()});
            }

            return false;
         } else {
            ClientPacketListenerBridge var3 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
            if (var3 == null) {
               return false;
            }

            Bridge2_33 var4 = var3.bridge$getPlayerInfo(var2);
            if (var4 == null) {
               if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                  Slayer.method3("[Markers] Marker source check failed (player info) %s", new Object[]{var2});
               }

               return false;
            } else {
               GameProfile var5 = var4.bridge$getGameProfile();
               if (var5 == null) {
                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     Slayer.method3("[Markers] Marker source check failed (game profile) %s", new Object[]{var2});
                  }

                  return false;
               } else {
                  ThreadModuleDump63.method4().method33();
                  KeystrokesType var6 = Highlight3Iterator.method9();
                  if (var6 != null) {
                     Markers3 var7 = field1.get(var6);
                     if (var7 != null && !var7.check(var4, var5, var1)) {
                        if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                           Slayer.method3("[Markers] Extra check for %s failed! %s, %s", new Object[]{var6, var2, var1.method1()});
                        }

                        return false;
                     }
                  }

                  for (Gui2Extension var8 : var1.method3()) {
                     if (var8.getDetectionFunction().check(var4, var5, var1)) {
                        if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                           Slayer.method3("[Markers] Team check %s success %s, %s", new Object[]{var8.name(), var2, var1.method1()});
                        }

                        return true;
                     }
                  }

                  if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
                     Slayer.method3("[Markers] All %s team checks failed for %s, %s", new Object[]{var1.method3().size(), var2, var1.method1()});
                  }

                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public Markers method4() {
      String var1 = null;
      if (Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
         var1 = GuiRewindhandlersHandler23.field7.method7().field1;
      }

      return new Markers(this.field2, ThreadModuleDump63.MC_VERSION, this.getDimension(), var1);
   }

   private int getDimension() {
      return ThreadModuleDump63.method7().bridge$getDimension();
   }
}

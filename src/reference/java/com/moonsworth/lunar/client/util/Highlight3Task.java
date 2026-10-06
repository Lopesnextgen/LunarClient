package com.moonsworth.lunar.client.util;

import com.lunarclient.common.v1.PublicServer;
import com.lunarclient.common.v1.Location.LocationCase;
import com.lunarclient.websocket.hostedworld.v1.AddressAndPort;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldResponse.Status;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.ConfirmDialog;
import com.moonsworth.lunar.client.gui.PlayerConfirmDialog;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Highlight3Task implements EventRegistrar {
   private static final List<Runnable> field1 = new ArrayList<>();

   public Highlight3Task() {
      this.handle(EventClientTick.class, var0 -> {
         while (field1.size() > 0) {
            field1.remove(0).run();
         }
      });
   }

   public static void method1(Memory var0, Source var1) {
      if (var0.getLocation() != null) {
         if (!Bridge.getMinecraftVersion().method19()
            || ThreadModuleDump63.method3().bridge$getIntegratedServer() == null
            || ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$isReady()) {
            if (var0.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER) {
               method3(var0);
            } else if (var0.getLocation().getLocationCase() == LocationCase.HOSTED_WORLD && var0.getHostedWorldJoinability() == Joinability.JOINABILITY_ALLOWED
               )
             {
               method2(new com.moonsworth.lunar.client.coordinates.Coordinates(var0.method10(), var0.method11()), var1);
            }
         }
      }
   }

   public static void method2(com.moonsworth.lunar.client.coordinates.Coordinates var0, Source var1) {
      FogIterator var2 = ThreadModuleDump63.method4().method81();
      if (var2.method34()) {
         Bridge5Extension6 var3 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new ConfirmDialog("hostedWorldJoin", var0, var2x -> {
            ThreadModuleDump63.method3().bridge$displayScreen(var3);
            var2.method38(null);
         })));
         ThreadModuleDump63.method5()
            .ifPresentOrElse(
               var4 -> {
                  var2.method40(var0.uuid());
                  var4.method99()
                     .joinHostedWorld(
                        null,
                        JoinHostedWorldRequest.newBuilder().setWorldHostUuid(ThreadModuleDump66.method3(var0.uuid())).setSource(var1).build(),
                        var3xx -> {
                           if (var3xx.getStatus() != Status.STATUS_OK) {
                              Slayer.method5("Failed to join hosted world: " + var3xx.getStatus(), new Object[0]);
                              ThreadModuleDump63.method3().bridge$displayScreen(var3);
                              ThreadModuleDump63.method4()
                                 .method69()
                                 .method6(
                                    NotificationType.ERROR,
                                    ThreadModuleDump63.method4().method67().method2("popups", "hostedWorldErrorTitle"),
                                    ThreadModuleDump63.method4().method67().method2("popups", "hostedWorldErrorContent", var3xx.getStatusValue())
                                 );
                           } else {
                              field1.add(() -> {
                                 if (var2.method20()) {
                                    var2.method12();
                                 }

                                 ThreadModuleDump10.disconnect();
                                 var2.method40(var0.uuid());
                                 AddressAndPort var3xxx = var3xx.getRelay();
                                 Bridge3_19 var4x = Bridge.method8().method50("Hosted World", var3xxx.getAddress() + ":" + var3xxx.getPort(), false);
                                 var4x.bridge$disableResourcePack();
                                 ThreadModuleDump63.method3().bridge$connect(var4x, null);
                                 var2.method38(var0);
                              });
                           }
                        }
                     );
               },
               () -> {
                  ThreadModuleDump63.method3().bridge$displayScreen(var3);
                  var2.method38(null);
               }
            );
      }
   }

   private static void method3(Memory var0) {
      ThreadModuleDump10.disconnect();
      ThreadModuleDump63.method3().bridge$displayScreen(null);
      PublicServer var1 = var0.getLocation().getPublicServer();
      ThreadModuleDump63.method3().bridge$connect(Bridge.method8().method50(var1.getName(), var1.getPrimaryAddress(), false), null);
   }

   public static void method4(Memory var0) {
      Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      ThreadModuleDump63.method3()
         .bridge$displayScreen(Bridge.method8().method18(new PlayerConfirmDialog("hostedWorldInvite", var0.method11(), var0.method10(), "invite", var2 -> {
            if (var2) {
               FogIterator var3 = ThreadModuleDump63.method4().method81();
               var3.method23(var0.method10());
               var3.method9(var3.method28().method1());
            }

            ThreadModuleDump63.method3().bridge$displayScreen(var1);
         })));
   }

   public static boolean method5(Memory var0) {
      if (ThreadModuleDump63.method3().bridge$getSession() == null
         || var0.method10().equals(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId())) {
         return false;
      }

      if (var0.getLocation() == null) {
         return false;
      }

      return switch (var0.getLocation().getLocationCase()) {
         case PUBLIC_SERVER -> true;
         case HOSTED_WORLD -> !ThreadModuleDump63.method4().method81().method26()
            || ThreadModuleDump63.method4().method81().method37().uuid() != var0.method10();
         default -> false;
      };
   }

   public static AnimatedValue method6(Memory var0, GuiWidget var1) {
      if (ThreadModuleDump63.method3().bridge$getSession() != null && var0.getLocation() != null) {
         if (var0.getLocation().getLocationCase() == LocationCase.HOSTED_WORLD) {
            boolean var2 = ThreadModuleDump63.method4().method81().method26() && var0.method10().equals(ThreadModuleDump63.method4().method81().method39());
            Set var3 = ThreadModuleDump63.method4().method81().method30();
            boolean var4;
            synchronized (var3) {
               var4 = ThreadModuleDump63.method4().method81().method20() && var3.stream().anyMatch(var1x -> var1x.method5().equals(var0.method10()));
            }

            if (var0.getHostedWorldJoinability() != Joinability.JOINABILITY_UNSPECIFIED && !var2 && !var4) {
               if (!ThreadModuleDump63.method4().method81().method34()) {
                  var1.method17("joinWorldDisabled", new Object[0]);
                  return new AnimatedValue(ThreadModuleDump23.method10(217, 217, 217, 255), ThreadModuleDump23.method10(217, 217, 217, 191));
               }

               if (var0.getHostedWorldJoinability() == Joinability.JOINABILITY_ALLOWED) {
                  var1.method17("joinWorld", new Object[0]);
                  return new AnimatedValue(ThreadModuleDump23.method10(0, 255, 101, 255), ThreadModuleDump23.method10(0, 255, 101, 191));
               }

               if (var0.getHostedWorldJoinability() == Joinability.JOINABILITY_WORLD_FULL) {
                  var1.method17("worldFull", new Object[0]);
               } else if (var0.getHostedWorldJoinability() == Joinability.JOINABILITY_INCOMPATIBLE_MINECRAFT_VERSION) {
                  Config var5 = var0.method12();
                  String var6 = var5 != null ? var5.getDisplayName() : "unknown version";
                  var1.method17("worldRequiresVersion", new Object[]{var6});
               } else {
                  var1.method17("worldUnrecognizedError", new Object[0]);
               }

               return new AnimatedValue(ThreadModuleDump23.method10(217, 217, 217, 255), ThreadModuleDump23.method10(217, 217, 217, 191));
            }
         } else if (var0.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER) {
            var1.method17("joinServer", new Object[0]);
            return new AnimatedValue(ThreadModuleDump23.method10(0, 255, 101, 255), ThreadModuleDump23.method10(0, 255, 101, 191));
         }

         return null;
      } else {
         return null;
      }
   }
}

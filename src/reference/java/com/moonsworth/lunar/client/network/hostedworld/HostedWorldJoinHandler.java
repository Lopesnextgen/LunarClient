package com.moonsworth.lunar.client.network.hostedworld;

import com.lunarclient.common.v1.PublicServer;
import com.lunarclient.common.v1.Location.LocationCase;
import com.lunarclient.websocket.hostedworld.v1.AddressAndPort;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldResponse.Status;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.ConfirmDialog;
import com.moonsworth.lunar.client.gui.PlayerConfirmDialog;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.net.DisconnectUtils;

public class HostedWorldJoinHandler implements EventBusAccess {
   private static final List<Runnable> field1 = new ArrayList<>();

   public HostedWorldJoinHandler() {
      this.handle(EventTick.class, arg0 -> {
         while (field1.size() > 0) {
            field1.remove(0).run();
         }
      });
   }

   public static void method1(Memory memory0, Source source1) {
      if (memory0.getLocation() != null) {
         if (!Bridge.getMinecraftVersion().method19()
            || Ref.method3().bridge$getIntegratedServer() == null
            || Ref.method3().bridge$getIntegratedServer().bridge$isReady()) {
            if (memory0.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER) {
               method3(memory0);
            } else if (memory0.getLocation().getLocationCase() == LocationCase.HOSTED_WORLD && memory0.getHostedWorldJoinability() == Joinability.JOINABILITY_ALLOWED
               )
             {
               method2(new com.moonsworth.lunar.client.network.hostedworld.HostedWorldHost(memory0.method10(), memory0.method11()), source1);
            }
         }
      }
   }

   public static void method2(com.moonsworth.lunar.client.network.hostedworld.HostedWorldHost coordinates0, Source source1) {
      FogIterator fogiterator2 = Ref.method4().method81();
      if (fogiterator2.method34()) {
         GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new ConfirmDialog("hostedWorldJoin", coordinates0, arg2x -> {
            Ref.method3().bridge$displayScreen(bridge5extension63);
            fogiterator2.method38(null);
         })));
         Ref.method5()
            .ifPresentOrElse(
               arg4 -> {
                  fogiterator2.method40(coordinates0.uuid());
                  arg4.method99()
                     .joinHostedWorld(
                        null,
                        JoinHostedWorldRequest.newBuilder().setWorldHostUuid(ProtoConverter.method3(coordinates0.uuid())).setSource(source1).build(),
                        arg3xx -> {
                           if (arg3xx.getStatus() != Status.STATUS_OK) {
                              LunarLogger.method5("Failed to join hosted world: " + arg3xx.getStatus(), new Object[0]);
                              Ref.method3().bridge$displayScreen(bridge5extension63);
                              Ref.method4()
                                 .method69()
                                 .method6(
                                    NotificationType.ERROR,
                                    Ref.method4().method67().method2("popups", "hostedWorldErrorTitle", new Object[0]),
                                    Ref.method4().method67().method2("popups", "hostedWorldErrorContent", new Object[]{arg3xx.getStatusValue()})
                                 );
                           } else {
                              field1.add(() -> {
                                 if (fogiterator2.method20()) {
                                    fogiterator2.method12();
                                 }

                                 DisconnectUtils.method1();
                                 fogiterator2.method40(coordinates0.uuid());
                                 AddressAndPort addressandport3xxx = arg3xx.getRelay();
                                 ServerDataBridge bridge3_194x = Bridge.method8().method50("Hosted World", addressandport3xxx.getAddress() + ":" + addressandport3xxx.getPort(), false);
                                 bridge3_194x.bridge$disableResourcePack();
                                 Ref.method3().bridge$connect(bridge3_194x, null);
                                 fogiterator2.method38(coordinates0);
                              });
                           }
                        }
                     );
               },
               () -> {
                  Ref.method3().bridge$displayScreen(bridge5extension63);
                  fogiterator2.method38(null);
               }
            );
      }
   }

   private static void method3(Memory memory0) {
      DisconnectUtils.method1();
      Ref.method3().bridge$displayScreen(null);
      PublicServer publicserver1 = memory0.getLocation().getPublicServer();
      Ref.method3().bridge$connect(Bridge.method8().method50(publicserver1.getName(), publicserver1.getPrimaryAddress(), false), null);
   }

   public static void method4(Memory memory0) {
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreen();
      Ref.method3()
         .bridge$displayScreen(Bridge.method8().method18(new PlayerConfirmDialog("hostedWorldInvite", memory0.method11(), memory0.method10(), "invite", arg2 -> {
            if (arg2) {
               FogIterator fogiterator3 = Ref.method4().method81();
               fogiterator3.method23(memory0.method10());
               fogiterator3.method9(fogiterator3.method28().method1());
            }

            Ref.method3().bridge$displayScreen(bridge5extension61);
         })));
   }

   public static boolean method5(Memory memory0) {
      if (Ref.method3().bridge$getSession() == null
         || memory0.method10().equals(Ref.method3().bridge$getSession().bridge$getProfile().getId())) {
         return false;
      }

      if (memory0.getLocation() == null) {
         return false;
      }

      return switch (memory0.getLocation().getLocationCase()) {
         case PUBLIC_SERVER -> true;
         case HOSTED_WORLD -> !Ref.method4().method81().method26()
            || Ref.method4().method81().method37().uuid() != memory0.method10();
         default -> false;
      };
   }

   public static AnimatedValue method6(Memory memory0, GuiWidget calculator2handler1) {
      if (Ref.method3().bridge$getSession() != null && memory0.getLocation() != null) {
         if (memory0.getLocation().getLocationCase() == LocationCase.HOSTED_WORLD) {
            boolean flag2 = Ref.method4().method81().method26() && memory0.method10().equals(Ref.method4().method81().method39());
            Set set3 = Ref.method4().method81().method30();
            boolean flag4;
            synchronized (set3) {
               flag4 = Ref.method4().method81().method20() && set3.stream().anyMatch(arg1x -> arg1x.method5().equals(memory0.method10()));
            }

            if (memory0.getHostedWorldJoinability() != Joinability.JOINABILITY_UNSPECIFIED && !flag2 && !flag4) {
               if (!Ref.method4().method81().method34()) {
                  calculator2handler1.method17("joinWorldDisabled", new Object[0]);
                  return new AnimatedValue(ColorUtils.method10(217, 217, 217, 255), ColorUtils.method10(217, 217, 217, 191));
               }

               if (memory0.getHostedWorldJoinability() == Joinability.JOINABILITY_ALLOWED) {
                  calculator2handler1.method17("joinWorld", new Object[0]);
                  return new AnimatedValue(ColorUtils.method10(0, 255, 101, 255), ColorUtils.method10(0, 255, 101, 191));
               }

               if (memory0.getHostedWorldJoinability() == Joinability.JOINABILITY_WORLD_FULL) {
                  calculator2handler1.method17("worldFull", new Object[0]);
               } else if (memory0.getHostedWorldJoinability() == Joinability.JOINABILITY_INCOMPATIBLE_MINECRAFT_VERSION) {
                  Config config5 = memory0.method12();
                  String text6 = config5 != null ? config5.getDisplayName() : "unknown version";
                  calculator2handler1.method17("worldRequiresVersion", new Object[]{text6});
               } else {
                  calculator2handler1.method17("worldUnrecognizedError", new Object[0]);
               }

               return new AnimatedValue(ColorUtils.method10(217, 217, 217, 255), ColorUtils.method10(217, 217, 217, 191));
            }
         } else if (memory0.getLocation().getLocationCase() == LocationCase.PUBLIC_SERVER) {
            calculator2handler1.method17("joinServer", new Object[0]);
            return new AnimatedValue(ColorUtils.method10(0, 255, 101, 255), ColorUtils.method10(0, 255, 101, 191));
         }

         return null;
      } else {
         return null;
      }
   }
}

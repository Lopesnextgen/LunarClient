package com.moonsworth.lunar.client.network.transfer;

import com.google.common.collect.Maps;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import com.google.protobuf.ProtocolStringList;
import com.lunarclient.apollo.transfer.v1.PingData;
import com.lunarclient.apollo.transfer.v1.PingRequest;
import com.lunarclient.apollo.transfer.v1.PingResponse;
import com.lunarclient.apollo.transfer.v1.TransferRequest;
import com.lunarclient.apollo.transfer.v1.TransferResponse;
import com.lunarclient.apollo.transfer.v1.TransferResponse.Status;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.OldServerPingerBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransferHandler extends ApolloModuleHandler implements Translatable {
   private static final int field4 = 10;
   private static final long field5 = 3000L;
   private static final ExecutorService field6 = Executors.newCachedThreadPool(new DefaultThreadFactory("lunar-client-transfer-thread", true));
   private final Map<ByteString, Map<String, PingServerData>> field7 = Maps.newConcurrentMap();
   private OldServerPingerBridge field8;

   public TransferHandler() {
      super("transfer", "Transfer");
      this.handle(EventTick.class, this::method5);
   }

   protected void onDisable() {
      this.field7.clear();
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(TransferRequest.class, TransferResponse.class, PingRequest.class, PingResponse.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      if (!Ref.method4().method40().method85().method19()) {
         highlightimpl_31.unpack(TransferRequest.class).ifPresent(arg1x -> {
            String text2 = arg1x.getServerIp();
            ServerDataBridge bridge3_193 = Ref.method3().bridge$getCurrentServerData();
            this.debug("Transfer request %s -> %s", arg1x.getRequestId().toStringUtf8(), text2);
            if ((Boolean)Ref.method4().method41().method6().method59().get()) {
               TransferSrvResolver.method4(text2, arg4 -> {
                  if (arg4 != null && !arg4.isEmpty()) {
                     if (bridge3_193 != null) {
                        if (arg4.stream().anyMatch(arg1xxx -> TransferSrvResolver.method1(arg1xxx, bridge3_193.bridge$serverIP()))) {
                           Ref.method3().bridge$submit(() -> {
                              this.method10(arg1x, Status.STATUS_ACCEPTED);
                              this.method7(text2);
                           });
                        } else {
                           Ref.method3().bridge$submit(() -> Ref.method3().bridge$displayScreen(this.method6(arg1x, text2)));
                        }
                     }
                  } else {
                     Ref.method3().bridge$submit(() -> Ref.method3().bridge$displayScreen(this.method6(arg1x, text2)));
                  }
               });
            } else {
               this.method10(arg1x, Status.STATUS_ACCEPTED);
               this.method7(text2);
            }
         });
         highlightimpl_31.unpack(PingRequest.class)
            .ifPresent(
               arg1x -> {
                  ProtocolStringList protocolstringlist2 = arg1x.getServerIpsList();
                  if (this.field8 == null) {
                     this.field8 = Bridge.method8().method27();
                  }

                  HashMap map3 = new HashMap();
                  long number4 = System.currentTimeMillis() + 3000L;

                  for (String text7 : protocolstringlist2) {
                     PingServerData nameplate28 = new PingServerData(false, false, number4, null);
                     if (map3.putIfAbsent(text7, nameplate28) == null) {
                        if (map3.size() > 10) {
                           nameplate28.method6(true);
                        } else {
                           field6.submit(() -> this.method3(text7, nameplate28));
                        }
                     }
                  }

                  this.field7.put(arg1x.getRequestId(), map3);
                  this.debug(
                     "Ping request %s: %s addresses (%s unique), pinging %s",
                     arg1x.getRequestId().toStringUtf8(),
                     protocolstringlist2.size(),
                     map3.size(),
                     Math.min(map3.size(), 10)
                  );
               }
            );
      }
   }

   private void method3(String text1, PingServerData nameplate22) {
      InetSocketAddress inetsocketaddress3 = (InetSocketAddress)Ref.method3().bridge$resolveAddress(text1).orElse(null);
      if (inetsocketaddress3 != null && !this.method12(inetsocketaddress3.getAddress())) {
         ServerDataBridge bridge3_194 = Bridge.method8().method50(inetsocketaddress3.getHostName(), inetsocketaddress3.getHostString() + ":" + inetsocketaddress3.getPort(), false);
         bridge3_194.bridge$setPingCallback(arg3x -> {
            nameplate22.setCompleted(true);
            this.debug("%s -> pong, ping=%sms", text1, arg3x);
         });
         nameplate22.method7(bridge3_194);

         try {
            this.field8.bridge$ping(bridge3_194);
         } catch (Exception exception6) {
            nameplate22.method6(true);
            this.debug("%s -> ping threw %s, timed out immediately", text1, exception6.getClass().getSimpleName());
         }
      } else {
         nameplate22.method6(true);
         this.debug("%s -> unresolvable or private, timed out immediately", text1);
      }
   }

   private boolean method4() {
      ApolloDebugMod apollodebugmod1 = Ref.method4().method40().method80();
      return apollodebugmod1 != null && apollodebugmod1.isEnabled() && apollodebugmod1.method21();
   }

   private void debug(String text1, Object... items2) {
      if (this.method4()) {
         Ref.method4().method40().method80().method12(text1, items2);
      }
   }

   private void method5(EventTick highlightimpl21) {
      if (Ref.MC_VERSION >= 7 && this.field8 != null) {
         this.field8.bridge$tick();
      }

      if (Ref.method9() != null && !this.field7.isEmpty()) {
         for (Entry entry3 : this.field7.entrySet()) {
            boolean flag4 = false;
            ByteString bytestring5 = (ByteString)entry3.getKey();
            Map map6 = (Map)entry3.getValue();

            for (PingServerData nameplate28 : map6.values()) {
               if (!nameplate28.isCompleted() && !nameplate28.method2()) {
                  if (nameplate28.method3() < System.currentTimeMillis()) {
                     nameplate28.method6(true);
                  } else {
                     flag4 = true;
                  }
               }
            }

            if (!flag4) {
               this.method11(bytestring5, map6);
               this.field7.remove(bytestring5);
            }
         }
      }
   }

   private GuiScreenBridge method6(TransferRequest transferrequest1, String text2) {
      GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
      String text4 = this.OHROCHICOIOICHOCRROORRCIIICIHO("message", new Object[]{text2});
      String text5 = text4.substring(0, text4.length() / 2);
      String text6 = text4.substring(text4.length() / 2);
      ConfirmScreen bridge7iterator_27 = new ConfirmScreen(
         () -> new String[]{this.OHROCHICOIOICHOCRROORRCIIICIHO("header", new Object[0]), text5, text6},
         this.OHROCHICOIOICHOCRROORRCIIICIHO("cancel", new Object[0]),
         this.OHROCHICOIOICHOCRROORRCIIICIHO("confirm", new Object[0]),
         arg4x -> {
            this.method10(transferrequest1, arg4x ? Status.STATUS_ACCEPTED : Status.STATUS_REJECTED);
            if (arg4x) {
               this.method7(text2);
            } else {
               Ref.method3().bridge$displayScreen(bridge5extension63);
            }
         }
      );
      return Bridge.method8().method18(bridge7iterator_27);
   }

   private void method7(String text1) {
      Bridge.method9().bridge$submit(() -> {
         this.debug("Transferring to %s", text1);
         this.method8();
         this.method9(text1);
      });
   }

   private void method8() {
      NetHandlerPlayClientBridge bridgeextension_71 = Ref.method9();
      if (bridgeextension_71 != null) {
         bridgeextension_71.bridge$transferQuit();
      }

      Ref.method3().bridge$loadWorld(null);
   }

   private void method9(String text1) {
      ServerDataBridge bridge3_192 = Bridge.method8().method50(text1, text1, false);
      Bridge.method9().bridge$connect(bridge3_192, null);
   }

   public void method10(TransferRequest transferrequest1, Status status2) {
      this.debug("Transfer response %s -> %s", transferrequest1.getRequestId().toStringUtf8(), status2);
      TransferResponse transferresponse3 = TransferResponse.newBuilder().setRequestId(transferrequest1.getRequestId()).setStatus(status2).build();
      this.sendPacket(transferresponse3);
   }

   public void method11(ByteString bytestring1, Map<String, PingServerData> map2) {
      List list3 = map2.entrySet()
         .stream()
         .map(
            arg0 -> {
               PingServerData nameplate21x = (PingServerData)arg0.getValue();
               boolean flag2x = !nameplate21x.method2() && nameplate21x.method4() != null;
               int number3x = flag2x ? (int)nameplate21x.method4().bridge$getPingToServer() : 0;
               com.lunarclient.apollo.transfer.v1.PingData.Status status4x = flag2x
                  ? com.lunarclient.apollo.transfer.v1.PingData.Status.STATUS_SUCCESS
                  : com.lunarclient.apollo.transfer.v1.PingData.Status.STATUS_TIMED_OUT;
               return PingData.newBuilder().setServerIp((String)arg0.getKey()).setStatus(status4x).setPing(number3x).build();
            }
         )
         .toList();
      PingResponse pingresponse4 = PingResponse.newBuilder().setRequestId(bytestring1).addAllPingData(list3).build();
      if (this.method4()) {
         long number5 = map2.values().stream().findFirst().map(arg0 -> System.currentTimeMillis() - (arg0.method3() - 3000L)).orElse(0L);
         this.debug(
            "Ping response %s after %sms: %s",
            bytestring1.toStringUtf8(),
            number5,
            list3.stream().map(arg0 -> arg0.getServerIp() + "=" + arg0.getStatus() + "/" + arg0.getPing() + "ms").toList()
         );
      }

      this.sendPacket(pingresponse4);
   }

   private boolean method12(InetAddress inetaddress1) {
      return inetaddress1.isLoopbackAddress() || inetaddress1.isSiteLocalAddress();
   }

   public String getLanguagePath() {
      return "gui.transfer";
   }
}

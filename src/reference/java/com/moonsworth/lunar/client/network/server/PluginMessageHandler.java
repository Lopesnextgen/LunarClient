package com.moonsworth.lunar.client.network.server;

import com.google.common.base.Charsets;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.network.EventPluginChannelRegister;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerPing;
import com.moonsworth.lunar.client.event.mixin.gui.EventPluginMessage;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PluginMessageHandler implements EventBusAccess, Translatable {
   public static final String field1 = "lunarclient:pm";
   public static List<String> field2 = new ArrayList<>();
   private static final Set<String> field3 = new HashSet<>();
   private static final String field4 = "\u0000";

   public PluginMessageHandler() {
      this.handle(EventPluginMessage.class, this::method1);
      this.handle(EventServerPing.class, this::method2);
      this.handle(EventDisconnect.class, this::method3);
      this.handle(EventServerChange.class, this::method4);
   }

   private void method1(EventPluginMessage highlightimpl81) {
      if (highlightimpl81.getChannel() != null) {
         NetHandlerPlayClientBridge bridgeextension_72 = Ref.method9();
         if (bridgeextension_72 != null) {
            if (bridgeextension_72.bridge$getRegisterPacketName() != null) {
               if (highlightimpl81.getChannel().equals(bridgeextension_72.bridge$getRegisterPacketName())) {
                  String text3 = new String(highlightimpl81.getData(), Charsets.UTF_8);
                  this.method5(bridgeextension_72, List.of(text3.split("\u0000")));
               }
            }
         }
      }
   }

   private void method2(EventServerPing highlightimpl51) {
      LunarEventBus.method29().method12(EventServerChange.class, () -> new EventServerChange(false));
   }

   private void method3(EventDisconnect highlightimpl111) {
      LunarEventBus.method29().method12(EventServerChange.class, () -> new EventServerChange(true));
   }

   private void method4(EventServerChange highlightimpl101) {
      Client client2 = Client.method109();
      client2.setWorld("");
      client2.method48().removeIf(Waypoint::method41);
      client2.method57().clear();
   }

   public void method5(NetHandlerPlayClientBridge bridgeextension_71, List<String> list2) {
      field2 = list2;
      ArrayList list3 = new ArrayList<>(field3);
      boolean flag4 = list2.contains("lunarclient:pm") || list2.contains(bridgeextension_71.bridge$getLCChannelName());
      if (flag4) {
         list3.add(list2.contains("lunarclient:pm") ? "lunarclient:pm" : bridgeextension_71.bridge$getLCChannelName());
      }

      String text5 = list3.stream().filter(field2::contains).collect(Collectors.joining("\u0000"));
      if (!text5.isEmpty()) {
         BridgeImplementation bridge26 = Bridge.method8();
         bridgeextension_71.bridge$addToSendQueue(
            bridge26.method24(bridgeextension_71.bridge$getRegisterPacketName(), bridge26.method23(Unpooled.wrappedBuffer(text5.getBytes(StandardCharsets.UTF_8))))
         );
      }

      ApolloDebugMod apollodebugmod7 = Client.method109().method40().method80();
      if (apollodebugmod7 != null && apollodebugmod7.isEnabled() && apollodebugmod7.method16()) {
         apollodebugmod7.method16(list2, text5);
      }

      if (list2.contains("lunar:apollo") || list2.contains("apollo:json") || list2.contains("lunarclient:pm")) {
         Client.method109().method84().method21();
      }

      LunarEventBus.method29().method12(EventPluginChannelRegister.class, EventPluginChannelRegister::new);
      LunarEventBus.method29().method12(EventServerChange.class, () -> new EventServerChange(false));
   }

   public String getLanguagePath() {
      return "settings";
   }

   public static void method6(String text0) {
      field3.add(text0);
   }

   public static void method7(String text0) {
      field3.remove(text0);
   }
}

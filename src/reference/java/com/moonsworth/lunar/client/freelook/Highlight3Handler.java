package com.moonsworth.lunar.client.freelook;

import com.google.common.base.Charsets;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.PlayerLivingUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerPingEvent;
import com.moonsworth.lunar.client.event.mixin.gui.PluginMessageEvent;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Highlight3Handler implements EventRegistrar, Calculator2 {
   public static final String field1 = "lunarclient:pm";
   public static List<String> field2 = new ArrayList<>();
   private static final Set<String> field3 = new HashSet<>();
   private static final String field4 = "\u0000";

   public Highlight3Handler() {
      this.handle(PluginMessageEvent.class, this::method1);
      this.handle(ServerPingEvent.class, this::method2);
      this.handle(DisconnectEvent.class, this::method3);
      this.handle(ServerChangeEvent.class, this::method4);
   }

   private void method1(PluginMessageEvent var1) {
      if (var1.getChannel() != null) {
         ClientPacketListenerBridge var2 = ThreadModuleDump63.method9();
         if (var2 != null) {
            if (var2.bridge$getRegisterPacketName() != null) {
               if (var1.getChannel().equals(var2.bridge$getRegisterPacketName())) {
                  String var3 = new String(var1.getData(), Charsets.UTF_8);
                  this.method5(var2, List.of(var3.split("\u0000")));
               }
            }
         }
      }
   }

   private void method2(ServerPingEvent var1) {
      ClientEventBus.method29().method12(ServerChangeEvent.class, () -> new ServerChangeEvent(false));
   }

   private void method3(DisconnectEvent var1) {
      ClientEventBus.method29().method12(ServerChangeEvent.class, () -> new ServerChangeEvent(true));
   }

   private void method4(ServerChangeEvent var1) {
      Client var2 = Client.method109();
      var2.setWorld("");
      var2.method48().removeIf(GuiHandler2::method41);
      var2.method57().clear();
   }

   public void method5(ClientPacketListenerBridge var1, List<String> var2) {
      field2 = var2;
      ArrayList var3 = new ArrayList<>(field3);
      boolean var4 = var2.contains("lunarclient:pm") || var2.contains(var1.bridge$getLCChannelName());
      if (var4) {
         var3.add(var2.contains("lunarclient:pm") ? "lunarclient:pm" : var1.bridge$getLCChannelName());
      }

      String var5 = var3.stream().filter(field2::contains).collect(Collectors.joining("\u0000"));
      if (!var5.isEmpty()) {
         BridgeImplementation var6 = Bridge.method8();
         var1.bridge$addToSendQueue(
            var6.method24(var1.bridge$getRegisterPacketName(), var6.method23(Unpooled.wrappedBuffer(var5.getBytes(StandardCharsets.UTF_8))))
         );
      }

      ApolloDebugMod var7 = Client.method109().method40().method80();
      if (var7 != null && var7.isEnabled() && var7.method16()) {
         var7.method16(var2, var5);
      }

      if (var2.contains("lunar:apollo") || var2.contains("apollo:json") || var2.contains("lunarclient:pm")) {
         Client.method109().method84().method21();
      }

      ClientEventBus.method29().method12(PlayerLivingUpdateEvent.class, PlayerLivingUpdateEvent::new);
      ClientEventBus.method29().method12(ServerChangeEvent.class, () -> new ServerChangeEvent(false));
   }

   @Override
   public String getLanguagePath() {
      return "settings";
   }

   public static void method6(String var0) {
      field3.add(var0);
   }

   public static void method7(String var0) {
      field3.remove(var0);
   }
}

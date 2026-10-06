package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.gui.notification.Notification;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.util.Slayer;

public class Highlight3Iterator18 extends ApolloModuleHandler {
   public Highlight3Iterator18() {
      super("notification", "Notification");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(ResetNotificationsMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(DisplayNotificationMessage.class).ifPresent(var1x -> {
         if (ThreadModuleDump63.method4().method41().method6().method46().get()) {
            NotificationManager var2 = ThreadModuleDump63.method4().method69();
            ApolloModuleManager var3 = ThreadModuleDump63.method4().method84();
            Component var4 = Rewindhandlers3.method4(var1x.getTitleAdventureJsonLines());
            Component var5 = Rewindhandlers3.method4(var1x.getDescriptionAdventureJsonLines());
            String var6 = var1x.getResourceLocation();
            long var7 = NetworkTypes.fromProtobuf(var1x.getDisplayTime()).toMillis();
            String var9 = var4 != null ? AdventureTextBridge.getTextContentForRendering(var4) : var1x.getTitle();
            String var10 = var5 != null ? AdventureTextBridge.getTextContentForRendering(var5) : var1x.getDescription();
            if (var4 != null && var5 != null) {
               var3.method15(this.getId(), "DisplayNotificationMessage");
            } else {
               var3.method15(this.getId(), "DisplayNotificationMessage-Deprecated");
            }

            if (var6.isEmpty()) {
               var2.method6(NotificationType.INFO, var9, var10).method10(var7);
            } else {
               ResourceLocationBridge var11;
               try {
                  var11 = ResourceLocationBridge.create(var6);
               } catch (Exception var13) {
                  Slayer.method5("Failed to create resource location: " + var6);
                  var13.printStackTrace();
                  return;
               }

               Notification var12 = var2.method5(var11, var9, var10);
               if (var1x.hasDisplayTime()) {
                  var12.method10(var7);
               }
            }
         }
      });
      highlightImpl_3.unpack(ResetNotificationsMessage.class).ifPresent(var1x -> this.method3());
   }

   private void method3() {
      ThreadModuleDump63.method4().method69().method14();
   }
}

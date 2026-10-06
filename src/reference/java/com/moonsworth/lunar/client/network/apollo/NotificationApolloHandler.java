package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.notification.v1.DisplayNotificationMessage;
import com.lunarclient.apollo.notification.v1.ResetNotificationsMessage;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Set;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.util.LunarLogger;

public class NotificationApolloHandler extends ApolloModuleHandler {
   public NotificationApolloHandler() {
      super("notification", "Notification");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(ResetNotificationsMessage.class);
   }

   protected void onEnable() {
      this.method3();
   }

   protected void onDisable() {
      this.method3();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(DisplayNotificationMessage.class).ifPresent(arg1x -> {
         if ((Boolean)Ref.method4().method41().method6().method46().get()) {
            NotificationManager fogimpl2 = Ref.method4().method69();
            ApolloModuleManager foghandler23 = Ref.method4().method84();
            Component component4 = ApolloPacketUtils.method4(arg1x.getTitleAdventureJsonLines());
            Component component5 = ApolloPacketUtils.method4(arg1x.getDescriptionAdventureJsonLines());
            String text6 = arg1x.getResourceLocation();
            long number7 = NetworkTypes.fromProtobuf(arg1x.getDisplayTime()).toMillis();
            String text9 = component4 != null ? TextBridge.getTextContentForRendering(component4) : arg1x.getTitle();
            String text10 = component5 != null ? TextBridge.getTextContentForRendering(component5) : arg1x.getDescription();
            if (component4 != null && component5 != null) {
               foghandler23.method15(this.getId(), "DisplayNotificationMessage");
            } else {
               foghandler23.method15(this.getId(), "DisplayNotificationMessage-Deprecated");
            }

            if (text6.isEmpty()) {
               fogimpl2.method6(com.moonsworth.lunar.client.gui.notification.NotificationType.INFO, text9, text10).method10(number7);
            } else {
               ResourceLocationBridge horsestats1411;
               try {
                  horsestats1411 = ResourceLocationBridge.create(text6);
               } catch (Exception exception13) {
                  LunarLogger.method5("Failed to create resource location: " + text6, new Object[0]);
                  exception13.printStackTrace();
                  return;
               }

               com.moonsworth.lunar.client.gui.notification.Notification gui2iterator12 = fogimpl2.method5(horsestats1411, text9, text10);
               if (arg1x.hasDisplayTime()) {
                  gui2iterator12.method10(number7);
               }
            }
         }
      });
      highlightimpl_31.unpack(ResetNotificationsMessage.class).ifPresent(arg1x -> this.method3());
   }

   private void method3() {
      Ref.method4().method69().method14();
   }
}

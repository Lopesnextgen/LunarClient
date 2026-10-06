package com.moonsworth.lunar.client.gui.notification;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.ui.notification.FriendNotification;
import com.moonsworth.lunar.client.ui.notification.Notification;
import com.moonsworth.lunar.client.ui.notification.NotificationType;

public class NotificationManager extends ItemSetHandler<Notification> implements Gui {
   public static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "sound/friend_message.ogg");
   private final ConcurrentLinkedQueue<Notification> field3 = new ConcurrentLinkedQueue<>();

   protected Set<Notification> method3() {
      return new LinkedHashSet<>();
   }

   public void init() {
      super.init();
   }

   public Notification method2(String var1, String var2) {
      return this.method5(CosmeticManager.field38, var1, var2);
   }

   public Notification method3(String var1) {
      return this.method4(CosmeticManager.field38, var1);
   }

   public Notification method4(ResourceLocationBridge var1, String var2) {
      return this.method10(new Notification(var1, var2));
   }

   public Notification method5(ResourceLocationBridge var1, String var2, String var3) {
      return this.method10(new Notification(var1, var2, var3));
   }

   public Notification method6(NotificationType var1, String var2, String var3) {
      return this.method10(new Notification(var1.getIcon(), var2, var3));
   }

   public Notification method7(NotificationType var1, String var2) {
      return this.method10(new Notification(var1, var2));
   }

   public Notification method8(String var1, String var2) {
      return this.method10(new Notification(var1, var2));
   }

   public Notification method9(String var1) {
      return this.method10(new Notification(var1));
   }

   public Notification method10(Notification var1) {
      if (ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method37()) {
         this.method13().add(var1);
         this.field3.add(var1);
      }

      return var1;
   }

   public void method11(UUID var1, String var2) {
      if (ThreadModuleDump63.method4().method31().method16().isShowNotifications()) {
         Memory var3 = ThreadModuleDump63.method4().method50().method2(var1);
         if (var3 != null) {
            this.method10(new FriendNotification(var1, var3, var2));
         }
      }
   }

   public void method12(Memory var1, String var2) {
      if (ThreadModuleDump63.method4().method31().method16().isShowNotifications()) {
         ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field2);
         FriendNotification var3 = new FriendNotification(var1.method10(), var1, var2);
         var3.method2(true);
         this.method10(var3);
      }
   }

   @Nullable
   @Override
   public JsonElement method128() {
      Notification var1 = this.field3.poll();
      return var1 != null ? var1.provide() : null;
   }

   public void method14() {
      this.method13().clear();
   }

   public static String method15(String text, Object... var1) {
      return Client.method109().method67().method2("popups", text, var1);
   }
}

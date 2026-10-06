package com.moonsworth.lunar.client.ui.notification;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public enum NotificationType {
   INFO("info"),
   SUCCESS("success"),
   WARNING("warning"),
   ERROR("error");

   private final ResourceLocationBridge icon;

   NotificationType(String text) {
      this.icon = ResourceLocationBridge.create("lunar", "icons/notifications/" + text + "-24x24.png");
   }

   @Generated
   public ResourceLocationBridge getIcon() {
      return this.icon;
   }
}

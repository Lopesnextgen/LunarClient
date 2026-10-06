package com.moonsworth.lunar.client.ui.notification;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.gui.notification.FormattedNotification;

public class HostedWorldNotification extends FormattedNotification {
   public HostedWorldNotification(String text1) {
      super(text1, "hostedWorldHint");
      this.durationMs = (long)((float)this.durationMs * 1.5F);
   }

   public HostedWorldNotification(String text1, String text) {
      super(text1, text, "hostedWorldHint");
      this.durationMs = (long)((float)this.durationMs * 1.5F);
   }

   @Override
   public boolean hasIcon() {
      return true;
   }

   @Override
   public ResourceLocationBridge getIcon() {
      return NotificationType.SUCCESS.getIcon();
   }
}

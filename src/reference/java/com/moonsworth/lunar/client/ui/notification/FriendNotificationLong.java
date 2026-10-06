package com.moonsworth.lunar.client.ui.notification;

import com.moonsworth.lunar.client.util.memory.Memory;
import java.util.UUID;

public class FriendNotificationLong extends FriendNotification {
   public FriendNotificationLong(UUID uuid1, Memory memory2, String text) {
      super(uuid1, memory2, text);
      this.durationMs = (long)((float)this.durationMs * 1.5F);
   }
}

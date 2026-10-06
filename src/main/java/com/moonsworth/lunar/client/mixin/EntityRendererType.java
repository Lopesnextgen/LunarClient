package com.moonsworth.lunar.client.mixin;

import com.lunarclient.websocket.friend.v1.OnlineFriendStatus;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public enum EntityRendererType {
   ONLINE("Online", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_ONLINE),
   AWAY("Away", false, OnlineFriendStatus.ONLINE_FRIEND_STATUS_AWAY),
   BUSY("Busy", false, OnlineFriendStatus.ONLINE_FRIEND_STATUS_BUSY),
   INVISIBLE("Invisible", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_INVISIBLE),
   OFFLINE("Offline", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_UNSPECIFIED);

   private static final Map<OnlineFriendStatus, EntityRendererType> protobufToEnum = new HashMap<>();
   private final String name;
   private final boolean showNotifications;
   private final OnlineFriendStatus protobuf;

   public static EntityRendererType fromProtobuf(OnlineFriendStatus onlineFriendStatus) {
      return protobufToEnum.getOrDefault(onlineFriendStatus, OFFLINE);
   }

   EntityRendererType(String var3, boolean flag, OnlineFriendStatus onlineFriendStatus) {
      this.name = var3;
      this.showNotifications = flag;
      this.protobuf = onlineFriendStatus;
   }

   @Override
   public String toString() {
      return this.name;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public boolean isShowNotifications() {
      return this.showNotifications;
   }

   @Generated
   public OnlineFriendStatus getProtobuf() {
      return this.protobuf;
   }

   static {
      for (EntityRendererType var3 : values()) {
         protobufToEnum.put(var3.getProtobuf(), var3);
      }
   }
}

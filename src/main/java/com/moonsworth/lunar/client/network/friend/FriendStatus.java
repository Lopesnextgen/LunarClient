package com.moonsworth.lunar.client.network.friend;

import com.lunarclient.websocket.friend.v1.OnlineFriendStatus;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public enum FriendStatus {
   ONLINE("Online", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_ONLINE),
   AWAY("Away", false, OnlineFriendStatus.ONLINE_FRIEND_STATUS_AWAY),
   BUSY("Busy", false, OnlineFriendStatus.ONLINE_FRIEND_STATUS_BUSY),
   INVISIBLE("Invisible", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_INVISIBLE),
   OFFLINE("Offline", true, OnlineFriendStatus.ONLINE_FRIEND_STATUS_UNSPECIFIED);

   private static final Map<OnlineFriendStatus, FriendStatus> protobufToEnum = new HashMap<>();
   private final String name;
   private final boolean showNotifications;
   private final OnlineFriendStatus protobuf;

   public static FriendStatus fromProtobuf(OnlineFriendStatus onlinefriendstatus0) {
      return protobufToEnum.getOrDefault(onlinefriendstatus0, OFFLINE);
   }

   FriendStatus(String text, boolean flag, OnlineFriendStatus onlinefriendstatus5) {
      this.name = text;
      this.showNotifications = flag;
      this.protobuf = onlinefriendstatus5;
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
      for (FriendStatus entityrenderertype3 : values()) {
         protobufToEnum.put(entityrenderertype3.getProtobuf(), entityrenderertype3);
      }
   }
}

package com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate;

import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.cosmetic.v2.CosmeticOwnershipVisibility;
import com.lunarclient.websocket.cosmetic.v2.SetCosmeticOwnershipVisibilityRequest;
import com.lunarclient.websocket.friend.v1.AcceptFriendRequestRequest;
import com.lunarclient.websocket.friend.v1.AddFriendPinRequest;
import com.lunarclient.websocket.friend.v1.BroadcastStatusChangeRequest;
import com.lunarclient.websocket.friend.v1.CancelFriendRequestRequest;
import com.lunarclient.websocket.friend.v1.DenyFriendRequestRequest;
import com.lunarclient.websocket.friend.v1.LastSeenVisibility;
import com.lunarclient.websocket.friend.v1.OfflineFriend;
import com.lunarclient.websocket.friend.v1.RemoveFriendPinRequest;
import com.lunarclient.websocket.friend.v1.SendFriendRequestRequest;
import com.lunarclient.websocket.friend.v1.SetLastSeenVisibilityRequest;
import com.lunarclient.websocket.friend.v1.ToggleFriendRequestsRequest;
import com.lunarclient.websocket.friend.v1.SendFriendRequestResponse.Status;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.lunarclient.websocket.store.v1.IncomingGiftPrivacy;
import com.lunarclient.websocket.store.v1.SetIncomingGiftPrivacyRequest;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mixin.EntityRenderer6;
import com.moonsworth.lunar.client.mixin.EntityRendererType;
import com.moonsworth.lunar.client.util.Highlight3Task;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.time.Instant;
import java.util.UUID;

public class FriendApiLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static long field1 = 0L;

   @CallbackJS("addFriend")
   public static void method2(String var0) {
      if (var0.matches("([a-zA-Z0-9_]+)") && var0.length() <= 16) {
         ThreadModuleDump63.method5()
            .ifPresent(
               var1 -> var1.method93()
                  .sendFriendRequest(
                     null,
                     SendFriendRequestRequest.newBuilder().setTargetUsername(var0).build(),
                     var0xx -> {
                        if (var0xx.getStatus() != Status.STATUS_OK) {
                           String var3 = switch (var0xx.getStatus()) {
                              case STATUS_TARGET_INVALID_USERNAME -> "Invalid username.";
                              case STATUS_TARGET_NOT_FOUND -> "Could not find this user.";
                              case STATUS_TARGET_FRIEND_REQUESTS_DISABLED -> "That user has disabled friend requests.";
                              case STATUS_TARGET_IS_SENDER -> "You can't friend yourself";
                              case STATUS_ALREADY_FRIENDS -> "Already friends with that user.";
                              case STATUS_ALREADY_HAVE_INBOUND_REQUEST -> "You have a pending friend request from that person.";
                              case STATUS_ALREADY_HAVE_OUTBOUND_REQUEST -> "You've already sent a friend request to this person.";
                              case STATUS_UNSPECIFIED, UNRECOGNIZED, STATUS_OK -> "Unknown error.";
                              default -> throw new IncompatibleClassChangeError();
                           };
                           ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, var3);
                        } else {
                           UuidAndUsername var1x = var0xx.getTarget();
                           EntityRenderer6 var2 = new EntityRenderer6(
                              ThreadModuleDump66.method1(var0xx.getTarget().getUuid()),
                              var0xx.getTarget().getUsername(),
                              Instant.now(),
                              var0xx.getTargetLogoColor().getColor(),
                              var0xx.getTargetPlusColor().getColor(),
                              var0xx.getTargetBadgeId() > 0
                                 ? (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var0xx.getTargetBadgeId())
                                 : null,
                              var0xx.getTargetRankName()
                           );
                           ThreadModuleDump63.method4().method51().method3(var2);
                           ThreadModuleDump63.method4().method69().method7(NotificationType.SUCCESS, "Friend request sent to " + var1x.getUsername());
                        }
                     }
                  )
            );
      } else {
         Client.method109().method69().method7(NotificationType.ERROR, Client.method109().method67().method2("gui.components", "incorrectUsername", new Object[0]));
      }
   }

   @CallbackJS("removeFriend")
   public static void method3(String var0) {
      Client.method109().method50().method5(UUID.fromString(var0));
   }

   @CallbackJS("setFriendRequests")
   public static void method3(Boolean var0) {
      ThreadModuleDump63.method5().ifPresent(var1 -> {
         var1.method93().toggleFriendRequests(null, ToggleFriendRequestsRequest.newBuilder().setAllowFriendRequests(var0).build(), var0xx -> {});
         var1.method70(var0);
      });
   }

   @CallbackJS("acceptFriendRequest")
   public static void method4(String var0) {
      UUID var1 = UUID.fromString(var0);
      Client var2 = ThreadModuleDump63.method4();
      ThreadModuleDump63.method5()
         .ifPresent(
            var2x -> var2x.method93()
               .acceptFriendRequest(null, AcceptFriendRequestRequest.newBuilder().setSenderUuid(ThreadModuleDump66.method3(var1)).build(), var1xx -> {
                  if (var1xx.getStatus() != com.lunarclient.websocket.friend.v1.AcceptFriendRequestResponse.Status.STATUS_OK) {
                     String var5 = switch (var1xx.getStatus()) {
                        case STATUS_YOUR_FRIEND_LIST_FULL -> "Your friends list is full.";
                        case STATUS_TARGET_FRIEND_LIST_FULL -> "This user's friends list is full.";
                        case STATUS_ALREADY_FRIENDS -> "Already friends with this player!";
                        case STATUS_FRIEND_REQUEST_NOT_FOUND -> "Cannot find associated friend request.";
                        case STATUS_UNSPECIFIED, UNRECOGNIZED, STATUS_OK -> "Unknown error.";
                        default -> throw new IncompatibleClassChangeError();
                     };
                     var2.method69().method7(NotificationType.ERROR, var5);
                  } else {
                     OfflineFriend var2xx = var1xx.getOfflineFriend();
                     UUID var3 = ThreadModuleDump66.method1(var2xx.getPlayer().getUuid());
                     Memory var4 = new Memory(var3);
                     var4.setName(var2xx.getPlayer().getUsername());
                     var4.method33(null);
                     var4.method4(EntityRendererType.OFFLINE);
                     if (var2xx.hasLastVisibleOnline()) {
                        var4.method35(var2xx.getLastVisibleOnline().getSeconds() * 1000L);
                     } else {
                        var4.method35(-1L);
                     }

                     if (var2xx.hasFriendsSince()) {
                        var4.method36(ThreadModuleDump66.method5(var2xx.getFriendsSince()));
                     }

                     if (var2xx.hasSocials()) {
                        var4.method37(var2xx.getSocials());
                     }

                     var2.method50().method4(var4);
                     var2.method50().method9();
                     var2.method69().method7(NotificationType.SUCCESS, var2xx.getPlayer().getUsername() + " has been added as a friend!");
                  }
               })
         );
      var2.method51().method2(var1);
   }

   @CallbackJS("denyFriendRequest")
   public static void method5(String var0) {
      UUID var1 = UUID.fromString(var0);
      boolean var2 = ThreadModuleDump63.method4().method51().method5(var1);
      ThreadModuleDump63.method5()
         .ifPresent(
            var2x -> {
               if (var2) {
                  var2x.method93()
                     .cancelFriendRequest(null, CancelFriendRequestRequest.newBuilder().setTargetUuid(ThreadModuleDump66.method3(var1)).build(), var0xx -> {});
               } else {
                  var2x.method93()
                     .denyFriendRequest(null, DenyFriendRequestRequest.newBuilder().setSenderUuid(ThreadModuleDump66.method3(var1)).build(), var0xx -> {});
               }
            }
         );
      ThreadModuleDump63.method4().method51().method2(var1);
   }

   @CallbackJS("setVisibilityStatus")
   public static void method6(EntityRendererType var0) {
      if (var0 != null && var0 != EntityRendererType.OFFLINE) {
         ThreadModuleDump63.method4().method31().method4(var0);
         ThreadModuleDump63.method5()
            .ifPresent(
               var0x -> var0x.method93()
                  .broadcastStatusChange(
                     null,
                     BroadcastStatusChangeRequest.newBuilder().setNewStatus(ThreadModuleDump63.method4().method31().method16().getProtobuf()).build(),
                     var0xx -> {}
                  )
            );
      } else {
         throw new RuntimeException("Invalid arguments provided: " + var0 + " is not a valid status");
      }
   }

   @CallbackJS("setLastSeenVisibility")
   public static void method7(LastSeenVisibility var0) {
      ThreadModuleDump63.method5().ifPresent(var1 -> {
         var1.method93().setLastSeenVisibility(null, SetLastSeenVisibilityRequest.newBuilder().setVisibility(var0).build(), var0xx -> {});
         var1.method71(var0);
      });
   }

   @CallbackJS("setIncomingGiftPrivacy")
   public static void method8(IncomingGiftPrivacy var0) {
      ThreadModuleDump63.method5().ifPresent(var1 -> {
         var1.method89().setIncomingGiftPrivacy(null, SetIncomingGiftPrivacyRequest.newBuilder().setIncomingGiftPrivacy(var0).build(), var0xx -> {});
         var1.method72(var0);
      });
   }

   @CallbackJS("setCosmeticOwnershipVisibility")
   public static void method9(CosmeticOwnershipVisibility var0) {
      ThreadModuleDump63.method5().ifPresent(var1 -> {
         var1.method87().setCosmeticOwnershipVisibility(null, SetCosmeticOwnershipVisibilityRequest.newBuilder().setVisibility(var0).build(), var0xx -> {});
         var1.method74(var0);
      });
   }

   @CallbackJS("join")
   public static void method10(UUID var0) {
      if (System.currentTimeMillis() - field1 > 1000L) {
         field1 = System.currentTimeMillis();
         Memory var1 = ThreadModuleDump63.method4().method50().method2(var0);
         if (var1 != null) {
            Highlight3Task.method1(var1, Source.SOURCE_FRIEND_LIST_INLINE);
         }
      }
   }

   @CallbackJS("setPinnedFriend")
   public static void method11(UUID var0, boolean var1) {
      Memory var2 = ThreadModuleDump63.method4().method50().method2(var0);
      if (var2 != null && var2.method31() != var1) {
         var2.method51(var1);
         ThreadModuleDump63.method4().method50().method9();
         if (var1) {
            Client.method109()
               .method35()
               .method93()
               .addFriendPin(null, AddFriendPinRequest.newBuilder().setTargetUuid(ThreadModuleDump66.method3(var0)).build(), var0x -> {});
         } else {
            Client.method109()
               .method35()
               .method93()
               .removeFriendPin(null, RemoveFriendPinRequest.newBuilder().setTargetUuid(ThreadModuleDump66.method3(var0)).build(), var0x -> {});
         }
      }
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method50().method17();
   }
}

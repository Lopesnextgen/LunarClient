package com.moonsworth.lunar.client.driver.bridge;

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
import com.moonsworth.lunar.client.account.Badge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.mixin.EntityRenderer6;
import com.moonsworth.lunar.client.network.friend.FriendStatus;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldJoinHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.time.Instant;
import java.util.UUID;

public class FriendApi implements DriverGuiExtension, GuiIterator.Extension {
   private static long field1 = 0L;

   public FriendApi() {
   }

   @CallbackJS("addFriend")
   public static void method2(String text0) {
      if (text0.matches("([a-zA-Z0-9_]+)") && text0.length() <= 16) {
         Ref.method5()
            .ifPresent(
               arg1 -> arg1.method93()
                  .sendFriendRequest(
                     null,
                     SendFriendRequestRequest.newBuilder().setTargetUsername(text0).build(),
                     arg0xx -> {
                        if (arg0xx.getStatus() != Status.STATUS_OK) {
                           String text3 = switch (arg0xx.getStatus()) {
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
                           Ref.method4().method69().method7(NotificationType.ERROR, text3);
                        } else {
                           UuidAndUsername uuidandusername1x = arg0xx.getTarget();
                           EntityRenderer6 entityrenderer62 = new EntityRenderer6(
                              ProtoConverter.method1(arg0xx.getTarget().getUuid()),
                              arg0xx.getTarget().getUsername(),
                              Instant.now(),
                              arg0xx.getTargetLogoColor().getColor(),
                              arg0xx.getTargetPlusColor().getColor(),
                              arg0xx.getTargetBadgeId() > 0
                                 ? (Badge)Ref.method4().method95().IORHHHROCRRHORHRCHCCHHIHICCRCO().get(arg0xx.getTargetBadgeId())
                                 : null,
                              arg0xx.getTargetRankName()
                           );
                           Ref.method4().method51().method3(entityrenderer62);
                           Ref.method4().method69().method7(NotificationType.SUCCESS, "Friend request sent to " + uuidandusername1x.getUsername());
                        }
                     }
                  )
            );
      } else {
         Client.method109().method69().method7(NotificationType.ERROR, Client.method109().method67().method2("gui.components", "incorrectUsername", new Object[0]));
      }
   }

   @CallbackJS("removeFriend")
   public static void method3(String text0) {
      Client.method109().method50().method5(UUID.fromString(text0));
   }

   @CallbackJS("setFriendRequests")
   public static void method3(Boolean flag0) {
      Ref.method5().ifPresent(arg1 -> {
         arg1.method93().toggleFriendRequests(null, ToggleFriendRequestsRequest.newBuilder().setAllowFriendRequests(flag0).build(), arg0xx -> {});
         arg1.method70(flag0);
      });
   }

   @CallbackJS("acceptFriendRequest")
   public static void method4(String text0) {
      UUID uuid1 = UUID.fromString(text0);
      Client client2 = Ref.method4();
      Ref.method5()
         .ifPresent(
            arg2x -> arg2x.method93()
               .acceptFriendRequest(null, AcceptFriendRequestRequest.newBuilder().setSenderUuid(ProtoConverter.method3(uuid1)).build(), arg1xx -> {
                  if (arg1xx.getStatus() != com.lunarclient.websocket.friend.v1.AcceptFriendRequestResponse.Status.STATUS_OK) {
                     String text5 = switch (arg1xx.getStatus()) {
                        case STATUS_YOUR_FRIEND_LIST_FULL -> "Your friends list is full.";
                        case STATUS_TARGET_FRIEND_LIST_FULL -> "This user's friends list is full.";
                        case STATUS_ALREADY_FRIENDS -> "Already friends with this player!";
                        case STATUS_FRIEND_REQUEST_NOT_FOUND -> "Cannot find associated friend request.";
                        case STATUS_UNSPECIFIED, UNRECOGNIZED, STATUS_OK -> "Unknown error.";
                        default -> throw new IncompatibleClassChangeError();
                     };
                     client2.method69().method7(NotificationType.ERROR, text5);
                  } else {
                     OfflineFriend offlinefriend2xx = arg1xx.getOfflineFriend();
                     UUID uuid3 = ProtoConverter.method1(offlinefriend2xx.getPlayer().getUuid());
                     Memory memory4 = new Memory(uuid3);
                     memory4.setName(offlinefriend2xx.getPlayer().getUsername());
                     memory4.method33(null);
                     memory4.method4(FriendStatus.OFFLINE);
                     if (offlinefriend2xx.hasLastVisibleOnline()) {
                        memory4.method35(offlinefriend2xx.getLastVisibleOnline().getSeconds() * 1000L);
                     } else {
                        memory4.method35(-1L);
                     }

                     if (offlinefriend2xx.hasFriendsSince()) {
                        memory4.method36(ProtoConverter.method5(offlinefriend2xx.getFriendsSince()));
                     }

                     if (offlinefriend2xx.hasSocials()) {
                        memory4.method37(offlinefriend2xx.getSocials());
                     }

                     client2.method50().method4(memory4);
                     client2.method50().method9();
                     client2.method69().method7(NotificationType.SUCCESS, offlinefriend2xx.getPlayer().getUsername() + " has been added as a friend!");
                  }
               })
         );
      client2.method51().method2(uuid1);
   }

   @CallbackJS("denyFriendRequest")
   public static void method5(String text0) {
      UUID uuid1 = UUID.fromString(text0);
      boolean flag2 = Ref.method4().method51().method5(uuid1);
      Ref.method5()
         .ifPresent(
            arg2x -> {
               if (flag2) {
                  arg2x.method93()
                     .cancelFriendRequest(null, CancelFriendRequestRequest.newBuilder().setTargetUuid(ProtoConverter.method3(uuid1)).build(), arg0xx -> {});
               } else {
                  arg2x.method93()
                     .denyFriendRequest(null, DenyFriendRequestRequest.newBuilder().setSenderUuid(ProtoConverter.method3(uuid1)).build(), arg0xx -> {});
               }
            }
         );
      Ref.method4().method51().method2(uuid1);
   }

   @CallbackJS("setVisibilityStatus")
   public static void method6(FriendStatus entityrenderertype0) {
      if (entityrenderertype0 != null && entityrenderertype0 != FriendStatus.OFFLINE) {
         Ref.method4().method31().method4(entityrenderertype0);
         Ref.method5()
            .ifPresent(
               arg0x -> arg0x.method93()
                  .broadcastStatusChange(
                     null,
                     BroadcastStatusChangeRequest.newBuilder().setNewStatus(Ref.method4().method31().method16().getProtobuf()).build(),
                     arg0xx -> {}
                  )
            );
      } else {
         throw new RuntimeException("Invalid arguments provided: " + entityrenderertype0 + " is not a valid status");
      }
   }

   @CallbackJS("setLastSeenVisibility")
   public static void method7(LastSeenVisibility lastseenvisibility0) {
      Ref.method5().ifPresent(arg1 -> {
         arg1.method93().setLastSeenVisibility(null, SetLastSeenVisibilityRequest.newBuilder().setVisibility(lastseenvisibility0).build(), arg0xx -> {});
         arg1.method71(lastseenvisibility0);
      });
   }

   @CallbackJS("setIncomingGiftPrivacy")
   public static void method8(IncomingGiftPrivacy incominggiftprivacy0) {
      Ref.method5().ifPresent(arg1 -> {
         arg1.method89().setIncomingGiftPrivacy(null, SetIncomingGiftPrivacyRequest.newBuilder().setIncomingGiftPrivacy(incominggiftprivacy0).build(), arg0xx -> {});
         arg1.method72(incominggiftprivacy0);
      });
   }

   @CallbackJS("setCosmeticOwnershipVisibility")
   public static void method9(CosmeticOwnershipVisibility cosmeticownershipvisibility0) {
      Ref.method5().ifPresent(arg1 -> {
         arg1.method87().setCosmeticOwnershipVisibility(null, SetCosmeticOwnershipVisibilityRequest.newBuilder().setVisibility(cosmeticownershipvisibility0).build(), arg0xx -> {});
         arg1.method74(cosmeticownershipvisibility0);
      });
   }

   @CallbackJS("join")
   public static void method10(UUID uuid0) {
      if (System.currentTimeMillis() - field1 > 1000L) {
         field1 = System.currentTimeMillis();
         Memory memory1 = Ref.method4().method50().method2(uuid0);
         if (memory1 != null) {
            HostedWorldJoinHandler.method1(memory1, Source.SOURCE_FRIEND_LIST_INLINE);
         }
      }
   }

   @CallbackJS("setPinnedFriend")
   public static void method11(UUID uuid0, boolean flag1) {
      Memory memory2 = Ref.method4().method50().method2(uuid0);
      if (memory2 != null && memory2.method31() != flag1) {
         memory2.method51(flag1);
         Ref.method4().method50().method9();
         if (flag1) {
            Client.method109()
               .method35()
               .method93()
               .addFriendPin(null, AddFriendPinRequest.newBuilder().setTargetUuid(ProtoConverter.method3(uuid0)).build(), arg0x -> {});
         } else {
            Client.method109()
               .method35()
               .method93()
               .removeFriendPin(null, RemoveFriendPinRequest.newBuilder().setTargetUuid(ProtoConverter.method3(uuid0)).build(), arg0x -> {});
         }
      }
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method50().method17();
   }
}

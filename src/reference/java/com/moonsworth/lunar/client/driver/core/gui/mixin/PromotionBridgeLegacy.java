package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonObject;
import com.google.protobuf.util.JsonFormat;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.gameipc.promotion.v1.PlayPromotionRequest;
import com.lunarclient.gameipc.promotion.v1.PromotionType;
import com.lunarclient.websocket.promotion.v1.ClaimPendingRewardRequest;
import com.lunarclient.websocket.promotion.v1.PendingRewardType;
import com.lunarclient.websocket.promotion.v1.PromotionReward;
import com.lunarclient.websocket.promotion.v1.ClaimPendingRewardResponse.Result;
import com.lunarclient.websocket.promotion.v1.PromotionReward.Builder;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.NotNull;

public class PromotionBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static long field1 = 0L;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method97().method15();
   }

   @CallbackJS("playPromotion")
   public static void method2(PromotionType var0) {
      ThreadModuleDump63.method6()
         .ifPresent(
            var1 -> {
               if (ThreadModuleDump63.method4().method97().method13().contains(var0)) {
                  var1.method17()
                     .playPromotion(
                        null,
                        PlayPromotionRequest.newBuilder().setType(var0).build(),
                        var0xx -> ThreadModuleDump63.method4().method69().method3("Open the launcher to view the promotion")
                     );
               }
            }
         );
   }

   @CallbackJS("refresh")
   public static void refresh() {
      if (ThreadModuleDump63.method3().bridge$isWindowFocused()) {
         if (System.currentTimeMillis() - field1 < 5000L) {
            ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Wait a second before refreshing again..");
         } else {
            field1 = System.currentTimeMillis();
            ThreadModuleDump63.method4().method35().method14();
            ThreadModuleDump63.method4().method69().method6(NotificationType.INFO, "Refreshing...", "Checking for quest completion");
         }
      }
   }

   @CallbackJS("openMedalPromotion")
   public static void method3() {
      DriverViewportLegacy.method50().method56().method11(ThreadModuleDump48.field32, Initiator.INITIATOR_HOME_CTA);
      ThreadModuleDump63.method4().method69().method6(NotificationType.INFO, "Opened Medal.tv", "Complete the Medal quest in your browser!");
   }

   @CallbackJS("claimReward")
   public static void method4(PendingRewardType var0, String var1) {
      try {
         Builder var2 = PromotionReward.newBuilder();
         JsonFormat.parser().merge(var1, var2);
         PromotionReward var3 = var2.build();
         ThreadModuleDump63.method4()
            .method35()
            .method107()
            .claimPendingReward(null, ClaimPendingRewardRequest.newBuilder().setReward(var3).setType(var0).build(), var2x -> {
               Result var3x = var2x.getResult();
               if (var3x == Result.RESULT_SUCCESS) {
                  ThreadModuleDump63.method4().method69().method6(NotificationType.INFO, "Success!", "Successfully claimed reward");
                  ThreadModuleDump63.method4().method97().method4(var0);
                  ThreadModuleDump63.method4().method35().method14();
                  ThreadModuleDump63.method4().method53().method24();
                  if (var0 == PendingRewardType.PENDING_REWARD_TYPE_MEDAL) {
                     CosmeticsBridge.method18(var3.getCosmetic().getCosmeticId());
                  }

                  DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "promotion:claimSuccess", method5(var3));
               } else if (var3x == Result.RESULT_PLAYER_ALREADY_OWNED) {
                  ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "You already own this reward");
               } else if (var3x == Result.RESULT_PLAYER_ALREADY_CLAIMED) {
                  ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "You have already claimed this reward");
               } else {
                  ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "Unknown error");
               }
            });
      } catch (Exception var4) {
         ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "Unknown error");
      }
   }

   @NotNull
   private static JsonObject method5(PromotionReward var0) {
      JsonObject var1 = new JsonObject();
      if (var0.hasCosmetic()) {
         var1.addProperty("cosmeticId", var0.getCosmetic().getCosmeticId());
      }

      if (var0.hasBadge()) {
         var1.addProperty("badgeId", var0.getBadge().getBadgeId());
      }

      if (var0.hasEmote()) {
         var1.addProperty("emoteId", var0.getEmote().getEmoteId());
      }

      if (var0.hasSpray()) {
         var1.addProperty("sprayId", var0.getSpray().getSprayId());
      }

      return var1;
   }
}

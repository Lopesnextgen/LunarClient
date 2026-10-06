package com.moonsworth.lunar.client.driver.bridge;

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
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.driver.core.gui.mixin.CosmeticsBridge;

public class PromotionBridge implements DriverGuiExtension, GuiIterator.Extension {
   private static long field1 = 0L;

   public PromotionBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method97().method15();
   }

   @CallbackJS("playPromotion")
   public static void method2(PromotionType promotiontype0) {
      Ref.method6()
         .ifPresent(
            arg1 -> {
               if (Ref.method4().method97().IIORHHIRHIORHRCCCOICCRCHRRCCRH().contains(promotiontype0)) {
                  arg1.method17()
                     .playPromotion(
                        null,
                        PlayPromotionRequest.newBuilder().setType(promotiontype0).build(),
                        arg0xx -> Ref.method4().method69().method3("Open the launcher to view the promotion")
                     );
               }
            }
         );
   }

   @CallbackJS("refresh")
   public static void refresh() {
      if (Ref.method3().bridge$isWindowFocused()) {
         if (System.currentTimeMillis() - field1 < 5000L) {
            Ref.method4().method69().method7(NotificationType.ERROR, "Wait a second before refreshing again..");
         } else {
            field1 = System.currentTimeMillis();
            Ref.method4().method35().method14();
            Ref.method4().method69().method6(NotificationType.INFO, "Refreshing...", "Checking for quest completion");
         }
      }
   }

   @CallbackJS("openMedalPromotion")
   public static void method3() {
      DriverViewportLegacy.method50().method56().method11(LunarConstants.field32, Initiator.INITIATOR_HOME_CTA);
      Ref.method4().method69().method6(NotificationType.INFO, "Opened Medal.tv", "Complete the Medal quest in your browser!");
   }

   @CallbackJS("claimReward")
   public static void method4(PendingRewardType pendingrewardtype0, String text1) {
      try {
         Builder builder2 = PromotionReward.newBuilder();
         JsonFormat.parser().merge(text1, builder2);
         PromotionReward promotionreward3 = builder2.build();
         Ref.method4()
            .method35()
            .method107()
            .claimPendingReward(null, ClaimPendingRewardRequest.newBuilder().setReward(promotionreward3).setType(pendingrewardtype0).build(), arg2x -> {
               Result result3x = arg2x.getResult();
               if (result3x == Result.RESULT_SUCCESS) {
                  Ref.method4().method69().method6(NotificationType.INFO, "Success!", "Successfully claimed reward");
                  Ref.method4().method97().method4(pendingrewardtype0);
                  Ref.method4().method35().method14();
                  Ref.method4().method53().method24();
                  if (pendingrewardtype0 == PendingRewardType.PENDING_REWARD_TYPE_MEDAL) {
                     CosmeticsBridge.method18(promotionreward3.getCosmetic().getCosmeticId());
                  }

                  DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "promotion:claimSuccess", method5(promotionreward3));
               } else if (result3x == Result.RESULT_PLAYER_ALREADY_OWNED) {
                  Ref.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "You already own this reward");
               } else if (result3x == Result.RESULT_PLAYER_ALREADY_CLAIMED) {
                  Ref.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "You have already claimed this reward");
               } else {
                  Ref.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "Unknown error");
               }
            });
      } catch (Exception exception4) {
         Ref.method4().method69().method6(NotificationType.ERROR, "Failed to claim reward", "Unknown error");
      }
   }

   @NotNull
   private static JsonObject method5(PromotionReward promotionreward0) {
      JsonObject json1 = new JsonObject();
      if (promotionreward0.hasCosmetic()) {
         json1.addProperty("cosmeticId", promotionreward0.getCosmetic().getCosmeticId());
      }

      if (promotionreward0.hasBadge()) {
         json1.addProperty("badgeId", promotionreward0.getBadge().getBadgeId());
      }

      if (promotionreward0.hasEmote()) {
         json1.addProperty("emoteId", promotionreward0.getEmote().getEmoteId());
      }

      if (promotionreward0.hasSpray()) {
         json1.addProperty("sprayId", promotionreward0.getSpray().getSprayId());
      }

      return json1;
   }
}

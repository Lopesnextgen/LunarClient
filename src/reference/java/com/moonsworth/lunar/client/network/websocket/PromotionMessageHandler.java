package com.moonsworth.lunar.client.network.websocket;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Printer;
import com.lunarclient.gameipc.promotion.v1.PromotionType;
import com.lunarclient.websocket.promotion.v1.PendingReward;
import com.lunarclient.websocket.promotion.v1.PendingRewardType;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public class PromotionMessageHandler extends ItemSetHandler<PromotionType> implements GuiIterator.Extension {
   private final GuiIterator field2 = new GuiIterator();
   private List<PendingReward> field3 = ImmutableList.of();
   private List<PromotionType> field4 = ImmutableList.of();

   @Override
   protected Set<PromotionType> method3() {
      return new HashSet<>();
   }

   public void method2(List<PendingReward> var1) {
      if (FeatureFlag.PENDING_REWARDS.isEnabled()) {
         this.field3 = var1;
         JsonArray var2 = new JsonArray();
         Printer var3 = JsonFormat.printer();

         for (PendingReward var5 : var1) {
            try {
               String var6 = var3.print(var5);
               var2.add(JsonParser.parseString(var6));
            } catch (InvalidProtocolBufferException var7) {
               throw new RuntimeException(var7);
            }
         }

         this.field2.method3("pendingRewards", var2);
      }
   }

   public void method3(List<PromotionType> var1) {
      this.field4.forEach(this.method13()::remove);
      this.field4 = var1;
      this.field4.forEach(this.method13()::add);
      this.method7();
   }

   public void method4(PendingRewardType var1) {
      ArrayList var2 = new ArrayList<>(List.copyOf(this.field3));
      var2.removeIf(var1x -> var1x.getType() == var1);
      this.method2(var2);
   }

   public void method5(PromotionType var1) {
      if (FeatureFlag.GAME_PROMOTION_CTA.isEnabled()) {
         this.method13().add(var1);
         this.method7();
      }
   }

   public void method6(PromotionType var1) {
      this.method13().remove(var1);
      this.method7();
   }

   private void method7() {
      JsonArray var1 = new JsonArray();

      for (PromotionType var3 : this.method13()) {
         var1.add(var3.name());
      }

      this.field2.method3("promotions", var1);
   }

   @Generated
   @Override
   public GuiIterator getProvider() {
      return this.field2;
   }

   @Generated
   public List<PendingReward> method9() {
      return this.field3;
   }
}

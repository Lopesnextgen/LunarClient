package com.moonsworth.lunar.client.framework.listener;

import com.lunarclient.websocket.cosmetic.v2.PlayerCosmeticsPushV2;
import com.lunarclient.websocket.radio.v1.PlayerRadioPush;
import com.lunarclient.websocket.subscription.v1.SubscribeV2Request;
import com.lunarclient.websocket.subscription.v1.UnsubscribeRequest;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.player.EventPlayerJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class PlayerCosmeticsSubscription implements EventBusAccess {
   private static final long field1 = 150L;
   private static final long field2 = 750L;
   private final Set<UUID> field3 = new HashSet<>();
   private final Map<UUID, Long> field4 = new ConcurrentHashMap<>();
   private final Queue<UUID> field5 = new ConcurrentLinkedQueue<>();
   private boolean field6;
   private long field7 = Ref.method14();
   private long field8 = Ref.method14();

   public PlayerCosmeticsSubscription() {
      this.handle(EventPlayerJoinWorld.class, this::method3);
      this.handle(EventPlayerRemove.class, this::method4);
      this.handle(EventServerChange.class, this::method2);
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick highlightimpl21) {
      if (this.field6) {
         long number2 = Ref.method14() - this.field7;
         if (number2 > 150L && this.field4.size() > 0) {
            List list4 = this.field4
               .entrySet()
               .stream()
               .filter(arg0 -> arg0.getValue() <= System.currentTimeMillis())
               .map(Entry::getKey)
               .collect(Collectors.toList());
            if (!list4.isEmpty()) {
               Ref.method5().ifPresent(arg2x -> {
                  arg2x.method96().subscribeV2(null, SubscribeV2Request.newBuilder().addAllTargetUuids(ProtoConverter.method4(list4)).build(), arg1xx -> {
                     for (PlayerCosmeticsPushV2 playercosmeticspushv23 : arg1xx.getCosmeticPushesList()) {
                        arg2x.method51(playercosmeticspushv23);
                     }

                     for (PlayerRadioPush playerradiopush5x : arg1xx.getRadioPushesList()) {
                        arg2x.method52(playerradiopush5x);
                     }
                  });
                  this.field3.addAll(list4);

                  for (UUID uuid4x : list4) {
                     this.field4.remove(uuid4x);
                  }
               });
            }

            this.field7 = Ref.method14();
         }

         int number6 = this.field5.size();
         number2 = Ref.method14() - this.field8;
         if (number2 > 750L && number6 > 0) {
            Ref.method5()
               .ifPresent(
                  arg1x -> {
                     arg1x.method96()
                        .unsubscribe(null, UnsubscribeRequest.newBuilder().addAllTargetUuids(ProtoConverter.method4(this.field5)).build(), arg0 -> {});
                     CosmeticManager holograms122x = Ref.method4().method53();
                     this.field5.forEach(holograms122x::removePlayer);
                     this.field3.removeAll(this.field5);
                     this.field5.clear();
                     this.field8 = Ref.method14();
                  }
               );
         }
      }
   }

   private void method2(EventServerChange highlightimpl101) {
      if (this.field6) {
         CosmeticManager holograms122 = Ref.method4().method53();
         this.field5.forEach(holograms122::removePlayer);
         this.field4.forEach((arg1x, arg2x) -> holograms122.removePlayer(arg1x));
         this.field5.clear();
         this.field4.clear();
         this.field5.addAll(this.field3);
         this.field3.clear();
      }
   }

   private void method3(EventPlayerJoinWorld highlightimpl4_21) {
      if (this.field6) {
         this.method5(highlightimpl4_21.method1());
      }
   }

   private void method4(EventPlayerRemove highlightimpl151) {
      if (this.field6) {
         this.method6(highlightimpl151.method1());
      }
   }

   private void method5(Bridge6_10 bridge6_101) {
      if (this.method7(bridge6_101)) {
         this.field4.put(bridge6_101.bridge$getUniqueID(), System.currentTimeMillis() + 150L);
         this.field5.remove(bridge6_101.bridge$getUniqueID());
      }
   }

   private void method6(Bridge6_10 bridge6_101) {
      this.field4.remove(bridge6_101.bridge$getUniqueID());
      if (this.field3.contains(bridge6_101.bridge$getUniqueID())) {
         if (this.method7(bridge6_101)) {
            this.field5.add(bridge6_101.bridge$getUniqueID());
         }
      }
   }

   private boolean method7(Bridge6_10 bridge6_101) {
      return bridge6_101.bridge$getUniqueID().equals(Ref.method3().bridge$getSession().bridge$getProfile().getId())
         ? false
         : !NpcUtils.method2(bridge6_101, false);
   }

   public void method8() {
      this.field6 = true;
      if (Ref.method8() != null) {
         for (Bridge6_10 bridge6_102 : Ref.method8().bridge$getPlayerEntities()) {
            this.method5(bridge6_102);
         }
      }
   }

   public void method9() {
      this.field6 = false;
      CosmeticManager holograms121 = Ref.method4().method53();
      this.field5.forEach(holograms121::removePlayer);
      this.field4.forEach((arg1x, arg2) -> holograms121.removePlayer(arg1x));
      this.field3.forEach(holograms121::removePlayer);
      this.field5.clear();
      this.field4.clear();
      this.field3.clear();
   }
}

package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.lunarclient.websocket.cosmetic.v2.PlayerCosmeticsPushV2;
import com.lunarclient.websocket.radio.v1.PlayerRadioPush;
import com.lunarclient.websocket.subscription.v1.SubscribeV2Request;
import com.lunarclient.websocket.subscription.v1.UnsubscribeRequest;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.player.PlayerJoinWorldEventLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
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

public class GuiRewindhandlers5 implements EventRegistrar {
   private static final long field1 = 150L;
   private static final long field2 = 750L;
   private final Set<UUID> field3 = new HashSet<>();
   private final Map<UUID, Long> field4 = new ConcurrentHashMap<>();
   private final Queue<UUID> field5 = new ConcurrentLinkedQueue<>();
   private boolean field6;
   private long field7 = ThreadModuleDump63.method14();
   private long field8 = ThreadModuleDump63.method14();

   public GuiRewindhandlers5() {
      this.handle(PlayerJoinWorldEventLegacy.class, this::method3);
      this.handle(EventPlayerRemoval.class, this::method4);
      this.handle(ServerChangeEvent.class, this::method2);
      this.handle(EventClientTick.class, this::method1);
   }

   private void method1(EventClientTick var1) {
      if (this.field6) {
         long var2 = ThreadModuleDump63.method14() - this.field7;
         if (var2 > 150L && this.field4.size() > 0) {
            List var4 = this.field4
               .entrySet()
               .stream()
               .filter(var0 -> var0.getValue() <= System.currentTimeMillis())
               .map(Entry::getKey)
               .collect(Collectors.toList());
            if (!var4.isEmpty()) {
               ThreadModuleDump63.method5().ifPresent(var2x -> {
                  var2x.method96().subscribeV2(null, SubscribeV2Request.newBuilder().addAllTargetUuids(ThreadModuleDump66.method4(var4)).build(), var1xx -> {
                     for (PlayerCosmeticsPushV2 var3 : var1xx.getCosmeticPushesList()) {
                        var2x.method51(var3);
                     }

                     for (PlayerRadioPush var5x : var1xx.getRadioPushesList()) {
                        var2x.method52(var5x);
                     }
                  });
                  this.field3.addAll(var4);

                  for (UUID var4x : var4) {
                     this.field4.remove(var4x);
                  }
               });
            }

            this.field7 = ThreadModuleDump63.method14();
         }

         int var6 = this.field5.size();
         var2 = ThreadModuleDump63.method14() - this.field8;
         if (var2 > 750L && var6 > 0) {
            ThreadModuleDump63.method5()
               .ifPresent(
                  var1x -> {
                     var1x.method96()
                        .unsubscribe(null, UnsubscribeRequest.newBuilder().addAllTargetUuids(ThreadModuleDump66.method4(this.field5)).build(), var0 -> {});
                     CosmeticManager var2x = ThreadModuleDump63.method4().method53();
                     this.field5.forEach(var2x::removePlayer);
                     this.field3.removeAll(this.field5);
                     this.field5.clear();
                     this.field8 = ThreadModuleDump63.method14();
                  }
               );
         }
      }
   }

   private void method2(ServerChangeEvent var1) {
      if (this.field6) {
         CosmeticManager var2 = ThreadModuleDump63.method4().method53();
         this.field5.forEach(var2::removePlayer);
         this.field4.forEach((var1x, var2x) -> var2.removePlayer(var1x));
         this.field5.clear();
         this.field4.clear();
         this.field5.addAll(this.field3);
         this.field3.clear();
      }
   }

   private void method3(PlayerJoinWorldEventLegacy var1) {
      if (this.field6) {
         this.method5(var1.method1());
      }
   }

   private void method4(EventPlayerRemoval var1) {
      if (this.field6) {
         this.method6(var1.method1());
      }
   }

   private void method5(Bridge6_10 var1) {
      if (this.method7(var1)) {
         this.field4.put(var1.bridge$getUniqueID(), System.currentTimeMillis() + 150L);
         this.field5.remove(var1.bridge$getUniqueID());
      }
   }

   private void method6(Bridge6_10 var1) {
      this.field4.remove(var1.bridge$getUniqueID());
      if (this.field3.contains(var1.bridge$getUniqueID())) {
         if (this.method7(var1)) {
            this.field5.add(var1.bridge$getUniqueID());
         }
      }
   }

   private boolean method7(Bridge6_10 var1) {
      return var1.bridge$getUniqueID().equals(ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId())
         ? false
         : !ThreadModuleDump22.method2(var1, false);
   }

   public void method8() {
      this.field6 = true;
      if (ThreadModuleDump63.method8() != null) {
         for (Bridge6_10 var2 : ThreadModuleDump63.method8().bridge$getPlayerEntities()) {
            this.method5(var2);
         }
      }
   }

   public void method9() {
      this.field6 = false;
      CosmeticManager var1 = ThreadModuleDump63.method4().method53();
      this.field5.forEach(var1::removePlayer);
      this.field4.forEach((var1x, var2) -> var1.removePlayer(var1x));
      this.field3.forEach(var1::removePlayer);
      this.field5.clear();
      this.field4.clear();
      this.field3.clear();
   }
}

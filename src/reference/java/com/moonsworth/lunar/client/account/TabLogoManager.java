package com.moonsworth.lunar.client.account;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.lunarclient.websocket.cosmetic.v2.LoadTabLogosRequest;
import com.lunarclient.websocket.cosmetic.v2.LoadTabLogosResponse;
import com.lunarclient.websocket.cosmetic.v2.LoadTabLogosResponse.TabLogo;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.mod.misc.debug.ApolloDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;

public class TabLogoManager extends ItemSetHandler<UUID> implements EventRegistrar {
   private static final TabLogoManager.Data field2 = new TabLogoManager.Data(-1, -1, null);
   private final ExecutorService field3 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-tab-logo-thread", true));
   private Cache<UUID, TabLogoManager.Data> field4 = CacheBuilder.newBuilder().expireAfterAccess(10L, TimeUnit.MINUTES).maximumSize(300L).build();
   private long field5;

   public TabLogoManager() {
      this.handle(EventPlayerRemoval.class, this::method2);
   }

   @Override
   protected Set<UUID> method3() {
      return Sets.newConcurrentHashSet();
   }

   private void method2(EventPlayerRemoval var1) {
      UUID var2 = var1.method1().bridge$getUniqueID();
      CosmeticManager.Data var3 = ThreadModuleDump63.method4().method53().method63().get(var2);
      if (var3 != null) {
         this.field4.put(var2, this.method7(var3));
      }
   }

   public boolean method3(UUID var1) {
      if (var1 == null) {
         return false;
      }

      CosmeticManager.Data var2 = ThreadModuleDump63.method4().method53().method63().get(var1);
      if (var2 != null) {
         return true;
      }

      TabLogoManager.Data var3 = (TabLogoManager.Data)this.field4.getIfPresent(var1);
      return var3 != null && !var3.equals(field2);
   }

   public int method4(UUID var1) {
      TabLogoManager.Data var2 = this.method6(var1);
      if (var2 == null) {
         return 0;
      }

      GeneralSettings var3 = Client.method109().method41().method6();
      byte var4 = 0;
      if (var3.method41().get()) {
         var4 += 9;
      }

      if (var3.method42().get() && var2.field3 != null) {
         var4 += 9;
      }

      return var4;
   }

   public void method5(Set<UUID> var1) {
      if (!var1.isEmpty()) {
         Map var2 = ThreadModuleDump63.method4().method53().method63();

         for (UUID var4 : var1) {
            if (!var2.containsKey(var4)) {
               TabLogoManager.Data var5 = (TabLogoManager.Data)this.field4.getIfPresent(var4);
               if (var5 == null) {
                  this.method13().add(var4);
                  this.field4.put(var4, field2);
               }
            }
         }

         if (System.currentTimeMillis() - this.field5 > 3000L) {
            this.field5 = System.currentTimeMillis();
            this.field3.execute(() -> {
               try {
                  this.method8();
               } catch (Exception var2x) {
                  var2x.printStackTrace();
               }
            });
         }
      }
   }

   public TabLogoManager.Data method6(UUID var1) {
      if (var1 == null) {
         return null;
      }

      CosmeticManager.Data var2 = ThreadModuleDump63.method4().method53().method63().get(var1);
      if (var2 != null) {
         return this.method7(var2);
      }

      TabLogoManager.Data var3 = (TabLogoManager.Data)this.field4.getIfPresent(var1);
      return var3 != null && !var3.equals(field2) ? var3 : null;
   }

   private TabLogoManager.Data method7(CosmeticManager.Data var1) {
      return new TabLogoManager.Data(new Color(var1.method5(), var1.method6(), var1.method7(), 1.0F).getRGB(), var1.method9(), var1.method11());
   }

   private void method8() {
      if (!this.method13().isEmpty()) {
         ThreadModuleDump63.method5().ifPresent(var1 -> {
            HashSet var2 = Sets.newHashSet();
            this.method13().removeIf(var1x -> var2.add(ThreadModuleDump66.method3(var1x)));
            var1.method87().loadTabLogos(null, LoadTabLogosRequest.newBuilder().addAllPlayerUuids(var2).build(), this::method9);
         });
      }
   }

   private void method9(LoadTabLogosResponse var1) {
      ApolloDebugMod var2 = ThreadModuleDump63.method4().method40().method80();
      if (var2 != null && var2.isEnabled() && var2.method13()) {
         var2.method15(var1);
      }

      for (TabLogo var4 : var1.getTabLogosList()) {
         UUID var5 = ThreadModuleDump66.method1(var4.getPlayerUuid());
         int var6 = this.method10(var4.getLogoColor().getColor());
         int var7 = var4.getPlusColor().getColor();
         Gui2Handler2 var8 = (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(var4.getBadgeId());
         this.field4.put(var5, new TabLogoManager.Data(var6, var7 == 0 ? 0 : this.method10(var7), var8));
      }
   }

   private int method10(int var1) {
      return new Color(ThreadModuleDump23.method5(var1), ThreadModuleDump23.greenFloat(var1), ThreadModuleDump23.method7(var1), 1.0F).getRGB();
   }

   public class Data {
      private final int field1;
      private final int field2;
      private final Gui2Handler2 field3;

      public Data(int var1, int var2, Gui2Handler2 var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public int method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public Gui2Handler2 method3() {
         return this.field3;
      }
   }
}

package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.mixin.Fishing;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiRewindhandlersHandler213 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25 field7 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25.class
   );
   private static final Pattern field8 = Pattern.compile("^(?<type>From:|To:) (?:\\[.*?] )?(?<name>\\w+)$");
   private static final Fishing field9 = new Fishing(null, null, null, null, null, false, false);
   private static final Set<String> field10 = Sets.newHashSet(
      new String[]{
         "WHITE_GIFT",
         "GREEN_GIFT",
         "RED_GIFT",
         "SLICE_OF_BLUEBERRY_CAKE",
         "SLICE_OF_RED_VELVET_CAKE",
         "SLICE_OF_GREEN_VELVET_CAKE",
         "SLICE_OF_CHEESECAKE",
         "SLICE_OF_STRAWBERRY_SHORTCAKE"
      }
   );
   private final Cache<BridgeExtension, Fishing> field11 = CacheBuilder.newBuilder().weakKeys().expireAfterWrite(30L, TimeUnit.SECONDS).build();
   private final Cache<BridgeExtension, Fishing> field12 = CacheBuilder.newBuilder().weakKeys().expireAfterWrite(30L, TimeUnit.SECONDS).build();

   public GuiRewindhandlersHandler213() {
      this.handle(EventEverySecond.class, this::method1);
   }

   private void method1(EventEverySecond var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         for (BridgeExtension var4 : var2.bridge$getEntities()) {
            if (this.field12.getIfPresent(var4) == null && var4 instanceof ArmorStandBridge var5 && var5.bridge$isInvisible()) {
               ItemStackBridge var6 = var5.bridge$getHelmet();
               if (var6 != null && !var6.bridge$isEmpty()) {
                  String var7 = Gui3.method2(var6);
                  if (field10.contains(var7)) {
                     double var8 = var5.bridge$getPosX();
                     double var10 = var5.bridge$getPosY();
                     double var12 = var5.bridge$getPosZ();
                     List var14 = var2.bridge$getEntities(
                        AxisAlignedBBBridge.method2(var8 - 0.1, var10 - 2.0, var12 - 0.1, var8 + 0.1, var10 + 2.0, var12 + 0.1), var0 -> {
                           if (!(var0 instanceof ArmorStandBridge)) {
                              return false;
                           } else {
                              return !var0.bridge$isInvisible() ? false : var0.bridge$getCustomName() != null;
                           }
                        }
                     );
                     BridgeExtension var15 = null;
                     String var16 = null;
                     BridgeExtension var17 = null;
                     String var18 = null;
                     String var19 = this.field7.method5();

                     for (BridgeExtension var21 : var14) {
                        String var22 = AdventureTextBridge.getTextContent(var21.bridge$getCustomName());
                        Matcher var23 = field8.matcher(var22);
                        if (var23.matches()) {
                           if (var23.group("type").equals("From:")) {
                              var15 = var21;
                              var16 = var23.group("name");
                           } else {
                              var17 = var21;
                              var18 = var23.group("name");
                           }
                        } else if (var22.equals("CLICK TO OPEN")) {
                           var17 = var21;
                           var18 = var19;
                        }
                     }

                     if (var15 != null && var17 != null && !(var10 > var15.bridge$getPosY()) && !(var15.bridge$getPosY() > var17.bridge$getPosY())) {
                        Fishing var24 = new Fishing(var4, var15, var16, var17, var18, var16.equals(var19), var18.equals(var19));
                        this.field12.put(var4, var24);
                        this.field12.put(var15, var24);
                        this.field12.put(var17, var24);
                     } else {
                        this.field11.put(var4, field9);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean method2(BridgeExtension var1) {
      if (!(var1 instanceof ArmorStandBridge)) {
         return false;
      }

      if (!var1.bridge$isInvisible()) {
         return false;
      }

      if (this.field11.getIfPresent(var1) != null) {
         return false;
      }

      Fishing var2 = (Fishing)this.field12.getIfPresent(var1);
      return var2 != null && var2.method1();
   }

   protected void onDisable() {
      this.field12.invalidateAll();
      this.field11.invalidateAll();
   }
}

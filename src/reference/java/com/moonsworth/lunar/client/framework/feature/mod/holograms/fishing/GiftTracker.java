package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.mixin.GiftRecord;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GiftTracker extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener field7 = (com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener.class
   );
   private static final Pattern field8 = Pattern.compile("^(?<type>From:|To:) (?:\\[.*?] )?(?<name>\\w+)$");
   private static final GiftRecord field9 = new GiftRecord(null, null, null, null, null, false, false);
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
   private final Cache<BridgeExtension, GiftRecord> field11 = CacheBuilder.newBuilder().weakKeys().expireAfterWrite(30L, TimeUnit.SECONDS).build();
   private final Cache<BridgeExtension, GiftRecord> field12 = CacheBuilder.newBuilder().weakKeys().expireAfterWrite(30L, TimeUnit.SECONDS).build();

   public GiftTracker() {
      this.handle(EventSecond.class, this::method1);
   }

   private void method1(EventSecond highlightimpl41) {
      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      if (itemcounter6extension2 != null) {
         for (BridgeExtension bridgeextension4 : itemcounter6extension2.bridge$getEntities()) {
            if (this.field12.getIfPresent(bridgeextension4) == null && bridgeextension4 instanceof EntityArmorStandBridge bridgeextension_25 && bridgeextension_25.bridge$isInvisible()) {
               ItemStackBridge bridgeextension_46 = bridgeextension_25.bridge$getHelmet();
               if (bridgeextension_46 != null && !bridgeextension_46.bridge$isEmpty()) {
                  String text7 = SkyblockItemUtil.method2(bridgeextension_46);
                  if (field10.contains(text7)) {
                     double value8 = bridgeextension_25.bridge$getPosX();
                     double value10 = bridgeextension_25.bridge$getPosY();
                     double value12 = bridgeextension_25.bridge$getPosZ();
                     List list14 = itemcounter6extension2.bridge$getEntities(
                        AxisAlignedBBBridge.method2(value8 - 0.1, value10 - 2.0, value12 - 0.1, value8 + 0.1, value10 + 2.0, value12 + 0.1), arg0 -> {
                           if (!(arg0 instanceof EntityArmorStandBridge)) {
                              return false;
                           } else {
                              return !arg0.bridge$isInvisible() ? false : arg0.bridge$getCustomName() != null;
                           }
                        }
                     );
                     BridgeExtension bridgeextension15 = null;
                     String text16 = null;
                     BridgeExtension bridgeextension17 = null;
                     String text18 = null;
                     String text19 = this.field7.method5();

                     for (BridgeExtension bridgeextension21 : list14) {
                        String text22 = TextBridge.getTextContent(bridgeextension21.bridge$getCustomName());
                        Matcher matcher23 = field8.matcher(text22);
                        if (matcher23.matches()) {
                           if (matcher23.group("type").equals("From:")) {
                              bridgeextension15 = bridgeextension21;
                              text16 = matcher23.group("name");
                           } else {
                              bridgeextension17 = bridgeextension21;
                              text18 = matcher23.group("name");
                           }
                        } else if (text22.equals("CLICK TO OPEN")) {
                           bridgeextension17 = bridgeextension21;
                           text18 = text19;
                        }
                     }

                     if (bridgeextension15 != null && bridgeextension17 != null && !(value10 > bridgeextension15.bridge$getPosY()) && !(bridgeextension15.bridge$getPosY() > bridgeextension17.bridge$getPosY())) {
                        GiftRecord fishing24 = new GiftRecord(bridgeextension4, bridgeextension15, text16, bridgeextension17, text18, text16.equals(text19), text18.equals(text19));
                        this.field12.put(bridgeextension4, fishing24);
                        this.field12.put(bridgeextension15, fishing24);
                        this.field12.put(bridgeextension17, fishing24);
                     } else {
                        this.field11.put(bridgeextension4, field9);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean method2(BridgeExtension bridgeextension1) {
      if (!(bridgeextension1 instanceof EntityArmorStandBridge)) {
         return false;
      }

      if (!bridgeextension1.bridge$isInvisible()) {
         return false;
      }

      if (this.field11.getIfPresent(bridgeextension1) != null) {
         return false;
      }

      GiftRecord fishing2 = (GiftRecord)this.field12.getIfPresent(bridgeextension1);
      return fishing2 != null && fishing2.method1();
   }

   protected void onDisable() {
      this.field12.invalidateAll();
      this.field11.invalidateAll();
   }
}

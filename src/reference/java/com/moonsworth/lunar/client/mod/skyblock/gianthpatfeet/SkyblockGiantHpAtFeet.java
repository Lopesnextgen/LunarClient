package com.moonsworth.lunar.client.mod.skyblock.gianthpatfeet;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.HashSet;

public class SkyblockGiantHpAtFeet extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final HashSet<BridgeExtension> field9 = new HashSet<>();
   private static final String[] field10 = new String[]{"Giant", "Sadan", "L.A.S.R.", "Bigfoot"};

   public SkyblockGiantHpAtFeet(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() >= 6));
      this.handle(EventEntitySpawn.class, this::method1);
      this.handle(HudRenderLegacyEventAlt.class, this::method2);
      this.handle(EventRenderEntity.class, this::method3);
      this.handle(EventEntityRemove.class, this::method4);
      this.handle(EventWorldChange.class, this::method5);
      this.method51(this::onDisable);
   }

   private void onDisable() {
      this.field9.clear();
   }

   public String getId() {
      return "SKYBLOCK_GIANT_HP_AT_FEET";
   }

   private void method1(EventEntitySpawn highlightimpl6_21) {
      BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
      if (bridgeextension2 instanceof EntityArmorStandBridge) {
         if (this.field9.contains(bridgeextension2) || bridgeextension2.bridge$getCustomName() == null) {
            return;
         }

         String text3 = TextBridge.getTextContent(bridgeextension2.bridge$getCustomName()).trim();
         if (text3.contains("❤")) {
            for (String text7 : field10) {
               if (text3.contains(text7)) {
                  this.field9.add(bridgeextension2);
                  break;
               }
            }
         }
      }
   }

   private void method2(HudRenderLegacyEventAlt highlightimpl41) {
      if (!this.field9.isEmpty()) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         float value3 = highlightimpl41.method5();

         for (BridgeExtension bridgeextension5 : this.field9) {
            double value6 = MathUtils.method15(bridgeextension5.method3(), bridgeextension5.bridge$getPosX(), value3) - bridge2_432.bridge$renderPosX();
            double value8 = MathUtils.method15(bridgeextension5.method4(), bridgeextension5.bridge$getPosY(), value3) - bridge2_432.bridge$renderPosY() + 0.7;
            double value10 = MathUtils.method15(bridgeextension5.method5(), bridgeextension5.bridge$getPosZ(), value3) - bridge2_432.bridge$renderPosZ();
            Bridge.method14().method5(Ref.method10(), bridgeextension5.bridge$getCustomName(), value6, value8, value10, -12.0F, false, false, bridgeextension5);
         }
      }
   }

   private void method3(EventRenderEntity data81) {
      if (!this.field9.isEmpty()) {
         BridgeExtension bridgeextension2 = data81.method1();
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            if (this.field9.contains(bridgeextension2)) {
               data81.setCancelled(true);
            }
         }
      }
   }

   private void method4(EventEntityRemove highlightimpl121) {
      if (!this.field9.isEmpty()) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            this.field9.remove(bridgeextension2);
         }
      }
   }

   private void method5(EventWorldChange data31) {
      this.field9.clear();
   }
}

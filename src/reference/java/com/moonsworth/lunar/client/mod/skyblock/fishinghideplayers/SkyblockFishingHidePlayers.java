package com.moonsworth.lunar.client.mod.skyblock.fishinghideplayers;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.FishingHookTracker;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import org.joml.Vector3d;

public class SkyblockFishingHidePlayers extends AbstractFeature {
   private final FishingHookTracker field8 = (FishingHookTracker)this.method63(FishingHookTracker.class);

   public SkyblockFishingHidePlayers(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntity.class, this::method1);
      this.handle(EventSpawnParticle.class, this::method2);
      this.handle(EventRenderNameTag.class, this::method3);
   }

   public String getId() {
      return "SKYBLOCK_FISHING_HIDE_PLAYERS";
   }

   private void method1(EventRenderEntity data81) {
      if (IslandUtils.isOnIsland()) {
         EntityFishHookBridge bridgeextension232 = this.field8.method15();
         if (bridgeextension232 != null) {
            BridgeExtension bridgeextension3 = data81.method1();
            if (!bridgeextension3.equals(bridgeextension232) && !bridgeextension3.equals(Ref.method7())) {
               if (bridgeextension3 instanceof Bridge6_10 bridge6_104) {
                  if (NpcUtils.method2(bridge6_104, true)) {
                     return;
                  }
               } else if (!(bridgeextension3 instanceof EntityFishHookBridge)) {
                  return;
               }

               if (!(bridgeextension232.HORHROIOIOICIRHIOCOICHHHIHCIIO(data81.method1()) >= 16.0)) {
                  data81.setCancelled(true);
               }
            }
         }
      }
   }

   private void method2(EventSpawnParticle highlightimpl151) {
      if (IslandUtils.isOnIsland()) {
         if (this.field8.method11()) {
            if (this.field8.method13(highlightimpl151.method2())) {
               if (!(Math.abs(highlightimpl151.method7() - 0.01F) >= 0.001F)) {
                  Vector3d vector3d2 = new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ());
                  Vector3d vector3d3 = this.field8.method10();
                  if (!vector3d2.equals(vector3d3)) {
                     highlightimpl151.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private void method3(EventRenderNameTag highlightimpl111) {
      if (IslandUtils.isOnIsland() && !highlightimpl111.isCancelled()) {
         EntityFishHookBridge bridgeextension232 = this.field8.method15();
         if (bridgeextension232 != null) {
            if (highlightimpl111.method2() instanceof EntityPlayerBridge bridgeextension2223) {
               if (bridgeextension2223.bridge$getEntityId() != Ref.method7().bridge$getEntityId() && !NpcUtils.method3(bridgeextension2223, true)) {
                  double value10 = bridgeextension2223.bridge$getPosX();
                  double value6 = bridgeextension2223.bridge$getPosY();
                  double value8 = bridgeextension2223.bridge$getPosZ();
                  if (!(bridgeextension232.method15(value10, value6, value8) >= 16.0)) {
                     highlightimpl111.setCancelled(true);
                  }
               }
            }
         }
      }
   }
}

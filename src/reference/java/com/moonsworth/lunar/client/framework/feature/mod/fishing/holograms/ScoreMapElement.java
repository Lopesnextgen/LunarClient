package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemMapBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
public class ScoreMapElement extends MapElement {
   public ScoreMapElement(DungeonMapOverlay holograms3_21) {
      super("Score", holograms3_21);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      if (Ref.method7() != null) {
         ItemStackBridge bridgeextension_47 = (ItemStackBridge)Ref.method7().bridge$getInventory().bridge$getMainInventory().get(8);
         if (bridgeextension_47 != null && !bridgeextension_47.bridge$isEmpty() && bridgeextension_47.bridge$getItem() == Bridge.method28().method30() && bridgeextension_47.bridge$hasDisplayName()) {
            if (holograms2_53.method48()) {
               if (bridgeextension_47.bridge$getItem() instanceof ItemMapBridge bridge5_48) {
                  MapDataBridge itemcounter2_313 = bridge5_48.bridge$getMapData(bridgeextension_47, Ref.method8());
                  Integer number10 = bridge5_48.bridge$getMapId(bridgeextension_47, Ref.method8());
                  if (itemcounter2_313 != null && number10 != null) {
                     com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms211 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem(
                        itemcounter2_313, number10
                     );
                     float value12 = holograms_92.method35();
                     this.method4(mixinhelper_41, holograms211, value4 + value12, value5 + value12, 100.0F - value12 * 2.0F, 100.0F - value12 * 2.0F);
                  }
               }
            }
         }
      }
   }

   @Override
   public void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
   }

   @Override
   public boolean method3(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      if (Ref.method7() == null) {
         return false;
      } else {
         ItemStackBridge bridgeextension_43 = (ItemStackBridge)Ref.method7().bridge$getInventory().bridge$getMainInventory().get(8);
         if (bridgeextension_43 == null || bridgeextension_43.bridge$isEmpty() || bridgeextension_43.bridge$getItem() != Bridge.method28().method30() || !bridgeextension_43.bridge$hasDisplayName()) {
            return false;
         } else {
            return this.IHCCORIRRIIICCIOORCORRIHHROHOC.method24() ? false : holograms2_52.method48();
         }
      }
   }

   private void method4(
      MixinHelper_4 mixinhelper_41,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms22,
      float value3,
      float value4,
      float value5,
      float value6
   ) {
      ResourceLocationBridge horsestats147 = Ref.method3()
         .bridge$getGameRenderer()
         .bridge$getMapItemRenderer()
         .bridge$getMapTexture(holograms22.method1(), holograms22.method2());
      if (horsestats147 != null) {
         this.IHCCORIRRIIICCIOORCORRIHHROHOC.method19(mixinhelper_41, horsestats147, value3, value4, value5, value6, 0.0F, 0.0F, value5, value6);
      }
   }
}

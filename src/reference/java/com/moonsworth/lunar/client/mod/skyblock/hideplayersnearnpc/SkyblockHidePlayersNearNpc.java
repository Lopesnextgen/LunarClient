package com.moonsworth.lunar.client.mod.skyblock.hideplayersnearnpc;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.chest.NpcLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import org.joml.Vector3d;

public class SkyblockHidePlayersNearNpc extends AbstractFeature {
   private static final double field8 = 9.0;

   public SkyblockHidePlayersNearNpc(Skyblock skyblock1) {
      super(true);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.RENDER));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderEntity.class, this::method1);
   }

   private void method1(EventRenderEntity data81) {
      BridgeExtension bridgeextension2 = data81.method1();
      if (!bridgeextension2.equals(Ref.method7())) {
         if (bridgeextension2 instanceof Bridge6_10 bridge6_103) {
            if (!NpcUtils.method2(bridge6_103, true)) {
               NpcLocations chest4 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method32();
               if (chest4 != null) {
                  List list5 = chest4.getLocations(IslandUtils.getIsland().name());
                  if (list5 != null && !list5.isEmpty()) {
                     for (Vector3d vector3d7 : list5) {
                        if (bridgeextension2.method15(vector3d7.x(), vector3d7.y(), vector3d7.z()) < 9.0) {
                           data81.setCancelled(true);
                           return;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_HIDE_PLAYERS_NEAR_NPC";
   }
}

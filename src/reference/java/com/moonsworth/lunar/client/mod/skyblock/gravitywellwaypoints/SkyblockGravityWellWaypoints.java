package com.moonsworth.lunar.client.mod.skyblock.gravitywellwaypoints;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;

public class SkyblockGravityWellWaypoints extends AbstractFeature {
   private static final String field8 = "ewogICJ0aW1lc3RhbXAiIDogMTU5NTUyMTkzMDExNSwKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ZGUzNTY0ZDVmMjA1NmE3ZTE3ZTk2NDE5MjE4YjQyMTM4NDhhMmY0ZDJiNWZlMDM1Yjc0NjBjOTg2ZTZmNDhjIgogICAgfQogIH0KfQ==";
   private static final Vec3Bridge field9 = Vec3Bridge.method2(-485.5, 136.5, -1016.5);
   private final List<SkyblockGravityWellWaypoints.Data> field10 = new ArrayList<>();

   public SkyblockGravityWellWaypoints(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.CRIMSON_ISLE));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRIMSON_ISLES));
      this.method2(this::method13);
      this.handle(EventEntitySpawn.class, this::method1);
      this.handle(EventEntityRemove.class, this::method2);
      this.handle(EventWorldChange.class, arg1x -> this.method13());
   }

   public String getId() {
      return "SKYBLOCK_GRAVITY_WELL_WAYPOINTS";
   }

   private void method1(EventEntitySpawn highlightimpl6_21) {
      BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
      if (bridgeextension2 instanceof EntityArmorStandBridge bridgeextension_23) {
         ItemStackBridge bridgeextension_44 = bridgeextension_23.bridge$getHelmet();
         if (bridgeextension_44 == null) {
            return;
         }

         if (!bridgeextension_44.bridge$getItem().bridge$isItemSkull()) {
            return;
         }

         Optional optional5 = SkyblockItemUtil.method11(bridgeextension_44);
         if (optional5.isEmpty()
            || !((String)optional5.get())
               .equals(
                  "ewogICJ0aW1lc3RhbXAiIDogMTU5NTUyMTkzMDExNSwKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ZGUzNTY0ZDVmMjA1NmE3ZTE3ZTk2NDE5MjE4YjQyMTM4NDhhMmY0ZDJiNWZlMDM1Yjc0NjBjOTg2ZTZmNDhjIgogICAgfQogIH0KfQ=="
               )) {
            return;
         }

         if (bridgeextension2.bridge$getPosY() <= field9.bridge$yCoord() - 8.0 || bridgeextension2.bridge$getPosY() >= field9.bridge$yCoord() + 8.0) {
            return;
         }

         double value6 = bridgeextension2.method15(field9.bridge$xCoord(), field9.bridge$yCoord(), field9.bridge$zCoord());
         if (value6 > 800.0) {
            return;
         }

         for (SkyblockGravityWellWaypoints.Data data9 : this.field10) {
            if (bridgeextension2 == data9.method1()) {
               return;
            }
         }

         Waypoint guihandler210 = Waypoint.method18()
            .method2("Gravity Well: " + (this.field10.size() + 1))
            .method3(Vec3Bridge.method2(bridgeextension2.bridge$getPosX(), bridgeextension2.bridge$getPosY() + 3.0, bridgeextension2.bridge$getPosZ()))
            .method4(Client.method109().getWorld())
            .method5(Ref.method8().bridge$getDimensionId())
            .method12(WaypointStore.method19())
            .method13(true)
            .method19();
         this.field10.add(new SkyblockGravityWellWaypoints.Data(bridgeextension2, guihandler210));
         Ref.method4().method48().method6(guihandler210);
      }
   }

   private void method2(EventEntityRemove highlightimpl121) {
      SkyblockGravityWellWaypoints.Data data2 = null;

      for (SkyblockGravityWellWaypoints.Data data4 : this.field10) {
         if (data4.field1 == highlightimpl121.method1()) {
            data2 = data4;
         }
      }

      if (data2 != null) {
         Ref.method4().method48().method9(data2.field2);
         this.field10.remove(data2);
      }
   }

   private void method13() {
      for (SkyblockGravityWellWaypoints.Data data2 : this.field10) {
         Ref.method4().method48().method9(data2.field2);
      }

      this.field10.clear();
   }

   private static class Data {
      BridgeExtension field1;
      Waypoint field2;

      @Generated
      public BridgeExtension method1() {
         return this.field1;
      }

      @Generated
      public Waypoint method2() {
         return this.field2;
      }

      @Generated
      public Data(BridgeExtension bridgeextension1, Waypoint guihandler22) {
         this.field1 = bridgeextension1;
         this.field2 = guihandler22;
      }
   }
}

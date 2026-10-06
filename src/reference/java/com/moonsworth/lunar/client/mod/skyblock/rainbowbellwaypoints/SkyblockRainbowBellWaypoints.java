package com.moonsworth.lunar.client.mod.skyblock.rainbowbellwaypoints;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SkyblockRainbowBellWaypoints extends AbstractFeature {
   private static final Set<String> field8 = Sets.newHashSet(new String[]{"You rang the bell!", "This bell has already been rung!"});
   private static final List<Vec3Bridge> field9 = new ArrayList<Vec3Bridge>() {
      {
         this.add(Vec3Bridge.method2(-67.5, 66.0, -42.5));
         this.add(Vec3Bridge.method2(-95.5, 46.0, -56.5));
         this.add(Vec3Bridge.method2(-3.5, 96.0, -41.5));
         this.add(Vec3Bridge.method2(47.5, 55.0, -6.5));
         this.add(Vec3Bridge.method2(-29.5, 125.0, 59.5));
         this.add(Vec3Bridge.method2(-89.5, 109.0, 16.5));
         this.add(Vec3Bridge.method2(-49.5, 81.0, 0.5));
      }
   };
   private final List<Waypoint> field10 = new ArrayList<>();
   private final boolean[] field11 = new boolean[7];

   public SkyblockRainbowBellWaypoints(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.SAFARI));
      this.method50(this::method13);
      this.method51(this::reset);
      this.handle(TypedChatMessage.class, this::method1);
      this.method14(EventWorldChange.class, this::method13);
   }

   private void method1(TypedChatMessage data1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         if (field8.contains(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH())) {
            double value3 = bridge5extension_52.bridge$getPosX();
            double value5 = bridge5extension_52.bridge$getPosY();
            double value7 = bridge5extension_52.bridge$getPosZ();
            int index9 = 0;
            double value10 = Double.MAX_VALUE;

            for (int index12 = 0; index12 < field9.size(); index12++) {
               Vec3Bridge horsestats1513 = field9.get(index12);
               double value14 = horsestats1513.method4(value3, value5, value7);
               if (value14 < value10) {
                  index9 = index12;
                  value10 = value14;
               }
            }

            this.field11[index9] = true;
            this.method13();
         }
      }
   }

   private void method13() {
      if (!Ref.method4().method40().method20().isEnabled()) {
         Ref.method4().method69().method2("SkyBlock Mod", NotificationManager.method15("enableWaypointModForBellWaypoints", new Object[0]));
      }

      this.method14();
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 != null) {
         for (int index2 = 0; index2 < this.field11.length; index2++) {
            if (!this.field11[index2]) {
               this.field10
                  .add(
                     Waypoint.method18()
                        .method2(this.method14("bell", new Object[]{index2 + 1}))
                        .method3(field9.get(index2))
                        .method4(Client.method109().getWorld())
                        .method5(itemcounter6extension1.bridge$getDimensionId())
                        .method12(WaypointStore.method19())
                        .method13(true)
                        .method19()
                  );
            }
         }

         for (Waypoint guihandler23 : this.field10) {
            Ref.method4().method48().method6(guihandler23);
         }
      }
   }

   private void method14() {
      for (Waypoint guihandler22 : this.field10) {
         Ref.method4().method48().method9(guihandler22);
      }

      this.field10.clear();
   }

   private void reset() {
      Arrays.fill(this.field11, false);
      this.method14();
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_RAINBOW_BELL_WAYPOINTS";
   }
}

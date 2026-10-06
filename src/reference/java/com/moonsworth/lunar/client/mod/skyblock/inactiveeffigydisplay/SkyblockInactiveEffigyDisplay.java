package com.moonsworth.lunar.client.mod.skyblock.inactiveeffigydisplay;

import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;

public class SkyblockInactiveEffigyDisplay extends AbstractFeature {
   private final ScoreboardListener field8 = (ScoreboardListener)this.method63(ScoreboardListener.class);
   private String field9 = "";
   private final Pattern field10 = Pattern.compile("Effigies: (.+)");
   private final Map<Integer, Vec3Bridge> field11 = Map.of(
      1,
      Vec3Bridge.method2(150.5, 76.0, 95.5),
      2,
      Vec3Bridge.method2(193.5, 90.0, 119.5),
      3,
      Vec3Bridge.method2(235.5, 107.0, 147.5),
      4,
      Vec3Bridge.method2(293.5, 93.0, 134.5),
      5,
      Vec3Bridge.method2(262.5, 96.0, 94.5),
      6,
      Vec3Bridge.method2(240.5, 126.0, 118.5)
   );
   private List<Waypoint> field12 = new ArrayList<>();

   public SkyblockInactiveEffigyDisplay(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(EventWorldChange.class, arg1x -> this.refresh());
      this.handle(EventScoreboardUpdate.class, this::method1);
   }

   private void onDisable() {
      this.refresh();
   }

   public String getId() {
      return "SKYBLOCK_INACTIVE_EFFIGY_DISPLAY";
   }

   private void method1(EventScoreboardUpdate highlightimpl21) {
      if (IslandUtils.getIsland() == SkyblockIsland.RIFT) {
         boolean flag2 = false;
         UnmodifiableIterator unmodifiableiterator3 = this.field8.method5().iterator();

         while (unmodifiableiterator3.hasNext()) {
            Component component4 = (Component)unmodifiableiterator3.next();
            String text5 = TextBridge.asLegacyString(component4);
            Matcher matcher6 = this.field10.matcher(text5);
            if (matcher6.find()) {
               flag2 = true;
               String text7 = matcher6.group(1);
               if (!this.field9.equals(text7)) {
                  this.method2(text7);
                  this.field9 = text7;
               }
            }
         }

         if (!flag2) {
            this.refresh();
         }
      }
   }

   private void method2(String text1) {
      if (!Ref.method4().method40().method20().isEnabled()) {
         Ref.method4().method69().method2("SkyBlock Mod", "Waypoint Mod must be enabled to see Inactive Effigy waypoints!");
      }

      this.method13();
      this.field12 = new ArrayList<>();
      boolean[] items2 = new boolean[6];
      int index3 = 0;
      boolean flag4 = true;

      for (int index5 = 0; index5 < text1.length(); index5++) {
         char character6 = text1.charAt(index5);
         switch (character6) {
            case '7':
               flag4 = false;
               break;
            case 'c':
               flag4 = true;
               break;
            case '⧯':
               if (index3 >= items2.length) {
                  System.err.println("Error parsing Effigy String in Skyblock Mod...");
                  return;
               }

               items2[index3] = flag4;
               index3++;
         }
      }

      WorldBridgeExtension itemcounter6extension8 = Ref.method8();
      if (itemcounter6extension8 != null) {
         for (int index9 = 0; index9 < items2.length; index9++) {
            if (!items2[index9]) {
               this.field12
                  .add(
                     Waypoint.method18()
                        .method2("Inactive Effigy: " + (index9 + 1))
                        .method3(this.field11.get(index9 + 1))
                        .method4(Client.method109().getWorld())
                        .method5(itemcounter6extension8.bridge$getDimensionId())
                        .method12(WaypointStore.method19())
                        .method13(true)
                        .method19()
                  );
            }
         }

         for (Waypoint guihandler27 : this.field12) {
            Ref.method4().method48().method6(guihandler27);
         }
      }
   }

   private void refresh() {
      this.method13();
      this.field9 = "";
   }

   private void method13() {
      for (Waypoint guihandler22 : this.field12) {
         Ref.method4().method48().method9(guihandler22);
      }
   }
}

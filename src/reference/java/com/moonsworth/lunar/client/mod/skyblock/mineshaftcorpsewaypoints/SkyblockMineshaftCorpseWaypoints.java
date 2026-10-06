package com.moonsworth.lunar.client.mod.skyblock.mineshaftcorpsewaypoints;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3ic;

public class SkyblockMineshaftCorpseWaypoints extends AbstractFeature {
   private final ScoreboardListener field8 = (ScoreboardListener)this.method63(ScoreboardListener.class);
   private final List<Vector3ic> field9 = new ArrayList<>();
   private boolean field10;

   public SkyblockMineshaftCorpseWaypoints(Skyblock skyblock1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.MINESHAFT));
      this.method51(this::onDisable);
      this.method50(this::onEnable);
      this.handle(EventTick.class, this::method1);
      this.handle(EventWorldChange.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method3);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_MINESHAFT_CORPSE_WAYPOINTS";
   }

   private void onDisable() {
      this.field9.clear();
   }

   private void method1(EventTick highlightimpl21) {
      if (this.field10) {
         String[] items2 = this.field8.method6().stream().findFirst().map(arg0 -> arg0.split(" ")).orElse(null);
         if (items2 != null && items2.length == 3) {
            String text3 = items2[2];
            if (text3.endsWith("_C")) {
               text3 = "ONYX_C";
            }

            List list4 = (List)Ref.method4().method40().method82().method15().method31().get(text3);
            if (list4 != null) {
               this.field9.addAll(list4);
               this.field10 = false;
            }
         }
      }
   }

   private void onEnable() {
      this.field9.clear();
      this.field10 = true;
   }

   private void method2(EventWorldChange data31) {
      this.field9.clear();
      this.field10 = true;
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_434.bridge$renderPosX(), -bridge2_434.bridge$renderPosY(), -bridge2_434.bridge$renderPosZ());

         for (Vector3ic vector3ic6 : this.field9) {
            WorldRenderUtils.drawBoxAtCoordinate(highlightimpl21.method3(), vector3ic6, 570490624, true);
         }

         bridgeextension_93.pop();
         this.field9.removeIf(arg1x -> bridge5extension_52.method17(arg1x) < 25.0);
      }
   }
}

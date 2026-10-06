package com.moonsworth.lunar.client.mod.skyblock.healingcircleoverlay;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.ParticleSample;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import toxi.geom.Vec2D;

public class SkyblockHealingCircleOverlay extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("healingCircleColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(570490624))
      .method31();
   private List<com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data> field9 = Collections.emptyList();
   private List<ParticleSample> field10 = new ArrayList<>();

   public SkyblockHealingCircleOverlay(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method1(this::onDisable);
      this.handle(EventSecond.class, this::method1);
      this.handle(EventSpawnParticle.class, this::method2);
      this.handle(HudRenderLegacyEvent.class, this::method3);
   }

   private void onDisable() {
      this.field9 = Collections.emptyList();
      this.field10.clear();
   }

   private void method1(EventSecond highlightimpl41) {
      List list2 = this.field10;
      this.field10 = new ArrayList<>();
      this.field9 = CircleFitter.findCircles(list2, 7.0F, 15.0F, 20);
   }

   private void method2(EventSpawnParticle highlightimpl151) {
      if (highlightimpl151.method2() == ParticleType.HAPPY_VILLAGER) {
         if (highlightimpl151.getPosY() % 1.0 == 0.0) {
            this.field10
               .add(
                  new ParticleSample(
                     new Vec2D((float)highlightimpl151.getPosX(), (float)highlightimpl151.getPosZ()), (float)highlightimpl151.getPosY(), Ref.method3().bridge$getSystemTime()
                  )
               );
         }
      }
   }

   public void method3(HudRenderLegacyEvent highlightimpl21) {
      if (!this.field9.isEmpty()) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());

         for (com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CircleFitter.Data data5 : this.field9) {
            WorldRenderUtils.draw3DCylinder(bridgeextension_93, data5.method1(), 0.0, data5.method2(), data5.method4(), 255.0, this.field8.method14(0.0F), false);
         }

         bridgeextension_93.pop();
      }
   }

   public String getId() {
      return "SKYBLOCK_HEALING_CIRCLE_OVERLAY";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field8}));
   }
}

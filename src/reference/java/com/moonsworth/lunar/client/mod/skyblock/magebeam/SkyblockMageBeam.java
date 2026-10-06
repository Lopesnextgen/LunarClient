package com.moonsworth.lunar.client.mod.skyblock.magebeam;

import com.moonsworth.lunar.bridge.EntitySheepBridge;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.LineFitter;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderParticle;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.LineSegment;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class SkyblockMageBeam extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("customMageBeam")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideSheepOption")
      .method31();
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "mageBeamColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mageBeamTime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final IntegerOption field12 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "mageBeamThickness"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .method7(1, 5))
      .method31();
   private final List<SkyblockMageBeam.Data> field13 = new ArrayList<>();
   private final Set<Vector3d> field14 = new HashSet<>();

   public SkyblockMageBeam(Skyblock skyblock1) {
      super(false);
      this.method10(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method10(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method10(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method51(this::reset);
      this.handle(EventSpawnParticle.class, this::method1);
      this.handle(EventRenderParticle.class, this::method3);
      this.handle(EventServerTick.class, this::method2);
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventRenderEntity.class, this::method5);
      this.handle(EventWorldChange.class, arg1x -> this.reset());
   }

   private void method1(EventSpawnParticle highlightimpl151) {
      if ((Boolean)this.field8.get()) {
         if (highlightimpl151.method2() == ParticleType.FIREWORK) {
            if (highlightimpl151.method6() == 0.0F && highlightimpl151.method7() == 0.0F && highlightimpl151.method8() == 0.0F) {
               this.field14.add(new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ()));
            }
         }
      }
   }

   private void method2(EventServerTick highlightimpl91) {
      if ((Boolean)this.field8.get()) {
         if (!this.field14.isEmpty()) {
            LineSegment fishing2 = LineFitter.fitLine(this.field14, 100, 1.0E-5, 10);
            this.field14.clear();
            if (fishing2 != null) {
               this.field13.add(new SkyblockMageBeam.Data(fishing2.method1(), fishing2.method2(), Ref.method3().bridge$getSystemTime()));
            }
         }
      }
   }

   private void method3(EventRenderParticle highlightimpl261) {
      if ((Boolean)this.field8.get()) {
         if (highlightimpl261.method1().bridge$isFirework()) {
            highlightimpl261.cancel();
         }
      }
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      if (!this.field13.isEmpty()) {
         this.field13.removeIf(arg1x -> arg1x.method3() + (Integer)this.field11.get() * 1000 < Ref.method3().bridge$getSystemTime());

         for (SkyblockMageBeam.Data data3 : this.field13) {
            WorldRenderUtils.drawLine(highlightimpl21.method3(), data3.method1(), data3.method2(), this.field10, ((Integer)this.field12.get()).intValue(), true);
         }
      }
   }

   private void method5(EventRenderEntity data81) {
      if ((Boolean)this.field9.get()) {
         data81.setCancelled(data81.method1() instanceof EntitySheepBridge);
      }
   }

   private void reset() {
      this.field13.clear();
      this.field14.clear();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field8, arg1xx -> arg1xx.method9(new ClientOption[]{this.field11, this.field12, this.field10})
            );
            arg1x.method9(new ClientOption[]{this.field9});
         }
      );
   }

   public String getId() {
      return "SKYBLOCK_MAGE_BEAM";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data {
      private final Vector3dc field1;
      private final Vector3dc field2;
      private final long field3;

      private Data(Vector3dc vector3dc1, Vector3dc vector3dc2, long number3) {
         this.field1 = vector3dc1;
         this.field2 = vector3dc2;
         this.field3 = number3;
      }

      public Vector3dc method1() {
         return this.field1;
      }

      public Vector3dc method2() {
         return this.field2;
      }

      public long method3() {
         return this.field3;
      }
   }
}

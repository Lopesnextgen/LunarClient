package com.moonsworth.lunar.client.mod.skyblock.burrowlocating;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.BurrowLocatingListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.Burrow;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowWarp;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.chatwaypoints.SkyblockChatWaypoints;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3ic;
import toxi.geom.Vec3D;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockBurrowLocating extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private final BurrowLocatingListener field9 = (BurrowLocatingListener)this.method63(BurrowLocatingListener.class);
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("burrowEstimateColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("burrowEstimateLine").method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("burrowEstimatesPrioritizePlayerWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("burrowEstimateBeam").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field14 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "ritualClosestWarpKeybind"
         )
         .method18(this))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("burrowWarpToNearestAlert").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final MultiSelectOption field16 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "ritualClosestWarpAllowed"
         )
         .method3(BurrowWarp.names())
         .method2(BurrowWarp.getEnabledByDefault()))
      .method31();
   private final ColorOption field17 = (ColorOption)((Data)OptionFactory.method8("burrowMobColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ColorOption field18 = (ColorOption)((Data)OptionFactory.method8("burrowTreasureColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field19 = (ColorOption)((Data)OptionFactory.method8("burrowStartColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field20 = (ColorOption)((Data)OptionFactory.method8("burrowUnknownColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("burrowBeam").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public SkyblockBurrowLocating(Skyblock skyblock1) {
      super(true);
      this.method13(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method13(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.HUB));
      this.method13(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method15(EventTick.class, this::method14);
      this.handle(HudRenderLegacyEventAlt.class, this::method5);
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> {
         if (Ref.method4().method40() != null) {
            SkyblockChatWaypoints skyblockchatwaypoints1x = Ref.method4().method40().method82().method63();
            if (arg0 && skyblockchatwaypoints1x.isEnabled()) {
               ((ModEnabledState)skyblockchatwaypoints1x.method7(ModTraits.field6)).setEnabled(true);
            }
         }
      });
      this.field14.method3(this::method13);
   }

   public String getId() {
      return "SKYBLOCK_BURROW_LOCATING";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method12("pqExtremeWarning")).method4(true);
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11, this.field13, this.field14, this.field12});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field15, this.field16}))
         .method2(this.field14::isDefault);
      lightingextension231.method9(new ClientOption[]{this.field17, this.field18, this.field19, this.field20, this.field21});
   }

   private void method13() {
      if (IslandUtils.getIsland() == SkyblockIsland.HUB) {
         if (Ref.method3().bridge$getCurrentScreen() == null) {
            this.method16().ifPresent(arg0 -> ChatMessageQueue.method1("/warp " + arg0.getWarp()));
         }
      }
   }

   private void method14() {
      if (this.field9.method5()) {
         this.method15();
         this.field9.method6();
      }

      if (!this.field14.isDefault() && (Boolean)this.field15.get()) {
         this.method16()
            .ifPresent(
               arg1 -> this.field8
                  .method2(
                     ComparableImpl.method2()
                        .method1("BURROW_WARP")
                        .method2(Component.text("/warp " + arg1.getWarp()).append(Component.text(" (" + this.field14 + ")", NamedTextColor.GRAY)))
                        .method3(100L)
                        .method4(Type.HIGH)
                        .method6()
                  )
            );
      }
   }

   private void method15() {
      this.field8
         .method2(
            ComparableImpl.method2()
               .method1("REUSE_SPADE")
               .method2(Component.text(this.method15("useSpade", new Object[0]), NamedTextColor.YELLOW))
               .method4(Type.LOW)
               .method6()
         );
   }

   private void method5(HudRenderLegacyEventAlt highlightimpl41) {
      AbstractRenderContext bridgeextension_92 = highlightimpl41.method3();

      for (Burrow burrow4 : this.field9.method7().values()) {
         burrow4.method3(bridgeextension_92, this);
      }

      EntityRenderDispatcherBridge bridge2_4312 = Ref.method13();
      bridgeextension_92.push();
      bridgeextension_92.translate(-bridge2_4312.bridge$renderPosX(), -bridge2_4312.bridge$renderPosY(), -bridge2_4312.bridge$renderPosZ());

      for (Vector3ic vector3ic5 : this.field9.method8()) {
         if ((Boolean)this.field13.get()) {
            WorldRenderUtils.fadingBoxBeam(bridgeextension_92, vector3ic5, this.field10.method13());
         }

         WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_92, vector3ic5, ColorUtils.method22(this.field10.method13(), 34), true);
         double value6 = vector3ic5.x() + 0.5;
         double value8 = vector3ic5.y() + 1.5;
         double value10 = vector3ic5.z() + 0.5;
         WorldRenderUtils.drawComponent(bridgeextension_92, Component.text("Estimate"), value6, value8, value10, true, 1.0F, true);
      }

      bridgeextension_92.pop();
      Skyblock skyblock14 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)this.field11.get()) {
         SkyblockChatWaypoints skyblockchatwaypoints15 = skyblock14.method63();
         Optional optional16 = skyblock14.method63().method13();
         if ((Boolean)this.field12.get() && optional16.isPresent()) {
            Vector3ic vector3ic7 = (Vector3ic)optional16.get();
            WorldRenderUtils.drawLineFromCamera(
               new Vec3D((float)(vector3ic7.x() + 0.5), vector3ic7.y() + 1, (float)(vector3ic7.z() + 0.5)), bridgeextension_92, skyblockchatwaypoints15.method14().method13(), (Float)skyblock14.method19().get()
            );
         } else {
            this.field9
               .method10(this)
               .ifPresent(
                  arg1x -> WorldRenderUtils.drawLineFromCamera(
                     new Vec3D(
                        (float)(((Vector3ic)arg1x.getKey()).x() + 0.5), ((Vector3ic)arg1x.getKey()).y() + 1, (float)(((Vector3ic)arg1x.getKey()).z() + 0.5)
                     ),
                     bridgeextension_92,
                     (Integer)arg1x.getValue(),
                     (Float)Ref.method4().method40().method82().method19().get()
                  )
               );
         }
      }
   }

   private Optional<BurrowWarp> method16() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 == null) {
         return Optional.empty();
      }

      if ((Boolean)this.field12.get()) {
         Skyblock skyblock2 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         Optional optional3 = skyblock2.method63().method13();
         if (optional3.isPresent()) {
            Vector3ic vector3ic4 = (Vector3ic)optional3.get();
            BurrowWarp burrowtype318 = null;
            double value20 = bridge5extension_51.method15(vector3ic4.x(), vector3ic4.y(), vector3ic4.z());

            for (String text24 : (Set)this.field16.get()) {
               BurrowWarp burrowtype325 = BurrowWarp.fromName(text24);
               if (burrowtype325 != null) {
                  double value26 = burrowtype325.getDistanceSq(vector3ic4.x(), vector3ic4.y(), vector3ic4.z());
                  if (value26 < value20) {
                     burrowtype318 = burrowtype325;
                     value20 = value26;
                  }
               }
            }

            if (burrowtype318 == null) {
               return Optional.empty();
            }

            return Optional.of(burrowtype318);
         }
      }

      BurrowWarp burrowtype315 = null;
      double value16 = Double.MAX_VALUE;

      for (Vector3ic vector3ic6 : this.field9.method9()) {
         int number7 = vector3ic6.x();
         int number8 = vector3ic6.y();
         int number9 = vector3ic6.z();

         for (String text11 : (Set)this.field16.get()) {
            BurrowWarp burrowtype312 = BurrowWarp.fromName(text11);
            if (burrowtype312 != null) {
               double value13 = burrowtype312.getDistanceSq(number7, number8, number9);
               if (value13 < value16) {
                  burrowtype315 = burrowtype312;
                  value16 = value13;
               }
            }
         }
      }

      Optional optional17 = this.field9.method11();
      int number19 = (int)bridge5extension_51.bridge$getPosX();
      int number21 = (int)bridge5extension_51.bridge$getPosY();
      int number22 = (int)bridge5extension_51.bridge$getPosZ();
      return burrowtype315 != null && !optional17.isEmpty() && !(((Vector3ic)optional17.get()).distance(number19, number21, number22) < Math.sqrt(value16) + 50.0)
         ? Optional.of(burrowtype315)
         : Optional.empty();
   }

   @Generated
   public ColorOption method17() {
      return this.field10;
   }

   @Generated
   public ColorOption method19() {
      return this.field17;
   }

   @Generated
   public ColorOption method21() {
      return this.field18;
   }

   @Generated
   public ColorOption method22() {
      return this.field19;
   }

   @Generated
   public ColorOption method23() {
      return this.field20;
   }

   @Generated
   public ToggleOption method24() {
      return this.field21;
   }
}

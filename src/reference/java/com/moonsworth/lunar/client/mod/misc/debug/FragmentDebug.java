package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.TurboBlockBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.render.turbo.TurboBatchRecorder;
import com.moonsworth.lunar.client.render.turbo.TurboGroupCollector;
import com.moonsworth.lunar.client.render.turbo.DelegatingFragData;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.FragData;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import org.intellij.lang.annotations.Subst;
import org.joml.Vector3d;

@VersionGate(min = 8)
public abstract class FragmentDebug extends AbstractFeature {
   private final Supplier<TurboBatchRecorder> field8;
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("turboShowGroupBounds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("turboShowGroupCullBounds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("turboShowBounds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("turboShowTurboableBounds").method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("turboShowType").method31();
   private final ColorOption field14 = (ColorOption)((Data)OptionFactory.method8("turboGroupColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1141050365))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)OptionFactory.method8("turboGroupCullColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field16 = (ColorOption)((Data)OptionFactory.method8("turboBoundsColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16776961))
      .method31();
   private final ColorOption field17 = (ColorOption)((Data)OptionFactory.method8("turboTurboableBoundsColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16776961))
      .method31();
   private final FloatOption field18 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "turboGroupLineWidth"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 4.0F))
      .method31();
   private final FloatOption field19 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "turboGroupCullLineWidth"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 4.0F))
      .method31();
   private final FloatOption field20 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "turboBoundsLineWidth"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(1.0F, 4.0F))
      .method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("turboShowCount").method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("turboLastRebuildTime").method31();
   private final ColorOption field23 = (ColorOption)((Data)OptionFactory.method8("turboCountColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ColorOption field24 = (ColorOption)((Data)OptionFactory.method8("turboLastRebuildTimeColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();

   public FragmentDebug(Framework7Extension framework7extension1, boolean flag2, Supplier<TurboBatchRecorder> supplier3) {
      super(flag2);
      this.field8 = supplier3;
      this.method4(ModTraits.field16, ChildModBinding.method5(() -> this.field8.get() != null, framework7extension1));
   }

   public String getId() {
      throw new IllegalStateException("FragmentDebug must be created using FragmentDebug.create()!");
   }

   @Subst("TypeId")
   public String method13() {
      throw new IllegalStateException("FragmentDebug must be created using FragmentDebug.create()!");
   }

   protected void method2(HudRenderLegacyEvent highlightimpl21) {
      TurboBatchRecorder highlight3handler2 = this.field8.get();
      if (highlight3handler2 != null) {
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
         double value5 = bridge2_434.bridge$renderPosX();
         double value7 = bridge2_434.bridge$renderPosY();
         double value9 = bridge2_434.bridge$renderPosZ();
         TurboGroupCollector highlight3iterator211 = highlight3handler2 instanceof TurboGroupCollector highlight3iterator212 ? highlight3iterator212 : null;
         bridgeextension_93.push();
         bridgeextension_93.translate(-value5, -value7, -value9);
         if (highlight3iterator211 != null && (Boolean)this.field9.get()) {
            int number28 = this.field14.method14(0.0F);
            BufferBuilderBridge bridge_2813 = bridgeextension_93.method11((Float)this.field18.get());

            for (AxisAlignedBBBridge horsestats1215 : highlight3iterator211.method4()) {
               WorldRenderUtils.drawBoxOutline(bridge_2813, horsestats1215, number28);
            }

            bridge_2813.end();
         }

         if (highlight3iterator211 != null && (Boolean)this.field10.get()) {
            int number29 = this.field15.method14(0.0F);
            BufferBuilderBridge bridge_2833 = bridgeextension_93.method11((Float)this.field19.get());

            for (FragData holograms_343 : highlight3iterator211.method5()) {
               WorldRenderUtils.drawBoxOutline(bridge_2833, holograms_343.method1().method11(0.06), number29);
            }

            bridge_2833.end();
         }

         if ((Boolean)this.field11.get()) {
            BufferBuilderBridge bridge_2830 = bridgeextension_93.method11((Float)this.field20.get());

            for (TurboBlockBridge bridge_3839 : highlight3handler2.method5(TurboBlockBridge::bridge$isTurbo)) {
               WorldRenderUtils.drawBoxOutline(bridge_2830, bridge_3839.bridge$getBoundingBox(), this.field16.method14(bridge_3839.bridge$getBlockPos().hashCode()));
            }

            if ((Boolean)this.field12.get()) {
               for (TurboBlockBridge bridge_3840 : highlight3handler2.method5(TurboBlockBridge::lunar$supportsTurbo)) {
                  WorldRenderUtils.drawBoxOutline(bridge_2830, bridge_3840.bridge$getBoundingBox(), this.field17.method14(bridge_3840.bridge$getBlockPos().hashCode()));
               }
            }

            bridge_2830.end();
         }

         bridgeextension_93.pop();
         if ((Boolean)this.field13.get()) {
            boolean flag31 = (Boolean)this.field12.get();

            for (TurboBlockBridge bridge_3841 : highlight3handler2.method5(arg1x -> arg1x.bridge$isTurbo() || flag31 && arg1x.lunar$supportsTurbo())) {
               WorldRenderUtils.drawComponent(
                  bridgeextension_93, bridge_3841.bridge$getTypeName(), bridge_3841.bridge$getPosX() - value5, bridge_3841.bridge$getPosY() + 1.0 - value7, bridge_3841.bridge$getPosZ() - value9, true
               );
            }
         }

         if (highlight3iterator211 != null) {
            boolean flag32 = (Boolean)this.field21.get();
            boolean flag37 = (Boolean)this.field22.get();
            if (flag32 || flag37) {
               int number42 = this.field23.method14(0.0F);
               int number44 = this.field24.method14(0.0F);
               long number16 = System.currentTimeMillis();

               for (FragData holograms_319 : highlight3iterator211.method5()) {
                  Vector3d vector3d20 = holograms_319.method1().method13();
                  double value21 = vector3d20.x - value5;
                  double value23 = vector3d20.y - value7;
                  double value25 = vector3d20.z - value9;
                  if (flag32) {
                     WorldRenderUtils.drawString(bridgeextension_93, Integer.toString(holograms_319.getCount()), value21, value23, value25, number42, true);
                  }

                  if (flag37 && holograms_319 instanceof DelegatingFragData hologramshandler27) {
                     WorldRenderUtils.drawString(bridgeextension_93, number16 - hologramshandler27.method5() + "ms", value21, value23 + (flag32 ? 0.3 : 0.0), value25, number44, true);
                  }
               }
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      String text2 = this.method13();
      BooleanSupplier booleansupplier3 = () -> {
         TurboBatchRecorder highlight3handler1x = this.field8.get();
         return highlight3handler1x == null ? true : !(highlight3handler1x instanceof TurboGroupCollector);
      };
      ((SettingsSectionImpl)lightingextension231.method1(
            "turboGroup",
            arg1x -> {
               arg1x.method7(
                  this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14, this.field18})
               );
               arg1x.method7(
                  this.field10, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15, this.field19})
               );
            }
         ))
         .method2(booleansupplier3);
      lightingextension231.method1(
         "turbo" + text2,
         arg1x -> arg1x.method7(
            this.field11,
            arg1xx -> arg1xx.method9(new ClientOption[]{this.field12, this.field13, this.field16, this.field17, this.field20})
         )
      );
      ((SettingsSectionImpl)lightingextension231.method1("turbo" + text2 + "Count", arg1x -> {
         arg1x.method7(this.field21, arg1xx -> arg1xx.method9(new ClientOption[]{this.field23}));
         arg1x.method7(this.field22, arg1xx -> arg1xx.method9(new ClientOption[]{this.field24}));
      })).method2(booleansupplier3);
   }

   public static FragmentDebug method4(TurboRenderingDebugMod turborenderingdebugmod0, BatchEntityType hologramstype1) {
      final String text2 = hologramstype1.getLangKey();
      String text3 = text2.toUpperCase();
      final String text4 = "TURBO_DEBUG_" + text3 + "_CHILD_MOD";
      return new FragmentDebug(turborenderingdebugmod0, false, () -> Ref.method4().method89().method13(hologramstype1)) {
         @Override
         public String getId() {
            return text4;
         }

         @Override
         public String method13() {
            return text2;
         }
      };
   }
}

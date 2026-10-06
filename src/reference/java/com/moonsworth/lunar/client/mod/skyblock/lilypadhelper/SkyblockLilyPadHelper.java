package com.moonsworth.lunar.client.mod.skyblock.lilypadhelper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.EntityItemRenderStateProvider;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.EntityRenderState;
import com.moonsworth.lunar.bridge.Transformation;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockLilyPadHelper extends AbstractFeature {
   private static final double field8 = 0.6;
   private static final double field9 = 8.0;
   private final HologramEntityListener field10 = (HologramEntityListener)this.method9(HologramEntityListener.class);
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showLilyPadProgreess").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lilyPadProgressGradient").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field13 = (ColorOption)((Data)OptionFactory.method8("lilyPadProgress").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightLilyPads").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)OptionFactory.method8("highlightColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157584383))
      .method31();
   private final EntitySubscription<EntityItemRenderStateProvider> field16 = this.field10.method6().method1(EntityItemRenderStateProvider.class).method2(arg0 -> {
      ItemStackBridge bridgeextension_41x = arg0.bridge$getItemStack();
      return bridgeextension_41x != null && !bridgeextension_41x.bridge$isEmpty() ? bridgeextension_41x.bridge$getItem() == Bridge.method28().method54() : false;
   }).method4(this);

   public SkyblockLilyPadHelper(Skyblock skyblock1) {
      super(true);
      this.method12(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.LOTUS_ATOLL));
      this.method12(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.LOTUS_ATOLL));
      this.handle(HudRenderLegacyEvent.class, arg1x -> {
         this.method1(arg1x);
         this.method2(arg1x);
      });
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field11.get()) {
         if (!this.field16.isEmpty()) {
            EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
            AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
            bridgeextension_93.push();
            bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
            float value4 = highlightimpl21.method5();

            for (EntityItemRenderStateProvider bridgeextension2_96 : this.field16) {
               if (bridgeextension2_96.bridge$getRotationPitch() == 0.0) {
                  EntityRenderState mixinhelper_127 = bridgeextension2_96.bridge$getRenderState();
                  if (mixinhelper_127 != null) {
                     float value8 = ((Transformation)mixinhelper_127.bridge$getTransformation().bridge$get(0.0F)).bridge$getScale().x();
                     int number9 = ClampUtils.clamp((int)((value8 - 0.6) / 7.4 * 100.0), 0, 100);
                     int number10 = this.field12.get() ? this.method4(number9) : this.field13.method14(0.0F);
                     TextComponent text11 = Component.text(number9 + "%", TextColor.color(number10));
                     WorldRenderUtils.drawComponent(
                        bridgeextension_93,
                        text11,
                        bridgeextension2_96.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(value4),
                        bridgeextension2_96.IHRHHRIHICHOOICIRIOOHOICHIRHOI(value4) + 0.5,
                        bridgeextension2_96.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(value4),
                        true
                     );
                  }
               }
            }

            bridgeextension_93.pop();
         }
      }
   }

   private void method2(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field14.get()) {
         if (!this.field16.isEmpty()) {
            EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
            AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
            bridgeextension_93.push();
            bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
            float value4 = highlightimpl21.method5();
            DrawBufferBridge bridge2_325 = bridgeextension_93.method10(LunarRenderTypes.field15);
            bridge2_325.method1();

            for (EntityItemRenderStateProvider bridgeextension2_97 : this.field16) {
               AxisAlignedBBBridge horsestats128 = this.method3(bridgeextension2_97, value4);
               if (horsestats128 != null) {
                  WorldRenderUtils.fillBox(bridge2_325, horsestats128, this.field15.method14(0.0F));
               }
            }

            bridge2_325.method17(BufferMode.BATCHED);
            Skyblock skyblock11 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            BufferBuilderBridge bridge_2812 = bridgeextension_93.method11((Float)skyblock11.method19().get());

            for (EntityItemRenderStateProvider bridgeextension2_99 : this.field16) {
               AxisAlignedBBBridge horsestats1210 = this.method3(bridgeextension2_99, value4);
               if (horsestats1210 != null) {
                  WorldRenderUtils.drawBoxOutline(bridge_2812, horsestats1210, this.field15.method14(0.0F) & 16777215 | 0xFF000000);
               }
            }

            bridge_2812.end();
            bridgeextension_93.pop();
         }
      }
   }

   @Nullable
   private AxisAlignedBBBridge method3(EntityItemRenderStateProvider bridgeextension2_91, float value2) {
      if (bridgeextension2_91.bridge$getRotationPitch() != 0.0) {
         return null;
      }

      EntityRenderState mixinhelper_123 = bridgeextension2_91.bridge$getRenderState();
      if (mixinhelper_123 == null) {
         return null;
      }

      float value4 = ((Transformation)mixinhelper_123.bridge$getTransformation().bridge$get(0.0F)).bridge$getScale().x();
      double value5 = bridgeextension2_91.RHIRRICCRHHHIIHHIHHOHRCHIOORCC(value2);
      double value7 = bridgeextension2_91.IHRHHRIHICHOOICIRIOOHOICHIRHOI(value2);
      double value9 = bridgeextension2_91.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(value2);
      double value11 = value4 / 2.0 + 0.01;
      double value13 = 0.0625 * value4 + 0.01;
      return AxisAlignedBBBridge.method2(value5 - value11, value7 - value13, value9 - value11, value5 + value11, value7 + value13, value9 + value11);
   }

   private int method4(int number1) {
      if (number1 >= 75) {
         return -43691;
      } else if (number1 >= 50) {
         return -22016;
      } else {
         return number1 >= 25 ? -171 : -11141291;
      }
   }

   public String getId() {
      return "SKYBLOCK_LILY_PAD_HELPER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> {
            arg1xx.method9(new ClientOption[]{this.field12});
            arg1xx.method9(new ClientOption[]{this.field13}).method3(this.field12::get);
         });
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}));
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}

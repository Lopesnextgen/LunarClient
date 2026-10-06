package com.moonsworth.lunar.client.mod.misc.debug;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.turbo.TurboBatchRecorder;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.mod.misc.debug.TurboGroupRebuilds;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import java.util.Locale;
import org.intellij.lang.annotations.Subst;

@VersionGate(min = 8)
public class TurboRenderingDebugMod extends AbstractFeature {
   public TurboRenderingDebugMod() {
      super(false);
      this.handle(HudRenderLegacyEvent.class, this::method1);
      this.method13(ModTraits.field18, arg0 -> arg0.RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(8));
   }

   public String getId() {
      return "TURBO_RENDERING_DEBUG_MOD";
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      ModChildren alertextension2 = (ModChildren)this.method7(ModTraits.field5);
      if (alertextension2 != null) {
         for (Framework7Extension framework7extension4 : alertextension2.getChildren()) {
            if (framework7extension4 instanceof FragmentDebug fragmentdebugchildmod5 && fragmentdebugchildmod5.isEnabled()) {
               fragmentdebugchildmod5.method2(highlightimpl21);
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ModifierKeybindOption lightingextension491332 = (ModifierKeybindOption)OptionFactory.method18("turboEntityToggle").method31();
      ModifierKeybindOption lightingextension491333 = (ModifierKeybindOption)OptionFactory.method18("turboBlockEntityToggle").method31();
      SimpleKeybindOption lightingextension491324 = (SimpleKeybindOption)OptionFactory.method17("turboEntityDump").method31();
      SimpleKeybindOption lightingextension491325 = (SimpleKeybindOption)OptionFactory.method17("turboEntityDumpClosest").method31();
      lightingextension231.method1("toggleKeybind", arg4x -> arg4x.method9(new ClientOption[]{lightingextension491332, lightingextension491333, lightingextension491324, lightingextension491325}));
      lightingextension491332.method3(() -> {
         ToggleOption lightingextension4430 = Client.method109().method41().method7().method17();
         lightingextension4430.method10(!(Boolean)lightingextension4430.get());
      });
      lightingextension491333.method3(() -> {
         ToggleOption lightingextension4430 = Client.method109().method41().method7().method19();
         lightingextension4430.method10(!(Boolean)lightingextension4430.get());
      });
      lightingextension491324.method3(
         () -> Ref.method3()
            .bridge$getPointedEntity()
            .ifPresent(arg0 -> Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(arg0.bridge$getNbtAsComponent()))
      );
      lightingextension491325.method3(
         () -> {
            Bridge5Extension_5 bridge5extension_50 = Ref.method7();
            if (bridge5extension_50 != null) {
               List list1x = Ref.method8()
                  .bridge$getEntities(bridge5extension_50.bridge$getBoundingBox().method11(3.0), arg1xx -> arg1xx.lunar$supportsTurbo() && arg1xx != bridge5extension_50);
               if (!list1x.isEmpty()) {
                  BridgeExtension bridgeextension2x = null;
                  double value3x = Double.MAX_VALUE;

                  for (BridgeExtension bridgeextension6 : list1x) {
                     double value7 = bridgeextension6.method13(bridge5extension_50);
                     if (value7 < value3x) {
                        bridgeextension2x = bridgeextension6;
                        value3x = value7;
                     }
                  }

                  if (bridgeextension2x != null) {
                     Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(bridgeextension2x.bridge$getNbtAsComponent());
                  }
               }
            }
         }
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }

   protected List<Framework7Extension> method9() {
      Builder builder1 = ImmutableList.builder();

      for (BatchEntityType hologramstype5 : BatchEntityType.values()) {
         builder1.add(this.method5(hologramstype5));
         builder1.add(FragmentDebug.method4(this, hologramstype5));
      }

      builder1.add(new TurboGroupRebuilds(this));
      return builder1.build();
   }

   private Framework7Extension method5(BatchEntityType hologramstype1) {
      return new TurboRenderingDebugMod.Data(this, hologramstype1);
   }

   private static class Data extends AbstractFeature {
      @Subst("MOD_ID")
      private final String field8;

      private Data(TurboRenderingDebugMod turborenderingdebugmod1, BatchEntityType hologramstype2) {
         super(false);
         this.field8 = "TURBO_" + hologramstype2.name() + "_CHILD_HUD_MOD";
         this.method2(ModTraits.field16, ChildModBinding.method5(() -> Ref.method4().method89().method13(hologramstype2) != null, turborenderingdebugmod1));
         this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, new HudSize(10, 18, 22, 80, 120, 150), arg1x -> {
            TurboBatchRecorder highlight3handler2x = Ref.method4().method89().method13(hologramstype2);
            String text3 = hologramstype2.name().toLowerCase(Locale.ROOT).replace("_", " ");
            String text4 = text3.substring(0, 1).toUpperCase() + text3.substring(1);
            return text4 + ": " + (highlight3handler2x == null ? 0 : highlight3handler2x.method7());
         }));
      }

      protected void method1(boolean flag1) {
         this.method2(ModTraits.field6, ModEnabledState.method6(flag1));
         this.method2(ModTraits.field9, ModSearchIndex.method7());
      }

      public String getId() {
         return this.field8;
      }
   }
}

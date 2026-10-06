package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.mod.UnlockableFeature;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class ModsEnabledDebugMod extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("showModsWithoutFeatureDynamic")
            .method4(false))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private final HudComponentGroup field9 = new HudComponentGroup(true);

   public ModsEnabledDebugMod() {
      super(false);
      this.handle(EventSecond.class, this::method6);
   }

   protected void method1(boolean flag1) {
      this.method12(ModTraits.field6, new UnlockableFeature(ModEnabledState.method6(flag1)));
      this.method12(ModTraits.field9, ModSearchIndex.method7());
   }

   public MixinCore9Extension method13() {
      return MixinCore9Base.method9(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, WidgetFactory.withBackground(new ScrollableHudComponent(this.field9).method1(300.0F).method3(true)));
   }

   @ConstantName
   public String getId() {
      return "MODS_ENABLED_DEBUG_MOD";
   }

   protected String method18() {
      return "Mods Enabled";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method8().method11(this);
   }

   private void method6(EventSecond highlightimpl41) {
      if (this.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field1)) {
         this.field9.method8();
         if ((Boolean)this.field8.get()) {
            for (Framework7Extension framework7extension3 : Ref.method4().method40().method1()) {
               if (!framework7extension3.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field19)) {
                  this.field9.method5(this.method10(framework7extension3));
                  framework7extension3.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg1x -> arg1x.ORCHOHHCOHCORRICRIHCHHRORHHCHH(arg1xx -> {
                     if (arg1xx.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field19)) {
                        return false;
                     }

                     this.field9.method5(this.method10(arg1xx));
                     return true;
                  }));
               }
            }
         } else {
            for (Framework7Extension framework7extension5 : Ref.method4().method40().method1()) {
               this.method9(framework7extension5)
                  .ifPresent(
                     arg2 -> {
                        this.field9.method5(arg2);
                        framework7extension5.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5)
                           .ifPresent(arg1xx -> arg1xx.HROOOICICRCOCIROHIRICCCOCCIORH(arg1xxx -> this.method9(arg1xxx).ifPresent(this.field9::method5)));
                     }
                  );
            }
         }
      }
   }

   private String method7(Framework7Extension framework7extension1) {
      int number2 = this.method8(framework7extension1);
      if (number2 == 0) {
         return "";
      } else {
         return number2 == 1 ? " - " : "  " + "|-".repeat(Math.max(0, number2 - 1)) + " ";
      }
   }

   private int method8(Framework7Extension framework7extension1) {
      return framework7extension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field16)
         .<Framework7Extension>map(ChildModBinding::method1)
         .map(arg1x -> this.method8(arg1x) + 1)
         .orElse(0);
   }

   private Optional<HudComponent> method9(Framework7Extension framework7extension1) {
      return framework7extension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field12).filter(ModLifecycle::method12).map(arg2 -> this.method10(framework7extension1));
   }

   private HudComponent method10(Framework7Extension framework7extension1) {
      String text2 = method11(framework7extension1);
      return new HudComponentGroup().method5(new TextHudComponent(this.method7(framework7extension1))).method5(new TextHudComponent(text2));
   }

   @NotNull
   private static String method11(Framework7Extension framework7extension0) {
      if (com.moonsworth.lunar.client.framework.build.LunarBuildData.field4) {
         return framework7extension0.getId();
      }

      String text1 = framework7extension0.getClass().getSimpleName();
      if (text1.isBlank()) {
         text1 = framework7extension0.getClass().getName();
      }

      return text1;
   }
}

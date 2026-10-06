package com.moonsworth.lunar.client.mod.combat.knockbacktrainer;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.HideableHudComponent;
import com.moonsworth.lunar.client.ui.hud.BackgroundHudComponent;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.util.function.Supplier;

public class KnockbackStats extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showFallingPercent").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showStreak").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAvgError").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "statSubjectColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5592406))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "statValueColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "resetKnockbackStats"
         )
         .method18(this))
      .method31();

   public KnockbackStats(KnockbackTrainer knockbacktrainer1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(knockbacktrainer1));
      this.method2(ModTraits.field1, new KnockbackStats.Data());
      this.field13
         .method3(() -> ((KnockbackTrainer)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method19());
   }

   public String getId() {
      return "KNOCKBACK_STATS";
   }

   private KnockbackTrainer method13() {
      return (KnockbackTrainer)((ChildModBinding)this.method7(ModTraits.field16)).method1();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.field8,
               this.field9,
               this.field10,
               OptionFactory.method14("reset").method4(() -> this.method13().method19()).method31(),
               this.field13
            }
         )
      );
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field11, this.field12})
      );
   }

   private HudComponent method3(String text1, Supplier<String> supplier2, ToggleOption lightingextension4433) {
      return new HideableHudComponent(
            new HudComponentGroup(Type.CENTER)
               .method5(
                  new TextHudComponent(() -> this.method14(text1, new Object[0]) + ": ").method6(() -> this.field11.method14(0.0F))
               )
               .method5(new TextHudComponent(supplier2).method6(() -> this.field12.method14(0.0F)))
         )
         .method2(() -> !(Boolean)lightingextension4433.get());
   }

   private String method14() {
      KnockbackTrainer knockbacktrainer1 = this.method13();
      return this.method14("streakValue", new Object[]{knockbacktrainer1.method22().method7(), knockbacktrainer1.method22().method8()});
   }

   private String method15() {
      KnockbackTrainer knockbacktrainer1 = this.method13();
      return this.method14("ticksValue", new Object[]{String.format("%.1f", knockbacktrainer1.method22().method5())});
   }

   private void method16() {
      KnockbackTrainer knockbacktrainer1 = this.method13();
      String text2 = this.method14("fallingHits", new Object[0])
         + ": "
         + String.format("%.0f%% (%d)", knockbacktrainer1.method22().method4(), knockbacktrainer1.method22().method6())
         + "\n"
         + this.method14("streak", new Object[0])
         + ": "
         + this.method14()
         + "\n"
         + this.method14("avgJumpError", new Object[0])
         + ": "
         + this.method15();
      ClipboardUtils.method2(text2);
      Ref.method4().method69().method3(this.method14("copied", new Object[0]));
   }

   private final class Data extends MixinCore9Base {
      private Data() {
         super(70.0F, 110.0F, HudAnchor.MIDDLE_LEFT);
         HudComponentGroup mixincore5iterator2 = new HudComponentGroup(true, Type.START)
            .method5(
               KnockbackStats.this.method3(
                  "fallingHits",
                  () -> String.format("%.0f%%", this.OHCROHOHCIHICOCIHROIRCOIIICRCI ? 72.0 : KnockbackStats.this.method13().method22().method4()),
                  KnockbackStats.this.field8
               )
            )
            .method5(
               KnockbackStats.this.method3(
                  "streak",
                  () -> this.OHCROHOHCIHICOCIHROIRCOIIICRCI
                     ? KnockbackStats.this.method14("streakValue", new Object[]{3, 5})
                     : KnockbackStats.this.method14(),
                  KnockbackStats.this.field9
               )
            )
            .method5(
               KnockbackStats.this.method3(
                  "avgJumpError",
                  () -> this.OHCROHOHCIHICOCIHROIRCOIIICRCI
                     ? KnockbackStats.this.method14("ticksValue", new Object[]{"1.2"})
                     : KnockbackStats.this.method15(),
                  KnockbackStats.this.field10
               )
            );
         BackgroundHudComponent mixincore5impl23 = new BackgroundHudComponent(new PaddedHudComponent(mixincore5iterator2).method2(4.0F));
         this.method2(
            new MixinCore5Task(mixincore5impl23)
               .method2(() -> KnockbackStats.this.method14("rightClickToCopy", new Object[0]))
               .method11(KnockbackStats.this::method16)
         );
      }

      public boolean method31() {
         return false;
      }
   }
}

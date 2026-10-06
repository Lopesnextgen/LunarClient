package com.moonsworth.lunar.client.mod.render.potioneffects;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.client.ui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.potioneffects.Potioneffects;
import com.moonsworth.lunar.client.framework.feature.potioneffects.Potioneffects2;
import com.moonsworth.lunar.client.framework.feature.potioneffects.Potioneffects4;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.Comparator;
import lombok.Generated;
import org.intellij.lang.annotations.Subst;
import org.jspecify.annotations.Nullable;

public class PotionEffects extends AbstractFeature {
   protected final EnumOption<PotionEffects.Type> potionEffectsMode = (EnumOption<PotionEffects.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "potionEffectsMode", PotionEffects.Type.NORMAL
      )
      .method31();
   protected final ToggleOption minimalModeHorizontal = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("minimalModeHorizontal")
      .method31();
   protected final IntegerOption minimalModeTilesPerLine = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "minimalModeTilesPerLine"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(4))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .method31();
   protected final ToggleOption vanillaGroupEffects = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "vanillaGroupEffects"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption vanillaModeHorizontal = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "vanillaModeHorizontal"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final IntegerOption vanillaModeTilesPerLine = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "vanillaModeTilesPerLine"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 10))
      .method31();
   protected final ToggleOption vanillaSpacing = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("vanillaSpacing")
      .method31();
   protected final ToggleOption effectName = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectName"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption effectDuration = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectDuration"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption effectAmplifier = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectAmplifier"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showInInventory = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showInInventory"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption showWhileTyping = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showWhileTyping"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption potionBlink = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "potionBlink"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption potionBlinkIcon = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("potionBlinkIcon")
      .method31();
   protected final IntegerOption blinkDuration = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "blinkDuration"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .OCRRICRIORICCCRHIOHORCICIHHICO(2, 20))
      .method31();
   protected final ToggleOption uppercasePotionNames = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("uppercasePotionNames")
      .method31();
   protected final ToggleOption reversedText = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("reversedText")
      .method31();
   protected final ToggleOption formattedDurations = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("formattedDurations")
      .method31();
   private final ToggleOption hideModernIcons = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "hideModernIcons"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption hidePotionStatus = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hidePotionStatus")
      .method31();
   protected final ToggleOption background = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ColorOption backgroundColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final ToggleOption border = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("border")
      .method31();
   protected final FloatOption borderThickness = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   protected final ColorOption borderColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   protected final ToggleOption textShadow = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption colorNameBasedOnEffect = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("colorNameBasedOnEffect")
      .method31();
   protected final ToggleOption colorInfoBasedOnEffect = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("colorInfoBasedOnEffect")
      .method31();
   protected final ColorOption textColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final ColorOption infoColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "infoColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption excludePerm = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("excludePerm")
      .method31();
   protected final ToggleOption effectBars = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("effectBars")
      .method31();
   protected final EnumOption<PotionEffects.Type2> effectBarPosition = (EnumOption<PotionEffects.Type2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "effectBarPosition", PotionEffects.Type2.BOTTOM
      )
      .method31();
   private final ToggleOption effectBarVanillaHud = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectBarVanillaHud"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption effectBarInventory = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectBarInventory"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption effectBarLunarHud = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("effectBarLunarHud")
      .method31();
   protected final ToggleOption effectBarHideAmbient = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectBarHideAmbient"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption effectBarGradient = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "effectBarGradient"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption effectBarCustomColor = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("effectBarCustomColor")
      .method31();
   protected final ColorOption effectBarColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "effectBarColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method16()
      .method31();
   protected final ToggleOption hideAmbientDuration = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideAmbientDuration")
      .method31();
   protected final ToggleOption showEffectBackground = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showEffectBackground"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final EnumOption<NotificationAnchor> durationPosition = (EnumOption<NotificationAnchor>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "durationPosition", NotificationAnchor.BOTTOM_LEFT
      )
      .method31();
   protected final EnumOption<NotificationAnchor> amplifierPosition = (EnumOption<NotificationAnchor>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "amplifierPosition", NotificationAnchor.TOP_RIGHT
      )
      .method31();
   protected final FloatOption vanillaIconScale = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "vanillaIconScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.5F, 1.25F))
      .method31();
   protected final FloatOption vanillaTextScale = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "vanillaTextScale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.25F, 2.0F))
      .method31();
   protected final ToggleOption useMinecraftGUIScale = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("useMinecraftGUIScale")
      .method31();
   protected final ColorOption vanillaBlinkColor = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "vanillaBlinkColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final Int2ObjectMap<ToggleOption> excludedPotionOptions = new Int2ObjectArrayMap();
   private final Potioneffects2 renderer = new Potioneffects2(this);

   public PotionEffects() {
      super(true);
      this.method12(Framework.field1, new Potioneffects4(this));
      this.method9(EventClientTick.class, this::tick);
   }

   @Override
   public String getId() {
      return "POTION_EFFECTS";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.potionEffectsMode});
            ((SettingsSectionImpl)var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hideAmbientDuration}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5))
               .method2(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.vanillaGroupEffects, this.vanillaModeHorizontal, this.vanillaModeTilesPerLine, this.vanillaSpacing})
               .method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC().method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.minimalModeHorizontal, this.minimalModeTilesPerLine})
               .method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.MINIMAL);
            var1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC().method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.MINIMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectName}).method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.NORMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectDuration});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectAmplifier}).method3(() -> this.potionEffectsMode.get() == PotionEffects.Type.MINIMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.formattedDurations}).method3(() -> this.potionEffectsMode.get() == PotionEffects.Type.VANILLA);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.uppercasePotionNames, this.reversedText})
               .method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.NORMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showWhileTyping});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.potionBlink, var1xx -> {
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.potionBlinkIcon, this.blinkDuration});
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.vanillaBlinkColor}).method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            });
            var1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showInInventory});
            var1x.ROOOCICROROOHCIRRHHHCRCOROOHHH();
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hideModernIcons}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
            var1x.ROOOCICROROOHCIRRHHHCRCOROOHHH().RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hidePotionStatus});
            var1x.ROOOCICROROOHCIRRHHHCRCOROOHHH();
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.effectBars, var1xx -> {
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectBarPosition, this.effectBarVanillaHud, this.effectBarInventory, this.effectBarLunarHud});
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectBarHideAmbient}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectBarGradient}).method3(() -> this.effectBarCustomColor.get() && this.effectBarColor.method14());
               var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.effectBarCustomColor, var1xxx -> var1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.effectBarColor}));
            });
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.HUD,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.durationPosition, this.amplifierPosition})
               .method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.background, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.backgroundColor}));
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.border, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.borderThickness, this.borderColor})
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textShadow});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.colorNameBasedOnEffect}).method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.NORMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.colorInfoBasedOnEffect});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.showEffectBackground}).method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.textColor})
               .method3(() -> this.colorNameBasedOnEffect.get() || this.potionEffectsMode.get() != PotionEffects.Type.NORMAL);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.infoColor}).method3(this.colorInfoBasedOnEffect::get);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.vanillaIconScale, this.vanillaTextScale, this.useMinecraftGUIScale})
               .method3(() -> this.potionEffectsMode.get() != PotionEffects.Type.VANILLA);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1)).method9()}
               )
               .method3(this::isUsingMinecraftGuiScale);
         }
      );
      var1.method1("excludePotionEffects", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.excludePerm}));
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(this.createExcludeOptions());
   }

   private ToggleOption[] createExcludeOptions() {
      this.addExclusion(1, "excludeSpeed");
      this.addExclusion(2, "excludeSlowness");
      this.addExclusion(3, "excludeHaste");
      this.addExclusion(4, "excludeMiningFatigue");
      this.addExclusion(5, "excludeStrength");
      this.addExclusion(6, "excludeInstantHealth");
      this.addExclusion(7, "excludeInstantDamage");
      this.addExclusion(8, "excludeJumpBoost");
      this.addExclusion(9, "excludeNausea");
      this.addExclusion(10, "excludeRegen");
      this.addExclusion(11, "excludeResistance");
      this.addExclusion(12, "excludeFireRes");
      this.addExclusion(13, "excludeWaterBreathing");
      this.addExclusion(14, "excludeInvis");
      this.addExclusion(15, "excludeBlindness");
      this.addExclusion(16, "excludeNightVision");
      this.addExclusion(17, "excludeHunger");
      this.addExclusion(18, "excludeWeakness");
      this.addExclusion(19, "excludePoison");
      this.addExclusion(20, "excludeWither");
      this.addExclusion(21, "excludeHealthBoost");
      this.addExclusion(22, "excludeAbsorption");
      this.addExclusion(23, "excludeSaturation");
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         this.addExclusion(24, "excludeGlowing");
         this.addExclusion(25, "excludeLevitation");
         this.addExclusion(26, "excludeLuck");
         this.addExclusion(27, "excludeBadLuck");
      }

      if (ThreadModuleDump63.MC_VERSION >= 6) {
         this.addExclusion(28, "excludeSlowFalling");
         this.addExclusion(29, "excludeConduitPower");
         this.addExclusion(30, "excludeDolphinsGrace");
         if (ThreadModuleDump63.MC_VERSION < 22) {
            this.addExclusion(31, "excludeBadOmen");
         }

         this.addExclusion(ThreadModuleDump63.MC_VERSION >= 22 ? 31 : 32, "excludeHeroOfTheVillage");
      }

      if (ThreadModuleDump63.MC_VERSION >= 13) {
         this.addExclusion(33, "excludeDarkness");
      }

      if (ThreadModuleDump63.MC_VERSION >= 22) {
         this.addExclusion(34, "excludeTrialOmen");
         this.addExclusion(35, "excludeRaidOmen");
         this.addExclusion(36, "excludeWindCharged");
         this.addExclusion(37, "excludeWeaving");
         this.addExclusion(38, "excludeOozing");
         this.addExclusion(39, "excludeInfested");
      }

      if (ThreadModuleDump63.MC_VERSION >= 35) {
         this.addExclusion(40, "excludeBreathOfTheNautilus");
      }

      return this.excludedPotionOptions.values().stream().sorted(Comparator.comparing(ClientOption::getName)).toArray(ToggleOption[]::new);
   }

   private void addExclusion(int var1, @Subst("optionId") String var2) {
      this.excludedPotionOptions.put(var1, (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7(var2).method31());
   }

   private void tick() {
      ((Potioneffects4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1)).tick();
   }

   public boolean isEffectBarEnabledOnVanillaHud() {
      return this.isEnabled() && this.effectBars.get() && this.effectBarVanillaHud.get();
   }

   public boolean isEffectBarEnabledInInventory() {
      return this.isEnabled() && this.effectBars.get() && this.effectBarInventory.get();
   }

   public boolean isEffectBarEnabledOnLunarHud() {
      return this.effectBars.get() && this.effectBarLunarHud.get();
   }

   public boolean isUsingMinecraftGuiScale() {
      return this.potionEffectsMode.get() == PotionEffects.Type.VANILLA && this.useMinecraftGUIScale.get();
   }

   public void renderWithContext(AbstractRenderContext var1, com.moonsworth.lunar.bridge.fog.Fog var2, float var3, float var4, float var5, float var6) {
      this.renderer.method1(var1.method42(), var2, var3, var4, var5, var6);
   }

   public void method9(MixinHelper_4 var1, com.moonsworth.lunar.bridge.fog.Fog var2, float var3, float var4, float var5, float var6) {
      this.renderer.method1(var1, var2, var3, var4, var5, var6);
   }

   public boolean isEffectExcluded(com.moonsworth.lunar.bridge.fog.Fog var1) {
      if (this.excludePerm.get() && isPermanentEffect(var1)) {
         return true;
      }

      ToggleOption var2 = (ToggleOption)this.excludedPotionOptions.get(var1.bridge$getPotionID());
      return var2 != null && var2.get();
   }

   public static boolean isPermanentEffect(com.moonsworth.lunar.bridge.fog.Fog var0) {
      return ThreadModuleDump63.MC_VERSION <= 5 && var0.bridge$getIsPotionDurationMax()
         || var0.bridge$getDuration() == -1.0F
         || var0.bridge$getDuration() > 72000.0F;
   }

   public static int method12(com.moonsworth.lunar.bridge.fog.Fog var0, @Nullable Fog2 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         return var0.bridge$getColor() | 0xFF000000;
      } else {
         Integer var2 = Potioneffects.get(var0.bridge$getPotionID());
         if (var2 != null) {
            return var2;
         } else {
            return var1 != null && var1.bridge$isBadEffect() ? -7335920 : -15691760;
         }
      }
   }

   @Generated
   public ToggleOption getShowInInventory() {
      return this.showInInventory;
   }

   @Generated
   public ToggleOption getHideModernIcons() {
      return this.hideModernIcons;
   }

   protected enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      NORMAL("normal"),
      MINIMAL("minimal"),
      VANILLA("vanilla");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method23(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }

   protected enum Type2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      LEFT("left"),
      RIGHT("right"),
      TOP("top"),
      BOTTOM("bottom"),
      BORDER("border");

      private final String id;

      @Override
      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method23(this.id, new Object[0]);
      }

      @Generated
      Type2(String var3) {
         this.id = var3;
      }
   }
}

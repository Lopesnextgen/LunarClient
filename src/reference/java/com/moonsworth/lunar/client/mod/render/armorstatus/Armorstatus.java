package com.moonsworth.lunar.client.mod.render.armorstatus;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusSlot;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode;
import com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition;
import com.moonsworth.lunar.client.framework.feature.armorstatus.HotbarPosition;
import com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityDisplayMode;
import com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElement;
import com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElementProvider;
import com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusHud;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsParent;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Armorstatus extends AbstractFeature {
   public final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("itemName")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("itemCount")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showWhileTyping")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("moveArmorIndividually")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("textShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("damageOverlay")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("itemDamage")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("armorDamage")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("maxDamage")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideUnbreakableDurability")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final EnumOption<DurabilityPosition> field18 = (EnumOption<DurabilityPosition>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "durabilityPosition", DurabilityPosition.RIGHT
      )
      .method31();
   public final EnumOption<ArmorStatusListMode> field19 = (EnumOption<ArmorStatusListMode>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "listMode", ArmorStatusListMode.VERTICAL
      )
      .method31();
   public final EnumOption<DurabilityDisplayMode> field20 = (EnumOption<DurabilityDisplayMode>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "damageDisplay", DurabilityDisplayMode.VALUE
      )
      .method31();
   public final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("background")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("border")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final FloatOption field23 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("borderThickness")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   public final ColorOption field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   public final ColorOption field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   public final ColorOption field26 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "nameTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   public final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("vanillaMode")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hotbarAnchor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final EnumOption<HotbarPosition> field29 = (EnumOption<HotbarPosition>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "hotbarPosition", HotbarPosition.LEFT
      )
      .method31();
   public final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideEmptySlots")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   public final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("roundedCorners")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("lowDurabilityIndicator")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final IntegerOption field33 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "lowDurabilityThreshold"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 100))
      .method31();
   private final ToggleOption field34 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("staticDamageColors")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ColorOption field35 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "lowestColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5636096))
      .method31();
   private final ColorOption field36 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "lowColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption field37 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "mediumLowColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field38 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "mediumColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field39 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "highColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field40 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "highestColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final Map<ArmorStatusSlot, com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElementChildMod> field41 = new EnumMap<>(
      ArmorStatusSlot.class
   );
   private final ArmorStatusElementProvider field42 = new ArmorStatusElementProvider(this, this.field41);
   private final ArmorstatusBars field43 = new ArmorstatusBars(this);

   public Armorstatus() {
      super(true);
      this.method14(EventTick.class, this.field42::update);
      this.method7(ModTraits.field1, new ArmorStatusHud(this));
   }

   public String getId() {
      return "ARMORSTATUS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   protected List<Framework7Extension> method9() {
      Builder builder1 = ImmutableList.builder().add(new ArmorstatusProtection(this)).add(this.field43);

      for (ArmorStatusSlot armorstatustype5 : ArmorStatusSlot.values()) {
         if (armorstatustype5 != ArmorStatusSlot.OFF_HAND_HELD_ITEM || Ref.MC_VERSION >= 5) {
            com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElementChildMod framework7extension26 = com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElementChildMod.method4(
               this, armorstatustype5
            );
            builder1.add(framework7extension26);
            this.field41.put(armorstatustype5, framework7extension26);
         }
      }

      return builder1.build();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, SettingsParent::method19);
      lightingextension231.method7(
         SettingsPage.HUD,
         arg1x -> {
            arg1x.ROOOCICROROOHCIRRHHHCRCOROOHHH().method3(() -> Ref.MC_VERSION > 1);
            arg1x.method9(new ClientOption[]{this.field11}).method3(this.field27::get);
            arg1x.ROOOCICROROOHCIRRHHHCRCOROOHHH().method3(() -> (Boolean)this.field27.get() || Ref.MC_VERSION > 1);
            arg1x.method9(new ClientOption[]{this.field27});
            arg1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
            arg1x.method9(new ClientOption[]{this.field19})
               .method3(() -> (Boolean)this.field11.get() && !(Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field29})
               .method3(() -> !(Boolean)this.field27.get() || !(Boolean)this.field28.get());
            arg1x.method9(new ClientOption[]{this.field28, this.field31, this.field30}).method3(() -> !(Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field8}).method3(this.field27::get);
            arg1x.method9(new ClientOption[]{this.field9, this.field10, this.field12});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field21,
                  arg1xx -> {
                     arg1xx.method9(new ClientOption[]{this.field24});
                     arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                        this.field22, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.field23, this.field25})
                     );
                  }
               )
               .method3(() -> (Boolean)this.field11.get() || (Boolean)this.field27.get());
            arg1x.method9(
                  new ClientOption[]{((MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1)).method9()}
               )
               .method3(() -> (Boolean)this.field11.get() || (Boolean)this.field27.get() && (Boolean)this.field28.get());
            arg1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
         }
      );
      lightingextension231.method1(
         "damageOptions",
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field20})
               .method3(() -> !(Boolean)this.field14.get() && !(Boolean)this.field15.get());
            arg1x.method9(new ClientOption[]{this.field18})
               .method3(() -> this.field19.get() == ArmorStatusListMode.HORIZONTAL || this.method13() || (Boolean)this.field11.get() || (Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field13});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field32, arg1xx -> arg1xx.method9(new ClientOption[]{this.field33}))
               .method3(() -> !(Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field14, this.field15}).method3(() -> this.field20.get() == DurabilityDisplayMode.NONE);
            arg1x.method9(new ClientOption[]{this.field16})
               .method3(() -> this.field20.get() == DurabilityDisplayMode.PERCENT || this.method13() || (Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field17}).method3(this::method13);
            arg1x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
         }
      );
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field26})
               .method3(() -> !(Boolean)this.field8.get() || (Boolean)this.field27.get());
            arg1x.method9(new ClientOption[]{this.field40});
            arg1x.method9(new ClientOption[]{this.field39, this.field38, this.field37, this.field36, this.field35})
               .method3(() -> (Boolean)this.field34.get() || this.method13());
            arg1x.method9(new ClientOption[]{this.field34}).method3(this::method13);
         }
      );
   }

   private boolean method13() {
      return this.field20.get() == DurabilityDisplayMode.NONE || !(Boolean)this.field14.get() && !(Boolean)this.field15.get();
   }

   public Map<ArmorStatusSlot, ArmorStatusElement> method5(boolean flag1) {
      return this.field42.method1(flag1);
   }

   public ColorOption method6(int number1) {
      if ((Boolean)this.field34.get()) {
         return this.field40;
      } else if (number1 <= 10) {
         return this.field35;
      } else if (number1 <= 25) {
         return this.field36;
      } else if (number1 <= 40) {
         return this.field37;
      } else if (number1 <= 60) {
         return this.field38;
      } else {
         return number1 <= 80 ? this.field39 : this.field40;
      }
   }

   public static boolean method7(@Nullable ItemStackBridge bridgeextension_40, int number1) {
      int number2 = bridgeextension_40 == null ? 0 : bridgeextension_40.bridge$getMaxDamage();
      if (number2 > 0 && bridgeextension_40.bridge$isItemStackDamageable()) {
         int number3 = (number2 - bridgeextension_40.bridge$getItemDamage()) * 100 / number2;
         return number3 <= number1;
      } else {
         return false;
      }
   }

   @Generated
   public ArmorstatusBars method14() {
      return this.field43;
   }
}

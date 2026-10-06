package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Nullable;

public class HypixelBedwarsUpgradeDisplay extends AbstractFeature {
   private static final BiMap<Integer, String> romanNumerals = HashBiMap.create(
      ImmutableMap.builder()
         .put(1, "I")
         .put(2, "II")
         .put(3, "III")
         .put(4, "IV")
         .put(5, "V")
         .put(6, "VI")
         .put(7, "VII")
         .put(8, "VIII")
         .put(9, "IX")
         .put(10, "X")
         .build()
   );
   private static final List<String> forgeTiers = ImmutableList.builder().add(new String[]{"Iron", "Golden", "Emerald", "Molten"}).build();
   private static final Pattern upgradeMessagePattern = Pattern.compile("\\w{1,16} (?:purchased|unlocked) (?<name>[\\w\\s]+?)(?: (?<level>[IVXLCDM]+))?");
   private static final Pattern trapSetPattern = Pattern.compile("^\\w{1,16} (?:purchased|unlocked) ([a-zA-Z0-9-]+(?: [a-zA-Z0-9-]+)*) (?:Trap|trap)$");
   private static final Pattern trapRemovedPattern = Pattern.compile("^Removed ([a-zA-Z0-9-]+(?: [a-zA-Z0-9-]+)*) (?:Trap|trap) from the queue!$");
   private static final Pattern trapTriggeredPattern = Pattern.compile("([a-zA-Z0-9-]+(?: [a-zA-Z0-9-]+)*)\\s+(?:Trap|trap) (?:was set off!|set off by .+)$");
   private static final Pattern teamEliminatedPattern = Pattern.compile("TEAM ELIMINATED > (Red|Blue|Green|Yellow|Aqua|White|Pink|Gray) Team has been eliminated!");
   private final ToggleOption hideWhenNoUpgrades = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "hideWhenNoUpgrades"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption displayUpgradeCost = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "displayUpgradeCost"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showUpgrades = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showUpgrades"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showTraps = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showTraps"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption effectNames = (ToggleOption)OptionFactory.method7("effectNames").method31();
   private final ToggleOption romanNumeralsOption = (ToggleOption)OptionFactory.method7("romanNumerals").method31();
   private final ToggleOption textShadow = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final ColorOption upgradesTitleColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "upgradesTitleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption upgradeTextColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "upgradeTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption trapsTitleColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "trapsTitleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption trapsTextColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "trapsTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption upgradeCostColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "upgradeCostColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12211035))
      .method31();
   private final ColorOption maxUpgradeCostColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "maxUpgradeCostColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-13447886))
      .method31();
   private final Set<String> excludedItems = new HashSet<>();
   private final HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry registry = new HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry()
      .method1("Sharpened Swords")
      .method2("sharpness")
      .method3(4)
      .method4(8)
      .method5()
      .method1("Reinforced Armor")
      .method2("protection")
      .method3(2, 4, 8, 16)
      .method4(5, 10, 20, 30)
      .method5()
      .method1("Maniac Miner")
      .method2("haste")
      .method3(2, 4)
      .method4(4, 6)
      .method5()
      .method1("Forge")
      .method2("resources")
      .method3(2, 4, 6, 8)
      .method4(4, 8, 12, 16)
      .method5()
      .method1("Heal Pool")
      .method2("regeneration")
      .method3(1)
      .method4(3)
      .method5()
      .method1("Cushioned Boots")
      .method2("featherFalling")
      .method3(1, 2)
      .method4(2, 4)
      .method5()
      .method1("DeadShot")
      .method2("damage")
      .method3(3, 5, 7, 10)
      .method4(3, 5, 7, 10)
      .method5();
   private final List<String> activeTraps = new ArrayList<>();
   private final Map<BedwarsUpgrade, Integer> previewLevels = new LinkedHashMap<>();
   private HypixelBedwarsUpgradeDisplay.Type gameMode = HypixelBedwarsUpgradeDisplay.Type.SOLO;
   private String lastLocation;

   public HypixelBedwarsUpgradeDisplay(HypixelBedwars hypixelbedwars1) {
      super(true);
      this.method18(ModTraits.field18, ConfigRangeBuilder::method16);
      this.method18(ModTraits.field16, ChildModBinding.method3(hypixelbedwars1));
      this.method18(ModTraits.field1, new HypixelBedwarsUpgradeDisplay.Data());
      LunarEventBus.method29().method4(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage, 1);
      LunarEventBus.method29().method2(EventTick.class, this::updateGameMode);
      this.registry.method7().stream().limit(3L).forEach(arg1x -> this.previewLevels.put(arg1x, 1));
   }

   public String getId() {
      return "HYPIXEL_BEDWARS_UPGRADE_DISPLAY_CHILD";
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.hideWhenNoUpgrades, this.textShadow, this.background, this.border});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.showUpgrades, arg1xx -> arg1xx.method9(new ClientOption[]{this.romanNumeralsOption, this.effectNames, this.upgradesTitleColor, this.upgradeTextColor})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.showTraps, arg1xx -> arg1xx.method9(new ClientOption[]{this.trapsTitleColor, this.trapsTextColor})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.displayUpgradeCost, arg1xx -> arg1xx.method9(new ClientOption[]{this.upgradeCostColor, this.maxUpgradeCostColor})
            );
         }
      );
      ((SettingsSectionImpl)lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !(Boolean)this.background.get());
         arg1x.method9(new ClientOption[]{this.borderColor, this.borderThickness}).method3(() -> !(Boolean)this.border.get());
      })).method2(() -> !(Boolean)this.background.get() && !(Boolean)this.border.get());
      ((SettingsSectionImpl)lightingextension231.method1(
            "displayedUpgradesOptions",
            arg1x -> {
               for (BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3 : this.registry.method7()) {
                  ToggleOption lightingextension4434 = (ToggleOption)((ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
                              hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3.field1
                           )
                           .OOOIROIIOCOOHICRIRHHHRROHHHHIO(!this.excludedItems.contains(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3.field1)))
                        .method31())
                     .CICORRHIOIIOORRRICCORIOIOCIHII(arg2 -> {
                        if (arg2) {
                           this.excludedItems.remove(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3.field1);
                        } else {
                           this.excludedItems.add(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3.field1);
                        }
                     });
                  arg1x.method9(new ClientOption[]{lightingextension4434});
               }
            }
         ))
         .method2(() -> !(Boolean)this.showUpgrades.get());
   }

   public void save(JsonObject json1) {
      super.save(json1);
      JsonArray array2 = new JsonArray();
      this.excludedItems.forEach(array2::add);
      json1.add("excludedItems", array2);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      if (json1.has("excludedItems")) {
         for (JsonElement element4 : json1.getAsJsonArray("excludedItems")) {
            String text5 = element4.getAsString();
            if (this.registry.method6(text5)) {
               this.excludedItems.add(text5);
            }
         }
      }
   }

   public void shouldRender() {
      super.shouldRender();
      this.excludedItems.clear();
   }

   private void clear() {
      this.activeTraps.clear();
      this.registry.method9();
   }

   private void updateGameMode(EventTick highlightimpl21) {
      HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.getAll(ModTraits.field16)).method1();
      if (hypixelbedwars2.method21()) {
         HypixelLocation rewindhandlers23 = HypixelLocationListener.field7.method7();
         if (rewindhandlers23 != null) {
            if (!Objects.equals(this.lastLocation, rewindhandlers23.field1)) {
               this.lastLocation = rewindhandlers23.field1;
               this.clear();
            }

            if (rewindhandlers23.field3 != null) {
               String text4 = rewindhandlers23.field3.toLowerCase(Locale.ROOT);
               if (text4.contains("_eight_one")) {
                  this.gameMode = HypixelBedwarsUpgradeDisplay.Type.SOLO;
               } else if (text4.contains("_eight_two")) {
                  this.gameMode = HypixelBedwarsUpgradeDisplay.Type.DOUBLES;
               } else if (text4.contains("_four_three") || text4.contains("_four_four") || text4.contains("_two_four")) {
                  this.gameMode = HypixelBedwarsUpgradeDisplay.Type.OTHER;
               }
            }
         }
      }
   }

   private void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.getAll(ModTraits.field16)).method1();
      if (!data1.isCancelled() && hypixelbedwars2.method21()) {
         String text3 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         if (this.gameMode == HypixelBedwarsUpgradeDisplay.Type.SOLO && text3.equals("You have been eliminated!")) {
            this.clear();
         } else if (text3.trim().equals("- Max Team Upgrades")) {
            this.registry.method13();
         } else {
            Matcher matcher4 = field11.matcher(text3);
            if (matcher4.matches()) {
               this.activeTraps.add(matcher4.group(1));
            } else {
               matcher4 = field10.matcher(text3);
               if (matcher4.matches()) {
                  String text12 = matcher4.group("name").trim();
                  if (text12.toLowerCase(Locale.ROOT).contains("forge")) {
                     text12 = "Forge";
                  }

                  if (this.registry.method6(text12)) {
                     String text13 = matcher4.group("level");
                     if (text13 != null) {
                        text13 = text13.replaceAll("\\s", "").trim();
                     }

                     this.registry.method11(text12, text13);
                  }
               } else {
                  if (this.gameMode != HypixelBedwarsUpgradeDisplay.Type.SOLO) {
                     matcher4 = field14.matcher(text3);
                     if (matcher4.matches()) {
                        String text5 = TextBridge.getTextContent(Ref.method7().bridge$getDisplayNameComponent());
                        if (!text5.isEmpty()) {
                           char character6 = text5.charAt(0);
                           if (character6 == 'S') {
                              character6 = 'G';
                           }

                           String text7 = matcher4.group(1);
                           if (character6 == text7.charAt(0)) {
                              this.clear();
                           }
                        }

                        return;
                     }
                  }

                  matcher4 = field13.matcher(text3);
                  if (matcher4.matches()) {
                     this.activeTraps.remove(matcher4.group(1));
                     hypixelbedwars2.method14();
                  } else {
                     matcher4 = field12.matcher(text3);
                     if (matcher4.matches()) {
                        this.activeTraps.remove(matcher4.group(1));
                     }
                  }
               }
            }
         }
      }
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         if ((Boolean)HypixelBedwarsUpgradeDisplay.this.background.get()) {
            HypixelBedwarsUpgradeDisplay.this.backgroundColor.method11(mixinhelper_45, value2, value3, this.getWidth(), this.getHeight());
         }

         if ((Boolean)HypixelBedwarsUpgradeDisplay.this.border.get()) {
            HypixelBedwarsUpgradeDisplay.this.borderColor
               .method11(mixinhelper_45, this, value2, value3, this.getWidth(), this.getHeight(), (Float)HypixelBedwarsUpgradeDisplay.this.borderThickness.get());
         }

         float value6 = value3;
         int number7 = Ref.method10().method19() + 1;
         boolean flag8 = (Boolean)HypixelBedwarsUpgradeDisplay.this.textShadow.get();
         value2 += 3.0F;
         value3 += 3.0F;
         if ((Boolean)HypixelBedwarsUpgradeDisplay.this.showUpgrades.get()) {
            HypixelBedwarsUpgradeDisplay.this.upgradesTitleColor
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, "§l" + HypixelBedwarsUpgradeDisplay.this.method5("upgrades", new Object[0]), value2, value3, flag8);
            value3 += number7;
            if (!HypixelBedwarsUpgradeDisplay.this.registry.method10()) {
               HypixelBedwarsUpgradeDisplay.this.upgradeTextColor
                  .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, HypixelBedwarsUpgradeDisplay.this.method5("none", new Object[0]), value2, value3, flag8);
               value3 += number7;
            } else {
               for (BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10 : HypixelBedwarsUpgradeDisplay.this.registry.method8()) {
                  if (!HypixelBedwarsUpgradeDisplay.this.excludedItems.contains(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10.field1)) {
                     int index11 = HypixelBedwarsUpgradeDisplay.this.registry.method12(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10);
                     HypixelBedwarsUpgradeDisplay.this.upgradeTextColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, this.render(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10, index11), value2, value3, flag8);
                     value3 += number7;
                     if ((Boolean)HypixelBedwarsUpgradeDisplay.this.displayUpgradeCost.get()) {
                        value3 -= number7;
                        if (index11 < hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10.field2) {
                           boolean flag12 = HypixelBedwarsUpgradeDisplay.this.gameMode == HypixelBedwarsUpgradeDisplay.Type.SOLO
                              || HypixelBedwarsUpgradeDisplay.this.gameMode == HypixelBedwarsUpgradeDisplay.Type.DOUBLES;
                           int number13 = flag12 ? hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10.field4[index11] : hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data10.field5[index11];
                           int number14 = number13 > 9 ? 115 : 120;
                           HypixelBedwarsUpgradeDisplay.this.upgradeCostColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, number13 + " ⬆", value2 + number14, value3, flag8);
                        } else {
                           HypixelBedwarsUpgradeDisplay.this.maxUpgradeCostColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, " ✔", value2 + 115.0F, value3, flag8);
                        }

                        value3 += number7;
                     }
                  }
               }
            }
         }

         if ((Boolean)HypixelBedwarsUpgradeDisplay.this.showTraps.get()) {
            value3 += 3.0F;
            HypixelBedwarsUpgradeDisplay.this.trapsTitleColor
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, "§l" + HypixelBedwarsUpgradeDisplay.this.method5("traps", new Object[0]), value2, value3, flag8);
            value3 += number7;
            if (HypixelBedwarsUpgradeDisplay.this.activeTraps.isEmpty()) {
               HypixelBedwarsUpgradeDisplay.this.trapsTextColor
                  .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, HypixelBedwarsUpgradeDisplay.this.method5("none", new Object[0]), value2, value3, flag8);
               value3 += number7;
            } else {
               for (String text20 : HypixelBedwarsUpgradeDisplay.this.activeTraps) {
                  HypixelBedwarsUpgradeDisplay.this.trapsTextColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_45, text20 + " Trap", value2, value3, flag8);
                  value3 += number7;
               }
            }
         }

         this.method17(140.0F, Math.round(value3 - value6 + 1.0F));
      }

      public boolean shouldRender(boolean flag1) {
         HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)HypixelBedwarsUpgradeDisplay.this.getAll(ModTraits.field16)).method1();
         if (!hypixelbedwars2.method35()) {
            this.method17(0.0F, 0.0F);
            return false;
         } else if (flag1) {
            return (Boolean)HypixelBedwarsUpgradeDisplay.this.showUpgrades.get() || (Boolean)HypixelBedwarsUpgradeDisplay.this.showTraps.get();
         } else if (!hypixelbedwars2.method21() || HypixelLocationListener.field7.method7().method2()) {
            return false;
         } else {
            return HypixelBedwarsUpgradeDisplay.this.hideWhenNoUpgrades.get()
                  && !HypixelBedwarsUpgradeDisplay.this.registry.method10()
                  && HypixelBedwarsUpgradeDisplay.this.activeTraps.isEmpty()
               ? false
               : (Boolean)HypixelBedwarsUpgradeDisplay.this.showUpgrades.get() || (Boolean)HypixelBedwarsUpgradeDisplay.this.showTraps.get();
         }
      }

      private String render(BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1, int index2) {
         String text3 = HypixelBedwarsUpgradeDisplay.this.effectNames.get()
            ? HypixelBedwarsUpgradeDisplay.this.method5(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1.field3, new Object[0])
            : hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1.field1;
         if (hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1.method1()) {
            if (index2 >= HypixelBedwarsUpgradeDisplay.forgeTiers.size()) {
               index2 = HypixelBedwarsUpgradeDisplay.forgeTiers.size() - 1;
            }

            return HypixelBedwarsUpgradeDisplay.forgeTiers.get(index2) + " " + text3;
         } else {
            return hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1.field2 == 1
               ? text3
               : text3 + " " + (HypixelBedwarsUpgradeDisplay.this.romanNumeralsOption.get() ? (Serializable)HypixelBedwarsUpgradeDisplay.romanNumerals.get(index2) : index2);
         }
      }
   }

   private static class BedwarsUpgradeRegistry {
      private final Map<String, BedwarsUpgrade> upgrades = new HashMap<>();
      private final Map<BedwarsUpgrade, Integer> levels = new LinkedHashMap<>();
      private String name;
      private String id;
      private int[] soloDoublesCosts;
      private int[] teamsCosts;

      private BedwarsUpgradeRegistry() {
      }

      public HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry save(String text1) {
         this.name = text1;
         return this;
      }

      public HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry registerOptions(String text1) {
         this.id = text1;
         return this;
      }

      public HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry render(int... items1) {
         this.soloDoublesCosts = items1;
         return this;
      }

      public HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry shouldRender(int... items1) {
         this.teamsCosts = items1;
         return this;
      }

      public HypixelBedwarsUpgradeDisplay.BedwarsUpgradeRegistry updateGameMode() {
         this.upgrades
            .put(
               this.name,
               new BedwarsUpgrade(this.name, this.soloDoublesCosts.length, this.id, this.soloDoublesCosts, this.teamsCosts)
            );
         return this;
      }

      public boolean onChatMessage(String text1) {
         return this.upgrades.containsKey(text1);
      }

      public Collection<BedwarsUpgrade> getAll() {
         return this.upgrades.values();
      }

      public Set<BedwarsUpgrade> getActive() {
         return this.levels.keySet();
      }

      public void clearActive() {
         this.levels.clear();
      }

      public boolean hasActive() {
         return !this.levels.isEmpty();
      }

      public void setLevel(String text1, @Nullable String text2) {
         BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3 = this.upgrades.get(text1);
         if (hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3 == null) {
            LunarLogger.method5("Invalid bedwars upgrade " + text1, new Object[0]);
         } else {
            int number4;
            if (text2 != null && !text2.isEmpty()) {
               number4 = (Integer)HypixelBedwarsUpgradeDisplay.romanNumerals.inverse().get(text2);
            } else {
               number4 = this.getLevel(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3) + 1;
            }

            this.levels.put(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data3, number4);
         }
      }

      public int getLevel(BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1) {
         return !this.levels.containsKey(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1) ? 0 : this.levels.get(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data1);
      }

      public void unlockAll() {
         for (BedwarsUpgrade hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data2 : this.getAll()) {
            if (!"DeadShot".equals(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data2.field1)) {
               this.levels.put(hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data2, hypixelbedwarsupgradedisplaychild$hricoroooccocorocrhhcrrircoico$data2.field2);
            }
         }
      }
   }

   private enum Type {
      SOLO,
      DOUBLES,
      OTHER;

      Type() {
      }
   }
}

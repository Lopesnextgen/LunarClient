package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.scoreboard.ScoreBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import software.bernie.geckolib3.core.util.Color;

public class HypixelBedwarsTeamDisplay extends AbstractFeature {
   private final ToggleOption flip = (ToggleOption)OptionFactory.method7("flip").method31();
   private final EnumOption<HypixelBedwarsTeamDisplay.Type> displayMode = (EnumOption<HypixelBedwarsTeamDisplay.Type>)OptionFactory.method10(
         "displayMode", HypixelBedwarsTeamDisplay.Type.MINIMAL
      )
      .method31();
   private final ToggleOption ignoreCastlesMode = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "ignoreCastlesMode"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showName = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showName"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption dynamicHealthColor = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "dynamicHealthColor"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showHearts = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showHearts"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
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
   private final ColorOption titleText = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "titleText"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption healthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "healthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final ColorOption lowHealthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lowHealthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5636096))
      .method31();
   private final ColorOption mediumHealthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "mediumHealthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption highHealthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "highHealthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption highestHealthColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "highestHealthColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final List<HypixelBedwarsTeamDisplay.BedwarsTeamMember> previewMembers = Arrays.asList(
      new HypixelBedwarsTeamDisplay.BedwarsTeamMember("Teammate_1", UUID.randomUUID(), true),
      new HypixelBedwarsTeamDisplay.BedwarsTeamMember("Teammate_2", UUID.randomUUID(), true),
      new HypixelBedwarsTeamDisplay.BedwarsTeamMember("Teammate_3", UUID.randomUUID(), true)
   );
   private int maxHealth = 20;
   private String lastWorld = null;
   private List<HypixelBedwarsTeamDisplay.BedwarsTeamMember> members = new ArrayList<>();
   private final StringBuilder healthBarBuilder = new StringBuilder();

   public HypixelBedwarsTeamDisplay(HypixelBedwars hypixelbedwars1) {
      super(true);
      this.shouldRender(ModTraits.field18, ConfigRangeBuilder::method16);
      this.shouldRender(ModTraits.field16, ChildModBinding.method3(hypixelbedwars1));
      this.shouldRender(ModTraits.field1, new HypixelBedwarsTeamDisplay.Data());
      this.handle(EventEntityJoinWorld.class, arg1x -> {
         if (Objects.equals(arg1x.field1, Ref.method3().bridge$getPlayer())) {
            this.maxHealth = 20;
         }
      });
      this.handle(EventTick.class, this::onTick);
   }

   public String getId() {
      return "HYPIXEL_BEDWARS_TEAM_DISPLAY_CHILD";
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.displayMode});
            arg1x.method9(new ClientOption[]{this.showName})
               .method3(() -> this.displayMode.get() != HypixelBedwarsTeamDisplay.Type.MINIMAL);
            arg1x.method9(new ClientOption[]{this.dynamicHealthColor, this.showHearts})
               .method3(() -> this.displayMode.get() == HypixelBedwarsTeamDisplay.Type.NORMAL);
            arg1x.method9(new ClientOption[]{this.textShadow, this.background, this.border});
            arg1x.method9(new ClientOption[]{this.flip})
               .method3(() -> this.displayMode.get() == HypixelBedwarsTeamDisplay.Type.MINIMAL);
         }
      );
      ((SettingsSectionImpl)lightingextension231.method7(
            SettingsPage.COLOR,
            arg1x -> {
               arg1x.method9(new ClientOption[]{this.titleText, this.textColor});
               arg1x.method9(new ClientOption[]{this.healthColor})
                  .method3(() -> this.displayMode.get() == HypixelBedwarsTeamDisplay.Type.NORMAL || (Boolean)this.dynamicHealthColor.get());
               arg1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !(Boolean)this.background.get());
               arg1x.method9(new ClientOption[]{this.borderColor, this.borderThickness}).method3(() -> !(Boolean)this.border.get());
               arg1x.method9(new ClientOption[]{this.lowHealthColor, this.mediumHealthColor, this.highHealthColor, this.highestHealthColor})
                  .method3(() -> this.displayMode.get() == HypixelBedwarsTeamDisplay.Type.NORMAL || !(Boolean)this.dynamicHealthColor.get());
            }
         ))
         .method2(() -> !(Boolean)this.background.get() && !(Boolean)this.border.get());
   }

   private void onTick(EventTick highlightimpl21) {
      HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.renderHealthText(ModTraits.field16)).method1();
      if (hypixelbedwars2.method35() && Ref.method3().bridge$getWorld() != null) {
         Bridge5Extension_5 bridge5extension_53 = Ref.method3().bridge$getPlayer();
         if (bridge5extension_53 != null) {
            this.maxHealth = Math.max((int)bridge5extension_53.bridge$getMaxHealth(), this.maxHealth);
            HypixelLocation rewindhandlers24 = HypixelLocationListener.field7.method7();
            if (rewindhandlers24 != null && rewindhandlers24.field3 != null && !rewindhandlers24.field3.contains("_ONE")) {
               if (!Objects.equals(this.lastWorld, rewindhandlers24.field4)) {
                  this.clear();
                  this.lastWorld = rewindhandlers24.field4;
               }

               NetHandlerPlayClientBridge bridgeextension_75 = Ref.method3().bridge$getClientPacketListener();
               if (bridgeextension_75 != null) {
                  ScoreboardBridge lighting46 = Ref.method3().bridge$getWorld().bridge$getScoreboard();
                  List list7 = bridgeextension_75.bridge$getPlayerInfoMap();
                  if (lighting46 != null && list7 != null) {
                     com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting8 = lighting46.bridge$getObjectiveInDisplaySlot(0);
                     com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting9 = lighting46.bridge$getObjectiveInDisplaySlot(1);
                     if (lighting8 != null && lighting9 != null) {
                        for (ScoreBridge lighting211 : lighting46.bridge$getSortedScores(lighting9)) {
                           ScorePlayerTeamBridge lighting312 = lighting46.bridge$getPlayersTeam(lighting211.bridge$getPlayerName());
                           String text13 = TextBridge.getTextContent(
                              lighting312 == null ? lighting211.bridge$getPlayerComponent() : lighting312.bridge$formatString(lighting211.bridge$getPlayerComponent())
                           );
                           if (text13.endsWith("YOU")) {
                              char character14 = ChatFormatting.getTextWithoutFormattingCodes(text13).charAt(0);
                              this.members.clear();

                              for (PlayerInfoBridge bridge2_3316 : list7) {
                                 UUID uuid17 = bridge2_3316.bridge$getGameProfile().getId();
                                 if (!uuid17.equals(bridge5extension_53.bridge$getUniqueID())) {
                                    String text18 = bridge2_3316.bridge$getGameProfile().getName();
                                    ScorePlayerTeamBridge lighting319 = lighting46.bridge$getPlayersTeam(text18);
                                    ScoreBridge lighting220 = lighting46.bridge$getValueFromObjective(text18, lighting8);
                                    if (lighting220 != null && lighting319 != null) {
                                       String text21 = TextBridge.getTextContent(lighting319.bridge$formatString(Component.text(text18)));
                                       text21 = ChatFormatting.getTextWithoutFormattingCodes(text21);
                                       if (text21.charAt(0) == character14) {
                                          this.members.add(new HypixelBedwarsTeamDisplay.BedwarsTeamMember(text18, uuid17, false));
                                       }
                                    }
                                 }
                              }

                              if (!this.members.isEmpty()) {
                                 this.members.sort((arg0, arg1x) -> Integer.compare(arg1x.field1.length(), arg0.field1.length()));
                              }
                              break;
                           }
                        }
                     }
                  }
               }
            } else {
               this.clear();
            }
         }
      } else {
         this.clear();
      }
   }

   private void clear() {
      this.members.clear();
      this.lastWorld = null;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      public boolean shouldRender(boolean flag1) {
         HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)HypixelBedwarsTeamDisplay.this.renderHealthText(ModTraits.field16)).method1();
         if (!hypixelbedwars2.method35()) {
            this.renderMinimalMode(0.0F, 0.0F);
            return false;
         }

         if (flag1) {
            return true;
         }

         boolean flag3 = hypixelbedwars2.method21() && !HypixelBedwarsTeamDisplay.this.members.isEmpty();
         if (flag3 && (Boolean)HypixelBedwarsTeamDisplay.this.ignoreCastlesMode.get()) {
            HypixelLocation rewindhandlers24 = HypixelLocationListener.field7.method7();
            if (rewindhandlers24 != null && rewindhandlers24.field3 != null && rewindhandlers24.field3.contains("_CASTLE")) {
               flag3 = false;
            }
         }

         return flag3;
      }

      public void onTick(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (flag4 && !this.shouldRender(false)) {
            List list5 = HypixelBedwarsTeamDisplay.this.members;

            try {
               HypixelBedwarsTeamDisplay.this.members = HypixelBedwarsTeamDisplay.this.previewMembers;
               this.onTick(highlightimpl1.method2(), value2, value3);
            } finally {
               HypixelBedwarsTeamDisplay.this.members = list5;
            }
         } else {
            this.onTick(highlightimpl1.method2(), value2, value3);
         }
      }

      private void onTick(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         if ((Boolean)HypixelBedwarsTeamDisplay.this.background.get()) {
            HypixelBedwarsTeamDisplay.this.backgroundColor.method11(mixinhelper_41, value2, value3, this.getWidth(), this.getHeight());
         }

         if ((Boolean)HypixelBedwarsTeamDisplay.this.border.get()) {
            HypixelBedwarsTeamDisplay.this.borderColor
               .method11(mixinhelper_41, this, value2, value3, this.getWidth(), this.getHeight(), (Float)HypixelBedwarsTeamDisplay.this.borderThickness.get());
         }

         float value4 = value3;
         value2 += 3.0F;
         value3 += 3.0F;
         boolean flag5 = (Boolean)HypixelBedwarsTeamDisplay.this.textShadow.get();
         HypixelBedwarsTeamDisplay.this.titleText
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "§l" + HypixelBedwarsTeamDisplay.this.onTick("team", new Object[0]), value2, value3, flag5);
         value3 += 11.0F;
         HypixelBedwarsTeamDisplay.Type type6 = (HypixelBedwarsTeamDisplay.Type)HypixelBedwarsTeamDisplay.this.displayMode.get();
         float value7 = 0.0F;
         if (type6 == HypixelBedwarsTeamDisplay.Type.TEXT) {
            int number8 = Ref.method10().method19() + 1;

            for (HypixelBedwarsTeamDisplay.BedwarsTeamMember data210 : HypixelBedwarsTeamDisplay.this.members) {
               float value18;
               if ((Boolean)HypixelBedwarsTeamDisplay.this.flip.get()) {
                  if (data210.method2().isPresent()) {
                     int number12 = data210.method2().get();
                     this.renderHealthText(mixinhelper_41, number12, value2, value3, flag5);
                     value18 = 31.0F;
                  } else {
                     HypixelBedwarsTeamDisplay.this.healthColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "N/A", value2, value3, flag5);
                     value18 = 22.0F;
                  }

                  value2 += value18;
                  HypixelBedwarsTeamDisplay.this.textColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, data210.field1, value2, value3, flag5);
                  value2 -= value18;
                  value18 += Ref.method10().bridge$getStringWidth(data210.field1);
               } else {
                  HypixelBedwarsTeamDisplay.this.textColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, data210.field1, value2, value3, flag5);
                  value18 = Ref.method10().bridge$getStringWidth(data210.field1 + " ");
                  value2 += value18;
                  if (data210.method2().isPresent()) {
                     int number20 = data210.method2().get();
                     this.renderHealthText(mixinhelper_41, number20, value2, value3, flag5);
                     value2 -= value18;
                     value18 += 28.0F;
                  } else {
                     HypixelBedwarsTeamDisplay.this.healthColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "N/A", value2, value3, flag5);
                     value2 -= value18;
                     value18 += 19.0F;
                  }
               }

               value3 += number8;
               value7 = Math.max(value7, value18);
            }
         }

         if (type6 == HypixelBedwarsTeamDisplay.Type.NORMAL) {
            value7 = 123.0F;
            value3 = this.shouldRender(mixinhelper_41, value2, value3);
         }

         if (type6 == HypixelBedwarsTeamDisplay.Type.MINIMAL) {
            value7 = HypixelBedwarsTeamDisplay.this.showName.get() ? 126.0F : 28.0F;
            value3 = this.renderMinimalMode(mixinhelper_41, value2, value3);
         }

         this.renderMinimalMode(value7 + 6.0F, Math.round(value3 - value4 + 1.0F));
      }

      private float shouldRender(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         for (HypixelBedwarsTeamDisplay.BedwarsTeamMember data25 : HypixelBedwarsTeamDisplay.this.members) {
            mixinhelper_41.push();
            mixinhelper_41.method38(value2, value3, 0.0F);
            if (!(Boolean)HypixelBedwarsTeamDisplay.this.flip.get()) {
               this.renderHead(mixinhelper_41, data25);
               mixinhelper_41.method38(27.0F, 0.0F, 0.0F);
            }

            HypixelBedwarsTeamDisplay.this.textColor
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, data25.field1, 0.0F, 3.0F, (Boolean)HypixelBedwarsTeamDisplay.this.textShadow.get());
            if (!data25.method2().isPresent()) {
               HypixelBedwarsTeamDisplay.this.textColor
                  .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "N/A", 1.0F, 14.0F, (Boolean)HypixelBedwarsTeamDisplay.this.textShadow.get());
            } else {
               int number6 = data25.method2().get();
               double value7 = Math.floor(Math.min(number6, HypixelBedwarsTeamDisplay.this.maxHealth) / 2.0);
               double value9 = Math.floor(Math.max(0.0, number6 - HypixelBedwarsTeamDisplay.this.maxHealth) / 2.0);
               HypixelBedwarsTeamDisplay.this.healthBarBuilder.setLength(0);

               for (int index11 = 0; index11 < value7; index11++) {
                  HypixelBedwarsTeamDisplay.this.healthBarBuilder.append('❤');
               }

               mixinhelper_41.method18(Ref.method10(), "❤❤❤❤❤❤❤❤❤❤", 0, 12, -12632257, false);
               mixinhelper_41.method18(Ref.method10(), HypixelBedwarsTeamDisplay.this.healthBarBuilder.toString(), 0, 12, -58854, true);
               if (value9 > 0.0) {
                  value3 += 2.0F;
                  HypixelBedwarsTeamDisplay.this.healthBarBuilder.setLength(0);

                  for (int index12 = 0; index12 < value9; index12++) {
                     HypixelBedwarsTeamDisplay.this.healthBarBuilder.append('❤');
                  }

                  mixinhelper_41.method18(Ref.method10(), HypixelBedwarsTeamDisplay.this.healthBarBuilder.toString(), 0, 20, -75494, true);
               }
            }

            if ((Boolean)HypixelBedwarsTeamDisplay.this.flip.get()) {
               mixinhelper_41.method38(99.0F, 0.0F, 0.0F);
               this.renderHead(mixinhelper_41, data25);
            }

            mixinhelper_41.pop();
            value3 += 26.0F;
         }

         return value3;
      }

      private float renderMinimalMode(MixinHelper_4 mixinhelper_41, float value2, float value3) {
         boolean flag4 = (Boolean)HypixelBedwarsTeamDisplay.this.textShadow.get();
         value2 += 3.0F;

         for (HypixelBedwarsTeamDisplay.BedwarsTeamMember data26 : HypixelBedwarsTeamDisplay.this.members) {
            mixinhelper_41.push();
            mixinhelper_41.method38(value2, value3, 0.0F);
            mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> arg0x.method1(1.0F, 1.0F, 1.0F, 1.0F)));
            this.renderHead(mixinhelper_41, data26);
            mixinhelper_41.push();
            mixinhelper_41.method40(3.0F, 3.0F);
            LcuiScreen.method94(mixinhelper_41, -1.0F, 0.0F, 1.0F, 8.0F, -2139062017);
            mixinhelper_41.pop();
            if ((Boolean)HypixelBedwarsTeamDisplay.this.showName.get()) {
               HypixelBedwarsTeamDisplay.this.textColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, data26.field1, 28.0F, 9.5F, flag4);
            }

            if (data26.method2().isPresent()) {
               int number7 = data26.method2().get();
               double value8 = (double)number7 / Math.max(HypixelBedwarsTeamDisplay.this.maxHealth, number7);
               int number10;
               if ((Boolean)HypixelBedwarsTeamDisplay.this.dynamicHealthColor.get()) {
                  number10 = this.getHealthColor(number7);
               } else {
                  float value11 = (
                        1.0F - Math.max(0.0F, ((float)HypixelBedwarsTeamDisplay.this.maxHealth - number7) / HypixelBedwarsTeamDisplay.this.maxHealth)
                     )
                     / 3.0F;
                  number10 = Color.HSBtoRGB(value11, 1.0F, 1.0F);
               }

               mixinhelper_41.push();
               mixinhelper_41.method40(3.0F, 3.0F);
               LcuiScreen.method94(mixinhelper_41, -1.0F, (float)(8.0 - 8.0 * value8), 1.0F, (float)(8.0 * value8), number10);
               mixinhelper_41.pop();
               this.renderHealthText(mixinhelper_41, number7, 11.0F, 26.0F, flag4);
            } else {
               HypixelBedwarsTeamDisplay.this.healthColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, "N/A", 3.0F, 26.0F, flag4);
            }

            mixinhelper_41.pop();
            value3 += 36.0F;
         }

         return value3;
      }

      private void renderHead(MixinHelper_4 mixinhelper_41, HypixelBedwarsTeamDisplay.BedwarsTeamMember data22) {
         mixinhelper_41.push();
         mixinhelper_41.scale(3.0F, 3.0F, 1.0F);
         LcuiScreen.method46(mixinhelper_41, data22.getSkinLocation(), 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, -1);
         if (data22.method1().isPresent() && data22.method1().get().bridge$showHat()) {
            LcuiScreen.method46(mixinhelper_41, data22.getSkinLocation(), 0.0F, 0.0F, 40.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, -1);
         }

         mixinhelper_41.pop();
      }

      private void renderHealthText(MixinHelper_4 mixinhelper_41, int number2, float value3, float value4, boolean flag5) {
         int number6 = this.getHealthColor(number2);
         HypixelBedwarsTeamDisplay.Type type7 = (HypixelBedwarsTeamDisplay.Type)HypixelBedwarsTeamDisplay.this.displayMode.get();
         boolean flag8 = (Boolean)HypixelBedwarsTeamDisplay.this.showHearts.get();
         String text9 = flag8 ? String.valueOf((int)(number2 / 2.0 * 10.0) / 10.0) : String.valueOf(number2);
         float value10 = Ref.method10().bridge$getStringWidth(text9);
         if (type7 == HypixelBedwarsTeamDisplay.Type.TEXT) {
            mixinhelper_41.method19(Ref.method10(), text9, value3, value4, number6, flag5);
            if (flag8) {
               mixinhelper_41.method19(Ref.method10(), "❤", value3 + value10 + 2.0F, value4, -58854, flag5);
            } else {
               mixinhelper_41.method19(Ref.method10(), "HP", value3 + 16.0F, value4, number6, flag5);
            }
         }

         if (type7 == HypixelBedwarsTeamDisplay.Type.MINIMAL) {
            if (!flag8) {
               text9 = text9 + "/" + HypixelBedwarsTeamDisplay.this.maxHealth;
               value10 = Ref.method10().bridge$getStringWidth(text9);
            } else {
               value10 += Ref.method10().bridge$getStringWidth("❤");
            }

            mixinhelper_41.method19(Ref.method10(), text9, value3 - value10 / 2.0F, value4, number6, flag5);
            value3 += Ref.method10().bridge$getStringWidth(text9) - value10 / 2.0F;
            if (flag8) {
               mixinhelper_41.method19(Ref.method10(), "❤", value3 - 10.0F, value4, -58854, flag5);
            }
         }
      }

      private int getHealthColor(int number1) {
         int number2 = HypixelBedwarsTeamDisplay.this.healthColor.method13();
         if ((Boolean)HypixelBedwarsTeamDisplay.this.dynamicHealthColor.get()) {
            if (number1 <= 5) {
               number2 = HypixelBedwarsTeamDisplay.this.lowHealthColor.method13();
            } else if (number1 <= 10) {
               number2 = HypixelBedwarsTeamDisplay.this.mediumHealthColor.method13();
            } else if (number1 <= 15) {
               number2 = HypixelBedwarsTeamDisplay.this.highHealthColor.method13();
            } else {
               number2 = HypixelBedwarsTeamDisplay.this.highestHealthColor.method13();
            }
         }

         return number2;
      }
   }

   private class BedwarsTeamMember {
      private final String name;
      private final UUID uuid;
      private final boolean bot;
      private static final ResourceLocationBridge field4 = ResourceLocationBridge.create("minecraft", "textures/skins/wide/steve.png");

      private BedwarsTeamMember(String text1, UUID uuid2, boolean flag3) {
         this.name = text1;
         this.uuid = uuid2;
         this.bot = flag3;
      }

      public Optional<Bridge6_10> getEntity() {
         return this.bot ? Optional.empty() : Ref.method3().bridge$getWorld().bridge$getPlayerByUniqueId(this.uuid);
      }

      public Optional<Integer> registerOptions() {
         if (this.bot) {
            return Optional.of(1 + (int)(Math.abs(this.uuid.getLeastSignificantBits() ^ this.uuid.getMostSignificantBits()) % 20L));
         }

         ScoreboardBridge lighting41 = Ref.method3().bridge$getWorld().bridge$getScoreBoard();
         if (lighting41 != null) {
            com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting2 = lighting41.bridge$getObjectiveInDisplaySlot(0);
            if (lighting2 != null) {
               ScoreBridge lighting23 = lighting2.bridge$getScoreboard().bridge$getValueFromObjective(this.name, lighting2);
               if (lighting23 != null) {
                  return Optional.of(lighting23.bridge$getScorePoints());
               }
            }
         }

         return Optional.empty();
      }

      public ResourceLocationBridge getSkinLocation() {
         NetHandlerPlayClientBridge bridgeextension_71 = Ref.method3().bridge$getClientPacketListener();
         if (bridgeextension_71 != null && Ref.MC_VERSION != 0) {
            List list2 = bridgeextension_71.bridge$getPlayerInfoMap();
            return !this.bot && list2 != null
               ? list2.stream()
                  .filter(arg1x -> arg1x.bridge$getGameProfile().getId().equals(this.uuid))
                  .findFirst()
                  .<ResourceLocationBridge>map(PlayerInfoBridge::bridge$getLocationSkin)
                  .orElse(field4)
               : field4;
         } else {
            return field4;
         }
      }

      public String name() {
         return this.name;
      }

      public UUID id() {
         return this.uuid;
      }

      public boolean onTick() {
         return this.bot;
      }
   }

   private enum Type implements OptionEnumValue {
      NORMAL("normal"),
      TEXT("text"),
      MINIMAL("minimal");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}

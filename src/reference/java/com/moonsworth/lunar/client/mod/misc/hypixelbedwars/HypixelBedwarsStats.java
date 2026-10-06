package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars.Bedwars;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars.BedwarsUpdater;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.ui.hud.HudRowLayout;
import com.moonsworth.lunar.client.ui.hud.HudRow;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Nullable;

public class HypixelBedwarsStats extends AbstractFeature {
   private final ToggleOption textShadow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final ToggleOption autoAlign = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final EnumOption<HudRowAlignment> alignment = (EnumOption<HudRowAlignment>)OptionFactory.method10("alignment", HudRowAlignment.LEFT)
      .method31();
   private final ToggleOption showGame = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showGame").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption bedwarsSession = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bedwarsSession").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption total = (ToggleOption)OptionFactory.method7("total").method31();
   private final ToggleOption winstreak = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("winstreak").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption sessionGames = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("sessionGames").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption sessionTime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("sessionTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption gameTime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("gameTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption avgGameTime = (ToggleOption)OptionFactory.method7("avgGameTime").method31();
   private final ToggleOption finals = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("finals").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption finalsRatio = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("finalsRatio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption bedwarsBeds = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bedwarsBeds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption bedsRatio = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bedsRatio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption kills = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("kills").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption killsRatio = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("killsRatio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption wins = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("wins").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption winsRatio = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("winsRatio").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption headingColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "headingColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption statColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "statColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption numberColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "numberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption dividerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "dividerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8355712))
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
   private final Bedwars updater;
   private long sessionStart = 0L;
   private boolean parseEnabled = true;
   private int bedwarsState = -1;
   private final Pattern winstreakPattern = Pattern.compile("^Current Winstreak: ([0-9,]+)$");

   public HypixelBedwarsStats(HypixelBedwars hypixelbedwars1) {
      super(true);
      this.updater = new BedwarsUpdater();
      this.registerOptions(ModTraits.field18, ConfigRangeBuilder::method16);
      this.registerOptions(ModTraits.field16, ChildModBinding.method3(hypixelbedwars1));
      this.registerOptions(ModTraits.field1, this.createElement());
      this.handle(EventServerJoin.class, this::onServerJoin);
      this.handle(EventSecond.class, this::onSecond);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage);
      this.handle(EventEntitySpawn.class, this::onEntitySpawn);
   }

   public String getId() {
      return "HYPIXEL_BEDWARS_STATS_CHILD";
   }

   public void resetSession() {
      this.updater.field3 = new com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars.Bedwars.Data();
      this.updater.method2();
      this.updater.field6 = 0;
      this.sessionStart = Ref.method3().bridge$getSystemTime();
   }

   private boolean isInBedwars() {
      if (this.bedwarsState >= 0) {
         return this.bedwarsState == 1;
      }

      if (this.mc != null && this.mc.bridge$getWorld() != null) {
         ScoreboardBridge lighting41 = this.mc.bridge$getWorld().bridge$getScoreboard();
         com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting2 = lighting41.bridge$getObjectiveInDisplaySlot(1);
         if (lighting2 != null) {
            String text3 = TextBridge.getTextContent(TextBridge.asAdventure(lighting2.bridge$getDisplayName())).trim();
            boolean flag4 = text3.equalsIgnoreCase("BED WARS");
            this.bedwarsState = flag4 ? 1 : 0;
            return flag4;
         }
      }

      this.parseEnabled = false;
      this.bedwarsState = 0;
      return false;
   }

   public void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      this.updater.method12(data1);
   }

   public void onServerJoin(EventServerJoin highlightimpl161) {
      this.updater.method6();
      this.bedwarsState = -1;
      this.parseEnabled = true;
   }

   public void onEntitySpawn(EventEntitySpawn highlightimpl6_21) {
      if (this.parseEnabled && highlightimpl6_21.field1 instanceof EntityArmorStandBridge) {
         HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.onLocationUpdate(ModTraits.field16)).method1();
         if ((HypixelLocationListener.field7.method7().method2() || hypixelbedwars2.method36()) && (hypixelbedwars2.method35() || this.isInBedwars())) {
            EntityLivingBridge bridgeextension2_53 = (EntityLivingBridge)highlightimpl6_21.field1;
            if (!(bridgeextension2_53.bridge$getDisplayNameComponent() instanceof TextComponent text5)) {
               return;
            }

            String text6 = TextBridge.getTextContent(text5);
            Matcher matcher7 = this.winstreakPattern.matcher(text6);
            if (matcher7.find()) {
               try {
                  String text8 = matcher7.group(1);
                  this.updater.field5 = Integer.parseInt(text8.replace(",", ""));
                  this.parseEnabled = false;
               } catch (NumberFormatException numberformatexception9) {
               }
            }
         }
      }
   }

   public void onSecond(EventSecond highlightimpl41) {
      this.updater.method11();
   }

   public void onLocationUpdate(@Nullable HypixelLocation rewindhandlers21) {
      HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.onLocationUpdate(ModTraits.field16)).method1();
      if (!hypixelbedwars2.method38()) {
         this.updater.method8();
      } else {
         HypixelLocation rewindhandlers23 = rewindhandlers21 == null ? HypixelLocationListener.field7.method7() : rewindhandlers21;
         if (rewindhandlers23 == null || rewindhandlers23.field5 != null || rewindhandlers23.field1.contains("lobby")) {
            this.updater.method7(null);
         } else if (hypixelbedwars2.method35()) {
            this.updater.method7(rewindhandlers23.field1);
         } else {
            this.updater.method8();
         }
      }
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.textShadow});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.background,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.border, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.borderThickness})
               )
            );
            arg1x.method9(new ClientOption[]{this.autoAlign});
            arg1x.method9(new ClientOption[]{this.alignment}).method3(this.autoAlign::get);
         }
      );
      lightingextension231.method1(
         "renderOptions",
         arg1x -> arg1x.method9(
            new ClientOption[]{this.showGame, this.bedwarsSession, this.winstreak, this.sessionGames, this.sessionTime, this.gameTime, this.avgGameTime}
         )
      );
      lightingextension231.method1(
         "extraRenderOptions",
         arg1x -> arg1x.method9(
            new ClientOption[]{this.finals, this.finalsRatio, this.bedwarsBeds, this.bedsRatio, this.kills, this.killsRatio, this.wins, this.winsRatio}
         )
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.headingColor, this.statColor, this.numberColor, this.dividerColor});
         arg1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !(Boolean)this.background.get());
         arg1x.method9(new ClientOption[]{this.borderColor}).method3(() -> !(Boolean)this.border.get());
      });
   }

   private HudRowElement createElement() {
      return new HudRowElement(0.0F, 0.0F, HudAnchor.TOP_RIGHT) {
         public void registerOptions(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, List<HudRow> list5) {
            this.registerOptions(
               mixinhelper_41,
               value2,
               value3,
               (Boolean)HypixelBedwarsStats.this.autoAlign.get(),
               (HudRowAlignment)HypixelBedwarsStats.this.alignment.get(),
               (Boolean)HypixelBedwarsStats.this.background.get(),
               HypixelBedwarsStats.this.backgroundColor,
               (Boolean)HypixelBedwarsStats.this.border.get(),
               (Float)HypixelBedwarsStats.this.borderThickness.get(),
               HypixelBedwarsStats.this.borderColor
            );
         }

         private float registerOptions(int number1, int number2) {
            return (float)number1 / Math.max(1, number2);
         }

         private void onChatMessage(List<HudRow> list1, String text2, String text3, int number4, int number5, boolean flag6, boolean flag7, int number8) {
            if (flag6 && flag7) {
               list1.add(
                  HudRowLayout.method4(
                     number8,
                     new HudRow[]{
                        HudRowLayout.method5(text2 + ": ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(number4 + "", HypixelBedwarsStats.this.numberColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(" / ", HypixelBedwarsStats.this.dividerColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(text3 + ": ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(
                           String.format("%.2f", this.registerOptions(number4, number5)),
                           HypixelBedwarsStats.this.numberColor,
                           (Boolean)HypixelBedwarsStats.this.textShadow.get()
                        )
                     }
                  )
               );
            } else if (flag6) {
               list1.add(
                  HudRowLayout.method4(
                     number8,
                     new HudRow[]{
                        HudRowLayout.method5(text2 + ": ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(number4 + "", HypixelBedwarsStats.this.numberColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())
                     }
                  )
               );
            } else if (flag7) {
               list1.add(
                  HudRowLayout.method4(
                     number8,
                     new HudRow[]{
                        HudRowLayout.method5(text3 + ": ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                        HudRowLayout.method5(
                           String.format("%.2f", this.registerOptions(number4, number5)),
                           HypixelBedwarsStats.this.numberColor,
                           (Boolean)HypixelBedwarsStats.this.textShadow.get()
                        )
                     }
                  )
               );
            }
         }

         private void onServerJoin(
            String text1, int number2, int number3, com.moonsworth.lunar.client.framework.feature.hypixelbedwars.bedwars.Bedwars.Data data4, List<HudRow> list5
         ) {
            list5.add(
               HudRowLayout.method4(
                  number2, new HudRow[]{HudRowLayout.method5("§l" + text1, HypixelBedwarsStats.this.headingColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())}
               )
            );
            this.onChatMessage(
               list5,
               "Finals",
               "FKDR",
               data4.method1(),
               data4.method5(),
               (Boolean)HypixelBedwarsStats.this.finals.get(),
               (Boolean)HypixelBedwarsStats.this.finalsRatio.get(),
               number3
            );
            this.onChatMessage(
               list5,
               "Beds",
               "BBLR",
               data4.method3(),
               data4.method7(),
               (Boolean)HypixelBedwarsStats.this.bedwarsBeds.get(),
               (Boolean)HypixelBedwarsStats.this.bedsRatio.get(),
               number3
            );
            this.onChatMessage(
               list5,
               "Kills",
               "KDR",
               data4.method2(),
               data4.method6(),
               (Boolean)HypixelBedwarsStats.this.kills.get(),
               (Boolean)HypixelBedwarsStats.this.killsRatio.get(),
               number3
            );
            this.onChatMessage(
               list5,
               "Wins",
               "WLR",
               data4.method4(),
               data4.method8(),
               (Boolean)HypixelBedwarsStats.this.wins.get(),
               (Boolean)HypixelBedwarsStats.this.winsRatio.get(),
               number3
            );
         }

         @Nullable
         protected List<HudRow> onEntitySpawn(boolean flag1) {
            long number2 = Ref.method3().bridge$getSystemTime();
            byte number4 = 0;
            byte number5 = 0;
            byte number6 = 0;
            switch (HudRowLayout.method3(
               (Boolean)HypixelBedwarsStats.this.autoAlign.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (HudRowAlignment)HypixelBedwarsStats.this.alignment.get()
            )) {
               case LEFT:
                  number6 = 4;
                  break;
               case RIGHT:
                  number5 = 4;
            }

            ArrayList list7 = new ArrayList();
            boolean flag8 = (Boolean)HypixelBedwarsStats.this.finals.get()
               || (Boolean)HypixelBedwarsStats.this.finalsRatio.get()
               || (Boolean)HypixelBedwarsStats.this.kills.get()
               || (Boolean)HypixelBedwarsStats.this.killsRatio.get()
               || (Boolean)HypixelBedwarsStats.this.wins.get()
               || (Boolean)HypixelBedwarsStats.this.winsRatio.get();
            if ((Boolean)HypixelBedwarsStats.this.showGame.get()
               && ((Boolean)HypixelBedwarsStats.this.finals.get() || (Boolean)HypixelBedwarsStats.this.kills.get())) {
               list7.add(
                  HudRowLayout.method4(
                     number5, new HudRow[]{HudRowLayout.method5("§lGame", HypixelBedwarsStats.this.headingColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())}
                  )
               );
               if ((Boolean)HypixelBedwarsStats.this.finals.get()) {
                  list7.add(
                     HudRowLayout.method4(
                        number6,
                        new HudRow[]{
                           HudRowLayout.method5("Finals: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.this.updater.field2.method1() + "",
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.bedwarsBeds.get()) {
                  list7.add(
                     HudRowLayout.method4(
                        number6,
                        new HudRow[]{
                           HudRowLayout.method5("Beds: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.this.updater.field2.method3() + "",
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.kills.get()) {
                  list7.add(
                     HudRowLayout.method4(
                        number6,
                        new HudRow[]{
                           HudRowLayout.method5("Kills: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.this.updater.field2.method2() + "",
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }
            }

            if (flag8) {
               if ((Boolean)HypixelBedwarsStats.this.bedwarsSession.get()) {
                  if (number2 - HypixelBedwarsStats.this.sessionStart < 600L) {
                     list7.add(
                        HudRowLayout.method4(
                           number5,
                           new HudRow[]{
                              HudRowLayout.method5("§lSession", HypixelBedwarsStats.this.headingColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())
                           }
                        )
                     );
                     if (number2 - HypixelBedwarsStats.this.sessionStart < 200L) {
                        list7.add(
                           HudRowLayout.method4(
                              number6,
                              new HudRow[]{
                                 HudRowLayout.method5("Resetting.", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())
                              }
                           )
                        );
                     } else if (number2 - HypixelBedwarsStats.this.sessionStart < 400L) {
                        list7.add(
                           HudRowLayout.method4(
                              number6,
                              new HudRow[]{
                                 HudRowLayout.method5("Resetting..", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())
                              }
                           )
                        );
                     } else {
                        list7.add(
                           HudRowLayout.method4(
                              number6,
                              new HudRow[]{
                                 HudRowLayout.method5("Resetting...", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())
                              }
                           )
                        );
                     }

                     int index9 = -1;
                     if ((Boolean)HypixelBedwarsStats.this.finals.get() || (Boolean)HypixelBedwarsStats.this.finalsRatio.get()) {
                        index9++;
                     }

                     if ((Boolean)HypixelBedwarsStats.this.bedwarsBeds.get() || (Boolean)HypixelBedwarsStats.this.bedsRatio.get()) {
                        index9++;
                     }

                     if ((Boolean)HypixelBedwarsStats.this.kills.get() || (Boolean)HypixelBedwarsStats.this.killsRatio.get()) {
                        index9++;
                     }

                     if ((Boolean)HypixelBedwarsStats.this.wins.get() || (Boolean)HypixelBedwarsStats.this.winsRatio.get()) {
                        index9++;
                     }

                     for (int index10 = 0; index10 < index9; index10++) {
                        list7.add(
                           HudRowLayout.method4(
                              number6,
                              new HudRow[]{HudRowLayout.method5("", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())}
                           )
                        );
                     }
                  } else {
                     this.onServerJoin("Session", number5, number6, HypixelBedwarsStats.this.updater.field3, list7);
                  }
               }

               if ((Boolean)HypixelBedwarsStats.this.total.get()) {
                  this.onServerJoin("Total", number5, number6, HypixelBedwarsStats.this.updater.field4, list7);
               }
            }

            if ((Boolean)HypixelBedwarsStats.this.winstreak.get()
               || (Boolean)HypixelBedwarsStats.this.sessionGames.get()
               || (Boolean)HypixelBedwarsStats.this.sessionTime.get()
               || (Boolean)HypixelBedwarsStats.this.gameTime.get()
               || (Boolean)HypixelBedwarsStats.this.avgGameTime.get()) {
               list7.add(
                  HudRowLayout.method4(
                     0, new HudRow[]{HudRowLayout.method5("", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get())}
                  )
               );
               if ((Boolean)HypixelBedwarsStats.this.winstreak.get()) {
                  list7.add(
                     HudRowLayout.method4(
                        number4,
                        new HudRow[]{
                           HudRowLayout.method5("Winstreak: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.this.updater.field5 + "",
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.sessionGames.get()) {
                  list7.add(
                     HudRowLayout.method4(
                        number4,
                        new HudRow[]{
                           HudRowLayout.method5("Session Games: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.this.updater.field6 + "",
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.sessionTime.get()) {
                  long number11 = HypixelBedwarsStats.this.updater.method3(number2);
                  list7.add(
                     HudRowLayout.method4(
                        number4,
                        new HudRow[]{
                           HudRowLayout.method5("Session Time: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.formatDuration(number11),
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.gameTime.get()) {
                  long number12 = HypixelBedwarsStats.this.updater.method4(number2);
                  list7.add(
                     HudRowLayout.method4(
                        number4,
                        new HudRow[]{
                           HudRowLayout.method5("Game Time: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.formatDuration(number12),
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }

               if ((Boolean)HypixelBedwarsStats.this.avgGameTime.get()) {
                  long number13 = HypixelBedwarsStats.this.updater.method5(number2);
                  list7.add(
                     HudRowLayout.method4(
                        number4,
                        new HudRow[]{
                           HudRowLayout.method5("AVG Game Time: ", HypixelBedwarsStats.this.statColor, (Boolean)HypixelBedwarsStats.this.textShadow.get()),
                           HudRowLayout.method5(
                              HypixelBedwarsStats.formatDuration(number13),
                              HypixelBedwarsStats.this.numberColor,
                              (Boolean)HypixelBedwarsStats.this.textShadow.get()
                           )
                        }
                     )
                  );
               }
            }

            return list7;
         }

         public boolean onServerJoin(boolean flag1) {
            HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)HypixelBedwarsStats.this.onLocationUpdate(ModTraits.field16)).method1();
            if (!hypixelbedwars2.method35()) {
               this.method19(0.0F, 0.0F);
               return false;
            } else {
               return super.onServerJoin(flag1);
            }
         }
      };
   }

   private static String formatDuration(long number0) {
      long number2 = number0 / 1000L / 60L / 60L;
      long number4 = number0 / 1000L / 60L % 60L;
      long number6 = number0 / 1000L % 60L;
      return number2 == 0L ? String.format("%02d:%02d", number4, number6) : String.format("%d:%02d:%02d", number2, number4, number6);
   }

   @Generated
   public Bedwars getUpdater() {
      return this.updater;
   }
}

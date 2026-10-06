package com.moonsworth.lunar.client.mod.hud.scoreboard;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.scoreboard.ScoreBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.render.shader.HudShaderTarget;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModRestriction;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;

public class Scoreboard extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^[0-2][0-9]/[0-3][0-9]/[0-9][0-9] +([LlmM])");
   private final HypixelLocationListener field9 = (HypixelLocationListener)this.method7(HypixelLocationListener.class);
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("numbers").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("hideScoreboard").method31();
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("textShadow").method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1342177280))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "headerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1342177280))
      .method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("border").method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final FloatOption field17 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "toggleHideScoreboard"
         )
         .method18(this))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("displayToggleMessage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private boolean hidden;

   public Scoreboard() {
      super(true);
      this.method10(ModTraits.field1, new Scoreboard.Data());
      this.method10(ModTraits.field20, ModRestriction.method2(this, () -> {
         String text1 = this.field9.method7().field3;
         return text1 != null && text1.startsWith("RAVENGARD");
      }));
      ((SettingIntercept)this.method7(ModTraits.field4))
         .method6(arg0 -> Ref.method4().method99().method6(HudShaderTarget.SCOREBOARD_MOD), false);
      this.field18.method3(() -> {
         this.hidden = !this.hidden;
         if ((Boolean)this.field19.get()) {
            Ref.method4().method69().method3(NotificationManager.method15(this.hidden ? "scoreboardHidden" : "scoreboardDisplayed", new Object[0]));
         }
      });
   }

   public String getId() {
      return "SCOREBOARD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(new ClientOption[]{this.field10, this.field11, this.field12, this.field17, this.field15})
      );
      lightingextension231.method1("toggle", arg1x -> arg1x.method9(new ClientOption[]{this.field18, this.field19}));
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field13, this.field14, this.field16})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method9(true).method11(this);
   }

   @Generated
   public ColorOption method13() {
      return this.field13;
   }

   @Generated
   public ColorOption method14() {
      return this.field14;
   }

   private class Data extends HudElementBase {
      private com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge field9;

      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_RIGHT);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method17())) {
            com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting5;
            if (flag4) {
               if (Ref.method8() != null
                  && Scoreboard.this.mc.bridge$getWorld().bridge$getScoreboard().bridge$getObjectiveInDisplaySlot(1) != null) {
                  lighting5 = Scoreboard.this.mc.bridge$getWorld().bridge$getScoreboard().bridge$getObjectiveInDisplaySlot(1);
               } else {
                  ScoreboardBridge lighting46 = Bridge.method8().method43();
                  lighting5 = Bridge.method8().method44(lighting46, "Lunar Client");
                  lighting5.bridge$setDisplayName(
                     "" + ChatFormatting.RED + ChatFormatting.AQUA + "Lunar" + ChatFormatting.RESET + " " + ChatFormatting.BOLD + "Client"
                  );

                  for (String text8 : Set.of("Steve", "Alex", "Villager", "Enderman")) {
                     ScoreBridge lighting29 = lighting46.bridge$getValueFromObjective(text8, lighting5);
                     if (lighting29 != null) {
                        lighting29.bridge$setScorePoints(0);
                     }
                  }
               }
            } else {
               lighting5 = this.field9;
            }

            Bridge10_2 bridge10_230 = Ref.method10();
            ScoreboardBridge lighting431 = lighting5.bridge$getScoreboard();
            Collection list32 = lighting431.bridge$getSortedScores(lighting5);
            ArrayList list33 = new ArrayList();

            for (ScoreBridge lighting211 : list32) {
               if (lighting211.bridge$getPlayerName() != null && !lighting211.bridge$getPlayerName().startsWith("#")) {
                  list33.add(lighting211);
               }
            }

            ArrayList list34 = Lists.newArrayList(list33);
            ArrayList list35;
            if (list34.size() > 15) {
               list35 = Lists.newArrayList(Iterables.skip(list34, list32.size() - 15));
            } else {
               list35 = list34;
            }

            Bridge2_42 bridge2_4212 = lighting5.bridge$getDisplayName();
            int number13 = (int)bridge10_230.bridge$getStringWidth(bridge2_4212);
            int number14 = number13;
            Bridge2_42[] items15 = new Bridge2_42[list35.size()];
            float value16 = bridge10_230.bridge$getStringWidth(": ");
            boolean flag17 = true;
            int index18 = 0;

            for (ScoreBridge lighting220 : list35) {
               ScorePlayerTeamBridge lighting321 = lighting431.bridge$getPlayersTeam(lighting220.bridge$getPlayerName());
               float value22 = bridge10_230.bridge$getStringWidth(lighting220.bridge$getScoreComponent(lighting5));
               if (value22 > 0.0F) {
                  flag17 = false;
               }

               float value23 = value16 + value22;
               items15[index18] = this.method4(lighting321, lighting220.bridge$getPlayerComponentBridge());
               number14 = (int)Math.max(number14, bridge10_230.bridge$getStringWidth(items15[index18]) + value23);
               index18++;
            }

            int number37 = list35.size() * bridge10_230.method19() + 10;
            byte number38 = 2;
            int number39 = number14 + 2;
            boolean flag40 = (Boolean)Scoreboard.this.field10.get();
            boolean flag41 = flag40 && !flag17;
            if (flag41 && number14 > number13) {
               number39 -= Math.min(number14 - number13, 9);
            } else if (flag41) {
               number39 += 4;
            }

            MixinHelper_4 mixinhelper_424 = highlightimpl1.method2();
            if (!list35.isEmpty()) {
               Scoreboard.this.field14
                  .method11(mixinhelper_424, value2, value3 + (number37 - list35.size() * bridge10_230.method19()) - bridge10_230.method19() - 1.0F, number39, bridge10_230.method19());
               Scoreboard.this.field13.method11(mixinhelper_424, value2, value3 + (number37 - list35.size() * bridge10_230.method19()) - 1.0F, number39, number37 - bridge10_230.method19());
               if ((Boolean)Scoreboard.this.field15.get()) {
                  Scoreboard.this.field16.method11(mixinhelper_424, this, value2, value3, number39, number37, (Float)Scoreboard.this.field17.get());
               }
            }

            index18 = 0;

            for (ScoreBridge lighting226 : list35) {
               index18++;
               Bridge2_42 bridge2_4227 = items15[index18 - 1];
               int number28 = number37 - index18 * bridge10_230.method19();
               mixinhelper_424.method13(bridge10_230, bridge2_4227, value2 + number38, value3 + number28, -1, (Boolean)Scoreboard.this.field12.get());
               mixinhelper_424.method44(arg0 -> arg0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
               if (!flag40) {
                  Bridge2_42 bridge2_4229 = lighting226.bridge$getScoreComponentBridge(lighting5);
                  mixinhelper_424.method13(bridge10_230, bridge2_4229, value2 + number39 - bridge10_230.bridge$getStringWidth(bridge2_4229), value3 + number28, -1, (Boolean)Scoreboard.this.field12.get());
               }

               if (index18 == list35.size()) {
                  float value42 = value2 + number39 / 2.0F - number13 / 2.0F;
                  mixinhelper_424.method13(bridge10_230, bridge2_4212, value42, value3 + number28 - bridge10_230.method19(), -1, (Boolean)Scoreboard.this.field12.get());
               }
            }

            this.method58(number39, list35.size() * bridge10_230.method19() + 10);
         }
      }

      public boolean method4(boolean flag1) {
         if (Ref.method10() == null) {
            return false;
         }

         if ((Boolean)Scoreboard.this.field11.get() || Scoreboard.this.hidden) {
            return false;
         }

         if (flag1) {
            return true;
         }

         ScoreboardBridge lighting42 = Scoreboard.this.mc.bridge$getWorld().bridge$getScoreboard();
         com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting3 = null;
         ScorePlayerTeamBridge lighting34 = lighting42.bridge$getPlayersTeam(Scoreboard.this.mc.bridge$getPlayer().bridge$getName());
         if (lighting34 != null) {
            int number5 = lighting34.bridge$getChatFormat().getColorIndex();
            if (Bridge.getMinecraftVersion() == Config.field1) {
               lighting3 = lighting42.bridge$getObjectiveInDisplaySlot(1);
            } else if (number5 >= 0) {
               try {
                  lighting3 = lighting42.bridge$getObjectiveInDisplaySlot(3 + number5);
               } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception7) {
               }
            }
         }

         com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge lighting8 = lighting3 != null ? lighting3 : lighting42.bridge$getObjectiveInDisplaySlot(1);
         if (lighting8 != null) {
            this.field9 = lighting8;
            return true;
         } else {
            return false;
         }
      }

      public boolean method30() {
         return !Scoreboard.this.field11.get() && !Scoreboard.this.hidden ? super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() : false;
      }

      private Bridge2_42 method4(ScorePlayerTeamBridge lighting31, Bridge2_42 bridge2_422) {
         Bridge2_42 bridge2_423 = lighting31 == null ? bridge2_422 : lighting31.bridge$formatString(bridge2_422);
         NickHider nickhider4 = Ref.method4().method40().method41();
         if (nickhider4.isEnabled() && (Boolean)nickhider4.getHideLobbyID().get() && ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
            Component component5 = TextBridge.asAdventure(bridge2_423);
            String text6 = TextBridge.getTextContent(component5);
            String text7 = ChatFormatting.getTextWithoutFormattingCodes(text6);
            Matcher matcher8 = Scoreboard.field8.matcher(text7);
            if (matcher8.find()) {
               if (!component5.children().isEmpty()) {
                  Component component9 = (Component)component5.children().get(0);
                  if (Ref.MC_VERSION > 5 && !component9.children().isEmpty()) {
                     component9 = (Component)component9.children().get(0);
                  }

                  String text10 = ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent(component9));
                  if (!Scoreboard.field8.matcher(text10).find()) {
                     return TextBridge.asBridge(component9);
                  }
               }

               int index11 = text6.indexOf(text7.substring(matcher8.start(1)));
               if (index11 < 0) {
                  return bridge2_423;
               }

               Style style12 = component5.children().isEmpty() ? component5.style() : component5.style().merge(((Component)component5.children().get(0)).style());
               return TextBridge.asBridge(Component.text(text6.substring(0, index11), style12));
            }
         }

         return bridge2_423;
      }
   }
}

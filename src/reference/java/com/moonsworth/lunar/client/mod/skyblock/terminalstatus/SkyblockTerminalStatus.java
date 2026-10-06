package com.moonsworth.lunar.client.mod.skyblock.terminalstatus;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonTerminalListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalActivateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.EventTitle;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.List;
import java.util.regex.Matcher;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockTerminalStatus extends AbstractFeature {
   private final DungeonTerminalListener dungeonTerminalListener = (DungeonTerminalListener)this.method6(DungeonTerminalListener.class);
   private final DungeonFloorListener dungeonFloorListener = (DungeonFloorListener)this.method6(DungeonFloorListener.class);
   private final DungeonScoreListener dungeonScoreListener = (DungeonScoreListener)this.method6(DungeonScoreListener.class);
   private final AlertDisplayListener alertDisplayListener = (AlertDisplayListener)this.method6(AlertDisplayListener.class);
   private final ToggleOption showHud = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyBlockHideTerminalMessages = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyBlockHideTerminalMessages")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyBlockHideTerminalTitles = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyBlockHideTerminalTitles")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<SkyblockTerminalStatus.Type> skyblockTerminalHudFormat = (EnumOption<SkyblockTerminalStatus.Type>)OptionFactory.method10(
         "skyblockTerminalHudFormat", SkyblockTerminalStatus.Type.SHOW_MISSING
      )
      .method31();
   private final ToggleOption stageCompleteAlert = (ToggleOption)OptionFactory.method7("stageCompleteAlert").method31();
   private final TextOption alertText = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "alertText"
         )
         .method2("STAGE COMPLETE"))
      .method31();
   private final ColorOption alertColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "alertColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method15()
      .method31();
   private final IntegerOption timeToShow = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "timeToShow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1))
         .method7(1, 5))
      .method31();
   private final ToggleOption playChime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("playChime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public SkyblockTerminalStatus(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockTerminalStatus.Data()));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(TerminalActivateEvent.class, this::method2);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalPhaseEvent.class, this::method3);
      this.handle(EventTitle.class, this::method4);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method7(
         SettingsPage.HUD,
         arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showHud, arg1xx -> arg1xx.method9(new ClientOption[]{this.skyblockTerminalHudFormat}))
      );
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.skyBlockHideTerminalMessages, this.skyBlockHideTerminalTitles});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.stageCompleteAlert, arg1xx -> arg1xx.method9(new ClientOption[]{this.alertText, this.alertColor, this.timeToShow, this.playChime})
            );
         }
      );
   }

   private void method2(TerminalActivateEvent data31) {
      if ((Boolean)this.skyBlockHideTerminalMessages.get()) {
         data31.cancel();
      }
   }

   private void method3(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalPhaseEvent data21) {
      if ((Boolean)this.stageCompleteAlert.get()) {
         if (data21.method1() != 1) {
            TextComponent text2 = Component.text((String)this.alertText.get(), TextColor.color(this.alertColor.method14(0.0F)));
            this.alertDisplayListener
               .method2(ComparableImpl.method2().method1("TERMINAL_ADVANCE_STAGE").method2(text2).method3((Integer)this.timeToShow.get() * 1000).method6());
            if ((Boolean)this.playChime.get()) {
               IslandUtils.playSound();
            }
         }
      }
   }

   private void method4(EventTitle highlightimpl41) {
      if (highlightimpl41.method2() == com.moonsworth.lunar.client.event.mixin.gui.EventTitle.TitleSource.SERVER) {
         if ((Boolean)this.skyBlockHideTerminalTitles.get()) {
            String text2 = TextBridge.getTextContent(highlightimpl41.getTitle());
            if (text2 != null) {
               if (text2.equals("The gate has been destroyed!")) {
                  highlightimpl41.cancel();
               } else {
                  Matcher matcher3 = DungeonTerminalListener.field10.matcher(text2);
                  if (matcher3.matches()) {
                     highlightimpl41.cancel();
                  }
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_TERMINAL_STATUS";
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      private final Component titleComponent = ((TextComponent)Component.text("Terminal Status").color(NamedTextColor.AQUA)).decorate(TextDecoration.BOLD);

      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_RIGHT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(75, 100, 150, 150, 200, 250);
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method20() {
         return false;
      }

      @Nullable
      public List<HudLine> method5(boolean flag1) {
         if (flag1) {
            return this.method5(3, 1, false, false, 4, 7, 1);
         }

         int number2 = SkyblockTerminalStatus.this.dungeonTerminalListener.method22() == -1 ? 0 : SkyblockTerminalStatus.this.dungeonTerminalListener.method22();
         int number3 = SkyblockTerminalStatus.this.dungeonTerminalListener.method23() == -1
            ? SkyblockTerminalStatus.this.dungeonTerminalListener.method16()
            : SkyblockTerminalStatus.this.dungeonTerminalListener.method23();
         return this.method5(
            SkyblockTerminalStatus.this.dungeonTerminalListener.method21(),
            SkyblockTerminalStatus.this.dungeonTerminalListener.method18(),
            SkyblockTerminalStatus.this.dungeonTerminalListener.method5(),
            SkyblockTerminalStatus.this.dungeonTerminalListener.method17(),
            number2,
            number3,
            SkyblockTerminalStatus.this.dungeonTerminalListener.method15()
         );
      }

      private List<HudLine> method5(int number1, int number2, boolean flag3, boolean flag4, int number5, int number6, int number7) {
         return switch ((SkyblockTerminalStatus.Type)SkyblockTerminalStatus.this.skyblockTerminalHudFormat.get()) {
            case SIMPLE -> List.of(new HudLine(this.method6(number5, number6)));
            case DETAILED -> List.of(
               new HudLine(this.titleComponent),
               new HudLine(
                  Bridge.method28().method27(),
                  TextComponentFactory.builder()
                     .method2("Total")
                     .method3(this.method6(number5, number6))
                     .method5(NamedTextColor.WHITE)
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               ),
               new HudLine(
                  Bridge.method28().method61(),
                  TextComponentFactory.builder()
                     .method2("Terminals")
                     .method4(number1 + "")
                     .method5(NamedTextColor.WHITE)
                     .method7(number6 - 2 == number5 ? NamedTextColor.GREEN : NamedTextColor.RED)
                     .method9(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               ),
               new HudLine(
                  Bridge.method28().method62(),
                  TextComponentFactory.builder()
                     .method2("Levers")
                     .method4(number2 + "")
                     .method5(NamedTextColor.WHITE)
                     .method7(number2 == 2 ? NamedTextColor.GREEN : NamedTextColor.RED)
                     .method9(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               ),
               new HudLine(
                  Bridge.method28().method64(),
                  TextComponentFactory.builder()
                     .method2("Device")
                     .method4(flag3 ? "✔" : "✖")
                     .method5(NamedTextColor.WHITE)
                     .method7(flag3 ? NamedTextColor.GREEN : NamedTextColor.RED)
                     .method9(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               ),
               new HudLine(
                  Bridge.method28().method63(),
                  TextComponentFactory.builder()
                     .method2("Gate")
                     .method4(flag4 ? "✔" : "✖")
                     .method5(NamedTextColor.WHITE)
                     .method7(flag4 ? NamedTextColor.GREEN : NamedTextColor.RED)
                     .method9(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
            case SHOW_MISSING -> List.of(
               new HudLine(this.titleComponent),
               new HudLine(
                  Bridge.method28().method27(),
                  TextComponentFactory.builder()
                     .method2("Total")
                     .method3(this.method6(number5, number6))
                     .method5(NamedTextColor.WHITE)
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               ),
               new HudLine(
                  Bridge.method28().method42(),
                  TextComponentFactory.builder()
                     .method2("Missing")
                     .method3(this.method7(number1, number2, flag3, flag4, number6, number7))
                     .method5(NamedTextColor.WHITE)
                     .method7(NamedTextColor.GRAY)
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         };
      }

      private TextComponent method6(int number1, int number2) {
         return (TextComponent)((TextComponent)Component.text(number1).color(NamedTextColor.RED)).append(Component.text("/" + number2).color(NamedTextColor.GREEN));
      }

      private TextComponent method7(int number1, int number2, boolean flag3, boolean flag4, int number5, int number6) {
         TextComponent text7 = Component.text("");
         TextComponent text8 = Component.text(" ");
         TextComponent text9 = (TextComponent)Component.text(",").color(NamedTextColor.GRAY);
         int number10 = number5 - 3 - number1;
         int number11 = 2 - number2;
         boolean flag12 = true;
         if (number10 > 0) {
            text7 = (TextComponent)text7.append(Component.text(number10 + (number11 == 1 ? " term" : " terms")).color(NamedTextColor.GOLD));
            flag12 = false;
         }

         if (number11 > 0) {
            if (!flag12) {
               text7 = (TextComponent)text7.append(text9);
            } else {
               flag12 = false;
            }

            text7 = (TextComponent)((TextComponent)text7.append(text8))
               .append(Component.text(number11 + (number11 == 1 ? " lever" : " levers")).color(NamedTextColor.BLUE));
         }

         if (!flag3) {
            if (!flag12) {
               text7 = (TextComponent)text7.append(text9);
            } else {
               flag12 = false;
            }

            text7 = (TextComponent)((TextComponent)text7.append(text8)).append(Component.text("device").color(NamedTextColor.LIGHT_PURPLE));
         }

         if (!flag4 && number6 < 4) {
            if (!flag12) {
               text7 = (TextComponent)text7.append(text9);
            } else {
               flag12 = false;
            }

            text7 = (TextComponent)((TextComponent)text7.append(text8)).append(Component.text("gate").color(NamedTextColor.RED));
         }

         return text7;
      }

      public boolean method4(boolean flag1) {
         if (!super.method4(flag1)) {
            return false;
         } else if (!(Boolean)SkyblockTerminalStatus.this.showHud.get()) {
            return false;
         } else if (SkyblockTerminalStatus.this.dungeonFloorListener.method6().getNumber() != 7) {
            return false;
         } else {
            return flag1
               ? true
               : SkyblockTerminalStatus.this.dungeonScoreListener.method10()
                  && SkyblockTerminalStatus.this.dungeonTerminalListener.method15() > 0
                  && SkyblockTerminalStatus.this.dungeonTerminalListener.method15() < 5;
         }
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }

   private enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      SIMPLE("simple"),
      DETAILED("detailed"),
      SHOW_MISSING("showMissing");

      private final String id;

      public String id() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}

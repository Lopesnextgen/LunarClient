package com.moonsworth.lunar.client.mod.skyblock.ticktimerhud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
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
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;

public class SkyblockTickTimerHud extends AbstractFeature {
   private final DungeonFloorListener dungeonFloorListener = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonMapListener dungeonMapListener = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final EnumOption<SkyblockTickTimerHud.TickTimerUnit> skyblockTickTimerMode = (EnumOption<SkyblockTickTimerHud.TickTimerUnit>)OptionFactory.method10(
         "skyblockTickTimerMode", SkyblockTickTimerHud.TickTimerUnit.SECONDS
      )
      .method31();
   private final IntegerOption skyblockTickTimerDecimals = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "skyblockTickTimerDecimals"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 2))
      .method31();
   private final ToggleOption secretTimer = (ToggleOption)OptionFactory.method7("secretTimer").method31();
   private final ToggleOption skyblockStormTickTimer = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockStormTickTimer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockGoldorTerminalTickTimer = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockGoldorTerminalTickTimer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockGoldorDeathTickTimer = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockGoldorDeathTickTimer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption skyblockNecronDropTickTimer = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockNecronDropTickTimer")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> skyblockTickTimerPrefixColor = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockTickTimerPrefixColor", NamedColorOption.GRAY
      )
      .method31();
   private final EnumOption<NamedColorOption> skyblockTickTimerColor = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockTickTimerColor", NamedColorOption.GREEN
      )
      .method31();
   private final ToggleOption skyblockTickTimerDynamicColor = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockTickTimerDynamicColor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private SkyblockTickTimerHud.Type currentType;
   private int remainingTicks;

   public SkyblockTickTimerHud(Skyblock skyblock1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockTickTimerHud.Data()));
      this.method4(ModTraits.field17, ModCategories.method3(new SettingsPage[]{SettingsPage.DUNGEONS, SettingsPage.TIMERS}));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method51(this::onDisable);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage);
      this.handle(EventServerTick.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
   }

   private void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (text2.equals("Starting in 1 second.")) {
         this.method4(SkyblockTickTimerHud.Type.SECRET);
      } else if (this.dungeonFloorListener.method6().getNumber() == 7) {
         switch (text2) {
            case "[BOSS] Storm: Pathetic Maxor, just like expected.":
               this.method4(SkyblockTickTimerHud.Type.STORM);
               break;
            case "[BOSS] Storm: I should have known that I stood no chance.":
               this.method4(SkyblockTickTimerHud.Type.GOLDOR_TERMINALS);
               break;
            case "[BOSS] Goldor: Who dares trespass into my domain?":
               this.method4(SkyblockTickTimerHud.Type.GOLDOR_DEATH);
               break;
            case "The Core entrance is opening!":
               this.currentType = null;
               break;
            case "[BOSS] Necron: I'm afraid, your journey ends now.":
               this.method4(SkyblockTickTimerHud.Type.NECRON);
         }
      }
   }

   private void method2(EventServerTick highlightimpl91) {
      if (this.currentType != null) {
         if (--this.remainingTicks <= 0 && this.currentType.isRepeats()) {
            this.remainingTicks = this.currentType.getInterval();
         }
      }
   }

   private void onDisable() {
      this.currentType = null;
      this.remainingTicks = 0;
   }

   private void method3(EventWorldChange data31) {
      this.currentType = null;
      this.remainingTicks = 0;
   }

   private void method4(SkyblockTickTimerHud.Type type1) {
      this.currentType = type1;
      this.remainingTicks = type1.getInterval();
   }

   public String getId() {
      return "SKYBLOCK_TICK_TIMER_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.skyblockTickTimerMode});
            arg1x.method9(new ClientOption[]{this.skyblockTickTimerDecimals}).method3(() -> this.skyblockTickTimerMode.get() != SkyblockTickTimerHud.TickTimerUnit.SECONDS);
            arg1x.method9(
               new ClientOption[]{this.secretTimer, this.skyblockStormTickTimer, this.skyblockGoldorTerminalTickTimer, this.skyblockGoldorDeathTickTimer, this.skyblockNecronDropTickTimer, this.skyblockTickTimerPrefixColor, this.skyblockTickTimerDynamicColor}
            );
            arg1x.method9(new ClientOption[]{this.skyblockTickTimerColor}).method3(this.skyblockTickTimerDynamicColor::get);
         }
      );
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 66, 100, 100, 140, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (!this.shouldRender()) {
            return flag1 ? this.method3(SkyblockTickTimerHud.Type.STORM, 13) : null;
         } else {
            return this.method3(SkyblockTickTimerHud.this.currentType, SkyblockTickTimerHud.this.remainingTicks);
         }
      }

      private HudLine method3(SkyblockTickTimerHud.Type type1, int number2) {
         String text3 = switch ((SkyblockTickTimerHud.TickTimerUnit)SkyblockTickTimerHud.this.skyblockTickTimerMode.get()) {
            case TICKS -> number2 + "t";
            case SECONDS -> {
               double value4 = number2 / 20.0;
               yield String.format("%." + SkyblockTickTimerHud.this.skyblockTickTimerDecimals.get() + "f", value4) + "s";
            }
         };
         TextComponent text6 = SkyblockTickTimerHud.this.skyblockTickTimerDynamicColor.get()
            ? Component.text(text3, TextComponentFactory.colorForRatio(number2, type1.getInterval()))
            : Component.text(text3, TextComponentFactory.styleOf(SkyblockTickTimerHud.this.skyblockTickTimerColor));
         return new HudLine(
            Bridge.method28().method20(),
            ((Builder)((Builder)Component.text()
                     .append(
                        Component.text(
                           SkyblockTickTimerHud.this.method3(type1.getId(), new Object[0]) + ": ", TextComponentFactory.styleOf(SkyblockTickTimerHud.this.skyblockTickTimerPrefixColor)
                        )
                     ))
                  .append(text6))
               .build()
         );
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      private boolean shouldRender() {
         if (IslandUtils.getIsland() != SkyblockIsland.DUNGEON) {
            return false;
         } else if (SkyblockTickTimerHud.this.currentType == null) {
            return false;
         } else if (!(Boolean)SkyblockTickTimerHud.this.secretTimer.get() && SkyblockTickTimerHud.this.currentType == SkyblockTickTimerHud.Type.SECRET) {
            return false;
         } else if (!(Boolean)SkyblockTickTimerHud.this.skyblockStormTickTimer.get() && SkyblockTickTimerHud.this.currentType == SkyblockTickTimerHud.Type.STORM) {
            return false;
         } else if (!(Boolean)SkyblockTickTimerHud.this.skyblockGoldorTerminalTickTimer.get() && SkyblockTickTimerHud.this.currentType == SkyblockTickTimerHud.Type.GOLDOR_TERMINALS) {
            return false;
         } else if (!(Boolean)SkyblockTickTimerHud.this.skyblockGoldorDeathTickTimer.get() && SkyblockTickTimerHud.this.currentType == SkyblockTickTimerHud.Type.GOLDOR_DEATH) {
            return false;
         } else {
            return !SkyblockTickTimerHud.this.skyblockNecronDropTickTimer.get() && SkyblockTickTimerHud.this.currentType == SkyblockTickTimerHud.Type.NECRON
               ? false
               : SkyblockTickTimerHud.this.remainingTicks > 0;
         }
      }

      protected com.moonsworth.lunar.client.ui.hud.HudRowAlignment method16() {
         return com.moonsworth.lunar.client.ui.hud.HudRowAlignment.LEFT;
      }
   }

   private enum Type {
      SECRET(20, true, "secret"),
      STORM(20, true, "storm"),
      GOLDOR_TERMINALS(104, false, "goldorTerminals"),
      GOLDOR_DEATH(60, true, "goldorDeath"),
      NECRON(60, false, "necron");

      private final int interval;
      private final boolean repeats;
      private final String id;

      @Generated
      Type(int number3, boolean flag4, String text5) {
         this.interval = number3;
         this.repeats = flag4;
         this.id = text5;
      }

      @Generated
      public int getInterval() {
         return this.interval;
      }

      @Generated
      public boolean isRepeats() {
         return this.repeats;
      }

      @Generated
      public String getId() {
         return this.id;
      }
   }

   private enum TickTimerUnit implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      SECONDS,
      TICKS;

      TickTimerUnit() {
      }

      public String id() {
         return "skyblockTickTimer" + WordUtils.capitalizeFully(this.name());
      }

      @Override
      public String toString() {
         return this.method51(this.id(), new Object[0]);
      }
   }
}

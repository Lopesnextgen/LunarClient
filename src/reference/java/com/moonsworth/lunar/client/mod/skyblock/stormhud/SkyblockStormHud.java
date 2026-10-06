package com.moonsworth.lunar.client.mod.skyblock.stormhud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class SkyblockStormHud extends AbstractFeature {
   private static final long STORM_TIMER_DURATION_MS = 31250L;
   private static final String STORM_START_MESSAGE = "[BOSS] Storm: Pathetic Maxor, just like expected.";
   private static final String STORM_DEATH_MESSAGE = "[BOSS] Storm: I should have known that I stood no chance.";
   private static final String STORM_ENRAGED_MESSAGE = "⚠ Storm is enraged! ⚠";
   private static final ResourceLocationBridge STORM_TEXTURE = ResourceLocationBridge.create("lunar", "skyblock/hud/storm.png");
   private final DungeonFloorListener dungeonFloorListener = (DungeonFloorListener)this.method12(DungeonFloorListener.class);
   private final ToggleOption showHud = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption timerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "timerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final IntegerOption skyblockInvincibilityDecimals = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "skyblockInvincibilityDecimals"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 2))
      .method31();
   private final ToggleOption stormPurplePadDynamicColor = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("stormPurplePadDynamicColor")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showStormTimer = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showStormTimer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showStormEnragedTime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showStormEnragedTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption stormEnragedTimeToShow = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "stormEnragedTimeToShow"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final ToggleOption stormEnragedTimeChat = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("stormEnragedTimeChat").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption stormPurplePadTime = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("stormPurplePadTime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption announceStepOnPad = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("announceStepOnPad").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final IntegerOption purplePadCountdown = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "purplePadCountdown"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(1, 10))
      .method31();
   private HudTimer stormTimer;
   private long enragedTime = -1L;
   private boolean announcedStepOnPad;

   public SkyblockStormHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockStormHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method3(new SettingsPage[]{SettingsPage.DUNGEONS, SettingsPage.TIMERS}));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.dungeonFloorListener.method6().getNumber() == 7));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage);
      this.handle(EventTick.class, this::method2);
      this.handle(EventWorldChange.class, arg1x -> this.reset());
   }

   private void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      switch (text2) {
         case "[BOSS] Storm: Pathetic Maxor, just like expected.":
            this.stormTimer = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method3().method7().method2();
            break;
         case "[BOSS] Storm: I should have known that I stood no chance.":
            this.reset();
            break;
         case "⚠ Storm is enraged! ⚠":
            this.onStormEnraged();
      }
   }

   private void method2(EventTick highlightimpl21) {
      if ((Boolean)this.announceStepOnPad.get()) {
         if (!this.announcedStepOnPad && this.stormTimer != null) {
            if (this.stormTimer.get() > 31250L) {
               ChatMessageQueue.method1("/pc Step on purple pad!");
               this.announcedStepOnPad = true;
            }
         }
      }
   }

   private void onStormEnraged() {
      if (this.stormTimer != null) {
         this.enragedTime = this.stormTimer.get();
         if ((Boolean)this.stormEnragedTimeChat.get()) {
            SkyBlockChat.method1("Storm enraged in " + this.method4(this.enragedTime) + "!");
         }
      }
   }

   private String method4(long number1) {
      return this.formatSeconds(number1 / 1000.0);
   }

   private String formatSeconds(double value1) {
      return String.format("%." + this.skyblockInvincibilityDecimals.get() + "f", value1) + "s";
   }

   private boolean isPurplePadWarning() {
      if (this.stormTimer == null) {
         return false;
      }

      long number1 = this.stormTimer.get();
      return number1 > 31250L ? false : 31250L - number1 <= (Integer)this.purplePadCountdown.get() * 1000;
   }

   private void reset() {
      this.stormTimer = null;
      this.enragedTime = -1L;
      this.announcedStepOnPad = false;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.announceStepOnPad, this.stormEnragedTimeChat})
      );
      lightingextension231.method7(SettingsPage.HUD, arg1x -> arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showHud, arg1xx -> {
         arg1xx.method9(new ClientOption[]{this.showStormTimer});
         arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.stormPurplePadTime, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.purplePadCountdown}));
         arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showStormEnragedTime, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.stormEnragedTimeToShow}));
         arg1xx.method9(new ClientOption[]{this.textColor, this.stormPurplePadDynamicColor, this.skyblockInvincibilityDecimals});
         arg1xx.method9(new ClientOption[]{this.timerColor}).method3(this.stormPurplePadDynamicColor::get);
      }));
   }

   public String getId() {
      return "SKYBLOCK_STORM_HUD";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"f7", "m7", "floor seven", "master seven"})
         .method11(this);
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(20, 60, 120, 50, 140, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method3(37000L, 35800L, false);
         }

         if (SkyblockStormHud.this.stormTimer == null) {
            return null;
         }

         List list2 = this.method3(SkyblockStormHud.this.stormTimer.get(), SkyblockStormHud.this.enragedTime, SkyblockStormHud.this.isPurplePadWarning());
         return list2.isEmpty() ? null : list2;
      }

      private List<HudLine> method3(long number1, long number3, boolean flag5) {
         ArrayList list6 = new ArrayList();
         String text7 = SkyblockStormHud.this.method4(number1);
         if ((Boolean)SkyblockStormHud.this.showStormTimer.get()) {
            list6.add(
               new HudLine(
                  SkyblockStormHud.STORM_TEXTURE,
                  TextComponentFactory.builder()
                     .method2(SkyblockStormHud.this.method2("timer", new Object[0]))
                     .method6(SkyblockStormHud.this.textColor.method14(0.0F))
                     .method4(text7)
                     .method8(SkyblockStormHud.this.timerColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockStormHud.this.showStormEnragedTime.get() && number3 > 0L && number3 + (Integer)SkyblockStormHud.this.stormEnragedTimeToShow.get() * 1000 > number1) {
            list6.add(
               new HudLine(
                  Bridge.method28().method16(),
                  TextComponentFactory.builder()
                     .method2(SkyblockStormHud.this.method2("enragedIn", new Object[0]))
                     .method6(SkyblockStormHud.this.textColor.method14(0.0F))
                     .method4(SkyblockStormHud.this.method4(number3))
                     .method8(SkyblockStormHud.this.timerColor.method14(0.0F))
                     .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                     .build()
               )
            );
         }

         if ((Boolean)SkyblockStormHud.this.stormPurplePadTime.get() && flag5) {
            long number8 = 31250L - number1;
            com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory.Data data10 = TextComponentFactory.builder()
               .method2(SkyblockStormHud.this.method2("purplePadIn", new Object[0]))
               .method6(SkyblockStormHud.this.textColor.method14(0.0F))
               .method4(SkyblockStormHud.this.method4(number8))
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get());
            if ((Boolean)SkyblockStormHud.this.stormPurplePadDynamicColor.get()) {
               data10.method7(TextComponentFactory.colorForRatio(number8, ((Integer)SkyblockStormHud.this.purplePadCountdown.get()).intValue() * 1000L));
            } else {
               data10.method8(SkyblockStormHud.this.timerColor.method14(0.0F));
            }

            list6.add(new HudLine(Bridge.method28().method92(), data10.build()));
         }

         return list6;
      }

      public boolean method4(boolean flag1) {
         return (Boolean)SkyblockStormHud.this.showHud.get() && (flag1 || SkyblockStormHud.this.stormTimer != null) && super.method4(flag1);
      }
   }
}

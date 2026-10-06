package com.moonsworth.lunar.client.mod.skyblock.raffletaskshud;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.RaffleTaskDifficulty;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.RaffleTaskFilter;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockRaffleTasksHud extends AbstractFeature {
   private static final long field8 = 7200L;
   private static final Pattern field9 = Pattern.compile("^RAFFLE TASK! You completed the (?<name>.+?) raffle task and earned");
   private static final Pattern field10 = Pattern.compile("^Your Raffle Tasks have refreshed!$");
   private static final long field11 = 1782921600L;
   private static final long field12 = 1783872000L;
   private static final RaffleTaskFilter[] field13 = RaffleTaskFilter.values();
   private final EnumOption<RaffleTaskFilter> field14 = (EnumOption<RaffleTaskFilter>)OptionFactory.method10("raffleTaskFilter", RaffleTaskFilter.EASY)
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideCompletedRaffleTasks").method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("descriptionOnlyRaffleTasks")
         .method4(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("background").method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("alwaysShowRaffleTasks").method31();
   private final HighlightTypeListener field19 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final Map<String, Calculator> field20 = new LinkedHashMap<>();
   private long field21 = Long.MIN_VALUE;
   private final HudComponentGroup field22 = new HudComponentGroup(true, HudComponentGroup.field1).method3(1.0F);
   private final HudComponentGroup field23 = new HudComponentGroup(true, HudComponentGroup.field1).method3(1.0F);
   private final Map<String, Calculator> field24 = com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.Calculator.method1();

   public SkyblockRaffleTasksHud(Skyblock skyblock1) {
      super(true);
      this.method14(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method14(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method14(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method14(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockRaffleTasksHud.Data()));
      this.field14.method9(this::method17);
      this.field15.method9(this::method17);
      this.field16.method9(this::method17);
      this.handle(EventSlotUpdate.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method3);
      this.handle(EventTick.class, this::method4);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field14, this.field15, this.field16, this.field17, this.field18});
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_RAFFLE_TASKS_HUD";
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if (this.isActive()) {
         if (this.field19.method7() == SkyblockMenuType.RAFFLE_TASKS) {
            Calculator calculator2 = Calculator.method1(highlightimpl1.method3());
            if (calculator2 != null) {
               this.method13();
               this.field20.put(calculator2.getName(), calculator2);
            }
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (this.isActive()) {
         String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH()).trim();
         if (field10.matcher(text2).find()) {
            this.field20.clear();
            this.field21 = this.method6(SkyblockCalendar.now());
         } else {
            Matcher matcher3 = field9.matcher(text2);
            if (matcher3.find()) {
               this.method13();
               Calculator calculator4 = this.field20.get(matcher3.group("name").trim());
               if (calculator4 != null && !calculator4.isCompleted()) {
                  calculator4.setCompleted(true);
               }
            }
         }
      }
   }

   private void method4(EventTick highlightimpl21) {
      if (this.isActive()) {
         this.method13();
         this.method16();
      }
   }

   private boolean isActive() {
      if ((Boolean)this.field18.get()) {
         return true;
      }

      long number1 = SkyblockCalendar.now();
      return number1 >= 1782921600L && number1 < 1783872000L;
   }

   private void method13() {
      long number1 = this.method6(SkyblockCalendar.now());
      if (number1 != this.field21) {
         this.field20.clear();
         this.field21 = number1;
      }
   }

   private long method6(long number1) {
      return Math.floorDiv(number1 - 1560275700L, 7200L);
   }

   private long method14() {
      long number1 = Math.floorMod(SkyblockCalendar.now() - 1560275700L, 7200L);
      return (7200L - number1) * 1000L;
   }

   private int method8(Map<String, Calculator> map1, RaffleTaskDifficulty calculatortype22) {
      int index3 = 0;

      for (Calculator calculator5 : map1.values()) {
         if (calculator5.getDifficulty() == calculatortype22 && calculator5.isCompleted()) {
            index3++;
         }
      }

      return index3;
   }

   private void method9(int number1) {
      if (this.field20.isEmpty() && number1 == 0) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            bridge5extension_52.bridge$sendCommand("/centurytasks");
         }
      }
   }

   private List<String> method15() {
      return this.field20.isEmpty() ? List.of(this.method19("clickToOpen", new Object[0])) : List.of();
   }

   private HudComponent method11(RaffleTaskFilter gui2extension1) {
      HudComponentGroup mixincore5iterator2 = new HudComponentGroup(false, HudComponentGroup.field2).method3(4.0F);

      for (RaffleTaskFilter gui2extension6 : field13) {
         RaffleTaskDifficulty calculatortype27 = gui2extension6.getDifficulty();
         boolean flag8 = gui2extension6 == gui2extension1;
         NamedTextColor namedtextcolor9 = calculatortype27 == null ? NamedTextColor.WHITE : calculatortype27.getColor().getAdventureColor();
         mixincore5iterator2.method5(new MixinCore5Task(new TextHudComponent().method4(() -> {
            String text4 = calculatortype27 == null ? this.method19("filterAll", new Object[0]) : calculatortype27.getLabel();
            return (Component)(flag8 ? Component.text("[" + text4 + "]", namedtextcolor9).decorate(TextDecoration.BOLD) : Component.text(text4, NamedTextColor.DARK_GRAY));
         })).method10(() -> this.field14.OIRHOOIICOCIOOHICRRRICORIHHIHC(gui2extension6)));
      }

      return mixincore5iterator2;
   }

   private void method16() {
      this.method14(this.field22, this.field20, (RaffleTaskFilter)this.field14.get());
   }

   private void method17() {
      this.method14(this.field23, this.field24, (RaffleTaskFilter)this.field14.get());
   }

   private void method14(HudComponentGroup mixincore5iterator1, Map<String, Calculator> map2, RaffleTaskFilter gui2extension3) {
      RaffleTaskDifficulty calculatortype24 = gui2extension3.getDifficulty();
      mixincore5iterator1.method8();
      mixincore5iterator1.method5(
         new TextHudComponent()
            .method4(
               () -> ((TextComponent)Component.text(this.method19("raffleTasks", new Object[0])).color(NamedTextColor.LIGHT_PURPLE))
                  .decorate(TextDecoration.BOLD)
            )
            .method14(1.1)
      );
      if (map2.isEmpty()) {
         mixincore5iterator1.method5(new TextHudComponent(() -> this.method19("openMenu", new Object[0]), ChatFormatting.GRAY));
      } else {
         mixincore5iterator1.method5(this.method11(gui2extension3));

         for (RaffleTaskDifficulty calculatortype28 : RaffleTaskDifficulty.values()) {
            if (calculatortype24 == null || calculatortype24 == calculatortype28) {
               mixincore5iterator1.method5(new TextHudComponent(calculatortype28.getLabel() + " " + this.method8(map2, calculatortype28) + "/7", calculatortype28.getColor()));

               for (Calculator calculator10 : map2.values()) {
                  if (calculator10.getDifficulty() == calculatortype28 && (!calculator10.isCompleted() || !(Boolean)this.field15.get())) {
                     mixincore5iterator1.method5(new TextHudComponent(this.method15(calculator10)));
                  }
               }
            }
         }
      }

      mixincore5iterator1.method5(
         new TextHudComponent(() -> this.method19("timeUntilReset", new Object[0]) + Type.EASY_DYNAMIC_1.format(this.method14()), ChatFormatting.AQUA)
      );
   }

   private Component method15(Calculator calculator1) {
      if (!calculator1.isCompleted()) {
         if ((Boolean)this.field16.get() && calculator1.hasDescription()) {
            return Component.text(" - ", NamedTextColor.WHITE).append(calculator1.method2());
         }

         Object obj3 = Component.text(calculator1.getName(), NamedTextColor.WHITE);
         if (calculator1.hasDescription()) {
            obj3 = obj3.append(Component.text(" - ", NamedTextColor.GRAY)).append(calculator1.method2());
         }

         return (Component)obj3;
      } else {
         String text2 = this.field16.get() && calculator1.hasDescription()
            ? " - " + calculator1.method3()
            : calculator1.getName() + (calculator1.hasDescription() ? " - " + calculator1.method3() : "");
         return Component.text(text2, NamedTextColor.DARK_GRAY).decorate(TextDecoration.STRIKETHROUGH);
      }
   }

   private class Data extends MixinCore9Base {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.method1(arg1x -> arg1x || SkyblockRaffleTasksHud.this.isActive());
         MixinCore5Task mixincore5task2 = new MixinCore5Task(SkyblockRaffleTasksHud.this.field22)
            .method4(SkyblockRaffleTasksHud.this::method15)
            .method9(SkyblockRaffleTasksHud.this::method9);
         SkyblockRaffleTasksHud.this.method17();
         MixinCore5Task mixincore5task3 = new MixinCore5Task(SkyblockRaffleTasksHud.this.field23);
         this.method14(
            WidgetFactory.withBackground(mixincore5task2, SkyblockRaffleTasksHud.this.field17::get), WidgetFactory.withBackground(mixincore5task3, SkyblockRaffleTasksHud.this.field17::get)
         );
      }
   }
}

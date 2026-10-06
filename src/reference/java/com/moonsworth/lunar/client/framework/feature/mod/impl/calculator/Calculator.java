package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator;

import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.RaffleTaskDifficulty;
import java.util.LinkedHashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Calculator {
   private Calculator() {
   }

   public static Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator> method1() {
      LinkedHashMap map0 = new LinkedHashMap();
      method2(map0, RaffleTaskDifficulty.EASY, "Ticket Collector", "Obtain a Raffle Ticket.", true);
      method2(map0, RaffleTaskDifficulty.EASY, "Cake Eater", "Eat a slice of Century Cake.", true);
      method2(map0, RaffleTaskDifficulty.EASY, "Potioneer", "Drink a Potion.", true);
      method2(map0, RaffleTaskDifficulty.EASY, "One-tapped", "Kill a mob in a single hit.", false);
      method3(map0, RaffleTaskDifficulty.EASY, "Arthropod Slayer", "Kill an ", "\ue074 Arthropod", NamedTextColor.DARK_RED, " mob.");
      method2(map0, RaffleTaskDifficulty.EASY, "Gold Collector", "Obtain some Gold Essence.", false);
      method2(map0, RaffleTaskDifficulty.EASY, "Inflation Contributor", "Sell something to a Shop.", false);
      method2(map0, RaffleTaskDifficulty.MEDIUM, "Abiphone Caller", "Call someone on your Abiphone.", true);
      method2(map0, RaffleTaskDifficulty.MEDIUM, "Secret Finder", "Find a Secret in the Catacombs.", false);
      method2(map0, RaffleTaskDifficulty.MEDIUM, "Puzzle Solver", "Solve a Puzzle in Dungeons.", false);
      method2(map0, RaffleTaskDifficulty.MEDIUM, "Lost Adventurer Slayer", "Kill a Lost Adventurer.", false);
      method3(map0, RaffleTaskDifficulty.MEDIUM, "Arcane Slayer", "Kill an ", "\ue073 Arcane", NamedTextColor.DARK_PURPLE, " mob.");
      method3(map0, RaffleTaskDifficulty.MEDIUM, "Construct Slayer", "Kill a ", "\ue075 Construct", NamedTextColor.GRAY, " mob.");
      method2(map0, RaffleTaskDifficulty.MEDIUM, "Dragon Collector", "Obtain some Dragon Essence.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Master Mode Run", "Complete any Master Mode Dungeon Floor.", true);
      method2(map0, RaffleTaskDifficulty.HARD, "S+ Dungeon", "Get an S+ on any Dungeon Floor.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Flawless Dungeon", "Complete a Dungeon without anyone dying.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Rare Drop", "Find a RARE DROP with a base chance of 1% or lower.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Boss Slayer", "Kill a Boss.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Frozen Adventurer Slayer", "Kill a Frozen Adventurer.", false);
      method2(map0, RaffleTaskDifficulty.HARD, "Wither Collector", "Obtain some Wither Essence.", false);
      return map0;
   }

   private static void method2(
      Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator> map0,
      RaffleTaskDifficulty calculatortype21,
      String text2,
      String text3,
      boolean flag
   ) {
      map0.put(
         text2,
         new com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator(
            text2, Component.text(text3, NamedTextColor.GRAY), text3, calculatortype21, flag
         )
      );
   }

   private static void method3(
      Map<String, com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator> map0,
      RaffleTaskDifficulty calculatortype21,
      String text2,
      String text3,
      String text4,
      NamedTextColor namedtextcolor5,
      String text5
   ) {
      Component component7 = ((TextComponent)Component.text(text3, NamedTextColor.GRAY).append(Component.text(text4, namedtextcolor5))).append(Component.text(text5));
      map0.put(text2, new com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin.Calculator(text2, component7, text3 + text4 + text5, calculatortype21, false));
   }
}

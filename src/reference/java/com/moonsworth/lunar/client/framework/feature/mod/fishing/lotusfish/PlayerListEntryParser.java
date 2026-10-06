package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Nullable;

public class PlayerListEntryParser {
   public static Pattern field1 = Pattern.compile(
      "^\\[(?<sbLvl>\\d{1,3})\\] (?:\\[(?<rank>YOUTUBE|ADMIN)\\] )?(?<name>\\w{1,16})(?: (?<emblem>[^♲Ⓑቾ⚒])?(?<gamemode>[☀♲Ⓑ])?(?<faction>[ቾ⚒])?)?(?: \\((?<dungeonClass>\\w+)(?: (?<classLevelRoman>[IVXLC]+|\\d+))?\\))?(?: (?<guest>\\[✌\\]))?$"
   );

   public PlayerListEntryParser() {
   }

   public static PlayerListEntryParser.Data method1(String text0) {
      Matcher matcher1 = field1.matcher(text0);
      if (!matcher1.matches()) {
         return null;
      }

      int number2;
      try {
         number2 = Integer.parseInt(matcher1.group("sbLvl"));
      } catch (Exception exception10) {
         return null;
      }

      String text3 = matcher1.group("rank");
      String text4 = matcher1.group("name");
      String text5 = matcher1.group("emblem");
      String text6 = matcher1.group("gamemode");
      String text7 = matcher1.group("faction");
      String text8 = matcher1.group("dungeonClass");
      String text9 = matcher1.group("classLevelRoman");
      return new PlayerListEntryParser.Data(number2, text3, text4, text5, text6, text7, text8, text9);
   }

   public class Data {
      private final int field1;
      @Nullable
      private final String field2;
      private final String field3;
      @Nullable
      private final String field4;
      @Nullable
      private final String field5;
      @Nullable
      private final String field6;
      @Nullable
      private final String field7;
      @Nullable
      private final String field8;

      public Data(
         int number1,
         @Nullable String text2,
         String text3,
         @Nullable String text4,
         @Nullable String text5,
         @Nullable String text6,
         @Nullable String text7,
         @Nullable String text8
      ) {
         this.field1 = number1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
         this.field5 = text5;
         this.field6 = text6;
         this.field7 = text7;
         this.field8 = text8;
      }

      public int level() {
         return this.field1;
      }

      @Nullable
      public String rank() {
         return this.field2;
      }

      public String playerName() {
         return this.field3;
      }

      @Nullable
      public String method1() {
         return this.field4;
      }

      @Nullable
      public String method2() {
         return this.field5;
      }

      @Nullable
      public String faction() {
         return this.field6;
      }

      @Nullable
      public String method3() {
         return this.field7;
      }

      @Nullable
      public String method4() {
         return this.field8;
      }
   }
}

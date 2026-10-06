package com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Nullable;

public class Lotusfish2 {
   public static Pattern field1 = Pattern.compile(
      "^\\[(?<sbLvl>\\d{1,3})\\] (?:\\[(?<rank>YOUTUBE|ADMIN)\\] )?(?<name>\\w{1,16})(?: (?<emblem>[^♲Ⓑቾ⚒])?(?<gamemode>[☀♲Ⓑ])?(?<faction>[ቾ⚒])?)?(?: \\((?<dungeonClass>\\w+)(?: (?<classLevelRoman>[IVXLC]+|\\d+))?\\))?(?: (?<guest>\\[✌\\]))?$"
   );

   public static Lotusfish2.Data method1(String var0) {
      Matcher var1 = field1.matcher(var0);
      if (!var1.matches()) {
         return null;
      }

      int var2;
      try {
         var2 = Integer.parseInt(var1.group("sbLvl"));
      } catch (Exception var10) {
         return null;
      }

      String var3 = var1.group("rank");
      String var4 = var1.group("name");
      String var5 = var1.group("emblem");
      String var6 = var1.group("gamemode");
      String var7 = var1.group("faction");
      String var8 = var1.group("dungeonClass");
      String var9 = var1.group("classLevelRoman");
      return new Lotusfish2.Data(var2, var3, var4, var5, var6, var7, var8, var9);
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
         int var1,
         @Nullable String var2,
         String var3,
         @Nullable String var4,
         @Nullable String var5,
         @Nullable String var6,
         @Nullable String var7,
         @Nullable String var8
      ) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
         this.field8 = var8;
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

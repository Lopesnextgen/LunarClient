package com.moonsworth.lunar.client.ui.mainmenu;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import java.time.LocalDateTime;
import java.time.Month;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class MainMenuThemeManager implements com.moonsworth.lunar.client.framework.JsonFileConfig {
   private static String field1 = "";
   private static String field2 = "";
   private static MainMenuTheme field3 = null;
   private static int field4 = 0;
   private static MainMenuTheme field5 = null;
   private static boolean field6 = false;

   public MainMenuTheme method1() {
      LocalDateTime var1 = LocalDateTime.now();
      Month var2 = var1.getMonth();
      return this.method2(var1, var2);
   }

   public MainMenuTheme method2(LocalDateTime var1, Month var2) {
      if (!field6) {
         field6 = true;
         this.method4();
      }

      field3 = method3(var1, var2);
      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      return field3;
   }

   @Nullable
   private static MainMenuTheme method3(LocalDateTime var0, Month var1) {
      field5 = MainMenuHomeScreen.field22;

      for (SeasonalTheme var3 : MainMenuHomeScreen.field21) {
         if (var3.method1(var0, var1)) {
            field5 = var3.method3();
            break;
         }
      }

      String var5 = field5.name();
      if (!field2.equals(var5)) {
         field2 = var5;
         field1 = "";
      }

      if (!field1.isEmpty()) {
         for (SeasonalTheme var4 : MainMenuHomeScreen.field21) {
            if (var4.method3().name().equals(field1)) {
               return var4.method3();
            }
         }

         return MainMenuHomeScreen.field22;
      } else {
         return field5;
      }
   }

   public String method5() {
      return "main_menu_theme_manager.json";
   }

   public void load(JsonObject var1) {
      field1 = var1.has("selected") ? var1.get("selected").getAsString() : MainMenuHomeScreen.field22.name();
      field2 = var1.has("automatic") ? var1.get("automatic").getAsString() : MainMenuHomeScreen.field22.name();
      field4 = var1.has("balloonHighscore") ? var1.get("balloonHighscore").getAsInt() : 0;
   }

   public void method1(JsonObject var1) {
      var1.addProperty("selected", field1);
      var1.addProperty("automatic", field2);
      var1.addProperty("balloonHighscore", field4);
   }

   @Generated
   public static void method6(String var0) {
      field1 = var0;
   }

   @Generated
   public static String method10() {
      return field1;
   }

   @Generated
   public static void method8(String var0) {
      field2 = var0;
   }

   @Generated
   public static String method11() {
      return field2;
   }

   @Generated
   public static void method10(MainMenuTheme var0) {
      field3 = var0;
   }

   @Generated
   public static MainMenuTheme method12() {
      return field3;
   }

   @Generated
   public static int method13() {
      return field4;
   }

   @Generated
   public static void method13(int var0) {
      field4 = var0;
   }

   @Generated
   public static void method14(MainMenuTheme var0) {
      field5 = var0;
   }

   @Generated
   public static MainMenuTheme method15() {
      return field5;
   }
}

package com.moonsworth.lunar.client.ui.mainmenu;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuTheme;
import com.moonsworth.lunar.client.ui.mainmenu.NewYearTheme;
import com.moonsworth.lunar.client.ui.mainmenu.SpringTheme;
import com.moonsworth.lunar.client.ui.mainmenu.HalloweenTheme;
import com.moonsworth.lunar.client.ui.mainmenu.AnniversaryTheme;
import com.moonsworth.lunar.client.ui.mainmenu.VanillaTheme;
import com.moonsworth.lunar.client.ui.mainmenu.LunarTheme;
import com.moonsworth.lunar.client.ui.mainmenu.ChristmasTheme;
import com.moonsworth.lunar.client.ui.mainmenu.SummerTheme;
import com.moonsworth.lunar.client.ui.mainmenu.ClassicTheme;
import com.moonsworth.lunar.client.ui.mainmenu.JapanTheme;
import com.moonsworth.lunar.client.ui.mainmenu.TournamentTheme;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.ui.mainmenu.SeasonalThemeHandler;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.Generated;

public class MainMenuHomeScreen extends MainMenuScreen {
   private static final List<MainMenuThemeId> field20 = ImmutableList.of(
      MainMenuThemeId.method3("memeLogo", (var0, var1) -> var0.nextInt(1000000000) == 69),
      MainMenuThemeId.method2("blog"),
      MainMenuThemeId.method3("trailer", (var0, var1) -> FeatureFlag.TRAILER.isEnabled()),
      MainMenuThemeId.method3("wrapped", (var0, var1) -> !var1.contains("trailer") && FeatureFlag.WRAPPED.isEnabled()),
      MainMenuThemeId.method3("liveExperience", (var0, var1) -> FeatureFlag.LIVE_EXPERIENCE.isEnabled()),
      MainMenuThemeId.method3("advent", (var0, var1) -> FeatureFlag.ADVENT.isEnabled()),
      MainMenuThemeId.method3("discordCta", (var0, var1) -> !var1.contains("wrapped") && FeatureFlag.DISCORD_CTA.isEnabled())
   );
   public static final List<com.moonsworth.lunar.client.ui.mainmenu.SeasonalTheme<MainMenuTheme>> field21 = ImmutableList.of(
      SeasonalThemeHandler.method6(TournamentTheme::new, false, () -> ThreadModuleDump80.field14 != null),
      SeasonalThemeHandler.method5(HalloweenTheme::new, LocalDateTime.of(2025, 10, 24, 0, 0), LocalDateTime.of(2025, 11, 3, 0, 0)),
      SeasonalThemeHandler.method5(NewYearTheme::new, LocalDateTime.of(2025, 12, 31, 0, 0), LocalDateTime.of(2026, 1, 7, 0, 0)),
      SeasonalThemeHandler.method2(ChristmasTheme::new, Month.DECEMBER),
      SeasonalThemeHandler.method3(AnniversaryTheme::new, MonthDay.of(Month.APRIL, 6)),
      SeasonalThemeHandler.method2(SpringTheme::new, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE),
      SeasonalThemeHandler.method2(JapanTheme::new, Month.JULY),
      SeasonalThemeHandler.method2(SummerTheme::new, Month.JULY, Month.AUGUST, Month.SEPTEMBER),
      SeasonalThemeHandler.method10(VanillaTheme::new, true),
      SeasonalThemeHandler.method10(ClassicTheme::new, true)
   );
   public static final LunarTheme field22 = new LunarTheme();
   public final MainMenuTheme field23;
   private static final MainMenuThemeManager field24 = new MainMenuThemeManager();
   protected final Set<String> field25 = new HashSet<>();

   public MainMenuHomeScreen() {
      this.field23 = field24.method1();
      new PollingRateDetector().start();
      Random var1 = new Random(ThreadModuleDump48.field5);

      for (MainMenuThemeId var3 : field20) {
         var3.method1(this.field25, var1);
      }
   }

   @Override
   public boolean method5() {
      return (MainMenuThemeManager.method12() == null || MainMenuThemeManager.method12().method4()) && super.method5();
   }

   @Generated
   public static MainMenuThemeManager method3() {
      return field24;
   }

   @Generated
   public Set<String> method10() {
      return this.field25;
   }
}

package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import org.intellij.lang.annotations.Subst;

public enum SettingsPage {
   GENERAL("generalOptions", PhosphorIconLegacy.PI_SETTINGS02_SOLID),
   COLOR("colorOptions", PhosphorIconLegacy.PI_COLOR_PALETTE_SOLID),
   SETTINGS("settings", PhosphorIconLegacy.PI_SETTINGS02_SOLID),
   CONTROLS("controls", PhosphorIconLegacy.PI_KEYBOARD_WIRELESS_SOLID),
   OTHER("otherOptions", PhosphorIconLegacy.PI_SETTINGS02_SOLID),
   HUD("hudOptions", PhosphorIconLegacy.PI_MONITOR01_SOLID),
   RENDER("renderOptions", PhosphorIconLegacy.PI_CAMERA_DEFAULT_SOLID),
   AUDIO("audioOptions", PhosphorIconLegacy.PI_SPEAKER_ON_SOLID),
   STOPWATCHES("stopwatches", PhosphorIconLegacy.PI_TIMER_DEFAULT_SOLID),
   TIMERS("timers", PhosphorIconLegacy.PI_TIMER_DEFAULT_SOLID),
   INVENTORY("inventory", PhosphorIconLegacy.PI_BAG_SACK_POUCH_SOLID),
   CHAT("chat", PhosphorIconLegacy.PI_CHAT_DEFAULT_SOLID),
   DUNGEONS("dungeons", PhosphorIconLegacy.PI_DANGER_SKULL_SOLID),
   FARMING("farming", PhosphorIconLegacy.PI_AC_LEAF_SOLID),
   MINING("mining", PhosphorIconLegacy.PI_DIAMOND_COMPONENT_SOLID),
   FISHING("fishing", PhosphorIconLegacy.PI_WATER_DOUBLE_DROPLET_SOLID),
   SLAYER("slayer", PhosphorIconLegacy.PI_DANGER_SKULL_SOLID),
   CRIMSON_ISLE("crimsonIsle", PhosphorIconLegacy.PI_FIRE_DEFAULT_SOLID),
   END("end", PhosphorIconLegacy.PI_EYE_ON_SOLID),
   FORAGING("foraging", PhosphorIconLegacy.PI_CAMP_FIRE_SOLID),
   TAB_WIDGETS("tabWidgetsOptions", PhosphorIconLegacy.PI_REPEAT_RECTANGULAR_SOLID),
   EVENT("eventOptions", PhosphorIconLegacy.PI_CALENDAR_CHECK_SOLID),
   LOTUS_ATOLL("lotusAtoll", PhosphorIconLegacy.PI_BEACH_UMBRELLA_SOLID),
   SPIDERS_DEN("spidersDen", PhosphorIconLegacy.PI_BUG_SOLID),
   SKILLS("skills", PhosphorIconLegacy.PI_TOOLS_SOLID),
   ITEMS("items", PhosphorIconLegacy.PI_SHIELD_SOLID);

   private final String name;
   private final PhosphorIconLegacy icon;

   SettingsPage(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var3, PhosphorIconLegacy var4) {
      this.name = var3;
      this.icon = var4;
   }

   @Subst("generalOptions")
   public String getName() {
      return this.name;
   }

   public PhosphorIconLegacy getIcon() {
      return this.icon;
   }
}

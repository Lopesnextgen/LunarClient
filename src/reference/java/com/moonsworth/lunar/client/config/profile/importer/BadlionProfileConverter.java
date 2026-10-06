package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPattern;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairShape;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeAnimationType;
import com.moonsworth.lunar.client.framework.feature.keystrokes.EasingFunction;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeAnimationStyle;
import com.moonsworth.lunar.client.framework.feature.keystrokes.KeystrokeTimerType;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.mod.render.motionblur.MotionBlur.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import com.moonsworth.lunar.client.config.profile.ProfileData;
import com.moonsworth.lunar.client.config.profile.HudPosition;
import com.moonsworth.lunar.client.config.profile.ProfileColor;

public final class BadlionProfileConverter {
   private static final Set<String> field1 = buildKeyCodeNames();
   private static final float field2 = 2.0F;
   private static final Map<String, Float> field3 = Map.ofEntries(
      Map.entry("fps", 30.0F),
      Map.entry("cps", 30.0F),
      Map.entry("coordinates", 99.0F),
      Map.entry("direction", 56.0F),
      Map.entry("clock", 30.0F),
      Map.entry("memory", 30.0F),
      Map.entry("boss bar", 38.0F),
      Map.entry("combo counter", 30.0F),
      Map.entry("reach display", 30.0F),
      Map.entry("server address", 30.0F),
      Map.entry("uptime", 30.0F),
      Map.entry("pack display", 80.0F),
      Map.entry("speedometer", 30.0F),
      Map.entry("stopwatch", 30.0F),
      Map.entry("item tracker", 175.0F),
      Map.entry("block info", 106.0F),
      Map.entry("minimap", 414.0F),
      Map.entry("scoreboard", 200.0F),
      Map.entry("timers", 200.0F),
      Map.entry("ping", 30.0F),
      Map.entry("potion status", 160.0F),
      Map.entry("togglesprint", 17.0F)
   );
   private static final BiConsumer<ProfileData, JsonObject> field4 = (arg0, arg1) -> {};

   public BadlionProfileConverter() {
   }

   public ConvertedProfile convert(BadlionProfileConfig horsestats_21) {
      return this.convert(horsestats_21, null);
   }

   public ConvertedProfile convert(BadlionProfileConfig horsestats_21, BadlionProfileConverter.CanvasCrosshair data32) {
      BadlionProfileConverter.ModConverter data23 = new BadlionProfileConverter.ModConverter(horsestats_21, data32);
      data23.method1("FPS", "showFPS", "fps", (arg0, arg1x) -> copyBoolean(arg1x, "reverseOrder", arg0, "reversed"));
      data23.method1("CPS", "showCPS", "cps", BadlionProfileConverter::convertCps);
      data23.method1("COORDINATES", "coordinates", "coordinates", BadlionProfileConverter::convertCoordinates);
      data23.method19("COORDINATES", "coordinates", (arg0, arg1x) -> {
         copyColor(arg1x, "labelColor", arg0.method5("primaryColor"));
         copyColor(arg1x, "textColor", arg0.method5("secondaryColor"));
      }, "COORDINATES_X_CHILD", "COORDINATES_Y_CHILD", "COORDINATES_Z_CHILD", "COORDINATES_C_CHILD");
      data23.method1("KEYSTROKES", "keyStroke", "keystrokes", BadlionProfileConverter::convertKeystrokes);
      data23.method1("ARMORSTATUS", "armorStatus", "armor status", BadlionProfileConverter::convertArmorStatus);
      data23.method18(
         "ARMORSTATUS",
         "armorStatus",
         "ARMORSTATUS_HELD_ITEM_CHILD",
         "ARMORSTATUS_HELMET_CHILD",
         "ARMORSTATUS_CHESTPLATE_CHILD",
         "ARMORSTATUS_LEGGINGS_CHILD",
         "ARMORSTATUS_BOOTS_CHILD",
         "ARMORSTATUS_OFF_HAND_HELD_ITEM_CHILD",
         "ARMORSTATUS_PROTECTION_CHILD"
      );
      data23.method1("POTION_EFFECTS", "potionStatus", "potion status", BadlionProfileConverter::convertPotionStatus);
      data23.method1("SCOREBOARD", "scoreboard", "scoreboard", BadlionProfileConverter::convertScoreboard);
      data23.method1("DIRECTION_HUD", "showDirection", "direction", BadlionProfileConverter::convertDirectionHud);
      data23.method1("CLOCK", "clock", "clock", (arg0, arg1x) -> copyBoolean(arg1x, "militaryTime", arg0, "format24Hour"));
      data23.method1("BOSSBAR", "bossBar", "boss bar", BadlionProfileConverter::convertBossBar);
      data23.method1("MEMORY", "memoryDisplay", "memory", BadlionProfileConverter::convertMemory);
      data23.method1("COMBO", "comboCounter", "combo counter", field4);
      data23.method1("REACH_DISPLAY", "reachDisplay", "reach display", BadlionProfileConverter::convertReachDisplay);
      data23.method1("STOPWATCH", "stopwatch", "stopwatch", field4);
      data23.method1("SERVER_ADDRESS", "serverAddress", "server address", (arg0, arg1x) -> copyBoolean(arg1x, "serverIcon", arg0, "showIcon"));
      data23.method1("PLAYTIME", "uptime", "uptime", field4);
      data23.method1("PACK_DISPLAY", "resourcePackDisplay", "pack display", BadlionProfileConverter::convertPackDisplay);
      data23.method1("MOMENTUM", "speedometer", "speedometer", BadlionProfileConverter::convertSpeedometer);
      data23.method1("ITEM_COUNTER", "itemCounter", "item counter", field4);
      data23.method1("TNT_COUNTDOWN", "tntTime", "tnt timer", BadlionProfileConverter::convertTntTimer);
      data23.method1("WAILA", "blockInfo", "block info", BadlionProfileConverter::convertBlockInfo);
      data23.method1("ITEM_TRACKER", "itemTracker", "item tracker", (arg0, arg1x) -> {
         if (arg0.has("logPersist")) {
            arg1x.addProperty("popupDurationSec", clamp(arg0.method3("logPersist", 3.35F), 0.1F, 30.0F));
         }
      });
      data23.method1("MINIMAP", "newMiniMap", "minimap", BadlionProfileConverter::convertMinimap);
      data23.method1("TAB", "playerList", "player list", BadlionProfileConverter::convertPlayerList);
      data23.method1("COOLDOWNS", "timers", "timers", BadlionProfileConverter::convertCooldowns);
      data23.method2("PING", "showPing", "ping", "PING_HUD", BadlionProfileConverter::convertPing, field4);
      data23.method2(
         "SATURATION",
         "saturation",
         "saturation",
         "SATURATION_HUD_CHILD",
         (arg0, arg1x) -> copyBoolean(arg1x, "showAppleskinTooltip", arg0, "itemHoverStat"),
         field4
      );
      data23.method4();
      data23.method13();
      data23.method3("ZOOM", "zoom", BadlionProfileConverter::convertZoom);
      data23.method3("NICK_HIDER", "nickHider", BadlionProfileConverter::convertKeystrokes4);
      data23.method3("MOTION_BLUR", "motionBlur", BadlionProfileConverter::convertMotionBlur);
      data23.method3("ITEM_PHYSICS", "itemPhysic", (arg0, arg1x) -> {
         if (arg0.has("rotateSpeed")) {
            arg1x.addProperty("rotationSpeed", clamp(arg0.method3("rotateSpeed", 1.0F), 0.1F, 4.0F));
         }
      });
      data23.method3("TIME_CHANGER", "timeChanger", BadlionProfileConverter::convertTimeChanger);
      data23.method3("WEATHER_CHANGER", "weatherChanger", BadlionProfileConverter::convertKeystrokes5);
      data23.method3("COLOR_SATURATION", "colorSaturation", BadlionProfileConverter::convertColorSaturation);
      data23.method3("FOV", "fovChanger", BadlionProfileConverter::convertKeystrokes6);
      data23.method3("LIGHTING", "fullbright", BadlionProfileConverter::convertFullbright);
      data23.method3("HITBOX", "hitboxes", field4);
      data23.method3("GLINT_COLORIZER", "enchantGlint", BadlionProfileConverter::convertEnchantGlint);
      data23.method3("BLOCK_OUTLINE", "blockOverlay", BadlionProfileConverter::convertBlockOverlay);
      data23.method3("PARTICLE_CHANGER", "particles", field4);
      data23.method3("WORLDEDIT_CUI", "worldEditCUI", field4);
      data23.method3("REPLAYMOD", "replay", field4);
      data23.method3("SHINY_POTS", "shinypots", field4);
      data23.method3("DAMAGE_TINT", "damageTint", BadlionProfileConverter::convertKeystrokes0);
      data23.method3("HIT_COLOR", "hitColor", (arg0, arg1x) -> copyColor(arg1x, "hitArmorColor", arg0.method5("damageColor")));
      data23.method3("QUICKPLAY", "quickPlay", field4);
      data23.method3("MUMBLE_LINK", "mumbleLink", field4);
      data23.method3("CHUNK_BORDERS", "chunkBorders", BadlionProfileConverter::convertKeystrokes8);
      data23.method3("ONE_SEVEN_VISUALS", "animations", field4);
      data23.method3("PVP_INFO", "pvpInfo", field4);
      data23.method3("FREELOOK", "perspective", BadlionProfileConverter::convertKeystrokes1);
      data23.method3("CHAT", "chat", BadlionProfileConverter::convertChat);
      data23.method3("NAMETAG", "nameTags", field4);
      data23.method3("WAYPOINTS", "waypoints", field4);
      data23.method3("3D_SKINS", "skinsMod", BadlionProfileConverter::convertKeystrokes2);
      data23.method3("FOG", "fogCustomizer", field4);
      data23.method3("SOUND_CHANGER", "soundsMod", field4);
      data23.method3("MENU_BLUR", "inventoryBlur", (arg0, arg1x) -> {
         if (arg0.has("blurAmount")) {
            arg1x.addProperty("blurStrength", roundClamp(arg0.method3("blurAmount", 0.4F) * 20.0F, 0, 20));
         }
      });
      data23.method3("TEAM_VIEW", "teamMarker", field4);
      data23.method3("KILL_SOUNDS", "oofMod", field4);
      data23.method3("GUI_SCALE", "guiMod", BadlionProfileConverter::convertKeystrokes3);
      data23.method5();
      data23.method6();
      data23.method12();
      data23.method20();
      data23.method7();
      return data23.method23();
   }

   private static void convertKeystrokes(ProfileData horsestats$data0, JsonObject json1) {
      copyColor(json1, "backgroundColor", horsestats$data0.getColor("keystrokeBackgroundColor"));
      copyColor(json1, "backgroundPressedColor", horsestats$data0.getColor("keystrokeColorClicked"));
      copyColor(json1, "textColor", horsestats$data0.getColor("fontColor"));
      copyColor(json1, "textPressedColor", horsestats$data0.getColor("fontColorClicked"));
      if (horsestats$data0.has("fadeTime")) {
         json1.addProperty("keyFadeDelay", roundClamp(horsestats$data0.getFloat("fadeTime", 100.0F), 0, 500));
      }

      if (horsestats$data0.has("spacebarThickness")) {
         json1.addProperty("spacebarThickness", clamp(horsestats$data0.getFloat("spacebarThickness", 2.0F), 1.0F, 4.25F));
      }

      if (json1.has("border")) {
         json1.addProperty("innerBorder", json1.get("border").getAsBoolean());
      }

      String text2 = horsestats$data0.getString("selectedDirectionIndicator", "WASD");
      json1.addProperty("useArrows", "ARROW_KEYS".equals(text2));
      String text3 = horsestats$data0.getString("selectedCPSMode", "SMALL");
      boolean flag4 = !"NONE".equals(text3);
      json1.addProperty("leftCPS", flag4);
      json1.addProperty("rightCPS", flag4);
      convertKeystrokeAnimation(horsestats$data0, json1);
   }

   private static void convertKeystrokeAnimation(ProfileData horsestats$data0, JsonObject json1) {
      ProfileData horsestats$data2 = horsestats$data0.getSection("animationConfig");
      if (horsestats$data2 != null) {
         copyBoolean(json1, "animate", horsestats$data2, "animate");
         copyBoolean(json1, "animateColor", horsestats$data2, "animateColor");
         copyEnum(json1, "animationType", KeystrokeAnimationType.class, horsestats$data2.getString("animationType", null));
         copyEnum(json1, "timerType", KeystrokeTimerType.class, horsestats$data2.getString("timerType", null));
         copyEnum(json1, "animation", KeystrokeAnimationStyle.class, horsestats$data2.getString("animation", null));
         copyEnum(json1, "timingFunction", EasingFunction.class, horsestats$data2.getString("timingFunction", null));
         copyColor(json1, "animationStartColor", horsestats$data2.getColor("startColor"));
         copyColor(json1, "animationCenterColor", horsestats$data2.getColor("centerColor"));
         copyColor(json1, "animationEndColor", horsestats$data2.getColor("endColor"));
         if (horsestats$data2.has("duration")) {
            json1.addProperty("duration", clamp(horsestats$data2.getFloat("duration", 0.5F), 0.1F, 1.0F));
         }

         if (horsestats$data2.getBoolean("animate", false) && !horsestats$data2.getBoolean("animateColor", false)) {
            copyColor(json1, "backgroundPressedColor", horsestats$data2.getColor("color"));
         }
      }
   }

   private static void convertCps(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "reverseText", horsestats$data0, "reversed");
      if (horsestats$data0.has("removeCpsText")) {
         json1.addProperty("showCPSText", !horsestats$data0.getBoolean("removeCpsText", false));
      }

      copyBoolean(json1, "ignoreCancelledClicks", horsestats$data0, "ignoreCancelledClicks");
      if (horsestats$data0.has("button")) {
         json1.addProperty("rightClick", "RMB".equals(horsestats$data0.getString("button", "LMB")));
      }

      copyColor(json1, "lineColor", horsestats$data0.getColor("combinedSeparatorColor"));
   }

   private static void convertCoordinates(ProfileData horsestats$data0, JsonObject json1) {
      copyEnum(json1, "mode", com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode.class, horsestats$data0.getString("modOrientation", null));
      if (horsestats$data0.has("roundLocation")) {
         json1.addProperty("decimalCoordinates", !horsestats$data0.getBoolean("roundLocation", true));
      }
   }

   private static void convertArmorStatus(ProfileData horsestats$data0, JsonObject json1) {
      String text2 = horsestats$data0.getString("armorMode", "BOTH");
      json1.addProperty("damageDisplay", "BAR".equals(text2) ? "none" : (horsestats$data0.getBoolean("showPercentage", false) ? "percent" : "value"));
      json1.addProperty("damageOverlay", !"DURABILITY".equals(text2));
      copyEnum(json1, "listMode", com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode.class, horsestats$data0.getString("armorOrientation", null));
      copyEnum(
         json1,
         "durabilityPosition",
         com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition.class,
         horsestats$data0.getString("durabilityTextPosition", null)
      );
      copyBoolean(json1, "itemName", horsestats$data0, "showItemName");
      copyBoolean(json1, "itemCount", horsestats$data0, "showTotalInInv");
      copyBoolean(json1, "maxDamage", horsestats$data0, "showMaxDurability");
      copyBoolean(json1, "hideUnbreakableDurability", horsestats$data0, "hideDurabilityForUnbreakables");
      copyColor(json1, "nameTextColor", horsestats$data0.getColor("colorItemName"));
      if (horsestats$data0.has("colorSixtyPercent")) {
         json1.addProperty("staticDamageColors", true);
         copyColor(json1, "highestColor", horsestats$data0.getColor("colorSixtyPercent"));
         copyColor(json1, "highColor", horsestats$data0.getColor("colorSixtyPercent"));
         copyColor(json1, "mediumColor", horsestats$data0.getColor("colorFortyPercent"));
         copyColor(json1, "mediumLowColor", horsestats$data0.getColor("colorTenPercent"));
         copyColor(json1, "lowColor", horsestats$data0.getColor("colorTenPercent"));
         copyColor(json1, "lowestColor", horsestats$data0.getColor("colorZeroPercent"));
      }
   }

   private static void convertScoreboard(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("showNumbers")) {
         json1.addProperty("numbers", !horsestats$data0.getBoolean("showNumbers", true));
      }

      copyColor(json1, "headerColor", horsestats$data0.getColor("topBackground"));
   }

   private static void convertBossBar(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "renderBar", horsestats$data0, "showBossBar");
      copyBoolean(json1, "renderBossText", horsestats$data0, "showBossText");
      copyBoolean(json1, "customBossBar", horsestats$data0, "customBar");
      copyColor(json1, "barColor", horsestats$data0.getColor("barColor"));
   }

   private static void convertMemory(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("displayType")) {
         json1.addProperty("displayMode", "TOTAL".equals(horsestats$data0.getString("displayType", "PERCENT")) ? "megabytes" : "percentage");
      }
   }

   private static void convertReachDisplay(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "reverseOrder", horsestats$data0, "reversed");
      if (horsestats$data0.has("show0Blocks")) {
         json1.addProperty("hideZero", !horsestats$data0.getBoolean("show0Blocks", true));
      }

      copyBoolean(json1, "highlightAttackablePlayers", horsestats$data0, "highlightPlayerInRange");
      copyColor(json1, "highlightColor", horsestats$data0.getColor("playerHighlightColor"));
   }

   private static void convertTntTimer(ProfileData horsestats$data0, JsonObject json1) {
      copyColor(json1, "color", horsestats$data0.getColor("tagTextColor"));
      if (horsestats$data0.has("dynamicMode")) {
         json1.addProperty("staticCountdownColor", !horsestats$data0.getBoolean("dynamicMode", false));
      }

      copyBoolean(json1, "background", horsestats$data0, "showBackgroundColor");
   }

   private static void convertCooldowns(ProfileData horsestats$data0, JsonObject json1) {
      copyColor(json1, "textColor", horsestats$data0.getColor("textColor"));
      if (horsestats$data0.has("reversedText")) {
         json1.addProperty(
            "cooldownTextPosition",
            (horsestats$data0.getBoolean("reversedText", false)
                  ? com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition.LEFT
                  : com.moonsworth.lunar.client.framework.feature.cooldowns.CooldownTextPosition.RIGHT)
               .id()
         );
      }
   }

   private static void convertPotionStatus(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "potionBlink", horsestats$data0, "blinkingEnabled");
      copyBoolean(json1, "formattedDurations", horsestats$data0, "formattedDuration");
      copyBoolean(json1, "uppercasePotionNames", horsestats$data0, "upperCaseFormatting");
      copyBoolean(json1, "reversedText", horsestats$data0, "reversedText");
      if (horsestats$data0.has("showIcon")) {
         json1.addProperty("hideModernIcons", !horsestats$data0.getBoolean("showIcon", true));
      }

      if (horsestats$data0.has("potionStatusType")) {
         json1.addProperty("minimalMode", "CIRCLE".equals(horsestats$data0.getString("potionStatusType", "VANILLA")));
      }

      copyColor(json1, "textColor", horsestats$data0.getColor("potionTextColor"));
      copyColor(json1, "durationColor", horsestats$data0.getColor("timeTextColor"));
   }

   private static void convertDirectionHud(ProfileData horsestats$data0, JsonObject json1) {
      switch (horsestats$data0.getString("directionType", "")) {
         case "SIMPLE":
            json1.addProperty("hudStyle", "simple");
            break;
         case "CLASSIC":
            json1.addProperty("hudStyle", "legacy");
            break;
         case "REAL_COMPASS":
            json1.addProperty("hudStyle", "realCompass");
            break;
         case "FANCY_COMPASS":
         case "COMPASS":
            json1.addProperty("hudStyle", "normal");
      }

      copyBoolean(json1, "background", horsestats$data0, "drawBackground");
   }

   private static void convertPackDisplay(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "packIcon", horsestats$data0, "showIcon");
      copyBoolean(json1, "packExtension", horsestats$data0, "showFileType");
      copyBoolean(json1, "packDescription", horsestats$data0, "showDescription");
      copyBoolean(json1, "keepBold", horsestats$data0, "bold");
      copyBoolean(json1, "keepItalic", horsestats$data0, "italic");
      copyBoolean(json1, "keepUnderline", horsestats$data0, "underline");
      copyBoolean(json1, "keepStrikethrough", horsestats$data0, "strikethrough");
      copyBoolean(json1, "keepObfuscated", horsestats$data0, "obfuscated");
      if (horsestats$data0.has("packDisplayOrder")) {
         json1.addProperty("packOrder", "TOP_PACK".equals(horsestats$data0.getString("packDisplayOrder", "TOP_PACK")) ? "first" : "last");
      }

      convertPackTextMode(json1, "titleReplacement", horsestats$data0, "titleColorDisplayOption");
      copyColor(json1, "textColor", horsestats$data0.getColor("titleFullReplaceColor"));
      convertPackTextMode(json1, "descriptionReplacement", horsestats$data0, "descriptionColorDisplayOption");
      copyColor(json1, "descriptionReplacementColor", horsestats$data0.getColor("descriptionFullReplaceColor"));
   }

   private static void convertPackTextMode(JsonObject json0, String text1, ProfileData horsestats$data2, String text3) {
      switch (horsestats$data2.getString(text3, "")) {
         case "FULL_PACK_TEXT":
            json0.addProperty(text1, "full_text");
            break;
         case "WHITE_TEXT_ONLY":
            json0.addProperty(text1, "white_only_text");
      }
   }

   private static void convertSpeedometer(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("yAxis")) {
         json1.addProperty("useGroundSpeed", !horsestats$data0.getBoolean("yAxis", false));
      }

      copyBoolean(json1, "useAverageVelocity", horsestats$data0, "average");
      if (horsestats$data0.has("precision")) {
         int number2 = roundClamp(horsestats$data0.getFloat("precision", 2.0F), 0, 3);
         json1.addProperty("rounding", number2 == 0 ? "nearest" : number2 + "Decimal");
      }
   }

   private static void convertBlockInfo(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "alwaysShow", horsestats$data0, "alwaysShow");
      copyBoolean(json1, "showBlockCoords", horsestats$data0, "showCoords");
      copyColor(json1, "blockCoordsColor", horsestats$data0.getColor("coordsColor"));
      copyBoolean(json1, "showCorrectTool", horsestats$data0, "showCorrectTool");
      copyColor(json1, "correctToolColor", horsestats$data0.getColor("toolColor"));
      copyBoolean(json1, "showBreakTime", horsestats$data0, "showBreakTime");
      copyColor(json1, "breakTimeColor", horsestats$data0.getColor("breakTimeColor"));
      copyBoolean(json1, "showLightLevel", horsestats$data0, "showLightLevel");
      copyColor(json1, "lightLevelColor", horsestats$data0.getColor("lightLevelColor"));
      copyColor(json1, "textColor", horsestats$data0.getColor("blockNameColor"));
   }

   private static void convertMinimap(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("lockRotation")) {
         json1.addProperty("rotateWithPlayer", !horsestats$data0.getBoolean("lockRotation", false));
      }

      if (horsestats$data0.has("displayType")) {
         json1.addProperty("mapShape", "SQUARE".equals(horsestats$data0.getString("displayType", "ROUND")) ? "square" : "circle");
      }

      copyBoolean(json1, "compass", horsestats$data0, "directions");
      copyColor(json1, "compassColor", horsestats$data0.getColor("directionColor"));
      copyColor(json1, "borderColor", horsestats$data0.getColor("borderColor"));
      copyColor(json1, "playerMarkerColor", horsestats$data0.getColor("cursorColor"));
      copyBoolean(json1, "militaryTime", horsestats$data0, "format24Hour");
   }

   private static void convertPlayerList(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "moveSelfToTop", horsestats$data0, "moveSelfToTop");
      copyBoolean(json1, "disableHeader", horsestats$data0, "disableHeader");
      copyBoolean(json1, "disableFooter", horsestats$data0, "disableFooter");
      if (horsestats$data0.has("disablePlayerHeads")) {
         json1.addProperty("displayPlayerHead", !horsestats$data0.getBoolean("disablePlayerHeads", false));
      }

      copyBoolean(json1, "displayPingAsNumber", horsestats$data0, "showPingNumbers");
      copyBoolean(json1, "hidePing", horsestats$data0, "hidePing");
      copyBoolean(json1, "dynamicPingColor", horsestats$data0, "dynamicPingColor");
      copyBoolean(json1, "nameShadow", horsestats$data0, "namesTextShadow");
      copyBoolean(json1, "pingNumberShadow", horsestats$data0, "pingTextShadow");
      copyBoolean(json1, "hideNPC", horsestats$data0, "removeNPCs");
      copyColor(json1, "rowsColor", horsestats$data0.getColor("rowColor"));
      copyColor(json1, "backgroundColor", horsestats$data0.getColor("backgroundColor"));
      ProfileColor horsestats2_22 = horsestats$data0.getColor("nameHighlightColor");
      if (horsestats2_22 != null) {
         json1.addProperty("highlightOwnName", isEnabled(horsestats$data0.getElement("nameHighlightColor")));
         copyColor(json1, "nameColor", horsestats2_22);
      }
   }

   private static void convertPing(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "pingSpikeDetection", horsestats$data0, "pingSpikeDetector");
      copyColor(json1, "mediumSpikeColor", horsestats$data0.getColor("mediumColor"));
      copyColor(json1, "largeSpikeColor", horsestats$data0.getColor("largeColor"));
      if (horsestats$data0.has("mediumThreshold")) {
         json1.addProperty("mediumSpikeThreshold", roundClamp(horsestats$data0.getFloat("mediumThreshold", 20.0F), 1, 200));
      }

      if (horsestats$data0.has("largeThreshold")) {
         json1.addProperty("largeSpikeThreshold", roundClamp(horsestats$data0.getFloat("largeThreshold", 50.0F), 1, 200));
      }

      if (horsestats$data0.has("rollingAvgSize")) {
         json1.addProperty("pingMode", "averaged");
         json1.addProperty("averageSamples", roundClamp(horsestats$data0.getFloat("rollingAvgSize", 20.0F), 2, 20));
      }

      copyBoolean(json1, "showPingPrefix", horsestats$data0, "showPrefix");
   }

   private static void convertZoom(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "smoothZoom", horsestats$data0, "smoothZoom");
      copyBoolean(json1, "smoothCamera", horsestats$data0, "smoothMoving");
      copyBoolean(json1, "variableZoom", horsestats$data0, "scrollToZoomIn");
      copyBoolean(json1, "toggleKeyZoom", horsestats$data0, "zoomKeybindToggle");
      if (horsestats$data0.has("defaultZoomFactor")) {
         json1.addProperty("zoomDivisor", roundClamp(horsestats$data0.getFloat("defaultZoomFactor", 4.0F), 2, 10));
      }

      if (horsestats$data0.has("scrollZoomInSpeed")) {
         json1.addProperty("zoomScrollSpeed", clamp(horsestats$data0.getFloat("scrollZoomInSpeed", 1.0F), 0.25F, 5.0F));
      }

      copyKeybind(json1, "zoomKeybind", horsestats$data0, "zoomKeybind");
   }

   private static void convertMotionBlur(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("blurS")) {
         json1.addProperty("value", roundClamp(horsestats$data0.getFloat("blurS", 0.7F) * 10.0F, 1, 10));
      }

      switch (horsestats$data0.getString("blurType", "")) {
         case "LEGACY":
            json1.addProperty("type", Type.V1.id());
            break;
         case "OLD":
            json1.addProperty("type", Type.V2.id());
            break;
         case "NEW":
         case "BSL":
         case "ACCUMULATION":
            json1.addProperty("type", Type.V3.id());
      }
   }

   private static void convertTimeChanger(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("time")) {
         json1.addProperty("timeChangerTime", Math.floorMod(Math.round(horsestats$data0.getFloat("time", 0.0F)), 24000));
      }

      if (horsestats$data0.has("timeFrozen")) {
         json1.addProperty("timePassage", !horsestats$data0.getBoolean("timeFrozen", false));
      }

      if (horsestats$data0.has("timeMultiplier")) {
         json1.addProperty("speed", roundClamp(horsestats$data0.getFloat("timeMultiplier", 1.0F), 0, 20));
      }

      switch (horsestats$data0.getString("overWorldSkyType", "")) {
         case "REGULAR":
            json1.addProperty("overworldSky", com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger.Type.DEFAULT.id());
            break;
         case "NETHER":
            json1.addProperty("overworldSky", com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger.Type.NETHER.id());
            break;
         case "END":
            json1.addProperty("overworldSky", com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger.Type.END.id());
      }
   }

   private static void convertColorSaturation(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("saturation")) {
         json1.addProperty("colorSaturationSaturation", clamp(horsestats$data0.getFloat("saturation", 1.0F) * 5.0F, 0.0F, 10.0F));
      }

      copyBoolean(json1, "grayscale", horsestats$data0, "grayScale");
   }

   private static void convertFullbright(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("brightness")) {
         float value2 = horsestats$data0.getFloat("brightness", 100.0F);
         json1.addProperty("fullBright", value2 >= 100.0F);
         json1.addProperty("brightnessBoost", clamp(value2 / 10.0F, 1.0F, 10.0F));
      }
   }

   private static void convertEnchantGlint(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "showGlint", horsestats$data0, "enableAllGlints");
      ProfileColor horsestats2_22 = horsestats$data0.getColor("glintColor");
      copyColor(json1, "glintColor", horsestats2_22);
      copyColor(json1, "itemGlintLunarColor", horsestats2_22);
      copyColor(json1, "itemGlintVanillaColor", horsestats2_22);
      copyColor(json1, "armorGlintLunar", horsestats2_22);
      copyColor(json1, "armorGlintVanilla", horsestats2_22);
   }

   private static void convertBlockOverlay(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "blockOutline", horsestats$data0, "outline");
      copyColor(json1, "blockOutlineColor", horsestats$data0.getColor("outlineColor"));
      copyBoolean(json1, "blockOverlay", horsestats$data0, "fill");
      copyColor(json1, "blockOverlayColor", horsestats$data0.getColor("fillColor"));
      if (horsestats$data0.has("thickness")) {
         json1.addProperty("blockOutlineWidth", clamp(horsestats$data0.getFloat("thickness", 2.0F), 1.0F, 10.0F));
      }

      copyBoolean(json1, "blockOutlineSide", horsestats$data0, "oneSideOnly");
   }

   private static void convertDamageTint(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("intensity")) {
         json1.addProperty("vignetteIntensity", clamp(horsestats$data0.getFloat("intensity", 1.0F), 0.0F, 1.0F));
      }

      if (horsestats$data0.has("threshold")) {
         json1.addProperty("showVignetteBelow", roundClamp(horsestats$data0.getFloat("threshold", 30.0F), 0, 100));
      }

      copyBoolean(json1, "heartbeatAudio", horsestats$data0, "heartbeat");
      if (horsestats$data0.has("volume")) {
         json1.addProperty("heartbeatAudioVolume", clamp(horsestats$data0.getFloat("volume", 0.8F), 0.0F, 1.0F));
      }
   }

   private static void convertFreelook(ProfileData horsestats$data0, JsonObject json1) {
      copyKeybind(json1, "freelook", horsestats$data0, "toggleKeybind");
      copyBoolean(json1, "toggleKeyFreelook", horsestats$data0, "togglePerspective");
      copyBoolean(json1, "smoothCamera", horsestats$data0, "smoothCamera");
      copyBoolean(json1, "invertYaw", horsestats$data0, "invertYaw");
      copyBoolean(json1, "invertPitch", horsestats$data0, "invertPitch");
   }

   private static void convertSkins(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "showOthers", horsestats$data0, "showOthers");
      copyBoolean(json1, "enableHat", horsestats$data0, "showHead");
      copyBoolean(json1, "enableJacket", horsestats$data0, "showBody");
      copyBoolean(json1, "enableLeftSleeve", horsestats$data0, "showLeftArm");
      copyBoolean(json1, "enableRightSleeve", horsestats$data0, "showRightArm");
      copyBoolean(json1, "enableLeftPants", horsestats$data0, "showLeftLeg");
      copyBoolean(json1, "enableRightPants", horsestats$data0, "showRightLeg");
   }

   private static void convertGuiScale(ProfileData horsestats$data0, JsonObject json1) {
      int number2 = roundClamp(horsestats$data0.getFloat("inventoryScale", 0.0F), 0, 5);
      if (number2 > 0) {
         json1.addProperty("inventoryScale", number2);
      }
   }

   private static void convertNickHider(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "hideName", horsestats$data0, "hideNames");
      copyBoolean(json1, "hideRealName", horsestats$data0, "hideOwnName");
      copyBoolean(json1, "hideOthersNames", horsestats$data0, "hideOtherNames");
      copyBoolean(json1, "hideOwnSkin", horsestats$data0, "hideSkins");
      copyBoolean(json1, "hideOthersSkin", horsestats$data0, "hideOtherSkins");
      copyBoolean(json1, "useRealSkin", horsestats$data0, "useRealSkinForSelf");
      copyBoolean(json1, "hideLobbyID", horsestats$data0, "hideLobbyID");
      copyString(json1, "ownName", horsestats$data0, "selfName");
      copyString(json1, "hiddenPrefix", horsestats$data0, "prefix");
      String text2 = horsestats$data0.getString("suffix", "");
      if (!text2.isBlank()) {
         json1.addProperty("customSuffix", true);
         json1.addProperty("hiddenSuffix", text2);
      }
   }

   private static void convertWeatherChanger(ProfileData horsestats$data0, JsonObject json1) {
      if (horsestats$data0.has("weatherEnum")) {
         json1.addProperty("weatherMode", switch (roundClamp(horsestats$data0.getFloat("weatherEnum", 0.0F), 0, 3)) {
            case 1 -> com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger.Type.NATURAL.id();
            case 2 -> com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger.Type.RAIN.id();
            case 3 -> com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger.Type.SNOW.id();
            default -> com.moonsworth.lunar.client.mod.render.weatherchanger.WeatherChanger.Type.CLEAR.id();
         });
      }

      if (horsestats$data0.has("strength")) {
         json1.addProperty("rainStrength", clamp(horsestats$data0.getFloat("strength", 1.0F), 0.0F, 1.0F));
      }

      copyBoolean(json1, "thunderStorm", horsestats$data0, "thunderStorm");
      copyBoolean(json1, "playThunderSound", horsestats$data0, "thunderSound");
      if (horsestats$data0.has("lightningFreq")) {
         json1.addProperty("lightningFreq", clamp(horsestats$data0.getFloat("lightningFreq", 1.0F), 1.0F, 20.0F));
      }

      if (horsestats$data0.has("radiusXZ")) {
         json1.addProperty("lightningRadiusXZ", clamp(horsestats$data0.getFloat("radiusXZ", 128.0F), 8.0F, 512.0F));
      }

      if (horsestats$data0.has("shiftY")) {
         json1.addProperty("lightningOffsetY", clamp(horsestats$data0.getFloat("shiftY", 0.0F), -64.0F, 64.0F));
      }

      copyColor(json1, "rainColor", horsestats$data0.getColor("weatherColor"));
   }

   private static void convertFovChanger(ProfileData horsestats$data0, JsonObject json1) {
      copyClampedFloat(json1, "defaultFov", horsestats$data0, "defaultFov");
      copyClampedFloat(json1, "sprintingFOV", horsestats$data0, "sprinting");
      copyClampedFloat(json1, "speedFOV", horsestats$data0, "swiftness");
      copyClampedFloat(json1, "slownessFOV", horsestats$data0, "slowness");
      copyClampedFloat(json1, "flyingFov", horsestats$data0, "flying");
      copyBoolean(json1, "dynamicSprint", horsestats$data0, "dynamicSprint");
      copyBoolean(json1, "dynamicEffects", horsestats$data0, "dynamicStatusEffect");
      copyBoolean(json1, "dynamicBow", horsestats$data0, "dynamicBowFOV");
      if (horsestats$data0.has("fovType")) {
         json1.addProperty("smoothFov", "SMOOTH".equals(horsestats$data0.getString("fovType", "VANILLA")));
      }
   }

   private static void copyClampedFloat(JsonObject json0, String text1, ProfileData horsestats$data2, String text3) {
      if (horsestats$data2.has(text3)) {
         json0.addProperty(text1, roundClamp(horsestats$data2.getFloat(text3, 70.0F), 30, 110));
      }
   }

   private static void convertChunkBorders(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "grid", horsestats$data0, "showBorders");
      convertChunkBorderLines(json1, horsestats$data0, "chunkWalls", "gridColor", "gridLineThickness");
      convertChunkBorderLines(json1, horsestats$data0, "chunkCorners", "innerChunkCornerColor", "innerCornerThickness");
      convertChunkBorderLines(json1, horsestats$data0, "outsideChunkCorners", "outerChunkCornerColor", "outerCornerThickness");
   }

   private static void convertChunkBorderLines(JsonObject json0, ProfileData horsestats$data1, String text2, String text3, String text4) {
      ProfileData horsestats$data5 = horsestats$data1.getSection(text2);
      if (horsestats$data5 != null) {
         copyColor(json0, text3, horsestats$data5.getColor("lineColor"));
         if (horsestats$data5.has("lineThickness")) {
            json0.addProperty(text4, clamp(horsestats$data5.getFloat("lineThickness", 1.0F), 1.0F, 5.0F));
         }
      }
   }

   private static void convertChat(ProfileData horsestats$data0, JsonObject json1) {
      copyBoolean(json1, "smoothChat", horsestats$data0, "smoothChat");
      if (horsestats$data0.has("smoothChatSpeed")) {
         json1.addProperty("smoothChatSpeed", roundClamp(horsestats$data0.getFloat("smoothChatSpeed", 0.25F) * 10.0F, 1, 10));
      }

      copyBoolean(json1, "unlimitedChat", horsestats$data0, "infiniteHistory");
      copyBoolean(json1, "stackMessages", horsestats$data0, "antiSpam");
      if (horsestats$data0.has("timeStamp24h")) {
         json1.addProperty("twelveHourClock", !horsestats$data0.getBoolean("timeStamp24h", true));
      }

      copyBoolean(json1, "timestampBold", horsestats$data0, "timeStampBold");
      ProfileColor horsestats2_22 = horsestats$data0.getColor("chatBackgroundColor");
      if (horsestats2_22 != null) {
         json1.addProperty("chatBackgroundOpacity", horsestats2_22.alpha() / 255.0F);
      }
   }

   private static void copyBoolean(JsonObject json0, String text1, ProfileData horsestats$data2, String text3) {
      if (horsestats$data2.has(text3)) {
         json0.addProperty(text1, horsestats$data2.getBoolean(text3, false));
      }
   }

   private static <T extends Enum<T> & com.moonsworth.lunar.client.config.option.OptionEnumValue> void copyEnum(
      JsonObject json0, String text1, Class<T> clazz2, String text3
   ) {
      if (text3 != null) {
         for (Enum value7 : (Enum[])clazz2.getEnumConstants()) {
            if (value7.name().equals(text3)) {
               json0.addProperty(text1, ((com.moonsworth.lunar.client.config.option.OptionEnumValue)value7).id());
               return;
            }
         }
      }
   }

   private static void copyColor(JsonObject json0, String text1, ProfileColor horsestats2_22) {
      JsonObject json3 = toJsonColor(horsestats2_22);
      if (json3 != null) {
         json0.add(text1, json3);
      }
   }

   private static void copyString(JsonObject json0, String text1, ProfileData horsestats$data2, String text3) {
      String text4 = horsestats$data2.getString(text3, null);
      if (text4 != null && !text4.isBlank()) {
         json0.addProperty(text1, text4);
      }
   }

   private static boolean isEnabled(JsonElement element0) {
      return element0 != null && element0.isJsonObject() && element0.getAsJsonObject().has("enabled") && element0.getAsJsonObject().get("enabled").getAsBoolean();
   }

   private static JsonObject toJsonColor(ProfileColor horsestats2_20) {
      if (horsestats2_20 == null) {
         return null;
      }

      JsonObject json1 = new JsonObject();
      if (horsestats2_20.isChroma()) {
         json1.addProperty("value", (horsestats2_20.alpha() & 0xFF) << 24 | 0xFF0000);
         json1.addProperty("chroma", true);
      } else {
         json1.addProperty("value", horsestats2_20.toRgba());
      }

      return json1;
   }

   private static void copyKeybind(JsonObject json0, String text1, ProfileData horsestats$data2, String text3) {
      String text4 = parseKeyCode(horsestats$data2.getElement(text3));
      if (text4 != null) {
         json0.addProperty(text1, text4);
      }
   }

   private static String parseKeyCode(JsonElement element0) {
      if (element0 != null && element0.isJsonObject()) {
         JsonObject json1 = element0.getAsJsonObject();
         String text2 = json1.has("keyCode") ? json1.get("keyCode").getAsString() : null;
         return text2 != null && !"KEY_UNKNOWN".equals(text2) && field1.contains(text2) ? text2 : null;
      } else {
         return null;
      }
   }

   static JsonObject parseKeybind(JsonElement element0) {
      String text1 = parseKeyCode(element0);
      if (text1 == null) {
         return null;
      }

      JsonObject json2 = element0.getAsJsonObject();
      JsonObject json3 = new JsonObject();
      json3.addProperty("value", text1);
      if (getBooleanValue(json2, "shiftDown")) {
         json3.addProperty("shift", true);
      }

      if (getBooleanValue(json2, "ctrlDown")) {
         json3.addProperty("control", true);
      }

      if (getBooleanValue(json2, "altDown")) {
         json3.addProperty("alt", true);
      }

      return json3;
   }

   private static boolean getBooleanValue(JsonObject json0, String text1) {
      JsonElement element2 = json0.get(text1);
      if (element2 == null) {
         return false;
      }

      if (element2.isJsonObject() && element2.getAsJsonObject().has("value")) {
         element2 = element2.getAsJsonObject().get("value");
      }

      return element2.isJsonPrimitive() && element2.getAsJsonPrimitive().isBoolean() && element2.getAsBoolean();
   }

   private static int roundClamp(float value0, int number1, int number2) {
      return Math.max(number1, Math.min(number2, Math.round(value0)));
   }

   private static float clamp(float value0, float value1, float value2) {
      return Math.max(value1, Math.min(value2, value0));
   }

   static com.moonsworth.lunar.client.ui.hud.HudAnchor toHudAnchor(String text0) {
      if (text0 == null) {
         return null;
      }

      return switch (text0) {
         case "TOP_LEFT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_LEFT;
         case "TOP_CENTER" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_CENTER;
         case "TOP_RIGHT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.TOP_RIGHT;
         case "MIDDLE_LEFT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.MIDDLE_LEFT;
         case "MIDDLE_CENTER" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.MIDDLE_CENTER;
         case "MIDDLE_RIGHT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.MIDDLE_RIGHT;
         case "BOTTOM_LEFT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.BOTTOM_LEFT;
         case "BOTTOM_CENTER" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.BOTTOM_CENTER_R;
         case "BOTTOM_RIGHT" -> com.moonsworth.lunar.client.ui.hud.HudAnchor.BOTTOM_RIGHT;
         default -> null;
      };
   }

   private static Set<String> buildKeyCodeNames() {
      LinkedHashSet set0 = new LinkedHashSet();

      for (KeyCode bridgetype_84 : KeyCode.values()) {
         set0.add(bridgetype_84.name());
      }

      return set0;
   }

   static String toCrosshairPattern(BadlionProfileConverter.CanvasCrosshair data30) {
      com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairGridSize gui2extension21 = com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairGridSize.fromGridLength(
         Math.min(data30.size(), 63)
      );
      boolean[] items2 = new boolean[gui2extension21.size() * gui2extension21.size()];
      int number3 = Math.floorDiv(gui2extension21.size() - data30.size(), 2);

      for (int index4 = 0; index4 < data30.size(); index4++) {
         for (int index5 = 0; index5 < data30.size(); index5++) {
            if (data30.method1()[index5 + index4 * data30.size()] >>> 24 != 0) {
               int index6 = index5 + number3;
               int number7 = index4 + number3;
               if (index6 >= 0 && index6 < gui2extension21.size() && number7 >= 0 && number7 < gui2extension21.size()) {
                  items2[index6 + number7 * gui2extension21.size()] = true;
               }
            }
         }
      }

      return new CrosshairPattern(gui2extension21, items2).method4();
   }

   static ProfileColor dominantColor(BadlionProfileConverter.CanvasCrosshair data30) {
      HashMap map1 = new HashMap();

      for (int index5 : data30.method1()) {
         if (index5 >>> 24 != 0) {
            map1.merge(index5, 1, Integer::sum);
         }
      }

      return map1.entrySet().stream().max(Entry.comparingByValue()).map(arg0x -> {
         int number1x = (Integer)arg0x.getKey();
         return new ProfileColor(number1x >> 16 & 0xFF, number1x >> 8 & 0xFF, number1x & 0xFF, number1x >>> 24 & 0xFF, false);
      }).orElse(null);
   }

   private static final class ModConverter {
      private final BadlionProfileConfig field1;
      private final BadlionProfileConverter.CanvasCrosshair field2;
      private final JsonObject field3 = new JsonObject();
      private final JsonObject field4 = new JsonObject();
      private final List<String> field5 = new ArrayList<>();

      ModConverter(BadlionProfileConfig horsestats_21, BadlionProfileConverter.CanvasCrosshair data32) {
         this.KEY_CODE_NAMES = horsestats_21;
         this.DEFAULT_SCALE = data32;
         this.DEFAULT_HUD_HEIGHTS.addProperty("version", ConfigMigrator.field2);
         this.NO_OP.addProperty("version", ConfigMigrator.field2);
      }

      void convert(String text1, String text2, String text3, BiConsumer<ProfileData, JsonObject> biconsumer4) {
         ProfileData horsestats$data5 = this.convert1(text1, text2);
         if (horsestats$data5 != null) {
            JsonObject json6 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject(text1);
            JsonObject json7 = new JsonObject();
            this.convertDirectionHud(text3, json6, json7);
            this.convertPackDisplay(text2, json7);
            biconsumer4.accept(horsestats$data5, json7);
            this.convert2(json6, json7);
         }
      }

      void convert(
         String text1, String text2, String text3, String text4, BiConsumer<ProfileData, JsonObject> biconsumer5, BiConsumer<ProfileData, JsonObject> biconsumer6
      ) {
         ProfileData horsestats$data7 = this.convert1(text1, text2);
         if (horsestats$data7 != null) {
            JsonObject json8 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject(text1);
            JsonObject json9 = new JsonObject();
            biconsumer5.accept(horsestats$data7, json9);
            this.convert2(json8, json9);
            JsonObject json10 = new JsonObject();
            JsonObject json11 = new JsonObject();
            this.convertDirectionHud(text3, json10, json11);
            this.convertPackDisplay(text2, json11);
            biconsumer6.accept(horsestats$data7, json11);
            this.convert2(json10, json11);
            if (!json10.entrySet().isEmpty()) {
               json8.add(text4, json10);
            }
         }
      }

      void convertKeystrokes(String text1, String text2, BiConsumer<ProfileData, JsonObject> biconsumer3) {
         ProfileData horsestats$data4 = this.convert1(text1, text2);
         if (horsestats$data4 != null) {
            JsonObject json5 = new JsonObject();
            biconsumer3.accept(horsestats$data4, json5);
            this.convert2(this.DEFAULT_HUD_HEIGHTS.getAsJsonObject(text1), json5);
         }
      }

      void convertKeystrokeAnimation() {
         ProfileData horsestats$data1 = this.convert1("TOGGLE_SNEAK", "toggleSprint");
         if (horsestats$data1 != null) {
            JsonObject json2 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject("TOGGLE_SNEAK");
            JsonObject json3 = new JsonObject();
            json3.addProperty("toggleSprint", horsestats$data1.isEnabled());
            BadlionProfileConverter.copyKeybind(json3, "keybindSprint", horsestats$data1, "toggleKeybind");
            if (horsestats$data1.has("flyModifier")) {
               json3.addProperty("flyBoostAmount", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("flyModifier", 4.0F), 2, 8));
            }

            ProfileData horsestats$data4 = this.KEY_CODE_NAMES.method4("toggleSneak");
            if (horsestats$data4 != null) {
               this.field5.add("toggleSneak");
               json3.addProperty("toggleSneak", horsestats$data4.isEnabled());
               BadlionProfileConverter.copyKeybind(json3, "keybindSneak", horsestats$data4, "toggleKeybind");
               BadlionProfileConverter.copyBoolean(json3, "toggleSneakContainer", horsestats$data4, "inventorySneak");
            }

            json2.addProperty("enabled", horsestats$data1.isEnabled() || horsestats$data4 != null && horsestats$data4.isEnabled());
            this.convert2(json2, json3);
            JsonObject json5 = new JsonObject();
            JsonObject json6 = new JsonObject();
            this.convertDirectionHud("togglesprint", json5, json6);
            this.convertPackDisplay("toggleSprint", json6);
            this.convert2(json5, json6);
            if (!json5.entrySet().isEmpty()) {
               json2.add("TOGGLE_SNEAK_HUD_CHILD", json5);
            }
         }
      }

      void convertCps() {
         boolean flag1 = false;
         JsonObject json2 = new JsonObject();
         String[][] items3 = new String[][]{
            {"autoGG", "autoGG"}, {"autoFriend", "autoFriend"}, {"levelHead", "levelHead"}, {"autotip", "autoTip"}, {"autoWho", "autoWho"}
         };

         for (String[] items7 : items3) {
            ProfileData horsestats$data8 = this.KEY_CODE_NAMES.method4(items7[0]);
            if (horsestats$data8 != null) {
               this.field5.add(items7[0]);
               json2.addProperty(items7[1], horsestats$data8.isEnabled());
               flag1 |= horsestats$data8.isEnabled();
            }
         }

         if (!json2.entrySet().isEmpty() && !this.DEFAULT_HUD_HEIGHTS.has("HYPIXEL_MOD")) {
            JsonObject json9 = new JsonObject();
            json9.addProperty("enabled", flag1);
            json9.addProperty("seen", true);
            json9.add("options", json2);
            this.DEFAULT_HUD_HEIGHTS.add("HYPIXEL_MOD", json9);
         }
      }

      void convertCoordinates() {
         JsonObject json1 = new JsonObject();
         JsonObject json2 = new JsonObject();
         boolean flag3 = false;
         boolean flag4 = false;
         ProfileData horsestats$data5 = this.KEY_CODE_NAMES.method4("bedwarsBeds");
         if (horsestats$data5 != null) {
            this.field5.add("bedwarsBeds");
            json2.addProperty("coloredBeds", horsestats$data5.isEnabled());
            flag3 |= horsestats$data5.isEnabled();
            flag4 = true;
         }

         String[][] items6 = new String[][]{
            {"bedwarsDisplay", "HYPIXEL_BEDWARS_STATS_CHILD", "hystats"},
            {"bedwarsTeams", "HYPIXEL_BEDWARS_TEAM_DISPLAY_CHILD", null},
            {"bedwarsDisplayMod", "HYPIXEL_BEDWARS_UPGRADE_DISPLAY_CHILD", null}
         };

         for (String[] items10 : items6) {
            ProfileData horsestats$data11 = this.KEY_CODE_NAMES.method4(items10[0]);
            if (horsestats$data11 != null) {
               this.field5.add(items10[0]);
               flag4 = true;
               flag3 |= horsestats$data11.isEnabled();
               JsonObject json12 = new JsonObject();
               json12.addProperty("enabled", horsestats$data11.isEnabled());
               if (items10[2] != null) {
                  JsonObject json13 = new JsonObject();
                  this.convertDirectionHud(items10[2], json12, json13);
                  this.convert2(json12, json13);
               }

               json1.add(items10[1], json12);
            }
         }

         if (flag4 && !this.DEFAULT_HUD_HEIGHTS.has("HYPIXEL_BEDWARS")) {
            json1.addProperty("enabled", flag3);
            json1.addProperty("seen", true);
            this.convert2(json1, json2);
            this.DEFAULT_HUD_HEIGHTS.add("HYPIXEL_BEDWARS", json1);
         }
      }

      void convertArmorStatus() {
         ProfileData horsestats$data1 = this.convert1("SKYBLOCK", "skyblockAddons");
         if (horsestats$data1 != null) {
            JsonObject json2 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject("SKYBLOCK");
            JsonObject json3 = new JsonObject();
            BadlionProfileConverter.copyBoolean(json3, "skyblockAutocompleteWarps", horsestats$data1, "warpTabComplete");
            this.convert2(json2, json3);
            String[][] items4 = new String[][]{
               {"showDungeonDeathCounter", "SKYBLOCK_DEATH_COUNTER_HUD", "dungeondeathcounter"},
               {"showDungeonMilestoneDisplay", "SKYBLOCK_DUNGEON_MILESTONE_HUD", "dungeonmilestone"},
               {"dungeonSecretDisplay", "DUNGEON_SECRET_WAYPOINTS", null},
               {"invinciblilityHud", "SKYBLOCK_INVINCIBILITY_HUD", null},
               {"lividHealthDisplay", "SKYBLOCK_LIVID_SOLVER_HUD", "lividhealthdisplay"},
               {"showDungeonsHealerCooldownDisplay", "SKYBLOCK_REVIVE_HUD", "healerscooldownhud"},
               {"enableDungeonScoreCalculator", "SKYBLOCK_DUNGEON_SCORE_ALERT", null},
               {"enableDungeonProfitCalculator", "SKYBLOCK_CHEST_PROFIT", null},
               {"highlightUnopenedCroesusChests", "SKYBLOCK_CROESUS_CHESTS", null},
               {"glowingDungeonTeammates", "SKYBLOCK_DUNGEON_TEAMMATE_HIGHLIGHT", null},
               {"highlightTrashDungeonsLootInTrade", "SKYBLOCK_HIGHLIGHT_TRASH_DUNGEON_ITEMS", null},
               {"secretRoutesEnabled", "SKYBLOCK_DUNGEON_ROUTES", null},
               {"showDungeon3FireFreezeTimer", "SKYBLOCK_FIRE_FREEZE_TIMER", null},
               {"showDungeon4BearSpawnTimer", "SKYBLOCK_FLOOR_FOUR", null},
               {"showM3GuardianHp", "SKYBLOCK_PROFESSOR_GUARDIAN_HP", null},
               {"enableTerracorraRespawnTimer", "SKYBLOCK_TERRACOTTA_TIMER", null},
               {"showTerminalCompletionCounts", "SKYBLOCK_UNFINISHED_TERMINALS", null},
               {"colorBlazeSlayer", "SKYBLOCK_BLAZE_SLAYER", null},
               {"showHealthText", "SKYBLOCK_HEALTH_HUD", "skyblockaddonshealthtextbar"},
               {"showManaText", "SKYBLOCK_MANA_HUD", "skyblockaddonsmanatextbar"},
               {"showDefenceText", "SKYBLOCK_DEF_HUD", "skyblockaddonsdefencetextbar"},
               {"showSpeedPercentage", "SKYBLOCK_SPEED_HUD", "skyblockaddonsspeedpercentagebar"},
               {"showSkillBar", "SKYBLOCK_SKILL_PROGRESS_BAR_HUD", "skyblockaddonsskillbar"},
               {"enableExpTracker", "SKYBLOCK_SKILL_XP_TRACKER_HUD", "skyblockskilltracker"},
               {"findFairySouls", "SKYBLOCK_FAIRY_SOULS", null},
               {"enableTerminalSolver", "SKYBLOCK_TERMINAL_SOLVERS", null},
               {"enableTicTacToeSolver", "SKYBLOCK_TIC_TAC_TOE", null},
               {"enableWaterRoomSolver", "SKYBLOCK_WATER_ROOM", null},
               {"enableIceFillSolver", "SKYBLOCK_ICE_FILL", null},
               {"enableBoulderSolver", "SKYBLOCK_BOULDER", null},
               {"enableSilverfishPathSolver", "SKYBLOCK_ICE_PATH", null},
               {"enableTeleportPadSolver", "SKYBLOCK_TP_MAZE", null},
               {"enableExperimentationSolver", "SKYBLOCK_EXPERIMENT_SOLVERS", null},
               {"enableBlazeSolver", "SKYBLOCK_HIGHER_LOWER", null},
               {"enableTriviaAskerSolver", "SKYBLOCK_QUIZ", null},
               {"enableWeirdosRiddleSolver", "SKYBLOCK_THREE_WEIRDOS", null},
               {"showPowderMiningTracker", "SKYBLOCK_POWDER_TRACKER_HUD", "powderminingtracker"},
               {"showScathaCounterHud", "SKYBLOCK_SCATHA_TRACKER_HUD", "scathadisplay"},
               {"minesOfDivanMetalDetectorHelper", "SKYBLOCK_METAL_DETECTOR", "metaldetectordisplay"},
               {"minesOfDivanAllToolsAlert", "SKYBLOCK_DIVAN_ALL_TOOLS_ALERT", null},
               {"showTreasureChestHelper", "SKYBLOCK_TREASURE_CHEST_HELPER", null},
               {"showCrystalHollowsMap", "SKYBLOCK_CRYSTAL_HOLLOWS_MAP", "crystal hollows map"},
               {"showGemstoneMiningProfit", "SKYBLOCK_GEMSTONE_PROFIT", "gemstoneminingprofit"},
               {"wormSpawnWarning", "SKYBLOCK_WORM_SCATHA_ALERT", null},
               {"skyMallBuffWarning", "SKYBLOCK_SKY_MALL_BUFF_ALERT", null},
               {"glacialMineshaftCorpseLocations", "SKYBLOCK_MINESHAFT_CORPSE_WAYPOINTS", null},
               {"showPickonimbusDurability", "SKYBLOCK_PICKONIMBUS_DURABILITY", null},
               {"highlightEnderNodes", "SKYBLOCK_END_NODE_HIGHLIGHT", null},
               {"highlightGlowingMushrooms", "SKYBLOCK_GLOWING_MUSHROOM_HIGHLIGHT", null},
               {"legendarySeaCreatureWarning", "SKYBLOCK_SEA_CREATURE_ALERT", null},
               {"enableFishBobTimer", "SKYBLOCK_FISHING_REEL_TIMER_HUD", null},
               {"itemRarityShowcase", "SKYBLOCK_RARITY_BACKGROUND", null},
               {"inventoryButtonsEnabled", "SKYBLOCK_INVENTORY_BUTTONS", null},
               {"showEtherwarpOverlay", "ETHERWARP_PREVIEW", null},
               {"enableCropMilestoneTracker", "SKYBLOCK_CROP_TRACKER_HUD", "skyblockcropmilestonetracker"},
               {"showDenRainTimer", "SKYBLOCK_SPIDER_DEN_RAIN_HUD", "denraintimer"},
               {"enableAdBlock", "SKYBLOCK_WEBSITE_ADBLOCK", null},
               {"hidePlayersNearNPCs", "SKYBLOCK_HIDE_PLAYERS_NEAR_NPC", null}
            };

            for (String[] items8 : items4) {
               if (horsestats$data1.has(items8[0])) {
                  this.convertScoreboard(json2, horsestats$data1.getBoolean(items8[0], false), items8[1], items8[2]);
               }
            }

            this.convertBossBar(
               horsestats$data1,
               json2,
               "SKYBLOCK_DRAGON_FEATURES",
               null,
               "m7p5DragonBoxes",
               "m7p5DragonAlert",
               "m7p5DragonCountdown",
               "m7p5RelicWaypoints",
               "m7p5DimensionalSlash",
               "m7ptDragonNearAlert",
               "showM7DragonHp"
            );
            this.convertBossBar(horsestats$data1, json2, "SKYBLOCK_BOSS_TIMER", "slayertimer", "enableSlayerTimer", "enableSlayerTimerHUD");
            this.convertBossBar(
               horsestats$data1,
               json2,
               "SKYBLOCK_MINI_BOSS_ALERT",
               null,
               "slayerMinibossSpawnAlert",
               "slayerMinibossHighlight",
               "slayerMinibossSpawnPing",
               "slayerAreaMinibossHighlight"
            );
            this.convertBossBar(
               horsestats$data1,
               json2,
               "SKYBLOCK_ENDERMAN_SLAYER",
               null,
               "alertVoidgloomBeacon",
               "alertVoidgloomSkull",
               "voidgloomBeaconHighlight",
               "voidgloomSkullHighlight",
               "voidgloomBeaconTimer",
               "voidgloomBeaconBeam",
               "showVoidgloomLazerTimer"
            );
            this.convertBossBar(
               horsestats$data1,
               json2,
               "SKYBLOCK_BURROW_LOCATING",
               null,
               "mythologicalRitualShowGuess",
               "mythologicalRitualShowWaypoints",
               "mythologicalRitualPingNearbyParticles",
               "mythologicalRitualShowBurrowInChat"
            );
            this.convertBossBar(
               horsestats$data1,
               json2,
               "SKYBLOCK_PRICE_IN_LORE",
               null,
               "showItemPriceInfo",
               "showBinItemPrices",
               "showBazaarInstaSell",
               "showBazaarInstaBuy",
               "showNpcSell",
               "showEstimatedPrice"
            );
            this.convertBossBar(horsestats$data1, json2, "SKYBLOCK_PARTY_FINDER", null, "partyFinderInfo", "partyShowPlayers");
            this.convertBossBar(horsestats$data1, json2, "SKYBLOCK_PROTECT_ITEM", null, "dropConfirmation", "preventDropSellRareItems");
            if (this.convertMemory(horsestats$data1, "replaceRomanNumerals", "hideGreyEnchants", "useCustomEnchantColors") && !json2.has("SKYBLOCK_ENCHANTS")) {
               JsonObject json14 = new JsonObject();
               json14.addProperty("enabled", this.convertReachDisplay(horsestats$data1, "replaceRomanNumerals", "hideGreyEnchants", "organizeEnchantments", "useCustomEnchantColors"));
               JsonObject json17 = new JsonObject();
               BadlionProfileConverter.copyBoolean(json17, "skyblockReplaceRoman", horsestats$data1, "replaceRomanNumerals");
               BadlionProfileConverter.copyBoolean(json17, "skyblockHideVanillaEnchants", horsestats$data1, "hideGreyEnchants");
               BadlionProfileConverter.copyBoolean(json17, "skyblockEnchantColors", horsestats$data1, "useCustomEnchantColors");
               ProfileData horsestats$data20 = horsestats$data1.getSection("customEnchantColorSettings");
               if (horsestats$data20 != null) {
                  BadlionProfileConverter.copyBoolean(json17, "skyblockHypermaxBold", horsestats$data20, "makeHypermaxBold");
               }

               this.convert2(json14, json17);
               json2.add("SKYBLOCK_ENCHANTS", json14);
            }

            if (this.convertMemory(horsestats$data1, "formatDamage", "hideNonCrits") && !json2.has("SKYBLOCK_DAMAGE_SPLASH")) {
               JsonObject json15 = new JsonObject();
               json15.addProperty("enabled", this.convertReachDisplay(horsestats$data1, "formatDamage", "hideNonCrits"));
               JsonObject json18 = new JsonObject();
               BadlionProfileConverter.copyBoolean(json18, "truncateDamageSplash", horsestats$data1, "formatDamage");
               BadlionProfileConverter.copyBoolean(json18, "hideNonCrits", horsestats$data1, "hideNonCrits");
               this.convert2(json15, json18);
               json2.add("SKYBLOCK_DAMAGE_SPLASH", json15);
            }

            ProfileData horsestats$data16 = horsestats$data1.getSection("dungeonMap");
            if (horsestats$data16 != null) {
               this.convertScoreboard(json2, horsestats$data16.isEnabled(), "BETTERMAP_PRIMARY", "dungeonmap");
            }

            ProfileData horsestats$data19 = horsestats$data1.getSection("dungeonMapTwo");
            if (horsestats$data19 != null) {
               this.convertScoreboard(json2, horsestats$data19.isEnabled(), "BETTERMAP_SECONDARY", "dungeonmap2");
            }

            ProfileData horsestats$data21 = horsestats$data1.getSection("fishingUtilitiesDisplay");
            if (horsestats$data21 != null) {
               this.convertScoreboard(json2, horsestats$data21.getBoolean("showFishingUtilities", false), "SKYBLOCK_FISHING_INFO_HUD", "fishing utilities");
            }

            ProfileData horsestats$data22 = horsestats$data1.getSection("quiverDisplay");
            if (horsestats$data22 != null) {
               this.convertScoreboard(json2, horsestats$data22.getBoolean("enableQuiverDisplay", false), "SKYBLOCK_QUIVER_HUD", "quiverdisplay");
            }

            boolean flag9 = horsestats$data1.getBoolean("actionsUntilNextLevel", false)
               || horsestats$data1.getBoolean("skillPercentageInsteadOfXP", false)
               || horsestats$data1.getBoolean("showSkillXPGained", false)
               || horsestats$data1.getBoolean("showCollectionProgress", false);
            if (horsestats$data1.has("showSkillXPGained")) {
               this.convertScoreboard(json2, flag9, "SKYBLOCK_SKILL_PROGRESS_HUD", "skyblockaddonsskilltextbar");
            }

            if (horsestats$data1.has("enableDungeonTimers") || horsestats$data1.has("enableKuudraTimers")) {
               boolean flag10 = horsestats$data1.getBoolean("enableDungeonTimers", false);
               boolean flag11 = horsestats$data1.getBoolean("enableKuudraTimers", false);
               JsonObject json12 = new JsonObject();
               json12.addProperty("enabled", flag10 || flag11);
               JsonObject json13 = new JsonObject();
               json13.addProperty("dungeonTimer", flag10);
               json13.addProperty("kuudraTimer", flag11);
               this.convertDirectionHud("dungeontimers", json12, json13);
               json12.add("options", json13);
               json2.add("SKYBLOCK_DUNGEON_TIMER", json12);
            }

            if (horsestats$data1.getBoolean("itemPickupLog", false)) {
               JsonObject json23 = this.DEFAULT_HUD_HEIGHTS.has("ITEM_TRACKER") ? this.DEFAULT_HUD_HEIGHTS.getAsJsonObject("ITEM_TRACKER") : new JsonObject();
               boolean flag24 = json23.has("enabled") && json23.get("enabled").getAsBoolean();
               json23.addProperty("enabled", true);
               json23.addProperty("seen", true);
               JsonObject json25 = json23.has("options") ? json23.getAsJsonObject("options") : new JsonObject();
               if (!flag24) {
                  json25.addProperty("skyblockOnly", true);
                  this.convertDirectionHud("itempickuplog", json23, json25);
               }

               json23.add("options", json25);
               this.DEFAULT_HUD_HEIGHTS.add("ITEM_TRACKER", json23);
            }
         }
      }

      private void convertScoreboard(JsonObject json1, boolean flag2, String text3, String text4) {
         if (!json1.has(text3)) {
            JsonObject json5 = new JsonObject();
            json5.addProperty("enabled", flag2);
            if (text4 != null) {
               JsonObject json6 = new JsonObject();
               this.convertDirectionHud(text4, json5, json6);
               this.convert2(json5, json6);
            }

            json1.add(text3, json5);
         }
      }

      private void convertBossBar(ProfileData horsestats$data1, JsonObject json2, String text3, String text4, String... items5) {
         if (this.convertMemory(horsestats$data1, items5)) {
            this.convertScoreboard(json2, this.convertReachDisplay(horsestats$data1, items5), text3, text4);
         }
      }

      private boolean convertMemory(ProfileData horsestats$data1, String... items2) {
         for (String text6 : items2) {
            if (horsestats$data1.has(text6)) {
               return true;
            }
         }

         return false;
      }

      private boolean convertReachDisplay(ProfileData horsestats$data1, String... items2) {
         for (String text6 : items2) {
            if (horsestats$data1.getBoolean(text6, false)) {
               return true;
            }
         }

         return false;
      }

      void convertTntTimer() {
         ProfileData horsestats$data1 = this.convert1("AUTO_TEXT_HOTKEY", "autoText");
         if (horsestats$data1 != null) {
            JsonElement element2 = horsestats$data1.getElement("entries");
            if (element2 != null && element2.isJsonArray()) {
               JsonObject json3 = new JsonObject();
               int index4 = 1;

               for (JsonElement element6 : element2.getAsJsonArray()) {
                  if (element6.isJsonObject()) {
                     JsonObject json7 = element6.getAsJsonObject();
                     if (json7.has("command") && !json7.get("command").getAsString().isBlank()) {
                        JsonObject json8 = new JsonObject();
                        json8.addProperty("value", json7.get("command").getAsString());
                        JsonObject json9 = BadlionProfileConverter.parseKeybind(json7.get("toggleKeybind"));
                        if (json9 != null) {
                           json8.add(index4 + "hotkey", json9);
                        }

                        json3.add(index4 + "hotkey", json8);
                        index4++;
                     }
                  }
               }

               this.convert2(this.DEFAULT_HUD_HEIGHTS.getAsJsonObject("AUTO_TEXT_HOTKEY"), json3);
            }
         }
      }

      void convertCooldowns() {
         ProfileData horsestats$data1 = this.convert1("CROSSHAIR", "crosshair");
         if (horsestats$data1 != null) {
            JsonObject json2 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject("CROSSHAIR");
            JsonObject json3 = new JsonObject();
            if (horsestats$data1.has("visibleThirdPerson")) {
               json3.addProperty("showInF5", horsestats$data1.getBoolean("visibleThirdPerson", true));
            }

            this.convert2(json2, json3);
            json2.add("CROSSHAIR_NORMAL", this.convertPotionStatus(horsestats$data1, horsestats$data1.getColor("crosshairColor"), true));
            if (horsestats$data1.getBoolean("highlightHostile", false)) {
               json2.add("CROSSHAIR_ENEMY", this.convertPotionStatus(horsestats$data1, horsestats$data1.getColor("hostileColor"), true));
            }

            if (horsestats$data1.getBoolean("highlightPassive", false)) {
               json2.add("CROSSHAIR_FRIENDLY", this.convertPotionStatus(horsestats$data1, horsestats$data1.getColor("passiveColor"), true));
            }
         }
      }

      private JsonObject convertPotionStatus(ProfileData horsestats$data1, ProfileColor horsestats2_22, boolean flag3) {
         JsonObject json4 = new JsonObject();
         json4.addProperty("enabled", flag3);
         JsonObject json5 = new JsonObject();
         if (this.DEFAULT_SCALE != null) {
            json5.addProperty("crosshairMode", "crosshairModeCustom");
            json4.addProperty("customCrosshair", BadlionProfileConverter.toCrosshairPattern(this.DEFAULT_SCALE));
            ProfileColor horsestats2_26 = BadlionProfileConverter.dominantColor(this.DEFAULT_SCALE);
            if (horsestats2_26 != null) {
               horsestats2_22 = horsestats2_26;
            }
         } else {
            json5.addProperty("crosshairMode", "crosshairModeSimple");
         }

         String text10 = horsestats$data1.getString("selected", null);
         boolean flag7 = text10 != null && text10.startsWith("CIRCLE");
         BadlionProfileConverter.copyEnum(json5, "crosshairShape", CrosshairShape.class, text10);
         if (horsestats$data1.has("widthS") || horsestats$data1.has("width")) {
            json5.addProperty("crosshairWidth", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("widthS", horsestats$data1.getFloat("width", 5.0F)) / 2.0F, 0, 16));
         }

         if (horsestats$data1.has("heightS") || horsestats$data1.has("height")) {
            json5.addProperty("crosshairHeight", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("heightS", horsestats$data1.getFloat("height", 5.0F)) / 2.0F, 0, 16));
         }

         if (horsestats$data1.has("gapS") || horsestats$data1.has("gap")) {
            float value8 = horsestats$data1.getFloat("gapS", horsestats$data1.getFloat("gap", 0.0F));
            json5.addProperty("crosshairGap", BadlionProfileConverter.roundClamp(flag7 ? value8 : value8 / 2.0F, 0, 8));
         }

         if (horsestats$data1.has("thicknessS") || horsestats$data1.has("thickness")) {
            json5.addProperty("crosshairThickness", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("thicknessS", horsestats$data1.getFloat("thickness", 2.0F)) / 2.0F, 1, 5));
         }

         boolean flag11 = horsestats$data1.getBoolean("dot", false);
         json5.addProperty("crosshairDot", flag11);
         if (flag11) {
            json5.addProperty("dotSize", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("dotSize", 1.0F), 1, 16));
            ProfileColor horsestats2_29 = horsestats$data1.getColor("dotColor");
            if (horsestats2_29 != null) {
               json5.addProperty("customDotColor", true);
               BadlionProfileConverter.copyColor(json5, "dotColor", horsestats2_29);
            }

            if (horsestats$data1.getBoolean("dotOutline", false)) {
               json5.addProperty("dotOutline", true);
               json5.addProperty("dotOutlineThickness", 1.0F);
            }
         }

         BadlionProfileConverter.copyBoolean(json5, "crosshairOutline", horsestats$data1, "outline");
         BadlionProfileConverter.copyColor(json5, "outlineColor", horsestats$data1.getColor("outlineColor"));
         if (horsestats$data1.has("outlineThickness")) {
            float value12 = flag7 ? 1.0F : horsestats$data1.getFloat("outlineThickness", 1.0F) / 2.0F;
            json5.addProperty("outlineThickness", BadlionProfileConverter.clamp(value12, 0.0F, 1.0F));
         }

         json5.addProperty("crosshairColorVanilla", horsestats$data1.getBoolean("vanillaBlendering", false));
         BadlionProfileConverter.copyColor(json5, "color", horsestats2_22);
         BadlionProfileConverter.copyBoolean(json5, "crosshairDynamicBow", horsestats$data1, "dynamicBow");
         BadlionProfileConverter.copyBoolean(json5, "crosshairDynamicAttack", horsestats$data1, "dynamicAttack");
         if (horsestats$data1.getBoolean("shiftColor", false) || horsestats$data1.has("shiftThreshold")) {
            BadlionProfileConverter.copyBoolean(json5, "healthColorShift", horsestats$data1, "shiftColor");
            if (horsestats$data1.has("shiftThreshold")) {
               json5.addProperty("healthShiftThreshold", BadlionProfileConverter.roundClamp(horsestats$data1.getFloat("shiftThreshold", 5.0F), 1, 20));
            }
         }

         json4.add("options", json5);
         return json4;
      }

      private void convertDirectionHud(String text1, JsonObject json2, JsonObject json3) {
         HudPosition horsestats$data24 = this.KEY_CODE_NAMES.method5(text1);
         if (horsestats$data24 != null) {
            com.moonsworth.lunar.client.ui.hud.HudAnchor gui2extension25 = BadlionProfileConverter.toHudAnchor(horsestats$data24.getQuadrant());
            if (gui2extension25 != null) {
               json2.addProperty("position", gui2extension25.id());

               float value6 = switch (gui2extension25.getHorizontal()) {
                  case MIDDLE -> horsestats$data24.anchorX() + horsestats$data24.thirdWidth() / 2.0F;
                  case RIGHT -> horsestats$data24.anchorX() + horsestats$data24.thirdWidth();
                  default -> horsestats$data24.anchorX();
               };

               float value7 = switch (gui2extension25.getVertical()) {
                  case MIDDLE -> horsestats$data24.anchorY() + horsestats$data24.thirdHeight() / 2.0F;
                  case BOTTOM -> horsestats$data24.anchorY() + horsestats$data24.thirdHeight();
                  default -> horsestats$data24.anchorY();
               };
               float value8 = (horsestats$data24.pixelX() - value6) / 2.0F;
               float value9 = (horsestats$data24.pixelY() - value7) / 2.0F;
               if (value8 != 0.0F) {
                  json2.addProperty("x", value8);
               }

               if (value9 != 0.0F) {
                  json2.addProperty("y", value9);
               }

               Float value10 = BadlionProfileConverter.DEFAULT_HUD_HEIGHTS.get(text1);
               float value11 = 1.0F;
               if (horsestats$data24.isComputed()) {
                  value11 = horsestats$data24.scale() / 2.0F;
               } else if (value10 != null && horsestats$data24.getHeight() > 0.0F) {
                  value11 = horsestats$data24.getHeight() / value10;
               }

               if (Math.abs(value11 - 1.0F) > 0.01F) {
                  json3.addProperty("scale", BadlionProfileConverter.clamp(value11, 0.25F, 5.0F));
               }
            }
         }
      }

      private void convertPackDisplay(String text1, JsonObject json2) {
         boolean flag3 = this.KEY_CODE_NAMES.method6();
         json2.addProperty("brackets", flag3);
         json2.addProperty("background", !flag3);
         if (flag3) {
            BadlionProfileConverter.copyColor(json2, "bracketColor", ProfileColor.parse(this.KEY_CODE_NAMES.method8(text1, "bracketsColor")));
            BadlionProfileConverter.copyColor(json2, "textColor", ProfileColor.parse(this.KEY_CODE_NAMES.method8(text1, "valueColor")));
            this.convertPackTextMode(json2, "textShadow", this.KEY_CODE_NAMES.method8(text1, "textShadow"));
         } else {
            JsonElement element4 = this.KEY_CODE_NAMES.method7(text1, "backgroundColor");
            if (element4 != null && element4.isJsonObject()) {
               json2.addProperty("background", BadlionProfileConverter.isEnabled(element4));
               BadlionProfileConverter.copyColor(json2, "backgroundColor", ProfileColor.parse(element4));
            }

            JsonElement element5 = this.KEY_CODE_NAMES.method7(text1, "backgroundBorderColor");
            if (element5 != null && element5.isJsonObject()) {
               json2.addProperty("border", BadlionProfileConverter.isEnabled(element5));
               BadlionProfileConverter.copyColor(json2, "borderColor", ProfileColor.parse(element5));
            }

            BadlionProfileConverter.copyColor(json2, "textColor", ProfileColor.parse(this.KEY_CODE_NAMES.method7(text1, "textColor")));
            this.convertPackTextMode(json2, "textShadow", this.KEY_CODE_NAMES.method7(text1, "textShadow"));
         }
      }

      private void convertPackTextMode(JsonObject json1, String text2, JsonElement element3) {
         if (element3 != null && element3.isJsonObject() && element3.getAsJsonObject().has("value")) {
            json1.addProperty(text2, element3.getAsJsonObject().get("value").getAsBoolean());
         }
      }

      void convertSpeedometer(String text1, String text2, String... items3) {
         this.convertBlockInfo(text1, text2, BadlionProfileConverter.NO_OP, items3);
      }

      void convertBlockInfo(String text1, String text2, BiConsumer<ProfileData, JsonObject> biconsumer3, String... items4) {
         ProfileData horsestats$data5 = this.KEY_CODE_NAMES.method4(text2);
         if (horsestats$data5 != null && this.DEFAULT_HUD_HEIGHTS.has(text1)) {
            JsonObject json6 = this.DEFAULT_HUD_HEIGHTS.getAsJsonObject(text1);

            for (String text10 : items4) {
               JsonObject json11 = json6.has(text10) ? json6.getAsJsonObject(text10) : new JsonObject();
               JsonObject json12 = new JsonObject();
               this.convertPackDisplay(text2, json12);
               biconsumer3.accept(horsestats$data5, json12);
               this.convert2(json11, json12);
               if (!json11.entrySet().isEmpty()) {
                  json6.add(text10, json11);
               }
            }
         }
      }

      void convertMinimap() {
         ProfileData horsestats$data1 = this.KEY_CODE_NAMES.method4("betterframesConfig");
         if (horsestats$data1 != null) {
            this.field5.add("betterframesConfig");
            if (horsestats$data1.has("smartDisconnect")) {
               this.NO_OP.addProperty("smartDisconnect", horsestats$data1.getBoolean("smartDisconnect", false));
            }
         }
      }

      private ProfileData convertPlayerList(String text1, String text2) {
         ProfileData horsestats$data3 = this.KEY_CODE_NAMES.method4(text2);
         if (horsestats$data3 != null && !this.DEFAULT_HUD_HEIGHTS.has(text1)) {
            JsonObject json4 = new JsonObject();
            json4.addProperty("enabled", horsestats$data3.isEnabled());
            json4.addProperty("seen", true);
            this.DEFAULT_HUD_HEIGHTS.add(text1, json4);
            this.field5.add(text2);
            return horsestats$data3;
         } else {
            return null;
         }
      }

      private void convertPing(JsonObject json1, JsonObject json2) {
         if (!json2.entrySet().isEmpty()) {
            json1.add("options", json2);
         }
      }

      ConvertedProfile convertZoom() {
         LinkedHashSet set1 = new LinkedHashSet<>(this.KEY_CODE_NAMES.method3());
         this.field5.forEach(set1::remove);
         return new ConvertedProfile(this.DEFAULT_HUD_HEIGHTS, this.NO_OP, this.convert4(), this.convert4(), List.copyOf(this.field5), List.copyOf(set1));
      }

      private JsonObject convertMotionBlur() {
         JsonObject json1 = new JsonObject();
         json1.addProperty("version", ConfigMigrator.field2);
         return json1;
      }
   }

   public class CanvasCrosshair {
      private final int field1;
      private final int[] field2;

      public CanvasCrosshair(int number1, int[] items2) {
         this.KEY_CODE_NAMES = number1;
         this.DEFAULT_SCALE = items2;
      }

      public int size() {
         return this.KEY_CODE_NAMES;
      }

      public int[] convert() {
         return this.DEFAULT_SCALE;
      }
   }
}

package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.ui.hud.HudAlignment;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

public final class FeatherProfileConverter {
   private static final Map<Integer, String> field1 = buildVkKeyCodes();
   private static final Map<Integer, String> field2 = buildGlfwKeyCodes();
   private static final Map<String, String> field3 = new LinkedHashMap<String, String>() {
      {
         this.put("helmet:", "ARMORSTATUS_HELMET_CHILD");
         this.put("chestplate:", "ARMORSTATUS_CHESTPLATE_CHILD");
         this.put("leggings:", "ARMORSTATUS_LEGGINGS_CHILD");
         this.put("bootsConfig:", "ARMORSTATUS_BOOTS_CHILD");
         this.put("mainhand:", "ARMORSTATUS_HELD_ITEM_CHILD");
         this.put("offhand:", "ARMORSTATUS_OFF_HAND_HELD_ITEM_CHILD");
      }
   };
   private static final BiConsumer<FeatherProfileConfig.Data, JsonObject> NO_OP = (arg0, arg1) -> {};

   public FeatherProfileConverter() {
   }

   public FeatherConvertedProfile convert(FeatherProfileConfig horsestats31) {
      return this.convert(horsestats31, null, null);
   }

   public FeatherConvertedProfile convert(FeatherProfileConfig horsestats31, JsonArray array2) {
      return this.convert(horsestats31, array2, null);
   }

   public FeatherConvertedProfile convert(FeatherProfileConfig horsestats31, JsonArray array2, JsonObject json3) {
      FeatherProfileConverter.Data data4 = new FeatherProfileConverter.Data(horsestats31);
      data4.method1("FPS", "fps", (arg0, arg1x) -> copyBoolean(arg1x, "reverseOrder", arg0, "reversed"));
      data4.method1("CPS", "cps", (arg0, arg1x) -> {
         copyBoolean(arg1x, "showCPSText", arg0, "showCPSText");
         copyBoolean(arg1x, "rightClick", arg0, "right");
         copyColor(arg1x, "lineColor", arg0.method8("lineColor"));
      });
      data4.method1("KEYSTROKES", "keystrokes", FeatherProfileConverter::convertKeystrokes);
      data4.method1("COORDINATES", "coordinates", (arg0, arg1x) -> copyBoolean(arg1x, "decimalCoordinates", arg0, "showCoordDecimals"));
      data4.method5("COORDINATES", "coordinates", "COORDINATES_X_CHILD", "COORDINATES_Y_CHILD", "COORDINATES_Z_CHILD", "COORDINATES_C_CHILD");
      data4.method1("ARMORSTATUS", "armorStatus", FeatherProfileConverter::convertArmorStatus);
      data4.method5(
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
      data4.method11();
      data4.method1("REACH_DISPLAY", "reachDisplay", field4);
      data4.method1("COMBO", "comboDisplay", field4);
      data4.method1("POTION_EFFECTS", "potionEffects", (arg0, arg1x) -> copyBoolean(arg1x, "showAmplifier", arg0, "showAmplifier"));
      data4.method1("SCOREBOARD", "scoreboard", FeatherProfileConverter::convertScoreboard);
      data4.method1("MOMENTUM", "speedMeter", field4);
      data4.method1("WAILA", "blockIndicator", (arg0, arg1x) -> copyBoolean(arg1x, "showBreakTime", arg0, "showBlockBreakingPercentage"));
      data4.method1("TAB", "tablist", field4);
      data4.method1("NAMETAG", "nametags", (arg0, arg1x) -> copyBoolean(arg1x, "nametagShadow", arg0, "textShadow"));
      data4.method1("DIRECTION_HUD", "direction", FeatherProfileConverter::convertDirectionHud);
      data4.method1("CLOCK", "time", field4);
      data4.method1("ITEM_COUNTER", "itemCounter", field4);
      data4.method9();
      data4.method1("HORSE_STATS", "horses", field4);
      data4.method1("STOPWATCH", "stopwatch1", field4);
      data4.method1("SERVER_ADDRESS", "serverAddress", field4);
      data4.method1("PLAYTIME", "playtime", field4);
      data4.method1("MEMORY", "systemresources", field4);
      data4.method1("TNT_COUNTDOWN", "tnttimer", field4);
      data4.method1("PACK_DISPLAY", "packdisplay", field4);
      data4.method1("BOSSBAR", "bossBar", field4);
      data4.method1("TITLES", "titletweaker", field4);
      data4.method2("PING", "PING_HUD", "ping", (arg0, arg1x) -> arg1x.addProperty("showPingPrefix", false), (arg0, arg1x) -> {
         ParsedProfileColor horsestats2x = arg0.method8("textColor");
         if (horsestats2x != null) {
            arg1x.addProperty("overridePingTextColor", true);
            copyColor(arg1x, "pingTextColor", horsestats2x);
         }
      });
      data4.method2("SATURATION", "SATURATION_HUD_CHILD", "saturation", field4, field4);
      data4.method2("TOTEM_COUNTER", "TOTEM_COUNTER_HUD_CHILD", "totem", field4, field4);
      data4.method2("TOGGLE_SNEAK", "TOGGLE_SNEAK_HUD_CHILD", "toggleSprint", (arg0, arg1x) -> {
         copyBoolean(arg1x, "toggleSprint", arg0, "toggleSprint");
         copyBoolean(arg1x, "toggleSneak", arg0, "toggleSneak");
         copyBoolean(arg1x, "flyBoost", arg0, "flyBoostToggle");
         if (arg0.has("flyBoostAmount")) {
            arg1x.addProperty("flyBoostAmount", roundClamp(arg0.method6("flyBoostAmount", 4.0F), 2, 8));
         }
      }, field4);
      data4.method14();
      data4.method3("ZOOM", "zoom", (arg0, arg1x) -> {
         copyBoolean(arg1x, "smoothZoom", arg0, "smoothZoom");
         copyBoolean(arg1x, "smoothCamera", arg0, "smoothMovement");
         copyKeybind(arg1x, "zoomKeybind", arg0.method7("keyZoom"));
      });
      data4.method3("LIGHTING", "brightness", field4);
      data4.method3("FOV", "fovChanger", field4);
      data4.method3("FOG", "customfog", field4);
      data4.method3("MOTION_BLUR", "motionBlur", field4);
      data4.method3("ITEM_PHYSICS", "itemPhysic", field4);
      data4.method3("BLOCK_OUTLINE", "blockOverlay", FeatherProfileConverter::convertBlockOverlay);
      data4.method3("SNAPLOOK", "snaplook", field4);
      data4.method3("NICK_HIDER", "nickHider", (arg0, arg1x) -> {
         copyBoolean(arg1x, "hideOwnName", arg0, "hideOwnName");
         copyBoolean(arg1x, "hideOtherNames", arg0, "hideOtherNames");
      });
      data4.method3("HYPIXEL_MOD", "hypixel", (arg0, arg1x) -> {
         copyBoolean(arg1x, "autoGG", arg0, "autoGGEnabled");
         copyBoolean(arg1x, "antiGG", arg0, "antiGGEnabled");
         copyBoolean(arg1x, "levelHead", arg0, "levelHeadEnabled");
         copyBoolean(arg1x, "autoFriend", arg0, "autoFriendEnabled");
      });
      data4.method3("ATTACK_INDICATOR", "attackIndicator", field4);
      data4.method3("SCREENSHOT", "screenshot", field4);
      data4.method3("TIME_CHANGER", "timeChanger", (arg0, arg1x) -> copyBoolean(arg1x, "useRealTime", arg0, "useRealWorldTime"));
      data4.method3("WEATHER_CHANGER", "weatherchanger", FeatherProfileConverter::convertWeatherChanger);
      data4.method3("COLOR_SATURATION", "colorSaturation", FeatherProfileConverter::convertColorSaturation);
      data4.method3("SHULKER_PREVIEW", "shulkertooltips", field4);
      data4.method3("UHC_OVERLAY", "uhcoverlay", field4);
      data4.method3("WAYPOINTS", "waypoints", field4);
      data4.method3("SCROLLABLE_TOOLTIPS", "tooltips", field4);
      data4.method13(array2);
      data4.method3("TEAM_VIEW", "teamtracker", field4);
      data4.method3("INVENTORY_MOD", "inventory", field4);
      data4.method8();
      data4.method10();
      data4.method12();
      data4.method6();
      data4.method7(json3);
      data4.method3("TIER_TAGGER", "tiertagger", field4);
      data4.method3("GLINT_COLORIZER", "glint", field4);
      data4.method3("HITBOX", "hitbox", field4);
      data4.method3("CHAT", "customChat", field4);
      data4.method3("F3_DISPLAY", "customf3", field4);
      return data4.method20();
   }

   private static void convertKeystrokes(FeatherProfileConfig.Data data0, JsonObject json1) {
      boolean flag2 = "fill".equalsIgnoreCase(data0.method3("animType", "fill"));
      json1.addProperty("animate", flag2);
      if (flag2) {
         json1.addProperty("animation", "Smooth Fill");
         json1.addProperty("animationType", "synced");
         json1.addProperty("timerType", "complete");
         json1.addProperty("duration", clamp(data0.method6("fadeTime", 150.0F) / 1000.0F, 0.1F, 1.0F));
      } else if (data0.has("fadeTime")) {
         json1.addProperty("keyFadeDelay", roundClamp(data0.method6("fadeTime", 150.0F), 0, 500));
      }

      String text3 = data0.method3("keysDisplayMode", "names");
      json1.addProperty("keyStrokesMovement", !"none".equalsIgnoreCase(text3));
      json1.addProperty("useArrows", "arrows".equalsIgnoreCase(text3));
      json1.addProperty("keyStrokesClicks", !"none".equalsIgnoreCase(data0.method3("clicksDisplayMode", "names")));
      json1.addProperty("keyStrokesSpacebar", !"none".equalsIgnoreCase(data0.method3("jumpDisplayMode", "arrows")));
      boolean flag4 = "withClicks".equalsIgnoreCase(data0.method3("cpsDisplayMode", "separate"));
      json1.addProperty("leftCPS", flag4);
      json1.addProperty("rightCPS", flag4);
      if (data0.method5("border", false)) {
         json1.addProperty("innerBorder", true);
      }

      copyColor(json1, "textPressedColor", data0.method8("textActiveColor"));
      if (data0.method5("background", true)) {
         copyColor(json1, "backgroundPressedColor", data0.method8("backgroundActiveColor"));
      } else {
         putZeroColor(json1, "backgroundColor");
         putZeroColor(json1, "backgroundPressedColor");
      }
   }

   private static void convertArmorStatus(FeatherProfileConfig.Data data0, JsonObject json1) {
      copyBoolean(json1, "itemDamage", data0, "showItemDamage");
      copyBoolean(json1, "maxDamage", data0, "showMaxDamage");
      copyBoolean(json1, "itemName", data0, "enableItemName");
      copyBoolean(json1, "damageOverlay", data0, "showDamageOverlay");
      if (data0.has("dynamicColor")) {
         json1.addProperty("staticDamageColors", !data0.method5("dynamicColor", true));
      }

      if (data0.has("damageDisplayType")) {
         json1.addProperty("damageDisplay", damageDisplayMode(data0.method3("damageDisplayType", "value")));
      }

      if (data0.has("listMode")) {
         json1.addProperty("listMode", "horizontal".equalsIgnoreCase(data0.method3("listMode", "vertical")) ? "horizontal" : "vertical");
      }

      if (data0.has("textAlignment")) {
         json1.addProperty("durabilityPosition", durabilityPosition(data0.method3("textAlignment", "right")));
      }
   }

   private static void convertScoreboard(FeatherProfileConfig.Data data0, JsonObject json1) {
      if (data0.has("showNumbers")) {
         json1.addProperty("numbers", !data0.method5("showNumbers", true));
      }

      copyColor(json1, "headerColor", data0.method8("titleBackgroundColor"));
      if (data0.has("background") && !data0.method5("background", true)) {
         putZeroColor(json1, "backgroundColor");
         putZeroColor(json1, "headerColor");
      }
   }

   private static void convertDirectionHud(FeatherProfileConfig.Data data0, JsonObject json1) {
      json1.addProperty("background", true);
      json1.addProperty("hudStyle", "normal");
      copyBoolean(json1, "textShadow", data0, "shadow");
      copyColor(json1, "directionColor", data0.method8("directionColor"));
      copyColor(json1, "markerColor", data0.method8("markerColor"));
      if (data0.has("width")) {
         json1.addProperty("width", clamp(data0.method6("width", 300.0F), 168.0F, 448.0F));
      }
   }

   private static void convertBlockOverlay(FeatherProfileConfig.Data data0, JsonObject json1) {
      copyBoolean(json1, "blockOutline", data0, "showOutline");
      copyColor(json1, "blockOutlineColor", data0.method8("outlineColor"));
      if (data0.has("outlineThickness")) {
         json1.addProperty("blockOutlineWidth", clamp(data0.method6("outlineThickness", 25.0F) / 10.0F, 1.0F, 10.0F));
      }

      copyBoolean(json1, "blockOverlay", data0, "fill");
      copyColor(json1, "blockOverlayColor", data0.method8("fillColor"));
      copyBoolean(json1, "blockOutlineSide", data0, "showSide");
      if (data0.has("shapeMode")) {
         boolean flag2 = "dynamic".equalsIgnoreCase(data0.method3("shapeMode", "dynamic"));
         json1.addProperty("blockOutlineAccurate", flag2);
         json1.addProperty("blockOverlayAccurate", flag2);
      }
   }

   private static void convertWeatherChanger(FeatherProfileConfig.Data data0, JsonObject json1) {
      String text2 = data0.method3("weatherMode", null);
      if (text2 != null) {
         switch (text2.toLowerCase()) {
            case "clear":
               json1.addProperty("weatherMode", "clear");
               break;
            case "rain":
               json1.addProperty("weatherMode", "rain");
               break;
            case "thunder":
               json1.addProperty("weatherMode", "rain");
               json1.addProperty("thunderStorm", true);
               break;
            default:
               json1.addProperty("weatherMode", "natural");
         }
      }

      copyColor(json1, "rainColor", data0.method8("fallingColor"));
   }

   private static void convertColorSaturation(FeatherProfileConfig.Data data0, JsonObject json1) {
      if (data0.has("saturation")) {
         json1.addProperty("colorSaturationSaturation", clamp(data0.method6("saturation", 0.0F) * 10.0F + 5.0F, 0.0F, 10.0F));
      }

      if (data0.has("brightness")) {
         json1.addProperty("colorSaturationBrightness", clamp(data0.method6("brightness", 0.0F) * 10.0F + 5.0F, 0.0F, 10.0F));
      }

      if (data0.has("contrast")) {
         json1.addProperty("colorSaturationContrast", clamp(data0.method6("contrast", 0.0F) * 10.0F + 5.0F, 0.0F, 10.0F));
      }

      if (data0.has("hue")) {
         json1.addProperty("colorSaturationHue", clamp(data0.method6("hue", 0.0F) / 36.0F, 0.0F, 10.0F));
      }
   }

   private static void copyBoolean(JsonObject json0, String text1, FeatherProfileConfig.Data data2, String text3) {
      if (data2.has(text3)) {
         json0.addProperty(text1, data2.method5(text3, false));
      }
   }

   private static void copyColor(JsonObject json0, String text1, ParsedProfileColor horsestats2) {
      if (horsestats2 != null) {
         JsonObject json3 = new JsonObject();
         if (horsestats2.isChroma()) {
            json3.addProperty("value", (horsestats2.alpha() & 0xFF) << 24 | 0xFF0000);
            json3.addProperty("chroma", true);
         } else {
            json3.addProperty("value", horsestats2.toRgba());
         }

         json0.add(text1, json3);
      }
   }

   private static void copyKeybind(JsonObject json0, String text1, Integer index2) {
      if (index2 != null) {
         String text3 = field1.get(index2);
         if (text3 != null) {
            json0.addProperty(text1, text3);
         }
      }
   }

   private static JsonObject parseKeybind(JsonElement element0) {
      if (element0 != null && element0.isJsonObject()) {
         JsonElement element1 = element0.getAsJsonObject().get("keys");
         if (element1 != null && element1.isJsonArray()) {
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            String text5 = null;

            for (JsonElement element7 : element1.getAsJsonArray()) {
               int index8 = element7.getAsInt();
               switch (index8) {
                  case 340:
                  case 344:
                     flag2 = true;
                     break;
                  case 341:
                  case 345:
                     flag3 = true;
                     break;
                  case 342:
                  case 346:
                     flag4 = true;
                     break;
                  case 343:
                  default:
                     if (text5 == null) {
                        text5 = field2.get(index8);
                     }
               }
            }

            if (text5 == null) {
               return null;
            }

            JsonObject json9 = new JsonObject();
            json9.addProperty("value", text5);
            if (flag2) {
               json9.addProperty("shift", true);
            }

            if (flag3) {
               json9.addProperty("control", true);
            }

            if (flag4) {
               json9.addProperty("alt", true);
            }

            return json9;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static int roundClamp(float value0, int number1, int number2) {
      return Math.max(number1, Math.min(number2, Math.round(value0)));
   }

   private static float clamp(float value0, float value1, float value2) {
      return Math.max(value1, Math.min(value2, value0));
   }

   private static String convertItemSlug(String text0) {
      int index1 = text0.indexOf(36);
      if (index1 < 0) {
         return text0;
      }

      String text2 = text0.substring(0, index1);
      String text3 = text0.substring(index1 + 1);

      return switch (text2) {
         case "minecraft:potion" -> text3;
         case "minecraft:splash_potion" -> text3 + "_splash";
         case "minecraft:lingering_potion" -> text3 + "_lingering";
         default -> text2;
      };
   }

   private static String damageDisplayMode(String text0) {
      return switch (text0.toLowerCase()) {
         case "percent" -> "percent";
         case "none", "off" -> "none";
         default -> "value";
      };
   }

   private static String durabilityPosition(String text0) {
      return switch (text0.toLowerCase()) {
         case "up", "top" -> "top";
         case "down", "bottom" -> "bottom";
         case "left" -> "left";
         default -> "right";
      };
   }

   private static JsonObject asJsonObject(JsonElement element0) {
      return element0 != null && element0.isJsonObject() ? element0.getAsJsonObject() : null;
   }

   private static void putZeroColor(JsonObject json0, String text1) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("value", 0);
      json0.add(text1, json2);
   }

   static HudAnchor toHudAnchor(String text0) {
      if (text0 == null) {
         return null;
      }

      return switch (text0.toLowerCase()) {
         case "top_left" -> HudAnchor.TOP_LEFT;
         case "top_center" -> HudAnchor.TOP_CENTER;
         case "top_right" -> HudAnchor.TOP_RIGHT;
         case "center_left", "middle_left" -> HudAnchor.MIDDLE_LEFT;
         case "center", "center_center", "middle_center" -> HudAnchor.MIDDLE_CENTER;
         case "center_right", "middle_right" -> HudAnchor.MIDDLE_RIGHT;
         case "bottom_left" -> HudAnchor.BOTTOM_LEFT;
         case "bottom_center" -> HudAnchor.BOTTOM_CENTER_R;
         case "bottom_right" -> HudAnchor.BOTTOM_RIGHT;
         default -> null;
      };
   }

   private static Map<Integer, String> buildVkKeyCodes() {
      HashMap map0 = new HashMap();

      for (KeyCode bridgetype_84 : KeyCode.values()) {
         if (bridgetype_84 != KeyCode.KEY_NONE && !bridgetype_84.isMouse()) {
            int number5 = bridgetype_84.getVk();
            if (number5 != 0) {
               map0.putIfAbsent(number5, bridgetype_84.name());
            }
         }
      }

      map0.put(16, KeyCode.KEY_LSHIFT.name());
      map0.put(17, KeyCode.KEY_LCONTROL.name());
      map0.put(18, KeyCode.KEY_LMENU.name());
      map0.put(13, KeyCode.KEY_RETURN.name());
      map0.put(187, KeyCode.KEY_EQUALS.name());
      return map0;
   }

   private static Map<Integer, String> buildGlfwKeyCodes() {
      HashMap map0 = new HashMap();

      for (char character1 = '0'; character1 <= '9'; character1++) {
         map0.put(Integer.valueOf(character1), "KEY_" + character1);
      }

      for (char character2 = 'A'; character2 <= 'Z'; character2++) {
         map0.put(Integer.valueOf(character2), "KEY_" + character2);
      }

      map0.put(32, KeyCode.KEY_SPACE.name());
      map0.put(39, KeyCode.KEY_APOSTROPHE.name());
      map0.put(44, KeyCode.KEY_COMMA.name());
      map0.put(45, KeyCode.KEY_MINUS.name());
      map0.put(46, KeyCode.KEY_PERIOD.name());
      map0.put(47, KeyCode.KEY_SLASH.name());
      map0.put(59, KeyCode.KEY_SEMICOLON.name());
      map0.put(61, KeyCode.KEY_EQUALS.name());
      map0.put(91, KeyCode.KEY_LBRACKET.name());
      map0.put(92, KeyCode.KEY_BACKSLASH.name());
      map0.put(93, KeyCode.KEY_RBRACKET.name());
      map0.put(96, KeyCode.KEY_GRAVE.name());
      map0.put(256, KeyCode.KEY_ESCAPE.name());
      map0.put(257, KeyCode.KEY_RETURN.name());
      map0.put(258, KeyCode.KEY_TAB.name());
      map0.put(259, KeyCode.KEY_BACK.name());
      map0.put(260, KeyCode.KEY_INSERT.name());
      map0.put(261, KeyCode.KEY_DELETE.name());
      map0.put(262, KeyCode.KEY_RIGHT.name());
      map0.put(263, KeyCode.KEY_LEFT.name());
      map0.put(264, KeyCode.KEY_DOWN.name());
      map0.put(265, KeyCode.KEY_UP.name());
      map0.put(266, KeyCode.KEY_PGUP.name());
      map0.put(267, KeyCode.KEY_PGDOWN.name());
      map0.put(268, KeyCode.KEY_HOME.name());
      map0.put(269, KeyCode.KEY_END.name());
      map0.put(280, KeyCode.KEY_CAPITAL.name());
      map0.put(281, KeyCode.KEY_SCROLL.name());
      map0.put(282, KeyCode.KEY_NUMLOCK.name());
      map0.put(283, KeyCode.KEY_PRINTSC.name());
      map0.put(284, KeyCode.KEY_PAUSE.name());

      for (int index3 = 1; index3 <= 19; index3++) {
         map0.put(289 + index3, "KEY_F" + index3);
      }

      for (int index4 = 0; index4 <= 9; index4++) {
         map0.put(320 + index4, "KEY_NUMPAD" + index4);
      }

      map0.put(330, KeyCode.KEY_DECIMAL.name());
      map0.put(331, KeyCode.KEY_DIVIDE.name());
      map0.put(332, KeyCode.KEY_MULTIPLY.name());
      map0.put(333, KeyCode.KEY_SUBTRACT.name());
      map0.put(334, KeyCode.KEY_ADD.name());
      map0.put(335, KeyCode.KEY_NUMPADENTER.name());
      map0.put(336, KeyCode.KEY_NUMPADEQUALS.name());
      map0.put(340, KeyCode.KEY_LSHIFT.name());
      map0.put(341, KeyCode.KEY_LCONTROL.name());
      map0.put(342, KeyCode.KEY_LMENU.name());
      map0.put(343, KeyCode.KEY_LWINDOWS.name());
      map0.put(344, KeyCode.KEY_RSHIFT.name());
      map0.put(345, KeyCode.KEY_RCONTROL.name());
      map0.put(346, KeyCode.KEY_RMENU.name());
      map0.put(347, KeyCode.KEY_RWINDOWS.name());
      return map0;
   }

   private static final class Data {
      private final FeatherProfileConfig field1;
      private final JsonObject field2 = new JsonObject();
      private final List<String> field3 = new ArrayList<>();

      Data(FeatherProfileConfig horsestats31) {
         this.VK_KEY_CODES = horsestats31;
         this.GLFW_KEY_CODES.addProperty("version", ConfigMigrator.field2);
      }

      void convert(String text1, String text2, BiConsumer<FeatherProfileConfig.Data, JsonObject> biconsumer3) {
         FeatherProfileConfig.Data data4 = this.convertItemSlug(text1, text2);
         if (data4 != null) {
            JsonObject json5 = this.GLFW_KEY_CODES.getAsJsonObject(text1);
            JsonObject json6 = new JsonObject();
            this.damageDisplayMode(data4, json5, json6);
            this.convertKeystrokes(data4, json6);
            biconsumer3.accept(data4, json6);
            durabilityPosition(json5, json6);
         }
      }

      void convert(String text1, String text2, String text3, BiConsumer<FeatherProfileConfig.Data, JsonObject> biconsumer4, BiConsumer<FeatherProfileConfig.Data, JsonObject> biconsumer5) {
         FeatherProfileConfig.Data data6 = this.convertItemSlug(text1, text3);
         if (data6 != null) {
            JsonObject json7 = this.GLFW_KEY_CODES.getAsJsonObject(text1);
            JsonObject json8 = new JsonObject();
            biconsumer4.accept(data6, json8);
            durabilityPosition(json7, json8);
            JsonObject json9 = new JsonObject();
            JsonObject json10 = new JsonObject();
            this.damageDisplayMode(data6, json9, json10);
            this.convertKeystrokes(data6, json10);
            biconsumer5.accept(data6, json10);
            durabilityPosition(json9, json10);
            if (!json9.entrySet().isEmpty()) {
               json7.add(text2, json9);
            }
         }
      }

      void convert(String text1, String text2, BiConsumer<FeatherProfileConfig.Data, JsonObject> biconsumer3) {
         FeatherProfileConfig.Data data4 = this.convertItemSlug(text1, text2);
         if (data4 != null) {
            JsonObject json5 = new JsonObject();
            biconsumer3.accept(data4, json5);
            durabilityPosition(this.GLFW_KEY_CODES.getAsJsonObject(text1), json5);
         }
      }

      private void convertKeystrokes(FeatherProfileConfig.Data data1, JsonObject json2) {
         String text3 = data1.method3("displayMode", null);
         boolean flag4 = "brackets".equalsIgnoreCase(text3);
         json2.addProperty("brackets", flag4);
         if (flag4) {
            FeatherProfileConverter.copyColor(json2, "bracketColor", data1.method8("textColor"));
         }

         if (data1.has("background")) {
            json2.addProperty("background", data1.method5("background", true));
         } else if (text3 != null) {
            json2.addProperty("background", "background".equalsIgnoreCase(text3));
         }

         FeatherProfileConverter.copyColor(json2, "backgroundColor", data1.method8("backgroundColor"));
         String text5 = data1.method3("backgroundType", null);
         if (text5 != null) {
            boolean flag6 = "static".equalsIgnoreCase(text5);
            json2.addProperty("staticBackgroundWidth", flag6);
            json2.addProperty("staticBackgroundHeight", flag6);
            if (flag6) {
               if (data1.has("backgroundWidth")) {
                  json2.addProperty("backgroundWidth", Math.round(data1.method6("backgroundWidth", 60.0F)));
               }

               if (data1.has("backgroundHeight")) {
                  json2.addProperty("backgroundHeight", Math.round(data1.method6("backgroundHeight", 20.0F)));
               }
            }
         }

         FeatherProfileConverter.copyBoolean(json2, "border", data1, "border");
         FeatherProfileConverter.copyColor(json2, "borderColor", data1.method8("borderColor"));
         if (data1.has("borderThickness1")) {
            json2.addProperty("borderThickness", FeatherProfileConverter.clamp(data1.method6("borderThickness1", 1.0F) / 2.0F, 0.5F, 3.0F));
         }

         FeatherProfileConverter.copyColor(json2, "textColor", data1.method8("textColor"));
         FeatherProfileConverter.copyBoolean(json2, "textShadow", data1, "textShadow");
      }

      void convertArmorStatus(String text1, String text2, String... items3) {
         FeatherProfileConfig.Data data4 = this.VK_KEY_CODES.method5(text2);
         if (data4 != null && this.GLFW_KEY_CODES.has(text1)) {
            JsonObject json5 = this.GLFW_KEY_CODES.getAsJsonObject(text1);

            for (String text9 : items3) {
               JsonObject json10 = json5.has(text9) ? json5.getAsJsonObject(text9) : new JsonObject();
               JsonObject json11 = new JsonObject();
               this.convertKeystrokes(data4, json11);
               durabilityPosition(json10, json11);
               if (!json10.entrySet().isEmpty()) {
                  json5.add(text9, json10);
               }
            }
         }
      }

      void convertScoreboard() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("animations");
         if (data1 != null) {
            String text2 = data1.method3("hurtAnimationMode", "default").toLowerCase();
            if (!text2.equals("default") && this.convertItemSlug("HURT_CAM", "animations") != null) {
               JsonObject json3 = new JsonObject();
               if (text2.equals("off")) {
                  json3.addProperty("disableHurtCam", true);
               } else {
                  json3.addProperty("hurtShakingIntensity", FeatherProfileConverter.clamp(data1.method6("hurtMultiplier", 1.0F), 0.0F, 2.0F));
               }

               durabilityPosition(this.GLFW_KEY_CODES.getAsJsonObject("HURT_CAM"), json3);
            }
         }
      }

      void convertDirectionHud(JsonObject json1) {
         if (json1 != null && json1.has("graphicsOptions") && !this.GLFW_KEY_CODES.has("OVERLAY_MOD")) {
            FeatherProfileConfig.Data data2 = FeatherProfileConfig.wrap(json1.getAsJsonObject("graphicsOptions"));
            JsonObject json3 = new JsonObject();
            if (data2.method5("minViewBobbing", false)) {
               json3.addProperty("minimalViewBobbing", true);
            }

            if (data2.has("clearGlass") && data2.method5("clearGlass", false)) {
               json3.addProperty("clearGlass", true);
               FeatherProfileConverter.copyBoolean(json3, "clearColoredGlass", data2, "clearColoredGlass");
            }

            if (data2.has("fireHeight")) {
               json3.addProperty("fireHeight", FeatherProfileConverter.clamp(data2.method6("fireHeight", 1.0F), 0.0F, 2.0F));
            }

            if (!json3.entrySet().isEmpty()) {
               JsonObject json4 = new JsonObject();
               json4.addProperty("enabled", true);
               json4.addProperty("seen", true);
               json4.add("options", json3);
               this.GLFW_KEY_CODES.add("OVERLAY_MOD", json4);
            }
         }
      }

      void convertBlockOverlay() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("inventory");
         if (data1 != null && this.GLFW_KEY_CODES.has("INVENTORY_MOD")) {
            FeatherProfileConfig.Data data2 = data1.method2("inventoryHud:");
            if (data2.has("hudEnabled1")) {
               JsonObject json3 = new JsonObject();
               json3.addProperty("enabled", data2.method5("hudEnabled1", false));
               HudAnchor gui2extension24 = FeatherProfileConverter.toHudAnchor(data2.method9());
               if (gui2extension24 != null) {
                  json3.addProperty("position", gui2extension24.id());
               }

               float value5 = data2.method10();
               if (value5 != 0.0F) {
                  json3.addProperty("x", value5);
               }

               float value6 = data2.method11();
               if (value6 != 0.0F) {
                  json3.addProperty("y", value6);
               }

               JsonObject json7 = new JsonObject();
               json7.addProperty("scale", FeatherProfileConverter.clamp(data2.method12() * 0.35F, 0.25F, 5.0F));
               FeatherProfileConverter.copyBoolean(json7, "background", data1, "inventoryHudBackground");
               ParsedProfileColor horsestats8 = data1.method8("inventoryHudBackgroundColor");
               if (horsestats8 != null) {
                  json7.addProperty("mcBackground", false);
                  json7.addProperty("backgroundColor", true);
                  FeatherProfileConverter.copyColor(json7, "color", horsestats8);
               }

               FeatherProfileConverter.copyBoolean(json7, "grid", data1, "inventoryHudSlotBorder");
               FeatherProfileConverter.copyColor(json7, "gridColor", data1.method8("inventoryHudSlotBorderColor"));
               json3.add("options", json7);
               this.GLFW_KEY_CODES.getAsJsonObject("INVENTORY_MOD").add("INVENTORY_HUD", json3);
            }
         }
      }

      void convertWeatherChanger() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("itemCounter");
         if (data1 != null && data1.method1() != null && this.GLFW_KEY_CODES.has("ITEM_COUNTER")) {
            JsonElement element2 = data1.method1().get("Config");
            if (element2 != null && element2.isJsonArray()) {
               JsonObject json3 = this.GLFW_KEY_CODES.getAsJsonObject("ITEM_COUNTER");
               boolean flag4 = !"vertical".equalsIgnoreCase(data1.method3("displayMode", "horizontal"));
               HudAnchor gui2extension25 = FeatherProfileConverter.toHudAnchor(data1.method9());
               float value6 = data1.method10();
               float value7 = data1.method11();
               float value8 = 20.0F;
               byte number9 = 10;
               float value10 = gui2extension25 != null && gui2extension25.getHorizontal() == HudAlignment.RIGHT ? -value8 : value8;
               float value11 = gui2extension25 != null && gui2extension25.getVertical() == HudAlignment.BOTTOM ? -value8 : value8;
               LinkedHashSet set12 = new LinkedHashSet();
               LinkedHashMap map13 = new LinkedHashMap();
               boolean flag14 = false;
               int index15 = 0;

               for (JsonElement element17 : element2.getAsJsonArray()) {
                  if (element17.isJsonObject()) {
                     JsonObject json18 = element17.getAsJsonObject();
                     JsonObject json19 = FeatherProfileConverter.asJsonObject(json18.get("settings"));
                     JsonObject json20 = FeatherProfileConverter.asJsonObject(json18.get("hudElementConfig"));
                     if (json19 != null && json19.has("slug")) {
                        String text21 = FeatherProfileConverter.convertItemSlug(json19.get("slug").getAsString());
                        if (set12.add(text21)) {
                           JsonObject json22 = new JsonObject();
                           json22.addProperty("enabled", true);
                           boolean flag23 = json20 != null && json20.get("hudEnabled") != null && json20.get("hudEnabled").getAsBoolean();
                           if (flag23) {
                              flag14 = true;
                              if (json20.has("anchorPoint")) {
                                 HudAnchor gui2extension224 = FeatherProfileConverter.toHudAnchor(json20.get("anchorPoint").getAsString());
                                 if (gui2extension224 != null) {
                                    json22.addProperty("position", gui2extension224.id());
                                 }
                              }

                              float value35 = json20.has("relX") ? json20.get("relX").getAsFloat() : 0.0F;
                              if (value35 != 0.0F) {
                                 json22.addProperty("x", value35);
                              }

                              float value25 = json20.has("relY") ? json20.get("relY").getAsFloat() : 0.0F;
                              if (value25 != 0.0F) {
                                 json22.addProperty("y", value25);
                              }

                              float value26 = json20.has("scale") ? json20.get("scale").getAsFloat() : 1.0F;
                              if (value26 != 1.0F) {
                                 JsonObject json27 = new JsonObject();
                                 json27.addProperty("scale", FeatherProfileConverter.clamp(value26, 0.25F, 5.0F));
                                 json22.add("options", json27);
                              }
                           } else {
                              if (gui2extension25 != null) {
                                 json22.addProperty("position", gui2extension25.id());
                              }

                              int number36 = index15 % number9;
                              int number37 = index15 / number9;
                              float value38 = value6 + value10 * (flag4 ? number36 : number37);
                              float value39 = value7 + value11 * (flag4 ? number37 : number36);
                              if (value38 != 0.0F) {
                                 json22.addProperty("x", value38);
                              }

                              if (value39 != 0.0F) {
                                 json22.addProperty("y", value39);
                              }

                              index15++;
                           }

                           map13.put("ITEM_COUNTER_" + text21.replace("minecraft:", "").toUpperCase() + "_CHILD", json22);
                        }
                     }
                  }
               }

               if (!set12.isEmpty()) {
                  boolean flag28 = !flag14;

                  for (Entry entry31 : map13.entrySet()) {
                     JsonObject json33 = (JsonObject)entry31.getValue();
                     if (!flag28) {
                        JsonObject json34 = json33.has("options") ? json33.getAsJsonObject("options") : new JsonObject();
                        this.convertKeystrokes(data1, json34);
                        if (!json34.entrySet().isEmpty()) {
                           json33.add("options", json34);
                        }
                     }

                     json3.add((String)entry31.getKey(), json33);
                  }

                  JsonArray array30 = new JsonArray();
                  set12.forEach(array30::add);
                  JsonObject json32 = asJsonObject(json3);
                  json32.add("itemCounterCountedItems", array30);
                  json32.addProperty("itemCounterGrouped", flag28);
               }
            }
         }
      }

      void convertColorSaturation() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("inventory");
         if (data1 != null && !this.GLFW_KEY_CODES.has("ITEM_TRACKER")) {
            FeatherProfileConfig.Data data2 = data1.method2("itemPickup:");
            if (data2.has("hudEnabled1")) {
               JsonObject json3 = new JsonObject();
               json3.addProperty("enabled", data1.method4() && data2.method5("hudEnabled1", false));
               json3.addProperty("seen", true);
               JsonObject json4 = new JsonObject();
               this.damageDisplayMode(data2, json3, json4);
               if (data1.has("itemPickupRemovalDelay")) {
                  json4.addProperty("popupDurationSec", FeatherProfileConverter.clamp(data1.method6("itemPickupRemovalDelay", 12.0F), 0.1F, 30.0F));
               }

               durabilityPosition(json3, json4);
               this.GLFW_KEY_CODES.add("ITEM_TRACKER", json3);
            }
         }
      }

      void copyBoolean() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("armorStatus");
         if (data1 != null && this.GLFW_KEY_CODES.has("ARMORSTATUS")) {
            JsonObject json2 = this.GLFW_KEY_CODES.getAsJsonObject("ARMORSTATUS");
            String text3 = data1.has("textAlignment") ? FeatherProfileConverter.durabilityPosition(data1.method3("textAlignment", "right")) : null;
            boolean flag4 = false;

            for (Entry entry6 : FeatherProfileConverter.ARMOR_STATUS_CHILD_IDS.entrySet()) {
               String text7 = (String)entry6.getValue();

               Boolean flag8 = switch (text7) {
                  case "ARMORSTATUS_HELD_ITEM_CHILD" -> data1.has("showEquippedItem") ? data1.method5("showEquippedItem", true) : null;
                  case "ARMORSTATUS_OFF_HAND_HELD_ITEM_CHILD" -> data1.has("showOffhandItem") ? data1.method5("showOffhandItem", true) : null;
                  default -> null;
               };
               FeatherProfileConfig.Data data13 = data1.method2((String)entry6.getKey());
               boolean flag14 = data13.method5("hudEnabled1", false);
               if (flag14 || flag8 != null) {
                  JsonObject json11 = json2.has(text7) ? json2.getAsJsonObject(text7) : new JsonObject();
                  JsonObject json12 = json11.has("options") ? json11.getAsJsonObject("options") : new JsonObject();
                  if (flag8 != null) {
                     json11.addProperty("enabled", flag8);
                  }

                  if (flag14) {
                     flag4 = true;
                     this.damageDisplayMode(data13, json11, json12);
                     if (text3 != null) {
                        json12.addProperty("durabilityPosition", text3);
                     }
                  }

                  durabilityPosition(json11, json12);
                  if (!json11.entrySet().isEmpty()) {
                     json2.add(text7, json11);
                  }
               }
            }

            if (flag4) {
               asJsonObject(json2).addProperty("moveArmorIndividually", true);
            }
         }
      }

      void copyColor() {
         FeatherProfileConfig.Data data1 = this.VK_KEY_CODES.method5("uiScaling");
         if (data1 != null) {
            float value2 = data1.method6("containerScale", 0.0F);
            if (data1.method5("overrideContainerScale", false) && !(value2 <= 0.0F)) {
               if (this.convertItemSlug("GUI_SCALE", "uiScaling") != null) {
                  JsonObject json3 = new JsonObject();
                  json3.addProperty("inventoryScale", FeatherProfileConverter.roundClamp(value2, 1, 5));
                  durabilityPosition(this.GLFW_KEY_CODES.getAsJsonObject("GUI_SCALE"), json3);
               }
            }
         }
      }

      void copyKeybind(JsonArray array1) {
         FeatherProfileConfig.Data data2 = this.convertItemSlug("AUTO_TEXT_HOTKEY", "autoText");
         if (data2 != null && array1 != null) {
            JsonObject json3 = new JsonObject();
            int index4 = 1;

            for (JsonElement element6 : array1) {
               if (element6.isJsonObject()) {
                  JsonObject json7 = element6.getAsJsonObject();
                  if (json7.has("command") && !json7.get("command").getAsString().isBlank()) {
                     JsonObject json8 = new JsonObject();
                     json8.addProperty("value", json7.get("command").getAsString());
                     JsonObject json9 = FeatherProfileConverter.parseKeybind(json7.get("key"));
                     if (json9 != null) {
                        json8.add(index4 + "hotkey", json9);
                     }

                     json3.add(index4 + "hotkey", json8);
                     index4++;
                  }
               }
            }

            durabilityPosition(this.GLFW_KEY_CODES.getAsJsonObject("AUTO_TEXT_HOTKEY"), json3);
         }
      }

      void parseKeybind() {
         FeatherProfileConfig.Data data1 = this.convertItemSlug("CROSSHAIR", "crosshair");
         if (data1 != null) {
            JsonObject json2 = this.GLFW_KEY_CODES.getAsJsonObject("CROSSHAIR");
            JsonObject json3 = new JsonObject();
            if (data1.has("removeCrosshairInPerspective1")) {
               json3.addProperty("showInF5", !data1.method5("removeCrosshairInPerspective1", true));
            }

            durabilityPosition(json2, json3);
            boolean flag4 = data1.method5("invertColor", false);
            json2.add("CROSSHAIR_NORMAL", this.roundClamp(data1, data1.method8("color"), flag4));
            if (data1.method5("colorAllyEnemy", false)) {
               json2.add("CROSSHAIR_FRIENDLY", this.roundClamp(data1, data1.method8("allyColor"), flag4));
               json2.add("CROSSHAIR_ENEMY", this.roundClamp(data1, data1.method8("enemyColor"), flag4));
            }
         }
      }

      private JsonObject roundClamp(FeatherProfileConfig.Data data1, ParsedProfileColor horsestats2, boolean flag3) {
         JsonObject json4 = new JsonObject();
         json4.addProperty("enabled", true);
         JsonObject json5 = new JsonObject();
         json5.addProperty("crosshairMode", "crosshairModeSimple");
         float value6 = data1.method6("thickness", 1.0F);
         float value7 = data1.method6("size2", 5.0F) * 3.0F;
         float value8 = data1.method6("gap", 3.0F);
         float value9 = Math.max(0.0F, (value7 - 6.0F) / 2.0F);
         String text10 = data1.method3("type", "cross").toLowerCase();
         boolean flag11 = data1.method5("dot", false);
         boolean flag12 = data1.method5("outline", false);
         int number13 = FeatherProfileConverter.roundClamp(value6, 1, 5);
         json5.addProperty("crosshairThickness", number13);
         switch (text10) {
            case "circle":
               float value21 = Math.max((value7 - 3.0F) / 4.0F + value8 - 3.0F, 1.0F);
               json5.addProperty("crosshairShape", "circle");
               json5.addProperty("crosshairGap", FeatherProfileConverter.roundClamp((value21 - 1.0F) * 2.0F, 0, 8));
               break;
            case "square":
               int number20 = FeatherProfileConverter.roundClamp((value7 - 3.0F) / 4.0F, 0, 16);
               json5.addProperty("crosshairShape", "dot");
               json5.addProperty("crosshairWidth", number20);
               json5.addProperty("crosshairHeight", number20);
               break;
            case "arrow":
               int number19 = FeatherProfileConverter.roundClamp((value8 / 2.0F + value9) / (float)Math.sqrt(2.0), 0, 16);
               json5.addProperty("crosshairShape", "arrow");
               json5.addProperty("crosshairWidth", number19);
               json5.addProperty("crosshairHeight", number19);
               json5.addProperty("crosshairGap", 0);
               break;
            case "default":
            case "drawn":
               int number18 = FeatherProfileConverter.roundClamp(value7 * 7.0F / 15.0F, 0, 16);
               json5.addProperty("crosshairShape", "cross");
               json5.addProperty("crosshairWidth", number18);
               json5.addProperty("crosshairHeight", number18);
               json5.addProperty("crosshairGap", 0);
               json5.addProperty("crosshairThickness", FeatherProfileConverter.roundClamp(value7 / 15.0F, 1, 5));
               break;
            default:
               int number16 = FeatherProfileConverter.roundClamp(value9, 0, 16);
               json5.addProperty("crosshairShape", "cross");
               json5.addProperty("crosshairWidth", number16);
               json5.addProperty("crosshairHeight", number16);
               json5.addProperty("crosshairGap", FeatherProfileConverter.roundClamp((value8 - number13) / 2.0F, 0, 8));
         }

         json5.addProperty("crosshairDot", flag11);
         if (flag11) {
            json5.addProperty("dotSize", FeatherProfileConverter.roundClamp(value6 * 2.0F + 0.7F, 1, 16));
            ParsedProfileColor horsestats17 = data1.method8("dotColor");
            if (horsestats17 != null) {
               json5.addProperty("customDotColor", true);
               FeatherProfileConverter.copyColor(json5, "dotColor", horsestats17);
            }

            if (flag12) {
               json5.addProperty("dotOutline", true);
               json5.addProperty("dotOutlineThickness", 1.0F);
            }
         }

         json5.addProperty("crosshairColorVanilla", flag3);
         if (flag3) {
            json5.addProperty("vanillaBlendingColor", true);
         }

         FeatherProfileConverter.copyColor(json5, "color", horsestats2);
         FeatherProfileConverter.copyBoolean(json5, "crosshairOutline", data1, "outline");
         FeatherProfileConverter.copyColor(json5, "outlineColor", data1.method8("outlineColor"));
         if ("circle".equals(text10)) {
            json5.addProperty("outlineThickness", 1.0F);
         } else if (data1.has("outlineThickness")) {
            json5.addProperty("outlineThickness", Math.min(1.0F, data1.method6("outlineThickness", 1.0F) / 4.0F));
         }

         if (data1.method5("ignoreGuiScale", true)) {
            json5.addProperty("customScale", true);
            json5.addProperty("crosshairScale", "normal");
         }

         if (!data1.method5("disableBowZoom", false)) {
            json5.addProperty("crosshairDynamicBow", true);
            json5.addProperty("dynamicBowScale", FeatherProfileConverter.clamp((value8 + 5.0F) / 2.0F, 1.0F, 10.0F));
            json5.addProperty("dynamicDot", false);
         }

         json4.add("options", json5);
         return json4;
      }

      private FeatherProfileConfig.Data convertItemSlug(String text1, String text2) {
         FeatherProfileConfig.Data data3 = this.VK_KEY_CODES.method5(text2);
         if (data3 != null && !this.GLFW_KEY_CODES.has(text1)) {
            JsonObject json4 = new JsonObject();
            json4.addProperty("enabled", data3.method4());
            json4.addProperty("seen", true);
            this.GLFW_KEY_CODES.add(text1, json4);
            this.ARMOR_STATUS_CHILD_IDS.add(text2);
            return data3;
         } else {
            return null;
         }
      }

      private void damageDisplayMode(FeatherProfileConfig.Data data1, JsonObject json2, JsonObject json3) {
         HudAnchor gui2extension24 = FeatherProfileConverter.toHudAnchor(data1.method9());
         if (gui2extension24 != null) {
            json2.addProperty("position", gui2extension24.id());
         }

         float value5 = data1.method10();
         float value6 = data1.method11();
         if (value5 != 0.0F) {
            json2.addProperty("x", value5);
         }

         if (value6 != 0.0F) {
            json2.addProperty("y", value6);
         }

         float value7 = data1.method12();
         if (value7 != 1.0F) {
            json3.addProperty("scale", FeatherProfileConverter.clamp(value7, 0.25F, 5.0F));
         }
      }

      private static void durabilityPosition(JsonObject json0, JsonObject json1) {
         if (!json1.entrySet().isEmpty()) {
            json0.add("options", json1);
         }
      }

      private static JsonObject asJsonObject(JsonObject json0) {
         JsonObject json1 = json0.has("options") ? json0.getAsJsonObject("options") : new JsonObject();
         json0.add("options", json1);
         return json1;
      }

      FeatherConvertedProfile putZeroColor() {
         LinkedHashSet set1 = new LinkedHashSet<>(this.VK_KEY_CODES.method4());
         set1.removeAll(this.ARMOR_STATUS_CHILD_IDS);
         return new FeatherConvertedProfile(this.GLFW_KEY_CODES, toHudAnchor(), toHudAnchor(), toHudAnchor(), List.copyOf(this.ARMOR_STATUS_CHILD_IDS), List.copyOf(set1));
      }

      private static JsonObject toHudAnchor() {
         JsonObject json0 = new JsonObject();
         json0.addProperty("version", ConfigMigrator.field2);
         return json0;
      }
   }
}

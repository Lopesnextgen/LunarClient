package com.moonsworth.lunar.client.config.migration;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.UnfocusedFpsLimiter;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.KeyVersionPair;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.config.Config;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public final class VanillaOptionsFile {
   private static final KeyVersionPair field1 = new KeyVersionPair("key.keyboard.unknown", KeyCode.KEY_NONE, 0);
   private static final Map<String, KeyVersionPair> field2 = new HashMap<>();
   private static final Map<Integer, KeyVersionPair> field3 = new HashMap<>();
   private static final Map<KeyCode, KeyVersionPair> field4 = new HashMap<>();
   private static JsonObject field5 = new JsonObject();
   private static Config field6;
   private static final List<OptionMigration> field7 = ImmutableList.of(new TypeCoercionMigration());
   private static final List<OptionMigration> field8 = ImmutableList.of(new KeyCodeOptionMigration(), new KeyNameOptionMigration());
   private static final List<OptionMigration> field9 = ImmutableList.of(new FovOptionMigration());
   private static boolean dirty = true;
   private static boolean initialized = false;

   public static void method1(File file0) {
      try {
         File file1 = new File(file0, "optionsLC.txt");
         if (!file1.exists()) {
            return;
         }

         JsonParser jsonparser2 = new JsonParser();
         JsonElement element3 = jsonparser2.parse(new FileReader(file1));
         if (element3.isJsonObject()) {
            field5 = element3.getAsJsonObject();
            String text4 = field5.has("lastLaunchedVersion") ? field5.get("lastLaunchedVersion").getAsString() : "";
            field6 = Config.get(text4).orElse(Config.field2);
            LunarLogger.method3("Loaded File: Options", new Object[0]);
            initialized = true;
         }
      } catch (Exception exception5) {
         LunarLogger.method7("Couldn't load file [" + exception5.getMessage() + "]", new Object[0]);
         exception5.printStackTrace();
      }
   }

   public static void method2(File file0) {
      dirty = false;

      try {
         File file1 = new File(file0, "optionsLC.txt");
         if (!file1.exists() && !file1.createNewFile()) {
            LunarLogger.method6("Saving", "Failed to create save file!", new Object[0]);
            return;
         }

         if (UnfocusedFpsLimiter.method2().isPresent() && !UnfocusedFpsLimiter.method4()) {
            field5.add("maxFPS", new JsonPrimitive(UnfocusedFpsLimiter.method2().getAsInt()));
         }

         field5.addProperty("lastLaunchedVersion", Bridge.getMinecraftVersion().toString());

         try (FileWriter filewriter2 = new FileWriter(file1)) {
            LunarConstants.field22.toJson(field5, filewriter2);
            LunarLogger.method1("Saved File: Options", new Object[0]);
         }
      } catch (IOException exception7) {
         exception7.printStackTrace();
      }
   }

   public static JsonPrimitive method3(String text0, JsonPrimitive json1) {
      try {
         if (field5.has(text0) && !field5.get(text0).isJsonNull()) {
            json1 = field5.get(text0).getAsJsonPrimitive();
         }

         json1 = method4(text0, json1, field7, "pre-type");
         json1 = method4(text0, json1, field8, "base converter");
         json1 = method4(text0, json1, field9, "fov converter");
      } catch (Exception exception3) {
         CrashReporter.method5(exception3, "Setting Conversion");
      }

      return json1;
   }

   public static JsonPrimitive method4(String text0, JsonPrimitive json1, List<OptionMigration> list, String text3) {
      for (OptionMigration killsounds35 : list) {
         if (killsounds35.method1(field6)) {
            if (dirty) {
               json1 = killsounds35.method2(text0, json1);
            } else {
               JsonPrimitive json6 = killsounds35.method2(text0, json1);
               if (!json6.equals(json1)) {
                  dirty = true;
               }

               json1 = json6;
            }
         }
      }

      return json1;
   }

   public static void method5(String text0) {
      String[] items1 = text0.split(":");
      String text2 = items1[0];
      String text3 = items1.length >= 2 ? text0.substring(text2.length() + 1) : "";
      field5.addProperty(text2, text3);
   }

   public static void method6(String text0, KeyCode bridgetype_81, int value) {
      KeyVersionPair threadmoduledump303 = new KeyVersionPair(text0, bridgetype_81, value);
      field2.put(text0, threadmoduledump303);
      field3.put(value, threadmoduledump303);
      field4.put(bridgetype_81, threadmoduledump303);
   }

   public static KeyCode method7(String text0) {
      return field2.getOrDefault(text0, field1).method2();
   }

   public static KeyCode method8(int number0) {
      return field3.getOrDefault(number0, field1).method2();
   }

   public static String method9(KeyCode bridgetype_80) {
      return field4.getOrDefault(bridgetype_80, field1).method1();
   }

   public static String method10(int number0) {
      return field3.getOrDefault(number0, field1).method1();
   }

   public static int method11(KeyCode bridgetype_80) {
      return field4.getOrDefault(bridgetype_80, field1).method3();
   }

   public static int method12(String text0) {
      return field2.getOrDefault(text0, field1).method3();
   }

   public static void method13(File file0) {
      if (dirty) {
         method2(file0);
      }
   }

   @Generated
   private VanillaOptionsFile() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static JsonObject method14() {
      return field5;
   }

   @Generated
   public static boolean isInitialized() {
      return initialized;
   }

   static {
      method6("key.mouse.left", KeyCode.KEY_MOUSE1, -100);
      method6("key.mouse.right", KeyCode.KEY_MOUSE2, -99);
      method6("key.mouse.middle", KeyCode.KEY_MOUSE3, -98);
      method6("key.mouse.4", KeyCode.KEY_MOUSE4, -97);
      method6("key.mouse.5", KeyCode.KEY_MOUSE5, -96);
      method6("key.keyboard.0", KeyCode.KEY_0, 11);
      method6("key.keyboard.1", KeyCode.KEY_1, 2);
      method6("key.keyboard.2", KeyCode.KEY_2, 3);
      method6("key.keyboard.3", KeyCode.KEY_3, 4);
      method6("key.keyboard.4", KeyCode.KEY_4, 5);
      method6("key.keyboard.5", KeyCode.KEY_5, 6);
      method6("key.keyboard.6", KeyCode.KEY_6, 7);
      method6("key.keyboard.7", KeyCode.KEY_7, 8);
      method6("key.keyboard.8", KeyCode.KEY_8, 9);
      method6("key.keyboard.9", KeyCode.KEY_9, 10);
      method6("key.keyboard.a", KeyCode.KEY_A, 30);
      method6("key.keyboard.b", KeyCode.KEY_B, 48);
      method6("key.keyboard.c", KeyCode.KEY_C, 46);
      method6("key.keyboard.d", KeyCode.KEY_D, 32);
      method6("key.keyboard.e", KeyCode.KEY_E, 18);
      method6("key.keyboard.f", KeyCode.KEY_F, 33);
      method6("key.keyboard.g", KeyCode.KEY_G, 34);
      method6("key.keyboard.h", KeyCode.KEY_H, 35);
      method6("key.keyboard.i", KeyCode.KEY_I, 23);
      method6("key.keyboard.j", KeyCode.KEY_J, 36);
      method6("key.keyboard.k", KeyCode.KEY_K, 37);
      method6("key.keyboard.l", KeyCode.KEY_L, 38);
      method6("key.keyboard.m", KeyCode.KEY_M, 50);
      method6("key.keyboard.n", KeyCode.KEY_N, 49);
      method6("key.keyboard.o", KeyCode.KEY_O, 24);
      method6("key.keyboard.p", KeyCode.KEY_P, 25);
      method6("key.keyboard.q", KeyCode.KEY_Q, 16);
      method6("key.keyboard.r", KeyCode.KEY_R, 19);
      method6("key.keyboard.s", KeyCode.KEY_S, 31);
      method6("key.keyboard.t", KeyCode.KEY_T, 20);
      method6("key.keyboard.u", KeyCode.KEY_U, 22);
      method6("key.keyboard.v", KeyCode.KEY_V, 47);
      method6("key.keyboard.w", KeyCode.KEY_W, 17);
      method6("key.keyboard.x", KeyCode.KEY_X, 45);
      method6("key.keyboard.y", KeyCode.KEY_Y, 21);
      method6("key.keyboard.z", KeyCode.KEY_Z, 44);
      method6("key.keyboard.f1", KeyCode.KEY_F1, 59);
      method6("key.keyboard.f2", KeyCode.KEY_F2, 60);
      method6("key.keyboard.f3", KeyCode.KEY_F3, 61);
      method6("key.keyboard.f4", KeyCode.KEY_F4, 62);
      method6("key.keyboard.f5", KeyCode.KEY_F5, 63);
      method6("key.keyboard.f6", KeyCode.KEY_F6, 64);
      method6("key.keyboard.f7", KeyCode.KEY_F7, 65);
      method6("key.keyboard.f8", KeyCode.KEY_F8, 66);
      method6("key.keyboard.f9", KeyCode.KEY_F9, 67);
      method6("key.keyboard.f10", KeyCode.KEY_F10, 68);
      method6("key.keyboard.f11", KeyCode.KEY_F11, 87);
      method6("key.keyboard.f12", KeyCode.KEY_F12, 88);
      method6("key.keyboard.f13", KeyCode.KEY_F13, 100);
      method6("key.keyboard.f14", KeyCode.KEY_F14, 101);
      method6("key.keyboard.f15", KeyCode.KEY_F15, 102);
      method6("key.keyboard.f16", KeyCode.KEY_F16, 103);
      method6("key.keyboard.f17", KeyCode.KEY_F17, 104);
      method6("key.keyboard.f18", KeyCode.KEY_F18, 105);
      method6("key.keyboard.f19", KeyCode.KEY_F19, 113);
      method6("key.keyboard.num.lock", KeyCode.KEY_NUMLOCK, 69);
      method6("key.keyboard.keypad.0", KeyCode.KEY_NUMPAD0, 82);
      method6("key.keyboard.keypad.1", KeyCode.KEY_NUMPAD1, 79);
      method6("key.keyboard.keypad.2", KeyCode.KEY_NUMPAD2, 80);
      method6("key.keyboard.keypad.3", KeyCode.KEY_NUMPAD3, 81);
      method6("key.keyboard.keypad.4", KeyCode.KEY_NUMPAD4, 75);
      method6("key.keyboard.keypad.5", KeyCode.KEY_NUMPAD5, 76);
      method6("key.keyboard.keypad.6", KeyCode.KEY_NUMPAD6, 77);
      method6("key.keyboard.keypad.7", KeyCode.KEY_NUMPAD7, 71);
      method6("key.keyboard.keypad.8", KeyCode.KEY_NUMPAD8, 72);
      method6("key.keyboard.keypad.9", KeyCode.KEY_NUMPAD9, 73);
      method6("key.keyboard.keypad.add", KeyCode.KEY_ADD, 78);
      method6("key.keyboard.keypad.decimal", KeyCode.KEY_DECIMAL, 83);
      method6("key.keyboard.keypad.enter", KeyCode.KEY_NUMPADENTER, 156);
      method6("key.keyboard.keypad.equal", KeyCode.KEY_NUMPADEQUALS, 141);
      method6("key.keyboard.keypad.multiply", KeyCode.KEY_MULTIPLY, 55);
      method6("key.keyboard.keypad.divide", KeyCode.KEY_DIVIDE, 181);
      method6("key.keyboard.keypad.subtract", KeyCode.KEY_SUBTRACT, 74);
      method6("key.keyboard.down", KeyCode.KEY_DOWN, 208);
      method6("key.keyboard.left", KeyCode.KEY_LEFT, 203);
      method6("key.keyboard.right", KeyCode.KEY_RIGHT, 205);
      method6("key.keyboard.up", KeyCode.KEY_UP, 200);
      method6("key.keyboard.apostrophe", KeyCode.KEY_APOSTROPHE, 40);
      method6("key.keyboard.backslash", KeyCode.KEY_BACKSLASH, 43);
      method6("key.keyboard.comma", KeyCode.KEY_COMMA, 51);
      method6("key.keyboard.equal", KeyCode.KEY_EQUALS, 13);
      method6("key.keyboard.grave.accent", KeyCode.KEY_GRAVE, 41);
      method6("key.keyboard.left.bracket", KeyCode.KEY_LBRACKET, 26);
      method6("key.keyboard.minus", KeyCode.KEY_MINUS, 12);
      method6("key.keyboard.period", KeyCode.KEY_PERIOD, 52);
      method6("key.keyboard.right.bracket", KeyCode.KEY_RBRACKET, 27);
      method6("key.keyboard.semicolon", KeyCode.KEY_SEMICOLON, 39);
      method6("key.keyboard.slash", KeyCode.KEY_SLASH, 53);
      method6("key.keyboard.space", KeyCode.KEY_SPACE, 57);
      method6("key.keyboard.tab", KeyCode.KEY_TAB, 15);
      method6("key.keyboard.left.alt", KeyCode.KEY_LMENU, 56);
      method6("key.keyboard.left.control", KeyCode.KEY_LCONTROL, 29);
      method6("key.keyboard.left.shift", KeyCode.KEY_LSHIFT, 42);
      method6("key.keyboard.right.alt", KeyCode.KEY_RMENU, 184);
      method6("key.keyboard.right.control", KeyCode.KEY_RCONTROL, 157);
      method6("key.keyboard.right.shift", KeyCode.KEY_RSHIFT, 54);
      method6("key.keyboard.enter", KeyCode.KEY_RETURN, 28);
      method6("key.keyboard.escape", KeyCode.KEY_ESCAPE, 1);
      method6("key.keyboard.backspace", KeyCode.KEY_BACK, 14);
      method6("key.keyboard.delete", KeyCode.KEY_DELETE, 211);
      method6("key.keyboard.end", KeyCode.KEY_END, 207);
      method6("key.keyboard.home", KeyCode.KEY_HOME, 199);
      method6("key.keyboard.insert", KeyCode.KEY_INSERT, 210);
      method6("key.keyboard.caps.lock", KeyCode.KEY_CAPITAL, 58);
      method6("key.keyboard.pause", KeyCode.KEY_PAUSE, 197);
      method6("key.keyboard.scroll.lock", KeyCode.KEY_SCROLL, 70);
   }
}

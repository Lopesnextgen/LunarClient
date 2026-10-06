package com.moonsworth.lunar.client.util;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moonsworth.lunar.bridge.Bridge3_22;
import com.moonsworth.lunar.client.framework.feature.tps.JsonDeserializerIterator;
import com.moonsworth.lunar.client.framework.feature.tps.Tps;
import com.moonsworth.lunar.client.inventorymod.JsonSerializer;
import com.moonsworth.lunar.client.itemtracker.TypeAdapter;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;

public class ThreadModuleDump48 {
   public static final String field1 = System.getProperty("os.name");
   public static final String field2 = System.getProperty("os.version");
   public static final String field3 = ThreadModuleDump65.firstNonNull(System.getenv("PROCESSOR_ARCHITECTURE"), System.getProperty("os.arch"));
   public static final boolean field4 = Objects.equals(System.getProperty("java.awt.headless"), "true");
   public static final long field5 = System.currentTimeMillis();
   public static final Path field6 = Paths.get(System.getProperty("lunar.dataDir", System.getProperty("user.home") + File.separator + ".lunarclient"));
   public static final Path field7 = field6.resolve("game-cache");
   public static final Path field8 = field6.resolve("launcher-cache");
   public static final Path field9 = field6.resolve("textures").resolve("assets").resolve("lunar");
   public static final Path field10 = field9.resolve("dev_cosmetics");
   public static final Path field11 = field6.resolve("jit").resolve("assets").resolve("lunar-jit");
   public static final Path field12 = field6.resolve("offline").resolve("multiver");
   public static final Path field13 = field12.resolve("hypixel").resolve("skyblock");
   public static final Path field14 = field7.resolve("blog-post-images");
   public static final Path field15 = field7.resolve("avatar");
   public static final Path field16 = field7.resolve("covers");
   public static final Path field17 = field7.resolve("imgsrc");
   public static final String field18 = "RpEJNT11YK7VjO07Z75cV8iEybS1DXSnhbsIeGP84Azp7xMsAY9oxcS7ZuVhMISv";
   public static final String field19 = "https://api.klipy.com/api/v1/RpEJNT11YK7VjO07Z75cV8iEybS1DXSnhbsIeGP84Azp7xMsAY9oxcS7ZuVhMISv/gifs";
   public static final int field20 = 24;
   public static String field21 = "https://resourcepacks.gg";
   public static final Gson field22 = new GsonBuilder()
      .registerTypeAdapter(Bridge3_22.class, new JsonSerializer())
      .registerTypeAdapter(Tps.class, new JsonDeserializerIterator())
      .registerTypeAdapter(LocalDate.class, new TypeAdapter())
      .registerTypeAdapterFactory(new Annotation2.Data())
      .create();
   public static final Gson field23 = new GsonBuilder()
      .setPrettyPrinting()
      .registerTypeAdapter(Bridge3_22.class, new JsonSerializer())
      .registerTypeAdapter(Tps.class, new JsonDeserializerIterator())
      .registerTypeAdapter(LocalDate.class, new TypeAdapter())
      .registerTypeAdapterFactory(new Annotation2.Data())
      .create();
   public static final Random field24 = ThreadLocalRandom.current();
   public static final File field25;
   public static final File field26;
   private static final List<String> field27 = ImmutableList.of(
      "assets/lunar-jit/cosmetics/",
      "assets/lunar-jit/sprays/",
      "assets/lunar-jit/shaders/",
      "assets/lunar/jit_index",
      "assets/lunar/cosmetics.json",
      "assets/lunar/dev_cosmetics.json",
      "assets/lunar/sprays.json",
      "assets/lunar/jams",
      "assets/lunar/misc/",
      "assets/lunar/emotes/",
      "assets/lunar/sprays",
      "assets/lunar/particles/",
      new String[]{
         "assets/lunar/badges",
         "assets/lunar/geckolib_cosmetics/",
         "assets/lunar/cosmetics/",
         "assets/lunar/dev_cosmetics/",
         "assets/lunar/font/",
         "assets/lunar/textures/font/",
         "assets/lunar/icons/hypixel",
         "assets/lunar/icons/alerts",
         "assets/lunar/textures/bedwars_height_limit",
         "assets/lunar/textures/bedwars_coloured_beds",
         "assets/lunar/textures/height_limit"
      }
   );
   public static boolean field28 = LunarBuildData.field1.equalsIgnoreCase("master");
   public static boolean field29 = LunarBuildData.field1.equalsIgnoreCase("dev");
   public static boolean field30 = LunarBuildData.field1.equalsIgnoreCase("beta");
   public static boolean field31 = !LunarBuildData.field4 || !field28;
   public static String field32 = "https://medal.tv/lunar";

   public static void bootstrap() {
   }

   @Generated
   public static List<String> method1() {
      return field27;
   }

   static {
      Path var0 = field6.resolve("settings");
      field25 = var0.resolve("game").toFile();
      field26 = var0.resolve("game-backup").toFile();
   }
}

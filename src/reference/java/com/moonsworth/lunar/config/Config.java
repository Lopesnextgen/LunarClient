package com.moonsworth.lunar.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.files.Files3_2;
import com.moonsworth.lunar.files.FilesException;
import com.moonsworth.lunar.files.Files_5;
import com.moonsworth.lunar.ichor.Annotation2;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Config implements Comparable<Config> {
   public static Config field1;
   public static Config field2;
   public static Config field3;
   public static Config field4;
   public static Config field5;
   public static Config field6;
   public static Config field7;
   public static Config field8;
   public static Config field9;
   public static Config field10;
   public static Config field11;
   public static Config field12;
   public static Config field13;
   public static Config field14;
   public static Config field15;
   public static Config field16;
   public static Config field17;
   public static Config field18;
   public static Config field19;
   public static Config field20;
   public static Config field21;
   public static Config field22;
   public static Config field23;
   public static Config field24;
   public static Config field25;
   public static Config field26;
   public static Config field27;
   public static Config field28;
   public static Config field29;
   public static Config field30;
   public static Config field31;
   public static Config field32;
   public static Config field33;
   public static Config field34;
   public static Config field35;
   public static Config field36;
   public static Config field37;
   public static Config field38;
   public static Config field39;
   public static Config field40;
   public static Config field41;
   public static final String field42 = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
   public static final String field43 = System.getProperty("ichor.gameDirectory", null);
   private static boolean initialized = false;
   private static Map<String, Config> field44 = new HashMap<>();
   private static Path field45;
   private String id;
   private int ordinal;
   private boolean field46;
   private boolean field47;
   private String field48;
   private boolean field49;
   private String field50;
   private String field51;
   private boolean field52;
   private boolean field53;
   private int field54;
   private String type;
   private String field55;
   private String field56;
   private String displayName;
   private String field57;
   private List<String> field58;
   private String field59;
   private boolean field60;
   private List<Config.Data> field61;
   private boolean field62;
   private transient List<Path> field63;

   public static void method1(Path var0) {
      try {
         if (!initialized) {
            initialized = true;
            field44.clear();
            field45 = var0;
            com.typesafe.config.Config var1 = ConfigFactory.empty();
            byte[] var2 = Files3_2.toByteArray(Config.class.getClassLoader().getResourceAsStream("versions.conf"));
            if (var2.length > 0) {
               var1 = ConfigFactory.parseString(new String(var2)).resolve();
            }

            if (var1.isEmpty()) {
               throw new IllegalStateException("The question is, why is versions.conf missing from the jar?");
            }

            for (String var4 : var1.root().keySet()) {
               if (var4.length() > 1 && var4.startsWith("v") && Character.isDigit(var4.charAt(1))) {
                  ArrayList var5 = new ArrayList();

                  for (ConfigValue var7 : var1.getList(var4 + ".modules")) {
                     if (var7 instanceof ConfigObject var8) {
                        com.typesafe.config.Config var9 = var8.toConfig();
                        String var10 = var9.getString("name");
                        boolean var11 = var9.hasPath("default") && var9.getBoolean("default");
                        boolean var12 = var9.hasPath("private") && var9.getBoolean("private");
                        String var13 = var9.hasPath("baseModpack.version") ? var9.getString("baseModpack.version") : null;
                        String var14 = var9.hasPath("baseModpack.loader") ? var9.getString("baseModpack.loader") : null;
                        ConfigList var15 = var9.getList("modules");
                        ArrayList var16 = new ArrayList();

                        for (ConfigValue var18 : var15) {
                           var16.add(var18.unwrapped().toString());
                        }

                        var5.add(new Config.Data(var10, var16, var11, var12, var13, var14));
                     }
                  }

                  Config var20 = method37()
                     .method1(var4)
                     .method2(var1.getInt(var4 + ".ordinal"))
                     .method3(var1.getBoolean(var4 + ".snapshot"))
                     .method4(var1.getBoolean(var4 + ".private-in-launcher"))
                     .method5(var1.getIsNull(var4 + ".optifine") ? null : var1.getString(var4 + ".optifine"))
                     .method6(var1.getBoolean(var4 + ".optifine-namespaced-jar"))
                     .method7(var1.getIsNull(var4 + ".external-manifest") ? null : var1.getString(var4 + ".external-manifest"))
                     .method8(var1.getString(var4 + ".launch-class"))
                     .method9(var1.getBoolean(var4 + ".core-opengl"))
                     .method10(var1.getBoolean(var4 + ".latest"))
                     .method11(var1.getInt(var4 + ".java-version"))
                     .method12(var1.getString(var4 + ".type"))
                     .method13(var1.getString(var4 + ".major-version"))
                     .method14(var1.getString(var4 + ".exact-version"))
                     .method15(var1.getIsNull(var4 + ".display-name") ? var1.getString(var4 + ".exact-version") : var1.getString(var4 + ".display-name"))
                     .method16(var1.getString(var4 + ".asset-index"))
                     .method17(var1.getStringList(var4 + ".dependencies"))
                     .method18(var1.getIsNull(var4 + ".version-module") ? var4 : var1.getString(var4 + ".version-module"))
                     .method19(var1.getBoolean(var4 + ".default-minor-version"))
                     .method20(var5)
                     .method21(var1.getBoolean(var4 + ".deobfuscated"))
                     .method23();
                  if (var20.field53) {
                     if (field41 != null) {
                        throw new IllegalStateException(field41.id + " and " + var20.id + " are both the latest version??");
                     }

                     field41 = var20;
                  }

                  field44.put(var4, var20);
               }
            }

            field1 = Objects.requireNonNull(field44.get("v1_7"));
            field2 = Objects.requireNonNull(field44.get("v1_8"));
            field3 = Objects.requireNonNull(field44.get("v1_9"));
            field4 = Objects.requireNonNull(field44.get("v1_10"));
            field5 = Objects.requireNonNull(field44.get("v1_11"));
            field6 = Objects.requireNonNull(field44.get("v1_12"));
            field7 = Objects.requireNonNull(field44.get("v1_16_1"));
            field8 = Objects.requireNonNull(field44.get("v1_16_5"));
            field9 = Objects.requireNonNull(field44.get("v1_17_0"));
            field10 = Objects.requireNonNull(field44.get("v1_17_1"));
            field11 = Objects.requireNonNull(field44.get("v1_18_1"));
            field12 = Objects.requireNonNull(field44.get("v1_18_2"));
            field13 = Objects.requireNonNull(field44.get("v1_19_pre"));
            field14 = Objects.requireNonNull(field44.get("v1_19_0"));
            field15 = Objects.requireNonNull(field44.get("v1_19_2"));
            field16 = Objects.requireNonNull(field44.get("v1_19_3"));
            field17 = Objects.requireNonNull(field44.get("v1_19_4"));
            field18 = Objects.requireNonNull(field44.get("v1_20_0"));
            field19 = Objects.requireNonNull(field44.get("v1_20_1"));
            field20 = Objects.requireNonNull(field44.get("v1_20_2"));
            field21 = Objects.requireNonNull(field44.get("v1_20_3"));
            field22 = Objects.requireNonNull(field44.get("v1_20_4"));
            field23 = Objects.requireNonNull(field44.get("v1_20_5"));
            field24 = Objects.requireNonNull(field44.get("v1_20_6"));
            field25 = Objects.requireNonNull(field44.get("v1_21_0"));
            field26 = Objects.requireNonNull(field44.get("v1_21_1"));
            field27 = Objects.requireNonNull(field44.get("v1_21_2"));
            field28 = Objects.requireNonNull(field44.get("v1_21_3"));
            field29 = Objects.requireNonNull(field44.get("v1_21_4"));
            field30 = Objects.requireNonNull(field44.get("v1_21_5"));
            field31 = Objects.requireNonNull(field44.get("v1_21_6"));
            field32 = Objects.requireNonNull(field44.get("v1_21_7"));
            field33 = Objects.requireNonNull(field44.get("v1_21_8"));
            field34 = Objects.requireNonNull(field44.get("v1_21_9"));
            field35 = Objects.requireNonNull(field44.get("v1_21_10"));
            field36 = Objects.requireNonNull(field44.get("v1_21_11"));
            field37 = Objects.requireNonNull(field44.get("v26_1"));
            field38 = Objects.requireNonNull(field44.get("v26_1_1"));
            field39 = Objects.requireNonNull(field44.get("v26_1_2"));
            field40 = Objects.requireNonNull(field44.get("v26_2"));
         }
      } catch (Throwable var19) {
         throw var19;
      }
   }

   public static Optional<Config> get(String var0) {
      if (!initialized) {
         throw new IllegalStateException("Never initialized MinecraftVersion");
      } else {
         return Optional.ofNullable(field44.get(var0)).or(() -> {
            for (Config var2 : field44.values()) {
               if (var2.method45().equals(var0)) {
                  return Optional.of(var2);
               }
            }

            for (Config var4 : field44.values()) {
               if (var4.method44().equals(var0)) {
                  return Optional.of(var4);
               }
            }

            return Optional.empty();
         });
      }
   }

   public static Optional<Config> method2(int var0) {
      for (Config var2 : field44.values()) {
         if (var2.ordinal == var0) {
            return Optional.of(var2);
         }
      }

      return Optional.empty();
   }

   public boolean method3(Config var1) {
      return var1.ordinal < this.ordinal;
   }

   public boolean method4(Config var1) {
      return var1.ordinal <= this.ordinal;
   }

   public boolean method5(Config var1) {
      return var1.ordinal > this.ordinal;
   }

   public boolean method6(Config var1) {
      return var1.ordinal >= this.ordinal;
   }

   public static List<Config> method7(Config var0, Config var1) {
      return method8(var0, var1, false);
   }

   public static List<Config> method8(Config var0, Config var1, boolean var2) {
      ArrayList var3 = new ArrayList();

      for (Config var5 : field44.values()) {
         if (var2 ? var5.ordinal < var0.ordinal && var5.ordinal > var1.ordinal : var5.ordinal >= var0.ordinal && var5.ordinal <= var1.ordinal) {
            var3.add(var5);
         }
      }

      var3.sort(Comparator.naturalOrder());
      return var3;
   }

   public static List<Config> method9(Config var0) {
      ArrayList var1 = new ArrayList();

      for (Config var3 : field44.values()) {
         if (var3.method4(var0)) {
            var1.add(var3);
         }
      }

      var1.sort(null);
      return var1;
   }

   public static List<Config> method10(Config var0) {
      ArrayList var1 = new ArrayList();

      for (Config var3 : field44.values()) {
         if (var3.method6(var0)) {
            var1.add(var3);
         }
      }

      var1.sort(null);
      return var1;
   }

   public int method11(Config var1) {
      return Integer.compare(this.ordinal, var1.ordinal);
   }

   public static Collection<Config> method12() {
      return field44.values();
   }

   public Optional<String> method13() {
      return Optional.ofNullable(this.field50);
   }

   public Optional<String> method14() {
      return this.method13().map(var0 -> Config.class.getClassLoader().getResourceAsStream(var0)).map(var1 -> {
         try {
            return new String(Files3_2.toByteArray(var1));
         } catch (IOException var3) {
            throw new IllegalStateException("Couldn't read external json: " + this.field50);
         }
      });
   }

   public static Optional<Path> method15() {
      return Optional.ofNullable(field45);
   }

   public static Optional<Path> method16(String var0, String... var1) {
      return method15().map(var2 -> var2.resolve(Paths.get(var0, var1)));
   }

   public Optional<Path> method17() {
      return method16("lib", "OptiFine_" + this.id + ".jar");
   }

   public boolean method18() {
      return this.field48 != null;
   }

   @Annotation2(min = 6, onReturn = true)
   public boolean method19() {
      return "modern".equals(this.type);
   }

   @Annotation2(min = 5, onReturn = true)
   public boolean method20() {
      return this.method19() || this == field6;
   }

   @Annotation2(max = 5, onReturn = true)
   public boolean method21() {
      return "legacy".equals(this.type);
   }

   @Annotation2(min = 0, max = 1, onReturn = true)
   public boolean method22() {
      return "v1_7".equals(this.id) || "v1_8".equals(this.id);
   }

   @Annotation2(min = 8, onReturn = true)
   public boolean method23() {
      return this.field52;
   }

   @Annotation2(min = 17, onReturn = true)
   public boolean method24() {
      return this.method4(field18);
   }

   @Annotation2(min = 30, onReturn = true)
   public boolean method25() {
      return this.method4(field31);
   }

   public boolean method26() {
      return this.method21();
   }

   @Override
   public String toString() {
      return this.id;
   }

   public int toInt() {
      String[] var1 = this.method45().split("\\.");
      int var2 = Integer.parseInt(var1[0]);
      int var3 = Integer.parseInt(var1[1]);
      int var4 = var1.length > 2 ? Integer.parseInt(var1[2]) : 0;
      return var2 * 10000 + var3 * 100 + var4;
   }

   public void method27(Path var1, String var2) {
      if (Files_5.method1(var1)) {
         try {
            Files.createDirectories(var1.getParent());
            JsonObject var3 = this.method31();
            JsonObject var4 = var3.getAsJsonObject("downloads");
            String var5 = var4.getAsJsonObject(var2).get("url").getAsString();
            byte[] var6 = Files3_2.method3(var5);
            Files.write(var1, var6);
         } catch (FilesException | IOException var7) {
            throw new IllegalStateException("Failed to download Minecraft jar " + var1, var7);
         }
      }
   }

   public Path method28() {
      try {
         Path var1 = method34().resolve(Paths.get("versions", this.field56, this.field56 + ".jar"));
         this.method27(var1, "client");
         return var1;
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public URL method29() {
      try {
         return this.method28().toUri().toURL();
      } catch (Throwable var2) {
         throw var2;
      }
   }

   public static JsonObject method30() {
      byte[] var0;
      try {
         var0 = Files3_2.method3("https://launchermeta.mojang.com/mc/game/version_manifest.json");
      } catch (FilesException var4) {
         throw new IllegalStateException("Couldn't download manifest: https://launchermeta.mojang.com/mc/game/version_manifest.json", var4);
      }

      String var1 = new String(var0);

      try {
         return new JsonParser().parse(var1).getAsJsonObject();
      } catch (Exception var3) {
         throw new IllegalStateException("Failed to parse version manifest", var3);
      }
   }

   public JsonObject method31() {
      try {
         String var1 = null;
         if (this.field50 != null) {
            try {
               InputStream var2 = Config.class.getClassLoader().getResourceAsStream(this.field50);
               if (var2 != null) {
                  var1 = new String(Files3_2.toByteArray(var2));
               } else if (field45 != null) {
                  var1 = Files.readString(field45.resolve(this.field50));
               }
            } catch (IOException var10) {
               throw new IllegalStateException("Failed to read external manifest " + this.field50);
            }
         }

         if (var1 == null) {
            Path var13 = method34().resolve(Paths.get("versions", this.field56, this.field56 + ".json"));
            if (!Files.exists(var13)) {
               try {
                  Files.createDirectories(var13.getParent());
               } catch (IOException var9) {
                  throw new IllegalStateException("Failed to create directories at " + var13.getParent(), var9);
               }

               JsonObject var3 = method30();

               try {
                  for (JsonElement var5 : var3.getAsJsonArray("versions")) {
                     JsonObject var6 = var5.getAsJsonObject();
                     if (var6.get("id").getAsString().equals(this.field56)) {
                        byte[] var7 = Files3_2.method3(var6.get("url").getAsString());
                        if (var7 != null) {
                           Files.writeString(var13, new String(var7));
                        }
                     }
                  }
               } catch (Exception var11) {
                  throw new IllegalStateException("Failed to parse manifest", var11);
               }
            }

            try {
               var1 = Files.readString(var13);
            } catch (IOException var8) {
               throw new IllegalStateException("Failed to read manifest on disk at " + var13, var8);
            }
         }

         if (var1 == null) {
            throw new IllegalStateException("versionJson is null??");
         } else {
            return new JsonParser().parse(var1).getAsJsonObject();
         }
      } catch (Throwable var12) {
         throw var12;
      }
   }

   private List<Path> method32() {
      try {
         ArrayList var1 = new ArrayList();
         Path var2 = method34().resolve("libraries");

         for (String var4 : this.field58) {
            Path var5 = ConfigType.download(var4, var2);
            if (var5 != null) {
               var1.add(var5);
            }
         }

         return var1;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   public List<Path> method33() {
      if (this.field63 == null) {
         this.field63 = this.method32();
      }

      return this.field63;
   }

   public static Path method34() {
      if (field43 != null) {
         return Paths.get(field43);
      } else {
         String var0 = System.getProperty("os.name").toLowerCase(Locale.ROOT);
         if (var0.contains("win")) {
            return Paths.get(System.getenv("APPDATA"), ".minecraft");
         } else if (var0.contains("mac") || var0.contains("darwin")) {
            return Paths.get(System.getProperty("user.home"), "Library/Application Support/minecraft");
         } else if (var0.contains("linux")) {
            return Paths.get(System.getProperty("user.home"), ".minecraft/");
         } else {
            throw new RuntimeException("Failed to determine Minecraft directory for OS: " + var0);
         }
      }
   }

   private static String method35() {
      String var0 = System.getProperty("os.name").toLowerCase(Locale.ROOT);
      if (var0.contains("win")) {
         return "natives-windows";
      } else if (var0.contains("mac") || var0.contains("darwin")) {
         return "natives-osx";
      } else if (var0.contains("linux")) {
         return "natives-linux";
      } else {
         throw new RuntimeException("Failed to determine natives name for OS: " + var0);
      }
   }

   public static Config method36(Map<String, Object> var0) {
      Object var1 = var0.get("mcVer");
      if (var1 == null) {
         throw new IllegalStateException("Couldn't find MinecraftVersion in config");
      } else {
         return get(var1.toString()).orElseThrow(() -> new IllegalStateException("Couldn't find MinecraftVersion in config"));
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id, this.field56);
   }

   @Generated
   Config(
      String var1,
      int var2,
      boolean var3,
      boolean var4,
      String var5,
      boolean var6,
      String var7,
      String var8,
      boolean var9,
      boolean var10,
      int var11,
      String var12,
      String var13,
      String var14,
      String var15,
      String var16,
      List<String> var17,
      String var18,
      boolean var19,
      List<Config.Data> var20,
      boolean var21,
      List<Path> var22
   ) {
      this.id = var1;
      this.ordinal = var2;
      this.field46 = var3;
      this.field47 = var4;
      this.field48 = var5;
      this.field49 = var6;
      this.field50 = var7;
      this.field51 = var8;
      this.field52 = var9;
      this.field53 = var10;
      this.field54 = var11;
      this.type = var12;
      this.field55 = var13;
      this.field56 = var14;
      this.displayName = var15;
      this.field57 = var16;
      this.field58 = var17;
      this.field59 = var18;
      this.field60 = var19;
      this.field61 = var20;
      this.field62 = var21;
      this.field63 = var22;
   }

   @Generated
   public static Config.Data2 method37() {
      return new Config.Data2();
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public int getOrdinal() {
      return this.ordinal;
   }

   @Generated
   public boolean isSnapshot() {
      return this.field46;
   }

   @Generated
   public boolean method38() {
      return this.field47;
   }

   @Generated
   public String method39() {
      return this.field48;
   }

   @Generated
   public boolean method40() {
      return this.field49;
   }

   @Generated
   public String method41() {
      return this.field51;
   }

   @Generated
   public boolean method42() {
      return this.field53;
   }

   @Generated
   public int method43() {
      return this.field54;
   }

   @Generated
   public String getType() {
      return this.type;
   }

   @Generated
   public String method44() {
      return this.field55;
   }

   @Generated
   public String method45() {
      return this.field56;
   }

   @Generated
   public String getDisplayName() {
      return this.displayName;
   }

   @Generated
   public String method46() {
      return this.field57;
   }

   @Generated
   public List<String> getDependencies() {
      return this.field58;
   }

   @Generated
   public String method47() {
      return this.field59;
   }

   @Generated
   public boolean method48() {
      return this.field60;
   }

   @Generated
   public List<Config.Data> method49() {
      return this.field61;
   }

   @Generated
   public boolean method50() {
      return this.field62;
   }

   @Generated
   public void setId(String var1) {
      this.id = var1;
   }

   @Generated
   public void setOrdinal(int var1) {
      this.ordinal = var1;
   }

   @Generated
   public void method51(boolean var1) {
      this.field46 = var1;
   }

   @Generated
   public void method52(boolean var1) {
      this.field47 = var1;
   }

   @Generated
   public void method53(String var1) {
      this.field48 = var1;
   }

   @Generated
   public void method54(boolean var1) {
      this.field49 = var1;
   }

   @Generated
   public void method55(String var1) {
      this.field50 = var1;
   }

   @Generated
   public void method56(String var1) {
      this.field51 = var1;
   }

   @Generated
   public void method57(boolean var1) {
      this.field52 = var1;
   }

   @Generated
   public void method58(boolean var1) {
      this.field53 = var1;
   }

   @Generated
   public void method59(int var1) {
      this.field54 = var1;
   }

   @Generated
   public void setType(String var1) {
      this.type = var1;
   }

   @Generated
   public void method60(String var1) {
      this.field55 = var1;
   }

   @Generated
   public void method61(String var1) {
      this.field56 = var1;
   }

   @Generated
   public void setDisplayName(String var1) {
      this.displayName = var1;
   }

   @Generated
   public void method62(String var1) {
      this.field57 = var1;
   }

   @Generated
   public void method63(List<String> var1) {
      this.field58 = var1;
   }

   @Generated
   public void method64(String var1) {
      this.field59 = var1;
   }

   @Generated
   public void method65(boolean var1) {
      this.field60 = var1;
   }

   @Generated
   public void method66(List<Config.Data> var1) {
      this.field61 = var1;
   }

   @Generated
   public void method67(boolean var1) {
      this.field62 = var1;
   }

   @Generated
   public void method68(List<Path> var1) {
      this.field63 = var1;
   }

   public static class Data {
      public final String field1;
      public final List<String> field2;
      public final boolean field3;
      public final boolean field4;
      @Nullable
      public final String field5;
      @Nullable
      public final String field6;

      @Generated
      public Data(String var1, List<String> var2, boolean var3, boolean var4, @Nullable String var5, @Nullable String var6) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
      }

      @Generated
      public String getName() {
         return this.field1;
      }

      @Generated
      public List<String> method1() {
         return this.field2;
      }

      @Generated
      public boolean method2() {
         return this.field3;
      }

      @Generated
      public boolean method3() {
         return this.field4;
      }

      @Nullable
      @Generated
      public String method4() {
         return this.field5;
      }

      @Nullable
      @Generated
      public String method5() {
         return this.field6;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Config.Data var2)) {
            return false;
         } else {
            if (!var2.canEqual(this)) {
               return false;
            }

            if (this.method2() != var2.method2()) {
               return false;
            }

            if (this.method3() != var2.method3()) {
               return false;
            }

            String var3 = this.getName();
            String var4 = var2.getName();
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               List var5 = this.method1();
               List var6 = var2.method1();
               if (var5 == null ? var6 == null : var5.equals(var6)) {
                  String var7 = this.method4();
                  String var8 = var2.method4();
                  if (var7 == null ? var8 == null : var7.equals(var8)) {
                     String var9 = this.method5();
                     String var10 = var2.method5();
                     return var9 == null ? var10 == null : var9.equals(var10);
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof Config.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + (this.method2() ? 79 : 97);
         var2 = var2 * 59 + (this.method3() ? 79 : 97);
         String var3 = this.getName();
         var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
         List var4 = this.method1();
         var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
         String var5 = this.method4();
         var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
         String var6 = this.method5();
         return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      }

      @Generated
      @Override
      public String toString() {
         return "MinecraftVersion.ModuleGroup(name="
            + this.getName()
            + ", modules="
            + this.method1()
            + ", def="
            + this.method2()
            + ", priv="
            + this.method3()
            + ", baseModpackVersion="
            + this.method4()
            + ", baseModpackLoader="
            + this.method5()
            + ")";
      }
   }

   @Generated
   public static class Data2 {
      @Generated
      private String id;
      @Generated
      private int ordinal;
      @Generated
      private boolean field1;
      @Generated
      private boolean field2;
      @Generated
      private String field3;
      @Generated
      private boolean field4;
      @Generated
      private String field5;
      @Generated
      private String field6;
      @Generated
      private boolean field7;
      @Generated
      private boolean field8;
      @Generated
      private int field9;
      @Generated
      private String type;
      @Generated
      private String field10;
      @Generated
      private String field11;
      @Generated
      private String displayName;
      @Generated
      private String field12;
      @Generated
      private List<String> field13;
      @Generated
      private String field14;
      @Generated
      private boolean field15;
      @Generated
      private List<Config.Data> field16;
      @Generated
      private boolean field17;
      @Generated
      private List<Path> field18;

      @Generated
      Data2() {
      }

      @Generated
      public Config.Data2 method1(String var1) {
         this.id = var1;
         return this;
      }

      @Generated
      public Config.Data2 method2(int var1) {
         this.ordinal = var1;
         return this;
      }

      @Generated
      public Config.Data2 method3(boolean var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method4(boolean var1) {
         this.field2 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method5(String var1) {
         this.field3 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method6(boolean var1) {
         this.field4 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method7(String var1) {
         this.field5 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method8(String var1) {
         this.field6 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method9(boolean var1) {
         this.field7 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method10(boolean var1) {
         this.field8 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method11(int var1) {
         this.field9 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method12(String var1) {
         this.type = var1;
         return this;
      }

      @Generated
      public Config.Data2 method13(String var1) {
         this.field10 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method14(String var1) {
         this.field11 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method15(String var1) {
         this.displayName = var1;
         return this;
      }

      @Generated
      public Config.Data2 method16(String var1) {
         this.field12 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method17(List<String> var1) {
         this.field13 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method18(String var1) {
         this.field14 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method19(boolean var1) {
         this.field15 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method20(List<Config.Data> var1) {
         this.field16 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method21(boolean var1) {
         this.field17 = var1;
         return this;
      }

      @Generated
      public Config.Data2 method22(List<Path> var1) {
         this.field18 = var1;
         return this;
      }

      @Generated
      public Config method23() {
         return new Config(
            this.id,
            this.ordinal,
            this.field1,
            this.field2,
            this.field3,
            this.field4,
            this.field5,
            this.field6,
            this.field7,
            this.field8,
            this.field9,
            this.type,
            this.field10,
            this.field11,
            this.displayName,
            this.field12,
            this.field13,
            this.field14,
            this.field15,
            this.field16,
            this.field17,
            this.field18
         );
      }

      @Generated
      @Override
      public String toString() {
         return "MinecraftVersion.MinecraftVersionBuilder(id="
            + this.id
            + ", ordinal="
            + this.ordinal
            + ", snapshot="
            + this.field1
            + ", privateInLauncher="
            + this.field2
            + ", optifine="
            + this.field3
            + ", optifineNamespacedJar="
            + this.field4
            + ", externalManifest="
            + this.field5
            + ", launchClass="
            + this.field6
            + ", coreOpenGL="
            + this.field7
            + ", latest="
            + this.field8
            + ", javaVersion="
            + this.field9
            + ", type="
            + this.type
            + ", majorVersion="
            + this.field10
            + ", exactVersion="
            + this.field11
            + ", displayName="
            + this.displayName
            + ", assetIndex="
            + this.field12
            + ", dependencies="
            + this.field13
            + ", versionModule="
            + this.field14
            + ", defaultMinorVersion="
            + this.field15
            + ", moduleGroups="
            + this.field16
            + ", deobfuscated="
            + this.field17
            + ", dependencyPaths="
            + this.field18
            + ")";
      }
   }
}

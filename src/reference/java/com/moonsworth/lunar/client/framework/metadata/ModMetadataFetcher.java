package com.moonsworth.lunar.client.framework.metadata;

import com.google.common.base.CaseFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.io.JsonAdapters;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.client.util.Util;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.OptionGroup;
import com.moonsworth.lunar.client.framework.mod.ModMetadata;
import com.moonsworth.lunar.client.render.particle.Mod;
import com.moonsworth.lunar.client.framework.ApolloModGenerator;

public class ModMetadataFetcher extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<String, ModMetadata> implements com.moonsworth.lunar.client.config.JsonFileConfig {
   @Nullable
   public static final Path field2;
   public static final ModMetadata field3 = new ModMetadata(null, null, null, null);
   private ApolloModGenerator field4;
   private JsonObject field5 = new JsonObject();
   private CompletableFuture<HttpResponse<String>> field6;

   public ModMetadataFetcher() {
      if (field2 != null) {
         this.field4 = new ApolloModGenerator();
      } else if (FeatureFlag.MOD_METADATA.isEnabled()) {
         this.method10();
      }
   }

   public ModMetadata method1(String var1) {
      if (field2 != null) {
         return field3;
      }

      if (this.field6 != null) {
         try {
            HttpResponse var2 = this.field6.get();
            if (var2.statusCode() == 200) {
               String var3 = (String)var2.body();
               Gson var4 = new GsonBuilder().setLenient().create();
               JsonObject var5 = (JsonObject)var4.fromJson(var3, JsonObject.class);

               try {
                  if (var5.has("mods")) {
                     this.load(var5);
                  } else {
                     this.method4();
                  }
               } catch (IOException var11) {
                  var11.printStackTrace();
                  this.method4();
               }
            } else {
               this.method4();
            }
         } catch (InterruptedException | ExecutionException var12) {
            var12.printStackTrace();
            this.method4();
         } finally {
            this.field6 = null;
         }
      }

      return this.method2().getOrDefault(var1, field3);
   }

   public void method2(Set<Framework7Extension> var1) {
      var1.forEach(var1x -> {
         ModDetails var2 = (ModDetails)var1x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
         this.method3(var1x.getId(), var1x, var2);
      });
   }

   private void method3(String var1, Framework7Extension var2, @Nullable ModDetails var3) {
      Set var4;
      Set var5;
      Set var6;
      if (var3 != null) {
         var4 = new HashSet();

         for (Calculator2Handler var8 : var3.method1()) {
            if (var8 == Calculator2Handler.field7) {
               return;
            }

            var4.add(var8.getName());
         }

         var5 = var3.method2();
         var6 = var3.method3();
      } else {
         var4 = Collections.emptySet();
         var5 = Collections.emptySet();
         var6 = Collections.emptySet();
      }

      ModMetadata var12 = new ModMetadata(var5, var4, var6, null);
      this.method2().put(var1, var12);
      LinkedList var13 = new LinkedList();
      var2.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(Framework.field6).flatMap(ModEnabledState::method1).ifPresent(var13::add);
      Framework5 var9 = (Framework5)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var9 != null) {
         for (ClientOption var11 : var9.method2()) {
            if (method10(var11)) {
               var13.add(var11);
            }
         }
      }

      this.field4.method1(var1, new OptionGroup(var12.method1(var1) + ".details", var13));
   }

   @Override
   protected Map<String, ModMetadata> method3() {
      return new HashMap<>();
   }

   @Override
   public String method5() {
      return "mod_fallback.json";
   }

   @Override
   public void load(JsonObject var1) {
      var1 = var1.get("mods").getAsJsonObject();
      this.field5 = var1;
      Gson var2 = new Gson();
      Map var3 = this.method2();
      var3.clear();

      for (Entry var5 : var1.entrySet()) {
         var3.put((String)var5.getKey(), (ModMetadata)var2.fromJson((JsonElement)var5.getValue(), ModMetadata.class));
      }

      this.OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   @Override
   public void method1(JsonObject var1) {
      var1.add("mods", this.field5);
   }

   public void method10() {
      try {
         HttpRequest var1 = HttpRequest.newBuilder()
            .uri(URI.create(ServiceEndpoints.method7() + "/game/metadata/mod"))
            .header("X-Installation-Id", ThreadModuleDump80.installationId)
            .header("X-Overwolf-Muid", ThreadModuleDump80.overwolfMuid)
            .GET()
            .build();
         this.field6 = HttpClient.newHttpClient().sendAsync(var1, BodyHandlers.ofString());
      } catch (Exception var2) {
         var2.printStackTrace();
         this.method4();
      }
   }

   @Override
   public void close() {
      if (field2 != null) {
         this.method11();

         try {
            this.field4.method2(field2.resolve("api"));
            this.method9(field2);
         } catch (IOException var2) {
            throw new RuntimeException(var2);
         }

         System.exit(0);
      } else {
         this.clear();
         this.field5 = null;
      }
   }

   private void method11() {
      Gson var1 = new GsonBuilder()
         .setPrettyPrinting()
         .registerTypeAdapter(String.class, new JsonAdapters.StringSerializer())
         .registerTypeHierarchyAdapter(Set.class, new JsonAdapters.SetSerializer())
         .registerTypeHierarchyAdapter(Map.class, new JsonAdapters.Data())
         .create();
      JsonObject var2 = (JsonObject)var1.fromJson(var1.toJson(this.method2()), JsonObject.class);
      JsonObject var3 = new JsonObject();
      var3.add("mods", var2);
      File var4 = Paths.get(".").normalize().toAbsolutePath().toFile();
      File var5 = new File(var4, "mod_metadata.json");

      try {
         var4.mkdirs();
         var5.createNewFile();
         var1.toJson(var3, new FileWriter(var5));
      } catch (IOException var7) {
         var7.printStackTrace();
      }
   }

   private void method9(Path var1) {
      Path var2 = var1.resolve("docs").resolve("developers").resolve("mods");
      Files.createDirectories(var2);
      TranslationManager var3 = Client.method109().method67();
      String var4 = "_meta.json";
      File var5 = var2.resolve(var4).toFile();
      var5.createNewFile();
      FileWriter var6 = new FileWriter(var5, false);
      var6.write("{\n");
      List var7 = this.method2().entrySet().stream().sorted(Entry.comparingByKey()).toList();
      int var8 = 0;

      for (Entry var10 : var7) {
         String var11 = (String)var10.getKey();
         ModMetadata var12 = (ModMetadata)var10.getValue();
         String var13 = var12.method1(var11) + ".details";
         String var14 = ThreadModuleDump46.method6(var11, false).toLowerCase() + ".mdx";
         File var15 = var2.resolve(var14).toFile();
         var15.createNewFile();
         var8++;
         String var16 = var8 != this.method2().entrySet().size() ? "," : "";
         var6.write(Util.method7(1) + "\"" + var14.replace(".mdx", "") + "\": \"" + ThreadModuleDump46.method6(var11, true) + "\"" + var16 + "\n");
         FileWriter var17 = new FileWriter(var15, false);
         String var18 = var3.method2(var13, "name");
         var17.write("# " + var18 + "\n\n");
         String var19 = var3.method2(var13, "description");
         if (!"description".equals(var19)) {
            var17.write(var19 + "\n\n");
         }

         var17.write("## Integration\n\n");
         var17.write("### How to toggle the mod\n\n");
         var17.write(
            "```java\npublic void toggle%sExample(Player viewer, boolean value) {\n    Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());\n    apolloPlayerOpt.ifPresent(apolloPlayer -> this.modSettingModule.getOptions().set(apolloPlayer, %s.ENABLED, value));\n}\n```\n\n"
               .formatted(var18.replaceAll("[ ./]", ""), "Mod" + method11(var11))
         );
         var17.write("## Available options\n\n");

         for (ClientOption var22 : this.field4.getOptions(var11)) {
            Object var23 = var22.get();
            String var24 = ColorOption.class.isAssignableFrom(var22.method5()) ? "String" : var23.getClass().getSimpleName();
            String var25 = var22.method3();
            String var26 = var25 + "Description";
            String var27 = var22.OHROCHICOIOICHOCRROORRCIIICIHO(var26, new Object[0]);
            var17.write("- __`" + ThreadModuleDump46.method5(var22.getId()) + "`__\n");
            if (!var26.equals(var27)) {
               var17.write("    - " + var27 + "\n");
            }

            var17.write("    - Config Key: `" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, var25) + "`\n");
            var17.write("    - Values\n");
            var17.write("        - Type: `" + var24 + "`\n");
            var17.write("        - Default: `" + method13(var22, var23) + "`\n");
            if (!ColorOption.class.isAssignableFrom(var22.method5())) {
               NumberRule var28 = (NumberRule)var22.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
               if (var28 != null) {
                  var17.write("        - Minimum: `" + method13(var22, var28.getMin()) + "`\n");
                  var17.write("        - Maximum: `" + method13(var22, var28.getMax()) + "`\n\n");
               } else {
                  var17.write("\n");
               }
            } else {
               var17.write("\n");
            }
         }

         var17.close();
      }

      var6.write("}");
      var6.close();
   }

   public static boolean method10(ClientOption<?> var0) {
      return !var0.method2(com.moonsworth.lunar.client.config.option.OptionTraits.field12)
            && var0.method2(com.moonsworth.lunar.client.config.option.OptionTraits.field5)
         ? var0.method2(com.moonsworth.lunar.client.config.option.OptionTraits.field7) || var0.getDefaultValue() instanceof Boolean
         : false;
   }

   protected static String method11(String var0) {
      return ThreadModuleDump46.method6(var0.replace('.', '_'), true);
   }

   protected static String method12(ClientOption<?> var0, Object var1) {
      if (var1 instanceof Integer var2 && ColorOption.class.isAssignableFrom(var0.method5())) {
         Color var3 = new Color(var2, true);
         int var4 = var3.getRed();
         int var5 = var3.getGreen();
         int var6 = var3.getBlue();
         int var7 = var3.getAlpha();
         return var7 < 255 ? String.format("new Color(%d, %d, %d, %d)", var4, var5, var6, var7) : String.format("new Color(%d, %d, %d)", var4, var5, var6);
      } else {
         return var1.toString() + method14(var1);
      }
   }

   protected static String method13(ClientOption<?> var0, Object var1) {
      return var1 instanceof Integer var2 && ColorOption.class.isAssignableFrom(var0.method5())
         ? "#" + Integer.toHexString(var2).toUpperCase()
         : var1.toString() + method14(var1);
   }

   protected static String method14(Object var0) {
      if (var0 instanceof Float) {
         return "F";
      } else if (var0 instanceof Double) {
         return "D";
      } else {
         return var0 instanceof Long ? "L" : "";
      }
   }

   static {
      String var0 = System.getProperty("lunar.generateModClasses");
      field2 = var0 != null && !var0.isEmpty() ? Path.of(var0) : null;
   }

   public class Data {
      private final String field1;
      private final Framework7Extension field2;
      private final ModDetails field3;
      private final boolean field4;

      public Data(String var1, Framework7Extension var2, ModDetails var3, boolean var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public String method1() {
         return this.field1;
      }

      public Framework7Extension method2() {
         return this.field2;
      }

      public ModDetails method3() {
         return this.field3;
      }

      public boolean enabledByDefault() {
         return this.field4;
      }
   }
}

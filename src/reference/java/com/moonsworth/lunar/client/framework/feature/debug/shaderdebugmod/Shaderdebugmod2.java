package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import com.moonsworth.lunar.files.Files6_2;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;

public class Shaderdebugmod2 {
   public static void method1(Shaderdebugmod var0, boolean var1) {
      ShaderCloakRenderer var2 = null;
      if (var1) {
         var2 = ThreadModuleDump63.method4().method40().method74().method24();
      }

      if (var2 != null) {
         var2.method24(var2x -> {
            File var3x = method3(var0, var1, var2x);
            method2(var3x);
         });
      } else {
         File var3 = method3(var0, var1, null);
         method2(var3);
      }
   }

   private static void method2(File var0) {
      if (var0 == null) {
         ThreadModuleDump63.method4().method69().method7(NotificationType.ERROR, "Unable to export shader cloak!");
      }
   }

   private static File method3(Shaderdebugmod var0, boolean var1, BufferedImage var2) {
      Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
      if (var3 == null) {
         return null;
      }

      CosmeticMetadata var4 = ThreadModuleDump63.method4().method53().method15(var3.bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
      if (var4 == null) {
         return null;
      }

      ResourceLocationBridge var5 = var0.getLocation();
      String var6 = var4.method4().getName().toLowerCase().replace(" ", "_");
      ArrayList var7 = new ArrayList();
      Path var8 = ThreadModuleDump48.field10;
      Path var9 = var8.resolve("cloaks");
      boolean var10 = false;

      for (ShaderResource var12 : var0.method48()) {
         if (!JitPaths.method1(var12.method2())) {
            if (!var10) {
               var9 = var9.resolve(var6);
               var10 = true;
            }

            Path var13 = method6(var8, var12.method2().bridge$getPath());
            Path var14 = var1 ? var9.resolve(var13.getFileName()) : var13;
            var7.add(Files6_2.method1(var13, var14));
         }
      }

      Path var27 = var8.resolve("shaders").resolve(var6);
      if (var0.method49()) {
         Path var28 = method6(var8, var0.method50());
         Path var32 = var1 ? var27.resolve(var6 + ".vsh") : var28;
         var7.add(Files6_2.method1(var28, var32));
      }

      if (var0.method51()) {
         Path var29 = method6(var8, var0.method52());
         Path var33 = var1 ? var27.resolve(var6 + ".fsh") : var29;
         var7.add(Files6_2.method1(var29, var33));
      }

      if (!var1) {
         Path var30 = method6(var8, var5.bridge$getPath());
         var7.add(Files6_2.method1(var30, var30));
      }

      int var31 = 0;

      do {
         String var35 = (var31 == 0 ? "" : "_" + var31) + ".zip";
         String var15 = var1 ? "export_" : "dev_";
         Path var34 = var8.resolve(var15 + var6 + var35);
         if (!Files.exists(var34)) {
            File var36 = var34.toFile();

            try (ZipOutputStream var37 = new ZipOutputStream(new FileOutputStream(var36))) {
               for (Files6_2 var17 : var7) {
                  Path var18 = (Path)var17.field1;
                  if (!Files.exists(var18) || !Files.isRegularFile(var18)) {
                     Slayer.method8("Shader Cloaks", "Unable to export shader cloak! File %s is invalid!", new Object[]{var18});
                     return null;
                  }

                  String var19;
                  if (var17.field2 instanceof Path var21) {
                     var19 = var8.relativize(var21).toString().replace("\\", "/");
                  } else {
                     if (!(var17.field2 instanceof String var20)) {
                        return null;
                     }

                     var19 = var20;
                  }

                  var37.putNextEntry(new ZipEntry(var19));
                  String var48 = var18.getFileName().toString();
                  if (var1 && (var48.endsWith(".vsh") || var48.endsWith(".fsh"))) {
                     String var51 = Files.readString(var18);
                     int var23 = var51.indexOf("LUNAR-SHADER-DEFINITION-MARKER");
                     if (var23 >= 0) {
                        var51 = var51.substring(var23);
                     }

                     var37.write(var51.getBytes(StandardCharsets.UTF_8));
                  } else {
                     Files.copy(var18, var37);
                  }

                  var37.closeEntry();
               }

               ZipEntry var38 = new ZipEntry("shader_textures_EXPORT.json");
               var37.putNextEntry(var38);
               JsonObject var41 = new JsonObject();
               String var42 = var1 ? "cosmetics/cloaks/" + var6 + "/" + var6 + ".webp" : var5.toString();
               var41.add(var42, var0.method4(var1, var6));
               String var44 = ThreadModuleDump48.field23.toJson(var41);
               var37.write(var44.getBytes(StandardCharsets.UTF_8));
               var37.closeEntry();
               if (!var1) {
                  JsonArray var46 = new JsonArray();
                  JsonObject var49 = new JsonObject();
                  var49.addProperty("name", var4.method4().getName());
                  var49.addProperty("file", var4.method4().method3().toString());
                  var49.addProperty("category", "cloak");
                  var46.add(var49);
                  var38 = new ZipEntry("dev_cosmetics_EXPORT.json");
                  var37.putNextEntry(var38);
                  var44 = ThreadModuleDump48.field23.toJson(var46);
                  var37.write(var44.getBytes(StandardCharsets.UTF_8));
                  var37.closeEntry();
               }

               if (var1) {
                  if (var2 == null) {
                     Slayer.method8("Shader Cloaks", "Unable to take a screenshot of the cloak!", new Object[0]);
                     return null;
                  }

                  var38 = new ZipEntry("cloaks/" + var6 + "/" + var6 + ".png");
                  var37.putNextEntry(var38);
                  ImageIO.write(var2, "PNG", var37);
                  var37.closeEntry();
               }
            } catch (IOException var26) {
               Slayer.method8("Shader Cloaks", "Unable to export shader cloak! %s", new Object[]{var26.getMessage()});
               var26.printStackTrace();
               return null;
            }

            method4(var34);
            return var36;
         }
      } while (++var31 < 1000);

      Slayer.method8("Shader Cloaks", "Reached max export file limit! Please delete some exports!", new Object[0]);
      return null;
   }

   public static void method4(Path var0) {
      try {
         if (ThreadModuleDumpType2.isWindows()) {
            new ProcessBuilder("explorer.exe", "/select,", var0.toString()).start();
         } else if (ThreadModuleDumpType2.isMacos()) {
            new ProcessBuilder("open", "-R", var0.toString()).start();
         } else if (ThreadModuleDumpType2.isLinux()) {
            new ProcessBuilder("xdg-open", var0.getParent().toString()).start();
         }
      } catch (Exception var2) {
      }
   }

   public static String method5(ResourceLocationBridge var0, boolean var1) {
      String var2 = var0.bridge$getPath();
      var2 = var2.replaceFirst("dev_cosmetics", "cosmetics");
      return var1 ? "lunar-jit:" + var2 : var2;
   }

   public static Path method6(Path var0, String var1) {
      Path var2 = var0;
      if (var1.contains(":")) {
         var1 = ResourceLocationBridge.create(var1).bridge$getPath();
      }

      var1 = var1.replaceFirst("dev_cosmetics/", "");

      for (String var6 : var1.split("/")) {
         var2 = var2.resolve(var6);
      }

      return var2;
   }

   public static String method7(boolean var0, ShaderResource var1, String var2) {
      if (var0 && !JitPaths.method1(var1.method2())) {
         String[] var3 = var1.method2().bridge$getPath().split("/");
         String var4 = var3[var3.length - 1];
         int var5 = var4.lastIndexOf(".");
         if (var5 >= 0) {
            var4 = var4.substring(0, var5) + ".webp";
         }

         return "lunar-jit:cosmetics/cloaks/" + var2 + "/" + var4;
      } else {
         return var1.method2().toString();
      }
   }

   public static String method8(boolean var0, boolean var1, boolean var2, String var3, String var4) {
      return var1 && var0 ? "lunar-jit:cosmetics/cloaks/" + var4 + "/" + var4 + (var2 ? ".vsh" : ".fsh") : var3;
   }
}

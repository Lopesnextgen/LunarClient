package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.cosmetics.ShaderCloakRenderer;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import com.moonsworth.lunar.files.ValuePair;
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

public class ShaderCloakExporter {
   public ShaderCloakExporter() {
   }

   public static void method1(Shaderdebugmod shaderdebugmod0, boolean flag1) {
      ShaderCloakRenderer fov32 = null;
      if (flag1) {
         fov32 = Ref.method4().method40().method74().method24();
      }

      if (fov32 != null) {
         fov32.method24(arg2x -> {
            File file3x = method3(shaderdebugmod0, flag1, arg2x);
            method2(file3x);
         });
      } else {
         File file3 = method3(shaderdebugmod0, flag1, null);
         method2(file3);
      }
   }

   private static void method2(File file0) {
      if (file0 == null) {
         Ref.method4().method69().method7(NotificationType.ERROR, "Unable to export shader cloak!");
      }
   }

   private static File method3(Shaderdebugmod shaderdebugmod0, boolean flag1, BufferedImage bufferedimage2) {
      Bridge5Extension_5 bridge5extension_53 = Ref.method7();
      if (bridge5extension_53 == null) {
         return null;
      }

      CosmeticMetadata gui2handler34 = Ref.method4().method53().method15(bridge5extension_53.bridge$getUniqueID(), CosmeticCategoryType.CLOAK);
      if (gui2handler34 == null) {
         return null;
      }

      ResourceLocationBridge horsestats145 = shaderdebugmod0.getLocation();
      String text6 = gui2handler34.method4().getName().toLowerCase().replace(" ", "_");
      ArrayList list7 = new ArrayList();
      Path path8 = LunarConstants.field10;
      Path path9 = path8.resolve("cloaks");
      boolean flag10 = false;

      for (ShaderResource colorsaturation512 : shaderdebugmod0.method48()) {
         if (!JitPaths.method1(colorsaturation512.method2())) {
            if (!flag10) {
               path9 = path9.resolve(text6);
               flag10 = true;
            }

            Path path13 = method6(path8, colorsaturation512.method2().bridge$getPath());
            Path path14 = flag1 ? path9.resolve(path13.getFileName()) : path13;
            list7.add(ValuePair.method1(path13, path14));
         }
      }

      Path path27 = path8.resolve("shaders").resolve(text6);
      if (shaderdebugmod0.method49()) {
         Path path28 = method6(path8, shaderdebugmod0.method50());
         Path path32 = flag1 ? path27.resolve(text6 + ".vsh") : path28;
         list7.add(ValuePair.method1(path28, path32));
      }

      if (shaderdebugmod0.method51()) {
         Path path29 = method6(path8, shaderdebugmod0.method52());
         Path path33 = flag1 ? path27.resolve(text6 + ".fsh") : path29;
         list7.add(ValuePair.method1(path29, path33));
      }

      if (!flag1) {
         Path path30 = method6(path8, horsestats145.bridge$getPath());
         list7.add(ValuePair.method1(path30, path30));
      }

      int number31 = 0;

      do {
         String text35 = (number31 == 0 ? "" : "_" + number31) + ".zip";
         String text15 = flag1 ? "export_" : "dev_";
         Path path34 = path8.resolve(text15 + text6 + text35);
         if (!Files.exists(path34)) {
            File file36 = path34.toFile();

            try (ZipOutputStream zipoutputstream37 = new ZipOutputStream(new FileOutputStream(file36))) {
               for (ValuePair files6_217 : list7) {
                  Path path18 = (Path)files6_217.field1;
                  if (!Files.exists(path18) || !Files.isRegularFile(path18)) {
                     LunarLogger.method8("Shader Cloaks", "Unable to export shader cloak! File %s is invalid!", new Object[]{path18});
                     return null;
                  }

                  String text19;
                  if (files6_217.field2 instanceof Path path21) {
                     text19 = path8.relativize(path21).toString().replace("\\", "/");
                  } else {
                     if (!(files6_217.field2 instanceof String text20)) {
                        return null;
                     }

                     text19 = text20;
                  }

                  zipoutputstream37.putNextEntry(new ZipEntry(text19));
                  String text48 = path18.getFileName().toString();
                  if (flag1 && (text48.endsWith(".vsh") || text48.endsWith(".fsh"))) {
                     String text51 = Files.readString(path18);
                     int index23 = text51.indexOf("LUNAR-SHADER-DEFINITION-MARKER");
                     if (index23 >= 0) {
                        text51 = text51.substring(index23);
                     }

                     zipoutputstream37.write(text51.getBytes(StandardCharsets.UTF_8));
                  } else {
                     Files.copy(path18, zipoutputstream37);
                  }

                  zipoutputstream37.closeEntry();
               }

               ZipEntry zipentry38 = new ZipEntry("shader_textures_EXPORT.json");
               zipoutputstream37.putNextEntry(zipentry38);
               JsonObject json41 = new JsonObject();
               String text42 = flag1 ? "cosmetics/cloaks/" + text6 + "/" + text6 + ".webp" : horsestats145.toString();
               json41.add(text42, shaderdebugmod0.method4(flag1, text6));
               String text44 = LunarConstants.field23.toJson(json41);
               zipoutputstream37.write(text44.getBytes(StandardCharsets.UTF_8));
               zipoutputstream37.closeEntry();
               if (!flag1) {
                  JsonArray array46 = new JsonArray();
                  JsonObject json49 = new JsonObject();
                  json49.addProperty("name", gui2handler34.method4().getName());
                  json49.addProperty("file", gui2handler34.method4().method3().toString());
                  json49.addProperty("category", "cloak");
                  array46.add(json49);
                  zipentry38 = new ZipEntry("dev_cosmetics_EXPORT.json");
                  zipoutputstream37.putNextEntry(zipentry38);
                  text44 = LunarConstants.field23.toJson(array46);
                  zipoutputstream37.write(text44.getBytes(StandardCharsets.UTF_8));
                  zipoutputstream37.closeEntry();
               }

               if (flag1) {
                  if (bufferedimage2 == null) {
                     LunarLogger.method8("Shader Cloaks", "Unable to take a screenshot of the cloak!", new Object[0]);
                     return null;
                  }

                  zipentry38 = new ZipEntry("cloaks/" + text6 + "/" + text6 + ".png");
                  zipoutputstream37.putNextEntry(zipentry38);
                  ImageIO.write(bufferedimage2, "PNG", zipoutputstream37);
                  zipoutputstream37.closeEntry();
               }
            } catch (IOException exception26) {
               LunarLogger.method8("Shader Cloaks", "Unable to export shader cloak! %s", new Object[]{exception26.getMessage()});
               exception26.printStackTrace();
               return null;
            }

            method4(path34);
            return file36;
         }
      } while (++number31 < 1000);

      LunarLogger.method8("Shader Cloaks", "Reached max export file limit! Please delete some exports!", new Object[0]);
      return null;
   }

   public static void method4(Path path0) {
      try {
         if (OperatingSystem.isWindows()) {
            new ProcessBuilder("explorer.exe", "/select,", path0.toString()).start();
         } else if (OperatingSystem.isMacos()) {
            new ProcessBuilder("open", "-R", path0.toString()).start();
         } else if (OperatingSystem.isLinux()) {
            new ProcessBuilder("xdg-open", path0.getParent().toString()).start();
         }
      } catch (Exception exception2) {
      }
   }

   public static String method5(ResourceLocationBridge horsestats140, boolean flag1) {
      String text2 = horsestats140.bridge$getPath();
      text2 = text2.replaceFirst("dev_cosmetics", "cosmetics");
      return flag1 ? "lunar-jit:" + text2 : text2;
   }

   public static Path method6(Path path0, String text1) {
      Path path2 = path0;
      if (text1.contains(":")) {
         text1 = ResourceLocationBridge.create(text1).bridge$getPath();
      }

      text1 = text1.replaceFirst("dev_cosmetics/", "");

      for (String text6 : text1.split("/")) {
         path2 = path2.resolve(text6);
      }

      return path2;
   }

   public static String method7(boolean flag0, ShaderResource colorsaturation51, String text2) {
      if (flag0 && !JitPaths.method1(colorsaturation51.method2())) {
         String[] items3 = colorsaturation51.method2().bridge$getPath().split("/");
         String text4 = items3[items3.length - 1];
         int index5 = text4.lastIndexOf(".");
         if (index5 >= 0) {
            text4 = text4.substring(0, index5) + ".webp";
         }

         return "lunar-jit:cosmetics/cloaks/" + text2 + "/" + text4;
      } else {
         return colorsaturation51.method2().toString();
      }
   }

   public static String method8(boolean flag0, boolean flag1, boolean flag2, String text3, String text4) {
      return flag1 && flag0 ? "lunar-jit:cosmetics/cloaks/" + text4 + "/" + text4 + (flag2 ? ".vsh" : ".fsh") : text3;
   }
}

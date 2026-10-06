package com.moonsworth.lunar.config;

import com.moonsworth.lunar.files.MappingDownloader;
import com.moonsworth.lunar.files.FileHashUtils;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import lombok.Generated;

public enum ConfigType {
   FABRIC_MAVEN("https://maven.fabricmc.net/"),
   MOJANG_LIBRARIES("https://libraries.minecraft.net/"),
   MAVEN_CENTRAL("https://repo1.maven.org/maven2/"),
   LUNAR_FABRIC_MAVEN("https://maven-cache.lunarclientcdn.com/maven.fabricmc.net/"),
   LUNAR_MOJANG_LIBRARIES("https://maven-cache.lunarclientcdn.com/libraries.minecraft.net/"),
   LUNAR_MAVEN_CENTRAL("https://maven-cache.lunarclientcdn.com/repo1.maven.org/maven2/");

   private final String url;

   ConfigType(String text3) {
      this.url = text3;
   }

   public static Path download(String text0, Path path1) {
      String[] items2 = text0.split(":");
      if (items2.length < 3) {
         return null;
      }

      String text3 = items2[0];
      String text4 = items2[1];
      String text5 = items2[2];
      String text6 = null;
      if (items2.length >= 4) {
         text6 = items2[3];
      }

      text3 = text3.replaceAll("\\.", "/");
      String text7 = text4 + "-" + text5 + ".jar";
      if (text6 != null) {
         text7 = text4 + "-" + text5 + "-" + text6 + ".jar";
      }

      Path path8 = Paths.get(text3, text4, text5, text7);
      String text9 = path8.toString().replaceAll("\\\\", "/");
      Path path10 = path1.resolve(path8);
      if (FileHashUtils.method1(path10)) {
         ConfigType[] items11 = values();
         ArrayList list12 = new ArrayList(items11.length);

         for (ConfigType configtype16 : items11) {
            list12.add(configtype16.url);
         }

         try {
            MappingDownloader.method5(list12, text9, arg1x -> {
               try {
                  Files.createDirectories(path10.getParent());
                  Files.write(path10, arg1x);
                  return !FileHashUtils.method1(path10);
               } catch (Exception exception3x) {
                  throw new IllegalStateException("Failed to write jar bytes " + path10, exception3x);
               }
            });
         } catch (Exception exception17) {
            throw new IllegalStateException("Failed to write jar bytes " + path10, exception17);
         }

         if (Files.notExists(path10)) {
            throw new FileNotFoundException("Failed to download " + path10 + "!");
         }
      }

      return path10;
   }

   @Generated
   public String getUrl() {
      return this.url;
   }
}

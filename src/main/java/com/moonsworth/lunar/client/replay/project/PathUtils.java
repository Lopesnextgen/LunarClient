package com.moonsworth.lunar.client.replay.project;

import java.io.File;
import java.nio.file.Path;

public class PathUtils {
   public PathUtils() {
   }

   public static Path method1(Path path0, Path path1) {
      try {
         Path path2 = path0.toFile().exists() ? path0.toRealPath() : path0;
         Path path3 = path1.toFile().exists() ? path1.toRealPath() : path1;

         try {
            return path3.relativize(path2);
         } catch (Exception exception5) {
            return path2;
         }
      } catch (Exception exception6) {
         return path0;
      }
   }

   public static String method2(File file0, File file1) {
      return method1(file0.toPath(), file1.toPath()).toString().replace("\\", "/");
   }
}

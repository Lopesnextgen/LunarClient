package com.moonsworth.lunar.client.framework.feature.rewind.gui;

import java.io.File;
import java.nio.file.Path;

public class Gui5 {
   public static Path method1(Path var0, Path var1) {
      try {
         Path var2 = var0.toFile().exists() ? var0.toRealPath() : var0;
         Path var3 = var1.toFile().exists() ? var1.toRealPath() : var1;

         try {
            return var3.relativize(var2);
         } catch (Exception var5) {
            return var2;
         }
      } catch (Exception var6) {
         return var0;
      }
   }

   public static String method2(File var0, File var1) {
      return method1(var0.toPath(), var1.toPath()).toString().replace("\\", "/");
   }
}

package com.moonsworth.lunar.client.util;

import java.io.File;
import java.io.IOException;
import lombok.Generated;

public final class ThreadModuleDump28 {
   public static boolean method1(File file) {
      if (file.exists() && file.isDirectory()) {
         String var1 = file.getAbsolutePath();
         String var2 = null;
         if (ThreadModuleDumpType2.isWindows()) {
            var2 = "explorer.exe";
         } else if (ThreadModuleDumpType2.isMacos()) {
            var2 = "open";
         } else if (ThreadModuleDumpType2.isLinux()) {
            var2 = "xdg-open";
         }

         if (var2 != null) {
            try {
               new ProcessBuilder(var2, var1).start();
               return true;
            } catch (IOException var4) {
               var4.printStackTrace();
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Generated
   private ThreadModuleDump28() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

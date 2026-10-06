package com.moonsworth.lunar.client.util.io;

import java.io.File;
import java.io.IOException;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.OperatingSystem;

public final class FileExplorer {
   public static boolean method1(File file0) {
      if (file0.exists() && file0.isDirectory()) {
         String text1 = file0.getAbsolutePath();
         String text2 = null;
         if (OperatingSystem.isWindows()) {
            text2 = "explorer.exe";
         } else if (OperatingSystem.isMacos()) {
            text2 = "open";
         } else if (OperatingSystem.isLinux()) {
            text2 = "xdg-open";
         }

         if (text2 != null) {
            try {
               new ProcessBuilder(text2, text1).start();
               return true;
            } catch (IOException exception4) {
               exception4.printStackTrace();
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Generated
   private FileExplorer() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

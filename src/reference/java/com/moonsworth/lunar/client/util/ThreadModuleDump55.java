package com.moonsworth.lunar.client.util;

import java.net.URL;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public final class ThreadModuleDump55 {
   public static String method1(String var0) {
      String var1 = FilenameUtils.getExtension(new URL(var0).getPath());
      return method3(var1, "application/octet-stream");
   }

   public static String method2(String var0) {
      if (var0 == null) {
         return null;
      }

      String var1 = FilenameUtils.getExtension(var0);
      String var2 = method3(var1, null);
      return var2 != null && var2.startsWith("image/") ? var2 : null;
   }

   private static String method3(String var0, String var1) {
      if (var0 != null && !var0.isEmpty()) {
         return switch (var0.toLowerCase()) {
            case "png", "imgsrc" -> "image/png";
            case "jpeg", "jpg" -> "image/jpeg";
            case "gif" -> "image/gif";
            case "webp" -> "image/webp";
            case "html" -> "text/html";
            case "js" -> "application/javascript";
            case "css" -> "text/css";
            case "svg" -> "image/svg+xml";
            case "ttf" -> "font/ttf";
            default -> var1;
         };
      } else {
         return var1;
      }
   }

   @Generated
   private ThreadModuleDump55() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

package com.moonsworth.lunar.client.util.io;

import java.net.URL;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public final class MimeTypeUtils {
   public static String method1(String text0) {
      String text1 = FilenameUtils.getExtension(new URL(text0).getPath());
      return method3(text1, "application/octet-stream");
   }

   public static String method2(String text0) {
      if (text0 == null) {
         return null;
      }

      String text1 = FilenameUtils.getExtension(text0);
      String text2 = method3(text1, null);
      return text2 != null && text2.startsWith("image/") ? text2 : null;
   }

   private static String method3(String text0, String text1) {
      if (text0 != null && !text0.isEmpty()) {
         return switch (text0.toLowerCase()) {
            case "png", "imgsrc" -> "image/png";
            case "jpeg", "jpg" -> "image/jpeg";
            case "gif" -> "image/gif";
            case "webp" -> "image/webp";
            case "html" -> "text/html";
            case "js" -> "application/javascript";
            case "css" -> "text/css";
            case "svg" -> "image/svg+xml";
            case "ttf" -> "font/ttf";
            default -> text1;
         };
      } else {
         return text1;
      }
   }

   @Generated
   private MimeTypeUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

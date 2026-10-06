package com.moonsworth.lunar.client.util.io;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.OperatingSystem;

public class ClipboardUtils {
   public ClipboardUtils() {
   }

   public static String method1() {
      if (LunarConstants.field4) {
         return Bridge.method47().method1();
      }

      try {
         Clipboard clipboard0 = Toolkit.getDefaultToolkit().getSystemClipboard();
         if (clipboard0.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            return (String)clipboard0.getData(DataFlavor.stringFlavor);
         }
      } catch (Exception exception1) {
      }

      return "";
   }

   public static void method2(String text0) {
      if (!StringUtils.isEmpty(text0)) {
         if (LunarConstants.field4) {
            Bridge.method47().method2(text0);
            return;
         }

         try {
            StringSelection stringselection1 = new StringSelection(text0);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection1, null);
         } catch (Exception exception2) {
         }
      }
   }

   public static boolean method3() {
      return !LunarConstants.field4 || OperatingSystem.isMacos();
   }

   public static boolean method4(@Nullable BufferedImage bufferedimage0, Path path1) {
      if (!LunarConstants.field4) {
         try {
            BufferedImage bufferedimage2 = bufferedimage0 != null ? bufferedimage0 : ImageIO.read(path1.toFile());
            if (bufferedimage2 == null) {
               return false;
            }

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ClipboardUtils.Data(bufferedimage2), null);
            return true;
         } catch (Exception exception3) {
            LunarLogger.warn("Couldn't copy image to the clipboard", exception3);
            return false;
         }
      } else {
         return OperatingSystem.isMacos() ? method5(path1) : false;
      }
   }

   private static boolean method5(Path path0) {
      String text1 = path0.toAbsolutePath().toString().replace("\\", "\\\\").replace("\"", "\\\"");

      try {
         Process process2 = new ProcessBuilder("osascript", "-e", "set the clipboard to (read (POSIX file \"" + text1 + "\") as «class PNGf»)").start();
         if (!process2.waitFor(10L, TimeUnit.SECONDS)) {
            process2.destroyForcibly();
            return false;
         } else {
            return process2.exitValue() == 0;
         }
      } catch (Exception exception3) {
         LunarLogger.warn("Couldn't copy image to the clipboard via osascript", exception3);
         return false;
      }
   }

   private static class Data implements Transferable {
      private final Image field1;

      Data(Image image1) {
         this.field1 = image1;
      }

      @Override
      public DataFlavor[] getTransferDataFlavors() {
         return new DataFlavor[]{DataFlavor.imageFlavor};
      }

      @Override
      public boolean isDataFlavorSupported(DataFlavor dataflavor1) {
         return DataFlavor.imageFlavor == dataflavor1;
      }

      @Override
      public Object getTransferData(DataFlavor dataflavor1) {
         if (DataFlavor.imageFlavor != dataflavor1) {
            throw new UnsupportedFlavorException(dataflavor1);
         } else {
            return this.field1;
         }
      }
   }
}

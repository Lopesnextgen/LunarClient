package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.function.BooleanSupplier;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ThreadModuleDump29 {
   private static final ThreadModuleDump29.Type field1;
   private static final Image field2;

   public static boolean method1() {
      return field1 != ThreadModuleDump29.Type.NONE;
   }

   public static void method2(String var0, String var1, MessageType var2) {
      if (field1 != ThreadModuleDump29.Type.NONE) {
         field1.runner.send(var0, var1, var2);
      }
   }

   @Nullable
   private static Image method3() {
      try {
         IResourceBridge var0 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(ResourceLocationBridge.create("lunar", "logo/logo-64x64.png"));
         return var0 == null ? null : ImageIO.read(var0.bridge$getInputStream());
      } catch (IOException var1) {
         return null;
      }
   }

   static {
      if (ThreadModuleDump48.field4) {
         field1 = ThreadModuleDump29.Type.NONE;
         field2 = null;
      } else {
         ThreadModuleDump29.Type var0 = ThreadModuleDump29.Type.NONE;
         Image var1 = null;

         for (ThreadModuleDump29.Type var5 : ThreadModuleDump29.Type.values()) {
            if (var5 != ThreadModuleDump29.Type.NONE) {
               if (var5.needsIcon) {
                  if (var1 == null) {
                     var1 = method3();
                  }

                  if (var1 == null) {
                     continue;
                  }
               }

               if (var5.tester.getAsBoolean()) {
                  var0 = var5;
                  break;
               }
            }
         }

         field1 = var0;
         field2 = field1.needsIcon ? var1 : null;
      }
   }

   @FunctionalInterface
   private interface Extension {
      void send(String var1, String var2, MessageType var3);
   }

   public enum Type {
      NONE(false, null, null),
      NOTIFY(false, () -> {
         if (!ThreadModuleDumpType2.isLinux()) {
            return false;
         }

         try {
            return Runtime.getRuntime().exec(new String[]{"notify-send", "--help", ">", "nil"}).waitFor() == 0;
         } catch (Exception var1) {
            return false;
         }
      }, (var0, var1, var2) -> {
         String var3 = switch (var2) {
            case ERROR -> "critical";
            case WARNING -> "normal";
            default -> "low";
         };
         ProcessBuilder var4 = new ProcessBuilder("notify-send", "--app-name=Lunar Client", "--urgency=" + var3, "--", var0, var1);

         try {
            var4.start();
         } catch (IOException var6) {
            throw new RuntimeException(var6);
         }
      }),
      SYSTEM_TRAY(true, SystemTray::isSupported, (var0, var1, var2) -> {
         SystemTray var3 = SystemTray.getSystemTray();
         TrayIcon var4 = new TrayIcon(ThreadModuleDump29.field2, "Lunar Client");
         var4.setImageAutoSize(true);

         try {
            var3.add(var4);
            var4.displayMessage(var0, var1, var2);
         } catch (AWTException var6) {
            throw new RuntimeException(var6);
         }
      }),
      APPLE_SCRIPT(
         false,
         () -> {
            if (!ThreadModuleDumpType2.isMacos()) {
               return false;
            }

            try {
               Process var0 = Runtime.getRuntime().exec(new String[]{"osascript", "-?"});
               return var0.waitFor() != 2 && var0.exitValue() != 0;
            } catch (Exception var1) {
               return false;
            }
         },
         (var0, var1, var2) -> {
            ProcessBuilder var3 = new ProcessBuilder(
               "osascript",
               "-e",
               "display notification (system attribute \"LUNAR_NOTIFICATION_MESSAGE\") with title (system attribute \"LUNAR_NOTIFICATION_TITLE\")"
            );
            var3.environment().put("LUNAR_NOTIFICATION_TITLE", var0);
            var3.environment().put("LUNAR_NOTIFICATION_MESSAGE", var1);

            try {
               var3.start();
            } catch (IOException var5) {
               throw new RuntimeException(var5);
            }
         }
      );

      private final boolean needsIcon;
      private final BooleanSupplier tester;
      private final ThreadModuleDump29.Extension runner;

      @Generated
      Type(boolean var3, BooleanSupplier var4, ThreadModuleDump29.Extension var5) {
         this.needsIcon = var3;
         this.tester = var4;
         this.runner = var5;
      }
   }
}

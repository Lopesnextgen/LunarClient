package com.moonsworth.lunar.client.ui.notification;

import com.moonsworth.lunar.bridge.ResourceBridge;
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
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.OperatingSystem;

public class DesktopNotifier {
   private static final DesktopNotifier.Type field1;
   private static final Image field2;

   public DesktopNotifier() {
   }

   public static boolean method1() {
      return field1 != DesktopNotifier.Type.NONE;
   }

   public static void method2(String text, String text1, MessageType messageType) {
      if (field1 != DesktopNotifier.Type.NONE) {
         field1.runner.send(text, text1, messageType);
      }
   }

   @Nullable
   private static Image method3() {
      try {
         ResourceBridge bridge150 = Ref.method3().bridge$getResourceManager().bridge$getResource(ResourceLocationBridge.create("lunar", "logo/logo-64x64.png"));
         return bridge150 == null ? null : ImageIO.read(bridge150.bridge$getInputStream());
      } catch (IOException exception1) {
         return null;
      }
   }

   static {
      if (LunarConstants.field4) {
         field1 = DesktopNotifier.Type.NONE;
         field2 = null;
      } else {
         DesktopNotifier.Type type0 = DesktopNotifier.Type.NONE;
         Image image1 = null;

         for (DesktopNotifier.Type type5 : DesktopNotifier.Type.values()) {
            if (type5 != DesktopNotifier.Type.NONE) {
               if (type5.needsIcon) {
                  if (image1 == null) {
                     image1 = method3();
                  }

                  if (image1 == null) {
                     continue;
                  }
               }

               if (type5.tester.getAsBoolean()) {
                  type0 = type5;
                  break;
               }
            }
         }

         field1 = type0;
         field2 = field1.needsIcon ? image1 : null;
      }
   }

   @FunctionalInterface
   private interface Extension {
      void send(String text1, String text2, MessageType messagetype3);
   }

   public enum Type {
      NONE(false, null, null),
      NOTIFY(false, () -> {
         if (!OperatingSystem.isLinux()) {
            return false;
         }

         try {
            return Runtime.getRuntime().exec(new String[]{"notify-send", "--help", ">", "nil"}).waitFor() == 0;
         } catch (Exception exception1) {
            return false;
         }
      }, (arg0, arg1, arg2) -> {
         String text3 = switch (arg2) {
            case ERROR -> "critical";
            case WARNING -> "normal";
            default -> "low";
         };
         ProcessBuilder processbuilder4 = new ProcessBuilder("notify-send", "--app-name=Lunar Client", "--urgency=" + text3, "--", arg0, arg1);

         try {
            processbuilder4.start();
         } catch (IOException exception6) {
            throw new RuntimeException(exception6);
         }
      }),
      SYSTEM_TRAY(true, SystemTray::isSupported, (arg0, arg1, arg2) -> {
         SystemTray systemtray3 = SystemTray.getSystemTray();
         TrayIcon trayicon4 = new TrayIcon(DesktopNotifier.field2, "Lunar Client");
         trayicon4.setImageAutoSize(true);

         try {
            systemtray3.add(trayicon4);
            trayicon4.displayMessage(arg0, arg1, arg2);
         } catch (AWTException awtexception6) {
            throw new RuntimeException(awtexception6);
         }
      }),
      APPLE_SCRIPT(
         false,
         () -> {
            if (!OperatingSystem.isMacos()) {
               return false;
            }

            try {
               Process process0 = Runtime.getRuntime().exec(new String[]{"osascript", "-?"});
               return process0.waitFor() != 2 && process0.exitValue() != 0;
            } catch (Exception exception1) {
               return false;
            }
         },
         (arg0, arg1, arg2) -> {
            ProcessBuilder processbuilder3 = new ProcessBuilder(
               "osascript",
               "-e",
               "display notification (system attribute \"LUNAR_NOTIFICATION_MESSAGE\") with title (system attribute \"LUNAR_NOTIFICATION_TITLE\")"
            );
            processbuilder3.environment().put("LUNAR_NOTIFICATION_TITLE", arg0);
            processbuilder3.environment().put("LUNAR_NOTIFICATION_MESSAGE", arg1);

            try {
               processbuilder3.start();
            } catch (IOException exception5) {
               throw new RuntimeException(exception5);
            }
         }
      );

      private final boolean needsIcon;
      private final BooleanSupplier tester;
      private final DesktopNotifier.Extension runner;

      @Generated
      Type(boolean flag, BooleanSupplier booleansupplier4, DesktopNotifier.Extension extension5) {
         this.needsIcon = flag;
         this.tester = booleansupplier4;
         this.runner = extension5;
      }
   }
}

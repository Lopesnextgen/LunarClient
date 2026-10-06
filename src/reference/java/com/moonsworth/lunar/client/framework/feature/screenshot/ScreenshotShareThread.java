package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.UUID;
import java.util.function.IntConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ScreenshotShareThread extends Thread {
   private final Path field1;
   @Nullable
   private final UUID field2;
   private final boolean field3;
   private final boolean field4;
   private final String field5;
   private final IntConsumer field6;
   private final String field7;
   private final String field8;

   @Override
   public void run() {
      try {
         if (this.field4) {
            boolean flag6 = ClipboardUtils.method4(null, this.field1);
            String text8 = Ref.method4().method40().method32().method1(flag6 ? "copiedScreenshot" : "copyFailed", new Object[0]);
            Ref.method3()
               .bridge$submit(() -> Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(Bridge.method8().method13(text8)));
            return;
         }

         String text1 = new ScreenshotCallable(this.field1, this.field5, this.field2, this.field6, this.field7, this.field8).method1();
         if (this.field3) {
            String text2 = "Look what I am up to %F0%9F%91%80";
            text2 = text2.replace(" ", "%20");
            String text3 = "https://twitter.com/intent/tweet?url=" + URLEncoder.encode(text1, StandardCharsets.UTF_8) + "&via=LunarClient&text=" + text2;
            URI uri4 = new URI(text3);
            Ref.method3()
               .bridge$submit(() -> Ref.method3().bridge$displayScreen(Bridge.method8().method33(null, text3, uri4, false)));
            return;
         }

         BrowserUtils.method7(text1, Initiator.INITIATOR_SCREENSHOT_UPLOAD);
         Ref.method4().method69().method7(NotificationType.INFO, NotificationManager.method15("screenshotOpened", new Object[0]));
         Ref.method3().bridge$submit(() -> Ref.method3().bridge$displayScreen(null));
      } catch (Exception exception5) {
         if (this.field6 != null) {
            this.field6.accept(100);
         }

         exception5.printStackTrace();
      }
   }

   @Generated
   public ScreenshotShareThread(Path path1, @Nullable UUID uuid2, boolean flag3, boolean flag4, String text5, IntConsumer intconsumer6, String text7, String text8) {
      this.field1 = path1;
      this.field2 = uuid2;
      this.field3 = flag3;
      this.field4 = flag4;
      this.field5 = text5;
      this.field6 = intconsumer6;
      this.field7 = text7;
      this.field8 = text8;
   }
}

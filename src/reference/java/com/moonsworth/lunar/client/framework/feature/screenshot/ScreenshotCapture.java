package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.util.UUID;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class ScreenshotCapture {
   private IntBuffer field1;
   private int[] field2;

   public ScreenshotCapture() {
   }

   public static void method1(int[] items0, int index1, int number2) {
      int[] items3 = new int[index1];
      int number4 = number2 / 2;

      for (int index5 = 0; index5 < number4; index5++) {
         System.arraycopy(items0, index5 * index1, items3, 0, index1);
         System.arraycopy(items0, (number2 - 1 - index5) * index1, items0, index5 * index1, index1);
         System.arraycopy(items3, 0, items0, (number2 - 1 - index5) * index1, index1);
      }
   }

   public void method2(com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot1, File file2) {
      File file3 = new File(file2, "screenshots");
      if (!file3.exists()) {
         file3.mkdir();
      }

      this.method4(
         screenshot1,
         com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot.method7(file3),
         Ref.method3().bridge$displayWidth(),
         Ref.method3().bridge$displayHeight(),
         Ref.method3().bridge$getMainRenderTarget()
      );
   }

   public void method3(@Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot1, File file2, int number3, int number4, Bridge3_24 bridge3_245, boolean flag6) {
      this.method5(screenshot1, file2, number3, number4, bridge3_245, flag6, null, () -> {});
   }

   public void method4(@Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot1, File file2, int number3, int number4, Bridge3_24 bridge3_245) {
      this.method5(screenshot1, file2, number3, number4, bridge3_245, true, null, () -> {});
   }

   public void method5(
      @Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot1,
      File file2,
      int number3,
      int number4,
      Bridge3_24 bridge3_245,
      boolean flag6,
      Function<int[], BufferedImage> function7,
      Runnable runnable8
   ) {
      if (Bridge.method22().method1()) {
         number3 = bridge3_245.bridge$framebufferTextureWidth();
         number4 = bridge3_245.bridge$framebufferTextureHeight();
      }

      int number9 = number3;
      int number10 = number4;
      Screenshot screenshot11 = flag6 ? Screenshot.method1() : null;
      Bridge.method8().method91(bridge3_245.bridge$getColorTexture(false), 0, 0, number3, number4, TextureFormat.RGBA8, arg8x -> {
         int[] items9x = new int[arg8x.remaining() / 4];
         arg8x.asIntBuffer().get(items9x);
         new ScreenshotThread(screenshot1, items9x, number9, number10, bridge3_245, file2, screenshot11, function7, runnable8).start();
      });
   }

   public static void method6(com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot0, File file1, BufferedImage bufferedimage2, UUID uuid3, boolean flag4) {
      String text6 = file1.getName();
      String text5;
      if ((Boolean)screenshot0.method13().get() && ClipboardUtils.method4(bufferedimage2, file1.toPath())) {
         text5 = screenshot0.method1("savedAndCopiedScreenshot", new Object[0]);
      } else {
         text5 = screenshot0.method1("savedScreenshot", new Object[0]);
      }

      Builder builder7 = Component.text();
      builder7.append(Component.text(text5).decorate(TextDecoration.UNDERLINED));
      Builder builder8 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.GOLD))
            .content(" [" + screenshot0.method1("open", new Object[0]) + "]")
            .clickEvent(ClickEvent.openFile(file1.getCanonicalPath())))
         .hoverEvent(HoverEvent.showText(Component.text(text6)));
      builder7.append(builder8);
      if (ClipboardUtils.method3() && (Boolean)screenshot0.method16().get()) {
         Builder builder9 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.BLUE))
               .content(" [" + screenshot0.method1("copy", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(screenshot0.method19() + "_copy " + text6)))
            .hoverEvent(HoverEvent.showText(Component.text(screenshot0.method1("copyTheScreenshot", new Object[0]))));
         builder7.append(builder9);
      }

      if (flag4 && (Boolean)screenshot0.method14().get()) {
         Builder builder10 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.GREEN))
               .content(" [" + screenshot0.method1("upload", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(screenshot0.method19() + " " + text6 + " " + uuid3)))
            .hoverEvent(
               HoverEvent.showText(
                  Component.text(screenshot0.method1("uploadTo", new Object[0]))
                     .append(Component.text(screenshot0.method1("uploadToOpen", new Object[0]), NamedTextColor.GREEN))
               )
            );
         builder7.append(builder10);
      }

      if (flag4 && (Boolean)screenshot0.method15().get()) {
         Builder builder11 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.AQUA))
               .content(" [" + screenshot0.method1("tweet", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(screenshot0.method19() + "_tweet " + text6)))
            .hoverEvent(
               HoverEvent.showText(
                  Component.text(screenshot0.method1("uploadTo", new Object[0]))
                     .append(Component.text(screenshot0.method1("uploadToTweet", new Object[0]), NamedTextColor.AQUA))
               )
            );
         builder7.append(builder11);
      }

      if ((Boolean)screenshot0.method17().get()) {
         Builder builder12 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.RED))
               .content(" [" + screenshot0.method1("delete", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(screenshot0.method19() + "_delete " + text6)))
            .hoverEvent(HoverEvent.showText(Component.text(screenshot0.method1("deleteTheScreenshot", new Object[0]))));
         builder7.append(builder12);
      }

      Ref.method3()
         .bridge$submit(() -> Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(TextBridge.asBridge(builder7.build())));
   }
}

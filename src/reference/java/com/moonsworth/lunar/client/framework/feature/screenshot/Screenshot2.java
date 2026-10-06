package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
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

public class Screenshot2 {
   private IntBuffer field1;
   private int[] field2;

   public static void method1(int[] var0, int var1, int var2) {
      int[] var3 = new int[var1];
      int var4 = var2 / 2;

      for (int var5 = 0; var5 < var4; var5++) {
         System.arraycopy(var0, var5 * var1, var3, 0, var1);
         System.arraycopy(var0, (var2 - 1 - var5) * var1, var0, var5 * var1, var1);
         System.arraycopy(var3, 0, var0, (var2 - 1 - var5) * var1, var1);
      }
   }

   public void method2(com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot var1, File var2) {
      File var3 = new File(var2, "screenshots");
      if (!var3.exists()) {
         var3.mkdir();
      }

      this.method4(
         var1,
         com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot.method7(var3),
         ThreadModuleDump63.method3().bridge$displayWidth(),
         ThreadModuleDump63.method3().bridge$displayHeight(),
         ThreadModuleDump63.method3().bridge$getMainRenderTarget()
      );
   }

   public void method3(@Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot var1, File var2, int var3, int var4, Bridge3_24 var5, boolean var6) {
      this.method5(var1, var2, var3, var4, var5, var6, null, () -> {});
   }

   public void method4(@Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot var1, File var2, int var3, int var4, Bridge3_24 var5) {
      this.method5(var1, var2, var3, var4, var5, true, null, () -> {});
   }

   public void method5(
      @Nullable com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot var1,
      File var2,
      int var3,
      int var4,
      Bridge3_24 var5,
      boolean var6,
      Function<int[], BufferedImage> var7,
      Runnable var8
   ) {
      if (Bridge.method22().method1()) {
         var3 = var5.bridge$framebufferTextureWidth();
         var4 = var5.bridge$framebufferTextureHeight();
      }

      int var9 = var3;
      int var10 = var4;
      Screenshot var11 = var6 ? Screenshot.method1() : null;
      Bridge.method8().method91(var5.bridge$getColorTexture(false), 0, 0, var3, var4, TexturePixelFormat.RGBA8, var8x -> {
         int[] var9x = new int[var8x.remaining() / 4];
         var8x.asIntBuffer().get(var9x);
         new ScreenshotThread(var1, var9x, var9, var10, var5, var2, var11, var7, var8).start();
      });
   }

   public static void method6(com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot var0, File var1, BufferedImage var2, UUID var3, boolean var4) {
      String var6 = var1.getName();
      String var5;
      if ((Boolean)var0.method13().get() && ThreadModuleDump68.copyImageToClipboard(var2, var1.toPath())) {
         var5 = var0.method1("savedAndCopiedScreenshot", new Object[0]);
      } else {
         var5 = var0.method1("savedScreenshot", new Object[0]);
      }

      Builder var7 = Component.text();
      var7.append(Component.text(var5).decorate(TextDecoration.UNDERLINED));
      Builder var8 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.GOLD))
            .content(" [" + var0.method1("open", new Object[0]) + "]")
            .clickEvent(ClickEvent.openFile(var1.getCanonicalPath())))
         .hoverEvent(HoverEvent.showText(Component.text(var6)));
      var7.append(var8);
      if (ThreadModuleDump68.canCopyImages() && (Boolean)var0.method16().get()) {
         Builder var9 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.BLUE))
               .content(" [" + var0.method1("copy", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(var0.method19() + "_copy " + var6)))
            .hoverEvent(HoverEvent.showText(Component.text(var0.method1("copyTheScreenshot", new Object[0]))));
         var7.append(var9);
      }

      if (var4 && (Boolean)var0.method14().get()) {
         Builder var10 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.GREEN))
               .content(" [" + var0.method1("upload", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(var0.method19() + " " + var6 + " " + var3)))
            .hoverEvent(
               HoverEvent.showText(
                  Component.text(var0.method1("uploadTo", new Object[0]))
                     .append(Component.text(var0.method1("uploadToOpen", new Object[0]), NamedTextColor.GREEN))
               )
            );
         var7.append(var10);
      }

      if (var4 && (Boolean)var0.method15().get()) {
         Builder var11 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.AQUA))
               .content(" [" + var0.method1("tweet", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(var0.method19() + "_tweet " + var6)))
            .hoverEvent(
               HoverEvent.showText(
                  Component.text(var0.method1("uploadTo", new Object[0]))
                     .append(Component.text(var0.method1("uploadToTweet", new Object[0]), NamedTextColor.AQUA))
               )
            );
         var7.append(var11);
      }

      if ((Boolean)var0.method17().get()) {
         Builder var12 = (Builder)((Builder)((Builder)((Builder)Component.text().decorate(TextDecoration.BOLD)).color(NamedTextColor.RED))
               .content(" [" + var0.method1("delete", new Object[0]) + "]")
               .clickEvent(ClickEvent.runCommand(var0.method19() + "_delete " + var6)))
            .hoverEvent(HoverEvent.showText(Component.text(var0.method1("deleteTheScreenshot", new Object[0]))));
         var7.append(var12);
      }

      ThreadModuleDump63.method3()
         .bridge$submit(() -> ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$addMessage(AdventureTextBridge.asBridge(var7.build())));
   }
}

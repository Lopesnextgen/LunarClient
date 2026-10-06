package com.moonsworth.lunar.v1_7.mixin;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.Util;
import net.minecraft.util.Util.EnumOS;
import net.optifine.Config;
import optifine.Utils;
import optifine.Utils.OS;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Config.class)
public abstract class ConfigMixin {
   @Shadow
   public static GameSettings gameSettings;
   @Shadow
   public static Minecraft minecraft;
   @Shadow
   public static boolean fullscreenModeChecked;
   @Shadow
   public static boolean desktopModeChecked;

   @Overwrite
   public static void checkDisplayMode() {
      try {
         if (minecraft.isFullScreen()) {
            if (fullscreenModeChecked) {
               return;
            }

            fullscreenModeChecked = true;
            desktopModeChecked = false;
            DisplayMode var0 = Display.getDisplayMode();
            Dimension var1 = Config.getFullscreenDimension();
            if (var1 == null) {
               return;
            }

            if (var0.getWidth() == var1.width && var0.getHeight() == var1.height) {
               return;
            }

            DisplayMode var2 = Config.getDisplayMode(var1);
            if (var2 == null) {
               return;
            }

            Display.setDisplayMode(var2);
            minecraft.displayWidth = Display.getDisplayMode().getWidth();
            minecraft.displayHeight = Display.getDisplayMode().getHeight();
            if (minecraft.displayWidth <= 0) {
               minecraft.displayWidth = 1;
            }

            if (minecraft.displayHeight <= 0) {
               minecraft.displayHeight = 1;
            }

            if (minecraft.currentScreen != null) {
               ScaledResolution var3 = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
               int var4 = var3.getScaledWidth();
               int var5 = var3.getScaledHeight();
               minecraft.currentScreen.setWorldAndResolution(minecraft, var4, var5);
            }

            minecraft.loadingScreen = new LoadingScreenRenderer(minecraft);
            Config.updateFramebufferSize();
            Display.setFullscreen(true);
            minecraft.gameSettings.updateVSync();
            GL11.glEnable(3553);
         } else {
            if (desktopModeChecked) {
               return;
            }

            desktopModeChecked = true;
            fullscreenModeChecked = false;
            minecraft.gameSettings.updateVSync();
            Display.update();
            GL11.glEnable(3553);
            if (Utils.getPlatform() == OS.WINDOWS) {
               Display.setResizable(false);
               Display.setResizable(true);
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
         gameSettings.ofFullscreenMode = "Default";
         gameSettings.saveOfOptions();
      }
   }

   @Overwrite
   public static void checkDisplaySettings() {
      int var0 = Config.getAntialiasingLevel();
      if (var0 > 0) {
         DisplayMode var1 = Display.getDisplayMode();
         Config.dbg("FSAA Samples: " + var0);

         try {
            Display.destroy();
            Display.setDisplayMode(var1);
            Display.create(new PixelFormat().withDepthBits(24).withSamples(var0));
            if (Utils.getPlatform() != OS.MACOS) {
               Display.setResizable(false);
               Display.setResizable(true);
            }
         } catch (LWJGLException var9) {
            Config.warn("Error setting FSAA: " + var0 + "x");
            var9.printStackTrace();

            try {
               Display.setDisplayMode(var1);
               Display.create(new PixelFormat().withDepthBits(24));
               if (Utils.getPlatform() == OS.WINDOWS) {
                  Display.setResizable(false);
                  Display.setResizable(true);
               }
            } catch (LWJGLException var8) {
               var8.printStackTrace();

               try {
                  Display.setDisplayMode(var1);
                  Display.create();
                  if (Utils.getPlatform() == OS.WINDOWS) {
                     Display.setResizable(false);
                     Display.setResizable(true);
                  }
               } catch (LWJGLException var7) {
                  var7.printStackTrace();
               }
            }
         }

         if (Util.getOSType() != EnumOS.OSX) {
            try {
               File var2 = new File(minecraft.mcDataDir, "assets");
               ByteBuffer var3 = Config.readIconImage(new File(var2, "/icons/icon_16x16.png"));
               ByteBuffer var4 = Config.readIconImage(new File(var2, "/icons/icon_32x32.png"));
               ByteBuffer[] var5 = new ByteBuffer[]{var3, var4};
               Display.setIcon(var5);
            } catch (IOException var6) {
               Config.warn(var6.getClass().getName() + ": " + var6.getMessage());
            }
         }
      }
   }
}

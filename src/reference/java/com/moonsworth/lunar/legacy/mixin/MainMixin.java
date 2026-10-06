package com.moonsworth.lunar.legacy.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.client.config.DynamicFeatureFlag;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.io.ResourcePackUtils;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import com.moonsworth.lunar.legacy.Legacy2;
import com.moonsworth.lunar.legacy.wrapper.FolderResourcePackImpl;
import java.io.File;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Main.class)
public abstract class MainMixin {
   @Unique
   private static OptionSpec<String> lunar$installationId;
   @Unique
   private static OptionSpec<String> lunar$overwolfMuid;
   @Unique
   private static OptionSpec<String> lunar$launcherVersion;
   @Unique
   private static OptionSpec<String> lunar$texturesDir;
   @Unique
   private static OptionSpec<String> lunar$jitDir;
   @Unique
   private static OptionSpec<String> lunar$sentryTraceId;
   @Unique
   private static OptionSpec<String> lunar$modrinthModpackProjectId;
   @Unique
   private static OptionSpec<String> lunar$modrinthModpackVersionId;
   @Unique
   private static OptionSpec<String> lunar$curseforgeModpackModId;
   @Unique
   private static OptionSpec<String> lunar$curseforgeModpackFileId;
   @Unique
   private static OptionSpec<Integer> lunar$ipcPort;
   @Unique
   private static OptionSpec<String> lunar$uiDir;
   @Unique
   private static OptionSpec<String> lunar$webosrDir;
   @Unique
   private static OptionSpec<String> lunar$launchId;
   @Unique
   private static OptionSpec<String> lunar$canaryToken;
   @Unique
   private static OptionSpec<String> lunar$launcherFeatureFlags;

   public MainMixin() {
   }

   @Inject(method = "main([Ljava/lang/String;)V", at = @At("HEAD"))
   private static void lunar$main(String[] items0, CallbackInfo callback1) {
      System.setProperty("apple.awt.application.name", "Lunar Client");
      System.setProperty("jna.library.path", System.getProperty("java.library.path"));
      System.setProperty("gson.allowCapturingTypeVariables", "true");
      Bridge.method1(new Legacy2());
   }

   @Redirect(
      method = "main([Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Ljoptsimple/OptionParser;parse([Ljava/lang/String;)Ljoptsimple/OptionSet;")
   )
   private static OptionSet lunar$main$parse(OptionParser optionparser0, String... items1) {
      OptionSet optionset2 = optionparser0.parse(items1);
      LaunchOptions.field7 = (String)optionset2.valueOf(lunar$launcherVersion);
      LaunchOptions.field1 = (String)optionset2.valueOf(lunar$installationId);
      LaunchOptions.field2 = (String)optionset2.valueOf(lunar$overwolfMuid);
      LaunchOptions.field3 = (String)optionset2.valueOf(lunar$sentryTraceId);
      LaunchOptions.field8 = (String)optionset2.valueOf(lunar$modrinthModpackProjectId);
      LaunchOptions.field9 = (String)optionset2.valueOf(lunar$modrinthModpackVersionId);
      LaunchOptions.field10 = (String)optionset2.valueOf(lunar$curseforgeModpackModId);
      LaunchOptions.field11 = (String)optionset2.valueOf(lunar$curseforgeModpackFileId);
      LaunchOptions.field4 = (String)optionset2.valueOf(lunar$launchId);
      LaunchOptions.field5 = (String)optionset2.valueOf(lunar$canaryToken);
      String text3 = (String)optionset2.valueOf(lunar$launcherFeatureFlags);
      if (!text3.equals("not supplied")) {
         try {
            DynamicFeatureFlag.registerFromJson((JsonObject)new Gson().fromJson(text3, JsonObject.class));
         } catch (Exception exception10) {
            CrashReporter.method5(exception10, "Launcher Feature Flag Parse");
         }
      }

      String text4 = (String)optionset2.valueOf(lunar$texturesDir);
      if (text4 != null) {
         File file5 = new File(text4);
         System.out.println("[Bridge] Found textures dir: " + file5.getAbsolutePath());
         ResourcePackUtils.method4("lunar", file5.toPath());
         Bridge.method3((ResourcePackBridge)(new FolderResourcePackImpl("Lunar Cosmetics", "lunar", "Lunar Client cosmetics and assets", file5)));
         LaunchOptions.field17 = file5;
      }

      String text11 = (String)optionset2.valueOf(lunar$jitDir);
      if (text11 != null) {
         File file6 = new File(text11);
         System.out.println("[Bridge] Found JIT dir: " + file6.getAbsolutePath());
         ResourcePackUtils.method4("lunar-jit", file6.toPath());
         Bridge.method3((ResourcePackBridge)(new FolderResourcePackImpl("Lunar JIT Assets", "lunar-jit", "Lunar Client JIT assets", file6)));
         LaunchOptions.field18 = file6;
      }

      Integer number12 = (Integer)optionset2.valueOf(lunar$ipcPort);
      if (number12 != null) {
         System.out.println("[Bridge] Found IPC port: " + number12);
         LaunchOptions.field6 = number12;
      }

      String text7 = (String)optionset2.valueOf(lunar$uiDir);
      if (text7 != null) {
         File file8 = new File(text7);
         System.out.println("[Bridge] Found ui dir: " + file8.getAbsolutePath());
         LaunchOptions.field15 = file8;
      }

      String text13 = (String)optionset2.valueOf(lunar$webosrDir);
      if (text13 != null) {
         File file9 = new File(text13);
         System.out.println("[Bridge] Found webosr dir: " + file9.getAbsolutePath());
         LaunchOptions.field16 = file9;
         return optionset2;
      } else {
         throw new RuntimeException("webosrDir not found");
      }
   }

   @Inject(
      method = "main([Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Ljoptsimple/OptionParser;allowsUnrecognizedOptions()V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private static void lunar$main$(String[] items0, CallbackInfo callback1, OptionParser optionparser2) {
      lunar$launcherFeatureFlags = optionparser2.accepts("launcherFeatureFlags").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$launcherVersion = optionparser2.accepts("launcherVersion").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$installationId = optionparser2.accepts("installationId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$overwolfMuid = optionparser2.accepts("overwolfMuid").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$texturesDir = optionparser2.accepts("texturesDir").withRequiredArg();
      lunar$jitDir = optionparser2.accepts("jitDir").withRequiredArg();
      lunar$sentryTraceId = optionparser2.accepts("sentryTraceId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$modrinthModpackProjectId = optionparser2.accepts("modrinthModpackProjectId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$modrinthModpackVersionId = optionparser2.accepts("modrinthModpackVersionId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$curseforgeModpackModId = optionparser2.accepts("curseforgeModpackModId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$curseforgeModpackFileId = optionparser2.accepts("curseforgeModpackFileId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$ipcPort = optionparser2.accepts("ipcPort").withOptionalArg().ofType(Integer.class);
      lunar$uiDir = optionparser2.accepts("uiDir").withRequiredArg();
      lunar$webosrDir = optionparser2.accepts("webosrDir").withRequiredArg();
      lunar$launchId = optionparser2.accepts("launchId").withRequiredArg().defaultsTo("not supplied", new String[0]);
      lunar$canaryToken = optionparser2.accepts("canaryToken").withRequiredArg().defaultsTo("", new String[0]);
   }
}

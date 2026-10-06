package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.ichor.util.IchorService;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import java.nio.ByteBuffer;

@IchorService
public interface OpenALNative extends Library {
   OpenALNative INSTANCE = (OpenALNative)Native.load(getLibName(), OpenALNative.class);

   private static String getLibName() {
      if (Platform.isWindows()) {
         return Platform.is64Bit() ? "OpenAL64" : "OpenAL32";
      } else if (Platform.isLinux() && Platform.is64Bit() && Bridge.getMinecraftVersion().method21()) {
         return "openal64";
      } else {
         return Platform.isMac() && Bridge.getMinecraftVersion().method21() ? "openalsoft" : "openal";
      }
   }

   long alcLoopbackOpenDeviceSOFT(String text1);

   boolean alcIsRenderFormatSupportedSOFT(long number1, int number3, int number4, int number5);

   void alcRenderSamplesSOFT(long number1, ByteBuffer buffer3, int number4);

   Pointer alcGetString(long number1, int number3);

   boolean alcIsExtensionPresent(long number1, String text3);
}

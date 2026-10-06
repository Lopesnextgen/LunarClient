package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge5_3;
import com.moonsworth.lunar.legacy.wrapper.util.OpenALNative;
import com.sun.jna.Pointer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;

public class Bridge5Handler implements Bridge5_3 {
   private final IntBuffer field1 = BufferUtils.createIntBuffer(1);

   private long method1(Object var1) {
      if (var1 == null) {
         return 0L;
      }

      try {
         Field var2 = ALCdevice.class.getDeclaredField("device");
         var2.setAccessible(true);
         return var2.getLong(var1);
      } catch (Exception var3) {
         return 0L;
      }
   }

   public Object method1(String var1, int var2, int var3, int var4) {
      return ALC11.alcCaptureOpenDevice(var1, var2, var3, var4);
   }

   public void method2(Object var1) {
      ALC11.alcCaptureStart((ALCdevice)var1);
   }

   public int method3(Object var1, int var2) {
      if (var1 instanceof Long var3 && var3 == 0L) {
         var1 = null;
      }

      ALC10.alcGetInteger((ALCdevice)var1, var2, this.field1);
      return this.field1.get(0);
   }

   public String method4(Object var1, int var2) {
      if (var1 instanceof Long var3 && var3 == 0L) {
         var1 = null;
      }

      Pointer var7 = OpenALNative.INSTANCE.alcGetString(this.method1(var1), var2);
      if (var7 == null) {
         return null;
      }

      ArrayList var4 = new ArrayList();
      int var5 = 0;

      while (true) {
         String var6 = var7.getString(var5, "UTF-8");
         if (var6 == null || var6.isEmpty()) {
            return String.join("\u0000", var4);
         }

         var4.add(var6);
         var5 += var6.getBytes(StandardCharsets.UTF_8).length + 1;
      }
   }

   public List<String> method5(Object var1, int var2) {
      ArrayList var3 = new ArrayList();
      String var4 = this.method4(var1, var2);
      if (var4 != null && !var4.isEmpty()) {
         int var5 = 0;

         while (true) {
            int var6 = var4.indexOf(0, var5);
            if (var6 == -1 || var6 == var5) {
               var3.add(var4.substring(var5));
               return var3;
            }

            var3.add(var4.substring(var5, var6));
            var5 = var6 + 1;
         }
      } else {
         return var3;
      }
   }

   public void method6(Object var1, ByteBuffer var2, int var3) {
      ALC11.alcCaptureSamples((ALCdevice)var1, var2, var3);
   }

   public void method7(Object var1) {
      ALC11.alcCaptureStop((ALCdevice)var1);
   }

   public void method8(Object var1) {
      ALC11.alcCaptureCloseDevice((ALCdevice)var1);
   }

   public Object method9(Object var1, int[] var2) {
      IntBuffer var3 = BufferUtils.createIntBuffer(var2.length);
      var3.put(var2);
      var3.flip();
      return ALC10.alcCreateContext((ALCdevice)var1, var3);
   }

   public void method10(Object var1) {
      ALC10.alcDestroyContext((ALCcontext)var1);
   }

   public Object method11(String var1) {
      try {
         Constructor var2 = ALCdevice.class.getDeclaredConstructor(long.class);
         var2.setAccessible(true);
         long var3 = OpenALNative.INSTANCE.alcLoopbackOpenDeviceSOFT(var1);
         return var3 == 0L ? null : var2.newInstance(var3);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public boolean method12(Object var1, int var2, int var3, int var4) {
      return OpenALNative.INSTANCE.alcIsRenderFormatSupportedSOFT(this.method1(var1), var2, var3, var4);
   }

   public void method13(Object var1, ByteBuffer var2, int var3) {
      OpenALNative.INSTANCE.alcRenderSamplesSOFT(this.method1(var1), var2, var3);
   }

   public boolean method14(Object var1, String var2) {
      if (var1 instanceof Long var3 && var3 == 0L) {
         var1 = null;
      }

      return OpenALNative.INSTANCE.alcIsExtensionPresent(this.method1(var1), var2);
   }
}

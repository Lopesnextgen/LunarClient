package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AlcBridge;
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

public class AlcBridgeImpl implements AlcBridge {
   private final IntBuffer field1 = BufferUtils.createIntBuffer(1);

   public AlcBridgeImpl() {
   }

   private long method1(Object obj1) {
      if (obj1 == null) {
         return 0L;
      }

      try {
         Field field2 = ALCdevice.class.getDeclaredField("device");
         field2.setAccessible(true);
         return field2.getLong(obj1);
      } catch (Exception exception3) {
         return 0L;
      }
   }

   public Object method1(String text1, int number2, int number3, int number4) {
      return ALC11.alcCaptureOpenDevice(text1, number2, number3, number4);
   }

   public void method2(Object obj1) {
      ALC11.alcCaptureStart((ALCdevice)obj1);
   }

   public int method3(Object obj1, int number2) {
      if (obj1 instanceof Long number3 && number3 == 0L) {
         obj1 = null;
      }

      ALC10.alcGetInteger((ALCdevice)obj1, number2, this.field1);
      return this.field1.get(0);
   }

   public String method4(Object obj1, int number2) {
      if (obj1 instanceof Long number3 && number3 == 0L) {
         obj1 = null;
      }

      Pointer pointer7 = OpenALNative.INSTANCE.alcGetString(this.method1(obj1), number2);
      if (pointer7 == null) {
         return null;
      }

      ArrayList list4 = new ArrayList();
      int number5 = 0;

      while (true) {
         String text6 = pointer7.getString(number5, "UTF-8");
         if (text6 == null || text6.isEmpty()) {
            return String.join("\u0000", list4);
         }

         list4.add(text6);
         number5 += text6.getBytes(StandardCharsets.UTF_8).length + 1;
      }
   }

   public List<String> method5(Object obj1, int number2) {
      ArrayList list3 = new ArrayList();
      String text4 = this.method4(obj1, number2);
      if (text4 != null && !text4.isEmpty()) {
         int index5 = 0;

         while (true) {
            int index6 = text4.indexOf(0, index5);
            if (index6 == -1 || index6 == index5) {
               list3.add(text4.substring(index5));
               return list3;
            }

            list3.add(text4.substring(index5, index6));
            index5 = index6 + 1;
         }
      } else {
         return list3;
      }
   }

   public void method6(Object obj1, ByteBuffer buffer2, int number3) {
      ALC11.alcCaptureSamples((ALCdevice)obj1, buffer2, number3);
   }

   public void method7(Object obj1) {
      ALC11.alcCaptureStop((ALCdevice)obj1);
   }

   public void method8(Object obj1) {
      ALC11.alcCaptureCloseDevice((ALCdevice)obj1);
   }

   public Object method9(Object obj1, int[] items2) {
      IntBuffer intbuffer3 = BufferUtils.createIntBuffer(items2.length);
      intbuffer3.put(items2);
      intbuffer3.flip();
      return ALC10.alcCreateContext((ALCdevice)obj1, intbuffer3);
   }

   public void method10(Object obj1) {
      ALC10.alcDestroyContext((ALCcontext)obj1);
   }

   public Object method11(String text1) {
      try {
         Constructor constructor2 = ALCdevice.class.getDeclaredConstructor(long.class);
         constructor2.setAccessible(true);
         long number3 = OpenALNative.INSTANCE.alcLoopbackOpenDeviceSOFT(text1);
         return number3 == 0L ? null : constructor2.newInstance(number3);
      } catch (Exception exception5) {
         throw new RuntimeException(exception5);
      }
   }

   public boolean method12(Object obj1, int number2, int number3, int number4) {
      return OpenALNative.INSTANCE.alcIsRenderFormatSupportedSOFT(this.method1(obj1), number2, number3, number4);
   }

   public void method13(Object obj1, ByteBuffer buffer2, int number3) {
      OpenALNative.INSTANCE.alcRenderSamplesSOFT(this.method1(obj1), buffer2, number3);
   }

   public boolean method14(Object obj1, String text2) {
      if (obj1 instanceof Long number3 && number3 == 0L) {
         obj1 = null;
      }

      return OpenALNative.INSTANCE.alcIsExtensionPresent(this.method1(obj1), text2);
   }
}

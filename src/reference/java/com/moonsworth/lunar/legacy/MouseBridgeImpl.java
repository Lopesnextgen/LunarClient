package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.bridge.MouseHelperBridge;
import java.awt.image.BufferedImage;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.IntBuffer;
import java.util.function.Supplier;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MouseBridgeImpl implements MouseHelperBridge {
   private static final MethodHandle field1;
   private static final MethodHandle field2;
   private static final MethodHandle field3;

   public MouseBridgeImpl() {
   }

   public boolean method1(int number1) {
      return Mouse.isButtonDown(number1);
   }

   public int getX() {
      return Mouse.getX();
   }

   public int getY() {
      return Mouse.getY();
   }

   public static int method2() {
      byte number0 = 0;
      if (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)) {
         number0 |= 1;
      }

      if (Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) {
         number0 |= 2;
      }

      if (Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184)) {
         number0 |= 4;
      }

      return number0;
   }

   public void method2(int number1, int number2, int number3) {
      try {
         field1.invokeExact((int)number1);
         field2.invokeExact((int)number2);
         field3.invokeExact((int)number3);
      } catch (Throwable exception5) {
         throw new RuntimeException(exception5);
      }
   }

   public Cursor method4(int number1, Supplier<BufferedImage> supplier2, int number3, int number4) {
      try {
         BufferedImage bufferedimage5 = (BufferedImage)supplier2.get();
         if (bufferedimage5 == null) {
            return null;
         }

         int number6 = Cursor.getMaxCursorSize();
         if (number6 > 64) {
            number6 = 64;
         }

         IntBuffer intbuffer7 = BufferUtils.createIntBuffer(number6 * number6);

         for (int index8 = 0; index8 < number6 * number6; index8++) {
            int number9 = index8 % number6;
            int number10 = index8 / number6;
            if (number9 < bufferedimage5.getWidth() && number10 < bufferedimage5.getHeight()) {
               intbuffer7.put(bufferedimage5.getRGB(number9, bufferedimage5.getHeight() - 1 - number10));
            } else {
               intbuffer7.put(0);
            }
         }

         intbuffer7.flip();
         return new Cursor(number6, number6, number3, number4, 1, intbuffer7, null);
      } catch (LWJGLException lwjglexception11) {
         lwjglexception11.printStackTrace();
         return null;
      }
   }

   public void method4(Object obj1) {
      try {
         Mouse.setNativeCursor((Cursor)obj1);
      } catch (LWJGLException lwjglexception3) {
         lwjglexception3.printStackTrace();
      }
   }

   public void method5() {
      try {
         Mouse.setNativeCursor(null);
      } catch (LWJGLException lwjglexception2) {
         lwjglexception2.printStackTrace();
      }
   }

   static {
      try {
         Lookup lookup0 = MethodHandles.privateLookupIn(Mouse.class, MethodHandles.lookup());
         field1 = lookup0.findStaticSetter(Mouse.class, "event_dx", int.class);
         field2 = lookup0.findStaticSetter(Mouse.class, "event_dy", int.class);
         field3 = lookup0.findStaticSetter(Mouse.class, "event_dwheel", int.class);
      } catch (NoSuchFieldException | IllegalAccessException nosuchfieldexception1) {
         throw new ExceptionInInitializerError(nosuchfieldexception1);
      }
   }
}

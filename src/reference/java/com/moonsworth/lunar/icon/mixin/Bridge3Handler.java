package com.moonsworth.lunar.icon.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_27;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.function.Supplier;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;

public class Bridge3Handler implements Bridge3_27 {
   private static final DoubleBuffer field1 = BufferUtils.createDoubleBuffer(1);
   private long field2 = -1L;

   @Override
   public boolean method1(int var1) {
      int var2 = GLFW.glfwGetMouseButton(Bridge.method9().bridge$getWindowId(), var1);
      return var2 == 1;
   }

   @Override
   public int getX() {
      GLFW.glfwGetCursorPos(Bridge.method9().bridge$getWindowId(), field1, null);
      return (int)field1.get(0);
   }

   @Override
   public int getY() {
      GLFW.glfwGetCursorPos(Bridge.method9().bridge$getWindowId(), null, field1);
      return (int)field1.get(0);
   }

   public Long method2(int var1, Supplier<BufferedImage> var2, int var3, int var4) {
      return switch (var1) {
         case 0 -> GLFW.glfwCreateStandardCursor(212994);
         case 1 -> GLFW.glfwCreateStandardCursor(221187);
         case 2 -> GLFW.glfwCreateStandardCursor(221186);
         case 3 -> GLFW.glfwCreateStandardCursor(221188);
         case 4 -> GLFW.glfwCreateStandardCursor(221189);
         case 5 -> GLFW.glfwCreateStandardCursor(221190);
         default -> {
            BufferedImage var5 = (BufferedImage)var2.get();
            if (var5 == null) {
               yield null;
            } else {
               ByteBuffer var6 = BufferUtils.createByteBuffer(4 * var5.getWidth() * var5.getHeight());

               for (int var7 = 0; var7 < var5.getWidth(); var7++) {
                  for (int var8 = 0; var8 < var5.getHeight(); var8++) {
                     var6.putInt(var5.getRGB(var7, var5.getHeight() - 1 - var8));
                  }
               }

               var6.flip();
               GLFWImage var9 = GLFWImage.create().width(var5.getWidth()).height(var5.getHeight()).pixels(var6);
               yield GLFW.glfwCreateCursor(var9, var3, var4);
            }
         }
      };
   }

   @Override
   public void method4(Object var1) {
      GLFW.glfwSetCursor(Bridge.method9().bridge$getWindowId(), (Long)var1);
   }

   @Override
   public void method5() {
      if (this.field2 == -1L) {
         this.field2 = GLFW.glfwCreateStandardCursor(221185);
      }

      GLFW.glfwSetCursor(Bridge.method9().bridge$getWindowId(), this.field2);
   }
}

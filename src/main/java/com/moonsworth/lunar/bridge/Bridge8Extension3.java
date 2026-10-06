package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import org.jetbrains.annotations.Nullable;

public interface Bridge8Extension3 extends Bridge8Extension {
   default void bridge$allocate(boolean flag1, boolean flag2, int number3, int number4, TextureFormat bridgetype_115, boolean flag, boolean flag3) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$upload(int number1, int number2, int number3, int number4, int[] items5) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$uploadBgra(int number1, int number2, int number3, int number4, ByteBuffer buffer5, int value, int value2, int value3) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$setFilter(boolean flag1, boolean flag2) {
      throw new AbstractMethodErrorImpl();
   }

   default void method1() {
   }

   @Nullable
   default TextureBridge method2() {
      if (this instanceof TextureBridge bridge3_43) {
         return bridge3_43;
      } else {
         return this instanceof Bridge8Extension34 bridge8extension341 ? bridge8extension341.method3() : null;
      }
   }
}

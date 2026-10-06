package com.moonsworth.lunar.ichor;

import java.io.OutputStream;
import java.io.PrintStream;

class MixinErrorStdoutStream extends PrintStream {
   MixinErrorStdoutStream(OutputStream output1) {
      super(output1);
   }

   @Override
   public void print(String text1) {
      if (MixinSupport.method5(text1)) {
         MixinSupport.field4 = true;
      }

      super.print(text1);
   }
}

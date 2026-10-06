package com.moonsworth.lunar.ichor;

import java.io.OutputStream;
import java.io.PrintStream;
import org.jetbrains.annotations.NotNull;

class MixinErrorStderrStream extends PrintStream {
   MixinErrorStderrStream(OutputStream output1) {
      super(output1);
   }

   @Override
   public PrintStream printf(@NotNull String text1, Object... items2) {
      if (items2.length == 1 && items2[0] instanceof String text3 && text3.contains("Total unimplemented:")) {
         MixinSupport.field4 = true;
      }

      return super.printf(text1, items2);
   }
}

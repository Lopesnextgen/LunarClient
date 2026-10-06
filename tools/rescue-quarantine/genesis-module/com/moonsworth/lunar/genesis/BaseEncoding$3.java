package com.moonsworth.lunar.genesis;

import java.io.Reader;

final class BaseEncoding$3 extends Reader {
   BaseEncoding$3(Reader reader1, String text2) {
      this.field1 = reader1;
      this.field2 = text2;
   }

   @Override
   public int read() {
      int index1;
      do {
         index1 = this.field1.read();
      } while (index1 != -1 && this.field2.indexOf((char)index1) >= 0);

      return index1;
   }

   @Override
   public int read(char[] items1, int number2, int number3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void close() {
      this.field1.close();
   }
}

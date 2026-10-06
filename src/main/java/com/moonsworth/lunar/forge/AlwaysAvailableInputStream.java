package com.moonsworth.lunar.forge;

import java.io.FilterInputStream;
import java.io.InputStream;

class AlwaysAvailableInputStream extends FilterInputStream {
   public AlwaysAvailableInputStream(InputStream stream) {
      super(stream);
   }

   @Override
   public boolean markSupported() {
      return false;
   }

   @Override
   public int available() {
      return Integer.MAX_VALUE;
   }
}

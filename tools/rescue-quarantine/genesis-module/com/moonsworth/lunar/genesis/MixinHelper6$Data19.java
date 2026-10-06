package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.net.URL;
import com.google.common.io.ByteSource;
import com.google.common.base.Preconditions;

final class MixinHelper6$Data19 extends ByteSource {
   private final URL field1;

   private MixinHelper6$Data19(URL var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public InputStream openStream() {
      return this.field1.openStream();
   }

   @Override
   public String toString() {
      return "Resources.asByteSource(" + this.field1 + ")";
   }
}

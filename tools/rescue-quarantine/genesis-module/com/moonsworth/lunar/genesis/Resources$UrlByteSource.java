package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.net.URL;
import com.google.common.io.ByteSource;
import com.google.common.base.Preconditions;

final class Resources$UrlByteSource extends ByteSource {
   private final URL field1;

   private Resources$UrlByteSource(URL url1) {
      this.field1 = (URL)Preconditions.checkNotNull(url1);
   }

   public InputStream openStream() {
      return this.field1.openStream();
   }

   public String toString() {
      return "Resources.asByteSource(" + this.field1 + ")";
   }
}

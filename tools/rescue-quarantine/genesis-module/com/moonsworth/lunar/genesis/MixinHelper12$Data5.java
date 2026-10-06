package com.moonsworth.lunar.genesis;

import java.nio.charset.Charset;
import com.google.common.io.CharSource;
import com.google.common.base.Preconditions;

final class MixinHelper12$Data5 extends MixinHelper12$Data2 {
   static final MixinHelper12$Data5 field4 = new MixinHelper12$Data5();

   MixinHelper12$Data5() {
      super(new byte[0]);
   }

   @Override
   public CharSource method1(Charset var1) {
      Preconditions.checkNotNull(var1);
      return CharSource.method10();
   }

   @Override
   public byte[] read() {
      return this.field1;
   }

   @Override
   public String toString() {
      return "ByteSource.empty()";
   }
}

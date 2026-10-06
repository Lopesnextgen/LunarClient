package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@Annotation2
public final class FilterInputStreamLoader_2 extends FilterInputStream {
   private final MixinHelper42_2 field1;

   public FilterInputStreamLoader_2(MixinHelper5_8 var1, InputStream var2) {
      super(Preconditions.checkNotNull(var2));
      this.field1 = Preconditions.checkNotNull(var1.method1());
   }

   @CanIgnoreReturnValue
   @Override
   public int read() {
      int var1 = this.in.read();
      if (var1 != -1) {
         this.field1.method2((byte)var1);
      }

      return var1;
   }

   @CanIgnoreReturnValue
   @Override
   public int read(byte[] var1, int var2, int var3) {
      int var4 = this.in.read(var1, var2, var3);
      if (var4 != -1) {
         this.field1.method4(var1, var2, var4);
      }

      return var4;
   }

   @Override
   public boolean markSupported() {
      return false;
   }

   @Override
   public void mark(int var1) {
   }

   @Override
   public void reset() {
      throw new IOException("reset not supported");
   }

   public HashCode method1() {
      return this.field1.method15();
   }
}

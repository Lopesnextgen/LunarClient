package com.moonsworth.lunar.genesis;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

@Annotation2
public final class FilterOutputStreamSerializer extends FilterOutputStream {
   private final MixinHelper42_2 field1;

   public FilterOutputStreamSerializer(MixinHelper5_8 var1, OutputStream var2) {
      super(Preconditions.checkNotNull(var2));
      this.field1 = Preconditions.checkNotNull(var1.method1());
   }

   @Override
   public void write(int var1) {
      this.field1.method2((byte)var1);
      this.out.write(var1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this.field1.method4(var1, var2, var3);
      this.out.write(var1, var2, var3);
   }

   public HashCode method1() {
      return this.field1.method15();
   }

   @Override
   public void close() {
      this.out.close();
   }
}

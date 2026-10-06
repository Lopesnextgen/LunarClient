package com.moonsworth.lunar.genesis;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import com.google.common.base.Preconditions;

@Annotation2
@Annotation3
public final class FilterOutputStreamSerializer_2 extends FilterOutputStream {
   private long count;

   public FilterOutputStreamSerializer_2(OutputStream var1) {
      super(Preconditions.checkNotNull(var1));
   }

   public long getCount() {
      return this.count;
   }

   @Override
   public void write(byte[] var1, int var2, int var3) {
      this.out.write(var1, var2, var3);
      this.count += var3;
   }

   @Override
   public void write(int var1) {
      this.out.write(var1);
      this.count++;
   }

   @Override
   public void close() {
      this.out.close();
   }
}

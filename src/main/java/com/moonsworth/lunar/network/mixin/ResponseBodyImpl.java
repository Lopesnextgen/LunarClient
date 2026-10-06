package com.moonsworth.lunar.network.mixin;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ResponseBodyImpl extends ResponseBody {
   private final ResponseBody field1;
   private final MixinHelper3 field2;
   private BufferedSource field3;

   public ResponseBodyImpl(ResponseBody var1, MixinHelper3 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public MediaType contentType() {
      return this.field1.contentType();
   }

   public long contentLength() {
      return this.field1.contentLength();
   }

   public BufferedSource source() {
      if (this.field3 == null) {
         this.field3 = Okio.buffer(this.method1(this.field1.source()));
      }

      return this.field3;
   }

   private Source method1(Source var1) {
      return new ForwardingSource(var1) {
         long field1 = 0L;

         public long read(Buffer var1, long var2) {
            long var4 = super.read(var1, var2);
            this.field1 += var4 != -1L ? var4 : 0L;
            ResponseBodyImpl.this.field2.method4(this.field1, ResponseBodyImpl.this.field1.contentLength(), var4 == -1L);
            return var4;
         }
      };
   }
}

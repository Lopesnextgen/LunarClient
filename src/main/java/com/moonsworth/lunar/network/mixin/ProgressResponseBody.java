package com.moonsworth.lunar.network.mixin;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
   private final ResponseBody field1;
   private final ApiCallback field2;
   private BufferedSource field3;

   public ProgressResponseBody(ResponseBody responsebody1, ApiCallback mixinhelper32) {
      this.field1 = responsebody1;
      this.field2 = mixinhelper32;
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

   private Source method1(Source source1) {
      return new ForwardingSource(source1) {
         long field1 = 0L;

         public long read(Buffer buffer1, long index2) {
            long number4 = super.read(buffer1, index2);
            this.field1 += number4 != -1L ? number4 : 0L;
            ProgressResponseBody.this.field2.method4(this.field1, ProgressResponseBody.this.field1.contentLength(), number4 == -1L);
            return number4;
         }
      };
   }
}

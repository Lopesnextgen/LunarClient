package com.moonsworth.lunar.network.mixin;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {
   private final RequestBody field1;
   private final ApiCallback field2;

   public ProgressRequestBody(RequestBody requestbody1, ApiCallback mixinhelper32) {
      this.field1 = requestbody1;
      this.field2 = mixinhelper32;
   }

   public MediaType contentType() {
      return this.field1.contentType();
   }

   public long contentLength() {
      return this.field1.contentLength();
   }

   public void writeTo(BufferedSink bufferedsink1) {
      BufferedSink bufferedsink2 = Okio.buffer(this.method1(bufferedsink1));
      this.field1.writeTo(bufferedsink2);
      bufferedsink2.flush();
   }

   private Sink method1(Sink sink1) {
      return new ForwardingSink(sink1) {
         long bytesWritten = 0L;
         long contentLength = 0L;

         public void write(Buffer buffer1, long value) {
            super.write(buffer1, value);
            if (this.contentLength == 0L) {
               this.contentLength = ProgressRequestBody.this.contentLength();
            }

            this.bytesWritten += value;
            ProgressRequestBody.this.field2.method3(this.bytesWritten, this.contentLength, this.bytesWritten == this.contentLength);
         }
      };
   }
}

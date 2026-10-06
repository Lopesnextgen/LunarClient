package com.moonsworth.lunar.network.mixin;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class RequestBodyImpl extends RequestBody {
   private final RequestBody field1;
   private final MixinHelper3 field2;

   public RequestBodyImpl(RequestBody var1, MixinHelper3 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public MediaType contentType() {
      return this.field1.contentType();
   }

   public long contentLength() {
      return this.field1.contentLength();
   }

   public void writeTo(BufferedSink var1) {
      BufferedSink var2 = Okio.buffer(this.method1(var1));
      this.field1.writeTo(var2);
      var2.flush();
   }

   private Sink method1(Sink var1) {
      return new ForwardingSink(var1) {
         long bytesWritten = 0L;
         long contentLength = 0L;

         public void write(Buffer var1, long var2) {
            super.write(var1, var2);
            if (this.contentLength == 0L) {
               this.contentLength = RequestBodyImpl.this.contentLength();
            }

            this.bytesWritten += var2;
            RequestBodyImpl.this.field2.method3(this.bytesWritten, this.contentLength, this.bytesWritten == this.contentLength);
         }
      };
   }
}

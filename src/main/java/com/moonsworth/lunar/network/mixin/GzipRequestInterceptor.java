package com.moonsworth.lunar.network.mixin;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Interceptor.Chain;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

class GzipRequestInterceptor implements Interceptor {
   GzipRequestInterceptor() {
   }

   public Response intercept(Chain chain1) {
      Request request2 = chain1.request();
      if (request2.body() != null && request2.header("Content-Encoding") == null) {
         Request request3 = request2.newBuilder().header("Content-Encoding", "gzip").method(request2.method(), this.method1(this.method2(request2.body()))).build();
         return chain1.proceed(request3);
      } else {
         return chain1.proceed(request2);
      }
   }

   private RequestBody method1(final RequestBody requestbody1) {
      final Buffer buffer2 = new Buffer();
      requestbody1.writeTo(buffer2);
      return new RequestBody() {
         public MediaType contentType() {
            return requestbody1.contentType();
         }

         public long contentLength() {
            return buffer2.size();
         }

         public void writeTo(BufferedSink bufferedsink1x) {
            bufferedsink1x.write(buffer2.snapshot());
         }
      };
   }

   private RequestBody method2(final RequestBody requestbody1) {
      return new RequestBody() {
         public MediaType contentType() {
            return requestbody1.contentType();
         }

         public long contentLength() {
            return -1L;
         }

         public void writeTo(BufferedSink bufferedsink1x) {
            BufferedSink bufferedsink2 = Okio.buffer(new GzipSink(bufferedsink1x));
            requestbody1.writeTo(bufferedsink2);
            bufferedsink2.close();
         }
      };
   }
}

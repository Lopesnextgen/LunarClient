package com.moonsworth.webosr.handler;

import java.nio.ByteBuffer;

public interface FileSystem {
   FileSystem.Payload onRequest(String var1);

   boolean doesResourceExist(String var1);

   class Payload {
      private final ByteBuffer buffer;
      private final int length;
      private final String mimeType;
      private final int statusCode;

      public Payload(ByteBuffer var1, int value, String text, int value2) {
         this.buffer = var1;
         this.length = value;
         this.mimeType = text;
         this.statusCode = value2;
      }
   }
}

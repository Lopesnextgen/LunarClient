package com.moonsworth.webosr.handler;

import java.nio.ByteBuffer;

public class FileSystem$Payload {
   private final ByteBuffer buffer;
   private final int length;
   private final String mimeType;
   private final int statusCode;

   public FileSystem$Payload(ByteBuffer buffer1, int number2, String text3, int number4) {
      this.buffer = buffer1;
      this.length = number2;
      this.mimeType = text3;
      this.statusCode = number4;
   }
}

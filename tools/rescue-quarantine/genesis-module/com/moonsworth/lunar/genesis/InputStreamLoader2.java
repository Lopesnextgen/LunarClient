package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.base.Preconditions;

@Annotation3
final class InputStreamLoader2 extends InputStream {
   private final java.io.Reader field1;
   private final CharsetEncoder field2;
   private final byte[] field3 = new byte[1];
   private CharBuffer charBuffer;
   private ByteBuffer byteBuffer;
   private boolean endOfInput;
   private boolean draining;
   private boolean doneFlushing;

   InputStreamLoader2(java.io.Reader var1, Charset var2, int var3) {
      this(var1, var2.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), var3);
   }

   InputStreamLoader2(java.io.Reader var1, CharsetEncoder var2, int var3) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
      Preconditions.checkArgument(var3 > 0, "bufferSize must be positive: %s", var3);
      var2.reset();
      this.charBuffer = CharBuffer.allocate(var3);
      ((Buffer)this.charBuffer).flip();
      this.byteBuffer = ByteBuffer.allocate(var3);
   }

   @Override
   public void close() {
      this.field1.close();
   }

   @Override
   public int read() {
      return this.read(this.field3) == 1 ? UnsignedBytes.toInt(this.field3[0]) : -1;
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      if (var3 == 0) {
         return 0;
      }

      int var4 = 0;
      boolean var5 = this.endOfInput;

      while (true) {
         if (this.draining) {
            var4 += this.drain(var1, var2 + var4, var3 - var4);
            if (var4 == var3 || this.doneFlushing) {
               return var4 > 0 ? var4 : -1;
            }

            this.draining = false;
            ((Buffer)this.byteBuffer).clear();
         }

         while (true) {
            CoderResult var6;
            if (this.doneFlushing) {
               var6 = CoderResult.UNDERFLOW;
            } else if (var5) {
               var6 = this.field2.flush(this.byteBuffer);
            } else {
               var6 = this.field2.encode(this.charBuffer, this.byteBuffer, this.endOfInput);
            }

            if (var6.isOverflow()) {
               this.startDraining(true);
               break;
            }

            if (var6.isUnderflow()) {
               if (var5) {
                  this.doneFlushing = true;
                  this.startDraining(false);
                  break;
               }

               if (this.endOfInput) {
                  var5 = true;
               } else {
                  this.readMoreChars();
               }
            } else if (var6.isError()) {
               var6.throwException();
               return 0;
            }
         }
      }
   }

   private static CharBuffer grow(CharBuffer var0) {
      char[] var1 = Arrays.copyOf(var0.array(), var0.capacity() * 2);
      CharBuffer var2 = CharBuffer.wrap(var1);
      ((Buffer)var2).position(var0.position());
      ((Buffer)var2).limit(var0.limit());
      return var2;
   }

   private void readMoreChars() {
      if (availableCapacity(this.charBuffer) == 0) {
         if (this.charBuffer.position() > 0) {
            ((Buffer)this.charBuffer.compact()).flip();
         } else {
            this.charBuffer = grow(this.charBuffer);
         }
      }

      int var1 = this.charBuffer.limit();
      int var2 = this.field1.read(this.charBuffer.array(), var1, availableCapacity(this.charBuffer));
      if (var2 == -1) {
         this.endOfInput = true;
      } else {
         ((Buffer)this.charBuffer).limit(var1 + var2);
      }
   }

   private static int availableCapacity(Buffer var0) {
      return var0.capacity() - var0.limit();
   }

   private void startDraining(boolean var1) {
      ((Buffer)this.byteBuffer).flip();
      if (var1 && this.byteBuffer.remaining() == 0) {
         this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
      } else {
         this.draining = true;
      }
   }

   private int drain(byte[] var1, int var2, int var3) {
      int var4 = Math.min(var3, this.byteBuffer.remaining());
      this.byteBuffer.get(var1, var2, var4);
      return var4;
   }
}

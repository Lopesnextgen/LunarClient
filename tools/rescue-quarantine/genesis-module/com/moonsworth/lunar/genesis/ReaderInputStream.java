package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.base.Preconditions;

@GwtIncompatible
final class ReaderInputStream extends InputStream {
   private final Reader field1;
   private final CharsetEncoder field2;
   private final byte[] field3 = new byte[1];
   private CharBuffer charBuffer;
   private ByteBuffer byteBuffer;
   private boolean endOfInput;
   private boolean draining;
   private boolean doneFlushing;

   ReaderInputStream(Reader reader1, Charset charset2, int number3) {
      this(reader1, charset2.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), number3);
   }

   ReaderInputStream(Reader reader1, CharsetEncoder charsetencoder2, int number3) {
      this.field1 = (Reader)Preconditions.checkNotNull(reader1);
      this.field2 = (CharsetEncoder)Preconditions.checkNotNull(charsetencoder2);
      Preconditions.checkArgument(number3 > 0, "bufferSize must be positive: %s", number3);
      charsetencoder2.reset();
      this.charBuffer = CharBuffer.allocate(number3);
      ((Buffer)this.charBuffer).flip();
      this.byteBuffer = ByteBuffer.allocate(number3);
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
   public int read(byte[] items1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, items1.length);
      if (number3 == 0) {
         return 0;
      }

      int number4 = 0;
      boolean flag5 = this.endOfInput;

      while (true) {
         if (this.draining) {
            number4 += this.drain(items1, number2 + number4, number3 - number4);
            if (number4 == number3 || this.doneFlushing) {
               return number4 > 0 ? number4 : -1;
            }

            this.draining = false;
            ((Buffer)this.byteBuffer).clear();
         }

         while (true) {
            CoderResult coderresult6;
            if (this.doneFlushing) {
               coderresult6 = CoderResult.UNDERFLOW;
            } else if (flag5) {
               coderresult6 = this.field2.flush(this.byteBuffer);
            } else {
               coderresult6 = this.field2.encode(this.charBuffer, this.byteBuffer, this.endOfInput);
            }

            if (coderresult6.isOverflow()) {
               this.startDraining(true);
               break;
            }

            if (coderresult6.isUnderflow()) {
               if (flag5) {
                  this.doneFlushing = true;
                  this.startDraining(false);
                  break;
               }

               if (this.endOfInput) {
                  flag5 = true;
               } else {
                  this.readMoreChars();
               }
            } else if (coderresult6.isError()) {
               coderresult6.throwException();
               return 0;
            }
         }
      }
   }

   private static CharBuffer grow(CharBuffer buffer0) {
      char[] items1 = Arrays.copyOf(buffer0.array(), buffer0.capacity() * 2);
      CharBuffer buffer2 = CharBuffer.wrap(items1);
      ((Buffer)buffer2).position(buffer0.position());
      ((Buffer)buffer2).limit(buffer0.limit());
      return buffer2;
   }

   private void readMoreChars() {
      if (availableCapacity(this.charBuffer) == 0) {
         if (this.charBuffer.position() > 0) {
            ((Buffer)this.charBuffer.compact()).flip();
         } else {
            this.charBuffer = grow(this.charBuffer);
         }
      }

      int index1 = this.charBuffer.limit();
      int number2 = this.field1.read(this.charBuffer.array(), index1, availableCapacity(this.charBuffer));
      if (number2 == -1) {
         this.endOfInput = true;
      } else {
         ((Buffer)this.charBuffer).limit(index1 + number2);
      }
   }

   private static int availableCapacity(Buffer buffer0) {
      return buffer0.capacity() - buffer0.limit();
   }

   private void startDraining(boolean flag1) {
      ((Buffer)this.byteBuffer).flip();
      if (flag1 && this.byteBuffer.remaining() == 0) {
         this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
      } else {
         this.draining = true;
      }
   }

   private int drain(byte[] items1, int index2, int number3) {
      int index4 = Math.min(number3, this.byteBuffer.remaining());
      this.byteBuffer.get(items1, index2, index4);
      return index4;
   }
}

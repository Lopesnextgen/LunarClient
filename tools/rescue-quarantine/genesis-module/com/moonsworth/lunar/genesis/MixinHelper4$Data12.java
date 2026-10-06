package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;

class MixinHelper4$Data12 extends BaseEncoding {
   final MixinHelper4$Data9 field6;
   final @Nullable Character field7;
   @LazyInit
   private transient @Nullable BaseEncoding field8;
   @LazyInit
   private transient @Nullable BaseEncoding field9;

   MixinHelper4$Data12(String var1, String var2, @Nullable Character var3) {
      this(new MixinHelper4$Data9(var1, var2.toCharArray()), var3);
   }

   MixinHelper4$Data12(MixinHelper4$Data9 var1, @Nullable Character var2) {
      this.field6 = Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(var2 == null || !var1.matches(var2), "Padding character %s was already in alphabet", var2);
      this.field7 = var2;
   }

   @Override
   int maxEncodedSize(int var1) {
      return this.field6.field5 * MixinHelper7_3.divide(var1, this.field6.field6, RoundingMode.CEILING);
   }

   @Annotation3
   @Override
   public OutputStream encodingStream(final java.io.Writer var1) {
      Preconditions.checkNotNull(var1);
      return new OutputStream() {
         int bitBuffer = 0;
         int bitBufferLength = 0;
         int writtenChars = 0;

         @Override
         public void write(int var1x) {
            this.bitBuffer <<= 8;
            this.bitBuffer |= var1x & 0xFF;

            for (this.bitBufferLength += 8;
               this.bitBufferLength >= MixinHelper4$Data12.this.field6.field4;
               this.bitBufferLength = this.bitBufferLength - MixinHelper4$Data12.this.field6.field4
            ) {
               int var2 = this.bitBuffer >> this.bitBufferLength - MixinHelper4$Data12.this.field6.field4 & MixinHelper4$Data12.this.field6.field3;
               var1.write(MixinHelper4$Data12.this.field6.encode(var2));
               this.writtenChars++;
            }
         }

         @Override
         public void flush() {
            var1.flush();
         }

         @Override
         public void close() {
            if (this.bitBufferLength > 0) {
               int var1x = this.bitBuffer << MixinHelper4$Data12.this.field6.field4 - this.bitBufferLength & MixinHelper4$Data12.this.field6.field3;
               var1.write(MixinHelper4$Data12.this.field6.encode(var1x));
               this.writtenChars++;
               if (MixinHelper4$Data12.this.field7 != null) {
                  while (this.writtenChars % MixinHelper4$Data12.this.field6.field5 != 0) {
                     var1.write(MixinHelper4$Data12.this.field7);
                     this.writtenChars++;
                  }
               }
            }

            var1.close();
         }
      };
   }

   @Override
   void encodeTo(Appendable var1, byte[] var2, int var3, int var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkPositionIndexes(var3, var3 + var4, var2.length);
      int var5 = 0;

      while (var5 < var4) {
         this.encodeChunkTo(var1, var2, var3 + var5, Math.min(this.field6.field6, var4 - var5));
         var5 += this.field6.field6;
      }
   }

   void encodeChunkTo(Appendable var1, byte[] var2, int var3, int var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkPositionIndexes(var3, var3 + var4, var2.length);
      Preconditions.checkArgument(var4 <= this.field6.field6);
      long var5 = 0L;

      for (int var7 = 0; var7 < var4; var7++) {
         var5 |= var2[var3 + var7] & 0xFF;
         var5 <<= 8;
      }

      int var11 = (var4 + 1) * 8 - this.field6.field4;

      int var8;
      for (var8 = 0; var8 < var4 * 8; var8 += this.field6.field4) {
         int var9 = (int)(var5 >>> var11 - var8) & this.field6.field3;
         var1.append(this.field6.encode(var9));
      }

      if (this.field7 != null) {
         while (var8 < this.field6.field6 * 8) {
            var1.append(this.field7);
            var8 += this.field6.field4;
         }
      }
   }

   @Override
   int maxDecodedSize(int var1) {
      return (int)(((long)this.field6.field4 * var1 + 7L) / 8L);
   }

   @Override
   CharSequence trimTrailingPadding(CharSequence var1) {
      Preconditions.checkNotNull(var1);
      if (this.field7 == null) {
         return var1;
      }

      char var2 = this.field7;
      int var3 = var1.length() - 1;

      while (var3 >= 0 && var1.charAt(var3) == var2) {
         var3--;
      }

      return var1.subSequence(0, var3 + 1);
   }

   @Override
   public boolean canDecode(CharSequence var1) {
      Preconditions.checkNotNull(var1);
      var1 = this.trimTrailingPadding(var1);
      if (!this.field6.isValidPaddingStartPosition(var1.length())) {
         return false;
      }

      for (int var2 = 0; var2 < var1.length(); var2++) {
         if (!this.field6.canDecode(var1.charAt(var2))) {
            return false;
         }
      }

      return true;
   }

   @Override
   int decodeTo(byte[] var1, CharSequence var2) {
      Preconditions.checkNotNull(var1);
      var2 = this.trimTrailingPadding(var2);
      if (!this.field6.isValidPaddingStartPosition(var2.length())) {
         throw new MixinHelper4$Data11("Invalid input length " + var2.length());
      }

      int var3 = 0;

      for (int var4 = 0; var4 < var2.length(); var4 += this.field6.field5) {
         long var5 = 0L;
         int var7 = 0;

         for (int var8 = 0; var8 < this.field6.field5; var8++) {
            var5 <<= this.field6.field4;
            if (var4 + var8 < var2.length()) {
               var5 |= this.field6.decode(var2.charAt(var4 + var7++));
            }
         }

         int var11 = this.field6.field6 * 8 - var7 * this.field6.field4;

         for (int var9 = (this.field6.field6 - 1) * 8; var9 >= var11; var9 -= 8) {
            var1[var3++] = (byte)(var5 >>> var9 & 255L);
         }
      }

      return var3;
   }

   @Annotation3
   @Override
   public InputStream decodingStream(final java.io.Reader var1) {
      Preconditions.checkNotNull(var1);
      return new InputStream() {
         int bitBuffer = 0;
         int bitBufferLength = 0;
         int readChars = 0;
         boolean hitPadding = false;

         @Override
         public int read() {
            while (true) {
               int var1x = var1.read();
               if (var1x == -1) {
                  if (!this.hitPadding && !MixinHelper4$Data12.this.field6.isValidPaddingStartPosition(this.readChars)) {
                     throw new MixinHelper4$Data11("Invalid input length " + this.readChars);
                  }

                  return -1;
               }

               this.readChars++;
               char var2 = (char)var1x;
               if (MixinHelper4$Data12.this.field7 != null && MixinHelper4$Data12.this.field7 == var2) {
                  if (!this.hitPadding && (this.readChars == 1 || !MixinHelper4$Data12.this.field6.isValidPaddingStartPosition(this.readChars - 1))) {
                     throw new MixinHelper4$Data11("Padding cannot start at index " + this.readChars);
                  }

                  this.hitPadding = true;
               } else {
                  if (this.hitPadding) {
                     throw new MixinHelper4$Data11("Expected padding character but found '" + var2 + "' at index " + this.readChars);
                  }

                  this.bitBuffer = this.bitBuffer << MixinHelper4$Data12.this.field6.field4;
                  this.bitBuffer = this.bitBuffer | MixinHelper4$Data12.this.field6.decode(var2);
                  this.bitBufferLength = this.bitBufferLength + MixinHelper4$Data12.this.field6.field4;
                  if (this.bitBufferLength >= 8) {
                     this.bitBufferLength -= 8;
                     return this.bitBuffer >> this.bitBufferLength & 0xFF;
                  }
               }
            }
         }

         @Override
         public int read(byte[] var1x, int var2, int var3) {
            Preconditions.checkPositionIndexes(var2, var2 + var3, var1x.length);

            int var4;
            for (var4 = var2; var4 < var2 + var3; var4++) {
               int var5 = this.read();
               if (var5 == -1) {
                  int var6 = var4 - var2;
                  return var6 == 0 ? -1 : var6;
               }

               var1x[var4] = (byte)var5;
            }

            return var4 - var2;
         }

         @Override
         public void close() {
            var1.close();
         }
      };
   }

   @Override
   public BaseEncoding method6() {
      return this.field7 == null ? this : this.method6(this.field6, null);
   }

   @Override
   public BaseEncoding method7(char var1) {
      return 8 % this.field6.field4 != 0 && (this.field7 == null || this.field7 != var1) ? this.method6(this.field6, var1) : this;
   }

   @Override
   public BaseEncoding method8(String var1, int var2) {
      for (int var3 = 0; var3 < var1.length(); var3++) {
         Preconditions.checkArgument(!this.field6.matches(var1.charAt(var3)), "Separator (%s) cannot contain alphabet characters", var1);
      }

      if (this.field7 != null) {
         Preconditions.checkArgument(var1.indexOf(this.field7) < 0, "Separator (%s) cannot contain padding character", var1);
      }

      return new MixinHelper4$Data13(this, var1, var2);
   }

   @Override
   public BaseEncoding method9() {
      BaseEncoding var1 = this.field8;
      if (var1 == null) {
         MixinHelper4$Data9 var2 = this.field6.method1();
         var1 = this.field8 = var2 == this.field6 ? this : this.method6(var2, this.field7);
      }

      return var1;
   }

   @Override
   public BaseEncoding method10() {
      BaseEncoding var1 = this.field9;
      if (var1 == null) {
         MixinHelper4$Data9 var2 = this.field6.method2();
         var1 = this.field9 = var2 == this.field6 ? this : this.method6(var2, this.field7);
      }

      return var1;
   }

   BaseEncoding method6(MixinHelper4$Data9 var1, @Nullable Character var2) {
      return new MixinHelper4$Data12(var1, var2);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("BaseEncoding.");
      var1.append(this.field6.toString());
      if (8 % this.field6.field4 != 0) {
         if (this.field7 == null) {
            var1.append(".omitPadding()");
         } else {
            var1.append(".withPadChar('").append(this.field7).append("')");
         }
      }

      return var1.toString();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper4$Data12)) {
         return false;
      }

      MixinHelper4$Data12 var2 = (MixinHelper4$Data12)var1;
      return this.field6.equals(var2.field6) && MixinHelper72.equal(this.field7, var2.field7);
   }

   @Override
   public int hashCode() {
      return this.field6.hashCode() ^ MixinHelper72.hashCode(this.field7);
   }
}

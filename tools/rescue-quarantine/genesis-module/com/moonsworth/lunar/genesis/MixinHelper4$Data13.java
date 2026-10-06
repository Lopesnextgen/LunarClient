package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import com.google.common.io.BaseEncoding;
import com.google.common.base.Preconditions;

final class MixinHelper4$Data13 extends BaseEncoding {
   private final BaseEncoding field6;
   private final String field7;
   private final int field8;

   MixinHelper4$Data13(BaseEncoding var1, String var2, int var3) {
      this.field6 = Preconditions.checkNotNull(var1);
      this.field7 = Preconditions.checkNotNull(var2);
      this.field8 = var3;
      Preconditions.checkArgument(var3 > 0, "Cannot add a separator after every %s chars", var3);
   }

   @Override
   CharSequence trimTrailingPadding(CharSequence var1) {
      return this.field6.trimTrailingPadding(var1);
   }

   @Override
   int maxEncodedSize(int var1) {
      int var2 = this.field6.maxEncodedSize(var1);
      return var2 + this.field7.length() * MixinHelper7_3.divide(Math.max(0, var2 - 1), this.field8, RoundingMode.FLOOR);
   }

   @Annotation3
   @Override
   public OutputStream encodingStream(java.io.Writer var1) {
      return this.field6.encodingStream(separatingWriter(var1, this.field7, this.field8));
   }

   @Override
   void encodeTo(Appendable var1, byte[] var2, int var3, int var4) {
      this.field6.encodeTo(separatingAppendable(var1, this.field7, this.field8), var2, var3, var4);
   }

   @Override
   int maxDecodedSize(int var1) {
      return this.field6.maxDecodedSize(var1);
   }

   @Override
   public boolean canDecode(CharSequence var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         if (this.field7.indexOf(var4) < 0) {
            var2.append(var4);
         }
      }

      return this.field6.canDecode(var2);
   }

   @Override
   int decodeTo(byte[] var1, CharSequence var2) {
      StringBuilder var3 = new StringBuilder(var2.length());

      for (int var4 = 0; var4 < var2.length(); var4++) {
         char var5 = var2.charAt(var4);
         if (this.field7.indexOf(var5) < 0) {
            var3.append(var5);
         }
      }

      return this.field6.decodeTo(var1, var3);
   }

   @Annotation3
   @Override
   public InputStream decodingStream(java.io.Reader var1) {
      return this.field6.decodingStream(ignoringReader(var1, this.field7));
   }

   @Override
   public BaseEncoding method6() {
      return this.field6.method6().method8(this.field7, this.field8);
   }

   @Override
   public BaseEncoding method7(char var1) {
      return this.field6.method7(var1).method8(this.field7, this.field8);
   }

   @Override
   public BaseEncoding method8(String var1, int var2) {
      throw new UnsupportedOperationException("Already have a separator");
   }

   @Override
   public BaseEncoding method9() {
      return this.field6.method9().method8(this.field7, this.field8);
   }

   @Override
   public BaseEncoding method10() {
      return this.field6.method10().method8(this.field7, this.field8);
   }

   @Override
   public String toString() {
      return this.field6 + ".withSeparator(\"" + this.field7 + "\", " + this.field8 + ")";
   }
}

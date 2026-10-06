package com.moonsworth.lunar.genesis;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;

final class BaseEncoding$SeparatedBaseEncoding extends MixinHelper4_11 {
   private final MixinHelper4_11 field6;
   private final String field7;
   private final int field8;

   BaseEncoding$SeparatedBaseEncoding(MixinHelper4_11 mixinhelper4_111, String text2, int number3) {
      this.field6 = (MixinHelper4_11)Preconditions.checkNotNull(mixinhelper4_111);
      this.field7 = (String)Preconditions.checkNotNull(text2);
      this.field8 = number3;
      Preconditions.checkArgument(number3 > 0, "Cannot add a separator after every %s chars", number3);
   }

   CharSequence trimTrailingPadding(CharSequence text1) {
      return this.field6.trimTrailingPadding(text1);
   }

   int maxEncodedSize(int number1) {
      int number2 = this.field6.maxEncodedSize(number1);
      return number2 + this.field7.length() * MixinHelper7_3.divide(Math.max(0, number2 - 1), this.field8, RoundingMode.FLOOR);
   }

   @GwtIncompatible
   public OutputStream encodingStream(Writer writer1) {
      return this.field6.encodingStream(separatingWriter(writer1, this.field7, this.field8));
   }

   void encodeTo(Appendable appendable1, byte[] items2, int number3, int number4) {
      this.field6.encodeTo(separatingAppendable(appendable1, this.field7, this.field8), items2, number3, number4);
   }

   int maxDecodedSize(int number1) {
      return this.field6.maxDecodedSize(number1);
   }

   public boolean canDecode(CharSequence text1) {
      StringBuilder builder2 = new StringBuilder();

      for (int index3 = 0; index3 < text1.length(); index3++) {
         char character4 = text1.charAt(index3);
         if (this.field7.indexOf(character4) < 0) {
            builder2.append(character4);
         }
      }

      return this.field6.canDecode(builder2);
   }

   int decodeTo(byte[] items1, CharSequence text2) {
      StringBuilder builder3 = new StringBuilder(text2.length());

      for (int index4 = 0; index4 < text2.length(); index4++) {
         char character5 = text2.charAt(index4);
         if (this.field7.indexOf(character5) < 0) {
            builder3.append(character5);
         }
      }

      return this.field6.decodeTo(items1, builder3);
   }

   @GwtIncompatible
   public InputStream decodingStream(Reader reader1) {
      return this.field6.decodingStream(ignoringReader(reader1, this.field7));
   }

   public MixinHelper4_11 method6() {
      return this.field6.method6().method8(this.field7, this.field8);
   }

   public MixinHelper4_11 method7(char character1) {
      return this.field6.method7(character1).method8(this.field7, this.field8);
   }

   public MixinHelper4_11 method8(String text1, int number2) {
      throw new UnsupportedOperationException("Already have a separator");
   }

   public MixinHelper4_11 method9() {
      return this.field6.method9().method8(this.field7, this.field8);
   }

   public MixinHelper4_11 method10() {
      return this.field6.method10().method8(this.field7, this.field8);
   }

   public String toString() {
      return this.field6 + ".withSeparator(\"" + this.field7 + "\", " + this.field8 + ")";
   }
}

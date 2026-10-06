package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.base.Preconditions;

@Annotation3
final class MixinHelper222 extends MixinHelper22_2 implements Serializable {
   private final Pattern field1;
   private static final long field2 = 0L;

   MixinHelper222(Pattern var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public MixinHelper21_2 method1(CharSequence var1) {
      return new MixinHelper222.Data2(this.field1.matcher(var1));
   }

   @Override
   public String pattern() {
      return this.field1.pattern();
   }

   @Override
   public int flags() {
      return this.field1.flags();
   }

   @Override
   public String toString() {
      return this.field1.toString();
   }

   private static final class Data2 extends MixinHelper21_2 {
      final Matcher field1;

      Data2(Matcher var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      public boolean matches() {
         return this.field1.matches();
      }

      @Override
      public boolean find() {
         return this.field1.find();
      }

      @Override
      public boolean find(int var1) {
         return this.field1.find(var1);
      }

      @Override
      public String replaceAll(String var1) {
         return this.field1.replaceAll(var1);
      }

      @Override
      public int end() {
         return this.field1.end();
      }

      @Override
      public int start() {
         return this.field1.start();
      }
   }
}

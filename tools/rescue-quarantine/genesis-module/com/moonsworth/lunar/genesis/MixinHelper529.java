package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Funnel;
import com.google.common.base.Preconditions;

@Immutable
abstract class MixinHelper529 extends MixinHelper52 {
   final MixinHelper5_8[] field1;
   private static final long field2 = 0L;

   MixinHelper529(MixinHelper5_8... var1) {
      for (MixinHelper5_8 var5 : var1) {
         Preconditions.checkNotNull(var5);
      }

      this.field1 = var1;
   }

   abstract HashCode method1(MixinHelper42_2[] var1);

   @Override
   public MixinHelper42_2 method1() {
      MixinHelper42_2[] var1 = new MixinHelper42_2[this.field1.length];

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = this.field1[var2].method1();
      }

      return this.method4(var1);
   }

   @Override
   public MixinHelper42_2 method2(int var1) {
      Preconditions.checkArgument(var1 >= 0);
      MixinHelper42_2[] var2 = new MixinHelper42_2[this.field1.length];

      for (int var3 = 0; var3 < var2.length; var3++) {
         var2[var3] = this.field1[var3].method2(var1);
      }

      return this.method4(var2);
   }

   private MixinHelper42_2 method4(final MixinHelper42_2[] var1) {
      return new MixinHelper42_2() {
         @Override
         public MixinHelper42_2 method2(byte var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method2(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method3(byte[] var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method3(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method4(byte[] var1x, int var2, int var3) {
            for (MixinHelper42_2 var7 : var1) {
               var7.method4(var1x, var2, var3);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method5(ByteBuffer var1x) {
            int var2 = var1x.position();

            for (MixinHelper42_2 var6 : var1) {
               ((Buffer)var1x).position(var2);
               var6.method5(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method6(short var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method6(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method7(int var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method7(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method8(long var1x) {
            for (MixinHelper42_2 var6 : var1) {
               var6.method8(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method9(float var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method9(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method10(double var1x) {
            for (MixinHelper42_2 var6 : var1) {
               var6.method10(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method11(boolean var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method11(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method12(char var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method12(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method13(CharSequence var1x) {
            for (MixinHelper42_2 var5 : var1) {
               var5.method13(var1x);
            }

            return this;
         }

         @Override
         public MixinHelper42_2 method14(CharSequence var1x, Charset var2) {
            for (MixinHelper42_2 var6 : var1) {
               var6.method14(var1x, var2);
            }

            return this;
         }

         @Override
         public <T> MixinHelper42_2 method14(T var1x, Funnel<? super T> var2) {
            for (MixinHelper42_2 var6 : var1) {
               var6.method14(var1x, var2);
            }

            return this;
         }

         @Override
         public HashCode method15() {
            return MixinHelper529.this.method1(var1);
         }
      };
   }
}

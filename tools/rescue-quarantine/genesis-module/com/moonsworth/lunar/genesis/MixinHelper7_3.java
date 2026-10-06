package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import com.google.common.annotations.GwtCompatible;
import com.google.common.math.LongMath;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true)
public final class MixinHelper7_3 {
   @Annotation4
   static final int field1 = 1073741824;
   @Annotation4
   static final int field2 = -1257966797;
   @Annotation4
   static final byte[] field3 = new byte[]{9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
   @Annotation4
   static final int[] field4 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
   @Annotation4
   static final int[] field5 = new int[]{3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
   @Annotation4
   static final int field6 = 46340;
   private static final int[] field7 = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
   @Annotation4
   static int[] biggestBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

   @Annotation2
   public static int ceilingPowerOfTwo(int var0) {
      MixinHelper10_4.checkPositive("x", var0);
      if (var0 > 1073741824) {
         throw new ArithmeticException("ceilingPowerOfTwo(" + var0 + ") not representable as an int");
      } else {
         return 1 << -Integer.numberOfLeadingZeros(var0 - 1);
      }
   }

   @Annotation2
   public static int floorPowerOfTwo(int var0) {
      MixinHelper10_4.checkPositive("x", var0);
      return Integer.highestOneBit(var0);
   }

   public static boolean isPowerOfTwo(int var0) {
      return var0 > 0 & (var0 & var0 - 1) == 0;
   }

   @Annotation4
   static int lessThanBranchFree(int var0, int var1) {
      return ~(~(var0 - var1)) >>> 31;
   }

   public static int log2(int var0, RoundingMode var1) {
      MixinHelper10_4.checkPositive("x", var0);
      switch (var1) {
         case UNNECESSARY:
            MixinHelper10_4.checkRoundingUnnecessary(isPowerOfTwo(var0));
         case DOWN:
         case FLOOR:
            return 31 - Integer.numberOfLeadingZeros(var0);
         case UP:
         case CEILING:
            return 32 - Integer.numberOfLeadingZeros(var0 - 1);
         case HALF_DOWN:
         case HALF_UP:
         case HALF_EVEN:
            int var2 = Integer.numberOfLeadingZeros(var0);
            int var3 = -1257966797 >>> var2;
            int var4 = 31 - var2;
            return var4 + lessThanBranchFree(var3, var0);
         default:
            throw new AssertionError();
      }
   }

   @Annotation3
   public static int log10(int var0, RoundingMode var1) {
      MixinHelper10_4.checkPositive("x", var0);
      int var2 = log10Floor(var0);
      int var3 = field4[var2];
      switch (var1) {
         case UNNECESSARY:
            MixinHelper10_4.checkRoundingUnnecessary(var0 == var3);
         case DOWN:
         case FLOOR:
            return var2;
         case UP:
         case CEILING:
            return var2 + lessThanBranchFree(var3, var0);
         case HALF_DOWN:
         case HALF_UP:
         case HALF_EVEN:
            return var2 + lessThanBranchFree(field5[var2], var0);
         default:
            throw new AssertionError();
      }
   }

   private static int log10Floor(int var0) {
      byte var1 = field3[Integer.numberOfLeadingZeros(var0)];
      return var1 - lessThanBranchFree(var0, field4[var1]);
   }

   @Annotation3
   public static int pow(int var0, int var1) {
      MixinHelper10_4.checkNonNegative("exponent", var1);
      switch (var0) {
         case -2:
            if (var1 < 32) {
               return (var1 & 1) == 0 ? 1 << var1 : -(1 << var1);
            }

            return 0;
         case -1:
            return (var1 & 1) == 0 ? 1 : -1;
         case 0:
            return var1 == 0 ? 1 : 0;
         case 1:
            return 1;
         case 2:
            return var1 < 32 ? 1 << var1 : 0;
         default:
            byte var2 = 1;

            while (true) {
               switch (var1) {
                  case 0:
                     return var2;
                  case 1:
                     return var0 * var2;
               }

               var2 *= (var1 & 1) == 0 ? 1 : var0;
               var0 *= var0;
               var1 >>= 1;
            }
      }
   }

   @Annotation3
   public static int sqrt(int var0, RoundingMode var1) {
      MixinHelper10_4.checkNonNegative("x", var0);
      int var2 = sqrtFloor(var0);
      switch (var1) {
         case UNNECESSARY:
            MixinHelper10_4.checkRoundingUnnecessary(var2 * var2 == var0);
         case DOWN:
         case FLOOR:
            return var2;
         case UP:
         case CEILING:
            return var2 + lessThanBranchFree(var2 * var2, var0);
         case HALF_DOWN:
         case HALF_UP:
         case HALF_EVEN:
            int var3 = var2 * var2 + var2;
            return var2 + lessThanBranchFree(var3, var0);
         default:
            throw new AssertionError();
      }
   }

   private static int sqrtFloor(int var0) {
      return (int)Math.sqrt(var0);
   }

   public static int divide(int var0, int var1, RoundingMode var2) {
      Preconditions.checkNotNull(var2);
      if (var1 == 0) {
         throw new ArithmeticException("/ by zero");
      }

      int var3 = var0 / var1;
      int var4 = var0 - var1 * var3;
      if (var4 == 0) {
         return var3;
      }

      int var5 = 1 | (var0 ^ var1) >> 31;
      boolean var6;
      switch (var2) {
         case UNNECESSARY:
            MixinHelper10_4.checkRoundingUnnecessary(var4 == 0);
         case DOWN:
            var6 = false;
            break;
         case FLOOR:
            var6 = var5 < 0;
            break;
         case UP:
            var6 = true;
            break;
         case CEILING:
            var6 = var5 > 0;
            break;
         case HALF_DOWN:
         case HALF_UP:
         case HALF_EVEN:
            int var7 = Math.abs(var4);
            int var8 = var7 - (Math.abs(var1) - var7);
            if (var8 == 0) {
               var6 = var2 == RoundingMode.HALF_UP || var2 == RoundingMode.HALF_EVEN & (var3 & 1) != 0;
            } else {
               var6 = var8 > 0;
            }
            break;
         default:
            throw new AssertionError();
      }

      return var6 ? var3 + var5 : var3;
   }

   public static int mod(int var0, int var1) {
      if (var1 <= 0) {
         throw new ArithmeticException("Modulus " + var1 + " must be > 0");
      }

      int var2 = var0 % var1;
      return var2 >= 0 ? var2 : var2 + var1;
   }

   public static int gcd(int var0, int var1) {
      MixinHelper10_4.checkNonNegative("a", var0);
      MixinHelper10_4.checkNonNegative("b", var1);
      if (var0 == 0) {
         return var1;
      }

      if (var1 == 0) {
         return var0;
      }

      int var2 = Integer.numberOfTrailingZeros(var0);
      var0 >>= var2;
      int var3 = Integer.numberOfTrailingZeros(var1);
      var1 >>= var3;

      while (var0 != var1) {
         int var4 = var0 - var1;
         int var5 = var4 & var4 >> 31;
         var0 = var4 - var5 - var5;
         var1 += var5;
         var0 >>= Integer.numberOfTrailingZeros(var0);
      }

      return var0 << Math.min(var2, var3);
   }

   public static int checkedAdd(int var0, int var1) {
      long var2 = (long)var0 + var1;
      MixinHelper10_4.checkNoOverflow(var2 == (int)var2, "checkedAdd", var0, var1);
      return (int)var2;
   }

   public static int checkedSubtract(int var0, int var1) {
      long var2 = (long)var0 - var1;
      MixinHelper10_4.checkNoOverflow(var2 == (int)var2, "checkedSubtract", var0, var1);
      return (int)var2;
   }

   public static int checkedMultiply(int var0, int var1) {
      long var2 = (long)var0 * var1;
      MixinHelper10_4.checkNoOverflow(var2 == (int)var2, "checkedMultiply", var0, var1);
      return (int)var2;
   }

   public static int checkedPow(int var0, int var1) {
      MixinHelper10_4.checkNonNegative("exponent", var1);
      switch (var0) {
         case -2:
            MixinHelper10_4.checkNoOverflow(var1 < 32, "checkedPow", var0, var1);
            return (var1 & 1) == 0 ? 1 << var1 : -1 << var1;
         case -1:
            return (var1 & 1) == 0 ? 1 : -1;
         case 0:
            return var1 == 0 ? 1 : 0;
         case 1:
            return 1;
         case 2:
            MixinHelper10_4.checkNoOverflow(var1 < 31, "checkedPow", var0, var1);
            return 1 << var1;
         default:
            int var2 = 1;

            while (true) {
               switch (var1) {
                  case 0:
                     return var2;
                  case 1:
                     return checkedMultiply(var2, var0);
               }

               if ((var1 & 1) != 0) {
                  var2 = checkedMultiply(var2, var0);
               }

               var1 >>= 1;
               if (var1 > 0) {
                  MixinHelper10_4.checkNoOverflow(-46340 <= var0 & var0 <= 46340, "checkedPow", var0, var1);
                  var0 *= var0;
               }
            }
      }
   }

   @Annotation2
   public static int saturatedAdd(int var0, int var1) {
      return MixinHelper122.saturatedCast((long)var0 + var1);
   }

   @Annotation2
   public static int saturatedSubtract(int var0, int var1) {
      return MixinHelper122.saturatedCast((long)var0 - var1);
   }

   @Annotation2
   public static int saturatedMultiply(int var0, int var1) {
      return MixinHelper122.saturatedCast((long)var0 * var1);
   }

   @Annotation2
   public static int saturatedPow(int var0, int var1) {
      MixinHelper10_4.checkNonNegative("exponent", var1);
      switch (var0) {
         case -2:
            if (var1 >= 32) {
               return Integer.MAX_VALUE + (var1 & 1);
            }

            return (var1 & 1) == 0 ? 1 << var1 : -1 << var1;
         case -1:
            return (var1 & 1) == 0 ? 1 : -1;
         case 0:
            return var1 == 0 ? 1 : 0;
         case 1:
            return 1;
         case 2:
            if (var1 >= 31) {
               return Integer.MAX_VALUE;
            }

            return 1 << var1;
         default:
            int var2 = 1;
            int var3 = Integer.MAX_VALUE + (var0 >>> 31 & var1 & 1);

            while (true) {
               switch (var1) {
                  case 0:
                     return var2;
                  case 1:
                     return saturatedMultiply(var2, var0);
               }

               if ((var1 & 1) != 0) {
                  var2 = saturatedMultiply(var2, var0);
               }

               var1 >>= 1;
               if (var1 > 0) {
                  if (-46340 > var0 | var0 > 46340) {
                     return var3;
                  }

                  var0 *= var0;
               }
            }
      }
   }

   public static int factorial(int var0) {
      MixinHelper10_4.checkNonNegative("n", var0);
      return var0 < field7.length ? field7[var0] : Integer.MAX_VALUE;
   }

   public static int binomial(int var0, int var1) {
      MixinHelper10_4.checkNonNegative("n", var0);
      MixinHelper10_4.checkNonNegative("k", var1);
      Preconditions.checkArgument(var1 <= var0, "k (%s) > n (%s)", var1, var0);
      if (var1 > var0 >> 1) {
         var1 = var0 - var1;
      }

      if (var1 < biggestBinomials.length && var0 <= biggestBinomials[var1]) {
         switch (var1) {
            case 0:
               return 1;
            case 1:
               return var0;
            default:
               long var2 = 1L;

               for (int var4 = 0; var4 < var1; var4++) {
                  var2 *= var0 - var4;
                  var2 /= var4 + 1;
               }

               return (int)var2;
         }
      } else {
         return Integer.MAX_VALUE;
      }
   }

   public static int mean(int var0, int var1) {
      return (var0 & var1) + ((var0 ^ var1) >> 1);
   }

   @Annotation3
   @Annotation2
   public static boolean isPrime(int var0) {
      return LongMath.isPrime(var0);
   }

   private MixinHelper7_3() {
   }
}

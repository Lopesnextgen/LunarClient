package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.base.Preconditions;

@Annotation2
@CanIgnoreReturnValue
@Annotation3
public final class MixinHelper162 implements TimeLimiter {
   @Override
   public <T> T newProxy(T var1, Class<T> var2, long var3, TimeUnit var5) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Preconditions.checkNotNull(var5);
      return (T)var1;
   }

   @Override
   public <T> T callWithTimeout(Callable<T> var1, long var2, TimeUnit var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var4);

      try {
         return (T)var1.call();
      } catch (RuntimeException var6) {
         throw new MixinHelperException_2(var6);
      } catch (Exception var7) {
         throw new ExecutionException(var7);
      } catch (Error var8) {
         throw new MixinHelperError(var8);
      } catch (Throwable var9) {
         throw new ExecutionException(var9);
      }
   }

   @Override
   public <T> T callUninterruptiblyWithTimeout(Callable<T> var1, long var2, TimeUnit var4) {
      return this.callWithTimeout(var1, var2, var4);
   }

   @Override
   public void runWithTimeout(Runnable var1, long var2, TimeUnit var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var4);

      try {
         var1.run();
      } catch (RuntimeException var6) {
         throw new MixinHelperException_2(var6);
      } catch (Error var7) {
         throw new MixinHelperError(var7);
      } catch (Throwable var8) {
         throw new MixinHelperException_2(var8);
      }
   }

   @Override
   public void runUninterruptiblyWithTimeout(Runnable var1, long var2, TimeUnit var4) {
      this.runWithTimeout(var1, var2, var4);
   }
}

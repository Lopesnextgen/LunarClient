package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.eventbus.EventBus;
import com.google.common.base.Preconditions;

class MixinHelper6_6 {
   @Weak
   private EventBus field1;
   @Annotation4
   final Object field2;
   private final Method field3;
   private final Executor field4;

   static MixinHelper6_6 method1(EventBus var0, Object var1, Method var2) {
      return isDeclaredThreadSafe(var2) ? new MixinHelper6_6(var0, var1, var2) : new MixinHelper6$Data27(var0, var1, var2);
   }

   private MixinHelper6_6(EventBus var1, Object var2, Method var3) {
      this.field1 = var1;
      this.field2 = Preconditions.checkNotNull(var2);
      this.field3 = var3;
      var3.setAccessible(true);
      this.field4 = var1.method2();
   }

   final void method2(final Object var1) {
      this.field4.execute(new Runnable() {
         @Override
         public void run() {
            try {
               MixinHelper6_6.this.invokeSubscriberMethod(var1);
            } catch (InvocationTargetException var2) {
               MixinHelper6_6.this.field1.method3(var2.getCause(), MixinHelper6_6.this.method3(var1));
            }
         }
      });
   }

   @Annotation4
   void invokeSubscriberMethod(Object var1) {
      try {
         this.field3.invoke(this.field2, Preconditions.checkNotNull(var1));
      } catch (IllegalArgumentException var3) {
         throw new Error("Method rejected target/argument: " + var1, var3);
      } catch (IllegalAccessException var4) {
         throw new Error("Method became inaccessible: " + var1, var4);
      } catch (InvocationTargetException var5) {
         if (var5.getCause() instanceof Error) {
            throw (Error)var5.getCause();
         } else {
            throw var5;
         }
      }
   }

   private MixinHelper7_6 method3(Object var1) {
      return new MixinHelper7_6(this.field1, var1, this.field2, this.field3);
   }

   @Override
   public final int hashCode() {
      return (31 + this.field3.hashCode()) * 31 + System.identityHashCode(this.field2);
   }

   @Override
   public final boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof MixinHelper6_6)) {
         return false;
      }

      MixinHelper6_6 var2 = (MixinHelper6_6)var1;
      return this.field2 == var2.field2 && this.field3.equals(var2.field3);
   }

   private static boolean isDeclaredThreadSafe(Method var0) {
      return var0.getAnnotation(Annotation_4.class) != null;
   }
}

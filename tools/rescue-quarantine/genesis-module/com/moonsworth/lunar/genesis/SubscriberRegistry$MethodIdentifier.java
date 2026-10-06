package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;

final class SubscriberRegistry$MethodIdentifier {
   private final String field1;
   private final List<Class<?>> field2;

   SubscriberRegistry$MethodIdentifier(Method method1) {
      this.field1 = method1.getName();
      this.field2 = Arrays.asList(method1.getParameterTypes());
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1, this.field2});
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof SubscriberRegistry$MethodIdentifier)) {
         return false;
      }

      SubscriberRegistry$MethodIdentifier mixinhelper$data522 = (SubscriberRegistry$MethodIdentifier)obj1;
      return this.field1.equals(mixinhelper$data522.field1) && this.field2.equals(mixinhelper$data522.field2);
   }
}

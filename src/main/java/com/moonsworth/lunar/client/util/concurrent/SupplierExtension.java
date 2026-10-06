package com.moonsworth.lunar.client.util.concurrent;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SupplierExtension<T> extends Supplier<T> {
   static <T> SupplierExtension<T> lazy(@Nonnull Supplier<T> supplier0) {
      return new SupplierExtension.Data<>(supplier0);
   }

   static <T> SupplierExtension<T> concurrentLazy(@Nonnull Supplier<T> supplier0) {
      return new SupplierExtension.ConcurrentLazySupplier<>(supplier0);
   }

   boolean initialized();

   final class Data<T> implements SupplierExtension<T> {
      private Supplier<T> supplier;
      private T instance;

      private Data(Supplier<T> supplier1) {
         this.supplier = supplier1;
      }

      @Nullable
      @Override
      public final T get() {
         if (this.supplier != null) {
            this.instance = this.supplier.get();
            this.supplier = null;
         }

         return this.instance;
      }

      @Override
      public boolean initialized() {
         return this.supplier == null;
      }
   }

   final class ConcurrentLazySupplier<T> implements SupplierExtension<T> {
      private volatile Object lock = new Object();
      private volatile Supplier<T> supplier;
      private volatile T instance;

      private ConcurrentLazySupplier(Supplier<T> supplier1) {
         this.supplier = supplier1;
      }

      @Nullable
      @Override
      public final T get() {
         Object obj1 = this.lock;
         if (this.supplier != null) {
            synchronized (obj1) {
               if (this.supplier != null) {
                  this.instance = this.supplier.get();
                  this.supplier = null;
                  this.lock = null;
               }
            }
         }

         return this.instance;
      }

      @Override
      public boolean initialized() {
         return this.supplier == null;
      }
   }
}

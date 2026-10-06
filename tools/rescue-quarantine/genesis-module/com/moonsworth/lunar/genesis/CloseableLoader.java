package com.moonsworth.lunar.genesis;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.FinalizableReference;
import com.google.common.base.internal.Finalizer;

@Annotation3
public class CloseableLoader implements Closeable {
   private static final Logger field1 = Logger.getLogger(CloseableLoader.class.getName());
   private static final String field2 = "com.moonsworth.lunar.genesis.lib.google.common.base.internal.Finalizer";
   private static final Method field3;
   final ReferenceQueue<Object> field4 = new ReferenceQueue<>();
   final PhantomReference<Object> field5 = new PhantomReference<>(this, this.field4);
   final boolean field6;

   public CloseableLoader() {
      boolean var1 = false;

      try {
         field3.invoke(null, FinalizableReference.class, this.field4, this.field5);
         var1 = true;
      } catch (IllegalAccessException var3) {
         throw new AssertionError(var3);
      } catch (Throwable var4) {
         field1.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", var4);
      }

      this.field6 = var1;
   }

   @Override
   public void close() {
      this.field5.enqueue();
      this.cleanUp();
   }

   void cleanUp() {
      if (!this.field6) {
         Reference var1;
         while ((var1 = this.field4.poll()) != null) {
            var1.clear();

            try {
               ((FinalizableReference)var1).finalizeReferent();
            } catch (Throwable var3) {
               field1.log(Level.SEVERE, "Error cleaning up after reference.", var3);
            }
         }
      }
   }

   private static Class<?> method1(CloseableLoader.Extension... var0) {
      for (CloseableLoader.Extension var4 : var0) {
         Class var5 = var4.loadFinalizer();
         if (var5 != null) {
            return var5;
         }
      }

      throw new AssertionError();
   }

   static Method getStartFinalizer(Class<?> var0) {
      try {
         return var0.getMethod("startFinalizer", Class.class, ReferenceQueue.class, PhantomReference.class);
      } catch (NoSuchMethodException var2) {
         throw new AssertionError(var2);
      }
   }

   static {
      Class var0 = method1(new CloseableLoader.Data3(), new CloseableLoader.Data2(), new CloseableLoader.Data());
      field3 = getStartFinalizer(var0);
   }

   static class Data implements CloseableLoader.Extension {
      @Override
      public Class<?> loadFinalizer() {
         try {
            return Class.forName(
               "com.moonsworth.lunar.genesis.HORHROIOIOICIRHIOCOICHHHIHCIIO.HORHROIOIOICIRHIOCOICHHHIHCIIO.HORHROIOIOICIRHIOCOICHHHIHCIIO.HHRROIIHRRICIIHIIHICRHHRHOHHOO.HORHROIOIOICIRHIOCOICHHHIHCIIO.HORHROIOIOICIRHIOCOICHHHIHCIIO"
            );
         } catch (ClassNotFoundException var2) {
            throw new AssertionError(var2);
         }
      }
   }

   static class Data2 implements CloseableLoader.Extension {
      private static final String field1 = "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.";

      @Override
      public @Nullable Class<?> loadFinalizer() {
         try {
            java.net.URLClassLoader var1 = this.newLoader(this.getBaseUrl());
            return var1.loadClass("com.moonsworth.lunar.genesis.lib.google.common.base.internal.Finalizer");
         } catch (Exception var2) {
            CloseableLoader.field1
               .log(
                  Level.WARNING,
                  "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.",
                  var2
               );
            return null;
         }
      }

      URL getBaseUrl() {
         String var1 = "com.moonsworth.lunar.genesis.lib.google.common.base.internal.Finalizer".replace('.', '/') + ".class";
         URL var2 = this.getClass().getClassLoader().getResource(var1);
         if (var2 == null) {
            throw new FileNotFoundException(var1);
         }

         String var3 = var2.toString();
         if (!var3.endsWith(var1)) {
            throw new IOException("Unsupported path style: " + var3);
         }

         var3 = var3.substring(0, var3.length() - var1.length());
         return new URL(var2, var3);
      }

      java.net.URLClassLoader newLoader(URL var1) {
         return new java.net.URLClassLoader(new URL[]{var1}, null);
      }
   }

   static class Data3 implements CloseableLoader.Extension {
      @Annotation4
      static boolean disabled;

      @Override
      public @Nullable Class<?> loadFinalizer() {
         if (disabled) {
            return null;
         }

         ClassLoader var1;
         try {
            var1 = ClassLoader.getSystemClassLoader();
         } catch (SecurityException var4) {
            CloseableLoader.field1.info("Not allowed to access system class loader.");
            return null;
         }

         if (var1 != null) {
            try {
               return var1.loadClass("com.moonsworth.lunar.genesis.lib.google.common.base.internal.Finalizer");
            } catch (ClassNotFoundException var3) {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   interface Extension {
      @Nullable Class<?> loadFinalizer();
   }
}

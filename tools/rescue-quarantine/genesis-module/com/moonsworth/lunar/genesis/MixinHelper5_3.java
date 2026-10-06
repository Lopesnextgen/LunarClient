package com.moonsworth.lunar.genesis;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation2
@Annotation3
public final class MixinHelper5_3 {
   @Annotation4
   static final Logger field1 = Logger.getLogger(MixinHelper5_3.class.getName());

   private MixinHelper5_3() {
   }

   public static void close(@Nullable Closeable var0, boolean var1) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (IOException var3) {
            if (!var1) {
               throw var3;
            }

            field1.log(Level.WARNING, "IOException thrown while closing Closeable.", var3);
         }
      }
   }

   public static void closeQuietly(@Nullable InputStream var0) {
      try {
         close(var0, true);
      } catch (IOException var2) {
         throw new AssertionError(var2);
      }
   }

   public static void closeQuietly(@Nullable Reader var0) {
      try {
         close(var0, true);
      } catch (IOException var2) {
         throw new AssertionError(var2);
      }
   }
}

package com.moonsworth.lunar.genesis;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.CharMatcher;
import com.google.common.base.Enums;
import com.google.common.base.Strings;

@GwtCompatible(emulated = true)
final class MixinHelper14 {
   private static final Logger field1 = Logger.getLogger(MixinHelper14.class.getName());
   private static final MixinHelper8_10 field2 = method4();

   private MixinHelper14() {
   }

   static long systemNanoTime() {
      return System.nanoTime();
   }

   static CharMatcher method1(CharMatcher var0) {
      return var0.method25();
   }

   static <T extends Enum<T>> SerializableBase_2<T> method2(Class<T> var0, String var1) {
      WeakReference var2 = Enums.getEnumConstants(var0).get(var1);
      return var2 == null ? SerializableBase_2.method1() : SerializableBase_2.method2((T)var0.cast(var2.get()));
   }

   static String formatCompact4Digits(double var0) {
      return String.format(Locale.ROOT, "%.4g", var0);
   }

   static boolean stringIsNullOrEmpty(@Nullable String var0) {
      return var0 == null || var0.isEmpty();
   }

   static String nullToEmpty(@Nullable String var0) {
      return var0 == null ? "" : var0;
   }

   static String emptyToNull(@Nullable String var0) {
      return stringIsNullOrEmpty(var0) ? null : var0;
   }

   static MixinHelper22_2 method3(String var0) {
      Preconditions.checkNotNull(var0);
      return field2.method1(var0);
   }

   static boolean patternCompilerIsPcreLike() {
      return field2.isPcreLike();
   }

   private static MixinHelper8_10 method4() {
      return new MixinHelper14.Data4();
   }

   private static void logPatternCompilerError(ServiceConfigurationError var0) {
      field1.log(Level.WARNING, "Error loading regex compiler, falling back to next option", var0);
   }

   static void checkGwtRpcEnabled() {
      String var0 = "guava.gwt.emergency_reenable_rpc";
      if (!Boolean.parseBoolean(System.getProperty(var0, "false"))) {
         throw new UnsupportedOperationException(
            Strings.lenientFormat(
               "We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.",
               var0,
               "https://stackoverflow.com/q/5189914/28465",
               "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"
            )
         );
      }

      field1.log(
         Level.WARNING,
         "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.",
         new Throwable()
      );
   }

   private static final class Data4 implements MixinHelper8_10 {
      private Data4() {
      }

      @Override
      public MixinHelper22_2 method1(String var1) {
         return new MixinHelper222(Pattern.compile(var1));
      }

      @Override
      public boolean isPcreLike() {
         return true;
      }
   }
}

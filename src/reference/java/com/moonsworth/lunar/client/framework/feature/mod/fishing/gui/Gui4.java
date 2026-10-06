package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.client.config.option.ListOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public final class Gui4 {
   public static final char field1 = '=';
   private static final Pattern field2 = Pattern.compile("^[a-z0-9_.-]+:[a-z0-9_./-]+$");
   private static final Pattern field3 = Pattern.compile("^[A-Z0-9_:;-]+$");

   public static @Nullable String method1(String var0) {
      String var1 = var0.trim().toLowerCase(Locale.ROOT);
      if (var1.isEmpty()) {
         return null;
      }

      if (var1.indexOf(58) < 0) {
         var1 = "minecraft:" + var1;
      }

      return field2.matcher(var1).matches() ? var1 : null;
   }

   public static boolean method2(String var0) {
      Bridge6_4 var1 = Bridge.method28().method22(var0);
      return var1 != null && var1 != Bridge.method28().method25();
   }

   public static @Nullable String method3(String var0) {
      String var1 = var0.trim().toUpperCase(Locale.ROOT);
      return field3.matcher(var1).matches() ? var1 : null;
   }

   public static String method4(String var0, String var1) {
      return var0 + "=" + var1;
   }

   public static Gui4.@Nullable Data2 method5(String var0) {
      int var1 = var0.indexOf(61);
      if (var1 < 0) {
         return null;
      }

      String var2 = method3(var0.substring(0, var1));
      String var3 = method1(var0.substring(var1 + 1));
      return var2 != null && var3 != null ? new Gui4.Data2(var2, var3) : null;
   }

   public static Map<String, String> method6(List<String> var0) {
      HashMap var1 = new HashMap();

      for (String var3 : var0) {
         int var4 = var3.indexOf(61);
         if (var4 > 0) {
            var1.put(var3.substring(0, var4), var3.substring(var4 + 1));
         }
      }

      return Map.copyOf(var1);
   }

   public static boolean method7(ListOption<String> var0, String var1) {
      String var2 = var1 + "=";

      for (String var4 : new ArrayList((Collection)var0.get())) {
         if (var4.startsWith(var2)) {
            return var0.remove(var4);
         }
      }

      return false;
   }

   @Generated
   private Gui4() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data2 {
      private final String field1;
      private final String field2;

      public Data2(String var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public String encode() {
         return Gui4.method4(this.field1, this.field2);
      }

      public String method1() {
         return this.field1;
      }

      public String itemId() {
         return this.field2;
      }
   }
}

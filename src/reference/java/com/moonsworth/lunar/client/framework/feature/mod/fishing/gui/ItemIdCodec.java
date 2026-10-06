package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
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

public final class ItemIdCodec {
   public static final char field1 = '=';
   private static final Pattern field2 = Pattern.compile("^[a-z0-9_.-]+:[a-z0-9_./-]+$");
   private static final Pattern field3 = Pattern.compile("^[A-Z0-9_:;-]+$");

   public static @Nullable String method1(String text0) {
      String text1 = text0.trim().toLowerCase(Locale.ROOT);
      if (text1.isEmpty()) {
         return null;
      }

      if (text1.indexOf(58) < 0) {
         text1 = "minecraft:" + text1;
      }

      return field2.matcher(text1).matches() ? text1 : null;
   }

   public static boolean method2(String text0) {
      ItemBridge bridge6_41 = Bridge.method28().method22(text0);
      return bridge6_41 != null && bridge6_41 != Bridge.method28().method25();
   }

   public static @Nullable String method3(String text0) {
      String text1 = text0.trim().toUpperCase(Locale.ROOT);
      return field3.matcher(text1).matches() ? text1 : null;
   }

   public static String method4(String text0, String text1) {
      return text0 + "=" + text1;
   }

   public static ItemIdCodec.@Nullable ItemIdPair method5(String text0) {
      int index1 = text0.indexOf(61);
      if (index1 < 0) {
         return null;
      }

      String text2 = method3(text0.substring(0, index1));
      String text3 = method1(text0.substring(index1 + 1));
      return text2 != null && text3 != null ? new ItemIdCodec.ItemIdPair(text2, text3) : null;
   }

   public static Map<String, String> method6(List<String> list0) {
      HashMap map1 = new HashMap();

      for (String text3 : list0) {
         int index4 = text3.indexOf(61);
         if (index4 > 0) {
            map1.put(text3.substring(0, index4), text3.substring(index4 + 1));
         }
      }

      return Map.copyOf(map1);
   }

   public static boolean method7(ListOption<String> lightingextension4990, String text1) {
      String text2 = text1 + "=";

      for (String text4 : new ArrayList((Collection)lightingextension4990.get())) {
         if (text4.startsWith(text2)) {
            return lightingextension4990.remove(text4);
         }
      }

      return false;
   }

   @Generated
   private ItemIdCodec() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class ItemIdPair {
      private final String field1;
      private final String field2;

      public ItemIdPair(String text1, String text2) {
         this.field1 = text1;
         this.field2 = text2;
      }

      public String encode() {
         return ItemIdCodec.method4(this.field1, this.field2);
      }

      public String method1() {
         return this.field1;
      }

      public String itemId() {
         return this.field2;
      }
   }
}

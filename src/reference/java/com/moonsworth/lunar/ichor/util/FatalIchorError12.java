package com.moonsworth.lunar.ichor.util;

import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FatalIchorError12 {
   private static final ThreadLocal<FatalIchorError12> field1 = ThreadLocal.withInitial(FatalIchorError12::new);
   private final Map<String, Deque<String>> field2 = new HashMap<>();

   public static FatalIchorError12 method1() {
      return field1.get();
   }

   public String method2(String var1) {
      String var2 = Strings.repeat("*", 70);
      Serializable var3 = "/" + var2 + "\\\n";
      String var4 = "\\" + var2 + "/\n";
      StringBuilder var5 = new StringBuilder();

      for (Iterator var6 = this.method3(var1).descendingIterator(); var6.hasNext(); var5.append(" *|\n")) {
         String var7 = "|* " + (String)var6.next();
         var5.append(var7);
         int var8 = var3.length() - (var7.length() + 2);
         if (var8 > 0) {
            var5.append(Strings.repeat(" ", var8 - 2));
         }
      }

      return var3 + var5 + var4;
   }

   public Deque<String> method3(String var1) {
      return this.field2.computeIfAbsent(var1, var0 -> new ArrayDeque<>());
   }

   public void method4(String var1, String var2) {
      this.method3(var1).push(var2);
   }

   public void method5(String var1, String var2) {
      this.method3(var1).removeFirstOccurrence(var2);
   }
}

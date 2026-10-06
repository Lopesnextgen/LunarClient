package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.genesis.Strings;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DebugStack {
   private static final ThreadLocal<DebugStack> field1 = ThreadLocal.withInitial(DebugStack::new);
   private final Map<String, Deque<String>> field2 = new HashMap<>();

   public static DebugStack method1() {
      return field1.get();
   }

   public DebugStack() {
   }

   public String method2(String text1) {
      String text2 = Strings.repeat("*", 70);
      Serializable serializable3 = "/" + text2 + "\\\n";
      String text4 = "\\" + text2 + "/\n";
      StringBuilder builder5 = new StringBuilder();

      for (Iterator iterator6 = this.method3(text1).descendingIterator(); iterator6.hasNext(); builder5.append(" *|\n")) {
         String text7 = "|* " + (String)iterator6.next();
         builder5.append(text7);
         int number8 = serializable3.length() - (text7.length() + 2);
         if (number8 > 0) {
            builder5.append(Strings.repeat(" ", number8 - 2));
         }
      }

      return serializable3 + builder5 + text4;
   }

   public Deque<String> method3(String text1) {
      return this.field2.computeIfAbsent(text1, arg0 -> new ArrayDeque<>());
   }

   public void method4(String text1, String text2) {
      this.method3(text1).push(text2);
   }

   public void method5(String text1, String text2) {
      this.method3(text1).removeFirstOccurrence(text2);
   }
}

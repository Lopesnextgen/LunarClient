package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.Set;
import java.util.regex.Pattern;

public class QuizKey {
   private final String field1;
   private final String field2;
   private final String field3;
   private final Pattern field4;
   private final Pattern field5;
   private final Set<ChatFilterRule> field6;

   public QuizKey(String text, String text2, String text3, Pattern pattern4, Pattern pattern5, Set<ChatFilterRule> set) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
      this.field4 = pattern4;
      this.field5 = pattern5;
      this.field6 = set;
   }

   public String method1() {
      return this.field1;
   }

   public String method2() {
      return this.field2;
   }

   public String method3() {
      return this.field3;
   }

   public Pattern method4() {
      return this.field4;
   }

   public Pattern method5() {
      return this.field5;
   }

   public Set<ChatFilterRule> method6() {
      return this.field6;
   }
}

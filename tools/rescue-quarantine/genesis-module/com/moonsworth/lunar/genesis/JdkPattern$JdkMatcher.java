package com.moonsworth.lunar.genesis;

import java.util.regex.Matcher;
import com.google.common.base.Preconditions;

final class JdkPattern$JdkMatcher extends CommonMatcher {
   final Matcher field1;

   JdkPattern$JdkMatcher(Matcher matcher1) {
      this.field1 = (Matcher)Preconditions.checkNotNull(matcher1);
   }

   public boolean matches() {
      return this.field1.matches();
   }

   public boolean find() {
      return this.field1.find();
   }

   public boolean find(int number1) {
      return this.field1.find(number1);
   }

   public String replaceAll(String text1) {
      return this.field1.replaceAll(text1);
   }

   public int end() {
      return this.field1.end();
   }

   public int start() {
      return this.field1.start();
   }
}

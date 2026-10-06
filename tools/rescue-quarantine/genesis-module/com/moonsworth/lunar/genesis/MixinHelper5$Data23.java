package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;

final class MixinHelper5$Data23 {
   final String field1;
   int position = 0;

   MixinHelper5$Data23(String var1) {
      this.field1 = var1;
   }

   String method1(CharMatcher var1) {
      Preconditions.checkState(this.hasMore());
      int var2 = this.position;
      this.position = var1.method21().indexIn(this.field1, var2);
      return this.hasMore() ? this.field1.substring(var2, this.position) : this.field1.substring(var2);
   }

   String method2(CharMatcher var1) {
      int var2 = this.position;
      String var3 = this.method1(var1);
      Preconditions.checkState(this.position != var2);
      return var3;
   }

   char method3(CharMatcher var1) {
      Preconditions.checkState(this.hasMore());
      char var2 = this.previewChar();
      Preconditions.checkState(var1.matches(var2));
      this.position++;
      return var2;
   }

   char consumeCharacter(char var1) {
      Preconditions.checkState(this.hasMore());
      Preconditions.checkState(this.previewChar() == var1);
      this.position++;
      return var1;
   }

   char previewChar() {
      Preconditions.checkState(this.hasMore());
      return this.field1.charAt(this.position);
   }

   boolean hasMore() {
      return this.position >= 0 && this.position < this.field1.length();
   }
}

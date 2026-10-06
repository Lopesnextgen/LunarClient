package com.moonsworth.lunar.genesis;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;

final class MediaType$Tokenizer {
   final String field1;
   int position = 0;

   MediaType$Tokenizer(String text1) {
      this.field1 = text1;
   }

   String method1(CharMatcher predicateextension21) {
      Preconditions.checkState(this.hasMore());
      int index2 = this.position;
      this.position = predicateextension21.method21().indexIn(this.field1, index2);
      return this.hasMore() ? this.field1.substring(index2, this.position) : this.field1.substring(index2);
   }

   String method2(CharMatcher predicateextension21) {
      int number2 = this.position;
      String text3 = this.method1(predicateextension21);
      Preconditions.checkState(this.position != number2);
      return text3;
   }

   char method3(CharMatcher predicateextension21) {
      Preconditions.checkState(this.hasMore());
      char character2 = this.previewChar();
      Preconditions.checkState(predicateextension21.matches(character2));
      this.position++;
      return character2;
   }

   char consumeCharacter(char character1) {
      Preconditions.checkState(this.hasMore());
      Preconditions.checkState(this.previewChar() == character1);
      this.position++;
      return character1;
   }

   char previewChar() {
      Preconditions.checkState(this.hasMore());
      return this.field1.charAt(this.position);
   }

   boolean hasMore() {
      return this.position >= 0 && this.position < this.field1.length();
   }
}

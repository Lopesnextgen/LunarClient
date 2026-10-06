package com.moonsworth.lunar.client.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandSuggestionCollector implements CommandSuggestionBuilder {
   private final String field1;
   private final int field2;
   private final List<String> field3;

   public CommandSuggestionCollector(String text1) {
      this(text1, 0, new ArrayList<>());
   }

   private CommandSuggestionCollector(String text1, int value, List<String> list) {
      this.field1 = text1;
      this.field2 = value;
      this.field3 = list;
   }

   @Override
   public String method1() {
      return this.field2 <= this.field1.length() ? this.field1.substring(this.field2) : "";
   }

   @Override
   public int getStart() {
      return this.field2;
   }

   @Override
   public CommandSuggestionBuilder method2(String text1) {
      if (text1.equals(this.method1())) {
         return this;
      }

      this.field3.add(text1);
      return this;
   }

   @Override
   public CommandSuggestionBuilder method3(int value) {
      return new CommandSuggestionCollector(this.field1, value, this.field3);
   }

   public List<String> method4() {
      return Collections.unmodifiableList(this.field3);
   }
}

package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.CommandArguments;
import java.time.Duration;

final class SuggestionCommandArguments implements CommandArguments {
   static final SuggestionCommandArguments field1 = new SuggestionCommandArguments();

   SuggestionCommandArguments() {
   }

   @Override
   public String getString(String text1) {
      throw method1(text1);
   }

   @Override
   public int getInteger(String text1) {
      throw method1(text1);
   }

   @Override
   public double getDouble(String text1) {
      throw method1(text1);
   }

   @Override
   public Duration getDuration(String text1) {
      throw method1(text1);
   }

   private static UnsupportedOperationException method1(String text) {
      return new UnsupportedOperationException(
         "Suggester tried to read argument '" + text + "', but no argument values are parsed during tab-complete suggestion gathering"
      );
   }
}

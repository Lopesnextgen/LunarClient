package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.CommandSuggestionProvider;
import com.moonsworth.lunar.client.util.text.StringCursor;
import org.jetbrains.annotations.Nullable;

public abstract class CommandArgumentParser<T> {
   public CommandArgumentParser() {
   }

   @Nullable
   public abstract T method1(StringCursor threadmoduledump271);

   public boolean method2() {
      return false;
   }

   @Nullable
   public CommandSuggestionProvider method3() {
      return null;
   }
}

package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.CommandSuggestionProvider;
import com.moonsworth.lunar.client.util.text.StringCursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ArgumentCommandNode extends CommandNode {
   private final String field3;
   private final CommandArgumentParser<?> field4;
   @Nullable
   private CommandSuggestionProvider field5;

   public ArgumentCommandNode(String text, CommandArgumentParser<?> mixincore2) {
      this.field3 = text;
      this.field4 = mixincore2;
   }

   public static ArgumentCommandNode method1(String text, CommandArgumentParser<?> mixincore1) {
      return new ArgumentCommandNode(text, mixincore1);
   }

   public ArgumentCommandNode method2(CommandSuggestionProvider nameplate2_21) {
      this.field5 = nameplate2_21;
      return this;
   }

   @Nullable
   public CommandSuggestionProvider method3() {
      return this.field5 != null ? this.field5 : this.field4.method3();
   }

   public ArgumentCommandNode method4(String... items1) {
      return this.method5(Arrays.asList(items1));
   }

   public ArgumentCommandNode method5(Iterable<String> iterable) {
      return this.method2((arg1x, arg2) -> {
         String text3 = arg2.method1().toLowerCase(Locale.ROOT);

         for (String text5 : iterable) {
            if (text5.toLowerCase(Locale.ROOT).startsWith(text3)) {
               arg2.method2(text5);
            }
         }
      });
   }

   public ArgumentCommandNode method6(Enum<?>[] items1) {
      ArrayList list2 = new ArrayList(items1.length);

      for (Enum value6 : items1) {
         list2.add(value6.name());
      }

      return this.method5(list2);
   }

   public ArgumentCommandNode method7(CommandNode mixinnameplate1) {
      super.method1(mixinnameplate1);
      return this;
   }

   public ArgumentCommandNode method8(CommandExecutor mixinhelper1) {
      super.method2(mixinhelper1);
      return this;
   }

   @Override
   public boolean method3(StringCursor threadmoduledump271, BoundArguments mixinhelper222) {
      Object obj3 = this.field4.method1(threadmoduledump271);
      if (obj3 == null) {
         return false;
      }

      mixinhelper222.method1(this.field3, obj3);
      return true;
   }

   @Generated
   public String getName() {
      return this.field3;
   }

   @Generated
   public CommandArgumentParser<?> method10() {
      return this.field4;
   }
}

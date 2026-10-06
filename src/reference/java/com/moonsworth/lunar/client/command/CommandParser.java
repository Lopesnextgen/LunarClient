package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.text.StringCursor;

public class CommandParser {
   public CommandParser() {
   }

   public static boolean method1(LiteralCommandNode literalCommandNode, String text) {
      StringCursor threadmoduledump272 = new StringCursor(text.startsWith("/") ? text.substring(1) : text);
      BoundArguments mixinhelper223 = new BoundArguments();
      return method2(literalCommandNode, threadmoduledump272, mixinhelper223);
   }

   private static boolean method2(CommandNode mixinnameplate0, StringCursor threadmoduledump271, BoundArguments mixinhelper222) {
      int number3 = threadmoduledump271.getCursor();
      if (!mixinnameplate0.method3(threadmoduledump271, mixinhelper222)) {
         threadmoduledump271.setCursor(number3);
         return false;
      }

      if (threadmoduledump271.canRead() && threadmoduledump271.peek() == ' ') {
         int number4 = threadmoduledump271.getCursor();
         threadmoduledump271.skipWhitespace();
         int number5 = threadmoduledump271.getCursor();

         for (CommandNode mixinnameplate7 : mixinnameplate0.getChildren()) {
            if (method2(mixinnameplate7, threadmoduledump271, mixinhelper222)) {
               return true;
            }

            threadmoduledump271.setCursor(number5);
         }

         threadmoduledump271.setCursor(number4);
      }

      if (!threadmoduledump271.canRead() && mixinnameplate0.method4() != null) {
         mixinnameplate0.method4().execute(mixinhelper222);
         return true;
      } else {
         return false;
      }
   }
}

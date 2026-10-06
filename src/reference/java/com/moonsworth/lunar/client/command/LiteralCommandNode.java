package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.text.StringCursor;
import lombok.Generated;

public class LiteralCommandNode extends CommandNode {
   private final String field3;

   public LiteralCommandNode(String text1) {
      this.field3 = text1;
   }

   public static LiteralCommandNode method1(String text0) {
      return new LiteralCommandNode(text0);
   }

   public LiteralCommandNode method2(CommandNode mixinnameplate1) {
      super.method1(mixinnameplate1);
      return this;
   }

   public LiteralCommandNode method3(CommandExecutor mixinhelper1) {
      super.method2(mixinhelper1);
      return this;
   }

   @Override
   public boolean method3(StringCursor threadmoduledump271, BoundArguments mixinhelper222) {
      return this.field3.equals(StringArgumentParser.field1.method2(threadmoduledump271));
   }

   @Generated
   public String method5() {
      return this.field3;
   }
}

package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.CommandCompleter;
import java.util.List;
import lombok.Generated;

public class ClientCommand {
   private final LiteralCommandNode field1;

   public ClientCommand(LiteralCommandNode literalCommandNode) {
      this.field1 = literalCommandNode;
   }

   public String getName() {
      return this.field1.method5();
   }

   public String method1() {
      return "/" + this.getName();
   }

   public boolean isEnabled() {
      return true;
   }

   public boolean method2(String text1) {
      return CommandParser.method1(this.field1, text1);
   }

   public List<String> method3(String text1) {
      return CommandCompleter.method4(this.field1, text1);
   }

   public boolean method4() {
      return true;
   }

   @Generated
   public LiteralCommandNode method5() {
      return this.field1;
   }
}

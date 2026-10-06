package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.text.StringCursor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public abstract class CommandNode {
   private final List<CommandNode> field1 = new ArrayList<>();
   @Nullable
   private CommandExecutor field2;

   public CommandNode() {
   }

   public CommandNode method1(CommandNode mixinnameplate1) {
      this.field1.add(mixinnameplate1);
      return this;
   }

   public CommandNode method2(CommandExecutor mixinhelper1) {
      this.field2 = mixinhelper1;
      return this;
   }

   public List<CommandNode> getChildren() {
      return Collections.unmodifiableList(this.field1);
   }

   public abstract boolean method3(StringCursor threadmoduledump271, BoundArguments mixinhelper222);

   @Nullable
   @Generated
   public CommandExecutor method4() {
      return this.field2;
   }
}

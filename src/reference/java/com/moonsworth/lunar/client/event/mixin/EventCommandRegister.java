package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.event.LunarEvent;
import java.util.List;

public class EventCommandRegister extends LunarEvent {
   private final List<ClientCommand> field1;

   public EventCommandRegister(List<ClientCommand> list) {
      this.field1 = list;
   }

   public void method1(ClientCommand mixinnameplate21) {
      this.field1.add(mixinnameplate21);
   }
}

package com.moonsworth.lunar.client.highlight;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class HighlightImpl_3 extends LunarEvent {
   private final Any field1;

   public <T extends Message> Optional<T> unpack(Class<T> clazz1) {
      if (!this.field1.is(clazz1)) {
         return Optional.empty();
      }

      try {
         Message message2 = this.field1.unpack(clazz1);
         Ref.method4().method84().method13(message2.getClass());
         return Optional.of((T)message2);
      } catch (Exception exception3) {
         return Optional.empty();
      }
   }

   @Generated
   public Any getPacket() {
      return this.field1;
   }

   @Generated
   public HighlightImpl_3(Any any1) {
      this.field1 = any1;
   }
}

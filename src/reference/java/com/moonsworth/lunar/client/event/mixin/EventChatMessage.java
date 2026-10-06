package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EventChatMessage extends com.moonsworth.lunar.client.event.CancellableEvent {
   @NonNull
   private Component field1;
   @NonNull
   private final Component field2;
   @NotNull
   private String field3;
   @NonNull
   private final String field4;
   @NonNull
   private final String field5;
   public boolean field6 = false;
   private boolean field7 = false;
   private boolean changed = false;

   private EventChatMessage(@NotNull Component component1) {
      this.field1 = component1;
      this.field2 = component1;
      this.field3 = PlainTextComponentSerializer.plainText().serialize(component1);
      this.field4 = this.field3;
      this.field5 = TextBridge.getTextContent(component1);
   }

   public void method1(@NotNull Component component1) {
      this.changed = true;
      this.field1 = component1;
      this.field3 = PlainTextComponentSerializer.plainText().serialize(component1);
   }

   @NonNull
   @Generated
   public Component method2() {
      return this.field1;
   }

   @NonNull
   @Generated
   public Component method3() {
      return this.field2;
   }

   @NotNull
   @Generated
   public String method4() {
      return this.field3;
   }

   @NonNull
   @Generated
   public String method5() {
      return this.field4;
   }

   @NonNull
   @Generated
   public String method6() {
      return this.field5;
   }

   @Generated
   public boolean method7() {
      return this.field6;
   }

   @Generated
   public boolean method8() {
      return this.field7;
   }

   @Generated
   public boolean isChanged() {
      return this.changed;
   }

   @Generated
   public void method9(boolean flag) {
      this.field7 = flag;
   }

   public static class TypedChatMessage extends EventChatMessage {
      private final int field8;

      public TypedChatMessage(@NotNull Component component1, int number2) {
         super(component1);
         this.field8 = number2;
      }

      @Generated
      public int method1() {
         return this.field8;
      }
   }

   public static class EventActionBarMessage extends EventChatMessage {
      public EventActionBarMessage(@NotNull Component component1) {
         super(component1);
      }
   }

   public static class EventTypedMessage extends LunarEvent {
      @Nullable
      private final Component field1;
      @Nullable
      private final String field2;
      private final int field3;

      public EventTypedMessage(@Nullable Component component1, int number2) {
         this.field1 = component1;
         this.field2 = component1 == null ? null : PlainTextComponentSerializer.plainText().serialize(component1);
         this.field3 = number2;
      }

      @Nullable
      @Generated
      public Component getComponent() {
         return this.field1;
      }

      @Nullable
      @Generated
      public String method1() {
         return this.field2;
      }

      @Generated
      public int getType() {
         return this.field3;
      }
   }
}

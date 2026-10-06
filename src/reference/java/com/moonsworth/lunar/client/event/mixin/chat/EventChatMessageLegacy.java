package com.moonsworth.lunar.client.event.mixin.chat;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EventChatMessageLegacy extends com.moonsworth.lunar.client.highlight.HighlightImpl {
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

   private EventChatMessageLegacy(@NotNull Component var1) {
      this.field1 = var1;
      this.field2 = var1;
      this.field3 = PlainTextComponentSerializer.plainText().serialize(var1);
      this.field4 = this.field3;
      this.field5 = AdventureTextBridge.getTextContent(var1);
   }

   public void method1(@NotNull Component var1) {
      this.changed = true;
      this.field1 = var1;
      this.field3 = PlainTextComponentSerializer.plainText().serialize(var1);
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
   public void method9(boolean var1) {
      this.field7 = var1;
   }

   public static class Data extends EventChatMessageLegacy {
      private final int field8;

      public Data(@NotNull Component var1, int var2) {
         super(var1);
         this.field8 = var2;
      }

      @Generated
      public int method1() {
         return this.field8;
      }
   }

   public static class Data2 extends EventChatMessageLegacy {
      public Data2(@NotNull Component var1) {
         super(var1);
      }
   }

   public static class Data3 extends Highlight {
      @Nullable
      private final Component field1;
      @Nullable
      private final String field2;
      private final int field3;

      public Data3(@Nullable Component var1, int var2) {
         this.field1 = var1;
         this.field2 = var1 == null ? null : PlainTextComponentSerializer.plainText().serialize(var1);
         this.field3 = var2;
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

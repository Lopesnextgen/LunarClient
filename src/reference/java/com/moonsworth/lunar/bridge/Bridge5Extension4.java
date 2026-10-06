package com.moonsworth.lunar.bridge;

import java.util.Optional;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public interface Bridge5Extension4 extends Bridge5_15 {
   void bridge$addMessage(Bridge2_42 var1);

   void bridge$addMessageWithLunarId(Bridge2_42 var1, int var2, boolean var3);

   int bridge$addMessageWithLunarId(Bridge2_42 var1);

   void bridge$deleteMessageByLunarId(int var1, boolean var2);

   default void method1(int var1) {
      this.bridge$deleteMessageByLunarId(var1, false);
   }

   @Nullable
   Component bridge$getMessageContentByLunarId(int var1);

   boolean bridge$getChatOpen();

   int bridge$getLunarId();

   void bridge$clearChatMessages();

   void bridge$rescaleChat();

   boolean bridge$messageAddedByApollo();

   @Override
   default Optional<String> method1() {
      return Optional.of("New Chat");
   }
}

package com.moonsworth.lunar.bridge;

import java.util.Optional;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public interface GuiNewChatBridge extends GuiBridge {
   void bridge$addMessage(Bridge2_42 bridge2_421);

   void bridge$addMessageWithLunarId(Bridge2_42 bridge2_421, int number2, boolean flag3);

   int bridge$addMessageWithLunarId(Bridge2_42 bridge2_421);

   void bridge$deleteMessageByLunarId(int number1, boolean flag2);

   default void method1(int number1) {
      this.bridge$deleteMessageByLunarId(number1, false);
   }

   @Nullable
   Component bridge$getMessageContentByLunarId(int number1);

   boolean bridge$getChatOpen();

   int bridge$getLunarId();

   void bridge$clearChatMessages();

   void bridge$rescaleChat();

   boolean bridge$messageAddedByApollo();

   default Optional<String> method1() {
      return Optional.of("New Chat");
   }
}

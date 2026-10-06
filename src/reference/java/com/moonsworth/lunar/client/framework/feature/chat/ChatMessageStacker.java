package com.moonsworth.lunar.client.framework.feature.chat;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.MapRemoval;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

class ChatMessageStacker {
   private final Map<Component, ChatMessageStacker.Data> field1 = new HashMap<>();
   private Component field2;
   private int field3;

   ChatMessageStacker() {
   }

   public void clear() {
      this.field2 = null;
      this.field3 = 0;
      this.field1.clear();
   }

   protected void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat chat2 = Ref.method4().method40().method47();
      Component component3 = data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI();
      int number4 = data1.method1();
      long number5 = (Integer)chat2.method34().get() * 1000;
      MapRemoval.method2(this.field1, arg2x -> arg2x.method2(number5));
      if ((Boolean)chat2.method35().get() || (Boolean)chat2.method36().get()) {
         String text7 = TextBridge.getTextContent(component3);
         if ((Boolean)chat2.method35().get() && text7.isEmpty()) {
            return;
         }

         if ((Boolean)chat2.method36().get() && this.method2(text7)) {
            return;
         }
      }

      ChatMessageStacker.Data data10 = this.field1.get(component3);
      if (component3.equals(this.field2)) {
         int number11;
         if (data10 != null) {
            this.field3 = number11 = data10.method1(number4);
         } else {
            number11 = ++this.field3;
         }

         Component component12 = component3.append(Component.text(" [x" + number11 + "]").style(Style.empty().color(NamedTextColor.GRAY)));
         data1.OHROCHICOIOICHOCRROORRCIIICIHO(component12);
         data1.OOOHICCHHHRHCORIRCRHOCROROIOCR(true);
      } else {
         this.field2 = component3;
         this.field3 = 1;
         if ((Boolean)chat2.method30().get()) {
            if (data10 != null) {
               Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().method1(data10.field1);
               int number8 = data10.method1(number4) + (this.field3 - 1);
               Component component9 = component3.append(Component.text(" [x" + number8 + "]").style(Style.empty().color(NamedTextColor.GRAY)));
               data1.OHROCHICOIOICHOCRROORRCIIICIHO(component9);
               this.field1.put(component3, data10);
            } else {
               this.field1.put(component3, new ChatMessageStacker.Data(number4));
            }
         }
      }
   }

   private boolean method2(String text1) {
      char character2 = ' ';

      for (char character6 : text1.toCharArray()) {
         if (character2 == ' ') {
            if (character6 != '-' && character6 != 9644) {
               return false;
            }

            character2 = character6;
         }

         if (character6 != character2) {
            return false;
         }
      }

      return true;
   }

   private static class Data {
      private int stackSize;
      private long timestamp = System.currentTimeMillis();
      private int field1;

      public Data(int number1) {
         this.field1 = number1;
         this.stackSize = 1;
      }

      public int method1(int number1) {
         this.timestamp = System.currentTimeMillis();
         this.field1 = number1;
         return ++this.stackSize;
      }

      public boolean method2(long number1) {
         return System.currentTimeMillis() > this.timestamp + number1;
      }
   }
}

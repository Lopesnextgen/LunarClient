package com.moonsworth.lunar.client.framework.feature.chat;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump78;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

class Chat3 {
   private final Map<Component, Chat3.Data> field1 = new HashMap<>();
   private Component field2;
   private int field3;

   public void clear() {
      this.field2 = null;
      this.field3 = 0;
      this.field1.clear();
   }

   protected void method1(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat var2 = ThreadModuleDump63.method4().method40().method47();
      Component var3 = var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI();
      int var4 = var1.method1();
      long var5 = (Integer)var2.method34().get() * 1000;
      ThreadModuleDump78.removeIfValue(this.field1, var2x -> var2x.method2(var5));
      if ((Boolean)var2.method35().get() || (Boolean)var2.method36().get()) {
         String var7 = AdventureTextBridge.getTextContent(var3);
         if ((Boolean)var2.method35().get() && var7.isEmpty()) {
            return;
         }

         if ((Boolean)var2.method36().get() && this.method2(var7)) {
            return;
         }
      }

      Chat3.Data var10 = this.field1.get(var3);
      if (var3.equals(this.field2)) {
         int var11;
         if (var10 != null) {
            this.field3 = var11 = var10.method1(var4);
         } else {
            var11 = ++this.field3;
         }

         Component var12 = var3.append(Component.text(" [x" + var11 + "]").style(Style.empty().color(NamedTextColor.GRAY)));
         var1.OHROCHICOIOICHOCRROORRCIIICIHO(var12);
         var1.method9(true);
      } else {
         this.field2 = var3;
         this.field3 = 1;
         if ((Boolean)var2.method30().get()) {
            if (var10 != null) {
               ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().method1(var10.field1);
               int var8 = var10.method1(var4) + (this.field3 - 1);
               Component var9 = var3.append(Component.text(" [x" + var8 + "]").style(Style.empty().color(NamedTextColor.GRAY)));
               var1.OHROCHICOIOICHOCRROORRCIIICIHO(var9);
               this.field1.put(var3, var10);
            } else {
               this.field1.put(var3, new Chat3.Data(var4));
            }
         }
      }
   }

   private boolean method2(String var1) {
      char var2 = ' ';

      for (char var6 : var1.toCharArray()) {
         if (var2 == ' ') {
            if (var6 != '-' && var6 != 9644) {
               return false;
            }

            var2 = var6;
         }

         if (var6 != var2) {
            return false;
         }
      }

      return true;
   }

   private static class Data {
      private int stackSize;
      private long timestamp = System.currentTimeMillis();
      private int field1;

      public Data(int var1) {
         this.field1 = var1;
         this.stackSize = 1;
      }

      public int method1(int var1) {
         this.timestamp = System.currentTimeMillis();
         this.field1 = var1;
         return ++this.stackSize;
      }

      public boolean method2(long var1) {
         return System.currentTimeMillis() > this.timestamp + var1;
      }
   }
}

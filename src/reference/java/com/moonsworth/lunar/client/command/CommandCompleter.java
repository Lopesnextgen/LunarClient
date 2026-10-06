package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.CommandNode;
import com.moonsworth.lunar.client.command.ClientCommand;
import com.moonsworth.lunar.client.command.LiteralCommandNode;
import com.moonsworth.lunar.client.command.ArgumentCommandNode;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventCommandRegister;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class CommandCompleter {
   public CommandCompleter() {
   }

   public static List<String> method1(String text0) {
      return text0 != null && text0.startsWith("/") && text0.indexOf(32) >= 0 ? method3(text0) : Collections.emptyList();
   }

   public static String[] method2(String[] items0, String text1) {
      if (text1 != null && text1.startsWith("/")) {
         LinkedHashSet set2 = new LinkedHashSet(items0.length);
         set2.addAll(Arrays.asList(items0));
         return set2.addAll(method3(text1)) ? set2.toArray(new String[0]) : items0;
      } else {
         return items0;
      }
   }

   private static List<String> method3(String text0) {
      ArrayList list1 = new ArrayList();
      LunarEventBus.method29().method12(EventCommandRegister.class, () -> new EventCommandRegister(list1));
      ArrayList list2 = new ArrayList();

      for (ClientCommand mixinnameplate24 : list1) {
         if (mixinnameplate24.isEnabled()) {
            list2.addAll(mixinnameplate24.method3(text0));
         }
      }

      return list2;
   }

   public static List<String> method4(LiteralCommandNode mixinnameplateimpl0, String text1) {
      boolean flag2 = text1.startsWith("/");
      String text3 = flag2 ? text1.substring(1) : text1;
      String text4 = mixinnameplateimpl0.method5();
      int index5 = text3.indexOf(32);
      if (index5 < 0) {
         return text4.startsWith(text3) && !text4.equals(text3) ? List.of(flag2 ? "/" + text4 : text4) : Collections.emptyList();
      }

      if (!text3.substring(0, index5).equals(text4)) {
         return Collections.emptyList();
      }

      String text6 = text3.substring(index5 + 1);
      return method5(mixinnameplateimpl0, text6);
   }

   private static List<String> method5(CommandNode mixinnameplate0, String text1) {
      int index2 = text1.indexOf(32);
      if (index2 < 0) {
         ArrayList list10 = new ArrayList();

         for (CommandNode mixinnameplate12 : mixinnameplate0.getChildren()) {
            if (mixinnameplate12 instanceof LiteralCommandNode mixinnameplateimpl13) {
               if (mixinnameplateimpl13.method5().startsWith(text1)) {
                  list10.add(mixinnameplateimpl13.method5());
               }
            } else if (mixinnameplate12 instanceof ArgumentCommandNode mixinnameplateiterator15) {
               CommandSuggestionProvider nameplate2_216 = mixinnameplateiterator15.method3();
               if (nameplate2_216 != null) {
                  CommandSuggestionCollector nameplate3handler17 = new CommandSuggestionCollector(text1);
                  nameplate2_216.provide(SuggestionCommandArguments.field1, nameplate3handler17);
                  list10.addAll(nameplate3handler17.method4());
               }
            }
         }

         return list10;
      } else {
         String text3 = text1.substring(0, index2);
         String text4 = text1.substring(index2 + 1);

         for (CommandNode mixinnameplate6 : mixinnameplate0.getChildren()) {
            if (mixinnameplate6 instanceof LiteralCommandNode mixinnameplateimpl7) {
               if (mixinnameplateimpl7.method5().equals(text3)) {
                  return method5(mixinnameplate6, text4);
               }
            } else if (mixinnameplate6 instanceof ArgumentCommandNode mixinnameplateiterator14) {
               if (mixinnameplateiterator14.method10().method2()) {
                  CommandSuggestionProvider nameplate2_28 = mixinnameplateiterator14.method3();
                  if (nameplate2_28 == null) {
                     return Collections.emptyList();
                  }

                  CommandSuggestionCollector nameplate3handler9 = new CommandSuggestionCollector(text1);
                  nameplate2_28.provide(SuggestionCommandArguments.field1, nameplate3handler9);
                  return new ArrayList<>(nameplate3handler9.method4());
               }

               return method5(mixinnameplate6, text4);
            }
         }

         return Collections.emptyList();
      }
   }
}

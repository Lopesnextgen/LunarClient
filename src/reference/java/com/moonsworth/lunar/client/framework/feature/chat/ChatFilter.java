package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

class ChatFilter {
   @Nullable
   private List<ComponentTransformer> field1 = null;
   private Pattern field2 = Pattern.compile("");

   ChatFilter() {
   }

   protected boolean method1(EventChatMessage highlightimpl1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat chat2 = Ref.method4().method40().method47();
      boolean flag3 = chat2.method41().get() != com.moonsworth.lunar.client.config.option.NamedColorOption.OFF;
      boolean flag4 = (Boolean)chat2.method42().get();
      boolean flag5 = (Boolean)chat2.method43().get();
      boolean flag6 = (Boolean)chat2.method44().get();
      boolean flag7 = (Boolean)chat2.method45().get();
      boolean flag8 = (Boolean)chat2.method46().get();
      if (flag3 || flag4 || flag5 || flag6 || flag7 || flag8) {
         NamedTextColor namedtextcolor9 = ((com.moonsworth.lunar.client.config.option.NamedColorOption)chat2.method41().get()).getColor().getAdventureColor();
         Component component10 = highlightimpl1.method2();
         String text11 = Ref.method7().bridge$getName();
         Builder builder12 = (Builder)Component.text().content(text11).color(namedtextcolor9);
         if (flag4) {
            builder12.decorate(TextDecoration.BOLD);
         }

         if (flag5) {
            builder12.decorate(TextDecoration.ITALIC);
         }

         if (flag6) {
            builder12.decorate(TextDecoration.UNDERLINED);
         }

         if (flag7) {
            builder12.decorate(TextDecoration.STRIKETHROUGH);
         }

         if (flag8) {
            builder12.decorate(TextDecoration.OBFUSCATED);
         }

         Component component13 = ComponentTransformer.replaceLiteral(text11, builder12.build()).transform(component10);
         if (!component13.equals(component10)) {
            highlightimpl1.method1(component13);
         }
      }

      Component component14 = this.method2(highlightimpl1.method2(), highlightimpl1);
      if (component14 != null) {
         highlightimpl1.method1(component14);
      }

      if (highlightimpl1.method7() && (Boolean)chat2.method48().get()) {
         highlightimpl1.setCancelled(true);
         return true;
      } else {
         return false;
      }
   }

   @Nullable
   private Component method2(Component component1, EventChatMessage highlightimpl2) {
      if (this.field1 == null) {
         return null;
      }

      Component component3 = component1;

      for (ComponentTransformer componenttransformer5 : this.field1) {
         component3 = componenttransformer5.transform(component3);
      }

      if (component1.equals(component3)) {
         return null;
      }

      highlightimpl2.field6 = true;
      return component3;
   }

   @Contract("null -> null; !null -> !null")
   private List<Pattern> method3(@Nullable List<Pattern> list1) {
      if (list1 == null) {
         return null;
      }

      ArrayList list2 = new ArrayList();

      for (Pattern pattern4 : list1) {
         if (!pattern4.toString().isEmpty() && !pattern4.toString().contains("()")) {
            list2.add(pattern4);
         }
      }

      return list2;
   }

   protected void method4(ProfanityFilterMode gui2extension1) {
      List list2 = this.method3(switch (gui2extension1) {
         case NORMAL -> {
            ArrayList list7 = Lists.newArrayList(Ref.method4().method71().method1());
            Pattern pattern9 = Ref.method4().method71().method3();
            if (pattern9 != null) {
               list7.add(pattern9);
            }

            list7.add(this.field2);
            yield yield7;
         }
         case HIGH -> {
            ArrayList list3 = Lists.newArrayList(Ref.method4().method71().method2());
            Pattern pattern4 = Ref.method4().method71().method4();
            if (pattern4 != null) {
               list3.add(pattern4);
            }

            list3.add(this.field2);
            yield yield3;
         }
         case CUSTOM -> List.of(this.field2);
         case OFF -> null;
      });
      if (list2 == null) {
         this.field1 = null;
      } else {
         ComponentTransform componenttransform8 = ComponentTransform.builder().textFunctor(arg0 -> Strings.repeat("*", arg0.length())).build();
         com.google.common.collect.ImmutableList.Builder builder10 = new com.google.common.collect.ImmutableList.Builder();

         for (Pattern pattern6 : list2) {
            builder10.add(ComponentTransformer.of(ComponentPattern.pattern(pattern6), componenttransform8));
         }

         this.field1 = builder10.build();
      }
   }

   protected void method5(File file1) {
      try {
         if (!file1.exists()) {
            file1.createNewFile();
         }

         String text2 = "(";
         String text3 = ")";
         this.field2 = Pattern.compile(text2 + this.method6(Files.readAllLines(file1.toPath())) + text3, 2);
      } catch (IOException exception4) {
         CrashReporter.method5(exception4, "Loading ChatMod");
      }

      com.moonsworth.lunar.client.mod.misc.chat.Chat chat5 = Ref.method4().method40().method47();
      this.method4((ProfanityFilterMode)chat5.method47().get());
   }

   private String method6(List<String> list1) {
      StringBuilder builder2 = new StringBuilder();

      for (int index3 = 0; index3 < list1.size(); index3++) {
         String text4 = (String)list1.get(index3);
         if (!text4.isBlank()) {
            builder2.append('(');
            builder2.append(Pattern.quote(text4));
            builder2.append(")");
            if (index3 != list1.size() - 1) {
               builder2.append('|');
            }
         }
      }

      return builder2.toString();
   }
}

package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

class Chat4 {
   @Nullable
   private List<ComponentTransformer> field1 = null;
   private Pattern field2 = Pattern.compile("");

   protected boolean method1(EventChatMessageLegacy var1) {
      com.moonsworth.lunar.client.mod.misc.chat.Chat var2 = ThreadModuleDump63.method4().method40().method47();
      boolean var3 = var2.method41().get() != com.moonsworth.lunar.client.config.option.NamedColorOption.OFF;
      boolean var4 = (Boolean)var2.method42().get();
      boolean var5 = (Boolean)var2.method43().get();
      boolean var6 = (Boolean)var2.method44().get();
      boolean var7 = (Boolean)var2.method45().get();
      boolean var8 = (Boolean)var2.method46().get();
      if (var3 || var4 || var5 || var6 || var7 || var8) {
         NamedTextColor var9 = ((com.moonsworth.lunar.client.config.option.NamedColorOption)var2.method41().get()).getColor().getAdventureColor();
         Component var10 = var1.method2();
         String var11 = ThreadModuleDump63.method7().bridge$getName();
         Builder var12 = (Builder)Component.text().content(var11).color(var9);
         if (var4) {
            var12.decorate(TextDecoration.BOLD);
         }

         if (var5) {
            var12.decorate(TextDecoration.ITALIC);
         }

         if (var6) {
            var12.decorate(TextDecoration.UNDERLINED);
         }

         if (var7) {
            var12.decorate(TextDecoration.STRIKETHROUGH);
         }

         if (var8) {
            var12.decorate(TextDecoration.OBFUSCATED);
         }

         Component var13 = ComponentTransformer.replaceLiteral(var11, var12.build()).transform(var10);
         if (!var13.equals(var10)) {
            var1.method1(var13);
         }
      }

      Component var14 = this.method2(var1.method2(), var1);
      if (var14 != null) {
         var1.method1(var14);
      }

      if (var1.method7() && (Boolean)var2.method48().get()) {
         var1.setCancelled(true);
         return true;
      } else {
         return false;
      }
   }

   @Nullable
   private Component method2(Component var1, EventChatMessageLegacy var2) {
      if (this.field1 == null) {
         return null;
      }

      Component var3 = var1;

      for (ComponentTransformer var5 : this.field1) {
         var3 = var5.transform(var3);
      }

      if (var1.equals(var3)) {
         return null;
      }

      var2.field6 = true;
      return var3;
   }

   @Contract("null -> null; !null -> !null")
   private List<Pattern> method3(@Nullable List<Pattern> var1) {
      if (var1 == null) {
         return null;
      }

      ArrayList var2 = new ArrayList();

      for (Pattern var4 : var1) {
         if (!var4.toString().isEmpty() && !var4.toString().contains("()")) {
            var2.add(var4);
         }
      }

      return var2;
   }

   protected void method4(Gui2Extension var1) {
      List var2 = this.method3(switch (var1) {
         case NORMAL -> {
            ArrayList var7 = Lists.newArrayList(ThreadModuleDump63.method4().method71().method1());
            Pattern var9 = ThreadModuleDump63.method4().method71().method3();
            if (var9 != null) {
               var7.add(var9);
            }

            var7.add(this.field2);
            yield var7;
         }
         case HIGH -> {
            ArrayList var3 = Lists.newArrayList(ThreadModuleDump63.method4().method71().method2());
            Pattern var4 = ThreadModuleDump63.method4().method71().method4();
            if (var4 != null) {
               var3.add(var4);
            }

            var3.add(this.field2);
            yield var3;
         }
         case CUSTOM -> List.of(this.field2);
         case OFF -> null;
      });
      if (var2 == null) {
         this.field1 = null;
      } else {
         ComponentTransform var8 = ComponentTransform.builder().textFunctor(var0 -> Strings.repeat("*", var0.length())).build();
         com.google.common.collect.ImmutableList.Builder var10 = new com.google.common.collect.ImmutableList.Builder();

         for (Pattern var6 : var2) {
            var10.add(ComponentTransformer.of(ComponentPattern.pattern(var6), var8));
         }

         this.field1 = var10.build();
      }
   }

   protected void method5(File var1) {
      try {
         if (!var1.exists()) {
            var1.createNewFile();
         }

         String var2 = "(";
         String var3 = ")";
         this.field2 = Pattern.compile(var2 + this.method6(Files.readAllLines(var1.toPath())) + var3, 2);
      } catch (IOException var4) {
         Inventorymod2.method5(var4, "Loading ChatMod");
      }

      com.moonsworth.lunar.client.mod.misc.chat.Chat var5 = ThreadModuleDump63.method4().method40().method47();
      this.method4((Gui2Extension)var5.method47().get());
   }

   private String method6(List<String> var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var1.size(); var3++) {
         String var4 = (String)var1.get(var3);
         if (!var4.isBlank()) {
            var2.append('(');
            var2.append(Pattern.quote(var4));
            var2.append(")");
            if (var3 != var1.size() - 1) {
               var2.append('|');
            }
         }
      }

      return var2.toString();
   }
}

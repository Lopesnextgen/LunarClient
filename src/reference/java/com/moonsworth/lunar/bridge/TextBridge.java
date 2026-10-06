package com.moonsworth.lunar.bridge;

import com.google.common.base.Strings;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.TextDecoration.State;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.mutable.MutableInt;

@com.moonsworth.lunar.ichor.util.KeepName
public class TextBridge {
   private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");

   public TextBridge() {
   }

   public static String stripColor(String text0) {
      return STRIP_COLOR_PATTERN.matcher(text0).replaceAll("");
   }

   public static String getTextContentForRendering(Component component0) {
      StringBuilder builder1 = new StringBuilder();
      if (component0.decoration(TextDecoration.OBFUSCATED) == State.TRUE) {
         builder1.append("§k");
      }

      if (component0.decoration(TextDecoration.BOLD) == State.TRUE) {
         builder1.append("§l");
      }

      if (component0.decoration(TextDecoration.STRIKETHROUGH) == State.TRUE) {
         builder1.append("§m");
      }

      if (component0.decoration(TextDecoration.UNDERLINED) == State.TRUE) {
         builder1.append("§n");
      }

      if (component0.decoration(TextDecoration.ITALIC) == State.TRUE) {
         builder1.append("§o");
      }

      return builder1.isEmpty() ? asLegacyString(component0) : builder1.append(asLegacyString(component0)).append("§r").toString();
   }

   public static String getTextContent(@Nullable Component component0) {
      StringBuilder builder1 = new StringBuilder();
      if (component0 instanceof TextComponent text2) {
         builder1.append(text2.content());
      } else if (component0 instanceof TranslatableComponent) {
         return STRIP_COLOR_PATTERN.matcher(Bridge.method57().method1(asBridge(component0))).replaceAll("");
      }

      if (component0 != null) {
         for (Component component3 : component0.children()) {
            builder1.append(getTextContent(component3));
         }
      }

      return builder1.toString();
   }

   public static Component asAdventure(Bridge2_42 bridge2_420) {
      return (Component)(bridge2_420 instanceof ChatComponentStyleBridge bridge3_251 ? bridge3_251.moonBridge$asAdventureComponent() : new ComponentAdapterBridge(bridge2_420));
   }

   public static Component asAdventureCached(Bridge2_42 bridge2_420) {
      return (Component)(bridge2_420 instanceof ChatComponentStyleBridge bridge3_251 ? new DelegatingComponentBridge(bridge3_251.moonBridge$asAdventureComponent()) : new ComponentAdapterBridge(bridge2_420));
   }

   public static Style asAdventureStyle(VanillaStyleBridge bridge_310) {
      return ((ChatStyleBridge)bridge_310).moonBridge$asAdventureStyle();
   }

   public static Optional<TextComponent> asAdventureText(Bridge2_42 bridge2_420) {
      Component component1 = ((ChatComponentStyleBridge)bridge2_420).moonBridge$asAdventureComponent();
      return component1 instanceof TextComponent ? Optional.of((TextComponent)component1) : Optional.empty();
   }

   public static Bridge2_42 asBridge(Component component0) {
      return component0 == null ? null : ((AdventureComponentBridge)component0).moonBridge$asBridgeComponent();
   }

   public static VanillaStyleBridge asBridgeStyle(Style style0) {
      return ((StyleBridge)style0).moonBridge$asBridgeStyle();
   }

   public static String asLegacyString(Component component0) {
      return LegacyComponentSerializer.legacySection().serialize(component0);
   }

   public static TextComponent asAdventure(String text0) {
      if (text0 == null) {
         return Component.empty();
      }

      if (text0.contains("§")) {
         StringBuilder builder1 = new StringBuilder();
         builder1.append(text0.charAt(0));

         for (int index2 = 1; index2 < text0.length(); index2++) {
            if (text0.charAt(index2 - 1) == 167) {
               builder1.append(Character.toLowerCase(text0.charAt(index2)));
            } else {
               builder1.append(text0.charAt(index2));
            }
         }

         return LegacyComponentSerializer.legacySection().deserialize(builder1.toString());
      } else {
         return Component.text(text0);
      }
   }

   @Nullable
   public static Boolean asBoolean(State state0) {
      if (state0 == State.TRUE) {
         return true;
      } else {
         return state0 == State.FALSE ? false : null;
      }
   }

   public static boolean doesComponentContain(Component component0, String... items1) {
      String text2 = stripColor(getTextContent(component0));

      for (String text6 : items1) {
         if (text6 != null && !text6.isEmpty() && text2.contains(text6)) {
            return true;
         }
      }

      return false;
   }

   public static boolean doesComponentContainWord(Component component0, String... items1) {
      String text2 = stripColor(getTextContent(component0));

      for (String text6 : items1) {
         if (!Strings.isNullOrEmpty(text6)) {
            Pattern pattern7 = Pattern.compile("(^|\\W)" + Pattern.quote(text6) + "(\\W|$)");
            if (pattern7.matcher(text2).find()) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean doesComponentEqual(Component component0, String text1) {
      return text1 == null ? false : getTextContent(component0).equals(text1);
   }

   @Nullable
   public static TextComponent getFirstTextComponent(Component component0) {
      if (component0 instanceof TextComponent text1 && !text1.content().isEmpty()) {
         return text1;
      } else {
         for (Component component2 : component0.children()) {
            TextComponent text3 = getFirstTextComponent(component2);
            if (text3 != null) {
               return text3;
            }
         }

         return null;
      }
   }

   @Nullable
   public static Component getFirstColoredComponent(Component component0) {
      if (component0.style().color() != null) {
         return component0;
      }

      for (Component component2 : component0.children()) {
         Component component3 = getFirstColoredComponent(component2);
         if (component3 != null) {
            return component3;
         }
      }

      return null;
   }

   @Nullable
   public static TextComponent getLastTextComponent(Component component0) {
      if (component0 instanceof TextComponent text1 && !text1.content().isEmpty()) {
         return text1;
      } else {
         List list4 = component0.children();

         for (int index2 = list4.size() - 1; index2 >= 0; index2--) {
            TextComponent text3 = getLastTextComponent((Component)list4.get(index2));
            if (text3 != null) {
               return text3;
            }
         }

         return null;
      }
   }

   @Nullable
   public static TextComponent forTextComponents(Component component0, Predicate<TextComponent> predicate1) {
      if (component0 instanceof TextComponent text2 && !text2.content().isEmpty() && predicate1.test(text2)) {
         return text2;
      } else {
         for (Component component3 : component0.children()) {
            TextComponent text4 = forTextComponents(component3, predicate1);
            if (text4 != null) {
               return text4;
            }
         }

         return null;
      }
   }

   @Nullable
   private static TextComponent getTextComponentPiece(Component component0, MutableInt mutableint1) {
      if (component0 instanceof TextComponent text2 && !text2.content().isEmpty()) {
         if (mutableint1.getValue() <= 0) {
            return text2;
         }

         mutableint1.decrement();
      }

      for (Component component3 : component0.children()) {
         TextComponent text4 = getTextComponentPiece(component3, mutableint1);
         if (text4 != null) {
            return text4;
         }
      }

      return null;
   }

   @Nullable
   public static TextComponent getTextComponentPiece(Component component0, int value) {
      return getTextComponentPiece(component0, new MutableInt(value));
   }

   public static boolean containsColoredText(Component component0, TextColor textcolor1, String text2) {
      if (component0 instanceof TextComponent text3
         && !text3.content().isEmpty()
         && component0.color() != null
         && component0.color().value() == textcolor1.value()
         && text3.content().contains(text2)) {
         return true;
      } else {
         for (Component component4 : component0.children()) {
            if (containsColoredText(component4, textcolor1, text2)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean containsColoredText(Component component0, TextColor textcolor1, String... items2) {
      if (component0 instanceof TextComponent text3 && !text3.content().isEmpty() && component0.color() != null && component0.color().value() == textcolor1.value()) {
         String text4 = text3.content();

         for (String text8 : items2) {
            if (text4.contains(text8)) {
               return true;
            }
         }
      }

      for (Component component10 : component0.children()) {
         if (containsColoredText(component10, textcolor1, items2)) {
            return true;
         }
      }

      return false;
   }

   public static TextComponent findFirst(Component component0, String text1) {
      if (component0 instanceof TextComponent text2 && text2.content().equals(text1)) {
         return text2;
      } else {
         for (Component component3 : component0.children()) {
            TextComponent text4 = findFirst(component3, text1);
            if (text4 != null) {
               return text4;
            }
         }

         return null;
      }
   }

   public static boolean startsWith(Component component0, String text1) {
      TextComponent text2 = getFirstTextComponent(component0);
      return text2 != null && text2.content().startsWith(text1);
   }

   public static boolean startsWith(Component component0, String text1, TextColor textcolor2) {
      TextComponent text3 = getFirstTextComponent(component0);
      return text3 != null && text3.color() != null && text3.color().value() == textcolor2.value() && text3.content().startsWith(text1);
   }

   public static boolean startsWith(Component component0, TextColor textcolor1) {
      TextComponent text2 = getFirstTextComponent(component0);
      return text2 != null && text2.color() != null && text2.color().value() == textcolor1.value();
   }

   public static boolean endsWith(Component component0, String text1) {
      TextComponent text2 = getFirstTextComponent(component0);
      return text2 != null && text2.content().endsWith(text1);
   }

   public static boolean endsWith(Component component0, String text1, TextColor textcolor2) {
      TextComponent text3 = getLastTextComponent(component0);
      return text3 != null && text3.color() != null && text3.color().value() == textcolor2.value() && text3.content().endsWith(text1);
   }

   public static boolean endsWith(Component component0, TextColor textcolor1) {
      TextComponent text2 = getFirstTextComponent(component0);
      return text2 != null && text2.color() != null && text2.color().value() == textcolor1.value();
   }

   public static float getTextWidth(Component component0, Bridge10_2 bridge10_21) {
      return bridge10_21.bridge$getStringWidth(component0);
   }
}

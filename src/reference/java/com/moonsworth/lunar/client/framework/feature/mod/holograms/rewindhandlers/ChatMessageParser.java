package com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers;

import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class ChatMessageParser {
   private static final List<Function<String, Optional<? extends ChatMessageParser.Extension>>> field1 = List.of(
      ChatMessageParser.IslandVisitorChatMessage::method1,
      ChatMessageParser.Data::method1,
      ChatMessageParser.PartyChatMessage::method1,
      ChatMessageParser.GuildChatMessage::method1,
      ChatMessageParser.GuildOfficerChatMessage::method1,
      ChatMessageParser.DirectMessageReceived::method1,
      ChatMessageParser.DirectMessageSent::method1,
      ChatMessageParser.CoopChatMessage::method1
   );

   public static Optional<? extends ChatMessageParser.Extension> method1(String text0) {
      for (Function function2 : field1) {
         Optional optional3 = (Optional)function2.apply(text0);
         if (optional3.isPresent()) {
            return optional3;
         }
      }

      return Optional.empty();
   }

   @Generated
   private ChatMessageParser() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data implements ChatMessageParser.Extension {
      @Nullable
      private final Integer field1;
      @Nullable
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private final String field5;
      private static final Pattern field6 = Pattern.compile(
         "^(?:\\[(?<sblvl>\\d+)] )?(?:(?<emblems>[^a-zA-Z\\[ ]{1,3}) )?(?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$"
      );

      public Data(@Nullable Integer number1, @Nullable String text2, @Nullable String text3, String text4, String text5) {
         this.field1 = number1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
         this.field5 = text5;
      }

      public static Optional<ChatMessageParser.Data> method1(String text0) {
         Matcher matcher1 = field6.matcher(text0);
         return !matcher1.matches()
            ? Optional.empty()
            : Optional.of(
               new ChatMessageParser.Data(
                  (Integer)NumberUtils.method8(matcher1.group("sblvl")).orElse(null),
                  matcher1.group("emblems"),
                  matcher1.group("rank"),
                  matcher1.group("name"),
                  matcher1.group("msg")
               )
            );
      }

      @Nullable
      public Integer method3() {
         return this.field1;
      }

      @Nullable
      public String method4() {
         return this.field2;
      }

      @Nullable
      public String method5() {
         return this.field3;
      }

      @Override
      public String method1() {
         return this.field4;
      }

      @Override
      public String message() {
         return this.field5;
      }
   }

   public class CoopChatMessage implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^Co-op > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public CoopChatMessage(@Nullable String text1, String text2, String text3) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
      }

      public static Optional<ChatMessageParser.CoopChatMessage> method1(String text0) {
         Matcher matcher1 = field4.matcher(text0);
         return !matcher1.matches() ? Optional.empty() : Optional.of(new ChatMessageParser.CoopChatMessage(matcher1.group("rank"), matcher1.group("name"), matcher1.group("msg")));
      }

      @Nullable
      public String method3() {
         return this.field1;
      }

      @Override
      public String method1() {
         return this.field2;
      }

      @Override
      public String message() {
         return this.field3;
      }
   }

   public class PartyChatMessage implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^Party > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public PartyChatMessage(@Nullable String text1, String text2, String text3) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
      }

      public static Optional<ChatMessageParser.PartyChatMessage> method1(String text0) {
         Matcher matcher1 = field4.matcher(text0);
         return !matcher1.matches() ? Optional.empty() : Optional.of(new ChatMessageParser.PartyChatMessage(matcher1.group("rank"), matcher1.group("name"), matcher1.group("msg")));
      }

      @Nullable
      public String method3() {
         return this.field1;
      }

      @Override
      public String method1() {
         return this.field2;
      }

      @Override
      public String message() {
         return this.field3;
      }
   }

   public class DirectMessageReceived implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^From (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public DirectMessageReceived(@Nullable String text1, String text2, String text3) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
      }

      public static Optional<ChatMessageParser.DirectMessageReceived> method1(String text0) {
         Matcher matcher1 = field4.matcher(text0);
         return !matcher1.matches() ? Optional.empty() : Optional.of(new ChatMessageParser.DirectMessageReceived(matcher1.group("rank"), matcher1.group("name"), matcher1.group("msg")));
      }

      @Override
      public boolean method2() {
         return false;
      }

      @Nullable
      public String method3() {
         return this.field1;
      }

      @Override
      public String method1() {
         return this.field2;
      }

      @Override
      public String message() {
         return this.field3;
      }
   }

   public class GuildChatMessage implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private static final Pattern field5 = Pattern.compile("^Guild > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)(?: \\[(?<guildRank>[^]]+)\\])?: (?<msg>.+)$");

      public GuildChatMessage(@Nullable String text1, String text2, @Nullable String text3, String text4) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
      }

      public static Optional<ChatMessageParser.GuildChatMessage> method1(String text0) {
         Matcher matcher1 = field5.matcher(text0);
         return !matcher1.matches()
            ? Optional.empty()
            : Optional.of(new ChatMessageParser.GuildChatMessage(matcher1.group("rank"), matcher1.group("name"), matcher1.group("guildRank"), matcher1.group("msg")));
      }

      @Nullable
      public String method3() {
         return this.field1;
      }

      @Override
      public String method1() {
         return this.field2;
      }

      @Nullable
      public String method4() {
         return this.field3;
      }

      @Override
      public String message() {
         return this.field4;
      }
   }

   public class GuildOfficerChatMessage implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private static final Pattern field5 = Pattern.compile("^Officer > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)(?: \\[(?<guildRank>[^]]+)\\])?: (?<msg>.+)$");

      public GuildOfficerChatMessage(@Nullable String text1, String text2, @Nullable String text3, String text4) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
      }

      public static Optional<ChatMessageParser.GuildOfficerChatMessage> method1(String text0) {
         Matcher matcher1 = field5.matcher(text0);
         return !matcher1.matches()
            ? Optional.empty()
            : Optional.of(new ChatMessageParser.GuildOfficerChatMessage(matcher1.group("rank"), matcher1.group("name"), matcher1.group("guildRank"), matcher1.group("msg")));
      }

      @Nullable
      public String method3() {
         return this.field1;
      }

      @Override
      public String method1() {
         return this.field2;
      }

      @Nullable
      public String method4() {
         return this.field3;
      }

      @Override
      public String message() {
         return this.field4;
      }
   }

   public class IslandVisitorChatMessage implements ChatMessageParser.Extension {
      @Nullable
      private final Integer field1;
      @Nullable
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private final String field5;
      private static final Pattern field6 = Pattern.compile(
         "^(?:\\[(?<sblvl>\\d+)] )?(?:(?<emblems>[^a-zA-Z\\[ ]{1,3}) )?\\[✌] (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$"
      );

      public IslandVisitorChatMessage(@Nullable Integer number1, @Nullable String text2, @Nullable String text3, String text4, String text5) {
         this.field1 = number1;
         this.field2 = text2;
         this.field3 = text3;
         this.field4 = text4;
         this.field5 = text5;
      }

      public static Optional<ChatMessageParser.IslandVisitorChatMessage> method1(String text0) {
         Matcher matcher1 = field6.matcher(text0);
         return !matcher1.matches()
            ? Optional.empty()
            : Optional.of(
               new ChatMessageParser.IslandVisitorChatMessage(
                  (Integer)NumberUtils.method8(matcher1.group("sblvl")).orElse(null),
                  matcher1.group("emblems"),
                  matcher1.group("rank"),
                  matcher1.group("name"),
                  matcher1.group("msg")
               )
            );
      }

      @Nullable
      public Integer method3() {
         return this.field1;
      }

      @Nullable
      public String method4() {
         return this.field2;
      }

      @Nullable
      public String method5() {
         return this.field3;
      }

      @Override
      public String method1() {
         return this.field4;
      }

      @Override
      public String message() {
         return this.field5;
      }
   }

   public class DirectMessageSent implements ChatMessageParser.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^To (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public DirectMessageSent(@Nullable String text1, String text2, String text3) {
         this.field1 = text1;
         this.field2 = text2;
         this.field3 = text3;
      }

      public static Optional<ChatMessageParser.DirectMessageSent> method1(String text0) {
         Matcher matcher1 = field4.matcher(text0);
         return !matcher1.matches() ? Optional.empty() : Optional.of(new ChatMessageParser.DirectMessageSent(matcher1.group("rank"), matcher1.group("name"), matcher1.group("msg")));
      }

      @Override
      public String method1() {
         return Ref.method7().bridge$getName();
      }

      @Override
      public boolean method2() {
         return true;
      }

      @Nullable
      public String method4() {
         return this.field1;
      }

      public String method5() {
         return this.field2;
      }

      @Override
      public String message() {
         return this.field3;
      }
   }

   public interface Extension {
      String message();

      String method1();

      default boolean method2() {
         return this.method1().equals(Ref.method7().bridge$getName())
            || ChatMessageListener.field8 != null && this.method1().equals(ChatMessageListener.field8);
      }
   }
}

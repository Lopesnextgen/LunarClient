package com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers;

import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class Rewindhandlers {
   private static final List<Function<String, Optional<? extends Rewindhandlers.Extension>>> field1 = List.of(
      Rewindhandlers.Data7::method1,
      Rewindhandlers.Data::method1,
      Rewindhandlers.Data3::method1,
      Rewindhandlers.Data5::method1,
      Rewindhandlers.Data6::method1,
      Rewindhandlers.Data4::method1,
      Rewindhandlers.Data8::method1,
      Rewindhandlers.Data2::method1
   );

   public static Optional<? extends Rewindhandlers.Extension> method1(String var0) {
      for (Function var2 : field1) {
         Optional var3 = (Optional)var2.apply(var0);
         if (var3.isPresent()) {
            return var3;
         }
      }

      return Optional.empty();
   }

   @Generated
   private Rewindhandlers() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data implements Rewindhandlers.Extension {
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

      public Data(@Nullable Integer var1, @Nullable String var2, @Nullable String var3, String var4, String var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      public static Optional<Rewindhandlers.Data> method1(String var0) {
         Matcher var1 = field6.matcher(var0);
         return !var1.matches()
            ? Optional.empty()
            : Optional.of(
               new Rewindhandlers.Data(
                  ThreadModuleDump40.method8(var1.group("sblvl")).orElse(null),
                  var1.group("emblems"),
                  var1.group("rank"),
                  var1.group("name"),
                  var1.group("msg")
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

   public class Data2 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^Co-op > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public Data2(@Nullable String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static Optional<Rewindhandlers.Data2> method1(String var0) {
         Matcher var1 = field4.matcher(var0);
         return !var1.matches() ? Optional.empty() : Optional.of(new Rewindhandlers.Data2(var1.group("rank"), var1.group("name"), var1.group("msg")));
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

   public class Data3 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^Party > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public Data3(@Nullable String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static Optional<Rewindhandlers.Data3> method1(String var0) {
         Matcher var1 = field4.matcher(var0);
         return !var1.matches() ? Optional.empty() : Optional.of(new Rewindhandlers.Data3(var1.group("rank"), var1.group("name"), var1.group("msg")));
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

   public class Data4 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^From (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public Data4(@Nullable String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static Optional<Rewindhandlers.Data4> method1(String var0) {
         Matcher var1 = field4.matcher(var0);
         return !var1.matches() ? Optional.empty() : Optional.of(new Rewindhandlers.Data4(var1.group("rank"), var1.group("name"), var1.group("msg")));
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

   public class Data5 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private static final Pattern field5 = Pattern.compile("^Guild > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)(?: \\[(?<guildRank>[^]]+)\\])?: (?<msg>.+)$");

      public Data5(@Nullable String var1, String var2, @Nullable String var3, String var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public static Optional<Rewindhandlers.Data5> method1(String var0) {
         Matcher var1 = field5.matcher(var0);
         return !var1.matches()
            ? Optional.empty()
            : Optional.of(new Rewindhandlers.Data5(var1.group("rank"), var1.group("name"), var1.group("guildRank"), var1.group("msg")));
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

   public class Data6 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      @Nullable
      private final String field3;
      private final String field4;
      private static final Pattern field5 = Pattern.compile("^Officer > (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)(?: \\[(?<guildRank>[^]]+)\\])?: (?<msg>.+)$");

      public Data6(@Nullable String var1, String var2, @Nullable String var3, String var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public static Optional<Rewindhandlers.Data6> method1(String var0) {
         Matcher var1 = field5.matcher(var0);
         return !var1.matches()
            ? Optional.empty()
            : Optional.of(new Rewindhandlers.Data6(var1.group("rank"), var1.group("name"), var1.group("guildRank"), var1.group("msg")));
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

   public class Data7 implements Rewindhandlers.Extension {
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

      public Data7(@Nullable Integer var1, @Nullable String var2, @Nullable String var3, String var4, String var5) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
      }

      public static Optional<Rewindhandlers.Data7> method1(String var0) {
         Matcher var1 = field6.matcher(var0);
         return !var1.matches()
            ? Optional.empty()
            : Optional.of(
               new Rewindhandlers.Data7(
                  ThreadModuleDump40.method8(var1.group("sblvl")).orElse(null),
                  var1.group("emblems"),
                  var1.group("rank"),
                  var1.group("name"),
                  var1.group("msg")
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

   public class Data8 implements Rewindhandlers.Extension {
      @Nullable
      private final String field1;
      private final String field2;
      private final String field3;
      private static final Pattern field4 = Pattern.compile("^To (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+): (?<msg>.+)$");

      public Data8(@Nullable String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static Optional<Rewindhandlers.Data8> method1(String var0) {
         Matcher var1 = field4.matcher(var0);
         return !var1.matches() ? Optional.empty() : Optional.of(new Rewindhandlers.Data8(var1.group("rank"), var1.group("name"), var1.group("msg")));
      }

      @Override
      public String method1() {
         return ThreadModuleDump63.method7().bridge$getName();
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
         return this.method1().equals(ThreadModuleDump63.method7().bridge$getName())
            || GuiRewindhandlersHandler2.field8 != null && this.method1().equals(GuiRewindhandlersHandler2.field8);
      }
   }
}

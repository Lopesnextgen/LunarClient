package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class MixinSupport {
   private static final Pattern field1 = Pattern.compile("in ([a-zA-Z$_.]*)\\. No refMap loaded");
   private static final Pattern field2 = Pattern.compile("mixins\\.([a-zA-Z$_.]*)\\.json:([a-zA-Z$_.]*) from mod");
   private static boolean enabled;
   private static URLClassLoader field3;
   private static boolean field4;
   private static List<MixinSupport.Data> field5;

   public static void method1(IchorPipeline var0) {
      enabled = true;
      System.setProperty("mixin.checks.interfaces", "true");
      System.setProperty("ichor.failOnMixinError", "true");
      var0.method38().method1(var0x -> field4 = true);
      System.setErr(new PrintStream(System.err) {
         @Override
         public PrintStream printf(@NotNull String var1, Object... var2) {
            if (var2.length == 1 && var2[0] instanceof String var3 && var3.contains("Total unimplemented:")) {
               MixinSupport.field4 = true;
            }

            return super.printf(var1, var2);
         }
      });
      System.setOut(new PrintStream(System.out) {
         @Override
         public void print(String var1) {
            if (MixinSupport.method5(var1)) {
               MixinSupport.field4 = true;
            }

            super.print(var1);
         }
      });
   }

   public static void method2(URLClassLoader var0) {
      if (enabled && var0.method18().hasMixinRuntime()) {
         field3 = var0;
      }
   }

   public static void method3(Object var0) {
      if (enabled) {
         System.out.println("Force-loading all unloaded mixin targets.");
         if (field3 == null) {
            throw new FatalIchorError("Failed to find Mixin ClassLoader? Was Mixin initialized?");
         }

         field3.method20().audit(var0.getClass().getClassLoader());
         if (field4 && !Boolean.parseBoolean(System.getProperty("ichor.testing.ignoreMixinFailures", "false"))) {
            method4();
            throw new AssertionError("Caught one or more mixin errors! Check the log for more info");
         }
      }
   }

   private static void method4() {
      if (field4 && field5 != null && !field5.isEmpty()) {
         StringBuilder var0 = new StringBuilder();
         var0.append("ExceptionName,McClassPath,LunarClassId,ErrorMsg");

         for (MixinSupport.Data var2 : field5) {
            if (!var0.isEmpty()) {
               var0.append("\n");
            }

            var0.append(var2.toString());
         }

         Path var4 = Paths.get(".ichor/audit/mixin_error_report.csv");

         try {
            Files.createDirectories(var4.getParent());
            Files.write(var4, var0.toString().getBytes());
         } catch (IOException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   private static boolean method5(String var0) {
      if (var0.startsWith("org.spongepowered.asm") && var0.contains("throwables")) {
         Matcher var2 = field1.matcher(var0);
         if (var2.find()) {
            String var1 = var2.group(1).replace(".", "/");
            int var3 = var0.indexOf(":");
            String var4 = var0.substring(0, var3);
            int var5 = var4.lastIndexOf(".") + 1;
            String var6 = var4.substring(var5).replace(".", "/");
            String var7 = "-";
            var2 = field2.matcher(var0);
            if (var2.find()) {
               var7 = var2.group(1) + ":" + var2.group(2).replace(".", "/");
            }

            MixinSupport.Data var8 = new MixinSupport.Data(var6, var1, var7, var0);
            if (field5 == null) {
               field5 = new ArrayList<>();
               field5.add(var8);
            } else if (!field5.contains(var8)) {
               field5.add(var8);
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Generated
   public static boolean isEnabled() {
      return enabled;
   }

   private static class Data {
      private final String field1;
      private final String field2;
      private final String field3;
      private final String field4;

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else {
            return !(var1 instanceof MixinSupport.Data var2)
               ? false
               : Objects.equals(var2.field1, this.field1) && Objects.equals(var2.field2, this.field2) && Objects.equals(var2.field3, this.field3);
         }
      }

      @Override
      public String toString() {
         return this.field1 + "," + this.field2 + "," + this.field3 + "," + this.field4;
      }

      @Generated
      public Data(String var1, String var2, String var3, String var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }
   }
}

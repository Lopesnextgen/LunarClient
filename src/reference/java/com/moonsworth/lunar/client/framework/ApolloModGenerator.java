package com.moonsworth.lunar.client.framework;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.framework.codegen.TypeSpec;
import com.moonsworth.lunar.client.util.MixinHelper224;
import com.moonsworth.lunar.client.util.MixinHelper23;
import com.moonsworth.lunar.client.util.MixinHelper25;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.Util;
import com.moonsworth.lunar.client.util.UtilType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.OptionGroup;
import com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher;
import com.moonsworth.lunar.client.render.particle.Mod;

public class ApolloModGenerator {
   public static final String field1 = "%release_version%";
   private static final String field2 = "./apollo/api/src/main/java/com/lunarclient/apollo/mods/impl/";
   private static final List<String> field3 = new ArrayList<>(
      Arrays.asList(
         "/*",
         " * This file is part of Apollo, licensed under the MIT License.",
         " *",
         " * Copyright (c) 2026 Moonsworth",
         " *",
         " * Permission is hereby granted, free of charge, to any person obtaining a copy",
         " * of this software and associated documentation files (the \"Software\"), to deal",
         " * in the Software without restriction, including without limitation the rights",
         " * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell",
         " * copies of the Software, and to permit persons to whom the Software is",
         " * furnished to do so, subject to the following conditions:",
         " *",
         " * The above copyright notice and this permission notice shall be included in all",
         " * copies or substantial portions of the Software.",
         " *",
         " * THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR",
         " * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,",
         " * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE",
         " * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER",
         " * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,",
         " * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE",
         " * SOFTWARE.",
         " */"
      )
   );
   private final Map<String, OptionGroup> optionMap = new LinkedHashMap<>();
   private final Set<String> field4 = new LinkedHashSet<>();
   private static final Pattern field5 = Pattern.compile("public static final\\s\\w+<?\\w+>\\s(\\w+)\\s");
   private static final Function<String, Pattern> field6 = var0 -> Pattern.compile("(/\\*\\*(?:(?!\\*\\*).)*?)(?=" + var0 + ")(.*?;)", 32);

   public void method1(String var1, OptionGroup var2) {
      this.optionMap.put(var1, var2);
   }

   public void method2(Path var1) {
      Util var2 = new Util(var1);
      TranslationManager var3 = Client.method109().method67();

      for (Entry var5 : this.optionMap.entrySet()) {
         String var6 = ((OptionGroup)var5.getValue()).method1();
         List var7 = ((OptionGroup)var5.getValue()).options();
         boolean var8 = false;
         boolean var9 = false;
         boolean var10 = false;

         for (ClientOption var12 : var7) {
            if (ColorOption.class.isAssignableFrom(var12.method5())) {
               var8 = true;
               var10 = true;
            } else if (var12.method2(OptionTraits.field7)) {
               var9 = true;
            } else {
               var10 = true;
            }
         }

         if (!var7.isEmpty()) {
            String var21 = var3.method2(var6, "description");
            if ("description".equals(var21)) {
               var21 = "A mod class";
            }

            String var22 = (String)var5.getKey();
            String var13 = "Mod" + ModMetadataFetcher.method11(var22);
            String var14 = this.method6(var13);
            String var15 = this.method4(var14, var13);
            MixinHelper25.Data2 var16 = MixinHelper25.method2(MixinHelper25.Type3.CLASS);

            for (String var20 : var21.split("\n")) {
               if (!var20.isEmpty()) {
                  var16.method1(var20);
               }
            }

            var16.method3(MixinHelper25.Type2.SINCE, var15);
            TypeSpec.Data var23 = (TypeSpec.Data)TypeSpec.method2(var13, "com.lunarclient.apollo.mods.impl")
               .method5(field3)
               .method1(UtilType.PUBLIC)
               .method3()
               .method27()
               .method21(var16);

            for (ClientOption var26 : var7) {
               this.method3(var23, var22, var26, var14);
            }

            ApolloModGenerator.Data5 var25 = this.method10(var23, var14, var7);
            if (var9 || var25.method3()) {
               var23.method6("com.lunarclient.apollo.option.NumberOption");
            }

            if (var10 || var25.method1()) {
               var23.method6("com.lunarclient.apollo.option.SimpleOption");
            }

            var23.method6("io.leangen.geantyref.TypeToken");
            if (var8 || var25.method2()) {
               var23.method6("java.awt.Color");
            }

            var2.method3(var23);
         }
      }

      this.method9(var2);
      this.method7(var2);
      var2.method5();
   }

   protected List<ClientOption<?>> getOptions(String var1) {
      return this.optionMap.get(var1).options();
   }

   private void method3(TypeSpec.Data var1, String var2, ClientOption<?> var3, @Nullable String var4) {
      Object var5 = var3.get();
      String var6 = ColorOption.class.isAssignableFrom(var3.method5()) ? "Color" : var5.getClass().getSimpleName();
      String var7 = var3.getId();
      String var8 = ThreadModuleDump46.method5(var7);
      String var9 = var3.method3();
      String var10 = var9 + "Description";
      String var11 = var3.method28(var10, new Object[0]);
      String var12 = ".node(\""
         + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, var2)
         + "\", \""
         + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, var7)
         + "\").type(TypeToken.get("
         + var6
         + ".class))";
      MixinHelper224.Data var13;
      if (ColorOption.class.isAssignableFrom(var3.method5())) {
         var13 = MixinHelper224.method2(var8, "SimpleOption<Color>").method4(UtilType.PUBLIC).method6().method5().method3("SimpleOption.<Color>builder()");
         String var14 = this.method5(var4, "SimpleOption", "builder", var8);
         if (!var11.equals(var10)) {
            var11 = var11.replaceAll("§[0-9a-fk-orA-FK-OR]|\\n", "");
            var13.method3(".comment(\"" + var11.replace("\"", "\\\"") + "\")")
               .method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method1(var11).method3(MixinHelper25.Type2.SINCE, var14));
         } else {
            var13.method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method3(MixinHelper25.Type2.SINCE, var14));
         }

         var13.method3(var12);
      } else if (var3.method2(OptionTraits.field7)) {
         var1.method6("com.lunarclient.apollo.option.NumberOption");
         var13 = MixinHelper224.method2(var8, "NumberOption<" + var6 + ">")
            .method4(UtilType.PUBLIC)
            .method6()
            .method5()
            .method3("NumberOption.<" + var6 + ">number()");
         String var19 = this.method5(var4, "NumberOption", "number", var8);
         if (!var11.equals(var10)) {
            var11 = var11.replaceAll("§[0-9a-fk-orA-FK-OR]|\\n", "");
            var13.method3(".comment(\"" + var11.replace("\"", "\\\"") + "\")")
               .method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method1(var11).method3(MixinHelper25.Type2.SINCE, var19));
         } else {
            var13.method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method3(MixinHelper25.Type2.SINCE, var19));
         }

         var13.method3(var12);
         com.moonsworth.lunar.client.config.option.NumberRule var15 = (com.moonsworth.lunar.client.config.option.NumberRule)var3.RHRHIOOCICIORIOCIHHCIIRCRHHOII(
            OptionTraits.field7
         );
         var13.method3(".min(" + ModMetadataFetcher.method13(var3, var15.getMin()) + ").max(" + ModMetadataFetcher.method13(var3, var15.getMax()) + ")");
      } else {
         var13 = MixinHelper224.method2(var8, "SimpleOption<" + var6 + ">")
            .method4(UtilType.PUBLIC)
            .method6()
            .method5()
            .method3("SimpleOption.<" + var6 + ">builder()");
         String var20 = this.method5(var4, "SimpleOption", "builder", var8);
         if (!var11.equals(var10)) {
            var11 = var11.replaceAll("§[0-9a-fk-orA-FK-OR]|\\n", "");
            var13.method3(".comment(\"" + var11.replace("\"", "\\\"") + "\")")
               .method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method1(var11).method3(MixinHelper25.Type2.SINCE, var20));
         } else {
            var13.method3(MixinHelper25.method2(MixinHelper25.Type3.FIELD).method3(MixinHelper25.Type2.SINCE, var20));
         }

         var13.method3(var12);
      }

      var13.method3(".defaultValue(" + ModMetadataFetcher.method12(var3, var3.getDefaultValue()) + ")");
      var13.method3(".notifyClient()");
      var13.method3(".build()");
      var1.method17(var13, Objects.equals(var7, "enabled"));
   }

   private String method4(String var1, String var2) {
      if (var1 != null) {
         Pattern var3 = Pattern.compile("@since (\\d\\.\\d\\.\\d)\\n\\s*\\*/\\n(?:@Deprecated\\n)?public final class " + var2 + " \\{");
         Matcher var4 = var3.matcher(var1);
         if (var4.find()) {
            return var4.group(1);
         }
      }

      return "%release_version%";
   }

   private String method5(String var1, String var2, String var3, String var4) {
      if (var1 != null) {
         Pattern var5 = Pattern.compile("@since (\\d\\.\\d\\.\\d)\\n\\s*\\*/\\n\\s.*" + var2 + "<.*> " + var4 + " = " + var2 + "\\.<.*>" + var3 + "\\(\\)");
         Matcher var6 = var5.matcher(var1);
         if (var6.find()) {
            return var6.group(1);
         }
      }

      return "%release_version%";
   }

   @Nullable
   private String method6(String var1) {
      File var2 = new File("./apollo/api/src/main/java/com/lunarclient/apollo/mods/impl/" + var1 + ".java");
      if (var2.exists()) {
         try {
            return Files.readString(var2.toPath());
         } catch (IOException var4) {
         }
      }

      return null;
   }

   private void method7(Util var1) {
      ArrayList var2 = Lists.newArrayList();

      for (String var4 : this.optionMap.keySet()) {
         var2.add("com.lunarclient.apollo.mods.impl.Mod" + ModMetadataFetcher.method11(var4));
      }

      for (String var7 : this.field4) {
         var2.add("com.lunarclient.apollo.mods.impl." + var7);
      }

      Collections.sort(var2);
      TypeSpec.Data var6 = (TypeSpec.Data)TypeSpec.method2("Mods", "com.lunarclient.apollo.mods")
         .method5(field3)
         .method1(UtilType.PUBLIC)
         .method3()
         .method27()
         .method8(var2)
         .method21(MixinHelper25.method2(MixinHelper25.Type3.CLASS).method1("Mod container").method3(MixinHelper25.Type2.SINCE, "1.0.0"));
      var6.method6("java.util.Arrays");
      var6.method6("java.util.List");
      var6.method16(this.method8());
      var1.method3(var6);
   }

   private MixinHelper224.Data method8() {
      MixinHelper224.Data var1 = (MixinHelper224.Data)MixinHelper224.method2("ALL_MODS", "List<Class<?>>")
         .method4(UtilType.PUBLIC)
         .method6()
         .method5()
         .method3("Arrays.asList(")
         .method3(
            MixinHelper25.method2(MixinHelper25.Type3.FIELD).method1("List of all current mod classes").method3(MixinHelper25.Type2.SINCE, "1.0.0")
         );
      ArrayList var2 = new ArrayList();

      for (String var4 : this.optionMap.keySet()) {
         var2.add("Mod" + ModMetadataFetcher.method11(var4));
      }

      var2.addAll(this.field4);
      int var7 = 0;

      for (String var5 : var2) {
         var7++;
         String var6 = var7 != var2.size() ? "," : "";
         var1.method3(Util.method7(1) + var5 + ".class" + var6);
      }

      var1.method3(")");
      return var1;
   }

   private void method9(Util var1) {
      File var2 = new File("./apollo/api/src/main/java/com/lunarclient/apollo/mods/impl/");
      if (var2.exists()) {
         HashSet var3 = new HashSet();

         for (String var5 : this.optionMap.keySet()) {
            var3.add("Mod" + ModMetadataFetcher.method11(var5));
         }

         File[] var14 = var2.listFiles((var0, var1x) -> var1x.startsWith("Mod") && var1x.endsWith(".java"));
         if (var14 != null) {
            for (File var8 : var14) {
               String var9 = var8.getName().replace(".java", "");
               if (!var3.contains(var9)) {
                  String var10 = this.method6(var9);
                  if (var10 != null) {
                     String var11 = this.method4(var10, var9);
                     TypeSpec.Data var12 = (TypeSpec.Data)((TypeSpec.Data)TypeSpec.method2(var9, "com.lunarclient.apollo.mods.impl")
                           .method5(field3)
                           .method1(UtilType.PUBLIC)
                           .method3()
                           .method27()
                           .method21(MixinHelper23.method2("Deprecated")))
                        .method21(MixinHelper25.method2(MixinHelper25.Type3.CLASS).method3(MixinHelper25.Type2.SINCE, var11));
                     ApolloModGenerator.Data5 var13 = this.method10(var12, var10, Collections.emptyList());
                     if (var13.method3()) {
                        var12.method6("com.lunarclient.apollo.option.NumberOption");
                     }

                     if (var13.method1()) {
                        var12.method6("com.lunarclient.apollo.option.SimpleOption");
                     }

                     var12.method6("io.leangen.geantyref.TypeToken");
                     if (var13.method2()) {
                        var12.method6("java.awt.Color");
                     }

                     var1.method3(var12);
                     this.field4.add(var9);
                  }
               }
            }
         }
      }
   }

   private ApolloModGenerator.Data5 method10(TypeSpec.Data var1, @Nullable String var2, List<ClientOption<?>> var3) {
      if (var2 == null) {
         return new ApolloModGenerator.Data5(false, false, false);
      }

      Matcher var4 = field5.matcher(var2);
      ArrayList var5 = new ArrayList();

      while (var4.find()) {
         var5.add(var4.group(1));
      }

      for (ClientOption var7 : var3) {
         String var8 = ThreadModuleDump46.method5(var7.getId());
         var5.remove(var8);
      }

      boolean var17 = false;
      boolean var18 = false;
      boolean var19 = false;

      for (String var10 : var5) {
         Pattern var11 = field6.apply(" " + var10 + " ");
         Matcher var12 = var11.matcher(var2);
         if (var12.find()) {
            String var13 = var12.group(1);
            String var14 = var12.group(2);
            String var15;
            if (var13.contains("@Deprecated")) {
               var15 = var13 + var14;
            } else {
               int var16 = var13.indexOf("*/") + 2;
               var15 = var13.substring(0, var16) + "\n" + Util.method7(1) + "@Deprecated" + var13.substring(var16) + var14;
            }

            if (var15.contains("SimpleOption<Color>")) {
               var19 = true;
            } else if (var15.contains("NumberOption")) {
               var18 = true;
            } else {
               var17 = true;
            }

            var1.method13(new MixinHelper224(var15));
         }
      }

      return new ApolloModGenerator.Data5(var17, var19, var18);
   }

   private class Data5 {
      private final boolean field1;
      private final boolean field2;
      private final boolean field3;

      private Data5(boolean var1, boolean var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public boolean method1() {
         return this.field1;
      }

      public boolean method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }
   }
}

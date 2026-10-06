package com.moonsworth.lunar.client.framework.codegen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public class MethodSpec extends AbstractSpec {
   private final String field3;
   private final String returnType;
   private final List<NamedValue<String>> params;
   private final List<String> field4;
   private final int field5;
   private final boolean field6;

   private MethodSpec(
      Set<JavadocSpec> set1, Set<AnnotationSpec> set2, String text3, String text4, List<NamedValue<String>> list5, List<String> list6, int number7, boolean flag8
   ) {
      super(set1, set2);
      this.field3 = text3;
      this.returnType = text4;
      this.params = list5;
      this.field4 = list6;
      this.field5 = number7;
      this.field6 = flag8;
   }

   @Override
   public String method1(int number1) {
      StringBuilder builder2 = new StringBuilder();
      this.method1(builder2, number1);
      this.method2(builder2, number1);
      String text3 = Modifier.toString(this.field5);
      builder2.append(JavaFileWriter.method7(number1)).append(text3);
      if (!this.field6) {
         if (!text3.isEmpty()) {
            builder2.append(" ");
         }

         builder2.append(this.returnType);
      }

      builder2.append(" ").append(this.field3).append("(");
      boolean flag4 = true;

      for (NamedValue mixinhelper246 : this.params) {
         if (flag4) {
            flag4 = false;
         } else {
            builder2.append(", ");
         }

         builder2.append(mixinhelper246.method1(0));
      }

      builder2.append(") {\n");

      for (String text8 : this.field4) {
         builder2.append(JavaFileWriter.method8(text8, number1 + 1)).append("\n");
      }

      builder2.append(JavaFileWriter.method7(number1)).append("}");
      return builder2.toString();
   }

   public static MethodSpec.Data method2(String text0, String text1) {
      return new MethodSpec.Data(text0, text1);
   }

   public static class Data extends SpecBuilder<MethodSpec.Data> {
      private final String field3;
      private final String field4;
      private final List<NamedValue<String>> field5 = new ArrayList<>();
      private final List<String> field6 = new ArrayList<>();
      private int modifiers;
      private boolean field7;

      public MethodSpec.Data method1() {
         this.field7 = true;
         return this;
      }

      public MethodSpec.Data method2(NamedValue<String> mixinhelper241) {
         this.field5.add(mixinhelper241);
         return this;
      }

      @SafeVarargs
      public final MethodSpec.Data method3(NamedValue<String>... items1) {
         this.field5.addAll(List.of(items1));
         return this;
      }

      public MethodSpec.Data method4(String text1) {
         this.field6.add(text1);
         return this;
      }

      public MethodSpec.Data method5(String... items1) {
         this.field6.addAll(List.of(items1));
         return this;
      }

      public MethodSpec.Data method6(Visibility utiltype1) {
         this.modifiers = this.modifiers | switch (utiltype1) {
            case PUBLIC -> 1;
            case PROTECTED -> 4;
            case PRIVATE -> 2;
            case PACKAGE -> 0;
         };
         return this;
      }

      public MethodSpec.Data method7() {
         this.modifiers |= 1024;
         return this;
      }

      public MethodSpec.Data method8() {
         this.modifiers |= 16;
         return this;
      }

      public MethodSpec.Data method11() {
         this.modifiers |= 8;
         return this;
      }

      public MethodSpec.Data method12() {
         this.modifiers |= 16;
         return this;
      }

      public MethodSpec method13() {
         return new MethodSpec(
            ImmutableSet.copyOf(this.OCIRCRHIRCCHHCROORIROROORCCRCI()),
            ImmutableSet.copyOf(this.OROIORIIOCOCOCROCOOICRIHRCOCIR()),
            this.field3,
            this.field4,
            ImmutableList.copyOf(this.field5),
            ImmutableList.copyOf(this.field6),
            this.modifiers,
            this.field7
         );
      }

      @Generated
      protected Data(String text1, String text2) {
         this.field3 = text1;
         this.field4 = text2;
      }
   }
}

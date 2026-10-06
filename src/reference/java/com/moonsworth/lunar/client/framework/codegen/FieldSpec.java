package com.moonsworth.lunar.client.framework.codegen;

import com.google.common.collect.ImmutableSet;
import java.lang.reflect.Modifier;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;

public class FieldSpec extends AbstractSpec {
   private String fieldName;
   private String field3;
   @Nullable
   private String field4;
   private int modifiers;
   @Nullable
   private String field5;

   public FieldSpec(@Nullable String text1) {
      super(null, null);
      this.field5 = text1;
   }

   private FieldSpec(Set<JavadocSpec> set1, Set<AnnotationSpec> set2, String text3, String text4, @Nullable String text5, int number6) {
      super(set1, set2);
      this.fieldName = text3;
      this.field3 = text4;
      this.field4 = text5;
      this.modifiers = number6;
   }

   @Override
   public String method1(int number1) {
      if (this.field5 != null) {
         return JavaFileWriter.method7(number1) + this.field5;
      }

      StringBuilder builder2 = new StringBuilder();
      this.method1(builder2, number1);
      this.method2(builder2, number1);
      builder2.append(JavaFileWriter.method7(number1) + Modifier.toString(this.modifiers)).append(" ").append(this.field3).append(" ").append(this.fieldName);
      if (this.field4 != null) {
         String[] items3 = this.field4.split("\n");
         builder2.append(" = ").append(items3[0]);

         for (int index4 = 1; index4 < items3.length; index4++) {
            builder2.append("\n").append(JavaFileWriter.method7(number1 + 1)).append(items3[index4]);
         }
      }

      builder2.append(";");
      return builder2.toString();
   }

   public static FieldSpec.Data method2(String text0, String text1) {
      return new FieldSpec.Data(text0, text1);
   }

   public static class Data extends SpecBuilder<FieldSpec.Data> {
      private final String field3;
      private final String field4;
      private String field5;
      private int modifiers;

      public FieldSpec.Data method1(String text1) {
         this.field5 = text1;
         return this;
      }

      public FieldSpec.Data method2(String... items1) {
         this.field5 = String.join("\n", items1);
         return this;
      }

      public FieldSpec.Data method3(String text1) {
         if (this.field5 == null) {
            this.field5 = text1;
         } else {
            this.field5 = this.field5 + "\n" + text1;
         }

         return this;
      }

      public FieldSpec.Data method4(Visibility utiltype1) {
         this.modifiers = this.modifiers | switch (utiltype1) {
            case PUBLIC -> 1;
            case PROTECTED -> 4;
            case PRIVATE -> 2;
            case PACKAGE -> 0;
         };
         return this;
      }

      public FieldSpec.Data method5() {
         this.modifiers |= 16;
         return this;
      }

      public FieldSpec.Data method6() {
         this.modifiers |= 8;
         return this;
      }

      public FieldSpec.Data method7() {
         this.modifiers |= 128;
         return this;
      }

      public FieldSpec.Data method8() {
         this.modifiers |= 64;
         return this;
      }

      public FieldSpec method11() {
         return new FieldSpec(
            ImmutableSet.copyOf(this.OCIRCRHIRCCHHCROORIROROORCCRCI()),
            ImmutableSet.copyOf(this.OROIORIIOCOCOCROCOOICRIHRCOCIR()),
            this.field3,
            this.field4,
            this.field5,
            this.modifiers
         );
      }

      @Generated
      protected Data(String text1, String text2) {
         this.field3 = text1;
         this.field4 = text2;
      }
   }
}

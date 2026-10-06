package com.moonsworth.lunar.client.framework.codegen;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class JavadocSpec implements SourceEmitter {
   private final List<String> field1;

   @Override
   public String method1(int value) {
      StringBuilder builder2 = new StringBuilder();
      boolean flag3 = true;

      for (String text5 : this.field1) {
         if (flag3) {
            flag3 = false;
         } else {
            builder2.append("\n");
         }

         builder2.append(JavaFileWriter.method8(text5, value));
      }

      return builder2.toString();
   }

   public static JavadocSpec.JavadocBuilder method2(JavadocSpec.JavadocElementType javadocElementType) {
      return new JavadocSpec.JavadocBuilder(javadocElementType);
   }

   @Generated
   private JavadocSpec(List<String> list1) {
      this.field1 = list1;
   }

   public static class JavadocBuilder {
      private final JavadocSpec.JavadocElementType field1;
      private final List<String> field2 = new ArrayList<>();
      private final List<String> field3 = new ArrayList<>();

      public JavadocBuilder(JavadocSpec.JavadocElementType javadocElementType) {
         this.field1 = javadocElementType;
      }

      public JavadocSpec.JavadocBuilder method1(String text) {
         if (text.charAt(text.length() - 1) != '.') {
            text = text + ".";
         }

         this.field2.add(" * " + text);
         return this;
      }

      public JavadocSpec.JavadocBuilder method2(String... items1) {
         for (String text5 : items1) {
            this.method1(text5);
         }

         return this;
      }

      public JavadocSpec.JavadocBuilder method3(JavadocSpec.JavadocTag javadocTag, String text) {
         if (!javadocTag.validTypes.contains(this.field1)) {
            throw new RuntimeException("JavaDoc tag cannot be applied to this type: `" + javadocTag.name() + "` -> `" + this.field1.name() + "`");
         }

         this.field3.add(" * @" + javadocTag.name().toLowerCase() + " " + text);
         return this;
      }

      public JavadocSpec method4() {
         ArrayList list1 = new ArrayList();
         list1.add("/**");
         if (!this.field2.isEmpty()) {
            list1.addAll(this.field2);
         } else {
            list1.add(" * No documentation available.");
            list1.add(" *");
         }

         if (!this.field3.isEmpty()) {
            if (!this.field2.isEmpty()) {
               list1.add(" *");
            }

            list1.addAll(this.field3);
         }

         list1.add(" */");
         return new JavadocSpec(list1);
      }
   }

   public enum JavadocTag {
      DEPRECATED(JavadocSpec.JavadocElementType.CLASS, JavadocSpec.JavadocElementType.INTERFACE, JavadocSpec.JavadocElementType.ENUM, JavadocSpec.JavadocElementType.FIELD, JavadocSpec.JavadocElementType.METHOD),
      SEE(JavadocSpec.JavadocElementType.CLASS, JavadocSpec.JavadocElementType.INTERFACE, JavadocSpec.JavadocElementType.ENUM, JavadocSpec.JavadocElementType.FIELD, JavadocSpec.JavadocElementType.METHOD),
      PARAM(JavadocSpec.JavadocElementType.METHOD),
      RETURN(JavadocSpec.JavadocElementType.METHOD),
      EXCEPTION(JavadocSpec.JavadocElementType.METHOD),
      THROWS(JavadocSpec.JavadocElementType.METHOD),
      AUTHOR(JavadocSpec.JavadocElementType.CLASS, JavadocSpec.JavadocElementType.INTERFACE, JavadocSpec.JavadocElementType.ENUM, JavadocSpec.JavadocElementType.METHOD),
      VERSION(JavadocSpec.JavadocElementType.CLASS, JavadocSpec.JavadocElementType.INTERFACE, JavadocSpec.JavadocElementType.ENUM),
      SINCE(JavadocSpec.JavadocElementType.CLASS, JavadocSpec.JavadocElementType.INTERFACE, JavadocSpec.JavadocElementType.ENUM, JavadocSpec.JavadocElementType.FIELD, JavadocSpec.JavadocElementType.METHOD);

      private final List<JavadocSpec.JavadocElementType> validTypes;

      JavadocTag(JavadocSpec.JavadocElementType... items3) {
         this.validTypes = List.of(items3);
      }

      @Generated
      public List<JavadocSpec.JavadocElementType> getValidTypes() {
         return this.validTypes;
      }
   }

   public enum JavadocElementType {
      CLASS,
      INTERFACE,
      ENUM,
      FIELD,
      METHOD;

      JavadocElementType() {
      }
   }
}

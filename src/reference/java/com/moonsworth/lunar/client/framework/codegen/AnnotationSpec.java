package com.moonsworth.lunar.client.framework.codegen;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class AnnotationSpec implements SourceEmitter {
   private final String field1;
   private final List<NamedValue<SourceEmitter>> field2;

   @Override
   public String method1(int number1) {
      StringBuilder builder2 = new StringBuilder();
      builder2.append("@").append(this.field1);
      if (!this.field2.isEmpty()) {
         builder2.append("(");
         boolean flag3 = this.field2.size() >= 2;
         boolean flag4 = false;
         if (flag3) {
            builder2.append("\n");
         } else if (this.field2.get(0).method2().equals("value")) {
            flag4 = true;
         }

         for (int index5 = 0; index5 < this.field2.size(); index5++) {
            NamedValue mixinhelper246 = this.field2.get(index5);
            if (!flag4) {
               builder2.append(JavaFileWriter.method7(number1 + 1));
               builder2.append(mixinhelper246.method2()).append(" = ");
            }

            builder2.append(((SourceEmitter)mixinhelper246.method3()).method1(number1 + 1));
            if (this.field2.size() > 1 && index5 != this.field2.size()) {
               builder2.append(",\n");
            }
         }

         if (flag3) {
            builder2.append(JavaFileWriter.method7(number1));
         }

         builder2.append(")");
      }

      return builder2.toString();
   }

   public static AnnotationSpec.AnnotationSpecBuilder method2(String text) {
      return new AnnotationSpec.AnnotationSpecBuilder(text);
   }

   @Generated
   private AnnotationSpec(String text1, List<NamedValue<SourceEmitter>> list) {
      this.field1 = text1;
      this.field2 = list;
   }

   private static class AnnotationLiteral implements SourceEmitter {
      private final String field1;
      private final boolean field2;

      @Override
      public String method1(int number1) {
         return this.field2 ? "\"" + this.field1 + "\"" : this.field1;
      }

      @Generated
      public AnnotationLiteral(String text1, boolean flag) {
         this.field1 = text1;
         this.field2 = flag;
      }
   }

   public static class AnnotationSpecBuilder {
      private final String field1;
      private final Map<String, NamedValue<SourceEmitter>> field2 = new HashMap<>();

      public AnnotationSpec.AnnotationSpecBuilder method1(String text1, String text) {
         this.field2.put(text1, NamedValue.method4(text1, new AnnotationSpec.AnnotationLiteral(text, true)));
         return this;
      }

      public AnnotationSpec.AnnotationSpecBuilder method2(String text1, AnnotationSpec mixinhelper232) {
         this.field2.put(text1, NamedValue.method4(text1, mixinhelper232));
         return this;
      }

      public AnnotationSpec.AnnotationSpecBuilder method3(String text1, AnnotationSpec.AnnotationSpecBuilder builder) {
         this.field2.put(text1, NamedValue.method4(text1, builder.method9()));
         return this;
      }

      public AnnotationSpec.AnnotationSpecBuilder method4(String text1) {
         return this.method1("value", text1);
      }

      public AnnotationSpec.AnnotationSpecBuilder method5(AnnotationSpec mixinhelper231) {
         return this.method2("value", mixinhelper231);
      }

      public AnnotationSpec.AnnotationSpecBuilder method6(AnnotationSpec.AnnotationSpecBuilder builder) {
         return this.method3("value", builder);
      }

      public AnnotationSpec.AnnotationSpecBuilder method7(NamedValue<?> mixinhelper241) {
         Object obj3 = mixinhelper241.method3();
         NamedValue mixinhelper242;
         if (obj3 instanceof String text) {
            mixinhelper242 = NamedValue.method4(mixinhelper241.method2(), new AnnotationSpec.AnnotationLiteral(text, true));
         } else if (obj3 instanceof AnnotationSpec) {
            mixinhelper242 = mixinhelper241;
         } else if (obj3 instanceof AnnotationSpec.AnnotationSpecBuilder data35) {
            mixinhelper242 = NamedValue.method4(mixinhelper241.method2(), data35.method9());
         } else {
            mixinhelper242 = NamedValue.method4(mixinhelper241.method2(), new AnnotationSpec.AnnotationLiteral(obj3.toString(), false));
         }

         this.field2.put(mixinhelper242.method2(), mixinhelper242);
         return this;
      }

      public AnnotationSpec.AnnotationSpecBuilder method8(NamedValue<?>... items1) {
         for (NamedValue mixinhelper245 : items1) {
            this.method7(mixinhelper245);
         }

         return this;
      }

      public AnnotationSpec method9() {
         return new AnnotationSpec(this.field1, ImmutableList.copyOf(this.field2.values()));
      }

      @Generated
      public AnnotationSpecBuilder(String text1) {
         this.field1 = text1;
      }
   }
}

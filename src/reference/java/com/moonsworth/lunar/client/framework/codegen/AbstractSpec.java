package com.moonsworth.lunar.client.framework.codegen;

import java.util.Set;
import lombok.Generated;

public abstract class AbstractSpec implements SourceEmitter {
   private final Set<JavadocSpec> field1;
   private final Set<AnnotationSpec> field2;

   protected void method1(StringBuilder builder1, int number2) {
      for (JavadocSpec mixinhelper254 : this.field1) {
         builder1.append(mixinhelper254.method1(number2)).append("\n");
      }
   }

   protected void method2(StringBuilder builder1, int number2) {
      for (AnnotationSpec mixinhelper234 : this.field2) {
         builder1.append(JavaFileWriter.method7(number2)).append(mixinhelper234.method1(number2)).append("\n");
      }
   }

   @Generated
   protected AbstractSpec(Set<JavadocSpec> set, Set<AnnotationSpec> set2) {
      this.field1 = set;
      this.field2 = set2;
   }
}

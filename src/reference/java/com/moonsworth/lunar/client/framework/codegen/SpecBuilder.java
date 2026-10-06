package com.moonsworth.lunar.client.framework.codegen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public abstract class SpecBuilder<T extends SpecBuilder<T>> {
   private final Set<JavadocSpec> javadocs = new HashSet<>();
   private final Set<AnnotationSpec> annotations = new HashSet<>();

   public T addJavadoc(JavadocSpec mixinhelper251) {
      this.javadocs.add(mixinhelper251);
      return (T)this;
   }

   public T addJavadocs(JavadocSpec... items1) {
      this.javadocs.addAll(List.of(items1));
      return (T)this;
   }

   public T addJavadoc(JavadocSpec.JavadocBuilder builder) {
      return this.addJavadoc(builder.method4());
   }

   public T addJavadocs(JavadocSpec.JavadocBuilder... items1) {
      for (JavadocSpec.JavadocBuilder data25 : items1) {
         this.addJavadoc(data25);
      }

      return (T)this;
   }

   public T addAnnotation(AnnotationSpec mixinhelper231) {
      this.annotations.add(mixinhelper231);
      return (T)this;
   }

   public T addAnnotations(AnnotationSpec... items1) {
      this.annotations.addAll(List.of(items1));
      return (T)this;
   }

   public T addAnnotation(AnnotationSpec.AnnotationSpecBuilder builder) {
      return this.addAnnotation(builder.method9());
   }

   public T addAnnotations(AnnotationSpec.AnnotationSpecBuilder... items1) {
      for (AnnotationSpec.AnnotationSpecBuilder data35 : items1) {
         this.addAnnotation(data35);
      }

      return (T)this;
   }

   @Generated
   public Set<JavadocSpec> javadocs() {
      return this.javadocs;
   }

   @Generated
   public Set<AnnotationSpec> annotations() {
      return this.annotations;
   }

   @Generated
   public SpecBuilder() {
   }
}

package com.moonsworth.lunar.network;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractOpenApiSchema {
   private Object instance;
   private Boolean field1;
   private final String field2;

   public AbstractOpenApiSchema(String text, Boolean booleanValue) {
      this.field2 = text;
      this.field1 = booleanValue;
   }

   public abstract Map<String, Class<?>> method1();

   public Object getInstance() {
      return this.instance;
   }

   public void setInstance(Object obj1) {
      this.instance = obj1;
   }

   public Object method4() {
      return this.method5(this);
   }

   private Object method5(AbstractOpenApiSchema mixinhelper61) {
      if (mixinhelper61.getInstance() == null) {
         return null;
      } else {
         return mixinhelper61.getInstance() instanceof AbstractOpenApiSchema ? this.method5((AbstractOpenApiSchema)mixinhelper61.getInstance()) : mixinhelper61.getInstance();
      }
   }

   public String method6() {
      return this.field2;
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class ").append(this.getClass()).append(" {\n");
      builder1.append("    instance: ").append(this.method7(this.instance)).append("\n");
      builder1.append("    isNullable: ").append(this.method7(this.field1)).append("\n");
      builder1.append("    schemaType: ").append(this.method7(this.field2)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String method7(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         AbstractOpenApiSchema mixinhelper62 = (AbstractOpenApiSchema)obj1;
         return Objects.equals(this.instance, mixinhelper62.instance) && Objects.equals(this.field1, mixinhelper62.field1) && Objects.equals(this.field2, mixinhelper62.field2);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.instance, this.field1, this.field2);
   }

   public Boolean method8() {
      return Boolean.TRUE.equals(this.field1) ? Boolean.TRUE : Boolean.FALSE;
   }
}

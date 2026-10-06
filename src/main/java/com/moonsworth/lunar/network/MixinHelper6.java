package com.moonsworth.lunar.network;

import java.util.Map;
import java.util.Objects;

public abstract class MixinHelper6 {
   private Object instance;
   private Boolean field1;
   private final String field2;

   public MixinHelper6(String var1, Boolean var2) {
      this.field2 = var1;
      this.field1 = var2;
   }

   public abstract Map<String, Class<?>> method1();

   public Object getInstance() {
      return this.instance;
   }

   public void setInstance(Object var1) {
      this.instance = var1;
   }

   public Object method4() {
      return this.method5(this);
   }

   private Object method5(MixinHelper6 var1) {
      if (var1.getInstance() == null) {
         return null;
      } else {
         return var1.getInstance() instanceof MixinHelper6 ? this.method5((MixinHelper6)var1.getInstance()) : var1.getInstance();
      }
   }

   public String method6() {
      return this.field2;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class ").append(this.getClass()).append(" {\n");
      var1.append("    instance: ").append(this.method7(this.instance)).append("\n");
      var1.append("    isNullable: ").append(this.method7(this.field1)).append("\n");
      var1.append("    schemaType: ").append(this.method7(this.field2)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String method7(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper6 var2 = (MixinHelper6)var1;
         return Objects.equals(this.instance, var2.instance) && Objects.equals(this.field1, var2.field1) && Objects.equals(this.field2, var2.field2);
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

package com.moonsworth.lunar.files;

import java.util.Objects;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class Files3 {
   private final String field1;
   private final String field2;
   @Nullable
   private final String field3;
   private final boolean field4;
   private final boolean field5;
   @Nullable
   private final Predicate<Files6> field6;

   public Files3(String var1, String var2, String var3) {
      this(var1, var2, var3, false, false);
   }

   public Files3(String var1, String var2, String var3, boolean var4, boolean var5) {
      this(var1, var2, var3, var4, var5, null);
   }

   public Files3(String var1, String var2, @Nullable String var3, boolean var4, boolean var5, @Nullable Predicate<Files6> var6) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
   }

   public Files3 method1(Files5.Extension var1) {
      String var2 = var1.method2(this.field2);
      String var3 = var1.method2(this.field3);
      return new Files3(this.field1, var2, var3, this.field4, this.field5, this.field6);
   }

   public String method2(Files5.Extension var1) {
      return this.field1 + "/" + var1.method2(this.field2);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Files3 var2 = (Files3)var1;
         return this.field1.equals(var2.field1) && this.field2.equals(var2.field2) && Objects.equals(this.field3, var2.field3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.field2, this.field3);
   }

   public boolean method3(Files6 var1) {
      return this.field6 == null || this.field6.test(var1);
   }

   public String namespace() {
      return this.field1;
   }

   public String method4() {
      return this.field2;
   }

   @Nullable
   public String url() {
      return this.field3;
   }

   public boolean method5() {
      return this.field4;
   }

   public boolean optional() {
      return this.field5;
   }

   @Nullable
   public Predicate<Files6> method6() {
      return this.field6;
   }
}

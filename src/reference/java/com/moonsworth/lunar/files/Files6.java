package com.moonsworth.lunar.files;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import lombok.Generated;

public class Files6 implements Files5.Extension {
   private final Collection<Files5> field1;
   private final Files_3 field2;
   private final Map<String, Files2_2> field3;
   private final Set<Files3> field4;
   private transient Function<Files3, Optional<Files2_2>> field5;

   public Files6(Collection<Files5> var1, Files_3 var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = new ConcurrentHashMap<>();
      this.field4 = Collections.newSetFromMap(new ConcurrentHashMap<>());
   }

   public void method1(Files2_2 var1) {
      if (var1.method1() == null) {
         throw new IllegalStateException("DataSource was null?");
      }

      this.field3.put(var1.method1().method4(), var1);
      this.field4.remove(var1.method1());
   }

   public Files2_2 method3(String var1) {
      Files2_2 var2 = this.field3.get(var1);
      if (var2 != null) {
         return var2;
      }

      if (this.field5 != null) {
         Files3 var3 = new Files3(this.field2.getNamespace(), var1, null);
         Optional var4 = this.field5.apply(var3);
         if (var4.isPresent()) {
            Files2_2 var5 = (Files2_2)var4.get();
            this.method1(var5);
            return var5;
         }
      }

      return null;
   }

   public Files2_2 method3(Files3 var1) {
      String var2 = var1.method1(this).method4();
      Files2_2 var3 = this.field3.get(var2);
      if (var3 != null) {
         return var3;
      }

      if (this.field5 != null) {
         Optional var4 = this.field5.apply(var1);
         if (var4.isPresent()) {
            Files2_2 var5 = (Files2_2)var4.get();
            this.method1(var5);
            return var5;
         }
      }

      return null;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.field2);
   }

   @Override
   public Collection<Files5> method1() {
      return this.field1;
   }

   @Generated
   public Collection<Files5> method5() {
      return this.field1;
   }

   @Generated
   public Files_3 method6() {
      return this.field2;
   }

   @Generated
   public Map<String, Files2_2> getData() {
      return this.field3;
   }

   @Generated
   public Set<Files3> method7() {
      return this.field4;
   }

   @Generated
   public Function<Files3, Optional<Files2_2>> method8() {
      return this.field5;
   }

   @Generated
   public void method9(Function<Files3, Optional<Files2_2>> var1) {
      this.field5 = var1;
   }
}

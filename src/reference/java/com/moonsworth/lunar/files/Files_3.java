package com.moonsworth.lunar.files;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import lombok.Generated;

public abstract class Files_3 {
   protected final List<Files3> field1;
   protected final Set<Files_3> parents;
   protected Function<Files5.Extension, Set<Files3>> field2 = var0 -> new HashSet<>();

   public Files_3() {
      this.field1 = new ArrayList<>();
      this.parents = new HashSet<>();
   }

   public abstract Collection<Files2_2> method1(Files6 var1);

   public abstract String getId();

   public abstract String getNamespace();

   public Set<Files3> method2(Files5.Extension var1) {
      return this.field2.apply(var1);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.getId(), this.getNamespace());
   }

   @Generated
   public List<Files3> method3() {
      return this.field1;
   }

   @Generated
   public Set<Files_3> getParents() {
      return this.parents;
   }

   @Generated
   public Function<Files5.Extension, Set<Files3>> method5() {
      return this.field2;
   }

   @Generated
   public void method6(Function<Files5.Extension, Set<Files3>> var1) {
      this.field2 = var1;
   }
}

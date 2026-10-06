package com.moonsworth.lunar.files;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public class Files4$Data {
   @Nullable
   private final Collection<Files2_2> field1;
   @Nullable
   private final Set<Files3> field2;

   public Files4$Data(@Nullable Collection<Files2_2> var1, @Nullable Set<Files3> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public void method1(Consumer<Collection<Files2_2>> var1, Consumer<Set<Files3>> var2) {
      if (this.field1 != null) {
         var1.accept(this.field1);
      } else {
         var2.accept(this.field2);
      }
   }

   @Nullable
   public Collection<Files2_2> method2() {
      return this.field1;
   }

   @Nullable
   public Set<Files3> method3() {
      return this.field2;
   }
}

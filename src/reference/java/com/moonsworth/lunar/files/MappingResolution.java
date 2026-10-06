package com.moonsworth.lunar.files;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public class MappingResolution {
   @Nullable
   private final Collection<ArtifactData> field1;
   @Nullable
   private final Set<Files3> field2;

   public MappingResolution(@Nullable Collection<ArtifactData> list1, @Nullable Set<Files3> set2) {
      this.field1 = list1;
      this.field2 = set2;
   }

   public void method1(Consumer<Collection<ArtifactData>> consumer1, Consumer<Set<Files3>> consumer2) {
      if (this.field1 != null) {
         consumer1.accept(this.field1);
      } else {
         consumer2.accept(this.field2);
      }
   }

   @Nullable
   public Collection<ArtifactData> method2() {
      return this.field1;
   }

   @Nullable
   public Set<Files3> method3() {
      return this.field2;
   }
}

package com.moonsworth.lunar.files;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MappingBundle extends Files_3 {
   private final String field3;

   public MappingBundle(String text1) {
      this.field3 = text1;
   }

   public Collection<ArtifactData> getArtifacts(Files6 files61) {
      return List.copyOf(files61.getData().values());
   }

   public String getId() {
      return "freeze/" + Objects.hash(this.method3());
   }

   public String getNamespace() {
      return this.field3;
   }

   public static MappingBundle of(String text0, Files3... items1) {
      return new PredefinedMappingBundle(text0, items1);
   }
}

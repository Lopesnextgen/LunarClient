package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.cosmetics.CosmeticIndexEntry;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;

public class CosmeticCompatibilityPreset {
   private String name;
   private CosmeticCompatibilityPreset field1;
   private Set<CosmeticIndexEntry> field2;
   private Set<CosmeticIndexEntry> field3;

   public CompiledCompatibilityPreset method1() {
      this.field3.removeAll(this.field2);
      return new CompiledCompatibilityPreset(this.name, this.field3.stream().mapToLong(CosmeticIndexEntry::getId).boxed().collect(Collectors.toSet()));
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public CosmeticCompatibilityPreset method2() {
      return this.field1;
   }

   @Generated
   public Set<CosmeticIndexEntry> method3() {
      return this.field2;
   }

   @Generated
   public Set<CosmeticIndexEntry> method4() {
      return this.field3;
   }

   @Generated
   CosmeticCompatibilityPreset(String text, CosmeticCompatibilityPreset holograms_2, Set<CosmeticIndexEntry> set, Set<CosmeticIndexEntry> set2) {
      this.name = text;
      this.field1 = holograms_2;
      this.field2 = set;
      this.field3 = set2;
   }
}

package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;

public class Holograms12 {
   private final String field1;
   private final int field2;

   public static List<Holograms12> method1(String var0) {
      return var0 == null ? null : Arrays.stream(var0.split(";")).map(Holograms12::method2).filter(Objects::nonNull).collect(Collectors.toList());
   }

   public static Holograms12 method2(String var0) {
      String[] var1 = var0.split(":");
      if (var1.length != 2) {
         return null;
      }

      try {
         return new Holograms12(var1[0], Integer.parseInt(var1[1]));
      } catch (NullPointerException var3) {
         return null;
      }
   }

   public Optional<Holograms7> method3(Holograms3_3 var1) {
      return var1.method23(this.field1).filter(var1x -> var1x.getSections().size() > this.field2).map(var1x -> var1x.getSections().get(this.field2));
   }

   public String method4() {
      return this.field1;
   }

   @Generated
   public Holograms12(String var1, int value) {
      this.field1 = var1;
      this.field2 = value;
   }

   @Generated
   public int method5() {
      return this.field2;
   }
}

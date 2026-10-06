package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Generated;

public class RouteLink {
   private final String field1;
   private final int field2;

   public static List<RouteLink> method1(String text0) {
      return text0 == null ? null : Arrays.stream(text0.split(";")).map(RouteLink::method2).filter(Objects::nonNull).collect(Collectors.toList());
   }

   public static RouteLink method2(String text0) {
      String[] items1 = text0.split(":");
      if (items1.length != 2) {
         return null;
      }

      try {
         return new RouteLink(items1[0], Integer.parseInt(items1[1]));
      } catch (NullPointerException nullpointerexception3) {
         return null;
      }
   }

   public Optional<RouteSection> method3(RouteManager holograms3_31) {
      return holograms3_31.method23(this.field1).filter(arg1x -> arg1x.getSections().size() > this.field2).map(arg1x -> arg1x.getSections().get(this.field2));
   }

   public String method4() {
      return this.field1;
   }

   @Generated
   public RouteLink(String text, int value) {
      this.field1 = text;
      this.field2 = value;
   }

   @Generated
   public int method5() {
      return this.field2;
   }
}

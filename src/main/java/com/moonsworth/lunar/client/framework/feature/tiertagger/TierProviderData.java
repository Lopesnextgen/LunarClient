package com.moonsworth.lunar.client.framework.feature.tiertagger;

import java.util.List;

public class TierProviderData {
   private final String field1;
   private final List<TierPlacement> field2;
   private final boolean field3;
   private final boolean field4;

   public TierProviderData(String text, List<TierPlacement> list, boolean flag, boolean flag2) {
      this.field1 = text;
      this.field2 = list;
      this.field3 = flag;
      this.field4 = flag2;
   }

   public boolean method1() {
      return this.field2.stream().anyMatch(arg0 -> arg0.method3() == 2);
   }

   public String method2() {
      return this.field1;
   }

   public List<TierPlacement> method3() {
      return this.field2;
   }

   public boolean method4() {
      return this.field3;
   }

   public boolean method5() {
      return this.field4;
   }
}

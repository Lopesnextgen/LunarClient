package com.moonsworth.lunar.network.mixin;

import java.util.HashSet;

public class MixinHelper6 {
   public String description;
   public String field1;
   public HashSet<String> field2 = null;

   public MixinHelper6(String text, String text2, HashSet<String> set) {
      this.description = text;
      this.field1 = text2;
      this.field2 = set;
   }
}

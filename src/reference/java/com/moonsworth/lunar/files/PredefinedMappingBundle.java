package com.moonsworth.lunar.files;

import java.util.Arrays;
import java.util.HashSet;

class PredefinedMappingBundle extends MappingBundle {
   PredefinedMappingBundle(String text1, Files3[] items2) {
      super(text1);
      this.field4 = items2;
      HashSet set3 = new HashSet<>(Arrays.asList(this.field4));
      this.field1.addAll(set3);
      this.method6(arg1x -> set3);
   }
}

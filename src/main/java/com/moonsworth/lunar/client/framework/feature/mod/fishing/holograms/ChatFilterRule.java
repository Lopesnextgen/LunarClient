package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Generated;

public class ChatFilterRule {
   private final Pattern field1;
   private final Set<String> field2;
   private final Map<String, String> field3;
   private final int field4;

   public ChatFilterRule(Pattern pattern1, Map<String, String> map, int value) {
      this.field1 = pattern1;
      this.field2 = null;
      this.field3 = map;
      this.field4 = value;
   }

   public ChatFilterRule(Pattern pattern1, Set<String> set) {
      this.field1 = pattern1;
      this.field2 = set;
      this.field3 = null;
      this.field4 = -1;
   }

   @Generated
   public Pattern method1() {
      return this.field1;
   }

   @Generated
   public Set<String> method2() {
      return this.field2;
   }

   @Generated
   public Map<String, String> method3() {
      return this.field3;
   }

   @Generated
   public int method4() {
      return this.field4;
   }
}

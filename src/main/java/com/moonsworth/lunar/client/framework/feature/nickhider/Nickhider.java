package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.lunarclient.adventure.transform.ComponentTransformer;
import java.util.regex.Pattern;
import lombok.Generated;

public class Nickhider {
   private final Pattern field1;
   private final String field2;
   private final String field3;
   private final boolean field4;
   private final ComponentTransformer field5;

   public Nickhider(Pattern pattern1, String text, String text2, boolean flag) {
      this.field1 = pattern1;
      this.field2 = text;
      this.field3 = text2;
      this.field4 = flag;
      this.field5 = ComponentTransformer.replacePattern(pattern1, text2);
   }

   @Generated
   public Pattern getPattern() {
      return this.field1;
   }

   @Generated
   public String method1() {
      return this.field2;
   }

   @Generated
   public String method2() {
      return this.field3;
   }

   @Generated
   public boolean method3() {
      return this.field4;
   }

   @Generated
   public ComponentTransformer method4() {
      return this.field5;
   }
}

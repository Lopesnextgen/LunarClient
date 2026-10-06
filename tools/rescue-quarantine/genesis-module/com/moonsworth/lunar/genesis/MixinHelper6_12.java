package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;

@GwtCompatible
public final class MixinHelper6_12 {
   static final String field1 = "-_.*";
   static final String field2 = "-._~!$'()*,;&=@:";
   private static final Escaper field3 = new MixinHelper32_3("-_.*", true);
   private static final Escaper field4 = new MixinHelper32_3("-._~!$'()*,;&=@:+", false);
   private static final Escaper field5 = new MixinHelper32_3("-._~!$'()*,;&=@:+/?", false);

   private MixinHelper6_12() {
   }

   public static Escaper method1() {
      return field3;
   }

   public static Escaper method2() {
      return field4;
   }

   public static Escaper method3() {
      return field5;
   }
}

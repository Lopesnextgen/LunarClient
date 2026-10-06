package com.moonsworth.lunar.client.util;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class MixinHelper25 implements MixinHelper2 {
   private final List<String> field1;

   @Override
   public String method1(int var1) {
      StringBuilder var2 = new StringBuilder();
      boolean var3 = true;

      for (String var5 : this.field1) {
         if (var3) {
            var3 = false;
         } else {
            var2.append("\n");
         }

         var2.append(Util.method8(var5, var1));
      }

      return var2.toString();
   }

   public static MixinHelper25.Data2 method2(MixinHelper25.Type3 type3) {
      return new MixinHelper25.Data2(type3);
   }

   @Generated
   private MixinHelper25(List<String> var1) {
      this.field1 = var1;
   }

   public static class Data2 {
      private final MixinHelper25.Type3 field1;
      private final List<String> field2 = new ArrayList<>();
      private final List<String> field3 = new ArrayList<>();

      public Data2(MixinHelper25.Type3 var1) {
         this.field1 = var1;
      }

      public MixinHelper25.Data2 method1(String var1) {
         if (var1.charAt(var1.length() - 1) != '.') {
            var1 = var1 + ".";
         }

         this.field2.add(" * " + var1);
         return this;
      }

      public MixinHelper25.Data2 method2(String... var1) {
         for (String var5 : var1) {
            this.method1(var5);
         }

         return this;
      }

      public MixinHelper25.Data2 method3(MixinHelper25.Type2 var1, String var2) {
         if (!var1.validTypes.contains(this.field1)) {
            throw new RuntimeException("JavaDoc tag cannot be applied to this type: `" + var1.name() + "` -> `" + this.field1.name() + "`");
         }

         this.field3.add(" * @" + var1.name().toLowerCase() + " " + var2);
         return this;
      }

      public MixinHelper25 method4() {
         ArrayList var1 = new ArrayList();
         var1.add("/**");
         if (!this.field2.isEmpty()) {
            var1.addAll(this.field2);
         } else {
            var1.add(" * No documentation available.");
            var1.add(" *");
         }

         if (!this.field3.isEmpty()) {
            if (!this.field2.isEmpty()) {
               var1.add(" *");
            }

            var1.addAll(this.field3);
         }

         var1.add(" */");
         return new MixinHelper25(var1);
      }
   }

   public enum Type2 {
      DEPRECATED(MixinHelper25.Type3.CLASS, MixinHelper25.Type3.INTERFACE, MixinHelper25.Type3.ENUM, MixinHelper25.Type3.FIELD, MixinHelper25.Type3.METHOD),
      SEE(MixinHelper25.Type3.CLASS, MixinHelper25.Type3.INTERFACE, MixinHelper25.Type3.ENUM, MixinHelper25.Type3.FIELD, MixinHelper25.Type3.METHOD),
      PARAM(MixinHelper25.Type3.METHOD),
      RETURN(MixinHelper25.Type3.METHOD),
      EXCEPTION(MixinHelper25.Type3.METHOD),
      THROWS(MixinHelper25.Type3.METHOD),
      AUTHOR(MixinHelper25.Type3.CLASS, MixinHelper25.Type3.INTERFACE, MixinHelper25.Type3.ENUM, MixinHelper25.Type3.METHOD),
      VERSION(MixinHelper25.Type3.CLASS, MixinHelper25.Type3.INTERFACE, MixinHelper25.Type3.ENUM),
      SINCE(MixinHelper25.Type3.CLASS, MixinHelper25.Type3.INTERFACE, MixinHelper25.Type3.ENUM, MixinHelper25.Type3.FIELD, MixinHelper25.Type3.METHOD);

      private final List<MixinHelper25.Type3> validTypes;

      Type2(MixinHelper25.Type3... var3) {
         this.validTypes = List.of(var3);
      }

      @Generated
      public List<MixinHelper25.Type3> getValidTypes() {
         return this.validTypes;
      }
   }

   public enum Type3 {
      CLASS,
      INTERFACE,
      ENUM,
      FIELD,
      METHOD;
   }
}

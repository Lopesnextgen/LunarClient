package com.moonsworth.lunar.client.util;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class MixinHelper23 implements MixinHelper2 {
   private final String field1;
   private final List<MixinHelper24<MixinHelper2>> field2;

   @Override
   public String method1(int var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("@").append(this.field1);
      if (!this.field2.isEmpty()) {
         var2.append("(");
         boolean var3 = this.field2.size() >= 2;
         boolean var4 = false;
         if (var3) {
            var2.append("\n");
         } else if (this.field2.get(0).method2().equals("value")) {
            var4 = true;
         }

         for (int var5 = 0; var5 < this.field2.size(); var5++) {
            MixinHelper24 var6 = this.field2.get(var5);
            if (!var4) {
               var2.append(Util.method7(var1 + 1));
               var2.append(var6.method2()).append(" = ");
            }

            var2.append(((MixinHelper2)var6.method3()).method1(var1 + 1));
            if (this.field2.size() > 1 && var5 != this.field2.size()) {
               var2.append(",\n");
            }
         }

         if (var3) {
            var2.append(Util.method7(var1));
         }

         var2.append(")");
      }

      return var2.toString();
   }

   public static MixinHelper23.Data3 method2(String text) {
      return new MixinHelper23.Data3(text);
   }

   @Generated
   private MixinHelper23(String var1, List<MixinHelper24<MixinHelper2>> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   private static class Data2 implements MixinHelper2 {
      private final String field1;
      private final boolean field2;

      @Override
      public String method1(int var1) {
         return this.field2 ? "\"" + this.field1 + "\"" : this.field1;
      }

      @Generated
      public Data2(String var1, boolean var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }

   public static class Data3 {
      private final String field1;
      private final Map<String, MixinHelper24<MixinHelper2>> field2 = new HashMap<>();

      public MixinHelper23.Data3 method1(String var1, String var2) {
         this.field2.put(var1, MixinHelper24.method4(var1, new MixinHelper23.Data2(var2, true)));
         return this;
      }

      public MixinHelper23.Data3 method2(String var1, MixinHelper23 var2) {
         this.field2.put(var1, MixinHelper24.method4(var1, var2));
         return this;
      }

      public MixinHelper23.Data3 method3(String var1, MixinHelper23.Data3 var2) {
         this.field2.put(var1, MixinHelper24.method4(var1, var2.method9()));
         return this;
      }

      public MixinHelper23.Data3 method4(String var1) {
         return this.method1("value", var1);
      }

      public MixinHelper23.Data3 method5(MixinHelper23 var1) {
         return this.method2("value", var1);
      }

      public MixinHelper23.Data3 method6(MixinHelper23.Data3 var1) {
         return this.method3("value", var1);
      }

      public MixinHelper23.Data3 method7(MixinHelper24<?> var1) {
         Object var3 = var1.method3();
         MixinHelper24 var2;
         if (var3 instanceof String var4) {
            var2 = MixinHelper24.method4(var1.method2(), new MixinHelper23.Data2(var4, true));
         } else if (var3 instanceof MixinHelper23) {
            var2 = var1;
         } else if (var3 instanceof MixinHelper23.Data3 var5) {
            var2 = MixinHelper24.method4(var1.method2(), var5.method9());
         } else {
            var2 = MixinHelper24.method4(var1.method2(), new MixinHelper23.Data2(var3.toString(), false));
         }

         this.field2.put(var2.method2(), var2);
         return this;
      }

      public MixinHelper23.Data3 method8(MixinHelper24<?>... var1) {
         for (MixinHelper24 var5 : var1) {
            this.method7(var5);
         }

         return this;
      }

      public MixinHelper23 method9() {
         return new MixinHelper23(this.field1, ImmutableList.copyOf(this.field2.values()));
      }

      @Generated
      public Data3(String var1) {
         this.field1 = var1;
      }
   }
}

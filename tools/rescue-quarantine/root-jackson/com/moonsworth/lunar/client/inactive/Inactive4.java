package com.moonsworth.lunar.client.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.eliotlash.molang.ast.Evaluator;
import com.eliotlash.molang.ast.Expr.Constant;
import com.moonsworth.lunar.IOException22;
import com.moonsworth.lunar.MixinHelper102_4;
import com.moonsworth.lunar.MixinHelper3222;
import com.moonsworth.lunar.MixinHelper3234;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.MixinHelper73_3;
import com.moonsworth.lunar.MixinHelper9_9;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate2;
import com.moonsworth.lunar.client.inactive.mixin.colorsaturation.mixin.Colorsaturation;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Inactive4 {
   private final List<Pair<Horsestats14, Evaluatable>> field1;

   public Inactive4(List<Pair<Horsestats14, Evaluatable>> var1) {
      this.field1 = var1;
   }

   public Inactive4(String var1) {
      this.field1 = new ArrayList<>();
      Horsestats14 var2 = Horsestats14.create(var1);
      var2 = Nameplate2.method2(var2);
      this.field1.add(Pair.of(var2, Evaluatable.of(new Constant(1.0))));
   }

   public Horsestats14 method1(Evaluator var1) {
      if (var1 != null) {
         for (Pair var3 : this.field1) {
            if (((Evaluatable)var3.second()).evaluate(var1) == 1.0) {
               return (Horsestats14)var3.first();
            }
         }
      }

      return (Horsestats14)this.field1.get(0).first();
   }

   public List<Pair<Horsestats14, Evaluatable>> method2() {
      return this.field1;
   }

   public static class Data extends MixinHelper102_4<Inactive4> {
      public Inactive4 method1(MixinHelper53 var1, MixinHelper73_3 var2) {
         MixinHelper9_9 var3 = var1.method98();
         if (var3 instanceof MixinHelper3234 var10) {
            return new Inactive4(var10.method39());
         } else if (var3 instanceof MixinHelper3222 var4) {
            ArrayList var5 = new ArrayList();
            Iterator var6 = var4.method52();

            while (var6.hasNext()) {
               Entry var7 = (Entry)var6.next();
               if (!(var7.getValue() instanceof MixinHelper3234 var8)) {
                  throw new IOException22(var1, "Expected a molang string");
               }

               Horsestats14 var11 = Horsestats14.create((String)var7.getKey());
               var11 = Nameplate2.method2(var11);
               var5.add(Pair.of(var11, Colorsaturation.method10(var8.method39())));
            }

            return new Inactive4(var5);
         } else {
            throw new IOException22(var1, "Expected a string or an object for geckolib molang resource provider");
         }
      }
   }
}

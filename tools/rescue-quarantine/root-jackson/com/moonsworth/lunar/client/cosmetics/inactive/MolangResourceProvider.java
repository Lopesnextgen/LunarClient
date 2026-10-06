package com.moonsworth.lunar.client.cosmetics.inactive;

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
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.colorsaturation.mixin.AnimationKeyframeParser;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class MolangResourceProvider {
   private final List<Pair<Horsestats14, Evaluatable>> field1;

   public MolangResourceProvider(List<Pair<Horsestats14, Evaluatable>> list1) {
      this.field1 = list1;
   }

   public MolangResourceProvider(String text1) {
      this.field1 = new ArrayList<>();
      Horsestats14 horsestats142 = Horsestats14.create(text1);
      horsestats142 = Nameplate2.method2(horsestats142);
      this.field1.add(Pair.of(horsestats142, Evaluatable.of(new Constant(1.0))));
   }

   public Horsestats14 method1(Evaluator evaluator1) {
      if (evaluator1 != null) {
         for (Pair pair3 : this.field1) {
            if (((Evaluatable)pair3.second()).evaluate(evaluator1) == 1.0) {
               return (Horsestats14)pair3.first();
            }
         }
      }

      return (Horsestats14)this.field1.get(0).first();
   }

   public List<Pair<Horsestats14, Evaluatable>> method2() {
      return this.field1;
   }

   public static class MolangResourceLoader extends MixinHelper102_4<MolangResourceProvider> {
      public MolangResourceLoader() {
      }

      public MolangResourceProvider method1(MixinHelper53 mixinhelper531, MixinHelper73_3 mixinhelper73_32) {
         MixinHelper9_9 mixinhelper9_93 = mixinhelper531.method98();
         if (mixinhelper9_93 instanceof MixinHelper3234 mixinhelper323410) {
            return new MolangResourceProvider(mixinhelper323410.method39());
         } else if (mixinhelper9_93 instanceof MixinHelper3222 mixinhelper32224) {
            ArrayList list5 = new ArrayList();
            Iterator iterator6 = mixinhelper32224.method52();

            while (iterator6.hasNext()) {
               Entry entry7 = (Entry)iterator6.next();
               if (!(entry7.getValue() instanceof MixinHelper3234 mixinhelper32348)) {
                  throw new IOException22(mixinhelper531, "Expected a molang string");
               }

               Horsestats14 horsestats1411 = Horsestats14.create((String)entry7.getKey());
               horsestats1411 = Nameplate2.method2(horsestats1411);
               list5.add(Pair.of(horsestats1411, AnimationKeyframeParser.method10(mixinhelper32348.method39())));
            }

            return new MolangResourceProvider(list5);
         } else {
            throw new IOException22(mixinhelper531, "Expected a string or an object for geckolib molang resource provider");
         }
      }
   }
}

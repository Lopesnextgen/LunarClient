package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModSearchIndex;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Generated;

public class FeatureIndex implements ModSearchIndex {
   private final List<String> field1 = new ArrayList<>();
   private final List<String> field2 = new ArrayList<>();

   public FeatureIndex() {
   }

   @Override
   public void method3(Framework7Extension framework7) {
      if (this.field1.isEmpty()) {
         Pair pair2 = ModSearchIndex.method8(framework7);
         this.field1.addAll((Collection<? extends String>)pair2.first());
         this.field2.addAll((Collection<? extends String>)pair2.second());
      }
   }

   @Override
   public boolean method4(String text1) {
      text1 = text1.toLowerCase();
      return this.method5(text1) || this.method6(text1);
   }

   @Override
   public boolean method5(String text1) {
      for (String text3 : this.method1()) {
         if (text3.startsWith(text1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean method6(String text1) {
      if ((Boolean)Ref.method4().method41().method6().method52().get()) {
         for (String text3 : this.method2()) {
            if (text3.startsWith(text1)) {
               return true;
            }
         }
      }

      return false;
   }

   @Generated
   @Override
   public List<String> method1() {
      return this.field1;
   }

   @Generated
   @Override
   public List<String> method2() {
      return this.field2;
   }
}

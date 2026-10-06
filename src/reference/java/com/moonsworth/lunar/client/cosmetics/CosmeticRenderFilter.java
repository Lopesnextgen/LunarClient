package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public class CosmeticRenderFilter {
   private final Set<RenderScope> field1;
   private final List<Integer> field2;
   private final List<Integer> field3;
   private final List<Integer> field4;

   public boolean method1(int number1) {
      return this.field2.contains(number1);
   }

   public boolean method2(int number1) {
      return this.field3.contains(number1);
   }

   public boolean method3(int number1) {
      return this.field4.contains(number1);
   }

   public boolean method4(RenderScope renderScope) {
      return this.field1.contains(renderScope);
   }

   public static boolean method5() {
      CosmeticRenderFilter mixinextra0 = Ref.method4().method84().method25();
      return mixinextra0 != null && mixinextra0.method4(RenderScope.RENDER_COSMETICS_EQUIPPED);
   }

   public static List<Integer> method6(List<Integer> list) {
      CosmeticRenderFilter mixinextra1 = Ref.method4().method84().method25();
      if (mixinextra1 == null) {
         return null;
      } else if (mixinextra1.method4(RenderScope.RENDER_COSMETICS_ALL)) {
         return list;
      } else {
         return mixinextra1.method4(RenderScope.RENDER_COSMETICS) ? list.stream().filter(mixinextra1::method1).toList() : null;
      }
   }

   public static boolean method7(int number0) {
      CosmeticRenderFilter mixinextra1 = Ref.method4().method84().method25();
      if (mixinextra1 == null) {
         return false;
      } else {
         return mixinextra1.method4(RenderScope.RENDER_SPRAYS_ALL) ? true : mixinextra1.method4(RenderScope.RENDER_SPRAYS) && mixinextra1.method3(number0);
      }
   }

   public static boolean method8(int number0) {
      CosmeticRenderFilter mixinextra1 = Ref.method4().method84().method25();
      if (mixinextra1 == null) {
         return false;
      } else {
         return mixinextra1.method4(RenderScope.RENDER_EMOTES_ALL) ? true : mixinextra1.method4(RenderScope.RENDER_EMOTES) && mixinextra1.method2(number0);
      }
   }

   @Generated
   @Override
   public String toString() {
      return "CosmeticRenderFilter(scopes="
         + this.field1
         + ", associatedCosmeticIds="
         + this.field2
         + ", associatedEmoteIds="
         + this.field3
         + ", associatedSprayIds="
         + this.field4
         + ")";
   }

   @Generated
   public CosmeticRenderFilter(Set<RenderScope> set, List<Integer> list, List<Integer> list2, List<Integer> list3) {
      this.field1 = set;
      this.field2 = list;
      this.field3 = list2;
      this.field4 = list3;
   }
}

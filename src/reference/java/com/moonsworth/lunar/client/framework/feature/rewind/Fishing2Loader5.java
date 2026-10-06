package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader5<T extends Number & Comparable<T>> extends Fishing2Loader<MultiNumberOption<T>, List<T>> {
   public Fishing2Loader5(Nameplate2 var1, MultiNumberOption<T> var2) {
      super(var1, var2);
   }

   protected List<T> method1(List<T> var1) {
      return var1 == null ? null : new ArrayList<>(var1);
   }

   public List<T> method2(MultiNumberOption<T> var1) {
      List var2 = (List)var1.get();
      return var2 == null ? null : new ArrayList<>(var2);
   }

   public void method3(MultiNumberOption<T> var1, List<T> var2) {
      var1.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2 == null ? null : new ArrayList(var2));
   }

   public List<T> method4(ThreadModuleDump18 var1, List<T> var2, List<T> var3, List<T> var4, List<T> var5, float var6) {
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < var3.size(); var8++) {
         var7.add((Number)Fishing3.method1(var1, (Number)var2.get(var8), (Number)var3.get(var8), (Number)var4.get(var8), (Number)var5.get(var8), var6));
      }

      return var7;
   }

   public Fishing2Loader5<T> method5(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader5 var4 = new Fishing2Loader5(var1, (MultiNumberOption<T>)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (Fishing2Loader5<T>)this.method2(var4, var1, var2, var3);
   }

   @Override
   public String type() {
      return ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).getId();
   }
}

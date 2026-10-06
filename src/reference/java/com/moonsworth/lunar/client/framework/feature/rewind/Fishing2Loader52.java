package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader52 extends Fishing2Loader5<Double> {
   private boolean field13 = false;

   public Fishing2Loader52(Nameplate2 var1, MultiNumberOption<Double> var2) {
      super(var1, var2);
   }

   @Override
   public List<Double> method4(ThreadModuleDump18 var1, List<Double> var2, List<Double> var3, List<Double> var4, List<Double> var5, float var6) {
      ArrayList var7 = new ArrayList();

      for (int var8 = 0; var8 < 3; var8++) {
         var7.add((Double)Fishing3.method1(var1, (Double)var2.get(var8), (Double)var3.get(var8), (Double)var4.get(var8), (Double)var5.get(var8), var6));
      }

      double var16 = Gui2.method2((Double)var2.get(3));
      double var10 = Gui2.method2((Double)var3.get(3));
      double var12 = Gui2.method2((Double)var4.get(3));
      double var14 = Gui2.method2((Double)var5.get(3));
      var16 = Gui2.method1(var16, var10);
      var12 = Gui2.method1(var12, var10);
      var14 = Gui2.method1(var14, var12);
      var7.add(Fishing3.method1(var1, var16, var10, var12, var14, var6));
      var7.add((Double)Fishing3.method1(var1, (Double)var2.get(4), (Double)var3.get(4), (Double)var4.get(4), (Double)var5.get(4), var6));
      if (var2.size() >= 6) {
         var7.add((Double)Fishing3.method1(var1, (Double)var2.get(5), (Double)var3.get(5), (Double)var4.get(5), (Double)var5.get(5), var6));
      }

      return var7;
   }

   @Override
   public void method12(ThreadModuleDump6<Nameplate4> var1, int var2) {
      RewindHandlers var3 = ((Nameplate4)var1.get()).method6();
      boolean var4 = var3.method44() != this.field13;
      this.field13 = var3.method44();
      if (!var4 && var3.method44() && var3.method26()) {
         this.CCIOIOCOCCRHHRCHCHCRICIORHORII(var2);
         if (!Objects.deepEquals(
            ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).get(), ((MultiNumberOption)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR).get()
         )) {
            if (this.method31() != null
               && (this.method27().size() > 1 || !this.method27().containsKey(Integer.MIN_VALUE))
               && !this.method9(this.method31())) {
               this.method30(null);
            }

            ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH)
               .OIRHOOIICOCIOOHICRRRICORIHHIHC(new ArrayList((Collection)((MultiNumberOption)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR).get()));
         }
      } else {
         if (var3.method44()) {
            super.method2(var1, var2);
         }
      }
   }

   public Fishing2Loader52 method3(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader52 var4 = new Fishing2Loader52(var1, (MultiNumberOption<Double>)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (Fishing2Loader52)this.method3(var4, var1, var2, var3);
   }

   @Override
   public void method3() {
   }

   @Override
   protected ThreadModuleDump73Type method25() {
      return ThreadModuleDump73Type.CATMULL_ROM;
   }
}

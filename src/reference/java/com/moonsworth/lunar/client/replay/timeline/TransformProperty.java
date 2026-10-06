package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.NumberInterpolator;
import com.moonsworth.lunar.client.replay.timeline.AngleUtils;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.Easing;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.config.option.InterpolationMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class TransformProperty extends ListProperty<Double> {
   private boolean field13 = false;

   public TransformProperty(UndoRedoManager nameplate21, MultiNumberOption<Double> lightingextension4942) {
      super(nameplate21, lightingextension4942);
   }

   @Override
   public List<Double> method4(Easing threadmoduledump181, List<Double> list2, List<Double> list3, List<Double> list4, List<Double> list5, float value6) {
      ArrayList list7 = new ArrayList();

      for (int index8 = 0; index8 < 3; index8++) {
         list7.add((Double)NumberInterpolator.method1(threadmoduledump181, (Double)list2.get(index8), (Double)list3.get(index8), (Double)list4.get(index8), (Double)list5.get(index8), value6));
      }

      double value16 = AngleUtils.method2((Double)list2.get(3));
      double value10 = AngleUtils.method2((Double)list3.get(3));
      double value12 = AngleUtils.method2((Double)list4.get(3));
      double value14 = AngleUtils.method2((Double)list5.get(3));
      value16 = AngleUtils.method1(value16, value10);
      value12 = AngleUtils.method1(value12, value10);
      value14 = AngleUtils.method1(value14, value12);
      list7.add(NumberInterpolator.method1(threadmoduledump181, value16, value10, value12, value14, value6));
      list7.add((Double)NumberInterpolator.method1(threadmoduledump181, (Double)list2.get(4), (Double)list3.get(4), (Double)list4.get(4), (Double)list5.get(4), value6));
      if (list2.size() >= 6) {
         list7.add((Double)NumberInterpolator.method1(threadmoduledump181, (Double)list2.get(5), (Double)list3.get(5), (Double)list4.get(5), (Double)list5.get(5), value6));
      }

      return list7;
   }

   @Override
   public void method12(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      RewindHandlers rewindhandlers3 = ((ReplayContext)threadmoduledump61.get()).method6();
      boolean flag4 = rewindhandlers3.method44() != this.field13;
      this.field13 = rewindhandlers3.method44();
      if (!flag4 && rewindhandlers3.method44() && rewindhandlers3.method26()) {
         this.CCIOIOCOCCRHHRCHCHCRICIORHORII(number2);
         if (!Objects.deepEquals(
            ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).get(), ((MultiNumberOption)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR).get()
         )) {
            if (this.IOOOHRHCIIRRHOIHIHRHHIHOCRHHCO() != null
               && (this.CORHOROOHORCHOHICCOOOCRICHRRHC().size() > 1 || !this.CORHOROOHORCHOHICCOOOCRICHRRHC().containsKey(Integer.MIN_VALUE))
               && !this.OCHCRRHHCHHOHRHCCCICOCHIRHRIRI(this.IOOOHRHCIIRRHOIHIHRHHIHOCRHHCO())) {
               this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(null);
            }

            ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH)
               .OIRHOOIICOCIOOHICRRRICORIHHIHC(new ArrayList((Collection)((MultiNumberOption)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR).get()));
         }
      } else {
         if (rewindhandlers3.method44()) {
            super.method2(threadmoduledump61, number2);
         }
      }
   }

   public TransformProperty method3(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      TransformProperty fishing2loader524 = new TransformProperty(nameplate21, (MultiNumberOption<Double>)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (TransformProperty)this.method3(fishing2loader524, nameplate21, range2, range3);
   }

   @Override
   public void method3() {
   }

   @Override
   protected InterpolationMode method25() {
      return InterpolationMode.CATMULL_ROM;
   }
}

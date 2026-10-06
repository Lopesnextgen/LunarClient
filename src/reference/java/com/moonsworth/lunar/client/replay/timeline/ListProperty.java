package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.NumberInterpolator;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.config.option.MultiNumberOption;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.Easing;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class ListProperty<T extends Number & Comparable<T>> extends KeyframeProperty<MultiNumberOption<T>, List<T>> {
   public ListProperty(UndoRedoManager nameplate21, MultiNumberOption<T> lightingextension4942) {
      super(nameplate21, lightingextension4942);
   }

   protected List<T> method1(List<T> list1) {
      return list1 == null ? null : new ArrayList<>(list1);
   }

   public List<T> method2(MultiNumberOption<T> lightingextension4941) {
      List list2 = (List)lightingextension4941.get();
      return list2 == null ? null : new ArrayList<>(list2);
   }

   public void method3(MultiNumberOption<T> lightingextension4941, List<T> list2) {
      lightingextension4941.OIRHOOIICOCIOOHICRRRICORIHHIHC(list2 == null ? null : new ArrayList(list2));
   }

   public List<T> method4(Easing threadmoduledump181, List<T> list2, List<T> list3, List<T> list4, List<T> list5, float value6) {
      ArrayList list7 = new ArrayList();

      for (int index8 = 0; index8 < list3.size(); index8++) {
         list7.add((Number)NumberInterpolator.method1(threadmoduledump181, (Number)list2.get(index8), (Number)list3.get(index8), (Number)list4.get(index8), (Number)list5.get(index8), value6));
      }

      return list7;
   }

   public ListProperty<T> method5(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      ListProperty fishing2loader54 = new ListProperty(nameplate21, (MultiNumberOption<T>)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (ListProperty<T>)this.method2(fishing2loader54, nameplate21, range2, range3);
   }

   @Override
   public String type() {
      return ((MultiNumberOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).getId();
   }
}

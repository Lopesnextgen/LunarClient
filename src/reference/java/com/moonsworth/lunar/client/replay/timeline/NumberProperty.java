package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.NumberInterpolator;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.Easing;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class NumberProperty<T extends Number & Comparable<T>> extends KeyframeProperty<ClientOption<T>, T> {
   public NumberProperty(UndoRedoManager nameplate21, ClientOption<T> lightingextension2) {
      super(nameplate21, lightingextension2);
   }

   public NumberProperty<T> method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      NumberProperty fishing2loader34 = new NumberProperty(nameplate21, this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (NumberProperty<T>)this.method2(fishing2loader34, nameplate21, range2, range3);
   }

   public T method2(Easing threadmoduledump181, T value2, T value3, T value4, T value5, float value6) {
      return NumberInterpolator.method1(threadmoduledump181, (T)value2, (T)value3, (T)value4, (T)value5, value6);
   }

   @Override
   public String type() {
      return this.ICHRCHIOIIROHIHROICCORICOCCIHH.getId();
   }
}

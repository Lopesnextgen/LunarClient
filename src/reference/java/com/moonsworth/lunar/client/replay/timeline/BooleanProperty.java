package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.Easing;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class BooleanProperty<T> extends KeyframeProperty<ClientOption<T>, T> {
   public BooleanProperty(UndoRedoManager nameplate21, ClientOption<T> lightingextension2) {
      super(nameplate21, lightingextension2);
   }

   @Override
   public T method10(Easing threadmoduledump181, T value2, T value3, T value4, T value5, float value6) {
      return (T)(value6 == 1.0 ? value4 : value3);
   }

   public BooleanProperty<T> method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      BooleanProperty fishing2loader24 = new BooleanProperty(nameplate21, this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (BooleanProperty<T>)this.method10(fishing2loader24, nameplate21, range2, range3);
   }

   @Override
   public String type() {
      return this.ICHRCHIOIIROHIHROICCORICOCCIHH.getId();
   }
}

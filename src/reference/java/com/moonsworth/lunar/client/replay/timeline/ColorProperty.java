package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.NumberInterpolator;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.math.ColorMath;
import com.moonsworth.lunar.client.util.math.Easing;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class ColorProperty extends KeyframeProperty<ColorOption, ColorProperty.ColorValue> {
   public ColorProperty(UndoRedoManager nameplate21, ColorOption lightingextension42222) {
      super(nameplate21, lightingextension42222);
      ((ColorOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).method19().HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method1());
      ((ColorOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).method21().HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method1());
      ((ColorOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).method23().HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method1());
   }

   public ColorProperty method2(UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3) {
      ColorProperty fishing2loader44 = new ColorProperty(nameplate21, (ColorOption)this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (ColorProperty)this.method3(fishing2loader44, nameplate21, range2, range3);
   }

   public ColorProperty.ColorValue method2(
      Easing threadmoduledump181, ColorProperty.ColorValue data2, ColorProperty.ColorValue data3, ColorProperty.ColorValue data4, ColorProperty.ColorValue data5, float value6
   ) {
      int number7 = NumberInterpolator.method1(threadmoduledump181, data2.field1 >> 24 & 0xFF, data3.field1 >> 24 & 0xFF, data4.field1 >> 24 & 0xFF, data5.field1 >> 24 & 0xFF, value6);
      com.moonsworth.lunar.client.util.math.ColorMath.Data data8 = ColorMath.method2(data2.field1);
      com.moonsworth.lunar.client.util.math.ColorMath.Data data9 = ColorMath.method2(data3.field1);
      com.moonsworth.lunar.client.util.math.ColorMath.Data data10 = ColorMath.method2(data4.field1);
      com.moonsworth.lunar.client.util.math.ColorMath.Data data11 = ColorMath.method2(data5.field1);
      double value12 = NumberInterpolator.method1(threadmoduledump181, data8.method1(), data9.method1(), data10.method1(), data11.method1(), value6);
      double value14 = NumberInterpolator.method1(threadmoduledump181, data8.method2(), data9.method2(), data10.method2(), data11.method2(), value6);
      double value16 = ColorMath.method1(data9.method3(), data10.method3(), value6);
      int number18 = ColorMath.method3(value12, value14, value16, number7);
      return new ColorProperty.ColorValue(number18, data3.field2, data3.field3, data3.field4);
   }

   protected ColorProperty.ColorValue method3(ColorOption lightingextension42221) {
      return new ColorProperty.ColorValue(
         lightingextension42221.method8(),
         lightingextension42221.method19().RCHCHCOOIOCHCRORRHCHHRCHHRRRCR(),
         lightingextension42221.method21().CHCRHHCOIIOHICIICHHIOCCHICRIOC(),
         (com.moonsworth.lunar.client.render.color.ColorAnimation)lightingextension42221.method23().getDefaultValue()
      );
   }

   public ColorProperty.ColorValue method4(ColorOption lightingextension42221) {
      return new ColorProperty.ColorValue(
         lightingextension42221.method13(),
         (Boolean)lightingextension42221.method19().get(),
         (Integer)lightingextension42221.method21().get(),
         (com.moonsworth.lunar.client.render.color.ColorAnimation)lightingextension42221.method23().get()
      );
   }

   public void method5(ColorOption lightingextension42221, ColorProperty.ColorValue data2) {
      lightingextension42221.method1(data2.field1);
      lightingextension42221.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(data2.field2);
      lightingextension42221.method21().method1(data2.field3);
      lightingextension42221.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(data2.field4);
   }

   @Override
   public String type() {
      return ((ColorOption)this.ICHRCHIOIIROHIHROICCORICOCCIHH).getId();
   }

   public class ColorValue {
      private final int field1;
      private final boolean field2;
      private final int field3;
      private final com.moonsworth.lunar.client.render.color.ColorAnimation field4;

      public ColorValue(int number1, boolean flag2, int number3, com.moonsworth.lunar.client.render.color.ColorAnimation gui2extension4) {
         this.field1 = number1;
         this.field2 = flag2;
         this.field3 = number3;
         this.field4 = gui2extension4;
      }

      public int value() {
         return this.field1;
      }

      public boolean method1() {
         return this.field2;
      }

      public int method2() {
         return this.field3;
      }

      public com.moonsworth.lunar.client.render.color.ColorAnimation method3() {
         return this.field4;
      }
   }
}

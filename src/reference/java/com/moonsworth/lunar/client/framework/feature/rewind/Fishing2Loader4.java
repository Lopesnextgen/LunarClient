package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump17;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader4 extends Fishing2Loader<ColorOption, Fishing2Loader4.Data> {
   public Fishing2Loader4(Nameplate2 var1, ColorOption var2) {
      super(var1, var2);
      ((ColorOption)this.field1).method19().HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method1());
      ((ColorOption)this.field1).method21().HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method1());
      ((ColorOption)this.field1).method23().HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method1());
   }

   public Fishing2Loader4 method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader4 var4 = new Fishing2Loader4(var1, (ColorOption)this.field2);
      return (Fishing2Loader4)this.method3(var4, var1, var2, var3);
   }

   public Fishing2Loader4.Data method2(
      ThreadModuleDump18 var1, Fishing2Loader4.Data var2, Fishing2Loader4.Data var3, Fishing2Loader4.Data var4, Fishing2Loader4.Data var5, float var6
   ) {
      int var7 = Fishing3.method1(var1, var2.field1 >> 24 & 0xFF, var3.field1 >> 24 & 0xFF, var4.field1 >> 24 & 0xFF, var5.field1 >> 24 & 0xFF, var6);
      ThreadModuleDump17.Data var8 = ThreadModuleDump17.rgbToLch(var2.field1);
      ThreadModuleDump17.Data var9 = ThreadModuleDump17.rgbToLch(var3.field1);
      ThreadModuleDump17.Data var10 = ThreadModuleDump17.rgbToLch(var4.field1);
      ThreadModuleDump17.Data var11 = ThreadModuleDump17.rgbToLch(var5.field1);
      double var12 = Fishing3.method1(var1, var8.method1(), var9.method1(), var10.method1(), var11.method1(), var6);
      double var14 = Fishing3.method1(var1, var8.method2(), var9.method2(), var10.method2(), var11.method2(), var6);
      double var16 = ThreadModuleDump17.interpolateHue(var9.method3(), var10.method3(), var6);
      int var18 = ThreadModuleDump17.lchToRgb(var12, var14, var16, var7);
      return new Fishing2Loader4.Data(var18, var3.field2, var3.field3, var3.field4);
   }

   protected Fishing2Loader4.Data method3(ColorOption var1) {
      return new Fishing2Loader4.Data(
         var1.method13(),
         var1.method19().RCHCHCOOIOCHCRORRHCHHRCHHRRRCR(),
         var1.method21().method13(),
         var1.method23().getDefaultValue()
      );
   }

   public Fishing2Loader4.Data method4(ColorOption var1) {
      return new Fishing2Loader4.Data(var1.method13(), var1.method19().get(), var1.method21().get(), var1.method23().get());
   }

   public void method5(ColorOption var1, Fishing2Loader4.Data var2) {
      var1.method1(Integer.valueOf(var2.field1));
      var1.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.field2);
      var1.method21().method1(var2.field3);
      var1.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.field4);
   }

   @Override
   public String type() {
      return ((ColorOption)this.field1).getId();
   }

   public class Data {
      private final int field1;
      private final boolean field2;
      private final int field3;
      private final com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension field4;

      public Data(int var1, boolean var2, int var3, com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
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

      public com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension method3() {
         return this.field4;
      }
   }
}

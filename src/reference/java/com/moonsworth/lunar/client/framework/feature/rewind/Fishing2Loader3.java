package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader3<T extends Number & Comparable<T>> extends Fishing2Loader<ClientOption<T>, T> {
   public Fishing2Loader3(Nameplate2 var1, ClientOption<T> var2) {
      super(var1, var2);
   }

   public Fishing2Loader3<T> method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader3 var4 = new Fishing2Loader3(var1, this.HHIHOCCIIRHHOCOHIHHRRCICCRIRRR);
      return (Fishing2Loader3<T>)this.method2(var4, var1, var2, var3);
   }

   public T method2(ThreadModuleDump18 var1, T var2, T var3, T var4, T var5, float var6) {
      return Fishing3.method1(var1, (T)var2, (T)var3, (T)var4, (T)var5, var6);
   }

   @Override
   public String type() {
      return this.ICHRCHIOIIROHIHROICCORICOCCIHH.getId();
   }
}

package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump18;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Loader2<T> extends Fishing2Loader<ClientOption<T>, T> {
   public Fishing2Loader2(Nameplate2 var1, ClientOption<T> var2) {
      super(var1, var2);
   }

   @Override
   public T method10(ThreadModuleDump18 var1, T var2, T var3, T var4, T var5, float var6) {
      return (T)(var6 == 1.0 ? var4 : var3);
   }

   public Fishing2Loader2<T> method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Fishing2Loader2 var4 = new Fishing2Loader2(var1, this.field2);
      return (Fishing2Loader2<T>)this.method10(var4, var1, var2, var3);
   }

   @Override
   public String type() {
      return this.field1.getId();
   }
}

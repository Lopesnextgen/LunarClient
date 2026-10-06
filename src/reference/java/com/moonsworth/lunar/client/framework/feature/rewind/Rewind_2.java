package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;
import org.apache.commons.lang3.Range;

@Annotation7
public class Rewind_2 extends RewindIterator<Rewind_2> {
   public Rewind_2(Nameplate2 var1) {
      super(var1);
   }

   public Rewind_2 method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      Rewind_2 var4 = new Rewind_2(var1);
      return (Rewind_2)this.method3(var4, var1, var2, var3);
   }

   @Override
   public String type() {
      return "effect";
   }
}

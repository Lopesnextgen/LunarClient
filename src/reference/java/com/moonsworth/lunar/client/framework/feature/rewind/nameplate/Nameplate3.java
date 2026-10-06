package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class Nameplate3 extends Fishing2Iterator3 {
   private final Function<RewindHandlers, List<Nameplate_2>> field13;
   private boolean field14 = false;

   public Nameplate3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      Function<RewindHandlers, List<Nameplate_2>> var4
   ) {
      this(var1, var2, var3, null, var4);
   }

   public Nameplate3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      ToggleOption var4,
      Function<RewindHandlers, List<Nameplate_2>> var5
   ) {
      this(var1, var2, var3, true, true, var4, var5);
   }

   public Nameplate3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      boolean var4,
      boolean var5,
      ToggleOption option,
      Function<RewindHandlers, List<Nameplate_2>> function
   ) {
      super(var1, var2, var3, Collections::emptyList, var4, var5, option);
      this.field13 = function;
      this.method23(true);
      this.method24(true);
   }

   @Override
   public Fishing2Iterator method3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Nameplate3 var4 = new Nameplate3(var1, this.method10(), this.type(), this.field13);
      return this.method8(var4, var1, var2, var3);
   }

   public boolean method2(String var1) {
      if (this.field14) {
         return true;
      } else {
         return this.method16() == Fishing2Iterator.Type.KEYFRAMES
            ? !this.method12().containsKey(var1)
            : !this.method11().containsKey(var1);
      }
   }

   @Generated
   public Function<RewindHandlers, List<Nameplate_2>> method3() {
      return this.field13;
   }

   @Generated
   public void method4(boolean var1) {
      this.field14 = var1;
   }
}

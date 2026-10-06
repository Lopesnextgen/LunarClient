package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.FrameworkType;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightingExtension4952 extends com.moonsworth.lunar.client.config.option.CyclingDropdownOption<Tiertagger_2> {
   private final TierTagger field9;
   private final boolean field10;

   public LightingExtension4952(TierTagger var1, String var2, boolean flag) {
      super(
         var2,
         Codec.stringResolver(Tiertagger_2::apiName, var1x -> var1.field10.getTierProvider().method9(var1x).orElse(Tiertagger_2.field5)),
         flag ? Tiertagger_2.field5 : var1.field10.getTierProvider().method3().stream().findFirst().orElse(Tiertagger_2.field5),
         Collections.emptyList(),
         Tiertagger_2::niceName
      );
      this.field9 = var1;
      this.field10 = flag;
   }

   public void method1(Tiertagger_2 var1, boolean var2) {
      super.method11(var1, var2);

      for (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var4 : com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES) {
         var4.getTierProvider().method6();
      }

      this.field9.clearCaches();
   }

   public void setOptions(List<Tiertagger_2> var1) {
      super.setOptions(this.method5(var1));
      this.method10(this.method9());
      if (this.field9.method7(Framework.field8) == FrameworkType.COMPLETE) {
         LcuiScreen.method145();
      }
   }

   public List<Tiertagger_2> getOptions() {
      return this.method5(this.field9.field10.getTierProvider().method3());
   }

   public Tiertagger_2 method9() {
      if (this.field10) {
         return Tiertagger_2.field5;
      }

      List var1 = this.getOptions();
      return var1.isEmpty() ? Tiertagger_2.field5 : (Tiertagger_2)var1.get(0);
   }

   private List<Tiertagger_2> method5(List<Tiertagger_2> var1) {
      if (!this.field10) {
         return var1;
      }

      ArrayList var2 = new ArrayList(var1.size() + 1);
      var2.add(Tiertagger_2.field5);
      var2.addAll(var1);
      return var2;
   }
}

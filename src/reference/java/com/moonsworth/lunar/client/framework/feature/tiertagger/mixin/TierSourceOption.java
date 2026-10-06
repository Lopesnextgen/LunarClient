package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLoadState;
import com.moonsworth.lunar.client.framework.feature.tiertagger.TierGameMode;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TierSourceOption extends com.moonsworth.lunar.client.config.option.CyclingDropdownOption<TierGameMode> {
   private final TierTagger field9;
   private final boolean field10;

   public TierSourceOption(TierTagger tiertagger1, String text2, boolean flag3) {
      super(
         text2,
         Codec.stringResolver(TierGameMode::apiName, arg1x -> tiertagger1.field10.getTierProvider().method9(arg1x).orElse(TierGameMode.field5)),
         flag3 ? TierGameMode.field5 : tiertagger1.field10.getTierProvider().method3().stream().findFirst().orElse(TierGameMode.field5),
         Collections.emptyList(),
         TierGameMode::niceName
      );
      this.field9 = tiertagger1;
      this.field10 = flag3;
   }

   public void method1(TierGameMode tiertagger_21, boolean flag2) {
      super.method11(tiertagger_21, flag2);

      for (com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource gui2extension4 : com.moonsworth.lunar.client.framework.feature.tiertagger.TiertaggerSource.VALUES) {
         gui2extension4.getTierProvider().method6();
      }

      this.field9.clearCaches();
   }

   public void setOptions(List<TierGameMode> list1) {
      super.setOptions(this.method5(list1));
      this.method10(this.method9());
      if (this.field9.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field8) == ModLoadState.COMPLETE) {
         LcuiScreen.method145();
      }
   }

   public List<TierGameMode> getOptions() {
      return this.method5(this.field9.field10.getTierProvider().method3());
   }

   public TierGameMode method9() {
      if (this.field10) {
         return TierGameMode.field5;
      }

      List list1 = this.getOptions();
      return list1.isEmpty() ? TierGameMode.field5 : (TierGameMode)list1.get(0);
   }

   private List<TierGameMode> method5(List<TierGameMode> list1) {
      if (!this.field10) {
         return list1;
      }

      ArrayList list2 = new ArrayList(list1.size() + 1);
      list2.add(TierGameMode.field5);
      list2.addAll(list1);
      return list2;
   }
}

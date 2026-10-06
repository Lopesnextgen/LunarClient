package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.PipelineStage;

public class SmuggleHideStageHook implements IchorInjection, SmuggleHideHook {
   public SmuggleHideStageHook() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.PRE_META_MIXIN};
   }
}

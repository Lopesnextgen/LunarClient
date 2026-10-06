package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.PipelineStage;

public class SmuggleRestoreStageHook implements IchorInjection, SmuggleRestoreHook {
   public SmuggleRestoreStageHook() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_META_MIXIN};
   }
}

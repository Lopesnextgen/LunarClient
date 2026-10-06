package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.ClassNameRegexFilter;
import com.moonsworth.lunar.loader.PipelineStage;

public class ReplayModFieldWideningHook extends ClassNameRegexFilter implements FieldAccessWideningHook {
   public ReplayModFieldWideningHook() {
      super("com.replaymod.*");
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.EXTERNAL_REMAP};
   }
}

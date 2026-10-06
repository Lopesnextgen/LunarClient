package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.ClassNameRegexFilter;
import com.moonsworth.lunar.loader.PipelineStage;

public class ReplayModMappingHook extends ClassNameRegexFilter implements LunarMappingProviderHook, FieldAccessWideningHook {
   public ReplayModMappingHook() {
      super("com.replaymod.*");
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.EXTERNAL_REMAP};
   }
}

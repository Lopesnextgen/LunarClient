package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.ClassPrefixFilter;
import com.moonsworth.lunar.loader.PipelineStage;

public class ForgeAccessWideningHook extends ClassPrefixFilter implements AccessWideningHook {
   public ForgeAccessWideningHook() {
      super(new String[]{"net/minecraftforge/"});
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_OPTIFINE_PATCH};
   }
}

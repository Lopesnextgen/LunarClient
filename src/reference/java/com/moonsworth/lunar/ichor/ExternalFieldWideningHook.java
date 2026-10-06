package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.PipelineStage;

public class ExternalFieldWideningHook implements IchorInjection, FieldAccessWideningHook {
   public ExternalFieldWideningHook() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.EXTERNAL_REMAP};
   }

   public boolean method1(ClassTransformContext autocloseableiterator2$data31) {
      String text2 = autocloseableiterator2$data31.className();
      return !text2.startsWith("it/unimi/dsi/fastutil/") && !text2.startsWith("com/moonsworth/");
   }
}

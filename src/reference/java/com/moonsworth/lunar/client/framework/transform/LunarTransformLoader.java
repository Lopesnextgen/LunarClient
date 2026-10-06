package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.List;
import com.moonsworth.lunar.client.framework.transform.LunarIntermediaryMapper;

public class LunarTransformLoader extends IchorModule {
   public LunarTransformLoader() {
      super("lunar");
   }

   @Override
   public List<IchorLoader> method1(IchorPipeline ichor71) {
      return List.of(new LunarIntermediaryMapper());
   }
}

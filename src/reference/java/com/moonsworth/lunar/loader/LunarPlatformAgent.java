package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.ArrayList;
import java.util.List;

public class LunarPlatformAgent extends IchorModule {
   public LunarPlatformAgent() {
      super("lunar-platform");
   }

   public List<IchorLoader> method1(IchorPipeline ichor71) {
      ArrayList list2 = new ArrayList();

      for (LoaderDependency ichor4type26 : LoaderDependency.values()) {
         if (ichor71.method33().contains(ichor4type26)) {
            list2.add(ichor4type26);
         }
      }

      return list2;
   }
}

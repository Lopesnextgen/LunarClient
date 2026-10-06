package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.ArrayList;
import java.util.List;

public class Ichor6Impl extends IchorModule {
   public Ichor6Impl() {
      super("lunar-platform");
   }

   @Override
   public List<Ichor5> method1(IchorPipeline var1) {
      ArrayList var2 = new ArrayList();

      for (Ichor4Type2 var6 : Ichor4Type2.values()) {
         if (var1.method33().contains(var6)) {
            var2.add(var6);
         }
      }

      return var2;
   }
}

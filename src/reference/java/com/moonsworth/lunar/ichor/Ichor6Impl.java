package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.files.Files7;
import java.util.List;

public class Ichor6Impl extends IchorModule {
   public Ichor6Impl() {
      super("common");
   }

   @Override
   public List<Ichor5> method1(IchorPipeline var1) {
      Files7 var2 = var1.method34().method3();
      var2.method4(Files.Data2.field5);
      var2.method4(Files.Data2.field11);
      var2.method4(Files.Data2.field13);
      var2.method4(Files.Data2.field15);
      var2.method4(Files.Data2.field19);
      return List.of(new Ichor5Handler());
   }

   @Override
   public IchorModule.Type method5() {
      return IchorModule.Type.PRE_INIT;
   }
}

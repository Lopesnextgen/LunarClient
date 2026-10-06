package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Tiertagger2Iterator2 extends Tiertagger2Iterator_3 {
   private static final Tiertagger2$Data field4 = new Tiertagger2$Data(
      ServiceEndpoints.method4() + "/tier-tagger/mctiers/", RCCHRICRRRHICROHRHOIICCOHORORC, true, true
   );

   @NotNull
   @Override
   public Tiertagger2.Tiertagger2$Data method1() {
      return field4;
   }

   @Override
   protected boolean method2() {
      return Tiertagger6Impl.method3().isLoaded();
   }

   @Override
   protected List<Tiertagger_2> method4() {
      return Tiertagger6Impl.method3().method4();
   }

   @Nullable
   @Override
   protected String method3(String var1) {
      return Tiertagger6Impl.method3().method2(var1);
   }

   @Override
   protected int method4(String var1) {
      return Tiertagger6Impl.method3().method3(var1);
   }
}

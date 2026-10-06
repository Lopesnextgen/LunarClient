package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.Annotation8;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.MixinMisc4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.srg.tsrg.TSrgWriter;

public class Ichor2Iterator2 extends com.moonsworth.lunar.ichor.util.Ichor2Handler {
   private final Ichor5Iterator field2;

   public Ichor2Iterator2(Ichor5Iterator var1) {
      super("com/replaymod/", "me/Danker/", "com/github/lunatrius/");
      this.field2 = var1;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.EXTERNAL_REMAP};
   }

   @Annotation8(RHRRICIHIHHROIRCHHROOOHHOCHORH = true, OORRORHHCIICCICOOOIOOHRRHCOIHI = true, method2 = truevar1) {
      try {
         IchorPipeline var2 = var1.method1();
         String var3 = Config.method36(var2.method34().method6()).getId();
         MappingSet var4 = var2.method10(Files.Data2.field16)
            .orElseThrow(() -> new FatalIchorError("Can't find S2L mappings for " + var3 + " " + Files.Data2.field16.method1(var2.method34().method3())));
         MappingSet var5 = var2.method10(Files.Data2.field4)
            .orElseThrow(() -> new FatalIchorError("Can't find lunar mappings " + Files.Data2.field4.method1(var2.method34().method3())));
         var5.getTopLevelClassMappings().parallelStream().forEach(var2x -> {
            String var3x = var2x.getDeobfuscatedName();
            Set var4x = FatalIchorError6.method12(var3x, var1);
            if (!var4x.isEmpty()) {
               for (String var6x : var4x) {
                  MixinMisc4.method6(var6x, var3x, var4, var4, var1xx -> FatalIchorError6.method12(var1xx, var1));
               }
            } else if (Ichor6Impl.field3) {
               Ichor6Impl.field2.info("Skipping mapping: " + var3x + " has no super classes?");
            }
         });
         this.field2.method5(var1, var4);
         if (Ichor6Impl.field3) {
            try {
               TSrgWriter var6 = new TSrgWriter(new FileWriter(Ichor6Impl.field5.resolve(var3 + "_ModPostRemapIchor_remapSearge.tsrg").toFile()));
               var6.write(var4);
               var6.close();
            } catch (IOException var7) {
               var7.printStackTrace();
            }
         }

         return var4;
      } catch (Throwable var8) {
         throw var8;
      }
   }
}

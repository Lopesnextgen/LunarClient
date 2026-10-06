package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.Annotation8;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.FileWriter;
import java.io.IOException;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.srg.tsrg.TSrgWriter;

public class Ichor2Impl extends com.moonsworth.lunar.ichor.util.Ichor2Handler {
   private final Ichor5Iterator field2;

   public Ichor2Impl(Ichor5Iterator var1) {
      super("com/replaymod/", "me/Danker/", "com/github/lunatrius/");
      this.field2 = var1;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INIT};
   }

   @Annotation8(method1 = true, OORRORHHCIICCICOOOIOOHRRHCOIHI = truemethod2(URLClassLoader var1) {
      try {
         String var2 = Config.method36(var1.method1().method34().method6()).getId();
         MappingSet var3 = var1.method1().method10(Files.Data2.field4).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
         this.field2.method5(var1, var3);
         if (Ichor6Impl.field3) {
            try {
               TSrgWriter var4 = new TSrgWriter(new FileWriter(Ichor6Impl.field5.resolve(var2 + "_completedRemapSearge.tsrg").toFile()));
               var4.write(var3);
               var4.close();
            } catch (IOException var5) {
               var5.printStackTrace();
            }
         }

         return var3;
      } catch (Throwable var6) {
         throw var6;
      }
   }
}

package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files7;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.RemapperIterator2;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Ichor6Impl extends IchorModule {
   public static final String field1 = "forge";
   public static final FatalIchorError5 field2 = new FatalIchorError5("Ichor/Forge");
   public static final boolean field3 = Boolean.parseBoolean(System.getProperty("ichor.forgeDebugMappings", "false"));
   public static final boolean field4 = Boolean.parseBoolean(System.getProperty("ichor.forgeDebugBinpatch", "false"));
   public static final Path field5 = Paths.get(".ichor/forge/");

   public Ichor6Impl() {
      super("forge");
   }

   @Override
   public List<Ichor5> method1(IchorPipeline var1) {
      try {
         if (field3) {
            Files.createDirectories(field5);
            RemapperIterator2.DEBUG = true;
         }

         field3 = true;
         Files7 var2 = var1.method34().method3();
         var2.method4(com.moonsworth.lunar.files.Files.Data2.field17);
         var2.method4(com.moonsworth.lunar.files.Files.Data2.field9);
         return List.of(new Ichor5Iterator());
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Override
   public Map<String, String> method2(IchorPipeline var1) {
      Config var2 = Config.method36(var1.method34().method6());
      return var2.method21() ? Map.of("forge", "Forge_" + var2.getId() + ".jar") : Map.of();
   }

   @Override
   public IchorModule.Type method5() {
      return IchorModule.Type.INIT;
   }
}

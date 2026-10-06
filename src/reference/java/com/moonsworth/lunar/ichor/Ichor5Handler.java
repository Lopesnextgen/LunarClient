package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.files.Files3;
import com.moonsworth.lunar.files.Files7;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Generated;
import org.cadixdev.lorenz.MappingSet;

public class Ichor5Handler implements Ichor5, MixinMisc5 {
   private static final String field1 = "com.moonsworth.lunar.magnify.ichor.LVTCleanupIchor";
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<String, String> field3 = new HashMap<>();

   @Override
   public void loadIchor(IchorTransformer var1) {
      try {
         IchorPipeline var2 = var1.method20();
         boolean var3 = var2.hasModule("fabric");
         boolean var4 = var3 && (!URLClassLoader.field4 || URLClassLoader.field5);
         if (var2.method33().contains(Ichor4Type.ACCESS_WIDEN)) {
            try (InputStream var5 = Ichor5Handler.class.getResourceAsStream("/lunar/minecraft.accesswidener")) {
               if (var5 != null) {
                  MappingSet var6 = var4 ? var2.method10(Files.Data2.field14).orElse(null) : null;
                  var1.method1(new Ichor2Iterator(var6, var5));
               }
            }
         }

         if (var2.method33().contains(Ichor4Type.INIT)) {
            Config var12 = Config.method36(var2.method34().method6());

            try {
               var1.method1((IchorInjection)Class.forName("com.moonsworth.lunar.magnify.ichor.ParameterAnnotationIchor").getConstructor().newInstance());
            } catch (ClassNotFoundException var9) {
            }

            Optional var13 = NestClassNameMapper.method4(var12);
            var13.ifPresent(var1x -> var1.method1(new Ichor2Iterator_2(var1x)));
            var1.method1(new Ichor2Handler23(var12));
            if (!var12.method50()) {
               if (var12.method19()) {
                  Files3 var7 = var4 ? Files.Data2.field10 : Files.Data2.field4;
                  var1.method1(new Ichor2Handler4.Data(var7));
               } else {
                  var1.method1(new Ichor2Handler4.LegacyRemapProvider(Files.Data2.field4));
               }
            }

            var1.method1(new Ichor2Handler5());
            var1.method1(new Ichor2Handler6());
            var1.method1(new Ichor2Iterator2());
            var1.method1(new Ichor2Handler());
            var1.method1(new Ichor2Iterator3());
            if (var12.method19()) {
               var1.method1(new Ichor2Handler22());
               if (var3) {
                  if (var4 && !var12.method50()) {
                     var1.method1(new Ichor2Impl2());
                  }
               } else if (URLClassLoader.field4) {
                  if (!var12.method50()) {
                     Files7 var14 = var1.method20().method34().method3();
                     if (var14.method10(Files.Data2.field12).isEmpty()) {
                        throw new IllegalStateException("Can't find intermediary mappings for " + Files.Data2.field12);
                     }

                     var1.method1(new Ichor2Handler2());
                  } else {
                     var1.method1(new Ichor2Handler3());
                  }
               }
            } else {
               var1.method1(new Ichor2Impl());
            }

            Files7 var15 = var2.method34().method3();
            var15.method10(Files.Data2.field18).ifPresent(var1x -> {
               try {
                  Class var2x = Class.forName("com.moonsworth.lunar.magnify.ichor.LVTCleanupIchor");
                  Object var3x = var2x.getConstructor().newInstance();
                  var2x.getField("parchmentData").set(var3x, var1x.method2());
                  var1.method1((IchorInjection)var3x);
               } catch (ReflectiveOperationException var4x) {
               }
            });
         }
      } catch (Throwable var11) {
         throw var11;
      }
   }

   @Generated
   @Override
   public Map<String, String> method1() {
      return this.field2;
   }

   @Generated
   @Override
   public Map<String, String> method2() {
      return this.field3;
   }
}

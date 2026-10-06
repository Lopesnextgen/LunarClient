package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.bridge.Bridge2_24;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.files.Files5_2;
import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.MixinInternal2;
import com.moonsworth.lunar.ichor.RemapperIterator2;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.moonsworth.lunar.loader.mixin.MixinInternal3Handler;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingFormats;
import org.cadixdev.lorenz.io.MappingsReader;
import org.objectweb.asm.tree.ClassNode;
import com.moonsworth.lunar.client.framework.transform.RandomRewriteTransform;
import com.moonsworth.lunar.client.framework.transform.PreMixinTransform;
import com.moonsworth.lunar.client.framework.transform.LwjglRelocationTransform;
import com.moonsworth.lunar.client.framework.transform.NewInstanceFactoryTransform;

public class LunarIntermediaryMapper implements Ichor5 {
   private static Bridge2_24 field1;
   private static Config field2;

   @Override
   public void loadIchor(IchorTransformer var1) {
      try {
         IchorPipeline var2 = var1.method20();
         if (var2.method33().contains(Ichor4Type.INIT)) {
            Config var3 = Config.method36(var2.method34().method6());
            field2 = var3;
            String var4 = new String(FatalIchorError7.toByteArray(LunarIntermediaryMapper.class.getClassLoader().getResourceAsStream("bridge/bridgeConfig.json")));
            boolean var5 = var2.hasModule("fabric");
            boolean var6 = var5 && (!URLClassLoader.field4 || URLClassLoader.field5);
            MappingSet var7 = null;
            if (var6 && !var3.method50()) {
               var7 = var2.hasModule("fabric")
                  ? var2.method10(Files.Data2.field14).orElseThrow(() -> new FatalIchorError("Can't find Lunar to Intermediary mappings"))
                  : null;
               field1 = new Bridge2_24(var4, var7);
            } else {
               field1 = new Bridge2_24(var4);
            }

            var1.method1(new RandomRewriteTransform());
            var1.method1(new PreMixinTransform());
            var1.method22().add(new LunarIntermediaryMapper.Data(var3));
            if (var2.method11("tinyvk").isPresent()) {
               var1.method1(new GlAccessTransform());
            }

            String var8 = var3.getType();
            List var9 = List.of(
               "com/moonsworth/lunar/" + var8 + "/optifine/",
               "com/moonsworth/lunar/" + var8 + "/forge/",
               "com/moonsworth/lunar/" + var8 + "/optiforge/",
               "com/moonsworth/lunar/sodium/",
               "com/moonsworth/lunar/fabric/",
               "com/moonsworth/lunar/bridge/v1_",
               "com/moonsworth/lunar/replaymod/",
               "com/moonsworth/lunar/compat/",
               "com/moonsworth/lunar/ferrite/",
               "com/moonsworth/lunar/noxesium/"
            );

            for (Files6_2 var12 : var3.method3(Config.field6)
               ? List.of(new Files6_2<>("vanilla", null), new Files6_2<>("optifine", "com/moonsworth/lunar/" + var8 + "/optifine/"))
               : List.of(
                  new Files6_2<>("vanilla", null),
                  new Files6_2<>("optifine", "com/moonsworth/lunar/" + var8 + "/optifine/"),
                  new Files6_2<>("forge", "com/moonsworth/lunar/" + var8 + "/forge/"),
                  new Files6_2<>("optiforge", "com/moonsworth/lunar/" + var8 + "/optiforge/")
               )) {
               String var13 = (String)var12.field1;
               String var14 = (String)var12.field2;
               String var15 = var3.getId() + "_inflight_" + var13 + ".kin";
               MappingSet var16 = null;

               try (InputStream var17 = LunarIntermediaryMapper.class.getClassLoader().getResourceAsStream(var15)) {
                  if (var17 != null) {
                     MappingsReader var18 = Files5_2.field1.createReader(var17);

                     try {
                        var16 = var18.read();
                        if (RemapperIterator2.DEBUG) {
                           MappingFormats.XSRG.write(var16, Paths.get(".ichor/" + var3.getId() + "_inflight_" + var13 + ".xsrg"));
                        }
                     } catch (Throwable var24) {
                        if (var18 != null) {
                           try {
                              var18.close();
                           } catch (Throwable var22) {
                              var24.addSuppressed(var22);
                           }
                        }

                        throw var24;
                     }

                     if (var18 != null) {
                        var18.close();
                     }

                     if (var16.getTopLevelClassMappings().isEmpty()) {
                        throw new IllegalStateException("Inflight mappings were empty?");
                     }
                  } else if ("vanilla".equals(var13) && !LunarBuildData.field4 || "optifine".equals(var13) && var2.hasModule("optifine")) {
                     throw new IllegalStateException("Failed to find " + var15 + " on the classpath");
                  }
               }

               if (var16 != null) {
                  var1.method1(new MixinTaskTransform(var3, var16, var7, var14, var14 == null ? var9 : List.of()));
                  if ("true".equals(var2.method34().method6().get("ichor.testing"))) {
                     var1.method1(new OmniMixinValidationTransform(var3, var14, var14 == null ? var9 : List.of()));
                  }
               }
            }

            var1.method1(new NewInstanceFactoryTransform());

            try {
               var1.method1((IchorInjection)Class.forName("com.moonsworth.lunar.magnify.ichor.LineNumberIchor").getConstructor().newInstance());
            } catch (ClassNotFoundException var23) {
            }

            if (var3.method6(Config.field6)) {
               var1.method1(new LwjglRelocationTransform());
            }
         }
      } catch (Throwable var26) {
         throw var26;
      }
   }

   public static void method1(ClassNode var0, ClassProvider var1) {
      field1.method3(var0, field2, var1);
   }

   public static class Data extends MixinInternal3Handler {
      public Data(Config var1) {
         super(var1);
      }

      @Override
      public void method1(Ichor4 var1, MixinInternal2 var2, URLClassLoader var3) {
         if (var1 == Ichor4Type.MIXIN) {
            boolean var4 = Boolean.parseBoolean(var3.method1().method34().method6().getOrDefault("ichor.testing", "false").toString());
            ArrayList var5 = new ArrayList<>(
               List.of(
                  this.HOCORROCHHHROIHIRCOCCHCIOIOIIR(),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("lunar"),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("bridge_combined"),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("bridge_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId()),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("lunar_combined"),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("lunar_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId())
               )
            );
            if (var4) {
               var5.addAll(
                  List.of(
                     this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("test"),
                     this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("integrationTest"),
                     this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("integrationTest_combined"),
                     this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("integrationTest_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId())
                  )
               );
            }

            var2.registerMixins(var5);
         }
      }
   }
}

package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Ichor6Iterator extends IchorModule {
   public static final Set<String> field1 = new HashSet<>();
   public static final FatalIchorError5 field2 = FatalIchorError5.method2("Ichor+Forge");
   public static final List<MixinHelper> field3 = new ArrayList<>();
   public static final Set<String> field4 = new HashSet<>();
   private static final Map<String, List<Object>> field5 = new ConcurrentHashMap<>();
   private final Map<String, String> field6 = new HashMap<>();

   public Ichor6Iterator() {
      super("forge-runtime");
   }

   @Override
   public List<Ichor5> method1(IchorPipeline var1) {
      IchorAPI.doNotCacheClasses();
      Config var2 = Config.method36(var1.method34().method6());
      ArrayList var3 = new ArrayList();
      if (var2 == Config.field2 || var2 == Config.field6) {
         method4("net.minecraftforge.fml.relauncher.FMLCorePlugin");
         method4("net.minecraftforge.classloading.FMLForgePlugin");
      }

      return var3;
   }

   @Override
   public Map<String, String> method2(IchorPipeline var1) {
      return this.field6;
   }

   @Override
   public IchorModule.Type method5() {
      return IchorModule.Type.INIT;
   }

   public static void method4(String var0) {
      field4.add(var0);
   }

   private static List<Object> method5(ClassLoader var0) {
      try {
         field2.info("RuntimeForgeIchorModule.loadFMLLoadingPluginsImpl");
         ArrayList var1 = new ArrayList();

         for (String var3 : field4) {
            field2.info("Instantiating IFMLLoadingPlugin " + var3 + " in " + var0.getName());

            try {
               Object var4 = Class.forName(var3, false, var0).getConstructor().newInstance();
               var1.add(var4);
            } catch (ClassNotFoundException var5) {
               new IllegalStateException("Failed to initialize " + var3 + " in " + var0.getName(), var5).printStackTrace();
            }

            field2.info("Done instantiating IFMLLoadingPlugin " + var3);
         }

         return var1;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   public static List<Object> method6(ClassLoader var0) {
      List var1 = field5.get(var0.getName());
      if (var1 == null) {
         var1 = method5(var0);
         field5.put(var0.getName(), var1);
      }

      return var1;
   }
}

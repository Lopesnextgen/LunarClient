package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Holograms8 {
   private final File field1 = ThreadModuleDump48.field12
      .resolve("hypixel" + File.separator + "skyblock" + File.separator + "dungeon" + File.separator + "routes.json")
      .toFile();

   public Map<String, List<Holograms2>> method1() {
      return this.method4(this.field1);
   }

   public Map<String, List<Holograms2>> method2() {
      return this.method3(Holograms3_3.field1);
   }

   private Map<String, List<Holograms2>> method3(File var1) {
      HashMap var2 = new HashMap();
      String[] var3 = var1.list();
      if (var3 == null) {
         return var2;
      }

      for (String var7 : var3) {
         File var8 = new File(var1, var7);
         String[] var9 = var8.list();
         if (var9 != null) {
            ArrayList var10 = new ArrayList();

            for (String var14 : var9) {
               File var15 = new File(var8, var14);
               String var16 = var14.replaceFirst("\\.lcroute$", "");

               try (FileInputStream var17 = new FileInputStream(var15)) {
                  Holograms2 var18 = this.method5(var17, var16);
                  if (var18 != null) {
                     var10.add(var18);
                  }
               } catch (Exception var22) {
                  Slayer.method7("An error occurred while trying to load the route file: {}", new Object[]{var16, var22});
               }
            }

            var2.put(var7, var10);
         }
      }

      return var2;
   }

   private Map<String, List<Holograms2>> method4(File var1) {
      HashMap var2 = new HashMap();

      try (FileSystem var3 = FileSystems.newFileSystem(var1.toPath(), Collections.emptyMap())) {
         for (Path var5 : var3.getRootDirectories()) {
            try (Stream var6 = Files.walk(var5, 1)) {
               for (Path var8 : var6.toList()) {
                  if (!var5.equals(var8)) {
                     String var9 = var8.getFileName().toString();

                     try (Stream var10 = Files.walk(var8, 1)) {
                        for (Path var12 : var10.toList()) {
                           if (!var8.equals(var12) && var12.getFileName().toString().endsWith(".lcroute")) {
                              List var13 = var2.computeIfAbsent(var9, var0 -> new ArrayList());
                              var13.add(this.method5(Files.newInputStream(var12), var12.getFileName().toString().split("\\.")[0]));
                           }
                        }
                     }
                  }
               }
            }
         }

         return var2;
      } catch (IOException var20) {
         throw new RuntimeException(var20);
      }
   }

   private Holograms2 method5(InputStream var1, String var2) {
      try (BufferedReader var3 = new BufferedReader(new InputStreamReader(var1))) {
         Holograms4_3 var4 = (Holograms4_3)ThreadModuleDump48.field22.fromJson(var3, Holograms4_3.class);
         return var4.toRoute(var2);
      } catch (Exception var8) {
         Slayer.error("An error occurred while trying to load the route file: " + var2, var8);
         return null;
      }
   }
}

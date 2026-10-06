package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.LunarConstants;
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

public class RouteFileLoader {
   private final File field1 = LunarConstants.field12
      .resolve("hypixel" + File.separator + "skyblock" + File.separator + "dungeon" + File.separator + "routes.json")
      .toFile();

   public RouteFileLoader() {
   }

   public Map<String, List<DungeonRoute>> method1() {
      return this.method4(this.field1);
   }

   public Map<String, List<DungeonRoute>> method2() {
      return this.method3(RouteManager.field1);
   }

   private Map<String, List<DungeonRoute>> method3(File file1) {
      HashMap map2 = new HashMap();
      String[] items3 = file1.list();
      if (items3 == null) {
         return map2;
      }

      for (String text7 : items3) {
         File file8 = new File(file1, text7);
         String[] items9 = file8.list();
         if (items9 != null) {
            ArrayList list10 = new ArrayList();

            for (String text14 : items9) {
               File file15 = new File(file8, text14);
               String text16 = text14.replaceFirst("\\.lcroute$", "");

               try (FileInputStream stream17 = new FileInputStream(file15)) {
                  DungeonRoute holograms218 = this.method5(stream17, text16);
                  if (holograms218 != null) {
                     list10.add(holograms218);
                  }
               } catch (Exception exception22) {
                  LunarLogger.method7("An error occurred while trying to load the route file: {}", new Object[]{text16, exception22});
               }
            }

            map2.put(text7, list10);
         }
      }

      return map2;
   }

   private Map<String, List<DungeonRoute>> method4(File file1) {
      HashMap map2 = new HashMap();

      try (FileSystem filesystem3 = FileSystems.newFileSystem(file1.toPath(), Collections.emptyMap())) {
         for (Path path5 : filesystem3.getRootDirectories()) {
            try (Stream stream6 = Files.walk(path5, 1)) {
               for (Path path8 : stream6.toList()) {
                  if (!path5.equals(path8)) {
                     String text9 = path8.getFileName().toString();

                     try (Stream stream10 = Files.walk(path8, 1)) {
                        for (Path path12 : stream10.toList()) {
                           if (!path8.equals(path12) && path12.getFileName().toString().endsWith(".lcroute")) {
                              List list13 = map2.computeIfAbsent(text9, arg0 -> new ArrayList());
                              list13.add(this.method5(Files.newInputStream(path12), path12.getFileName().toString().split("\\.")[0]));
                           }
                        }
                     }
                  }
               }
            }
         }

         return map2;
      } catch (IOException exception20) {
         throw new RuntimeException(exception20);
      }
   }

   private DungeonRoute method5(InputStream input1, String text2) {
      try (BufferedReader reader3 = new BufferedReader(new InputStreamReader(input1))) {
         RouteDataCodec holograms4_34 = (RouteDataCodec)LunarConstants.field22.fromJson(reader3, RouteDataCodec.class);
         return holograms4_34.toRoute(text2);
      } catch (Exception exception8) {
         LunarLogger.error("An error occurred while trying to load the route file: " + text2, exception8);
         return null;
      }
   }
}

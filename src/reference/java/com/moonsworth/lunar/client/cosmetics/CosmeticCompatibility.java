package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

public class CosmeticCompatibility implements LoadableHandler {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "cosmetics/compat.json");
   private Map<String, CompiledCompatibilityPreset> field2 = new HashMap<>();

   public Optional<CompiledCompatibilityPreset> method1(String var1) {
      return Optional.ofNullable(this.field2.get(var1));
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
      this.field2.clear();
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      Slayer.method3("Loading cosmetic compatibility json", new Object[0]);

      try {
         IResourceBridge var2 = var1.bridge$getResource(field1);
         if (var2 == null) {
            Slayer.method5("Could not find cosmetic compatibility json file: " + field1, new Object[0]);
            return;
         }

         JsonObject var3 = (JsonObject)ThreadModuleDump48.field22.fromJson(IOUtils.toString(var2.bridge$getInputStream()), JsonObject.class);
         DefaultDirectedGraph var4 = this.method2(var3);
         CycleDetector var5 = new CycleDetector(var4);
         if (var5.detectCycles()) {
            Slayer.method7("Invalid parenting cycle in compatibility presets", new Object[0]);
            Set var19 = var5.findCycles();
            Slayer.method7("Following compatibility presets are in a cycle", new Object[0]);
            var19.forEach(System.out::println);
            return;
         }

         TopologicalOrderIterator var6 = new TopologicalOrderIterator(var4);
         HashMap var7 = new HashMap();

         while (var6.hasNext()) {
            JsonObject var8 = (JsonObject)var6.next();
            ArrayList var9 = new ArrayList(var4.incomingEdgesOf(var8));
            String var10 = var8.get("name").getAsString();
            HashSet var11 = new HashSet();
            HashSet var12 = new HashSet();
            HashSet var13 = new HashSet();
            HashSet var14 = new HashSet();
            CosmeticCompatibilityPreset var15 = null;
            if (var9.size() == 1) {
               JsonObject var16 = ((JsonGraphEdge)var9.get(0)).method1();
               var15 = (CosmeticCompatibilityPreset)var7.get(var16.get("name").getAsString());
               var11.addAll(var15.method4());
               var12.addAll(var15.method3());
            }

            if (var8.get("incompatible") instanceof JsonArray var20) {
               var20.forEach(var2x -> {
                  Set var3x = CosmeticMatcher.method3(var2x.getAsString()).method2();
                  var13.addAll(var3x);
                  var12.removeAll(var3x);
               });
            }

            if (var8.get("compatible") instanceof JsonArray var21) {
               var21.forEach(var2x -> {
                  Set var3x = CosmeticMatcher.method3(var2x.getAsString()).method2();
                  var14.addAll(var3x);
                  var11.removeAll(var3x);
               });
            }

            var14.addAll(var12);
            var13.addAll(var11);
            var7.put(var10, new CosmeticCompatibilityPreset(var10, var15, var14, var13));
         }

         var7.forEach((var1x, var2x) -> this.field2.put(var1x, var2x.method1()));
         Slayer.method3("Compiled cosmetic compatability presets", new Object[0]);
      } catch (IOException var18) {
         Slayer.method7("Could not find cosmetic compatibility json file", new Object[0]);
      }
   }

   private DefaultDirectedGraph<JsonObject, JsonGraphEdge> method2(JsonObject var1) {
      DefaultDirectedGraph var2 = new DefaultDirectedGraph(JsonGraphEdge.class);
      HashMap var3 = new HashMap();
      if (var1.get("presets") instanceof JsonArray var4) {
         var4.forEach(var2x -> {
            if (var2x instanceof JsonObject var3x && var3x.has("name")) {
               var3.put(var3x.get("name").getAsString(), var3x);
               var2.addVertex(var3x);
            }
         });

         for (JsonElement var6 : var4) {
            if (var6 instanceof JsonObject var7 && var7.has("parent")) {
               String var8 = var7.get("parent").getAsString();
               if (var3.containsKey(var8)) {
                  var2.addEdge((JsonObject)var3.get(var8), var7);
               } else {
                  Slayer.method7("Could not find compatibility preset {}, referenced from {}", new Object[]{var8, var7.get("name").getAsString()});
               }
            }
         }
      }

      return var2;
   }
}

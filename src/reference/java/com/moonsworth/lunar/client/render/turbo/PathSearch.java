package com.moonsworth.lunar.client.render.turbo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.render.turbo.TurboPath;
import com.moonsworth.lunar.client.render.turbo.PathFinder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class PathSearch {
   public PathNode[] field1 = new PathNode[32];
   public int field2;
   public PathFinder field3;
   public NodeHeap field4 = new NodeHeap();

   public PathSearch(PathFinder var1, int var2) {
      this.field3 = var1;
      this.field2 = var2;
   }

   @Nullable
   public TurboPath method1(Itemcounter6 var1, PathEntity var2, Set<Vector3iBridge> var3, float var4, int var5, float var6) {
      this.field4.clear();
      this.field3.method1(var1, var2);
      PathNode var7 = this.field3.method6();
      if (var7 == null) {
         return null;
      }

      HashMap var8 = new HashMap();

      for (Vector3iBridge var10 : var3) {
         var8.put(this.field3.method4(var10), var10);
      }

      TurboPath var11 = this.method4(var7, var8, var4, var5, var6);
      this.field3.done();
      return var11;
   }

   private float method2(PathNode var1, Set<ScoredPathNode> var2) {
      float var3 = Float.MAX_VALUE;

      for (ScoredPathNode var5 : var2) {
         float var6 = var1.method2(var5);
         var5.method1(var6, var1);
         var3 = Math.min(var6, var3);
      }

      return var3;
   }

   public TurboPath method3(PathNode var1, Vector3iBridge var2, boolean var3) {
      ArrayList var4 = Lists.newArrayList();
      PathNode var5 = var1;
      var4.add(0, var5);

      while (var5.field5 != null) {
         var5 = var5.field5;
         var4.add(0, var5);
      }

      return new TurboPath(var4, var2, var3);
   }

   @Nullable
   public TurboPath method4(PathNode var1, Map<ScoredPathNode, Vector3iBridge> var2, float var3, int var4, float var5) {
      Set var6 = var2.keySet();
      var1.field2 = 0.0F;
      var1.field3 = this.method2(var1, var6);
      var1.field4 = var1.field3;
      this.field4.clear();
      this.field4.method2(var1);
      int var7 = 0;
      HashSet var8 = Sets.newHashSetWithExpectedSize(var6.size());
      int var9 = (int)(this.field2 * var5);

      while (!this.field4.isEmpty()) {
         if (++var7 >= var9) {
            break;
         }

         PathNode var10 = this.field4.method3();
         var10.closed = true;

         for (ScoredPathNode var12 : var6) {
            if (var10.method4(var12) <= var4) {
               var12.method2();
               var8.add(var12);
            }
         }

         if (!var8.isEmpty()) {
            break;
         }

         if (!(var10.method2(var1) >= var3)) {
            int var16 = this.field3.method7(this.field1, var10);

            for (int var17 = 0; var17 < var16; var17++) {
               PathNode var13 = this.field1[var17];
               float var14 = this.method5(var10, var13);
               var13.field6 = var10.field6 + var14;
               float var15 = var10.field2 + var14 + var13.field7;
               if (var13.field6 < var3 && (!var13.method14() || var15 < var13.field2)) {
                  var13.field5 = var10;
                  var13.field2 = var15;
                  var13.field3 = this.method2(var13, var6) * 1.5F;
                  if (var13.method14()) {
                     this.field4.method4(var13, var13.field2 + var13.field3);
                  } else {
                     var13.field4 = var13.field2 + var13.field3;
                     this.field4.method2(var13);
                  }
               }
            }
         }
      }

      return (!var8.isEmpty()
            ? var8.stream().map(var2x -> this.method3(var2x.method3(), (Vector3iBridge)var2.get(var2x), true)).min(Comparator.comparingInt(TurboPath::method8))
            : var6.stream()
               .map(var2x -> this.method3(var2x.method3(), (Vector3iBridge)var2.get(var2x), false))
               .min(Comparator.comparingDouble(TurboPath::method17).thenComparingInt(TurboPath::method8)))
         .orElse(null);
   }

   private float method5(PathNode var1, PathNode var2) {
      return var1.method2(var2);
   }
}

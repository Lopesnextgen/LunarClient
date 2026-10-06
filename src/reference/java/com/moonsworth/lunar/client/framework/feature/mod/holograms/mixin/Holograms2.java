package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

class Holograms2 {
   private static final String field1 = "block.note_block.harp";
   private final GuiRewindhandlersHandler23 field2;
   private final GuiRewindhandlersHandler24 field3;
   private final GuiRewindhandlersHandler29 field4;
   private Holograms field5;
   private long field6 = 0L;
   private final Set<Holograms2.Data3> field7 = new HashSet<>();
   private final List<Vector3dc> field8 = new ArrayList<>();
   private final Cache<Vector3dc, Object> field9 = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.SECONDS).build();
   private final Cache<Vector3ic, Object> field10 = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.SECONDS).build();
   private boolean field11 = true;
   private int field12;
   private boolean field13;

   Holograms2(GuiRewindhandlersHandler23 var1, GuiRewindhandlersHandler24 var2, GuiRewindhandlersHandler29 var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public boolean method1() {
      return this.field13;
   }

   public void method2() {
      this.field13 = false;
   }

   void clear() {
      this.field5 = null;
      this.field7.clear();
   }

   Collection<Vector3ic> method3() {
      Stream var1 = this.field7.stream().map(Holograms2.Data3::method1);
      return this.field5 != null && this.field5.field5 != null ? Stream.concat(Stream.of(this.field5.field5), var1).toList() : var1.toList();
   }

   void method4(Vector3ic var1) {
      this.field10.put(var1, true);
   }

   boolean method5(EventParticleSpawn var1) {
      return this.field5 != null
         && this.field5.method1(new Vector3d(var1.getPosX(), var1.getPosY(), var1.getPosZ()))
         && ThreadModuleDump63.method3().bridge$getSystemTime() - this.field5.field6 < 1000L;
   }

   void method6(EventParticleSpawn var1) {
      this.method7(var1);
      if (var1.method2() == HorsestatsType2.DRIPPING_LAVA) {
         if (Math.abs(var1.method12()) == 0.0F) {
            return;
         }

         if (this.field5 != null) {
            this.field5.method2(new Vector3d(var1.getPosX(), var1.getPosY(), var1.getPosZ()));
         }
      }

      if (var1.method2() == HorsestatsType2.DUST) {
         Vector3d var2 = new Vector3d(var1.getPosX(), var1.getPosY(), var1.getPosZ());
         boolean var3 = false;

         for (Vector3ic var5 : this.field10.asMap().keySet()) {
            if (var2.distanceSquared(var5.x() + 0.5, var5.y() + 2, var5.z() + 0.5) < 9.0) {
               var3 = true;
            }
         }

         if (var3) {
            if (this.field11) {
               this.field8.clear();
               this.field11 = false;
            }

            if (this.field8.isEmpty() || this.field8.get(this.field8.size() - 1).distanceSquared(var2) > 0.001) {
               this.field8.add(var2);
               if (this.field8.size() > 100) {
                  this.field8.remove(0);
               }
            }
         }
      }
   }

   private void method7(EventParticleSpawn var1) {
      if (this.field4.method7()) {
         if (var1.method2() == HorsestatsType2.LARGE_SMOKE) {
            Vector3i var2 = new Vector3i((int)Math.floor(var1.getPosX()), (int)Math.floor(var1.getPosY()) - 1, (int)Math.floor(var1.getPosZ()));
            this.method8(var1x -> var1x.method1().equals(var2));
         }
      }
   }

   private void method8(Predicate<Holograms2.Data3> var1) {
      List var2 = this.field7.stream().filter(var1).toList();
      var2.forEach(var1x -> this.method4(var1x.method1()));
      var2.forEach(this.field7::remove);
   }

   void method9() {
      if (!this.field11) {
         ArrayList var1 = new ArrayList();
         Vector3dc var2 = null;
         Vector3d var3 = null;
         int var4 = 0;

         for (int var5 = 2; var5 < this.field8.size(); var5++) {
            Vector3dc var6 = this.field8.get(var5);
            Vector3dc var7 = this.field8.get(var5 - 1);
            Vector3dc var8 = this.field8.get(var5 - 2);
            Vector3d var9 = var6.add(var8, new Vector3d()).div(2.0);
            boolean var10 = var9.equals(var7, 0.001) && var6.distanceSquared(var8) < 0.5;
            if (var10) {
               if (var2 != null && !var2.add(var3.mul(var5 - 2 - var4, new Vector3d()), new Vector3d()).equals(var8, 0.001)) {
                  var2 = null;
               }

               if (var2 == null) {
                  var2 = var8;
                  var3 = var7.sub(var8, new Vector3d());
                  var4 = var5 - 2;
               } else {
                  int var11 = var5 - var4;
                  var3 = var6.sub(var2, new Vector3d()).div(var11);
                  if (var11 == 15) {
                     var1.add(Files6_2.method1(var2, var3));
                  }
               }
            }
         }

         for (Files6_2 var27 : var1) {
            if (this.field9.getIfPresent(var27.field1) == null) {
               this.field9.put(var27.field1, true);
               double var28 = ((Vector3dc)var27.field1).y();
               double var29 = ((Vector3dc)var27.field2).y();
               boolean var30 = var29 > 0.0;
               int var12 = var30 ? (int)var28 + 1 : (int)var28;
               Vector3i var13 = null;
               double var14 = Double.MAX_VALUE;

               do {
                  double var16 = (var12 - var28) / var29;
                  Vector3d var18 = ((Vector3dc)var27.field1).add(((Vector3dc)var27.field2).mul(var16, new Vector3d()), new Vector3d());
                  if (((Vector3dc)var27.field2).length() * var16 > 2.0 && GuiRewindhandlersHandler24.method7(var18.x(), var18.z())) {
                     double var19 = Math.abs(Math.abs(var18.x() % 1.0) - 0.5);
                     double var21 = Math.abs(Math.abs(var18.z() % 1.0) - 0.5);
                     double var23 = Math.sqrt(var19 * var19 + var21 * var21) / var16 * 300.0;
                     if (var23 < var14) {
                        Vector3i var25 = new Vector3i((int)Math.floor(var18.x()), var12 - 2, (int)Math.floor(var18.z()));
                        if (this.field3.method5(var25)) {
                           var13 = var25;
                           var14 = var23;
                        }
                     }
                  }

                  var12 = var30 ? var12 + 1 : var12 - 1;
               } while (var12 - 2 >= 70 && var12 - 2 <= 100);

               this.field13 = var13 == null;
               if (var13 != null && !this.field2.method9().contains(var13)) {
                  this.field7.add(new Holograms2.Data3(var13));
               }
            }
         }
      }

      this.field11 = true;
      if (this.field5 != null && this.field5.field5 != null) {
         this.field7.add(new Holograms2.Data3(this.field5.field5));
         this.field5 = null;
      }
   }

   void method10(boolean var1) {
      long var2 = ThreadModuleDump63.method3().bridge$getSystemTime();
      long var4 = var2 - this.field6;
      this.field6 = var2;
      if (var4 >= 2300L || this.field5 != null && this.field5.method8()) {
         if (this.field5 != null) {
            if (this.field5.method8() && !this.field5.method9()) {
               return;
            }

            this.field5 = null;
         }

         Vector3d var6 = var1
            ? ThreadModuleDump63.method7().bridge$getLastReportedLookAngle().method6()
            : ThreadModuleDump63.method7().bridge$getLastReportedLookAngle().method6();
         Vector3d var7 = ThreadModuleDump63.method7().bridge$getLastReportedPos().add(0.0, Click3.getEyeHeight(), 0.0).add(var6);
         this.field5 = new Holograms(this.field3, this.field2, var7, var6);
      }
   }

   void method11() {
      if (ThreadModuleDump63.method7() != null) {
         this.field7.removeIf(var1 -> !this.field3.method5(var1.method1()));
         if (this.field4.method7()) {
            this.field12++;
            if (this.field12 > 3) {
               this.method8(
                  var0 -> ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(var0.method1().x(), var0.method1().y(), var0.method1().z()) < 225.0
               );
               if (this.field5 != null
                  && this.field5.field5 != null
                  && ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(this.field5.field5.x(), this.field5.field5.y(), this.field5.field5.z())
                     < 225.0) {
                  this.field5 = null;
               }
            }
         } else {
            this.field12 = 0;
         }
      }
   }

   void method12(String var1, double var2, double var4, double var6) {
      if ("block.note_block.harp".equals(var1) && this.field5 != null) {
         Vector3d var8 = new Vector3d(var2, var4, var6);
         if (this.field5.method1(var8)) {
            this.field5.method3(var8);
         }
      }
   }

   void method13() {
      this.field13 = true;
   }

   void method14(Vector3i var1) {
      if (this.field5 != null && this.field5.field5 != null && this.field5.field5.x == var1.x && this.field5.field5.z == var1.z) {
         this.field5 = null;
      }

      this.method8(var1x -> var1x.method1().x() == var1.x && var1x.method1().z() == var1.z);
   }

   void method15() {
      Vector3i var1 = new Vector3i(
         (int)ThreadModuleDump63.method7().bridge$getPosX(),
         (int)ThreadModuleDump63.method7().bridge$getPosY(),
         (int)ThreadModuleDump63.method7().bridge$getPosZ()
      );
      this.method8(var1x -> var1x.method1().distanceSquared(var1) < 25L);
   }

   class Data3 {
      private final Vector3ic field1;

      Data3(Vector3ic var1) {
         this.field1 = var1;
      }

      public Vector3ic method1() {
         return this.field1;
      }
   }
}

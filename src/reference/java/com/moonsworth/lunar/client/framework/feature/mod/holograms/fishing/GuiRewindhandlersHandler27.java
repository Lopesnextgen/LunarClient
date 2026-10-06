package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.joml.Vector3d;

public class GuiRewindhandlersHandler27 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final long field7 = 200L;
   private static final int field8 = 3;
   private final HashMap<Integer, EntityFishHookBridge> field9 = new HashMap<>();
   private final ArrayList<GuiRewindhandlersHandler27.Data2> field10 = new ArrayList<>();
   private EntityFishHookBridge field11;
   private boolean field12;
   private double field13;
   private double field14;
   private double field15;
   private double field16 = -1.0;
   private long field17 = -1L;
   private boolean field18;

   public GuiRewindhandlersHandler27() {
      this.handle(ProjectileBaseEvent.EventProjectileLaunch.class, this::method1);
      this.handle(ProjectileBaseEvent.EventProjectileRemoval.class, this::method2);
      this.method9(EventParticleSpawn.class, this::method3, 101);
      this.handle(EventEverySecond.class, this::method4);
      this.handle(EventClientTick.class, this::method5);
      this.handle(EventWorldLifecycle.EventWorldChanged.class, this::method6);
   }

   private void method1(ProjectileBaseEvent.EventProjectileLaunch var1) {
      if (Click3.hasIsland()) {
         if (var1.method1() instanceof EntityFishHookBridge var2) {
            this.field9.put(var2.bridge$getEntityId(), var2);
            if (ThreadModuleDump63.method7().equals(var1.method2())) {
               this.field11 = var2;
            }
         }
      }
   }

   private void method2(ProjectileBaseEvent.EventProjectileRemoval var1) {
      if (var1.method1() instanceof EntityFishHookBridge var2) {
         this.field9.remove(var2.bridge$getEntityId());
         if (var2 == this.field11) {
            this.field11 = null;
            this.field16 = -1.0;
            this.field17 = -1L;
            this.field18 = false;
         }
      }
   }

   private void method3(EventParticleSpawn var1) {
      if (Click3.hasIsland()) {
         if (this.method12()) {
            if (this.method13(var1.method2())) {
               double var2 = var1.getPosX();
               double var4 = var1.getPosY();
               double var6 = var1.getPosZ();
               double var8 = var1.method6();
               double var10 = var1.method7();
               double var12 = var1.method8();
               double var14 = ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(var2, var4, var6);
               if (!(var14 > 250.0) && !(Math.abs(var10 - 0.01F) >= 0.001F)) {
                  double var16 = this.method8(var8, -var12);
                  double var18 = this.method8(-var8, var12);
                  ArrayList var20 = new ArrayList();
                  ArrayList var21 = new ArrayList();

                  for (EntityFishHookBridge var23 : this.field9.values()) {
                     int var24 = var23.bridge$getEntityId();
                     if (!var23.bridge$isRemoved() && !var20.contains(var24) && !var21.contains(var24)) {
                        GuiRewindhandlersHandler27.Type var25 = this.method9(var23, var2, var4, var6, var16, var18);
                        if (var25 == GuiRewindhandlersHandler27.Type.ANGLE1) {
                           var20.add(var24);
                        } else if (var25 == GuiRewindhandlersHandler27.Type.ANGLE2) {
                           var21.add(var24);
                        } else if (var25 == GuiRewindhandlersHandler27.Type.EITHER) {
                           var20.add(var24);
                           var21.add(var24);
                        }
                     }
                  }

                  if (!var20.isEmpty() || !var21.isEmpty()) {
                     boolean var47 = false;
                     long var48 = ThreadModuleDump63.method3().bridge$getSystemTime();
                     Iterator var49 = this.field10.iterator();

                     while (true) {
                        GuiRewindhandlersHandler27.Data2 var26;
                        GuiRewindhandlersHandler27.Data var27;
                        double var28;
                        ArrayList var30;
                        while (true) {
                           if (!var49.hasNext()) {
                              if (!var47) {
                                 var20.removeAll(var21);
                                 if (!var20.isEmpty()) {
                                    GuiRewindhandlersHandler27.Data var50 = new GuiRewindhandlersHandler27.Data(var48, var16, var2, var6);

                                    for (Integer var54 : var20) {
                                       EntityFishHookBridge var56 = this.field9.get(var54);
                                       if (var54 != null && !var56.bridge$isRemoved()) {
                                          double var29 = var56.bridge$getPosX() - var2;
                                          double var59 = var56.bridge$getPosZ() - var6;
                                          double var61 = Math.sqrt(var29 * var29 + var59 * var59);
                                          var50.field4.put(var54, var61);
                                       }
                                    }

                                    this.field10.add(new GuiRewindhandlersHandler27.Data2(var50, var20));
                                 } else if (!var21.isEmpty()) {
                                    GuiRewindhandlersHandler27.Data var51 = new GuiRewindhandlersHandler27.Data(var48, var18, var2, var6);

                                    for (Integer var55 : var21) {
                                       EntityFishHookBridge var57 = this.field9.get(var55);
                                       if (var55 != null && !var57.bridge$isRemoved()) {
                                          double var58 = var57.bridge$getPosX() - var2;
                                          double var60 = var57.bridge$getPosZ() - var6;
                                          double var62 = Math.sqrt(var58 * var58 + var60 * var60);
                                          var51.field4.put(var55, var62);
                                       }
                                    }

                                    this.field10.add(new GuiRewindhandlersHandler27.Data2(var51, var21));
                                 }
                              }

                              return;
                           }

                           var26 = (GuiRewindhandlersHandler27.Data2)var49.next();
                           var27 = var26.method1();
                           if (var48 - var27.lastUpdate <= 200L) {
                              if (this.method7(var27.currentAngle, var16, 16.0)) {
                                 var30 = var20;
                                 var28 = var16;
                                 break;
                              }

                              if (this.method7(var27.currentAngle, var18, 16.0)) {
                                 var30 = var21;
                                 var28 = var18;
                                 break;
                              }
                           }
                        }

                        if (!Collections.disjoint(var26.method2(), var30)) {
                           HashSet var31 = new HashSet();

                           for (int var33 : var30) {
                              if (var26.method2().contains(var33) && var27.field4.containsKey(var33)) {
                                 EntityFishHookBridge var34 = this.field9.get(var33);
                                 if (var34 != null && !var34.bridge$isRemoved()) {
                                    double var35 = var27.field4.get(var33);
                                    double var37 = var34.bridge$getPosX() - var2;
                                    double var39 = var34.bridge$getPosZ() - var6;
                                    double var41 = Math.sqrt(var37 * var37 + var39 * var39);
                                    double var43 = var35 - var41;
                                    if (!(var41 >= 0.2) || !(var43 <= -0.1) && !(var43 >= 0.3)) {
                                       if (this.field11.bridge$getEntityId() == var33 && var27.field1 > 3) {
                                          double var45 = var34.method15(var2, var4, var6);
                                          if (this.field16 <= -1.0 || this.field17 <= -1L) {
                                             this.field16 = var45;
                                             this.field17 = ThreadModuleDump63.method3().bridge$getSystemTime();
                                             continue;
                                          }

                                          if (var45 > this.field16 && ThreadModuleDump63.method3().bridge$getSystemTime() - this.field17 >= 200L) {
                                             this.field16 = -1.0;
                                             this.field17 = -1L;
                                             continue;
                                          }

                                          if (var45 < this.field16) {
                                             this.field16 = var45;
                                             this.field17 = ThreadModuleDump63.method3().bridge$getSystemTime();
                                             this.field13 = var2;
                                             this.field14 = var4;
                                             this.field15 = var6;
                                          }
                                       }

                                       var27.field4.put(var33, var41);
                                       var31.add(var33);
                                    }
                                 }
                              }
                           }

                           if (!var31.isEmpty()) {
                              var26.method2().retainAll(var31);
                              var27.field4.keySet().retainAll(var31);
                              var27.lastUpdate = var48;
                              var27.field1++;
                              var27.currentAngle = var28;
                              var47 = true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventEverySecond var1) {
      long var2 = ThreadModuleDump63.method3().bridge$getSystemTime();
      ArrayList var4 = new ArrayList();

      for (GuiRewindhandlersHandler27.Data2 var6 : this.field10) {
         if (var2 - var6.method1().lastUpdate > 700L) {
            var4.add(var6);
         }
      }

      this.field10.retainAll(var4);
   }

   private void method5(EventClientTick var1) {
      if (this.method11()) {
         if (this.field11.bridge$isInWaterOrBubble() || this.field11.bridge$isOnFire()) {
            this.field18 = true;
         }

         if (this.field12 && this.field11.bridge$isInWaterOrBubble()) {
            this.field12 = false;
         } else if (!this.field12 && this.field11.bridge$isOnFire()) {
            this.field12 = true;
         }
      }
   }

   private void method6(EventWorldLifecycle.EventWorldChanged var1) {
      this.field11 = null;
      this.field9.clear();
      this.field10.clear();
      this.field12 = false;
      this.field16 = -1.0;
      this.field17 = -1L;
   }

   private boolean method7(double var1, double var3, double var5) {
      double var7 = Math.abs(var1 - var3);
      if (var7 > 180.0) {
         var7 = 360.0 - var7;
      }

      return var7 <= var5;
   }

   private double method8(double var1, double var3) {
      double var5 = Math.toDegrees(Math.acos(var1 / 0.04F));
      double var7 = Math.toDegrees(Math.asin(var3 / 0.04F));
      if (var1 < 0.0) {
         var7 = 180.0 - var7;
      }

      if (var3 < 0.0) {
         var5 = 360.0 - var5;
      }

      var5 %= 360.0;
      var7 %= 360.0;
      if (var5 < 0.0) {
         var5 += 360.0;
      }

      if (var7 < 0.0) {
         var7 += 360.0;
      }

      double var9 = var5 - var7;
      if (var9 < -180.0) {
         var9 += 360.0;
      }

      if (var9 > 180.0) {
         var9 -= 360.0;
      }

      return var7 + var9 / 2.0;
   }

   private GuiRewindhandlersHandler27.Type method9(EntityFishHookBridge var1, double var2, double var4, double var6, double var8, double var10) {
      double var12 = var4 - var1.bridge$getPosY();
      float var14 = this.field12 ? 1.5F : 0.5F;
      if (Math.abs(var12) > var14) {
         return GuiRewindhandlersHandler27.Type.NONE;
      }

      double var15 = var2 - var1.bridge$getPosX();
      double var17 = var6 - var1.bridge$getPosZ();
      double var19 = Math.sqrt(var15 * var15 + var17 * var17);
      if (var19 < 0.2) {
         return GuiRewindhandlersHandler27.Type.EITHER;
      }

      float var21 = (float)Math.toDegrees(Math.atan2(0.03125, var19)) * 1.5F;
      float var22 = (float)Math.toDegrees(Math.atan2(var15, var17));
      var22 %= 360.0F;
      if (var22 < 0.0F) {
         var22 += 360.0F;
      }

      if (this.method7(var8, var22, var21)) {
         return GuiRewindhandlersHandler27.Type.ANGLE1;
      } else {
         return this.method7(var10, var22, var21) ? GuiRewindhandlersHandler27.Type.ANGLE2 : GuiRewindhandlersHandler27.Type.NONE;
      }
   }

   public Vector3d method10() {
      if (!this.method12()) {
         return null;
      } else {
         return !(this.field16 <= 0.0) && !(this.field16 >= 8.5) && this.field17 > 0L ? new Vector3d(this.field13, this.field14, this.field15) : null;
      }
   }

   public boolean method11() {
      return this.field11 != null;
   }

   public boolean method12() {
      return this.method11() && this.field18;
   }

   public boolean method13(HorsestatsType2 var1) {
      return this.field12 ? var1 == HorsestatsType2.SMOKE : var1 == HorsestatsType2.FISHING;
   }

   @Generated
   public EntityFishHookBridge method15() {
      return this.field11;
   }

   @Generated
   public boolean method16() {
      return this.field12;
   }

   public static class Data {
      public int field1 = 0;
      public long lastUpdate;
      public double currentAngle;
      public double field2;
      public double field3;
      public final Map<Integer, Double> field4 = new HashMap<>();

      public Data(long var1, double var3, double var5, double var7) {
         this.lastUpdate = var1;
         this.currentAngle = var3;
         this.field2 = var5;
         this.field3 = var7;
      }
   }

   public class Data2 {
      private final GuiRewindhandlersHandler27.Data field1;
      private final List<Integer> field2;

      public Data2(GuiRewindhandlersHandler27.Data var1, List<Integer> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public GuiRewindhandlersHandler27.Data method1() {
         return this.field1;
      }

      public List<Integer> method2() {
         return this.field2;
      }
   }

   private enum Type {
      NONE,
      EITHER,
      ANGLE1,
      ANGLE2;
   }
}

package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileSpawn;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileRemove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.joml.Vector3d;

public class FishingHookTracker extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final long field7 = 200L;
   private static final int field8 = 3;
   private final HashMap<Integer, EntityFishHookBridge> field9 = new HashMap<>();
   private final ArrayList<FishingHookTracker.Trail> field10 = new ArrayList<>();
   private EntityFishHookBridge field11;
   private boolean field12;
   private double field13;
   private double field14;
   private double field15;
   private double field16 = -1.0;
   private long field17 = -1L;
   private boolean field18;

   public FishingHookTracker() {
      this.handle(EventProjectileSpawn.class, this::method1);
      this.handle(EventProjectileRemove.class, this::method2);
      this.method9(EventSpawnParticle.class, this::method3, 101);
      this.handle(EventSecond.class, this::method4);
      this.handle(EventTick.class, this::method5);
      this.handle(EventWorldChange.class, this::method6);
   }

   private void method1(EventProjectileSpawn data71) {
      if (IslandUtils.isOnIsland()) {
         if (data71.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof EntityFishHookBridge bridgeextension232) {
            this.field9.put(bridgeextension232.bridge$getEntityId(), bridgeextension232);
            if (Ref.method7().equals(data71.method2())) {
               this.field11 = bridgeextension232;
            }
         }
      }
   }

   private void method2(EventProjectileRemove data91) {
      if (data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof EntityFishHookBridge bridgeextension232) {
         this.field9.remove(bridgeextension232.bridge$getEntityId());
         if (bridgeextension232 == this.field11) {
            this.field11 = null;
            this.field16 = -1.0;
            this.field17 = -1L;
            this.field18 = false;
         }
      }
   }

   private void method3(EventSpawnParticle highlightimpl151) {
      if (IslandUtils.isOnIsland()) {
         if (this.method12()) {
            if (this.method13(highlightimpl151.method2())) {
               double value2 = highlightimpl151.getPosX();
               double value4 = highlightimpl151.getPosY();
               double value6 = highlightimpl151.getPosZ();
               double value8 = highlightimpl151.method6();
               double value10 = highlightimpl151.method7();
               double value12 = highlightimpl151.method8();
               double value14 = Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(value2, value4, value6);
               if (!(value14 > 250.0) && !(Math.abs(value10 - 0.01F) >= 0.001F)) {
                  double value16 = this.method8(value8, -value12);
                  double value18 = this.method8(-value8, value12);
                  ArrayList list20 = new ArrayList();
                  ArrayList list21 = new ArrayList();

                  for (EntityFishHookBridge bridgeextension2323 : this.field9.values()) {
                     int index24 = bridgeextension2323.bridge$getEntityId();
                     if (!bridgeextension2323.bridge$isRemoved() && !list20.contains(index24) && !list21.contains(index24)) {
                        FishingHookTracker.Type type25 = this.method9(bridgeextension2323, value2, value4, value6, value16, value18);
                        if (type25 == FishingHookTracker.Type.ANGLE1) {
                           list20.add(index24);
                        } else if (type25 == FishingHookTracker.Type.ANGLE2) {
                           list21.add(index24);
                        } else if (type25 == FishingHookTracker.Type.EITHER) {
                           list20.add(index24);
                           list21.add(index24);
                        }
                     }
                  }

                  if (!list20.isEmpty() || !list21.isEmpty()) {
                     boolean flag47 = false;
                     long number48 = Ref.method3().bridge$getSystemTime();
                     Iterator iterator49 = this.field10.iterator();

                     while (true) {
                        FishingHookTracker.Trail data226;
                        FishingHookTracker.Data data27;
                        double value28;
                        ArrayList list30;
                        while (true) {
                           if (!iterator49.hasNext()) {
                              if (!flag47) {
                                 list20.removeAll(list21);
                                 if (!list20.isEmpty()) {
                                    FishingHookTracker.Data data50 = new FishingHookTracker.Data(number48, value16, value2, value6);

                                    for (Integer index54 : list20) {
                                       EntityFishHookBridge bridgeextension2356 = this.field9.get(index54);
                                       if (index54 != null && !bridgeextension2356.bridge$isRemoved()) {
                                          double value29 = bridgeextension2356.bridge$getPosX() - value2;
                                          double value59 = bridgeextension2356.bridge$getPosZ() - value6;
                                          double value61 = Math.sqrt(value29 * value29 + value59 * value59);
                                          data50.field4.put(index54, value61);
                                       }
                                    }

                                    this.field10.add(new FishingHookTracker.Trail(data50, list20));
                                 } else if (!list21.isEmpty()) {
                                    FishingHookTracker.Data data51 = new FishingHookTracker.Data(number48, value18, value2, value6);

                                    for (Integer index55 : list21) {
                                       EntityFishHookBridge bridgeextension2357 = this.field9.get(index55);
                                       if (index55 != null && !bridgeextension2357.bridge$isRemoved()) {
                                          double value58 = bridgeextension2357.bridge$getPosX() - value2;
                                          double value60 = bridgeextension2357.bridge$getPosZ() - value6;
                                          double value62 = Math.sqrt(value58 * value58 + value60 * value60);
                                          data51.field4.put(index55, value62);
                                       }
                                    }

                                    this.field10.add(new FishingHookTracker.Trail(data51, list21));
                                 }
                              }

                              return;
                           }

                           data226 = (FishingHookTracker.Trail)iterator49.next();
                           data27 = data226.method1();
                           if (number48 - data27.lastUpdate <= 200L) {
                              if (this.method7(data27.currentAngle, value16, 16.0)) {
                                 list30 = list20;
                                 value28 = value16;
                                 break;
                              }

                              if (this.method7(data27.currentAngle, value18, 16.0)) {
                                 list30 = list21;
                                 value28 = value18;
                                 break;
                              }
                           }
                        }

                        if (!Collections.disjoint(data226.method2(), list30)) {
                           HashSet set31 = new HashSet();

                           for (int index33 : list30) {
                              if (data226.method2().contains(index33) && data27.field4.containsKey(index33)) {
                                 EntityFishHookBridge bridgeextension2334 = this.field9.get(index33);
                                 if (bridgeextension2334 != null && !bridgeextension2334.bridge$isRemoved()) {
                                    double value35 = data27.field4.get(index33);
                                    double value37 = bridgeextension2334.bridge$getPosX() - value2;
                                    double value39 = bridgeextension2334.bridge$getPosZ() - value6;
                                    double value41 = Math.sqrt(value37 * value37 + value39 * value39);
                                    double value43 = value35 - value41;
                                    if (!(value41 >= 0.2) || !(value43 <= -0.1) && !(value43 >= 0.3)) {
                                       if (this.field11.bridge$getEntityId() == index33 && data27.field1 > 3) {
                                          double value45 = bridgeextension2334.method15(value2, value4, value6);
                                          if (this.field16 <= -1.0 || this.field17 <= -1L) {
                                             this.field16 = value45;
                                             this.field17 = Ref.method3().bridge$getSystemTime();
                                             continue;
                                          }

                                          if (value45 > this.field16 && Ref.method3().bridge$getSystemTime() - this.field17 >= 200L) {
                                             this.field16 = -1.0;
                                             this.field17 = -1L;
                                             continue;
                                          }

                                          if (value45 < this.field16) {
                                             this.field16 = value45;
                                             this.field17 = Ref.method3().bridge$getSystemTime();
                                             this.field13 = value2;
                                             this.field14 = value4;
                                             this.field15 = value6;
                                          }
                                       }

                                       data27.field4.put(index33, value41);
                                       set31.add(index33);
                                    }
                                 }
                              }
                           }

                           if (!set31.isEmpty()) {
                              data226.method2().retainAll(set31);
                              data27.field4.keySet().retainAll(set31);
                              data27.lastUpdate = number48;
                              data27.field1++;
                              data27.currentAngle = value28;
                              flag47 = true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventSecond highlightimpl41) {
      long number2 = Ref.method3().bridge$getSystemTime();
      ArrayList list4 = new ArrayList();

      for (FishingHookTracker.Trail data26 : this.field10) {
         if (number2 - data26.method1().lastUpdate > 700L) {
            list4.add(data26);
         }
      }

      this.field10.retainAll(list4);
   }

   private void method5(EventTick highlightimpl21) {
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

   private void method6(EventWorldChange data31) {
      this.field11 = null;
      this.field9.clear();
      this.field10.clear();
      this.field12 = false;
      this.field16 = -1.0;
      this.field17 = -1L;
   }

   private boolean method7(double value1, double value3, double value5) {
      double value7 = Math.abs(value1 - value3);
      if (value7 > 180.0) {
         value7 = 360.0 - value7;
      }

      return value7 <= value5;
   }

   private double method8(double value1, double value3) {
      double value5 = Math.toDegrees(Math.acos(value1 / 0.04F));
      double value7 = Math.toDegrees(Math.asin(value3 / 0.04F));
      if (value1 < 0.0) {
         value7 = 180.0 - value7;
      }

      if (value3 < 0.0) {
         value5 = 360.0 - value5;
      }

      value5 %= 360.0;
      value7 %= 360.0;
      if (value5 < 0.0) {
         value5 += 360.0;
      }

      if (value7 < 0.0) {
         value7 += 360.0;
      }

      double value9 = value5 - value7;
      if (value9 < -180.0) {
         value9 += 360.0;
      }

      if (value9 > 180.0) {
         value9 -= 360.0;
      }

      return value7 + value9 / 2.0;
   }

   private FishingHookTracker.Type method9(EntityFishHookBridge bridgeextension231, double value2, double value4, double value6, double value8, double value10) {
      double value12 = value4 - bridgeextension231.bridge$getPosY();
      float value14 = this.field12 ? 1.5F : 0.5F;
      if (Math.abs(value12) > value14) {
         return FishingHookTracker.Type.NONE;
      }

      double value15 = value2 - bridgeextension231.bridge$getPosX();
      double value17 = value6 - bridgeextension231.bridge$getPosZ();
      double value19 = Math.sqrt(value15 * value15 + value17 * value17);
      if (value19 < 0.2) {
         return FishingHookTracker.Type.EITHER;
      }

      float value21 = (float)Math.toDegrees(Math.atan2(0.03125, value19)) * 1.5F;
      float value22 = (float)Math.toDegrees(Math.atan2(value15, value17));
      value22 %= 360.0F;
      if (value22 < 0.0F) {
         value22 += 360.0F;
      }

      if (this.method7(value8, value22, value21)) {
         return FishingHookTracker.Type.ANGLE1;
      } else {
         return this.method7(value10, value22, value21) ? FishingHookTracker.Type.ANGLE2 : FishingHookTracker.Type.NONE;
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

   public boolean method13(ParticleType horsestatstype21) {
      return this.field12 ? horsestatstype21 == ParticleType.SMOKE : horsestatstype21 == ParticleType.FISHING;
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

      public Data(long number1, double value3, double value5, double value7) {
         this.lastUpdate = number1;
         this.currentAngle = value3;
         this.field2 = value5;
         this.field3 = value7;
      }
   }

   public class Trail {
      private final FishingHookTracker.Data field1;
      private final List<Integer> field2;

      public Trail(FishingHookTracker.Data data1, List<Integer> list2) {
         this.field1 = data1;
         this.field2 = list2;
      }

      public FishingHookTracker.Data method1() {
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

      Type() {
      }
   }
}

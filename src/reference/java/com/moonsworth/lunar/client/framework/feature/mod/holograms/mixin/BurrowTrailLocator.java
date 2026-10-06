package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.files.ValuePair;
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

class BurrowTrailLocator {
   private static final String field1 = "block.note_block.harp";
   private final BurrowLocatingListener field2;
   private final BurrowGroundScanner field3;
   private final EquippedItemListener field4;
   private BurrowTrailPredictor field5;
   private long field6 = 0L;
   private final Set<BurrowTrailLocator.LocatedBurrow> field7 = new HashSet<>();
   private final List<Vector3dc> field8 = new ArrayList<>();
   private final Cache<Vector3dc, Object> field9 = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.SECONDS).build();
   private final Cache<Vector3ic, Object> field10 = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.SECONDS).build();
   private boolean field11 = true;
   private int field12;
   private boolean field13;

   BurrowTrailLocator(BurrowLocatingListener guirewindhandlershandler231, BurrowGroundScanner guirewindhandlershandler242, EquippedItemListener guirewindhandlershandler293) {
      this.field2 = guirewindhandlershandler231;
      this.field3 = guirewindhandlershandler242;
      this.field4 = guirewindhandlershandler293;
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
      Stream stream1 = this.field7.stream().map(BurrowTrailLocator.LocatedBurrow::method1);
      return this.field5 != null && this.field5.field5 != null ? Stream.concat(Stream.of(this.field5.field5), stream1).toList() : stream1.toList();
   }

   void method4(Vector3ic vector3ic1) {
      this.field10.put(vector3ic1, true);
   }

   boolean method5(EventSpawnParticle highlightimpl151) {
      return this.field5 != null
         && this.field5.method1(new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ()))
         && Ref.method3().bridge$getSystemTime() - this.field5.field6 < 1000L;
   }

   void method6(EventSpawnParticle highlightimpl151) {
      this.method7(highlightimpl151);
      if (highlightimpl151.method2() == ParticleType.DRIPPING_LAVA) {
         if (Math.abs(highlightimpl151.method12()) == 0.0F) {
            return;
         }

         if (this.field5 != null) {
            this.field5.method2(new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ()));
         }
      }

      if (highlightimpl151.method2() == ParticleType.DUST) {
         Vector3d vector3d2 = new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ());
         boolean flag3 = false;

         for (Vector3ic vector3ic5 : this.field10.asMap().keySet()) {
            if (vector3d2.distanceSquared(vector3ic5.x() + 0.5, vector3ic5.y() + 2, vector3ic5.z() + 0.5) < 9.0) {
               flag3 = true;
            }
         }

         if (flag3) {
            if (this.field11) {
               this.field8.clear();
               this.field11 = false;
            }

            if (this.field8.isEmpty() || this.field8.get(this.field8.size() - 1).distanceSquared(vector3d2) > 0.001) {
               this.field8.add(vector3d2);
               if (this.field8.size() > 100) {
                  this.field8.remove(0);
               }
            }
         }
      }
   }

   private void method7(EventSpawnParticle highlightimpl151) {
      if (this.field4.method7()) {
         if (highlightimpl151.method2() == ParticleType.LARGE_SMOKE) {
            Vector3i vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()) - 1, (int)Math.floor(highlightimpl151.getPosZ()));
            this.method8(arg1x -> arg1x.method1().equals(vector3i2));
         }
      }
   }

   private void method8(Predicate<BurrowTrailLocator.LocatedBurrow> predicate1) {
      List list2 = this.field7.stream().filter(predicate1).toList();
      list2.forEach(arg1x -> this.method4(arg1x.method1()));
      list2.forEach(this.field7::remove);
   }

   void method9() {
      if (!this.field11) {
         ArrayList list1 = new ArrayList();
         Vector3dc vector3dc2 = null;
         Vector3d vector3d3 = null;
         int number4 = 0;

         for (int index5 = 2; index5 < this.field8.size(); index5++) {
            Vector3dc vector3dc6 = this.field8.get(index5);
            Vector3dc vector3dc7 = this.field8.get(index5 - 1);
            Vector3dc vector3dc8 = this.field8.get(index5 - 2);
            Vector3d vector3d9 = vector3dc6.add(vector3dc8, new Vector3d()).div(2.0);
            boolean flag10 = vector3d9.equals(vector3dc7, 0.001) && vector3dc6.distanceSquared(vector3dc8) < 0.5;
            if (flag10) {
               if (vector3dc2 != null && !vector3dc2.add(vector3d3.mul(index5 - 2 - number4, new Vector3d()), new Vector3d()).equals(vector3dc8, 0.001)) {
                  vector3dc2 = null;
               }

               if (vector3dc2 == null) {
                  vector3dc2 = vector3dc8;
                  vector3d3 = vector3dc7.sub(vector3dc8, new Vector3d());
                  number4 = index5 - 2;
               } else {
                  int number11 = index5 - number4;
                  vector3d3 = vector3dc6.sub(vector3dc2, new Vector3d()).div(number11);
                  if (number11 == 15) {
                     list1.add(ValuePair.method1(vector3dc2, vector3d3));
                  }
               }
            }
         }

         for (ValuePair files6_227 : list1) {
            if (this.field9.getIfPresent(files6_227.field1) == null) {
               this.field9.put((Vector3dc)files6_227.field1, true);
               double value28 = ((Vector3dc)files6_227.field1).y();
               double value29 = ((Vector3dc)files6_227.field2).y();
               boolean flag30 = value29 > 0.0;
               int number12 = flag30 ? (int)value28 + 1 : (int)value28;
               Vector3i vector3i13 = null;
               double value14 = Double.MAX_VALUE;

               do {
                  double value16 = (number12 - value28) / value29;
                  Vector3d vector3d18 = ((Vector3dc)files6_227.field1).add(((Vector3dc)files6_227.field2).mul(value16, new Vector3d()), new Vector3d());
                  if (((Vector3dc)files6_227.field2).length() * value16 > 2.0 && BurrowGroundScanner.method7(vector3d18.x(), vector3d18.z())) {
                     double value19 = Math.abs(Math.abs(vector3d18.x() % 1.0) - 0.5);
                     double value21 = Math.abs(Math.abs(vector3d18.z() % 1.0) - 0.5);
                     double value23 = Math.sqrt(value19 * value19 + value21 * value21) / value16 * 300.0;
                     if (value23 < value14) {
                        Vector3i vector3i25 = new Vector3i((int)Math.floor(vector3d18.x()), number12 - 2, (int)Math.floor(vector3d18.z()));
                        if (this.field3.method5(vector3i25)) {
                           vector3i13 = vector3i25;
                           value14 = value23;
                        }
                     }
                  }

                  number12 = flag30 ? number12 + 1 : number12 - 1;
               } while (number12 - 2 >= 70 && number12 - 2 <= 100);

               this.field13 = vector3i13 == null;
               if (vector3i13 != null && !this.field2.method9().contains(vector3i13)) {
                  this.field7.add(new BurrowTrailLocator.LocatedBurrow(vector3i13));
               }
            }
         }
      }

      this.field11 = true;
      if (this.field5 != null && this.field5.field5 != null) {
         this.field7.add(new BurrowTrailLocator.LocatedBurrow(this.field5.field5));
         this.field5 = null;
      }
   }

   void method10(boolean flag1) {
      long number2 = Ref.method3().bridge$getSystemTime();
      long number4 = number2 - this.field6;
      this.field6 = number2;
      if (number4 >= 2300L || this.field5 != null && this.field5.method8()) {
         if (this.field5 != null) {
            if (this.field5.method8() && !this.field5.method9()) {
               return;
            }

            this.field5 = null;
         }

         Vector3d vector3d6 = flag1
            ? Ref.method7().IHICCCCICICRICOIIRCOIHRCCCIICO().method6()
            : Ref.method7().bridge$getLastReportedLookAngle().method6();
         Vector3d vector3d7 = Ref.method7().bridge$getLastReportedPos().add(0.0, IslandUtils.getEyeHeight(), 0.0).add(vector3d6);
         this.field5 = new BurrowTrailPredictor(this.field3, this.field2, vector3d7, vector3d6);
      }
   }

   void method11() {
      if (Ref.method7() != null) {
         this.field7.removeIf(arg1 -> !this.field3.method5(arg1.method1()));
         if (this.field4.method7()) {
            this.field12++;
            if (this.field12 > 3) {
               this.method8(
                  arg0 -> Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(arg0.method1().x(), arg0.method1().y(), arg0.method1().z()) < 225.0
               );
               if (this.field5 != null
                  && this.field5.field5 != null
                  && Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(this.field5.field5.x(), this.field5.field5.y(), this.field5.field5.z())
                     < 225.0) {
                  this.field5 = null;
               }
            }
         } else {
            this.field12 = 0;
         }
      }
   }

   void method12(String text1, double value2, double value4, double value6) {
      if ("block.note_block.harp".equals(text1) && this.field5 != null) {
         Vector3d vector3d8 = new Vector3d(value2, value4, value6);
         if (this.field5.method1(vector3d8)) {
            this.field5.method3(vector3d8);
         }
      }
   }

   void method13() {
      this.field13 = true;
   }

   void method14(Vector3i vector3i1) {
      if (this.field5 != null && this.field5.field5 != null && this.field5.field5.x == vector3i1.x && this.field5.field5.z == vector3i1.z) {
         this.field5 = null;
      }

      this.method8(arg1x -> arg1x.method1().x() == vector3i1.x && arg1x.method1().z() == vector3i1.z);
   }

   void method15() {
      Vector3i vector3i1 = new Vector3i(
         (int)Ref.method7().bridge$getPosX(),
         (int)Ref.method7().bridge$getPosY(),
         (int)Ref.method7().bridge$getPosZ()
      );
      this.method8(arg1x -> arg1x.method1().distanceSquared(vector3i1) < 25L);
   }

   class LocatedBurrow {
      private final Vector3ic field1;

      LocatedBurrow(Vector3ic vector3ic1) {
         this.field1 = vector3ic1;
      }

      public Vector3ic method1() {
         return this.field1;
      }
   }
}

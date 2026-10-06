package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunk.EventChunkLoad;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class BurrowGroundScanner extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   public static final int field7 = -283;
   public static final int field8 = 200;
   public static final int field9 = -230;
   public static final int field10 = 200;
   public static final int field11 = 70;
   public static final int field12 = 100;
   private final Map<Long, BurrowGroundScanner.Data> field13 = new HashMap<>();
   private final Deque<ChunkBridge> field14 = new ArrayDeque<>();

   public BurrowGroundScanner() {
      this.handle(EventChunkLoad.class, this::method1);
      this.handle(EventWorldChange.class, this::method2);
      this.handle(EventTick.class, this::method3);
   }

   protected boolean isEnabled() {
      return IslandUtils.getIsland() == SkyblockIsland.HUB;
   }

   protected void onDisable() {
      this.field13.clear();
      this.field14.clear();
   }

   protected void onEnable() {
      int number1 = Ref.method7().bridge$getBlockX() >> 4;
      int number2 = Ref.method7().bridge$getBlockZ() >> 4;

      for (int index3 = number1 - 8; index3 <= number1 + 8; index3++) {
         for (int index4 = number2 - 8; index4 <= number2 + 8; index4++) {
            this.field14.add(Ref.method8().bridge$getChunk(index3, index4));
         }
      }
   }

   private void method1(EventChunkLoad data91) {
      for (ChunkBridge itemcounter23 : this.field14) {
         if (itemcounter23.bridge$getX() == data91.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getX()
            && itemcounter23.bridge$getZ() == data91.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getZ()) {
            return;
         }
      }

      long index5 = MapCoord.method2(data91.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getX(), data91.IIOHICICIRRRHOCCIOORRHHHIHHICR().bridge$getZ());
      BurrowGroundScanner.Data data4 = this.field13.get(index5);
      if (data4 == null || data4.method2() <= Ref.method3().bridge$getSystemTime() - 300000L) {
         this.field14.add(data91.IIOHICICIRRRHOCCIOORRHHHIHHICR());
      }
   }

   private void method2(EventWorldChange data31) {
      this.field13.clear();
      this.field14.clear();
   }

   private void method3(EventTick highlightimpl21) {
      this.method5();
   }

   private void method5() {
      if (!this.field14.isEmpty()) {
         ChunkBridge itemcounter21 = this.field14.poll();
         if (itemcounter21.bridge$isLoaded()) {
            this.field13.put(method6(itemcounter21), new BurrowGroundScanner.Data(itemcounter21));
         } else {
            this.method5();
         }
      }
   }

   public boolean method5(Vector3ic vector3ic1) {
      if (!method7(vector3ic1.x(), vector3ic1.z())) {
         return false;
      }

      if (vector3ic1.y() >= 70 && vector3ic1.y() <= 100) {
         if (!Ref.method8().bridge$isChunkLoaded(vector3ic1.x() >> 4, vector3ic1.z() >> 4)) {
            long index2 = MapCoord.method3(vector3ic1);
            BurrowGroundScanner.Data data4 = this.field13.get(index2);
            return data4 == null ? true : data4.method1(vector3ic1);
         } else {
            return Ref.method8().ICRHORIIHOHROHOHOCOOHOOCOORRHO(vector3ic1) == Bridge.method34().method7()
               && Ref.method8().bridge$getBlockAt(vector3ic1.x(), vector3ic1.y() + 1, vector3ic1.z()).bridge$isAir();
         }
      } else {
         return false;
      }
   }

   private static long method6(ChunkBridge itemcounter20) {
      return MapCoord.method2(itemcounter20.bridge$getX(), itemcounter20.bridge$getZ());
   }

   public static boolean method7(double value0, double value2) {
      return value0 >= -283.0 && value0 <= 200.0 && value2 >= -230.0 && value2 <= 200.0 && value0 + value2 >= -380.0 && value2 - value0 >= -300.0;
   }

   private static class Data {
      private final long field1 = Ref.method3().bridge$getSystemTime();
      private final Set<Vector3ic> field2 = new HashSet<>();

      public Data(ChunkBridge itemcounter21) {
         int number2 = itemcounter21.bridge$getX() << 4;
         int number3 = itemcounter21.bridge$getZ() << 4;
         Bridge3_23 bridge3_234 = Bridge.method34().method7();

         for (int index5 = 0; index5 < 16; index5++) {
            for (int index6 = 0; index6 < 16; index6++) {
               for (int index7 = 70; index7 <= 100; index7++) {
                  if (BurrowGroundScanner.method7(index5 + number2, index6 + number3)) {
                     Bridge3_23 bridge3_238 = itemcounter21.bridge$getBlockState(index5, index7, index6).bridge$getBlock();
                     if (bridge3_238 == bridge3_234) {
                        Bridge3_23 bridge3_239 = itemcounter21.bridge$getBlockState(index5, index7 + 1, index6).bridge$getBlock();
                        if (bridge3_239.bridge$isAir()) {
                           this.field2.add(new Vector3i(index5 + number2, index7, index6 + number3));
                        }
                     }
                  }
               }
            }
         }
      }

      public boolean method1(Vector3ic vector3ic1) {
         return this.field2.contains(vector3ic1);
      }

      public long method2() {
         return this.field1;
      }
   }
}

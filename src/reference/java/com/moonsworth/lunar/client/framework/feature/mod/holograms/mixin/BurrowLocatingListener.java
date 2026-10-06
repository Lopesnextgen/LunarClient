package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.Burrow;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowKind;
import com.moonsworth.lunar.client.event.player.EventItemUse;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate;
import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class BurrowLocatingListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final EquippedItemListener field7 = (EquippedItemListener)this.method3(EquippedItemListener.class);
   private final BurrowGroundScanner field8 = (BurrowGroundScanner)this.method3(BurrowGroundScanner.class);
   private final BurrowTrailLocator field9 = new BurrowTrailLocator(this, this.field8, this.field7);
   private final BurrowTracker field10 = new BurrowTracker(this, this.field8, this.field7);

   private BurrowLocatingListener() {
      this.handle(EventWorldChange.class, arg1 -> {
         this.field9.clear();
         this.field10.clear();
      });
      this.handle(EventSpawnParticle.class, this::method1);
      this.handle(EventPlaySound.class, arg1 -> this.field9.method12(arg1.getPath(), arg1.method5(), arg1.method6(), arg1.method7()));
      this.handle(BlockUpdate.class, arg1 -> {
         if (this.field7.method7()) {
            this.field9.method10(false);
         }
      });
      this.handle(EventItemUse.class, arg1 -> {
         if (arg1.method1().equals(Ref.method7()) && this.field7.method7()) {
            this.field9.method10(true);
         }
      });
      this.handle(EventTick.class, arg1 -> {
         this.field9.method9();
         this.field10.method1();
      });
      this.handle(EventSecond.class, arg1 -> {
         this.field9.method11();
         this.field10.method14();
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
   }

   protected void onDisable() {
      this.field9.clear();
      this.field10.clear();
   }

   private void method1(EventSpawnParticle highlightimpl151) {
      if (this.field9.method5(highlightimpl151)) {
         this.field9.method6(highlightimpl151);
      } else {
         this.field10.method2(highlightimpl151);
         this.field9.method6(highlightimpl151);
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      if (text2.startsWith("You died") || text2.startsWith("You were killed")) {
         this.field10.method9();
      }

      if (text2.startsWith("You dug out a Griffin Burrow! (") || text2.startsWith("You finished the Griffin burrow chain! (")) {
         this.field10.method10();
         List list3 = this.field10.method11(5);
         list3.forEach(this.field9::method4);
         this.field9.method15();
      }

      if (text2.startsWith("You finished the Griffin burrow chain! (")) {
         this.field9.method13();
      }

      if (text2.startsWith("You dug out ")) {
         this.field10.method12();
      }
   }

   void method3(Vector3i vector3i1) {
      this.field9.method14(vector3i1);
   }

   public boolean method5() {
      return this.field9.method1();
   }

   public void method6() {
      this.field9.method2();
   }

   public Map<Vector3i, Burrow> method7() {
      return this.field10.method15();
   }

   public Collection<Vector3ic> method8() {
      return this.field9.method3();
   }

   public Set<Vector3ic> method9() {
      HashSet set1 = new HashSet<>(this.field9.method3());
      set1.addAll(this.field10.method15().keySet());
      return set1;
   }

   public Map<Vector3ic, Integer> method9(SkyblockBurrowLocating skyblockburrowlocating1) {
      HashMap map2 = new HashMap();

      for (Vector3ic vector3ic4 : this.field9.method3()) {
         map2.put(vector3ic4, skyblockburrowlocating1.method17().method14(0.0F));
      }

      this.field10.method15().forEach((arg2x, arg3) -> map2.put(arg2x, BurrowKind.getColor(skyblockburrowlocating1, arg3.method6())));
      return map2;
   }

   public Optional<Entry<Vector3ic, Integer>> method10(SkyblockBurrowLocating skyblockburrowlocating1) {
      Entry entry2 = null;
      double value3 = Double.MAX_VALUE;

      for (Entry entry6 : this.method9(skyblockburrowlocating1).entrySet()) {
         Vector3ic vector3ic7 = (Vector3ic)entry6.getKey();
         double value8 = Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(vector3ic7.x(), vector3ic7.y(), vector3ic7.z());
         if (value8 < value3) {
            entry2 = entry6;
            value3 = value8;
         }
      }

      return Optional.ofNullable(entry2);
   }

   public Optional<Vector3ic> method11() {
      Vector3ic vector3ic1 = null;
      double value2 = Double.MAX_VALUE;

      for (Vector3ic vector3ic5 : this.method9()) {
         double value6 = Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(vector3ic5.x(), vector3ic5.y(), vector3ic5.z());
         if (value6 < value2) {
            vector3ic1 = vector3ic5;
            value2 = value6;
         }
      }

      return Optional.ofNullable(vector3ic1);
   }
}

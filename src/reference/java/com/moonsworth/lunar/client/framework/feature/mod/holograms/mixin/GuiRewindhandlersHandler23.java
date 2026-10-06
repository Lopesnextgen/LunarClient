package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.Burrow;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowType2;
import com.moonsworth.lunar.client.event.player.EventItemRightClick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdateNotify.Data;
import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class GuiRewindhandlersHandler23 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler29 field7 = (GuiRewindhandlersHandler29)this.method3(GuiRewindhandlersHandler29.class);
   private final GuiRewindhandlersHandler24 field8 = (GuiRewindhandlersHandler24)this.method3(GuiRewindhandlersHandler24.class);
   private final Holograms2 field9 = new Holograms2(this, this.field8, this.field7);
   private final Holograms3 field10 = new Holograms3(this, this.field8, this.field7);

   private GuiRewindhandlersHandler23() {
      this.handle(EventWorldChanged.class, var1 -> {
         this.field9.clear();
         this.field10.clear();
      });
      this.handle(EventParticleSpawn.class, this::method1);
      this.handle(EventSoundPlay.class, var1 -> this.field9.method12(var1.getPath(), var1.method5(), var1.method6(), var1.method7()));
      this.handle(Data.class, var1 -> {
         if (this.field7.method7()) {
            this.field9.method10(false);
         }
      });
      this.handle(EventItemRightClick.class, var1 -> {
         if (var1.method1().equals(ThreadModuleDump63.method7()) && this.field7.method7()) {
            this.field9.method10(true);
         }
      });
      this.handle(EventClientTick.class, var1 -> {
         this.field9.method9();
         this.field10.method1();
      });
      this.handle(EventEverySecond.class, var1 -> {
         this.field9.method11();
         this.field10.method14();
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, this::method2);
   }

   protected void onDisable() {
      this.field9.clear();
      this.field10.clear();
   }

   private void method1(EventParticleSpawn var1) {
      if (this.field9.method5(var1)) {
         this.field9.method6(var1);
      } else {
         this.field10.method2(var1);
         this.field9.method6(var1);
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
      if (var2.startsWith("You died") || var2.startsWith("You were killed")) {
         this.field10.method9();
      }

      if (var2.startsWith("You dug out a Griffin Burrow! (") || var2.startsWith("You finished the Griffin burrow chain! (")) {
         this.field10.method10();
         List var3 = this.field10.method11(5);
         var3.forEach(this.field9::method4);
         this.field9.method15();
      }

      if (var2.startsWith("You finished the Griffin burrow chain! (")) {
         this.field9.method13();
      }

      if (var2.startsWith("You dug out ")) {
         this.field10.method12();
      }
   }

   void method3(Vector3i var1) {
      this.field9.method14(var1);
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
      HashSet var1 = new HashSet<>(this.field9.method3());
      var1.addAll(this.field10.method15().keySet());
      return var1;
   }

   public Map<Vector3ic, Integer> method9(SkyblockBurrowLocating var1) {
      HashMap var2 = new HashMap();

      for (Vector3ic var4 : this.field9.method3()) {
         var2.put(var4, var1.method17().method14(0.0F));
      }

      this.field10.method15().forEach((var2x, var3) -> var2.put(var2x, BurrowType2.getColor(var1, var3.method6())));
      return var2;
   }

   public Optional<Entry<Vector3ic, Integer>> method10(SkyblockBurrowLocating var1) {
      Entry var2 = null;
      double var3 = Double.MAX_VALUE;

      for (Entry var6 : this.method9(var1).entrySet()) {
         Vector3ic var7 = (Vector3ic)var6.getKey();
         double var8 = ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(var7.x(), var7.y(), var7.z());
         if (var8 < var3) {
            var2 = var6;
            var3 = var8;
         }
      }

      return Optional.ofNullable(var2);
   }

   public Optional<Vector3ic> method11() {
      Vector3ic var1 = null;
      double var2 = Double.MAX_VALUE;

      for (Vector3ic var5 : this.method9()) {
         double var6 = ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(var5.x(), var5.y(), var5.z());
         if (var6 < var2) {
            var1 = var5;
            var2 = var6;
         }
      }

      return Optional.ofNullable(var1);
   }
}

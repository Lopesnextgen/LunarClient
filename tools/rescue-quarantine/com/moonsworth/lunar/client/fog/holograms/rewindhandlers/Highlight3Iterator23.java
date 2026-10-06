package com.moonsworth.lunar.client.fog.holograms.rewindhandlers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge17Extension;
import com.moonsworth.lunar.bridge.Bridge20;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension5;
import com.moonsworth.lunar.bridge.Bridge_38;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.Client;
import com.moonsworth.lunar.client.fog.holograms.FogIterator_3;
import com.moonsworth.lunar.client.fog.holograms.FogLoader24;
import com.moonsworth.lunar.client.fog.holograms.Holograms2_2;
import com.moonsworth.lunar.client.fog.holograms.Holograms3_4;
import com.moonsworth.lunar.client.fog.holograms.HologramsType;
import com.moonsworth.lunar.client.fog.holograms.Holograms_3;
import com.moonsworth.lunar.client.fog.holograms.Holograms_6;
import com.moonsworth.lunar.client.fog.holograms.gui.IterableExtension3;
import com.moonsworth.lunar.client.fog.holograms.highlight.Highlight_2;
import com.moonsworth.lunar.client.fog.holograms.nameplate.Nameplate_2;
import com.moonsworth.lunar.client.fog.rewindhandlers.RewindhandlersType2;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl12;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl15;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl20;
import com.moonsworth.lunar.client.highlight.fishing.HighlightBase.Data9;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl14.Data2;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl16.Type2;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightImpl7;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightBase2.Data8;
import com.moonsworth.lunar.client.highlight.mixin.fishing.mixin.HighlightImpl6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@Annotation2(min = 8)
public class Highlight3Iterator23 extends com.moonsworth.lunar.client.fog.holograms.Highlight3Iterator23<Horsestats20Extension, Void> {
   private final Rewindhandlers field9 = new Rewindhandlers();
   private int field10;

   public Highlight3Iterator23(FogIterator_3 var1) {
      super(var1);
      this.handle(HighlightImpl15.class, var1x -> this.method12(var1x.method1()));
      this.handle(Data9.class, var1x -> this.method12(var1x.IIHCRIOCIOCROOIRCHHORRORIOOCRC()));
      this.handle(HighlightImpl12.class, var1x -> this.method12(var1x.method1()));
      this.handle(HighlightImpl20.class, var1x -> {
         if (var1x.field1.lunar$supportsTurbo()) {
            this.field9.method3(var1x.field1, 0);
         }
      });
      this.handle(Data8.class, var1x -> this.method13(var1x.IIOHICICIRRRHOCCIOORRHHHIHHICR()));
      this.handle(HighlightImpl6.class, var1x -> {
         if (var1x.method2()) {
            int var2x = var1x.getId();

            for (BridgeExtension var4 : ThreadModuleDump63.method3().bridge$getWorld().bridge$getEntities()) {
               if (var4.bridge$isTurbo() && var4 instanceof BridgeExtension5 var5) {
                  var5.bridge$getFramedMapId().ifPresent(var3 -> {
                     if (var2x == var3) {
                        this.field9.method3(var4, 0);
                        this.method13(var4.CCHCORHCRHOCHHHCRHRCIHHIROHRCO(), false);
                     }
                  });
               }
            }
         }
      });
      this.handle(Data2.class, var1x -> {
         if (var1x.method1() == ItemcounterType2.SPECTATOR) {
            this.clear();
         }
      });
      this.handle(HighlightImpl7.class, var1x -> {
         Horsestats20Extension var2x = var1x.method1();
         if (this.OICORIRRCCHHRHRICICOOHHRRHOORR.containsKey(var2x)) {
            this.method13(var2x, false);
         }
      });
      FogLoader24 var2 = Client.method109().method41().method7();
      this.field10 = var2.method18().get();
   }

   @Override
   public HologramsType method1() {
      return HologramsType.ENTITIES;
   }

   @Override
   public Nameplate_2<Horsestats20Extension> method9() {
      return Nameplate_2.field1;
   }

   @Override
   public Holograms2_2<BridgeExtension> method2() {
      return Holograms3_4.field1;
   }

   @Override
   public void clear() {
      super.clear();
      this.field9.clear();
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         for (BridgeExtension var3 : var1.bridge$getEntities()) {
            if (var3.bridge$isTurbo()) {
               var3.bridge$setTurbo(false);
            }
         }
      }
   }

   @Override
   public void method3() {
      this.clear();
   }

   @Override
   public void method4(List<String> var1) {
      var1.add("[LC Turbo Entities] Affected Entities: " + this.CCHHRRHCHIOOHCROCICHHIHCCORHCR);
      var1.add("[LC Turbo Entities] Affected Sections: " + this.RCIOCOCRCCCOORRHIHCRIOIHRRIORR());
      var1.add("[LC Turbo Entities] Render Types: " + this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.size());
   }

   @Override
   public List<Bridge_38> method5(Predicate<Bridge_38> var1) {
      ArrayList var2 = new ArrayList();
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 != null) {
         for (BridgeExtension var5 : var3.bridge$entitiesForRendering()) {
            if (var1.test(var5)) {
               var2.add(var5);
            }
         }
      }

      return var2;
   }

   public Type2 method12() {
      return this.HCHHIRIHRORHHOHOIOIIIRRIHIICIC.method28();
   }

   protected boolean method8(@Nullable Void var1) {
      Type2 var2 = this.HCHHIRIHRORHHOHOIOIIIRRIHIICIC.method28();
      if (var2 == Type2.WATER || FogIterator_3.method22(var2) && !Holograms_6.method7(Bridge.method42())) {
         for (int var3 = 0; var3 < this.field10 && !this.CICICHIHORCIRCRRCCIOIIIHOHCCIR.isEmpty(); var3++) {
            this.ICRHORIIHOHROHOHOCOOHOOCOORRHO(var1, (Horsestats20Extension)this.CICICHIHORCIRCRRCCIOIIIHOHCCIR.removeFirst());
         }

         return false;
      } else {
         return true;
      }
   }

   protected void method9(@Nullable Void var1, Horsestats20Extension var2, Horsestats20 var3, Map<Bridge20, Bridge4Extension> var4, List<Object> var5) {
      Bridge2_43 var6 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
      List var7 = ThreadModuleDump63.method8()
         .bridge$getEntities(
            var2.method25(),
            var2x -> var2x.lunar$supportsTurbo() && this.field9.method4(this, var2x) && Horsestats20Extension.method9(var2x.bridge$getBlockPos(), var2)
         );
      if (var7.isEmpty()) {
         this.field9.method7(var2, false);
      } else {
         Highlight_2 var8 = this.HCHHIRIHRORHHOHOIOIIIRRIHIICIC.method23();

         for (BridgeExtension var10 : new ArrayList(var7)) {
            var8.method5();
            this.method10(var8, var3, var6, var10, var4);
            var8.method6();
         }

         var5.addAll(var7);
      }
   }

   private void method10(Highlight_2 var1, Horsestats20 var2, Bridge2_43 var3, BridgeExtension var4, Map<Bridge20, Bridge4Extension> var5) {
      Bridge5_16 var6 = Bridge.method8().method61();
      var6.bridge$pushPose();
      var6.bridge$translate(var4.bridge$getPosX() - var2.bridge$getX(), var4.bridge$getPosY() - var2.bridge$getY(), var4.bridge$getPosZ() - var2.bridge$getZ());
      Bridge17Extension var7 = Bridge.method8().method76(var2x -> var1.method7(var5, var2x));
      var3.bridge$render(
         var4, 0.0, 0.0, 0.0, (float)var4.bridge$getRotationYaw(), 1.0F, var6, var7, var3.bridge$getRenderer(var4).bridge$getPackedLightCoords(var4, 1.0F)
      );
      if (ThreadModuleDump63.MC_VERSION >= 39) {
         try {
            ((AutoCloseable)var7).close();
         } catch (Exception var9) {
         }
      }

      var6.bridge$popPose();
   }

   protected void method11(Horsestats20Extension var1, boolean var2) {
      this.field9.method7(var1, true);
   }

   private void method12(BridgeExtension var1) {
      if (var1.lunar$supportsTurbo()) {
         this.field9.method2(var1);
         if (!var1.bridge$isAlive() && var1.bridge$isTurbo()) {
            this.field9.method6(this, var1.CCHCORHCRHOCHHHCRHRCIHHIROHRCO(), var1, false, true);
         }
      }
   }

   private void method13(Itemcounter2 var1) {
      this.field9.method1(var1);
      Iterator var2 = this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.entrySet().iterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         Map var4 = (Map)var3.getValue();
         Iterator var5 = var4.entrySet().iterator();

         while (var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            Horsestats20Extension var7 = (Horsestats20Extension)var6.getKey();
            if (var1.bridge$getX() == var7.bridge$getX() && var1.bridge$getZ() == var7.bridge$getZ()) {
               ((Bridge2_5)var6.getValue()).bridge$close();
               var5.remove();
            }
         }

         if (var4.isEmpty()) {
            var2.remove();
         }
      }

      this.CICICHIHORCIRCRRCCIOIIIHOHCCIR.removeIf(var1x -> var1.bridge$getX() == var1x.bridge$getX() && var1.bridge$getZ() == var1x.bridge$getZ());

      for (int var8 = var1.bridge$getMinSection(); var8 < var1.bridge$getMaxSection(); var8++) {
         Horsestats20Extension var9 = Horsestats20Extension.method2(var1.bridge$getX(), var8, var1.bridge$getZ());
         ((IterableExtension3)this.OHCHOCHRRHIHOHCRHCHORIOHHRRRIH).IICIROOOIIIHCHICHIRRRRIRHHHHOR(var9);
         Holograms_3 var10 = (Holograms_3)this.OICORIRRCCHHRHRICICOOHHRRHOORR.remove(var9);
         if (var10 != null) {
            this.CCHHRRHCHIOOHCROCICHHIHCCORHCR = this.CCHHRRHCHIOOHCROCICHHIHCCORHCR - var10.getCount();
         }
      }
   }

   public static boolean method14() {
      return RewindhandlersType2.TURBO_ENTITIES.isEnabled();
   }

   @Generated
   public Rewindhandlers method15() {
      return this.field9;
   }

   @Generated
   public int method17() {
      return this.field10;
   }

   @Generated
   public void method17(int var1) {
      this.field10 = var1;
   }
}

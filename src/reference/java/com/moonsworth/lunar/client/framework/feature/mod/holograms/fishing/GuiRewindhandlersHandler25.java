package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.SlotRenderEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent.ScreenInitPostEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.SlotUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class GuiRewindhandlersHandler25 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler23 field7 = (GuiRewindhandlersHandler23)this.method3(GuiRewindhandlersHandler23.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22 field8 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22.class
   );
   private final Set<Fishing3> field9 = new HashSet<>();
   private final Pattern field10 = Pattern.compile("^(\\w+)( Gemstone)? Collector(: ([\\d.]+%|DONE))?$");
   private final Pattern field11 = Pattern.compile("^(\\w+) Crystal Hunter(: ([\\d.]+%|DONE))?$");
   private final Pattern field12 = Pattern.compile("^(.+) Slayer(: ([\\d.]+%|DONE))?$");
   private final Set<String> field13 = Set.of("Goblin Raid", "Raffle", "Lucky Raffle");
   private final Set<String> field14 = Set.of("Corpse Looter", "Mineshaft Explorer");
   private Fishing3 field15;
   private long field16;
   private Fishing3 field17;
   private long field18;
   boolean field19;

   public GuiRewindhandlersHandler25() {
      this.handle(ScreenOpenEvent.class, this::method1);
      this.handle(SlotRenderEvent.class, this::method5);
      this.handle(SlotUpdateEvent.class, this::method6);
      this.handle(ScreenInitPostEvent.class, this::method2);
      this.handle(EventWorldChanged.class, this::method3);
      this.handle(TabListUpdateEvent.class, this::method4);
   }

   private void method1(ScreenOpenEvent var1) {
      this.field19 = false;
   }

   private void method2(ScreenInitPostEvent var1) {
      this.field19 = false;
   }

   private void method3(EventWorldChanged var1) {
      for (Fishing3 var4 : new HashSet<>(this.field9)) {
         this.field9.remove(var4);
         ClientEventBus.method29().method12(HighlightBase2.Data3.class, () -> new HighlightBase2.Data3(var4));
      }
   }

   private void method4(TabListUpdateEvent var1) {
      if (Click3.getIsland().containsPowderSources()) {
         Lotusfish3 var2 = this.field7.method6().get("commissions");
         if (var2 != null) {
            HashSet var3 = new HashSet();

            for (String var5 : var2.method3()) {
               var5 = var5.trim();
               Fishing3.Data var6 = Fishing3.method1();
               var6.method2(var5.endsWith(": DONE"));

               for (String var8 : this.field13) {
                  if (var5.startsWith(var8)) {
                     var6.method1(Fishing3.Type.PARTICIPATE).method6(var8);
                     break;
                  }
               }

               for (String var24 : this.field14) {
                  if (var5.startsWith(var24)) {
                     var6.method1(Fishing3.Type.MISC).method9(var24);
                     break;
                  }
               }

               Matcher var18 = this.field10.matcher(var5);
               if (var18.find()) {
                  String var25 = var18.group(1);
                  var6.method1(Fishing3.Type.COLLECT).method3(FishingType.fromId(var25)).method4(var25);
               }

               var18 = this.field11.matcher(var5);
               if (var18.find()) {
                  String var26 = var18.group(1);
                  var6.method1(Fishing3.Type.CRYSTAL).method8(var26);
               }

               var18 = this.field12.matcher(var5);
               if (var18.find()) {
                  String var27 = var18.group(1);
                  var6.method1(Fishing3.Type.SLAY).method7(var27);
               }

               Fishing3 var28 = var6.method10();
               if (var28.method2() != null) {
                  var3.add(var28);
               }
            }

            HashSet var9 = new HashSet();

            for (Fishing3 var13 : this.field9) {
               if (!var3.contains(var13) && (!var13.equals(this.field17) || ThreadModuleDump63.method3().bridge$getSystemTime() - this.field18 >= 5000L)) {
                  var9.add(var13);
               }
            }

            HashSet var12 = new HashSet();

            for (Fishing3 var21 : var3) {
               if (!this.field9.contains(var21) && (!var21.equals(this.field15) || ThreadModuleDump63.method3().bridge$getSystemTime() - this.field16 >= 5000L)
                  )
                {
                  var12.add(var21);
               }
            }

            for (Fishing3 var22 : var9) {
               this.field9.remove(var22);
               ClientEventBus.method29().method12(HighlightBase2.Data3.class, () -> new HighlightBase2.Data3(var22));
            }

            for (Fishing3 var23 : var12) {
               this.field9.add(var23);
               ClientEventBus.method29().method12(HighlightBase2.Data2.class, () -> new HighlightBase2.Data2(var23));
            }
         }
      }
   }

   private void method5(SlotRenderEvent var1) {
      Bridge3_18 var2 = var1.method5();
      if (var2 != null) {
         int var3 = var2.bridge$getIndex();
         if (var3 >= 0 && var3 <= 36) {
            ItemStackBridge var4 = var1.method5().bridge$getItemStack();
            if (var4 != null && !var4.bridge$isEmpty()) {
               if (var4.bridge$getRawDisplayName().startsWith("Filter")) {
                  this.field19 = true;
               } else if (var4.bridge$getRawDisplayName().startsWith("Commission #")) {
                  Fishing3 var5 = this.method7(var4);
                  if (var5 != null && var5.method3()) {
                     this.field9.remove(var5);
                     ClientEventBus.method29().method12(HighlightBase2.Data.class, () -> new HighlightBase2.Data(var5));
                     this.field15 = var5;
                     this.field16 = ThreadModuleDump63.method3().bridge$getSystemTime();
                  }
               }
            }
         }
      }
   }

   private void method6(SlotUpdateEvent var1) {
      if (this.field8.method7() == HighlightType.COMMISSIONS) {
         if (!this.field19) {
            int var2 = var1.getSlot();
            if (var2 >= 11 && var2 <= 15) {
               ItemStackBridge var3 = var1.method3();
               if (var3 != null) {
                  if (var3.bridge$getRawDisplayName().startsWith("Commission #")) {
                     Fishing3 var4 = this.method7(var3);
                     if (var4 != null) {
                        if (!var4.equals(this.field15) || ThreadModuleDump63.method3().bridge$getSystemTime() - this.field16 >= 5000L) {
                           if (!this.field9.contains(var4)) {
                              this.field9.add(var4);
                              this.field17 = var4;
                              this.field18 = ThreadModuleDump63.method3().bridge$getSystemTime();
                              ClientEventBus.method29().method12(HighlightBase2.Data2.class, () -> new HighlightBase2.Data2(var4));
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private Fishing3 method7(ItemStackBridge var1) {
      Fishing3.Data var2 = Fishing3.method1();
      boolean var3 = false;
      boolean var4 = false;

      label57:
      for (String var6 : Gui3.method15(var1)) {
         if (!var4 && var6.equals("COMPLETED")) {
            var2.method2(true);
            var4 = true;
         } else if (!var3) {
            for (String var8 : this.field13) {
               if (var8.equals(var6)) {
                  var2.method1(Fishing3.Type.PARTICIPATE).method6(var8);
                  var3 = true;
                  continue label57;
               }
            }

            for (String var13 : this.field14) {
               if (var13.equals(var6)) {
                  var2.method1(Fishing3.Type.MISC).method9(var13);
                  var3 = true;
                  continue label57;
               }
            }

            Matcher var10 = this.field10.matcher(var6);
            if (var10.find()) {
               String var14 = var10.group(1);
               var2.method1(Fishing3.Type.COLLECT).method3(FishingType.fromId(var14)).method4(var14);
               var3 = true;
            } else {
               var10 = this.field11.matcher(var6);
               if (var10.find()) {
                  String var15 = var10.group(1);
                  var2.method1(Fishing3.Type.CRYSTAL).method8(var15);
                  var3 = true;
               } else {
                  var10 = this.field12.matcher(var6);
                  if (var10.find()) {
                     String var16 = var10.group(1);
                     var2.method1(Fishing3.Type.SLAY).method7(var16);
                     var3 = true;
                  }
               }
            }
         }
      }

      return var3 ? var2.method10() : null;
   }

   protected void onEnable() {
      this.method4(new TabListUpdateEvent());
   }

   @Generated
   public Set<Fishing3> method8() {
      return this.field9;
   }
}

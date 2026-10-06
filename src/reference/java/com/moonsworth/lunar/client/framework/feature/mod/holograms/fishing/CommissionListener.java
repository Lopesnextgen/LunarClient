package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommission;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.CommissionEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class CommissionListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final SkyblockScoreboardParser field7 = (SkyblockScoreboardParser)this.method3(SkyblockScoreboardParser.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener field8 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener.class
   );
   private final Set<SkyBlockCommission> field9 = new HashSet<>();
   private final Pattern field10 = Pattern.compile("^(\\w+)( Gemstone)? Collector(: ([\\d.]+%|DONE))?$");
   private final Pattern field11 = Pattern.compile("^(\\w+) Crystal Hunter(: ([\\d.]+%|DONE))?$");
   private final Pattern field12 = Pattern.compile("^(.+) Slayer(: ([\\d.]+%|DONE))?$");
   private final Set<String> field13 = Set.of("Goblin Raid", "Raffle", "Lucky Raffle");
   private final Set<String> field14 = Set.of("Corpse Looter", "Mineshaft Explorer");
   private SkyBlockCommission field15;
   private long field16;
   private SkyBlockCommission field17;
   private long field18;
   boolean field19;

   public CommissionListener() {
      this.handle(EventScreenOpen.class, this::method1);
      this.handle(EventRenderSlot.class, this::method5);
      this.handle(EventSlotUpdate.class, this::method6);
      this.handle(EventScreenInitPost.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
      this.handle(EventTabListUpdate.class, this::method4);
   }

   private void method1(EventScreenOpen highlightimpl91) {
      this.field19 = false;
   }

   private void method2(EventScreenInitPost data51) {
      this.field19 = false;
   }

   private void method3(EventWorldChange data31) {
      for (SkyBlockCommission fishing34 : new HashSet<>(this.field9)) {
         this.field9.remove(fishing34);
         LunarEventBus.method29().method12(CommissionEvent.CommissionRemoveEvent.class, () -> new CommissionEvent.CommissionRemoveEvent(fishing34));
      }
   }

   private void method4(EventTabListUpdate highlightimpl31) {
      if (IslandUtils.getIsland().containsPowderSources()) {
         ScoreboardSection lotusfish32 = this.field7.method6().get("commissions");
         if (lotusfish32 != null) {
            HashSet set3 = new HashSet();

            for (String text5 : lotusfish32.method3()) {
               text5 = text5.trim();
               SkyBlockCommission.Data data6 = SkyBlockCommission.method1();
               data6.method2(text5.endsWith(": DONE"));

               for (String text8 : this.field13) {
                  if (text5.startsWith(text8)) {
                     data6.method1(SkyBlockCommission.Type.PARTICIPATE).method6(text8);
                     break;
                  }
               }

               for (String text24 : this.field14) {
                  if (text5.startsWith(text24)) {
                     data6.method1(SkyBlockCommission.Type.MISC).method9(text24);
                     break;
                  }
               }

               Matcher matcher18 = this.field10.matcher(text5);
               if (matcher18.find()) {
                  String text25 = matcher18.group(1);
                  data6.method1(SkyBlockCommission.Type.COLLECT).method3(FishingType.fromId(text25)).method4(text25);
               }

               matcher18 = this.field11.matcher(text5);
               if (matcher18.find()) {
                  String text26 = matcher18.group(1);
                  data6.method1(SkyBlockCommission.Type.CRYSTAL).method8(text26);
               }

               matcher18 = this.field12.matcher(text5);
               if (matcher18.find()) {
                  String text27 = matcher18.group(1);
                  data6.method1(SkyBlockCommission.Type.SLAY).method7(text27);
               }

               SkyBlockCommission fishing328 = data6.method10();
               if (fishing328.method2() != null) {
                  set3.add(fishing328);
               }
            }

            HashSet set9 = new HashSet();

            for (SkyBlockCommission fishing313 : this.field9) {
               if (!set3.contains(fishing313) && (!fishing313.equals(this.field17) || Ref.method3().bridge$getSystemTime() - this.field18 >= 5000L)) {
                  set9.add(fishing313);
               }
            }

            HashSet set12 = new HashSet();

            for (SkyBlockCommission fishing321 : set3) {
               if (!this.field9.contains(fishing321) && (!fishing321.equals(this.field15) || Ref.method3().bridge$getSystemTime() - this.field16 >= 5000L)
                  )
                {
                  set12.add(fishing321);
               }
            }

            for (SkyBlockCommission fishing322 : set9) {
               this.field9.remove(fishing322);
               LunarEventBus.method29().method12(CommissionEvent.CommissionRemoveEvent.class, () -> new CommissionEvent.CommissionRemoveEvent(fishing322));
            }

            for (SkyBlockCommission fishing323 : set12) {
               this.field9.add(fishing323);
               LunarEventBus.method29().method12(CommissionEvent.CommissionStartEvent.class, () -> new CommissionEvent.CommissionStartEvent(fishing323));
            }
         }
      }
   }

   private void method5(EventRenderSlot highlightimpl51) {
      SlotBridge bridge3_182 = highlightimpl51.method5();
      if (bridge3_182 != null) {
         int number3 = bridge3_182.bridge$getIndex();
         if (number3 >= 0 && number3 <= 36) {
            ItemStackBridge bridgeextension_44 = highlightimpl51.method5().bridge$getItemStack();
            if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty()) {
               if (bridgeextension_44.bridge$getRawDisplayName().startsWith("Filter")) {
                  this.field19 = true;
               } else if (bridgeextension_44.bridge$getRawDisplayName().startsWith("Commission #")) {
                  SkyBlockCommission fishing35 = this.method7(bridgeextension_44);
                  if (fishing35 != null && fishing35.method3()) {
                     this.field9.remove(fishing35);
                     LunarEventBus.method29().method12(CommissionEvent.Data.class, () -> new CommissionEvent.Data(fishing35));
                     this.field15 = fishing35;
                     this.field16 = Ref.method3().bridge$getSystemTime();
                  }
               }
            }
         }
      }
   }

   private void method6(EventSlotUpdate highlightimpl1) {
      if (this.field8.method7() == SkyblockMenuType.COMMISSIONS) {
         if (!this.field19) {
            int number2 = highlightimpl1.getSlot();
            if (number2 >= 11 && number2 <= 15) {
               ItemStackBridge bridgeextension_43 = highlightimpl1.method3();
               if (bridgeextension_43 != null) {
                  if (bridgeextension_43.bridge$getRawDisplayName().startsWith("Commission #")) {
                     SkyBlockCommission fishing34 = this.method7(bridgeextension_43);
                     if (fishing34 != null) {
                        if (!fishing34.equals(this.field15) || Ref.method3().bridge$getSystemTime() - this.field16 >= 5000L) {
                           if (!this.field9.contains(fishing34)) {
                              this.field9.add(fishing34);
                              this.field17 = fishing34;
                              this.field18 = Ref.method3().bridge$getSystemTime();
                              LunarEventBus.method29().method12(CommissionEvent.CommissionStartEvent.class, () -> new CommissionEvent.CommissionStartEvent(fishing34));
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private SkyBlockCommission method7(ItemStackBridge bridgeextension_41) {
      SkyBlockCommission.Data data2 = SkyBlockCommission.method1();
      boolean flag3 = false;
      boolean flag4 = false;

      label57:
      for (String text6 : SkyblockItemUtil.method15(bridgeextension_41)) {
         if (!flag4 && text6.equals("COMPLETED")) {
            data2.method2(true);
            flag4 = true;
         } else if (!flag3) {
            for (String text8 : this.field13) {
               if (text8.equals(text6)) {
                  data2.method1(SkyBlockCommission.Type.PARTICIPATE).method6(text8);
                  flag3 = true;
                  continue label57;
               }
            }

            for (String text13 : this.field14) {
               if (text13.equals(text6)) {
                  data2.method1(SkyBlockCommission.Type.MISC).method9(text13);
                  flag3 = true;
                  continue label57;
               }
            }

            Matcher matcher10 = this.field10.matcher(text6);
            if (matcher10.find()) {
               String text14 = matcher10.group(1);
               data2.method1(SkyBlockCommission.Type.COLLECT).method3(FishingType.fromId(text14)).method4(text14);
               flag3 = true;
            } else {
               matcher10 = this.field11.matcher(text6);
               if (matcher10.find()) {
                  String text15 = matcher10.group(1);
                  data2.method1(SkyBlockCommission.Type.CRYSTAL).method8(text15);
                  flag3 = true;
               } else {
                  matcher10 = this.field12.matcher(text6);
                  if (matcher10.find()) {
                     String text16 = matcher10.group(1);
                     data2.method1(SkyBlockCommission.Type.SLAY).method7(text16);
                     flag3 = true;
                  }
               }
            }
         }
      }

      return flag3 ? data2.method10() : null;
   }

   protected void onEnable() {
      this.method4(new EventTabListUpdate());
   }

   @Generated
   public Set<SkyBlockCommission> method8() {
      return this.field9;
   }
}

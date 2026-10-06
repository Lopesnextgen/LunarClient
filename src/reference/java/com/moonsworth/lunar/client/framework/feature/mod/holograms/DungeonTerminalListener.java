package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonFloorDetectedEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonExitEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.CancellableEvent;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class DungeonTerminalListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final DungeonFloorListener field7 = (DungeonFloorListener)this.method3(DungeonFloorListener.class);
   private final DungeonScoreListener field8 = (DungeonScoreListener)this.method3(DungeonScoreListener.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener.class
   );
   public static final Pattern field10 = Pattern.compile(
      "^(?<username>\\w+) (?<verb>activated|completed) a (?<subject>terminal|lever|device)! \\((?<progress>\\d)/(?<total>\\d)\\)"
   );
   public static final String field11 = "The gate has been destroyed!";
   public static final int field12 = 4;
   private static final String field13 = "[BOSS] Goldor: Who dares trespass into my domain?";
   private static final String field14 = "The Core entrance is opening!";
   private SkyblockMenuType field15;
   private long field16;
   private int field17;
   private int field18;
   private boolean field19;
   private int field20;
   private int field21;
   private int field22 = -1;
   private int field23 = -1;

   public DungeonTerminalListener() {
      this.handle(TypedChatMessage.class, this::method5);
      this.handle(DungeonExitEvent.class, arg1 -> this.reset());
      this.handle(DungeonFloorDetectedEvent.class, arg1 -> this.reset());
      this.handle(EventScreenInitPost.class, this::method7);
      this.handle(EventScreenOpen.class, this::method8);
   }

   public boolean method5() {
      return this.field22 == -1 ? false : this.field22 > this.field21 + this.field20;
   }

   private void reset() {
      this.field15 = null;
      this.field16 = -1L;
      this.field17 = 0;
      this.method7();
   }

   private void method6() {
      this.field17++;
      long number1 = Ref.method3().bridge$getSystemTime();
      if (this.field17 == 1) {
         this.field16 = number1;
      }

      this.method7();
      LunarEventBus.method29().method12(TerminalEvents.TerminalPhaseEvent.class, () -> new TerminalEvents.TerminalPhaseEvent(number1, this.field17));
   }

   private void method7() {
      this.field18 = this.method4(this.field17);
      this.field19 = false;
      this.field20 = 0;
      this.field21 = 0;
      this.field23 = -1;
      this.field22 = -1;
   }

   private int method4(int number1) {
      return switch (number1) {
         case 1, 3, 4 -> 7;
         case 2 -> 8;
         default -> -1;
      };
   }

   private void method5(TypedChatMessage data1) {
      if (this.field7.method6().getNumber() == 7 && this.field8.method10()) {
         String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if (text2.equals("[BOSS] Goldor: Who dares trespass into my domain?") && this.field17 == 0) {
            this.method6();
         } else {
            if (text2.equals("The Core entrance is opening!")) {
               this.reset();
            }

            if (text2.equals("The gate has been destroyed!")) {
               this.field19 = true;
               CancellableEvent highlightimpl9 = (CancellableEvent)LunarEventBus.method29()
                  .method12(TerminalEvents.TerminalActivateEvent.class, () -> new TerminalEvents.TerminalActivateEvent(TerminalEvents.TerminalActivateEvent.Type.GATE, "", false));
               if (highlightimpl9 != null && highlightimpl9.isCancelled()) {
                  data1.cancel();
               }

               this.method8();
            } else {
               Matcher matcher3 = field10.matcher(text2);
               if (matcher3.matches()) {
                  int number4 = this.field22;
                  this.field22 = Integer.parseInt(matcher3.group("progress"));
                  this.field23 = Integer.parseInt(matcher3.group("total"));
                  String text6 = matcher3.group("subject");
                  TerminalEvents.TerminalActivateEvent.Type type5;
                  if (text6.equals("device")) {
                     type5 = TerminalEvents.TerminalActivateEvent.Type.DEVICE;
                  } else if (text6.equals("lever")) {
                     type5 = TerminalEvents.TerminalActivateEvent.Type.LEVER;
                     this.field20++;
                  } else {
                     type5 = TerminalEvents.TerminalActivateEvent.Type.TERMINAL;
                     this.field21++;
                  }

                  TerminalEvents.TerminalActivateEvent.Type type7 = type5;
                  CancellableEvent highlightimpl8 = (CancellableEvent)LunarEventBus.method29()
                     .method12(TerminalEvents.TerminalActivateEvent.class, () -> new TerminalEvents.TerminalActivateEvent(type7, matcher3.group("username"), number4 == this.field22));
                  if (highlightimpl8 != null && highlightimpl8.isCancelled()) {
                     data1.cancel();
                  }

                  this.method8();
               }
            }
         }
      }
   }

   private void method8() {
      if (this.field22 != -1 && this.field23 != -1) {
         if (this.field22 == this.field23 && (this.field17 == 4 || this.field19)) {
            this.method6();
         }
      }
   }

   private void method7(EventScreenInitPost data51) {
      SkyblockMenuType highlighttype2 = this.field9.method7();
      if (highlighttype2 != null && highlighttype2.isTerminalGui()) {
         if (highlighttype2 != this.field15) {
            LunarEventBus.method29().method12(TerminalEvents.Data.class, () -> new TerminalEvents.Data(highlighttype2));
         }

         this.field15 = highlighttype2;
      }
   }

   private void method8(EventScreenOpen highlightimpl91) {
      if (highlightimpl91.method2() == null) {
         this.field15 = null;
      }
   }

   @Generated
   public DungeonFloorListener method9() {
      return this.field7;
   }

   @Generated
   public DungeonScoreListener method10() {
      return this.field8;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener method11() {
      return this.field9;
   }

   @Generated
   public SkyblockMenuType method12() {
      return this.field15;
   }

   @Generated
   public long method13() {
      return this.field16;
   }

   @Generated
   public int method15() {
      return this.field17;
   }

   @Generated
   public int method16() {
      return this.field18;
   }

   @Generated
   public boolean method17() {
      return this.field19;
   }

   @Generated
   public int method18() {
      return this.field20;
   }

   @Generated
   public int method21() {
      return this.field21;
   }

   @Generated
   public int method22() {
      return this.field22;
   }

   @Generated
   public int method23() {
      return this.field23;
   }
}

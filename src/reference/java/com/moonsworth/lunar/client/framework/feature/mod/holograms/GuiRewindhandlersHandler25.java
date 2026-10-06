package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data9;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.highlight.HighlightImpl;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent.ScreenInitPostEvent;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class GuiRewindhandlersHandler25 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler22_2 field7 = (GuiRewindhandlersHandler22_2)this.method3(GuiRewindhandlersHandler22_2.class);
   private final GuiRewindhandlersHandler23_2 field8 = (GuiRewindhandlersHandler23_2)this.method3(GuiRewindhandlersHandler23_2.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22 field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22.class
   );
   public static final Pattern field10 = Pattern.compile(
      "^(?<username>\\w+) (?<verb>activated|completed) a (?<subject>terminal|lever|device)! \\((?<progress>\\d)/(?<total>\\d)\\)"
   );
   public static final String field11 = "The gate has been destroyed!";
   public static final int field12 = 4;
   private static final String field13 = "[BOSS] Goldor: Who dares trespass into my domain?";
   private static final String field14 = "The Core entrance is opening!";
   private HighlightType field15;
   private long field16;
   private int field17;
   private int field18;
   private boolean field19;
   private int field20;
   private int field21;
   private int field22 = -1;
   private int field23 = -1;

   public GuiRewindhandlersHandler25() {
      this.handle(Data.class, this::method5);
      this.handle(Rewindhandlers$Data9.class, var1 -> this.reset());
      this.handle(Rewindhandlers$Data10.class, var1 -> this.reset());
      this.handle(ScreenInitPostEvent.class, this::method7);
      this.handle(ScreenOpenEvent.class, this::method8);
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
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (this.field17 == 1) {
         this.field16 = var1;
      }

      this.method7();
      ClientEventBus.method29().method12(Rewindhandlers2.Data2.class, () -> new Rewindhandlers2.Data2(var1, this.field17));
   }

   private void method7() {
      this.field18 = this.method4(this.field17);
      this.field19 = false;
      this.field20 = 0;
      this.field21 = 0;
      this.field23 = -1;
      this.field22 = -1;
   }

   private int method4(int var1) {
      return switch (var1) {
         case 1, 3, 4 -> 7;
         case 2 -> 8;
         default -> -1;
      };
   }

   private void method5(Data var1) {
      if (this.field7.method6().getNumber() == 7 && this.field8.method10()) {
         String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if (var2.equals("[BOSS] Goldor: Who dares trespass into my domain?") && this.field17 == 0) {
            this.method6();
         } else {
            if (var2.equals("The Core entrance is opening!")) {
               this.reset();
            }

            if (var2.equals("The gate has been destroyed!")) {
               this.field19 = true;
               HighlightImpl var9 = (HighlightImpl)ClientEventBus.method29()
                  .method12(Rewindhandlers2.Data3.class, () -> new Rewindhandlers2.Data3(Rewindhandlers2.Data3.Type.GATE, "", false));
               if (var9 != null && var9.isCancelled()) {
                  var1.cancel();
               }

               this.method8();
            } else {
               Matcher var3 = field10.matcher(var2);
               if (var3.matches()) {
                  int var4 = this.field22;
                  this.field22 = Integer.parseInt(var3.group("progress"));
                  this.field23 = Integer.parseInt(var3.group("total"));
                  String var6 = var3.group("subject");
                  Rewindhandlers2.Data3.Type var5;
                  if (var6.equals("device")) {
                     var5 = Rewindhandlers2.Data3.Type.DEVICE;
                  } else if (var6.equals("lever")) {
                     var5 = Rewindhandlers2.Data3.Type.LEVER;
                     this.field20++;
                  } else {
                     var5 = Rewindhandlers2.Data3.Type.TERMINAL;
                     this.field21++;
                  }

                  Rewindhandlers2.Data3.Type var7 = var5;
                  HighlightImpl var8 = (HighlightImpl)ClientEventBus.method29()
                     .method12(Rewindhandlers2.Data3.class, () -> new Rewindhandlers2.Data3(var7, var3.group("username"), var4 == this.field22));
                  if (var8 != null && var8.isCancelled()) {
                     var1.cancel();
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

   private void method7(ScreenInitPostEvent var1) {
      HighlightType var2 = this.field9.method7();
      if (var2 != null && var2.isTerminalGui()) {
         if (var2 != this.field15) {
            ClientEventBus.method29().method12(Rewindhandlers2.Data.class, () -> new Rewindhandlers2.Data(var2));
         }

         this.field15 = var2;
      }
   }

   private void method8(ScreenOpenEvent var1) {
      if (var1.method2() == null) {
         this.field15 = null;
      }
   }

   @Generated
   public GuiRewindhandlersHandler22_2 method9() {
      return this.field7;
   }

   @Generated
   public GuiRewindhandlersHandler23_2 method10() {
      return this.field8;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22 method11() {
      return this.field9;
   }

   @Generated
   public HighlightType method12() {
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

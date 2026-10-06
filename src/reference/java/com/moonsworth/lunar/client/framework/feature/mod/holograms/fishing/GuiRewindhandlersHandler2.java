package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EntitySpawnEvent;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.TextComponent;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28 field7 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28.class
   );
   private final com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25 field8 = (com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25)this.method3(
      com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25.class
   );
   private final Pattern field9 = Pattern.compile("Spawned by: (.+)");
   private final Pattern field10 = Pattern.compile(
      "^(?<mobTypes>[^ ]+ )?☠ (?<type>Revenant Horror|Atoned Horror|Tarantula Broodfather|Sven Packmaster|Voidgloom Seraph|Bloodfiend|Inferno Demonlord)"
   );
   private String field11 = "";
   private boolean field12 = false;

   public GuiRewindhandlersHandler2() {
      this.handle(Data.class, this::method1);
      this.handle(ScoreboardUpdateEvent.class, this::method2);
      this.handle(EventWorldChanged.class, this::method3);
      this.handle(EntitySpawnEvent.class, this::method4);
   }

   private void method1(Data var1) {
      if (Click3.hasIsland() && this.field12) {
         String var2 = AdventureTextBridge.getTextContent(var1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
         if (var2.equals("SLAYER QUEST COMPLETE!") || var2.equals("NICE! SLAYER BOSS SLAIN!")) {
            ClientEventBus.method29()
               .method12(HighlightBase3.Data.class, () -> new HighlightBase3.Data(this.field11, ThreadModuleDump63.method3().bridge$getSystemTime()));
            this.field12 = false;
         } else if (var2.equals("SLAYER QUEST FAILED!") || var2.equals("Your Slayer Quest has been cancelled!")) {
            ClientEventBus.method29()
               .method12(HighlightBase3.Data2.class, () -> new HighlightBase3.Data2(this.field11, ThreadModuleDump63.method3().bridge$getSystemTime()));
            this.field12 = false;
         }
      }
   }

   private void method2(ScoreboardUpdateEvent var1) {
      if (Click3.hasIsland()) {
         ImmutableList var2 = this.field7.method6();

         for (int var3 = 0; var3 < var2.size(); var3++) {
            String var4 = (String)var2.get(var3);
            if (var4.contains("Slayer Quest")) {
               if (var3 + 1 >= var2.size()) {
                  return;
               }

               this.field11 = AdventureChatFormatting.getTextWithoutFormattingCodes((String)var2.get(var3 + 1));
            }
         }
      }
   }

   private void method3(EventWorldChanged var1) {
      if (this.field12) {
         ClientEventBus.method29()
            .method12(HighlightBase3.Data2.class, () -> new HighlightBase3.Data2(this.field11, ThreadModuleDump63.method3().bridge$getSystemTime()));
         this.field12 = false;
      }
   }

   private void method4(EntitySpawnEvent var1) {
      if (!this.field12) {
         if (Click3.hasIsland()) {
            if (var1.field1.bridge$getCustomName() instanceof TextComponent var2) {
               Itemcounter6Extension var9 = ThreadModuleDump63.method8();
               if (var9 != null) {
                  List var4 = var9.bridge$getEntities();
                  String var5 = AdventureTextBridge.getTextContent(var2);
                  Matcher var6 = this.field9.matcher(var5);
                  if (var6.find()) {
                     String var7 = var6.group(1);
                     String var8 = this.field8.method6();
                     if (var7.equals(var8) || var7.equals(ThreadModuleDump63.method4().method31().getName())) {
                        this.method5(var1.field1, var4, this.field10);
                        return;
                     }
                  }

                  var6 = this.field10.matcher(var5);
                  if (var6.find()) {
                     this.method5(var1.field1, var4, this.field9);
                  }
               }
            }
         }
      }
   }

   private void method5(BridgeExtension var1, List<BridgeExtension> var2, Pattern var3) {
      Iterator var4 = var2.iterator();

      while (true) {
         if (!var4.hasNext()) {
            return;
         }

         BridgeExtension var5 = (BridgeExtension)var4.next();
         if (var5.bridge$getCustomName() instanceof TextComponent var6
            && var5 instanceof ArmorStandBridge
            && !(Math.abs(var5.bridge$getPosX() - var1.bridge$getPosX()) > 0.1)
            && !(Math.abs(var5.bridge$getPosY() - var1.bridge$getPosY()) > 0.5)
            && !(Math.abs(var5.bridge$getPosZ() - var1.bridge$getPosZ()) > 0.1)) {
            String var11 = AdventureTextBridge.getTextContent(var6);
            Matcher var8 = var3.matcher(var11);
            if (var8.find()) {
               if (var3 != this.field9) {
                  break;
               }

               String var9 = var8.group(1);
               String var10 = this.field8.method6();
               if (var9.equals(var10) || var9.equals(ThreadModuleDump63.method4().method31().getName())) {
                  break;
               }
            }
         }
      }

      ClientEventBus.method29()
         .method12(HighlightBase3.Data3.class, () -> new HighlightBase3.Data3(this.field11, ThreadModuleDump63.method3().bridge$getSystemTime()));
      this.field12 = true;
   }
}

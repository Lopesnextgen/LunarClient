package com.moonsworth.lunar.client.guiRewindhandlers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.lighting.Lighting;
import com.moonsworth.lunar.bridge.lighting.Lighting2;
import com.moonsworth.lunar.bridge.lighting.Lighting3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.ScoreboardUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler28 extends DynamicListener {
   private ImmutableList<Component> field7 = ImmutableList.of();
   private ImmutableList<String> field8 = ImmutableList.of();
   private ImmutableList<String> field9 = ImmutableList.of();

   public GuiRewindhandlersHandler28() {
      this.method2(ScoreboardUpdateEvent.class, this::method1, 200);
   }

   private void method1(ScoreboardUpdateEvent var1) {
      ImmutableList var2 = method2(var1.method1());
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      UnmodifiableIterator var5 = var2.iterator();

      while (var5.hasNext()) {
         Component var6 = (Component)var5.next();
         var3.add(AdventureTextBridge.getTextContent(var6));
         var4.add(AdventureTextBridge.asLegacyString(var6));
      }

      this.field7 = var2;
      this.field8 = ImmutableList.copyOf(var3);
      this.field9 = ImmutableList.copyOf(var4);
   }

   private static ImmutableList<Component> method2(Lighting4 var0) {
      ArrayList var1 = new ArrayList();
      Lighting var2 = var0.bridge$getObjectiveInDisplaySlot(1);

      for (Lighting2 var5 : var0.bridge$getSortedScores(var2)) {
         String var6 = var5.bridge$getPlayerName();
         Lighting3 var7 = var0.bridge$getPlayersTeam(var6);
         if (var7 != null) {
            var1.add(var7.bridge$getPrefixAndSuffix());
         }
      }

      Collections.reverse(var1);
      return ImmutableList.copyOf(var1);
   }

   @Override
   protected void onEnable() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         Lighting4 var2 = var1.bridge$getScoreBoard();
         if (var2 != null) {
            ClientEventBus.method29().method12(ScoreboardUpdateEvent.class, () -> new ScoreboardUpdateEvent(var2));
         }
      }
   }

   @Generated
   public ImmutableList<Component> method5() {
      return this.field7;
   }

   @Generated
   public ImmutableList<String> method6() {
      return this.field8;
   }

   @Generated
   public ImmutableList<String> method7() {
      return this.field9;
   }
}

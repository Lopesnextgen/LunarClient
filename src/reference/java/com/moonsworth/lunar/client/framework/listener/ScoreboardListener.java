package com.moonsworth.lunar.client.framework.listener;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class ScoreboardListener extends DynamicListener {
   private ImmutableList<Component> field7 = ImmutableList.of();
   private ImmutableList<String> field8 = ImmutableList.of();
   private ImmutableList<String> field9 = ImmutableList.of();

   public ScoreboardListener() {
      this.method2(EventScoreboardUpdate.class, this::method1, 200);
   }

   private void method1(EventScoreboardUpdate highlightimpl21) {
      ImmutableList list2 = method2(highlightimpl21.method1());
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();
      UnmodifiableIterator unmodifiableiterator5 = list2.iterator();

      while (unmodifiableiterator5.hasNext()) {
         Component component6 = (Component)unmodifiableiterator5.next();
         list3.add(TextBridge.getTextContent(component6));
         list4.add(TextBridge.asLegacyString(component6));
      }

      this.field7 = list2;
      this.field8 = ImmutableList.copyOf(list3);
      this.field9 = ImmutableList.copyOf(list4);
   }

   private static ImmutableList<Component> method2(ScoreboardBridge lighting40) {
      ArrayList list1 = new ArrayList();
      ScoreboardObjectiveBridge lighting2 = lighting40.bridge$getObjectiveInDisplaySlot(1);

      for (ScoreBridge lighting25 : lighting40.bridge$getSortedScores(lighting2)) {
         String text6 = lighting25.bridge$getPlayerName();
         ScorePlayerTeamBridge lighting37 = lighting40.bridge$getPlayersTeam(text6);
         if (lighting37 != null) {
            list1.add(lighting37.bridge$getPrefixAndSuffix());
         }
      }

      Collections.reverse(list1);
      return ImmutableList.copyOf(list1);
   }

   @Override
   protected void onEnable() {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 != null) {
         ScoreboardBridge lighting42 = itemcounter6extension1.bridge$getScoreBoard();
         if (lighting42 != null) {
            LunarEventBus.method29().method12(EventScoreboardUpdate.class, () -> new EventScoreboardUpdate(lighting42));
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

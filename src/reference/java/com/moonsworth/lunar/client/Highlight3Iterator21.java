package com.moonsworth.lunar.client;

import com.lunarclient.apollo.module.combat.CombatModule;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Collection;
import java.util.List;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator21 extends ApolloModuleHandler {
   public Highlight3Iterator21() {
      super("combat", "Combat");
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(CombatModule.DISABLE_MISS_PENALTY, CombatModule.DISABLE_BLOCK_MISS_PENALTY, CombatModule.ALLOW_DIG_AND_USE);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
   }
}

package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.module.combat.CombatModule;
import com.lunarclient.apollo.option.Option;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import java.util.Collection;
import java.util.List;

public class CombatApolloHandler extends ApolloModuleHandler {
   public CombatApolloHandler() {
      super("combat", "Combat");
   }

   public Collection<Option<?, ?, ?>> method1() {
      return List.of(CombatModule.DISABLE_MISS_PENALTY, CombatModule.DISABLE_BLOCK_MISS_PENALTY, CombatModule.ALLOW_DIG_AND_USE);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
   }
}

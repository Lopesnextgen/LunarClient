package com.moonsworth.lunar.client.util.game;

import java.util.function.Predicate;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

@com.moonsworth.lunar.ichor.Annotation2(max = 0)
public class PredicateEntitySelector implements IEntitySelector {
   private final Predicate predicate;

   public PredicateEntitySelector(Predicate var1) {
      this.predicate = var1;
   }

   public boolean isEntityApplicable(Entity var1) {
      return this.predicate.test(var1);
   }
}

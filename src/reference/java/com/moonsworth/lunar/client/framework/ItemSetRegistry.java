package com.moonsworth.lunar.client.framework;

import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.mixin.EntityRenderer6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;

public class ItemSetRegistry extends ItemSetHandler<EntityRenderer6> {
   private final List<EntityRenderer6> field2 = new ArrayList<>();

   @Override
   protected Set<EntityRenderer6> method3() {
      return new HashSet<>();
   }

   public void method2(UUID var1) {
      this.method13().removeIf(var1x -> var1x.getUuid().equals(var1));
      this.method7().removeIf(var1x -> var1x.getUuid().equals(var1));
      this.method6();
   }

   public void method3(EntityRenderer6 var1) {
      this.field2.add(var1);
      this.method6();
   }

   public void method4(EntityRenderer6 var1) {
      this.method13().add(var1);
      this.method6();
   }

   public void clear() {
      this.method13().clear();
      this.field2.clear();
      this.method6();
   }

   public boolean method5(UUID var1) {
      return this.field2.stream().anyMatch(var1x -> var1x.getUuid().equals(var1));
   }

   private void method6() {
      ThreadModuleDump63.method4().method50().method9();
   }

   @Generated
   public List<EntityRenderer6> method7() {
      return this.field2;
   }
}

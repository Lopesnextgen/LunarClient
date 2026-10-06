package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;

public class SingleEntityBatchGroup<ID> extends SortedEntityBatchGroup<ID> {
   private ID field7;

   public SingleEntityBatchGroup(EntityBatchType<ID> var1) {
      super(var1);
   }

   @Override
   public void method1() {
   }

   @Override
   public boolean method1(Vec3Bridge var1, ID var2) {
      if (super.method4(var1, var2)) {
         if (var2 != this.field7) {
            this.field7 = (ID)var2;
            this.method2();
         }

         return true;
      } else {
         return false;
      }
   }
}

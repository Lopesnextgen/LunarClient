package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import java.util.function.Consumer;

public class ConsumerEntityBatchGroup<ID> extends EntityBatchGroup<ID> {
   private final Consumer<ID> field6;
   private ID field7;

   public ConsumerEntityBatchGroup(EntityBatchType<ID> var1, Consumer<ID> var2) {
      super(var1);
      this.field6 = var2;
   }

   @Override
   public boolean method1(Vec3Bridge var1, ID var2) {
      if (super.method1(var1, (ID)var2)) {
         if (var2 != this.field7) {
            this.field7 = (ID)var2;
            this.field6.accept((ID)var2);
         }

         return true;
      } else {
         return false;
      }
   }
}

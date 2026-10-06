package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;

@Annotation2(min = 8)
public class EntityBatchGroup<ID> implements BatchGroup<ID> {
   private final EntityBatchType<ID> field1;
   private final List<ID> field2 = new ArrayList<>();
   private final Vector3d field3 = new Vector3d();
   private Vec3Bridge field4;
   private boolean isDirty = true;
   private boolean field5 = true;

   public EntityBatchGroup(EntityBatchType<ID> var1) {
      this.field1 = var1;
   }

   @NotNull
   @Override
   public Iterator<ID> iterator() {
      if (this.isDirty) {
         this.isDirty = false;
         if (this.method4()) {
            this.field3.set(this.field4.bridge$xCoord(), this.field4.bridge$yCoord(), this.field4.bridge$zCoord());
            Vec3Bridge var1 = ThreadModuleDump63.method7().bridge$getEyePosition();
            this.field1.method3(this.field2, var1);
         }
      }

      return this.field2.iterator();
   }

   @Override
   public boolean method1(Vec3Bridge var1, ID iD) {
      if (var1 == this.field4) {
         return false;
      }

      this.isDirty = true;
      this.field4 = var1;
      return true;
   }

   @Override
   public void method2(ID var1) {
      if (!this.field2.contains(var1)) {
         this.isDirty = true;
         this.field5 = true;
         this.field2.add((ID)var1);
      }
   }

   @Override
   public void method3(ID var1) {
      this.field2.remove(var1);
   }

   @Override
   public void clear() {
      this.field2.clear();
      this.field3.set(0.0, 0.0, 0.0);
      this.field4 = null;
      this.isDirty = true;
      this.field5 = true;
   }

   @Override
   public boolean method4() {
      if (this.field5) {
         this.field5 = false;
         return true;
      } else {
         return this.field1.method2(this.field4, this.field3);
      }
   }
}

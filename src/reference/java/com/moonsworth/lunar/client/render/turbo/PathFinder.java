package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.render.turbo.ScoredPathNode;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public abstract class PathFinder {
   protected com.moonsworth.lunar.client.render.turbo.PathEntity field1;
   protected Int2ObjectMap<com.moonsworth.lunar.client.render.turbo.PathNode> field2 = new Int2ObjectOpenHashMap();
   protected int field3;
   protected int field4;
   protected int field5;
   protected boolean field6;
   protected boolean field7;
   protected com.moonsworth.lunar.client.render.turbo.PathWorldView field8;

   public void method1(Itemcounter6 var1, com.moonsworth.lunar.client.render.turbo.PathEntity var2) {
      this.field8 = new com.moonsworth.lunar.client.render.turbo.PathWorldView(var1, var2.method8());
      this.field1 = var2;
      this.field2.clear();
      this.field3 = (int)Math.floor(var2.bridge$getWidth() + 1.0F);
      this.field4 = (int)Math.floor(var2.bridge$getHeight() + 1.0F);
      this.field5 = this.field3;
   }

   public void done() {
      this.field8 = null;
      this.field1 = null;
   }

   public com.moonsworth.lunar.client.render.turbo.PathNode method2(Vector3iBridge var1) {
      return this.method3(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   public com.moonsworth.lunar.client.render.turbo.PathNode method3(int var1, int var2, int var3) {
      return (com.moonsworth.lunar.client.render.turbo.PathNode)this.field2
         .computeIfAbsent(
            com.moonsworth.lunar.client.render.turbo.PathNode.method1(var1, var2, var3),
            var3x -> new com.moonsworth.lunar.client.render.turbo.PathNode(var1, var2, var3)
         );
   }

   @NotNull
   public ScoredPathNode method4(Vector3iBridge var1) {
      return this.method5(var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ());
   }

   @NotNull
   public abstract ScoredPathNode method5(int var1, int var2, int var3);

   public abstract com.moonsworth.lunar.client.render.turbo.PathNode method6();

   public abstract int method7(
      com.moonsworth.lunar.client.render.turbo.PathNode[] var1, com.moonsworth.lunar.client.render.turbo.PathNode var2
   );

   public boolean method8() {
      return this.field7;
   }

   @Generated
   public Int2ObjectMap<com.moonsworth.lunar.client.render.turbo.PathNode> method9() {
      return this.field2;
   }

   @Generated
   public void method10(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public boolean method11() {
      return this.field6;
   }

   @Generated
   public void method12(boolean var1) {
      this.field7 = var1;
   }
}

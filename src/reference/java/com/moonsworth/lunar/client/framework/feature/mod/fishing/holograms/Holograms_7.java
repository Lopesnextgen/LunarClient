package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Holograms_7 {
   @Annotation2
   private final HologramsType6 type;
   @Annotation2
   private final Vector3iBridge pos;
   @Nullable
   @Annotation2
   private final Vector3iBridge entrance;
   @Nullable
   @Annotation2
   private final ArrayList<Vector3iBridge> levers;
   @Nullable
   @Annotation2
   private final Vector3iBridge superboom;
   @Annotation2
   private final ArrayList<Vector3iBridge> redstoneKey;
   @Nullable
   @Annotation2
   private final Vector3iBridge redstoneKeyPlacement;
   private transient boolean found;
   private transient ArrayList<Boolean> field1;
   private transient boolean field2;
   private transient boolean field3;
   private transient boolean field4;

   public Holograms_7(
      HologramsType6 var1,
      Vector3iBridge vector3iBridge,
      @Nullable Vector3iBridge var3,
      @Nullable ArrayList<Vector3iBridge> var4,
      @Nullable Vector3iBridge var5,
      ArrayList<Vector3iBridge> list,
      @Nullable Vector3iBridge var7
   ) {
      this.type = var1;
      this.pos = vector3iBridge;
      this.entrance = var3;
      this.levers = var4;
      this.superboom = var5;
      this.redstoneKey = list;
      this.redstoneKeyPlacement = var7;
      if (var4 != null) {
         this.field1 = new ArrayList<>(Collections.nCopies(var4.size(), false));
      } else {
         this.field1 = new ArrayList<>();
      }
   }

   public Holograms_7 method1() {
      return new Holograms_7(this.type, this.pos, this.entrance, this.levers, this.superboom, this.redstoneKey, this.redstoneKeyPlacement);
   }

   @Generated
   public HologramsType6 getType() {
      return this.type;
   }

   @Generated
   public Vector3iBridge getPos() {
      return this.pos;
   }

   @Nullable
   @Generated
   public Vector3iBridge getEntrance() {
      return this.entrance;
   }

   @Nullable
   @Generated
   public ArrayList<Vector3iBridge> getLevers() {
      return this.levers;
   }

   @Nullable
   @Generated
   public Vector3iBridge getSuperboom() {
      return this.superboom;
   }

   @Generated
   public ArrayList<Vector3iBridge> getRedstoneKey() {
      return this.redstoneKey;
   }

   @Nullable
   @Generated
   public Vector3iBridge getRedstoneKeyPlacement() {
      return this.redstoneKeyPlacement;
   }

   @Generated
   public boolean isFound() {
      return this.found;
   }

   @Generated
   public ArrayList<Boolean> method10() {
      return this.field1;
   }

   @Generated
   public boolean method11() {
      return this.field2;
   }

   @Generated
   public boolean method12() {
      return this.field3;
   }

   @Generated
   public boolean method13() {
      return this.field4;
   }

   @Generated
   public void setFound(boolean var1) {
      this.found = var1;
   }

   @Generated
   public void method15(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public void method16(boolean var1) {
      this.field3 = var1;
   }

   @Generated
   public void method17(boolean var1) {
      this.field4 = var1;
   }
}

package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.ArrayList;
import java.util.Collections;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class RoomSecret {
   @KeepName
   private final SecretType type;
   @KeepName
   private final Vec3iBridge pos;
   @Nullable
   @KeepName
   private final Vec3iBridge entrance;
   @Nullable
   @KeepName
   private final ArrayList<Vec3iBridge> levers;
   @Nullable
   @KeepName
   private final Vec3iBridge superboom;
   @KeepName
   private final ArrayList<Vec3iBridge> redstoneKey;
   @Nullable
   @KeepName
   private final Vec3iBridge redstoneKeyPlacement;
   private transient boolean found;
   private transient ArrayList<Boolean> field1;
   private transient boolean field2;
   private transient boolean field3;
   private transient boolean field4;

   public RoomSecret(
      SecretType secretType,
      Vec3iBridge horsestats202,
      @Nullable Vec3iBridge horsestats203,
      @Nullable ArrayList<Vec3iBridge> list4,
      @Nullable Vec3iBridge horsestats205,
      ArrayList<Vec3iBridge> list,
      @Nullable Vec3iBridge horsestats207
   ) {
      this.type = secretType;
      this.pos = horsestats202;
      this.entrance = horsestats203;
      this.levers = list4;
      this.superboom = horsestats205;
      this.redstoneKey = list;
      this.redstoneKeyPlacement = horsestats207;
      if (list4 != null) {
         this.field1 = new ArrayList<>(Collections.nCopies(list4.size(), false));
      } else {
         this.field1 = new ArrayList<>();
      }
   }

   public RoomSecret method1() {
      return new RoomSecret(this.type, this.pos, this.entrance, this.levers, this.superboom, this.redstoneKey, this.redstoneKeyPlacement);
   }

   @Generated
   public SecretType getType() {
      return this.type;
   }

   @Generated
   public Vec3iBridge getPos() {
      return this.pos;
   }

   @Nullable
   @Generated
   public Vec3iBridge getEntrance() {
      return this.entrance;
   }

   @Nullable
   @Generated
   public ArrayList<Vec3iBridge> getLevers() {
      return this.levers;
   }

   @Nullable
   @Generated
   public Vec3iBridge getSuperboom() {
      return this.superboom;
   }

   @Generated
   public ArrayList<Vec3iBridge> getRedstoneKey() {
      return this.redstoneKey;
   }

   @Nullable
   @Generated
   public Vec3iBridge getRedstoneKeyPlacement() {
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
   public void setFound(boolean flag1) {
      this.found = flag1;
   }

   @Generated
   public void method15(boolean flag1) {
      this.field2 = flag1;
   }

   @Generated
   public void method16(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public void method17(boolean flag1) {
      this.field4 = flag1;
   }
}

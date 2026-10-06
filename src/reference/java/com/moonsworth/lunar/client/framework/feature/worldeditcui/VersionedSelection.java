package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public abstract class VersionedSelection extends WorldEditSelectionBase implements WorldeditSelectionVersion {
   private long field2;

   public VersionedSelection() {
   }

   public VersionedSelection method2(long value) {
      this.field2 = value;
      return this;
   }

   @Override
   public long method4() {
      return this.field2;
   }
}

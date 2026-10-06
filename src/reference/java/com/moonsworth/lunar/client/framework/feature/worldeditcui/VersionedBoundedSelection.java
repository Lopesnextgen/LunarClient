package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public abstract class VersionedBoundedSelection extends BoundedSelection implements WorldeditSelectionVersion {
   private long field4;

   public VersionedBoundedSelection() {
   }

   public VersionedBoundedSelection method2(long value) {
      this.field4 = value;
      return this;
   }

   @Override
   public long method4() {
      return this.field4;
   }
}

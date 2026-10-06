package com.moonsworth.lunar.client.mumble;

import lombok.Generated;

public final class MumbleVec {
   public double xCoord;
   public double yCoord;
   public double zCoord;

   @Generated
   public MumbleVec(double value, double value2, double value3) {
      this.xCoord = value;
      this.yCoord = value2;
      this.zCoord = value3;
   }

   @Generated
   @Override
   public String toString() {
      return "MumbleVec(xCoord=" + this.xCoord + ", yCoord=" + this.yCoord + ", zCoord=" + this.zCoord + ")";
   }
}

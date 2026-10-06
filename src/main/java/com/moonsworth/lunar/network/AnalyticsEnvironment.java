package com.moonsworth.lunar.network;

import lombok.Generated;

public enum AnalyticsEnvironment {
   PRODUCTION(0),
   DEVELOPMENT(1);

   final int serverIndex;

   @Generated
   AnalyticsEnvironment(int value) {
      this.serverIndex = value;
   }
}

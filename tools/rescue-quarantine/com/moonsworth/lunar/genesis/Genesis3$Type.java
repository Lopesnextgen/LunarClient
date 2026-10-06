package com.moonsworth.lunar.genesis;

import lombok.Generated;

enum Genesis3$Type {
   BRANCH("gitBranch"),
   GIT_HASH("gitHash"),
   FULL_GIT_HASH("fullGitHash"),
   PRODUCTION("production"),
   PROGUARD_UUID("proguardUuid"),
   LUNAR_VERSION("lunarVersion"),
   UI_BRANCH("uiBranch"),
   UI_GIT_HASH("uiGitHash");

   private final String id;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   Genesis3$Type(String text3) {
      this.id = text3;
   }
}

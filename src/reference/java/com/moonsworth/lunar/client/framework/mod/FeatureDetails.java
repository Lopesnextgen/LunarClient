package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.chat.translation.CachedReplacement;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.translation.TranslationFormatter;
import java.util.Set;
import lombok.Generated;

public class FeatureDetails implements ModDetails, Translatable {
   private final String field1;
   private final Set<ModCategory> field2;
   private final Set<String> field3;
   private final Set<String> field4;
   private final boolean field5;
   private final boolean field6;

   @Override
   public boolean method4() {
      return this.field6;
   }

   public String getLanguagePath() {
      return this.field1 + ".details";
   }

   @Override
   public String getName() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO("name", new Object[0]);
   }

   @Override
   public String getDescription() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO("description", new Object[0]);
   }

   @Override
   public String method5(String text1, Object... items2) {
      return Client.method109().method67().method2(this.field1 + ".info", text1, items2);
   }

   @Override
   public CachedReplacement method6(String text1) {
      return TranslationFormatter.method3(Client.method109().method67().method2(this.field1 + ".info", text1, new Object[0]));
   }

   @Generated
   @Override
   public String toString() {
      return "FeatureDetails(languagePath="
         + this.getLanguagePath()
         + ", categories="
         + this.method1()
         + ", aliases="
         + this.method2()
         + ", originalAuthors="
         + this.method3()
         + ", isVanilla="
         + this.isVanilla()
         + ", allowsKeybind="
         + this.field6
         + ")";
   }

   @Generated
   public FeatureDetails(String text1, Set<ModCategory> set2, Set<String> set3, Set<String> set4, boolean flag5, boolean flag6) {
      this.field1 = text1;
      this.field2 = set2;
      this.field3 = set3;
      this.field4 = set4;
      this.field5 = flag5;
      this.field6 = flag6;
   }

   @Generated
   @Override
   public Set<ModCategory> method1() {
      return this.field2;
   }

   @Generated
   @Override
   public Set<String> method2() {
      return this.field3;
   }

   @Generated
   @Override
   public Set<String> method3() {
      return this.field4;
   }

   @Generated
   @Override
   public boolean isVanilla() {
      return this.field5;
   }
}

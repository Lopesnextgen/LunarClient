package com.moonsworth.lunar.client.account.skin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Objects;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class SavedSkin implements JsonProvider {
   private final String field1;
   private SkinType field2;
   private String name;
   private final String field3;

   public SavedSkin(String text1, SkinType skinType, String text) {
      this.field1 = text1;
      this.field2 = skinType;
      this.name = text;
      this.field3 = FilenameUtils.getBaseName(text1);
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (object != null && this.getClass() == object.getClass()) {
         SavedSkin gui2handler2 = (SavedSkin)object;
         return Objects.equals(this.field3, gui2handler2.getHash());
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field3);
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("url", this.field1);
      json1.addProperty("skinType", this.field2.getName());
      json1.addProperty("name", this.name);
      json1.addProperty("hash", this.field3);
      json1.addProperty("favoriteIndex", Ref.method4().method75().method24().indexOf(this));
      return json1;
   }

   @Generated
   public String getUrl() {
      return this.field1;
   }

   @Generated
   public SkinType method2() {
      return this.field2;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getHash() {
      return this.field3;
   }

   @Generated
   public void method2(SkinType skinType) {
      this.field2 = skinType;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }
}

package com.moonsworth.lunar.client.config.profile;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import lombok.Generated;

public class ModProfile {
   private ResourceLocationBridge icon = null;
   private String name;
   private String displayName;
   private boolean defaultProfile;
   private boolean active;
   private String iconName;
   private String server;
   private File file;

   public ModProfile(String text1, String text2, boolean flag3, boolean flag4, String text5) {
      this(Ref.method4().method61().method3(), text1, text2, flag3, flag4, text5);
   }

   public ModProfile(ModProfile horsestats1, String text2, String text3, boolean flag4, boolean flag5, String text6) {
      this.name = text2;
      this.displayName = text3;
      this.defaultProfile = flag4;
      this.active = flag5;
      this.iconName = text6;
      this.icon = null;
      this.server = "";
      if (!text6.equalsIgnoreCase("")) {
         this.icon = ResourceLocationBridge.create("lunar", "icons/profiles/" + text6 + ".png");
      }

      this.file = new File(LunarConstants.field25 + File.separator + text2);
      if (!this.file.exists()) {
         if (!flag5 && !ModProfileManager.DEFAULT_PROFILE_NAMES.contains(text2)) {
            Ref.method4().method61().method3(horsestats1);
         }

         if (!this.file.mkdirs()) {
            LunarLogger.method3("Can't make directory for " + text2 + " profile.", new Object[0]);
         }
      }
   }

   public ModProfile(ModProfile horsestats1, String text2, boolean flag3, boolean flag4) {
      this(horsestats1, text2, text2, flag3, flag4, "");
   }

   public ModProfile(String text1, boolean flag2, boolean flag3) {
      this(text1, text1, flag2, flag3, "");
   }

   public void setIconName(String text1) {
      this.iconName = text1;
      if (text1.equalsIgnoreCase("")) {
         this.icon = null;
      } else {
         this.icon = ResourceLocationBridge.create("lunar", "icons/profiles/" + text1 + ".png");
      }
   }

   @Generated
   public ResourceLocationBridge getIcon() {
      return this.icon;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public String getDisplayName() {
      return this.displayName;
   }

   @Generated
   public boolean isDefaultProfile() {
      return this.defaultProfile;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public String getIconName() {
      return this.iconName;
   }

   @Generated
   public String getServer() {
      return this.server;
   }

   @Generated
   public File getFile() {
      return this.file;
   }

   @Generated
   public void setIcon(ResourceLocationBridge horsestats141) {
      this.icon = horsestats141;
   }

   @Generated
   public void setName(String text1) {
      this.name = text1;
   }

   @Generated
   public void setDisplayName(String text1) {
      this.displayName = text1;
   }

   @Generated
   public void setDefaultProfile(boolean flag1) {
      this.defaultProfile = flag1;
   }

   @Generated
   public void setActive(boolean flag1) {
      this.active = flag1;
   }

   @Generated
   public void setServer(String text1) {
      this.server = text1;
   }

   @Generated
   public void setFile(File file1) {
      this.file = file1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ModProfile horsestats2)) {
         return false;
      } else {
         if (!horsestats2.canEqual(this)) {
            return false;
         }

         if (this.isDefaultProfile() != horsestats2.method2()) {
            return false;
         }

         if (this.isActive() != horsestats2.isActive()) {
            return false;
         }

         ResourceLocationBridge horsestats143 = this.getIcon();
         ResourceLocationBridge horsestats144 = horsestats2.getIcon();
         if (horsestats143 == null ? horsestats144 == null : horsestats143.equals(horsestats144)) {
            String text5 = this.getName();
            String text6 = horsestats2.getName();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               String text7 = this.getDisplayName();
               String text8 = horsestats2.getDisplayName();
               if (text7 == null ? text8 == null : text7.equals(text8)) {
                  String text9 = this.getIconName();
                  String text10 = horsestats2.getIconName();
                  if (text9 == null ? text10 == null : text9.equals(text10)) {
                     String text11 = this.getServer();
                     String text12 = horsestats2.getServer();
                     if (text11 == null ? text12 == null : text11.equals(text12)) {
                        File file13 = this.getFile();
                        File file14 = horsestats2.getFile();
                        return file13 == null ? file14 == null : file13.equals(file14);
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ModProfile;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.isDefaultProfile() ? 79 : 97);
      number2 = number2 * 59 + (this.isActive() ? 79 : 97);
      ResourceLocationBridge horsestats143 = this.getIcon();
      number2 = number2 * 59 + (horsestats143 == null ? 43 : horsestats143.hashCode());
      String text4 = this.getName();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      String text5 = this.getDisplayName();
      number2 = number2 * 59 + (text5 == null ? 43 : text5.hashCode());
      String text6 = this.getIconName();
      number2 = number2 * 59 + (text6 == null ? 43 : text6.hashCode());
      String text7 = this.getServer();
      number2 = number2 * 59 + (text7 == null ? 43 : text7.hashCode());
      File file8 = this.getFile();
      return number2 * 59 + (file8 == null ? 43 : file8.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "Profile(icon="
         + this.getIcon()
         + ", name="
         + this.getName()
         + ", displayName="
         + this.getDisplayName()
         + ", defaultProfile="
         + this.isDefaultProfile()
         + ", active="
         + this.isActive()
         + ", iconName="
         + this.getIconName()
         + ", server="
         + this.getServer()
         + ", file="
         + this.getFile()
         + ")";
   }
}

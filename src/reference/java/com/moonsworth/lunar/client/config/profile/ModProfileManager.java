package com.moonsworth.lunar.client.config.profile;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class ModProfileManager implements LoadableHandler, JsonProvider, EventBusAccess {
   public static final ImmutableList<String> DEFAULT_PROFILE_NAMES = ImmutableList.of("Arena PvP", "Hypixel Skyblock", "UHC");
   private static final ImmutableList<String> PROFILE_CONFIG_FILES = ImmutableList.of("controls.json", "general.json", "mods.json", "performance.json");
   private final List<ModProfile> modProfiles = new LinkedList<>();
   private ModProfile activeProfile;
   private long lastSwitchTime;
   private boolean switching;
   private ModProfile pendingProfile;

   public ModProfileManager() {
      this.handle(EventTick.class, arg1 -> {
         if (this.pendingProfile != null) {
            this.switchProfile(this.pendingProfile);
            this.pendingProfile = null;
         }
      });
   }

   public List<ModProfile> getModProfiles() {
      LinkedList list1 = new LinkedList<>(this.modProfiles);
      list1.sort((arg0, arg1x) -> arg0.method2() ? 1 : (arg1x.method2() ? -1 : 0));
      return list1;
   }

   public ModProfile switchProfile() {
      for (ModProfile horsestats2 : this.modProfiles) {
         if (horsestats2.method2()) {
            return horsestats2;
         }
      }

      return null;
   }

   public void switchProfile(ModProfile horsestats1) {
      if (this.activeProfile.equals(horsestats1)) {
         LunarLogger.method5("Profile", new Object[]{"We are attempt to switch to the current loaded profile."});
      } else {
         this.lastSwitchTime = System.currentTimeMillis();
         this.switching = true;

         try {
            Ref.method4().method41().close();
            Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
            this.setActiveProfile(horsestats1);
            Ref.method4().method41().init();
            Ref.method4().method40().HHIHOOIHCOHOIRORCHICOCHCORROCR();
         } finally {
            this.switching = false;
         }
      }
   }

   public void scheduleSwitch(ModProfile horsestats1) {
      this.pendingProfile = horsestats1;
   }

   public void deleteProfile(ModProfile horsestats1) {
      this.modProfiles.remove(horsestats1);

      try {
         FileUtils.deleteDirectory(horsestats1.getFile());
      } catch (IOException exception3) {
         exception3.printStackTrace();
      }
   }

   public ModProfile createProfile(String text1) {
      ModProfile horsestats2 = new ModProfile(this.getActiveProfile(), text1, false, false);
      this.modProfiles.add(horsestats2);
      this.switchProfile(horsestats2);
      return horsestats2;
   }

   public void setActiveProfile(ModProfile horsestats1) {
      this.activeProfile.setActive(false);
      horsestats1.setActive(true);
      this.activeProfile = horsestats1;
   }

   public void close() {
      this.save();
   }

   public void addProfile(ModProfile horsestats1) {
      boolean flag2 = this.modProfiles.stream().anyMatch(arg1x -> arg1x.getName().equalsIgnoreCase(horsestats1.getName()));
      if (!flag2) {
         this.modProfiles.add(horsestats1);
      }
   }

   public void save() {
      JsonArray array1 = this.toJson();
      File file2 = new File(LunarConstants.field25 + File.separator + "profile_manager.json");

      try {
         if (!file2.exists() && !file2.createNewFile()) {
            return;
         }

         try (FileWriter filewriter3 = new FileWriter(file2)) {
            LunarConstants.field22.toJson(array1, filewriter3);
         }
      } catch (Exception exception8) {
         LunarLogger.method7("Couldn't save to file [%s]", new Object[]{exception8.getMessage()});
      }
   }

   private JsonArray toJson() {
      JsonArray array1 = new JsonArray();

      for (ModProfile horsestats3 : this.modProfiles) {
         JsonObject json4 = new JsonObject();
         json4.addProperty("name", horsestats3.getName());
         json4.addProperty("displayName", horsestats3.getDisplayName());
         json4.addProperty("default", horsestats3.method2());
         json4.addProperty("active", horsestats3.isActive());
         json4.addProperty("iconName", horsestats3.getIconName());
         json4.addProperty("server", horsestats3.getServer());
         array1.add(json4);
      }

      return array1;
   }

   public void init() {
      File file1 = new File(LunarConstants.field25 + File.separator + "profile_manager.json");
      if (!file1.exists()) {
         this.activeProfile = new ModProfile("Default", true, true);
         this.modProfiles.add(this.activeProfile);
         UnmodifiableIterator unmodifiableiterator11 = field1.iterator();

         while (unmodifiableiterator11.hasNext()) {
            String text12 = (String)unmodifiableiterator11.next();
            this.createBundledProfile(text12);
         }
      } else {
         try {
            JsonParser jsonparser2 = new JsonParser();
            JsonElement element3 = jsonparser2.parse(new FileReader(file1));
            if (element3.isJsonArray()) {
               for (JsonElement element5 : element3.getAsJsonArray()) {
                  JsonObject json6 = element5.getAsJsonObject();
                  String text7 = json6.has("iconName") && !json6.get("iconName").isJsonNull() ? json6.get("iconName").getAsString() : "";
                  String text8 = json6.has("server") && !json6.get("server").isJsonNull() ? json6.get("server").getAsString() : "";
                  ModProfile horsestats9 = new ModProfile(
                     json6.get("name").getAsString(),
                     json6.get("displayName").getAsString(),
                     json6.get("default").getAsBoolean(),
                     json6.get("active").getAsBoolean(),
                     text7
                  );
                  horsestats9.setServer(text8);
                  this.modProfiles.add(horsestats9);
                  if (horsestats9.isActive()) {
                     this.activeProfile = horsestats9;
                  }
               }
            }
         } catch (Exception exception10) {
            LunarLogger.method7("Couldn't load file [" + exception10.getMessage() + "]", new Object[0]);
         }

         if (this.activeProfile == null) {
            this.activeProfile = new ModProfile("Default", true, true);
            this.modProfiles.add(this.activeProfile);
         }
      }
   }

   private void createBundledProfile(String text1) {
      this.createBundledProfileAt(text1, -1);
   }

   private ModProfile createBundledProfileAt(String text1, int index2) {
      ModProfile horsestats3 = new ModProfile(text1, false, false);
      switch (text1) {
         case "Arena PvP":
            horsestats3.setIconName("crossed-swords");
            break;
         case "Hypixel Skyblock":
            horsestats3.setIconName("hypixel");
            break;
         case "UHC":
            horsestats3.setIconName("apple");
      }

      UnmodifiableIterator unmodifiableiterator11 = field2.iterator();

      while (unmodifiableiterator11.hasNext()) {
         String text12 = (String)unmodifiableiterator11.next();

         try {
            InputStream input6 = this.getClass().getResourceAsStream("/assets/profiles/" + text1 + "/" + text12);
            byte[] items7 = new byte[input6.available()];
            input6.read(items7);
            File file8 = new File(horsestats3.getFile() + File.separator + text12);
            FileOutputStream stream9 = new FileOutputStream(file8);
            stream9.write(items7);
         } catch (IOException exception10) {
            exception10.printStackTrace();
         }
      }

      if (index2 == -1) {
         this.modProfiles.add(horsestats3);
      } else {
         this.modProfiles.add(index2, horsestats3);
      }

      return horsestats3;
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      if (this.activeProfile != null) {
         json1.addProperty("activeProfile", this.activeProfile.getName());
      }

      JsonArray array2 = new JsonArray();
      UnmodifiableIterator unmodifiableiterator3 = field1.iterator();

      while (unmodifiableiterator3.hasNext()) {
         String text4 = (String)unmodifiableiterator3.next();
         array2.add(text4);
      }

      json1.add("defaultProfiles", array2);
      json1.add("profiles", this.toJson());
      return json1;
   }

   public ModProfile resetDefaultProfile(ModProfile horsestats1) {
      String text2 = horsestats1.getName();
      if (!field1.contains(text2)) {
         return null;
      }

      int number3 = this.modProfiles.indexOf(horsestats1);
      if (horsestats1.isActive()) {
         this.switchProfile(this.switchProfile());
      }

      this.deleteProfile(horsestats1);
      return this.createBundledProfileAt(text2, number3);
   }

   @Generated
   public ModProfile getActiveProfile() {
      return this.activeProfile;
   }

   @Generated
   public long getLastSwitchTime() {
      return this.lastSwitchTime;
   }

   @Generated
   public boolean isSwitching() {
      return this.switching;
   }
}

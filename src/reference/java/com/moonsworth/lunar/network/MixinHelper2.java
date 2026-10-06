package com.moonsworth.lunar.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MixinHelper2 implements Serializable {
   private static final long field1 = 1L;
   public static final String SERIALIZED_NAME_PLAYER_UUID = "player_uuid";
   @SerializedName("player_uuid")
   private String playerUuid;
   public static final String SERIALIZED_NAME_INSTALLATION_ID = "installation_id";
   @SerializedName("installation_id")
   private String installationId;
   public static final String SERIALIZED_NAME_OVERWOLF_MUID = "overwolf_muid";
   @SerializedName("overwolf_muid")
   private String overwolfMuid;
   public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
   @SerializedName("timestamp")
   private OffsetDateTime timestamp;
   public static final String SERIALIZED_NAME_EVENT_ID = "event_id";
   @SerializedName("event_id")
   private String eventId;
   public static final String SERIALIZED_NAME_ASSETSERVER_SESSION_ID = "assetserver_session_id";
   @SerializedName("assetserver_session_id")
   private UUID assetserverSessionId;
   public static final String SERIALIZED_NAME_LAUNCH_ID = "launch_id";
   @SerializedName("launch_id")
   private String launchId;
   public static final String SERIALIZED_NAME_INBOUND_LOCATION = "inbound_location";
   @SerializedName("inbound_location")
   private MixinHelper9 inboundLocation;
   public static final String SERIALIZED_NAME_LOCATION = "location";
   @SerializedName("location")
   private MixinHelper10 location;
   public static final String SERIALIZED_NAME_MINECRAFT_VERSION = "minecraft_version";
   @SerializedName("minecraft_version")
   private String minecraftVersion;
   public static final String SERIALIZED_NAME_LUNAR_CLIENT_GIT_COMMIT = "lunar_client_git_commit";
   @SerializedName("lunar_client_git_commit")
   private String lunarClientGitCommit;
   public static final String SERIALIZED_NAME_LUNAR_CLIENT_GIT_BRANCH = "lunar_client_git_branch";
   @SerializedName("lunar_client_git_branch")
   private String lunarClientGitBranch;
   public static final String SERIALIZED_NAME_LUNAR_CLIENT_SEMVER = "lunar_client_semver";
   @SerializedName("lunar_client_semver")
   private String lunarClientSemver;
   public static final String SERIALIZED_NAME_LUNAR_CLIENT_UI_GIT_COMMIT = "lunar_client_ui_git_commit";
   @SerializedName("lunar_client_ui_git_commit")
   private String lunarClientUiGitCommit;
   public static final String SERIALIZED_NAME_LUNAR_CLIENT_UI_GIT_BRANCH = "lunar_client_ui_git_branch";
   @SerializedName("lunar_client_ui_git_branch")
   private String lunarClientUiGitBranch;
   public static final String SERIALIZED_NAME_OPERATING_SYSTEM = "operating_system";
   @SerializedName("operating_system")
   private String operatingSystem;
   public static final String SERIALIZED_NAME_OPERATING_SYSTEM_RELEASE = "operating_system_release";
   @SerializedName("operating_system_release")
   private String operatingSystemRelease;
   public static final String SERIALIZED_NAME_CPU_ARCHITECTURE = "cpu_architecture";
   @SerializedName("cpu_architecture")
   private String cpuArchitecture;
   public static final String SERIALIZED_NAME_LAUNCHER_VERSION = "launcher_version";
   @SerializedName("launcher_version")
   private String launcherVersion;
   public static final String SERIALIZED_NAME_CANARY_TOKEN = "canary_token";
   @SerializedName("canary_token")
   private String canaryToken;
   public static final String SERIALIZED_NAME_ICHOR_MODULES = "ichor_modules";
   @SerializedName("ichor_modules")
   private List<String> ichorModules = new ArrayList<>();
   public static final String SERIALIZED_NAME_GL_EXTENSIONS = "gl_extensions";
   @SerializedName("gl_extensions")
   private List<String> glExtensions = new ArrayList<>();
   public static final String SERIALIZED_NAME_INSTALLED_MODS = "installed_mods";
   @SerializedName("installed_mods")
   private List<MixinHelper14> installedMods = new ArrayList<>();
   public static final String SERIALIZED_NAME_WEARER_UUID = "wearer_uuid";
   @SerializedName("wearer_uuid")
   private String wearerUuid;
   public static final String SERIALIZED_NAME_WEARER_COSMETIC_IDS = "wearer_cosmetic_ids";
   @SerializedName("wearer_cosmetic_ids")
   private List<BigDecimal> wearerCosmeticIds = new ArrayList<>();
   public static final String SERIALIZED_NAME_WEARER_OUTFIT_ID = "wearer_outfit_id";
   @SerializedName("wearer_outfit_id")
   private String wearerOutfitId;
   public static final String SERIALIZED_NAME_TRIGGER = "trigger";
   @SerializedName("trigger")
   private String trigger;
   public static final String SERIALIZED_NAME_GEO_LOCATION = "geo_location";
   @SerializedName("geo_location")
   private MixinHelper7 geoLocation;
   public static HashSet<String> field54 = new HashSet<>();
   public static HashSet<String> field55 = new HashSet<>();

   public MixinHelper2 playerUuid(String var1) {
      this.playerUuid = var1;
      return this;
   }

   @Nullable
   public String getPlayerUuid() {
      return this.playerUuid;
   }

   public void setPlayerUuid(String var1) {
      this.playerUuid = var1;
   }

   public MixinHelper2 installationId(String var1) {
      this.installationId = var1;
      return this;
   }

   @Nonnull
   public String getInstallationId() {
      return this.installationId;
   }

   public void setInstallationId(String var1) {
      this.installationId = var1;
   }

   public MixinHelper2 overwolfMuid(String var1) {
      this.overwolfMuid = var1;
      return this;
   }

   @Nullable
   public String getOverwolfMuid() {
      return this.overwolfMuid;
   }

   public void setOverwolfMuid(String var1) {
      this.overwolfMuid = var1;
   }

   public MixinHelper2 timestamp(OffsetDateTime var1) {
      this.timestamp = var1;
      return this;
   }

   @Nullable
   public OffsetDateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(OffsetDateTime var1) {
      this.timestamp = var1;
   }

   public MixinHelper2 eventId(String var1) {
      this.eventId = var1;
      return this;
   }

   @Nullable
   public String getEventId() {
      return this.eventId;
   }

   public void setEventId(String var1) {
      this.eventId = var1;
   }

   public MixinHelper2 assetserverSessionId(UUID var1) {
      this.assetserverSessionId = var1;
      return this;
   }

   @Nullable
   public UUID getAssetserverSessionId() {
      return this.assetserverSessionId;
   }

   public void setAssetserverSessionId(UUID var1) {
      this.assetserverSessionId = var1;
   }

   public MixinHelper2 launchId(String var1) {
      this.launchId = var1;
      return this;
   }

   @Nullable
   public String getLaunchId() {
      return this.launchId;
   }

   public void setLaunchId(String var1) {
      this.launchId = var1;
   }

   public MixinHelper2 inboundLocation(MixinHelper9 var1) {
      this.inboundLocation = var1;
      return this;
   }

   @Nullable
   public MixinHelper9 getInboundLocation() {
      return this.inboundLocation;
   }

   public void setInboundLocation(MixinHelper9 var1) {
      this.inboundLocation = var1;
   }

   public MixinHelper2 location(MixinHelper10 var1) {
      this.location = var1;
      return this;
   }

   @Nullable
   public MixinHelper10 getLocation() {
      return this.location;
   }

   public void setLocation(MixinHelper10 var1) {
      this.location = var1;
   }

   public MixinHelper2 minecraftVersion(String var1) {
      this.minecraftVersion = var1;
      return this;
   }

   @Nullable
   public String getMinecraftVersion() {
      return this.minecraftVersion;
   }

   public void setMinecraftVersion(String var1) {
      this.minecraftVersion = var1;
   }

   public MixinHelper2 lunarClientGitCommit(String var1) {
      this.lunarClientGitCommit = var1;
      return this;
   }

   @Nullable
   public String getLunarClientGitCommit() {
      return this.lunarClientGitCommit;
   }

   public void setLunarClientGitCommit(String var1) {
      this.lunarClientGitCommit = var1;
   }

   public MixinHelper2 lunarClientGitBranch(String var1) {
      this.lunarClientGitBranch = var1;
      return this;
   }

   @Nullable
   public String getLunarClientGitBranch() {
      return this.lunarClientGitBranch;
   }

   public void setLunarClientGitBranch(String var1) {
      this.lunarClientGitBranch = var1;
   }

   public MixinHelper2 lunarClientSemver(String var1) {
      this.lunarClientSemver = var1;
      return this;
   }

   @Nullable
   public String getLunarClientSemver() {
      return this.lunarClientSemver;
   }

   public void setLunarClientSemver(String var1) {
      this.lunarClientSemver = var1;
   }

   public MixinHelper2 lunarClientUiGitCommit(String var1) {
      this.lunarClientUiGitCommit = var1;
      return this;
   }

   @Nullable
   public String getLunarClientUiGitCommit() {
      return this.lunarClientUiGitCommit;
   }

   public void setLunarClientUiGitCommit(String var1) {
      this.lunarClientUiGitCommit = var1;
   }

   public MixinHelper2 lunarClientUiGitBranch(String var1) {
      this.lunarClientUiGitBranch = var1;
      return this;
   }

   @Nullable
   public String getLunarClientUiGitBranch() {
      return this.lunarClientUiGitBranch;
   }

   public void setLunarClientUiGitBranch(String var1) {
      this.lunarClientUiGitBranch = var1;
   }

   public MixinHelper2 operatingSystem(String var1) {
      this.operatingSystem = var1;
      return this;
   }

   @Nullable
   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public void setOperatingSystem(String var1) {
      this.operatingSystem = var1;
   }

   public MixinHelper2 operatingSystemRelease(String var1) {
      this.operatingSystemRelease = var1;
      return this;
   }

   @Nullable
   public String getOperatingSystemRelease() {
      return this.operatingSystemRelease;
   }

   public void setOperatingSystemRelease(String var1) {
      this.operatingSystemRelease = var1;
   }

   public MixinHelper2 cpuArchitecture(String var1) {
      this.cpuArchitecture = var1;
      return this;
   }

   @Nullable
   public String getCpuArchitecture() {
      return this.cpuArchitecture;
   }

   public void setCpuArchitecture(String var1) {
      this.cpuArchitecture = var1;
   }

   public MixinHelper2 launcherVersion(String var1) {
      this.launcherVersion = var1;
      return this;
   }

   @Nullable
   public String getLauncherVersion() {
      return this.launcherVersion;
   }

   public void setLauncherVersion(String var1) {
      this.launcherVersion = var1;
   }

   public MixinHelper2 canaryToken(String var1) {
      this.canaryToken = var1;
      return this;
   }

   @Nullable
   public String getCanaryToken() {
      return this.canaryToken;
   }

   public void setCanaryToken(String var1) {
      this.canaryToken = var1;
   }

   public MixinHelper2 ichorModules(List<String> var1) {
      this.ichorModules = var1;
      return this;
   }

   public MixinHelper2 addIchorModulesItem(String var1) {
      if (this.ichorModules == null) {
         this.ichorModules = new ArrayList<>();
      }

      this.ichorModules.add(var1);
      return this;
   }

   @Nullable
   public List<String> getIchorModules() {
      return this.ichorModules;
   }

   public void setIchorModules(List<String> var1) {
      this.ichorModules = var1;
   }

   public MixinHelper2 glExtensions(List<String> var1) {
      this.glExtensions = var1;
      return this;
   }

   public MixinHelper2 addGlExtensionsItem(String var1) {
      if (this.glExtensions == null) {
         this.glExtensions = new ArrayList<>();
      }

      this.glExtensions.add(var1);
      return this;
   }

   @Nullable
   public List<String> getGlExtensions() {
      return this.glExtensions;
   }

   public void setGlExtensions(List<String> var1) {
      this.glExtensions = var1;
   }

   public MixinHelper2 installedMods(List<MixinHelper14> var1) {
      this.installedMods = var1;
      return this;
   }

   public MixinHelper2 addInstalledModsItem(MixinHelper14 var1) {
      if (this.installedMods == null) {
         this.installedMods = new ArrayList<>();
      }

      this.installedMods.add(var1);
      return this;
   }

   @Nonnull
   public List<MixinHelper14> getInstalledMods() {
      return this.installedMods;
   }

   public void setInstalledMods(List<MixinHelper14> var1) {
      this.installedMods = var1;
   }

   public MixinHelper2 wearerUuid(String var1) {
      this.wearerUuid = var1;
      return this;
   }

   @Nullable
   public String getWearerUuid() {
      return this.wearerUuid;
   }

   public void setWearerUuid(String var1) {
      this.wearerUuid = var1;
   }

   public MixinHelper2 wearerCosmeticIds(List<BigDecimal> var1) {
      this.wearerCosmeticIds = var1;
      return this;
   }

   public MixinHelper2 addWearerCosmeticIdsItem(BigDecimal var1) {
      if (this.wearerCosmeticIds == null) {
         this.wearerCosmeticIds = new ArrayList<>();
      }

      this.wearerCosmeticIds.add(var1);
      return this;
   }

   @Nullable
   public List<BigDecimal> getWearerCosmeticIds() {
      return this.wearerCosmeticIds;
   }

   public void setWearerCosmeticIds(List<BigDecimal> var1) {
      this.wearerCosmeticIds = var1;
   }

   public MixinHelper2 wearerOutfitId(String var1) {
      this.wearerOutfitId = var1;
      return this;
   }

   @Nullable
   public String getWearerOutfitId() {
      return this.wearerOutfitId;
   }

   public void setWearerOutfitId(String var1) {
      this.wearerOutfitId = var1;
   }

   public MixinHelper2 trigger(String var1) {
      this.trigger = var1;
      return this;
   }

   @Nullable
   public String getTrigger() {
      return this.trigger;
   }

   public void setTrigger(String var1) {
      this.trigger = var1;
   }

   public MixinHelper2 geoLocation(MixinHelper7 var1) {
      this.geoLocation = var1;
      return this;
   }

   @Nullable
   public MixinHelper7 getGeoLocation() {
      return this.geoLocation;
   }

   public void setGeoLocation(MixinHelper7 var1) {
      this.geoLocation = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper2 var2 = (MixinHelper2)var1;
         return Objects.equals(this.playerUuid, var2.playerUuid)
            && Objects.equals(this.installationId, var2.installationId)
            && Objects.equals(this.overwolfMuid, var2.overwolfMuid)
            && Objects.equals(this.timestamp, var2.timestamp)
            && Objects.equals(this.eventId, var2.eventId)
            && Objects.equals(this.assetserverSessionId, var2.assetserverSessionId)
            && Objects.equals(this.launchId, var2.launchId)
            && Objects.equals(this.inboundLocation, var2.inboundLocation)
            && Objects.equals(this.location, var2.location)
            && Objects.equals(this.minecraftVersion, var2.minecraftVersion)
            && Objects.equals(this.lunarClientGitCommit, var2.lunarClientGitCommit)
            && Objects.equals(this.lunarClientGitBranch, var2.lunarClientGitBranch)
            && Objects.equals(this.lunarClientSemver, var2.lunarClientSemver)
            && Objects.equals(this.lunarClientUiGitCommit, var2.lunarClientUiGitCommit)
            && Objects.equals(this.lunarClientUiGitBranch, var2.playerUuid1)
            && Objects.equals(this.operatingSystem, var2.operatingSystem)
            && Objects.equals(this.operatingSystemRelease, var2.operatingSystemRelease)
            && Objects.equals(this.cpuArchitecture, var2.cpuArchitecture)
            && Objects.equals(this.launcherVersion, var2.playerUuid6)
            && Objects.equals(this.canaryToken, var2.playerUuid8)
            && Objects.equals(this.ichorModules, var2.ichorModules)
            && Objects.equals(this.glExtensions, var2.glExtensions)
            && Objects.equals(this.installedMods, var2.installedMods)
            && Objects.equals(this.wearerUuid, var2.wearerUuid)
            && Objects.equals(this.wearerCosmeticIds, var2.wearerCosmeticIds)
            && Objects.equals(this.wearerOutfitId, var2.wearerOutfitId)
            && Objects.equals(this.trigger, var2.installationId1)
            && Objects.equals(this.geoLocation, var2.installationId3);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(
         this.playerUuid,
         this.installationId,
         this.overwolfMuid,
         this.timestamp,
         this.eventId,
         this.assetserverSessionId,
         this.launchId,
         this.inboundLocation,
         this.location,
         this.minecraftVersion,
         this.lunarClientGitCommit,
         this.lunarClientGitBranch,
         this.lunarClientSemver,
         this.lunarClientUiGitCommit,
         this.lunarClientUiGitBranch,
         this.operatingSystem,
         this.operatingSystemRelease,
         this.cpuArchitecture,
         this.launcherVersion,
         this.canaryToken,
         this.ichorModules,
         this.glExtensions,
         this.installedMods,
         this.wearerUuid,
         this.wearerCosmeticIds,
         this.wearerOutfitId,
         this.trigger,
         this.geoLocation
      );
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class BaseGameEvent {\n");
      var1.append("    playerUuid: ").append(this.toIndentedString(this.playerUuid)).append("\n");
      var1.append("    installationId: ").append(this.toIndentedString(this.installationId)).append("\n");
      var1.append("    overwolfMuid: ").append(this.toIndentedString(this.overwolfMuid)).append("\n");
      var1.append("    timestamp: ").append(this.toIndentedString(this.timestamp)).append("\n");
      var1.append("    eventId: ").append(this.toIndentedString(this.eventId)).append("\n");
      var1.append("    assetserverSessionId: ").append(this.toIndentedString(this.assetserverSessionId)).append("\n");
      var1.append("    launchId: ").append(this.toIndentedString(this.launchId)).append("\n");
      var1.append("    inboundLocation: ").append(this.toIndentedString(this.inboundLocation)).append("\n");
      var1.append("    location: ").append(this.toIndentedString(this.location)).append("\n");
      var1.append("    minecraftVersion: ").append(this.toIndentedString(this.minecraftVersion)).append("\n");
      var1.append("    lunarClientGitCommit: ").append(this.toIndentedString(this.lunarClientGitCommit)).append("\n");
      var1.append("    lunarClientGitBranch: ").append(this.toIndentedString(this.lunarClientGitBranch)).append("\n");
      var1.append("    lunarClientSemver: ").append(this.toIndentedString(this.lunarClientSemver)).append("\n");
      var1.append("    lunarClientUiGitCommit: ").append(this.toIndentedString(this.lunarClientUiGitCommit)).append("\n");
      var1.append("    lunarClientUiGitBranch: ").append(this.toIndentedString(this.lunarClientUiGitBranch)).append("\n");
      var1.append("    operatingSystem: ").append(this.toIndentedString(this.operatingSystem)).append("\n");
      var1.append("    operatingSystemRelease: ").append(this.toIndentedString(this.operatingSystemRelease)).append("\n");
      var1.append("    cpuArchitecture: ").append(this.toIndentedString(this.cpuArchitecture)).append("\n");
      var1.append("    launcherVersion: ").append(this.toIndentedString(this.launcherVersion)).append("\n");
      var1.append("    canaryToken: ").append(this.toIndentedString(this.canaryToken)).append("\n");
      var1.append("    ichorModules: ").append(this.toIndentedString(this.ichorModules)).append("\n");
      var1.append("    glExtensions: ").append(this.toIndentedString(this.glExtensions)).append("\n");
      var1.append("    installedMods: ").append(this.toIndentedString(this.installedMods)).append("\n");
      var1.append("    wearerUuid: ").append(this.toIndentedString(this.wearerUuid)).append("\n");
      var1.append("    wearerCosmeticIds: ").append(this.toIndentedString(this.wearerCosmeticIds)).append("\n");
      var1.append("    wearerOutfitId: ").append(this.toIndentedString(this.wearerOutfitId)).append("\n");
      var1.append("    trigger: ").append(this.toIndentedString(this.trigger)).append("\n");
      var1.append("    geoLocation: ").append(this.toIndentedString(this.geoLocation)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field55.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEvent is not found in the empty JSON string", field55.toString())
         );
      }

      JsonObject var1 = var0.getAsJsonObject();
      if (var1.get("player_uuid") != null && !var1.get("player_uuid").isJsonNull() && !var1.get("player_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `player_uuid` to be a primitive type in the JSON string but got `%s`", var1.get("player_uuid").toString())
         );
      }

      if (!var1.get("installation_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `installation_id` to be a primitive type in the JSON string but got `%s`", var1.get("installation_id").toString())
         );
      }

      if (var1.get("overwolf_muid") != null && !var1.get("overwolf_muid").isJsonNull() && !var1.get("overwolf_muid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `overwolf_muid` to be a primitive type in the JSON string but got `%s`", var1.get("overwolf_muid").toString())
         );
      }

      if (var1.get("event_id") != null && !var1.get("event_id").isJsonNull() && !var1.get("event_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `event_id` to be a primitive type in the JSON string but got `%s`", var1.get("event_id").toString())
         );
      }

      if (var1.get("assetserver_session_id") != null
         && !var1.get("assetserver_session_id").isJsonNull()
         && !var1.get("assetserver_session_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `assetserver_session_id` to be a primitive type in the JSON string but got `%s`",
               var1.get("assetserver_session_id").toString()
            )
         );
      }

      if (var1.get("launch_id") != null && !var1.get("launch_id").isJsonNull() && !var1.get("launch_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `launch_id` to be a primitive type in the JSON string but got `%s`", var1.get("launch_id").toString())
         );
      }

      if (var1.get("inbound_location") != null && !var1.get("inbound_location").isJsonNull()) {
         MixinHelper9.validateJsonElement(var1.get("inbound_location"));
      }

      if (var1.get("location") != null && !var1.get("location").isJsonNull()) {
         MixinHelper10.validateJsonElement(var1.get("location"));
      }

      if (var1.get("minecraft_version") != null && !var1.get("minecraft_version").isJsonNull() && !var1.get("minecraft_version").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `minecraft_version` to be a primitive type in the JSON string but got `%s`", var1.get("minecraft_version").toString()
            )
         );
      }

      if (var1.get("lunar_client_git_commit") != null
         && !var1.get("lunar_client_git_commit").isJsonNull()
         && !var1.get("lunar_client_git_commit").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_git_commit` to be a primitive type in the JSON string but got `%s`",
               var1.get("lunar_client_git_commit").toString()
            )
         );
      }

      if (var1.get("lunar_client_git_branch") != null
         && !var1.get("lunar_client_git_branch").isJsonNull()
         && !var1.get("lunar_client_git_branch").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_git_branch` to be a primitive type in the JSON string but got `%s`",
               var1.get("lunar_client_git_branch").toString()
            )
         );
      }

      if (var1.get("lunar_client_semver") != null && !var1.get("lunar_client_semver").isJsonNull() && !var1.get("lunar_client_semver").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_semver` to be a primitive type in the JSON string but got `%s`", var1.get("lunar_client_semver").toString()
            )
         );
      }

      if (var1.get("lunar_client_ui_git_commit") != null
         && !var1.get("lunar_client_ui_git_commit").isJsonNull()
         && !var1.get("lunar_client_ui_git_commit").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_ui_git_commit` to be a primitive type in the JSON string but got `%s`",
               var1.get("lunar_client_ui_git_commit").toString()
            )
         );
      }

      if (var1.get("lunar_client_ui_git_branch") != null
         && !var1.get("lunar_client_ui_git_branch").isJsonNull()
         && !var1.get("lunar_client_ui_git_branch").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_ui_git_branch` to be a primitive type in the JSON string but got `%s`",
               var1.get("lunar_client_ui_git_branch").toString()
            )
         );
      }

      if (var1.get("operating_system") != null && !var1.get("operating_system").isJsonNull() && !var1.get("operating_system").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `operating_system` to be a primitive type in the JSON string but got `%s`", var1.get("operating_system").toString()
            )
         );
      }

      if (var1.get("operating_system_release") != null
         && !var1.get("operating_system_release").isJsonNull()
         && !var1.get("operating_system_release").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `operating_system_release` to be a primitive type in the JSON string but got `%s`",
               var1.get("operating_system_release").toString()
            )
         );
      }

      if (var1.get("cpu_architecture") != null && !var1.get("cpu_architecture").isJsonNull() && !var1.get("cpu_architecture").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `cpu_architecture` to be a primitive type in the JSON string but got `%s`", var1.get("cpu_architecture").toString()
            )
         );
      }

      if (var1.get("launcher_version") != null && !var1.get("launcher_version").isJsonNull() && !var1.get("launcher_version").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `launcher_version` to be a primitive type in the JSON string but got `%s`", var1.get("launcher_version").toString()
            )
         );
      }

      if (var1.get("canary_token") != null && !var1.get("canary_token").isJsonNull() && !var1.get("canary_token").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `canary_token` to be a primitive type in the JSON string but got `%s`", var1.get("canary_token").toString())
         );
      }

      if (var1.get("ichor_modules") != null && !var1.get("ichor_modules").isJsonNull() && !var1.get("ichor_modules").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `ichor_modules` to be an array in the JSON string but got `%s`", var1.get("ichor_modules").toString())
         );
      }

      if (var1.get("gl_extensions") != null && !var1.get("gl_extensions").isJsonNull() && !var1.get("gl_extensions").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `gl_extensions` to be an array in the JSON string but got `%s`", var1.get("gl_extensions").toString())
         );
      }

      if (!var1.get("installed_mods").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `installed_mods` to be an array in the JSON string but got `%s`", var1.get("installed_mods").toString())
         );
      }

      JsonArray var2 = var1.getAsJsonArray("installed_mods");

      for (int var3 = 0; var3 < var2.size(); var3++) {
         MixinHelper14.validateJsonElement(var2.get(var3));
      }

      if (var1.get("wearer_uuid") != null && !var1.get("wearer_uuid").isJsonNull() && !var1.get("wearer_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `wearer_uuid` to be a primitive type in the JSON string but got `%s`", var1.get("wearer_uuid").toString())
         );
      }

      if (var1.get("wearer_cosmetic_ids") != null && !var1.get("wearer_cosmetic_ids").isJsonNull() && !var1.get("wearer_cosmetic_ids").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `wearer_cosmetic_ids` to be an array in the JSON string but got `%s`", var1.get("wearer_cosmetic_ids").toString())
         );
      }

      if (var1.get("wearer_outfit_id") != null && !var1.get("wearer_outfit_id").isJsonNull() && !var1.get("wearer_outfit_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `wearer_outfit_id` to be a primitive type in the JSON string but got `%s`", var1.get("wearer_outfit_id").toString()
            )
         );
      }

      if (var1.get("trigger") != null && !var1.get("trigger").isJsonNull() && !var1.get("trigger").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `trigger` to be a primitive type in the JSON string but got `%s`", var1.get("trigger").toString())
         );
      }

      if (var1.get("geo_location") != null && !var1.get("geo_location").isJsonNull()) {
         MixinHelper7.validateJsonElement(var1.get("geo_location"));
      }
   }

   public static MixinHelper2 method80(String var0) {
      return (MixinHelper2)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper2.class);
   }

   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field54.add("player_uuid");
      field54.add("installation_id");
      field54.add("overwolf_muid");
      field54.add("timestamp");
      field54.add("event_id");
      field54.add("assetserver_session_id");
      field54.add("launch_id");
      field54.add("inbound_location");
      field54.add("location");
      field54.add("minecraft_version");
      field54.add("lunar_client_git_commit");
      field54.add("lunar_client_git_branch");
      field54.add("lunar_client_semver");
      field54.add("lunar_client_ui_git_commit");
      field54.add("lunar_client_ui_git_branch");
      field54.add("operating_system");
      field54.add("operating_system_release");
      field54.add("cpu_architecture");
      field54.add("launcher_version");
      field54.add("canary_token");
      field54.add("ichor_modules");
      field54.add("gl_extensions");
      field54.add("installed_mods");
      field54.add("wearer_uuid");
      field54.add("wearer_cosmetic_ids");
      field54.add("wearer_outfit_id");
      field54.add("trigger");
      field54.add("geo_location");
      field55.add("installation_id");
      field55.add("installed_mods");
   }
}

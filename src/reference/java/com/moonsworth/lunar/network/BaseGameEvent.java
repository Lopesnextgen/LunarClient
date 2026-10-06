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

public class BaseGameEvent implements Serializable {
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
   private BaseGameEventInboundLocation inboundLocation;
   public static final String SERIALIZED_NAME_LOCATION = "location";
   @SerializedName("location")
   private BaseGameEventLocation location;
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
   private List<BaseGameEventInstalledModsInner> installedMods = new ArrayList<>();
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
   private BaseGameEventGeoLocation geoLocation;
   public static HashSet<String> field54 = new HashSet<>();
   public static HashSet<String> field55 = new HashSet<>();

   public BaseGameEvent() {
   }

   public BaseGameEvent playerUuid(String text1) {
      this.playerUuid = text1;
      return this;
   }

   @Nullable
   public String getPlayerUuid() {
      return this.playerUuid;
   }

   public void setPlayerUuid(String text1) {
      this.playerUuid = text1;
   }

   public BaseGameEvent installationId(String text1) {
      this.installationId = text1;
      return this;
   }

   @Nonnull
   public String getInstallationId() {
      return this.installationId;
   }

   public void setInstallationId(String text1) {
      this.installationId = text1;
   }

   public BaseGameEvent overwolfMuid(String text1) {
      this.overwolfMuid = text1;
      return this;
   }

   @Nullable
   public String getOverwolfMuid() {
      return this.overwolfMuid;
   }

   public void setOverwolfMuid(String text1) {
      this.overwolfMuid = text1;
   }

   public BaseGameEvent timestamp(OffsetDateTime offsetdatetime1) {
      this.timestamp = offsetdatetime1;
      return this;
   }

   @Nullable
   public OffsetDateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(OffsetDateTime offsetdatetime1) {
      this.timestamp = offsetdatetime1;
   }

   public BaseGameEvent eventId(String text1) {
      this.eventId = text1;
      return this;
   }

   @Nullable
   public String getEventId() {
      return this.eventId;
   }

   public void setEventId(String text1) {
      this.eventId = text1;
   }

   public BaseGameEvent assetserverSessionId(UUID uuid1) {
      this.assetserverSessionId = uuid1;
      return this;
   }

   @Nullable
   public UUID getAssetserverSessionId() {
      return this.assetserverSessionId;
   }

   public void setAssetserverSessionId(UUID uuid1) {
      this.assetserverSessionId = uuid1;
   }

   public BaseGameEvent launchId(String text1) {
      this.launchId = text1;
      return this;
   }

   @Nullable
   public String getLaunchId() {
      return this.launchId;
   }

   public void setLaunchId(String text1) {
      this.launchId = text1;
   }

   public BaseGameEvent inboundLocation(BaseGameEventInboundLocation mixinhelper91) {
      this.inboundLocation = mixinhelper91;
      return this;
   }

   @Nullable
   public BaseGameEventInboundLocation getInboundLocation() {
      return this.inboundLocation;
   }

   public void setInboundLocation(BaseGameEventInboundLocation mixinhelper91) {
      this.inboundLocation = mixinhelper91;
   }

   public BaseGameEvent location(BaseGameEventLocation mixinhelper101) {
      this.location = mixinhelper101;
      return this;
   }

   @Nullable
   public BaseGameEventLocation getLocation() {
      return this.location;
   }

   public void setLocation(BaseGameEventLocation mixinhelper101) {
      this.location = mixinhelper101;
   }

   public BaseGameEvent minecraftVersion(String text1) {
      this.minecraftVersion = text1;
      return this;
   }

   @Nullable
   public String getMinecraftVersion() {
      return this.minecraftVersion;
   }

   public void setMinecraftVersion(String text1) {
      this.minecraftVersion = text1;
   }

   public BaseGameEvent lunarClientGitCommit(String text1) {
      this.lunarClientGitCommit = text1;
      return this;
   }

   @Nullable
   public String getLunarClientGitCommit() {
      return this.lunarClientGitCommit;
   }

   public void setLunarClientGitCommit(String text1) {
      this.lunarClientGitCommit = text1;
   }

   public BaseGameEvent lunarClientGitBranch(String text1) {
      this.lunarClientGitBranch = text1;
      return this;
   }

   @Nullable
   public String getLunarClientGitBranch() {
      return this.lunarClientGitBranch;
   }

   public void setLunarClientGitBranch(String text1) {
      this.lunarClientGitBranch = text1;
   }

   public BaseGameEvent lunarClientSemver(String text1) {
      this.lunarClientSemver = text1;
      return this;
   }

   @Nullable
   public String getLunarClientSemver() {
      return this.lunarClientSemver;
   }

   public void setLunarClientSemver(String text1) {
      this.lunarClientSemver = text1;
   }

   public BaseGameEvent lunarClientUiGitCommit(String text1) {
      this.lunarClientUiGitCommit = text1;
      return this;
   }

   @Nullable
   public String getLunarClientUiGitCommit() {
      return this.lunarClientUiGitCommit;
   }

   public void setLunarClientUiGitCommit(String text1) {
      this.lunarClientUiGitCommit = text1;
   }

   public BaseGameEvent lunarClientUiGitBranch(String text1) {
      this.lunarClientUiGitBranch = text1;
      return this;
   }

   @Nullable
   public String getLunarClientUiGitBranch() {
      return this.lunarClientUiGitBranch;
   }

   public void setLunarClientUiGitBranch(String text1) {
      this.lunarClientUiGitBranch = text1;
   }

   public BaseGameEvent operatingSystem(String text1) {
      this.operatingSystem = text1;
      return this;
   }

   @Nullable
   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public void setOperatingSystem(String text1) {
      this.operatingSystem = text1;
   }

   public BaseGameEvent operatingSystemRelease(String text1) {
      this.operatingSystemRelease = text1;
      return this;
   }

   @Nullable
   public String getOperatingSystemRelease() {
      return this.operatingSystemRelease;
   }

   public void setOperatingSystemRelease(String text1) {
      this.operatingSystemRelease = text1;
   }

   public BaseGameEvent cpuArchitecture(String text1) {
      this.cpuArchitecture = text1;
      return this;
   }

   @Nullable
   public String getCpuArchitecture() {
      return this.cpuArchitecture;
   }

   public void setCpuArchitecture(String text1) {
      this.cpuArchitecture = text1;
   }

   public BaseGameEvent launcherVersion(String text1) {
      this.launcherVersion = text1;
      return this;
   }

   @Nullable
   public String getLauncherVersion() {
      return this.launcherVersion;
   }

   public void setLauncherVersion(String text1) {
      this.launcherVersion = text1;
   }

   public BaseGameEvent canaryToken(String text1) {
      this.canaryToken = text1;
      return this;
   }

   @Nullable
   public String getCanaryToken() {
      return this.canaryToken;
   }

   public void setCanaryToken(String text1) {
      this.canaryToken = text1;
   }

   public BaseGameEvent ichorModules(List<String> list1) {
      this.ichorModules = list1;
      return this;
   }

   public BaseGameEvent addIchorModulesItem(String text1) {
      if (this.ichorModules == null) {
         this.ichorModules = new ArrayList<>();
      }

      this.ichorModules.add(text1);
      return this;
   }

   @Nullable
   public List<String> getIchorModules() {
      return this.ichorModules;
   }

   public void setIchorModules(List<String> list1) {
      this.ichorModules = list1;
   }

   public BaseGameEvent glExtensions(List<String> list1) {
      this.glExtensions = list1;
      return this;
   }

   public BaseGameEvent addGlExtensionsItem(String text1) {
      if (this.glExtensions == null) {
         this.glExtensions = new ArrayList<>();
      }

      this.glExtensions.add(text1);
      return this;
   }

   @Nullable
   public List<String> getGlExtensions() {
      return this.glExtensions;
   }

   public void setGlExtensions(List<String> list1) {
      this.glExtensions = list1;
   }

   public BaseGameEvent installedMods(List<BaseGameEventInstalledModsInner> list1) {
      this.installedMods = list1;
      return this;
   }

   public BaseGameEvent addInstalledModsItem(BaseGameEventInstalledModsInner mixinhelper141) {
      if (this.installedMods == null) {
         this.installedMods = new ArrayList<>();
      }

      this.installedMods.add(mixinhelper141);
      return this;
   }

   @Nonnull
   public List<BaseGameEventInstalledModsInner> getInstalledMods() {
      return this.installedMods;
   }

   public void setInstalledMods(List<BaseGameEventInstalledModsInner> list1) {
      this.installedMods = list1;
   }

   public BaseGameEvent wearerUuid(String text1) {
      this.wearerUuid = text1;
      return this;
   }

   @Nullable
   public String getWearerUuid() {
      return this.wearerUuid;
   }

   public void setWearerUuid(String text1) {
      this.wearerUuid = text1;
   }

   public BaseGameEvent wearerCosmeticIds(List<BigDecimal> list1) {
      this.wearerCosmeticIds = list1;
      return this;
   }

   public BaseGameEvent addWearerCosmeticIdsItem(BigDecimal bigDecimal) {
      if (this.wearerCosmeticIds == null) {
         this.wearerCosmeticIds = new ArrayList<>();
      }

      this.wearerCosmeticIds.add(bigDecimal);
      return this;
   }

   @Nullable
   public List<BigDecimal> getWearerCosmeticIds() {
      return this.wearerCosmeticIds;
   }

   public void setWearerCosmeticIds(List<BigDecimal> list1) {
      this.wearerCosmeticIds = list1;
   }

   public BaseGameEvent wearerOutfitId(String text1) {
      this.wearerOutfitId = text1;
      return this;
   }

   @Nullable
   public String getWearerOutfitId() {
      return this.wearerOutfitId;
   }

   public void setWearerOutfitId(String text1) {
      this.wearerOutfitId = text1;
   }

   public BaseGameEvent trigger(String text1) {
      this.trigger = text1;
      return this;
   }

   @Nullable
   public String getTrigger() {
      return this.trigger;
   }

   public void setTrigger(String text1) {
      this.trigger = text1;
   }

   public BaseGameEvent geoLocation(BaseGameEventGeoLocation mixinhelper71) {
      this.geoLocation = mixinhelper71;
      return this;
   }

   @Nullable
   public BaseGameEventGeoLocation getGeoLocation() {
      return this.geoLocation;
   }

   public void setGeoLocation(BaseGameEventGeoLocation mixinhelper71) {
      this.geoLocation = mixinhelper71;
   }

   @Override
   public boolean equals(Object obj1) {
      if (this == obj1) {
         return true;
      } else if (obj1 != null && this.getClass() == obj1.getClass()) {
         BaseGameEvent mixinhelper22 = (BaseGameEvent)obj1;
         return Objects.equals(this.playerUuid, mixinhelper22.playerUuid)
            && Objects.equals(this.installationId, mixinhelper22.installationId)
            && Objects.equals(this.overwolfMuid, mixinhelper22.overwolfMuid)
            && Objects.equals(this.timestamp, mixinhelper22.timestamp)
            && Objects.equals(this.eventId, mixinhelper22.eventId)
            && Objects.equals(this.assetserverSessionId, mixinhelper22.assetserverSessionId)
            && Objects.equals(this.launchId, mixinhelper22.launchId)
            && Objects.equals(this.inboundLocation, mixinhelper22.inboundLocation)
            && Objects.equals(this.location, mixinhelper22.location)
            && Objects.equals(this.minecraftVersion, mixinhelper22.minecraftVersion)
            && Objects.equals(this.lunarClientGitCommit, mixinhelper22.lunarClientGitCommit)
            && Objects.equals(this.lunarClientGitBranch, mixinhelper22.lunarClientGitBranch)
            && Objects.equals(this.lunarClientSemver, mixinhelper22.lunarClientSemver)
            && Objects.equals(this.lunarClientUiGitCommit, mixinhelper22.lunarClientUiGitCommit)
            && Objects.equals(this.lunarClientUiGitBranch, mixinhelper22.playerUuid1)
            && Objects.equals(this.operatingSystem, mixinhelper22.operatingSystem)
            && Objects.equals(this.operatingSystemRelease, mixinhelper22.operatingSystemRelease)
            && Objects.equals(this.cpuArchitecture, mixinhelper22.cpuArchitecture)
            && Objects.equals(this.launcherVersion, mixinhelper22.playerUuid6)
            && Objects.equals(this.canaryToken, mixinhelper22.playerUuid8)
            && Objects.equals(this.ichorModules, mixinhelper22.ichorModules)
            && Objects.equals(this.glExtensions, mixinhelper22.glExtensions)
            && Objects.equals(this.installedMods, mixinhelper22.installedMods)
            && Objects.equals(this.wearerUuid, mixinhelper22.wearerUuid)
            && Objects.equals(this.wearerCosmeticIds, mixinhelper22.wearerCosmeticIds)
            && Objects.equals(this.wearerOutfitId, mixinhelper22.wearerOutfitId)
            && Objects.equals(this.trigger, mixinhelper22.installationId1)
            && Objects.equals(this.geoLocation, mixinhelper22.installationId3);
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
      StringBuilder builder1 = new StringBuilder();
      builder1.append("class BaseGameEvent {\n");
      builder1.append("    playerUuid: ").append(this.toIndentedString(this.playerUuid)).append("\n");
      builder1.append("    installationId: ").append(this.toIndentedString(this.installationId)).append("\n");
      builder1.append("    overwolfMuid: ").append(this.toIndentedString(this.overwolfMuid)).append("\n");
      builder1.append("    timestamp: ").append(this.toIndentedString(this.timestamp)).append("\n");
      builder1.append("    eventId: ").append(this.toIndentedString(this.eventId)).append("\n");
      builder1.append("    assetserverSessionId: ").append(this.toIndentedString(this.assetserverSessionId)).append("\n");
      builder1.append("    launchId: ").append(this.toIndentedString(this.launchId)).append("\n");
      builder1.append("    inboundLocation: ").append(this.toIndentedString(this.inboundLocation)).append("\n");
      builder1.append("    location: ").append(this.toIndentedString(this.location)).append("\n");
      builder1.append("    minecraftVersion: ").append(this.toIndentedString(this.minecraftVersion)).append("\n");
      builder1.append("    lunarClientGitCommit: ").append(this.toIndentedString(this.lunarClientGitCommit)).append("\n");
      builder1.append("    lunarClientGitBranch: ").append(this.toIndentedString(this.lunarClientGitBranch)).append("\n");
      builder1.append("    lunarClientSemver: ").append(this.toIndentedString(this.lunarClientSemver)).append("\n");
      builder1.append("    lunarClientUiGitCommit: ").append(this.toIndentedString(this.lunarClientUiGitCommit)).append("\n");
      builder1.append("    lunarClientUiGitBranch: ").append(this.toIndentedString(this.lunarClientUiGitBranch)).append("\n");
      builder1.append("    operatingSystem: ").append(this.toIndentedString(this.operatingSystem)).append("\n");
      builder1.append("    operatingSystemRelease: ").append(this.toIndentedString(this.operatingSystemRelease)).append("\n");
      builder1.append("    cpuArchitecture: ").append(this.toIndentedString(this.cpuArchitecture)).append("\n");
      builder1.append("    launcherVersion: ").append(this.toIndentedString(this.launcherVersion)).append("\n");
      builder1.append("    canaryToken: ").append(this.toIndentedString(this.canaryToken)).append("\n");
      builder1.append("    ichorModules: ").append(this.toIndentedString(this.ichorModules)).append("\n");
      builder1.append("    glExtensions: ").append(this.toIndentedString(this.glExtensions)).append("\n");
      builder1.append("    installedMods: ").append(this.toIndentedString(this.installedMods)).append("\n");
      builder1.append("    wearerUuid: ").append(this.toIndentedString(this.wearerUuid)).append("\n");
      builder1.append("    wearerCosmeticIds: ").append(this.toIndentedString(this.wearerCosmeticIds)).append("\n");
      builder1.append("    wearerOutfitId: ").append(this.toIndentedString(this.wearerOutfitId)).append("\n");
      builder1.append("    trigger: ").append(this.toIndentedString(this.trigger)).append("\n");
      builder1.append("    geoLocation: ").append(this.toIndentedString(this.geoLocation)).append("\n");
      builder1.append("}");
      return builder1.toString();
   }

   private String toIndentedString(Object obj1) {
      return obj1 == null ? "null" : obj1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement element0) {
      if (element0 == null && !field55.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in BaseGameEvent is not found in the empty JSON string", field55.toString())
         );
      }

      JsonObject json1 = element0.getAsJsonObject();
      if (json1.get("player_uuid") != null && !json1.get("player_uuid").isJsonNull() && !json1.get("player_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `player_uuid` to be a primitive type in the JSON string but got `%s`", json1.get("player_uuid").toString())
         );
      }

      if (!json1.get("installation_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `installation_id` to be a primitive type in the JSON string but got `%s`", json1.get("installation_id").toString())
         );
      }

      if (json1.get("overwolf_muid") != null && !json1.get("overwolf_muid").isJsonNull() && !json1.get("overwolf_muid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `overwolf_muid` to be a primitive type in the JSON string but got `%s`", json1.get("overwolf_muid").toString())
         );
      }

      if (json1.get("event_id") != null && !json1.get("event_id").isJsonNull() && !json1.get("event_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `event_id` to be a primitive type in the JSON string but got `%s`", json1.get("event_id").toString())
         );
      }

      if (json1.get("assetserver_session_id") != null
         && !json1.get("assetserver_session_id").isJsonNull()
         && !json1.get("assetserver_session_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `assetserver_session_id` to be a primitive type in the JSON string but got `%s`",
               json1.get("assetserver_session_id").toString()
            )
         );
      }

      if (json1.get("launch_id") != null && !json1.get("launch_id").isJsonNull() && !json1.get("launch_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `launch_id` to be a primitive type in the JSON string but got `%s`", json1.get("launch_id").toString())
         );
      }

      if (json1.get("inbound_location") != null && !json1.get("inbound_location").isJsonNull()) {
         BaseGameEventInboundLocation.validateJsonElement(json1.get("inbound_location"));
      }

      if (json1.get("location") != null && !json1.get("location").isJsonNull()) {
         BaseGameEventLocation.validateJsonElement(json1.get("location"));
      }

      if (json1.get("minecraft_version") != null && !json1.get("minecraft_version").isJsonNull() && !json1.get("minecraft_version").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `minecraft_version` to be a primitive type in the JSON string but got `%s`", json1.get("minecraft_version").toString()
            )
         );
      }

      if (json1.get("lunar_client_git_commit") != null
         && !json1.get("lunar_client_git_commit").isJsonNull()
         && !json1.get("lunar_client_git_commit").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_git_commit` to be a primitive type in the JSON string but got `%s`",
               json1.get("lunar_client_git_commit").toString()
            )
         );
      }

      if (json1.get("lunar_client_git_branch") != null
         && !json1.get("lunar_client_git_branch").isJsonNull()
         && !json1.get("lunar_client_git_branch").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_git_branch` to be a primitive type in the JSON string but got `%s`",
               json1.get("lunar_client_git_branch").toString()
            )
         );
      }

      if (json1.get("lunar_client_semver") != null && !json1.get("lunar_client_semver").isJsonNull() && !json1.get("lunar_client_semver").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_semver` to be a primitive type in the JSON string but got `%s`", json1.get("lunar_client_semver").toString()
            )
         );
      }

      if (json1.get("lunar_client_ui_git_commit") != null
         && !json1.get("lunar_client_ui_git_commit").isJsonNull()
         && !json1.get("lunar_client_ui_git_commit").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_ui_git_commit` to be a primitive type in the JSON string but got `%s`",
               json1.get("lunar_client_ui_git_commit").toString()
            )
         );
      }

      if (json1.get("lunar_client_ui_git_branch") != null
         && !json1.get("lunar_client_ui_git_branch").isJsonNull()
         && !json1.get("lunar_client_ui_git_branch").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `lunar_client_ui_git_branch` to be a primitive type in the JSON string but got `%s`",
               json1.get("lunar_client_ui_git_branch").toString()
            )
         );
      }

      if (json1.get("operating_system") != null && !json1.get("operating_system").isJsonNull() && !json1.get("operating_system").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `operating_system` to be a primitive type in the JSON string but got `%s`", json1.get("operating_system").toString()
            )
         );
      }

      if (json1.get("operating_system_release") != null
         && !json1.get("operating_system_release").isJsonNull()
         && !json1.get("operating_system_release").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `operating_system_release` to be a primitive type in the JSON string but got `%s`",
               json1.get("operating_system_release").toString()
            )
         );
      }

      if (json1.get("cpu_architecture") != null && !json1.get("cpu_architecture").isJsonNull() && !json1.get("cpu_architecture").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `cpu_architecture` to be a primitive type in the JSON string but got `%s`", json1.get("cpu_architecture").toString()
            )
         );
      }

      if (json1.get("launcher_version") != null && !json1.get("launcher_version").isJsonNull() && !json1.get("launcher_version").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `launcher_version` to be a primitive type in the JSON string but got `%s`", json1.get("launcher_version").toString()
            )
         );
      }

      if (json1.get("canary_token") != null && !json1.get("canary_token").isJsonNull() && !json1.get("canary_token").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `canary_token` to be a primitive type in the JSON string but got `%s`", json1.get("canary_token").toString())
         );
      }

      if (json1.get("ichor_modules") != null && !json1.get("ichor_modules").isJsonNull() && !json1.get("ichor_modules").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `ichor_modules` to be an array in the JSON string but got `%s`", json1.get("ichor_modules").toString())
         );
      }

      if (json1.get("gl_extensions") != null && !json1.get("gl_extensions").isJsonNull() && !json1.get("gl_extensions").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `gl_extensions` to be an array in the JSON string but got `%s`", json1.get("gl_extensions").toString())
         );
      }

      if (!json1.get("installed_mods").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `installed_mods` to be an array in the JSON string but got `%s`", json1.get("installed_mods").toString())
         );
      }

      JsonArray array2 = json1.getAsJsonArray("installed_mods");

      for (int index3 = 0; index3 < array2.size(); index3++) {
         BaseGameEventInstalledModsInner.validateJsonElement(array2.get(index3));
      }

      if (json1.get("wearer_uuid") != null && !json1.get("wearer_uuid").isJsonNull() && !json1.get("wearer_uuid").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `wearer_uuid` to be a primitive type in the JSON string but got `%s`", json1.get("wearer_uuid").toString())
         );
      }

      if (json1.get("wearer_cosmetic_ids") != null && !json1.get("wearer_cosmetic_ids").isJsonNull() && !json1.get("wearer_cosmetic_ids").isJsonArray()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `wearer_cosmetic_ids` to be an array in the JSON string but got `%s`", json1.get("wearer_cosmetic_ids").toString())
         );
      }

      if (json1.get("wearer_outfit_id") != null && !json1.get("wearer_outfit_id").isJsonNull() && !json1.get("wearer_outfit_id").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `wearer_outfit_id` to be a primitive type in the JSON string but got `%s`", json1.get("wearer_outfit_id").toString()
            )
         );
      }

      if (json1.get("trigger") != null && !json1.get("trigger").isJsonNull() && !json1.get("trigger").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `trigger` to be a primitive type in the JSON string but got `%s`", json1.get("trigger").toString())
         );
      }

      if (json1.get("geo_location") != null && !json1.get("geo_location").isJsonNull()) {
         BaseGameEventGeoLocation.validateJsonElement(json1.get("geo_location"));
      }
   }

   public static BaseGameEvent method80(String text) {
      return (BaseGameEvent)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(text, BaseGameEvent.class);
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

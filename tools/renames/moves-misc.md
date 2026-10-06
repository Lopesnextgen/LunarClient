# Moves: misc clusters (304 classes)

1. calculator/ is the i18n engine (replacement parts + variables) → client.translation; only SharedInfo needed a rename (SharedTranslations, shared_info keys).
2. click/ splits two ways: BugReportCategory + EmptyScreen → client.gui, the 7 chest/ notice models → client.notification; all keep names.
3. feature/ is cosmetic models → client.cosmetics (keep names); framework/feature/pkg is 3D-skin layers → client.cosmetics.skin (Pkg→SkinOverlayDetector transparency probe, Pkg2_2→HatLayerRenderer, Pkg3_2→BodyOverlayLayerRenderer, Pkg3$Data/Type/Type2→SkinPartEntry/OverlayGarment/BodyPartSpec, mixin Pkg/PkgType→CubeListBuilder/CubeDirection).
4. pkg/ is server addressing → client.server (Pkg→ResolvedServerAddress InetSocketAddress iface, Pkg2→ServerBlocklist incl. nested MojangBlocklist payload, Pkg3→UnresolvedServerAddress HostAndPort+IDN, Pkg4→ServerAddressResolver fun-iface, Pkg5→ServerSrvLookup, Pkg6→ServerAddressPipeline); keystrokes/ joins it (Keystrokes→PluginChannelRegistry lunar:apollo/… channels, Highlight3Iterator→ServerBrandWatcher, other two keep names).
5. killsounds/ is config migrations → client.config.migration (23 renames incl. FogScaleMigration removing useMinecraftScale, BossbarPositionMigration y 40→12, mixin Killsounds→VanillaOptionsFile optionsLC.txt); the rest were already *Migration, move-only.
6. lighting/ is the settings framework → client.option (10 root + 9 nameplate renames: NumberRangeBuilder/SteppedNumberRangeBuilder, SettingsGroup/ParentFactory, OptionTraits/NumberRule/AbstractNumberRule/DoubleNumberRule, ListenerSet/FeatureLinkRef/DisplaySpec, SettingsSection/Builder/Impl); remaining ~36 keep names.
7. markers/ is the WebOSR driver → client.driver mirroring subpackages with mixin→core (20 renames: PhosphorIcon PI_* icons, DriverOverlay/RouteRegistry, HologramSubject/Preset, StorePrice/Entry, LockerSection/Context, MigrationContext, ProfileImportProvider, DriverViewport, DualMarkerScreen, MinimapViewContext, WaypointContext, Cosmetic/EmoteHologram); Driver* vocabulary kept per in-tree consistency.
8. client/lotusfish is Lunar game-transform shims → new client.transform (all 6 renamed: LwjglRelocationTransform, RandomRewriteTransform, NewInstanceFactoryTransform, PreMixinTransform, LunarTransformLoader, FunctionProviderFactory); util/lotusfish/mixin is Ogg/CRC audio → new client.audio.ogg (Lotusfish→Crc32).
9. horsestats/ is mod profiles → client.profile (+client.profile.importer for Badlion/Feather/Converted/Locator): ModProfile/ModProfileManager/ProfileData/HudPosition/ProfileColor/ParsedProfileColor\n(all source-verified: icons/profiles/, activeProfile, BOTTOM_* anchors, CHROMA, r/g/b string); bridge/horsestats stays in place per precedent (BlockPosBridge/HoverEventAction/DirectionAxis/AxisPlane), BridgeExtension relocates to bridge root; Horsestats9/17/24/27 excluded as empty per audit02.md.
10. All 304 new simple names grep-verified unique tree-wide (ServerAddress was taken twice → UnresolvedServerAddress; client/lotusfish Lotusfish collided → FunctionProviderFactory); no trailing digits (Crc32 keeps 32 as the algorithm name); map is 304×5-col, applier-ready.

## Evidence table

| old | new package | new name | evidence |
|---|---|---|---|
| `client.calculator.CachedReplacement` | `client.translation` | `CachedReplacement` | translation replacement part; name accurate, move-only |
| `client.calculator.ClientLanguage` | `client.translation` | `ClientLanguage` | translation replacement part; name accurate, move-only |
| `client.calculator.ConstantReplacement` | `client.translation` | `ConstantReplacement` | translation replacement part; name accurate, move-only |
| `client.calculator.SharedInfo` | `client.translation` | `SharedTranslations` | "shared_info" + takeMeThere/blocks keys; the shared translation bundle |
| `client.calculator.StringArrayReplacement` | `client.translation` | `StringArrayReplacement` | translation replacement part; name accurate, move-only |
| `client.calculator.SupplierReplacement` | `client.translation` | `SupplierReplacement` | translation replacement part; name accurate, move-only |
| `client.calculator.Translatable` | `client.translation` | `Translatable` | translation replacement part; name accurate, move-only |
| `client.calculator.TranslationVariable` | `client.translation` | `TranslationVariable` | translation replacement part; name accurate, move-only |
| `client.click.BugReportCategory` | `client.gui` | `BugReportCategory` | misc GUI model; name accurate, move-only |
| `client.click.EmptyScreen` | `client.gui` | `EmptyScreen` | misc GUI model; name accurate, move-only |
| `client.click.chest.AnchorFunction` | `client.notification` | `AnchorFunction` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.FriendNotification` | `client.notification` | `FriendNotification` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.FriendNotificationLong` | `client.notification` | `FriendNotificationLong` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.HostedWorldNotification` | `client.notification` | `HostedWorldNotification` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.Notification` | `client.notification` | `Notification` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.NotificationAnchor` | `client.notification` | `NotificationAnchor` | player/hosted-world notice model; name accurate, move-only |
| `client.click.chest.NotificationType` | `client.notification` | `NotificationType` | player/hosted-world notice model; name accurate, move-only |
| `client.feature.BodywearCosmetic` | `client.cosmetics` | `BodywearCosmetic` | cosmetic model (category/slot/settings); name accurate, move-only |
| `client.feature.Cosmetic` | `client.cosmetics` | `Cosmetic` | cosmetic model (category/slot/settings); name accurate, move-only |
| `client.feature.CosmeticCategory` | `client.cosmetics` | `CosmeticCategory` | cosmetic model (category/slot/settings); name accurate, move-only |
| `client.feature.CosmeticSettings` | `client.cosmetics` | `CosmeticSettings` | cosmetic model (category/slot/settings); name accurate, move-only |
| `client.feature.CosmeticSlot` | `client.cosmetics` | `CosmeticSlot` | cosmetic model (category/slot/settings); name accurate, move-only |
| `client.framework.feature.pkg.LegacySkinLayerRenderer` | `client.cosmetics.skin` | `LegacySkinLayerRenderer` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.Pkg` | `client.cosmetics.skin` | `SkinOverlayDetector` | 64x64 skin transparency probe (region x32-64/y0-16) over BridgeExtension222 |
| `client.framework.feature.pkg.Pkg2_2` | `client.cosmetics.skin` | `HatLayerRenderer` | LayerRendererBridge gated on bridge$bipedHead visible + bridge$showHat + skull/limb checks |
| `client.framework.feature.pkg.Pkg3$Data` | `client.cosmetics.skin` | `SkinPartEntry` | 6-field mesh + garment + body-spec + part/visible-supplier bundle consumed by body-overlay renderer |
| `client.framework.feature.pkg.Pkg3$Type` | `client.cosmetics.skin` | `OverlayGarment` | LEFT/RIGHT_PANTS_LEG, LEFT/RIGHT_SLEEVE, JACKET second-layer garment tags |
| `client.framework.feature.pkg.Pkg3$Type2` | `client.cosmetics.skin` | `BodyPartSpec` | HEAD/BODY/LEGS/ARMS/ARMS_SLIM + voxel dims + yOffsetMagicValue |
| `client.framework.feature.pkg.Pkg3_2` | `client.cosmetics.skin` | `BodyOverlayLayerRenderer` | LayerRendererBridge building pants/sleeve/jacket Pkg3$Data entries from method34 |
| `client.framework.feature.pkg.SkinDataCache` | `client.cosmetics.skin` | `SkinDataCache` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.SkinLayerCache` | `client.cosmetics.skin` | `SkinLayerCache` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.SkinLayerFactory` | `client.cosmetics.skin` | `SkinLayerFactory` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.SkinLayerRenderer` | `client.cosmetics.skin` | `SkinLayerRenderer` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.SkullSkinLayerRenderer` | `client.cosmetics.skin` | `SkullSkinLayerRenderer` | skin-layer cache/renderer/factory; name accurate, move-only |
| `client.framework.feature.pkg.mixin.CustomizableCube` | `client.cosmetics.skin` | `CustomizableCube` | skin-cube helper; name accurate, move-only |
| `client.framework.feature.pkg.mixin.Pkg` | `client.cosmetics.skin` | `CubeListBuilder` | CustomizableCube list + tex-offset builder (method1/method2/method5 chaining) |
| `client.framework.feature.pkg.mixin.PkgType` | `client.cosmetics.skin` | `CubeDirection` | DOWN/UP/NORTH/SOUTH/WEST/EAST + axis/step getters; cube-face enum |
| `client.framework.feature.pkg.mixin.SolidPixelWrapper` | `client.cosmetics.skin` | `SolidPixelWrapper` | skin-cube helper; name accurate, move-only |
| `client.horsestats.BadlionProfileConfig` | `client.profile.importer` | `BadlionProfileConfig` | profile importer piece; name accurate, move-only |
| `client.horsestats.BadlionProfileConverter` | `client.profile.importer` | `BadlionProfileConverter` | profile importer piece; name accurate, move-only |
| `client.horsestats.BadlionProfileImporter` | `client.profile.importer` | `BadlionProfileImporter` | profile importer piece; name accurate, move-only |
| `client.horsestats.ConvertedProfile` | `client.profile.importer` | `ConvertedProfile` | profile importer piece; name accurate, move-only |
| `client.horsestats.ExternalProfileLocator` | `client.profile.importer` | `ExternalProfileLocator` | profile importer piece; name accurate, move-only |
| `client.horsestats.FogIterator` | `client.profile` | `ModProfileManager` | activeProfile/active + /assets/profiles/ + controls.json profile manager |
| `client.horsestats.Horsestats$Data` | `client.profile` | `ProfileData` | enabled/value profile-fields bundle |
| `client.horsestats.Horsestats$Data2` | `client.profile` | `HudPosition` | BOTTOM_CENTER/... anchor constants + computed/height HUD position |
| `client.horsestats.Horsestats` | `client.profile` | `ModProfile` | mod profile model; icons/profiles/ + ".png" profile icons |
| `client.horsestats.Horsestats2_2` | `client.profile` | `ProfileColor` | alpha/blue/CHROMA/color/green/mode profile color |
| `client.horsestats.mixin.FeatherConvertedProfile` | `client.profile.importer` | `FeatherConvertedProfile` | feather profile importer piece; name accurate, move-only |
| `client.horsestats.mixin.FeatherProfileConfig` | `client.profile.importer` | `FeatherProfileConfig` | feather profile importer piece; name accurate, move-only |
| `client.horsestats.mixin.FeatherProfileConverter` | `client.profile.importer` | `FeatherProfileConverter` | feather profile importer piece; name accurate, move-only |
| `client.horsestats.mixin.FeatherProfileImporter` | `client.profile.importer` | `FeatherProfileImporter` | feather profile importer piece; name accurate, move-only |
| `client.horsestats.mixin.Horsestats` | `client.profile.importer` | `ParsedProfileColor` | parses "r/g/b[/a]" profile-color string into packed RGBA int |
| `client.keystrokes.Highlight3Iterator` | `client.server` | `ServerBrandWatcher` | handles EventServerChange/EventServerBrand/EventDisconnect; brand holder over absent KeystrokesType |
| `client.keystrokes.Keystrokes` | `client.server` | `PluginChannelRegistry` | channel lists lunar:apollo/apollo:json/lunarclient:pm/badl ion:timers + worldedit:cui->worldeditcui |
| `client.keystrokes.PinnedServer` | `client.server` | `PinnedServer` | server icon/pinned entry; name accurate, move-only |
| `client.keystrokes.ServerIconEntry` | `client.server` | `ServerIconEntry` | server icon/pinned entry; name accurate, move-only |
| `client.killsounds.AutoTextActionsMigration` | `client.config.migration` | `AutoTextActionsMigration` | already-named *Migration; move-only |
| `client.killsounds.BlockOutlineMigration` | `client.config.migration` | `BlockOutlineMigration` | already-named *Migration; move-only |
| `client.killsounds.BossbarColorMigration` | `client.config.migration` | `BossbarColorMigration` | already-named *Migration; move-only |
| `client.killsounds.ConfigEntryMigration` | `client.config.migration` | `ConfigEntryMigration` | already-named *Migration; move-only |
| `client.killsounds.ConfigMigrator` | `client.config.migration` | `ConfigMigrator` | already-named *Migration; move-only |
| `client.killsounds.CrosshairF5Migration` | `client.config.migration` | `CrosshairF5Migration` | already-named *Migration; move-only |
| `client.killsounds.HeightLimitMigration` | `client.config.migration` | `HeightLimitMigration` | already-named *Migration; move-only |
| `client.killsounds.HudOptionMigration` | `client.config.migration` | `HudOptionMigration` | already-named *Migration; move-only |
| `client.killsounds.Killsounds` | `client.config.migration` | `ConfigIdResolver` | root id-resolution entry of the config-migration cluster |
| `client.killsounds.Killsounds15Impl` | `client.config.migration` | `CosmeticIdMigration` | per-mod cosmetic id migration root of the 15-family |
| `client.killsounds.Killsounds15Impl10` | `client.config.migration` | `CrosshairOutlineMigration` | crosshair-outline per-mod migration |
| `client.killsounds.Killsounds15Impl2` | `client.config.migration` | `OverlayEnabledMigration` | overlay-enabled per-mod migration |
| `client.killsounds.Killsounds15Impl3` | `client.config.migration` | `TimeChangerMigration` | time-changer per-mod migration |
| `client.killsounds.Killsounds15Impl4` | `client.config.migration` | `BossbarPositionMigration` | bossbar y 40->12 per-mod migration |
| `client.killsounds.Killsounds15Impl5` | `client.config.migration` | `SkyblockBossMigration` | skyblock bossbar per-mod migration |
| `client.killsounds.Killsounds15Impl6` | `client.config.migration` | `BossbarOffsetMigration` | bossbar y 12->1 per-mod migration |
| `client.killsounds.Killsounds15Impl7` | `client.config.migration` | `FullBrightMigration` | fullbright per-mod migration |
| `client.killsounds.Killsounds15Impl8` | `client.config.migration` | `ParticleQualityMigration` | particle-quality per-mod migration |
| `client.killsounds.Killsounds15Impl9` | `client.config.migration` | `CoordinatesColorMigration` | coordinates-color per-mod migration |
| `client.killsounds.Killsounds15Iterator` | `client.config.migration` | `UhcOverlayMigration` | UHC overlay migration |
| `client.killsounds.Killsounds15Iterator2` | `client.config.migration` | `HeldItemScaleMigration` | held-item scale migration |
| `client.killsounds.Killsounds15Iterator4` | `client.config.migration` | `WaypointRenderMigration` | waypoint-render migration |
| `client.killsounds.Killsounds15Iterator5` | `client.config.migration` | `MultiFeatureMigration` | multi-feature migration |
| `client.killsounds.Killsounds15Iterator6` | `client.config.migration` | `OverlayCleanupMigration` | overlay-cleanup migration |
| `client.killsounds.Killsounds2_2` | `client.config.migration` | `BedWarsHeightLimitMigration` | BedWars height-limit migration |
| `client.killsounds.Killsounds3_2` | `client.config.migration` | `ConfigMigration` | core config-migration driver |
| `client.killsounds.Killsounds4_2` | `client.config.migration` | `FogScaleMigration` | removes useMinecraftScale |
| `client.killsounds.KillsoundsHandler` | `client.config.migration` | `VersionedIdResolver` | versioned id-resolution handler |
| `client.killsounds.ParticleChangerMigration` | `client.config.migration` | `ParticleChangerMigration` | already-named *Migration; move-only |
| `client.killsounds.PingEntryMigration` | `client.config.migration` | `PingEntryMigration` | already-named *Migration; move-only |
| `client.killsounds.PingMigration` | `client.config.migration` | `PingMigration` | already-named *Migration; move-only |
| `client.killsounds.PotionEffectsMigration` | `client.config.migration` | `PotionEffectsMigration` | already-named *Migration; move-only |
| `client.killsounds.SkyblockFloorFourMigration` | `client.config.migration` | `SkyblockFloorFourMigration` | already-named *Migration; move-only |
| `client.killsounds.SnaplookMigration` | `client.config.migration` | `SnaplookMigration` | already-named *Migration; move-only |
| `client.killsounds.StopwatchMigration` | `client.config.migration` | `StopwatchMigration` | already-named *Migration; move-only |
| `client.killsounds.mixin.FovOptionMigration` | `client.config.migration` | `FovOptionMigration` | already-named option migration; move-only |
| `client.killsounds.mixin.KeyCodeOptionMigration` | `client.config.migration` | `KeyCodeOptionMigration` | already-named option migration; move-only |
| `client.killsounds.mixin.KeyNameOptionMigration` | `client.config.migration` | `KeyNameOptionMigration` | already-named option migration; move-only |
| `client.killsounds.mixin.Killsounds` | `client.config.migration` | `VanillaOptionsFile` | reads vanilla options file optionsLC.txt |
| `client.killsounds.mixin.Killsounds6Impl` | `client.config.migration` | `TypeCoercionMigration` | option type-coercion migration |
| `client.killsounds.mixin.OptionAccessor` | `client.config.migration` | `MigratedOptionReader` | reader over migrated options |
| `client.killsounds.mixin.OptionMigration` | `client.config.migration` | `OptionMigration` | already-named option migration; move-only |
| `client.lighting.AbstractOptionBuilder` | `client.option` | `AbstractOptionBuilder` | already-named option/settings type; move-only |
| `client.lighting.BooleanOption` | `client.option` | `BooleanOption` | already-named option/settings type; move-only |
| `client.lighting.ConditionalOption` | `client.option` | `ConditionalOption` | already-named option/settings type; move-only |
| `client.lighting.DevOptionBaker` | `client.option` | `DevOptionBaker` | already-named option/settings type; move-only |
| `client.lighting.JsonConfigurable` | `client.option` | `JsonConfigurable` | already-named option/settings type; move-only |
| `client.lighting.KeyBind` | `client.option` | `KeyBind` | already-named option/settings type; move-only |
| `client.lighting.Lighting$Extension` | `client.option` | `NumberRangeBuilder` | numeric-range builder |
| `client.lighting.Lighting$Extension2` | `client.option` | `SteppedNumberRangeBuilder` | stepped numeric-range builder |
| `client.lighting.Lighting2_2` | `client.option` | `OptionBakerFactory` | option-baker factory |
| `client.lighting.LightingBase` | `client.option` | `DefaultedOptionBuilder` | defaulted option builder root |
| `client.lighting.LightingException` | `client.option` | `OptionException` | option-framework exception |
| `client.lighting.LightingExtension2$Extension` | `client.option` | `SettingsGroupFactory` | settings-group factory |
| `client.lighting.LightingExtension2$Extension2` | `client.option` | `SettingsParentFactory` | settings-parent factory |
| `client.lighting.LightingHandler` | `client.option` | `BakedOptionNode` | baked option node |
| `client.lighting.LightingIterator` | `client.option` | `PruningOptionBaker` | pruning option baker |
| `client.lighting.LightingType` | `client.option` | `OptionFlag` | option flag enum |
| `client.lighting.OptionBaker` | `client.option` | `OptionBaker` | already-named option/settings type; move-only |
| `client.lighting.OptionCategory` | `client.option` | `OptionCategory` | already-named option/settings type; move-only |
| `client.lighting.OptionCombiner` | `client.option` | `OptionCombiner` | already-named option/settings type; move-only |
| `client.lighting.OptionGroup` | `client.option` | `OptionGroup` | already-named option/settings type; move-only |
| `client.lighting.OptionProvider` | `client.option` | `OptionProvider` | already-named option/settings type; move-only |
| `client.lighting.OptionTreeNode` | `client.option` | `OptionTreeNode` | already-named option/settings type; move-only |
| `client.lighting.RootSettingsBuilder` | `client.option` | `RootSettingsBuilder` | already-named option/settings type; move-only |
| `client.lighting.SettingsBuilder` | `client.option` | `SettingsBuilder` | already-named option/settings type; move-only |
| `client.lighting.SettingsNode` | `client.option` | `SettingsNode` | already-named option/settings type; move-only |
| `client.lighting.SettingsRegistrant` | `client.option` | `SettingsRegistrant` | already-named option/settings type; move-only |
| `client.lighting.SettingsTreeBuilder` | `client.option` | `SettingsTreeBuilder` | already-named option/settings type; move-only |
| `client.lighting.SettingsTreeMapper` | `client.option` | `SettingsTreeMapper` | already-named option/settings type; move-only |
| `client.lighting.TriState` | `client.option` | `TriState` | already-named option/settings type; move-only |
| `client.lighting.mixin.CrosshairDrawOption` | `client.option` | `CrosshairDrawOption` | already-named option type; move-only |
| `client.lighting.nameplate.AlertExtension` | `client.option` | `AlertExtension` | already-named option/display type; move-only |
| `client.lighting.nameplate.GuiExtension` | `client.option` | `GuiExtension` | already-named option/display type; move-only |
| `client.lighting.nameplate.Nameplate` | `client.option` | `OptionTraits` | option traits model |
| `client.lighting.nameplate.Nameplate2Task` | `client.option` | `ListenerSet` | update-listener set |
| `client.lighting.nameplate.Nameplate3Handler` | `client.option` | `FeatureLinkRef` | feature-link reference |
| `client.lighting.nameplate.Nameplate4Task` | `client.option` | `DisplaySpec` | display spec |
| `client.lighting.nameplate.OptionChildren` | `client.option` | `OptionChildren` | already-named option/display type; move-only |
| `client.lighting.nameplate.OptionDisplay` | `client.option` | `OptionDisplay` | already-named option/display type; move-only |
| `client.lighting.nameplate.OptionFeatureLink` | `client.option` | `OptionFeatureLink` | already-named option/display type; move-only |
| `client.lighting.nameplate.OptionJsonProvider` | `client.option` | `OptionJsonProvider` | already-named option/display type; move-only |
| `client.lighting.nameplate.OptionUpdateListeners` | `client.option` | `OptionUpdateListeners` | already-named option/display type; move-only |
| `client.lighting.nameplate.ThreadModuleDump43Extension` | `client.option` | `SettingsSectionBuilder` | settings-section builder |
| `client.lighting.nameplate.ThreadModuleDump43Extension2` | `client.option` | `SettingsSection` | settings section |
| `client.lighting.nameplate.ThreadModuleDump43Extension22` | `client.option` | `SettingsSectionImpl` | settings-section impl |
| `client.lighting.nameplate.mixin.ByteNumberRange` | `client.option` | `ByteNumberRange` | already-named numeric-range rule; move-only |
| `client.lighting.nameplate.mixin.FloatNumberRange` | `client.option` | `FloatNumberRange` | already-named numeric-range rule; move-only |
| `client.lighting.nameplate.mixin.IntegerNumberRange` | `client.option` | `IntegerNumberRange` | already-named numeric-range rule; move-only |
| `client.lighting.nameplate.mixin.LongNumberRange` | `client.option` | `LongNumberRange` | already-named numeric-range rule; move-only |
| `client.lighting.nameplate.mixin.Nameplate` | `client.option` | `NumberRule` | numeric rule over NameplateHandler |
| `client.lighting.nameplate.mixin.NameplateHandler` | `client.option` | `AbstractNumberRule` | abstract numeric-rule base |
| `client.lighting.nameplate.mixin.NameplateImpl` | `client.option` | `DoubleNumberRule` | Double rule; method4() returns "Double" |
| `client.lighting.nameplate.mixin.ShortNumberRange` | `client.option` | `ShortNumberRange` | already-named numeric-range rule; move-only |
| `client.lotusfish.Ichor2Handler` | `client.transform` | `PreMixinTransform` | PRE_MIXIN stage forwarding ClassNode to absent Ichor5Iterator |
| `client.lotusfish.Ichor2Handler22` | `client.transform` | `NewInstanceFactoryTransform` | PRE_META_MIXIN synthesizing static newInstance factories per <init> |
| `client.lotusfish.Ichor2Impl` | `client.transform` | `LwjglRelocationTransform` | POST_MIXIN SimpleRemapper org/lwjgl BufferUtils->actually3 + nanovg/tinyfd/stb/system/opus prefixes |
| `client.lotusfish.Ichor2Iterator2` | `client.transform` | `RandomRewriteTransform` | FINAL over net/minecraft/client: Math.random->ThreadLocalRandom.nextDouble, new Random()->RandomImpl |
| `client.lotusfish.Ichor6Impl` | `client.transform` | `LunarTransformLoader` | Ichor6 "lunar" loader returning Ichor5Iterator |
| `client.lotusfish.Lotusfish` | `client.transform` | `FunctionProviderFactory` | @KeepName factory whose newFunctionProvider builds absent FunctionProvider |
| `client.markers.Bridge7Handler` | `client.driver` | `DualMarkerScreen` | Bridge7_8 impl fanning method1/2/3 to two delegates; flag swaps zero-position Markers.Data4 (Data4 absent) |
| `client.markers.ClipboardHandler` | `client.driver` | `ClipboardHandler` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverBridge` | `client.driver` | `DriverBridge` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverComponent` | `client.driver` | `DriverComponent` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverContext` | `client.driver` | `DriverContext` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverElement` | `client.driver` | `DriverElement` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverGuiExtension` | `client.driver` | `DriverGuiExtension` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverScreen` | `client.driver` | `DriverScreen` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverSettingExtension` | `client.driver` | `DriverSettingExtension` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.DriverViewContext` | `client.driver` | `DriverViewContext` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.FunctionBusImpl` | `client.driver` | `FunctionBusImpl` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.GuiExtension` | `client.driver` | `ProfileImportProvider` | DriverGuiExtension whose provide() returns feather/badlion file arrays + maxImports |
| `client.markers.Highlight3Iterator` | `client.driver` | `DriverViewport` | abstract EventBusAccess+Translatable view base: width/height/framebuffer + DriverComponent list; webosr debug flag |
| `client.markers.Markers$Data` | `client.driver` | `ComponentStyleData` | component style data bundle |
| `client.markers.Markers2Handler` | `client.driver` | `DriverOverlayRegistry` | driver overlay registry |
| `client.markers.Markers2Handler2` | `client.driver` | `DriverRouteRegistry` | driver route registry |
| `client.markers.MarkersImpl` | `client.driver` | `MigrationContext` | DriverContext + migration/featherDetected/badlionDetected JSON |
| `client.markers.MarkersType` | `client.driver` | `PhosphorIcon` | PI_*_SOLID phosphor-icon constants |
| `client.markers.TextureHandler` | `client.driver` | `TextureHandler` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.VanillaHomeContext` | `client.driver` | `VanillaHomeContext` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.WebOsrNative` | `client.driver` | `WebOsrNative` | driver-framework type, in-tree vocabulary; name accurate, move-only |
| `client.markers.gui.AbstractDataProvider` | `client.driver.gui` | `AbstractDataProvider` | driver GUI data provider; name accurate, move-only |
| `client.markers.gui.AnalyticsDataProvider` | `client.driver.gui` | `AnalyticsDataProvider` | driver GUI data provider; name accurate, move-only |
| `client.markers.gui.FeatureDataProvider` | `client.driver.gui` | `FeatureDataProvider` | driver GUI data provider; name accurate, move-only |
| `client.markers.gui.Gui` | `client.driver.gui` | `Gui` | driver GUI data provider; name accurate, move-only |
| `client.markers.gui.GuiExtension` | `client.driver.gui` | `GuiExtension` | driver GUI data provider; name accurate, move-only |
| `client.markers.gui.MarkersDataProvider` | `client.driver.gui` | `MarkersDataProvider` | driver GUI data provider; name accurate, move-only |
| `client.markers.holograms.ColorsaturationExtension` | `client.driver.holograms` | `ColorsaturationExtension` | driver hologram model; name accurate, move-only |
| `client.markers.holograms.Holograms` | `client.driver.holograms` | `Holograms` | driver hologram model; name accurate, move-only |
| `client.markers.holograms.Holograms2Impl` | `client.driver.holograms` | `CosmeticHologram` | extends absent Holograms2 base; cosmeticId/metadata/spin JSON |
| `client.markers.holograms.HologramsIterator` | `client.driver.holograms` | `HologramsIterator` | driver hologram model; name accurate, move-only |
| `client.markers.holograms.HoverModel` | `client.driver.holograms` | `HoverModel` | driver hologram model; name accurate, move-only |
| `client.markers.holograms.MarkerModelRenderer` | `client.driver.holograms` | `MarkerModelRenderer` | driver hologram model; name accurate, move-only |
| `client.markers.mixin.MarkerIconRenderer` | `client.driver.core` | `MarkerIconRenderer` | driver core type; name accurate, move-only |
| `client.markers.mixin.Markers` | `client.driver.core` | `DriverView` | driver view model |
| `client.markers.mixin.Markers3Impl` | `client.driver.core` | `UltralightConfigFactory` | ultralight config factory |
| `client.markers.mixin.MarkersType` | `client.driver.core` | `DriverFieldType` | driver field-type enum |
| `client.markers.mixin.gui.Annotation` | `client.driver.core.gui` | `Annotation` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.Gui` | `client.driver.core.gui` | `Gui` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.Gui2Task` | `client.driver.core.gui` | `Gui2Task` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.GuiExtension` | `client.driver.core.gui` | `GuiExtension` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.JsonProvider` | `client.driver.core.gui` | `JsonProvider` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.JsonSetSerializer` | `client.driver.core.gui` | `JsonSetSerializer` | driver core GUI bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.AccountBridge` | `client.driver.core.gui.mixin` | `AccountBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.AlertBridge` | `client.driver.core.gui.mixin` | `AlertBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.BadgeBridge` | `client.driver.core.gui.mixin` | `BadgeBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.ColorBridge` | `client.driver.core.gui.mixin` | `ColorBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.CosmeticPreviewBridge` | `client.driver.core.gui.mixin` | `CosmeticPreviewBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.Gui` | `client.driver.core.gui.mixin` | `Gui` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.GuiExtension` | `client.driver.core.gui.mixin` | `GuiExtension` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.HomeButtonBridge` | `client.driver.core.gui.mixin` | `HomeButtonBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.HomeNavigationBridge` | `client.driver.core.gui.mixin` | `HomeNavigationBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.HomeRadioBridge` | `client.driver.core.gui.mixin` | `HomeRadioBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.HomeThemeBridge` | `client.driver.core.gui.mixin` | `HomeThemeBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.HostedWorldBridge` | `client.driver.core.gui.mixin` | `HostedWorldBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.LanguagePageBridge` | `client.driver.core.gui.mixin` | `LanguagePageBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.LunarPlusBridge` | `client.driver.core.gui.mixin` | `LunarPlusBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.MetadataBridge` | `client.driver.core.gui.mixin` | `MetadataBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.NotificationBridge` | `client.driver.core.gui.mixin` | `NotificationBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.OutfitBridge` | `client.driver.core.gui.mixin` | `OutfitBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.PlayerBridge` | `client.driver.core.gui.mixin` | `PlayerBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.ProfileBridge` | `client.driver.core.gui.mixin` | `ProfileBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.PromotionBridge` | `client.driver.core.gui.mixin` | `PromotionBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.SavedSkinBridge` | `client.driver.core.gui.mixin` | `SavedSkinBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.ServerDiscoveryBridge` | `client.driver.core.gui.mixin` | `ServerDiscoveryBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.ServerPingBridge` | `client.driver.core.gui.mixin` | `ServerPingBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.SettingsBridge` | `client.driver.core.gui.mixin` | `SettingsBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.SkyblockBridge` | `client.driver.core.gui.mixin` | `SkyblockBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.SocialMediaBridge` | `client.driver.core.gui.mixin` | `SocialMediaBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.StyngrBridge` | `client.driver.core.gui.mixin` | `StyngrBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.UiStateBridge` | `client.driver.core.gui.mixin` | `UiStateBridge` | home-screen JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.nameplate.FriendApi` | `client.driver.core.gui.mixin.nameplate` | `FriendApi` | friends/locker JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.nameplate.FriendsGuiExtension` | `client.driver.core.gui.mixin.nameplate` | `FriendsGuiExtension` | friends/locker JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.nameplate.Nameplate` | `client.driver.core.gui.mixin.nameplate` | `Nameplate` | friends/locker JS bridge; name accurate, move-only |
| `client.markers.mixin.gui.mixin.rewindhandlers.GuiExtension` | `client.driver.core.gui.mixin.rewindhandlers` | `GuiExtension` | minimap/waypoint JS API; name accurate, move-only |
| `client.markers.mixin.gui.mixin.rewindhandlers.MinimapJsApi` | `client.driver.core.gui.mixin.rewindhandlers` | `MinimapJsApi` | minimap/waypoint JS API; name accurate, move-only |
| `client.markers.mixin.gui.mixin.rewindhandlers.WaypointImportJsApi` | `client.driver.core.gui.mixin.rewindhandlers` | `WaypointImportJsApi` | minimap/waypoint JS API; name accurate, move-only |
| `client.markers.mixin.gui.mixin.rewindhandlers.WaypointJsApi` | `client.driver.core.gui.mixin.rewindhandlers` | `WaypointJsApi` | minimap/waypoint JS API; name accurate, move-only |
| `client.markers.mixin.highlight.Gui2Handler` | `client.driver.core.highlight` | `StorePriceEntry` | store price entry over Highlight value bundle |
| `client.markers.mixin.highlight.Highlight` | `client.driver.core.highlight` | `StorePrice` | Integer/double/Double price bundle |
| `client.markers.mixin.holograms.HologramBounds` | `client.driver.core.holograms` | `HologramBounds` | driver hologram bridge; name accurate, move-only |
| `client.markers.mixin.holograms.HologramElement` | `client.driver.core.holograms` | `HologramElement` | driver hologram bridge; name accurate, move-only |
| `client.markers.mixin.holograms.HologramRenderer` | `client.driver.core.holograms` | `HologramRenderer` | driver hologram bridge; name accurate, move-only |
| `client.markers.mixin.holograms.Holograms` | `client.driver.core.holograms` | `Holograms` | driver hologram bridge; name accurate, move-only |
| `client.markers.mixin.holograms.HologramsType` | `client.driver.core.holograms` | `HologramSubject` | PLAYER/COSMETIC subject enum |
| `client.markers.mixin.holograms.mixin.HologramSkin` | `client.driver.core.holograms.mixin` | `HologramSkin` | driver hologram impl; name accurate, move-only |
| `client.markers.mixin.holograms.mixin.Holograms` | `client.driver.core.holograms.mixin` | `Holograms` | driver hologram impl; name accurate, move-only |
| `client.markers.mixin.holograms.mixin.Holograms2Iterator` | `client.driver.core.holograms.mixin` | `EmoteHologram` | extends absent Holograms2 base; skin/display/emoteId + EmoteAPI |
| `client.markers.mixin.holograms.mixin.HologramsType` | `client.driver.core.holograms.mixin` | `HologramCameraPreset` | FRONT/BACK/DEFAULT/SUITS/HAT/CLOAK camera-preset enum |
| `client.markers.mixin.holograms.mixin.SkinOverride` | `client.driver.core.holograms.mixin` | `SkinOverride` | driver hologram impl; name accurate, move-only |
| `client.markers.mixin.nameplate.MarkersImpl` | `client.driver.core.nameplate` | `LockerContext` | locker driver context |
| `client.markers.mixin.nameplate.NameplateType` | `client.driver.core.nameplate` | `LockerSection` | locker-section enum |
| `client.markers.mixin.rewindhandlers.Markers3Handler` | `client.driver.core.rewindhandlers` | `MinimapViewContext` | DriverViewContext rendering minimap via MinimapJsApi zoom + render-distance clamp |
| `client.markers.nameplate.PositionQuad` | `client.driver.nameplate` | `PositionQuad` | driver component-callback model; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentCursorCallback` | `client.driver.nameplate.mixin` | `ComponentCursorCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentDropCallback` | `client.driver.nameplate.mixin` | `ComponentDropCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentFocusCallback` | `client.driver.nameplate.mixin` | `ComponentFocusCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentKeyCallback` | `client.driver.nameplate.mixin` | `ComponentKeyCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentMouseButtonCallback` | `client.driver.nameplate.mixin` | `ComponentMouseButtonCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentResizeCallback` | `client.driver.nameplate.mixin` | `ComponentResizeCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.ComponentScrollCallback` | `client.driver.nameplate.mixin` | `ComponentScrollCallback` | driver component callback; name accurate, move-only |
| `client.markers.nameplate.mixin.Nameplate` | `client.driver.nameplate.mixin` | `Nameplate` | driver component callback; name accurate, move-only |
| `client.markers.rewindhandlers.ConfigureWaypointProps` | `client.driver.rewindhandlers` | `ConfigureWaypointProps` | waypoint-props bridge; name accurate, move-only |
| `client.markers.rewindhandlers.MarkersImpl` | `client.driver.rewindhandlers` | `WaypointContext` | DriverContext + waypoints enabled/locked JSON |
| `client.pkg.Pkg` | `client.server` | `ResolvedServerAddress` | InetSocketAddress-backed address: hostName/hostAddress/port + method4 factory |
| `client.pkg.Pkg2` | `client.server` | `ServerBlocklist` | sessionserver.mojang.com/blockedservers fetch + patchy BlockedServers reflection; nested Data3 is the MojangBlocklist payload |
| `client.pkg.Pkg3` | `client.server` | `UnresolvedServerAddress` | Guava HostAndPort + IDN parse, server.invalid fallback, toString ServerAddress(...) |
| `client.pkg.Pkg4` | `client.server` | `ServerAddressResolver` | @FunctionalInterface resolve(Pkg3)->Optional<Pkg>; SYSTEM does InetAddress.getByName |
| `client.pkg.Pkg5` | `client.server` | `ServerSrvLookup` | SRV lookup over _minecraft._tcp. via JNDI DNS (com.sun.jndi.dns globals) |
| `client.pkg.Pkg6` | `client.server` | `ServerAddressPipeline` | composes Pkg4 resolver + Pkg5 SRV redirect + Pkg2 blocklist into Optional<Pkg> |
| `client.util.lotusfish.mixin.Lotusfish` | `client.audio.ogg` | `Crc32` | CRC-32 (poly 79764919/0x04C11DB7) table over bytes |
| `client.util.lotusfish.mixin.LotusfishException` | `client.audio.ogg` | `LotusfishException` | Ogg page model/reader; name accurate, move-only |
| `client.util.lotusfish.mixin.OggPage` | `client.audio.ogg` | `OggPage` | Ogg page model/reader; name accurate, move-only |
| `client.util.lotusfish.mixin.OggPageReader` | `client.audio.ogg` | `OggPageReader` | Ogg page model/reader; name accurate, move-only |
| `bridge.horsestats.AxisCoordinateChooser` | `bridge.horsestats` | `AxisCoordinateChooser` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.BridgeExtension` | `bridge` | `BridgeExtension` | empty MovementInputBridge alias in wrong package; relocated next to it, move-only |
| `bridge.horsestats.ChatComponentFactoryBridge` | `bridge.horsestats` | `ChatComponentFactoryBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.ChatFormatting` | `bridge.horsestats` | `ChatFormatting` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.ClickEventActionBridge` | `bridge.horsestats` | `ClickEventActionBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.ClipboardBridge` | `bridge.horsestats` | `ClipboardBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.DamageSourceBridge` | `bridge.horsestats` | `DamageSourceBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.DirectionAxisDirection` | `bridge.horsestats` | `DirectionAxisDirection` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.DyeColorBridge` | `bridge.horsestats` | `DyeColorBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.EntityEquipmentSlotBridge` | `bridge.horsestats` | `EntityEquipmentSlotBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.EnumChatFormattingBridge` | `bridge.horsestats` | `EnumChatFormattingBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.EnumFacingBridge` | `bridge.horsestats` | `EnumFacingBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.EnumFacingValue` | `bridge.horsestats` | `EnumFacingValue` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.FaceBakeryBridge` | `bridge.horsestats` | `FaceBakeryBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.GameProfilePropertyMerger` | `bridge.horsestats` | `GameProfilePropertyMerger` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.GuiResourcePackListBridge` | `bridge.horsestats` | `GuiResourcePackListBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.Horsestats20Extension2` | `bridge.horsestats` | `BlockPosBridge` | offset/above/below/asLong over Vec3iBridge; impl by BlockPos/MutableBlockPos mixins (absent) |
| `bridge.horsestats.HorsestatsType$Type` | `bridge.horsestats` | `DirectionAxis` | X/Y/Z + isVertical/isHorizontal over absent HorsestatsType_2 facing enum |
| `bridge.horsestats.HorsestatsType$Type3` | `bridge.horsestats` | `AxisPlane` | HORIZONTAL/VERTICAL facing+axis groups |
| `bridge.horsestats.HorsestatsType` | `bridge.horsestats` | `HoverEventAction` | SHOW_TEXT; SackCountListener compares hoverevent.action()==SHOW_TEXT |
| `bridge.horsestats.IChatComponentMarker` | `bridge.horsestats` | `IChatComponentMarker` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.ImageConverterBridge` | `bridge.horsestats` | `ImageConverterBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.KeyBindingEntry` | `bridge.horsestats` | `KeyBindingEntry` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.KeyBindingSource` | `bridge.horsestats` | `KeyBindingSource` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.LanguageBridge` | `bridge.horsestats` | `LanguageBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.LunarSymbols` | `bridge.horsestats` | `LunarSymbols` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.ModelRotationBridge` | `bridge.horsestats` | `ModelRotationBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.MovingObjectPositionBridge` | `bridge.horsestats` | `MovingObjectPositionBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.MovingObjectTypeBridge` | `bridge.horsestats` | `MovingObjectTypeBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.RaytraceType` | `bridge.horsestats` | `RaytraceType` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.RotationOperator` | `bridge.horsestats` | `RotationOperator` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.TexturedBoxBridge` | `bridge.horsestats` | `TexturedBoxBridge` | bridge type, precedent keeps package; name accurate, move-only |
| `bridge.horsestats.Vec3iBridge` | `bridge.horsestats` | `Vec3iBridge` | bridge type, precedent keeps package; name accurate, move-only |

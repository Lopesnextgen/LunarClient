# Package rename map

Generated from the decompiled tree `tools/work/staging/decompiled/com/moonsworth/lunar` by `tools/make_package_renames.py`.
`package-renames.tsv` contains **1262** full package-prefix mappings (old prefix -> new prefix), sorted and unique; this report explains how the
names were chosen.

## Method

The obfuscator reused a small pool of segment names for many unrelated packages, so a
single global name per segment would be wrong in most subtrees. Each obfuscated package
is therefore renamed from the evidence found in that package (consistently inside its
parent package):

* mostly `*Mixin` classes -> `mixin`; mostly event classes -> `event`;
  screens/GUI -> `gui`; renderers/textures -> `render`; config/settings -> `config`;
  packets/network -> `network`; utilities/managers -> `util`
* a package holding a single renamed module class -> the module name in lower case
  (e.g. `HorseStats` -> `horsestats`, `Freelook` -> `freelook`)
* otherwise the dominant meaningful CamelCase word of the readable class names
  (brand words such as `Skyblock`/`Hypixel` are ignored)
* packages without readable classes borrow the class names of their direct children;
  if that also fails they fall back to the most common name for that segment in the
  same top-level subtree (or elsewhere in the tree), and finally to a stable
  `pkgNN` placeholder
* two different obfuscated siblings may never share a name: the weaker one gets a
  numeric suffix (`mixin`, `mixin2`, ...), and existing readable sibling names
  (`util`, `gui`, ...) are never taken over

## Curated structural renames

| old package | new segment |
|---|---|
| `com/moonsworth/lunar/CCROIHHHCOCHHOHORCIRHOCRROIOCI` | `framework` |
| `com/moonsworth/lunar/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `annotations` |
| `com/moonsworth/lunar/HIOROOCORHICHIRIHIRCCORCIIICCH` | `bridgeimpl` |
| `com/moonsworth/lunar/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `network` |
| `com/moonsworth/lunar/HRICOROOOCCOCOROCRHHCRRIRCOICO` | `bridge` |
| `com/moonsworth/lunar/IHRHHRIHICHOOICIRIOOHOICHIRHOI` | `mixin2` |
| `com/moonsworth/lunar/IIORCIOOIHRRRICOHIRCIHOOCCOHRO` | `config` |
| `com/moonsworth/lunar/OHOOORICRHIIIIRHCICICOCHROICRC` | `loader` |
| `com/moonsworth/lunar/ORCHOHHCOHCORRICRIHCHHRORHHCHH` | `icon` |
| `com/moonsworth/lunar/ORICHRORRORHORHOIHCRHOORCRRHOI` | `files` |
| `com/moonsworth/lunar/RHIRRICCRHHHIIHHIHHOHRCHIOORCC` | `mixin` |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI` | `framework` |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO` | `feature` |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO` | `mod` |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `impl` |
| `com/moonsworth/lunar/client/RCIOICOHRIOIIRRRROCRHCIICRROHO` | `feature` |

## Segment names and evidence

`fallback` is the name used when a package occurrence has no local readable classes;
`votes` counts the local names inferred for occurrences ending in that segment.

| obfuscated segment | fallback | packages ending in seg | local votes | examples |
|---|---|---:|---|---|
| `HORHROIOIOICIRHIOCOICHHHIHCIIO` | `mixin` | 372 | `mixin`:56, `serverborders`:1, `armorstatusbarschild`:1, `chat`:1 | ALC10Mixin, ALMixin, ASMClassLoaderMixin, ActionBar |
| `HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `mixin` | 192 | `mixin`:29, `servercommandconfig`:1, `armorstatus`:1, `rabbit`:1 | AbstractClientPlayerMixin, AbstractComponentMixin, Armorstatus, BakedQuadMixin |
| `HRICOROOOCCOCOROCRHHCRRIRCOICO` | `mixin` | 122 | `mixin`:19, `serverholograms`:1, `armorstatus`:1, `hide`:1 | AbstractGuiSliderMixin, AbstractHorseMixin, ArmorstatusProtectionChild, AttackIndicator |
| `ICRHORIIHOHROHOHOCOOHOOCOORRHO` | `mixin` | 86 | `mixin`:11, `nameplate`:1, `burrow`:1, `debug`:1 | ASMClassLoaderMixin, AbstractTextureMixin, BlockBarrierMixin, BlockDoublePlantMixin |
| `RCIOICOHRIOIIRRRROCRHCIICRROHO` | `mixin` | 65 | `mixin`:9, `rewindhandlers`:2, `gui`:1, `bedwars`:1 | ASMClassLoaderMixin, AbstractTextureMixin, AutoTextActions, BettermapPrimary |
| `OHROCHICOIOICHOCRROORRCIIICIHO` | `mixin` | 52 | `mixin`:6, `holograms`:1, `ultrasequencer`:1, `bedwars`:1 | Bossbar, ChunkProviderClientMixin, CrashReportMixin, EffectRendererMixin |
| `ORCHOHHCOHCORRICRIHCHHRORHHCHH` | `mixin` | 41 | `mixin`:4, `highlight`:1, `rewindhandlers`:1, `chat`:1 | ActiveRenderInfoMixin, BlockModelRendererMixin, Chat, ChatCommandAliasesChild |
| `ORICHRORRORHORHOIHCRHOORCRRHOI` | `mixin` | 37 | `mixin`:4, `gui`:1, `chunkborders`:1 | ChunkBorders, ClickEventMixin, EffectRendererMixin, EntityFXMixin |
| `HROHOIOCHIRIHICOORIHOHCIOIRIIH` | `mixin` | 32 | `mixin`:4, `fishing`:1, `clock`:1 | BootstrapMixin, Clock, ContainerLocalRenderInformationMixin, EntityRendererMixin |
| `CCROIHHHCOCHHOHORCIRHOCRROIOCI` | `mixin` | 26 | `mixin`:4, `colorsaturation`:1, `beeheemoth`:1 | AbstractResourcePackMixin, C00HandshakeMixin, C01PacketChatMessageMixin, ColorSaturation |
| `OHOOORICRHIIIIRHCICICOCHROICRC` | `mixin` | 21 | `mixin`:3, `chest`:1, `combo`:1 | Combo, EnchantmentMixin, EntityDiggingFXMixin, GameSettingsMixin |
| `OIRIOIIIRICICCHORICRRIRROHCRIC` | `mixin` | 20 | `mixin`:2, `click`:1, `cooldowns`:1 | Cooldowns, FramebufferMixin, LimitedInputStreamMixin, MpegAudioFileReaderMixin |
| `HIOROOCORHICHIRIHIRCCORCIIICCH` | `mixin` | 17 | `mixin`:2, `coordinates`:1, `preview`:1 | Coordinates, EtherwarpPreview, GravityStormPreview, NBTTagCompoundMixin |
| `RHIRRICCRHHHIIHHIHHOHRCHIOORCC` | `mixin` | 14 | `mixin`:2, `lotusfish`:1, `cps`:1 | Cps, NetHandlerPlayClientMixin, NetworkManagerMixin, NetworkManager_v1_12Mixin |
| `IHRHHRIHICHOOICIRIOOHOICHIRHOI` | `mixin` | 12 | `mixin`:2, `alert`:1, `crosshair`:1 | Crosshair, IntegratedPlayerListMixin, NetHandlerHandshakeTCPMixin, NetHandlerLoginServerMixin |
| `IIORCIOOIHRRRICOHIRCIHOOCCOHRO` | `mixin` | 10 | `mixin`:2, `waypoints`:1, `damagetint`:1 | DamageTint, LayerCustomHeadMixin, RenderPlayerMixin, ScoreMixin |
| `RIIICIRHRCIHOOOORHOICRIICCCRHR` | `mixin` | 7 | `mixin`:2, `calculator`:1, `daycounter`:1 | DayCounter, IntegratedServerMixin, SkyblockCalculatorInSigns, SkyblockDamageSplash |
| `IRCHCHOHRRIOORHRCHRIHIOHRCIHRH` | `mixin` | 6 | `mixin`:2, `inactive`:1, `debug`:1 | ApolloDebugMod, BasicDebugMod, CosmeticDebugMod, EnumChatFormattingMixin |
| `RIIIOHCCHRRRORICCHIIHHOORIIOIR` | `mixin` | 6 | `mixin`:2, `gui`:1, `directionhud`:1 | BeamSegmentMixin, DirectionHud, ExplosionMixin, SkyblockSkillGlobeHud |
| `HICCRORORCRIHCORCCIORIOROORIHR` | `fog` | 5 | `fog`:1, `gui`:1, `mixin`:1 | Fog, SkyblockDefHud, SkyblockHealthHud, SkyblockKuudraArmorStacksHud |
| `HRORICORIHHHRICRIRCIIOHCRIRRHI` | `slayer` | 5 | `slayer`:1, `f3display`:1, `mixin`:1 | AxisAlignedBBMixin, BlockPosMixin, DamageSourceMixin, F3Display |
| `CCHOHROIOHHCCCOCIIOIRCHRROHHRC` | `horsestats` | 3 | `horsestats`:1 | HorseStats |
| `CORRCOHCRHOHHOIHOIOICORROHOOOO` | `freelook` | 3 | `freelook`:1 | Freelook |
| `HCCIHCROHHCOOHCHCHHHICIOROHICC` | `lighting` | 3 | `lighting`:1 | Lighting |
| `HCHHRHHCRIIORRRICOOCCOCHIRRRRR` | `fov` | 3 | `fov`:1 | Fov |
| `HIRHCCHIRHRORIICOIHIHCICOIRHHC` | `hitbox` | 3 | `hitbox`:1 | Hitbox |
| `HROOOICICRCOCIROHIRICCCOCCIORH` | `itemcounter` | 3 | `itemcounter`:1 | ItemCounter |
| `ICOOHRIORIOOIIRRIHHOOIOHHCORIR` | `hitcolor` | 3 | `hitcolor`:1 | HitColor |
| `OCRORCCHCRIOOIRHOHRRHCRRHRCIHO` | `fps` | 3 | `fps`:1 | Fps |
| `OORHIHOOORHHIRORRICRRORCHRRIIC` | `glintcolorizer` | 3 | `glintcolorizer`:1 | GlintColorizer |
| `RRIHRICRHHHHHIIOOCOCICOIHCIIRC` | `heightlimit` | 3 | `heightlimit`:1 | HeightLimit |
| `CCHIHOHCICHCRCCCHRIIOHHIICCCIO` | `itemtracker` | 2 | `itemtracker`:1 | ItemTracker |
| `CHIRIORIIRHHHHCIIHOHCHIIORCOHH` | `memory` | 2 | `memory`:1 | Memory |
| `HIRIHCROOIRIORCCOIRRCRHOHCCRRO` | `markers` | 2 | `markers`:1 | Markers |
| `HOCCRCCRCHROIICOOHOHRIICRHCOHR` | `itemphysics` | 2 | `itemphysics`:1 | ItemPhysics |
| `HOOHOOCICIOHOCCCCRCICOIIOIOIIH` | `inventorymod` | 2 | `inventorymod`:1 | InventoryMod |
| `HRRCROICHIIROIHRCOIHRRHCCRIIRH` | `knockbacktrainer` | 2 | `knockbacktrainer`:1 | KnockbackTrainer |
| `IICOCHRHIRHIHIIRHOORCHHIIOIOIR` | `mobsize` | 2 | `mobsize`:1 | MobSize |
| `OICRROHCCIRICRIHCOIRCOORHRHRHC` | `lightoverlay` | 2 | `lightoverlay`:1 | LightOverlay |
| `OIHOHCCCRCORHCRIRHIHOOCIOHRCHR` | `tps` | 2 | `tps`:1 | HypixelMod, HypixelTps |
| `ORCCOORHRRCOHOHRRHIHRRIOCOHCIC` | `menublur` | 2 | `menublur`:1 | MenuBlur |
| `RCOHRRRHRIOORHCRORIIHCCORRCRRC` | `animations` | 2 | `animations`:1 | CustomDroppedItems, HeldItemAnimations, ItemCustomizer |
| `RHRHIOOCICIORIOCIHHCIIRCRHHOII` | `autotexthotkey` | 2 | `autotexthotkey`:1 | AutoTextHotkey |
| `RIHHRCCHRCRRIOOOIHICHCCHIRRCCC` | `minimap` | 2 | `minimap`:1 | Minimap |
| `RIOOHRCHIRCIICRIRCOCHIRHHCOIRO` | `keystrokes` | 2 | `keystrokes`:1 | Keystrokes |
| `RRCIOHHRCRCCOCCIOROHORCRROHCOC` | `killsounds` | 2 | `killsounds`:1 | KillSounds |
| `CCOIRCORCORHORHHIORHOIHOHRCICH` | `zoom` | 1 | `zoom`:1 | Zoom |
| `CHOHRRHCHHCHCOOCICOCORRHRIICIH` | `autotextactions` | 1 | `autotextactions`:1 | AutoTextActions |
| `CHRIHRCRCIOIHIROIHROCHHHCOICOI` | `gui` | 1 | `gui`:1 | PackDisplay, ShaderPackDisplay |
| `CICORRHIOIIOORRRICCORIOIOCIHII` | `hurtcam` | 1 | `hurtcam`:1 | HurtCam |
| `COIOHCORCRCOHRROIIIOIHRIOOCIRC` | `snaplook` | 1 | `snaplook`:1 | Snaplook |
| `HCCRIIRHHCCRCCRORCCOHCIIRCOCOO` | `playtime` | 1 | `playtime`:1 | Playtime |
| `HCROHRCRRCRCIRIOOOHHIRORHCCHCR` | `packorganizer` | 1 | `packorganizer`:1 | PackOrganizer |
| `HHHROHCHIOHICCIHRCHCOIHRHHRORR` | `motionblur` | 1 | `motionblur`:1 | MotionBlur |
| `HIIIOHRRROCICIOIORRRIRCRCHHIII` | `shields` | 1 | `shields`:1 | Shields |
| `HIIOIOORCIICIRROCCRIOHRHOOIRCR` | `potioneffects` | 1 | `potioneffects`:1 | PotionEffects |
| `HIOCORCOCHIOHHCIROHIIRRHRHOIHO` | `scrollabletooltips` | 1 | `scrollabletooltips`:1 | ScrollableTooltips |
| `HOCHIRROHIHIHIIHRHOHIOOIRHHCIC` | `titles` | 1 | `titles`:1 | Titles |
| `HORHIHCCOCCORIIIOHRRRROROCHCIC` | `shinypots` | 1 | `shinypots`:1 | ShinyPots |
| `HORHIRROCIOIICIOHCOCCOOHIRCCRI` | `onesevenvisuals` | 1 | `onesevenvisuals`:1 | OneSevenVisuals |
| `HROCOOROCRHIRHOHIRHIRIHOOCHRIH` | `render` | 1 | `render`:1 | ParticleChanger, ParticleChangerBloodChild |
| `HRRRIRCICHHHRROIHRRCRCORIHHOHI` | `waila` | 1 | `waila`:1 | Waila |
| `ICIHHHCHHOIHHRROOCHOICRHOCHCOI` | `pvpinfo` | 1 | `pvpinfo`:1 | PvpInfo |
| `IHCORIOHOHHOIORHCCOOIIIHOCROOI` | `shulkerpreview` | 1 | `shulkerpreview`:1 | ShulkerPreview |
| `IHHCOCHOHOCRCRRHCIHHHIHCRIOIHR` | `counter` | 1 | `counter`:1 | TotemCounter, TotemCounterHudChild |
| `IHHIOOICIOOICRICCIRCOHCHRIROIC` | `soundchanger` | 1 | `soundchanger`:1 | SoundChanger |
| `IICIROOOIIIHCHICHIRRRRIRHHHHOR` | `panoramamaker` | 1 | `panoramamaker`:1 | PanoramaMaker |
| `IIHOIORIOCHCOORROROHIRIRIOHOHH` | `tiertagger` | 1 | `tiertagger`:1 | TierTagger |
| `IRIHHIROOHRHOIHHCIORRHIOHRCOCH` | `toggle` | 1 | `toggle`:1 | ToggleSneak, ToggleSneakHudChild |
| `OCOCRCHOHOIICRHIRCCHCIHICHRCHH` | `potioncounter` | 1 | `potioncounter`:1 | PotionCounter |
| `OCRRICRIORICCCRHIOHORCICIHHICO` | `saturation` | 1 | `saturation`:1 | Saturation, SaturationHudChild |
| `OHICCIICIRHHHOIORCRORRRCHHROCH` | `guiscale` | 1 | `guiscale`:1 | GuiScale |
| `OHRCHRORCORHICIHCOHICIHHHIRORC` | `quickplay` | 1 | `quickplay`:1 | Quickplay |
| `OIHCCIIRIOORHHOOICRCIORCOICOIR` | `timechanger` | 1 | `timechanger`:1 | TimeChanger |
| `OIIHHHCRHIHHRICHIIICHIHHRRIROI` | `stopwatch` | 1 | `stopwatch`:1 | Stopwatch |
| `OIOHHHHCICORIHOHOHIRHRROOORRCO` | `audiosubtitles` | 1 | `audiosubtitles`:1 | AudioSubtitles |
| `OIRHOOIICOCIOOHICRRRICORIHHIHC` | `replaymod` | 1 | `replaymod`:1 | Replaymod |
| `OIRIHIOOOCHRCHICICHCHCIOOOOOII` | `mumblelink` | 1 | `mumblelink`:1 | MumbleLink |
| `OOCIOHRCCCRHHIIIIOOCIIHHHRIORO` | `rewind` | 1 | `rewind`:1 | Rewind, RewindHandlers, RewindRecordingIndicatorChild, TickDebug |
| `OOIIRCOCCICIRCRHHOHCIRIRHRROHO` | `teamview` | 1 | `teamview`:1 | TeamView |
| `OOOHICCHHHRHCORIRCRHOCROROIOCR` | `momentum` | 1 | `momentum`:1 | Momentum |
| `OOOHIHRHRHIIICICCCRRICOOICOIRR` | `worldeditcui` | 1 | `worldeditcui`:1 | WorldeditCui |
| `ORHCCCIHICRHROHROHHCIIOCCICHRC` | `weatherchanger` | 1 | `weatherchanger`:1 | WeatherChanger |
| `ORICCCROCROHCRRIRHICRHCRRCOOHI` | `tntcountdown` | 1 | `tntcountdown`:1 | TntCountdown |
| `OROIRHCRHRCHIOCOCRHHIOIHCIICCH` | `reachdisplay` | 1 | `reachdisplay`:1 | ReachDisplay |
| `OROOHCRHOIRRHIIRRRRRIICHRICHHI` | `serveraddress` | 1 | `serveraddress`:1 | ServerAddress |
| `RCCHCRRHRORRCCHHOHCHHCCOHHROHI` | `staffxray` | 1 | `staffxray`:1 | StaffXray |
| `RCRHIIIOHIRHRCORCCCIRRRICHIHRC` | `waypoints` | 1 | `waypoints`:1 | Waypoints |
| `RCRIRIRRCCCOOCCHOHOOHOCIHHCCHH` | `nickhider` | 1 | `nickhider`:1 | NickHider |
| `RHOROICRIHRHCOCOORIHHIRORICRCR` | `screenshot` | 1 | `screenshot`:1 | Screenshot |
| `RICOCIIIHIRIRHCIICHCIIRCRIRIRO` | `ping` | 1 | `ping`:1 | Ping, PingHud, PingNametag |
| `RIIRRICRORIOOIOHIHOHRHROHOIRCH` | `nametag` | 1 | `nametag`:1 | Nametag |
| `ROIRRICOOCCCOIIIRHHRCCIOOCCRHR` | `scoreboard` | 1 | `scoreboard`:1 | Scoreboard |
| `ROOHOCCOCROCIHHOHOOORHHOHIROCR` | `gui` | 1 | `gui`:1 | OverlayMod, TotemAnimationOverlayChild |
| `RRICHCOCIOOCICHRCRCOCOCIIOOIRO` | `tab` | 1 | `tab`:1 | Tab |
| `RRRHCCROHRCCIRHORHOOOCCRRCHRCC` | `radio` | 1 | `radio`:1 | Radio |

## Example resolved mappings

| old package prefix | new package prefix | evidence (class count / examples) |
|---|---|---|
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO` | `client/framework/feature/mod/impl` | 163 classes: SkyblockForagingBeaconSolver, SkyblockCaughtCrittersHud, SkyblockLassoHelper, SkyblockGalateaMobHighlight |
| `com/moonsworth/lunar/legacy/HHRROIIHRRICIIHIIHICRHHRHOHHOO/HHRROIIHRRICIIHIIHICRHHRHOHHOO/HRICOROOOCCOCOROCRHHCRRIRCOICO/ORCHOHHCOHCORRICRIHCHHRORHHCHH/HHRROIIHRRICIIHIIHICRHHRHOHHOO` | `legacy/mixin2/mixin/mixin/mixin/mixin` | 16 classes: RenderPlayer_v1_7Mixin, RenderEntityItemMixin, RenderXPOrb_v1_7Mixin, ItemRendererMixin |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO` | `legacy/mixin` | 90 classes: ContainerMixin, ContainerChestMixin, ContainerRepairMixin, GuiContainerCreativeMixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/ICRHORIIHOHROHOHOCOOHOOCOORRHO` | `client/framework/feature/mod/debug` | 17 classes: SkyblockDebugHudScrollable, SkyblockChildEnabledDebug, SkyblockDebugRarityBackground, SkyblockRenderDebugBlockColors |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO` | `client/framework/feature` | 121 classes: ItemTracker, HorseStats, Zoom, ColorSaturation |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/OHROCHICOIOICHOCRROORRCIIICIHO` | `client/framework/feature/mod/impl/ultrasequencer` | 5 classes: SkyblockChronomatron, SkyblockEnchants, SkyblockExperimentSolvers, SkyblockUltrasequencer |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO/ORCHOHHCOHCORRICRIHCHHRORHHCHH` | `legacy/mixin/mixin2` | 11 classes: EntityWitherSkullMixin, EntityAgeableMixin, DataWatcherMixin, EntityDragonMixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/ORICHRORRORHORHOIHCRHOORCRRHOI` | `client/framework/feature/mod/impl/gui2` | 16 classes: SkyblockLockMouse, SkyblockComposterHud, SkyblockVisitorProfit, SkyblockAngleHud |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/HROHOIOCHIRIHICOORIHOHCIOIRIIH` | `client/framework/feature/mod/impl/fishing` | 13 classes: SkyblockSeaCreatureAlert, SkyblockFishingBaitHud, SkyblockTrophyFishExchangeRate, SkyblockHideChumBuckets |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO/ICRHORIIHOHROHOHOCOOHOOCOORRHO/CCROIHHHCOCHHOHORCIRHOCRROIOCI` | `legacy/mixin/mixin/mixin2` | 10 classes: SkinManagerMixin, DefaultResourcePackMixin, AbstractResourcePackMixin, LanguageManagerMixin |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO/OHOOORICRHIIIIRHCICICOCHROICRC` | `legacy/mixin/mixin16` | 17 classes: ItemMixin, ItemArmorMixin, ItemSpadeMixin, EnchantmentMixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/OIRIOIIIRICICCHORICRRIRROHCRIC` | `client/framework/feature/mod/impl/click` | 21 classes: SkyblockInventoryButtons, SkyblockArrowPoisonHud, SkyblockWardrobeHotkeys, SkyblockMiddleClickGui |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/HIOROOCORHICHIRIHIRCCORCIIICCH` | `client/framework/feature/mod/impl/preview` | 4 classes: GravityStormPreview, EtherwarpPreview, SkyblockSmoothTeleport, SkyblockVanillaItemModels |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO/RHIRRICCRHHHIIHHIHHOHRCHIOORCC` | `legacy/mixin/mixin4` | 10 classes: Serializer_v1_7Mixin, NetworkManager_v1_12Mixin, NetHandlerPlayClientMixin, NetworkManager_v1_7Mixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/IHRHHRIHICHOOICIRIOOHOICHIRHOI` | `client/framework/feature/mod/impl/alert` | 17 classes: SkyblockPickonimbusDurability, SkyblockCrystalHollowsMap, SkyblockSkyMallBuffAlert, SkyblockCompletedCommissions |
| `com/moonsworth/lunar/legacy/HHRROIIHRRICIIHIIHICRHHRHOHHOO/HHRROIIHRRICIIHIIHICRHHRHOHHOO/IIORCIOOIHRRRICOHIRCIHOOCCOHRO` | `legacy/mixin2/mixin/mixin13` | 5 classes: RenderPlayerMixin, LayerCustomHeadMixin, TileEntityItemStackRendererMixin, TileEntitySkullRenderer_v1_7Mixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/RIIICIRHRCIHOOOORHOICRIICCCRHR` | `client/framework/feature/mod/impl/calculator` | 6 classes: SkyblockCalculatorInSigns, TeamCakeHighlight, SkyblockDamageSplash, SkyblockProfileViewer |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IRCHCHOHRRIOORHRCHRIHIOHRCIHRH` | `client/framework/feature/debug` | 14 classes: McDebugRenderer, ApolloDebugMod, PathfindingDebug, BasicDebugMod |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/RIIIOHCCHRRRORICCHIIHHOORIIOIR` | `client/framework/feature/mod/impl/gui4` | 4 classes: SkyblockSkillGlobeHud, SkyblockSkillProgressBarHud, SkyblockSkillProgressHud, SkyblockSkillXpTrackerHud |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOIHIOIROHORRROCIHRHROHIHIHOCO/HORHROIOIOICIRHIOCOICHHHIHCIIO/HICCRORORCRIHCORCCIORIOROORIHR` | `client/framework/feature/mod/impl/gui` | 6 classes: SkyblockHealthHud, SkyblockDefHud, SkyblockKuudraArmorStacksHud, SkyblockManaHud |
| `com/moonsworth/lunar/legacy/HRICOROOOCCOCOROCRHHCRRIRCOICO/HRORICORIHHHRICRIRCIIOHCRIRRHI` | `legacy/mixin/mixin12` | 12 classes: MovementInputMixin, AxisAlignedBBMixin, NodeProcessorMixin, BlockPosMixin |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CCHOHROIOHHCCCOCIIOIRCHRROHHRC` | `client/framework/feature/horsestats` | 1 classes: HorseStats |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CORRCOHCRHOHHOIHOIOICORROHOOOO` | `client/framework/feature/freelook` | 1 classes: Freelook |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HCCIHCROHHCOOHCHCHHHICIOROHICC` | `client/framework/feature/lighting` | 1 classes: Lighting |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HCHHRHHCRIIORRRICOOCCOCHIRRRRR` | `client/framework/feature/fov` | 1 classes: Fov |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HIRHCCHIRHRORIICOIHIHCICOIRHHC` | `client/framework/feature/hitbox` | 1 classes: Hitbox |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HROOOICICRCOCIROHIRICCCOCCIORH` | `client/framework/feature/itemcounter` | 1 classes: ItemCounter |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ICOOHRIORIOOIIRRIHHOOIOHHCORIR` | `client/framework/feature/hitcolor` | 1 classes: HitColor |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OCRORCCHCRIOOIRHOHRRHCRRHRCIHO` | `client/framework/feature/fps` | 1 classes: Fps |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OORHIHOOORHHIRORRICRRORCHRRIIC` | `client/framework/feature/glintcolorizer` | 1 classes: GlintColorizer |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RRIHRICRHHHHHIIOOCOCICOIHCIIRC` | `client/framework/feature/heightlimit` | 1 classes: HeightLimit |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CCHIHOHCICHCRCCCHRIIOHHIICCCIO` | `client/framework/feature/itemtracker` | 1 classes: ItemTracker |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CHIRIORIIRHHHHCIIHOHCHIIORCOHH` | `client/framework/feature/memory` | 1 classes: Memory |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HIRIHCROOIRIORCCOIRRCRHOHCCRRO` | `client/framework/feature/markers` | 1 classes: Markers |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOCCRCCRCHROIICOOHOHRIICRHCOHR` | `client/framework/feature/itemphysics` | 1 classes: ItemPhysics |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOOHOOCICIOHOCCCCRCICOIIOIOIIH` | `client/framework/feature/inventorymod` | 1 classes: InventoryMod |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HRRCROICHIIROIHRCOIHRRHCCRIIRH` | `client/framework/feature/knockbacktrainer` | 1 classes: KnockbackTrainer |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IICOCHRHIRHIHIIRHOORCHHIIOIOIR` | `client/framework/feature/mobsize` | 1 classes: MobSize |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OICRROHCCIRICRIHCOIRCOORHRHRHC` | `client/framework/feature/lightoverlay` | 1 classes: LightOverlay |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIHOHCCCRCORHCRIRHIHOOCIOHRCHR` | `client/framework/feature/tps` | 2 classes: HypixelMod, HypixelTps |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ORCCOORHRRCOHOHRRHIHRRIOCOHCIC` | `client/framework/feature/menublur` | 1 classes: MenuBlur |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RCOHRRRHRIOORHCRORIIHCCORRCRRC` | `client/framework/feature/animations` | 3 classes: CustomDroppedItems, HeldItemAnimations, ItemCustomizer |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RHRHIOOCICIORIOCIHHCIIRCRHHOII` | `client/framework/feature/autotexthotkey` | 1 classes: AutoTextHotkey |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RIHHRCCHRCRRIOOOIHICHCCHIRRCCC` | `client/framework/feature/minimap` | 1 classes: Minimap |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RIOOHRCHIRCIICRIRCOCHIRHHCOIRO` | `client/framework/feature/keystrokes` | 1 classes: Keystrokes |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RRCIOHHRCRCCOCCIOROHORCRROHCOC` | `client/framework/feature/killsounds` | 1 classes: KillSounds |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CCOIRCORCORHORHHIORHOIHOHRCICH` | `client/framework/feature/zoom` | 1 classes: Zoom |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CHOHRRHCHHCHCOOCICOCORRHRIICIH` | `client/framework/feature/autotextactions` | 1 classes: AutoTextActions |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CHRIHRCRCIOIHIROIHROCHHHCOICOI` | `client/framework/feature/gui` | 2 classes: ShaderPackDisplay, PackDisplay |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/CICORRHIOIIOORRRICCORIOIOCIHII` | `client/framework/feature/hurtcam` | 1 classes: HurtCam |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/COIOHCORCRCOHRROIIIOIHRIOOCIRC` | `client/framework/feature/snaplook` | 1 classes: Snaplook |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HCCRIIRHHCCRCCRORCCOHCIIRCOCOO` | `client/framework/feature/playtime` | 1 classes: Playtime |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HCROHRCRRCRCIRIOOOHHIRORHCCHCR` | `client/framework/feature/packorganizer` | 1 classes: PackOrganizer |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HHHROHCHIOHICCIHRCHCOIHRHHRORR` | `client/framework/feature/motionblur` | 1 classes: MotionBlur |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HIIIOHRRROCICIOIORRRIRCRCHHIII` | `client/framework/feature/shields` | 1 classes: Shields |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HIIOIOORCIICIRROCCRIOHRHOOIRCR` | `client/framework/feature/potioneffects` | 1 classes: PotionEffects |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HIOCORCOCHIOHHCIROHIIRRHRHOIHO` | `client/framework/feature/scrollabletooltips` | 1 classes: ScrollableTooltips |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HOCHIRROHIHIHIIHRHOHIOOIRHHCIC` | `client/framework/feature/titles` | 1 classes: Titles |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HORHIHCCOCCORIIIOHRRRROROCHCIC` | `client/framework/feature/shinypots` | 1 classes: ShinyPots |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HORHIRROCIOIICIOHCOCCOOHIRCCRI` | `client/framework/feature/onesevenvisuals` | 1 classes: OneSevenVisuals |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HROCOOROCRHIRHOHIRHIRIHOOCHRIH` | `client/framework/feature/render` | 2 classes: ParticleChangerBloodChild, ParticleChanger |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/HRRRIRCICHHHRROIHRRCRCORIHHOHI` | `client/framework/feature/waila` | 1 classes: Waila |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ICIHHHCHHOIHHRROOCHOICRHOCHCOI` | `client/framework/feature/pvpinfo` | 1 classes: PvpInfo |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IHCORIOHOHHOIORHCCOOIIIHOCROOI` | `client/framework/feature/shulkerpreview` | 1 classes: ShulkerPreview |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IHHCOCHOHOCRCRRHCIHHHIHCRIOIHR` | `client/framework/feature/counter` | 2 classes: TotemCounter, TotemCounterHudChild |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IHHIOOICIOOICRICCIRCOHCHRIROIC` | `client/framework/feature/soundchanger` | 1 classes: SoundChanger |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IICIROOOIIIHCHICHIRRRRIRHHHHOR` | `client/framework/feature/panoramamaker` | 1 classes: PanoramaMaker |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IIHOIORIOCHCOORROROHIRIRIOHOHH` | `client/framework/feature/tiertagger` | 1 classes: TierTagger |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/IRIHHIROOHRHOIHHCIORRHIOHRCOCH` | `client/framework/feature/toggle` | 2 classes: ToggleSneakHudChild, ToggleSneak |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OCOCRCHOHOIICRHIRCCHCIHICHRCHH` | `client/framework/feature/potioncounter` | 1 classes: PotionCounter |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OCRRICRIORICCCRHIOHORCICIHHICO` | `client/framework/feature/saturation` | 2 classes: SaturationHudChild, Saturation |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OHICCIICIRHHHOIORCRORRRCHHROCH` | `client/framework/feature/guiscale` | 1 classes: GuiScale |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OHRCHRORCORHICIHCOHICIHHHIRORC` | `client/framework/feature/quickplay` | 1 classes: Quickplay |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIHCCIIRIOORHHOOICRCIORCOICOIR` | `client/framework/feature/timechanger` | 1 classes: TimeChanger |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIIHHHCRHIHHRICHIIICHIHHRRIROI` | `client/framework/feature/stopwatch` | 1 classes: Stopwatch |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIOHHHHCICORIHOHOHIRHRROOORRCO` | `client/framework/feature/audiosubtitles` | 1 classes: AudioSubtitles |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIRHOOIICOCIOOHICRRRICORIHHIHC` | `client/framework/feature/replaymod` | 1 classes: Replaymod |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OIRIHIOOOCHRCHICICHCHCIOOOOOII` | `client/framework/feature/mumblelink` | 1 classes: MumbleLink |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OOCIOHRCCCRHHIIIIOOCIIHHHRIORO` | `client/framework/feature/rewind` | 6 classes: Rewind, RewindRecordingIndicatorChild, TickDebug, RewindHandlers |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OOIIRCOCCICIRCRHHOHCIRIRHRROHO` | `client/framework/feature/teamview` | 1 classes: TeamView |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OOOHICCHHHRHCORIRCRHOCROROIOCR` | `client/framework/feature/momentum` | 1 classes: Momentum |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OOOHIHRHRHIIICICCCRRICOOICOIRR` | `client/framework/feature/worldeditcui` | 1 classes: WorldeditCui |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ORHCCCIHICRHROHROHHCIIOCCICHRC` | `client/framework/feature/weatherchanger` | 1 classes: WeatherChanger |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ORICCCROCROHCRRIRHICRHCRRCOOHI` | `client/framework/feature/tntcountdown` | 1 classes: TntCountdown |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OROIRHCRHRCHIOCOCRHHIOIHCIICCH` | `client/framework/feature/reachdisplay` | 1 classes: ReachDisplay |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/OROOHCRHOIRRHIIRRRRRIICHRICHHI` | `client/framework/feature/serveraddress` | 1 classes: ServerAddress |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RCCHCRRHRORRCCHHOHCHHCCOHHROHI` | `client/framework/feature/staffxray` | 1 classes: StaffXray |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RCRHIIIOHIRHRCORCCCIRRRICHIHRC` | `client/framework/feature/waypoints` | 1 classes: Waypoints |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RCRIRIRRCCCOOCCHOHOOHOCIHHCCHH` | `client/framework/feature/nickhider` | 1 classes: NickHider |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RHOROICRIHRHCOCOORIHHIRORICRCR` | `client/framework/feature/screenshot` | 1 classes: Screenshot |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RICOCIIIHIRIRHCIICHCIIRCRIRIRO` | `client/framework/feature/ping` | 3 classes: Ping, PingHud, PingNametag |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RIIRRICRORIOOIOHIHOHRHROHOIRCH` | `client/framework/feature/nametag` | 1 classes: Nametag |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ROIRRICOOCCCOIIIRHHRCCIOOCCRHR` | `client/framework/feature/scoreboard` | 1 classes: Scoreboard |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/ROOHOCCOCROCIHHOHOOORHHOHIROCR` | `client/framework/feature/gui2` | 2 classes: TotemAnimationOverlayChild, OverlayMod |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RRICHCOCIOOCICHRCRCOCOCIIOOIRO` | `client/framework/feature/tab` | 1 classes: Tab |
| `com/moonsworth/lunar/client/CCROIHHHCOCHHOHORCIRHOCRROIOCI/RCIOICOHRIOIIRRRROCRHCIICRROHO/RRRHCCROHRCCIRHORHOOOCCRRCHRCC` | `client/framework/feature/radio` | 1 classes: Radio |

(96 examples; the full list of 1262 mappings is in `package-renames.tsv`.)

## Notes

* The decompiled tree mixes several copies of the same client classes (flat
  `client/<module>`, nested `client/framework/feature/...` and the raw `lunar/<obf>`
  tree); every copy is renamed from its own local evidence.
* Some raw `com/moonsworth/lunar/<obf>` packages contain no renamed classes at all, so
  they fall back to the name most commonly inferred for that segment elsewhere.

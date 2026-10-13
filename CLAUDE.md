# CLAUDE.md - ManyIdeas Core

Library der „Many Ideas“-Familie (ManyIdeasDoors, ManyIdeasChristmas, ManyIdeasHalloween) und zugleich eigene Inhalte-Mod: Tischsägen, Mörser, Färbe-Werkbank, Redstone-Schlüssel, Regenbogen-/Farbblöcke, Dielen, Platten, gerade Blumen u. a. Mod-ID `manyideas_core`, Package `de.geheimagentnr1.manyideas_core`, Client+Server.

Diese Datei ist auf allen `develop_*`-Branches identisch; Quelle: `../Docs/mod-claude/ManyIdeasCore.md` (Änderungen dort, dann `Docs/tools/sync_mod_claude_md.sh`). Versionsstand: [`../Docs/VERSIONEN.md`](../Docs/VERSIONEN.md).

## Versionen

- Welche Jars/Branches welche MC-Versionen abdecken, Tags und Ranges: [`../Docs/VERSIONEN.md`](../Docs/VERSIONEN.md). NeoForge ab `develop_1.21.1`, Forge bis `develop_1.21` plus `develop_1.21.1_forge` ([`../Docs/forge/README.md`](../Docs/forge/README.md)). `develop_1.21.3` ist ein unveröffentlichter Forge-Versuch.
- Ab 1.21.2 ist fast jede MC-Version ein eigener Core-Branch (Registrierung, Item-Modelle, GUI, Renderer, Loot-Format ändern sich laufend), Details: [`../Docs/migrations/1.21.1-to-1.21.2.md`](../Docs/migrations/1.21.1-to-1.21.2.md) 4i, [`../Docs/migrations/1.21.11-to-26.1.md`](../Docs/migrations/1.21.11-to-26.1.md).
- Die abhängigen Mods binden ein Core-Jar `ManyIdeasCore-<mc>:${mic_version}` aus `ModdingMavenRepositories` (`<mc>` bis 1.21.1 = `minecraft_version`, ab 1.21.2 eigene Variable `mic_minecraft_version`, damit ein Jar mehrere Core-Jars abdecken kann) - **Core immer zuerst veröffentlichen** (lokales Maven), dann die abhängigen Mods.
- CurseForge-Projekt `433642`, Modrinth `BBY8EPJt`.

## Abhängigkeiten

- Keine Pflicht-Mods. JEI optional (`compileOnly`/`runtimeOnly` in `custom.gradle`, Maven `https://maven.blamejared.com/`), Quellen unter `src/integrations/jei/java` als zusätzliches Source-Set. Auf `develop_1.21.2` (1.21.2 - 1.21.3) ist die JEI-Integration auskommentiert (kein JEI für diese Versionen).
- Forge 1.20 - 1.21.1-Forge: MinecraftForgeAPI (`mfapi_version`, per jarJar gebündelt), siehe [`../Docs/forge/README.md`](../Docs/forge/README.md).

## Architektur

- **Framework (NeoForge, `core/`)**: `AbstractMod` (Basis für alle ManyIdeas-Mods, `initMod()` + `registerEventHandler(..)`), `core/registry/` (`RegistryEntry`, `ElementsRegisterFactory`, `RegistryHelper`, `RegistryKeys`), `core/elements/` (`BlocksRegisterFactory`, `ItemsRegisterFactory`, `CommandsRegisterFactory`, `CreativeModeTabFactory`, `RecipeTypesRegisterFactory`, `BlockItemInterface`), `core/events/` (Mod-/Forge-Event-Interfaces), `core/network/AbstractNetwork`.
  - Forge 1.20 - 1.21.1-Forge: dieselben Klassen kommen aus MinecraftForgeAPI (`de.geheimagentnr1.minecraft_forge_api.AbstractMod`), dazu `config/ClientConfig` (Debug-Blöcke, alle Farben im Creative-Tab). Forge bis 1.19.4: statische Handler in `handlers/` und `elements/RegistryEntry`.
- **Templates für abhängige Mods** (`elements/blocks/template_blocks/`): `MultiBlock` (+ `MultiBlockCalculater`/`MultiBlockRunner`), `doors/BigDoor` (Mehrblock-Tür), `doors/DoubleDoorBlock`, `dyed/DyeBlock`/`DyeBlockItem`, `AxisBlock`, `EveryDirectionBlock`, `flowers_straight/`. Dazu `util/doors/` (`DoorsHelper`, `BigDoorsHelper`, `OpenedByHelper`), `util/voxel_shapes/`, Blockstate-Properties in `elements/block_state_properties/` (`OpenedBy`, `Color`, `BlockSide`, `EveryDirectionFacing`).
- **Redstone-Schlüssel** (`elements/items/tools/redstone_key/`): Blöcke mit `RedstoneKeyable` (alle Türen, Doors-Sensor) bekommen per Rechtsklick ein Menü für `OpenedBy` (Hand/Redstone/beides); Paket `RedstoneKeyStateUpdateMsg`.
- **Rezepte** (`elements/recipes/`, `elements/blocks/table_saws|mortar`): Typen `table_sawing_stone|iron|diamond`, `grinding` (Mörser), `dyed_shaped`/`dyed_shapeless` mit Ingredients `color_item`/`color_tag`. Abhängige Mods nutzen `manyideas_core:table_sawing_*` in ihren Rezept-JSONs.
- **Netzwerk** (`network/Network`): `RedstoneKeyStateUpdateMsg`, ab 1.21.2 `TableSawRecipesSyncMsg`; ab 1.21.4 zusätzlich `elements/recipes/ClientRecipes` (Rezept-Cache für JEI).
- **Spieler-Dekorationen** (`special/decoration_renderer/`): Client lädt `player_decorations.json` aus dem GitHub-Repo `GeheimagentNr1/Online_Mod_Data` und rendert Items an bestimmten Spielern.
- `Entfernt/` enthält alte, nicht kompilierte Inhalte (Kochtopf, Töpferscheibe, Metall-Räucherofen).

**Was abhängige Mods bricht:** Signaturen von `AbstractMod`, `RegistryEntry.create(..)`, `ElementsRegisterFactory`/`BlocksRegisterFactory`, `CreativeModeTabFactory` (`getDisplayBlocks()`/`getDisplayItems()`), der Template-Blöcke und `RegistryHelper`; Rezepttyp- und Property-Namen (`manyideas_core:color`, `table_sawing_*`). Nach Core-Änderungen alle drei abhängigen Mods gegen das neue Core-Jar bauen und per `bincheck.ps1` prüfen.

## Funktionen, Befehle, Konfiguration

- `/givedb <target> <dye block> <color>` (Rechtestufe Gamemaster) - Farbblock in bestimmter Farbe geben; `/element_count` (Debug: Anzahl Blöcke/Items je Mod).
- Debug-Block `DebugBlockCullface` (`ModDebugBlocksRegisterFactory`); auf Forge über die Client-Config schaltbar, NeoForge hat keine Config.

## Fallstricke

- **Registrierung ab 1.21.2:** Block-/Item-IDs müssen vor dem Konstruktor feststehen. Einträge als `RegistryEntry.create( name, () -> new X() )` (Supplier); `RegistryHelper` hält währenddessen den Key in einem `ThreadLocal`, Templates setzen die ID mit `RegistryHelper.withBlockId( properties )`, Items mit `RegistryHelper.itemProperties()`. Abhängige Mods ändern nur `new X()` → `() -> new X()` (bzw. `withBlockId(..)` in Blöcken, die direkt Vanilla-Klassen erweitern). 1.21.1 und Forge kennen nur `create( name, value )`.
- **Tischsägen-Rezepte ab 1.21.2:** Der Server schickt keine Rezepte mehr an den Client - `TableSawRecipesSyncMsg` (in `OnDatapackSyncEvent`, Login und `/reload`) füllt den Client-Cache `TableSawRecipes`. Menü-Listen sind veränderbare Kopien (`toList()` → Client-Absturz beim Herausnehmen).
- **JEI ab 1.21.4:** eigene Rezepttypen per `event.sendRecipes(..)` anfordern, Client speichert sie in `ClientRecipes` (Listener mit `EventPriority.HIGHEST`). `jei:textures/gui/gui_vanilla.png` existiert nicht mehr: NeoForge `createBlankDrawable` + `getSlotDrawable()`/`getRecipeArrow()`, Forge Vanilla-`crafting_table.png` (29, 16, 116×54). Details: [`../Docs/migrations/1.21.1-to-1.21.2.md`](../Docs/migrations/1.21.1-to-1.21.2.md) Abschnitt „JEI und eigene Rezepttypen“.
- **Klick-Ergebnisse ab 1.21.2:** `PASS_TO_DEFAULT_BLOCK_INTERACTION` → `TRY_WITH_EMPTY_HAND` (sonst öffnet die Tür mit Item in der Hand nicht), `SKIP_DEFAULT_BLOCK_INTERACTION` → `PASS` (sonst greift der Redstone-Schlüssel nicht); `onBlockExploded` überschreiben, nicht `wasExploded`.
- **Mehrblöcke und Flüssigkeiten** (NeoForge und Forge 1.20.1): `MultiBlock` ist `LiquidBlockContainer` mit `canPlaceLiquid = false` (Signatur 1.20.2 `Player`, ab 1.21.5 `LivingEntity`) und überschreibt `canBeReplaced( BlockState, Fluid )` mit `false` - sonst entfernen fließendes Wasser oder ein Eimer die ganze Tür ohne Drop. Ab 1.21.5 Mehrblock-Abbau in `affectNeighborsAfterRemoval`.
- **Loot:** `match_tool` nur im Format `"predicates": { "minecraft:enchantments": [..] }` (alte Form wird still ignoriert, Regenbogenglas). Ab 26.3 Loot-Tabellen in beiden Formaten (`conditions`/`functions` und `condition`/`modifier`, `match_block`).
- **Kompostierbarkeit** (Blumen): Data-Map `data/neoforge/data_maps/item/compostables.json`; 26.3 `Item.Properties.compostable(..)`.
- **Item-Modelle:** 1.21.4 Client-Item-Definitionen (`assets/manyideas_core/items`), Farb-Items ab 1.21.9 per `minecraft:select` + `minecraft:component`; Modelle ohne `#missing`-Flächen, ab 26.1 UVs nur 0 - 16.
- **Creative-Tabs:** jedes Item nur einmal ausgeben (ab NeoForge 26.2.0.88 Absturz bei Duplikaten).

## Testen

- Allgemein: [`../Docs/testing/README.md`](../Docs/testing/README.md). Core immer zusammen mit **ManyIdeasDoors** testen - große Türen sind die eigentlichen Nutzer von `MultiBlock`, `BigDoor`, Redstone-Schlüssel und Tischsägen-Rezepten.
- Prüfpunkte: Tischsäge (Rezepte erscheinen, Items lassen sich herausnehmen, nach `/reload`), Färbe-Werkbank, Mörser, Redstone-Schlüssel an einer Tür, JEI-Kategorien (Hintergrund, Slots), Behutsamkeit am Regenbogenglas, Abbau/Explosion einer großen Tür (nur Ursprungsblock droppt), Wasser/Eimer an einer Tür.
- `Docs/testing/tools/neoforge_smoke_all.sh` enthält den Wassertest an einer großen Akazientür (braucht Doors im Pack); RCON-Loot-Test per `setblock` + `loot spawn .. mine ..`.
- Unit-Tests: `src/test/java` (VoxelShape-Helfer).

## Links

- Wiki: https://github.com/GeheimagentNr1/ManyIdeasCore/wiki
- CurseForge: https://www.curseforge.com/minecraft/mc-mods/many-ideas-core, Modrinth: https://modrinth.com/mod/BBY8EPJt
- [`../Docs/migrations/BREAKING_CHANGES.md`](../Docs/migrations/BREAKING_CHANGES.md), [`../Docs/migrations/1.21.1-to-1.21.2.md`](../Docs/migrations/1.21.1-to-1.21.2.md) 4i, [`../Docs/forge/README.md`](../Docs/forge/README.md)

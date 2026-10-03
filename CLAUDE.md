# CLAUDE.md - ManyIdeas Core

## Projekt-Übersicht

**ManyIdeas Core** ist ein NeoForge Minecraft Mod.
- **Mod ID**: `manyideas_core`
- **Package**: `de.geheimagentnr1.manyideas_core`
- **Java Version**: 21 (`develop_26.1`/`develop_26.3`: 25, `jdk-25.0.4.7-hotspot`)
- **NeoForge Version**: je Branch, siehe Tabelle

| Branch | MC | Range | NeoForge (kompiliert gegen) | Hinweis |
|---|---|---|---|---|
| `develop_1.21.1` | 1.21.1 | `[1.21.1,1.21.2)` | `21.1.216` | Fix-Release 3.0.2 (Behutsamkeits-Loot, Blumen-Kompostierbarkeit) |
| `develop_1.21.2` | 1.21.2 - 1.21.3 | `[1.21.2,1.21.4)` | `21.2.1-beta` | Registrierung mit Supplier + Key-Kontext (IDs), Rezept-API/JSON, Tischsägen-Rezept-Sync, Klick-Ergebnisse |
| `develop_1.21.4` | 1.21.4 | `[1.21.4,1.21.5)` | `21.4.158` | Client-Item-Definitionen (`assets/manyideas_core/items`), Farb-Items per `range_dispatch` + eigener Property, `RenderShape.INVISIBLE` |
| `develop_1.21.5` | 1.21.5 | `[1.21.5,1.21.6)` | `21.5.98` | Multiblock-Abbau in `affectNeighborsAfterRemoval`, `onCraftedBy` |
| `develop_1.21.6` | 1.21.6 - 1.21.8 | `[1.21.6,1.21.9)` | `21.6.20-beta` | GUI (`RenderPipelines`, Alpha-Farben), Paket über `ServerboundCustomPayloadPacket` |
| `develop_1.21.9` | 1.21.9 - 1.21.10 | `[1.21.9,1.21.11)` | `21.9.16-beta` | Submit-Renderer (End-Block, Spieler-Dekoration), Maus-Events, Farb-Items per `minecraft:select` + Komponente |
| `develop_1.21.11` | 1.21.11 | `[1.21.11,1.21.12)` | `21.11.45` | `Identifier`, `LEVEL_GAMEMASTERS`, `renderContents` |
| `develop_26.1` | 26.1 - 26.2 | `[26.1,26.3)` | `26.1.0.19-beta` (Java 25) | `RecipeSerializer`-Record, `ItemStackTemplate`, `GuiGraphicsExtractor`, End-Portal `submitCube` |
| `develop_26.3` | 26.3 | `[26.3,27)` | `26.3.0.36-beta` (Java 25) | Loot in beiden Formaten, `Prediction`, `PushReaction.IMMOVEABLE`, Kompostierbarkeit als Item-Komponente |

Alle 3.0.2, released 2026-10-03 (ingame getestet auf 1.21.1 - 26.3). Details: [`../Docs/migrations/1.21.1-to-1.21.2.md`](../Docs/migrations/1.21.1-to-1.21.2.md) 4i, [`../Docs/migrations/1.21.11-to-26.1.md`](../Docs/migrations/1.21.11-to-26.1.md).

**Registrierung ab 1.21.2:** Block-/Item-IDs müssen vor dem Konstruktor feststehen. Einträge daher als `RegistryEntry.create( name, () -> new X() )` (Supplier); während der Supplier läuft, kennt `RegistryHelper` den Key, und die Templates (`DoubleDoorBlock`, `BigDoor`, `MultiBlock`, ...) setzen die ID per `RegistryHelper.withBlockId( properties )`, Items per `RegistryHelper.itemProperties()`. Abhängige Mods (Doors, Christmas, Halloween) müssen nur `new X()` → `() -> new X()` ändern.

**Tischsägen-Rezepte:** Seit 1.21.2 schickt der Server keine Rezepte mehr an den Client; `TableSawRecipesSyncMsg` sendet sie beim Login und nach `/reload`, der Client hält sie in `TableSawRecipes`. Rezeptlisten im Menü sind veränderbare Kopien (eine `toList()`-Liste brachte den Client beim Herausnehmen zum Absturz).

Dieser Mod dient als Core-Library für andere "Many Ideas" Mods und enthält gemeinsam genutzte Blöcke, Items, Tools und Utilities.

## Projektstruktur

```
src/main/java/de/geheimagentnr1/manyideas_core/
├── ManyIdeasCore.java          # Haupt-Mod-Klasse (@Mod Annotation)
├── core/                       # Framework-Klassen für Mod-Entwicklung
│   ├── AbstractMod.java        # Basis-Klasse für Mods
│   ├── events/                 # Event-Handler Interfaces
│   ├── network/                # Netzwerk-Abstraktion
│   └── registry/               # Registry-Utilities
├── elements/                   # Mod-Inhalte
│   ├── blocks/                 # Block-Definitionen
│   ├── items/                  # Item-Definitionen
│   ├── commands/               # Commands und ArgumentTypes
│   ├── recipes/                # Rezept-Serializer und -Typen
│   └── creative_mod_tabs/      # Creative-Tabs
├── network/                    # Netzwerk-Pakete
├── special/                    # Spezielle Features (z.B. Player Decorations)
└── util/                       # Utility-Klassen (VoxelShapes, etc.)
```

## Architektur-Patterns

### 1. Event Handler Registration
Event Handler werden über `registerEventHandler()` in `initMod()` registriert:
```java
registerEventHandler( new ModBlocksRegisterFactory() );
```

### 2. Registry Pattern
Neue Blöcke/Items werden über `ElementsRegisterFactory<T>` registriert:
- Erstelle eine Klasse die `ElementsRegisterFactory<T>` erweitert
- Implementiere `registryKey()` und `elements()`
- Nutze `RegistryEntry<T>` für einzelne Einträge

### 3. Client/Server Separation
Keine Dist-Abfrage über FML-APIs (nicht versionsübergreifend). Client-only Handler per `@EventBusSubscriber( modid = ManyIdeasCore.MODID, value = Dist.CLIENT )` (ab NeoForge 21.6 ohne `bus`), siehe `ManyIdeasCoreClientSetup`.

### 4. Netzwerk-Pakete
Netzwerk-Kommunikation erfolgt über `AbstractNetwork`:
- Erweitere `AbstractNetwork`
- Registriere Pakete in `registerPackets()`
- Nutze `PayloadRegistrar` für Packet-Registration

## Code-Stil

- **Annotations**: Nutze `@NotNull` aus `org.jetbrains.annotations`
- **Lombok**: Projekt nutzt Lombok (z.B. für Getter/Setter)
- **Formatierung**: Leerzeichen nach `(` und vor `)` bei Methodenaufrufen
- **Imports**: Keine Wildcard-Imports

## Build & Test

```bash
# Build
./gradlew build

# Client starten
./gradlew runClient

# Server starten
./gradlew runServer

# Data Generation
./gradlew runData

# Tests
./gradlew test

# Publish to local Maven
./gradlew publish
```

## Deployment

- **CurseForge**: `./gradlew curseforge`
- **Modrinth**: `./gradlew modrinth`
- Debug-Modus: `-PuploadDebug=true`

## Wichtige Hinweise

1. **Mod-Abhängigkeiten**: Andere Mods können von diesem Core-Mod abhängen
2. **Ressourcen**: Generierte Ressourcen liegen in `src/generated/resources`
3. **Lizenzen**: `All Rights Reserved` - Drittanbieter-Lizenzen in `lib_licences/`
4. **Tests**: Unit-Tests in `src/test/java/` (JUnit 5)

## Häufige Aufgaben

### Neuen Block hinzufügen
1. Block-Klasse in `elements/blocks/` erstellen
2. `RegistryEntry` in `ModBlocksRegisterFactory.elements()` hinzufügen
3. Blockstate JSON in `src/main/resources/assets/manyideas_core/blockstates/`
4. Model JSON in `src/main/resources/assets/manyideas_core/models/block/`
5. Textur in `src/main/resources/assets/manyideas_core/textures/block/`

### Neues Item hinzufügen
1. Item-Klasse in `elements/items/` erstellen (falls custom)
2. `RegistryEntry` in `ModItemsRegisterFactory.elements()` hinzufügen
3. Model JSON in `src/main/resources/assets/manyideas_core/models/item/`
4. Textur in `src/main/resources/assets/manyideas_core/textures/item/`

### Neues Netzwerk-Paket hinzufügen
1. Paket-Klasse mit `CustomPacketPayload` erstellen
2. `TYPE` und `STREAM_CODEC` definieren
3. In `Network.registerPackets()` registrieren

## Testing

### Java-Versionen

Verschiedene Java-Versionen sind unter `C:\Program Files\Eclipse Adoptium` installiert. Für einen Gradle-Build muss die passende Java-Version gewählt werden:

```powershell
# Java 21 für MC 1.20.5+ (NeoForge)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.12.8-hotspot"
./gradlew build
```

### Unit Tests (JUnit 5)

Für reine Logik-Tests ohne Minecraft-Abhängigkeiten:

```bash
./gradlew test
```

Tests liegen unter `src/test/java/`. Ergebnisse: `build/reports/tests/test/index.html`

### NeoForge GameTest Framework

Für Integration Tests in einer echten Minecraft-Umgebung:

```bash
./gradlew runGameTestServer
```

Der triviale GameTest wurde beim 1.21.2-Port entfernt (annotationsbasierte GameTests gibt es ab 1.21.5 nicht mehr).

### CI/CD (GitHub Actions)

Der Workflow `.github/workflows/build-and-test.yml` führt automatisch aus:
1. **Build**: Kompiliert den Mod
2. **Unit Tests**: Führt JUnit Tests aus
3. **GameTests**: Startet GameTestServer (optional)

### Was kann automatisiert getestet werden?

| Aspekt | Automatisiert? | Methode |
|--------|----------------|---------|
| Utility-Klassen | ✅ | JUnit |
| Config-Parsing | ✅ | JUnit |
| Commands | ✅ | GameTest |
| Block/Item-Verhalten | ✅ | GameTest |
| Multi-MC-Version | ⚠️ Pro Branch | CI Matrix |

## Referenzen

- [NeoForge Migration Primer](https://docs.neoforged.net/primer/docs/) — Dokumentiert API-Aenderungen zwischen Minecraft/NeoForge-Versionen; nuetzlich fuer die Pruefung von Breaking Changes beim Upgrade auf neue Versionen

---

## Wissensdatenbank

Versionsübergreifende Migrations- und Entwicklungs-Erkenntnisse (Breaking Changes, Fixes, Testumgebungs-Patterns) werden zentral in [`../Docs/`](../Docs/) gepflegt. Bei neuen relevanten Erkenntnissen dort ergänzen, nicht nur hier.

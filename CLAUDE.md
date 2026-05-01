# CLAUDE.md - ManyIdeas Core

## Projekt-Übersicht

**ManyIdeas Core** ist ein NeoForge Minecraft Mod für Minecraft 1.21.1.
- **Mod ID**: `manyideas_core`
- **Package**: `de.geheimagentnr1.manyideas_core`
- **Java Version**: 21
- **NeoForge Version**: 21.1.x

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
Client-only Code wird mit `FMLLoader.getDist() == Dist.CLIENT` geprüft:
```java
if( FMLLoader.getDist() == Dist.CLIENT ) {
    // Client-only code
}
```

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
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
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

GameTest-Klassen werden mit `@GameTestHolder` annotiert und liegen unter `src/main/java/.../elements/gametests/`.

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

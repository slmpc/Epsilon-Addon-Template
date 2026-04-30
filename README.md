<h1 align="center">Epsilon Addon Template</h1>
<h4 align="center">
    <p>
        <b>English</b> |
        <a href="README_zh.md">中文</a>
    </p>
</h4>

<p align="center">
  <a href="LICENSE"><img alt="License" src="https://img.shields.io/badge/license-Apache%202.0-blue?style=flat-square"></a>
  <img alt="Loaders" src="https://img.shields.io/badge/loaders-NeoForge%20%26%20Fabric-6a5acd?style=flat-square">
</p>

## Overview

This repository is a **multiloader OpenEpsilon addon template**.

It keeps shared addon logic in `common`, and loader-specific bootstrap code in `fabric` and `neoforge`.

## Project Layout

- `common` – shared addon class and shared resources
- `fabric` – Fabric bootstrap + `open_epsilon:addon` entrypoint
- `neoforge` – NeoForge bootstrap + addon registration event hook
- `epsilon_libs` – local OpenEpsilon jars used for development and testing

## Included Example

The template ships with a minimal addon:

- shared addon class: `common/src/main/java/com/example/epsilonaddon/template/EpsilonAddonTemplate.java`
- Fabric entrypoint: `fabric/src/main/java/com/example/epsilonaddon/template/fabric/EpsilonAddonTemplateFabricEntrypoint.java`
- NeoForge bootstrap: `neoforge/src/main/java/com/example/epsilonaddon/template/neoforge/EpsilonAddonTemplateNeoForge.java`

Replace these files with your own addon implementation when starting a new project.

## Local OpenEpsilon Dependencies

Before building, place the built OpenEpsilon jars into `epsilon_libs/`.

Expected filenames are configured in `gradle.properties`:

- `epsilon_common_jar`
- `epsilon_fabric_jar`
- `epsilon_neoforge_jar`

By default they match OpenEpsilon `2026.3.0` for Minecraft `26.1.2`.

## Build

```powershell
.\gradlew.bat build
```

## Run

```powershell
.\gradlew.bat :fabric:runClient
.\gradlew.bat :neoforge:runClient
```

## Addon Development Notes

- Fabric registers addons through the custom `open_epsilon:addon` entrypoint.
- NeoForge registers addons by listening to `com.github.epsilon.neoforge.addon.EpsilonAddonSetupEvent`.
- Shared addon settings should use lang keys like `{addonId}.settings.{settingName}`.

See `docs/addon-development.md` for the API usage guide.

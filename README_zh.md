<h1 align="center">Epsilon Addon Template</h1>
<h4 align="center">
    <p>
        <a href="README.md">English</a> |
        <b>中文</b>
    </p>
</h4>

<p align="center">
  <a href="LICENSE"><img alt="许可证" src="https://img.shields.io/badge/license-Apache%202.0-blue?style=flat-square"></a>
  <img alt="加载器" src="https://img.shields.io/badge/loaders-NeoForge%20%26%20Fabric-6a5acd?style=flat-square">
</p>

## 项目简介

这个仓库现在是一个 **Epsilon 的多加载器 addon 模板**。

它把共享 addon 逻辑放在 `common`，并在 `fabric` 与 `neoforge` 中分别提供对应加载器的引导代码。

## 目录结构

- `common`：共享 addon 类与共享资源
- `fabric`：Fabric 启动类与 `epsilon:addon` 自定义入口
- `neoforge`：NeoForge 启动类与 addon 注册事件监听
- `epsilon_libs`：本地开发/调试使用的 Epsilon jar

## 模板内置示例

模板已经包含一个最小 addon 示例：

- 共享 addon：`common/src/main/java/com/example/epsilonaddon/template/EpsilonAddonTemplate.java`
- Fabric 入口：`fabric/src/main/java/com/example/epsilonaddon/template/fabric/EpsilonAddonTemplateFabricEntrypoint.java`
- NeoForge 入口：`neoforge/src/main/java/com/example/epsilonaddon/template/neoforge/EpsilonAddonTemplateNeoForge.java`

开始新项目时，可以直接在这些文件基础上继续改，或者整体替换成你自己的包名和类名。

## 本地 Epsilon 依赖

构建前，需要先把 Epsilon 构建产物放到 `epsilon_libs/` 目录下。

构建脚本会自动识别匹配 `epsilon-common-*.jar`、`epsilon-fabric-*.jar`、`epsilon-neoforge-*.jar` 的 jar 文件（排除 sources jar）。

## 构建

```powershell
.\gradlew.bat build
```

## 运行

```powershell
.\gradlew.bat :fabric:runClient
.\gradlew.bat :neoforge:runClient
```

## Addon 开发说明

- Fabric 通过自定义入口 `epsilon:addon` 注册 addon。
- NeoForge 通过监听 `com.github.epsilon.neoforge.addon.EpsilonAddonSetupEvent` 注册 addon。
- 共享 addon setting 的翻译 key 建议使用 `{addonId}.settings.{settingName}`。

更多 API 用法见 `docs/addon-development.md`。

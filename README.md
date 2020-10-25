<img align="left" alt="logo" width="100" src="https://i.imgur.com/wazC5XA.png">

# Bukkit for Fabric (B4F)
[![Join Discord](https://img.shields.io/badge/Discord-Join-7289DA?logo=discord&style=for-the-badge)](https://discord.gg/Qp4a2Nj) [![Build Status](https://img.shields.io/jenkins/build?jobUrl=http%3A%2F%2Fci.fungus-soft.com%3A8080%2Fjob%2FBukkit4Fabric&style=for-the-badge)](http://ci.javazilla.com:8080/job/Bukkit4Fabric/) [![Fabric 1.16](https://img.shields.io/badge/Fabric-1.16.3-blue?style=for-the-badge)](https://fabricmc.net/use/?page=server)

适用于 FabricAPI 的 Bukkit/Spigot/Paper Modding API 的实现方法

[在CurseForge上查看](https://curseforge.com/minecraft/mc-mods/bukkit) or [在Jenkins上下载](http://bukkitfabric.javazilla.com/)

[English](https://github.com/HGR-Infinite-Community/bukkit4fabric/blob/master/README_en-us.md)


## 开源协议
该项目遵循 GPL-3 协议
包 `org.bukkit.craftbukkit` 经过我们的修改才得以实现的新功能来保证 FabricAPI 对 BukkitAPI 的完美支持

## NMS Support
We do support using Spigot's ``net.minecraft.server`` classes. 

Classes and Fields will automatically remap to their intermediary counterparts *(Including Reflection!)*

> 对更多方法的支持将很快被添加
>

## B4F 基于以下开源项目
* [The Bukkit Project](https://bukkit.org/) 用来获取 BukkitAPI
* [SpigotMC](https://spigotmc.org/) 保证 BukkitAPI 兼容 Spigot 插件
* [PaperMC](https://papermc.io/) 更强大的 BukkitAPI 衍生品
* [Fabric](https://fabricmc.net/) 用来加载 B4F 或添加更多 Mod
* [Mixin](https://github.com/HGR-Infinite-Community/Mixin) 用来混淆 Minecraft 源码来实现 NMS

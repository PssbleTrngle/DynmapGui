[ISSUES]: https://github.com/PssbleTrngle/DynmapGUI/issues

[DOWNLOAD]: https://modrinth.com/project/dynmap-gui/versions

[MODRINTH]: https://modrinth.com/project/dynmap-gui

# Dynmap GUI <!-- modrinth_exclude.start -->

[![Release](https://img.shields.io/github/v/release/PssbleTrngle/DynmapGUI?label=Version&sort=semver)][DOWNLOAD]
[![Issues](https://img.shields.io/github/issues/PssbleTrngle/DynmapGUI?label=Issues)][ISSUES]
[![Modrinth](https://img.shields.io/modrinth/dt/PZKxuodp?color=green&logo=modrinth&logoColor=green)][MODRINTH]

<!-- modrinth_exclude.end -->

[Dynmap](https://modrinth.com/plugin/dynmap) addon which adds a server-side GUI that can be used to see, create or edit markers.

## Usage

The GUI can be accessed using the command 
```
/markers
```
This accesses a list view of all markers. The player can change dimensions, view a specific marker in detail or create new ones (depending on their [permissions](#Permissions)).
Some icons have built-in mapping to specific items, other will simply display as a white banner.

![Marker List GUI](https://raw.githubusercontent.com/PssbleTrngle/DynmapGui/1.20.x/screenshots/list.png)

Permitted players can create a marker at their location by entering a name and then selecting an icon.
If there are multiple marker sets, they may also choose to which they want to add the new marker.

![Create Marker GUI](https://raw.githubusercontent.com/PssbleTrngle/DynmapGui/1.20.x/screenshots/create.png)

Viewing a specific marker in details allows players to edit the name, select a different, move the icon to their current position, delete the icon or even teleport to it (depending on their [permissions](#Permissions))

![Marker Controls GUI](https://raw.githubusercontent.com/PssbleTrngle/DynmapGui/1.20.x/screenshots/controls.png)

## Permissions

The mod uses the dynmap permission system, which means it works with all permission systems supported by the base mod.
The following permissions are defined and can be granted to players:
```yml
- marker.update # Modify a marker, including changing the name or icon
- marker.add # Create new markers
- marker.movehere # Move a marker to their current position
- marker.teleport # Teleport to a selected marker
- marker.delete # Delete a marker
```

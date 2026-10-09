<!-- VERSION-LOCKED-PUBLICATION:START -->
# Just Enough Mekanism Multiblocks: Version Locked

<img src="https://raw.githubusercontent.com/victor0hxz/JEMM-Version-Locked/main/publication/BANNER-VERSION-LOCKED.png" alt="Just Enough Mekanism Multiblocks: Version Locked" width="100%" />

**Minecraft 26.1.2 · NeoForge · Java 25**

[CurseForge](https://www.curseforge.com/minecraft/mc-mods/just-enough-mekanism-multiblocks-unofficial-atm11) · [Downloads](https://github.com/victor0hxz/JEMM-Version-Locked/releases) · [Source](https://github.com/victor0hxz/JEMM-Version-Locked) · [Report an issue](https://github.com/victor0hxz/JEMM-Version-Locked/issues)

An unofficial community port for Minecraft 26.1.2 and NeoForge.

JEI integration displaying Mekanism multiblock construction requirements and supported Mekanism Extras tiers.

**Requirements:** Mekanism Version Locked 2.1; JEI 29.37.0.99. Optional: Generators and Mekanism Extras

## 🔒 Version Locked

The builds distributed here target Minecraft 26.1.2 and Java 25. Files for newer Minecraft versions are not provided by this release.

## Community project

This is a fan-maintained compatibility project by victor0hxz. The upstream developers and the All the Mods team have not endorsed this port. Original contributions remain credited to their respective authors.

## Original project

Original project: https://github.com/gisellevonbingen-Minecraft/JustEnoughMekanismMultiblocks/tree/1.21

Original authors: **Giselle**. Visit the upstream project for official releases and to support its developers.

## Credits and license

This port does not claim ownership of the original code, artwork or assets. The original **MIT** license and copyright notices are preserved with the distribution.

## 🛠️ Bugs and compatibility

Please report port-specific issues at https://github.com/victor0hxz/JEMM-Version-Locked/issues. Include your Minecraft and NeoForge versions, installed mod list, relevant logs and any crash report. Compatibility with every mod combination has not been verified.

---

## 🧩 Explore the Version Locked collection

The related Mekanism ports for Minecraft 26.1.2 are available here:

- [Mekanism: Version Locked](https://www.curseforge.com/minecraft/mc-mods/mekanism-version-locked)
- [Mekanism: Tools Version Locked](https://www.curseforge.com/minecraft/mc-mods/mekanism-tools-version-locked)
- [Mekanism: Generators Version Locked](https://www.curseforge.com/minecraft/mc-mods/mekanism-generators-version-locked)
- [Mekanism: Additions Version Locked](https://www.curseforge.com/minecraft/mc-mods/mekanism-additions-version-locked)

## 🧪 ATM11 compatibility

This distribution was prepared for the ATM11 compatibility project. See the GitHub port report for the validation performed on this build. Further testing in your full modpack is required; compatibility with future pack releases is not guaranteed.

## Installation at a glance

- Minecraft: 26.1.2
- Loader: NeoForge
- Java: 25
- Project type: unofficial community port
- License: MIT
- GitHub, downloads and source documentation: https://github.com/victor0hxz/JEMM-Version-Locked
- Installation: replace older copies of this mod and avoid duplicate mod IDs.

Thank you to Giselle for the original project.

<!-- VERSION-LOCKED-PUBLICATION:END -->

---

## Build and port documentation

# Just Enough Mekanism Multiblocks Version Locked

Unofficial fan-maintained adaptation by victor0hxz for **ATM11 0.9.0 / Minecraft 26.1.2 / NeoForge 26.1.2.109 / Java 25**. This project is not affiliated with or endorsed by the original authors or the ATM team.

Adds eight JEI multiblock calculators with adjustable dimensions, material costs and statistics. Induction matrices support the four Mekanism tiers and four optional Mekanism Extras tiers.

Original authors: **Giselle**. [Upstream project](https://github.com/gisellevonbingen-Minecraft/JustEnoughMekanismMultiblocks/tree/1.21). The original MIT license and copyright notice are preserved. Original production features were retained; test fixtures and dependencies are not bundled in the release JAR.

## Installation

Download the JAR from [Releases](https://github.com/victor0hxz/JEMM-Version-Locked/releases), install the matching dependencies, and replace any older copy of the same mod. Do not install this build alongside the original mod: the mod ID is preserved. These builds target 26.1.2; they are not 1.21.1 builds.

Dependencies tested: Mekanism Version Locked 2.1; JEI 29.37.0.99. Optional: Generators and Mekanism Extras.

## Validation

Compiled and audited for Java 25. Tested in isolated client/server worlds with the ATM11 dependency versions, including applicable functional transfer, production, construction and JEI checks. Full-pack long-running gameplay still needs community testing; the first release is marked as a prerelease. See [Portuguese port report](PORT-REPORT.pt-BR.md) for the exact scope and known limitations. Send port-specific issues to this repository, rather than to the upstream authors.

## Building

Install JDK 25. Download the required dependency JARs yourself from their official projects and put them in `libs/`; this repository does not redistribute dependencies. See [dependency filenames](libs/README.md). Run `gradlew.bat jar` on Windows or `./gradlew jar` elsewhere. For Mekanism Extras use `gradlew.bat -Patm11Smoke jar`, which disables unrelated development runtime mods. Its inherited Spotless task is not part of this command and does not support all Java 25 syntax.

## Créditos e versão de fã

Port não oficial mantido por victor0hxz. Todos os créditos do mod original pertencem a Giselle. As alterações desta versão são de compatibilidade com o ATM11 0.9.0. Não é uma versão oficial do mod nem do modpack.

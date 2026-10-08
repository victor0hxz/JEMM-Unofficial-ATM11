# Just Enough Mekanism Multiblocks - Unofficial ATM11 Compatibility Port

Unofficial fan-maintained adaptation by victor0hxz for **ATM11 0.9.0 / Minecraft 26.1.2 / NeoForge 26.1.2.109 / Java 25**. This project is not affiliated with or endorsed by the original authors or the ATM team.

Adds eight JEI multiblock calculators with adjustable dimensions, material costs and statistics. Induction matrices support the four Mekanism tiers and four optional Mekanism Extras tiers.

Original authors: **Giselle**. [Upstream project](https://github.com/gisellevonbingen-Minecraft/JustEnoughMekanismMultiblocks/tree/1.21). The original MIT license and copyright notice are preserved. Original production features were retained; test fixtures and dependencies are not bundled in the release JAR.

## Installation

Download the JAR from [Releases](https://github.com/victor0hxz/JEMM-Unofficial-ATM11/releases), install the matching dependencies, and replace any older copy of the same mod. Do not install this build alongside the original mod: the mod ID is preserved. These builds target 26.1.2; they are not 1.21.1 builds.

Dependencies tested: Mekanism Version Locked 2.1; JEI 29.37.0.99. Optional: Generators and Mekanism Extras.

## Validation

Compiled and audited for Java 25. Tested in isolated client/server worlds with the ATM11 dependency versions, including applicable functional transfer, production, construction and JEI checks. Full-pack long-running gameplay still needs community testing; the first release is marked as a prerelease. See [Portuguese port report](PORT-REPORT.pt-BR.md) for the exact scope and known limitations. Send port-specific issues to this repository, rather than to the upstream authors.

## Building

Install JDK 25. Download the required dependency JARs yourself from their official projects and put them in `libs/`; this repository does not redistribute dependencies. See [dependency filenames](libs/README.md). Run `gradlew.bat jar` on Windows or `./gradlew jar` elsewhere. For Mekanism Extras use `gradlew.bat -Patm11Smoke jar`, which disables unrelated development runtime mods. Its inherited Spotless task is not part of this command and does not support all Java 25 syntax.

## Créditos e versão de fã

Port não oficial mantido por victor0hxz. Todos os créditos do mod original pertencem a Giselle. As alterações desta versão são de compatibilidade com o ATM11 0.9.0. Não é uma versão oficial do mod nem do modpack.

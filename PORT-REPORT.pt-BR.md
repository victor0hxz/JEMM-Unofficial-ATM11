# Just Enough Mekanism Multiblocks — ATM11 0.9.0

Alvo: ATM11 0.9.0, Minecraft 26.1.2, NeoForge 26.1.2.109, Java 25.

Copie somente o JAR desta pasta para a pasta mods da instância. Substitua versões anteriores do mesmo mod; os JARs de dependências já presentes no ATM11 continuam necessários. Cada mod possui sua própria pasta de entrega.

Os testes foram feitos em mundos de desenvolvimento separados da instância do CurseForge. A instância e os saves do usuário não foram modificados. Os testes confirmam os cenários descritos abaixo; ainda é necessário validar no pack completo, especialmente automação prolongada e integrações com outros mods.

O JAR contém as classes e os recursos do mod, sem incluir cópias das dependências ou as classes de testes. Todos os arquivos Java da produção possuem sua classe no JAR; nenhuma família de recursos foi removida para obter a compilação.

Base oficial MIT, branch 1.21: https://github.com/gisellevonbingen-Minecraft/JustEnoughMekanismMultiblocks/tree/1.21
Dependências: Mekanism Version Locked 2.1 e JEI 29.37.0.99. Mekanism Generators acrescenta suas páginas. Mekanism Extras é opcional para os níveis adicionais.

Port2 mantém as oito categorias e os controles de dimensão do port1. A matriz agora permite selecionar os quatro níveis do Mekanism e os quatro níveis do Extras, quantidades de células/provedores, custos e capacidade/taxa de saída. Os cálculos consultam os valores configurados do Mekanism Extras, limitam a ocupação ao volume interno e saturam a capacidade Infinite em Long.MAX_VALUE. Corrigida a inicialização do resfriamento do reator enquanto os data maps ainda estão chegando do servidor.

Cliente real: oito categorias registradas com receitas; botões de dimensão e custos; oito níveis de matriz; leitura da capacidade configurada; proteção contra overflow; célula Infinite presente nos custos. Páginas abertas com JEI, Sodium e Iris.

Auditoria: 60 classes, 6 arquivos JSON; bytecode Java 25.

JAR: `JustEnoughMekanismMultiblocks-26.1.2-6.5-port2-atm11-0.9.0.jar`

SHA-256: `127da36366680033fc962d8679d00b23af7d2d44575e387186a5dea4a940d83d`

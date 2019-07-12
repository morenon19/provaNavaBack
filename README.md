Prova Nava

Prova nava é a implementação de um case proposto pela impresa Nava, desenvolvido por Daniel Munhoz.


Instalação:

Banco de Dados:

Importar os arquivos .sql que se encontram no diretório "Prova_Nava_Backend/Database" usando o wizard do MySQL Workbench
ou copiando e colando o código.

Back-End:
Inserir no arquivo application que se encontra dentro do diretório "Prova_Nava_Backend/config" a configuração do banco de dados
utilizado.
É necessária a inserção do nome de usuário, senha e caminho de acesso.
Após a configuração de acesso ao banco, executar via terminal o seguinte comando dentro do diretório  "Prova_Nava_Backend":

 	java -jar -Xmx1g -Xms1g -jar prova-nava-0.0.1-SNAPSHOT.jar

Após a execução do comando o back end subirá e já estará pronto para uso.


Front-End:
Caso necessário, trocar dentro do diretório "Prova Nava\Prova_Nava_FrontEnd\app\js\value" no arquivo configValue
o valor da chave "baseUrl" para o endereço onde o back-end está rodando.
Após isso copiar a pasta app para dentro do servidor Web de preferência e acessar via navegador.


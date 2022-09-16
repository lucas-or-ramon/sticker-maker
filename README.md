# Sticker Maker

Este projeto está sendo desenvolvido durante a Imersão Java da Alura, onde o objetivo é aperfeiçoar os conhecimentos em Java e desenvolvimento backend. 

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Sticker Maker**
| :label: Tecnologias | java, spring, aws
| :rocket: URL         | https://sticker-maker-devcanoa.herokuapp.com/api/v1/sticker

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![](https://static.apkdone.me/wp-content/uploads/2020/08/Sticker-Maker-poster.jpg#vitrinedev)

## Detalhes do projeto

Textos e imagens que descrevam seu projeto, suas conquistas, seus desafios, próximos passos, etc...

# Imersão Java da Alura

As APIs do [IMDB](https://imdb-api.com/) e da [NASA](https://api.nasa.gov/) são consumidas buscando imagens. Essas imagens são extraídas e tratadas pelo _sticker-maker_ de forma que seja possível criar Stickers customizados.

Atualmente, o projeto conta com as seguintes funcionalidades:
- Cria um sticker de um dos 250 top filmes do IMDB buscando pelo título em inglês.
- Cria um sticker de alguma imagem disponibilizada pela Nasa durante o período de 12/07/2022 à 22/07/2022 buscando pelo título em inglês.
- Armazena o sticker e disponibiliza uma url para download. 
- Em todos os casos, é possível customizar a mensagem do sticker com os seguintes parâmetros:
  - Fonte (SERIF, DIALOG, SANS_SERIF, MONOSPACED, DIALOG_INPUT)
  - Conteúdo da mensagem
  - Cor (BLUE, CYAN, MAGENTA, GREEN, YELLOW, ORANGE, PINK, RED, BLACK, DARK_GRAY, GRAY, LIGHT_GRAY, WHITE)
  - (Indisponível) Posição na imagem (TOP, BOTTOM, CENTER)

### Aplicação hospedada no Heroku
- Você pode consumir o serviço consultando a api que está hospedada no Heroku:
  - Criar sticker de filme
  ```shell
  curl --location --request POST 'https://sticker-maker-devcanoa.herokuapp.com/api/v1/sticker/imdb?title=Rings' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "color": "BLUE",
      "content": "Épico!",
      "fontSize": 150,
      "fontName": "SANS_SERIF",
      "fontStyle": "BOLD"
  }'
  ```
  - Criar sticker de imagem da Nasa
  ```shell
  curl --location --request POST 'https://sticker-maker-devcanoa.herokuapp.com/api/v1/sticker/nasa?title=Moon' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "color": "BLUE",
      "content": "Maravilhoso!",
      "fontSize": 150,
      "fontName": "SERIF",
      "fontStyle": "ITALIC"
  }'
  ```

### Subir aplicação localmente
- Em todos os casos é necessário informar as variáveis de ambiente:
  - `AWS_ACCESS_KEY_ID`: ID de acesso da AWS
  - `AWS_SECRET_ACCESS_KEY`: Chave de acesso da AWS
  - `bucketName`: Nome do Bucket na AWS para armazenar os arquivos
  - `imdbUrl`: URL da API do IMDB retornando lista de imagens
  - `nasaUrl`: URL da API da Nasa retornando lista de imagens
  
#### Docker
- Pré-requisitos: Docker

#### Procedimento
- Abra o terminal e execute o comando informando as variáveis de ambiente (entre as aspas simples)
```shell
docker run -e nasaUrl='SUA_nasaUrl' \
-e imdbUrl='SUA_imdbUrl' \
-e bucketName='SEU_bucketName' \
-e AWS_ACCESS_KEY_ID='SUA_AWS_ACCESS_KEY_ID' \
-e AWS_SECRET_ACCESS_KEY='SUA_AWS_SECRET_ACCESS_KEY' \
-p 8080:8080 --name sticker-maker lucasorramon/sticker-maker:latest
 ```

#### Gradle
- Pré-requisitos: Java 17+ e Gradle 7+

#### Procedimento
- Abra o terminal e clone o projeto para a sua máquina
```shell
git clone git@github.com:lucas-or-ramon/sticker-maker.git
```
- Entre na pasta do projeto
```shell
cd sticker-maker
```
- Execute o comando para executar a aplicação informando as variáveis de ambiente
```shell
nasaUrl=SUA_nasaUrl \
imdbUrl=SUA_imdbUrl \
bucketName=SEU_bucketName \
AWS_ACCESS_KEY_ID=SUA_AWS_ACCESS_KEY_ID \
AWS_SECRET_ACCESS_KEY=SUA_AWS_SECRET_ACCESS_KEY \ 
gradle clean build bootRun
```

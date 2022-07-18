# Imersão Java da Alura

---
Este projeto foi desenvolvido durante a Imersão Java da Alura, onde o objetivo é aperfeiçoar os conhecimentos em Java e desenvolvimento backend. 

A API do [IMDB](https://imdb-api.com/) é consumida buscando informações de filmes e séries. As informações necessárias são extraídas e tratadas pelo _movie-sticker_ de forma que seja possível criar Stickers a partir dessas produções.

## Aplicação hospedada no Heroku
- https://movie-sticker.herokuapp.com/api/v1/sticker
- A aplicação está retornando, no momento, apenas as informações dos filmes.

## Subir aplicação com Docker
### Pré Requisitos
- Docker

### Procedimento
- Abra o terminal e execute o comando informando a sua apiKey (entre as aspas simples) <br>`docker run -e apiKey='SUA_API_KEY' -p 8080:8080 --name movie-sticker lucasorramon/movie-sticker:latest`

## Subir a aplicação localmente
### Pré Requisitos
- Java 17+
- Gradle 7+

### Procedimento
- Abra o terminal e clone o projeto para a sua máquina <br> `git clone git@github.com:lucas-or-ramon/movie-sticker.git`
- Entre na pasta do projeto <br> `cd movie-sticker`
- Execute o comando para executar a aplicação informando a sua apiKey <br> `apikey=SUA_API_KEY gradle clean build bootRun`

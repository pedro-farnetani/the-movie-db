# The Movie DB

Este projeto é uma aplicação desenvolvida utilizando **Kotlin**, com gerenciamento de dependências e build configurado via **Gradle**.
Ele utiliza **Jetpack Compose** para a construção de interfaces modernas e reativas, e **Hilt** para injeção de dependências.
O objetivo do projeto é fornecer uma experiência interativa para explorar e visualizar informações sobre filmes.

## Tecnologias Utilizadas

- **Kotlin**
- **Gradle**
- **Jetpack Compose**
- **Hilt**

## Estrutura do Projeto

- `features/movie-catalog/src/main/kotlin`: Código-fonte principal do catálogo de filmes.
- `core`: Componentes reutilizáveis e funcionalidades centrais.
- `designsystem`: Componentes de UI compartilhados.

## Como Executar

1. Certifique-se de ter o **JDK** instalado.
2. Clone o repositório:
   ```bash
   git clone git@github.com:pedro-farnetani/the-movie-db.git
   

## Clarificações

Para este projeto foi utilizado SharedElements como ferramenta de navegação, que é uma biblioteca experimental do Jetpack Compose.
Para projetos futuros, recomenda-se utilizar o Navigation Compose, que é a biblioteca oficial de navegação do Jetpack Compose.

## Próximos Passos

- Adicionar a chamada ao retry quando a API falhar.
- Melhorar a experiência do usuário provendo mais informações sobre os filmes.
- Criar um endpoint para retornar os detalhes do filme selecionado.
- Implementar testes instrumentados para garantir a qualidade do código.
- Adicionar novas features
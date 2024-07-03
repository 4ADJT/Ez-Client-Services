# Ez-Blue - The Blue Zone Easy
## Ez-Client-Services

Ez-Client-Services é um microserviço que gerência as informações dos clientes e seus veículos no sistema de parquímetro.

## Intenção

O objetivo deste serviço é fornecer APIs para gerenciar clientes e veículos, incluindo operações de criação, leitura, atualização e exclusão (CRUD).

## Como executar

### Requisitos

- JDK 17+
- Maven 3.6.3+
- Docker (opcional para execução com Docker)

## Executar localmente

1. Clone o repositório:
```sh
git clone https://github.com/4ADJT/Ez-Client-Services.git
cd Ez-Client-Services
```

2. Compile e execute o projeto
```sh
mvn clean package
mvn spring-boot:run
```
## Executar com Docker

1. Compile o projeto e crie a imagem Docker:
```sh
docker build -t ez-client-services .
```

2. Execute o container:
```sh
docker run -p 8082:8082 ez-client-services
```

## Configuração
A configuração do Ez-Client-Services pode ser encontrada no arquivo application.properties na pasta src/main/resources.
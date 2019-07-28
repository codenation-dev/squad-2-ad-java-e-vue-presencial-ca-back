# Centralizador de Erros

## Objetivo
O objetivo deste produto é centralizar os erros de aplicações que ajudarão os desenvolvedores 
no monitoramento dos sistemas e assim tomar decisões mais acertadas.

## Contextualização

Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços 
ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes 
camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. 
Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros
 de erros em um local, de onde podem monitorar e tomar decisões mais acertadas.

### Requisitos Técnicos 

#### Backend
- Deve disponibilizar endpoints para serem usados pelo frontend da aplicação
- Deve gravar os logs de erro em um banco de dados relacional
- Deve permitir acesso a API apenas com um token de autenticação válido

#### Frontend
- Deve implementar as funcionalidades de acordo com os wireframes
- Deve ser acessada por navegadores desktop e mobile
- Deve consumir a API do produto desenvolvida na forma de uma Single Page Application

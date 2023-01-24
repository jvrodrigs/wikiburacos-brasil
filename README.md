<div>
<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://spring.io/images/spring-logo-2022-dark-2f10e8055653ec50e693eb444291d742.svg" width="400"></a></p>
<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://d33wubrfki0l68.cloudfront.net/554c3b0e09cf167f0281fda839a5433f2040b349/ecfc9/img/header_logo.svg" width="100"></a></p>
</div>

# WikiBuracos

A criação do WB vai dar ênfase em uma facilidade maior para as pessoas comunicarem outras sobre 
avenidas, ruas e locais que estão com trânsito devido alguma reforma ou problemas de infraestrutura urbana. 
Com o WB, o usuário poderá cadastrar foto, endereço e uma descrição sobre o que os outros poderem encontrar caso vá por aquele caminho.

Essa aplicação está em desenvolvimento, caso você queira ajudar (dev, mentoria, melhorias) o projeto, enviar um e-mail para:

`` 
João Vitor : jvrodrigs@gmail.com
``

### Projeto

1. [] Base do Projeto
    1. Tecnologias: 
        1. [x] SpringBoot (Java 17)
        2. [] React + Vite 
        3. [x] Docker Back
        4. [] Docker Front 
        5. [] Amazon Web Services (AWS)
2. [] Autenticação
   * [x] Métodos base: Sessão + JWT
   * [] Métodos custom: Google, Github e Linkedin. (Possibilidade para ser estudado ainda.)
3. [x] Modelo de Usuário (wiki_user)
   * Id, Username, Email, Name, Password, Admin, Created At;
4. [x] Modelo de Publicação (wiki_volunteers)
   * Id, Name, Email, Phone, Address, Photo (Link S3), Description, Created At;
5. [] Rotas de Públicas
    1. [x] Listagem das publicações.
    2. [x] Criação de uma nova pulicação.
    3. [x] Mais informações de uma publicação X.
    4. [x] Após o ato da criação, gerar link de compartilhamento.
    5. [x] Criação de Usuário. 
6. [x] Rotas de Autenticação
    1. [x] Autenticação do Usuário.
7. [] Administrativo
    1. [] Modelagem Admin
       1. [x] Página com contador de públicações, usuários diferentes e visitas.
       2. [x] Página visualização das publicações e opções de DELETE.
       3. [] Alguma ação administrativa encima de uma publicação, é necessário informar ao autor via e-mail.
    2. [] Modelagem Usuário
       1. [x] Página com suas públicações.
       2. [x] Página com todas as publicações.
       3. [] Opção de comentário ou forma de avisar que é verídico aquela informação.
8. [] Landing Page
    1. [] Apresentação do Projeto.
    2. [] Como fazer uma públicação.
    3. [] Informações de contato.
9. [] Páginas Administrativas
    1. [] Admin
    2. [] Usuário 

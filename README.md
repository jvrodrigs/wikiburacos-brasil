# WikiBuracos

---

A criação do WB vai dar ênfase em uma facilidade maior para as pessoas comunicarem outras sobre 
avenidas, ruas e locais que estão com trânsito devido alguma reforma ou problemas de infraestrutura urbana. 
Com o WB, o usuário poderá cadastrar foto, endereço e uma descrição sobre o que os outros poderem encontrar caso vá por aquele caminho.

Essa aplicação está em desenvolvimento, caso você queira ajudar (dev, mentoria, melhorias) o projeto, enviar um e-mail para:

`` `
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
    3. [] Mais informações de uma publicação X.
    4. [x] Após o ato da criação, gerar link de compartilhamento.
    5. [x] Criação de Usuário. 
6. [x] Rotas de Autenticação
    1. [x] Autenticação do Usuário.
7. [] Administrativo
    1. [] Modelagem Admin
       1. [] Página com contador de públicação, usuários diferentes e visitas.
       2. [] Página visualização das publicações e opções de READ e DELETE.
       3. [] Alguma ação administrativa encima de uma publicação, é necessário informar ao autor via e-mail.
    2. [] Modelagem Usuário
       1. [] Página com suas públicações.
       2. [] Página com todas as publicações.
       3. [] Opção de comentário ou forma de avisar que é verídico aquela informação.
8. [] Landing Page
    1. [] Apresentação do Projeto.
    2. [] Como fazer uma públicação.
    3. [] Informações de contato.
9. [] Páginas Administrativas
    1. [] Admin
    2. [] Usuário 
## 💳 Integração Stripe com Spring Boot
 
Este projeto demonstra como criar uma sessão de pagamento com Stripe usando Spring Boot e como escutar os webhooks enviados pela Stripe após o pagamento.

## ✅ Funcionalidades

* Criação de vendas (simulando um produto)

* Geração de sessão de checkout do Stripe

* Webhook para receber confirmação de pagamento

* Atualização da venda após pagamento

## 🔧 Tecnologias
* Java 17

* Spring Boot

* Stripe Java SDK

* Maven

* PostgreSQL (ou H2)

* Insomnia ou Postman para testes HTTP

## ▶️ Como executar

1. Clone o projeto:

* git clone git@github.com:wesleydsilva9609/weebhook-api.git

* cd weebhook-api

2. Configure seu application.properties:

* stripe.api.key=sk_test_sua_chave_secreta

* stripe.webhook.secret=whsec_sua_chave_webhook

3. Rode o projeto:

* ./mvnw spring-boot:run

## 🧪 Testar com Stripe CLI
* Login: stripe login

* Inicie o listener: stripe listen --forward-to localhost:8080/webhook/stripe

## 📮 Endpoints principais
POST /vendas → Cadastra uma venda

GET / vendas{id} busca uma venda por id cadastrada no banco

POST /checkout/create-session/{vendaId} → Cria uma sessão de pagamento

POST /webhook/stripe → Recebe eventos do Stripe

POST /webhook/teste → teste usando insonia

json exemplo POST /webhook/stripe(teste no Insomnia)
```
{
  "type": "checkout.session.completed",
  "data": {
    "object": {
      "metadata": {
        "venda": "5"
      }
    }
  }
}
```


resposta esperada: Webhook enviado com sucesso(teste no Insomnia)

e buscando o produto com o id da venda o status é alterado de "pendente" pra "pago"
```
{
	"id": 5,
	"valor": 5000,
	"status": "PAGO"
}
```
## 📝 Exemplo de resposta ao criar sessão

POST  http://localhost:8080/checkout/create-session/5
  ```
json
{
       "id": "cs_test_...",
       "url": "https://checkout.stripe.com/..."
}
``` 

## 🧠 Aprendizados

Durante o desenvolvimento deste projeto, pratiquei e aprendi os seguintes tópicos:

- Integração com a API da Stripe para pagamentos online.
- Criação de sessões de checkout com produtos e preços dinâmicos.
- Configuração e uso de webhooks para escutar eventos de pagamento.
- Simulação de eventos com o Stripe CLI.
- Tratamento de dados com entidades JPA (ex: `Venda`).
- Desenvolvimento de endpoints REST com Spring Boot.
- Uso de variáveis de ambiente para proteger chaves sensíveis.
- Boas práticas de organização em projetos Java.






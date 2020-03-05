#ibge-dtb

Esse proejeto tem como missão expor uma API para os códigos de Estado e Município da [Divisão Territorial do Brasil do IBGE](https://www.ibge.gov.br/explica/codigos-dos-municipios.php).
Como esses dados são fornecidos apenas como planilha, esse projeto baixa essa planilha e modela os dados em forma de API.

Para ver as chamadas possísveis acesse:
```$xslt
GET /api/profile
```

## Exemplo de como obeter os dados de um município:
```$xslt
GET /api/municipios/search/nome_e_estado?nomeCidade=S%C3%A3o+Paulo&siglaEstado=SP
```
Resultado:
```$xslt
{
  "_embedded" : {
    "municipios" : [ {
      "nome" : "São Paulo",
      "codigoIBGE" : 3550308,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/municipios/3550308"
        },
        "municipio" : {
          "href" : "http://localhost:8080/api/municipios/3550308"
        },
        "estado" : {
          "href" : "http://localhost:8080/api/municipios/3550308/estado"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/municipios/search/nome_e_estado?nomeCidade=S%C3%A3o+Paulo&siglaEstado=SP"
    }
  }
}
```

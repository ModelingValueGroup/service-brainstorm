GET `/api/Settings`
Response:
```json
{
  "Environment": "Test",
  "ApiType": "Execution",
  "Authority": "https://login.avo.la",
  "TokenEndpoint": "https://login.avo.la/connect/token",
  "IdentityManager": "https://login.avo.la:444",
  "APIVersion": "2.1.13.0",
  "Organisation": "fabhlth"
}
```

POST `https://login.avo.la/connect/token`
Request:
```
grant_type=client_credentials&client_id=apiclient-FabHLTH-79b34b85833f480196d97ca836988acf&client_secret=hDbMIVGxZzD0lDXx4fBmpNpHNXwTJ4vF74hdCYbocU&scope=avola-api-client
```

Response:
```json
{
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlRaVHlrZEtHaEs1Snd3VjZ6VGlFcFAyNkstayIsImtpZCI6IlRaVHlrZEtHaEs1Snd3VjZ6VGlFcFAyNkstayJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmF2by5sYSIsImF1ZCI6Imh0dHBzOi8vbG9naW4uYXZvLmxhL3Jlc291cmNlcyIsImV4cCI6MTYwMjMzODA5NSwibmJmIjoxNjAyMzM0NDk1LCJjbGllbnRfaWQiOiJhcGljbGllbnQtRmFiSExUSC03OWIzNGI4NTgzM2Y0ODAxOTZkOTdjYTgzNjk4OGFjZiIsInJvbGUiOiJDdXN0b21lckFwaUNsaWVudCIsIm9yZ2FuaXNhdGlvbiI6IkZhYkhMVEgiLCJzY29wZSI6ImF2b2xhLWFwaS1jbGllbnQifQ.S9pezx5gvgI7cI9ndryQz0loZSOwZ3i7LfUAIahtsEl2A55C-fJyWEjcuyD5uKRmh4KsLQPByHrWA3MLSEfxbd-vvLsHoIpiZkeHO2FaOY7GOgz7qs4dG73anj8A5t3T2jTdti0pUMnHg8YIt8ixW__T8-7PjaKmd95Qu9tcgm8tNqc5sglIfVwTkVoykPI_O9BzALikQ8vKD3nhqcYjyD3qQGN9ANIkHb8AGKsKl8ANkzWp0tf_JxoJNZBpCheshb2-oPXNpgVhVHkMpQEbg1iz2LE1etjoYa3VHUw6_pW_iRth0NgJuaFbGBrniJRQ7To__ACBDnvmCMZJNisQeJlsTZgp3CRdmWANRCgA26oojCBU8HJMTCJcTd6QBA5NTOgYtptc1f6T6EsAl_B8hzcJTxdZ57TWSmt71d4nIwW7CFHohAjRx032iYvfSCwMD4d3L-LhG3ldzJ7Nkwid09DY9Lsf6ds2ld5sw527sAHj6TbxMR9ZN3BYjvJDwhPQ5Dw1H-kM8t2oz_cmdgEXFcsbDzpd5DM_SUQ1Aaf-twUxfz4g3zfj1bbqGkuWW3ouhvOBF4hwIHoF3Tc6ukUrrxSLVceFb6LlkjfOJhoWt12jS17j8h1lXRaa-KDkgC0QWQ9uFpE3ipriWo_A6nt2AtukS6jNMDYx9rfba7SvoS8",
  "expires_in": 3600,
  "token_type": "Bearer"
}
```

GET `/api/ApiExecution/decisions/list`
Headers:
`Authorization: Bearer eyJ0eX...`

Response:
```json
[
  {
    "DecisionServiceId": 62,
    "Name": "Triage actie",
    "Versions": [
      {
        "DecisionServiceId": 62,
        "Name": "Triage actie - Version 4",
        "DecisionName": null,
        "DecisionServiceVersionId": 152,
        "VersionNumber": 4,
        "InputData": [
          {
            "BusinessDataId": 706,
            "Version": 1,
            "Name": "Radiologische bevindingen",
            "Type": "ListData",
            "Question": "Wat is de uitkomst van de triage CT-scan?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "114"
              }
            ]
          },
          {
            "BusinessDataId": 720,
            "Version": 1,
            "Name": "Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          },
          {
            "BusinessDataId": 719,
            "Version": 1,
            "Name": "Lichaamstemperatuur",
            "Type": "DecimalNumberData",
            "Question": "Wat is de lichaamstemperatuur van de patiënt bij binnenkomst in het ziekenhuis",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "45.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "24.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "graden Celcius"
              }
            ]
          },
          {
            "BusinessDataId": 704,
            "Version": 1,
            "Name": "Respiratoire klachten",
            "Type": "ListData",
            "Question": "Welke respiratoire klachten heeft de patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "112"
              }
            ]
          },
          {
            "BusinessDataId": 725,
            "Version": 1,
            "Name": "Status eerdere COVID-19 test",
            "Type": "ListData",
            "Question": "Is er bij u recent een COVID-19 test afgenomen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "120"
              }
            ]
          },
          {
            "BusinessDataId": 726,
            "Version": 1,
            "Name": "Status COVID-19 SWAB",
            "Type": "ListData",
            "Question": "Wat is de status van de SWAB?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 720,
            "Version": 1,
            "Name": "Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 728,
            "Version": 1,
            "Name": "Routing MUMC",
            "Type": "ListData",
            "Question": "Waar moet de patiënt naartoe?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "122"
              }
            ]
          },
          {
            "BusinessDataId": 718,
            "Version": 2,
            "Name": "Triage uitkomst",
            "Type": "ListData",
            "Question": "Hoe wordt deze patient getrieerd?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "117"
              }
            ]
          },
          {
            "BusinessDataId": 705,
            "Version": 1,
            "Name": "Covid-19 casusdefinitie",
            "Type": "ListData",
            "Question": "Wat is het ingeschatte risico op Covid-19 besmetting bij deze patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "113"
              }
            ]
          },
          {
            "BusinessDataId": 724,
            "Version": 1,
            "Name": "Positief voor COVID-19",
            "Type": "ListData",
            "Question": "Is de patient positief getest voor COVID-19?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "119"
              }
            ]
          },
          {
            "BusinessDataId": 729,
            "Version": 1,
            "Name": "Soort bed",
            "Type": "ListData",
            "Question": "Welk soort bed moet deze patient krijgen gezien de isolatie eis",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "123"
              }
            ]
          },
          {
            "BusinessDataId": 730,
            "Version": 1,
            "Name": "Maatregelen",
            "Type": "ListData",
            "Question": "Welke isolatiemaatregelen zijn nodig bij deze patiënt",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "124"
              }
            ]
          }
        ],
        "MetaData": [
          {
            "BusinessDataId": 727,
            "Version": 1,
            "Name": null,
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "5"
              },
              {
                "Name": "MetaDataName",
                "Value": "TriageID"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          }
        ],
        "PairData": [],
        "ListData": [
          {
            "ListId": 114,
            "Items": [
              {
                "Id": 1513,
                "Order": 0,
                "Value": "4 Verdachte bevindingen",
                "Name": "4 Verdachte bevindingen"
              },
              {
                "Id": 1514,
                "Order": 0,
                "Value": "3 Geen verdachte bevindingen",
                "Name": "3 Geen verdachte bevindingen"
              },
              {
                "Id": 1544,
                "Order": 0,
                "Value": "1 Nog niet aangevraagd",
                "Name": "1 Nog niet aangevraagd"
              },
              {
                "Id": 1545,
                "Order": 0,
                "Value": "2 Nog niet bekend",
                "Name": "2 Nog niet bekend"
              }
            ]
          },
          {
            "ListId": 118,
            "Items": [
              {
                "Id": 1531,
                "Order": 0,
                "Value": "Geen actie vereist",
                "Name": "Geen actie vereist"
              },
              {
                "Id": 1532,
                "Order": 0,
                "Value": "Controleer temperatuur, SWAB & CT-scan",
                "Name": "Controleer temperatuur, SWAB & CT-scan"
              }
            ]
          },
          {
            "ListId": 112,
            "Items": [
              {
                "Id": 1507,
                "Order": 0,
                "Value": "1 Ja; hoesten en/of kortademigheid",
                "Name": "1 Ja; hoesten en/of kortademigheid"
              },
              {
                "Id": 1508,
                "Order": 0,
                "Value": "2 Nee; geen klachten",
                "Name": "2 Nee; geen klachten"
              }
            ]
          },
          {
            "ListId": 120,
            "Items": [
              {
                "Id": 1537,
                "Order": 0,
                "Value": "1 Nee, niet uitgevoerd",
                "Name": "1 Nee, niet uitgevoerd"
              },
              {
                "Id": 1538,
                "Order": 0,
                "Value": "2 Ja; Geen COVID-19",
                "Name": "2 Ja; Geen COVID-19"
              },
              {
                "Id": 1539,
                "Order": 0,
                "Value": "3 Ja; wel COVID-19",
                "Name": "3 Ja; wel COVID-19"
              }
            ]
          },
          {
            "ListId": 121,
            "Items": [
              {
                "Id": 1540,
                "Order": 0,
                "Value": "1 Niet aangevraagd",
                "Name": "1 Niet aangevraagd"
              },
              {
                "Id": 1541,
                "Order": 0,
                "Value": "2 Nog niet bekend (wel aangevraagd)",
                "Name": "2 Nog niet bekend (wel aangevraagd)"
              },
              {
                "Id": 1542,
                "Order": 0,
                "Value": "3 Negatief voor COVID-19",
                "Name": "3 Negatief voor COVID-19"
              },
              {
                "Id": 1543,
                "Order": 0,
                "Value": "4 Positief voor COVID-19",
                "Name": "4 Positief voor COVID-19"
              }
            ]
          },
          {
            "ListId": 122,
            "Items": [
              {
                "Id": 1546,
                "Order": 0,
                "Value": "Geen isolatieafdeling",
                "Name": "Geen isolatieafdeling"
              },
              {
                "Id": 1547,
                "Order": 0,
                "Value": "AoA",
                "Name": "AoA"
              },
              {
                "Id": 1548,
                "Order": 0,
                "Value": "C4",
                "Name": "C4"
              }
            ]
          },
          {
            "ListId": 117,
            "Items": [
              {
                "Id": 1525,
                "Order": 0,
                "Value": "GROEN (Behandel niet als COVID-19 besmetting)",
                "Name": "GROEN (Behandel niet als COVID-19 besmetting)"
              },
              {
                "Id": 1526,
                "Order": 0,
                "Value": "ROOD (Behandel als Covid-19 besmetting)",
                "Name": "ROOD (Behandel als Covid-19 besmetting)"
              },
              {
                "Id": 1528,
                "Order": 0,
                "Value": "GEEL (Behandel als COVID-19 besmetting)",
                "Name": "GEEL (Behandel als COVID-19 besmetting)"
              },
              {
                "Id": 1530,
                "Order": 0,
                "Value": "Nog niet bekend; additionele informatie nodig",
                "Name": "Nog niet bekend; additionele informatie nodig"
              }
            ]
          },
          {
            "ListId": 113,
            "Items": [
              {
                "Id": 1509,
                "Order": 0,
                "Value": "Verdacht",
                "Name": "Verdacht"
              },
              {
                "Id": 1510,
                "Order": 0,
                "Value": "Niet verdacht",
                "Name": "Niet verdacht"
              },
              {
                "Id": 1533,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 119,
            "Items": [
              {
                "Id": 1534,
                "Order": 0,
                "Value": "Nog niet bepaald",
                "Name": "Nog niet bepaald"
              },
              {
                "Id": 1535,
                "Order": 0,
                "Value": "Positief voor COVID-19",
                "Name": "Positief voor COVID-19"
              },
              {
                "Id": 1536,
                "Order": 0,
                "Value": "Negatief voor COVID-19",
                "Name": "Negatief voor COVID-19"
              }
            ]
          },
          {
            "ListId": 123,
            "Items": [
              {
                "Id": 1549,
                "Order": 0,
                "Value": "Geen restricties",
                "Name": "Geen restricties"
              },
              {
                "Id": 1550,
                "Order": 0,
                "Value": "Cohort",
                "Name": "Cohort"
              },
              {
                "Id": 1551,
                "Order": 0,
                "Value": "Eenpersoonskamer met sluis en manometer",
                "Name": "Eenpersoonskamer met sluis en manometer"
              }
            ]
          },
          {
            "ListId": 124,
            "Items": [
              {
                "Id": 1552,
                "Order": 0,
                "Value": "Strikt virale isolatie",
                "Name": "Strikt virale isolatie"
              },
              {
                "Id": 1553,
                "Order": 0,
                "Value": "Normale hygiene",
                "Name": "Normale hygiene"
              }
            ]
          }
        ]
      },
      {
        "DecisionServiceId": 62,
        "Name": "Triage actie - Version 17",
        "DecisionName": null,
        "DecisionServiceVersionId": 165,
        "VersionNumber": 17,
        "InputData": [
          {
            "BusinessDataId": 747,
            "Version": 1,
            "Name": "Locatie behandeling",
            "Type": "ListData",
            "Question": "Waar wordt de patient hierna verder behandeld?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "132"
              }
            ]
          },
          {
            "BusinessDataId": 706,
            "Version": 2,
            "Name": "CT-Thorax",
            "Type": "ListData",
            "Question": "Wat is de uitkomst van de triage CT-Thorax-scan?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "114"
              }
            ]
          },
          {
            "BusinessDataId": 725,
            "Version": 1,
            "Name": "Status eerdere COVID-19 test",
            "Type": "ListData",
            "Question": "Is er bij u recent een COVID-19 test afgenomen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "120"
              }
            ]
          },
          {
            "BusinessDataId": 726,
            "Version": 1,
            "Name": "Status COVID-19 SWAB",
            "Type": "ListData",
            "Question": "Wat is de status van de SWAB?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 719,
            "Version": 1,
            "Name": "Lichaamstemperatuur",
            "Type": "DecimalNumberData",
            "Question": "Wat is de lichaamstemperatuur van de patiënt bij binnenkomst in het ziekenhuis",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "45.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "24.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "graden Celcius"
              }
            ]
          },
          {
            "BusinessDataId": 735,
            "Version": 1,
            "Name": "Hoesten",
            "Type": "PairData",
            "Question": "Hoest u?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "55"
              }
            ]
          },
          {
            "BusinessDataId": 736,
            "Version": 1,
            "Name": "Keelpijn",
            "Type": "PairData",
            "Question": "Heeft u keelpijn?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "56"
              }
            ]
          },
          {
            "BusinessDataId": 737,
            "Version": 1,
            "Name": "Kortademigheid",
            "Type": "PairData",
            "Question": "Bent u kortademig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "57"
              }
            ]
          },
          {
            "BusinessDataId": 738,
            "Version": 1,
            "Name": "Benauwd",
            "Type": "PairData",
            "Question": "Bent u snotterig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "58"
              }
            ]
          },
          {
            "BusinessDataId": 739,
            "Version": 1,
            "Name": "Verkouden",
            "Type": "PairData",
            "Question": "Bent u verkouden",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "59"
              }
            ]
          },
          {
            "BusinessDataId": 740,
            "Version": 1,
            "Name": "Saturatie",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 745,
            "Version": 1,
            "Name": "Immuungecompromitteerd",
            "Type": "PairData",
            "Question": "Is de patient wel/niet immuungecompromitteerd?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "61"
              }
            ]
          },
          {
            "BusinessDataId": 744,
            "Version": 1,
            "Name": "Leeftijd",
            "Type": "WholeNumberData",
            "Question": "Wat is de leeftijd van de patient?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "130"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 746,
            "Version": 1,
            "Name": "Aantal dagen tussen klachten en test",
            "Type": "WholeNumberData",
            "Question": "Wat is het aantal dagen tussen de start van de klachten en de laatste test?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 720,
            "Version": 1,
            "Name": "Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 728,
            "Version": 1,
            "Name": "Routing MUMC",
            "Type": "ListData",
            "Question": "Waar moet de patiënt naartoe?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "122"
              }
            ]
          },
          {
            "BusinessDataId": 732,
            "Version": 1,
            "Name": "Covid verdenking",
            "Type": "ListData",
            "Question": "Tot welke covidverdenking hoort deze patient",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 733,
            "Version": 1,
            "Name": "Koorts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "127"
              }
            ]
          },
          {
            "BusinessDataId": 704,
            "Version": 1,
            "Name": "Respiratoire klachten",
            "Type": "ListData",
            "Question": "Welke respiratoire klachten heeft de patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "112"
              }
            ]
          },
          {
            "BusinessDataId": 743,
            "Version": 1,
            "Name": "Hertest noodzakelijk",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "131"
              }
            ]
          },
          {
            "BusinessDataId": 729,
            "Version": 1,
            "Name": "Soort bed",
            "Type": "ListData",
            "Question": "Welk soort bed moet deze patient krijgen gezien de isolatie eis",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "123"
              }
            ]
          },
          {
            "BusinessDataId": 730,
            "Version": 1,
            "Name": "Maatregelen",
            "Type": "ListData",
            "Question": "Welke isolatiemaatregelen zijn nodig bij deze patiënt",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "124"
              }
            ]
          }
        ],
        "MetaData": [],
        "PairData": [
          {
            "PairId": 55,
            "ValueForTrue": "Wel hoesten",
            "ValueForFalse": "Niet hoesten"
          },
          {
            "PairId": 56,
            "ValueForTrue": "Wel keelpijn",
            "ValueForFalse": "Geen keelpijn"
          },
          {
            "PairId": 57,
            "ValueForTrue": "Wel kortademig",
            "ValueForFalse": "Niet kortademig"
          },
          {
            "PairId": 58,
            "ValueForTrue": "Wel benauwd",
            "ValueForFalse": "Niet benauwd"
          },
          {
            "PairId": 59,
            "ValueForTrue": "Wel verkouden",
            "ValueForFalse": "Niet verkouden"
          },
          {
            "PairId": 61,
            "ValueForTrue": "Niet immuungecompromitteerd",
            "ValueForFalse": "Wel immuungecompromitteerd"
          }
        ],
        "ListData": [
          {
            "ListId": 132,
            "Items": [
              {
                "Id": 1603,
                "Order": 0,
                "Value": "Thuis / eerste lijn",
                "Name": "Thuis / eerste lijn"
              },
              {
                "Id": 1604,
                "Order": 0,
                "Value": "Behandeling op SEH",
                "Name": "Behandeling op SEH"
              },
              {
                "Id": 1605,
                "Order": 0,
                "Value": "Opname in Ziekenhuis",
                "Name": "Opname in Ziekenhuis"
              }
            ]
          },
          {
            "ListId": 114,
            "Items": [
              {
                "Id": 1513,
                "Order": 5,
                "Value": "3 Verdachte bevindingen",
                "Name": "3 Verdachte bevindingen"
              },
              {
                "Id": 1514,
                "Order": 5,
                "Value": "2 Geen verdachte bevindingen",
                "Name": "2 Geen verdachte bevindingen"
              },
              {
                "Id": 1545,
                "Order": 5,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              }
            ]
          },
          {
            "ListId": 120,
            "Items": [
              {
                "Id": 1537,
                "Order": 0,
                "Value": "1 Nee, niet uitgevoerd",
                "Name": "1 Nee, niet uitgevoerd"
              },
              {
                "Id": 1538,
                "Order": 0,
                "Value": "2 Ja; Geen COVID-19",
                "Name": "2 Ja; Geen COVID-19"
              },
              {
                "Id": 1539,
                "Order": 0,
                "Value": "3 Ja; wel COVID-19",
                "Name": "3 Ja; wel COVID-19"
              }
            ]
          },
          {
            "ListId": 121,
            "Items": [
              {
                "Id": 1540,
                "Order": 0,
                "Value": "1 Niet aangevraagd",
                "Name": "1 Niet aangevraagd"
              },
              {
                "Id": 1541,
                "Order": 0,
                "Value": "2 Aangevraagd; nog niet bekend",
                "Name": "2 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1542,
                "Order": 0,
                "Value": "3 Negatief voor COVID-19",
                "Name": "3 Negatief voor COVID-19"
              },
              {
                "Id": 1543,
                "Order": 0,
                "Value": "4 Positief voor COVID-19",
                "Name": "4 Positief voor COVID-19"
              }
            ]
          },
          {
            "ListId": 118,
            "Items": [
              {
                "Id": 1531,
                "Order": 0,
                "Value": "Geen actie vereist",
                "Name": "Geen actie vereist"
              },
              {
                "Id": 1532,
                "Order": 0,
                "Value": "Controleer temperatuur, SWAB & CT-scan",
                "Name": "Controleer temperatuur, SWAB & CT-scan"
              }
            ]
          },
          {
            "ListId": 122,
            "Items": [
              {
                "Id": 1546,
                "Order": 0,
                "Value": "Geen isolatieafdeling",
                "Name": "Geen isolatieafdeling"
              },
              {
                "Id": 1547,
                "Order": 0,
                "Value": "AoA",
                "Name": "AoA"
              },
              {
                "Id": 1548,
                "Order": 0,
                "Value": "C4",
                "Name": "C4"
              },
              {
                "Id": 1600,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1601,
                "Order": 0,
                "Value": "Naar eerste lijns zorg",
                "Name": "Naar eerste lijns zorg"
              }
            ]
          },
          {
            "ListId": 126,
            "Items": [
              {
                "Id": 1568,
                "Order": 6,
                "Value": "Niet verdacht van COVID-19 besmetting (Groen)",
                "Name": "Niet verdacht van COVID-19 besmetting (Groen)"
              },
              {
                "Id": 1569,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Oranje)",
                "Name": "Verdacht van COVID-19 besmetting (Oranje)"
              },
              {
                "Id": 1570,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Geel)",
                "Name": "Verdacht van COVID-19 besmetting (Geel)"
              },
              {
                "Id": 1571,
                "Order": 6,
                "Value": "COVID-19 vastgesteld (Rood)",
                "Name": "COVID-19 vastgesteld (Rood)"
              },
              {
                "Id": 1572,
                "Order": 6,
                "Value": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)",
                "Name": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)"
              },
              {
                "Id": 1573,
                "Order": 6,
                "Value": "Nog niet bekend; additionele informatie nodig",
                "Name": "Nog niet bekend; additionele informatie nodig"
              }
            ]
          },
          {
            "ListId": 127,
            "Items": [
              {
                "Id": 1575,
                "Order": 0,
                "Value": "Temperatuur onbekend",
                "Name": "Temperatuur onbekend"
              },
              {
                "Id": 1576,
                "Order": 0,
                "Value": "Koortsvrij",
                "Name": "Koortsvrij"
              },
              {
                "Id": 1577,
                "Order": 0,
                "Value": "Verhoging",
                "Name": "Verhoging"
              },
              {
                "Id": 1578,
                "Order": 0,
                "Value": "Koorts",
                "Name": "Koorts"
              }
            ]
          },
          {
            "ListId": 112,
            "Items": [
              {
                "Id": 1507,
                "Order": 1,
                "Value": "Wel respiratoire klachten",
                "Name": "Wel respiratoire klachten"
              },
              {
                "Id": 1508,
                "Order": 1,
                "Value": "Geen respiratoire klachten",
                "Name": "Geen respiratoire klachten"
              },
              {
                "Id": 1596,
                "Order": 0,
                "Value": "Niet ingevuld",
                "Name": "Niet ingevuld"
              }
            ]
          },
          {
            "ListId": 131,
            "Items": [
              {
                "Id": 1597,
                "Order": 0,
                "Value": "Hertest niet noodzakelijk",
                "Name": "Hertest niet noodzakelijk"
              },
              {
                "Id": 1598,
                "Order": 0,
                "Value": "Hertest wel noodzakelijk",
                "Name": "Hertest wel noodzakelijk"
              }
            ]
          },
          {
            "ListId": 123,
            "Items": [
              {
                "Id": 1549,
                "Order": 0,
                "Value": "Geen restricties",
                "Name": "Geen restricties"
              },
              {
                "Id": 1550,
                "Order": 0,
                "Value": "Cohort",
                "Name": "Cohort"
              },
              {
                "Id": 1551,
                "Order": 0,
                "Value": "Eenpersoonskamer met sluis en manometer",
                "Name": "Eenpersoonskamer met sluis en manometer"
              },
              {
                "Id": 1599,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1606,
                "Order": 0,
                "Value": "Niet van toepassing",
                "Name": "Niet van toepassing"
              }
            ]
          },
          {
            "ListId": 124,
            "Items": [
              {
                "Id": 1552,
                "Order": 0,
                "Value": "Strikt virale isolatie",
                "Name": "Strikt virale isolatie"
              },
              {
                "Id": 1553,
                "Order": 0,
                "Value": "Normale hygiene",
                "Name": "Normale hygiene"
              },
              {
                "Id": 1602,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              }
            ]
          }
        ]
      },
      {
        "DecisionServiceId": 62,
        "Name": "SEH Triage actie - Version 21",
        "DecisionName": null,
        "DecisionServiceVersionId": 171,
        "VersionNumber": 21,
        "InputData": [
          {
            "BusinessDataId": 747,
            "Version": 2,
            "Name": "SEH Locatie behandeling",
            "Type": "ListData",
            "Question": "Waar wordt de patient hierna verder behandeld?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "132"
              }
            ]
          },
          {
            "BusinessDataId": 706,
            "Version": 3,
            "Name": "SEH CT-Thorax",
            "Type": "ListData",
            "Question": "Wat is de uitkomst van de triage CT-Thorax-scan?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "114"
              }
            ]
          },
          {
            "BusinessDataId": 725,
            "Version": 2,
            "Name": "SEH Status eerdere COVID-19 test",
            "Type": "ListData",
            "Question": "Is er bij u recent een COVID-19 test afgenomen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "120"
              }
            ]
          },
          {
            "BusinessDataId": 726,
            "Version": 2,
            "Name": "SEH Status COVID-19 SWAB",
            "Type": "ListData",
            "Question": "Wat is de status van de SWAB?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 719,
            "Version": 2,
            "Name": "SEH Lichaamstemperatuur",
            "Type": "DecimalNumberData",
            "Question": "Wat is de lichaamstemperatuur van de patiënt bij binnenkomst in het ziekenhuis",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "45.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "24.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "graden Celcius"
              }
            ]
          },
          {
            "BusinessDataId": 735,
            "Version": 2,
            "Name": "SEH Hoesten",
            "Type": "PairData",
            "Question": "Hoest u?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "55"
              }
            ]
          },
          {
            "BusinessDataId": 736,
            "Version": 2,
            "Name": "SEH Keelpijn",
            "Type": "PairData",
            "Question": "Heeft u keelpijn?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "56"
              }
            ]
          },
          {
            "BusinessDataId": 737,
            "Version": 2,
            "Name": "SEH Kortademigheid",
            "Type": "PairData",
            "Question": "Bent u kortademig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "57"
              }
            ]
          },
          {
            "BusinessDataId": 738,
            "Version": 2,
            "Name": "SEH Benauwd",
            "Type": "PairData",
            "Question": "Bent u snotterig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "58"
              }
            ]
          },
          {
            "BusinessDataId": 739,
            "Version": 2,
            "Name": "SEH Verkouden",
            "Type": "PairData",
            "Question": "Bent u verkouden",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "59"
              }
            ]
          },
          {
            "BusinessDataId": 740,
            "Version": 3,
            "Name": "SEH Saturatie (gemeten in tent)",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 745,
            "Version": 2,
            "Name": "SEH Immuungecompromitteerd",
            "Type": "PairData",
            "Question": "Is de patient wel/niet immuungecompromitteerd?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "61"
              }
            ]
          },
          {
            "BusinessDataId": 744,
            "Version": 2,
            "Name": "SEH Leeftijd",
            "Type": "WholeNumberData",
            "Question": "Wat is de leeftijd van de patient?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "130"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 746,
            "Version": 2,
            "Name": "SEH Aantal dagen tussen klachten en test",
            "Type": "WholeNumberData",
            "Question": "Wat is het aantal dagen tussen de start van de klachten en de laatste test?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 720,
            "Version": 2,
            "Name": "SEH Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 728,
            "Version": 2,
            "Name": "SEH Routing MUMC",
            "Type": "ListData",
            "Question": "Waar moet de patiënt naartoe?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "122"
              }
            ]
          },
          {
            "BusinessDataId": 732,
            "Version": 3,
            "Name": "SEH Covid verdenking initieel",
            "Type": "ListData",
            "Question": "Tot welke covidverdenking hoort deze patient",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 733,
            "Version": 2,
            "Name": "SEH Koorts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "127"
              }
            ]
          },
          {
            "BusinessDataId": 704,
            "Version": 2,
            "Name": "SEH Respiratoire klachten",
            "Type": "ListData",
            "Question": "Welke respiratoire klachten heeft de patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "112"
              }
            ]
          },
          {
            "BusinessDataId": 743,
            "Version": 2,
            "Name": "SEH Hertest noodzakelijk",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "131"
              }
            ]
          },
          {
            "BusinessDataId": 729,
            "Version": 2,
            "Name": "SEH Soort bed",
            "Type": "ListData",
            "Question": "Welk soort bed moet deze patient krijgen gezien de isolatie eis",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "123"
              }
            ]
          },
          {
            "BusinessDataId": 730,
            "Version": 2,
            "Name": "SEH Maatregelen",
            "Type": "ListData",
            "Question": "Welke isolatiemaatregelen zijn nodig bij deze patiënt",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "124"
              }
            ]
          }
        ],
        "MetaData": [],
        "PairData": [
          {
            "PairId": 55,
            "ValueForTrue": "Wel hoesten",
            "ValueForFalse": "Niet hoesten"
          },
          {
            "PairId": 56,
            "ValueForTrue": "Wel keelpijn",
            "ValueForFalse": "Geen keelpijn"
          },
          {
            "PairId": 57,
            "ValueForTrue": "Wel kortademig",
            "ValueForFalse": "Niet kortademig"
          },
          {
            "PairId": 58,
            "ValueForTrue": "Wel benauwd",
            "ValueForFalse": "Niet benauwd"
          },
          {
            "PairId": 59,
            "ValueForTrue": "Wel verkouden",
            "ValueForFalse": "Niet verkouden"
          },
          {
            "PairId": 61,
            "ValueForTrue": "Niet immuungecompromitteerd",
            "ValueForFalse": "Wel immuungecompromitteerd"
          }
        ],
        "ListData": [
          {
            "ListId": 132,
            "Items": [
              {
                "Id": 1603,
                "Order": 0,
                "Value": "Thuis / eerste lijn",
                "Name": "Thuis / eerste lijn"
              },
              {
                "Id": 1604,
                "Order": 0,
                "Value": "Behandeling op SEH",
                "Name": "Behandeling op SEH"
              },
              {
                "Id": 1605,
                "Order": 0,
                "Value": "Opname in Ziekenhuis",
                "Name": "Opname in Ziekenhuis"
              }
            ]
          },
          {
            "ListId": 114,
            "Items": [
              {
                "Id": 1513,
                "Order": 5,
                "Value": "3 Verdachte bevindingen",
                "Name": "3 Verdachte bevindingen"
              },
              {
                "Id": 1514,
                "Order": 5,
                "Value": "2 Geen verdachte bevindingen",
                "Name": "2 Geen verdachte bevindingen"
              },
              {
                "Id": 1545,
                "Order": 5,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              }
            ]
          },
          {
            "ListId": 120,
            "Items": [
              {
                "Id": 1537,
                "Order": 0,
                "Value": "1 Nee, niet uitgevoerd",
                "Name": "1 Nee, niet uitgevoerd"
              },
              {
                "Id": 1538,
                "Order": 0,
                "Value": "2 Ja; Geen COVID-19",
                "Name": "2 Ja; Geen COVID-19"
              },
              {
                "Id": 1539,
                "Order": 0,
                "Value": "3 Ja; wel COVID-19",
                "Name": "3 Ja; wel COVID-19"
              }
            ]
          },
          {
            "ListId": 121,
            "Items": [
              {
                "Id": 1540,
                "Order": 0,
                "Value": "1 Niet aangevraagd",
                "Name": "1 Niet aangevraagd"
              },
              {
                "Id": 1541,
                "Order": 0,
                "Value": "2 Aangevraagd; nog niet bekend",
                "Name": "2 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1542,
                "Order": 0,
                "Value": "3 Negatief voor COVID-19",
                "Name": "3 Negatief voor COVID-19"
              },
              {
                "Id": 1543,
                "Order": 0,
                "Value": "4 Positief voor COVID-19",
                "Name": "4 Positief voor COVID-19"
              }
            ]
          },
          {
            "ListId": 118,
            "Items": [
              {
                "Id": 1531,
                "Order": 0,
                "Value": "Geen actie vereist",
                "Name": "Geen actie vereist"
              },
              {
                "Id": 1532,
                "Order": 0,
                "Value": "Controleer temperatuur, SWAB & CT-scan",
                "Name": "Controleer temperatuur, SWAB & CT-scan"
              }
            ]
          },
          {
            "ListId": 122,
            "Items": [
              {
                "Id": 1546,
                "Order": 0,
                "Value": "Geen isolatieafdeling",
                "Name": "Geen isolatieafdeling"
              },
              {
                "Id": 1547,
                "Order": 0,
                "Value": "AoA",
                "Name": "AoA"
              },
              {
                "Id": 1548,
                "Order": 0,
                "Value": "C4",
                "Name": "C4"
              },
              {
                "Id": 1600,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1601,
                "Order": 0,
                "Value": "Naar eerste lijns zorg",
                "Name": "Naar eerste lijns zorg"
              },
              {
                "Id": 1664,
                "Order": 0,
                "Value": "A1",
                "Name": "A1"
              }
            ]
          },
          {
            "ListId": 126,
            "Items": [
              {
                "Id": 1568,
                "Order": 6,
                "Value": "Niet verdacht van COVID-19 besmetting (Groen)",
                "Name": "Niet verdacht van COVID-19 besmetting (Groen)"
              },
              {
                "Id": 1569,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Oranje)",
                "Name": "Verdacht van COVID-19 besmetting (Oranje)"
              },
              {
                "Id": 1570,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Geel)",
                "Name": "Verdacht van COVID-19 besmetting (Geel)"
              },
              {
                "Id": 1571,
                "Order": 6,
                "Value": "COVID-19 vastgesteld (Rood)",
                "Name": "COVID-19 vastgesteld (Rood)"
              },
              {
                "Id": 1572,
                "Order": 6,
                "Value": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)",
                "Name": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)"
              },
              {
                "Id": 1573,
                "Order": 6,
                "Value": "Nog niet bekend; additionele informatie nodig",
                "Name": "Nog niet bekend; additionele informatie nodig"
              }
            ]
          },
          {
            "ListId": 127,
            "Items": [
              {
                "Id": 1575,
                "Order": 0,
                "Value": "Temperatuur onbekend",
                "Name": "Temperatuur onbekend"
              },
              {
                "Id": 1576,
                "Order": 0,
                "Value": "Koortsvrij",
                "Name": "Koortsvrij"
              },
              {
                "Id": 1577,
                "Order": 0,
                "Value": "Verhoging",
                "Name": "Verhoging"
              },
              {
                "Id": 1578,
                "Order": 0,
                "Value": "Koorts",
                "Name": "Koorts"
              }
            ]
          },
          {
            "ListId": 112,
            "Items": [
              {
                "Id": 1507,
                "Order": 1,
                "Value": "Wel respiratoire klachten",
                "Name": "Wel respiratoire klachten"
              },
              {
                "Id": 1508,
                "Order": 1,
                "Value": "Geen respiratoire klachten",
                "Name": "Geen respiratoire klachten"
              },
              {
                "Id": 1596,
                "Order": 0,
                "Value": "Niet ingevuld",
                "Name": "Niet ingevuld"
              }
            ]
          },
          {
            "ListId": 131,
            "Items": [
              {
                "Id": 1597,
                "Order": 0,
                "Value": "Hertest niet noodzakelijk",
                "Name": "Hertest niet noodzakelijk"
              },
              {
                "Id": 1598,
                "Order": 0,
                "Value": "Hertest wel noodzakelijk",
                "Name": "Hertest wel noodzakelijk"
              }
            ]
          },
          {
            "ListId": 123,
            "Items": [
              {
                "Id": 1549,
                "Order": 0,
                "Value": "Geen restricties",
                "Name": "Geen restricties"
              },
              {
                "Id": 1550,
                "Order": 0,
                "Value": "Cohort",
                "Name": "Cohort"
              },
              {
                "Id": 1551,
                "Order": 0,
                "Value": "Eenpersoonskamer met sluis en manometer",
                "Name": "Eenpersoonskamer met sluis en manometer"
              },
              {
                "Id": 1599,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1606,
                "Order": 0,
                "Value": "Niet van toepassing",
                "Name": "Niet van toepassing"
              }
            ]
          },
          {
            "ListId": 124,
            "Items": [
              {
                "Id": 1552,
                "Order": 0,
                "Value": "Strikt virale isolatie",
                "Name": "Strikt virale isolatie"
              },
              {
                "Id": 1553,
                "Order": 0,
                "Value": "Normale hygiene",
                "Name": "Normale hygiene"
              },
              {
                "Id": 1602,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              }
            ]
          }
        ]
      },
      {
        "DecisionServiceId": 62,
        "Name": "SEH Triage actie - Version 38",
        "DecisionName": null,
        "DecisionServiceVersionId": 190,
        "VersionNumber": 38,
        "InputData": [
          {
            "BusinessDataId": 747,
            "Version": 2,
            "Name": "SEH Locatie behandeling",
            "Type": "ListData",
            "Question": "Waar wordt de patient hierna verder behandeld?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "132"
              }
            ]
          },
          {
            "BusinessDataId": 726,
            "Version": 2,
            "Name": "SEH Status COVID-19 SWAB",
            "Type": "ListData",
            "Question": "Wat is de status van de SWAB?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 850,
            "Version": 1,
            "Name": "ZKH Status Covid-19 SWAB hertest",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 706,
            "Version": 3,
            "Name": "SEH CT-Thorax",
            "Type": "ListData",
            "Question": "Wat is de uitkomst van de triage CT-Thorax-scan?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "114"
              }
            ]
          },
          {
            "BusinessDataId": 745,
            "Version": 2,
            "Name": "SEH Immuungecompromitteerd",
            "Type": "PairData",
            "Question": "Is de patient wel/niet immuungecompromitteerd?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "61"
              }
            ]
          },
          {
            "BusinessDataId": 744,
            "Version": 2,
            "Name": "SEH Leeftijd",
            "Type": "WholeNumberData",
            "Question": "Wat is de leeftijd van de patient?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "130"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 735,
            "Version": 2,
            "Name": "SEH Hoesten",
            "Type": "PairData",
            "Question": "Hoest u?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "55"
              }
            ]
          },
          {
            "BusinessDataId": 736,
            "Version": 2,
            "Name": "SEH Keelpijn",
            "Type": "PairData",
            "Question": "Heeft u keelpijn?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "56"
              }
            ]
          },
          {
            "BusinessDataId": 737,
            "Version": 2,
            "Name": "SEH Kortademigheid",
            "Type": "PairData",
            "Question": "Bent u kortademig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "57"
              }
            ]
          },
          {
            "BusinessDataId": 738,
            "Version": 2,
            "Name": "SEH Benauwd",
            "Type": "PairData",
            "Question": "Bent u snotterig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "58"
              }
            ]
          },
          {
            "BusinessDataId": 739,
            "Version": 2,
            "Name": "SEH Verkouden",
            "Type": "PairData",
            "Question": "Bent u verkouden",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "59"
              }
            ]
          },
          {
            "BusinessDataId": 845,
            "Version": 1,
            "Name": "SEH Datum Ontstaan Respiratoire Klachten",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 857,
            "Version": 1,
            "Name": "SEH Datum afname eerste SWAB",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 740,
            "Version": 3,
            "Name": "SEH Saturatie (gemeten in tent)",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 842,
            "Version": 1,
            "Name": "SEH Braken",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "205"
              }
            ]
          },
          {
            "BusinessDataId": 843,
            "Version": 2,
            "Name": "SEH Diarree",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "206"
              }
            ]
          },
          {
            "BusinessDataId": 782,
            "Version": 2,
            "Name": "SEH Datum binnenkomst op SEH",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 846,
            "Version": 1,
            "Name": "SEH Datum Ontstaan Intestinale Klachten",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 848,
            "Version": 1,
            "Name": "SEH COVID-19 verdenking door Arts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "207"
              }
            ]
          },
          {
            "BusinessDataId": 719,
            "Version": 2,
            "Name": "SEH Lichaamstemperatuur",
            "Type": "DecimalNumberData",
            "Question": "Wat is de lichaamstemperatuur van de patiënt bij binnenkomst in het ziekenhuis",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "45.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "24.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "graden Celcius"
              }
            ]
          },
          {
            "BusinessDataId": 725,
            "Version": 2,
            "Name": "SEH Status eerdere COVID-19 test",
            "Type": "ListData",
            "Question": "Is er bij u recent een COVID-19 test afgenomen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "120"
              }
            ]
          },
          {
            "BusinessDataId": 858,
            "Version": 1,
            "Name": "SEH Datum hertest SWAB",
            "Type": "DateTimeData",
            "Question": "Op welke datum is de COVID-19 SWAB hertest afgenomen?",
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 855,
            "Version": 1,
            "Name": "SEH reden bezoek aan SEH",
            "Type": "ListData",
            "Question": "Wat is de reden voor het bezoek aan de SEH?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "210"
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 720,
            "Version": 2,
            "Name": "SEH Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 728,
            "Version": 3,
            "Name": "SEH Routing",
            "Type": "ListData",
            "Question": "Waar moet de patiënt naartoe?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "122"
              }
            ]
          },
          {
            "BusinessDataId": 844,
            "Version": 1,
            "Name": "ZKH COVID vaststelling",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 743,
            "Version": 2,
            "Name": "SEH Hertest noodzakelijk",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "131"
              }
            ]
          },
          {
            "BusinessDataId": 852,
            "Version": 1,
            "Name": "SEH Hertesten vanwege recentheid klachten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "208"
              }
            ]
          },
          {
            "BusinessDataId": 704,
            "Version": 2,
            "Name": "SEH Respiratoire klachten",
            "Type": "ListData",
            "Question": "Welke respiratoire klachten heeft de patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "112"
              }
            ]
          },
          {
            "BusinessDataId": 780,
            "Version": 2,
            "Name": "SEH aantal dagen respiratoire klachten",
            "Type": "TimespanData",
            "Question": "Hoelang heeft de patient al respiratoire klachten",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "TimespanUnit",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 841,
            "Version": 1,
            "Name": "SEH Saturatie OK",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "204"
              }
            ]
          },
          {
            "BusinessDataId": 836,
            "Version": 2,
            "Name": "SEH Acuut intestinale klachten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "200"
              }
            ]
          },
          {
            "BusinessDataId": 853,
            "Version": 1,
            "Name": "SEH aantal dagen intestinale klachten",
            "Type": "TimespanData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "TimespanUnit",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 777,
            "Version": 1,
            "Name": "SEH Covid verdenking definitief",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 835,
            "Version": 1,
            "Name": "SEH Covid verdenking aanmelding",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 733,
            "Version": 2,
            "Name": "SEH Koorts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "127"
              }
            ]
          },
          {
            "BusinessDataId": 838,
            "Version": 1,
            "Name": "SEH Status eerdere COVID-19 test definitief",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "202"
              }
            ]
          },
          {
            "BusinessDataId": 916,
            "Version": 1,
            "Name": "SEH Validiteit hertest",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "211"
              }
            ]
          },
          {
            "BusinessDataId": 887,
            "Version": 1,
            "Name": "SEH Datum hertest vanaf",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 729,
            "Version": 2,
            "Name": "SEH Soort bed",
            "Type": "ListData",
            "Question": "Welk soort bed moet deze patient krijgen gezien de isolatie eis",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "123"
              }
            ]
          },
          {
            "BusinessDataId": 730,
            "Version": 2,
            "Name": "SEH Maatregelen",
            "Type": "ListData",
            "Question": "Welke isolatiemaatregelen zijn nodig bij deze patiënt",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "124"
              }
            ]
          },
          {
            "BusinessDataId": 854,
            "Version": 1,
            "Name": "SEH CT-Thorax aanvragen",
            "Type": "ListData",
            "Question": "Moet ik voor deze patient een CT-Thorax aanvragen",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "209"
              }
            ]
          }
        ],
        "MetaData": [
          {
            "BusinessDataId": 727,
            "Version": 2,
            "Name": null,
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "6"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH META TriageID"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 849,
            "Version": 2,
            "Name": null,
            "Type": "TextData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "8"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH Toelichting aanpassing COVID-19 verdenking"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "CaseSensitive",
                "Value": ""
              },
              {
                "Name": "MaximumTextLength",
                "Value": ""
              }
            ]
          },
          {
            "BusinessDataId": 847,
            "Version": 1,
            "Name": null,
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "9"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH Datum Saturatiemeting"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 858,
            "Version": 1,
            "Name": null,
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "10"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH Datum hertest SWAB"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 859,
            "Version": 1,
            "Name": null,
            "Type": "PairData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "11"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH META Printknop gebruikt"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "PairId",
                "Value": "62"
              }
            ]
          }
        ],
        "PairData": [
          {
            "PairId": 61,
            "ValueForTrue": "Niet immuungecompromitteerd",
            "ValueForFalse": "Wel immuungecompromitteerd"
          },
          {
            "PairId": 55,
            "ValueForTrue": "Wel hoesten",
            "ValueForFalse": "Niet hoesten"
          },
          {
            "PairId": 56,
            "ValueForTrue": "Wel keelpijn",
            "ValueForFalse": "Geen keelpijn"
          },
          {
            "PairId": 57,
            "ValueForTrue": "Wel kortademig",
            "ValueForFalse": "Niet kortademig"
          },
          {
            "PairId": 58,
            "ValueForTrue": "Wel benauwd",
            "ValueForFalse": "Niet benauwd"
          },
          {
            "PairId": 59,
            "ValueForTrue": "Wel verkouden",
            "ValueForFalse": "Niet verkouden"
          },
          {
            "PairId": 62,
            "ValueForTrue": "Wel Geprint",
            "ValueForFalse": "Niet Geprint"
          }
        ],
        "ListData": [
          {
            "ListId": 132,
            "Items": [
              {
                "Id": 1603,
                "Order": 0,
                "Value": "Thuis / eerste lijn",
                "Name": "Thuis / eerste lijn"
              },
              {
                "Id": 1604,
                "Order": 0,
                "Value": "Behandeling op SEH",
                "Name": "Behandeling op SEH"
              },
              {
                "Id": 1605,
                "Order": 0,
                "Value": "Opname in Ziekenhuis",
                "Name": "Opname in Ziekenhuis"
              }
            ]
          },
          {
            "ListId": 121,
            "Items": [
              {
                "Id": 1541,
                "Order": 0,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1542,
                "Order": 0,
                "Value": "2 Negatief voor COVID-19",
                "Name": "2 Negatief voor COVID-19"
              },
              {
                "Id": 1543,
                "Order": 0,
                "Value": "3 Positief voor COVID-19",
                "Name": "3 Positief voor COVID-19"
              },
              {
                "Id": 1864,
                "Order": 0,
                "Value": "0 Niet aangevraagd",
                "Name": "0 Niet aangevraagd"
              }
            ]
          },
          {
            "ListId": 114,
            "Items": [
              {
                "Id": 1513,
                "Order": 5,
                "Value": "3 Verdachte bevindingen",
                "Name": "3 Verdachte bevindingen"
              },
              {
                "Id": 1514,
                "Order": 5,
                "Value": "2 Geen verdachte bevindingen",
                "Name": "2 Geen verdachte bevindingen"
              },
              {
                "Id": 1545,
                "Order": 5,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1863,
                "Order": 0,
                "Value": "0 Niet aangevraagd",
                "Name": "0 Niet aangevraagd"
              }
            ]
          },
          {
            "ListId": 205,
            "Items": [
              {
                "Id": 1824,
                "Order": 0,
                "Value": "Wel Braken",
                "Name": "Wel Braken"
              },
              {
                "Id": 1825,
                "Order": 0,
                "Value": "Niet Braken",
                "Name": "Niet Braken"
              }
            ]
          },
          {
            "ListId": 206,
            "Items": [
              {
                "Id": 1826,
                "Order": 0,
                "Value": "Wel Diarree",
                "Name": "Wel Diarree"
              },
              {
                "Id": 1827,
                "Order": 0,
                "Value": "Geen Diarree",
                "Name": "Geen Diarree"
              }
            ]
          },
          {
            "ListId": 207,
            "Items": [
              {
                "Id": 1828,
                "Order": 0,
                "Value": "1 COVID-19 verdenking niet aanpassen",
                "Name": "1 COVID-19 verdenking niet aanpassen"
              },
              {
                "Id": 1829,
                "Order": 0,
                "Value": "3 Lage verdenking van COVID-19 (GEEL)",
                "Name": "3 Lage verdenking van COVID-19 (GEEL)"
              },
              {
                "Id": 1830,
                "Order": 0,
                "Value": "4 Hoge verdenking van COVID-19 (ORANJE)",
                "Name": "4 Hoge verdenking van COVID-19 (ORANJE)"
              },
              {
                "Id": 1831,
                "Order": 0,
                "Value": "2 Geen verdenking van COVID-19 (GROEN)",
                "Name": "2 Geen verdenking van COVID-19 (GROEN)"
              }
            ]
          },
          {
            "ListId": 120,
            "Items": [
              {
                "Id": 1537,
                "Order": 0,
                "Value": "1 Nee, niet uitgevoerd",
                "Name": "1 Nee, niet uitgevoerd"
              },
              {
                "Id": 1538,
                "Order": 0,
                "Value": "2 Ja; Geen COVID-19",
                "Name": "2 Ja; Geen COVID-19"
              },
              {
                "Id": 1539,
                "Order": 0,
                "Value": "3 Ja; wel COVID-19",
                "Name": "3 Ja; wel COVID-19"
              }
            ]
          },
          {
            "ListId": 210,
            "Items": [
              {
                "Id": 1836,
                "Order": 0,
                "Value": "1 Respiratoire klachten",
                "Name": "1 Respiratoire klachten"
              },
              {
                "Id": 1837,
                "Order": 0,
                "Value": "2 Intestinale klachten",
                "Name": "2 Intestinale klachten"
              },
              {
                "Id": 1838,
                "Order": 0,
                "Value": "3 Anders",
                "Name": "3 Anders"
              }
            ]
          },
          {
            "ListId": 118,
            "Items": [
              {
                "Id": 1531,
                "Order": 0,
                "Value": "Geen actie vereist",
                "Name": "Geen actie vereist"
              },
              {
                "Id": 1532,
                "Order": 0,
                "Value": "Controleer temperatuur, SWAB & CT-scan",
                "Name": "Controleer temperatuur, SWAB & CT-scan"
              }
            ]
          },
          {
            "ListId": 122,
            "Items": [
              {
                "Id": 1600,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1601,
                "Order": 0,
                "Value": "Naar eerste lijns zorg",
                "Name": "Naar eerste lijns zorg"
              },
              {
                "Id": 1839,
                "Order": 0,
                "Value": "SEH Groen",
                "Name": "SEH Groen"
              },
              {
                "Id": 1840,
                "Order": 0,
                "Value": "SEH Geel",
                "Name": "SEH Geel"
              },
              {
                "Id": 1841,
                "Order": 0,
                "Value": "SEH Oranje",
                "Name": "SEH Oranje"
              },
              {
                "Id": 1842,
                "Order": 0,
                "Value": "SEH Rood",
                "Name": "SEH Rood"
              },
              {
                "Id": 1843,
                "Order": 0,
                "Value": "Opname Groen",
                "Name": "Opname Groen"
              },
              {
                "Id": 1844,
                "Order": 0,
                "Value": "Opname Geel",
                "Name": "Opname Geel"
              },
              {
                "Id": 1845,
                "Order": 0,
                "Value": "Opname Oranje",
                "Name": "Opname Oranje"
              },
              {
                "Id": 1846,
                "Order": 0,
                "Value": "Opname Rood",
                "Name": "Opname Rood"
              }
            ]
          },
          {
            "ListId": 126,
            "Items": [
              {
                "Id": 1568,
                "Order": 6,
                "Value": "Niet verdacht van COVID-19 besmetting (Groen)",
                "Name": "Niet verdacht van COVID-19 besmetting (Groen)"
              },
              {
                "Id": 1569,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Oranje)",
                "Name": "Verdacht van COVID-19 besmetting (Oranje)"
              },
              {
                "Id": 1570,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Geel)",
                "Name": "Verdacht van COVID-19 besmetting (Geel)"
              },
              {
                "Id": 1571,
                "Order": 6,
                "Value": "COVID-19 vastgesteld (Rood)",
                "Name": "COVID-19 vastgesteld (Rood)"
              },
              {
                "Id": 1572,
                "Order": 6,
                "Value": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)",
                "Name": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)"
              },
              {
                "Id": 1573,
                "Order": 6,
                "Value": "Nog niet bekend; additionele informatie nodig",
                "Name": "Nog niet bekend; additionele informatie nodig"
              }
            ]
          },
          {
            "ListId": 131,
            "Items": [
              {
                "Id": 1597,
                "Order": 0,
                "Value": "Hertest niet noodzakelijk",
                "Name": "Hertest niet noodzakelijk"
              },
              {
                "Id": 1598,
                "Order": 0,
                "Value": "Hertest wel noodzakelijk",
                "Name": "Hertest wel noodzakelijk"
              },
              {
                "Id": 1862,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 208,
            "Items": [
              {
                "Id": 1832,
                "Order": 0,
                "Value": "NIET Hertesten vanwege recente klachten",
                "Name": "NIET Hertesten vanwege recente klachten"
              },
              {
                "Id": 1833,
                "Order": 0,
                "Value": "WEL Hertesten vanwege recente klachten",
                "Name": "WEL Hertesten vanwege recente klachten"
              },
              {
                "Id": 1861,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 112,
            "Items": [
              {
                "Id": 1507,
                "Order": 1,
                "Value": "Wel respiratoire klachten",
                "Name": "Wel respiratoire klachten"
              },
              {
                "Id": 1508,
                "Order": 1,
                "Value": "Geen respiratoire klachten",
                "Name": "Geen respiratoire klachten"
              },
              {
                "Id": 1596,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 204,
            "Items": [
              {
                "Id": 1821,
                "Order": 1,
                "Value": "Saturatie OK",
                "Name": "Saturatie OK"
              },
              {
                "Id": 1822,
                "Order": 1,
                "Value": "Saturatie Niet OK",
                "Name": "Saturatie Niet OK"
              },
              {
                "Id": 1823,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 200,
            "Items": [
              {
                "Id": 1811,
                "Order": 0,
                "Value": "Geen intestinale klachten",
                "Name": "Geen intestinale klachten"
              },
              {
                "Id": 1812,
                "Order": 0,
                "Value": "Wel intestinale klachten",
                "Name": "Wel intestinale klachten"
              },
              {
                "Id": 1813,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 127,
            "Items": [
              {
                "Id": 1575,
                "Order": 0,
                "Value": "Temperatuur onbekend",
                "Name": "Temperatuur onbekend"
              },
              {
                "Id": 1576,
                "Order": 0,
                "Value": "Koortsvrij",
                "Name": "Koortsvrij"
              },
              {
                "Id": 1577,
                "Order": 0,
                "Value": "Verhoging",
                "Name": "Verhoging"
              },
              {
                "Id": 1578,
                "Order": 0,
                "Value": "Koorts",
                "Name": "Koorts"
              }
            ]
          },
          {
            "ListId": 202,
            "Items": [
              {
                "Id": 1814,
                "Order": 0,
                "Value": "Niet uitgevoerd",
                "Name": "Niet uitgevoerd"
              },
              {
                "Id": 1815,
                "Order": 0,
                "Value": "Covid positief",
                "Name": "Covid positief"
              },
              {
                "Id": 1816,
                "Order": 0,
                "Value": "Covid negatief",
                "Name": "Covid negatief"
              },
              {
                "Id": 1817,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 211,
            "Items": [
              {
                "Id": 1865,
                "Order": 0,
                "Value": "WEL valide hertest",
                "Name": "WEL valide hertest"
              },
              {
                "Id": 1866,
                "Order": 0,
                "Value": "NIET valide hertest",
                "Name": "NIET valide hertest"
              },
              {
                "Id": 1867,
                "Order": 0,
                "Value": "Niet te bepalen",
                "Name": "Niet te bepalen"
              }
            ]
          },
          {
            "ListId": 123,
            "Items": [
              {
                "Id": 1599,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 1847,
                "Order": 0,
                "Value": "ZKH Bed Groen",
                "Name": "ZKH Bed Groen"
              },
              {
                "Id": 1848,
                "Order": 0,
                "Value": "ZKH Bed Geel",
                "Name": "ZKH Bed Geel"
              },
              {
                "Id": 1849,
                "Order": 0,
                "Value": "ZKH Bed Oranje",
                "Name": "ZKH Bed Oranje"
              },
              {
                "Id": 1850,
                "Order": 0,
                "Value": "ZKH Bed Rood",
                "Name": "ZKH Bed Rood"
              },
              {
                "Id": 1851,
                "Order": 0,
                "Value": "SEH Bed Groen",
                "Name": "SEH Bed Groen"
              },
              {
                "Id": 1852,
                "Order": 0,
                "Value": "SEH Bed Geel",
                "Name": "SEH Bed Geel"
              },
              {
                "Id": 1853,
                "Order": 0,
                "Value": "SEH Bed Oranje",
                "Name": "SEH Bed Oranje"
              },
              {
                "Id": 1854,
                "Order": 0,
                "Value": "SEH Bed Rood",
                "Name": "SEH Bed Rood"
              },
              {
                "Id": 1855,
                "Order": 0,
                "Value": "Thuis/Eerste Lijn",
                "Name": "Thuis/Eerste Lijn"
              }
            ]
          },
          {
            "ListId": 124,
            "Items": [
              {
                "Id": 1856,
                "Order": 0,
                "Value": "Maatregelen Groen",
                "Name": "Maatregelen Groen"
              },
              {
                "Id": 1857,
                "Order": 0,
                "Value": "Maatregelen Geel",
                "Name": "Maatregelen Geel"
              },
              {
                "Id": 1858,
                "Order": 0,
                "Value": "Maatregelen Oranje",
                "Name": "Maatregelen Oranje"
              },
              {
                "Id": 1859,
                "Order": 0,
                "Value": "Maatregelen Rood",
                "Name": "Maatregelen Rood"
              },
              {
                "Id": 1860,
                "Order": 0,
                "Value": "Verdenking nog niet bepaald",
                "Name": "Verdenking nog niet bepaald"
              }
            ]
          },
          {
            "ListId": 209,
            "Items": [
              {
                "Id": 1834,
                "Order": 0,
                "Value": "WEL CT-Thorax aanvragen",
                "Name": "WEL CT-Thorax aanvragen"
              },
              {
                "Id": 1835,
                "Order": 0,
                "Value": "GEEN CT-Thorax aanvragen",
                "Name": "GEEN CT-Thorax aanvragen"
              }
            ]
          }
        ]
      },
      {
        "DecisionServiceId": 62,
        "Name": "SEH Triage actie - Version 65",
        "DecisionName": null,
        "DecisionServiceVersionId": 258,
        "VersionNumber": 65,
        "InputData": [
          {
            "BusinessDataId": 747,
            "Version": 2,
            "Name": "SEH Locatie behandeling",
            "Type": "ListData",
            "Question": "Waar wordt de patient hierna verder behandeld?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "132"
              }
            ]
          },
          {
            "BusinessDataId": 719,
            "Version": 2,
            "Name": "SEH Lichaamstemperatuur",
            "Type": "DecimalNumberData",
            "Question": "Wat is de lichaamstemperatuur van de patiënt bij binnenkomst in het ziekenhuis",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "45.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "24.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "graden Celcius"
              }
            ]
          },
          {
            "BusinessDataId": 735,
            "Version": 2,
            "Name": "SEH Hoesten",
            "Type": "PairData",
            "Question": "Hoest u?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "55"
              }
            ]
          },
          {
            "BusinessDataId": 736,
            "Version": 2,
            "Name": "SEH Keelpijn",
            "Type": "PairData",
            "Question": "Heeft u keelpijn?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "56"
              }
            ]
          },
          {
            "BusinessDataId": 737,
            "Version": 2,
            "Name": "SEH Kortademigheid",
            "Type": "PairData",
            "Question": "Bent u kortademig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "57"
              }
            ]
          },
          {
            "BusinessDataId": 738,
            "Version": 2,
            "Name": "SEH Benauwd",
            "Type": "PairData",
            "Question": "Bent u snotterig?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "58"
              }
            ]
          },
          {
            "BusinessDataId": 739,
            "Version": 2,
            "Name": "SEH Verkouden",
            "Type": "PairData",
            "Question": "Bent u verkouden",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "59"
              }
            ]
          },
          {
            "BusinessDataId": 725,
            "Version": 2,
            "Name": "SEH Status eerdere COVID-19 test",
            "Type": "ListData",
            "Question": "Is er bij u recent een COVID-19 test afgenomen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "120"
              }
            ]
          },
          {
            "BusinessDataId": 740,
            "Version": 3,
            "Name": "SEH Saturatie (gemeten in tent)",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 842,
            "Version": 1,
            "Name": "SEH Braken",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "205"
              }
            ]
          },
          {
            "BusinessDataId": 843,
            "Version": 2,
            "Name": "SEH Diarree",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "206"
              }
            ]
          },
          {
            "BusinessDataId": 1022,
            "Version": 1,
            "Name": "SEH Alternatieve verklaring intestinale klachten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "249"
              }
            ]
          },
          {
            "BusinessDataId": 782,
            "Version": 2,
            "Name": "SEH Datum binnenkomst op SEH",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 846,
            "Version": 1,
            "Name": "SEH Datum Ontstaan Intestinale Klachten",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 992,
            "Version": 2,
            "Name": "ZZ -VERVALLEN- SEH CT-Thorax CORADS",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "228"
              }
            ]
          },
          {
            "BusinessDataId": 726,
            "Version": 2,
            "Name": "SEH Status COVID-19 SWAB",
            "Type": "ListData",
            "Question": "Wat is de status van de SWAB?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 850,
            "Version": 1,
            "Name": "ZKH Status Covid-19 SWAB hertest",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "121"
              }
            ]
          },
          {
            "BusinessDataId": 745,
            "Version": 2,
            "Name": "SEH Immuungecompromitteerd",
            "Type": "PairData",
            "Question": "Is de patient wel/niet immuungecompromitteerd?",
            "Properties": [
              {
                "Name": "PairId",
                "Value": "61"
              }
            ]
          },
          {
            "BusinessDataId": 744,
            "Version": 2,
            "Name": "SEH Leeftijd",
            "Type": "WholeNumberData",
            "Question": "Wat is de leeftijd van de patient?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "130"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 845,
            "Version": 1,
            "Name": "SEH Datum Ontstaan Respiratoire Klachten",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 857,
            "Version": 1,
            "Name": "SEH Datum afname eerste SWAB",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 848,
            "Version": 3,
            "Name": "SEH Covid aanpassing verdenking door Arts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "207"
              }
            ]
          },
          {
            "BusinessDataId": 987,
            "Version": 2,
            "Name": "ZKH Covid aanpassing vaststelling door Arts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "227"
              }
            ]
          },
          {
            "BusinessDataId": 983,
            "Version": 2,
            "Name": "SEH Evident Poliklnisch",
            "Type": "ListData",
            "Question": "Waar vindt hoogstwaarschijnlijk de behandeling plaats?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "225"
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 720,
            "Version": 2,
            "Name": "SEH Triage actie",
            "Type": "ListData",
            "Question": "Welke actie moet je uitvoeren",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "118"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 728,
            "Version": 3,
            "Name": "SEH Routing",
            "Type": "ListData",
            "Question": "Waar moet de patiënt naartoe?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "122"
              }
            ]
          },
          {
            "BusinessDataId": 777,
            "Version": 2,
            "Name": "ZKH COVID vaststelling",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 835,
            "Version": 1,
            "Name": "SEH Covid verdenking aanmelding",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 733,
            "Version": 2,
            "Name": "SEH Koorts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "127"
              }
            ]
          },
          {
            "BusinessDataId": 704,
            "Version": 2,
            "Name": "SEH Respiratoire klachten",
            "Type": "ListData",
            "Question": "Welke respiratoire klachten heeft de patiënt?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "112"
              }
            ]
          },
          {
            "BusinessDataId": 838,
            "Version": 1,
            "Name": "SEH Status eerdere COVID-19 test definitief",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "202"
              }
            ]
          },
          {
            "BusinessDataId": 841,
            "Version": 1,
            "Name": "SEH Saturatie OK",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "204"
              }
            ]
          },
          {
            "BusinessDataId": 836,
            "Version": 2,
            "Name": "SEH Acuut intestinale klachten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "200"
              }
            ]
          },
          {
            "BusinessDataId": 853,
            "Version": 1,
            "Name": "SEH aantal dagen intestinale klachten",
            "Type": "TimespanData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "TimespanUnit",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 994,
            "Version": 1,
            "Name": "SEH CT-Thorax",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "114"
              }
            ]
          },
          {
            "BusinessDataId": 706,
            "Version": 5,
            "Name": "SEH CT-Thorax CORADS",
            "Type": "ListData",
            "Question": "Wat is de uitkomst van de triage CT-Thorax-scan?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "228"
              }
            ]
          },
          {
            "BusinessDataId": 985,
            "Version": 1,
            "Name": "SEH Covid verdenking onderzoek",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 743,
            "Version": 3,
            "Name": "ZKH Hertest noodzakelijk",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "131"
              }
            ]
          },
          {
            "BusinessDataId": 852,
            "Version": 1,
            "Name": "SEH Hertesten vanwege recentheid klachten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "208"
              }
            ]
          },
          {
            "BusinessDataId": 780,
            "Version": 3,
            "Name": "SEH aantal dagen respiratoire klachten",
            "Type": "TimespanData",
            "Question": "Hoelang heeft de patient al respiratoire klachten",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "TimespanUnit",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 990,
            "Version": 1,
            "Name": "SEH Covid verdenking Arts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 989,
            "Version": 1,
            "Name": "ZKH Covid vaststelling Arts",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "126"
              }
            ]
          },
          {
            "BusinessDataId": 729,
            "Version": 2,
            "Name": "SEH Soort bed",
            "Type": "ListData",
            "Question": "Welk soort bed moet deze patient krijgen gezien de isolatie eis",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "123"
              }
            ]
          },
          {
            "BusinessDataId": 730,
            "Version": 2,
            "Name": "SEH Maatregelen",
            "Type": "ListData",
            "Question": "Welke isolatiemaatregelen zijn nodig bij deze patiënt",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "124"
              }
            ]
          },
          {
            "BusinessDataId": 854,
            "Version": 1,
            "Name": "SEH CT-Thorax aanvragen",
            "Type": "ListData",
            "Question": "Moet ik voor deze patient een CT-Thorax aanvragen",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "209"
              }
            ]
          },
          {
            "BusinessDataId": 887,
            "Version": 2,
            "Name": "ZKH Datum hertest vanaf",
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 1021,
            "Version": 1,
            "Name": "SEH Default Locatie behandeling",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "132"
              }
            ]
          }
        ],
        "MetaData": [
          {
            "BusinessDataId": 727,
            "Version": 2,
            "Name": null,
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "6"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH META TriageID"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 849,
            "Version": 2,
            "Name": null,
            "Type": "TextData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "8"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH Toelichting aanpassing COVID-19 verdenking"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "CaseSensitive",
                "Value": ""
              },
              {
                "Name": "MaximumTextLength",
                "Value": ""
              }
            ]
          },
          {
            "BusinessDataId": 859,
            "Version": 1,
            "Name": null,
            "Type": "PairData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "11"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH META Printknop gebruikt"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "PairId",
                "Value": "62"
              }
            ]
          },
          {
            "BusinessDataId": 978,
            "Version": 1,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "12"
              },
              {
                "Name": "MetaDataName",
                "Value": "ZKH Ontslag"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "222"
              }
            ]
          },
          {
            "BusinessDataId": 979,
            "Version": 1,
            "Name": null,
            "Type": "DateTimeData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "13"
              },
              {
                "Name": "MetaDataName",
                "Value": "ZKH Datum ontslag"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "MinimumDatetimeValue",
                "Value": ""
              },
              {
                "Name": "DateTimePrecision",
                "Value": "Days"
              }
            ]
          },
          {
            "BusinessDataId": 1022,
            "Version": 1,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "25"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH Alternatieve verklaring intestinale klachten"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "249"
              }
            ]
          }
        ],
        "PairData": [
          {
            "PairId": 55,
            "ValueForTrue": "Wel hoesten",
            "ValueForFalse": "Niet hoesten"
          },
          {
            "PairId": 56,
            "ValueForTrue": "Wel keelpijn",
            "ValueForFalse": "Geen keelpijn"
          },
          {
            "PairId": 57,
            "ValueForTrue": "Wel kortademig",
            "ValueForFalse": "Niet kortademig"
          },
          {
            "PairId": 58,
            "ValueForTrue": "Wel benauwd",
            "ValueForFalse": "Niet benauwd"
          },
          {
            "PairId": 59,
            "ValueForTrue": "Wel verkouden",
            "ValueForFalse": "Niet verkouden"
          },
          {
            "PairId": 61,
            "ValueForTrue": "Niet immuungecompromitteerd",
            "ValueForFalse": "Wel immuungecompromitteerd"
          },
          {
            "PairId": 62,
            "ValueForTrue": "Wel Geprint",
            "ValueForFalse": "Niet Geprint"
          }
        ],
        "ListData": [
          {
            "ListId": 132,
            "Items": [
              {
                "Id": 1603,
                "Order": 0,
                "Value": "Thuis / eerste lijn",
                "Name": "Thuis / eerste lijn"
              },
              {
                "Id": 1605,
                "Order": 0,
                "Value": "Opname in Ziekenhuis",
                "Name": "Opname in Ziekenhuis"
              },
              {
                "Id": 1919,
                "Order": 0,
                "Value": "Behandeling op SEH / Poliklinisch",
                "Name": "Behandeling op SEH / Poliklinisch"
              }
            ]
          },
          {
            "ListId": 120,
            "Items": [
              {
                "Id": 1537,
                "Order": 0,
                "Value": "1 Nee, niet uitgevoerd",
                "Name": "1 Nee, niet uitgevoerd"
              },
              {
                "Id": 1538,
                "Order": 0,
                "Value": "2 Ja; Geen COVID-19",
                "Name": "2 Ja; Geen COVID-19"
              },
              {
                "Id": 1539,
                "Order": 0,
                "Value": "3 Ja; wel COVID-19",
                "Name": "3 Ja; wel COVID-19"
              }
            ]
          },
          {
            "ListId": 205,
            "Items": [
              {
                "Id": 1824,
                "Order": 0,
                "Value": "Wel Braken",
                "Name": "Wel Braken"
              },
              {
                "Id": 1825,
                "Order": 0,
                "Value": "Niet Braken",
                "Name": "Niet Braken"
              }
            ]
          },
          {
            "ListId": 206,
            "Items": [
              {
                "Id": 1826,
                "Order": 0,
                "Value": "Wel Diarree",
                "Name": "Wel Diarree"
              },
              {
                "Id": 1827,
                "Order": 0,
                "Value": "Geen Diarree",
                "Name": "Geen Diarree"
              }
            ]
          },
          {
            "ListId": 249,
            "Items": [
              {
                "Id": 2032,
                "Order": 0,
                "Value": "Geen alternatieve verklaring intestinale klachten",
                "Name": "Geen alternatieve verklaring intestinale klachten"
              },
              {
                "Id": 2033,
                "Order": 0,
                "Value": "Wel alternatieve verklaring intestinale klachten",
                "Name": "Wel alternatieve verklaring intestinale klachten"
              }
            ]
          },
          {
            "ListId": 228,
            "Items": [
              {
                "Id": 1930,
                "Order": 0,
                "Value": "0 Niet aangevraagd",
                "Name": "0 Niet aangevraagd"
              },
              {
                "Id": 1931,
                "Order": 0,
                "Value": "CO-RADS 1: Geen",
                "Name": "CO-RADS 1: Geen"
              },
              {
                "Id": 1932,
                "Order": 0,
                "Value": "CO-RADS 2: Laag",
                "Name": "CO-RADS 2: Laag"
              },
              {
                "Id": 1933,
                "Order": 0,
                "Value": "CO-RADS 3: Onzeker",
                "Name": "CO-RADS 3: Onzeker"
              },
              {
                "Id": 1934,
                "Order": 0,
                "Value": "CO-RADS 4: Hoog",
                "Name": "CO-RADS 4: Hoog"
              },
              {
                "Id": 1935,
                "Order": 0,
                "Value": "CO-RADS 5: Zeer hoog",
                "Name": "CO-RADS 5: Zeer hoog"
              },
              {
                "Id": 1936,
                "Order": 0,
                "Value": "1 Aangevraagd; niet bekend",
                "Name": "1 Aangevraagd; niet bekend"
              },
              {
                "Id": 1946,
                "Order": 0,
                "Value": "2 Geen verdachte bevindingen",
                "Name": "2 Geen verdachte bevindingen"
              },
              {
                "Id": 1947,
                "Order": 0,
                "Value": "3 Verdachte bevindingen",
                "Name": "3 Verdachte bevindingen"
              }
            ]
          },
          {
            "ListId": 121,
            "Items": [
              {
                "Id": 1541,
                "Order": 0,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1542,
                "Order": 0,
                "Value": "2 Negatief voor COVID-19",
                "Name": "2 Negatief voor COVID-19"
              },
              {
                "Id": 1543,
                "Order": 0,
                "Value": "3 Positief voor COVID-19",
                "Name": "3 Positief voor COVID-19"
              },
              {
                "Id": 1864,
                "Order": 0,
                "Value": "0 Niet aangevraagd",
                "Name": "0 Niet aangevraagd"
              }
            ]
          },
          {
            "ListId": 207,
            "Items": [
              {
                "Id": 1828,
                "Order": 2,
                "Value": "1 COVID-19 verdenking niet aanpassen",
                "Name": "1 COVID-19 verdenking niet aanpassen"
              },
              {
                "Id": 1829,
                "Order": 2,
                "Value": "3 Lage verdenking van COVID-19 (GEEL)",
                "Name": "3 Lage verdenking van COVID-19 (GEEL)"
              },
              {
                "Id": 1830,
                "Order": 2,
                "Value": "4 Hoge verdenking van COVID-19 (ORANJE)",
                "Name": "4 Hoge verdenking van COVID-19 (ORANJE)"
              },
              {
                "Id": 1831,
                "Order": 2,
                "Value": "2 Geen verdenking van COVID-19 (GROEN)",
                "Name": "2 Geen verdenking van COVID-19 (GROEN)"
              },
              {
                "Id": 1918,
                "Order": 2,
                "Value": "5 COVID-19 vastgesteld (ROOD)",
                "Name": "5 COVID-19 vastgesteld (ROOD)"
              }
            ]
          },
          {
            "ListId": 227,
            "Items": [
              {
                "Id": 1925,
                "Order": 2,
                "Value": "1 Covid vaststelling niet aanpassen",
                "Name": "1 Covid vaststelling niet aanpassen"
              },
              {
                "Id": 1926,
                "Order": 2,
                "Value": "3 Covid vaststelling naar laagverdacht (Geel)",
                "Name": "3 Covid vaststelling naar laagverdacht (Geel)"
              },
              {
                "Id": 1927,
                "Order": 2,
                "Value": "4 Covid vaststelling naar hoog verdacht (Oranje)",
                "Name": "4 Covid vaststelling naar hoog verdacht (Oranje)"
              },
              {
                "Id": 1928,
                "Order": 2,
                "Value": "2 Covid vastelling naar niet verdacht (Groen)",
                "Name": "2 Covid vastelling naar niet verdacht (Groen)"
              },
              {
                "Id": 1929,
                "Order": 2,
                "Value": "5 Covid vastgesteld (Rood)",
                "Name": "5 Covid vastgesteld (Rood)"
              }
            ]
          },
          {
            "ListId": 225,
            "Items": [
              {
                "Id": 1912,
                "Order": 0,
                "Value": "1 - Evident poliklinisch traject via SEH/poli",
                "Name": "1 - Evident poliklinisch traject via SEH/poli"
              },
              {
                "Id": 1913,
                "Order": 0,
                "Value": "3 - Ziekenhuisopname",
                "Name": "3 - Ziekenhuisopname"
              },
              {
                "Id": 1938,
                "Order": 0,
                "Value": "2 - Traject nog niet te bepalen",
                "Name": "2 - Traject nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 118,
            "Items": [
              {
                "Id": 1531,
                "Order": 0,
                "Value": "Geen actie vereist",
                "Name": "Geen actie vereist"
              },
              {
                "Id": 1532,
                "Order": 0,
                "Value": "Controleer temperatuur, SWAB & CT-scan",
                "Name": "Controleer temperatuur, SWAB & CT-scan"
              }
            ]
          },
          {
            "ListId": 122,
            "Items": [
              {
                "Id": 1600,
                "Order": 0,
                "Value": "Nog nader te bepalen",
                "Name": "Nog nader te bepalen"
              },
              {
                "Id": 1601,
                "Order": 0,
                "Value": "Naar eerste lijns zorg",
                "Name": "Naar eerste lijns zorg"
              },
              {
                "Id": 1839,
                "Order": 0,
                "Value": "SEH Groen",
                "Name": "SEH Groen"
              },
              {
                "Id": 1840,
                "Order": 0,
                "Value": "SEH Geel",
                "Name": "SEH Geel"
              },
              {
                "Id": 1841,
                "Order": 0,
                "Value": "SEH Oranje",
                "Name": "SEH Oranje"
              },
              {
                "Id": 1842,
                "Order": 0,
                "Value": "SEH Rood",
                "Name": "SEH Rood"
              },
              {
                "Id": 1843,
                "Order": 0,
                "Value": "Opname Groen",
                "Name": "Opname Groen"
              },
              {
                "Id": 1844,
                "Order": 0,
                "Value": "Opname Geel",
                "Name": "Opname Geel"
              },
              {
                "Id": 1845,
                "Order": 0,
                "Value": "Opname Oranje",
                "Name": "Opname Oranje"
              },
              {
                "Id": 1846,
                "Order": 0,
                "Value": "Opname Rood",
                "Name": "Opname Rood"
              }
            ]
          },
          {
            "ListId": 126,
            "Items": [
              {
                "Id": 1568,
                "Order": 6,
                "Value": "Niet verdacht van COVID-19 besmetting (Groen)",
                "Name": "Niet verdacht van COVID-19 besmetting (Groen)"
              },
              {
                "Id": 1569,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Oranje)",
                "Name": "Verdacht van COVID-19 besmetting (Oranje)"
              },
              {
                "Id": 1570,
                "Order": 6,
                "Value": "Verdacht van COVID-19 besmetting (Geel)",
                "Name": "Verdacht van COVID-19 besmetting (Geel)"
              },
              {
                "Id": 1571,
                "Order": 6,
                "Value": "COVID-19 vastgesteld (Rood)",
                "Name": "COVID-19 vastgesteld (Rood)"
              },
              {
                "Id": 1572,
                "Order": 6,
                "Value": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)",
                "Name": "Vastgesteld dat er geen COVID-19 besmetting is (Groen)"
              },
              {
                "Id": 1573,
                "Order": 6,
                "Value": "Nog niet bekend; additionele informatie nodig",
                "Name": "Nog niet bekend; additionele informatie nodig"
              }
            ]
          },
          {
            "ListId": 127,
            "Items": [
              {
                "Id": 1575,
                "Order": 0,
                "Value": "Temperatuur onbekend",
                "Name": "Temperatuur onbekend"
              },
              {
                "Id": 1576,
                "Order": 0,
                "Value": "Koortsvrij",
                "Name": "Koortsvrij"
              },
              {
                "Id": 1577,
                "Order": 0,
                "Value": "Verhoging",
                "Name": "Verhoging"
              },
              {
                "Id": 1578,
                "Order": 0,
                "Value": "Koorts",
                "Name": "Koorts"
              }
            ]
          },
          {
            "ListId": 112,
            "Items": [
              {
                "Id": 1507,
                "Order": 1,
                "Value": "Wel respiratoire klachten",
                "Name": "Wel respiratoire klachten"
              },
              {
                "Id": 1508,
                "Order": 1,
                "Value": "Geen respiratoire klachten",
                "Name": "Geen respiratoire klachten"
              }
            ]
          },
          {
            "ListId": 202,
            "Items": [
              {
                "Id": 1814,
                "Order": 0,
                "Value": "Niet uitgevoerd",
                "Name": "Niet uitgevoerd"
              },
              {
                "Id": 1815,
                "Order": 0,
                "Value": "Covid positief",
                "Name": "Covid positief"
              },
              {
                "Id": 1816,
                "Order": 0,
                "Value": "Covid negatief",
                "Name": "Covid negatief"
              },
              {
                "Id": 1817,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 204,
            "Items": [
              {
                "Id": 1821,
                "Order": 1,
                "Value": "Saturatie OK",
                "Name": "Saturatie OK"
              },
              {
                "Id": 1822,
                "Order": 1,
                "Value": "Saturatie Niet OK",
                "Name": "Saturatie Niet OK"
              }
            ]
          },
          {
            "ListId": 200,
            "Items": [
              {
                "Id": 1811,
                "Order": 0,
                "Value": "Geen intestinale klachten",
                "Name": "Geen intestinale klachten"
              },
              {
                "Id": 1812,
                "Order": 0,
                "Value": "Wel intestinale klachten",
                "Name": "Wel intestinale klachten"
              }
            ]
          },
          {
            "ListId": 114,
            "Items": [
              {
                "Id": 1513,
                "Order": 5,
                "Value": "3 Verdachte bevindingen",
                "Name": "3 Verdachte bevindingen"
              },
              {
                "Id": 1514,
                "Order": 5,
                "Value": "2 Geen verdachte bevindingen",
                "Name": "2 Geen verdachte bevindingen"
              },
              {
                "Id": 1545,
                "Order": 5,
                "Value": "1 Aangevraagd; nog niet bekend",
                "Name": "1 Aangevraagd; nog niet bekend"
              },
              {
                "Id": 1863,
                "Order": 0,
                "Value": "0 Niet aangevraagd",
                "Name": "0 Niet aangevraagd"
              }
            ]
          },
          {
            "ListId": 131,
            "Items": [
              {
                "Id": 1597,
                "Order": 0,
                "Value": "Hertest niet noodzakelijk",
                "Name": "Hertest niet noodzakelijk"
              },
              {
                "Id": 1598,
                "Order": 0,
                "Value": "Hertest wel noodzakelijk",
                "Name": "Hertest wel noodzakelijk"
              },
              {
                "Id": 1862,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 208,
            "Items": [
              {
                "Id": 1832,
                "Order": 0,
                "Value": "NIET Hertesten vanwege recente klachten",
                "Name": "NIET Hertesten vanwege recente klachten"
              },
              {
                "Id": 1833,
                "Order": 0,
                "Value": "WEL Hertesten vanwege recente klachten",
                "Name": "WEL Hertesten vanwege recente klachten"
              },
              {
                "Id": 1861,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 123,
            "Items": [
              {
                "Id": 1599,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 1847,
                "Order": 0,
                "Value": "ZKH Bed Groen",
                "Name": "ZKH Bed Groen"
              },
              {
                "Id": 1848,
                "Order": 0,
                "Value": "ZKH Bed Geel",
                "Name": "ZKH Bed Geel"
              },
              {
                "Id": 1849,
                "Order": 0,
                "Value": "ZKH Bed Oranje",
                "Name": "ZKH Bed Oranje"
              },
              {
                "Id": 1850,
                "Order": 0,
                "Value": "ZKH Bed Rood",
                "Name": "ZKH Bed Rood"
              },
              {
                "Id": 1851,
                "Order": 0,
                "Value": "SEH Bed Groen",
                "Name": "SEH Bed Groen"
              },
              {
                "Id": 1852,
                "Order": 0,
                "Value": "SEH Bed Geel",
                "Name": "SEH Bed Geel"
              },
              {
                "Id": 1853,
                "Order": 0,
                "Value": "SEH Bed Oranje",
                "Name": "SEH Bed Oranje"
              },
              {
                "Id": 1854,
                "Order": 0,
                "Value": "SEH Bed Rood",
                "Name": "SEH Bed Rood"
              },
              {
                "Id": 1855,
                "Order": 0,
                "Value": "Thuis/Eerste Lijn",
                "Name": "Thuis/Eerste Lijn"
              }
            ]
          },
          {
            "ListId": 124,
            "Items": [
              {
                "Id": 1856,
                "Order": 0,
                "Value": "ZKH Maatregelen Groen",
                "Name": "ZKH Maatregelen Groen"
              },
              {
                "Id": 1857,
                "Order": 0,
                "Value": "ZKH Maatregelen Geel",
                "Name": "ZKH Maatregelen Geel"
              },
              {
                "Id": 1858,
                "Order": 0,
                "Value": "ZKH Maatregelen Oranje",
                "Name": "ZKH Maatregelen Oranje"
              },
              {
                "Id": 1859,
                "Order": 0,
                "Value": "ZKH Maatregelen Rood",
                "Name": "ZKH Maatregelen Rood"
              },
              {
                "Id": 1860,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 1920,
                "Order": 0,
                "Value": "SEH Maatregelen Groen",
                "Name": "SEH Maatregelen Groen"
              },
              {
                "Id": 1921,
                "Order": 9,
                "Value": "SEH Maatregelen Geel",
                "Name": "SEH Maatregelen Geel"
              },
              {
                "Id": 1922,
                "Order": 9,
                "Value": "SEH Maatregelen Oranje",
                "Name": "SEH Maatregelen Oranje"
              },
              {
                "Id": 1923,
                "Order": 9,
                "Value": "SEH Maatregelen Rood",
                "Name": "SEH Maatregelen Rood"
              },
              {
                "Id": 1924,
                "Order": 9,
                "Value": "Thuis/Eerste Lijn",
                "Name": "Thuis/Eerste Lijn"
              }
            ]
          },
          {
            "ListId": 209,
            "Items": [
              {
                "Id": 1834,
                "Order": 0,
                "Value": "WEL CT-Thorax aanvragen",
                "Name": "WEL CT-Thorax aanvragen"
              },
              {
                "Id": 1835,
                "Order": 0,
                "Value": "GEEN CT-Thorax aanvragen",
                "Name": "GEEN CT-Thorax aanvragen"
              }
            ]
          },
          {
            "ListId": 222,
            "Items": [
              {
                "Id": 1903,
                "Order": 0,
                "Value": "1 - Gezond naar huis",
                "Name": "1 - Gezond naar huis"
              },
              {
                "Id": 1904,
                "Order": 0,
                "Value": "2 - Ziek naar huis",
                "Name": "2 - Ziek naar huis"
              },
              {
                "Id": 1905,
                "Order": 0,
                "Value": "3 - Transfer naar ander ziekenhuis",
                "Name": "3 - Transfer naar ander ziekenhuis"
              },
              {
                "Id": 1906,
                "Order": 0,
                "Value": "4 - Overleden",
                "Name": "4 - Overleden"
              }
            ]
          }
        ]
      }
    ]
  },
  {
    "DecisionServiceId": 64,
    "Name": "IC Triage opnemen op IC",
    "Versions": [
      {
        "DecisionServiceId": 64,
        "Name": "IC Triage opnemen op IC - Version 41",
        "DecisionName": null,
        "DecisionServiceVersionId": 253,
        "VersionNumber": 41,
        "InputData": [
          {
            "BusinessDataId": 793,
            "Version": 4,
            "Name": "ICE (Un)witnessed Cardiac Arrest",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "167"
              }
            ]
          },
          {
            "BusinessDataId": 794,
            "Version": 3,
            "Name": "ICE Herhaaldelijke Hartstilstand",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "165"
              }
            ]
          },
          {
            "BusinessDataId": 796,
            "Version": 3,
            "Name": "ICE Tweede Hartstilstand <72 uur",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "168"
              }
            ]
          },
          {
            "BusinessDataId": 980,
            "Version": 2,
            "Name": "ICE ROSC",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "223"
              }
            ]
          },
          {
            "BusinessDataId": 996,
            "Version": 2,
            "Name": "ICE Cardio vasculaire afwijkingen - kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "231"
              }
            ]
          },
          {
            "BusinessDataId": 995,
            "Version": 2,
            "Name": "ICE Trauma - Kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "232"
              }
            ]
          },
          {
            "BusinessDataId": 800,
            "Version": 3,
            "Name": "ICE Trauma Injury Severity Score (TRISS)",
            "Type": "WholeNumberData",
            "Question": "Wat is de voorspelde mortaliteit volgens TRISS?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 997,
            "Version": 2,
            "Name": "ICE Brandwonden - Kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "233"
              }
            ]
          },
          {
            "BusinessDataId": 982,
            "Version": 2,
            "Name": "ICE Luchtwegverbranding",
            "Type": "ListData",
            "Question": "Is er sprake van luchtwegverbranding?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "224"
              }
            ]
          },
          {
            "BusinessDataId": 822,
            "Version": 3,
            "Name": "ICE Leeftijd",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "130"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 981,
            "Version": 4,
            "Name": "ICE Percentage verbranding",
            "Type": "WholeNumberData",
            "Question": "Wat is het percentage verbranding?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 807,
            "Version": 4,
            "Name": "ICE Ernstige (post-anoxische) hersenschade",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "178"
              }
            ]
          },
          {
            "BusinessDataId": 813,
            "Version": 2,
            "Name": "ICE Vergevorderde niet behandelbare neuromusculaire aandoeningen",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "185"
              }
            ]
          },
          {
            "BusinessDataId": 814,
            "Version": 2,
            "Name": "ICE Gevorderde Neurodegeneratieve ziekten",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "186"
              }
            ]
          },
          {
            "BusinessDataId": 998,
            "Version": 3,
            "Name": "ICE Neurologische afwijkingen - Kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "234"
              }
            ]
          },
          {
            "BusinessDataId": 789,
            "Version": 3,
            "Name": "ICE Electieve palliatieve chirurgie",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "163"
              }
            ]
          },
          {
            "BusinessDataId": 797,
            "Version": 4,
            "Name": "ICE Maligniteit - kop",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "171"
              }
            ]
          },
          {
            "BusinessDataId": 1003,
            "Version": 2,
            "Name": "ICE Prognose bij maligniteit",
            "Type": "ListData",
            "Question": "Is er sprake van een slechte prognose?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "159"
              }
            ]
          },
          {
            "BusinessDataId": 805,
            "Version": 2,
            "Name": "ICE NYHA classificatie",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "177"
              }
            ]
          },
          {
            "BusinessDataId": 1000,
            "Version": 2,
            "Name": "ICE Chronisch hartfalen",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "236"
              }
            ]
          },
          {
            "BusinessDataId": 806,
            "Version": 4,
            "Name": "ICE Overige ernstige chronische longziektes <1 levensverwachting",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "179"
              }
            ]
          },
          {
            "BusinessDataId": 812,
            "Version": 3,
            "Name": "ICE Thuis zuurstofbehoeftig",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "184"
              }
            ]
          },
          {
            "BusinessDataId": 993,
            "Version": 2,
            "Name": "ICE Pulmonale afwijkingen - Kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "229"
              }
            ]
          },
          {
            "BusinessDataId": 816,
            "Version": 4,
            "Name": "ICE COPD",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "189"
              }
            ]
          },
          {
            "BusinessDataId": 984,
            "Version": 2,
            "Name": "ICE COPD GOLD classificatie",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "226"
              }
            ]
          },
          {
            "BusinessDataId": 1005,
            "Version": 2,
            "Name": "ICE PaO2 Chronische longaandoening",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmHg"
              }
            ]
          },
          {
            "BusinessDataId": 821,
            "Version": 2,
            "Name": "ICE Taaislijmziekte",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "191"
              }
            ]
          },
          {
            "BusinessDataId": 817,
            "Version": 2,
            "Name": "ICE FEV1 na bronchodilatatie",
            "Type": "PercentageData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "100.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "0.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "2"
              }
            ]
          },
          {
            "BusinessDataId": 825,
            "Version": 3,
            "Name": "ICE Pulmonaire fibrose",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "193"
              }
            ]
          },
          {
            "BusinessDataId": 826,
            "Version": 3,
            "Name": "ICE VC",
            "Type": "PercentageData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "100.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "0.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "2"
              }
            ]
          },
          {
            "BusinessDataId": 827,
            "Version": 3,
            "Name": "ICE TLC",
            "Type": "PercentageData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "100.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "0.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "2"
              }
            ]
          },
          {
            "BusinessDataId": 828,
            "Version": 4,
            "Name": "ICE Chronische dialyse-patient",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "194"
              }
            ]
          },
          {
            "BusinessDataId": 829,
            "Version": 4,
            "Name": "ICE Contra-indicatie transplantatie",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "195"
              }
            ]
          },
          {
            "BusinessDataId": 1007,
            "Version": 2,
            "Name": "ICE Nefrologische afwijkingen - kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "240"
              }
            ]
          },
          {
            "BusinessDataId": 1008,
            "Version": 2,
            "Name": "ICE Chronisch leverfalen",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "241"
              }
            ]
          },
          {
            "BusinessDataId": 1009,
            "Version": 2,
            "Name": "ICE Abdominale/hepatische afwijkingen - kop",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "242"
              }
            ]
          },
          {
            "BusinessDataId": 830,
            "Version": 2,
            "Name": "ICE MELD score",
            "Type": "DecimalNumberData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "40.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "0.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "0"
              },
              {
                "Name": "DecimalUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 824,
            "Version": 2,
            "Name": "ICE Clinical Frailty",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "192"
              }
            ]
          },
          {
            "BusinessDataId": 1010,
            "Version": 2,
            "Name": "ICE Dementie",
            "Type": "ListData",
            "Question": "Is er sprake van dementie?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "243"
              }
            ]
          },
          {
            "BusinessDataId": 831,
            "Version": 2,
            "Name": "ZZ -VERVALLEN- ICE Medicatie resistent AIDS",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "196"
              }
            ]
          },
          {
            "BusinessDataId": 832,
            "Version": 2,
            "Name": "ZZ -VERVALLEN- ICE Overige gevorderde en onomkeerbaar immuungecompromitteerd",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "197"
              }
            ]
          },
          {
            "BusinessDataId": 833,
            "Version": 2,
            "Name": "ZZ -VERVALLEN- ICE inhoudsdeskundige collega betrokken",
            "Type": "ListData",
            "Question": "Heeft u een inhoudsdeskundige collega betrokken?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "198"
              }
            ]
          },
          {
            "BusinessDataId": 917,
            "Version": 2,
            "Name": "ICI Zuurstoftoediening",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "212"
              }
            ]
          },
          {
            "BusinessDataId": 776,
            "Version": 5,
            "Name": "ICI Saturatie",
            "Type": "WholeNumberData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": "100"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 819,
            "Version": 3,
            "Name": "ICE PaO2",
            "Type": "DecimalNumberData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "100.000000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "0.000000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "0"
              },
              {
                "Name": "DecimalUnit",
                "Value": "mmHg"
              }
            ]
          },
          {
            "BusinessDataId": 1018,
            "Version": 2,
            "Name": "ICI Bloedgas pH",
            "Type": "DecimalNumberData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": "7.800000000000"
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": "6.800000000000"
              },
              {
                "Name": "DecimalPlaces",
                "Value": "2"
              },
              {
                "Name": "DecimalUnit",
                "Value": "pH"
              }
            ]
          },
          {
            "BusinessDataId": 1020,
            "Version": 1,
            "Name": "ICI Bloedgas pCO2",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmHg"
              }
            ]
          },
          {
            "BusinessDataId": 919,
            "Version": 3,
            "Name": "ICI Bloedgas HCO3",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmol/L"
              }
            ]
          },
          {
            "BusinessDataId": 951,
            "Version": 2,
            "Name": "ICI Onvermogen vrijhouden luchtwegen",
            "Type": "ListData",
            "Question": "Onvermogen om luchtwegen vrij te houden?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "219"
              }
            ]
          },
          {
            "BusinessDataId": 921,
            "Version": 2,
            "Name": "ICI Dreigende uitputting",
            "Type": "ListData",
            "Question": "Dreigende uitputting?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "213"
              }
            ]
          },
          {
            "BusinessDataId": 922,
            "Version": 2,
            "Name": "ICI Gebruik hulpademhalingsspieren",
            "Type": "ListData",
            "Question": "Gebruikt hulpademhalingsspieren?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "214"
              }
            ]
          },
          {
            "BusinessDataId": 920,
            "Version": 3,
            "Name": "ICI Ademhalingsfrequentie",
            "Type": "WholeNumberData",
            "Question": "",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "/min"
              }
            ]
          },
          {
            "BusinessDataId": 762,
            "Version": 4,
            "Name": "ICI Systolische bloeddruk",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmHg"
              }
            ]
          },
          {
            "BusinessDataId": 774,
            "Version": 4,
            "Name": "ICI Reagerend op vloeistoftherapie",
            "Type": "ListData",
            "Question": "Reageert op 500ml Ringerlactaat?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "154"
              }
            ]
          },
          {
            "BusinessDataId": 945,
            "Version": 2,
            "Name": "ICI bewustzijnsniveau",
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "217"
              }
            ]
          },
          {
            "BusinessDataId": 977,
            "Version": 2,
            "Name": "ICI Diurese",
            "Type": "DecimalNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumDecimalValue",
                "Value": ""
              },
              {
                "Name": "MinimumDecimalValue",
                "Value": ""
              },
              {
                "Name": "DecimalPlaces",
                "Value": "1"
              },
              {
                "Name": "DecimalUnit",
                "Value": "ml/kg/uur"
              }
            ]
          },
          {
            "BusinessDataId": 991,
            "Version": 2,
            "Name": "ICI Diastole bloeddruk",
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmHg"
              }
            ]
          }
        ],
        "OutputData": [
          {
            "BusinessDataId": 834,
            "Version": 2,
            "Name": "IC Triage opnemen op IC",
            "Type": "ListData",
            "Question": "Zou de patient o.b.v. in- en exclusiecriteria moeten worden opgenomen op de IC?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "199"
              }
            ]
          }
        ],
        "TraceData": [
          {
            "BusinessDataId": 748,
            "Version": 2,
            "Name": "ICE Voldoet aan Exclusievoorwaarden",
            "Type": "ListData",
            "Question": "Zijn er exclusiecriteria op de patient van toepassing?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "133"
              }
            ]
          },
          {
            "BusinessDataId": 750,
            "Version": 2,
            "Name": "ICE Lage waarschijnlijkheid om te overleven",
            "Type": "ListData",
            "Question": "Heeft de patient een lage waarschijnlijkheid om te overleven?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "135"
              }
            ]
          },
          {
            "BusinessDataId": 749,
            "Version": 3,
            "Name": "ICE Hartstilstand lage overleving",
            "Type": "ListData",
            "Question": "Heeft de patient een lage waarschijnlijkheid om te overleven op basis van een hartstilstand?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "134"
              }
            ]
          },
          {
            "BusinessDataId": 765,
            "Version": 2,
            "Name": "ICE Ernstig Trauma",
            "Type": "ListData",
            "Question": "Is er sprake van ernstig trauma?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "147"
              }
            ]
          },
          {
            "BusinessDataId": 783,
            "Version": 2,
            "Name": "ICE Ernstige Brandwonden",
            "Type": "ListData",
            "Question": "Is er sprake van ernstige brandwonden?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "148"
              }
            ]
          },
          {
            "BusinessDataId": 804,
            "Version": 5,
            "Name": "ICE Voorspelde mortaliteit agv Brandwonden",
            "Type": "WholeNumberData",
            "Question": "Wat is de voorspelde mortaliteit a.g.v. brandwonden?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "%"
              }
            ]
          },
          {
            "BusinessDataId": 768,
            "Version": 2,
            "Name": "ICE Ernstig Irreversibel Neurologisch Lijden",
            "Type": "ListData",
            "Question": "Is er sprake van ernstig irreversibel neurologisch lijden?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "149"
              }
            ]
          },
          {
            "BusinessDataId": 751,
            "Version": 2,
            "Name": "ICE Korte levensverwachting",
            "Type": "ListData",
            "Question": "Heeft de patient een korte levensverwachting?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "136"
              }
            ]
          },
          {
            "BusinessDataId": 785,
            "Version": 3,
            "Name": "ICE Maligniteiten met slechte prognose",
            "Type": "ListData",
            "Question": "Is er sprake van een maligniteit met een slechte prognose?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "159"
              }
            ]
          },
          {
            "BusinessDataId": 786,
            "Version": 2,
            "Name": "ICE Eindstadium orgaanfalen",
            "Type": "ListData",
            "Question": "Is er sprake van een eindstadium orgaanfalen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "160"
              }
            ]
          },
          {
            "BusinessDataId": 799,
            "Version": 2,
            "Name": "ICE Hartfalen levensverwachting <1 jaar",
            "Type": "ListData",
            "Question": "Is de levensverwachting <1 jaar a.g.v.  hartfalen?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "173"
              }
            ]
          },
          {
            "BusinessDataId": 801,
            "Version": 2,
            "Name": "ICE Chronische longziekte levensverwachting <1 jaar",
            "Type": "ListData",
            "Question": "Is de levensverwachting <1 jaar a.g.v.  een chronische longziekte?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "174"
              }
            ]
          },
          {
            "BusinessDataId": 808,
            "Version": 2,
            "Name": "ICE Ernstige COPD",
            "Type": "ListData",
            "Question": "Is er sprake van ernstige COPD?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "180"
              }
            ]
          },
          {
            "BusinessDataId": 815,
            "Version": 2,
            "Name": "ICE Ernstig lage PaO2",
            "Type": "ListData",
            "Question": "Is er sprake van een ernstig lage PaO2?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "188"
              }
            ]
          },
          {
            "BusinessDataId": 810,
            "Version": 2,
            "Name": "ICE Ernstige taaislijmziekte",
            "Type": "ListData",
            "Question": "Is er sprake van ernstige taaislijmziekte?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "182"
              }
            ]
          },
          {
            "BusinessDataId": 811,
            "Version": 2,
            "Name": "ICE Ernstige pulmonaire fibrose",
            "Type": "ListData",
            "Question": "Is er sprake van ernstige pulmonaire fibrose?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "183"
              }
            ]
          },
          {
            "BusinessDataId": 802,
            "Version": 2,
            "Name": "ICE Chronische dialyse-patient levensverwachting <1 jaar",
            "Type": "ListData",
            "Question": "Is de levensverwachting <1 jaar a.g.v. chronische dialyse?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "175"
              }
            ]
          },
          {
            "BusinessDataId": 803,
            "Version": 2,
            "Name": "ICE Gevorderd leverfalen levensverwachting <1 jaar",
            "Type": "ListData",
            "Question": "Is de levensverwachting <1 jaar gevorderd leverfalen ?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "176"
              }
            ]
          },
          {
            "BusinessDataId": 787,
            "Version": 2,
            "Name": "ICE Gevorderde leeftijd",
            "Type": "ListData",
            "Question": "Is er sprake van een hoge biologische leeftijd en daardoor een beperkte prognose?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "161"
              }
            ]
          },
          {
            "BusinessDataId": 820,
            "Version": 2,
            "Name": "ICE Biologische Leeftijd",
            "Type": "ListData",
            "Question": "Wat is de biologische leeftijd van de patient?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "190"
              }
            ]
          },
          {
            "BusinessDataId": 788,
            "Version": 3,
            "Name": "ICE Gevorderd en onomkeerbaar immuungecompromitteerd",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "162"
              }
            ]
          },
          {
            "BusinessDataId": 755,
            "Version": 3,
            "Name": "ICI Voldoet aan inclusievoorwaarden",
            "Type": "ListData",
            "Question": "Zijn er inclusiecriteria op de patient van toepassing?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "138"
              }
            ]
          },
          {
            "BusinessDataId": 756,
            "Version": 3,
            "Name": "ICI Invasieve ventilatoire ondersteuning",
            "Type": "ListData",
            "Question": "Heeft de patient Invasieve ventilatoire ondersteuning nodig?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "139"
              }
            ]
          },
          {
            "BusinessDataId": 758,
            "Version": 3,
            "Name": "ICI Refractaire Hypoxemie",
            "Type": "ListData",
            "Question": "Heeft de patient Invasieve ventilatoire ondersteuning nodig a.g.v. refractaire hypoxemie?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "141"
              }
            ]
          },
          {
            "BusinessDataId": 759,
            "Version": 3,
            "Name": "ICI Respiratoire Acidose",
            "Type": "ListData",
            "Question": "Heeft de patient Invasieve ventilatoire ondersteuning nodig a.g.v. respiratoire acidose?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "142"
              }
            ]
          },
          {
            "BusinessDataId": 1017,
            "Version": 1,
            "Name": "ICI Acidose/Alkalose",
            "Type": "ListData",
            "Question": "Wat is de oorzaak",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "248"
              }
            ]
          },
          {
            "BusinessDataId": 1012,
            "Version": 2,
            "Name": "ICI Bloedgas pH OK",
            "Type": "ListData",
            "Question": "Is de waarde voor bloedgas pH in orde?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "244"
              }
            ]
          },
          {
            "BusinessDataId": 1013,
            "Version": 2,
            "Name": "ICI Bloedgas pCO2 OK",
            "Type": "ListData",
            "Question": "Is de waarde voor bloedgas pC02 in orde?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "245"
              }
            ]
          },
          {
            "BusinessDataId": 1014,
            "Version": 3,
            "Name": "ICI Bloedgas HC03 OK",
            "Type": "ListData",
            "Question": "",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "246"
              }
            ]
          },
          {
            "BusinessDataId": 761,
            "Version": 4,
            "Name": "ICI Onvermogen luchtwegen vrijhouden",
            "Type": "ListData",
            "Question": "Onvermogen om luchtwegen vrij te houden?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "144"
              }
            ]
          },
          {
            "BusinessDataId": 760,
            "Version": 3,
            "Name": "ICI Klinisch bewezen ademhalingsinsufficientie",
            "Type": "ListData",
            "Question": "Heeft de patient Invasieve ventilatoire ondersteuning nodig a.g.v. klinisch bewezen ademhalingsinsufficientie?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "143"
              }
            ]
          },
          {
            "BusinessDataId": 757,
            "Version": 3,
            "Name": "ICI Hypotensie",
            "Type": "ListData",
            "Question": "Heeft de patient hypotensie?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "140"
              }
            ]
          },
          {
            "BusinessDataId": 763,
            "Version": 4,
            "Name": "ICI Lage Systolische bloeddruk",
            "Type": "ListData",
            "Question": "Heeft de patient een lage systolische bloeddruk?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "145"
              }
            ]
          },
          {
            "BusinessDataId": 764,
            "Version": 3,
            "Name": "ICI Relatieve hypotensie + aanwijzingen shock",
            "Type": "ListData",
            "Question": "Heeft de patient relatieve hypotensie met klinische aanwijzingen van shock?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "146"
              }
            ]
          },
          {
            "BusinessDataId": 770,
            "Version": 4,
            "Name": "ICI Verlaagd bewustzijnsniveau",
            "Type": "ListData",
            "Question": "Is er sprake van een verlaagd bewustzijnsniveau?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "150"
              }
            ]
          },
          {
            "BusinessDataId": 775,
            "Version": 3,
            "Name": "ICI Verminderde diurese",
            "Type": "ListData",
            "Question": "Is er sprake van verminderde diurese?",
            "Properties": [
              {
                "Name": "ValueListId",
                "Value": "155"
              }
            ]
          },
          {
            "BusinessDataId": 976,
            "Version": 2,
            "Name": "ICI Mean Arterial Pressure (MAP)",
            "Type": "WholeNumberData",
            "Question": "Wat is de Mean Arterial Pressure (MAP)?",
            "Properties": [
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "mmHg"
              }
            ]
          }
        ],
        "MetaData": [
          {
            "BusinessDataId": 1011,
            "Version": 2,
            "Name": null,
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "14"
              },
              {
                "Name": "MetaDataName",
                "Value": "ICI Hartslag (META)"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumLongValue",
                "Value": "230"
              },
              {
                "Name": "MinimumLongValue",
                "Value": "0"
              },
              {
                "Name": "WholeNumberUnit",
                "Value": "bpm"
              }
            ]
          },
          {
            "BusinessDataId": 727,
            "Version": 2,
            "Name": null,
            "Type": "WholeNumberData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "15"
              },
              {
                "Name": "MetaDataName",
                "Value": "SEH META TriageID"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "MaximumLongValue",
                "Value": ""
              },
              {
                "Name": "MinimumLongValue",
                "Value": ""
              },
              {
                "Name": "WholeNumberUnit",
                "Value": null
              }
            ]
          },
          {
            "BusinessDataId": 1004,
            "Version": 3,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "16"
              },
              {
                "Name": "MetaDataName",
                "Value": "META ICE Aard behandeling maligniteit"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "239"
              }
            ]
          },
          {
            "BusinessDataId": 1001,
            "Version": 3,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "17"
              },
              {
                "Name": "MetaDataName",
                "Value": "META ICE Aard maligniteit"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "237"
              }
            ]
          },
          {
            "BusinessDataId": 795,
            "Version": 4,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "18"
              },
              {
                "Name": "MetaDataName",
                "Value": "META ICE Overleg inhoudsdeskundige"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "170"
              }
            ]
          },
          {
            "BusinessDataId": 999,
            "Version": 3,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "19"
              },
              {
                "Name": "MetaDataName",
                "Value": "META ICE Gemetastaseerde maligniteit"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "235"
              }
            ]
          },
          {
            "BusinessDataId": 798,
            "Version": 2,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "20"
              },
              {
                "Name": "MetaDataName",
                "Value": "META ICE Levensverwachting <1 jaar (overig)"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "172"
              }
            ]
          },
          {
            "BusinessDataId": 771,
            "Version": 4,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "21"
              },
              {
                "Name": "MetaDataName",
                "Value": "ICI Aanzienlijke hoeveelheid secreet"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "151"
              }
            ]
          },
          {
            "BusinessDataId": 772,
            "Version": 3,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "22"
              },
              {
                "Name": "MetaDataName",
                "Value": "ICI Andere luchtwegproblemen"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "152"
              }
            ]
          },
          {
            "BusinessDataId": 1015,
            "Version": 1,
            "Name": null,
            "Type": "ListData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "23"
              },
              {
                "Name": "MetaDataName",
                "Value": "IC Aanpassing IC Triage advies"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "ValueListId",
                "Value": "247"
              }
            ]
          },
          {
            "BusinessDataId": 1016,
            "Version": 1,
            "Name": null,
            "Type": "TextData",
            "Question": null,
            "Properties": [
              {
                "Name": "Required",
                "Value": "False"
              },
              {
                "Name": "Validate",
                "Value": "False"
              },
              {
                "Name": "MetaDataId",
                "Value": "24"
              },
              {
                "Name": "MetaDataName",
                "Value": "IC Verklaring aanpassing IC Triage advies"
              },
              {
                "Name": "MetaDataVersion",
                "Value": "1"
              },
              {
                "Name": "CaseSensitive",
                "Value": ""
              },
              {
                "Name": "MaximumTextLength",
                "Value": ""
              }
            ]
          }
        ],
        "PairData": [],
        "ListData": [
          {
            "ListId": 167,
            "Items": [
              {
                "Id": 1703,
                "Order": 2,
                "Value": "2- Witnessed Cardiac Arrest",
                "Name": "2- Witnessed Cardiac Arrest"
              },
              {
                "Id": 1704,
                "Order": 2,
                "Value": "3- Unwitnessed Cardiac Arrest",
                "Name": "3- Unwitnessed Cardiac Arrest"
              },
              {
                "Id": 1705,
                "Order": 2,
                "Value": "1- Geen hartstilstand",
                "Name": "1- Geen hartstilstand"
              },
              {
                "Id": 2008,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 165,
            "Items": [
              {
                "Id": 1700,
                "Order": 2,
                "Value": "GEEN Herhaaldelijke Hartstilstand",
                "Name": "GEEN Herhaaldelijke Hartstilstand"
              },
              {
                "Id": 1701,
                "Order": 2,
                "Value": "WEL Herhaaldelijke Hartstilstand",
                "Name": "WEL Herhaaldelijke Hartstilstand"
              },
              {
                "Id": 1702,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 168,
            "Items": [
              {
                "Id": 1706,
                "Order": 2,
                "Value": "WEL Tweede Hartstilstand <72 uur",
                "Name": "WEL Tweede Hartstilstand <72 uur"
              },
              {
                "Id": 1707,
                "Order": 2,
                "Value": "GEEN Tweede Hartstilstand <72 uur",
                "Name": "GEEN Tweede Hartstilstand <72 uur"
              },
              {
                "Id": 1708,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 223,
            "Items": [
              {
                "Id": 1907,
                "Order": 0,
                "Value": "WEL ROSC",
                "Name": "WEL ROSC"
              },
              {
                "Id": 1908,
                "Order": 0,
                "Value": "GEEN ROSC",
                "Name": "GEEN ROSC"
              },
              {
                "Id": 1909,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 231,
            "Items": [
              {
                "Id": 1948,
                "Order": 0,
                "Value": "WEL cardio vasculaire afwijkingen",
                "Name": "WEL cardio vasculaire afwijkingen"
              },
              {
                "Id": 1949,
                "Order": 0,
                "Value": "Geen cardio vasculaire afwijkingen",
                "Name": "Geen cardio vasculaire afwijkingen"
              },
              {
                "Id": 1950,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 232,
            "Items": [
              {
                "Id": 1951,
                "Order": 0,
                "Value": "WEL Trauma",
                "Name": "WEL Trauma"
              },
              {
                "Id": 1952,
                "Order": 0,
                "Value": "GEEN Trauma",
                "Name": "GEEN Trauma"
              },
              {
                "Id": 1953,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 233,
            "Items": [
              {
                "Id": 1954,
                "Order": 0,
                "Value": "WEL Brandwonden",
                "Name": "WEL Brandwonden"
              },
              {
                "Id": 1955,
                "Order": 0,
                "Value": "GEEN Brandwonden",
                "Name": "GEEN Brandwonden"
              },
              {
                "Id": 1956,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 224,
            "Items": [
              {
                "Id": 1910,
                "Order": 0,
                "Value": "WEL Luchtwegverbranding",
                "Name": "WEL Luchtwegverbranding"
              },
              {
                "Id": 1911,
                "Order": 0,
                "Value": "GEEN Luchtwegverbranding",
                "Name": "GEEN Luchtwegverbranding"
              },
              {
                "Id": 1992,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 178,
            "Items": [
              {
                "Id": 1741,
                "Order": 0,
                "Value": "WEL Ernstig (post-anoxische) hersenschade",
                "Name": "WEL Ernstig (post-anoxische) hersenschade"
              },
              {
                "Id": 1742,
                "Order": 0,
                "Value": "GEEN Ernstig (post-anoxische) hersenschade",
                "Name": "GEEN Ernstig (post-anoxische) hersenschade"
              },
              {
                "Id": 1743,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 185,
            "Items": [
              {
                "Id": 1762,
                "Order": 0,
                "Value": "GEEN Vergevorderde niet behandelbare neuromusculaire aandoeningen",
                "Name": "GEEN Vergevorderde niet behandelbare neuromusculaire aandoeningen"
              },
              {
                "Id": 1763,
                "Order": 0,
                "Value": "WEL Vergevorderde niet behandelbare neuromusculaire aandoeningen",
                "Name": "WEL Vergevorderde niet behandelbare neuromusculaire aandoeningen"
              },
              {
                "Id": 1764,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 186,
            "Items": [
              {
                "Id": 1765,
                "Order": 0,
                "Value": "GEEN Gevorderde Neurodegeneratieve ziekten",
                "Name": "GEEN Gevorderde Neurodegeneratieve ziekten"
              },
              {
                "Id": 1766,
                "Order": 0,
                "Value": "WEL Gevorderde Neurodegeneratieve ziekten",
                "Name": "WEL Gevorderde Neurodegeneratieve ziekten"
              },
              {
                "Id": 1767,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 234,
            "Items": [
              {
                "Id": 1957,
                "Order": 0,
                "Value": "WEL Neurologische afwijkingen",
                "Name": "WEL Neurologische afwijkingen"
              },
              {
                "Id": 1958,
                "Order": 0,
                "Value": "GEEN Neurologische afwijkingen",
                "Name": "GEEN Neurologische afwijkingen"
              },
              {
                "Id": 1959,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 163,
            "Items": [
              {
                "Id": 1694,
                "Order": 0,
                "Value": "WEL Electieve palliatieve chirurgie",
                "Name": "WEL Electieve palliatieve chirurgie"
              },
              {
                "Id": 1695,
                "Order": 0,
                "Value": "GEEN Electieve palliatieve chirurgie",
                "Name": "GEEN Electieve palliatieve chirurgie"
              },
              {
                "Id": 1696,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 171,
            "Items": [
              {
                "Id": 1718,
                "Order": 2,
                "Value": "GEEN maligniteit",
                "Name": "GEEN maligniteit"
              },
              {
                "Id": 1719,
                "Order": 2,
                "Value": "WEL maligniteit",
                "Name": "WEL maligniteit"
              },
              {
                "Id": 1720,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 159,
            "Items": [
              {
                "Id": 1682,
                "Order": 0,
                "Value": "GEEN Slechte prognose bij maligniteit",
                "Name": "GEEN Slechte prognose bij maligniteit"
              },
              {
                "Id": 1683,
                "Order": 0,
                "Value": "WEL Slechte prognose bij maligniteit",
                "Name": "WEL Slechte prognose bij maligniteit"
              },
              {
                "Id": 1684,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 177,
            "Items": [
              {
                "Id": 1736,
                "Order": 0,
                "Value": "Class I",
                "Name": "Class I"
              },
              {
                "Id": 1737,
                "Order": 0,
                "Value": "Class II",
                "Name": "Class II"
              },
              {
                "Id": 1738,
                "Order": 0,
                "Value": "Class III",
                "Name": "Class III"
              },
              {
                "Id": 1739,
                "Order": 0,
                "Value": "Class IV",
                "Name": "Class IV"
              },
              {
                "Id": 1740,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 236,
            "Items": [
              {
                "Id": 1963,
                "Order": 0,
                "Value": "WEL Chronisch hartfalen",
                "Name": "WEL Chronisch hartfalen"
              },
              {
                "Id": 1964,
                "Order": 0,
                "Value": "GEEN Chronisch hartfalen",
                "Name": "GEEN Chronisch hartfalen"
              },
              {
                "Id": 1965,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 179,
            "Items": [
              {
                "Id": 1747,
                "Order": 0,
                "Value": "WEL Overige ernstige chronische longziektes",
                "Name": "WEL Overige ernstige chronische longziektes"
              },
              {
                "Id": 1748,
                "Order": 0,
                "Value": "GEEN Overige ernstige chronische longziektes",
                "Name": "GEEN Overige ernstige chronische longziektes"
              },
              {
                "Id": 1749,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 184,
            "Items": [
              {
                "Id": 1759,
                "Order": 0,
                "Value": "WEL Thuis zuurstofbehoeftig",
                "Name": "WEL Thuis zuurstofbehoeftig"
              },
              {
                "Id": 1760,
                "Order": 0,
                "Value": "GEEN Thuis zuurstofbehoeftig",
                "Name": "GEEN Thuis zuurstofbehoeftig"
              },
              {
                "Id": 1761,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 229,
            "Items": [
              {
                "Id": 1943,
                "Order": 0,
                "Value": "WEL Pulmonale afwijkingen",
                "Name": "WEL Pulmonale afwijkingen"
              },
              {
                "Id": 1944,
                "Order": 0,
                "Value": "GEEN Pulmonale afwijkingen",
                "Name": "GEEN Pulmonale afwijkingen"
              },
              {
                "Id": 1945,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 189,
            "Items": [
              {
                "Id": 1771,
                "Order": 0,
                "Value": "WEL COPD",
                "Name": "WEL COPD"
              },
              {
                "Id": 1772,
                "Order": 0,
                "Value": "GEEN COPD",
                "Name": "GEEN COPD"
              },
              {
                "Id": 1773,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 226,
            "Items": [
              {
                "Id": 1914,
                "Order": 0,
                "Value": "GOLD 1",
                "Name": "GOLD 1"
              },
              {
                "Id": 1915,
                "Order": 0,
                "Value": "GOLD 2",
                "Name": "GOLD 2"
              },
              {
                "Id": 1916,
                "Order": 0,
                "Value": "GOLD 3",
                "Name": "GOLD 3"
              },
              {
                "Id": 1917,
                "Order": 0,
                "Value": "GOLD 4",
                "Name": "GOLD 4"
              },
              {
                "Id": 2009,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 191,
            "Items": [
              {
                "Id": 1777,
                "Order": 3,
                "Value": "WEL Taaislijmziekte",
                "Name": "WEL Taaislijmziekte"
              },
              {
                "Id": 1778,
                "Order": 3,
                "Value": "GEEN Taaislijmziekte",
                "Name": "GEEN Taaislijmziekte"
              },
              {
                "Id": 1779,
                "Order": 3,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 193,
            "Items": [
              {
                "Id": 1786,
                "Order": 0,
                "Value": "WEL Pulmonaire fibrose",
                "Name": "WEL Pulmonaire fibrose"
              },
              {
                "Id": 1787,
                "Order": 0,
                "Value": "GEEN Pulmonaire fibrose",
                "Name": "GEEN Pulmonaire fibrose"
              },
              {
                "Id": 1788,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 194,
            "Items": [
              {
                "Id": 1790,
                "Order": 0,
                "Value": "WEL Chronische dialyse-patient",
                "Name": "WEL Chronische dialyse-patient"
              },
              {
                "Id": 1791,
                "Order": 0,
                "Value": "GEEN Chronische dialyse-patient",
                "Name": "GEEN Chronische dialyse-patient"
              },
              {
                "Id": 1792,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 195,
            "Items": [
              {
                "Id": 1793,
                "Order": 0,
                "Value": "WEL Contra-indicatie transplantatie",
                "Name": "WEL Contra-indicatie transplantatie"
              },
              {
                "Id": 1794,
                "Order": 0,
                "Value": "GEEN Contra-indicatie transplantatie",
                "Name": "GEEN Contra-indicatie transplantatie"
              },
              {
                "Id": 1795,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 240,
            "Items": [
              {
                "Id": 1993,
                "Order": 0,
                "Value": "WEL Nefrologische afwijkingen",
                "Name": "WEL Nefrologische afwijkingen"
              },
              {
                "Id": 1994,
                "Order": 0,
                "Value": "GEEN Nefrologische afwijkingen",
                "Name": "GEEN Nefrologische afwijkingen"
              },
              {
                "Id": 1995,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 241,
            "Items": [
              {
                "Id": 1999,
                "Order": 0,
                "Value": "WEL Chronisch leverfalen",
                "Name": "WEL Chronisch leverfalen"
              },
              {
                "Id": 2000,
                "Order": 0,
                "Value": "GEEN Chronisch leverfalen",
                "Name": "GEEN Chronisch leverfalen"
              },
              {
                "Id": 2001,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 242,
            "Items": [
              {
                "Id": 1996,
                "Order": 3,
                "Value": "WEL Abdominale/hepatische afwijkingen",
                "Name": "WEL Abdominale/hepatische afwijkingen"
              },
              {
                "Id": 1997,
                "Order": 3,
                "Value": "GEEN Abdominale/hepatische afwijkingen",
                "Name": "GEEN Abdominale/hepatische afwijkingen"
              },
              {
                "Id": 1998,
                "Order": 3,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 192,
            "Items": [
              {
                "Id": 1780,
                "Order": 0,
                "Value": "6 Moderately frail",
                "Name": "6 Moderately frail"
              },
              {
                "Id": 1781,
                "Order": 0,
                "Value": "7 Severely frail",
                "Name": "7 Severely frail"
              },
              {
                "Id": 1782,
                "Order": 0,
                "Value": "8 Very severely frail",
                "Name": "8 Very severely frail"
              },
              {
                "Id": 1783,
                "Order": 0,
                "Value": "9 Terminally ill",
                "Name": "9 Terminally ill"
              },
              {
                "Id": 1784,
                "Order": 0,
                "Value": "5 Mildly frail",
                "Name": "5 Mildly frail"
              },
              {
                "Id": 1785,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 1939,
                "Order": 0,
                "Value": "4 Vulnerable",
                "Name": "4 Vulnerable"
              },
              {
                "Id": 1940,
                "Order": 0,
                "Value": "3 Managing well",
                "Name": "3 Managing well"
              },
              {
                "Id": 1941,
                "Order": 0,
                "Value": "2 Well",
                "Name": "2 Well"
              },
              {
                "Id": 1942,
                "Order": 0,
                "Value": "1 Very fit",
                "Name": "1 Very fit"
              }
            ]
          },
          {
            "ListId": 243,
            "Items": [
              {
                "Id": 2002,
                "Order": 0,
                "Value": "WEL Dementie",
                "Name": "WEL Dementie"
              },
              {
                "Id": 2003,
                "Order": 0,
                "Value": "GEEN Dementie",
                "Name": "GEEN Dementie"
              },
              {
                "Id": 2004,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 196,
            "Items": [
              {
                "Id": 1796,
                "Order": 0,
                "Value": "WEL Medicatie resistent AIDS",
                "Name": "WEL Medicatie resistent AIDS"
              },
              {
                "Id": 1797,
                "Order": 0,
                "Value": "GEEN Medicatie resistent AIDS",
                "Name": "GEEN Medicatie resistent AIDS"
              },
              {
                "Id": 1798,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 197,
            "Items": [
              {
                "Id": 1799,
                "Order": 0,
                "Value": "WEL Overige immuungecompromitteerd",
                "Name": "WEL Overige immuungecompromitteerd"
              },
              {
                "Id": 1800,
                "Order": 0,
                "Value": "GEEN Overige immuungecompromitteerd",
                "Name": "GEEN Overige immuungecompromitteerd"
              },
              {
                "Id": 1801,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 198,
            "Items": [
              {
                "Id": 1802,
                "Order": 0,
                "Value": "WEL inhoudsdeskundige collega betrokken",
                "Name": "WEL inhoudsdeskundige collega betrokken"
              },
              {
                "Id": 1804,
                "Order": 0,
                "Value": "GEEN inhoudsdeskundige collega betrokken",
                "Name": "GEEN inhoudsdeskundige collega betrokken"
              },
              {
                "Id": 1806,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 212,
            "Items": [
              {
                "Id": 1868,
                "Order": 0,
                "Value": "0- Geen zuurstof",
                "Name": "0- Geen zuurstof"
              },
              {
                "Id": 1869,
                "Order": 0,
                "Value": "1 l/min",
                "Name": "1 l/min"
              },
              {
                "Id": 1870,
                "Order": 0,
                "Value": "2 l/min",
                "Name": "2 l/min"
              },
              {
                "Id": 1871,
                "Order": 0,
                "Value": "3 l/min",
                "Name": "3 l/min"
              },
              {
                "Id": 1872,
                "Order": 0,
                "Value": "4 l/min",
                "Name": "4 l/min"
              },
              {
                "Id": 1873,
                "Order": 0,
                "Value": "5 l/min",
                "Name": "5 l/min"
              },
              {
                "Id": 1874,
                "Order": 0,
                "Value": "6 l/min",
                "Name": "6 l/min"
              },
              {
                "Id": 1875,
                "Order": 0,
                "Value": "7- Venturi-masker",
                "Name": "7- Venturi-masker"
              },
              {
                "Id": 1876,
                "Order": 0,
                "Value": "8- Non-rebreathing mask FiO2>0.85",
                "Name": "8- Non-rebreathing mask FiO2>0.85"
              },
              {
                "Id": 2010,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 219,
            "Items": [
              {
                "Id": 1894,
                "Order": 0,
                "Value": "WEL Onvermogen vrijhouden luchtwegen",
                "Name": "WEL Onvermogen vrijhouden luchtwegen"
              },
              {
                "Id": 1895,
                "Order": 0,
                "Value": "GEEN Onvermogen vrijhouden luchtwegen",
                "Name": "GEEN Onvermogen vrijhouden luchtwegen"
              },
              {
                "Id": 1896,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 213,
            "Items": [
              {
                "Id": 1877,
                "Order": 0,
                "Value": "WEL Dreigende uitputting",
                "Name": "WEL Dreigende uitputting"
              },
              {
                "Id": 1878,
                "Order": 0,
                "Value": "GEEN Dreigende uitputting",
                "Name": "GEEN Dreigende uitputting"
              },
              {
                "Id": 2007,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 214,
            "Items": [
              {
                "Id": 1879,
                "Order": 0,
                "Value": "WEL gebruik hulpademhalingsspieren",
                "Name": "WEL gebruik hulpademhalingsspieren"
              },
              {
                "Id": 1880,
                "Order": 0,
                "Value": "GEEN gebruik hulpademhalingsspieren",
                "Name": "GEEN gebruik hulpademhalingsspieren"
              },
              {
                "Id": 2005,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 154,
            "Items": [
              {
                "Id": 1658,
                "Order": 0,
                "Value": "GEEN Reactie op vloeistoftherapie",
                "Name": "GEEN Reactie op vloeistoftherapie"
              },
              {
                "Id": 1659,
                "Order": 0,
                "Value": "WEL Reactie op vloeistoftherapie",
                "Name": "WEL Reactie op vloeistoftherapie"
              },
              {
                "Id": 1660,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 217,
            "Items": [
              {
                "Id": 1888,
                "Order": 1,
                "Value": "1- Alert",
                "Name": "1- Alert"
              },
              {
                "Id": 1889,
                "Order": 1,
                "Value": "2- Verbal",
                "Name": "2- Verbal"
              },
              {
                "Id": 1890,
                "Order": 1,
                "Value": "3- Pain",
                "Name": "3- Pain"
              },
              {
                "Id": 1891,
                "Order": 1,
                "Value": "4- Unresponsive",
                "Name": "4- Unresponsive"
              },
              {
                "Id": 2006,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 199,
            "Items": [
              {
                "Id": 1803,
                "Order": 0,
                "Value": "NIET opnemen op IC",
                "Name": "NIET opnemen op IC"
              },
              {
                "Id": 1805,
                "Order": 0,
                "Value": "WEL opnemen op IC",
                "Name": "WEL opnemen op IC"
              },
              {
                "Id": 1807,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 133,
            "Items": [
              {
                "Id": 1611,
                "Order": 2,
                "Value": "Voldoet WEL aan exclusiecriteria",
                "Name": "Voldoet WEL aan exclusiecriteria"
              },
              {
                "Id": 1612,
                "Order": 2,
                "Value": "Voldoet NIET aan exclusiecriteria",
                "Name": "Voldoet NIET aan exclusiecriteria"
              },
              {
                "Id": 1640,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 135,
            "Items": [
              {
                "Id": 1607,
                "Order": 2,
                "Value": "WEL Lage waarschijnlijkheid om te overleven",
                "Name": "WEL Lage waarschijnlijkheid om te overleven"
              },
              {
                "Id": 1608,
                "Order": 2,
                "Value": "GEEN Lage waarschijnlijkheid om te overleven",
                "Name": "GEEN Lage waarschijnlijkheid om te overleven"
              },
              {
                "Id": 1641,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 134,
            "Items": [
              {
                "Id": 1643,
                "Order": 1,
                "Value": "WEL Hartstilstand lage overleving",
                "Name": "WEL Hartstilstand lage overleving"
              },
              {
                "Id": 1644,
                "Order": 1,
                "Value": "GEEN Hartstilstand lage overleving",
                "Name": "GEEN Hartstilstand lage overleving"
              },
              {
                "Id": 1645,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 147,
            "Items": [
              {
                "Id": 1670,
                "Order": 0,
                "Value": "GEEN Ernstig Trauma",
                "Name": "GEEN Ernstig Trauma"
              },
              {
                "Id": 1671,
                "Order": 0,
                "Value": "WEL Ernstig Trauma",
                "Name": "WEL Ernstig Trauma"
              },
              {
                "Id": 1672,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 148,
            "Items": [
              {
                "Id": 1673,
                "Order": 3,
                "Value": "GEEN Ernstige Brandwonden",
                "Name": "GEEN Ernstige Brandwonden"
              },
              {
                "Id": 1674,
                "Order": 3,
                "Value": "WEL Ernstige Brandwonden",
                "Name": "WEL Ernstige Brandwonden"
              },
              {
                "Id": 1675,
                "Order": 3,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 149,
            "Items": [
              {
                "Id": 1676,
                "Order": 0,
                "Value": "GEEN Ernstig Irreversibel Neurologisch Lijden",
                "Name": "GEEN Ernstig Irreversibel Neurologisch Lijden"
              },
              {
                "Id": 1677,
                "Order": 0,
                "Value": "WEL Ernstig Irreversibel Neurologisch Lijden",
                "Name": "WEL Ernstig Irreversibel Neurologisch Lijden"
              },
              {
                "Id": 1678,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 136,
            "Items": [
              {
                "Id": 1609,
                "Order": 0,
                "Value": "WEL Korte levensverwachting",
                "Name": "WEL Korte levensverwachting"
              },
              {
                "Id": 1610,
                "Order": 0,
                "Value": "GEEN Korte levensverwachting",
                "Name": "GEEN Korte levensverwachting"
              },
              {
                "Id": 1642,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 160,
            "Items": [
              {
                "Id": 1685,
                "Order": 0,
                "Value": "WEL Eindstadium orgaanfalen",
                "Name": "WEL Eindstadium orgaanfalen"
              },
              {
                "Id": 1686,
                "Order": 0,
                "Value": "GEEN Eindstadium orgaanfalen",
                "Name": "GEEN Eindstadium orgaanfalen"
              },
              {
                "Id": 1687,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 173,
            "Items": [
              {
                "Id": 1724,
                "Order": 0,
                "Value": "WEL Hartfalen levensverwachting <1 jaar",
                "Name": "WEL Hartfalen levensverwachting <1 jaar"
              },
              {
                "Id": 1725,
                "Order": 0,
                "Value": "GEEN Hartfalen levensverwachting <1 jaar",
                "Name": "GEEN Hartfalen levensverwachting <1 jaar"
              },
              {
                "Id": 1726,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 174,
            "Items": [
              {
                "Id": 1727,
                "Order": 0,
                "Value": "WEL Chronische longziekte levensverwachting <1 jaar",
                "Name": "WEL Chronische longziekte levensverwachting <1 jaar"
              },
              {
                "Id": 1728,
                "Order": 0,
                "Value": "GEEN Chronische longziekte levensverwachting <1 jaar",
                "Name": "GEEN Chronische longziekte levensverwachting <1 jaar"
              },
              {
                "Id": 1729,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 180,
            "Items": [
              {
                "Id": 1750,
                "Order": 0,
                "Value": "WEL Ernstige COPD",
                "Name": "WEL Ernstige COPD"
              },
              {
                "Id": 1751,
                "Order": 0,
                "Value": "GEEN Ernstige COPD",
                "Name": "GEEN Ernstige COPD"
              },
              {
                "Id": 1752,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 188,
            "Items": [
              {
                "Id": 1768,
                "Order": 0,
                "Value": "WEL Ernstig lage PaO2",
                "Name": "WEL Ernstig lage PaO2"
              },
              {
                "Id": 1769,
                "Order": 0,
                "Value": "GEEN Ernstig lage PaO2",
                "Name": "GEEN Ernstig lage PaO2"
              },
              {
                "Id": 1770,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 182,
            "Items": [
              {
                "Id": 1753,
                "Order": 0,
                "Value": "WEL Ernstige taaislijmziekte",
                "Name": "WEL Ernstige taaislijmziekte"
              },
              {
                "Id": 1754,
                "Order": 0,
                "Value": "GEEN Ernstige taaislijmziekte",
                "Name": "GEEN Ernstige taaislijmziekte"
              },
              {
                "Id": 1755,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 183,
            "Items": [
              {
                "Id": 1756,
                "Order": 0,
                "Value": "WEL Ernstige pulmonaire fibrose",
                "Name": "WEL Ernstige pulmonaire fibrose"
              },
              {
                "Id": 1757,
                "Order": 0,
                "Value": "GEEN Ernstige pulmonaire fibrose",
                "Name": "GEEN Ernstige pulmonaire fibrose"
              },
              {
                "Id": 1758,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 175,
            "Items": [
              {
                "Id": 1730,
                "Order": 0,
                "Value": "WEL Chronische dialyse-patient levensverwachting <1 jaar",
                "Name": "WEL Chronische dialyse-patient levensverwachting <1 jaar"
              },
              {
                "Id": 1731,
                "Order": 0,
                "Value": "GEEN Chronische dialyse-patient levensverwachting <1 jaar",
                "Name": "GEEN Chronische dialyse-patient levensverwachting <1 jaar"
              },
              {
                "Id": 1732,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 176,
            "Items": [
              {
                "Id": 1733,
                "Order": 0,
                "Value": "WEL Gevorderd leverfalen levensverwachting <1 jaar",
                "Name": "WEL Gevorderd leverfalen levensverwachting <1 jaar"
              },
              {
                "Id": 1734,
                "Order": 0,
                "Value": "GEEN Gevorderd leverfalen levensverwachting <1 jaar",
                "Name": "GEEN Gevorderd leverfalen levensverwachting <1 jaar"
              },
              {
                "Id": 1735,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 161,
            "Items": [
              {
                "Id": 1688,
                "Order": 0,
                "Value": "WEL Gevorderde leeftijd",
                "Name": "WEL Gevorderde leeftijd"
              },
              {
                "Id": 1689,
                "Order": 0,
                "Value": "GEEN Gevorderde leeftijd",
                "Name": "GEEN Gevorderde leeftijd"
              },
              {
                "Id": 1690,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 190,
            "Items": [
              {
                "Id": 1774,
                "Order": 0,
                "Value": "Kind",
                "Name": "Kind"
              },
              {
                "Id": 1775,
                "Order": 0,
                "Value": "Volwassene",
                "Name": "Volwassene"
              },
              {
                "Id": 1776,
                "Order": 0,
                "Value": "Hoge Biologische Leeftijd",
                "Name": "Hoge Biologische Leeftijd"
              },
              {
                "Id": 1789,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 162,
            "Items": [
              {
                "Id": 1691,
                "Order": 0,
                "Value": "WEL Gevorderd en onomkeerbaar immuungecompromitteerd",
                "Name": "WEL Gevorderd en onomkeerbaar immuungecompromitteerd"
              },
              {
                "Id": 1692,
                "Order": 0,
                "Value": "GEEN Gevorderd en onomkeerbaar immuungecompromitteerd",
                "Name": "GEEN Gevorderd en onomkeerbaar immuungecompromitteerd"
              },
              {
                "Id": 1693,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 138,
            "Items": [
              {
                "Id": 1613,
                "Order": 3,
                "Value": "Voldoet WEL aan inclusievoorwaarden opname IC fase 3",
                "Name": "Voldoet WEL aan inclusievoorwaarden opname IC fase 3"
              },
              {
                "Id": 1614,
                "Order": 3,
                "Value": "Voldoet NIET aan inclusievoorwaarden opname IC fase 3",
                "Name": "Voldoet NIET aan inclusievoorwaarden opname IC fase 3"
              },
              {
                "Id": 1615,
                "Order": 3,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 139,
            "Items": [
              {
                "Id": 1616,
                "Order": 0,
                "Value": "GEEN Invasieve ventilatoire ondersteuning nodig",
                "Name": "GEEN Invasieve ventilatoire ondersteuning nodig"
              },
              {
                "Id": 1617,
                "Order": 0,
                "Value": "WEL Invasieve ventilatoire ondersteuning nodig",
                "Name": "WEL Invasieve ventilatoire ondersteuning nodig"
              },
              {
                "Id": 1618,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 141,
            "Items": [
              {
                "Id": 1622,
                "Order": 0,
                "Value": "GEEN Refractaire Hypoxemie",
                "Name": "GEEN Refractaire Hypoxemie"
              },
              {
                "Id": 1623,
                "Order": 0,
                "Value": "WEL Refractaire Hypoxemie",
                "Name": "WEL Refractaire Hypoxemie"
              },
              {
                "Id": 1624,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 142,
            "Items": [
              {
                "Id": 1625,
                "Order": 3,
                "Value": "GEEN Respiratoire Acidose",
                "Name": "GEEN Respiratoire Acidose"
              },
              {
                "Id": 1626,
                "Order": 3,
                "Value": "WEL Respiratoire Acidose",
                "Name": "WEL Respiratoire Acidose"
              },
              {
                "Id": 1627,
                "Order": 3,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 248,
            "Items": [
              {
                "Id": 2023,
                "Order": 0,
                "Value": "Geen afwijkende bloed pH",
                "Name": "Geen afwijkende bloed pH"
              },
              {
                "Id": 2024,
                "Order": 0,
                "Value": "Respiratoire acidose",
                "Name": "Respiratoire acidose"
              },
              {
                "Id": 2025,
                "Order": 0,
                "Value": "Metabole acidose",
                "Name": "Metabole acidose"
              },
              {
                "Id": 2026,
                "Order": 0,
                "Value": "Respiratoire alkalose",
                "Name": "Respiratoire alkalose"
              },
              {
                "Id": 2027,
                "Order": 0,
                "Value": "Metabole alkalose",
                "Name": "Metabole alkalose"
              },
              {
                "Id": 2028,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 244,
            "Items": [
              {
                "Id": 2011,
                "Order": 0,
                "Value": "Bloedgas pH NORMAAL",
                "Name": "Bloedgas pH NORMAAL"
              },
              {
                "Id": 2012,
                "Order": 0,
                "Value": "Bloedgas pH TE HOOG",
                "Name": "Bloedgas pH TE HOOG"
              },
              {
                "Id": 2013,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 2031,
                "Order": 0,
                "Value": "Bloedgas pH TE LAAG",
                "Name": "Bloedgas pH TE LAAG"
              }
            ]
          },
          {
            "ListId": 245,
            "Items": [
              {
                "Id": 2014,
                "Order": 1,
                "Value": "Bloedgas pCO2 NORMAAL",
                "Name": "Bloedgas pCO2 NORMAAL"
              },
              {
                "Id": 2015,
                "Order": 1,
                "Value": "Bloedgas pCO2 TE HOOG",
                "Name": "Bloedgas pCO2 TE HOOG"
              },
              {
                "Id": 2016,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 2030,
                "Order": 1,
                "Value": "Bloedgas pCO2 TE LAAG",
                "Name": "Bloedgas pCO2 TE LAAG"
              }
            ]
          },
          {
            "ListId": 246,
            "Items": [
              {
                "Id": 2017,
                "Order": 0,
                "Value": "Bloedgas HC03 NORMAAL",
                "Name": "Bloedgas HC03 NORMAAL"
              },
              {
                "Id": 2018,
                "Order": 0,
                "Value": "Bloedgas HC03 TE HOOG",
                "Name": "Bloedgas HC03 TE HOOG"
              },
              {
                "Id": 2019,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 2029,
                "Order": 0,
                "Value": "Bloedgas HCO3 TE LAAG",
                "Name": "Bloedgas HCO3 TE LAAG"
              }
            ]
          },
          {
            "ListId": 144,
            "Items": [
              {
                "Id": 1631,
                "Order": 0,
                "Value": "GEEN Onvermogen luchtwegen vrijhouden",
                "Name": "GEEN Onvermogen luchtwegen vrijhouden"
              },
              {
                "Id": 1632,
                "Order": 0,
                "Value": "WEL Onvermogen luchtwegen vrijhouden",
                "Name": "WEL Onvermogen luchtwegen vrijhouden"
              },
              {
                "Id": 1633,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 143,
            "Items": [
              {
                "Id": 1628,
                "Order": 4,
                "Value": "GEEN Klinisch bewezen ademshalingsinsufficientie",
                "Name": "GEEN Klinisch bewezen ademshalingsinsufficientie"
              },
              {
                "Id": 1629,
                "Order": 4,
                "Value": "WEL Klinisch bewezen ademshalingsinsufficientie",
                "Name": "WEL Klinisch bewezen ademshalingsinsufficientie"
              },
              {
                "Id": 1630,
                "Order": 4,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              },
              {
                "Id": 1885,
                "Order": 4,
                "Value": "MOGELIJK Klinisch bewezen ademshalingsinsufficientie",
                "Name": "MOGELIJK Klinisch bewezen ademshalingsinsufficientie"
              }
            ]
          },
          {
            "ListId": 140,
            "Items": [
              {
                "Id": 1619,
                "Order": 2,
                "Value": "GEEN Hypotensie",
                "Name": "GEEN Hypotensie"
              },
              {
                "Id": 1620,
                "Order": 2,
                "Value": "WEL Hypotensie",
                "Name": "WEL Hypotensie"
              },
              {
                "Id": 1621,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 145,
            "Items": [
              {
                "Id": 1634,
                "Order": 2,
                "Value": "GEEN Lage Systolische bloeddruk",
                "Name": "GEEN Lage Systolische bloeddruk"
              },
              {
                "Id": 1635,
                "Order": 2,
                "Value": "WEL Lage Systolische bloeddruk",
                "Name": "WEL Lage Systolische bloeddruk"
              },
              {
                "Id": 1636,
                "Order": 2,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 146,
            "Items": [
              {
                "Id": 1637,
                "Order": 0,
                "Value": "GEEN Relatieve hypotensie + aanwijzingen shock",
                "Name": "GEEN Relatieve hypotensie + aanwijzingen shock"
              },
              {
                "Id": 1638,
                "Order": 0,
                "Value": "WEL Relatieve hypotensie + aanwijzingen shock",
                "Name": "WEL Relatieve hypotensie + aanwijzingen shock"
              },
              {
                "Id": 1639,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 150,
            "Items": [
              {
                "Id": 1646,
                "Order": 1,
                "Value": "GEEN Verlaagd bewustzijnsniveau",
                "Name": "GEEN Verlaagd bewustzijnsniveau"
              },
              {
                "Id": 1647,
                "Order": 0,
                "Value": "WEL Verlaagd bewustzijnsniveau",
                "Name": "WEL Verlaagd bewustzijnsniveau"
              },
              {
                "Id": 1648,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 155,
            "Items": [
              {
                "Id": 1661,
                "Order": 1,
                "Value": "GEEN Verminderde Diurese",
                "Name": "GEEN Verminderde Diurese"
              },
              {
                "Id": 1662,
                "Order": 1,
                "Value": "WEL Verminderde Diurese",
                "Name": "WEL Verminderde Diurese"
              },
              {
                "Id": 1663,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 239,
            "Items": [
              {
                "Id": 1988,
                "Order": 0,
                "Value": "GEEN Palliatieve behandeling (curatief)",
                "Name": "GEEN Palliatieve behandeling (curatief)"
              },
              {
                "Id": 1989,
                "Order": 0,
                "Value": "WEL Palliatieve behandeling",
                "Name": "WEL Palliatieve behandeling"
              },
              {
                "Id": 1990,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 237,
            "Items": [
              {
                "Id": 1966,
                "Order": 0,
                "Value": "Blaaskanker",
                "Name": "Blaaskanker"
              },
              {
                "Id": 1967,
                "Order": 0,
                "Value": "Borstkanker",
                "Name": "Borstkanker"
              },
              {
                "Id": 1971,
                "Order": 0,
                "Value": "Bot- en wekedelenkanker",
                "Name": "Bot- en wekedelenkanker"
              },
              {
                "Id": 1972,
                "Order": 0,
                "Value": "Darmkanker",
                "Name": "Darmkanker"
              },
              {
                "Id": 1973,
                "Order": 0,
                "Value": "Eierstokkanker",
                "Name": "Eierstokkanker"
              },
              {
                "Id": 1975,
                "Order": 0,
                "Value": "Hemato-oncologie",
                "Name": "Hemato-oncologie"
              },
              {
                "Id": 1976,
                "Order": 0,
                "Value": "Hersentumoren",
                "Name": "Hersentumoren"
              },
              {
                "Id": 1977,
                "Order": 0,
                "Value": "Hoofd-halskanker",
                "Name": "Hoofd-halskanker"
              },
              {
                "Id": 1978,
                "Order": 0,
                "Value": "Huidkanker",
                "Name": "Huidkanker"
              },
              {
                "Id": 1979,
                "Order": 0,
                "Value": "HPB-tumoren",
                "Name": "HPB-tumoren"
              },
              {
                "Id": 1981,
                "Order": 0,
                "Value": "Longkanker",
                "Name": "Longkanker"
              },
              {
                "Id": 1982,
                "Order": 0,
                "Value": "Neuro-endocriene neoplasie",
                "Name": "Neuro-endocriene neoplasie"
              },
              {
                "Id": 1983,
                "Order": 0,
                "Value": "Prostaatkanker",
                "Name": "Prostaatkanker"
              },
              {
                "Id": 1984,
                "Order": 0,
                "Value": "Schildklierkanker",
                "Name": "Schildklierkanker"
              },
              {
                "Id": 1985,
                "Order": 0,
                "Value": "Slokdarm- en maagkanker",
                "Name": "Slokdarm- en maagkanker"
              },
              {
                "Id": 1986,
                "Order": 0,
                "Value": "Zeldzame kanker",
                "Name": "Zeldzame kanker"
              },
              {
                "Id": 1987,
                "Order": 0,
                "Value": "Kanker bij kinderen",
                "Name": "Kanker bij kinderen"
              },
              {
                "Id": 1991,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 170,
            "Items": [
              {
                "Id": 1715,
                "Order": 0,
                "Value": "WEL overleg met inhoudsdeskundige",
                "Name": "WEL overleg met inhoudsdeskundige"
              },
              {
                "Id": 1716,
                "Order": 0,
                "Value": "GEEN overleg met inhoudsdeskundige",
                "Name": "GEEN overleg met inhoudsdeskundige"
              },
              {
                "Id": 1717,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 235,
            "Items": [
              {
                "Id": 1960,
                "Order": 0,
                "Value": "WEL Gemetastaseerd maligniteit",
                "Name": "WEL Gemetastaseerd maligniteit"
              },
              {
                "Id": 1961,
                "Order": 0,
                "Value": "GEEN gemetastaseerde maligniteit",
                "Name": "GEEN gemetastaseerde maligniteit"
              },
              {
                "Id": 1962,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 172,
            "Items": [
              {
                "Id": 1721,
                "Order": 0,
                "Value": "WEL Levensverwachting <1 jaar (overig)",
                "Name": "WEL Levensverwachting <1 jaar (overig)"
              },
              {
                "Id": 1722,
                "Order": 0,
                "Value": "GEEN Levensverwachting <1 jaar (overig)",
                "Name": "GEEN Levensverwachting <1 jaar (overig)"
              },
              {
                "Id": 1723,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 151,
            "Items": [
              {
                "Id": 1649,
                "Order": 1,
                "Value": "GEEN Aanzienlijke hoeveelheid secreet",
                "Name": "GEEN Aanzienlijke hoeveelheid secreet"
              },
              {
                "Id": 1650,
                "Order": 1,
                "Value": "WEL Aanzienlijke hoeveelheid secreet",
                "Name": "WEL Aanzienlijke hoeveelheid secreet"
              },
              {
                "Id": 1651,
                "Order": 1,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 152,
            "Items": [
              {
                "Id": 1652,
                "Order": 0,
                "Value": "GEEN Andere luchtwegproblemen",
                "Name": "GEEN Andere luchtwegproblemen"
              },
              {
                "Id": 1653,
                "Order": 0,
                "Value": "WEL Andere luchtwegproblemen",
                "Name": "WEL Andere luchtwegproblemen"
              },
              {
                "Id": 1654,
                "Order": 0,
                "Value": "Nog niet te bepalen",
                "Name": "Nog niet te bepalen"
              }
            ]
          },
          {
            "ListId": 247,
            "Items": [
              {
                "Id": 2020,
                "Order": 0,
                "Value": "IC Triage advies niet aangepast",
                "Name": "IC Triage advies niet aangepast"
              },
              {
                "Id": 2021,
                "Order": 0,
                "Value": "IC Triage conclusie: Wel opnemen",
                "Name": "IC Triage conclusie: Wel opnemen"
              },
              {
                "Id": 2022,
                "Order": 0,
                "Value": "IC Triage conclusie: Niet opnemen",
                "Name": "IC Triage conclusie: Niet opnemen"
              }
            ]
          }
        ]
      }
    ]
  }
]
```

POST `/api/ApiExecution/execute`
Headers:
`Authorization: Bearer eyJ0eX...`

Request:
```json
{
  "DecisionServiceId": 64,
  "VersionNumber": 38,
  "Reference": 1905472219,
  "ExecutionRequestData": [
    {
      "Key": 762,
      "Value": ""
    },
    {
      "Key": 771,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 774,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 776,
      "Value": ""
    },
    {
      "Key": 788,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 789,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 793,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 794,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 795,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 796,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 797,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 800,
      "Value": ""
    },
    {
      "Key": 804,
      "Value": ""
    },
    {
      "Key": 805,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 806,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 807,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 812,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 813,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 814,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 816,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 817,
      "Value": ""
    },
    {
      "Key": 819,
      "Value": ""
    },
    {
      "Key": 821,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 822,
      "Value": ""
    },
    {
      "Key": 824,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 825,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 826,
      "Value": ""
    },
    {
      "Key": 827,
      "Value": ""
    },
    {
      "Key": 828,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 829,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 830,
      "Value": ""
    },
    {
      "Key": 917,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 919,
      "Value": ""
    },
    {
      "Key": 920,
      "Value": ""
    },
    {
      "Key": 921,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 922,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 945,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 951,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 977,
      "Value": ""
    },
    {
      "Key": 980,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 981,
      "Value": ""
    },
    {
      "Key": 982,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 984,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 991,
      "Value": ""
    },
    {
      "Key": 993,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 995,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 996,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 997,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 998,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 999,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1000,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1001,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1003,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1004,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1005,
      "Value": ""
    },
    {
      "Key": 1007,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1008,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1009,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1010,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1011,
      "Value": ""
    },
    {
      "Key": 1015,
      "Value": "IC Triage advies niet aangepast"
    },
    {
      "Key": 1016,
      "Value": "Nog niet te bepalen"
    },
    {
      "Key": 1018,
      "Value": ""
    },
    {
      "Key": 1020,
      "Value": ""
    }
  ],
  "ExecutionRequestMetaData": []
}
```

Response:
```json
{
  "DecisionTableId": 366,
  "DecisionServiceId": 64,
  "Reference": "1905472219",
  "ExecutionKey": "20201010-131232--91356e27d12e436881f70e8d7830e7c7",
  "FinalConclusionBusinessDataIds": [
    834
  ],
  "ConclusionValueType": 0,
  "HitConclusions": [
    {
      "ConclusionName": "ICE Hartstilstand lage overleving",
      "ConclusionId": 345,
      "DecisionTableName": "ICE Hartstilstand lage overleving",
      "DecisionTableId": 350,
      "BusinessDataId": 749,
      "RowId": 1685,
      "RowExpression": "ICE Herhaaldelijke Hartstilstand In [GEEN Herhaaldelijke Hartstilstand, Nog niet te bepalen] And ICE Tweede Hartstilstand <72 uur In [GEEN Tweede Hartstilstand <72 uur, Nog niet te bepalen] And ICE ROSC In [WEL ROSC, Nog niet te bepalen] And ICE (Un)witnessed Cardiac Arrest = [Nog niet te bepalen] And ICE Cardio vasculaire afwijkingen - kop In [WEL cardio vasculaire afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Hartstilstand lage overleving",
      "ConclusionId": 345,
      "DecisionTableName": "ICE Hartstilstand lage overleving",
      "DecisionTableId": 350,
      "BusinessDataId": 749,
      "RowId": 1688,
      "RowExpression": "ICE Herhaaldelijke Hartstilstand In [GEEN Herhaaldelijke Hartstilstand, Nog niet te bepalen] And ICE Tweede Hartstilstand <72 uur In [GEEN Tweede Hartstilstand <72 uur, Nog niet te bepalen] And ICE ROSC = [Nog niet te bepalen] And ICE (Un)witnessed Cardiac Arrest In [2- Witnessed Cardiac Arrest, Nog niet te bepalen] And ICE Cardio vasculaire afwijkingen - kop In [WEL cardio vasculaire afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Hartstilstand lage overleving",
      "ConclusionId": 345,
      "DecisionTableName": "ICE Hartstilstand lage overleving",
      "DecisionTableId": 350,
      "BusinessDataId": 749,
      "RowId": 1809,
      "RowExpression": "ICE Herhaaldelijke Hartstilstand In [GEEN Herhaaldelijke Hartstilstand, Nog niet te bepalen] And ICE Tweede Hartstilstand <72 uur = [Nog niet te bepalen] And ICE ROSC In [WEL ROSC, Nog niet te bepalen] And ICE (Un)witnessed Cardiac Arrest In [2- Witnessed Cardiac Arrest, Nog niet te bepalen] And ICE Cardio vasculaire afwijkingen - kop In [WEL cardio vasculaire afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 11,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Hartstilstand lage overleving",
      "ConclusionId": 345,
      "DecisionTableName": "ICE Hartstilstand lage overleving",
      "DecisionTableId": 350,
      "BusinessDataId": 749,
      "RowId": 1810,
      "RowExpression": "ICE Herhaaldelijke Hartstilstand = [Nog niet te bepalen] And ICE Tweede Hartstilstand <72 uur In [GEEN Tweede Hartstilstand <72 uur, Nog niet te bepalen] And ICE ROSC In [WEL ROSC, Nog niet te bepalen] And ICE (Un)witnessed Cardiac Arrest In [2- Witnessed Cardiac Arrest, Nog niet te bepalen] And ICE Cardio vasculaire afwijkingen - kop In [WEL cardio vasculaire afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 10,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstig Trauma",
      "ConclusionId": 347,
      "DecisionTableName": "ICE Ernstig Trauma",
      "DecisionTableId": 352,
      "BusinessDataId": 765,
      "RowId": 1311,
      "RowExpression": "ICE Trauma Injury Severity Score (TRISS) Is Empty And ICE Trauma - Kop In [WEL Trauma, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "ConclusionId": 351,
      "DecisionTableName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "DecisionTableId": 356,
      "BusinessDataId": 768,
      "RowId": 1345,
      "RowExpression": "ICE Vergevorderde niet behandelbare neuromusculaire aandoeningen In [GEEN Vergevorderde niet behandelbare neuromusculaire aandoeningen, Nog niet te bepalen] And ICE Gevorderde Neurodegeneratieve ziekten In [GEEN Gevorderde Neurodegeneratieve ziekten, Nog niet te bepalen] And ICE Ernstige (post-anoxische) hersenschade = [Nog niet te bepalen] And ICE Neurologische afwijkingen - Kop In [WEL Neurologische afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "ConclusionId": 351,
      "DecisionTableName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "DecisionTableId": 356,
      "BusinessDataId": 768,
      "RowId": 1347,
      "RowExpression": "ICE Ernstige (post-anoxische) hersenschade In [GEEN Ernstig (post-anoxische) hersenschade, Nog niet te bepalen] And ICE Vergevorderde niet behandelbare neuromusculaire aandoeningen = [Nog niet te bepalen] And ICE Gevorderde Neurodegeneratieve ziekten In [GEEN Gevorderde Neurodegeneratieve ziekten, Nog niet te bepalen] And ICE Neurologische afwijkingen - Kop In [WEL Neurologische afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "ConclusionId": 351,
      "DecisionTableName": "ICE Ernstig Irreversibel Neurologisch Lijden",
      "DecisionTableId": 356,
      "BusinessDataId": 768,
      "RowId": 1348,
      "RowExpression": "ICE Ernstige (post-anoxische) hersenschade In [GEEN Ernstig (post-anoxische) hersenschade, Nog niet te bepalen] And ICE Vergevorderde niet behandelbare neuromusculaire aandoeningen In [GEEN Vergevorderde niet behandelbare neuromusculaire aandoeningen, Nog niet te bepalen] And ICE Gevorderde Neurodegeneratieve ziekten = [Nog niet te bepalen] And ICE Neurologische afwijkingen - Kop In [WEL Neurologische afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstige Brandwonden",
      "ConclusionId": 348,
      "DecisionTableName": "ICE Ernstige Brandwonden",
      "DecisionTableId": 353,
      "BusinessDataId": 783,
      "RowId": 1325,
      "RowExpression": "ICE Voorspelde mortaliteit agv Brandwonden Is Empty And ICE Brandwonden - Kop In [WEL Brandwonden, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Lage waarschijnlijkheid om te overleven",
      "ConclusionId": 329,
      "DecisionTableName": "ICE Lage waarschijnlijkheid om te overleven",
      "DecisionTableId": 334,
      "BusinessDataId": 750,
      "RowId": 1275,
      "RowExpression": "ICE Ernstig Irreversibel Neurologisch Lijden In [GEEN Ernstig Irreversibel Neurologisch Lijden, Nog niet te bepalen] And ICE Ernstige Brandwonden In [GEEN Ernstige Brandwonden, Nog niet te bepalen] And ICE Ernstig Trauma In [GEEN Ernstig Trauma, Nog niet te bepalen] And ICE Hartstilstand lage overleving = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Lage waarschijnlijkheid om te overleven",
      "ConclusionId": 329,
      "DecisionTableName": "ICE Lage waarschijnlijkheid om te overleven",
      "DecisionTableId": 334,
      "BusinessDataId": 750,
      "RowId": 1276,
      "RowExpression": "ICE Ernstig Irreversibel Neurologisch Lijden In [GEEN Ernstig Irreversibel Neurologisch Lijden, Nog niet te bepalen] And ICE Ernstige Brandwonden In [GEEN Ernstige Brandwonden, Nog niet te bepalen] And ICE Ernstig Trauma = [Nog niet te bepalen] And ICE Hartstilstand lage overleving In [GEEN Hartstilstand lage overleving, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Lage waarschijnlijkheid om te overleven",
      "ConclusionId": 329,
      "DecisionTableName": "ICE Lage waarschijnlijkheid om te overleven",
      "DecisionTableId": 334,
      "BusinessDataId": 750,
      "RowId": 1277,
      "RowExpression": "ICE Ernstig Irreversibel Neurologisch Lijden In [GEEN Ernstig Irreversibel Neurologisch Lijden, Nog niet te bepalen] And ICE Ernstige Brandwonden = [Nog niet te bepalen] And ICE Ernstig Trauma In [GEEN Ernstig Trauma, Nog niet te bepalen] And ICE Hartstilstand lage overleving In [GEEN Hartstilstand lage overleving, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Lage waarschijnlijkheid om te overleven",
      "ConclusionId": 329,
      "DecisionTableName": "ICE Lage waarschijnlijkheid om te overleven",
      "DecisionTableId": 334,
      "BusinessDataId": 750,
      "RowId": 1278,
      "RowExpression": "ICE Ernstig Irreversibel Neurologisch Lijden = [Nog niet te bepalen] And ICE Ernstige Brandwonden In [GEEN Ernstige Brandwonden, Nog niet te bepalen] And ICE Ernstig Trauma In [GEEN Ernstig Trauma, Nog niet te bepalen] And ICE Hartstilstand lage overleving In [GEEN Hartstilstand lage overleving, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Maligniteiten met slechte prognose",
      "ConclusionId": 344,
      "DecisionTableName": "ICE Maligniteiten met slechte prognose",
      "DecisionTableId": 349,
      "BusinessDataId": 785,
      "RowId": 1816,
      "RowExpression": "ICE Maligniteit - kop In [WEL maligniteit, Nog niet te bepalen] And ICE Prognose bij maligniteit = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Hartfalen levensverwachting <1 jaar",
      "ConclusionId": 349,
      "DecisionTableName": "ICE Hartfalen levensverwachting <1 jaar",
      "DecisionTableId": 354,
      "BusinessDataId": 799,
      "RowId": 1328,
      "RowExpression": "ICE NYHA classificatie = [Nog niet te bepalen] And ICE Cardio vasculaire afwijkingen - kop In [WEL cardio vasculaire afwijkingen, Nog niet te bepalen] And ICE Chronisch hartfalen In [WEL Chronisch hartfalen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstige COPD",
      "ConclusionId": 352,
      "DecisionTableName": "ICE Ernstige COPD",
      "DecisionTableId": 357,
      "BusinessDataId": 808,
      "RowId": 1780,
      "RowExpression": "ICE COPD GOLD classificatie = [Nog niet te bepalen] And ICE COPD In [WEL COPD, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 2,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstige taaislijmziekte",
      "ConclusionId": 354,
      "DecisionTableName": "ICE Ernstige taaislijmziekte",
      "DecisionTableId": 359,
      "BusinessDataId": 810,
      "RowId": 1360,
      "RowExpression": "ICE Taaislijmziekte = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 2,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstige pulmonaire fibrose",
      "ConclusionId": 356,
      "DecisionTableName": "ICE Ernstige pulmonaire fibrose",
      "DecisionTableId": 361,
      "BusinessDataId": 811,
      "RowId": 1786,
      "RowExpression": "ICE Pulmonaire fibrose = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 1,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Ernstig lage PaO2",
      "ConclusionId": 353,
      "DecisionTableName": "ICE Ernstig lage PaO2",
      "DecisionTableId": 358,
      "BusinessDataId": 815,
      "RowId": 1874,
      "RowExpression": "ICE PaO2 Chronische longaandoening Is Empty And ICE COPD GOLD classificatie <> [GOLD 4]",
      "Value": "GEEN Ernstig lage PaO2",
      "RowOrder": 2,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "ConclusionId": 350,
      "DecisionTableName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "DecisionTableId": 355,
      "BusinessDataId": 801,
      "RowId": 1336,
      "RowExpression": "ICE Ernstige COPD = [Nog niet te bepalen] And ICE Ernstige taaislijmziekte In [GEEN Ernstige taaislijmziekte, Nog niet te bepalen] And ICE Ernstige pulmonaire fibrose In [GEEN Ernstige pulmonaire fibrose, Nog niet te bepalen] And ICE Thuis zuurstofbehoeftig In [GEEN Thuis zuurstofbehoeftig, Nog niet te bepalen] And ICE Ernstig lage PaO2 In [GEEN Ernstig lage PaO2, Nog niet te bepalen] And ICE Pulmonale afwijkingen - Kop In [WEL Pulmonale afwijkingen, Nog niet te bepalen] And ICE Overige ernstige chronische longziektes <1 levensverwachting In [GEEN Overige ernstige chronische longziektes, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "ConclusionId": 350,
      "DecisionTableName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "DecisionTableId": 355,
      "BusinessDataId": 801,
      "RowId": 1337,
      "RowExpression": "ICE Ernstige COPD In [GEEN Ernstige COPD, Nog niet te bepalen] And ICE Ernstige taaislijmziekte = [Nog niet te bepalen] And ICE Ernstige pulmonaire fibrose In [GEEN Ernstige pulmonaire fibrose, Nog niet te bepalen] And ICE Thuis zuurstofbehoeftig In [GEEN Thuis zuurstofbehoeftig, Nog niet te bepalen] And ICE Ernstig lage PaO2 In [GEEN Ernstig lage PaO2, Nog niet te bepalen] And ICE Pulmonale afwijkingen - Kop In [WEL Pulmonale afwijkingen, Nog niet te bepalen] And ICE Overige ernstige chronische longziektes <1 levensverwachting In [GEEN Overige ernstige chronische longziektes, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 11,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "ConclusionId": 350,
      "DecisionTableName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "DecisionTableId": 355,
      "BusinessDataId": 801,
      "RowId": 1338,
      "RowExpression": "ICE Ernstige COPD In [GEEN Ernstige COPD, Nog niet te bepalen] And ICE Ernstige taaislijmziekte In [GEEN Ernstige taaislijmziekte, Nog niet te bepalen] And ICE Ernstige pulmonaire fibrose = [Nog niet te bepalen] And ICE Thuis zuurstofbehoeftig In [GEEN Thuis zuurstofbehoeftig, Nog niet te bepalen] And ICE Ernstig lage PaO2 In [GEEN Ernstig lage PaO2, Nog niet te bepalen] And ICE Pulmonale afwijkingen - Kop In [WEL Pulmonale afwijkingen, Nog niet te bepalen] And ICE Overige ernstige chronische longziektes <1 levensverwachting In [GEEN Overige ernstige chronische longziektes, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 12,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "ConclusionId": 350,
      "DecisionTableName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "DecisionTableId": 355,
      "BusinessDataId": 801,
      "RowId": 1339,
      "RowExpression": "ICE Ernstige COPD In [GEEN Ernstige COPD, Nog niet te bepalen] And ICE Ernstige taaislijmziekte In [GEEN Ernstige taaislijmziekte, Nog niet te bepalen] And ICE Ernstige pulmonaire fibrose In [GEEN Ernstige pulmonaire fibrose, Nog niet te bepalen] And ICE Thuis zuurstofbehoeftig = [Nog niet te bepalen] And ICE Ernstig lage PaO2 In [GEEN Ernstig lage PaO2, Nog niet te bepalen] And ICE Pulmonale afwijkingen - Kop In [WEL Pulmonale afwijkingen, Nog niet te bepalen] And ICE Overige ernstige chronische longziektes <1 levensverwachting In [GEEN Overige ernstige chronische longziektes, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 13,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "ConclusionId": 350,
      "DecisionTableName": "ICE Chronische longziekte levensverwachting <1 jaar",
      "DecisionTableId": 355,
      "BusinessDataId": 801,
      "RowId": 1794,
      "RowExpression": "ICE Ernstige COPD In [GEEN Ernstige COPD, Nog niet te bepalen] And ICE Ernstige taaislijmziekte In [GEEN Ernstige taaislijmziekte, Nog niet te bepalen] And ICE Ernstige pulmonaire fibrose In [GEEN Ernstige pulmonaire fibrose, Nog niet te bepalen] And ICE Thuis zuurstofbehoeftig In [GEEN Thuis zuurstofbehoeftig, Nog niet te bepalen] And ICE Ernstig lage PaO2 In [GEEN Ernstig lage PaO2, Nog niet te bepalen] And ICE Overige ernstige chronische longziektes <1 levensverwachting = [Nog niet te bepalen] And ICE Pulmonale afwijkingen - Kop In [WEL Pulmonale afwijkingen, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 14,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Chronische dialyse-patient levensverwachting <1 jaar",
      "ConclusionId": 358,
      "DecisionTableName": "ICE Chronische dialyse-patient levensverwachting <1 jaar",
      "DecisionTableId": 363,
      "BusinessDataId": 802,
      "RowId": 1821,
      "RowExpression": "ICE Nefrologische afwijkingen - kop In [WEL Nefrologische afwijkingen, Nog niet te bepalen] And ICE Chronische dialyse-patient = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Gevorderd leverfalen levensverwachting <1 jaar",
      "ConclusionId": 359,
      "DecisionTableName": "ICE Gevorderd leverfalen levensverwachting <1 jaar",
      "DecisionTableId": 364,
      "BusinessDataId": 803,
      "RowId": 1825,
      "RowExpression": "ICE Abdominale/hepatische afwijkingen - kop In [WEL Abdominale/hepatische afwijkingen, Nog niet te bepalen] And ICE MELD score Is Empty And ICE Chronisch leverfalen = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Eindstadium orgaanfalen",
      "ConclusionId": 346,
      "DecisionTableName": "ICE Eindstadium orgaanfalen",
      "DecisionTableId": 351,
      "BusinessDataId": 786,
      "RowId": 1319,
      "RowExpression": "ICE Hartfalen levensverwachting <1 jaar In [GEEN Hartfalen levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische longziekte levensverwachting <1 jaar = [Nog niet te bepalen] And ICE Chronische dialyse-patient levensverwachting <1 jaar In [GEEN Chronische dialyse-patient levensverwachting <1 jaar, Nog niet te bepalen] And ICE Gevorderd leverfalen levensverwachting <1 jaar In [GEEN Gevorderd leverfalen levensverwachting <1 jaar, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Eindstadium orgaanfalen",
      "ConclusionId": 346,
      "DecisionTableName": "ICE Eindstadium orgaanfalen",
      "DecisionTableId": 351,
      "BusinessDataId": 786,
      "RowId": 1320,
      "RowExpression": "ICE Hartfalen levensverwachting <1 jaar = [Nog niet te bepalen] And ICE Chronische longziekte levensverwachting <1 jaar In [GEEN Chronische longziekte levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische dialyse-patient levensverwachting <1 jaar In [GEEN Chronische dialyse-patient levensverwachting <1 jaar, Nog niet te bepalen] And ICE Gevorderd leverfalen levensverwachting <1 jaar In [GEEN Gevorderd leverfalen levensverwachting <1 jaar, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Eindstadium orgaanfalen",
      "ConclusionId": 346,
      "DecisionTableName": "ICE Eindstadium orgaanfalen",
      "DecisionTableId": 351,
      "BusinessDataId": 786,
      "RowId": 1321,
      "RowExpression": "ICE Hartfalen levensverwachting <1 jaar In [GEEN Hartfalen levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische longziekte levensverwachting <1 jaar In [GEEN Chronische longziekte levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische dialyse-patient levensverwachting <1 jaar = [Nog niet te bepalen] And ICE Gevorderd leverfalen levensverwachting <1 jaar In [GEEN Gevorderd leverfalen levensverwachting <1 jaar, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Eindstadium orgaanfalen",
      "ConclusionId": 346,
      "DecisionTableName": "ICE Eindstadium orgaanfalen",
      "DecisionTableId": 351,
      "BusinessDataId": 786,
      "RowId": 1322,
      "RowExpression": "ICE Hartfalen levensverwachting <1 jaar In [GEEN Hartfalen levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische longziekte levensverwachting <1 jaar In [GEEN Chronische longziekte levensverwachting <1 jaar, Nog niet te bepalen] And ICE Chronische dialyse-patient levensverwachting <1 jaar In [GEEN Chronische dialyse-patient levensverwachting <1 jaar, Nog niet te bepalen] And ICE Gevorderd leverfalen levensverwachting <1 jaar = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Biologische Leeftijd",
      "ConclusionId": 355,
      "DecisionTableName": "ICE Biologische Leeftijd",
      "DecisionTableId": 360,
      "BusinessDataId": 820,
      "RowId": 1375,
      "RowExpression": "ICE Leeftijd Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Gevorderde leeftijd",
      "ConclusionId": 357,
      "DecisionTableName": "ICE Gevorderde leeftijd",
      "DecisionTableId": 362,
      "BusinessDataId": 787,
      "RowId": 1374,
      "RowExpression": "ICE Biologische Leeftijd = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Korte levensverwachting",
      "ConclusionId": 342,
      "DecisionTableName": "ICE Korte levensverwachting",
      "DecisionTableId": 347,
      "BusinessDataId": 751,
      "RowId": 1287,
      "RowExpression": "ICE Electieve palliatieve chirurgie In [GEEN Electieve palliatieve chirurgie, Nog niet te bepalen] And ICE Gevorderd en onomkeerbaar immuungecompromitteerd In [GEEN Gevorderd en onomkeerbaar immuungecompromitteerd, Nog niet te bepalen] And ICE Gevorderde leeftijd In [GEEN Gevorderde leeftijd, Nog niet te bepalen] And ICE Eindstadium orgaanfalen In [GEEN Eindstadium orgaanfalen, Nog niet te bepalen] And ICE Maligniteiten met slechte prognose = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Korte levensverwachting",
      "ConclusionId": 342,
      "DecisionTableName": "ICE Korte levensverwachting",
      "DecisionTableId": 347,
      "BusinessDataId": 751,
      "RowId": 1288,
      "RowExpression": "ICE Electieve palliatieve chirurgie In [GEEN Electieve palliatieve chirurgie, Nog niet te bepalen] And ICE Gevorderd en onomkeerbaar immuungecompromitteerd In [GEEN Gevorderd en onomkeerbaar immuungecompromitteerd, Nog niet te bepalen] And ICE Gevorderde leeftijd In [GEEN Gevorderde leeftijd, Nog niet te bepalen] And ICE Eindstadium orgaanfalen = [Nog niet te bepalen] And ICE Maligniteiten met slechte prognose In [GEEN Slechte prognose bij maligniteit, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Korte levensverwachting",
      "ConclusionId": 342,
      "DecisionTableName": "ICE Korte levensverwachting",
      "DecisionTableId": 347,
      "BusinessDataId": 751,
      "RowId": 1289,
      "RowExpression": "ICE Electieve palliatieve chirurgie In [GEEN Electieve palliatieve chirurgie, Nog niet te bepalen] And ICE Gevorderd en onomkeerbaar immuungecompromitteerd In [GEEN Gevorderd en onomkeerbaar immuungecompromitteerd, Nog niet te bepalen] And ICE Gevorderde leeftijd = [Nog niet te bepalen] And ICE Eindstadium orgaanfalen In [GEEN Eindstadium orgaanfalen, Nog niet te bepalen] And ICE Maligniteiten met slechte prognose In [GEEN Slechte prognose bij maligniteit, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Korte levensverwachting",
      "ConclusionId": 342,
      "DecisionTableName": "ICE Korte levensverwachting",
      "DecisionTableId": 347,
      "BusinessDataId": 751,
      "RowId": 1290,
      "RowExpression": "ICE Electieve palliatieve chirurgie In [GEEN Electieve palliatieve chirurgie, Nog niet te bepalen] And ICE Gevorderd en onomkeerbaar immuungecompromitteerd = [Nog niet te bepalen] And ICE Gevorderde leeftijd In [GEEN Gevorderde leeftijd, Nog niet te bepalen] And ICE Eindstadium orgaanfalen In [GEEN Eindstadium orgaanfalen, Nog niet te bepalen] And ICE Maligniteiten met slechte prognose In [GEEN Slechte prognose bij maligniteit, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 10,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Korte levensverwachting",
      "ConclusionId": 342,
      "DecisionTableName": "ICE Korte levensverwachting",
      "DecisionTableId": 347,
      "BusinessDataId": 751,
      "RowId": 1291,
      "RowExpression": "ICE Electieve palliatieve chirurgie = [Nog niet te bepalen] And ICE Gevorderd en onomkeerbaar immuungecompromitteerd In [GEEN Gevorderd en onomkeerbaar immuungecompromitteerd, Nog niet te bepalen] And ICE Gevorderde leeftijd In [GEEN Gevorderde leeftijd, Nog niet te bepalen] And ICE Eindstadium orgaanfalen In [GEEN Eindstadium orgaanfalen, Nog niet te bepalen] And ICE Maligniteiten met slechte prognose In [GEEN Slechte prognose bij maligniteit, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 11,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Voldoet aan Exclusievoorwaarden",
      "ConclusionId": 328,
      "DecisionTableName": "ICE Voldoet aan Exclusievoorwaarden",
      "DecisionTableId": 333,
      "BusinessDataId": 748,
      "RowId": 1226,
      "RowExpression": "ICE Korte levensverwachting In [GEEN Korte levensverwachting, Nog niet te bepalen] And ICE Lage waarschijnlijkheid om te overleven = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICE Voldoet aan Exclusievoorwaarden",
      "ConclusionId": 328,
      "DecisionTableName": "ICE Voldoet aan Exclusievoorwaarden",
      "DecisionTableId": 333,
      "BusinessDataId": 748,
      "RowId": 1227,
      "RowExpression": "ICE Lage waarschijnlijkheid om te overleven In [GEEN Lage waarschijnlijkheid om te overleven, Nog niet te bepalen] And ICE Korte levensverwachting = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 5,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Refractaire Hypoxemie",
      "ConclusionId": 335,
      "DecisionTableName": "ICI Refractaire Hypoxemie",
      "DecisionTableId": 340,
      "BusinessDataId": 758,
      "RowId": 1235,
      "RowExpression": "ICI Saturatie Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Refractaire Hypoxemie",
      "ConclusionId": 335,
      "DecisionTableName": "ICI Refractaire Hypoxemie",
      "DecisionTableId": 340,
      "BusinessDataId": 758,
      "RowId": 1567,
      "RowExpression": "ICI Zuurstoftoediening = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 5,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Bloedgas pH OK",
      "ConclusionId": 418,
      "DecisionTableName": "ICI Bloedgas pH OK",
      "DecisionTableId": 423,
      "BusinessDataId": 1012,
      "RowId": 1871,
      "RowExpression": "ICI Bloedgas pH Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Bloedgas pCO2 OK",
      "ConclusionId": 419,
      "DecisionTableName": "ICI Bloedgas pCO2 OK",
      "DecisionTableId": 424,
      "BusinessDataId": 1013,
      "RowId": 1862,
      "RowExpression": "ICI Bloedgas pCO2 Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Bloedgas HC03 OK",
      "ConclusionId": 420,
      "DecisionTableName": "ICI Bloedgas HC03 OK",
      "DecisionTableId": 425,
      "BusinessDataId": 1014,
      "RowId": 1864,
      "RowExpression": "ICI Bloedgas HCO3 Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Acidose/Alkalose",
      "ConclusionId": 421,
      "DecisionTableName": "ICI Acidose/Alkalose",
      "DecisionTableId": 426,
      "BusinessDataId": 1017,
      "RowId": 1878,
      "RowExpression": "ICI Bloedgas pCO2 OK = [Nog niet te bepalen] And ICI Bloedgas pH OK <> [Bloedgas pH NORMAAL]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Acidose/Alkalose",
      "ConclusionId": 421,
      "DecisionTableName": "ICI Acidose/Alkalose",
      "DecisionTableId": 426,
      "BusinessDataId": 1017,
      "RowId": 1881,
      "RowExpression": "ICI Bloedgas pH OK = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Acidose/Alkalose",
      "ConclusionId": 421,
      "DecisionTableName": "ICI Acidose/Alkalose",
      "DecisionTableId": 426,
      "BusinessDataId": 1017,
      "RowId": 1882,
      "RowExpression": "ICI Bloedgas HC03 OK = [Nog niet te bepalen] And ICI Bloedgas pH OK <> [Bloedgas pH NORMAAL]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 10,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Respiratoire Acidose",
      "ConclusionId": 336,
      "DecisionTableName": "ICI Respiratoire Acidose",
      "DecisionTableId": 341,
      "BusinessDataId": 759,
      "RowId": 1866,
      "RowExpression": "ICI Acidose/Alkalose = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Klinisch bewezen ademhalingsinsufficientie",
      "ConclusionId": 376,
      "DecisionTableName": "ICI Klinisch bewezen ademhalingsinsufficientie",
      "DecisionTableId": 381,
      "BusinessDataId": 760,
      "RowId": 1834,
      "RowExpression": "ICI Ademhalingsfrequentie Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 1,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Onvermogen luchtwegen vrijhouden",
      "ConclusionId": 337,
      "DecisionTableName": "ICI Onvermogen luchtwegen vrijhouden",
      "DecisionTableId": 342,
      "BusinessDataId": 761,
      "RowId": 1826,
      "RowExpression": "ICI Onvermogen vrijhouden luchtwegen = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 3,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Invasieve ventilatoire ondersteuning",
      "ConclusionId": 331,
      "DecisionTableName": "ICI Invasieve ventilatoire ondersteuning",
      "DecisionTableId": 336,
      "BusinessDataId": 756,
      "RowId": 1219,
      "RowExpression": "ICI Onvermogen luchtwegen vrijhouden In [GEEN Onvermogen luchtwegen vrijhouden, Nog niet te bepalen] And ICI Klinisch bewezen ademhalingsinsufficientie In [GEEN Klinisch bewezen ademshalingsinsufficientie, Nog niet te bepalen, MOGELIJK Klinisch bewezen ademshalingsinsufficientie] And ICI Respiratoire Acidose In [GEEN Respiratoire Acidose, Nog niet te bepalen] And ICI Refractaire Hypoxemie = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Invasieve ventilatoire ondersteuning",
      "ConclusionId": 331,
      "DecisionTableName": "ICI Invasieve ventilatoire ondersteuning",
      "DecisionTableId": 336,
      "BusinessDataId": 756,
      "RowId": 1220,
      "RowExpression": "ICI Onvermogen luchtwegen vrijhouden In [GEEN Onvermogen luchtwegen vrijhouden, Nog niet te bepalen] And ICI Klinisch bewezen ademhalingsinsufficientie In [GEEN Klinisch bewezen ademshalingsinsufficientie, Nog niet te bepalen, MOGELIJK Klinisch bewezen ademshalingsinsufficientie] And ICI Respiratoire Acidose = [Nog niet te bepalen] And ICI Refractaire Hypoxemie In [GEEN Refractaire Hypoxemie, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 7,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Invasieve ventilatoire ondersteuning",
      "ConclusionId": 331,
      "DecisionTableName": "ICI Invasieve ventilatoire ondersteuning",
      "DecisionTableId": 336,
      "BusinessDataId": 756,
      "RowId": 1221,
      "RowExpression": "ICI Onvermogen luchtwegen vrijhouden In [GEEN Onvermogen luchtwegen vrijhouden, Nog niet te bepalen] And ICI Klinisch bewezen ademhalingsinsufficientie = [Nog niet te bepalen] And ICI Respiratoire Acidose In [GEEN Respiratoire Acidose, Nog niet te bepalen] And ICI Refractaire Hypoxemie In [GEEN Refractaire Hypoxemie, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 8,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Invasieve ventilatoire ondersteuning",
      "ConclusionId": 331,
      "DecisionTableName": "ICI Invasieve ventilatoire ondersteuning",
      "DecisionTableId": 336,
      "BusinessDataId": 756,
      "RowId": 1222,
      "RowExpression": "ICI Onvermogen luchtwegen vrijhouden = [Nog niet te bepalen] And ICI Klinisch bewezen ademhalingsinsufficientie In [GEEN Klinisch bewezen ademshalingsinsufficientie, Nog niet te bepalen, MOGELIJK Klinisch bewezen ademshalingsinsufficientie] And ICI Respiratoire Acidose In [GEEN Respiratoire Acidose, Nog niet te bepalen] And ICI Refractaire Hypoxemie In [GEEN Refractaire Hypoxemie, Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 9,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Lage Systolische bloeddruk",
      "ConclusionId": 332,
      "DecisionTableName": "ICI Lage Systolische bloeddruk",
      "DecisionTableId": 337,
      "BusinessDataId": 763,
      "RowId": 1225,
      "RowExpression": "ICI Systolische bloeddruk Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 3,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Verlaagd bewustzijnsniveau",
      "ConclusionId": 385,
      "DecisionTableName": "ICI Verlaagd bewustzijnsniveau",
      "DecisionTableId": 390,
      "BusinessDataId": 770,
      "RowId": 1622,
      "RowExpression": "ICI bewustzijnsniveau = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 3,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Verminderde diurese",
      "ConclusionId": 410,
      "DecisionTableName": "ICI Verminderde diurese",
      "DecisionTableId": 415,
      "BusinessDataId": 775,
      "RowId": 1681,
      "RowExpression": "ICI Diurese Is Empty",
      "Value": "Nog niet te bepalen",
      "RowOrder": 1,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Mean Arterial Pressure (MAP)",
      "ConclusionId": 415,
      "DecisionTableName": "ICI Mean Arterial Pressure (MAP)",
      "DecisionTableId": 420,
      "BusinessDataId": 976,
      "RowId": 1766,
      "RowExpression": "ICI Systolische bloeddruk Is Empty",
      "Value": "9999",
      "RowOrder": 1,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Mean Arterial Pressure (MAP)",
      "ConclusionId": 415,
      "DecisionTableName": "ICI Mean Arterial Pressure (MAP)",
      "DecisionTableId": 420,
      "BusinessDataId": 976,
      "RowId": 1767,
      "RowExpression": "ICI Diastole bloeddruk Is Empty",
      "Value": "9999",
      "RowOrder": 2,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Relatieve hypotensie + aanwijzingen shock",
      "ConclusionId": 338,
      "DecisionTableName": "ICI Relatieve hypotensie + aanwijzingen shock",
      "DecisionTableId": 343,
      "BusinessDataId": 764,
      "RowId": 1769,
      "RowExpression": "ICI Mean Arterial Pressure (MAP) = 9999",
      "Value": "Nog niet te bepalen",
      "RowOrder": 5,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Hypotensie",
      "ConclusionId": 333,
      "DecisionTableName": "ICI Hypotensie",
      "DecisionTableId": 338,
      "BusinessDataId": 757,
      "RowId": 1231,
      "RowExpression": "ICI Relatieve hypotensie + aanwijzingen shock In [GEEN Relatieve hypotensie + aanwijzingen shock, Nog niet te bepalen] And ICI Lage Systolische bloeddruk = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Hypotensie",
      "ConclusionId": 333,
      "DecisionTableName": "ICI Hypotensie",
      "DecisionTableId": 338,
      "BusinessDataId": 757,
      "RowId": 1232,
      "RowExpression": "ICI Lage Systolische bloeddruk In [GEEN Lage Systolische bloeddruk, Nog niet te bepalen] And ICI Relatieve hypotensie + aanwijzingen shock = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 5,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Voldoet aan inclusievoorwaarden",
      "ConclusionId": 330,
      "DecisionTableName": "ICI Voldoet aan inclusievoorwaarden",
      "DecisionTableId": 335,
      "BusinessDataId": 755,
      "RowId": 1211,
      "RowExpression": "ICI Invasieve ventilatoire ondersteuning In [GEEN Invasieve ventilatoire ondersteuning nodig, Nog niet te bepalen] And ICI Hypotensie = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 5,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "ICI Voldoet aan inclusievoorwaarden",
      "ConclusionId": 330,
      "DecisionTableName": "ICI Voldoet aan inclusievoorwaarden",
      "DecisionTableId": 335,
      "BusinessDataId": 755,
      "RowId": 1213,
      "RowExpression": "ICI Hypotensie In [GEEN Hypotensie, Nog niet te bepalen] And ICI Invasieve ventilatoire ondersteuning = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 4,
      "RowAnnotations": []
    },
    {
      "ConclusionName": "IC Triage opnemen op IC",
      "ConclusionId": 361,
      "DecisionTableName": "IC Triage opnemen op IC",
      "DecisionTableId": 366,
      "BusinessDataId": 834,
      "RowId": 1407,
      "RowExpression": "ICE Voldoet aan Exclusievoorwaarden = [Nog niet te bepalen] And ICI Voldoet aan inclusievoorwaarden = [Nog niet te bepalen]",
      "Value": "Nog niet te bepalen",
      "RowOrder": 6,
      "RowAnnotations": []
    }
  ],
  "Errors": [],
  "BusinessDataValues": {
    "748": "Nog niet te bepalen",
    "749": "Nog niet te bepalen",
    "750": "Nog niet te bepalen",
    "751": "Nog niet te bepalen",
    "755": "Nog niet te bepalen",
    "756": "Nog niet te bepalen",
    "757": "Nog niet te bepalen",
    "758": "Nog niet te bepalen",
    "759": "Nog niet te bepalen",
    "760": "Nog niet te bepalen",
    "761": "Nog niet te bepalen",
    "762": "",
    "763": "Nog niet te bepalen",
    "764": "Nog niet te bepalen",
    "765": "Nog niet te bepalen",
    "768": "Nog niet te bepalen",
    "770": "Nog niet te bepalen",
    "771": "Nog niet te bepalen",
    "774": "Nog niet te bepalen",
    "775": "Nog niet te bepalen",
    "776": "",
    "783": "Nog niet te bepalen",
    "785": "Nog niet te bepalen",
    "786": "Nog niet te bepalen",
    "787": "Nog niet te bepalen",
    "788": "Nog niet te bepalen",
    "789": "Nog niet te bepalen",
    "793": "Nog niet te bepalen",
    "794": "Nog niet te bepalen",
    "795": "Nog niet te bepalen",
    "796": "Nog niet te bepalen",
    "797": "Nog niet te bepalen",
    "799": "Nog niet te bepalen",
    "800": "",
    "801": "Nog niet te bepalen",
    "802": "Nog niet te bepalen",
    "803": "Nog niet te bepalen",
    "804": "",
    "805": "Nog niet te bepalen",
    "806": "Nog niet te bepalen",
    "807": "Nog niet te bepalen",
    "808": "Nog niet te bepalen",
    "810": "Nog niet te bepalen",
    "811": "Nog niet te bepalen",
    "812": "Nog niet te bepalen",
    "813": "Nog niet te bepalen",
    "814": "Nog niet te bepalen",
    "815": "GEEN Ernstig lage PaO2",
    "816": "Nog niet te bepalen",
    "817": "",
    "819": "",
    "820": "Nog niet te bepalen",
    "821": "Nog niet te bepalen",
    "822": "",
    "824": "Nog niet te bepalen",
    "825": "Nog niet te bepalen",
    "826": "",
    "827": "",
    "828": "Nog niet te bepalen",
    "829": "Nog niet te bepalen",
    "830": "",
    "831": "",
    "832": "",
    "833": "",
    "834": "Nog niet te bepalen",
    "917": "Nog niet te bepalen",
    "919": "",
    "920": "",
    "921": "Nog niet te bepalen",
    "922": "Nog niet te bepalen",
    "945": "Nog niet te bepalen",
    "951": "Nog niet te bepalen",
    "976": "9999",
    "977": "",
    "980": "Nog niet te bepalen",
    "981": "",
    "982": "Nog niet te bepalen",
    "984": "Nog niet te bepalen",
    "991": "",
    "993": "Nog niet te bepalen",
    "995": "Nog niet te bepalen",
    "996": "Nog niet te bepalen",
    "997": "Nog niet te bepalen",
    "998": "Nog niet te bepalen",
    "999": "Nog niet te bepalen",
    "1000": "Nog niet te bepalen",
    "1001": "Nog niet te bepalen",
    "1003": "Nog niet te bepalen",
    "1004": "Nog niet te bepalen",
    "1005": "",
    "1007": "Nog niet te bepalen",
    "1008": "Nog niet te bepalen",
    "1009": "Nog niet te bepalen",
    "1010": "Nog niet te bepalen",
    "1011": "",
    "1012": "Nog niet te bepalen",
    "1013": "Nog niet te bepalen",
    "1014": "Nog niet te bepalen",
    "1015": "IC Triage advies niet aangepast",
    "1016": "Nog niet te bepalen",
    "1017": "Nog niet te bepalen",
    "1018": "",
    "1020": ""
  }
}
```

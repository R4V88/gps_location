# gps_location

1. użyto dockera do konteneryzacji db PostreSQL, plik konfiguracyjny w repozytorium
2. w rozwiązaniu zastosowano strukturę z podziałem na warstwy:
  db - warstwa bazy danych
  application - warstwa logiki biznesowej z interfejsami w pakiecie port
  entity - warstwa modeli biznesowych
  web - warstwa prezentacji zawierająca usługi restowe 
3. testy w analogicznej strukturze w pakiecie src/test. Testowano tylko metody z dostępem publicznym
4. Usługa typu POST /api/locations przyjmuje 3 parametry:
  {
    "deviceId": long,
    "latitude": String,
    "longitude": String
  }
  jeśli utworzenie nowej lokalizacji się powiedzie to zwracany jest Header Location np: http://localhost:8080/api/locations/{id}
  jeśli utworzenie nowej lokalizacji zakończy się niepowodzeniem to usługa zwraca kod 400 wraz z przyczyną.

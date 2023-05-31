# ShiftMate

## 1. Strona tytułowa
Tytuł: Dokumentacja ShiftMate - tworzenie grafików w organizacjach 
Przeznaczenie: Dokumentacja jest przeznaczona zarówno dla użytkowników i programistów. 
## 2. Spis treści
1. [Strona tytułowa](#1-strona-tytułowa)
2. [Spis treści](#2-spis-treści)
3. [Historia zmian](#3-historia-zmian)
4. [Wprowadzenie (czym jest projekt)](#4-wprowadzenie)
5. [Dokumentacja właściwa](#5-dokumentacja-właściwa)
   - 5.1 [Uruchomienie](#51-uruchomienie)
   - 5.2 [Biblioteki](#52-biblioteki)
   - 5.3 [Opis modułów, klas, zmiennych](#53-opis-modułów-klas-zmiennych)
## 3. Historia zmian
| Numer wersji | Data       | Autor              | Opis zmian                                 |
|--------------|------------|--------------------|--------------------------------------------|
| 1.0          | 2023-05-29 | Michał Stefański <br> Dawid Klimowicz <br> Mateusz Kwiatkowski | Pierwsza wersja aplikacji                  |
|              |            |                    | - Stworzono interfejs aplikacji dla pracownika i menagera    |
|              |            |                    | - Połączenie z bazą danych Firebase      |
|              |            |                    | - Implementacja logowania i rejestrowania firm |
|              |            |                    | - Implementacja możliwości tworzenia kont pracowników              |
|              |            |                    | - Umożliwiono pracownikom składanie dyspozycji|
|              |            |                    | - Umożliwiono managerom tworzenie grafików dla konkretych pracowników        |
|              |            |                    | - Umożliwiono managerom wyśweitlanie grafiku wszystkich pracowników na jednym kalendarzu|
|              |            |                    | - Umożliwiono pracownikom wyswietlanie zarówno swoich jak i wszystkich zmian w organizacji      |

## 4. Wprowadzenie
Projekt ShiftMate to aplikacja umożliwiająca dowolnym organizacjom tworzenie indywidualnego podejścia do ich potrzeb planowania czasu Z aplikacji mogą korzystać nie tylko firmy, lecz także wszelkie przedsięwzięcia, które potrzebują planowania grafików w swoim zespole. 

Aby rozpocząć korzystanie z aplikacji należy zarejestrować organizacje. Konto organizacji jest kontem zarządzającym, które ma możliwość dodawania pracowników do organizacji co równoznaczne jest z zakładaniem dla nich kont w aplikacji. Co więcej tylko to konto posiada widok umożliwiający podgląd wszystkich członków zespołu oraz tylko ono ma możliwość tworzenia końcowych grafików. Te, tworzy się poprzez wybranie danego pracownika i wpisanie odpowiednich godzin w kafelkach kalendarza. Osoba zarządzająca nie może dodawać zmian w dni oznaczone jako niedyspozycyjne przez pracownika. Istnieje możliwość rozszerzenia widoku kalendarza na danym pracowniku o wszystkich pracowników, tak aby zarządzający mógł zobaczyć gdzie zostały już zaplanowane jakieś zmiany.

Pracownik nie ma możliwości rejestracji, musi otrzymać dane do logowania od zarządzającego zespołem. Po zalogowaniu się pracownik ma dostęp do widoku, który pozwala mu składać swoją dyspozycje. Domyślnie wszystkie dni są dyspozycyjne, poprzez klikanie w konkretne daty kafelki kalendarza zmieniają się na czerwono i zapisują jako dni niedyspozycyjne. Następną funkcją tego widoku jest podgląd końcowego grafiku. Gdy osoba zarządzająca zespołem/organizacją utworzy grafik dla danego pracownika jest to miejsce gdzie może go zobaczyć. Co więcej pracownik ma możliwość rozszerzenia swojego kalendarza o wszystkie zmiany pozostałych członków zespołu. Ma to na celu umożliwienie podglądu z kim dany członek zespołu będzie realizował zadania tego samego dnia.

Aplikacja ma na celu służyć w swojej prostocie. Podczas tworzenia położono głowny nacisk na minimalistyczny i przejrzysty interfejs. Ograniczono do minimum potrzeby aplikacji do tworzenia grafików, co gwarantuje szybkie i intuicyjne wdrożenie. 

## 5. Dokumentacja właściwa
### 5.1 Uruchomienie 

Proces uruchomienia aplikacji `ShiftMate` jest niezwykle prosty. Pamiętaj o kilku krokach, które zapewnią Ci płynne przejście do działania:

   1. Na początku upewnij się, że masz zainstalowane wymagane technologie, takie jak `Java` i `Spring`. To właśnie na nich oparta jest nasza aplikacja. Możesz je łatwo           zainstalować, korzystając z oficjalnych stron i dokumentacji.

   2. Następnie przystąp do podstawowej konfiguracji `Java` z frameworkiem `Spring`. Dobra wiadomość jest taka, że istnieją przyjazne przewodniki i instrukcje, które krok         po kroku pokazują, jak to zrobić. Możesz skorzystać z takich materiałów jak: https://spring.io/guides/gs/guides-with-vscode/

   3. Ważne jest również wybranie odpowiedniego środowiska pracy. W naszym przypadku polecamy korzystanie z `Visual Studio Code`, które jest łatwe w obsłudze i oferuje           przydatne narzędzia dla programistów.

   4. Kiedy już masz gotowe podstawy, czas pobrać projekt z repozytorium `Github` i zaimportować go do swojego wybranego środowiska programistycznego. To może być wybrany         przez Ciebie IDE (np. `Visual Studio Code`) lub inny edytor kodu, który preferujesz.

   5. Aby uruchomić projekt, otwórz `Command Palett`e (w przypadku `Visual Studio Code` skrót klawiszowy to `Ctrl + Shift + P`) i wybierz opcję `Spring Boot Dashboard:Run`.       Ten krok uruchomi aplikację wewnątrz środowiska programistycznego.

   6. Teraz powinnaś/powinieneś zobaczyć, że projekt się uruchamia. Jeśli wszystko przebiegnie pomyślnie, aplikacja zostanie uruchomiona na określonym porcie, a na ekranie       pojawi się ekran logowania.

### 5.2 Biblioteki

1. `Firebase Admin SDK`:
   - Służy do zarządzania aplikacjami Firebase.
   - Zapewnia narzędzia do integracji aplikacji z usługami Firebase, takimi jak autoryzacja, baza danych, powiadomienia push itp.
   
2. `Thymeleaf`:
   - Jest to silnik szablonów, który umożliwia tworzenie dynamicznych stron HTML.
   - Ułatwia osadzanie danych z serwera w szablonach HTML, co pozwala na wyświetlanie dynamicznych treści w aplikacji webowej.
   
3. `Spring Boot Starter Web`:
   - Jest to zestaw zależności potrzebnych do tworzenia aplikacji webowych w technologii Spring Boot.
   - Umożliwia obsługę żądań HTTP, zarządzanie trasami (routingiem), kontrolery, przetwarzanie formularzy itp.
   
4. `Narzędzia deweloperskie Spring Boot`:
   - Jest to zestaw narzędzi, które ułatwiają rozwój aplikacji Spring Boot.
   - Automatycznie przeładowuje aplikację po wprowadzeniu zmian w kodzie, co przyspiesza proces iteracyjny podczas rozwoju aplikacji.
   
5. `Spring Boot Starter Test`:
   - Zawiera narzędzia potrzebne do tworzenia testów jednostkowych i integracyjnych w aplikacji Spring Boot.
   - Umożliwia sprawdzanie poprawności działania poszczególnych komponentów aplikacji i integracji między nimi.
   
6. `Walidacja danych w Spring Boot`:
   - Biblioteka dostarczająca funkcje do walidacji danych wejściowych w aplikacji Spring Boot.
   - Pozwala na sprawdzenie poprawności danych wprowadzanych przez użytkownika i zastosowanie zdefiniowanych reguł walidacji.
   
7. `Passay`:
   - Biblioteka służąca do generowania i sprawdzania haseł zgodnych z określonymi regułami.
   - Może być używana w aplikacjach, które wymagają silnych haseł i chcą zapewnić, że użytkownicy tworzą hasła spełniające określone kryteria.

### 5.3 Opis modułów, klas, zmiennych 


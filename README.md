# ShiftMate

## 1. Strona tytułowa
Tytuł: Dokumentacja ShiftMate - tworzenie grafików w organizacjach 
Przeznaczenie: Dokumentacja jest przeznaczona zarówno dla użytkowników i programistów. 
## 2. Spis treści
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

## 4. Wprowadzenie (czym jest projekt)
Projekt ShiftMate to aplikacja umożliwiająca dowolnym organizacjom tworzenie indywidualnego podejścia do ich potrzeb planowania czasu Z aplikacji mogą korzystać nie tylko firmy, lecz także wszelkie przedsięwzięcia, które potrzebują planowania grafików w swoim zespole. 

Aby rozpocząć korzystanie z aplikacji należy zarejestrować organizacje. Konto organizacji jest kontem zarządzającym, które ma możliwość dodawania pracowników do organizacji co równoznaczne jest z zakładaniem dla nich kont w aplikacji. Co więcej tylko to konto posiada widok umożliwiający podgląd wszystkich członków zespołu oraz tylko ono ma możliwość tworzenia końcowych grafików. Te, tworzy się poprzez wybranie danego pracownika i wpisanie odpowiednich godzin w kafelkach kalendarza. Osoba zarządzająca nie może dodawać zmian w dni oznaczone jako niedyspozycyjne przez pracownika. Istnieje możliwość rozszerzenia widoku kalendarza na danym pracowniku o wszystkich pracowników, tak aby zarządzający mógł zobaczyć gdzie zostały już zaplanowane jakieś zmiany.

Pracownik nie ma możliwości rejestracji, musi otrzymać dane do logowania od zarządzającego zespołem. Po zalogowaniu się pracownik ma dostęp do widoku, który pozwala mu składać swoją dyspozycje. Domyślnie wszystkie dni są dyspozycyjne, poprzez klikanie w konkretne daty kafelki kalendarza zmieniają się na czerwono i zapisują jako dni niedyspozycyjne. Następną funkcją tego widoku jest podgląd końcowego grafiku. Gdy osoba zarządzająca zespołem/organizacją utworzy grafik dla danego pracownika jest to miejsce gdzie może go zobaczyć. Co więcej pracownik ma możliwość rozszerzenia swojego kalendarza o wszystkie zmiany pozostałych członków zespołu. Ma to na celu umożliwienie podglądu z kim dany członek zespołu będzie realizował zadania tego samego dnia.

Aplikacja ma na celu służyć w swojej prostocie. Podczas tworzenia położono głowny nacisk na minimalistyczny i przejrzysty interfejs. Ograniczono do minimum potrzeby aplikacji do tworzenia grafików, co gwarantuje szybkie i intuicyjne wdrożenie. 

## 5. Dokumentacja właściwa (uruchomienie, info o bibliotekach, opis modułów, klas, zmiennych…)
### 5.1 Uruchomienie 
### 5.2 Biblioteki
### 5.3 Opis modułów, klas, zmiennych 


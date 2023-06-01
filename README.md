# ShiftMate

## 1. Strona tytułowa
Dokumentacja ShiftMate - tworzenie grafików w organizacjach 
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

### 5.3 Opis najważniejszych modułów, klas, zmiennych 
#### - Metoda `createCompany` obsługuje proces tworzenia nowej firmy w systemie.

lokalizacja: RegisterController

1. Tworzony jest obiekt CompanyService, który odpowiada za obsługę operacji związanych z firmą.
2. Dodawany jest obiekt company do modelu, aby można go było wyświetlić na widoku.
3. Jeśli wystąpiły błędy walidacji danych firmy, błędy są dodawane do modelu dla pól "companyName", "email", "password" i "confirmPassword". Następnie zwracany jest widok "register" bez kontynuowania procesu rejestracji.
4. Wywołuje się metoda registerCompany z obiektem companyService, aby zarejestrować nową firmę. Jeśli podczas rejestracji wystąpił wyjątek, dodawane są odpowiednie błędy do obiektu result i przekierowanie następuje na stronę rejestracji ("/register").
Jeśli rejestracja firmy powiodła się, następuje przekierowanie na stronę główną ("/").

#### - Metoda `signInUserOrCompany` służy do uwierzytelniania użytkownika lub firmy przy użyciu Firebase Authentication.

lokalizacja: LoginController 

1. Tworzona jest instancja Firestore za pomocą metody FirestoreClient.getFirestore(), umożliwiająca operacje na bazie danych Firestore.
2. Pobierane są dane logowania z obiektu company, takie jak email, hasło i nazwa firmy.
3. Tworzona jest instancja FirebaseAuth za pomocą FirebaseAuth.getInstance(), umożliwiająca uwierzytelnianie użytkowników.
4. Wykonuje się zapytanie do bazy danych Firestore w celu pobrania dokumentów z kolekcji "companies", gdzie email jest równy podanemu emailowi.
5. Wykonuje się zapytanie do bazy danych Firestore w celu pobrania dokumentów z kolekcji "users", gdzie email jest równy podanemu emailowi.
6. Pobrane dane są zapisywane w obiektach futureCompanies i futureUsers, reprezentujących asynchroniczne wyniki zapytań.
7. Przechodzi się do obsługi wyjątków związanych z pobieraniem wyników zapytań.
8. W pętli iteruje się przez dokumenty z pobranej kolekcji "companies" i porównuje się hasło z zahashowanym hasłem podanym przez użytkownika.
9. Jeśli hasła są zgodne, zapisuje się w atrybucie sesji "companyName" nazwę firmy z dokumentu oraz ustawia się atrybut "userType" na "manager", wskazując, że uwierzytelniony użytkownik to manager firmy. Następnie zwracany jest wynik "success".
10. Jeśli hasła nie są zgodne, przechodzi się do pętli iterującej przez dokumenty z kolekcji "users" i porównuje się hasło z zahashowanym hasłem podanym przez użytkownika.
11. Jeśli hasła są zgodne, zapisuje się w atrybutach sesji "userName" nazwę użytkownika z dokumentu, "userId" identyfikator użytkownika z dokumentu oraz ustawia się atrybut "userType" na "user", wskazując, że uwierzytelniony użytkownik to zwykły użytkownik. Następnie zwracany jest wynik "success".
12. Jeśli nie znaleziono pasujących dokumentów w obu kolekcjach lub hasła nie są zgodne, zwracany jest wynik "Dane logowania niepoprawne".

#### - Metoda `loginCompany` obsługuje proces logowania firmy w systemie.

lokalizacja: LoginController 

1. Jeśli wystąpiły błędy walidacji danych logowania, błędy są dodawane do modelu i sprawdzane, czy występują błędy związane z polem "email" lub "password". Jeśli tak, zwracany jest widok "login" bez kontynuowania procesu logowania.
2. Wywołuje się metoda signInUserOrCompany w celu uwierzytelnienia firmy. Jeśli wynik uwierzytelniania to "success", ustawia się atrybut sesji "loggedIn" na wartość true i zwraca się przekierowanie na stronę "/home".
3. Jeśli uwierzytelnianie nie powiodło się, przekierowuje się na stronę "/login".

#### - Metoda `homePage` obsługuje wyświetlanie strony głównej w zależności od stanu uwierzytelnienia użytkownika.

lokalizacja: HomeController

1. Sprawdza, czy użytkownik jest uwierzytelniony poprzez odczytanie atrybutu sesji "loggedIn". Jeśli użytkownik jest uwierzytelniony, pobierane są również informacje o typie użytkownika ("userType"), nazwie użytkownika ("userName") oraz nazwie firmy ("companyName").
2. Jeśli użytkownik jest uwierzytelniony jako menadżer firmy (typ użytkownika "manager"), dodaje do modelu nazwę firmy jako "userName" i pobiera listę użytkowników dla tej firmy. Następnie dodaje listę użytkowników do modelu jako "userList" i zwraca widok "home_manager".
3. Jeśli użytkownik jest uwierzytelniony jako pracownik (typ użytkownika "user"), dodaje do modelu identyfikator użytkownika jako "userId" oraz nazwę użytkownika jako "userName" i zwraca widok "home_worker".
4. Jeśli użytkownik nie jest uwierzytelniony lub typ użytkownika jest inny niż "manager" lub "user", przekierowuje na stronę logowania ("/login").

#### - Metoda `findByCompanyName` służy do wyszukiwania użytkowników w oparciu o nazwę firmy. 

lokalizacja: HomeController

1. Tworzony jest obiekt firestore przy użyciu metody FirestoreClient.getFirestore(), który umożliwia komunikację z bazą danych Firebase.
2. Inicjalizowana jest pusta lista userList, która będzie przechowywać obiekty typu User.
3. Tworzona jest referencja do kolekcji "users" w bazie danych Firebase.
4. Tworzone jest zapytanie do bazy danych, które filtruje dokumenty na podstawie pola "companyName" zgodnie z przekazaną nazwą firmy.
5. Wykonuje się zapytanie do bazy danych i oczekuje na wynik.
6. Iteruje się po dokumentach wynikowych, a dla każdego dokumentu tworzy się obiekt typu User na podstawie danych z dokumentu. Obiekt User jest dodawany do listy userList.
7. Zwracana jest lista userList zawierająca znalezione rekordy użytkowników.



#### - Metoda `createUser` służy do tworzenia nowego użytkownika na podstawie przesłanych danych formularza.

lokalizacja: UserController

1. Wywoływana jest metoda registerUser(user, session), która jest odpowiedzialna za rejestrację nowego użytkownika na podstawie przekazanych danych. Wynik rejestracji jest porównywany z wartością "success".
2. Jeśli rezultat rejestracji jest równy "success", przekierowuje na stronę "home" za pomocą adresu URL "redirect:/home".
3. Jeśli rezultat rejestracji nie jest równy "success", zwraca widok "add_people".

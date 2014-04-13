-- phpMyAdmin SQL Dump
-- version 4.1.11
-- http://www.phpmyadmin.net
--
-- Počítač: localhost
-- Vytvořeno: Ned 13. dub 2014, 23:06
-- Verze serveru: 5.5.24-log
-- Verze PHP: 5.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databáze: `mysql`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `administrativa`
--
-- Vytvořeno: Pon 24. bře 2014, 21:55
--

CREATE TABLE IF NOT EXISTS `administrativa` (
  `uzivatelskejmeno` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `mobilnitelefon` varchar(50) NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vypisuji data pro tabulku `administrativa`
--

INSERT INTO `administrativa` (`uzivatelskejmeno`, `email`, `telefon`, `mobilnitelefon`) VALUES
('2N94Z86LPY', 'tichy@seznam.cz', '+420888777555', '+420888777444'),
('9JPPMMG204', 'racinsky.matej@seznam.cz', '+42077874641', '+42047866688'),
('RXNDZSG8I3', 'memnarch@seznam.cz', '+420777894841', '+420777864981'),
('RZT4I5J4JY', 'memnarch@seznam.cz', '+420778746416', '+420777864981'),
('WXWJAYNFAM', 'memnarch@seznam.cz', '+420777894841', '+420777864981');

-- --------------------------------------------------------

--
-- Struktura tabulky `ip_adresa`
--
-- Vytvořeno: Úte 25. úno 2014, 21:55
--

CREATE TABLE IF NOT EXISTS `ip_adresa` (
  `ip` varchar(20) CHARACTER SET latin1 NOT NULL,
  `count` int(10) NOT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `ip_adresa`
--

INSERT INTO `ip_adresa` (`ip`, `count`) VALUES
('127.0.0.1', 43),
('147.32.216.99', 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `login`
--
-- Vytvořeno: Sob 22. úno 2014, 15:58
--

CREATE TABLE IF NOT EXISTS `login` (
  `uzivatelskejmeno` varchar(20) COLLATE utf8_czech_ci NOT NULL,
  `jmeno` varchar(20) COLLATE utf8_czech_ci NOT NULL,
  `prijmeni` varchar(20) COLLATE utf8_czech_ci NOT NULL,
  `hashhesla` varchar(1000) COLLATE utf8_czech_ci NOT NULL,
  `rights` int(1) NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `login`
--

INSERT INTO `login` (`uzivatelskejmeno`, `jmeno`, `prijmeni`, `hashhesla`, `rights`) VALUES
('0G4T8O70RG', 'nkědo', 'cizí', '48923273-12511656118-12613-77-10310512310716-587554-8621317087104-76-119-20-8023', 3),
('1KP4V8SWUF', 'Eva', 'Drahá', '-61-4311816-431102661102-52127308-51-14-101122310454-26-102-402820103-11-121106-92', 4),
('1WSCP3H2EC', 'Ludvík', 'Svoboda', '121-1199716-42-82-2345-53-874-60-102-2418-6932961519011078-55-31-10095121-291038841', 4),
('2N94Z86LPY', 'nikdo', 'zajímavý', '-55-119-711164249-6134-316-42-83-1-84-91-1009178-76-70-109-9681-2930-82122-58-128-571', 1),
('424231NTC1', 'jméno', 'příjmení', '-670-103-35-121273523-1049239-32-7457-49-8340118-52-65-64-1202011747-10-73-15-29-1021189', 3),
('47PR7NOPK8', 'Ludvík', 'Svoboda', '-44-175135103-1697111-1202237-27-92-1081627-4468-117-91-37609732285137-13-35-8-2529', 2),
('4CICK5AF2A', 'Pan', 'Testovací', '66-25-105-22-83-127-4212441-56-3896-110-105105-84-3084-11710315-50-67-91-59-20-38-316-110111-38', 4),
('4HNT9ZUS3Y', 'Pan', 'Testovací', '-42-10-27-236784-65-23-4142-14939-3932-2518-104-29583-26-3-64874455-18874-95-3', 4),
('5RFN1O7A71', 'Ludvík', 'Svoboda', '-12554-5474-9735-7-8478356580-43-55-1192619128-605-16-10362122-33-112-128-106-6814', 4),
('6K53RP08Z3', 'jméno', 'příjmení', '-61-4311816-431102661102-52127308-51-14-101122310454-26-102-402820103-11-121106-92', 3),
('6R98JKH6Q7', 'Pan', 'Testovací', '-40-14-105-31-92-80-1-113-41-1-449689-102118-6624312643-114-53-27690-71111-66-59-32-6571', 4),
('6TE13KDZ2L', 'jméno', 'příjmení', '-670-103-35-121273523-1049239-32-7457-49-8340118-52-65-64-1202011747-10-73-15-29-1021189', 4),
('79655P34FC', 'jméno', 'příjmení', '-42-20-4544-29-75-24-119-10416-57-775844-64496542-106108-5788-14-38-91-83-8293-48-19112', 4),
('838YJ7LLGI', '1', '2', '110-1-968-4440-77-64-13-281084444-65-9546-8712074-75-41-69-81-60-69-9-121-83-123-10-124-75', 4),
('9JPPMMG204', 'Linda', 'Prchavá', 'V4FN90ZV0M', 1),
('A1KDJX7L6N', 'Pan', 'Testovací', '-20-21-27835-44-29-7479117-43-208611578-87119-38-1042479-82-1136-37-55-8263-124-127-105-20', 4),
('admin', 'Matěj', 'Račinský', '-66-12493-1760-82-105-10510361-110-57-61-479117-10627-101-43-57-4919118176250-5478-7811163', 0),
('administrativa', 'Jan', 'Pápelák', '60-49-98-12581811244-56121-37-6884-3-1214133-44-25-7917-91-313-110-3311-51-5584114-76', 1),
('ALP5LU7LAX', 'Učitel', 'Učitelský', '-9-445681612656672640-89127-3449-187348-25-81-4256-128-121-97-93-52-1049-711354-88', 2),
('AM5XCS0N1N', 'Učitel', 'Učitelský', '49-3810-3777-1249-7834-91406854-59-662136-14-8992-6614117419361118221211164036', 2),
('B30LPZ2XVR', 'Ludvík', 'Svoboda', '-114-794943-893488-46-1113-11554-88-839429-150-98-90958-40-120-100-7426114-94594-3', 4),
('BCOSQQR0HU', 'zevidovaný', 'uživatel', '-10-73914-126-671226314-6778-62-79-3388143817-34-3839-123-2026-644462-116-16-843434', 4),
('CF466ZGNLP', 'jméno', 'příjmení', '-42-20-4544-29-75-24-119-10416-57-775844-64496542-106108-5788-14-38-91-83-8293-48-19112', 3),
('CM1C4CEL2A', 'zevidovaný', 'uživatel', '-39124-4296-52-85-39-128418-10410865-821062281-6-70-88102-35-5719-24-99637107-1268457', 3),
('CSJ15PQTIR', 'Učitel', 'Učitelský', '-99115-10-26-29-19-112321-64-93-723113122-890-80-124762-110-75-102-64-68127-10-46-97103117', 2),
('DKNWGFQYYR', 'jméno', 'příjmení', '-107-1123486115-5366-110-5568-39-37-88243740-30-73917250-9952-12634-2072117-119-75-2236', 3),
('DQPBYJNQKV', 'Ludvík', 'Svoboda', '-10272-9510251-122-5857-16-10742-85-10-4753-125-127-210-8855101-35-37494949078126-51-6', 4),
('E84T4EZBEL', 'jméno', 'příjmení', '-21-122794114-82-21-26-815-112-1263212188134-619-6611015-2638116-107-90-201383-18-74', 4),
('IGR0KPQ268', 'změněné', 'přejmenovaný', '-409711-2074-12511189-98-111118893670-14-1038-121-88558-64109-599941-29-54-8294-47-31', 3),
('IZGQ2BWXOY', 'přejmenovaný', 'uživatel', '38560117-19-17115471114-9-317828-126-80-3243-6919-31-99-24-1-14-23-66-43111581834', 4),
('J60XCHHQTG', 'jméno', 'příjmení', '-21-20-204999055-40121120-125-12-109-7144-9711341-12293-15-8911811824-2376-2-18-11214', 4),
('JBQS7RZMTC', 'Učitel', 'Učitelský', '24393619-80-19-5312-8-48554110954410444-12015-21363212667-15106-967283-65-107-1', 2),
('JWHYKUFY8M', 'Marigolt', 'Jasnovidný', '22-80-757-63-7-9185-75-1-6288121-83555-93-9727-67-11567-83-100107-7855-751184106-41', 2),
('KIEVQESIP2', 'Učitel', 'Učitelský', '86-12812627-9432-898081-671187-454935-42-45-2594-7775107158353-110-126291016479-104', 2),
('OWTY45L18O', 'Pan', 'Testovací', '-79-121-103-28-55251107957-125-60-11270-113-38-12012693-23-5-34-54-11964-46-114-79-78-84-9883', 4),
('P5MFARL1H2', 'Pan', 'Testovací', '-3-107-84-100-7975-8553121-62-10895-11-59-97858357106-6667-12169-57-26-8235113-3069-105', 4),
('pedagog', 'Evžena', 'Cudná', '703297-2059-123-1-57-114120-128-71813-26-76-17-87-8563116412322-634544-15114-111-11473', 2),
('QRR4HOWVXM', 'Eva', 'Drahá', '32-14-2310795687038-12411962-61014-467892-12223732764-906222-672256-2174-90126', 4),
('RXNDZSG8I3', 'Nezajímavý', 'Zmetek', '5951-89-7539-6281-5074-125-2010977-2448-80-5253-999810-104-55-3125-121299511683-8034', 1),
('RZT4I5J4JY', 'Učitel', 'Učitelský', '43-11584-30-293-105-45734377022-372818-49-23109-11438-4537-34101927069-39-4653-59', 1),
('student', 'Boris', 'Malý', '-4711473-125-28-7198-119-111-124126-1121-12838-10019-1012458-61-411059-35916543-4-765079', 3),
('Učitel', 'Učitel', 'Učitelský', '-9661100101-5146-16-6223-17337973-288-64-38-92-78-54-99-114-7311347-110-112111-113-69-9394', 2),
('uchazec', 'Olga', 'Milá', '27-116-19-8133-80-16-103-94-89-6040-52111-95-12180-662-6042-2-934-80-7888-63-6057-31-88', 4),
('WXWJAYNFAM', 'Nezajímavý', 'Zmetek', '6186-4432-12-68-101-5967203371-91285659-80-97-126-110-8434-11241-56-110-44-572925-36-94', 1),
('X1WQ7YQG8U', 'Ludvík', 'Svoboda', '-18-111-6127-6-88-4421104-9-343-64113-1084852-29-842-12145-62-5154108-62-2863116-50120', 4),
('XIKZV0S0AG', 'Ludvík', 'Svoboda', '8545-110103-21-91-81-10-88-1774598-35-81-68-25-58734127107-81103448124-100-86-6-7965', 4),
('YP2KCXE1A1', 'jmeno', 'prijmeni', '22-7183-85-86-20-6-9611932-1254370331231138-103-34932-3876121-19576-5254113443', 4),
('YYY6UNUT2A', 'Nový', 'Pedagog', '-49-75-72-101264118-11295176-616101-46116-297086-35-3311073-5692-12321481103-9886', 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `pedagogove`
--
-- Vytvořeno: Pon 24. bře 2014, 21:06
--

CREATE TABLE IF NOT EXISTS `pedagogove` (
  `uzivatelskejmeno` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `mobilnitelefon` varchar(50) NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vypisuji data pro tabulku `pedagogove`
--

INSERT INTO `pedagogove` (`uzivatelskejmeno`, `email`, `telefon`, `mobilnitelefon`) VALUES
('47PR7NOPK8', 'memnarch@seznam.cz', '+420778746415', '+420478666888'),
('ALP5LU7LAX', 'memnarch@seznam.cz', '+420777864981', '+420777864981'),
('AM5XCS0N1N', 'memnarch@seznam.cz', '+420777864981', '+420777864981'),
('CSJ15PQTIR', 'memnarch@seznam.cz', '+420778746415', '+420777864981'),
('JBQS7RZMTC', 'memnarch@seznam.cz', '+420777864981', '+420777864981'),
('JWHYKUFY8M', 'racinsky.matej@seznam.cz', '+420555777888', '+420222555666'),
('KIEVQESIP2', 'memnarch@seznam.cz', '+420777864981', '+420777864981'),
('YYY6UNUT2A', 'memnarch@seznam.cz', '+420777888555', '+420777888999');

-- --------------------------------------------------------

--
-- Struktura tabulky `studenti`
--
-- Vytvořeno: Pon 24. bře 2014, 23:07
--

CREATE TABLE IF NOT EXISTS `studenti` (
  `uzivatelskejmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `semestr` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stavstudia` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `skupina` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`),
  KEY `uzivatelskejmeno` (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `studenti`
--

INSERT INTO `studenti` (`uzivatelskejmeno`, `semestr`, `stavstudia`, `skupina`) VALUES
('0G4T8O70RG', '5', 'studuje', '42'),
('424231NTC1', '1', 'studující', '666'),
('6K53RP08Z3', '3', 'studující', '42'),
('CF466ZGNLP', '1', 'studuje', '5'),
('CM1C4CEL2A', '1', 'studuje', '5'),
('DKNWGFQYYR', '1', 'studuje', '7'),
('IGR0KPQ268', '1', 'studuje', '42');

-- --------------------------------------------------------

--
-- Struktura tabulky `uchazeci`
--
-- Vytvořeno: Čtv 27. úno 2014, 09:48
--

CREATE TABLE IF NOT EXISTS `uchazeci` (
  `uzivatelskejmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniprogram` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniobor` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `pohlavi` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `statniprislusnost` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodinnystav` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `dennarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mesicnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `roknarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cisloOP` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodnecislo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislopasu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mistonarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okresnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `castobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `obec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `posta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacastobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaobec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaokres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresapsc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresastat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresatelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaposta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `nazevstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `adresastredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `oborstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `jkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `izo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rokmaturity` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mobilnitelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stavprihlasky` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `skolne` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `uchazeci`
--

INSERT INTO `uchazeci` (`uzivatelskejmeno`, `studijniprogram`, `studijniobor`, `pohlavi`, `statniprislusnost`, `rodinnystav`, `email`, `dennarozeni`, `mesicnarozeni`, `roknarozeni`, `cisloOP`, `rodnecislo`, `cislopasu`, `mistonarozeni`, `okresnarozeni`, `ulice`, `cislodomu`, `castobce`, `obec`, `okres`, `psc`, `stat`, `telefon`, `posta`, `kontaktniadresaulice`, `kontaktniadresacislodomu`, `kontaktniadresacastobce`, `kontaktniadresaobec`, `kontaktniadresaokres`, `kontaktniadresapsc`, `kontaktniadresastat`, `kontaktniadresatelefon`, `kontaktniadresaposta`, `nazevstredniskoly`, `adresastredniskoly`, `oborstredniskoly`, `jkov`, `kkov`, `izo`, `rokmaturity`, `mobilnitelefon`, `stavprihlasky`, `skolne`) VALUES
('0G4T8O70RG', 'program', 'obor', 'muž', 'česká', 'svobodný', 'racinsky.matej@seznam.cz', '16', '6', '1993', '10', '930616/0402', 'nevyplněno', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '7799', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'Jiřího z Poděbrad', '7799', 'částovice', 'obec', 'okres', '40747', 'český', '+420', 'malá', 'gymnázium', 'Ústavní', 'všeobecné', '555', '5', '7894564', '1983', '+420777864981', 'prijat', 'zaplaceno'),
('424231NTC1', 'dgs', 'gds', 'muž', 'vds', 'dsy vf', 'glimmervodid@seznam.cz', '84', '5', '201154', '6667', '8884', '9992', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '+420777864981', 'prijat', 'nezaplaceno'),
('6K53RP08Z3', 'studium krabic', 'papírových', 'muž', 'česká', 'ženatý', 'glimmervoid@seznam.cz', '8', '5', '2011', '666', '888', '999', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '+420777864981', 'prijat', 'nezaplaceno'),
('6TE13KDZ2L', 'stále testuji přenos', 'obor', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '6', '1', '6', '5', '5', '5', 'praha', 'praha', 'Ji?ího z Pod?brad', '5', '?ástovice', 'obec', 'okres', '40747', 'N?mecko', '5', 'malá', 'nijaká', '6', '6', '?ástovice', 'obec', 'okres', '5', 'malá', '?eský', 'školská', 'školská', 'obor', '5', '5', '5', '2000', '+420777864981', 'prijat', 'nezaplaceno'),
('79655P34FC', 'program', 'obor', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '16', '6', '1993', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'gymnízium', 'res', 'res', '5', '5', '5', '1983', '+42047866688', 'prijat', 'nezaplaceno'),
('BCOSQQR0HU', 'nový', 'design', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '16', '6', '1993', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+420778746415', 'malá', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+420', 'malá', 'gymnázium', 'Ústavní', 'všeobecné', '5', '5', '5', '2000', '+420478666888', 'prijat', 'nezaplaceno'),
('CF466ZGNLP', 'program', 'obor', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '16', '6', '1993', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'gymnízium', 'res', 'res', '5', '5', '5', '1983', '+42047866688', 'prijat', 'nezaplaceno'),
('CM1C4CEL2A', 'nový', 'design', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '16', '6', '1993', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+420778746415', 'malá', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+420', 'malá', 'gymnázium', 'Ústavní', 'všeobecné', '5', '5', '5', '2000', '+420478666888', 'prijat', 'nezaplaceno'),
('DKNWGFQYYR', 'prokrastinační metody', 'studium gaučingu', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '16', '6', '1993', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '+42077874641', 'malá', 'gymnízium', 'res', 'res', '5', '5', '5', '1983', '+42047866688', 'prijat', 'nezaplaceno'),
('E84T4EZBEL', 'dgs', 'gds', 'muž', 'vds', 'dsy vf', 'glimmervoid@seznam.cz', '84', '5', '201154', '6667', '8884', '9992', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '+420777864981', 'prijat', 'nezaplaceno'),
('IGR0KPQ268', 'program', 'obor', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '5', '5', '6', '10', '8884', '9992', 'Praha', 'Ostrava', 'Ji?ího z Pod?brad', '7799', '?ástovice', 'obícka', 'okrsek', '40747', '?eský', '725427954', 'malá', 'hgc', '5555', 'thfx', 'fthgxh', 'htrfx', '456', 'htgf', '4523', 'fgx', 'bn', 'res', 'res', '5', '555', '5', '2000', '+420777864981', 'nezevidovan', 'nezaplaceno'),
('IZGQ2BWXOY', 'systémy', 'otev?ené', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '6', '2', '2013', '555', '777', '666', 'praha 8', 'praha', 'tajemná', '5555', 'malá', 'obícka', 'okrsek', '87494', '?eská Republika', '789456123', 'zapadlá pošta', 'tajemná', '5555', '789456123', 'malá', 'obícka', 'okrsek', '87494', 'zapadlá pošta', '?eská Republika', 'st?ední', 'pod st?ížkou', 'obecná', '555', '555', '7894564', '2000', '+420777864981', 'zaplacenpoplatek', 'nezaplaceno'),
('J60XCHHQTG', 'dgs', 'gds', 'muž', 'vds', 'dsy vf', 'glimmervoid@seznam.cz', '84', '5', '201154', '6667', '8884', '9992', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '+420777864981', 'nezaplacenpoplatek', 'nezaplaceno');

-- --------------------------------------------------------

--
-- Struktura tabulky `uchazeci_ipspam`
--
-- Vytvořeno: Stř 12. bře 2014, 23:54
--

CREATE TABLE IF NOT EXISTS `uchazeci_ipspam` (
  `uzivatelskejmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniprogram` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniobor` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `pohlavi` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `statniprislusnost` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodinnystav` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `dennarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mesicnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `roknarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cisloOP` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodnecislo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislopasu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mistonarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okresnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `castobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `obec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `posta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacastobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaobec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaokres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresapsc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresastat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresatelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaposta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `nazevstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `adresastredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `oborstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `jkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `izo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rokmaturity` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mobilnitelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stavprihlasky` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `skolne` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `uchazeci_ipspam`
--

INSERT INTO `uchazeci_ipspam` (`uzivatelskejmeno`, `studijniprogram`, `studijniobor`, `pohlavi`, `statniprislusnost`, `rodinnystav`, `email`, `dennarozeni`, `mesicnarozeni`, `roknarozeni`, `cisloOP`, `rodnecislo`, `cislopasu`, `mistonarozeni`, `okresnarozeni`, `ulice`, `cislodomu`, `castobce`, `obec`, `okres`, `psc`, `stat`, `telefon`, `posta`, `kontaktniadresaulice`, `kontaktniadresacislodomu`, `kontaktniadresacastobce`, `kontaktniadresaobec`, `kontaktniadresaokres`, `kontaktniadresapsc`, `kontaktniadresastat`, `kontaktniadresatelefon`, `kontaktniadresaposta`, `nazevstredniskoly`, `adresastredniskoly`, `oborstredniskoly`, `jkov`, `kkov`, `izo`, `rokmaturity`, `mobilnitelefon`, `stavprihlasky`, `skolne`) VALUES
('OWTY45L18O', 'program', 'obor', 'muž', 'nový formulář', 'neženat', 'memnarch@seznam.cz', '93', '6', '16', '5', '930616/0402', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '7799', 'castobce', 'Podbořany', 'okres', '40747', 'český', '+42077874641', 'malá', 'Jiřího z Poděbrad', '7799', 'castobce', 'Podbořany', 'okres', '40747', 'český', '+42077874641', 'malá', 'gymnízium', 'Ústavní', 'všeobecné', '5', '5', '7894564', '1983', '+420777864981', 'nezaplacenpoplatek', 'nezaplaceno'),
('QRR4HOWVXM', 'studium krabic', 'papírových', 'muž', '?eská', 'ženatý', 'glimmervoid@seznam.cz', '8', '5', '2011', '666', '888', '999', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '+420777864981', 'zaplacenpoplatek', 'nezaplaceno'),
('XIKZV0S0AG', 'program', 'obor', 'muž', 'česká', 'svobodný', 'memnarch@seznam.cz', '5', '1', '5', '5', '5', '5', 'Praha', 'Ostrava', 'Jiřího z Poděbrad', '5', 'částovice', 'obec', 'okres', '40747', 'český', '5', 'malá', 'nejkřivolačejší ulice', '5', 'částovice', 'obec', 'okres', '40747', 'český', '5', 'malá', 'bn', 'res', 'res', '5', '5', '5', '38', '+420777864981', 'zaplacenpoplatek', 'nezaplaceno');

-- --------------------------------------------------------

--
-- Struktura tabulky `uchazeci_spam`
--
-- Vytvořeno: Stř 12. bře 2014, 23:54
--

CREATE TABLE IF NOT EXISTS `uchazeci_spam` (
  `uzivatelskejmeno` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniprogram` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `studijniobor` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `pohlavi` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `statniprislusnost` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodinnystav` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `dennarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mesicnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `roknarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cisloOP` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rodnecislo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislopasu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mistonarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okresnarozeni` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `ulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `cislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `castobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `obec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `okres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `psc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `telefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `posta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaulice` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacislodomu` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresacastobce` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaobec` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaokres` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresapsc` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresastat` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresatelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kontaktniadresaposta` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `nazevstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `adresastredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `oborstredniskoly` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `jkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `kkov` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `izo` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `rokmaturity` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `mobilnitelefon` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `stavprihlasky` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `skolne` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  PRIMARY KEY (`uzivatelskejmeno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `uchazeci_spam`
--

INSERT INTO `uchazeci_spam` (`uzivatelskejmeno`, `studijniprogram`, `studijniobor`, `pohlavi`, `statniprislusnost`, `rodinnystav`, `email`, `dennarozeni`, `mesicnarozeni`, `roknarozeni`, `cisloOP`, `rodnecislo`, `cislopasu`, `mistonarozeni`, `okresnarozeni`, `ulice`, `cislodomu`, `castobce`, `obec`, `okres`, `psc`, `stat`, `telefon`, `posta`, `kontaktniadresaulice`, `kontaktniadresacislodomu`, `kontaktniadresacastobce`, `kontaktniadresaobec`, `kontaktniadresaokres`, `kontaktniadresapsc`, `kontaktniadresastat`, `kontaktniadresatelefon`, `kontaktniadresaposta`, `nazevstredniskoly`, `adresastredniskoly`, `oborstredniskoly`, `jkov`, `kkov`, `izo`, `rokmaturity`, `mobilnitelefon`, `stavprihlasky`, `skolne`) VALUES
('1KP4V8SRUF', 'studium krabic', 'papírových', 'muž', '?eská', 'ženatý', 'glimmervoid@seznam.cz', '8', '5', '2011', '666', '888', '999', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '', 'nezaplacen registrační poplatek', ''),
('CF466ZGRLP', 'program', 'obor', 'muž', '?eská', 'svobodný', 'memnarch@seznam.cz', '6', '1', '6', '5', '5', '5', 'praha', 'praha', 'Ji?ího z Pod?brad', '5', '?ástovice', 'obec', 'okres', '40747', 'N?mecko', '5', 'malá', 'nijaká', '6', '6', '?ástovice', 'obec', 'okres', '5', 'malá', '?eský', 'školská', 'školská', 'obor', '5', '5', '5', '2000', '', 'zaplacen registrační poplatek', 'nezaplaceno'),
('CM1C4CE42A', 'dgs', 'gds', 'muž', 'vds', 'dsy vf', 'glimmervoid@seznam.cz', '84', '5', '201154', '6667', '8884', '9992', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '', 'zaplacen registrační poplatek', ''),
('DKNWGFQ2YR', 'program', 'obor', 'muž', '?eská', 'svobodný', 'memnarch@seznam.cz', '5', '5', '6', '10', '8884', '9992', 'Praha', 'Ostrava', 'Ji?ího z Pod?brad', '7799', '?ástovice', 'obícka', 'okrsek', '40747', '?eský', '725427954', 'malá', 'hgc', '5555', 'thfx', 'fthgxh', 'htrfx', '456', 'htgf', '4523', 'fgx', 'bn', 'res', 'res', '5', '555', '5', '2000', '', 'zaplacen registrační poplatek', 'nezaplaceno'),
('E84T4EZ4EL', 'systémy', 'otev?ené', 'muž', '?eská', 'svobodný', 'memnarch@seznam.cz', '6', '2', '2013', '555', '777', '666', 'praha 8', 'praha', 'tajemná', '5555', 'malá', 'obícka', 'okrsek', '87494', '?eská Republika', '789456123', 'zapadlá pošta', 'tajemná', '5555', '789456123', 'malá', 'obícka', 'okrsek', '87494', 'zapadlá pošta', '?eská Republika', 'st?ední', 'pod st?ížkou', 'obecná', '555', '555', '7894564', '2000', '', 'zaplacen registrační poplatek', ''),
('IGR0KPQ768', 'dgs', 'gds', 'muž', 'vds', 'dsy vf', 'glimmervoid@seznam.cz', '84', '5', '201154', '6667', '8884', '9992', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '', 'zaplacen registrační poplatek', ''),
('J60XCHH7TG', 'program', 'obor', 'muž', '?eská', 'svobodný', 'memnarch@seznam.cz', '6', '1', '6', '5', '5', '5', 'praha', 'praha', 'Ji?ího z Pod?brad', '5', '?ástovice', 'obec', 'okres', '40747', 'N?mecko', '5', 'malá', 'nijaká', '6', '6', '?ástovice', 'obec', 'okres', '5', 'malá', '?eský', 'školská', 'školská', 'obor', '5', '5', '5', '2000', '', 'zaplacen registrační poplatek', ''),
('QRR4HOW4XM', 'studium krabic', 'papírových', 'muž', '?eská', 'ženatý', 'glimmervoid@seznam.cz', '8', '5', '2011', '666', '888', '999', 'malá ?tvr?', 'brno', 'p?í?ná', '7799', 'velká', 'obec', 'okres', '780490', 'N?mecko', '123456789', 'n?jaká pošta', 'p?í?ná', '7799', '123456789', 'velká', 'obec', 'okres', '780490', 'n?jaká pošta', 'N?mecko', 'u?ilišt?', 'za sklepem', 'u?ilišt?', '555', '555', '7894564', '2000', '', 'zaplacen registrační poplatek', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

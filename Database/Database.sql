-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 06 Lut 2019, 19:00
-- Wersja serwera: 10.3.12-MariaDB-1:10.3.12+maria~bionic-log
-- Wersja PHP: 7.2.14-1+ubuntu18.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `DeliveryDb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Address`
--

CREATE TABLE `Address` (
  `ID` int(11) NOT NULL,
  `HouseNumber` varchar(5) COLLATE utf8_polish_ci NOT NULL,
  `ApartmentNumber` char(4) COLLATE utf8_polish_ci DEFAULT NULL,
  `Street` varchar(80) COLLATE utf8_polish_ci DEFAULT NULL,
  `PostCode` char(6) COLLATE utf8_polish_ci NOT NULL,
  `City` varchar(40) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Client`
--

CREATE TABLE `Client` (
  `ID` int(11) NOT NULL,
  `Username` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `ID_data` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `ClientHistory`
-- (See below for the actual view)
--
CREATE TABLE `ClientHistory` (
`ID` int(11)
,`Location` varchar(255)
,`DeliveredStatus` varchar(12)
,`ID_client` int(11)
,`TelephoneNumber` int(9)
,`senderFirstName` varchar(255)
,`SenderLastName` varchar(255)
,`SenderCity` varchar(40)
,`SenderPostCode` char(6)
,`SenderStreet` varchar(80)
,`SenderHouseNumber` varchar(5)
,`SenderApartmentNumber` char(4)
,`ReceiverFirstName` varchar(255)
,`ReceiverLastName` varchar(255)
,`ReceiverCity` varchar(40)
,`ReceiverPostCode` char(6)
,`ReceiverStreet` varchar(80)
,`ReceiverHouseNumber` varchar(5)
,`ReceiverApartmentNumber` char(4)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Courier`
--

CREATE TABLE `Courier` (
  `ID` int(11) NOT NULL,
  `Username` varchar(255) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `CourierData`
-- (See below for the actual view)
--
CREATE TABLE `CourierData` (
`ID_courier` int(11)
,`ID` int(11)
,`ID_client` int(11)
,`TelephoneNumber` int(9)
,`DeliveredStatus` varchar(12)
,`ReceiverFirstName` varchar(255)
,`ReceiverLastName` varchar(255)
,`ReceiverCity` varchar(40)
,`ReceiverPostCode` char(6)
,`ReceiverStreet` varchar(80)
,`ReceiverHouseNumber` varchar(5)
,`ReceiverApartmentNumber` char(4)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Data`
--

CREATE TABLE `Data` (
  `ID` int(11) NOT NULL,
  `FirstName` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `LastName` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `ID_address` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Dimensions`
--

CREATE TABLE `Dimensions` (
  `ID` int(11) NOT NULL,
  `Width` int(11) NOT NULL,
  `Height` int(11) NOT NULL,
  `Depth` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Email`
--

CREATE TABLE `Email` (
  `ID` int(11) NOT NULL,
  `Email` varchar(128) COLLATE utf8_polish_ci NOT NULL,
  `Confirmation` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Package`
--

CREATE TABLE `Package` (
  `ID` int(11) NOT NULL,
  `ID_sender` int(11) NOT NULL,
  `ID_receiver` int(11) NOT NULL,
  `Location` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `ID_courier` int(11) NOT NULL,
  `ID_dimensions` int(11) NOT NULL,
  `TelephoneNumber` int(9) NOT NULL,
  `Date` date NOT NULL,
  `ID_client` int(11) NOT NULL,
  `DeliveredStatus` varchar(12) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Storeman`
--

CREATE TABLE `Storeman` (
  `ID` int(11) NOT NULL,
  `Username` varchar(255) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `StoremanData`
-- (See below for the actual view)
--
CREATE TABLE `StoremanData` (
`ID` int(11)
,`DeliveredStatus` varchar(12)
,`ID_courier` int(11)
,`City` varchar(40)
,`PostCode` char(6)
,`Street` varchar(80)
,`HouseNumber` varchar(5)
,`ApartmentNumber` char(4)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `User`
--

CREATE TABLE `User` (
  `Username` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `UserType` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `ID_email` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Wyzwalacze `User`
--
DELIMITER $$
CREATE TRIGGER `add_memberships` AFTER INSERT ON `User` FOR EACH ROW BEGIN
	IF (NEW.UserType = 'Client') THEN
    	INSERT INTO Client SET Username = NEW.Username ;
    ELSEIF (NEW.UserType = 'Courier') THEN
    	INSERT INTO Courier SET Username = NEW.Username ;
    ELSEIF (NEW.UserType = 'Storeman') THEN
    	INSERT INTO Storeman SET Username = NEW.Username ;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura widoku `ClientHistory`
--
DROP TABLE IF EXISTS `ClientHistory`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ClientHistory`  AS  select `Package`.`ID` AS `ID`,`Package`.`Location` AS `Location`,`Package`.`DeliveredStatus` AS `DeliveredStatus`,`Package`.`ID_client` AS `ID_client`,`Package`.`TelephoneNumber` AS `TelephoneNumber`,`s`.`FirstName` AS `senderFirstName`,`s`.`LastName` AS `SenderLastName`,`sa`.`City` AS `SenderCity`,`sa`.`PostCode` AS `SenderPostCode`,`sa`.`Street` AS `SenderStreet`,`sa`.`HouseNumber` AS `SenderHouseNumber`,`sa`.`ApartmentNumber` AS `SenderApartmentNumber`,`r`.`FirstName` AS `ReceiverFirstName`,`r`.`LastName` AS `ReceiverLastName`,`ra`.`City` AS `ReceiverCity`,`ra`.`PostCode` AS `ReceiverPostCode`,`ra`.`Street` AS `ReceiverStreet`,`ra`.`HouseNumber` AS `ReceiverHouseNumber`,`ra`.`ApartmentNumber` AS `ReceiverApartmentNumber` from ((`Package` join (`Data` `s` join `Address` `sa` on(`s`.`ID_address` = `sa`.`ID`))) join (`Data` `r` join `Address` `ra` on(`r`.`ID_address` = `ra`.`ID`))) where `Package`.`ID_sender` = `s`.`ID` and `Package`.`ID_receiver` = `r`.`ID` ;

-- --------------------------------------------------------

--
-- Struktura widoku `CourierData`
--
DROP TABLE IF EXISTS `CourierData`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `CourierData`  AS  select `Package`.`ID_courier` AS `ID_courier`,`Package`.`ID` AS `ID`,`Package`.`ID_client` AS `ID_client`,`Package`.`TelephoneNumber` AS `TelephoneNumber`,`Package`.`DeliveredStatus` AS `DeliveredStatus`,`r`.`FirstName` AS `ReceiverFirstName`,`r`.`LastName` AS `ReceiverLastName`,`ra`.`City` AS `ReceiverCity`,`ra`.`PostCode` AS `ReceiverPostCode`,`ra`.`Street` AS `ReceiverStreet`,`ra`.`HouseNumber` AS `ReceiverHouseNumber`,`ra`.`ApartmentNumber` AS `ReceiverApartmentNumber` from (`Package` join (`Data` `r` join `Address` `ra` on(`r`.`ID_address` = `ra`.`ID`))) where `Package`.`ID_receiver` = `r`.`ID` ;

-- --------------------------------------------------------

--
-- Struktura widoku `StoremanData`
--
DROP TABLE IF EXISTS `StoremanData`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `StoremanData`  AS  select `p`.`ID` AS `ID`,`p`.`DeliveredStatus` AS `DeliveredStatus`,`p`.`ID_courier` AS `ID_courier`,`a`.`City` AS `City`,`a`.`PostCode` AS `PostCode`,`a`.`Street` AS `Street`,`a`.`HouseNumber` AS `HouseNumber`,`a`.`ApartmentNumber` AS `ApartmentNumber` from (`Package` `p` join (`Data` `d` join `Address` `a` on(`d`.`ID_address` = `a`.`ID`)) on(`p`.`ID_receiver` = `d`.`ID`)) ;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID` (`ID`);

--
-- Indexes for table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `username` (`Username`);

--
-- Indexes for table `Courier`
--
ALTER TABLE `Courier`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `username` (`Username`);

--
-- Indexes for table `Data`
--
ALTER TABLE `Data`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Data_ID` (`ID`);

--
-- Indexes for table `Dimensions`
--
ALTER TABLE `Dimensions`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indexes for table `Email`
--
ALTER TABLE `Email`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `Package`
--
ALTER TABLE `Package`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `package_id` (`ID`),
  ADD KEY `Date` (`Date`);

--
-- Indexes for table `Storeman`
--
ALTER TABLE `Storeman`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `username` (`Username`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`Username`),
  ADD KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `Address`
--
ALTER TABLE `Address`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT dla tabeli `Client`
--
ALTER TABLE `Client`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14908;
--
-- AUTO_INCREMENT dla tabeli `Courier`
--
ALTER TABLE `Courier`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT dla tabeli `Data`
--
ALTER TABLE `Data`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;
--
-- AUTO_INCREMENT dla tabeli `Dimensions`
--
ALTER TABLE `Dimensions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT dla tabeli `Email`
--
ALTER TABLE `Email`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14916;
--
-- AUTO_INCREMENT dla tabeli `Package`
--
ALTER TABLE `Package`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT dla tabeli `Storeman`
--
ALTER TABLE `Storeman`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

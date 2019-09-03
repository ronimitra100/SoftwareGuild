-- -----------------------------------------------------
-- Database HotelReservationSystem
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `HotelReservationSystem` ;

-- -----------------------------------------------------
-- Database HotelReservationSystem
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `HotelReservationSystem` DEFAULT CHARACTER SET utf8 ;
USE `HotelReservationSystem` ;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ROOM_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ROOM_TYPES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ROOM_TYPES` (
  `RoomTypeID` INT(3) NOT NULL AUTO_INCREMENT,
  `RoomTypeDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RoomTypeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ROOMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ROOMS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ROOMS` (
  `RoomTypeID` INT(3) NOT NULL,
  `RoomNum` INT(5) NOT NULL,
  `Floor` VARCHAR(3) NOT NULL,
  `OccupancyLimit` INT NULL,
  PRIMARY KEY (`RoomNum`),
  CONSTRAINT `FK_RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `HotelReservationSystem`.`ROOM_TYPES` (`RoomTypeID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`AMENITIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`AMENITIES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`AMENITIES` (
  `AmenityID` INT(2) NOT NULL AUTO_INCREMENT,
  `Amenity Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AmenityID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ROOM_AMENITIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ROOM_AMENITIES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ROOM_AMENITIES` (
  `ID` INT NOT NULL,
  `RoomNum` INT(5) NOT NULL,
  `AmenityID` INT(2) NOT NULL,
  
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_RoomNum`
    FOREIGN KEY (`RoomNum`)
    REFERENCES `HotelReservationSystem`.`ROOMS` (`RoomNum`),
  CONSTRAINT `FK_AmenityID`
    FOREIGN KEY (`AmenityID`)
    REFERENCES `HotelReservationSystem`.`AMENITIES` (`AmenityID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ROOM_RATES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ROOM_RATES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ROOM_RATES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `RoomTypeID` INT(3) NOT NULL,
  `StartDate` DATE NULL,
  `EndDate` DATE NULL,
  `RoomRate` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `RoomTypeID_UNIQUE` (`RoomTypeID` ASC),
  CONSTRAINT `RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `HotelReservationSystem`.`ROOM_TYPES` (`RoomTypeID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`CUSTOMERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`CUSTOMERS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`CUSTOMERS` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `CustomerName` VARCHAR(45) NOT NULL,
  `CustomerPhone` VARCHAR(45) NOT NULL,
  `CusomertEmail` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CustomerID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`GUESTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`GUESTS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`GUESTS` (
  `GuestID` INT NOT NULL AUTO_INCREMENT,
  `CustomerID` INT NOT NULL,
  `GuestName` VARCHAR(45) NOT NULL,
  `GuestAge` INT NULL,
  PRIMARY KEY (`GuestID`),
  INDEX `FK_CustomerID_idx` (`CustomerID` ASC),
  CONSTRAINT `FK_CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `HotelReservationSystem`.`CUSTOMERS` (`CustomerID`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`RESERVATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`RESERVATIONS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`RESERVATIONS` (
  `ReservationID` INT NOT NULL AUTO_INCREMENT,
  `CustomerID` INT NOT NULL,
  `StartDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`ReservationID`),
  CONSTRAINT `FK_CUSTOMERS_CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `HotelReservationSystem`.`CUSTOMERS` (`CustomerID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ReservationID` INT NULL,
  `RoomNum` INT(5) NULL,
  `GuestID` INT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_RESERVATIONS_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`),
  CONSTRAINT `FK_ROOMS_RoomNum`
    FOREIGN KEY (`RoomNum`)
    REFERENCES `HotelReservationSystem`.`ROOMS` (`RoomNum`),
  CONSTRAINT `FK_GUESTS_GuestID`
    FOREIGN KEY (`GuestID`)
    REFERENCES `HotelReservationSystem`.`GUESTS` (`GuestID`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ADDONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ADDONS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ADDONS` (
  `AddOnID` INT(2) NOT NULL AUTO_INCREMENT,
  `AddOnDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AddOnID`),
  UNIQUE INDEX `AddOnDescription_UNIQUE` (`AddOnDescription` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ADDON_RATES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ADDON_RATES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ADDON_RATES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `AddOnID` INT(2) NOT NULL,
  `StartDate` DATE NULL,
  `EndDate` DATE NULL,
  `AddOnRate` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_AddOnID_idx` (`AddOnID` ASC),
  CONSTRAINT `FK_AddOnID`
    FOREIGN KEY (`AddOnID`)
    REFERENCES `HotelReservationSystem`.`ADDONS` (`AddOnID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ADDON_USAGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ADDON_USAGE` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ADDON_USAGE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ReservationID` INT NOT NULL,
  `AddOnID` INT(2) NOT NULL,
  `OrderDate` DATE NOT NULL,
  `Notes` VARCHAR(100),
  PRIMARY KEY (`ID`),
  INDEX `FK_ReservationID_idx` (`ReservationID` ASC),
  INDEX `FK_AddOnID_idx` (`AddOnID` ASC),
  CONSTRAINT `FK_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`),
  CONSTRAINT `FK_ADDONS_AddOnID`
    FOREIGN KEY (`AddOnID`)
    REFERENCES `HotelReservationSystem`.`ADDONS` (`AddOnID`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`PROMOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`PROMOS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`PROMOS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PromoCode` VARCHAR(45) NOT NULL,
  `StartDate` DATE NOT NULL,
  `PROMOScol` VARCHAR(45) NULL,
  `EndDate` DATE NULL,
  `PercentOffDiscount` DECIMAL(10,2) NULL,
  `DollarOffDiscount` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ID`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`PROMO_USAGE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`PROMO_USAGE` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`PROMO_USAGE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ReservationID` INT NOT NULL,
  `PromoID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_PU_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`),
  CONSTRAINT `FK_PromoID`
    FOREIGN KEY (`PromoID`)
    REFERENCES `HotelReservationSystem`.`PROMOS` (`ID`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`BILL_SUMMARY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`BILL_SUMMARY` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`BILL_SUMMARY` (
  `BillID` INT NOT NULL,
  `TotalCost` DECIMAL(10,2) NULL,
  `TotalTax` DECIMAL(10,2) NULL,
  `BillDate` DATE NULL,
  `ReservationID` INT NULL,
  PRIMARY KEY (`BillID`),
  INDEX `FK_ReservationID_idx` (`ReservationID` ASC),
  CONSTRAINT `FK_BS_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`TAX_TYPE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`TAX_TYPE` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`TAX_TYPE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TaxTypeDescription` VARCHAR(100) NOT NULL,
  `TaxPercentage` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`BILL_DETAILS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`BILL_DETAILS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`BILL_DETAILS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `BillSummaryID` INT NOT NULL,
  `RoomNum` INT(5) NULL,
  `AddOnID` INT(2) NULL,
  `UsageDate` DATE NULL,
  `UsageCost` DECIMAL(10,2) NULL,
  `TaxTypeID` INT NULL,
  `UsageTax` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_BD_RoomNum`
    FOREIGN KEY (`RoomNum`)
    REFERENCES `HotelReservationSystem`.`ROOMS` (`RoomNum`),
  CONSTRAINT `FK_BD_AddOnID`
    FOREIGN KEY (`AddOnID`)
    REFERENCES `HotelReservationSystem`.`ADDONS` (`AddOnID`),
  CONSTRAINT `FK_TaxTypeID`
    FOREIGN KEY (`TaxTypeID`)
    REFERENCES `HotelReservationSystem`.`TAX_TYPE` (`ID`),
  CONSTRAINT `FK_BillSummaryID`
    FOREIGN KEY (`BillSummaryID`)
    REFERENCES `HotelReservationSystem`.`BILL_SUMMARY` (`BillID`)
)
ENGINE = InnoDB;
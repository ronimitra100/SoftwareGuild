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

INSERT INTO `HotelReservationSystem`.`ROOM_TYPES` VALUES(1,'Single');
INSERT INTO `HotelReservationSystem`.`ROOM_TYPES` VALUES(2,'Double');
INSERT INTO `HotelReservationSystem`.`ROOM_TYPES` VALUES(3,'King');


-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`ROOMS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`ROOMS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`ROOMS` (
  `RoomTypeID` INT(3) NOT NULL,
  `RoomNum` INT(5) NOT NULL,
  `Floor` VARCHAR(3) NOT NULL,
  `OccupancyLimit` INT(3) NULL,
  PRIMARY KEY (`RoomNum`),
  CONSTRAINT `FK_RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `HotelReservationSystem`.`ROOM_TYPES` (`RoomTypeID`)
)
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(1,101,1, 1);
INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(2,202,2, 3);
INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(3,303,3, 2);
INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(1,405,4, 1);
INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(2,507,5, 3);
INSERT INTO `HotelReservationSystem`.`ROOMS` VALUES(3,608,6, 2);
-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`AMENITIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`AMENITIES` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`AMENITIES` (
  `AmenityID` INT(2) NOT NULL AUTO_INCREMENT,
  `Amenity Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AmenityID`))
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`AMENITIES` VALUES(1,'Fridge');
INSERT INTO `HotelReservationSystem`.`AMENITIES` VALUES(2,'Spa Bath');
INSERT INTO `HotelReservationSystem`.`AMENITIES` VALUES(3,'Microwave');

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

INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(1, 101, 2);
INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(2, 202, 3);
INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(3, 101, 1);
INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(4, 303, 2);
INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(5, 608, 1);
INSERT INTO `HotelReservationSystem`.`ROOM_AMENITIES` VALUES(6, 101, 3);
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
  CONSTRAINT `RoomTypeID`
    FOREIGN KEY (`RoomTypeID`)
    REFERENCES `HotelReservationSystem`.`ROOM_TYPES` (`RoomTypeID`)
)
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(1,1,'2019-01-01', '2019-11-15',100.00);
INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(2,2,'2019-01-01', '2019-11-15',200.00);
INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(3,3,'2019-01-01', '2019-11-15',150.00);
INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(4,1,'2019-11-16', '2020-01-15',150.00);
INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(5,2,'2019-11-16', '2020-01-15',250.00);
INSERT INTO `HotelReservationSystem`.`ROOM_RATES` VALUES(6,3,'2019-11-16', '2020-01-15',200.00);
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

INSERT INTO `HotelReservationSystem`.`CUSTOMERS` VALUES(1, 'John Doe', '111-222-3333', 'john.doe@gmail.com');
INSERT INTO `HotelReservationSystem`.`CUSTOMERS` VALUES(2, 'Mary Poppins', '444-555-4444', 'mary.poppins@gmail.com');
INSERT INTO `HotelReservationSystem`.`CUSTOMERS` VALUES(3, 'David Smith', '666-777-8888', 'david.smith@gmail.com');

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`GUESTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`GUESTS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`GUESTS` (
  `GuestID` INT NOT NULL,
  `CustomerID` INT NOT NULL,
  `GuestName` VARCHAR(45) NOT NULL,
  `GuestAge` INT NULL,
  PRIMARY KEY (`GuestID`),
  CONSTRAINT `FK_CustomerID`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `HotelReservationSystem`.`CUSTOMERS` (`CustomerID`)
)
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(1001, 1, 'John Doe', 25);
INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(2001, 2, 'Mary Poppins', 35);
INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(2002, 2, 'Mary Samll', 5);
INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(3001, 3, 'David Smith', 40);
INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(3002, 3, 'David Little', 1);
INSERT INTO `HotelReservationSystem`.`GUESTS` VALUES(3003, 3, 'David Young', 10);

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

INSERT INTO `HotelReservationSystem`.`RESERVATIONS` VALUES(10001,1,'2019-05-05', '2019-05-15');
INSERT INTO `HotelReservationSystem`.`RESERVATIONS` VALUES(10002,2,'2019-05-01', '2019-05-06');
INSERT INTO `HotelReservationSystem`.`RESERVATIONS` VALUES(10003,3,'2019-05-06', '2019-05-20');


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

INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900001,10001, 202,1001);
INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900002,10002, 303,2001);
INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900003,10002, 507,2002);
INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900004,10003, 608,3001);
INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900005,10003, 608,3002);
INSERT INTO `HotelReservationSystem`.`GUEST_ROOM_RESERVATIONS` VALUES(900006,10003, 101,3003);

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

INSERT INTO `HotelReservationSystem`.`ADDONS` VALUES(11,'Room Service');
INSERT INTO `HotelReservationSystem`.`ADDONS` VALUES(12,'Meals');

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

INSERT INTO `HotelReservationSystem`.`ADDON_RATES` VALUES(1, 11, '2019-05-07','2019-11-14', 10.00);
INSERT INTO `HotelReservationSystem`.`ADDON_RATES` VALUES(2, 11, '2019-11-14','2020-01-14', 15.00);
INSERT INTO `HotelReservationSystem`.`ADDON_RATES` VALUES(3, 12, '2019-05-07','2019-11-14', 30.00);
INSERT INTO `HotelReservationSystem`.`ADDON_RATES` VALUES(4, 12, '2019-11-14','2020-01-14', 45.00);

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

INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(1, 10001,11, '2019-05-07', "Ordered Room Service for Room 202");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(2, 10002,11, '2019-05-05', "Ordered Room Service for Room 507");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(3, 10002,12, '2019-05-05', "Ordered Lunch");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(4, 10002,12, '2019-05-05', "Ordered Dinner");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(5, 10003,11, '2019-05-08', "Ordered Room Service for Room 608");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(6, 10003,12, '2019-05-08', "Ordered Breakfast");
INSERT INTO `HotelReservationSystem`.`ADDON_USAGE` VALUES(7, 10003,12, '2019-05-08', "Ordered Dinner");
-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`PROMOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`PROMOS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`PROMOS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PromoCode` VARCHAR(45) NOT NULL,
  `StartDate` DATE NOT NULL,
  `EndDate` DATE NULL,
  `PercentOffDiscount` DECIMAL(10,2) NULL,
  `DollarOffDiscount` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ID`))
  ENGINE = InnoDB;
  
INSERT INTO `HotelReservationSystem`.`PROMOS` VALUES(1, 'GOOD_FRIDAY','2019-04-02', '2019-05-03', 0.00, 25.00);
INSERT INTO `HotelReservationSystem`.`PROMOS` VALUES(2, 'MEMORIAL_DAY','2019-05-04', '2019-06-05', 15.00, 0.00);



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

INSERT INTO `HotelReservationSystem`.`PROMO_USAGE` VALUES(91,10001,2);
INSERT INTO `HotelReservationSystem`.`PROMO_USAGE` VALUES(92,10002,1);
INSERT INTO `HotelReservationSystem`.`PROMO_USAGE` VALUES(93,10003,2);

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
  CONSTRAINT `FK_BS_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`)
)
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`BILL_SUMMARY` VALUES(1502,1.00,1.00,'2019-05-06', 10002);

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

INSERT INTO `HotelReservationSystem`.`TAX_TYPE` VALUES(1, 'Hotel Occupancy Tax', 10.00);
INSERT INTO `HotelReservationSystem`.`TAX_TYPE` VALUES(2, 'Sales Tax', 7.50);
INSERT INTO `HotelReservationSystem`.`TAX_TYPE` VALUES(3, 'Service Tax', 20.00);

-- -----------------------------------------------------
-- Table `HotelReservationSystem`.`BILL_DETAILS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HotelReservationSystem`.`BILL_DETAILS` ;

CREATE TABLE IF NOT EXISTS `HotelReservationSystem`.`BILL_DETAILS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ReservationID` INT NOT NULL,
  `RoomNum` INT(5),
  `AddOnUsageID` INT,
  `UsageDate` DATE NULL,
  `UsageCost` DECIMAL(10,2) NULL,
  `TaxTypeID` INT NULL,
  `UsageTax` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_BD_RoomNum`
    FOREIGN KEY (`RoomNum`)
    REFERENCES `HotelReservationSystem`.`ROOMS` (`RoomNum`),
  CONSTRAINT `FK_BD_AddOnUsageID`
    FOREIGN KEY (`AddOnUsageID`)
    REFERENCES `HotelReservationSystem`.`ADDON_USAGE` (`ID`),
  CONSTRAINT `FK_TaxTypeID`
    FOREIGN KEY (`TaxTypeID`)
    REFERENCES `HotelReservationSystem`.`TAX_TYPE` (`ID`),
    CONSTRAINT `FK_BD_ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `HotelReservationSystem`.`RESERVATIONS` (`ReservationID`)
)
ENGINE = InnoDB;

INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6001,10001,202,null,'2019-05-01',1650.00,1,165.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6002,10001,null,1,'2019-05-07',10.00,3,2.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6003,10002,303,null,'2019-05-01',900.00,1,90.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6004,10002,507,null,'2019-05-01',1200.00,1,120.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6005,10002,null,2,'2019-05-05',10.00,1,2.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6006,10002,null,3,'2019-05-05',15.00,1,1.13);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(6007,10002,null,4,'2019-05-05',15.00,1,1.13);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(60008,10003,101,null,'2019-05-06',1500.00,1,150.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(60009,10003,608,null,'2019-05-06',2250.00,1,225.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(60010,10003,null,5,'2019-05-08',10.00,1,2.00);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(60011,10003,null,6,'2019-05-08',15.00,1,1.13);
INSERT INTO `HotelReservationSystem`.`BILL_DETAILS` VALUES(60012,10003,null,7,'2019-05-08',15.00,1,1.13);
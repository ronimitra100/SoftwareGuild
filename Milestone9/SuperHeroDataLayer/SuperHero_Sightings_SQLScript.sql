-- -----------------------------------------------------
-- Database SuperHero_Sightings
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `SuperHero_Sightings` ;
CREATE DATABASE IF NOT EXISTS `SuperHero_Sightings` ;
USE `SuperHero_Sightings` ;

-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`SuperHeroes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`SuperHeroes` (
  `HeroID` INT NOT NULL AUTO_INCREMENT,
  `HeroName` VARCHAR(45) NOT NULL,
  `HeroDescription` VARCHAR(128) NOT NULL,
  `HeroSuperPower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`HeroID`),
  UNIQUE INDEX `HeroName_UNIQUE` (`HeroName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Organizations` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NOT NULL,
  `OrgDescription` VARCHAR(128) NOT NULL,
  `OrgAddress` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`OrgID`),
  UNIQUE INDEX `OrgName_UNIQUE` (`OrgName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Superhero_Organization_Affliliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Superhero_Organization_Affliliations` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `OrgID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_HeroOrg_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_HeroOrg_OrgID_idx` (`OrgID` ASC),
  CONSTRAINT `FK_HeroOrg_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHero_Sightings`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HeroOrg_OrgID`
    FOREIGN KEY (`OrgID`)
    REFERENCES `SuperHero_Sightings`.`Organizations` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Locations` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(128) NOT NULL,
  `LocationAddress` VARCHAR(128) NOT NULL,
  `Latitude` DECIMAL NOT NULL,
  `Longitude` DECIMAL NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings`.`Sightings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings`.`Sightings` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `SightingTime` DATE NOT NULL,
  PRIMARY KEY (`SightingID`),
  INDEX `FK_Sightings_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_Sightings_LocationID_idx` (`LocationID` ASC),
  CONSTRAINT `FK_Sightings_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHero_Sightings`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sightings_LocationID`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperHero_Sightings`.`Locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Database SuperHero_Sightings_Test
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `SuperHero_Sightings_Test` ;
CREATE DATABASE IF NOT EXISTS `SuperHero_Sightings_Test` ;
USE `SuperHero_Sightings_Test` ;

-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`SuperHeroes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`SuperHeroes` (
  `HeroID` INT NOT NULL AUTO_INCREMENT,
  `HeroName` VARCHAR(45) NOT NULL,
  `HeroDescription` VARCHAR(128) NOT NULL,
  `HeroSuperPower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`HeroID`),
  UNIQUE INDEX `HeroName_UNIQUE` (`HeroName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Organizations` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NOT NULL,
  `OrgDescription` VARCHAR(128) NOT NULL,
  `OrgAddress` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`OrgID`),
  UNIQUE INDEX `OrgName_UNIQUE` (`OrgName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Superhero_Organization_Affliliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Superhero_Organization_Affliliations` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `OrgID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_HeroOrg_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_HeroOrg_OrgID_idx` (`OrgID` ASC),
  CONSTRAINT `FK_HeroOrg_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHero_Sightings_Test`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HeroOrg_OrgID`
    FOREIGN KEY (`OrgID`)
    REFERENCES `SuperHero_Sightings_Test`.`Organizations` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Locations` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(128) NOT NULL,
  `LocationAddress` VARCHAR(128) NOT NULL,
  `Latitude` DECIMAL NOT NULL,
  `Longitude` DECIMAL NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHero_Sightings_Test`.`Sightings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHero_Sightings_Test`.`Sightings` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `SightingTime` DATE NOT NULL,
  PRIMARY KEY (`SightingID`),
  INDEX `FK_Sightings_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_Sightings_LocationID_idx` (`LocationID` ASC),
  CONSTRAINT `FK_Sightings_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHero_Sightings_Test`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sightings_LocationID`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperHero_Sightings_Test`.`Locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

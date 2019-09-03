-- -----------------------------------------------------
-- Database SuperHeroSightings
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `SuperHeroSightings` ;
CREATE DATABASE IF NOT EXISTS `SuperHeroSightings` ;
USE `SuperHeroSightings` ;

-- -----------------------------------------------------
-- Table `SuperHeroSightings`.`SuperHeroes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHeroSightings`.`SuperHeroes` (
  `HeroID` INT NOT NULL AUTO_INCREMENT,
  `HeroName` VARCHAR(45) NOT NULL,
  `HeroDescription` VARCHAR(128) NOT NULL,
  `HeroSuperPower` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`HeroID`),
  UNIQUE INDEX `HeroName_UNIQUE` (`HeroName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHeroSightings`.`Organizations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHeroSightings`.`Organizations` (
  `OrgID` INT NOT NULL AUTO_INCREMENT,
  `OrgName` VARCHAR(45) NOT NULL,
  `OrgDescription` VARCHAR(128) NOT NULL,
  `OrgAddress` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`OrgID`),
  UNIQUE INDEX `OrgName_UNIQUE` (`OrgName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHeroSightings`.`SuperheroOrganizationAffliliations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHeroSightings`.`SuperheroOrganizationAffliliations` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `OrgID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_HeroOrg_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_HeroOrg_OrgID_idx` (`OrgID` ASC),
  CONSTRAINT `FK_HeroOrg_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHeroSightings`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_HeroOrg_OrgID`
    FOREIGN KEY (`OrgID`)
    REFERENCES `SuperHeroSightings`.`Organizations` (`OrgID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHeroSightings`.`Locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHeroSightings`.`Locations` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(45) NOT NULL,
  `LocationDescription` VARCHAR(128) NOT NULL,
  `LocationAddress` VARCHAR(128) NOT NULL,
  `Latitude` DECIMAL NOT NULL,
  `Longitude` DECIMAL NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperHeroSightings`.`Sightings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SuperHeroSightings`.`Sightings` (
  `SightingID` INT NOT NULL AUTO_INCREMENT,
  `HeroID` INT NOT NULL,
  `LocationID` INT NOT NULL,
  `SightingTime` DATETIME NOT NULL,
  PRIMARY KEY (`SightingID`),
  INDEX `FK_Sightings_HeroID_idx` (`HeroID` ASC),
  INDEX `FK_Sightings_LocationID_idx` (`LocationID` ASC),
  CONSTRAINT `FK_Sightings_HeroID`
    FOREIGN KEY (`HeroID`)
    REFERENCES `SuperHeroSightings`.`SuperHeroes` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sightings_LocationID`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperHeroSightings`.`Locations` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
